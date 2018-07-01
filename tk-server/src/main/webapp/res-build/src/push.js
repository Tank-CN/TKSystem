/**
 * Created by feiwen8772 on 15/5/12.
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
    require("res-build/res/plugin/form/jquery.form.js");
    require("res-build/res/plugin/jquery-validation-1.13.1/dist/jquery.validate.min.js");
    var $addForm = $("#form_edit");


    var tool = require("tool");
    //工具函数
    var hzuitl = {
        formatDate: function (timestamp, format) {
            return formatDate(timestamp, format);
        },
        byteLength: function (str) {
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
        var byteLen = 0, len = str.length;
        if (!str) return 0;
        for (var i = 0; i < len; i++)
            byteLen += str.charCodeAt(i) > 255 ? 2 : 1;
        return byteLen;
    }

    var Utilitiy = {
        init: function () {
            this.bind();
        },
        bind: function () {
            //验证表单
            $addForm.validate({
                rules: {
                    uid: {
                        required: true,
                        number:true
                    },
                    title: {
                        required: true
                    },
                    des: {
                        required: true
                    },
                    isAlert: {
                        required: true
                    }
                },
                messages: {
                    uid: {
                        required: "请填写用户ID",
                        number:"必须输入数字"
                    },
                    title: {
                        required: "请填写标题"
                    },
                    des: {
                        required: "请填写描述"
                    },
                    isAlert: {
                        required: "请选择消息类型"
                    }
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
                    if ($(element).attr("name") === "sdate" || $(element).attr("name") === "edate") {
                        $(element).closest(".col-md-4").append(error)
                    } else {
                        error.insertAfter(element);
                    }
                },
                submitHandler: function (fm) {

                    $(fm).ajaxSubmit({
                        type: "POST",
                        dataType: 'json',
                        url: ROOTPAth + "/admin/msg/pushuid",
                        beforeSubmit: function (formData, jqForm, options) {
                            var isSuccess = $addForm.validate().form();
                            tool.startPageLoading();
                            return isSuccess;
                        },
                        success: function (data) {
                            tool.stopPageLoading();
                            var $tipModal= $('#modal-box');
                            if (data.code===1) {
                                $tipModal.on('show.bs.modal', function (event) {
                                    $tipModal.find(".j-modal-closebtn").attr("href",ROOTPAth +"/admin/msg/push?pcode=RMsg&subcode=PushMsg")
                                });
                                $tipModal.modal("show");
                            }
                        },
                        error: function() {
                            tool.stopPageLoading();
                            $("#ajax_fail").modal("show")
                        }


                    });
                }
            });
        }
    };
    Utilitiy.init()
});
