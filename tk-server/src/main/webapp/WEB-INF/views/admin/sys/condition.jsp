<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<content tag="cssconfig">
    <link href="${ctx}/res-build/css/sys.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx}/res-build/res/module/ajaxpage/css/page.css" rel="stylesheet" type="text/css"/>
</content>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<body>
<!-- BEGIN PAGE HEADER-->
<h3 class="page-title">
    APP模块管理
</h3>

<div class="page-bar clearfix">
    <ul class="page-breadcrumb">
        <li><i class="iconfont ico-home">&#xe60a;</i> <a href="index.html">主页</a> <i class="iconfont ico-angle-right">
            &#xe605;</i></li>
        <li><a href="#">基础维护</a> <i class="iconfont ico-angle-right">&#xe605;</i></li>
        <li><a href="#">APP模块管理</a></li>
    </ul>
</div>
<!-- END PAGE HEADER-->

<div>
    <div class="row">
        <div class="col-md-12">
            <div class="portlet">
                <div class="portlet-title clearfix">
                    <div class="caption">
                        <i class="iconfont">&#xe62b;</i>${modulename} APP模块条件
                    </div>
                    <div class="actions">
                        <a class="btn btn-success btn-sm" href="#" data-toggle="modal"
                           data-target="#addAppModalCondition">
                            <i class="iconfont">&#xe612;</i>
                            <span class="hidden-480">添加APP模块条件</span>
                        </a>

                    </div>
                </div>
                <div class="portlet-body" id="condition-list">

                    <div class="table-pages clearfix">

                        <div class="page-info" role="status" aria-live="polite">总共获取 <span
                                class="page-info-num"></span> 条记录
                        </div>
                    </div>
                    <div class="table-container">
                        <table class="table table-striped table-bordered table-hover dataTable no-footer"
                               id="datatable_ajax">

                            <thead>
                            <tr role="row">
                                <th class="sorting_desc">模块名称
                                </th>
                                <th class="sorting">字段/条件
                                </th>
                                <th class="sorting">是否启用
                                </th>

                                <th class="heading sorting_disabled">操作</th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>


                </div>
            </div>
            <div>
                <ul id='paging'></ul>
            </div>
            <!-- End: life time stats -->
        </div>
    </div>
    <div>
        <input type="hidden" id="webBasePath" value="${ctx}">
    </div>
</div>

<div class="modal fade" id="addAppModalCondition" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">添加APP功能条件</h4>
            </div>
            <form class="form-horizontal" id="addConditionForm">
                <input type="hidden" name="appmoduleid" value="${id}">
                <input type="hidden" name="appmodulename" value="${modulename}">

                <div class="modal-body">
                    <div class="portlet-body form">
                        <div class="form-body">

                            <div class="form-group">
                                <label class="col-md-3 control-label">模块名称</label>

                                <div class="col-md-7">

                                    <p class="form-control-static">${modulename}</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">条件字段</label>

                                <div class="col-md-7">
                                    <%--<select name="fieldname" class="form-control">
                                        <option value="1">身份证</option>
                                        <option value="2">姓名</option>
                                        <option value="3">就诊卡</option>
                                    </select>--%>
                                    <label class="radio-inline">
                                        <input type="radio" name="fieldname" checked
                                               value="1"> 身份证
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="fieldname"
                                               value="2"> 姓名
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="fieldname"
                                               value="3"> 就诊卡
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="fieldname"
                                               value="4"> 手机号码
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">条件</label>

                                <div class="col-md-7">
                                    <select name="conditions" class="form-control">
                                        <option value="1">不为空</option>
                                    </select>
                                </div>
                            </div>

                            <!-- end form-->
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-success" id="formSaveBtn">保存</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </div>
            </form>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<div class="modal fade" id="modifyModalCondition" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">编辑APP功能条件</h4>
            </div>
            <form class="form-horizontal" id="modifyConditionForm">
                <input type="hidden" name="id" value="">
                <input type="hidden" name="appmoduleid" value="">
                <input type="hidden" name="appmodulename" value="">

                <div class="modal-body">
                    <div class="portlet-body form">
                        <div class="form-body">

                            <div class="form-group">
                                <label class="col-md-3 control-label">模块名称</label>

                                <div class="col-md-7">

                                    <p class="form-control-static">${modulename}</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">条件字段</label>

                                <div class="col-md-7">
                                    <%--<select name="fieldname" disabled class="form-control">
                                        <option value="1">身份证</option>
                                        <option value="2">姓名</option>
                                        <option value="3">就诊卡</option>
                                    </select>--%>
                                    <label class="radio-inline">
                                        <input type="radio" name="fieldname" disabled
                                               value="1"> 身份证
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="fieldname" disabled
                                               value="2"> 姓名
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="fieldname" disabled
                                               value="3"> 就诊卡
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="fieldname" disabled
                                               value="4"> 手机号码
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">条件</label>

                                <div class="col-md-7">
                                    <select name="conditions" disabled class="form-control">
                                        <option value="1">不为空</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">是否启用</label>

                                <div class="col-md-7">
                                    <%--<select name="flag" disabled class="form-control">
                                        <option value="1">启用</option>
                                        <option value="0">禁用</option>
                                    </select>--%>
                                    <label class="radio-inline">
                                       <input type="radio" name="flag" disabled
                                              value="1"> 启用
                                   </label>
                                   <label class="radio-inline">
                                       <input type="radio" name="flag" disabled
                                              value="0"> 禁用
                                   </label>
                                </div>
                            </div>
                            <!-- end form-->
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-success j-form-edit"><i class="iconfont">&#xe61c;</i>我要编辑
                        </button>
                        <button type="submit" class="btn btn-success j-form-save" style="display: none"><i
                                class="iconfont">&#xe62c;</i>保存
                        </button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </div>
            </form>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
</body>

<content tag="jsconfig">
    <script type="text/javascript">
        var conditionId =${id};
        var moduleName = "${modulename}";
    </script>
    <script type="text/javascript" src="${ctx}/res-build/config.js" data-init="condition.js"></script>

</content>
