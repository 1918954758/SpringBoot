function ajaxGet(o) {
    var id = 1;
    var name = "zichen";
    var page = 3;
    $.ajax({
        type: "get",
        url: "/test/ajax_get?id=" + id + "&name=" + name + "&page=" + page,
        dataType: "json",
        success: function (json) {
            console.log("success");
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log("error");
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
        success: function (json) {

        }
    });
}