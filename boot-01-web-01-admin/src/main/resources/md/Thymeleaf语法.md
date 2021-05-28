# Thymeleaf

### 1. 页面点击按钮，携带参数，请求后台controller
```html
<tr>
    <td th:href="@{/order/details(orderId=${o.id})}></td>
</tr>
```

### 2. 页面获取请求域（View、Map等）中的数据
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

### 3. 页面遍历请求域（View、Map等）中的数据
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

### 4. 页面使用三元表达式，实现分页按钮高亮展示
```html
<li th:class="当前页 == 分页按钮显示页 ? 'active' : ''"> Dynamic Table </li>
```

### 5. 实现分页小工具，数字序列
```html
/*
 * Create a sequence (array) of integer numbers going
 * from x to y
 */
${#numbers.sequence(from,to)}
${#numbers.sequence(from,to,step)}
```

### 6. 发送请求，请求路径获取参数
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

### 7. 对比请求参数，请求路径参数差异
  
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
### 8. Thymeleaf点击事件传参
```html
<!-- 可穿一个或者多个 -->
<!-- 一个 -->
<a th:onclick="'javascript:addUser(' + ${pages.current} + ')'">新增用户</a>
<a th:onclick="addUser([[${pages.current}]])" class="btn btn-primary btn-sm">新增用户</a>
<!-- 多个 -->
<a th:onclick="updateUser([[${pages.current}]], [[${user.id}]], [[${user.name}]], [[${user.age}]], [[${user.email}]])" class="btn btn-default btn-sm">更新用户</a>
```

#### 9. js
- 1. 给input赋值
```js
document.getElementById('id').value = obj;
```
2. div显示和隐藏
```js
/* 显示 */
document.getElementById('divId').style.display='block';
/* 隐藏 */
document.getElementById('divId').style.display='none';
```
- 3. ajax发送请求
```js
/* post */
function sendPost(o) {
    var id = $('#id').val();
    var name = $('#name').val();
    $.ajax({
        type: "post",
        url: "/add/user",
        data: {
            "id": 1,
            "name": "zhangsan"
        },
        dataType: "text"
        success: function (json) {
            console.log('success', json);
        }
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            console.log('error', errorThrown);
        }
    });
}
/* get */
function sendGet(o) {
    var id = $('#id').val();
    var name = $('#name').val();
    $.ajax({
        type: "get",
        url: "http://localhost:8080/find/user",
        dataType: "json"
        success: function (json) {
            console.log('success', data);
        }
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            console.log('error', errorThrown);
        }
    });
}
```

- 4. 原生js实现ajax发送请求
```js
/**
 * 实现步骤
 * 1、建立XMLHttpRequest对象；
 * 2、设置回调函数；
 * 3、使用open方法与服务器建立链接；
 * 4、向服务器发送数据；
 * 5、在回调函数中针对不同的响应状态进行处理；
  */
function js(o) {
    // 原生ajax实现，非常的简单
    // function ajax() {
    //     var xhr = new XMLHttpRequest();
    //     xhr.open('get', 'http://localhost:8080/readNum');
    //     xhr.send();
    //     xhr.onreadystatechange = function() {
    //         if (xhr.readyState === 4) {
    //             console.log('success', xhr.responseText);
    //         } else {
    //             console.log('error', xhr.responseText);
    //         }
    //     }
    // }

    var test = document.getElementById('test');
    test.onclick = function() {
        ajax({
            type: 'get',
            url: 'http://localhost:8080/readNum',
            dataType: 'json'
            success: function(data) {
                console.log('success', data);
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log('error', errorThrown);
            }
        });
    }

    //data传入的参数也需要做兼容处理，对于中文还需要encode转码
    function params(data) {
        //if (typeof data === 'string') {
        //    return data;
        //}
        if (typeof data === 'object') {
            var arr = [];
            for (var key in data) {
                arr.push(encodeURIComponent(key) + "=" + encodeURIComponent(data[key]));
            }
            return arr.join("&");
        }
        return data;
    }

    function ajax(options) {

        //非空处理
        options = options || {};

        //处理参数，给出默认值

        //请求方式
        options.type = options.type.toUpperCase() || 'GET';
        //是否异步
        options.async = options.async || true;
        //请求地址
        options.url = options.url || window.location.href;
        //请求参数
        options.data = options.data || '';
        //响应格式
        options.dataType = options.dataType || 'JSON';
        //超时时间
        options.timeout = options.timeout || 10000;
        //请求完成后的回调
        options.complete = options.complete || function(){};
        //请求成功后的回调
        options.success = options.success || function(){};
        //请求失败后的回调
        options.error = options.error || function(){};

        //初始化异步对象
        var xhr = new XMLHttpRequest();

        //参数处理
        var data = params(options.data);

        //需要判断get和post
        if (options.type === 'GET') {
            //打开请求，如果url已经有参数了，直接追加，没有从问号开始拼接
            if (options.url.indexOf('?') !== -1) {
                xhr.open(options.type, options.url + '&' + data);
            } else {
                xhr.open(options.type, options.url + '?' + data);
            }
            //发送请求，因为参数都跟在url后面，所以不用在send里面做任何处理
            xhr.send();
        }
        if (options.type === 'POST') {
            //打开请求
            xhr.open(options.type, options.url);
            //设置请求头，如果不写，就是浏览器默认，就是下面这个
            xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
            //发送请求，post请求的时候，会将data中的参数按照&拆分出来
            xhr.send(data);
        }


        //解析结果
        xhr.onreadystatechange = function() {

            //readyState五个状态 0:未发送请求，1:已发送请求，2:已经握手，3:正在处理请求，4:请求处理完成

            //0：请求未初始化（还没有调用 open()）;
            //1：请求已经建立，但是还没有发送（还没有调用 send()）;
            //2：请求已发送，正在处理中（通常现在可以从响应中获取内容头）;
            //3：请求在处理中，通常响应中已有部分数据可用了，但是服务器还没有完成响应的生成;
            //4：响应已完成，您可以获取并使用服务器的响应了;

            //console.log('xhr', xhr);

            if (xhr.readyState === 4) {

                options.complete();

                if (xhr.status === 200) {
                    options.success(xhr.responseText);
                } else {
                    options.error(xhr, xhr.status, xhr.statusText);
                }
            }

        }
    }
}
```