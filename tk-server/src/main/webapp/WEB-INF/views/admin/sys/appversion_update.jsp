<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<content tag="cssconfig">
    <link href="${ctx}/res-build/css/sys.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/res-build/res/module/ajaxpage/css/page.css" rel="stylesheet" type="text/css"/>
</content>
<body>

<h3 class="page-title">
    APP更新
</h3>

<div class="page-bar clearfix">
    <ul class="page-breadcrumb">
            <li><i class="iconfont ico-home">&#xe60a;</i> <a href="index.html">主页</a> <i class="iconfont ico-angle-right">
                &#xe605;</i></li>
            <li><a href="#">APP更新维护</a> <i class="iconfont ico-angle-right">&#xe605;</i></li>
            <li><a href="#">APP更新</a></li>
        </ul>
</div>

    <div class="row">

        <div class="col-md-12">
                <div class="portlet-body form">
                    <!-- BEGIN FORM-->
                    <form  class="form-horizontal" id="companyForm" enctype="multipart/form-data">
                        <input type="hidden" value="${id}" name="id"/>
                        <input type="hidden" value="android_pub" name="appcode"/>
                        <div class="form-body">
                            <div class="form-group">
                                <label class="col-md-3 control-label">app名称</label>
                                <div class="col-md-4">
                                    <input type="text" class="form-control" name="appname" disabled placeholder="app名称">
                                </div>
                            </div>
                            <%--<div class="form-group">
                                <label class="col-md-3 control-label">App类型</label>
                                <div class="col-md-4">
                                    <select name="appcode" id="appcode" class="form-control">
                                        <option value="andoid_pub">Android</option>
                                        <option value="IOS">IOS</option>
                                    </select>
                                    &lt;%&ndash; //  <input type="text" class="form-control" name="appcode" placeholder="App类型">&ndash;%&gt;
                                </div>
                            </div>--%>
                            <div class="form-group">
                                <label class="col-md-3 control-label">App版本</label>
                                <div class="col-md-4">
                                    <input type="text" class="form-control" name="appversion"
                                           placeholder="App版本，必须是正整数">
                                </div><div class="col-md-4" style="line-height: 34px; color: #888888">
                                                                    App版本必须为正整数</div>
                            </div>
                            <%--<div class="form-group" id="apk_file">--%>
                                <%--<label class="control-label col-md-3">APP文件</label>--%>
                                <%--<div class="col-md-4">--%>
                                    <%--<div id="selImg" class="form-control"> <i class="fa fa-plus"></i>--%>
                                        <%--<span>选择文件</span></div>--%>
                                    <%--<input type="file" id="file" name="file" class="">--%>

                                <%--</div>--%>
                            <%--</div>--%>
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
                                               value="2"> 是
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
                                    <button type="submit" class="btn btn-success j-save">保存</button>
                                    <button type="button" class="btn btn-default">返回</button>
                                </div>
                            </div>
                        </div>
                    </form>
                    <!-- END FORM-->
                </div>
        </div>
    <div>
        <input type="hidden" id="webBasePath" value="${ctx}">
    </div>
</div>

<div class="modal fade bs-example-modal-sm" id="modal-box" tabindex="-1" role="dialog"
      aria-labelledby="mySmallModalLabel" aria-hidden="true">
     <div class="modal-dialog modal-sm">

         <div class="modal-content">

             <div class="modal-body">
                 <div class="dialogtip-con-wrap clearfix dialogtipg-success">
                     <div class="dialogtip-icon iconfont">&#xe614;</div>
                     <div class="dialogtip-con">
                         <h4 class="dialogtip-tit">操作成功</h4>

                         <div class="dialogtip-msg">数据添加成功</div>
                     </div>
                 </div>
             </div>
             <div class="modal-footer">
                 <a href="${ctx}/admin/sys/appversion?pcode=Sys&subcode=APPUpdate"
                    class="btn btn-success">返回</a>
                 <%--<button type="button" class="btn btn-danger" data-dismiss="modal">继续添加</button>--%>
             </div>
         </div>

     </div>
 </div>
</body>
<content tag="jsconfig">
    <script type="text/javascript" src="${ctx}/res-build/config.js" data-init="appversion-update.js"></script>

</content>

