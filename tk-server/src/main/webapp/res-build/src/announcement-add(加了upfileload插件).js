/**
 * Created by maojf on 15/1/22.
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
    var $etitor = $("#summernote");
    var $selImg = $("#selImg");
    var $file = $("#file");
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
            //选择图片
            // $selImg.on("click", function () {
            //     $file.click();
            // });

            // $file.on("change", function () {

            //     $selImg.find(".has-img").remove();
            //     $selImg.append('<span class="has-img">' + $file.val() + '</span>');
            // });
            //编辑器
            $etitor.summernote({
                height: 300
               
            });
           
            //验证表单
            $addForm.validate({
                rules: {
                    title: {
                        required: true,
                        maxlength:15,
                    },
                    publisher:{required: true},

                  
                },
                messages: {
                    title: {
                        required: "请填写标题",
                        maxlength:jQuery.validator.format('15字以内'),
                    },
                    publisher: {required: "请填写发布人"},

                 
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
                submitHandler: function (fm) {
                    if ($file.val().length>4 && (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG|xlsx|docx)$/.test($file.val()))) {
                            $("#modal-box-error").modal("show");
                            $file.focus();
                            return
                    }
                    $(fm).ajaxSubmit({
                        type: "POST",
                        dataType: "html",
                        url: ROOTPAth + "/admin/doc/announcement/saveorupdate",//新增提交
                        beforeSubmit: function (formData, jqForm, options) {
                            if (hzuitl.byteLength($etitor.code()) / (1024 * 1024) > 4) {
                                alert("文本内容总数不得超过4M 请减小图片大小或者精简文字");
                                return false;
                            }
                            var isSuccess = $addForm.validate().form();
                            /*if(isSuccess)
                             $('body').modalmanager('loading');*/

                            return isSuccess;
                        },
                        success: function (data) {
                            var $tipModal= $('#modal-box');
                            var newdata=JSON.parse(data);
                            if (newdata.code===1) {

                                $tipModal.on('show.bs.modal', function (event) {
                                    $tipModal.find(".j-modal-closebtn").attr("href",ROOTPAth +"/admin/doc/announcement/updateView?id="+newdata.data+"&currentpage=1&pcode=DmdRe&subcode=DocAnnouncement")
                                });
                                $tipModal.modal("show");
                            }
                        }


                    });
                }
            });

        },
       
    };
    Utilitiy.init()
});
