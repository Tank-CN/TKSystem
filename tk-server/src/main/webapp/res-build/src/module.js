/**
 * Created by feiwen8772 on 15/4/29.
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
    require("res-build/res/plugin//autoTextarea/jquery.autoTextarea.js");
    require("res-build/res/plugin/jquery-formautofill/jquery.formautofill.min.js");
    require("res-build/res/plugin/jquery-validation-1.13.1/dist/jquery.validate.min.js");
    var $modifyModuleForm = $("#modifyModuleForm");
    var $addModuleForm = $('#addModuleForm');
    var $tree = $("#tree_3");
    var $addSubmit = $("#formSaveBtn");
    var $deteleModalSure = $("#deteleModalSure");
    var Utilitiy = {
        init: function () {
            var self = this;
            //var ROOTPAth = $('#webBasePath').val();
            $tree.jstree({
                "core": {
                    "themes": {
                        "responsive": false
                    },
                    "check_callback": true,
                    'data': {
                        async: true,
                        'url': function (node) {
                            return ROOTPAth + '/admin/sys/module/allNode';
                        },
                        type: "POST",
                        'data': function (node) {
                            return {'id': node.pcode, 'text': node.code};
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
                        if (node.parent === "#") {

                            tmp.create.label = "添加子模块";
                            tmp.create.action = function () {
                                $('#pcode').val(node.id);
                                $('#parentModuleInput').val(node.text);
                                $('#addModal').modal('show');
                            };
                        } else {
                            delete tmp.create
                        }
                        delete tmp.remove.action;
                        tmp.remove.label = "删除";
                        tmp.remove.action = function () {
                            self.deleteModule(node.original.uuid);
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
                    "contextmenu",
                    "state", "types", "wholerow"
                ]
            });
            this.bind();
        },
        bind: function () {
            var self = this;
            $tree.bind('activate_node.jstree', function (e, data) {

                var postPath = ROOTPAth + '/admin/sys/module/detail/' + data.node.original.uuid;
                $.post(postPath, function (moduleObj) {
                    $modifyModuleForm.autofill(moduleObj);
                    $modifyModuleForm.find(".form-actions").show();
                });
            });
            $("textarea").autoTextarea({
                maxHeight: 520,
                resizeWindon: true
            });
            $modifyModuleForm.validate({
                rules: {
                    title: "required",
                    code: "required",
                    url: "required"
                },
                messages: {
                    title: "模块名称不能为空",
                    code: "模块编码不能为空",
                    url: "模块url地址不能为空"
                },
                submitHandler: function () {
                    var savePath = ROOTPAth + '/admin/sys/module/update';
                    $.post(savePath, $modifyModuleForm.serialize(), function (data) {
                        $tree.jstree("refresh");
                    });
                }
            });

            $("#addModuleSpan").on('click', function (e) {
                $('#pcode').val('0');
                $('#parentModuleInput').val('父节点');
                $('#addModal').modal('show');
            });

            $addModuleForm.validate({
                rules: {
                    title: "required",
                    code: "required"
                },
                messages: {
                    title: "模块名称不能为空",
                    code: "模块编码不能为空"
                },
                submitHandler: function () {
                    $addSubmit.prop("disabled", true);
                    var savePath = ROOTPAth + '/admin/sys/module/create';
                    $.post(savePath, $('#addModuleForm').serialize(), function (data) {
                        $addSubmit.prop("disabled", false);
                        $tree.jstree("refresh");
                        $('#addModal').modal('hide');
                        $('#addModuleForm')[0].reset();
                    });
                }
            });
            /*function save(){
             var savePath = $('#webBasePath').val() + '/admin/sys/module/create';
             $.post(savePath, $('#addModuleForm').serialize(), function(data){
             $("#tree_3").jstree("refresh");
             $('#addModal').modal('hide');
             $('#addModuleForm')[0].reset();
             });
             }*/

            /*function update(){
             var savePath = ROOTPAth + '/admin/sys/module/update';
             $.post(savePath, $modifyModuleForm.serialize(), function(data){
             $("#tree_3").jstree("refresh");
             });
             }*/

        },
        deleteModule: function (moduleId) {
            var self = this;

            /*$deteleModalSure.on("show.bs.modal",function(){
             $deteleModalSure.find(".j-sure-btn").data("moduleId",moduleId)
             });*/
            $deteleModalSure.modal("show");
            $deteleModalSure.on("click", ".j-sure-btn", function (e) {
                e.preventDefault();
                self.delPostData(moduleId)
            })
        },
        delPostData: function (moduleId) {
            var delPath = ROOTPAth + '/admin/sys/module/delete/' + moduleId;
            $.ajax({
                url: delPath,
                type: "POST",
                success: function (data) {
                    $("#tree_3").jstree("refresh");
                    $deteleModalSure.modal("hide");
                }
            });
        }
    };
    Utilitiy.init();
    return Utilitiy;

});
