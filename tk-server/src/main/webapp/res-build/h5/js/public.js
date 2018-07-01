$(function() {
    var html = document.querySelector('html');
    var rem = $(window).width() / 70;
    html.style.fontSize = rem + "px";
   
    // 搜索按钮的点击
    
});

// 搜索按钮的点击
var myInput=document.getElementById("myInput");
if(myInput){
    myInput.addEventListener("input", changeButton);
}

$("#search").on('click', '.cancel', function(event) {
    event.preventDefault();

    $(this).hide().prev(".confirm").show().attr({
        disabled: 'disabled'
    });
    $(this).parent("#search").find("input").val("");
    $(".search_result").html("");
    $(".list").show();

});
$("#search").on('focus', 'input', function(event) {
    $(".list_result").show();
    $(".list").hide();
})

function changeButton() {
    $("#search").find('button').removeAttr('disabled');
}
// 数据加载中
function loading() {
    var loadCon = $(" <div id='loading'><div class='bg_master'></div><div class='toast'><p class='weui_toast_content'>数据加载中...</p></div></div>");
    $("body").append(loadCon);
}
// 数据加载结束
function stoploading() {
    $("#loading").remove();
}

// 获取url的字符串参数
function getParameter(param) {
    var query = window.location.search;

    var iLen = param.length;
    var iStart = query.indexOf(param);
    if (iStart == -1)
        return "";
    iStart += iLen + 1;
    var iEnd = query.indexOf("&", iStart);
    if (iEnd == -1)
        return query.substring(iStart);

    return query.substring(iStart, iEnd);
}
