/**
 * Created by feiwen8772 on 15/5/6.
 *
 *
 */
define(function(require, exports, module) {
    require("res-build/res/plugin/jquery-validation-1.13.1/dist/jquery.validate.min.js");
    require("res-build/res/plugin/bs-confirmation/bootstrap-confirmation.js");
    require("res-build/res/module/underscore/underscore.js");
    var tool = require("tool");
    var Page = require("page");
    var juicer = require("juicer");
    var pageIndex;

    var rolePageIndex; //角色分页
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
            '<label style="display: block;margin-bottom: 8px" data-spell="${item.spell}" data-orgname="${item.title}" data-orgid="${item.id}">',
            '<input value="${item.id}" name="orgs"',
            '{@if item.hasorgs===1}',
            'checked="checked"',
            '{@/if}',
            ' type="radio"> ${item.title}',
            '</label> ',
            '{@/each}'
        ].join(""));
    var orgsDisabledTPL = juicer(
        ['{@each data as item,index}',
            '<label style="display: block;margin-bottom: 8px" data-spell="${item.spell}" data-orgname="${item.title}" data-orgid="${item.id}">',
            '<input value="${item.id}" disabled name="orgs" checked="checked"  type="radio"> ${item.title}',
            '</label> ',
            '{@/each}'
        ].join(""));
    $addModal.on('show.bs.modal', function(event) {

        $UserForm[0].reset();
        var hidden_input = $("<input type='hidden' id='orgname' name='orgname'><input type='hidden' id='orgid' name ='orgid'>");
        $UserForm.find(".modal-body").before(hidden_input);

        $.ajax({
            url: orgsPath,
            type: "POST",
            datatype: "json",
            success: function(data) {
                console.log(data);
                if (data.code === 1) {
                    $addModal.find(".checkbox").empty().append(orgsTPL.render(data));
                }
            }
        });
    });
    $modifyModal.on('show.bs.modal', function(event) {
        var button = $(event.relatedTarget); // Button that triggered the modal

        var usernema = button.data('usernema'); // Extract info from data-* attributes
        // var email = button.data('email');
        // var mobile = button.data('mobile');
        var id = button.data('id');
        var orgs = button.data('orgs');
        var orgsname = button.data('orgid');
        // $modifyModal.data('orgs', orgs);
        $ModifyForm.data('orgid', button.data('orgid'));
        // var orgsObj = {};
        // orgsObj.data = orgs;
        // var hidden_input = $("<input type='hidden' id='" + data_orgname + "' name='" + data_orgname + "'><input type='hidden' id='" + data_orgid + "' name ='" + data_orgid + "'>");
        // $("#vUserForm").find(".modal-body").prev(hidden_input);
        var hidden_input = $("<input type='hidden' id='orgname' name='orgname' value=" + button.data('orgs') + "><input type='hidden' id='orgid' name ='orgid'  value=" + button.data('orgid') + ">");
        $("#vUserModifyForm").find(".modal-body").before(hidden_input);
        var modal = $(this);
        //modal.find('.modal-title').text('New message to ' + recipient)
        modal.find('input[name=username]').val(usernema);
        // modal.find('input[name=email]').val(email);
        // modal.find('input[name=mobile]').val(mobile);
        modal.find('input[name=id]').val(id);
        // modal.find(".checkbox").empty().append(orgsDisabledTPL.render(orgsObj));
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

            // '    <td>',
            // '{@each item.orgs as item2,index}',
            // '<p style="margin: 0;padding:0;line-height: 1.5">${item2.name}</p>',
            // '{@/each}',
            // '</td>',
            '    <td>${item.orgname}</td>',


            '    <td class=" heading">',
            ' <button type="button" class="btn btn-default btn-xs j-disable j-edit" data-toggle="modal" data-target="#modifyModal"  data-usernema="${item.username}"  data-orgs="${item.orgname}"  data-id="${item.id}" data-orgid="${item.orgid}"><span class="iconfont iconfont-xs">&#xe62d;</span>查看</button>',
            '{@if item.id!==1}',
            // ' <button type="button" class="btn btn-default btn-xs j-disable j-add" data-toggle="modal" data-target="#addRoleModal" data-usernema="${item.username}"   data-id="${item.id}"><span  class="iconfont iconfont-xs">&#xe610;</span>添加角色 </button>',
            ' <button type="button" class="btn btn-danger btn-xs j-disable j-del" data-toggle="confirmation"  data-placement="top"><span class="iconfont iconfont-xs">&#xe618;</span>删除</button>',
            '{@/if}',
            '    </td>',
            '</tr>',
            '{@/each}',
            '{@/if}'
        ].join(""));
    var Utilitiy = {
        init: function() {

            tool.startPageLoading();
            this.bind();
        },
        bind: function() {
            var self = this;
            //列表分页
            pageIndex = new Page({
                ajax: {
                    url: ROOTPAth + '/admin/sys/adminAccount/list',
                    type: 'POST',
                    dataType: 'json',
                    data: function() {
                        var data = {
                            length: pagelength
                        };
                        return data;
                    },
                    success: function(res) {
                        var newData = $.extend({}, res);

                        $.each(res.data, function(i, val) {

                            newData.data[i].orgstxt = JSON.stringify(val.orgs);
                        });
                        tool.stopPageLoading();
                        $accountList.find(".page-info-num").text(res.data.length);

                        $table.find("tbody").empty().append(listTpl.render(newData));
                        //删除权限
                        $table.find(".j-del").confirmation({
                            title: "确定删除该帐号吗？",
                            btnOkLabel: "确定",
                            btnCancelLabel: "取消",
                            onConfirm: function(event, element) {
                                event.preventDefault();
                                self.deleteUser($(element));
                            }
                        });
                        //$table.find("tbody").empty().append("");
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
            pageIndex.reset();
            //拼音首字母检索
            $addModal.on("keyup", ".search-field", function() {
                var $this = $(this);
                self.searchOrgs($this);
            });
            $addModal.on("click", ".search-clear-btn", function(e) {
                e.preventDefault();
                var $input = $addModal.find(".search-field");
                $input.val("");
                self.searchOrgs($input);
            });
            $addModal.on("change", "input[name='orgs']", function(e) {
                var $label = $(this).closest("label");
                var data_orgname = $label.attr("data-orgname");
                var data_orgid = $label.attr("data-orgid");


                $("#orgname").val(data_orgname);
                $("#orgid").val(data_orgid);

                if (this.checked) {
                    $label.prependTo($addModal.find(".checkbox"));

                } else { //反之 取消全选
                    var $lastchecked = $addModal.find(".checkbox").find(":checked").last().closest("label");
                    $label.insertAfter($lastchecked);
                }
            });
            //拼音首字母检索
            $modifyModal.on("keyup", ".search-field", function() {
                var $this = $(this);
                self.searchModifyOrgs($this);
            });
            $modifyModal.on("click", ".search-clear-btn", function(e) {
                e.preventDefault();
                var $input = $modifyModal.find(".search-field");
                $input.val("");
                self.searchModifyOrgs($input);
            });
            $modifyModal.on("change", "input[name='orgs']", function(e) {
                var $label = $(this).closest("label");
                var data_orgname = $label.attr("data-orgname");
                var data_orgid = $label.attr("data-orgid");

                $("#orgname").val(data_orgname);
                $("#orgid").val(data_orgid);
                if (this.checked) {
                    $label.prependTo($modifyModal.find(".checkbox"));
                } else { //反之 取消全选
                    var $lastchecked = $modifyModal.find(".checkbox").find(":checked").last().closest("label");
                    $label.insertAfter($lastchecked);
                }
            });
            //分页，修改每页显示数据
            $accountList.on("change", ".j-length", function() {
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
                        remote: { //自带远程验证存在的方法
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
                    password_again: {
                        equalTo: "#password"
                    },

                    orgs: "required"
                },
                messages: {
                    username: {
                        required: "用户名不能为空",
                        remote: "用户名重复"
                    },
                    password: "密码不能为空",
                    password_again: "两次输入密码不一致",

                    orgs: "至少选择一个机构"
                },
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input


                invalidHandler: function(event, validator) { //display error alert on form submit
                    //	                $('.alert-danger', $('.login-form')).show();
                },
                highlight: function(element) { // hightlight error inputs
                    $(element)
                        .closest('.form-group').addClass('has-error'); // set error class to the control group
                },

                success: function(label) {
                    label.closest('.form-group').removeClass('has-error');
                    label.remove();
                },

                errorPlacement: function(error, element) {
                    if ($(element).attr("name") === "orgs") {
                        $(element).closest(".col-md-8").append(error)
                    } else {
                        error.insertAfter(element);
                    }
                },
                submitHandler: function() {
                    var savePath = ROOTPAth + '/admin/sys/adminAccount/save';
                    $.post(savePath, $UserForm.serialize(), function(data) {
                        if (data.data.id) {
                            $addModal.modal("hide");
                            $addRoletipModal.modal('show');
                            // $("#vUserModifyForm").find('input[type="hidden"]').remove();
                            // $addRoletipModal.find(".j-add-role").data({
                            //     usernema: $UserForm.find("input[name=username]").val(),
                            //     email: $UserForm.find("input[name=email]").val(),
                            //     mobile: $UserForm.find("input[name=mobile]").val(),
                            //     id: data.data.id
                            // });


                        }

                        pageIndex.reset();
                    });
                    $("#vUserForm").find("input[type='hidden']").eq(0).remove();
                    $("#vUserForm").find("input[type='hidden']").eq(1).remove();
                }
            });
            //我要编辑
            $ModifyForm.on("click", ".j-form-edit", function(event) {
                var formDom = event.delegateTarget;
                $(this).hide();
                $(formDom).find(".j-form-save").show();

                $(formDom).find(".j-disabled-item[disabled]").prop("disabled", false);

                //显示机构
                $(formDom).find(".search-input").show();
                // $(event.relatedTarget)
                var orgid = $ModifyForm.data('orgid');

                $.ajax({
                    url: orgsPath,
                    type: "POST",
                    datatype: "json",
                    data: {
                        length: 100
                    },
                    success: function(data) {

                        if (data.code === 1) {


                            $.each(data.data, function(index, value) {

                                if (value.id == orgid) {

                                    var newobj = $.extend({
                                        hasorgs: 1
                                    }, data.data[index]);
                                    data.data.splice(index, 1, newobj)
                                }
                            });

                            var newData = $.extend({}, data);

                            var cutArr = _.partition(data.data, function(val) {
                                return val.hasorgs === 1 ? true : false
                            });
                            var UnionArr = _.union(cutArr[0], cutArr[1]);
                            newData.data = UnionArr;
                            $(formDom).find(".checkbox").empty().append(orgsTPL.render(newData));
                        }
                    }
                });
            });
            $modifyModal.on('show.bs.modal', function(event) {
                var $modal = $(this);
                // $modal.find('input[name=email]').prop("disabled", true);
                // $modal.find('input[name=mobile]').prop("disabled", true);
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
                     */
                    /*username: function () {
                                         return $UserForm.find('input[name="userName"]').val();
                                         }*/
                    /*
                                         }
                                         }
                    //                      },*/
                    // email: "required",
                    // mobile: "required"
                },
                messages: {
                    /*username: {
                     required: "用户名不能为空",
                     remote: "用户名重复"
                     },*/
                    // email: "邮箱不能为空",
                    // mobile: "手机不能为空"
                },
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input


                invalidHandler: function(event, validator) { //display error alert on form submit
                    //	                $('.alert-danger', $('.login-form')).show();
                },
                highlight: function(element) { // hightlight error inputs
                    $(element)
                        .closest('.form-group').addClass('has-error'); // set error class to the control group
                },

                success: function(label) {
                    label.closest('.form-group').removeClass('has-error');
                    label.remove();
                },

                errorPlacement: function(error, element) {
                    error.insertAfter(element);
                },
                submitHandler: function() {

                    // var data_orgname = $modifyModal.find(".checkbox").find("radio[checked='checked']").parent("label").attr("data-orgname");
                    // var data_orgid = $modifyModal.find(".checkbox").find("radio[checked='checked']").parent("label").attr("data-orgid");

                    var updatePath = ROOTPAth + '/admin/sys/adminAccount/update';
                    $.post(updatePath, $ModifyForm.serialize(), function(data) {
                        $('#modifyModal').modal('hide');
                        // $("#vUserForm").find('input[type="hidden"]').remove();
                        $addRoletipModal.modal('show');
                        pageIndex.reset();
                        $("#vUserModifyForm").find("input[type='hidden']").eq(0).remove();
                        $("#vUserModifyForm").find("input[type='hidden']").eq(1).remove();
                    });

                }
            });

        },
        searchModifyOrgs: function($this) {
            var key = $this.val().toUpperCase();
            $modifyModal.find(".checkbox").find("label").each(function() {
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
        searchOrgs: function($this) {
            var key = $this.val().toUpperCase();
            $addModal.find(".checkbox").find("label").each(function() {
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

        deleteUser: function($that) {
            var $tr = $that.closest("tr");
            var accountid = $tr.data("accountid");
            var delPath = ROOTPAth + '/admin/sys/adminAccount/delete/' + accountid;
            $.ajax({
                url: delPath,
                type: "POST",
                success: function(data) {
                    pageIndex.reset();
                }
            });

        }
    };
    Utilitiy.init();
});