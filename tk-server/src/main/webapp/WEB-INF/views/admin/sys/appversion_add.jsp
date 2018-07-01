<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<body>
<h3 class="page-title">
    APP更新
</h3>

<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><i class="fa fa-home"></i> <a href="index.html">主页</a> <i class="fa fa-angle-right"></i></li>
        <li><a href="#">APP更新</a> <i class="fa fa-angle-right"></i></li>
        <li><a href="#">APP更新</a></li>
    </ul>
</div>
    <div class="row">
        <div class="col-md-12">
            <!-- BEGIN PAGE TITLE & BREADCRUMB-->
           <%-- <h3 class="page-title">
                问卷维护
                <small>评估问卷管理</small>
            </h3>
            <ul class="page-breadcrumb breadcrumb">
                <li><i class="fa fa-home"></i><a href="index.html">主页</a> <i class="fa fa-angle-right"></i></li>
                <li><a href="#">评估问卷维护</a> <i class="fa fa-angle-right"></i></li>
                <li><a href="#">添加问卷</a></li>
            </ul>--%>
            <!-- END PAGE TITLE & BREADCRUMB-->
        </div>
        <div class="col-md-12">
                <div class="portlet-body form">
                    <!-- BEGIN FORM-->
                    <form  class="form-horizontal" id="companyForm" enctype="multipart/form-data">
                        <div class="form-body">
                            <div class="form-group">
                                <label class="col-md-3 control-label">app名称</label>
                                <div class="col-md-4">
                                    <input type="text" class="form-control" name="appname" placeholder="app名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">App类型</label>
                                <div class="col-md-4">
                                    <select name="appcode" id="appcode" class="form-control">
                                        <option value="Android">Android</option>
                                        <option value="IOS">IOS</option>
                                    </select>
                                 <%-- //  <input type="text" class="form-control" name="appcode" placeholder="App类型">--%>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">App版本</label>
                                <div class="col-md-4">
                                    <input type="text" class="form-control" name="appversion" placeholder="App版本">
                                </div>
                            </div>
                            <div class="form-group" id="apk_file">
                                <label class="control-label col-md-3">APP文件</label>
                                <div class="col-md-4">
                                    <div id="selImg" class="form-control"> <i class="fa fa-plus"></i>
                                        <span>选择文件</span></div>
                                    <input type="file" id="file" name="file" class="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">是否强制</label>
                                <div class="col-md-4">
                                    <%--<select name="type" class="form-control">
                                        <option value="1">否</option>
                                        <option value="2">是</option>
                                    </select>--%>
                                    <label class="radio-inline">
                                        <input type="radio" name="type" checked
                                               value="1"> 否
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="type"
                                               value="0"> 是
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">更新说明</label>
                                <div class="col-md-4">
                                    <textarea class="form-control" rows="4" placeholder="更新说明" name="des"></textarea>
                                </div>
                            </div>


                        </div>
                        <div class="form-actions">
                            <div class="row">
                                <div class="col-md-offset-3 col-md-9">
                                    <button type="submit" class="btn green">保存</button>
                                    <button type="button" class="btn default">返回</button>
                                </div>
                            </div>
                        </div>
                    </form>
                    <!-- END FORM-->
                </div>
        </div>
    </div>
    <div>
        <input type="hidden" id="webBasePath" value="${ctx}">
    </div>
<script>

    $(function(){
        var options = {
            type: "POST",
            dataType: "json",
            url: "${ctx}/admin/sys/appversion/save",
            beforeSubmit:function(formData, jqForm, options){
                var isSuccess=$("#companyForm").validate().form();
                if(isSuccess)
                 $('body').modalmanager('loading');
                return isSuccess;
                // alert($("#summernote").find("textarea").val());
                //alert($.param(formData));
            },
            success:function(data){
                if (data) {
                    $('body').modalmanager('removeLoading');
                    var url = $('#webBasePath').val() + '/admin/sys/appversion';
                    ajaxLoadView(url);

                }else{
                    // alert()
                }
                /*    if(data){
                 var url = $('#webBasePath').val() + '/admin/ifm/art';
                 ajaxLoadView(url);
                 }*/
            } // post-submit callback

        };

        $('#companyForm').submit(function() {

            // 提交表单
            $(this).ajaxSubmit(options);
            // 为了防止普通浏览器进行表单提交和产生页面导航（防止页面刷新？）返回false
            return false;
        });
    });

    $("#selImg").on("click",function(){
        $("#exampleInputFile").click();
    });

    $("#exampleInputFile").on("change",function(){
        if($("#exampleInputFile").val()==""||$("#exampleInputFile").val()==undefined){
            $("#selImg span").html("选择图片");
        }else{
            var filePos=$("#exampleInputFile").val().lastIndexOf(".");
            var ext = $("#exampleInputFile").val().substring(filePos+1,$("#exampleInputFile").val().length);
           // alert();
            if(ext.toLowerCase()!="apk"){
                alert("不支持该文件上传!");
                $("#selImg span").html("选择图片");
                $("#exampleInputFile").val(null);
                return false;
            }
            var pos=$("#exampleInputFile").val().lastIndexOf("\\");
            $("#selImg span").html(($("#exampleInputFile").val().substring(pos+1).length>15)?$("#exampleInputFile").val().substring(pos+1).substring(0,15)+"...":$("#exampleInputFile").val().substring(pos+1));
        }
    });

    $("#appcode").on("change",function(){
        if($(this).val().split("_")[1]=="android"){
            $("#apk_file").show();
        }else{
            $("#apk_file").hide();
            $("#selImg span").html("选择图片");
            $("#exampleInputFile").val(null);
        }

    });

 /*   function save(){
        $('#formSaveBtn').attr('disabled', true);
        var savePath = $('#webBasePath').val() + '/admin/mdc/drug/save';
        $.post(savePath, $('#companyForm').serialize(), function(data){
            var url = $('#webBasePath').val() + '/admin/mdc/drug';
            ajaxLoadView(url);
        });
    }*/

    function ajaxLoadView(url){
        var pageContentBody = $('.page-content .page-content-body');
        $.ajax({
            type: "GET",
            cache: false,
            url: url,
            dataType: "html",
            success: function (res) {
                Metronic.stopPageLoading();
                pageContentBody.html(res);
                Layout.fixContentHeight(); // fix content height
                Metronic.initAjax(); // initialize core stuff
            },
            error: function (xhr, ajaxOptions, thrownError) {
                pageContentBody.html('<h4>Could not load the requested content.</h4>');
                Metronic.stopPageLoading();
            }
        });
    }

    $('#companyForm').validate({
        rules:{
            appname:"required"
        },
        messages: {
            appname: "APP名称不能为空"
        }
    });
</script>
</body>

