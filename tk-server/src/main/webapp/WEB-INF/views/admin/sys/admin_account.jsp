<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<content tag="cssconfig">
<link href="${ctx}/res-build/css/sys.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/res-build/res/module/ajaxpage/css/page.css" rel="stylesheet" type="text/css" />
</content>
<body>
	<!-- BEGIN PAGE HEADER-->
	<h3 class="page-title">系统配置</h3>
	<div class="page-bar clearfix">
		<ul class="page-breadcrumb">
			<li><i class="iconfont ico-home">&#xe60a;</i> <a href="index.html">主页</a> <i class="iconfont ico-angle-right"> &#xe605;</i></li>
			<li><a href="#">系统配置</a> <i class="iconfont ico-angle-right">&#xe605;</i></li>
			<li><a href="#">管理员帐户管理</a></li>
		</ul>
	</div>
	<!-- END PAGE HEADER-->
	<!-- BEGIN PAGE CONTENT-->
	<div class="row">
		<div class="col-md-12">
			<div class="portlet">
				<div class="portlet-title">
					<div class="caption">
						<i class="iconfont">&#xe619;</i> 管理员帐户管理
					</div id="">
					<div class="actions">
						<a class="btn btn-success btn-sm" href="#" data-toggle="modal" data-target="#addModal"> <i class="iconfont">&#xe612;</i> <span class="hidden-480">添加管理员帐户</span>
						</a>
					</div>
				</div>
				<div class="portlet-body" id="account-list">
					<div class="table-pages clearfix">
						<div class="table-page clearfix"></div>
						<div class="page-length">
							<span class="seperator">|</span> 每页显示 <select name="j-length" aria-controls="datatable_ajax" class="j-length input-xsmall input-sm input-inline">
								<option value="10">10</option>
								<option value="25">25</option>
								<option value="50">50</option>
								<option value="75">75</option>
								<option value="100">100</option>
							</select> 条记录
						</div>
						<div class="page-info" role="status" aria-live="polite">
							<span class="seperator">|</span> 总共获取 <span class="page-info-num"></span> 条记录
						</div>
					</div>
					<div class="table-container">
						<table class="table table-striped table-bordered table-hover dataTable no-footer" id="datatable_ajax">

							<thead>
								<tr role="row">
									<th class="sorting_desc">账号</th>
									
									<th class="sorting">所属机构</th>
									<th class="sorting">操作</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
					<div class="table-pages clearfix">
						<div class="table-page clearfix"></div>
						<div class="page-length">
							<span class="seperator">|</span> 每页显示 <select name="j-length" aria-controls="datatable_ajax" class="j-length input-xsmall input-sm input-inline">
								<option value="10">10</option>
								<option value="25">25</option>
								<option value="50">50</option>
								<option value="75">75</option>
								<option value="100">100</option>
							</select> 条记录
						</div>
						<div class="page-info" role="status" aria-live="polite">
							<span class="seperator">|</span> 总共获取 <span class="page-info-num"></span> 条记录
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="addModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">添加管理员</h4>
				</div>
				<form class="form-horizontal" id="vUserForm">
					<!-- <input type="hidden" id="orgname" name="orgname">
					<input type="hidden" id="orgid" name ="orgid"> -->
					<div class="modal-body">
						<div class="portlet-body form">
							<div class="form-body">
								<div class="form-group">
									<label class="col-md-3 control-label">用户名</label>
									<div class="col-md-8">
										<input type="text" class="form-control" name="username" placeholder="用户名">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">密码</label>

									<div class="col-md-8">
										<input type="password" class="form-control" name="password" placeholder="密码" id="password">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">重复密码</label>

									<div class="col-md-8">
										<input type="password" class="form-control" placeholder="重复密码" id="password_again" name="password_again">
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-3 control-label">所属机构</label>
									<div class="col-md-8">
										<div class="search-input">
											<input type="search" class="form-control search-field" placeholder="拼音首字母检索" id="search-field" autocomplete="off"> <a class="search-clear-btn iconfont" href="#">&#xe616;</a>
										</div>
										<div class="checkbox-con" style="border: 1px solid #e5e5e5; padding: 5px 10px; border-radius: 4px; margin-top: 5px; height: 230px; overflow-y: auto">
											<div class="checkbox"></div>
										</div>
									</div>
								</div>
							</div>
							<!-- END FORM-->
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-success">
							<i class="iconfont">&#xe61c;</i> 保存
						</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<div class="modal fade" id="modifyModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">修改管理员</h4>
				</div>
				<form class="form-horizontal" id="vUserModifyForm">
					
					<div class="modal-body">
						<div class="portlet-body form">
							<div class="form-body">
								<div class="form-group">
									<label class="col-md-3 control-label">用户名</label>
									<div class="col-md-8">
										<input type="hidden" name="id" id="uidHidde"> <input type="text" class="form-control" disabled="disabled" name="username" placeholder="用户名">
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-3 control-label">所属机构</label>
									<div class="col-md-8">
										<div class="search-input" style="display: none">
											<input type="search" class="form-control search-field" placeholder="拼音首字母检索" autocomplete="off"> <a class="search-clear-btn iconfont" href="#">&#xe616;</a>
										</div>
										<div class="checkbox-con" style="border: 1px solid #e5e5e5; padding: 5px 10px; border-radius: 4px; margin-top: 5px; height: 230px; overflow-y: auto">
											<div class="checkbox"></div>
										</div>
									</div>
								</div>
								<!-- END FORM-->
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-success j-form-edit">
								<i class="iconfont">&#xe61c;</i> 我要编辑
							</button>
							<button type="submit" class="btn btn-success j-form-save" style="display: none">
								<i class="iconfont">&#xe62c;</i> 保存
							</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							<%-- <button type="submit" class="btn btn-success">修改</button>
                         <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>--%>
						</div>
					</div>
				</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
<div class="modal fade bs-example-modal-sm" id="modal-box" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
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
                <a href="${ctx}/admin/sys/adminAccount?pcode=1&subcode=1"
                   class="btn btn-success">返回列表</a>
               
            </div>
			</div>
		</div>
	</div>
	<!-- END PAGE CONTENT-->
</body>
<content tag="jsconfig"> <script type="text/javascript" src="${ctx}/res-build/config.js" data-init="admin_account.js"></script> </content>
