/**
 * Created by feiwen8772 on 15/5/12.
 *
 *
 */
define(function(require, exports, module) {
    require("res-build/res/plugin/form/jquery.form.js");
    require("res-build/res/plugin/jquery-validation-1.13.1/dist/jquery.validate.min.js");
    var $addForm = $("#form_edit");
    var $etitor = $("#summernote");
    var tool = require("tool");

    //工具函数
    var hzuitl = {
        formatDate: function(timestamp, format) {
            return formatDate(timestamp, format);
        },
        byteLength: function(str) {
            return byteLength(str);
        }
    };

    function formatDate(timestamp, format) {
        var d = new Date(timestamp);
        var year = d.getFullYear();
        var month = d.getMonth() + 1;
        var date = d.getDate();
        var hour = d.getHours();
        var minute = d.getMinutes();
        var second = d.getSeconds();
        if (format)
            return year + "-" + month + "-" + date;
        else
            return year + "-" + month + "-" + date + "   " + hour + ":" + minute + ":" + second;
    }

    function byteLength(str) {
        var byteLen = 0,
            len = str.length;
        if (!str) return 0;
        for (var i = 0; i < len; i++)
            byteLen += str.charCodeAt(i) > 255 ? 2 : 1;
        return byteLen;
    }

    var Utilitiy = {
        init: function() {
            this.bind();


        },
        bind: function() {
            //选择图片

            //编辑器
            $etitor.summernote({
                height: 300
                    /*lang: 'zh-CN',*/
                    /*onblur: function(e) {
                     $etitor.val($etitor.code())
                     }*/
            });
            //

            // Datepicker

            //验证表单
            $addForm.validate({
                rules: {
                    title: {
                        required: true
                    },

                    kinds: {
                        required: true
                    },
                    info: {
                        required: true
                    },

                },
                messages: {
                    name: {
                        title: "请填写服务名称"
                    },

                    kinds: {
                        required: "请选择类型"
                    },
                    info: {
                        required: "请填写服务内容"
                    },
                    // code: {
                    //     required: "请填写工号",
                    //     remote: "工号重复"
                    // }
                },
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input


                invalidHandler: function(event, validator) { //display error alert on form submit
                    //                  $('.alert-danger', $('.login-form')).show();
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
                submitHandler: function(fm) {

                    // if ($file.val().length && (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test($file.val()))) {
                    //     $("#modal-box-error").modal("show");
                    //     $file.focus();
                    //     return
                    // }
                    console.log(doctorId);
                    $(fm).ajaxSubmit({
                        type: "POST",
                        dataType: "html",
                        url: ROOTPAth + "/admin/bussiness/serviceitem/saveorupdate",
                        data: {
                            docid: doctorId
                        },
                        beforeSubmit: function(formData, jqForm, options) {

                            var isSuccess = $addForm.validate().form();
                            /*if(isSuccess)
                             $('body').modalmanager('loading');*/
                            tool.startPageLoading();
                            return isSuccess;
                        },
                        success: function(data) {
                            tool.stopPageLoading();
                            console.log(data);
                            var $tipModal = $('#modal-box');
                            var newdata = JSON.parse(data);
                            if (newdata.code === 1) {

                                $tipModal.on('show.bs.modal', function(event) {
                                    var url = window.location.href;
                                    var tabindex = url.indexOf("/");
                                    var tabnum = url.substring(0, tabindex + 1);
                                    $tipModal.find(".btn-success").attr("href", tabnum + "admin/bas/doctor/updateView?id=" + doctorId + "&pcode=2&subcode=5&tabindex=1&scurrentpage=1&currentpage=" + currentpage)
                                        // $tipModal.find(".j-modal-closebtn").attr("href", tabnum + "admin/bussiness/serviceitem/updateViewByDoc?id=" + newdata.data + "&currentpage=1&pcode=2&subcode=5&docid=" + doctorId)
                                    $tipModal.find(".j-modal-closebtn").attr("href", tabnum + "admin/bussiness/serviceitem/updateViewByDoc?id=" + newdata.data + "&pcode=2&subcode=5&docid=" + doctorId + "&scurrentpage=1&currentpage=" + currentpage)

                                });
                                $tipModal.modal("show");
                            }
                        },
                        error: function() {
                            tool.stopPageLoading();
                            $("#ajax_fail").modal("show")
                        },



                    });
                }
            });

        },

    };
    Utilitiy.init()
});