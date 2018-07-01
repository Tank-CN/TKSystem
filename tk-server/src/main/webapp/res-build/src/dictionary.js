/**
 * Created by feiwen8772 on 15/5/8.
 *                       _oo0oo_
 *                      o8888888o
 *                      88" . "88
 *                      (| -_- |)
 *                      0\  =  /0
 *                    ___/`---'\___
 *                  .' \\|     |// '.
 *                 / \\|||  :  |||// \
 *                / _||||| -:- |||||- \
 *               |   | \\\  -  /// |   |
 *               | \_|  ''\---/''  |_/ |
 *               \  .-\__  '-'  ___/-. /
 *             ___'. .'  /--.--\  `. .'___
 *          ."" '<  `.___\_<|>_/___.' >' "".
 *         | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *         \  \ `_.   \_ __\ /__ _/   .-` /  /
 *     =====`-.____`.___ \_____/___.-`___.-'=====
 *                       `=---='
 *
 *
 *     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 *               佛祖保佑         永无BUG
 *
 */
define(function (require, exports, module) {
    require("res-build/res/plugin/jstree/dist/jstree.min.js");
    require("res-build/res/plugin/jquery-formautofill/jquery.formautofill.min.js");
    require("res-build/res/plugin/jquery-validation-1.13.1/dist/jquery.validate.min.js");
    var $modifyNodeForm = $("#modifyModeForm");
    var $addModuleForm = $('#addModuleForm');
    var $tree = $("#tree_3");
    var dictionaryName = "";

    var Utilitiy = {
        init: function () {
            var self = this;
            $tree.jstree({
                "state": {"key": "region-demo"},
                "core": {
                    "themes": {
                        "responsive": false
                    },
                    "check_callback": true,
                    'data': {
                        async: true,
                        'url': function (node) {
                            return ROOTPAth + '/admin/sys/dictionary/rootNode';
                        },
                        'data': function (node) {
                            return {'id': node.id === "#" ? 0 : node.id,"dictionaryName":dictionaryName};
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
                        "icon": "icon-folder"
                    },
                    "file": {
                        "icon": "icon-file"
                    }
                },
                "contextmenu": {
                    "items": function (node) {
                        var tmp = $.jstree.defaults.contextmenu.items();
                        tmp.rename = undefined;
                        delete tmp.create.action;
                        tmp.create.label = "添加下级字典";
                        tmp.create.action = function () {
                            $("#parentMain").show();
                            $("#idxMain").show();
                            $("#addCid").val(node.cid);
                            $("#cidMain").hide();
                            $('#parentMoudleInput').val(node.text);
                            $('#addPid').val(node.id);
                            $('#addModal').modal('show');
                        };
                        delete tmp.remove.action;
                        tmp.remove.label = "删除";
                        tmp.remove.action = function () {
                            self.deleteModule(node.id);
                        };
                        delete tmp.ccp.action;
                        tmp.ccp.submenu = null;
                        tmp.ccp.label = "刷新";
                        tmp.ccp.action = function () {
                            $tree.jstree("refresh");
                        };
                        return tmp;
                    }
                },
                "plugins": [
                    "contextmenu","search",
                    "state", "types", "wholerow"
                ]
            });
            this.bind();
        },
        bind: function () {
            var self = this;

            $tree.bind('activate_node.jstree', function (e, data) {
                var basePath = $('#webBasePath').val();
                if (data.node.id === '#') {
                    var postPath = ROOTPAth + '/admin/sys/dictionary/get/0';
                } else {
                    var postPath = ROOTPAth + '/admin/sys/dictionary/get/' + data.node.id;
                }
                $.post(postPath, function (nodeData) {
                	if(nodeData.idx == null || nodeData.idx == undefined){
                		nodeData.idx = "";
                	}
                	$("#dictionary_memo").val("");
                    $modifyNodeForm.autofill(nodeData);
                    if(!nodeData.sort){
                    	$("#sort").val("");
                    }
                    if(!nodeData.gbcode){
                    	$("#gbcode").val("");
                    }
                    $modifyNodeForm.find(".form-actions").show();
                });
            });
            $modifyNodeForm.validate({
                rules: {
                    title: "required"
                },
                messages: {
                    title: "字典中文名称不能为空"
                },
                submitHandler: function () {
                    var savePath = ROOTPAth + '/admin/sys/dictionary/update';
                    $.post(savePath, $modifyNodeForm.serialize(), function (data) {
                        $tree.jstree("refresh");
                    });
                }
            });

            $("#addModuleSpan").on('click', function (e) {
                $("#parentMain").hide();
                $("#idxMain").hide();
                $("#cidMain").show();
                $('#addPid').val('0');
                $('#addModal').modal('show');
            });
            
            $("#create-node-btn").on("click",function(){
                $addModuleForm.submit();
            });
            $("#update-node-btn").on("click",function(){
                $modifyNodeForm.submit();
            });

            $addModuleForm.validate({
                rules: {
                    title: "required",
                    cid:"required"
                },
                messages: {
                    title: "字典中文名称不能为空",
                    cid:"字典英文名称不能为空"
                },
                submitHandler: function () {
                    var savePath = ROOTPAth + '/admin/sys/dictionary/create';
                    $.post(savePath, $('#addModuleForm').serialize(), function (data) {
                        $tree.jstree("refresh");
                        $('#addModal').modal('hide');
                        $('#addModuleForm')[0].reset();
                    });
                }
            });

        },
        deleteModule: function (moduleId) {
            var delPath = ROOTPAth + '/admin/sys/dictionary/delete/' + moduleId;
            $.ajax({
                url: delPath,
                type: "post",
                success: function (data) {
                    $("#tree_3").jstree("refresh");
                }
            });
        },
        postData: function ($form) {

        }
    };
    Utilitiy.init("");
    
    $("#findDictionaryName").on('click',function(){
        var dname = $("#dictionaryName").val();
        dictionaryName = dname;
        $("#tree_3").jstree("refresh");
    });
    $("#allDictionaryName").on('click',function(){
        $("#dictionaryName").val("");
        dictionaryName = "";
        $("#tree_3").jstree("refresh");
    });
    
});

