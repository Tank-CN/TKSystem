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

    require("res-build/res/plugin//autoTextarea/jquery.autoTextarea.js");
    require("res-build/res/plugin/jquery-formautofill/jquery.formautofill.min.js");
    require("res-build/res/plugin/jquery-validation-1.13.1/dist/jquery.validate.min.js");
    require("res-build/res/plugin/jstree/dist/jstree.min.js");
    var $addForm = $("#form_edit");
    var $selImg = $("#selImg");
    var $file = $("#file");
    $("textarea").autoTextarea({
                    maxHeight: 520,
                    resizeWindon: true
                });
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
            $.ajax({
                type: "POST",
                url: ROOTPAth + "/admin/dynamic/detail",
                data: {id: drid},
                dataType: "json",
                success: function (data) {
                    var newData = $.extend({}, data);
                    $addForm[0].reset();
                    $addForm.autofill(data);
                    if (newData.imgurl) {
                        $("#imgurl").attr("src", newData.imgurl).show();
                    }

                }
            });

        },
        bind: function () {
            //选择图片
            $selImg.on("click", function () {
                $file.click();
            });

            $file.on("change", function () {

                $selImg.find(".has-img").remove();
                $selImg.append('<span class="has-img">' + $file.val() + '</span>');
            });
            //我要编辑
            $addForm.on("click", ".j-edit", function (event) {
                var formDom = event.delegateTarget;
                $(formDom).find(".form-actions-top").hide();
                $(formDom).find(".form-actions").find(".j-edit").hide();
                $(formDom).find(".j-save").show();
                $(formDom).find("[disabled]").prop("disabled", false);
            });

            //提交表单
            $.validator.addMethod('orgdept', function (value, element) {
                return $("#deptid").val();
            });
            $addForm.validate({
                rules: {
                    title: {
                        required: true
                    },
                    content: {
                        required: true
                    }
                },
                messages: {
                    title: {
                        required: '请输入标题'
                    },
                    content: {
                        required: '请输入内容'
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
                    error.insertAfter(element);
                },
                submitHandler: function () {
                    if ($file.val().length && (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test($file.val()))) {
                        $("#modal-box-error").modal("show");
                        $file.focus();
                        return
                    }
                    $addForm.ajaxSubmit({
                        type: "POST",
                        dataType: "html",
                        url: ROOTPAth + "/admin/dynamic/saveorupdate",

                        success: function (data) {
                            var $tipModal= $('#modal-box');
                                                                                    var newdata=JSON.parse(data);
                                                                                    if (newdata.code===1) {

                                                                                        $tipModal.on('show.bs.modal', function (event) {
                                                                                            $tipModal.find(".j-modal-closebtn").attr("href",ROOTPAth +"/admin/dynamic/updateView?drid="+newdata.data+"&pcode=DmdRe&subcode=DmdRecord")
                                                                                        });
                                                                                        $tipModal.modal("show");
                                                                                    }
                        }


                    });
                }
            });

        }
    };
    Utilitiy.init()
});
