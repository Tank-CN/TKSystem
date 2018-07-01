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
    require("res-build/res/plugin/jquery-formautofill/jquery.formautofill.min.js");
    require("res-build/res/plugin/jquery-validation-1.13.1/dist/jquery.validate.min.js");
    require("res-build/res/plugin/jstree/dist/jstree.min.js");
    var fixedOnScroll = require("res-build/src/fixedOnScroll.js");
    var $addForm = $("#form_edit");
    var $etitor = $("#summernote");
    var editTag = false;
    //选择图片
    var $selImg = $("#selImg");
    var $file = $("#file");
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
    fixedOnScroll.scroll($addForm.find(".form-actions-fixtop"));

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
        init: function () {
            tool.startPageLoading();
            this.bind();
            this.initForm();
        },
        initForm: function () {

            $.ajax({
                type: "POST",
                url: ROOTPAth + "/admin/business/news/detail/" + organizationID,
                dataType: "json",
                success: function (data) {
                    tool.stopPageLoading();
                    $addForm[0].reset();
                    $addForm.autofill(data);
                    $("#summernote-con").html(data.content);
                    if (data.imgurl) {
                        $("#imgurl").attr("src", data.imgurl).show();
                    }
                    $('#summernote').summernote('destroy');
                    $('#summernote').hide();
                    // $('#summernote').summernote("code",data.introduce);

                    // todo 已有图片设置
                    /*var pos = data.picurl.lastIndexOf("/");
                     $("#selImg span").html((data.picurl.substring(pos + 1).length > 15) ? data.picurl.substring(pos + 1).substring(0, 15) + "..." : data.picurl.substring(pos + 1));*/


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
                editTag = true;
                var formDom = event.delegateTarget;
                /*$(formDom).find(".form-actions-top").hide();*/
                //$(this).hide();
                $(formDom).find(".j-edit").hide();
                $(formDom).find(".j-save").show();
                $(formDom).find("[disabled]").prop("disabled", false);
                $(formDom).find("#summernote-con").remove();
                //编辑器
                // $('#summernote').summernote({
                //     height: 300,
                //     lang: 'zh-CN',
                //
                //     // keyup: function(e) {
                //     //   //$('#summernote').summernote('code').val("");
                //
                //     //   $('#summernote').summernote('code').html("");
                //     //  $('#summernote').summernote('code').val($('#summernote').summernote('code').code())
                //     // }
                // });

                $etitor.summernote({
                    height: 300,
                    callbacks: {
                        onImageUpload: function (files, editor, welEditable) {
                            for (var i = files.length - 1; i >= 0; i--) {
                                sendFile(files[i], this);
                            }
                        }
                    }
                });
                //create record for attachment
                function sendFile(file, el) {
                    data = new FormData();
                    data.append("file", file); // 表单名称

                    $.ajax({
                        type: "POST",
                        url: "/admin/business/fileupload",
                        data: data,
                        cache: false,
                        contentType: false,
                        processData: false,
                        dataType: 'json',
                        success: function (response) {
                            // 这里可能要根据你的服务端返回的上传结果做一些修改哦
                            $(el).summernote('editor.insertImage', response.url, response.filename);
                        },
                        error: function (error) {
                            alert('图片上传失败');
                        },
                        complete: function (response) {
                        }
                    });
                }
            });


            //验证表单
            $addForm.validate({
                rules: {
                    title: {
                        required: true
                    },
                    des: {
                        required: true
                    }
                },
                messages: {
                    title: {
                        required: "请填写广告名称"
                    },
                    des: {
                        required: "请填写广告描述"
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
                submitHandler: function (fm) {
                    if ($file.val().length && (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test($file.val()))) {
                        $("#modal-box-error").modal("show");
                        $file.focus();
                        return
                    }
                    $(fm).ajaxSubmit({
                        type: "POST",
                        dataType: "html",
                        url: ROOTPAth + "/admin/business/news/saveorupdate",
                        data: {
                            id: organizationID
                        },
                        beforeSubmit: function (formData, jqForm, options) {
                            if (hzuitl.byteLength($('#summernote').summernote('code')) / (1024 * 1024) > 4) {
                                alert("文本内容总数不得超过4M 请减小图片大小或者精简文字");
                                return false;
                            }
                            var isSuccess = $addForm.validate().form();
                            var markupStr = $('#summernote').summernote("code");

                            $('#summernote').summernote('code', markupStr);
                            /*if(isSuccess)
                             $('body').modalmanager('loading');*/
                            tool.startPageLoading();
                            return isSuccess;
                        },
                        success: function (data) {
                            tool.stopPageLoading();
                            if (data) {
                                var $tipModal = $('#modal-box');
                                var newdata = JSON.parse(data);
                                if (newdata.code === 1) {

                                    $tipModal.on('show.bs.modal', function (event) {
                                        $tipModal.find(".j-modal-closebtn").attr("href", ROOTPAth + "/admin/business/news/updateView?id=" + organizationID + "&pcode=AD&subcode=ADItem&currentpage=" + currentpage)
                                    });
                                    $tipModal.modal("show");
                                }


                            }
                        },
                        error: function () {
                            tool.stopPageLoading();
                            $("#ajax_fail").modal("show")
                        },


                    });
                }
            });
        }
    };
    Utilitiy.init()
});