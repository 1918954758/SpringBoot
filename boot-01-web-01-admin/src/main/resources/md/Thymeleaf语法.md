# Thymeleaf

- 页面点击按钮，携带参数，请求后台controller
```html
<tr>
    <td th:href="@{/order/details(orderId=${o.id})}></td>
</tr>
```

- 页面获取请求域（View、Map等）中的数据
```html
<!-- 写法一：属性获取 -->
<tr>
    <td th:text="${users.user}"></td>
</tr>
<!-- 写法二：行内获取 -->
<tr>
    <td>[[${users.user}]]</td>
</tr>
```

- 页面遍历请求域（View、Map等）中的数据
```html
<!-- user: 表示拿到list中的一条数据 -->
<!-- stat：表示拿到数据的条数（从1开始） -->
<tr th:each="user,stat:${users}">
    <td th:text="${stat.count}")></td><!-- 属性获取 -->
    <td th:text="${user.id}")></td>
    <td th:text="${user.name}")></td>
    <td>[[${user.age}]]</td><!-- 行内获取 -->
</tr>
```

- 页面使用三元表达式，实现分页按钮高亮展示
```html
<li th:class="当前页 == 分页按钮显示页 ? 'active' : ''"> Dynamic Table </li>
```

- 实现分页小工具，数字序列
```html
/*
 * Create a sequence (array) of integer numbers going
 * from x to y
 */
${#numbers.sequence(from,to)}
${#numbers.sequence(from,to,step)}
```

- 发送请求，请求路径获取参数
```java
public class TableController {
    /**
     * 请求路径上获取参数   /delete/user/{1}
     * 此时没有请求名
     * @param id
     * @return
     */
    @GetMapping("/delete/user/{id}/{currentPage}")
    public String deleteUser(@PathVariable("id") Long id, @PathVariable("currentPage") Integer currentPage, RedirectAttributes redirectAttributes) {
        userService.removeById(id);
        redirectAttributes.addAttribute("currentPage", currentPage);
        return "redirect:/dynamic_table";
    }
}
```
```html
<!-- 请求路径放置参数 圆括号后面制定参数名以及参数从哪里获取 -->
<a th:href = "@{/delete/user/{id}(id=${user.id}, currentPage=${pages.current})}" type="btn btn-danger btn-sm" type = "button">Delete</a>
```

- 对比请求参数，请求路径参数差异
  
- 请求参数
```java
import org.springframework.web.bind.annotation.RequestParam;

public class TableController {
    /**
     * 请求路径参数：/delete/user?id=1&currentPage=3
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
```html
<a th:href = "@{/delete/user(id=${user.id}, currentPage=${pages.current})}" type="btn btn-danger btn-sm" type = "button">Delete</a>
```

- 请求路径参数
```java
import org.springframework.web.bind.annotation.RequestParam;

public class TableController {
    /**
     * 请求路径参数：/delete/user?id=1&currentPage=3
     * @param id
     * @param currentPage
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/delete/user/{id}/{currentPage}")
    public String deleteUser(@RequestParam("id") Long id, @RequestParam("currentPage") Integer currentPage, RedirectAttributes redirectAttributes) {
        userService.removeById(id);
        redirectAttributes.addAttribute("currentPage", currentPage);
        return "redirect:/dynamic_table";
    }
}
```
```html
<a th:href = "@{/delete/user/{id}/{currentPage}(id=${user.id}, currentPage=${pages.current})}" type="btn btn-danger btn-sm" type = "button">Delete</a>
```
