# Mybatis-Plus 添加修改用户

## 1. 添加用户
#### 1. 准备新增按钮
```html
<a th:onclick="addUser([[${pages.current}]])" class="btn btn-primary btn-sm">新增用户</a>
```
#### 2. 增加弹出框
```html
<!-- addUser -->
<div name="panelAddUserName" id="panelAddUserId" class="white_content" style="display: none"><br>
    <input type="hidden" name="addCurrentPageName" id="addCurrentPageId"/>
    <form name="panelAddUserName4Form" id="panelAddUserId4Form">
        姓名：<input type="input" id="addName" class="form-control" placeholder="姓名" autofocus/>
        年龄：<input type="input" id="addAge" class="form-control" placeholder="年龄"/>
        邮箱：<input type="input" id="addEmail" class="form-control" placeholder="邮箱"/>
        <br/>
        <div>
            <button class="glyphicon glyphicon-ok" th:onclick="addUserSubmit()" type="submit">添加</button>
            <button class="glyphicon glyphicon-remove" onclick="cancelAddOrUpdateUser()">取消</button>
        </div>
        <!-- http://v3.bootcss.com/getting-started/#download -->
    </form>
</div>
<div id="addFade" class="black_overlay" style="display: none"></div>
```

#### 3. 添加css
```css
.black_overlay{
	display: none;
	position: absolute;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 100%;
	background-color: black;
	z-index:1001;
	-moz-opacity: 0.8;
	opacity:.80;
	filter: alpha(opacity=88);
}
.white_content {
	display: none;
	position: absolute;
	top: 25%;
	left: 25%;
	width: 55%;
	height: 55%;
	padding: 20px;
	border: 10px solid orange;
	background-color: white;
	z-index:1002;
	overflow: auto;
}
```
#### 4. 添加js
```js
function addUser(obj) {
    document.getElementById('panelAddUserId').style.display='block';
    document.getElementById('addFade').style.display='block';
    document.getElementById('addCurrentPageId').value = obj;
}

function addUserSubmit(obj) {
    var id = $('#updateId').val();
    var name = $('#addName').val();
    var age = $('#addAge').val();
    var email = $('#addEmail').val();
    var currentPage = $('#addCurrentPageId').val();//当前页数
    $.ajax({
        type: "post",
        url: "/add/user",
        data: {
            "id": id,
            "name": name,
            "age": age,
            "email": email,
            "currentPage": currentPage
        },
        dateType: "text",
        success: function (json){

        }
    });
}

function cancelAddOrUpdateUser() {
    document.getElementById('panelAddUserId').style.display='none';
    document.getElementById('addFade').style.display='none';
}
```

#### 5. 添加Controller
```java
@Controller
@Slf4j
class TableController {
    @PostMapping("/add/user")
    public String addUser(User user, RedirectAttributes redirectAttributes) {
        userService.save(user);
        redirectAttributes.addAttribute("currentPage", user.getCurrentPage());
        return "redirect:/dynamic_table";
    }
}
```


## 2. 修改用户
#### 1. 准备更新按钮
```html
<a th:onclick="updateUser([[${pages.current}]], [[${user.id}]], [[${user.name}]], [[${user.age}]], [[${user.email}]])" class="btn btn-default btn-sm">更新用户</a>
```
#### 2. 增加弹出框
```html
<!-- updateUser -->
<div name="panelUpdateUserName" id="panelUpdateUserId" class="white_content" style="display: none"><br>
    <input type="hidden" name="updateCurrentPageName" id="updateCurrentPageId"/>
    <form name="panelAddUserName4Form" id="panelUpdateUserId4Form">
        id：<input type="input" id="updateId" class="form-control" placeholder="id" readonly="readonly"/><br/>
        姓名：<input type="input" id="updateName" class="form-control" placeholder="姓名"/><br/>
        年龄：<input type="input" id="updateAge" class="form-control" placeholder="年龄"/><br/>
        邮箱：<input type="input" id="updateEmail" class="form-control" placeholder="邮箱"/><br/>
        <br/>
        <div>
            <button class="glyphicon glyphicon-ok" th:onclick="updateUserSubmit()" type="submit">更新</button>
            <button class="glyphicon glyphicon-remove" onclick="cancelAddOrUpdateUser()">取消</button>
        </div>
        <!-- http://v3.bootcss.com/getting-started/#download -->
    </form>
</div>
<div id="updateFade" class="black_overlay" style="display: none"></div>
```

#### 3. 添加css
```css
.black_overlay{
	display: none;
	position: absolute;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 100%;
	background-color: black;
	z-index:1001;
	-moz-opacity: 0.8;
	opacity:.80;
	filter: alpha(opacity=88);
}
.white_content {
	display: none;
	position: absolute;
	top: 25%;
	left: 25%;
	width: 55%;
	height: 55%;
	padding: 20px;
	border: 10px solid orange;
	background-color: white;
	z-index:1002;
	overflow: auto;
}
```

#### 4. 添加js
```js
function updateUser(currentPageId, id, name, age, email) {
    document.getElementById('panelUpdateUserId').style.display='block';
    document.getElementById('updateFade').style.display='block';
    document.getElementById('updateCurrentPageId').value = currentPageId;
    document.getElementById("updateId").value = id;
    document.getElementById("updateName").value = name;
    document.getElementById("updateAge").value = age;
    document.getElementById("updateEmail").value = email;
}

// 更新用户
function updateUserSubmit(obj) {
    var id = $('#updateId').val();
    var name = $('#updateName').val();
    var age = $('#updateAge').val();
    var email = $('#updateEmail').val();
    var currentPage = $('#updateCurrentPageId').val();

    $.ajax({
        type: "post",
        url: "/update/user",
        data: {
            "id": id,
            "name": name,
            "age": age,
            "email": email,
            "currentPage": currentPage
        },
        dateType: "text",
        success: function (json) {

        }
    });

}

function cancelAddOrUpdateUser() {
    document.getElementById('panelAddUserId').style.display='none';
    document.getElementById('addFade').style.display='none';
}
```

#### 5. 添加Controller
```java
@Controller
@Slf4j
class TableController {
    @PostMapping("/update/user")
    public String updateUser(User user, RedirectAttributes redirectAttributes) {
        userService.updateById(user);
        redirectAttributes.addAttribute("currentPage", user.getCurrentPage());
        return "redirect:/dynamic_table";
    }
}
```

## 3. 注意细节
- 默认情况下，Mybatis-Plus 新增自增主键的值是 19位随机数，
- 数据库配置了自增主键，Mybatis-Plus 需要配置
    - @TableId=(Type=IdType.AUTO) 或者
    - mybatis-plus.global-config.db-config.id-type=auto (自增，按照数据库自增)


