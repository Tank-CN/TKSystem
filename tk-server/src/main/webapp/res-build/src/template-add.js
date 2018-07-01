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
    require("res-build/res/module/insertcontent/insertcontent.js");
    require("res-build/res/plugin/jquery-validation-1.13.1/dist/jquery.validate.min.js");
    var $addForm = $("#form_edit");

    var Utilitiy = {
        init: function () {
            this.bind();


        },
        bind: function () {

            $addForm.on("click",".j-variable-btn", function (event) {
                var insertCon=$(this).text();
                   $("#text").insertContent(insertCon);
                   event.preventDefault();
            });

            //验证表单
            $addForm.validate({
                rules: {
                    name: {
                        required: true
                    },
                    text: {
                        required: true
                    },
                    code: {
                        required: true
                    }    //维度
                },
                messages: {
                    name: {
                        required: "请填写模板名称"
                    },
                    text: {
                        required: "请填写模板内容"
                    },
                    code: {
                        required: "请填写自定义代码"
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

                    $.ajax({
                        type: "POST",
                        dataType: "json",
                        url: ROOTPAth + "/admin/sys/template/save",
                         data:$addForm.serialize(),
                        success: function (data) {

                            if (data) {
                                $('#modal-box').modal("show");
                            }
                        }


                    });
                }
            });
        }
    };
    Utilitiy.init()
});
