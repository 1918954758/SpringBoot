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