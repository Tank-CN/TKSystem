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
    var fixedOnScroll = require("res-build/src/fixedOnScroll.js");
    var $addForm = $("#form_edit");
    var $etitor = $("#summernote");
    var editTag = false;
    //选择图片
    var $selImg = $("#selImg");
    var $selImg2 = $("#selImg2");
    var $selImg3 = $("#selImg3");
    var $file = $("#file");
    var $file2 = $("#file2");
    var $file3 = $("#file3");
    //工具函数
    var hzuitl = {
        formatDate: function (timestamp, format) {
            return new Date(timestamp).Format(format);
        },
        byteLength: function (str) {
            return byteLength(str);
        }
    };
    fixedOnScroll.scroll($addForm.find(".form-actions-fixtop"));

 // 对Date的扩展，将 Date 转化为指定格式的String 
 // 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
 // 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
 // 例子： 
 // (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
 // (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
 Date.prototype.Format = function(fmt) 
 { //author: meizz 
   var o = { 
     "M+" : this.getMonth()+1,                 //月份 
     "d+" : this.getDate(),                    //日 
     "h+" : this.getHours(),                   //小时 
     "m+" : this.getMinutes(),                 //分 
     "s+" : this.getSeconds(),                 //秒 
     "q+" : Math.floor((this.getMonth()+3)/3), //季度 
     "S"  : this.getMilliseconds()             //毫秒 
   }; 
   if(/(y+)/.test(fmt)) 
     fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
   for(var k in o) 
     if(new RegExp("("+ k +")").test(fmt)) 
   fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length))); 
   return fmt; 
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
           // this.createTree();
            this.initForm();

        },
        initForm: function () {

            $.ajax({
                type: "POST",
                url: ROOTPAth + "/admin/doc/announcement/detail/" + announcementID,
                dataType: "json",
                success: function (data) {
                    //console.log(data);
//                    var createfile=data.createdon.slice(0,10);
                    var createfile = hzuitl.formatDate(data.createdon, 'yyyy-MM-dd');
                    var whref=window.location.href;
                    var whrefNum=whref.indexOf('admin/');
                    var ulrDefine=whref.slice(0,whrefNum);
                    $addForm[0].reset();
                    $addForm.autofill(data);
                    $("#summernote-con").html(data.content);
                    if(data.attachurl1){
                        $("#imgurl").attr("href",  ulrDefine+"/upload/doc/announcement/"+createfile+"/"+data.attachurl1).show();
                        $("#imgurl").text(data.attachurl1);
                    }
                    if(data.attachurl2){
                        $("#imgurl2").attr("href", ulrDefine+"/upload/doc/announcement/"+createfile+"/"+data.attachurl2).show();
                        $("#imgurl2").text(data.attachurl2);
                    }
                    if(data.attachurl3){
                        $("#imgurl3").attr("href",  ulrDefine+"/upload/doc/announcement/"+createfile+"/"+data.attachurl3).show();
                        $("#imgurl3").text(data.attachurl3);
                    }
                    
                    $etitor.code(data.content);
                }
            });
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
                $selImg.append('<span class="has-img">' + $file.val() + '</span>');
            });
            $file2.on("change", function () {

                $selImg2.find(".has-img").remove();
                $selImg2.append('<span class="has-img">' + $file2.val() + '</span>');
            });
            $file3.on("change", function () {

                $selImg3.find(".has-img").remove();
                $selImg3.append('<span class="has-img">' + $file3.val() + '</span>');
            });
            //我要编辑
           // $addForm.on("click", ".j-edit", function (event) {
                editTag = true;
               // var formDom = event.delegateTarget;
                /*$(formDom).find(".form-actions-top").hide();*/
                //$(this).hide();
               // $(formDom).find(".j-edit").hide();
               // $(formDom).find(".j-save").show();
               // $(formDom).find("[disabled]").prop("disabled", false);
               // $(formDom).find("[name=spell]").prop("disabled", true);
               //$(formDom).find("#summernote-con").remove();
                //编辑器
                $etitor.summernote({
                    height: 300,
                    /*lang: 'zh-CN',*/
                    onblur: function(e) {
                     $etitor.val($etitor.code())
                    }
                });
            //});
            //验证表单
            $addForm.validate({
                rules: {
                    title: {
                        required: true,
                        maxlength:15
                    },

                    publishusername:{required: true},
                },
                messages: {
                    title: {
                        required: "请填写标题",
                        maxlength:$.validator.format('15字以内')
                    },
                    publishusername: {required: "请填写发布人"}
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
                            console.log(data);
                            if (data) {
                                var $tipModal= $('#modal-box');
                                                            var newdata=JSON.parse(data);
                                                            if (newdata.code===1) {

                                                                $tipModal.on('show.bs.modal', function (event) {
                                                                    $tipModal.find(".j-modal-closebtn").attr("href",ROOTPAth +"/admin/doc/announcement/updateView?id="+newdata.data+"&currentpage=1&pcode=DmdRe&subcode=DocAnnouncement")
                                                                });
                                                                $tipModal.modal("show");
                                                            }


                            }
                        }


                    });
                }
            });
        },
        
    };
    Utilitiy.init()
});
