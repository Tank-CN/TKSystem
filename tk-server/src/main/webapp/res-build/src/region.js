/**
 * Created by feiwen8772 on 15/5/11.
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
define(function(require, exports, module) {
    require("res-build/res/plugin/jstree/dist/jstree.min.js");
    require("res-build/res/plugin/jquery-formautofill/jquery.formautofill.min.js");
    require("res-build/res/plugin/jquery-validation-1.13.1/dist/jquery.validate.min.js");
    var fixedOnScroll = require("res-build/src/fixedOnScroll-mod.js");
    var $tree = $("#tree_3");
    var $modifyModuleForm = $("#node-form");
    var $addRegionForm = $("#addRegionForm");
    var $addModal = $('#addModal');
    var $portletBox = $("#portlet-box");
    var Utilitiy = {
        init: function() {
            var self = this;
            fixedOnScroll.scroll($portletBox.find(".portlet"));

            $tree.jstree({
                "state": {
                    "key": "region-demo"
                },
                "core": {
                    "themes": {
                        "responsive": false
                    },
                    // so that create works
                    "check_callback": true,
                    'data': {
                        async: true,
                        'url': function(node) {
                            return ROOTPAth + '/admin/bas/region/list';
                        },
                        type: "POST",
                        'data': function(node) {
                            return {
                                'pid': node.id === "#" ? 1 : node.id
                            };
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
                    "items": function(node) {
                        var tmp = $.jstree.defaults.contextmenu.items();
                        delete tmp.create;
                        delete tmp.rename.action;
                        tmp.rename.label = "创建子目录";
                        tmp.rename.action = function() {
                            self.addRegion(node);

                        };

                        delete tmp.remove.action;
                        tmp.remove.label = "删除";
                        tmp.remove.action = function() {
                            if (confirm("是否将此" + node.text + "类别删除?")) {
                                $.post(ROOTPAth + '/admin/bas/region/delete/' + node.id, function(data) {
                                    if (data) {
                                        $tree.jstree("refresh");
                                    } else {
                                        $tree.jstree("refresh");
                                    }
                                });
                            }

                        };

                        delete tmp.ccp.action;
                        tmp.ccp.submenu = null;
                        tmp.ccp.label = "刷新";
                        tmp.ccp.action = function() {
                            $("#tree_3").jstree("refresh");
                        };

                        return tmp;
                    }
                },
                "plugins": [
                    "contextmenu",
                    "state", "types", "wholerow"
                ]
            });
            //清楚树形菜单缓存
            $tree.jstree(true).clear_state();
            this.bind();
        },
        bind: function() {
            //激活树菜单
            $tree.bind('activate_node.jstree', function(e, data) {
                //var ROOTPAth = $('#webBasePath').val();
                if (data.node.id === '#') {
                    var postPath = ROOTPAth + '/admin/bas/region/detail/0';
                } else {
                    var postPath = ROOTPAth + '/admin/bas/region/detail/' + data.node.id;
                }
                /*var parents=data.node.parent;
                 var parentstext="";
                 if(parents==="#"){
                 parentstext="中国";
                 }else{
                 parentstext=$("#"+parents+">.jstree-anchor").text();
                 }*/
                var parentstext = "";
                var parents = data.node.parents.sort(function(a, b) {
                    return a - b
                });
                //var parentstext=$("#"+parents+">.jstree-anchor").text();
                if (parents.length === 1) {
                    parentstext = "中国"
                } else {
                    $.each(parents, function(index, val) {
                        if (val === "#") {
                            /*parentstext+="中国";*/
                        } else {
                            parentstext += $("#" + val + ">.jstree-anchor").text() + " ";
                        }

                    })
                }

                //var postPath = ROOTPAth + '/admin/sys/module/detail/' + data.node.original.uuid;
                $.post(postPath, function(moduleObj) {

                    var obj = $.extend({}, moduleObj, {
                        "pname": parentstext
                    });
                    $modifyModuleForm.autofill(obj);
                    $modifyModuleForm.find(".form-actions").show();
                });
            });
            //修改
            $modifyModuleForm.on('submit', function(e) {
                e.preventDefault();
                var postPath = ROOTPAth + '/admin/bas/region/update/';
                $.post(postPath, $modifyModuleForm.serialize(), function(data) {
                    if (data) {
                        $modifyModuleForm[0].reset();
                        $tree.jstree("refresh");
                    }
                });
            });
            //新增
            $addRegionForm.on('submit', function(e) {
                e.preventDefault();
                var postPath = ROOTPAth + '/admin/bas/region/save/';
                $.post(postPath, $addRegionForm.serialize(), function(data) {
                    if (data) {
                        $addRegionForm[0].reset();
                        $tree.jstree("refresh");
                        $addModal.modal('hide');
                    }
                });
            });
            //todo 右侧固定定位

        },
        addRegion: function(node) {
            console.log(node);
            var parentstext = "";
            var parents = node.parents.sort(function(a, b) {
                return a - b
            });
            //var parentstext=$("#"+parents+">.jstree-anchor").text();
            if (parents.length === 1) {
                parentstext = "中国"
            } else {
                $.each(parents, function(index, val) {
                    if (val === "#") {
                        /*parentstext+="中国";*/
                    } else {
                        parentstext += $("#" + val + ">.jstree-anchor").text() + " ";
                    }

                })
            }
            parentstext += $("#" + node.id + ">.jstree-anchor").text();
            var obj = {
                pid: node.id,
                level: node.original.level + 1,
                "pname": parentstext
            };
            $addRegionForm.autofill(obj);

            $addModal.modal('show');
        }
    };
    Utilitiy.init();
});