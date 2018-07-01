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
    var $addForm = $("#form_edit");
    var $etitor = $("#summernote");
    var $allmap = $("#allmap");
    var $selImg = $("#selImg");
    var $file = $("#file");
    var tool = require("tool");
    var editTag = false;
    // 对Date的扩展，将 Date 转化为指定格式的String
    // 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
    // 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
    // 例子：
    // (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
    // (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
    Date.prototype.Format = function (fmt) { //author: meizz
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "h+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    };
    /*调用：

     var time1 = new Date().Format("yyyy-MM-dd");
     var time2 = new Date().Format("yyyy-MM-dd HH:mm:ss");  */
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

    var map = new BMap.Map("allmap", {minZoom: 4, maxZoom: 20});
    var Utilitiy = {
        init: function () {
            this.bind();
            $.ajax({
                type: "POST",
                url: ROOTPAth + "/admin/business/activity/detail/"+activityID,
                dataType: "json",
                success: function (data) {

                    var stimeTxt = new Date(data.stime).Format("yyyy-MM-dd hh:mm:ss"),
                        etimeTxt = new Date(data.etime).Format("yyyy-MM-dd hh:mm:ss");
                    var newData = $.extend({}, data, {
                        //2015-05-22 04:05:00
                        sdate: stimeTxt.substr(0, 10),
                        sh: stimeTxt.substr(11, 2),
                        sm: stimeTxt.substr(14, 2),
                        edate: etimeTxt.substr(0, 10),
                        eh: etimeTxt.substr(11, 2),
                        em: etimeTxt.substr(14, 2)
                    });

                    $addForm[0].reset();
                    $addForm.autofill(newData);
                    $("#imgurl").attr("src", newData.imgurl).show();
                    // todo 已有图片设置
                    /*var pos = data.picurl.lastIndexOf("/");
                     $("#selImg span").html((data.picurl.substring(pos + 1).length > 15) ? data.picurl.substring(pos + 1).substring(0, 15) + "..." : data.picurl.substring(pos + 1));*/
                    //设置地图
                    map.centerAndZoom(new BMap.Point(data.longitude, data.latitude), 11);
                    //map.addControl(new BMap.ZoomControl());   //添加地图缩放控件
                    map.enableScrollWheelZoom(true);
                    var marker;
                    var point = new BMap.Point(data.longitude, data.latitude);
                    map.centerAndZoom(point, 20);
                    marker = new BMap.Marker(point);  // 创建标注
                    map.addOverlay(marker);               // 将标注添加到地图中
                    marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画


                    function showInfo(e) {
                        if (editTag) {
                            $addForm.find('input[name="longitude"]').val(e.point.lng);
                            $addForm.find('input[name="latitude"]').val(e.point.lat);
                            map.removeOverlay(marker);
                            var point = new BMap.Point(e.point.lng, e.point.lat);
                            map.centerAndZoom(point, 20);
                            marker = new BMap.Marker(point);  // 创建标注
                            map.addOverlay(marker);               // 将标注添加到地图中
                            marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
                        }
                    }

                    map.addEventListener("click", showInfo);

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
                $(formDom).find(".form-actions-top").hide();
                $(formDom).find(".form-actions").find(".j-edit").hide();
                $(formDom).find(".j-save").show();
                $(formDom).find("[disabled]").prop("disabled", false);
            });
            // Datepicker
            $('#sdate').datepicker({
                maxDate: '+1000d',
                minDate: '-100d',
                dateFormat: "yy-mm-dd",
                selectOtherMonths: true,
                yearRange: "-0:+3",
                changeMonth: true,
                changeYear: true,
                inline: true
            });
            $('#edate').datepicker({
                maxDate: '+1000d',
                minDate: '-100d',
                dateFormat: "yy-mm-dd",
                selectOtherMonths: true,
                yearRange: "-0:+3",
                changeMonth: true,
                changeYear: true,
                inline: true
            });

            //验证表单
            //验证表单
            $addForm.validate({
                rules: {
                    title: {
                        required: true
                    },
                    bid: {
                        required: true
                    },
                    bname: {
                        required: true
                    },
                    sdate: {
                        required: true
                    },
                    edate: {
                        required: true
                    },
                    address: {
                        required: true
                    },
                    content: {
                        required: true
                    },
                    pay: {
                        required: true
                    },
                    linkman: {
                        required: true
                    },
                    linkphone: {
                        required: true
                    }/*,
                     file: {
                     required: true
                     },
                     des: {
                     required: true
                     },


                     longitude: {
                     required: true
                     }, //经度
                     latitude: {
                     required: true
                     }    //维度*/
                },
                messages: {
                    title: {
                        required: "请填写活动标题"
                    },
                    bid: {
                        required: "请填写商家ID"
                    },
                    bname: {
                        required: "请填写商家名称"
                    },
                    sdate: {
                        required: "请填写活动时间"
                    },
                    edate: {
                        required: "请填写活动时间"
                    },
                    address: {
                        required: "请填写活动地址"
                    },
                    content: {
                        required: "请填写内容"
                    },
                    pay: {
                        required: "请填写费用"
                    },
                    linkman: {
                        required: "请填写联系人"
                    },
                    linkphone: {
                        required: "请填写联系人电话"
                    }/*,
                     file: {
                     required: "请上传图片"

                     },
                     des: {
                     required: "请填写概要"
                     },


                     longitude: {
                     required: "请填写经度"
                     }, //经度
                     latitude: {
                     required: "请填写维度"
                     }*/
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

                    if ($file.val().length && (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test($file.val()))) {
                        $("#modal-box-error").modal("show");
                        $file.focus();
                        return
                    }
                    $(fm).ajaxSubmit({

                        type: "POST",
                        dataType: 'html',
                        url: ROOTPAth + "/admin/business/activity/saveorupdate",
                        beforeSubmit: function (formData, jqForm, options) {
                            var isSuccess = $addForm.validate().form();
                            tool.startPageLoading();
                            return isSuccess;
                        },
                        success: function (data) {
                            tool.stopPageLoading();
                            var $tipModal = $('#modal-box');
                            var newdata = JSON.parse(data);
                            if (newdata.code === 1) {

                                $tipModal.on('show.bs.modal', function (event) {
                                    $tipModal.find(".j-modal-closebtn").attr("href", ROOTPAth +"/admin/business/activity/updateView?id=" + newdata.data + "&pcode=activity&subcode=activitylist")
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
