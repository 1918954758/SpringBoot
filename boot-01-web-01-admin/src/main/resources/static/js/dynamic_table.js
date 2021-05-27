function addUser(obj) {
    document.getElementById('panelAddUserId').style.display='block';
    document.getElementById('fade').style.display='block';
    document.getElementById('cpId').value = obj;
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
    var cpId = $('#cpId').val();//当前页数
    $.ajax({
        type: "post",
        url: "/add/user",
        data: {
            "name": name,
            "age": age,
            "email": email,
            "cpId": cpId
        },
        dateType: "text",
        success: function (json){

        }
    });
}