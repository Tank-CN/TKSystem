<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<content tag="cssconfig">
    <link href="${ctx}/res-build/css/sys.css" rel="stylesheet" type="text/css"/>

</content>
<content tag="headerjsconfig">

</content>

<body>
<div class="row">
    <div class="col-md-12">
        <!-- BEGIN PAGE TITLE & BREADCRUMB-->
        <h3 class="page-title">
            系统配置
            <small>角色管理</small>
        </h3>

        <div class="page-bar clearfix">
            <ul class="page-breadcrumb">
                <li><i class="iconfont ico-home">&#xe60a;</i> <a href="index.html">主页</a> <i
                        class="iconfont ico-angle-right">
                    &#xe605;</i></li>
                <li><a href="${ctx}/admin/sys/roleModule/addView">角色管理</a> <i class="iconfont ico-angle-right">
                    &#xe605;</i></li>
                <li><a href="#">添加角色</a></li>
            </ul>
        </div>
        <!-- END PAGE TITLE & BREADCRUMB-->
    </div>
    <div class="col-md-12">
        <div class="portlet-body form">
            <!-- BEGIN FORM-->
            <form class="form-horizontal" id="form_create">
                <div class="form-body">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label col-md-3">角色名称</label>

                                <div class="col-md-9">
                                    <input type="text" name="title" data-required="1" class="form-control"
                                           placeholder="角色名称">
                                </div>
                            </div>
                        </div>
                        <!--/span-->
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label col-md-3">角色编号</label>

                                <div class="col-md-9">
                                    <input type="text" name="code" data-required="1" class="form-control"
                                           placeholder="角色自定义编号">
                                </div>
                            </div>
                        </div>
                        <!--/span-->
                    </div>
                    <div class="row">
                        <div class="col-md-12 ">
                            <div class="form-group">
                                <label class="control-label col-md-3" style="width: 12.5%">角色介绍</label>

                                <div class="col-md-9" style="width: 87.5%">
                                    <textarea name="intro" placeholder="角色介绍" class="form-control" rows="3"></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-actions right">
                    <div class="row">
                        <div class="col-md-offset-3 col-md-9">
                            <button type="submit" class="btn btn-success" id="fromSubmitBtn"><i class="iconfont">&#xe62c;</i>保存</button>
                            <a type="button" class="btn btn-default" href="${ctx}/admin/sys/role?pcode=Sys&subcode=SysRole">返回</a>
                        </div>
                    </div>
                </div>
            </form>
            <!-- END FORM-->
            <div id="auth-maintain" class="auth-maintain">
                <h4 class="form-section" id="authH">维护权限</h4>

                <div id="moduleResTableWrap">
                    <table class="table table-hover" id="moduleResTable">
                        <thead>
                        <tr>
                            <th>模块名称</th>
                            <th>模块编码</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>


                        </tbody>
                    </table>
                </div>

            </div>

        </div>
    </div>
</div>
<div>
    <input type="hidden" id="webBasePath" value="${ctx}">
    <input type="hidden" id="roleId">
</div>
<div class="modal fade bs-example-modal-sm" id="modal-box" tabindex="-1" role="dialog"
     aria-labelledby="mySmallModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">

        <div class="modal-content">

            <div class="modal-body">
                <div class="dialogtip-con-wrap clearfix dialogtipg-error">
                    <div class="dialogtip-icon iconfont">&#xe616;</div>
                    <div class="dialogtip-con">
                        <h4 class="dialogtip-tit">操作失败</h4>
                        <div class="dialogtip-msg">请先添加上级模块权限</div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <%--<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>--%>
                <button type="button" class="btn btn-danger" data-dismiss="modal">我知道了</button>
            </div>
        </div>

    </div>
</div>

</body>
<content tag="jsconfig">
    <script type="text/javascript" src="${ctx}/res-build/config.js" data-init="role-add.js"></script>

</content>
