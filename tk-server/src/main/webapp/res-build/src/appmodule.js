/**
 * Created by feiwen8772 on 15/5/18.
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
    var tool = require("tool");
    var $modifyAppModuleForm = $("#modifyAppModuleForm"),
        $appModuleWrap = $("#appModule-wrap"),
        $modifyTip = $("#doctor-nonelist-wrap"),
        $addModal = $("#addAppModal"),
        $addModuleForm = $("#addModuleForm");

    var $tree = $("#tree_3");

    var Utilitiy = {
        init: function () {
            tool.startPageLoading("数据加载中...");
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
                            return ROOTPAth + '/admin/sys/appmodule/tree';
                        },
                        type: "POST",
                        'data': function (node) {
                            return {'id': node.id};
                        },
                        "success": function () {
                            tool.stopPageLoading();
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
                        var items = {};
                        if (node.parent === "#") {
                            items.createModule = {
                                "label": "添加APP功能模块",
                                "action": function () {


                                    var moduleObj = {
                                        apptype: node.id.replace("u", "")
                                    };
                                    $addModuleForm.autofill(moduleObj);
                                    $addModal.modal('show');
                                }
                            };
                        }
                        if (node.parent !== "#") {
                            items.removeModule = {
                                "label": "删除APP功能模块",
                                "action": function () {
                                    var delPath = ROOTPAth + '/admin/sys/appmodule/delete';
                                    if (confirm("确定删除" + node.text + "功能模块吗?")) {
                                        $.ajax({
                                            url: delPath,
                                            type: "POST",
                                            data: {
                                                id: node.id
                                            },
                                            success: function (data) {
                                                $tree.jstree("refresh");
                                            }
                                        });
                                    }
                                }
                            };
                            items.conditionModule = {
                                "label": "APP模块条件（功能）",
                                "action": function () {

                                    var url=ROOTPAth + '/admin/sys/condition?pcode=Sys&subcode=appmodule&id='+node.id+'&modulename='+node.text;
                                    window.location.href = url;
                                }
                            };
                        }

                        return items;
                    }
                },
                "plugins": [
                    "contextmenu",
                    "state", "types", "wholerow"
                ]
            }).on('loaded.jstree', function () {
                $tree.jstree('open_all');
            });
            this.bind();
        },
        bind: function () {
            var self = this;
            $tree.bind('activate_node.jstree', function (e, data) {
                //var ROOTPAth = $('#webBasePath').val();

                //var postPath = ROOTPAth + '/admin/sys/module/detail/' + data.node.original.uuid;
                if (data.node.parent === "#") {
                    return;
                }
                $modifyAppModuleForm[0].reset();
                var getPath = ROOTPAth + '/admin/sys/appmodule/detail';
                $.ajax({
                    url: getPath,
                    type: "POST",
                    datatype: "json",
                    data: {id: data.node.id},
                    success: function (data) {
                        $modifyAppModuleForm.autofill(data);
                        $appModuleWrap.show();
                        $modifyTip.hide()
                    }
                });

            });
            //修改模块信息
            $modifyAppModuleForm.on("submit", function (ev) {
                ev.preventDefault();
                self.modifyData(this);
            });
            //新增功能模块
            $addModuleForm.on("submit", function (ev) {
                ev.preventDefault();
                self.addData(this);
            });
            $("textarea").autoTextarea({
                maxHeight: 520,
                resizeWindon: true
            });


        },
        //修改模块信息
        modifyData: function (fm) {
            var $fm = $(fm);
            var getPath = ROOTPAth + '/admin/sys/appmodule/update';
            $.ajax({
                url: getPath,
                type: "POST",
                datatype: "json",
                data: $fm.serialize(),
                success: function (data) {
                    $tree.jstree("refresh");
                }
            });
        },
        deleteModule: function (moduleId) {

        },
        addData: function (fm) {
            var $fm = $(fm);
            var getPath = ROOTPAth + '/admin/sys/appmodule/save';
            $.ajax({
                url: getPath,
                type: "POST",
                datatype: "json",
                data: $fm.serialize(),
                success: function (data) {
                    $tree.jstree("refresh");
                    $addModal.hide()
                }
            });
        }
    };
    Utilitiy.init();
    return Utilitiy;

});
