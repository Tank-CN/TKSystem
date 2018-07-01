<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<content tag="cssconfig">
<link href="${ctx}/res-build/css/sys.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/res-build/res/plugin/jstree/dist/themes/default/style.min.css" rel="stylesheet" type="text/css" />
</content>

<body>
	<!-- BEGIN PAGE HEADER-->
	<h3 class="page-title">系统配置</h3>

	<div class="page-bar clearfix">

		<ul class="page-breadcrumb">
			<li><i class="iconfont ico-home">&#xe60a;</i> <a href="index.html">主页</a> <i class="iconfont ico-angle-right">&#xe605;</i></li>
			<li><a href="#">系统配置</a> <i class="iconfont ico-angle-right">&#xe605;</i></li>
			<li><a href="#">数据字典</a></li>
		</ul>
	</div>
	<!-- END PAGE HEADER-->

	<!-- BEGIN PAGE CONTENT-->
	<div class="row">
		<div class="col-md-4">
			<!-- Begin: life time stats -->
			<div class="portlet portlet-blue portlet-module">
				<div class="portlet-title clearfix" style="margin-bottom: 5px">
					<div class="caption">
						<i class="iconfont">&#xe60b;</i> 数据字典
					</div>
					<div class="tools">
						<button type="button" class="btn btn-default btn-xs" data-toggle="modal" <%--data-target="#addModal"--%> title="添加根模块" id="addModuleSpan">添加根字典</button>
					</div>
				</div>
				<!--
				<div style="margin-bottom: 5px;width:336px">
					<input type="text" id="dictionaryName" name="dictionaryName"  placeholder="字典名称" style="width: 70%"/>
					<button id="findDictionaryName">查询</button>
					<button id="allDictionaryName">全部</button>
				</div>-->
				<div class="portlet-body" style="height:430px;overflow-y: auto">
					<div id="tree_3" class="tree-demo"></div>
				</div>
			</div>
		</div>
		<div class="col-md-8">
			<!-- Begin: life time stats -->
			<div class="portlet portlet-blue portlet-module">
				<div class="portlet-title clearfix">
					<div class="caption">
						<i class="fa fa-comments"></i>字典详细信息
					</div>
				</div>
				<div class="portlet-body form">
					<form class="form-horizontal" role="form" id="modifyModeForm">
						<input type="hidden" name="iid" />
						<input type="hidden" name="cid" />
						<input type="hidden" name="pid" />
						<div class="form-body">
							<div class="form-group">
								<label class="col-md-3 control-label">字典中文名称</label>
								<div class="col-md-7">
									<input type="text" class="form-control" placeholder="字典中文名称" name="title">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">字典英文名称</label>
								<div class="col-md-7">
									<input type="text" class="form-control" disabled="disabled" placeholder="字典英文名称" name="cid">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">字典编码</label>

								<div class="col-md-7">
									<input type="text" class="form-control" placeholder="字典编码" id="gbcode" name="gbcode">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">字典值</label>
								<div class="col-md-7">
									<input type="text" class="form-control" placeholder="字典值" name="idx">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">字典顺序</label>
								<div class="col-md-7">
									<input type="text" class="form-control" placeholder="字典顺序" id="sort" name="sort">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">字典状态</label>

								<div class="col-md-7">
									<%--<select class="form-control" name="flag">
                                    <option value="1">启用</option>
                                    <option value="0">关闭</option>
                                </select>--%>
									<label class="radio-inline"> <input type="radio" name="flag" checked value="1"> 启用
									</label> <label class="radio-inline"> <input type="radio" name="flag" value="0"> 关闭
									</label>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">备 注</label>

								<div class="col-md-7">
									<textarea class="form-control" rows="3" placeholder="备注" id="dictionary_memo" name="memo"></textarea>
								</div>
							</div>
							<div class="form-actions clearfix fluid">
								<div class="col-md-offset-3 col-md-7">
									<%--<button type="button" class="btn btn-info" id="create-node-btn">新增</button>--%>
									<button type="button" class="btn btn-success" id="update-node-btn">更 新</button>
									<%--<button type="button" class="btn red" id="del-node-btn">删 除</button>--%>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div>
		<input type="hidden" id="webBasePath" value="${ctx}">
	</div>

	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">添加字典</h4>
				</div>
				<form class="form-horizontal" id="addModuleForm" style="height: 500px">
						<div class="form-body" style="margin-top: 15px">
							<div class="form-group" id="parentMain">
								<label class="col-md-3 control-label">上级字典</label>
								<div class="col-md-7">
									<input type="text" class="form-control" id="parentMoudleInput" name='pname' placeholder="父节点" readonly>
								</div>
								<input type="hidden" name="pid" id="addPid" value="0"> 
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">字典中文名</label>
								<div class="col-md-7">
									<input type="text" class="form-control" placeholder="字典中文名" name="title">
								</div>
							</div>
							<div class="form-group" id="idxMain">
								<label class="col-md-3 control-label">字典值</label>
								<div class="col-md-7">
									<input type="text" class="form-control" placeholder="字典值" name="idx">
								</div>
							</div>
							<div class="form-group" id="cidMain">
								<label class="col-md-3 control-label">字典英文名</label>

								<div class="col-md-7">
									<input type="text" class="form-control" placeholder="字典英文名" id="addCid" name="cid">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">字典编码</label>

								<div class="col-md-7">
									<input type="text" class="form-control" placeholder="字典编码" name="gbcode">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">字典顺序</label>

								<div class="col-md-7">
									<input type="text" class="form-control" placeholder="字典顺序" name="sort">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">字典状态</label>

								<div class="col-md-7">
									<label class="radio-inline"> <input type="radio" name="flag" checked value="1"> 启用
									</label> <label class="radio-inline"> <input type="radio" name="flag" value="0"> 关闭
									</label>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">备 注</label>

								<div class="col-md-7">
									<textarea class="form-control" rows="3" placeholder="备注" id="dictionary_memo" name="memo"></textarea>
								</div>
							</div>
							<div class="form-actions clearfix fluid">
								<div class="col-md-offset-3 col-md-7">
									<button type="button" class="btn btn-info" id="create-node-btn">新增</button>
									<%--<button type="button" class="btn btn-success" id="update-node-btn">更 新</button>--%>
									<%--<button type="button" class="btn red" id="del-node-btn">删 除</button>--%>
								</div>
							</div>
						</div>
					</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<!-- END PAGE CONTENT-->
	<content tag="jsconfig"> <script type="text/javascript" src="${ctx}/res-build/config.js" data-init="classify.js"></script> </content>
</body>

