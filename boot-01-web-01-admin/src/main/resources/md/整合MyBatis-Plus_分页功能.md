# Mybatis-Plus_分页功能

## 1. 动态展示查询到的数据
- mapper层
```java
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User>{
}
```
- 调整service层
```java
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * service 层接口
 */
public interface UserService extends IService<User> {
}
```
```java
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/***
 * public class ServiceImpl<M extends BaseMapper<T>, T> implements IService<T>
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
```
- controller层

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TableController {

    @Autowired
    private UserService userService;

    @GetMapping("/dynamic_table")
    public String dynamic_table(Model model) {
        
        List<User> list = userService.list();
        model.addAttribute("users", list);
        return "table/dynamic_table";
    }
}
```
- dynamic_table.html
```html
<table class="display table table-bordered table-striped" id="dynamic-table">
    <thead>
        <tr>
            <th href="#">Dashboard</th>
            <th name="id">编号</th>
            <th name="name">姓名</th>
            <th name="email">邮箱</th>
            <th name="age">年龄</th>
        </tr>
    </thead>
    <tbody>
        <tr class="gradeX" th:each="user,stat:${users}">
            <td th:text="${stat.count}">Trident</td>
            <td th:text="${user.id}">Internet Explorer 4.0</td>
            <td th:text="${user.name}">Win 95+</td>
            <td th:text="${user.email}">4</td>
            <td class="center hidden-phone">[[${user.age}]]</td>
        </tr>
    </tbody>
    <tfoot>
    </tfoot>
</table>
```

## 2. 动态展示查询到的数据（分页展示）
- Mybatis-Plus实现分页查询，需要使用官方提供的插件

```java
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
        // 分页溢出总页数是否进行处理
        paginationInnerInterceptor.setOverflow(true);
        paginationInnerInterceptor.setMaxLimit(500L);
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        return interceptor;
    }
}
```


- mapper层
```java
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User>{
}
```
- 调整service层
```java
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * service 层接口
 */
public interface UserService extends IService<User> {
}
```
```java
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/***
 * public class ServiceImpl<M extends BaseMapper<T>, T> implements IService<T>
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
```
- controller层

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TableController {

    @Autowired
    private UserService userService;

    @GetMapping("/dynamic_table")
    public String dynamic_table(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage, Model model) {
        
        /*List<User> list = userService.list();
        model.addAttribute("users", list);*/
        Page<User> userPage = new Page<>(currentPage, 2);
        Page<User> page = userService.page(userPage);
        model.addAttribute("pages", page);
        return "table/dynamic_table";
    }
}
```
- dynamic_table.html
```html
<div class="panel-body">
    <div class="adv-table">
        <table class="display table table-bordered table-striped" id="dynamic-table">
            <thead>
                <tr>
                    <th href="#">Dashboard</th>
                    <th name="id">编号</th>
                    <th name="name">姓名</th>
                    <th name="email">邮箱</th>
                    <th name="age">年龄</th>
                </tr>
            </thead>
            <tbody>
                <!-- pages拿到的是一个一个Records,二每一个records又是一个User，故此可以使用 pages.records.id 方法来获取值 -->
                <tr class="gradeX" th:each="user,stat:${pages.records}">
                    <td th:text="${stat.count}">Trident</td>
                    <td th:text="${user.id}">Internet Explorer 4.0</td>
                    <td th:text="${user.name}">Win 95+</td>
                    <td th:text="${user.email}">4</td>
                    <td class="center hidden-phone">[[${user.age}]]</td>
                </tr>
            </tbody>
            <tfoot>
            </tfoot>
        </table>
        <div class="row-fluid">
            <div class="span6">
                <div class="dataTables_info" id="dynamic-table_info">
                    当前是第 [[${pages.current}]] 页，共 [[${pages.pages}]] 页，共 [[${pages.total}]] 条数据
                </div>
            </div>
            <div class="span6">
                <!--class="dataTables_paginate paging_bootstrap pagination" 展示上一页和下一页的样式-->
                <div class="dataTables_paginate paging_bootstrap pagination">
                    <ul>
                        <li class="prev disabled"><a href="#">← 前一页</a></li>
                        <!-- th:each="num:${#numbers.sequence(1, pages.pages)}" - 数字递增（1 -> 总页数） -->
                        <!-- th:class="${pages.current == num ? 'active' : ''}" - 使用三元运算符，高亮展示分页数字标签 -->
                        <li th:class="${pages.current == num ? 'active' : ''}" th:each="num:${#numbers.sequence(1, pages.pages)}">
                            <!-- th:href="@{/dynamic_table(currentPage=${num})}" - 点击分页数字标签，请求/dynamic_table，并且携带参数请求 (currentPage=${num}) （name=value）-->
                            <!-- [[${num}]] - 拿到循环出的分页数字标签展示 -->
                            <a href="#" th:href="@{/dynamic_table(currentPage=${num})}">[[${num}]]</a>
                        </li>
                        <li class="next disabled"><a href="#">后一页 → </a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
```

## 3. 用户删除功能
```java
@Controller
@Slf4j
public class TableController {

    @Autowired
    private UserService userService;

    /**
     * 使用请求路径参数的方式获取用户ID和当前页数
     * @param id
     * @param currentPage
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/delete/user/{id}/{currentPage}")
    public String deleteUser(@PathVariable("id") Long id, @PathVariable("currentPage") Integer currentPage, RedirectAttributes redirectAttributes) {
        userService.removeById(id);
        redirectAttributes.addAttribute("currentPage", currentPage);
        return "redirect:/dynamic_table";
    }

    /**'
     * 使用请求参数的方式获取用户ID和当前页数
     * @param id
     * @param currentPage
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/delete/user")
    public String deleteUser(@RequestParam("id") Long id, @RequestParam("currentPage") Integer currentPage, RedirectAttributes redirectAttributes) {
        userService.removeById(id);
        redirectAttributes.addAttribute("currentPage", currentPage);
        return "redirect:/dynamic_table";
    }
}
```
- 注意，当点击删除按钮的时候，需要重定向到当前页面当前页数的位置
- 我们可以使用 RedirectAttributes.java 来实现参数的拼接
    - return "redirect:/dynamic_table";
    - 底层: /dynamic_table?currentPage=currentPage
```html
<tr class="gradeX" th:each="user,stat:${pages.records}">
    <td th:text="${stat.count}">Trident</td>
    <td th:text="${user.id}">Internet Explorer 4.0</td>
    <td th:text="${user.name}">Win 95+</td>
    <td th:text="${user.email}">4</td>
    <td class="center hidden-phone">[[${user.age}]]</td>
    <td>
        <!-- 使用请求参数路径的方式获取用户ID和当前页数 -->
        <a th:href="@{/delete/user/{id}/{currentPage}(id=${user.id}, currentPage=${pages.current})}" class="btn btn-danger btn-sm" type="button">Delete</a>
        <!-- 使用请求参数的方式获取用户ID和当前页数 -->
        <a th:href="@{/delete/user(id=${user.id}, currentPage=${pages.current})}" class="btn btn-danger btn-sm" type="button">Delete</a>
    </td>
</tr>
```