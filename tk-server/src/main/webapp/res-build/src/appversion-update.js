/**
 * Created by feiwen8772 on 15/6/15.
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
    require("res-build/res/plugin/jquery-formautofill/jquery.formautofill.min.js");
    var tool = require("tool");
    require("res-build/res/plugin/jquery-validation-1.13.1/dist/jquery.validate.min.js");
    var $addForm = $("#companyForm");
    var $selImg = $("#selImg");
    var $file = $("#file");
    var Utilitiy = {
        init: function () {
            this.bind();
            $.ajax({
                type: "POST",
                url: ROOTPAth + '/admin/sys/appversion/detail/' + $addForm.find('input[name="id"]').val(),
                data: {},
                dataType: "json",
                success: function (data) {
                    $addForm.autofill(data);


                }
            });
        },
        bind: function () {
            //选择图片
            $selImg.on("click", function () {
                $file.click();
            });

            $file.on("change", function () {


                var filePos = $file.val().lastIndexOf(".");
                var ext = $file.val().substring(filePos + 1, $file.val().length);
                // alert();
                if (ext.toLowerCase() != "apk") {
                    alert("不支持该文件上传!");
                    $file.val(null);
                    return false;
                }
                $selImg.find(".has-img").remove();
                $selImg.append('<span class="has-img">' + $file.val() + '</span>');

            });

            $addForm.validate({
                rules: {
                    appversion: {
                        required: true,
                        digits: true,
                        min: 1
                    },
                    des: {
                        required: true
                    }
                },
                messages: {
                    appversion: {
                        required: 'APP版本不能为空',
                        digits: "APP版本必须为大于等于1的整数",
                        min: 1
                    },
                    des: {
                        required: "APP更新说明不能为空"
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
                    /*if ($file.val().length && (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test($file.val()))) {
                     $("#modal-box-error").modal("show");
                     $file.focus();
                     return
                     }*/
                    tool.startPageLoading("上传中...");
                    $addForm.ajaxSubmit({
                        type: "POST",
                        dataType: "html",
                        url: ROOTPAth + "/admin/sys/appversion/update",

                        success: function (data) {
                            if (data) {
                                $('#modal-box').modal("show");

                            }
                            tool.stopPageLoading();
                        }
                    });
                }
            });
        }
    };
    Utilitiy.init();
});
