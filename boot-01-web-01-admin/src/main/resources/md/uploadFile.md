## 文件上传

### 1. 页面编写

```html
<!-- 文件上传固定写法 action、method、enctype -->
<form role="form" th:action="@{/upload}" method="post" enctype="multipart/form-data">
    <div class="form-group">
        <label for="exampleInputEmail1">邮箱</label>
        <input type="email" name="email" class="form-control" id="exampleInputEmail1" placeholder="Enter email">
    </div>
    <div class="form-group">
        <label for="exampleInputPassword1">用户名</label>
        <input type="text" name="userName" class="form-control" id="exampleInputPassword1" placeholder="Password">
    </div>
    <div class="form-group">
        <label for="exampleInputFile">头像</label>
        <input type="file" name="headerImg" id="exampleInputFile">
    </div>
    <div class="form-group">
        <label for="exampleInputFile">生活照</label>
        <input type="file" name="photos" multiple>
    </div>
    <div class="checkbox">
        <label>
            <input type="checkbox"> Check me out
        </label>
    </div>
    <button type="submit" class="btn btn-primary">提交</button>
</form>
```

### 2. 代码编写

```java
/**
 * @name: FormTestController
 * @description:
 * @author: zichen
 * @date: 2021/5/15  19:29
 */
@Controller
@Slf4j
public class FormTestController {

    @GetMapping("/form_layouts")
    public String form_layouts() {
        return "form/form_layouts";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("email") String email,
                         @RequestParam("userName") String userName,
                         @RequestPart("headerImg") MultipartFile headerImg,
                         @RequestPart("photos") MultipartFile[] photos) throws IOException {

        log.info("上传的信息：email = {}，userName = {}，headerImg = {}，photos = {}", email, userName, headerImg.getSize(), photos.length);

        if (!headerImg.isEmpty()) {
            String originalFilename = headerImg.getOriginalFilename();
            headerImg.transferTo(new File("E:\\idea-springboot\\SpringBoot\\boot-01-web-01-admin\\src\\main\\resources\\upload\\" + originalFilename));
        }
        if (photos.length > 0) {
            for (MultipartFile photo : photos) {
                if (!photo.isEmpty()) {
                    String originalFilename = photo.getOriginalFilename();
                    photo.transferTo(new File("E:\\idea-springboot\\SpringBoot\\boot-01-web-01-admin\\src\\main\\resources\\upload\\" + originalFilename));
                }
            }
        }
        return "index";
    }
}
```

### 3. 修改文件上传大小

```properties
# 单个文件上传大小
spring.servlet.multipart.max-file-size=2MB
# 整个请求大小
spring.servlet.multipart.max-request-size=10MB
```

### 4. 文件上传自动配置类

- MultiPartAutoConfiguration