/**
 * Created by feiwen8772 on 15/5/6.
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
    require("res-build/res/plugin/jquery-validation-1.13.1/dist/jquery.validate.min.js");
    require("res-build/res/plugin/bs-confirmation/bootstrap-confirmation.js");
    require("res-build/res/module/underscore/underscore.js");
    var tool = require("tool");
    var Page = require("page");
    var juicer = require("juicer");
    var pageIndex;

    var rolePageIndex;//角色分页
    var $table = $("#datatable_ajax");
    var $accountList = $("#account-list");
    var $UserForm = $('#vUserForm');
    var $ModifyForm = $("#vUserModifyForm");
    var $accountInfoTable = $("#account-info-table");
    var $allRoleTable = $("#allRoleTable");
    var $modifyModal = $('#modifyModal');
    var $addModal = $('#addModal');
    var $addRoletipModal = $('#modal-box');
    var pagelength = 10; //一页多少条；
    var orgsPath = ROOTPAth + '/admin/bas/organization/getsources1';
    $('body').tooltip({
        selector: '.has-tooltip'
    });

    var orgsTPL = juicer(
        ['{@each data as item,index}',
            '<label style="display: block;margin-bottom: 8px" data-spell="${item.spell}">',
            '<input value="${item.id}" name="orgs"',
            '{@if item.hasorgs===1}',
            'checked="checked"',
            '{@/if}',
            ' type="checkbox"> ${item.title}',
            '</label> ',
            '{@/each}'].join(""));
    var orgsDisabledTPL = juicer(
        ['{@each data as item,index}',
            '<label style="display: block;margin-bottom: 8px">',
            '<input value="${item.id}" disabled name="orgs" checked="checked"  type="checkbox"> ${item.name}',
            '</label> ',
            '{@/each}'].join(""));
    $addModal.on('show.bs.modal', function (event) {

        $UserForm[0].reset();


        $.ajax({
            url: orgsPath,
            type: "POST",
            datatype: "json",
            success: function (data) {

                if (data.code === 1) {
                    $addModal.find(".checkbox").empty().append(orgsTPL.render(data));
                }
            }
        });
    });
    $modifyModal.on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the modal

        var usernema = button.data('usernema'); // Extract info from data-* attributes
        var email = button.data('email');
        var mobile = button.data('mobile');
        var id = button.data('id');
        var orgs = button.data('orgs');
        $modifyModal.data('orgs', orgs);
        var orgsObj = {};
        orgsObj.data = orgs;

        var modal = $(this);
        //modal.find('.modal-title').text('New message to ' + recipient)
        modal.find('input[name=username]').val(usernema);
        modal.find('input[name=email]').val(email);
        modal.find('input[name=mobile]').val(mobile);
        modal.find('input[name=id]').val(id);
        modal.find(".checkbox").empty().append(orgsDisabledTPL.render(orgsObj));
    });
    //角色列表模板
    var roleListTpl = juicer(
        [
            '{@if total === 0}',
            '<tr>',
            '<td colspan="3" style="text-align:center">',
            '暂无记录,请添加',
            '</td>',

            '</tr>',

            '{@else}',
            '{@each data as item,index}',
            '<tr role="row" class="odd" data-roleid="${item.id}"  data-rolecode="${item.code}"         data-rolename="${item.title}" >',
            '    <td>${item.title}</td>',
            '    <td>${item.code}</td>',
            '    <td>',
            '<button type="button" class="btn btn-default btn-xs j-add" data-toggle="confirmation" data-placement="top" data-original-title="" title="" ',
            '{@if item.hasrole === 1}',
            'style="display:none"',
            '{@else}',
            '',
            '{@/if}',
            ' ><span  class="iconfont iconfont-xs">&#xe61f;</span>添加权限 </button>',
            '<button type="button" class="btn btn-danger btn-xs j-del" data-toggle="confirmation" data-placement="top" data-original-title="" title="" ',
            '{@if item.hasrole === 1}',
            '',
            '{@else}',
            'style="display:none"',
            '{@/if}',
            ' ><span class="iconfont iconfont-xs">&#xe61d;</span>删除权限 </button>',

            '        </td>',
            '</tr>',
            '{@/each}',
            '{@/if}'].join(""));
    var $addRoleModal = $('#addRoleModal');
    var $hasroleCon = $addRoleModal.find(".account-has-role");
    $addRoleModal.on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        $addRoletipModal.modal('hide');
        var usernema = button.data('usernema'); // Extract info from data-* attributes
        var email = button.data('email');
        var mobile = button.data('mobile');
        var id = button.data('id');

        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this);
        //modal.find('.modal-title').text('New message to ' + recipient)
        var accountInfoHtml = ['<tr >',
            '    <td>' + usernema + '</td>',
            '    <td>' + email + '</td>',
            '    <td>' + mobile + '</td>', '</tr>'].join("");
        modal.find($accountInfoTable.find("tbody")).html(accountInfoHtml);
        modal.find('input[name=id]').val(id);
        //角色分页
        rolePageIndex = new Page({
            ajax: {
                url: ROOTPAth + '/admin/sys/role/list',
                type: 'POST',
                dataType: 'json',
                data: function () {
                    var data = {
                        length: 5
                    };

                    return data;
                },
                success: function (res) {
                    var newData = $.extend({}, res);

                    var rolesPath = ROOTPAth + '/admin/sys/userRole/roleByUser/' + id;
                    $.post(rolesPath, function (data) {
                        /*console.log("data");
                         console.log(data);*/
                        $hasroleCon.empty();
                        $.each(data, function (i, val) {
                            var hasroleid = val.id;
                            //设置已有权限
                            $hasroleCon.append('<span class="label label-default j-has-role-' + val.code + '">' + val.title + '</span> ');
                            $.each(res.data, function (i, val) {
                                if (newData.data[i].id == hasroleid) {
                                    newData.data[i].hasrole = 1
                                }
                            });
                            //newData.data[i].flagtxt = val.flag === 0 ? '启用' : '停用'

                        });
                        modal.find($allRoleTable.find("tbody")).empty().append(roleListTpl.render(newData));

                        //删除权限
                        $addRoleModal.find(".j-del").confirmation({
                            title: "确定删除该权限吗？",
                            btnOkLabel: "确定",
                            btnCancelLabel: "取消",
                            onConfirm: function (event, element) {
                                event.preventDefault();
                                Utilitiy.delRole($(element));
                            }/*,
                             onCancel: function () {
                             alert('cancel')
                             }*/
                        });
                        $addRoleModal.find(".j-add").confirmation({
                            title: "确定增加该权限吗？",
                            btnOkLabel: "确定",
                            btnCancelLabel: "取消",
                            onConfirm: function (event, element) {
                                event.preventDefault();
                                Utilitiy.addRole($(element));
                            }/*,
                             onCancel: function () {
                             alert('cancel')
                             }*/
                        });
                        /*$.each(data, function(index, obj){
                         $('#allRoleTable span[name="remove'+obj.id+'"]').show();
                         $('#allRoleTable span[name="add'+obj.id+'"]').hide();
                         });*/
                    });

                }
            },
            pageName: "page",
            tpl: {
                go: false //隐藏跳转到第几页
            },
            getTotalPage: function (res) {
                //返回总页数
                return Math.ceil(res.total / pagelength);
            },
            pageWrapper: '.role-table-page'
        });
        rolePageIndex.reset();
    });

    var listTpl = juicer(
        [
            '{@if total === 0}',
            '<tr>',
            '<td colspan="5" style="text-align:center">',
            '暂无记录,请添加',
            '</td>',

            '</tr>',

            '{@else}',
            '{@each data as item,index}',
            '{@if index%2==0}',
            '<tr role="row" class="odd" data-accountid="${item.id}">',
            '{@else}',
            '<tr role="row" class="even" data-accountid="${item.id}">',
            '{@/if}',
            '    <td>${item.username}</td>',
            '    <td>${item.realname}</td>',
            '    <td>${item.orgname}</td>',

            '    <td class=" heading">',
            /*' <button type="button" class="btn btn-default btn-xs j-disable j-edit" data-toggle="modal" data-target="#modifyModal"  data-usernema="${item.username}" data-email="${item.email}" data-orgs="${item.orgstxt}" data-mobile="${item.mobile}" data-id="${item.id}"><span class="iconfont iconfont-xs">&#xe62d;</span>查看</button>',*/
            '{@if item.id!==1&&item.id!==2}',
            '{@if item.utype!==2}',
            ' <button type="button" class="btn btn-default btn-xs j-disable j-add" data-toggle="modal" data-target="#addRoleModal" data-usernema="${item.username}" data-email="${item.email}" data-mobile="${item.mobile}" data-id="${item.id}"><span  class="iconfont iconfont-xs">&#xe610;</span>添加角色 </button>',
            '{@/if}',
            //删除
            /*' <button type="button" class="btn btn-danger btn-xs j-disable j-del" data-toggle="confirmation"  data-placement="top"><span class="iconfont iconfont-xs">&#xe618;</span>删除</button>',*/
            ' <button type="button" class="btn btn-danger btn-xs j-disable" data-toggle="confirmation" data-placement="left"',
                                    '{@if item.flag === 9}',
                                    'style="display:none"',
                                    '{@else}',
                                    '',
                                    '{@/if}',
                                    '>' +
                                    '<span  class="iconfont iconfont-xs">&#xe61d;</span>禁用 </button>',
                                    //启用
                                    ' <button type="button" class="btn btn-default btn-xs j-enable" data-toggle="confirmation" data-placement="left"',
                                    '{@if item.flag === 9}',
                                    '',
                                    '{@else}',
                                    'style="display:none"',
                                    '{@/if}',
                                    '><span    class="iconfont iconfont-xs">&#xe61f;</span>启用 </button>',
            '{@/if}',
            '    </td>',
            '</tr>',
            '{@/each}',
            '{@/if}'].join(""));

    var Utilitiy = {
        init: function () {

            tool.startPageLoading();
            this.bind();
        },
        bind: function () {
            var self = this;
            //列表分页
            pageIndex = new Page({
                ajax: {
                    url: ROOTPAth + '/admin/sys/doctor/acount/list',
                    type: 'POST',
                    dataType: 'json',
                    data: function () {
                        var data = {
                            length: pagelength
                        };
                        return data;
                    },
                    success: function (res) {
                        var newData = $.extend({}, res);
                        $.each(res.data, function (i, val) {

                            newData.data[i].orgstxt = JSON.stringify(val.orgs);
                        });
                        tool.stopPageLoading();
                        $accountList.find(".page-info-num").text(res.data.length);

                        $table.find("tbody").empty().append(listTpl.render(newData));
                        //删除权限
                        /*$table.find(".j-del").confirmation({
                            title: "确定删除该帐号吗？",
                            btnOkLabel: "确定",
                            btnCancelLabel: "取消",
                            onConfirm: function (event, element) {
                                event.preventDefault();
                                self.deleteUser($(element));
                            }
                        });*/
                        $table.find(".j-disable").confirmation({
                                                title: "确定禁用该医生吗？",
                                                btnOkLabel: "确定",
                                                btnCancelLabel: "取消",
                                                onConfirm: function (event, element) {
                                                    event.preventDefault();
                                                    Utilitiy.disbaleDoctor($(element));
                                                }
                                            });
                                            $table.find(".j-enable").confirmation({
                                                title: "确定启用该医生吗？",
                                                btnOkLabel: "确定",
                                                btnCancelLabel: "取消",
                                                onConfirm: function (event, element) {
                                                    event.preventDefault();
                                                    Utilitiy.enableDoctor($(element));
                                                }
                                            });
                        //$table.find("tbody").empty().append("");
                    }
                },
                pageName: "page",
                /*tpl: {
                 go: true //隐藏跳转到第几页
                 },*/
                getTotalPage: function (res) {
                    //返回总页数
                    return Math.ceil(res.total / pagelength);
                },
                pageWrapper: '.table-page'
            });
            pageIndex.reset();
            //拼音首字母检索
            $addModal.on("keyup", ".search-field", function () {
                var $this = $(this);
                self.searchOrgs($this);
            });
            $addModal.on("click", ".search-clear-btn", function (e) {
                e.preventDefault();
                var $input = $addModal.find(".search-field");
                $input.val("");
                self.searchOrgs($input);
            });
            $addModal.on("change", "input[name='orgs']", function (e) {
                var $label = $(this).closest("label");
                if (this.checked) {
                    $label.prependTo($addModal.find(".checkbox"));
                } else {   //反之 取消全选
                    var $lastchecked = $addModal.find(".checkbox").find(":checked").last().closest("label");
                    $label.insertAfter($lastchecked);
                }
            });
            //拼音首字母检索
            $modifyModal.on("keyup", ".search-field", function () {
                var $this = $(this);
                self.searchModifyOrgs($this);
            });
            $modifyModal.on("click", ".search-clear-btn", function (e) {
                e.preventDefault();
                var $input = $modifyModal.find(".search-field");
                $input.val("");
                self.searchModifyOrgs($input);
            });
            $modifyModal.on("change", "input[name='orgs']", function (e) {
                var $label = $(this).closest("label");
                if (this.checked) {
                    $label.prependTo($modifyModal.find(".checkbox"));
                } else {   //反之 取消全选
                    var $lastchecked = $modifyModal.find(".checkbox").find(":checked").last().closest("label");
                    $label.insertAfter($lastchecked);
                }
            });
            //分页，修改每页显示数据
            $accountList.on("change", ".j-length", function () {
                var $this = $(this);
                pagelength = $this.val();
                var index = $this.get(0).selectedIndex;
                $accountList.find(".j-length").not(this).get(0).selectedIndex = index;
                pageIndex.reset();
            });
            //表单验证-添加用户
            $UserForm.validate({
                rules: {
                    username: {
                        required: true,
                        remote: {                         //自带远程验证存在的方法
                            url: ROOTPAth + '/admin/sys/adminAccount/sameName',
                            type: "POST",
                            dataType: "json",
                            data: {
                                /*username: function () {
                                 return $UserForm.find('input[name="userName"]').val();
                                 }*/
                            }
                        }
                    },
                    password: "required",
                    email: "required",
                    mobile: "required",
                    orgs: "required"
                },
                messages: {
                    username: {
                        required: "用户名不能为空",
                        remote: "用户名重复"
                    },
                    password: "密码不能为空",
                    email: "邮箱不能为空",
                    mobile: "手机不能为空",
                    orgs: "至少选择一个机构"
                },
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input


                invalidHandler: function (event, validator) { //display error alert on form submit
                    //	                $('.alert-danger', $('.login-form')).show();
                },
                highlight: function (element) { // hightlight error inputs
                    $(element)
                        .closest('.form-group').addClass('has-error'); // set error class to the control group
                },

                success: function (label) {
                    label.closest('.form-group').removeClass('has-error');
                    label.remove();
                },

                errorPlacement: function (error, element) {
                    if ($(element).attr("name") === "orgs") {
                        $(element).closest(".col-md-8").append(error)
                    } else {
                        error.insertAfter(element);
                    }
                },
                submitHandler: function () {
                    var savePath = ROOTPAth + '/admin/sys/adminAccount/save';


                    $.post(savePath, $UserForm.serialize(), function (data) {
                        if (data.data.id) {
                            $addRoletipModal.modal('show');
                            $addRoletipModal.find(".j-add-role").data({
                                usernema: $UserForm.find("input[name=username]").val(),
                                email: $UserForm.find("input[name=email]").val(),
                                mobile: $UserForm.find("input[name=mobile]").val(),
                                id: data.data.id
                            });
                            // data-usernema="${item.username}" data-email="${item.email}" data-mobile="${item.mobile}" data-id="${item.id}"
                            $('#addModal').modal('hide');
                        }

                        pageIndex.reset();
                    });
                }
            });
            //我要编辑
            $ModifyForm.on("click", ".j-form-edit", function (event) {
                var formDom = event.delegateTarget;
                $(this).hide();
                $(formDom).find(".j-form-save").show();
                $(formDom).find(".j-disabled-item[disabled]").prop("disabled", false);
                //显示机构
                $(formDom).find(".search-input").show();
                $.ajax({
                    url: orgsPath,
                    type: "POST",
                    datatype: "json",
                    data: {length: 100},
                    success: function (data) {

                        if (data.code === 1) {

                            var newData = $.extend({}, data);
                            newData.hasorgs = [];
                            newData.noorgs = [];
                            var orgs = $modifyModal.data("orgs");

                            $.each(orgs, function (i, val) {
                                var hasOrgsId = parseInt(val.id);
                                $.each(newData.data, function (i, val) {

                                    if (newData.data[i].id == hasOrgsId) {
                                        newData.data[i].hasorgs = 1;
                                    }

                                });

                                //newData.data[i].flagtxt = val.flag === 0 ? '启用' : '停用'

                            });
                            var cutArr = _.partition(newData.data, function (val) {
                                return val.hasorgs === 1 ? true : false
                            });
                            var UnionArr=_.union(cutArr[0],cutArr[1]);
                            newData.data=UnionArr;
                            $(formDom).find(".checkbox").empty().append(orgsTPL.render(newData));
                        }
                    }
                });
            });
            $modifyModal.on('show.bs.modal', function (event) {
                var $modal = $(this);
                $modal.find('input[name=email]').prop("disabled", true);
                $modal.find('input[name=mobile]').prop("disabled", true);
                $modal.find(".j-form-save").hide();
                $modal.find(".j-form-edit").show();
            });
            //表单验证-修改用户
            $ModifyForm.validate({
                rules: {
                    /*username: {
                     required: true,
                     remote: {                         //自带远程验证存在的方法
                     url: ROOTPAth + '/admin/sys/adminAccount/sameName',
                     type: "POST",
                     dataType: "json",
                     data: {
                     *//*username: function () {
                     return $UserForm.find('input[name="userName"]').val();
                     }*//*
                     }
                     }
                     },*/
                    email: "required",
                    mobile: "required"
                },
                messages: {
                    /*username: {
                     required: "用户名不能为空",
                     remote: "用户名重复"
                     },*/
                    email: "邮箱不能为空",
                    mobile: "手机不能为空"
                },
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input


                invalidHandler: function (event, validator) { //display error alert on form submit
                    //	                $('.alert-danger', $('.login-form')).show();
                },
                highlight: function (element) { // hightlight error inputs
                    $(element)
                        .closest('.form-group').addClass('has-error'); // set error class to the control group
                },

                success: function (label) {
                    label.closest('.form-group').removeClass('has-error');
                    label.remove();
                },

                errorPlacement: function (error, element) {
                    error.insertAfter(element);
                },
                submitHandler: function () {


                    var updatePath = ROOTPAth + '/admin/sys/adminAccount/update';
                    $.post(updatePath, $ModifyForm.serialize(), function (data) {
                        $('#modifyModal').modal('hide');
                        pageIndex.reset();
                    });

                }
            });

        },
        searchModifyOrgs: function ($this) {
            var key = $this.val().toUpperCase();
            $modifyModal.find(".checkbox").find("label").each(function () {
                var spell = $(this).data("spell").toUpperCase();

                //拼音
                if (spell.indexOf(key) >= 0) {
                    $(this).show();
                } else {
                    if (!$(this).find("input").is(":checked")) {
                        //console.log(!$(this).find("input").is(":checked"))
                        $(this).hide();
                    }
                }

            });
        },
        searchOrgs: function ($this) {
            var key = $this.val().toUpperCase();
            $addModal.find(".checkbox").find("label").each(function () {
                var spell = $(this).data("spell").toUpperCase();

                //拼音
                if (spell.indexOf(key) >= 0) {
                    $(this).show();
                } else {
                    if (!$(this).find("input").is(":checked")) {
                        //console.log(!$(this).find("input").is(":checked"))
                        $(this).hide();
                    }
                }

            });
        },
        delRole: function ($that) {
            var $this = $that;
            var $tr = $this.closest("tr");
            var roleId = $tr.data("roleid");
            var roleCode = $tr.data("rolecode");
            var deleteObjPath = ROOTPAth + '/admin/sys/userRole/delete/' + $addRoleModal.find('input[name=id]').val() + "/" + roleId;
            $.ajax({
                url: deleteObjPath,
                type: "POST",
                success: function (data) {
                    if (data === true) {

                        $hasroleCon.find('.j-has-role-' + roleCode).remove();
                        $this.hide();
                        $tr.find(".j-add").show();
                    }
                }
            });
        },
        addRole: function ($that) {
            var $this = $that;
            var $tr = $this.closest("tr");
            var roleId = $tr.data("roleid");
            var roleName = $tr.data("rolename");
            var roleCode = $tr.data("rolecode");
            var createObjPath = ROOTPAth + '/admin/sys/userRole/add';
            $.post(createObjPath, {uid: $addRoleModal.find('input[name=id]').val(), rid: roleId}, function (data) {

                if (data === true) {

                    $hasroleCon.append('<span class="label label-default j-has-role-' + roleCode + '">' + roleName + '</span> ');
                    $this.hide();
                    $tr.find(".j-del").show();
                }
            });
        },
        //禁用
                disbaleDoctor: function ($this) {
                            var $tr = $this.closest("tr");
                            var doctorId = $tr.data("accountid");
                            //var deleteObjPath = ROOTPAth + '/admin/bas/doctor/delete';
                            var deleteObjPath = ROOTPAth + '/admin/sys/doctor/acount/disable/'+doctorId;
                            $.ajax({
                                url: deleteObjPath,
                                type: "POST",
                                data: {
                                },
                                success: function (data) {
                                    pageIndex.reset();
                                }
                            });
                        },
                enableDoctor: function ($this) {
                                    var $tr = $this.closest("tr");
                                    var doctorId = $tr.data("accountid");
                                    //var deleteObjPath = ROOTPAth + '/admin/bas/doctor/delete';
                                    var deleteObjPath = ROOTPAth + '/admin/sys/doctor/acount/enable/'+doctorId;
                                    $.ajax({
                                        url: deleteObjPath,
                                        type: "POST",
                                        data: {
                                        },
                                        success: function (data) {
                                            pageIndex.reset();
                                        }
                                    });
                                },
        deleteUser: function ($that) {
            var $tr = $that.closest("tr");
            var accountid = $tr.data("accountid");
            var delPath = ROOTPAth + '/admin/sys/adminAccount/delete/' + accountid;
            $.ajax({
                url: delPath,
                type: "POST",
                success: function (data) {
                    pageIndex.reset();
                }
            });

        }
    };
    Utilitiy.init();
});

/*<script>
 var TableAjax = function () {

 var handleRecords = function () {

 var grid = new Datatable();
 grid.init({
 src: $("#datatable_ajax"),
 onSuccess: function (grid) {
 // execute some code on network or other general error
 },
 onError: function (grid) {
 // execute some code on network or other general error
 },
 dataTable: {
 "columnDefs": [
 {
 "targets": 3,
 "render": function (data, type, row, meta) {
 return '<span data-toggle="tooltip" data-placement="left" title="修改" onclick="updateModal(\'' + data.id + '\')" class="glyphicon glyphicon-pencil link-hand-ioc link-color"></span>&nbsp' +
 '<span data-toggle="tooltip" data-placement="left" title="添加角色" onclick="addRole(\'' + data.id + '\')" class="glyphicon glyphicon-plus link-hand-ioc link-color"></span>&nbsp' +
 '<span data-toggle="tooltip" data-placement="left" title="禁用" onclick="deleteUser(\'' + data.id + '\')" class="glyphicon glyphicon-trash link-hand-ioc link-color"></span>';
 },
 "orderable": false
 }
 ],
 "columns": [
 { "title": "账号", "data": "username","orderable": false  },
 { "title": "Email", "data": "email", "orderable": false  },
 { "title": "手机号码", "data": "mobile", "orderable": false  },
 { "title": "操作", "data": null, "orderable": false }
 ],
 "pageLength": 10,
 "serverSide": true,
 "ajax": {
 "url": "sys/adminAccount/list"
 },
 "order": [
 [ 2, "asc" ]
 ]
 }
 });
 }
 return {
 init: function () {
 handleRecords();
 }
 };
 }();

 jQuery(document).ready(function () {
 TableAjax.init();
 });

 $(function(){
 $('#vUserForm span[name="sameUserNameError"]').hide();
 });

 function addRole(userId){
 var modulesPath = $('#webBasePath').val() + '/admin/sys/manageRoleView/' + userId;
 ajaxLoadView(modulesPath)
 }

 function deleteUser(userId){
 var delPath = $('#webBasePath').val() + '/admin/sys/adminAccount/delete/' + userId;
 $.ajax({
 url:delPath,
 type:"DELETE",
 success:function(data){
 $('#addModal').modal('hide');
 var url = $('#webBasePath').val() + '/admin/sys/adminAccount';
 ajaxLoadView(url);
 }
 });
 }

 function createUser(){
 var savePath = $('#webBasePath').val() + '/admin/sys/adminAccount/save';
 $.post(savePath, $('#vUserForm').serialize(), function(data){
 $('#addModal').modal('hide');
 var url = $('#webBasePath').val() + '/admin/sys/adminAccount';
 ajaxLoadView(url);
 });
 }

 function updateModal(uId){
 var getUserPath = $('#webBasePath').val() + '/admin/sys/adminAccount/update/' + uId;
 $.get(getUserPath, function(data){
 $("#vUserModifyForm").autofill(data);
 $('#uidHidde').val(uId);
 $('#modifyModal').modal('show');
 });
 }

 function updateUser(){
 var updatePath = $('#webBasePath').val() + '/admin/sys/adminAccount/update';
 $.post(updatePath, $('#vUserModifyForm').serialize(), function(data){
 $('#modifyModal').modal('hide');
 var url = $('#webBasePath').val() + '/admin/sys/adminAccount';
 ajaxLoadView(url);
 });
 }

 $('#vUserForm input[name="username"]').blur(function(e){
 var sameNamePath = $('#webBasePath').val() + '/admin/sys/adminAccount/sameName/' + $(this).val();
 $.get(sameNamePath, function(data){
 if(data){
 $('#vUserForm span[name="sameUserNameError"]').show();
 } else {
 $('#vUserForm span[name="sameUserNameError"]').hide();
 }
 });
 });

 $('#vUserForm').validate({
 rules:{
 username:"required",
 password:"required",
 email:"required",
 mobile:"required"
 },
 messages: {
 username:"用户名不能为空",
 password:"密码不能为空",
 email:"邮箱不能为空",
 mobile:"手机不能为空"
 },
 submitHandler: function() {
 var sameNamePath = $('#webBasePath').val() + '/admin/sys/adminAccount/sameName/' + $('#vUserForm input[name="username"]').val();
 $.get(sameNamePath, function(data){
 if(data){
 $('#vUserForm span[name="sameUserNameError"]').show();
 } else {
 createUser();
 $('#vUserForm span[name="sameUserNameError"]').hide();
 }
 });
 }
 });

 $('#vUserModifyForm').validate({
 rules:{
 username:"required",
 email:"required",
 mobile:"required"
 },
 messages: {
 username:"用户名不能为空",
 email:"邮箱不能为空",
 mobile:"手机不能为空"
 },
 submitHandler: function() {
 updateUser();
 }
 });

 function ajaxLoadView(url){
 var pageContentBody = $('.page-content .page-content-body');
 $.ajax({
 type: "GET",
 cache: false,
 url: url,
 dataType: "html",
 success: function (res) {
 Metronic.stopPageLoading();
 pageContentBody.html(res);
 Layout.fixContentHeight(); // fix content height
 Metronic.initAjax(); // initialize core stuff
 },
 error: function (xhr, ajaxOptions, thrownError) {
 pageContentBody.html('<h4>Could not load the requested content.</h4>');
 Metronic.stopPageLoading();
 }
 });
 }
 </script>*/
