<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<content tag="cssconfig">
<link href="${ctx}/res-build/css/bas.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/res-build/res/plugin/jstree/dist/themes/default/style.min.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/res-build/res/plugin/bootstrap-summernote/summernote.css" rel="stylesheet">
<link href="${ctx}/res-build/css/font-awesome-4.3.0/css/font-awesome.min.css" rel="stylesheet">
</content>
<content tag="headerjsconfig"> <script type="text/javascript" src="http://api.map.baidu.com/api?v=1.5&ak=lYUVYI1SUwbp3mtDMzOEmQZ8"></script>
<script src="${ctx}/res-build/res/plugin/bootstrap-summernote/summernote.js"></script> </content>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />

<body>
	<h3 class="page-title">
		意见反馈 <small>修改意见</small>
	</h3>

	<div class="page-bar clearfix">
		<ul class="page-breadcrumb">
			<li><i class="iconfont ico-home">&#xe60a;</i> <a href="index.html">主页</a> <i class="iconfont ico-angle-right"> &#xe605;</i></li>
			<li><a href="${ctx}/admin/advice?pcode=Advice&subcode=AdviceList">意见反馈</a> <i class="iconfont ico-angle-right"> &#xe605;</i></li>
			<li><a href="#">修改意见</a></li>
		</ul>
	</div>

	<div class="row">
		<div class="col-md-12">
			<div class="portlet-body form">
				<!-- BEGIN FORM-->
				<form action="#" id="form_edit" class="form-horizontal" enctype="multipart/form-data">
					<input type="hidden" name="id" value="${id}">
					<div class="form-body">
						<div class="form-actions-topbox">
							<div class="form-actions-fixtop">
								<div class="row">
									<div class="col-md-offset-3 col-md-9">
										<button type="button" class="btn btn-success j-edit">
											<i class="iconfont"> &#xe61c;</i>我要编辑
										</button>
										<button type="submit" class="btn btn-success j-save" style="display: none">
											<i class="iconfont"> &#xe62c;</i>保存
										</button>
										<a href="${ctx}/admin/advice?pcode=Advice&subcode=AdviceList&currentpage=${currentpage}" class="btn btn-default">返回</a>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">提交时间</label>
							<div class="col-md-4">
								<input type="text" id="createdate" disabled name="createdate" placeholder="提交时间" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">用户</label>
							<div class="col-md-4">
								<input type="text" id="uid" disabled name="uid" placeholder="用户" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">意见反馈</label>
							<div class="col-md-4">
								<input type="text" id="content" disabled name="content" placeholder="意见反馈" class="form-control">
							</div>
						</div>

						<div class="form-actions">
							<div class="row">
								<div class="col-md-offset-3 col-md-9">
									<button type="button" class="btn btn-success j-edit">
										<i class="iconfont">&#xe61c;</i>我要编辑
									</button>
									<button type="submit" class="btn btn-success j-save" style="display: none">
										<i class="iconfont"> &#xe62c;</i>保存
									</button>
									<a href="${ctx}/admin/advice?pcode=Advice&subcode=AdviceList&currentpage=${currentpage}" class="btn btn-default">返回</a>
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
	<div class="modal fade bs-example-modal-sm" id="modal-box" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm">

			<div class="modal-content">

				<div class="modal-body">
					<div class="dialogtip-con-wrap clearfix dialogtipg-success">
						<div class="dialogtip-icon iconfont">&#xe614;</div>
						<div class="dialogtip-con">
							<h4 class="dialogtip-tit">操作成功</h4>

							<div class="dialogtip-msg">数据保存成功</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<a href="${ctx}/admin/advice?pcode=Advice&subcode=AdviceList&currentpage=${currentpage}" class="btn btn-success">返回机构列表</a> <a
						href="#" class="btn btn-danger j-modal-closebtn">关闭</a>
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

	<content tag="jsconfig"> <script type="text/javascript">
        var adviceID = ${id};
    </script> <script type="text/javascript" src="${ctx}/res-build/config.js" data-init="advice_update.js"></script> </content>
</body>

