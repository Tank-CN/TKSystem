define(function (require, exports, module) {
    require("res-build/res/plugin/jquery-validation-1.13.1/dist/jquery.validate.min.js");
    require("res-build/res/plugin/bs-confirmation/bootstrap-confirmation.js");
    var juicer = require("juicer");
    var $authMaintain = $("#auth-maintain");
    var $form = $('#form_create');
    var $table = $("#moduleResTable");
    var roleId = "";
    /*$('#modal-box').modal({});*/
    $('body').tooltip({
        selector: '.has-tooltip'
    });
    var Utilitiy = {
        init: function () {
            this.bind();
            this.getModule();
        },
        bind: function () {
            $form.validate({
                rules: {
                    code: "required",
                    title: "required",
                    /*url:"required",*/
                    intro: "required"
                },
                messages: {
                    code: "角色信息不能为空",
                    title: "角色编号不能为空",
                    intro: "角色介绍不能为空"
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
                    var createObjPath = ROOTPAth + '/admin/sys/role/create';
                    $.post(createObjPath, $form.serialize(), function (data) {
                        roleId = data;
                        $('#authH').show();
                        $authMaintain.show();
                        $('#fromSubmitBtn').hide();
                        /*$form[0].reset()*/
                    });
                }
            });

        },
        //获取模块列表
        getModule: function () {
            var self=this;
            var listTpl = juicer(
                [
                    '{@if total === 0}',
                    '<tr>',
                    '<td colspan="3" style="text-align:center">',
                    '暂无记录,请添加',
                    '</td>',

                    '</tr>',
                    '{@else}',
                    '{@each data as item,index}',

                    '<tr role="row" class="code-${item.code}"  data-modulid="${item.id}" data-flag="${item.flag}">',

                    '    <td>${item.title}</td>',
                    '    <td>${item.code}</td>',
                    '    <td>',
                    //添加权限
                                        '    <button type="button" class="btn btn-default btn-xs j-add" data-toggle="confirmation" data-placement="top"',
                                        '{@if item.flag === 0}',
                                        '',
                                        '{@else}',
                                        'style="display:none"',
                                        '{@/if}',
                                        '><span  class="iconfont iconfont-xs">&#xe61f;</span>添加权限 </button>',
                                        //删除权限
                                        '    <button type="button" class="btn btn-danger btn-xs j-del" data-toggle="confirmation" data-placement="top"',
                                        '{@if item.flag === 0}',
                                        'style="display:none"',
                                        '{@else}',
                                        '',
                                        '{@/if}',
                                        '><span  class="iconfont iconfont-xs">&#xe61d;</span>删除权限 </button>',

                    '        </td>',
                    '</tr>',
                    '{@each item.list as subitem,index}',

                    '<tr role="row"  data-modulid="${subitem.id}">',

                    '    <td class="sub-mod-nmae">${subitem.title}</td>',
                    '    <td>${subitem.code}</td>',
                    '    <td>' ,
                    //添加权限
                                        '    <button type="button"   data-pcode="${subitem.pcode}" class="btn btn-default btn-xs j-add" data-toggle="confirmation" data-placement="top"',
                                        '{@if subitem.flag === 0}',
                                        '',
                                        '{@else}',
                                        'style="display:none"',
                                        '{@/if}',
                                        '><span  class="iconfont iconfont-xs">&#xe61f;</span>添加权限 </button>',
                                        //删除权限
                                        '    <button type="button"   data-pcode="${subitem.pcode}" class="btn btn-danger btn-xs j-del" data-toggle="confirmation" data-placement="top"',
                                        '{@if subitem.flag === 0}',
                                        'style="display:none"',
                                        '{@else}',
                                        '',
                                        '{@/if}',
                                        '><span  class="iconfont iconfont-xs">&#xe61d;</span>删除权限 </button>',
                    '        </td>',
                    '</tr>',
                    '{@/each}',
                    '{@/each}',
                    '{@/if}'].join(""));

            var modulesPath = ROOTPAth + '/admin/sys/module/all';
            $.post(modulesPath, function (data) {
                $table.find("tbody").empty().append(listTpl.render(data));
                //添加权限
                                $table.find(".j-add").confirmation({
                                    title: "确定添加该角色权限吗？",
                                    btnOkLabel: "确定",
                                    btnCancelLabel: "取消",
                                    onConfirm: function (event, element) {
                                        event.preventDefault();
                                        self.addRole($(element));
                                    }/*,
                                     onCancel: function () {
                                     alert('cancel')
                                     }*/
                                });
                                //删除权限
                                $table.find(".j-del").confirmation({
                                    title: "确定删除该角色权限吗？",
                                    btnOkLabel: "确定",
                                    btnCancelLabel: "取消",
                                    onConfirm: function (event, element) {
                                        event.preventDefault();
                                        self.delRole($(element));
                                    }/*,
                                     onCancel: function () {
                                     alert('cancel')
                                     }*/
                                });

            });
        },
                //添加权限
                addRole: function ($that) {
                    //var $this = $(this);
                    var pcode = $that.data("pcode");
                    if (pcode) {
                        if ($table.find(".code-" + pcode).data("flag") == 0) {
                            $('#modal-box').modal("show");
                            return;
                        }
                    }
                    var $tr = $that.closest("tr");
                    var modulid = $tr.data("modulid");
                    var createObjPath = ROOTPAth + '/admin/sys/roleModule/create';
                    $.post(createObjPath, {mid: modulid, rid: roleId}, function (data) {

                        if (data === true) {
                            $that.hide();
                            $tr.data("flag", 1);
                            $tr.find(".j-del").show();
                        }
                    });
                },
                //删除权限
                delRole: function ($that) {
                    //var $this = $(this);
                    var pcode = $that.data("pcode");
                    var $tr = $that.closest("tr");
                    var modulid = $tr.data("modulid");
                    var deleteObjPath = ROOTPAth + '/admin/sys/roleModule/deleteByR2M/' + roleId + "/" + modulid;
                    $.ajax({
                        url: deleteObjPath,
                        type: "POST",
                        success: function (data) {
                            /* $('span[name="remove' + moduleId + '"]').hide();
                             $('span[name="add' + moduleId + '"]').show();
                             initOperateSpan();*/
                            $that.hide();
                            //$tr.data("flag",1);
                            $tr.find(".j-add").show();
                        }
                    });
                }
    };
    /*$('#modal-box').modal("show");
     $('#addModal').modal("show");*/
    Utilitiy.init();

});


