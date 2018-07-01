/**
 
 *
 */
define(function (require, exports, module) {
    var Page = require("page");
    var juicer = require("juicer");
    require("res-build/res/plugin/bs-confirmation/bootstrap-confirmation.js");
    var tool = require("tool");
    require("res-build/res/plugin/jquery-formautofill/jquery.formautofill.min.js");

    var $table = $("#datatable_ajax");
    var $conditionList = $("#condition-list");
    var $body = $('body');
    var $addAppModalCondition = $("#addAppModalCondition");
    var $addConditionForm = $("#addConditionForm");
    var $modifyModalCondition = $("#modifyModalCondition"), $modifyConditionForm = $("#modifyConditionForm");
    var listTpl = juicer(
        [
            '{@if total === 0}',
            '<tr>',
            '<td colspan="4" style="text-align:center">',
            '暂无记录,请添加',
            '</td>',

            '</tr>',

            '{@else}',
            '{@each data as item,index}',

            '<tr role="row" ',
            '{@if index%2==0}',
            'class="odd" ',
            '{@else}',
            'class="even" ',
            '{@/if}',
            'data-id="${item.id}" data-appmoduleid="${item.appmoduleid}" data-appmodulename="${item.appmodulename}" data-fieldname="${item.fieldname}" data-conditions="${item.conditions}" data-flag="${item.flag}">',
            '    <td>${item.appmodulename}</td>',
            '    <td>${item.fieldnamestr} / ${item.conditionsstr}</td>',
            '    <td>${item.flagstr}</td>',
            '    <td class="">',
            '       <button type="button" class="btn btn-default btn-xs j-edit" ><span class="iconfont iconfont-xs">&#xe62d;</span>查看</button> ',
            //删除
            ' <button type="button" class="btn btn-danger btn-xs j-del" data-toggle="confirmation" data-placement="left"><span   class="iconfont iconfont-xs">&#xe61d;</span>删除 </button>',

            '    </td>',
            '</tr>',
            '{@/each}',
            '{@/if}'].join(""));
    var Utilitiy = {
        init: function () {
            var self = this;
            this.bind();
            tool.startPageLoading();
            this.getList();

        },
        bind: function () {
            var self = this;
            //新增
            $addConditionForm.on("submit", function (event) {
                event.preventDefault();
                self.addCondition(this);
            });
            //新增
            $modifyConditionForm.on("submit", function (event) {
                event.preventDefault();
                self.modifyCondition(this);
            });
            //我要编辑
            $modifyModalCondition.on("click", ".j-form-edit", function (event) {
                var formDom = event.delegateTarget;
                $(this).hide();
                $(formDom).find(".j-form-save").show();
                $(formDom).find("[disabled]").prop("disabled", false);

            });
            $modifyModalCondition.on('show.bs.modal', function (event) {
                var $modal = $(this);
                $modal.find('select[name=fieldname]').prop("disabled", true);
                $modal.find('select[name=conditions]').prop("disabled", true);
                $modal.find('select[name=flag]').prop("disabled", true);
                $modal.find(".j-form-save").hide();
                $modal.find(".j-form-edit").show();
            });
            //修改
            $table.on("click", ".j-edit", function (event) {
                event.preventDefault();

                var $this = $(this);
                var $tr = $this.closest("tr");
                var conditionObj = $tr.data();
                $modifyConditionForm.autofill(conditionObj);
                $modifyModalCondition.modal("show")
            })
        },
        getList: function () {
            var self = this;
            var _ajax = {
                url: ROOTPAth + '/admin/sys/condition/listbymid',
                type: 'POST',
                dataType: 'json',
                data: {
                    mid: conditionId
                },
                success: function (res) {
                    tool.stopPageLoading();

                    var newData = {
                        data: res,
                        total: res.length
                    };

                    function fieldName(d) {
                        var fieldnamestr = "";
                        switch (d) {
                            case 1:
                                fieldnamestr = "身份证";
                                break;
                            case 2:
                                fieldnamestr = "姓名";
                                break;
                            case 3:
                                fieldnamestr = "就诊卡";
                                break;
                            default:
                                x = "";
                        }
                        return fieldnamestr;
                    }

                    $.each(newData.data, function (i, val) {
                        /*'    <td>${item.appmodulename}</td>',
                         */
                        newData.data[i].fieldnamestr = fieldName(newData.data[i].fieldname);
                        newData.data[i].conditionsstr = "不为空";
                        newData.data[i].flagstr = newData.data[i].flag === 1 ? "启用" : "禁用";
                    });
                    $table.find("tbody").empty().append(listTpl.render(newData));
                    $conditionList.find(".page-info-num").text(newData.data.length);
                    $table.find(".j-del").confirmation({
                        title: "确定删除该条件吗？",
                        btnOkLabel: "确定",
                        btnCancelLabel: "取消",
                        onConfirm: function (event, element) {
                            event.preventDefault();
                            self.delCondition($(element));
                        }
                    });
                }
            };
            $.ajax(_ajax)
        }, /*删除条件*/
        delCondition: function ($this) {
            var self = this;
            var $tr = $this.closest("tr");
            var conditionId = $tr.data("id");
            var deleteObjPath = ROOTPAth + '/admin/sys/condition/delete';
            $.ajax({
                url: deleteObjPath,
                type: "POST",
                data: {
                    id: conditionId
                },
                success: function (data) {
                    self.getList();
                }
            });
        },
        addCondition: function (fm) {
            var self = this;
            var $fm = $(fm);
            var getPath = ROOTPAth + '/admin/sys/condition/save';
            $.ajax({
                url: getPath,
                type: "POST",
                datatype: "json",
                data: $fm.serialize(),
                success: function (data) {
                    self.getList();
                    $addAppModalCondition.modal("hide");
                }
            });
        },
        modifyCondition: function (fm) {
            var self = this;
            var $fm = $(fm);
            var getPath = ROOTPAth + '/admin/sys/condition/update';
            $.ajax({
                url: getPath,
                type: "POST",
                datatype: "json",
                data: $fm.serialize(),
                success: function (data) {
                    self.getList();
                    $modifyModalCondition.modal("hide");
                }
            });
        }
    };
    Utilitiy.init()
});