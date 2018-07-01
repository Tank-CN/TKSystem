define(function (require, exports, module) {
    require("res-build/res/plugin/form/jquery.form.js");
    require("res-build/res/plugin/jquery-validation-1.13.1/dist/jquery.validate.min.js");
    var $addForm = $("#form_edit");
    var $etitor = $("#summernote");
    var $selImg = $("#selImg");
    var $selImg2 = $("#selImg2");
    var $selImg3 = $("#selImg3");
    var $file = $("#file");
    var $file2 = $("#file2");
    var $file3 = $("#file3");
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
            $selImg.on("click", function () {
                $file.click();
            });
             $selImg2.on("click", function () {
                $file2.click();
            });
              $selImg3.on("click", function () {
                $file3.click();
            });

            $file.on("change", function () {

                $selImg.find(".has-img").remove();
                $selImg.append('<span class="has-img">' + $file.val() + '<button type="button" class="btn btn-danger delfj btn-sm">删除附件</button></span>');
            });
            $file2.on("change", function () {

                $selImg2.find(".has-img").remove();
                $selImg2.append('<span class="has-img">' + $file2.val() + '<button type="button" class="btn btn-danger delfj btn-sm">删除附件</button></span>');
               // $selImg2.append('<button type="button" class="btn btn-danger delfj btn-sm">删除附件</button>');
            });
            $file3.on("change", function () {

                $selImg3.find(".has-img").remove();
                $selImg3.append('<span class="has-img">' + $file3.val() + '<button type="button" class="btn btn-danger delfj btn-sm">删除附件</button></span>');
               // $selImg3.append('<button type="button" class="btn btn-danger delfj btn-sm">删除附件</button>');
            });
           $selImg.on("click",".delfj",function () {
                $(this).closest('span').empty().prev("input").removeAttr('aria-invalid').removeClass('valid').val("");
            })
            $selImg2.on("click",".delfj",function () {
                $(this).closest('span').empty().prev("input").removeAttr('aria-invalid').removeClass('valid').val("");
            })
            $selImg3.on("click",".delfj",function () {
                $(this).closest('span').empty().prev("input").removeAttr('aria-invalid').removeClass('valid').val("");
            })
            //编辑器
            $etitor.summernote({
                height: 300
                /*lang: 'zh-CN',*/
                /*onblur: function(e) {
                 $etitor.val($etitor.code())
                 }*/
            });
            //
            //验证表单
            $addForm.validate({
                rules: {
                    title: {
                        required: true,
                        maxlength:30
                    },
                    // summernote: {
                    //     required: true,
                    //     maxlength:2000
                    // },
                   publishusername:{required: true}
                },
                messages: {
                    title: {
                        required: '请填写标题',
                        maxlength:$.validator.format('30字以内')
                    },
                    // summernote: {
                    //     required: '请填写标题',
                    //     maxlength:$.validator.format('2000字以内')
                    // },
                    publishusername: {required: '请填写发布人'}
                },
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input


                invalidHandler: function (event, validator) { //display error alert on form submit
                    //                  $('.alert-danger', $('.login-form')).show();
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
                    if ($file.val().length && (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG|xlsx|docx)$/.test($file.val()))) {
                            $("#modal-box-error").modal("show");
                            $file.focus();
                            return
                    }
                    if ($file2.val().length && (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG|xlsx|docx)$/.test($file2.val()))) {
                            $("#modal-box-error").modal("show");
                            $file2.focus();
                            return
                    }
                    if ($file3.val().length && (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG|xlsx|docx)$/.test($file3.val()))) {
                            $("#modal-box-error").modal("show");
                             $file3.focus();
                            return
                    }
                    $(fm).ajaxSubmit({
                        type: "POST",
                        dataType: "html",
                        url: ROOTPAth + "/admin/doc/announcement/saveorupdate",
                        beforeSubmit: function (formData, jqForm, options) {
                            
                            if (hzuitl.byteLength($etitor.code()) / (1024 * 1024) > 4) {
                                alert("文本内容总数不得超过4M 请减小图片大小或者精简文字");
                                return false;
                            }
                            if (hzuitl.byteLength($etitor.code())> 2000) {
                                alert("公告正文内容2000字以内");
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
                           // console.log(newdata);
                            if (newdata.code==1) {

                                $tipModal.on('show.bs.modal', function (event) {
                                    $tipModal.find(".j-modal-closebtn").attr("href",ROOTPAth +"/admin/doc/announcement/updateView?id="+newdata.data+"&currentpage=1&pcode=DmdRe&subcode=DocAnnouncement")
                                });
                                $tipModal.modal("show");
                            }
                        },
                        error: function(e) { 
                        	alert(e); 
                        }
                    });
                }
            });

        }
    };
    Utilitiy.init()
});
