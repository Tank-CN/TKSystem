<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<content tag="cssconfig">
    <link href="${ctx}/res-build/css/sys.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/res-build/res/module/ajaxpage/css/page.css" rel="stylesheet" type="text/css"/>
</content>
<body>
<!-- BEGIN PAGE HEADER-->
<h3 class="page-title">
    APP更新
</h3>

<div class="page-bar clearfix">
    <ul class="page-breadcrumb">
        <li><i class="iconfont ico-home">&#xe60a;</i> <a href="index.html">主页</a> <i class="iconfont ico-angle-right">
            &#xe605;</i></li>
        <li><a href="#">APP更新维护</a> <i class="iconfont ico-angle-right">&#xe605;</i></li>
        <li><a href="#">APP更新列表</a></li>
    </ul>
</div>
<!-- END PAGE HEADER-->

<!-- BEGIN PAGE CONTENT-->
<div class="row">
    <div class="col-md-12">
        <div class="portlet">
            <div class="portlet-title clearfix">

                <div class="caption">
                    <i class="iconfont">&#xe61a;</i>APP更新信息
                </div>
                <div class="actions">

                    <%--<a class="btn btn-success btn-sm" href="#" data-toggle="modal" data-target="#addModal">
                        <i class="iconfont">&#xe612;</i>
                        <span class="hidden-480">添加APP更新</span>
                    </a>--%>

                </div>

            </div>
            <div class="portlet-body" id="appversion-list">
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
                           id="datatable_ajax">

                        <thead>
                        <tr role="row">
                            <th class="sorting">app名称</th>
                            <th class="sorting">App类型</th>
                            <th class="sorting">App版本</th>
                            <th>更新时间</th>
                            <th>操作</th>
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
    <div>
        <input type="hidden" id="webBasePath" value="${ctx}">
    </div>
</div>
<!-- END PAGE CONTENT-->
<div class="modal fade" id="modifyModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">APP更新维护</h4>
            </div>
            <form class="form-horizontal" id="appModifyForm" enctype="multipart/form-data">
                <div class="modal-body">
                    <div class="portlet-body form">
                        <div class="form-body">
                            <div class="form-group">
                                <label class="col-md-3 control-label">App名称</label>

                                <div class="col-md-8">
                                    <input type="hidden" name="id" id="appIdHidde">
                                    <input type="text" class="form-control" name="appname" placeholder="App名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">App类型</label>

                                <div class="col-md-8">
                                    <select name="appcode" id="appcode" class="form-control">
                                        <option value="Android">Android</option>
                                        <option value="IOS">IOS</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">App版本</label>

                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="appversion" placeholder="App版本">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">是否强制</label>

                                <div class="col-md-8">
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
                            <div class="form-group" style="display: none">
                                <label class="col-md-3 control-label">App文件</label>

                                <div class="col-md-8">
                                    <input type="file" id="file" name="file" class="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">更新说明</label>

                                <div class="col-md-8">
                                    <textarea class="form-control" rows="4" placeholder="更新说明" name="des"></textarea>
                                </div>
                            </div>
                        </div>
                        <!-- END FORM-->
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success">修改</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
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
    <script type="text/javascript" src="${ctx}/res-build/config.js" data-init="appversion.js"></script>

</content>

