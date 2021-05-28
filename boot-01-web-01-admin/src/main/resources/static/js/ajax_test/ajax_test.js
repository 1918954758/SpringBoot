function ajaxGet(o) {
    var id = 1;
    var name = "zichen";
    var page = 3;
    $.ajax({
        type: "get",
        url: "/test/ajax_get?id=" + id + "&name=" + name + "&page=" + page,
        dataType: "json",
        success: function (data) {
            console.log("success = " + data.zichen);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            var readyState = XMLHttpRequest.readyState;
            var status = XMLHttpRequest.status;
            console.log(readyState);
            console.log(status);
        }
    });
}

function ajaxPost(o) {
    ///test/ajax_post
    var id = 1;
    var name = "zichen";
    var page = 3;
    $.ajax({
        type: "post",
        url: "/test/ajax_post",
        data: {
            "id": id,
            "name": name,
            "page": page
        },
        dataType: "json",
        success: function (data) {
            console.log("success = " + data.zichen);
        }
    });
}