function addUser(obj) {
    document.getElementById('panelAddUserId').style.display='block';
    document.getElementById('fade').style.display='block';
    document.getElementById('currentPageId').value = obj;
}

function cancelAddUser() {
    document.getElementById('panelAddUserId').style.display='none';
    document.getElementById('fade').style.display='none';
}

//window.addEventListener('touchmove', addUser, { passive: false });
//window.addEventListener('touchmove', cancelAddUser, { passive: false });

function addUserSubmit(obj) {
    var name = $('#name').val();
    var age = $('#age').val();
    var email = $('#email').val();
    var currentPage = $('#currentPageId').val();//当前页数
    $.ajax({
        type: "post",
        url: "/add/user",
        data: {
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