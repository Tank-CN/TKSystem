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
                <li><a href="${ctx}/admin/sys/template/addView?pcode=Sys&subcode=Template">模板管理</a> <i
                        class="iconfont ico-angle-right">
                    &#xe605;</i></li>
                <li><a href="#">添加模板</a></li>
            </ul>
        </div>
        <!-- END PAGE TITLE & BREADCRUMB-->
    </div>
    <div class="col-md-12">
        <div class="portlet-body form">
            <!-- BEGIN FORM-->
            <form class="form-horizontal" id="form_edit">
                <div class="form-body">
                    <div class="form-group">
                        <label class="control-label col-md-3">模板名称</label>

                        <div class="col-md-4">
                            <input type="text" name="name" class="form-control"
                                   placeholder="请输入模板名称，例如：门诊预约">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">模板类型</label>

                        <div class="col-md-4">
                            <label class="radio-inline">
                              <input type="radio" name="type" id="type1" checked value="1"> 消息
                            </label>
                            <label class="radio-inline">
                              <input type="radio" name="type" id="type2" value="2"> 短信
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-3">模板内容</label>

                        <div class="col-md-5">
                            <textarea id="text" name="text" class="form-control" rows="5"></textarea>

                            <div class="variable-wrap clearfix">
                                <div class="tit">变量：</div>
                                <div class="variable-con">
                                    <button type="button" class="btn btn-info btn-xs j-variable-btn">#姓名#</button>
                                    <button type="button" class="btn btn-info btn-xs j-variable-btn">#日期#</button>
                                    <button type="button" class="btn btn-info btn-xs j-variable-btn">#时间#</button>
                                    <button type="button" class="btn btn-info btn-xs j-variable-btn">#星期几#</button>
                                    <button type="button" class="btn btn-info btn-xs j-variable-btn">#医院名称#</button>
                                    <button type="button" class="btn btn-info btn-xs j-variable-btn">#科室名称#</button>
                                    <button type="button" class="btn btn-info btn-xs j-variable-btn">#医生姓名#</button>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="helper-warp">
                                <h3><i class="iconfont">&#xe62f;</i>模板编辑小贴士：</h3>
                                <ul>
                                    <li>1.添加变量:点击文本框下面的变量按钮，可以在文本框中自动增加变量，系统会在发送时自动替换为用户相应的数据。</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">自定义代码</label>

                        <div class="col-md-4">
                            <input type="text" name="code" class="form-control"
                                   placeholder="请输入自定义代码">
                        </div>
                    </div>
                    <div class="form-actions">
                        <div class="row">
                            <div class="col-md-offset-3 col-md-9">
                                <button type="submit" class="btn btn-success"><i class="iconfont">
                                    &#xe62c;</i>保存
                                </button>
                                <a type="button" class="btn btn-default"
                                   href="${ctx}/admin/sys/template?pcode=Sys&subcode=Template">返回</a>
                            </div>
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
<div class="modal fade bs-example-modal-sm" id="modal-box" tabindex="-1" role="dialog"
     aria-labelledby="mySmallModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-body">
                <div class="dialogtip-con-wrap clearfix dialogtipg-success">
                    <div class="dialogtip-icon iconfont">&#xe614;</div>
                    <div class="dialogtip-con">
                        <h4 class="dialogtip-tit">操作成功</h4>

                        <div class="dialogtip-msg">模板添加成功</div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <a href="${ctx}/admin/sys/template?pcode=Sys&subcode=Template"
                   class="btn btn-success">返回</a>
                <%--<button type="button" class="btn btn-danger" data-dismiss="modal">继续添加</button>--%>
            </div>
        </div>
    </div>
</div>

</body>
<content tag="jsconfig">
    <script type="text/javascript" src="${ctx}/res-build/config.js" data-init="template-add.js"></script>
</content>
