<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<content tag="cssconfig">
    <link href="${ctx}/res-build/css/bas.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/res-build/res/plugin/jstree/dist/themes/default/style.min.css" rel="stylesheet" type="text/css"/>
</content>

<body>
<!-- BEGIN PAGE HEADER-->
<h3 class="page-title">
    机构管理
</h3>

<div class="page-bar clearfix">
    <ul class="page-breadcrumb">
        <li><i class="iconfont ico-home">&#xe60a;</i> <a href="index.html">主页</a> <i class="iconfont ico-angle-right">&#xe605;</i></li>
        <li><a href="#">机构管理</a> <i class="iconfont ico-angle-right">&#xe605;</i></li>
        <li><a href="#">地区管理</a></li>
    </ul>
</div>

<!-- END PAGE HEADER-->

<!-- BEGIN PAGE CONTENT-->
<div class="row">
    <div class="col-md-6">
        <!-- Begin: life time stats -->
        <div class="portlet  portlet-blue portlet-module">
            <div class="portlet-title clearfix">
                <div class="caption">
                    <i class="iconfont">&#xe621;</i> 地区管理
                </div>
                <div class="tools">
                    <%--<button type="button" class="btn btn-default btn-xs" data-toggle="modal" title="添加根模块"  id="addRootRegion">添加根模块</button>--%>
                </div>
            </div>
            <div class="portlet-body jstree-wrap" style="height:430px;overflow-y: auto">
                <div id="tree_3" class="tree-demo">
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-6">
        <div class="portlet-box" id="portlet-box">
        <!-- Begin: life time stats -->
            <div class="portlet  portlet-blue portlet-module">
            <div class="portlet-title clearfix">
                <div class="caption">
                    <i class="iconfont">&#xe620;</i> 地区详细信息
                </div>
            </div>
            <div class="portlet-body form">
                <form class="form-horizontal" role="form" id="node-form">
                    <input type="hidden" name="id" />
                    <input type="hidden" name="pid" />
                    <input type="hidden" name="sort" />
                    <input type="hidden" name="level" />
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">父节点</label>

                            <div class="col-md-7">
                                <input type="text" class="form-control" id="parentTitle" name='pname' placeholder="父节点" readonly>
                            </div>
                            <input type="hidden" name="pid">
                            <input type="hidden" name="id">
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">名称</label>

                            <div class="col-md-7">
                                <input type="text" class="form-control" placeholder="名称" name="title">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">地区编码</label>

                            <div class="col-md-7">
                                <input type="text" class="form-control" placeholder="地区编码" id="longcode" name="longcode">
                            </div>
                        </div>
                        <div class="form-actions clearfix fluid ele-hide">
                            <div class="col-md-offset-3 col-md-7">
                                <button type="submit" class="btn btn-success " id="update-node-btn">更 新</button>

                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        </div>
    </div>
</div>
<div>
    <input type="hidden" id="webBasePath" value="${ctx}">
</div>
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">添加地区</h4>
            </div>
            <form class="form-horizontal" id="addRegionForm">
                <div class="modal-body">
                    <div class="portlet-body form">
                        <!-- BEGIN FORM-->
                        <input type="hidden" name="pid" />
                        <input type="hidden" name="sort" value="0" />
                        <input type="hidden" name="level" />
                        <div class="form-body">
                            <div class="form-group">
                                <label class="col-md-3 control-label">父节点</label>

                                <div class="col-md-7">
                                    <input type="text" class="form-control" id="pname" name='pname' placeholder="父节点" readonly>
                                </div>

                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">名称</label>

                                <div class="col-md-7">
                                    <input type="text" class="form-control" placeholder="名称" name="title">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">地区编码</label>

                                <div class="col-md-7">
                                    <input type="text" class="form-control" placeholder="地区编码" id="addlongcode" name="longcode">
                                </div>
                            </div>

                        </div>
                        <!-- END FORM-->
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success" id="formSaveBtn">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!-- END PAGE CONTENT-->
<%--<script>
    $(function () {
        $('#create-node-btn').hide();
        $('#update-node-btn').hide();
        $('#del-node-btn').hide();
    });
    var UITree = function () {

        var basePath = $('#webBasePath').val();
        var ajaxTreeSample = function () {

            $("#tree_3").jstree({
                "core": {
                    "themes": {
                        "responsive": false
                    },
                    // so that create works
                    "check_callback": true,
                    'data': {
                        async: true,
                        'url': function (node) {
                            return  basePath + '/admin/bas/region/rootNode';
                        },
                        'data': function (node) {
                            return { 'id': node.id};
                        }
                    }
                },
                "types": {
                    "#": {
                        "max_children": 1,
                        "max_depth": 4,
                        "valid_children": ["root"]
                    },
                    "default": {
                        "icon": "fa fa-folder icon-state-warning icon-lg"
                    },
                    "file": {
                        "icon": "fa fa-file icon-state-warning icon-lg"
                    }
                },
                "contextmenu" : {
                    "items" : function(node) {
                        var tmp = $.jstree.defaults.contextmenu.items();
                        delete tmp.create.action;
                        tmp.create.label = "根目录";
                        tmp.create.action=function(){
                            $('#node-form input[name="pid"]').val("0");
                            $('#parentTitle').val(node.partitle);
                            $('#node-form input[name="id"]').val("");
                            $('#node-form input[name="title"]').val("");
                            $('#node-form input[name="longcode"]').val("");
                            $('#create-node-btn').show();
                            $('#update-node-btn').hide();
                            $('#del-node-btn').hide();

                        };

                        delete tmp.rename.action;
                        tmp.rename.label = "子目录";
                        tmp.rename.action=function(){

                            $('#node-form input[name="pid"]').val(node.id);
                            $('#parentTitle').val(node.text);
                            $('#node-form input[name="title"]').val("");
                            $('#node-form input[name="id"]').val("");
                            $('#node-form input[name="longcode"]').val("");
                            $('#create-node-btn').show();
                            $('#update-node-btn').hide();
                            $('#del-node-btn').hide();
                        };

                        delete tmp.remove.action;
                        tmp.remove.label = "删除";
                        tmp.remove.action=function(){
                            if(confirm("是否将此"+node.text+"类别删除?")){
                                $.post(basePath + '/admin/bas/region/delete/'+node.id, function (data) {
                                    if (data) {
                                        $("#tree_3").jstree("refresh");
                                    } else {
                                        $("#tree_3").jstree("refresh");
                                    }
                                });
                            }

                        }

                        delete tmp.ccp.action;
                        tmp.ccp.submenu=null;
                        tmp.ccp.label = "刷新";
                        tmp.ccp.action=function(){
                            $("#tree_3").jstree("refresh");
                        }

                        return tmp;
                    }
                },
                "plugins": [
                    "contextmenu",
                    "state", "types", "wholerow"
                ]
            })
        }
        return {
            //main function to initiate the module
            init: function () {
                ajaxTreeSample();
            }

        };

    }();

    UITree.init();

    $("#tree_3").bind('activate_node.jstree', function (e, data) {
        var basePath = $('#webBasePath').val();
        if (data.node.id === '#') {
            var postPath = basePath + 'bas/region/get/0';
        } else {
            var postPath = basePath + 'bas/region/get/' + data.node.id;
        }
        $.post(postPath, function (nodeData) {
            if (nodeData.pid == 0) {
                $('#node-form input[name="parentTitle"]').val("根节点");
            } else {
                $('#parentTitle').val(nodeData.pname);
            }
            $('#node-form input[name="id"]').val(nodeData.id);
            $('#node-form input[name="pid"]').val(nodeData.pid);
            $('#node-form input[name="title"]').val(nodeData.title);
            $('#node-form input[name="longcode"]').val(nodeData.longcode);
            $('#node-form input[name="sort"]').val(nodeData.sort);
            $('#node-form input[name="level"]').val(nodeData.level);
            $('#create-node-btn').hide();
            $('#update-node-btn').show();
        });
    });


    $('#create-node-btn').on('click', function (data) {
        var basePath = $('#webBasePath').val();
        var postPath = basePath + 'bas/region/create';
        $.post(postPath, $('#node-form').serialize(), function (data) {
            if (data) {
                $('#node-form')[0].reset();
                $("#tree_3").jstree("refresh");
            }else{
                // alert()
            }
        });

    });

    $('#del-node-btn').on('click', function (e) {
        var basePath = $('#webBasePath').val();
        var idStr = $('#node-form input[name="id"]').val();
        var postPath = basePath + 'bas/region/delete/' + idStr;
        $.post(postPath, function (data) {
            if (data) {
                $('#node-form')[0].reset();
                $("#tree_3").jstree("refresh");
            } else {
                $("#tree_3").jstree("refresh");
            }
        });
    });

    $('#update-node-btn').on('click', function (e) {
        var basePath = $('#webBasePath').val();
        var postPath = basePath + 'bas/region/update/';
        $.post(postPath, $('#node-form').serialize(), function (data) {
            if (data) {
                $('#node-form')[0].reset();
                $("#tree_3").jstree("refresh");
            }
        });
    });

    $('#craete-root-node-a').on('click', function (e) {
        $('#dictionary-root-create-form input[name="pid"]').val('0');
        $('#createRootNodeModal').modal('show');
    });

    $('#create-dictionary-root-btn').on('click', function (e) {
        var basePath = $('#webBasePath').val();
        var postPath = basePath + 'bas/region/create';
        $.post(postPath, $('#dictionary-root-create-form').serialize(), function (data) {
            if (data) {
                $('#dictionary-root-create-form')[0].reset();
                $('#node-form')[0].reset();
                $('#createRootNodeModal').modal('hide');
                $("#tree_3").jstree("refresh");
            } else {

            }
        });
    });
</script>--%>
</body>
<content tag="jsconfig">
    <script type="text/javascript" src="${ctx}/res-build/config.js" data-init="region.js"></script>

</content>

