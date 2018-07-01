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
    <i class="iconfont">&#xe619;</i> 用户管理
</h3>

<div class="page-bar clearfix">
    <ul class="page-breadcrumb">
        <li><i class="iconfont ico-home">&#xe60a;</i> <a href="index.html">主页</a> <i class="iconfont ico-angle-right">
            &#xe605;</i></li>
        <li><a href="#">系统配置</a> <i class="iconfont ico-angle-right">&#xe605;</i></li>
        <li><a href="#">用户管理</a></li>
    </ul>
</div>

<!-- END PAGE HEADER-->

<!-- BEGIN PAGE CONTENT-->
<div class="row">
    <div class="col-md-12">
        <div class="portlet">
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
                            <th class="sorting_desc">用户名
                            </th>
                            <th class="sorting">姓名
                            </th>
                            <th class="sorting">性别
                            </th>
                            <th class="sorting">证件号码
                            </th>
                            <th class="sorting">生日</th>
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
    <script type="text/javascript" src="${ctx}/res-build/config.js" data-init="user-list.js"></script>

</content>

</body>
