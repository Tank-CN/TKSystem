<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<content tag="cssconfig">
    <link href="${ctx}/res-build/css/sys.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/res-build/res/module/ajaxpage/css/page.css" rel="stylesheet" type="text/css"/>

</content>
<content tag="headerjsconfig">

</content>
<body>
<!-- BEGIN PAGE HEADER-->
<h3 class="page-title">
    系统配置
</h3>

<div class="page-bar clearfix">
    <ul class="page-breadcrumb">
        <li><i class="iconfont ico-home">&#xe60a;</i> <a href="index.html">主页</a> <i class="iconfont ico-angle-right">
            &#xe605;</i></li>
        <li><a href="#">系统配置</a> <i class="iconfont ico-angle-right">&#xe605;</i></li>
        <li><a href="#">角色管理</a></li>
    </ul>
</div>

<!-- END PAGE HEADER-->

<!-- BEGIN PAGE CONTENT-->
<div class="row">
    <div class="col-md-12">
        <div class="portlet">
            <div class="portlet-title clearfix">
                <div class="caption">
                    <i class="iconfont">&#xe60f;</i> 角色管理
                </div>
                <div class="actions">
                    <a class="btn btn-success btn-sm" href="${ctx}/admin/sys/roleModule/addView?pcode=Sys&subcode=SysRole">
                        <i class="iconfont">&#xe612;</i>
                        <span class="hidden-480">添加角色</span>
                    </a>
                </div>
            </div>
            <div class="portlet-body" id="role-list">
                <%--<input type="hidden" id="page-length" value="10">--%>
                <div class="table-pages clearfix">
                    <div class="table-page clearfix"></div>
                    <div class="page-length"><span class="seperator">|</span>每页显示 <select name="j-length"
                                                                                          aria-controls="datatable_ajax"
                                                                                          class="j-length input-xsmall input-sm input-inline">
                        <option value="10">10</option>
                        <option value="25">25</option>
                        <option value="50">50</option>
                        <option value="75">75</option>
                        <option value="100">100</option>
                    </select> 条记录
                    </div>
                    <div class="page-info" role="status" aria-live="polite"><span class="seperator">|</span>总共获取 <span
                            class="page-info-num"></span> 条记录
                    </div>
                </div>
                <div class="table-container">
                    <table class="table table-striped table-bordered table-hover dataTable no-footer"
                           id="datatable_ajax" aria-describedby="datatable_ajax_info" role="grid">

                        <thead>
                        <tr role="row">
                            <th class="sorting_desc">角色名称
                            </th>
                            <th class="sorting">角色编号
                            </th>
                            <th class="sorting" >角色状态
                            </th>
                            <th class="sorting" >角色说明
                            </th>
                            <th class="heading sorting_disabled">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <div class="table-pages clearfix">
                    <div class="table-page clearfix"></div>
                    <div class="page-length"><span class="seperator">|</span>每页显示 <select name="j-length"
                                                                                          aria-controls="datatable_ajax"
                                                                                          class="j-length input-xsmall input-sm input-inline">
                        <option value="10">10</option>
                        <option value="25">25</option>
                        <option value="50">50</option>
                        <option value="75">75</option>
                        <option value="100">100</option>
                    </select> 条记录
                    </div>
                    <div class="page-info" role="status" aria-live="polite"><span class="seperator">|</span>总共获取 <span
                            class="page-info-num"></span> 条记录
                    </div>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" id="webBasePath" value="${ctx}">
</div>
<!-- END PAGE CONTENT-->
<!-- END PAGE CONTENT-->

<content tag="jsconfig">
    <script type="text/javascript" src="${ctx}/res-build/config.js" data-init="role.js"></script>

</content>

</body>
