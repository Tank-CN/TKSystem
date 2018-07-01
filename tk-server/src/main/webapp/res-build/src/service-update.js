/**
 * Created by feiwen8772 on 15/5/12.
 *    
 *
 */
define(function(require, exports, module) {
    require("res-build/res/plugin/form/jquery.form.js");
    require("res-build/res/plugin/jquery-formautofill/jquery.formautofill.min.js");
    require("res-build/res/plugin/jquery-validation-1.13.1/dist/jquery.validate.min.js");

    // var fixedOnScroll = require("res-build/src/fixedOnScroll.js");
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
            tool.startPageLoading();
            this.bind();

            this.initForm();

        },
        initForm: function() {

            $.ajax({
                type: "POST",
                url: ROOTPAth + "/admin/bussiness/serviceitem/detail/",
                dataType: "json",
                data: {
                    id: serviceId
                },
                success: function(data) {
                    tool.stopPageLoading();
                    console.log(data);
                    $addForm[0].reset();
                    $addForm.autofill(data);
                    $("#summernote-con").html(data.info);

                    $('#summernote').summernote('destroy');
                    $('#summernote').hide();
                    // $('#summernote').summernote("code",data.introduce);



                }
            });
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
            //我要编辑
            $addForm.on("click", ".j-edit", function(event) {
                editTag = true;

                var formDom = event.delegateTarget;
                /*$(formDom).find(".form-actions-top").hide();*/
                //$(this).hide();
                $(formDom).find(".j-edit").hide();
                $(formDom).find(".j-save").show();
                $(formDom).find("[disabled]").prop("disabled", false);
                $(formDom).find("[name=spell]").prop("disabled", true);
                $(formDom).find("#summernote-con").remove();
                //编辑器
                $('#summernote').summernote({
                    height: 300,
                    lang: 'zh-CN',

                    // keyup: function(e) {
                    //   //$('#summernote').summernote('code').val("");

                    //   $('#summernote').summernote('code').html("");
                    //  $('#summernote').summernote('code').val($('#summernote').summernote('code').code())
                    // }
                });
                // $addForm[0].find(".form-body").find("input").eq(0).focusin();
                // fixedOnScroll.scroll($addForm.find(".form-actions-fixtop"));

            });
            //

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

                    $(fm).ajaxSubmit({
                        type: "POST",
                        dataType: "html",
                        url: ROOTPAth + "/admin/bussiness/serviceitem/saveorupdate",
                        data: {
                            id: serviceId
                        },
                        beforeSubmit: function(formData, jqForm, options) {

                            // if (hzuitl.byteLength($etitor.summernote("code")) / (1024 * 1024) > 4) {
                            //     alert("文本内容总数不得超过4M 请减小图片大小或者精简文字");
                            //     return false;
                            // }
                            var isSuccess = $addForm.validate().form();
                            /*if(isSuccess)
                             $('body').modalmanager('loading');*/
                            var markupStr = $('#summernote').summernote("code");
                            tool.startPageLoading();
                            $('#summernote').summernote('code', markupStr);
                            return isSuccess;
                        },
                        success: function(data) {
                            tool.stopPageLoading();
                            console.log(data);
                            var $tipModal = $('#modal-box');
                            var newdata = JSON.parse(data);
                            if (newdata.code === 1) {

                                $tipModal.on('show.bs.modal', function(event) {
                                    $tipModal.find(".j-modal-closebtn").attr("href", ROOTPAth + "/admin/bussiness/serviceitem/updateView?id=" + serviceId + "&pcode=2&subcode=6&scurrentpage=" + scurrentpage)
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