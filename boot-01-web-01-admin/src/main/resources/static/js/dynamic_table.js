function addUser(obj) {
    document.getElementById('panelAddUserId').style.display='block';
    document.getElementById('addFade').style.display='block';
    document.getElementById('addCurrentPageId').value = obj;
}

function cancelAddOrUpdateUser() {
    document.getElementById('panelAddUserId').style.display='none';
    document.getElementById('addFade').style.display='none';
}

function updateUser(currentPageId, id, name, age, email) {
    document.getElementById('panelUpdateUserId').style.display='block';
    document.getElementById('updateFade').style.display='block';
    document.getElementById('updateCurrentPageId').value = currentPageId;
    document.getElementById("updateId").value = id;
    document.getElementById("updateName").value = name;
    document.getElementById("updateAge").value = age;
    document.getElementById("updateEmail").value = email;
}

//window.addEventListener('touchmove', addUser, { passive: false });
//window.addEventListener('touchmove', cancelAddUser, { passive: false });

// 新增用户
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
        dataType: "text",
        success: function (json){

        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {

        }
    });
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
        dataType: "text",
        success: function (json) {

        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {

        }
    });

}