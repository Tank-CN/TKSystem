/**
 * Created by feiwen8772 on 15/5/13.
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
    var Page = require("page");
    var juicer = require("juicer");
    require("res-build/res/plugin/jstree/dist/jstree.min.js");
    require("res-build/res/plugin/jquery-formautofill/jquery.formautofill.min.js");
    require("res-build/res/plugin/jquery-validation-1.13.1/dist/jquery.validate.min.js");
    require("res-build/res/plugin/bs-confirmation/bootstrap-confirmation.js");

    var tool = require("tool");
    var Store = require("./store.js");
    /*tool.startPageLoading();
     * tool.stopPageLoading();*/
    var $tree = $("#tree_3");
    var pageIndex;
    var $doctorListwrap = $("#doctor-list-wrap");
    var $doctorNoneListwrap = $("#doctor-nonelist-wrap");
    var $table = $("#datatable_ajax");
    var $doctorList = $("#doctor-list");
    var pagelength = 10; //一页多少条；
    var doctorLevel = 1; //默认医生
    var doctorPid = 1;
    var type = 1;
    //添加科室模态
    var $addDeptModal = $('#addDeptModal'),
        $addDeptForm = $("#addDeptForm"),
        $viewDeptModal = $('#viewDeptModal'),
        $viewDeptForm = $("#viewDeptForm");
    /*<tr role="row">

     <th class="sorting">姓名
     </th>
     <th class="sorting">性别</th>
     <th class="sorting">出生日期 </th>
     <th class="sorting">职称
     </th>

     <th class="sorting">所属机构
     </th>
     <th class="sorting">所属科室
     </th>
     <th class="sorting">擅长
     </th>
     <th class="heading sorting_disabled" style="width: 130px;">操作</th>
     </tr>*/


    Date.prototype.Format = function(fmt) { //author: meizz
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "h+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    };


    var pdeptObj = {};
    var listTpl = juicer(
        [
            '{@if total === 0}',
            '<tr>',
            '<td colspan="8" style="text-align:center">',
            '暂无记录,请添加',
            '</td>',

            '</tr>',

            '{@else}',
            '{@each data as item,index}',
            '{@if index%2==0}',
            '<tr role="row" class="odd" data-id="${item.id}">',
            '{@else}',
            '<tr role="row" class="even" data-id="${item.id}">',
            '{@/if}',
            '    <td>${item.name}</td>',
            '    <td>${item.sexname}</td>',
            '    <td>${item.birthdatetime}</td>',
            '    <td>${item.professionaltitle}</td>',
            '    <td>${item.orgname}</td>',
            '    <td class="">${item.deptname}</td>',
            '    <td class="">${item.expert}</td>',
            '    <td class="">',
            '        <a href="' + ROOTPAth + '/admin/bas/doctor/updateView?pcode=RPeInstitutions&subcode=BasOrg&id=${item.id}&pdeptid=${item.pdeptid}&pdeptname=${item.pdeptname}" class="btn btn-default btn-xs j-edit" ><span class="iconfont iconfont-xs">&#xe62d;</span>查看</a> ',
            //删除
            /*' <button type="button" class="btn btn-danger btn-xs j-del" data-toggle="confirmation" data-placement="left"><span   class="iconfont iconfont-xs">&#xe61d;</span>删除 </button>',*/
            ' <button type="button" class="btn btn-danger btn-xs j-disable" data-toggle="confirmation" data-placement="left"',
            '{@if item.flag === 0}',
            'style="display:none"',
            '{@else}',
            '',
            '{@/if}',
            '>' +
            '<span  class="iconfont iconfont-xs">&#xe61d;</span>禁用 </button>',
            //启用
            ' <button type="button" class="btn btn-default btn-xs j-enable" data-toggle="confirmation" data-placement="left"',
            '{@if item.flag === 0}',
            '',
            '{@else}',
            'style="display:none"',
            '{@/if}',
            '><span    class="iconfont iconfont-xs">&#xe61f;</span>启用 </button>',
            '    </td>',
            '</tr>',
            '{@/each}',
            '{@/if}'
        ].join(""));
    pageIndex = new Page({
        ajax: {
            url: ROOTPAth + '/admin/bas/doctor/list',
            type: 'POST',
            dataType: 'json',
            data: function() {
                var data = {
                    length: pagelength,
                    level: doctorLevel,
                    pid: doctorPid,
                    type: type
                };

                return data;
            },
            success: function(res) {
                tool.stopPageLoading();
                if (res.code === 1) {

                    var newData = $.extend({}, pdeptObj, res);
                    $.each(newData.data, function(i, val) {
                        val.sexname = val.sexcode === 1 ? "男" : "女";
                        if (val.birthdate) {
                            newData.data[i].birthdatetime = val.birthdate ? new Date(val.birthdate).Format("yyyy-MM-dd") : "";
                        } else {
                            newData.data[i].birthdatetime = "";
                        }
                    });
                    $doctorListwrap.show();
                    $doctorNoneListwrap.hide();
                    //共多少条记录
                    $doctorList.find(".page-info-num").text(res.total);
                    $table.find("tbody").empty().append(listTpl.render(newData));
                    $table.find(".j-disable").confirmation({
                        title: "确定禁用该医生吗？",
                        btnOkLabel: "确定",
                        btnCancelLabel: "取消",
                        onConfirm: function(event, element) {
                            event.preventDefault();
                            Utilitiy.disbaleDoctor($(element));
                        }
                    });
                    $table.find(".j-enable").confirmation({
                        title: "确定启用该医生吗？",
                        btnOkLabel: "确定",
                        btnCancelLabel: "取消",
                        onConfirm: function(event, element) {
                            event.preventDefault();
                            Utilitiy.enableDoctor($(element));
                        }
                    });
                }

            }
        },
        pageName: "page",
        /*tpl: {
         go: true //隐藏跳转到第几页
         },*/
        getTotalPage: function(res) {
            //返回总页数
            return Math.ceil(res.total / pagelength);
        },
        pageWrapper: '.table-page'
    });
    var ref;
    var tagNode;

    function getParam(name) {
        var sUrl = window.location.search.substr(1);
        var r = sUrl.match(new RegExp("(^|&)" + name + "=([^&]*)(&|$)"));
        return (r == null ? null : unescape(r[2]));
    }
    var Utilitiy = {
        init: function() {

            var self = this;
            tool.startPageLoading("数据加载中...");
            $tree.jstree({
                "state": {
                    "key": "dept"
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
                            return ROOTPAth + '/admin/bas/dept/listtree';
                        },
                        type: "POST",
                        'data': function(node) {
                            return {
                                'pid': node.id === "#" ? 0 : node.original.did,
                                'level': node.id === "#" ? 1 : node.original.level + 1,
                                'type': node.id === "#" ? 1 : node.original.type
                            };
                        },
                        "success": function(res) {
                            tool.stopPageLoading();
                        }
                    }
                },
                "types": {
                    "#": {
                        "max_children": 1,
                        "max_depth": 5,
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
                        var items = {};
                        if (node.original.level < 3) {
                            items.createoffice = {
                                "label": "添加科室",
                                "action": function() {
                                    self.addDept(node);
                                }
                            };
                        }
                        // items.adddoctor = {
                        //     "label": "添加医生",
                        //     "action": function() {
                        //         self.addDoctor(node);
                        //     }
                        // };
                        console.log(node);
                        if (node.parent !== "#") {
                            items.viewoffice = {
                                "label": "查看科室信息",
                                "action": function() {
                                    self.viewOffice(node);
                                }
                            }
                        }
                        items.remove = {
                            "label": "删除",
                            "action": function() {
                                var delPath = ROOTPAth + '/admin/bas/dept/delete/';
                                if (confirm("确定删除" + node.text + "科室吗?")) {
                                    $.ajax({
                                        url: delPath,
                                        type: "POST",
                                        data: {
                                            id: node.original.did
                                        },
                                        success: function(data) {
                                            $tree.jstree("refresh");
                                        }
                                    });
                                }
                            }
                        };
                        items.refresh = {
                            "label": "刷新",
                            "action": function() {
                                $tree.jstree("refresh");
                            }
                        };
                        return items;
                    }
                },
                "plugins": [
                    "contextmenu",
                    "state", "types", "wholerow"
                ]
            });
            //清楚树形菜单缓存 ,设置"state" : { "key" : "dept" }
            ref = $tree.jstree(true);
            ref.clear_state();
            /* ref.is_loaded(function () {

             });*/

            if (getParam("tagpage") === "1") {
                doctorLevel = Store.get("doctorlevel");
                doctorPid = Store.get("doctorpid");
                pageIndex.resetgoto(Store.get("currentpage"));
                setTimeout(function() {
                    ref.open_node("#" + Store.get("tagnode1"));
                    ref.select_node("#" + Store.get("tagnode1"));
                    setTimeout(function() {
                        ref.open_node("#" + Store.get("tagnode2"));
                        ref.select_node("#" + Store.get("tagnode2"));
                        setTimeout(function() {
                            ref.select_node("#" + Store.get("tagnode3"));

                        }, 2000)
                    }, 2000)
                }, 2000)
            }
            this.bind();
        },

        bind: function() {

            $("#downloadDept").on("click", function() {
                var url = "/admin/sys/file/download/depts";
                window.open(url);
            });

            $("#downloadDoctor").on("click", function() {
                var url = "/admin/sys/file/download/doctors";
                window.open(url);
            });

            $tree.bind('activate_node.jstree', function(e, data) {
                var node = data.node;
                tagNode = data.node;

                if (node.original.level === 1) {
                    Store.set("tagnode1", node.id);
                    Store.set("tagnode2", "");
                    Store.set("tagnode3", "");
                    Store.set("tagnode4", "");
                }
                if (node.original.level === 2) {
                    Store.set("tagnode2", node.id);
                    Store.set("tagnode3", "");
                    Store.set("tagnode4", "");
                }
                if (node.original.level === 3) {

                    Store.set("tagnode3", node.id);
                    Store.set("tagnode4", "");
                }
                if (node.original.level === 4) {

                    Store.set("tagnode4", node.id);
                }
                if (node.original.level < 5) {
                    //点击菜单展开或关闭
                    //$tree.jstree("toggle_node", "#" + data.node.id);
                    if (ref.is_closed("#" + node.id)) {
                        ref.open_node("#" + node.id)
                    }
                }
                doctorLevel = node.original.level; //默认医生
                doctorPid = node.original.did;
                type = node.original.type;
                tool.startPageLoading("数据加载中...");
                $doctorListwrap.find(".dept-doctor-tit").text(node.text);

                var orgid = node.original.orgid,
                    orgname = node.original.orgname,
                    deptid = 0,
                    deptname = "",
                    pdeptid = 0, //一级科室
                    pdeptname = "";
                var pnode = $tree.jstree("get_node", node.parent);
                if (node.original.level === 3) {
                    deptid = node.original.did;
                    deptname = node.text;
                    pdeptid = pnode.original.did;
                    pdeptname = pnode.text;
                } else if (node.original.level === 2) {

                    pdeptid = node.original.did;
                    pdeptname = node.text;
                }
                pdeptObj = {
                    pdeptid: pdeptid,
                    pdeptname: pdeptname
                };
                var addDoctorUrl = ROOTPAth + "/admin/bas/doctor/addView?pcode=RPeInstitutions&subcode=BasDepartment&orgid=" + orgid + "&orgname=" + orgname + "&deptid=" + deptid + "&deptname=" + deptname + "&pdeptid=" + pdeptid + "&pdeptname=" + pdeptname;
                $doctorListwrap.find(".j-add-doctor").attr("href", addDoctorUrl);

                pageIndex.reset();


            });
            //新增加
            //新增科室
            $addDeptForm.on('submit', function(e) {
                e.preventDefault();
                var postPath = ROOTPAth + '/admin/bas/dept/save/';
                $.post(postPath, $addDeptForm.serialize(), function(data) {
                    if (data) {
                        $addDeptForm[0].reset();
                        $tree.jstree("refresh");
                        $addDeptModal.modal('hide');
                    }
                });
            });
            //我要编辑科室
            $viewDeptForm.on("click", ".j-edit", function(event) {
                var formDom = event.delegateTarget;

                $(formDom).find(".j-save").show();
                $(formDom).find(".j-edit-input").prop("disabled", false);
                $(this).hide();
            });
            //保存编辑科室
            $viewDeptForm.on("submit", function(e) {
                e.preventDefault();
                var postPath = ROOTPAth + '/admin/bas/dept/update';
                var _ajax = {
                    type: "POST",
                    dataType: "json",
                    url: postPath,
                    data: $viewDeptForm.serialize(),
                    success: function(data) {
                        $tree.jstree("refresh");
                        $viewDeptForm[0].reset();
                        // $viewDeptModal.hide();
                        $viewDeptModal.modal("hide");
                    }
                };
                $.ajax(_ajax);
            });
            $table.on("click", ".j-edit", function(e) {

                Store.set("doctorlevel", doctorLevel);
                Store.set("doctorpid", doctorPid);
                Store.set("currentpage", pageIndex.current);

            });
        },
        //"查看科室信息",
        viewOffice: function(node) {
            console.log(node);
            var superiortitle = "";
            //父级节点

            // if (node.original.level === 2) {
            //     superiortitle = "";

            //     $viewDeptForm.find(".superior-title").hide();
            // } else if (node.original.level === 3) {
            var pnode = $tree.jstree("get_node", node.parent);
            console.log(pnode);
            superiortitle = pnode.text;
            $viewDeptForm.find(".superior-title").show();
            // }
            var id = node.original.did;
            var _ajax = {
                type: "POST",
                cache: false,
                url: ROOTPAth + '/admin/bas/dept/detail/',
                data: {
                    id: id
                },
                dataType: "json",
                success: function(res) {
                    var newdata = $.extend({}, {
                        superiortitle: superiortitle
                    }, res);
                    $viewDeptForm.autofill(newdata);
                    $viewDeptModal.modal("show");
                }
            };
            $.ajax(_ajax);
            //重置表单disabled
            $viewDeptForm.find(".j-edit-input").prop("disabled", true);
            $viewDeptForm.find(".j-edit").show();
            $viewDeptForm.find(".j-save").hide();

            $viewDeptModal.modal("show");


        },
        /*添加医生*/
        addDoctor: function(node) {
            var orgid = node.original.orgid,
                orgname = node.original.orgname,
                deptid = 0,
                deptname = "",
                pdeptid = 0, //一级科室
                pdeptname = "";

            //父级节点
            var pnode = $tree.jstree("get_node", node.parent);
            if (node.original.level === 3) {
                deptid = node.original.did;
                deptname = node.text;
                pdeptid = pnode.original.did;
                pdeptname = pnode.text;
            } else if (node.original.level === 2) {

                pdeptid = node.original.did;
                pdeptname = node.text;
            }
            var addDoctorUrl = ROOTPAth + "/admin/bas/doctor/addView?pcode=RPeInstitutions&subcode=BasDepartment&orgid=" + orgid + "&orgname=" + orgname + "&deptid=" + deptid + "&deptname=" + deptname + "&pdeptid=" + pdeptid + "&pdeptname=" + pdeptname;

            window.location.href = addDoctorUrl;
        },
        //禁用
        disbaleDoctor: function($this) {
            var $tr = $this.closest("tr");
            var doctorId = $tr.data("id");
            //var deleteObjPath = ROOTPAth + '/admin/bas/doctor/delete';
            var deleteObjPath = ROOTPAth + '/admin/bas/doctor/disbale';
            $.ajax({
                url: deleteObjPath,
                type: "POST",
                data: {
                    did: doctorId
                },
                success: function(data) {
                    pageIndex.reset();
                }
            });
        },
        enableDoctor: function($this) {
            var $tr = $this.closest("tr");
            var doctorId = $tr.data("id");
            //var deleteObjPath = ROOTPAth + '/admin/bas/doctor/delete';
            var deleteObjPath = ROOTPAth + '/admin/bas/doctor/enable';
            $.ajax({
                url: deleteObjPath,
                type: "POST",
                data: {
                    did: doctorId
                },
                success: function(data) {
                    pageIndex.reset();
                }
            });
        },
        /*删除医生*/
        delDoctor: function($this) {
            var $tr = $this.closest("tr");
            var doctorId = $tr.data("id");
            var deleteObjPath = ROOTPAth + '/admin/bas/doctor/delete';
            $.ajax({
                url: deleteObjPath,
                type: "POST",
                data: {
                    id: doctorId
                },
                success: function(data) {
                    pageIndex.reset();
                }
            });
        },
        /*添加机构*/
        addDept: function(node) {
            var pid, superiortitle = "";
            if (node.parent === "#") {
                pid = 0;
                superiortitle = "";
                $addDeptForm.find(".superior-title").hide();
            } else {
                pid = node.original.did;
                superiortitle = node.text;
                $addDeptForm.find(".superior-title").show();
            }
            var obj = {
                pid: pid,
                orgid: node.original.orgid,
                orgname: node.original.orgname,
                superiortitle: superiortitle,
                orgtitle: node.original.orgname
            };
            /*deptid
             deptname
             orgid
             orgname*/
            $addDeptForm.autofill(obj);
            $addDeptModal.modal("show")
        }
    };
    Utilitiy.init()
});