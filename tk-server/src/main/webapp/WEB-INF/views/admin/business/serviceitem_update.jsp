<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<content tag="cssconfig">
    <link href="${ctx}/res-build/css/bas.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx}/res-build/res/plugin/jstree/dist/themes/default/style.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/res-build/res/plugin/bootstrap-summernote/summernote.css" rel="stylesheet">
    <link href="${ctx}/res-build/css/font-awesome-4.3.0/css/font-awesome.min.css" rel="stylesheet">
    
</content>
<content tag="headerjsconfig">
  
    <script src="${ctx}/res-build/res/plugin/bootstrap-summernote/summernote.js"></script>
    <script src="${ctx}/res-build/res/plugin/bootstrap-summernote/lang/summernote-zh-CN.js"></script>

</content>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>

<body>
<h3 class="page-title">
    服务项目
    <small>修改项目</small>
</h3>

<div class="page-bar clearfix">
    <ul class="page-breadcrumb">
        <li><i class="iconfont ico-home">&#xe60a;</i> <a href="index.html">主页</a> <i
                class="iconfont ico-angle-right">
            &#xe605;</i></li>
        <li><a href="${ctx}/admin/bussiness/serviceitem?pcode=2&subcode=6">项目维护</a> <i
                class="iconfont ico-angle-right">
            &#xe605;</i></li>
        <li><a href="#">修改项目</a></li>
    </ul>
</div>

<div class="row">

    <div class="col-md-12">
        <div class="portlet-body form">
            <!-- BEGIN FORM-->
            <form action="#" id="form_edit" class="form-horizontal" enctype="multipart/form-data">
             

                <div class="form-body">
                <div class="form-actions-topbox">
                        <div class="form-actions-fixtop">
                            <div class="row">
                                <div class="col-md-offset-3 col-md-9">
                                    <button type="button" class="btn btn-success j-edit"><i class="iconfont">
                                        &#xe61c;</i>我要编辑
                                    </button>
                                    <button type="submit" class="btn btn-success j-save" style="display: none"><i
                                            class="iconfont">
                                        &#xe62c;</i>保存
                                    </button>
                                    <a href="${ctx}/admin/bussiness/serviceitem?pcode=2&subcode=6&scurrentpage=${scurrentpage}"
                                       class="btn btn-default">返回</a>
                                       <!-- <a href="${ctx}/admin/bussiness/serviceitem?pcode=2&subcode=6"
                                       class="btn btn-default">返回</a> -->
                                    <%--<button type="button" class="btn btn-success j-edit"><i class="iconfont">&#xe61c;</i>
                                        我要编辑
                                    </button>--%>
                                </div>
                            </div>
                        </div>
                </div>
                     <div class="form-group">
                        <label class="control-label col-md-3">服务名称</label>

                        <div class="col-md-4">
                            <input type="text" id="title" name="title" placeholder="服务名称" class="form-control" disabled>
                        </div>
                    </div>
                 
              
                       <input type="hidden" id="kinds" name="kinds" class="form-control" value="1">
                  
         
                    
                    <div class="form-group">
                        <label class="control-label col-md-3">服务内容</label>

                      <!--   <div class="col-md-9" style="width: 50%">
                           
                            <textarea id="summernote" name="info" class="form-control" rows="3"></textarea>
                        </div>
                    </div> -->

                   <div class="col-md-9" style="width: 50%">
                            <%--   <div id="summernote">summernote 1</div>--%>
                            <textarea id="summernote" name="info" style="display: none" disabled
                                      class="form-control"
                                      rows="3"></textarea>
                        </div>
                        <div class="col-md-9" style="width: 50%">
                            <%--   <div id="summernote">summernote 1</div>--%>
                            <div id="summernote-con"
                                 style="background-color:#eeeeee; border: 1px solid #CCCCCC;-moz-border-radius:5px; -webkit-border-radius:5px; border-radius:5px;  padding: 6px 12px;"
                                 class=""></div>
                        </div>

                    </div>

                   <div class="form-actions">
                        <div class="row">
                            <div class="col-md-offset-3 col-md-9">
                                <button type="button" class="btn btn-success j-edit"><i class="iconfont">&#xe61c;</i>我要编辑
                                </button>
                                <button type="submit" class="btn btn-success j-save" style="display: none"><i
                                        class="iconfont">
                                    &#xe62c;</i>保存
                                </button>
                                <a href="${ctx}/admin/bussiness/serviceitem?pcode=2&subcode=6&scurrentpage=${scurrentpage}"
                                   class="btn btn-default">返回</a>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
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

                        <div class="dialogtip-msg">数据添加成功</div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <a href="${ctx}/admin/bussiness/serviceitem?pcode=2&subcode=6&scurrentpage=${scurrentpage}"
                   class="btn btn-success">返回列表</a>
                <a href="#" class="btn btn-danger j-modal-closebtn">关闭</a>
            </div>
        </div>
    </div>
</div>
<div class="modal fade bs-example-modal-sm" id="modal-box-error" tabindex="-1" role="dialog"
     aria-labelledby="mySmallModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">

        <div class="modal-content">

            <div class="modal-body">
                <div class="dialogtip-con-wrap clearfix dialogtipg-error">
                    <div class="dialogtip-icon iconfont">&#xe616;</div>
                    <div class="dialogtip-con">
                        <h4 class="dialogtip-tit">操作失败</h4>

                        <div class="dialogtip-msg">请上传gif|jpg|jpeg|png格式的图片</div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">

                <button type="button" class="btn btn-danger" data-dismiss="modal">我知道了</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade bs-example-modal-sm" id="ajax_fail" tabindex="-1" role="dialog"
     aria-labelledby="mySmallModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
            <div class="modal-content">
            <div class="modal-body">
                <div class="dialogtip-con-wrap clearfix dialogtipg-warning">
                    <div class="dialogtip-icon iconfont">&#xe615;</div>
                        <div class="dialogtip-con">
                            <h4 class="dialogtip-tit" style="margin-top: 12px;">保存失败</h4>
                        </div>
                </div>
            </div>
            <div class="modal-footer">        
                <a href="#" class="btn btn-danger j-modal-closebtn" data-dismiss="modal">关闭</a>
            </div>
            </div>
    </div>
</div>
</body>

<content tag="jsconfig">
<script type="text/javascript">
        var serviceId =${id},scurrentpage=${scurrentpage};
    </script>
    <script type="text/javascript" src="${ctx}/res-build/config.js" data-init="service-update.js"></script>

</content>
