/**
 * Created by feiwen8772 on 15/5/20.
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
    require("res-build/res/plugin/jquery-validation-1.13.1/dist/jquery.validate.min.js");
    require("res-build/res/plugin/jquery-formautofill/jquery.formautofill.min.js");
    var selectArea = require("res-build/src/select_area.js");
    var selectAreas = new selectArea("Region");
    var $updateRegionModal = $("#updateRegionModal");
    var $updateRegionForm = $("#updateRegionForm");
    var $regionCon = $("#regionCon");
    var $counselCon = $("#counsel-con");
    var $updateCounselModal = $("#updateCounselModal");
    var $updateCounselForm = $("#updateCounselForm");
    var $alipayConModal = $("#alipayConModal");//支付宝模态框
    var $alipaycon = $("#alipaycon");//支付宝
    var $alipayRsaPrivateModal = $("#alipayRsaPrivateModal");
    var $appIndexconModal = $("#appIndexconModal");//首页模块模态框
    var $appindexcon = $("#appindexcon");
    var $appMyConModal = $("#appMyConModal"); //我的模块模态框
    var $appmycon = $("#appmycon");
    var $appAPIconModal = $("#appAPIconModal");//外网API模态框
    var $appapicon = $("#appapicon");
    var provinceid = $regionCon.find(".j-province").data("id"), cityid = $regionCon.find(".j-city").data("id"), countyid = $regionCon.find(".j-county").data("id");
    var Utilitiy = {
        init: function () {
            this.bind();
            //初始化日期
            //this.initRegion();

        },
        bind: function () {
            var self = this;
            $updateRegionModal.on('show.bs.modal', function (event) {
                self.initRegion();
            });
            //显示项目所属地区表单验证
            $regionCon.on("click", ".j-modifyModule-btn", function () {

                $updateRegionModal.modal("show");
            });
            var $counselItem;
            // 分诊
            $counselCon.on("click", ".j-edit", function () {
                var $this = $(this);
                $counselItem = $this;
                var $tr = $this.closest("tr");
                $updateCounselForm.find("input[name=id]").val($tr.data("id"));
                $updateCounselForm.find("input[name=text]").val($tr.data("val"));
                $updateCounselModal.find(".j-counsel-title").text($tr.data("title"));
                $updateCounselModal.modal("show");
            });
            //外网API模态框
            $appAPIconModal.on('show.bs.modal', function (event) {
                var $form = $appAPIconModal.find("form");
                $form[0].reset();
                var infoObj = {
                    apiurl_text: $.trim($appapicon.find(".j-apiurl").text())//我的档案

                };
                $form.autofill(infoObj);
            });

            $appAPIconModal.find("form").validate({
                rules: {
                    apiurl_text: {
                        required: true
                    }
                },
                messages: {
                    apiurl_text: {
                        required: "请填写外网API地址"
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

                    var postPath = ROOTPAth + '/admin/sys/config/update/api';
                    var _ajax = {
                        type: "POST",
                        dataType: "json",
                        url: postPath,
                        data: $(fm).serialize(),
                        success: function (data) {
                            if (data.code === 1) {
                                $appapicon.find(".j-apiurl").text( data.data.apiurl_text);

                            }
                            $appAPIconModal.modal("hide");
                        }
                    };
                    $.ajax(_ajax);


                }
            });

            //我的模块模态框
            $appMyConModal.on('show.bs.modal', function (event) {
                var $form = $appMyConModal.find("form");
                $form[0].reset();
                var infoObj = {
                    myrecord_open_text: $appmycon.find(".j-myrecord-open").data("val"),//我的档案
                    family_open_text: $appmycon.find(".j-family-open").data("val"),//家庭管理
                    serverrecord_open_text: $appmycon.find(".j-serverrecord-open").data("val"),//服务记录
                    sign_open_text: $appmycon.find(".j-sign-open").data("val"),//签约信息
                    monitor_open_text: $appmycon.find(".j-monitor-open").data("val")//健康监测
                };
                $form.autofill(infoObj);
            });

            $appMyConModal.find("form").on("submit", function (event) {
                event.preventDefault();
                var postPath = ROOTPAth + '/admin/sys/config/update/mine';
                var _ajax = {
                    type: "POST",
                    dataType: "json",
                    url: postPath,
                    data: $(this).serialize(),
                    success: function (data) {
                        if (data.code === 1) {
                            $appmycon.find(".j-myrecord-open").data("val", data.data.myrecord_open_text);
                            if (data.data.myrecord_open_text == "1") {
                                $appmycon.find(".j-myrecord-open").text("开启")
                            } else {
                                $appmycon.find(".j-myrecord-open").text("关闭")
                            }
                            $appmycon.find(".j-family-open").data("val", data.data.family_open_text);
                            if (data.data.family_open_text == "1") {
                                $appmycon.find(".j-family-open").text("开启")
                            } else {
                                $appmycon.find(".j-family-open").text("关闭")
                            }
                            $appmycon.find(".j-serverrecord-open").data("val", data.data.serverrecord_open_text);
                            if (data.data.serverrecord_open_text == "1") {
                                $appmycon.find(".j-serverrecord-open").text("开启")
                            } else {
                                $appmycon.find(".j-serverrecord-open").text("关闭")
                            }

                            $appmycon.find(".j-sign-open").data("val", data.data.sign_open_text);
                            if (data.data.sign_open_text == "1") {
                                $appmycon.find(".j-sign-open").text("开启")
                            } else {
                                $appmycon.find(".j-sign-open").text("关闭")
                            }

                            $appmycon.find(".j-monitor-open").data("val", data.data.monitor_open_text);
                            if (data.data.monitor_open_text == "1") {
                                $appmycon.find(".j-monitor-open").text("开启")
                            } else {
                                $appmycon.find(".j-monitor-open").text("关闭")
                            }

                        }
                        $appMyConModal.modal("hide");
                    }
                };
                $.ajax(_ajax);

            });
            //首页模块模态框
            $appIndexconModal.on('show.bs.modal', function (event) {
                var $form = $appIndexconModal.find("form");
                $form[0].reset();
                var infoObj = {
                    pubappoint_open_text: $appindexcon.find(".j-pubappoint-open").data("val"),//预约挂号
                    implementappoint_open_text: $appindexcon.find(".j-implementappoint-open").data("val"),//计免预约
                    childrenappoint_open_text: $appindexcon.find(".j-childrenappoint-open").data("val"),//儿保预约
                    reception_open_text: $appindexcon.find(".j-reception-open").data("val"),//分诊咨询
                    report_open_text: $appindexcon.find(".j-report-open").data("val"),//报告查询
                    seek_open_text: $appindexcon.find(".j-seek-open").data("val")//便捷寻医
                };
                $form.autofill(infoObj);
            });
            $appIndexconModal.find("form").on("submit", function (event) {
                event.preventDefault();
                var postPath = ROOTPAth + '/admin/sys/config/update/index';
                var _ajax = {
                    type: "POST",
                    dataType: "json",
                    url: postPath,
                    data: $(this).serialize(),
                    success: function (data) {
                        if (data.code === 1) {
                            $appindexcon.find(".j-pubappoint-open").data("val", data.data.pubappoint_open_text);
                            if (data.data.pubappoint_open_text == "1") {
                                $appindexcon.find(".j-pubappoint-open").text("开启")
                            } else {
                                $appindexcon.find(".j-pubappoint-open").text("关闭")
                            }
                            $appindexcon.find(".j-implementappoint-open").data("val", data.data.implementappoint_open_text);
                            if (data.data.implementappoint_open_text == "1") {
                                $appindexcon.find(".j-implementappoint-open").text("开启")
                            } else {
                                $appindexcon.find(".j-implementappoint-open").text("关闭")
                            }
                            $appindexcon.find(".j-childrenappoint-open").data("val", data.data.childrenappoint_open_text);
                            if (data.data.childrenappoint_open_text == "1") {
                                $appindexcon.find(".j-childrenappoint-open").text("开启")
                            } else {
                                $appindexcon.find(".j-childrenappoint-open").text("关闭")
                            }
                            $appindexcon.find(".j-reception-open").data("val", data.data.reception_open_text);
                            if (data.data.reception_open_text == "1") {
                                $appindexcon.find(".j-reception-open").text("开启")
                            } else {
                                $appindexcon.find(".j-reception-open").text("关闭")
                            }
                            $appindexcon.find(".j-report-open").data("val", data.data.report_open_text);
                            if (data.data.report_open_text == "1") {
                                $appindexcon.find(".j-report-open").text("开启")
                            } else {
                                $appindexcon.find(".j-report-open").text("关闭")
                            }
                            $appindexcon.find(".j-seek-open").data("val", data.data.seek_open_text);
                            if (data.data.seek_open_text == "1") {
                                $appindexcon.find(".j-seek-open").text("开启")
                            } else {
                                $appindexcon.find(".j-seek-open").text("关闭")
                            }

                        }
                        $appIndexconModal.modal("hide");
                    }
                };
                $.ajax(_ajax);

            });
            //支付宝功能
            $alipayRsaPrivateModal.on('show.bs.modal', function (event) {
                var button = $(event.relatedTarget);
                $alipayRsaPrivateModal.find(".portlet-body").text(button.data("val"))
            });
            $alipayConModal.on('show.bs.modal', function (event) {
                var $form = $alipayConModal.find("form");
                $form[0].reset();
                var infoObj = {
                    alipay_open_text: $alipaycon.find(".j-alipay-open").data("val"),
                    alipay_partner_text: $.trim($alipaycon.find(".j-alipay-partner").text()),
                    alipay_seller_text: $.trim($alipaycon.find(".j-alipay-seller").text()),
                    alipay_rsa_private_text: $alipaycon.find(".j-edit").data("val")
                };
                var $disabled = $alipayConModal.find(".j-disabled-hook");
                if ($alipaycon.find(".j-alipay-open").data("val") === 0) {
                    $disabled.prop("disabled", true)
                } else {
                    $disabled.prop("disabled", false)
                }
                $form.autofill(infoObj);


            });
            $alipayConModal.on("change", "input[name=alipay_open_text]", function () {
                var $disabled = $alipayConModal.find(".j-disabled-hook");
                if ($('input[name="alipay_open_text"]:checked').val() == "1") {
                    $disabled.prop("disabled", false)
                } else {
                    $disabled.prop("disabled", true)
                }
            });
            /*alipay_partner_text: $.trim($alipaycon.find(".j-alipay-partner").text()),
             alipay_seller_text: $.trim($alipaycon.find(".j-alipay-seller").text()),
             alipay_rsa_private_text: $alipaycon.find(".j-edit").data("val")*/
            //提交表单
            $.validator.addMethod('requiredval', function (value, element) {
                if ($alipayConModal.find('input[name="alipay_open_text"]:checked').val() == "1") {
                    return !!($(element).val());
                } else {
                    return true;
                }

            });
            $alipayConModal.find("form").validate({
                rules: {
                    alipay_partner_text: {
                        requiredval: true
                    },
                    alipay_seller_text: {
                        requiredval: true
                    },
                    alipay_rsa_private_text: {
                        requiredval: true
                    }
                },
                messages: {
                    alipay_partner_text: {
                        requiredval: "请填写支付宝用户名"
                    },
                    alipay_seller_text: {
                        requiredval: "请填写支付宝帐号"
                    },
                    alipay_rsa_private_text: {
                        requiredval: "请填写支付宝密钥"
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

                    var postPath = ROOTPAth + '/admin/sys/config/update/alipay';
                    var _ajax = {
                        type: "POST",
                        dataType: "json",
                        url: postPath,
                        data: $(fm).serialize(),
                        success: function (data) {
                            if (data.code === 1) {
                                $alipaycon.find(".j-alipay-open").data("val", data.data.alipay_open_text);
                                if (data.data.alipay_open_text === "1") {

                                    $alipaycon.find(".j-alipay-open").text("开启");

                                } else {
                                    $alipaycon.find(".j-alipay-open").text("关闭");
                                }
                                if (data.data.alipay_partner_text) {
                                    $alipaycon.find(".j-alipay-partner").text(data.data.alipay_partner_text)
                                }
                                if (data.data.alipay_seller_text) {
                                    $alipaycon.find(".j-alipay-seller").text(data.data.alipay_seller_text)
                                }
                                if (data.data.alipay_rsa_private_text) {
                                    $alipaycon.find(".j-edit").data("val", data.data.alipay_rsa_private_text)
                                }
                            }
                            $alipayConModal.modal("hide");
                        }
                    };
                    $.ajax(_ajax);


                }
            });
            //更新项目所属地区表单验证
            $updateRegionForm.validate({
                rules: {
                    provinceid: "required"
                },
                messages: {
                    provinceid: "请选择省/自治区/直辖市"
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
                    var cityid=$updateRegionForm.find("[name=cityid]").val(),
                        countyid=$updateRegionForm.find("[name=countyid]").val();
                    var postPath = ROOTPAth + '/admin/sys/setting/updateregion';
                    var _ajax = {
                        type: "POST",
                        dataType: "json",
                        url: postPath,
                        data:{
                            provinceid:$updateRegionForm.find("[name=provinceid]").val(),
                            provincetext:$updateRegionForm.find("[name=provincetext]").val(),
                            cityid:cityid,
                            citytext:cityid===""?"":$updateRegionForm.find("[name=citytext]").val(),
                            countyid:countyid,
                            countytext:countyid===""?"":$updateRegionForm.find("[name=countytext]").val()
                        },//$(fm).serialize()
                        success: function (data) {
                            $updateRegionModal.modal("hide");
                            $regionCon.find(".j-province").text($("#selProvinceTxt-Region").val());
                            $regionCon.find(".j-city").text($("#selCityTxt-Region").val());
                            $regionCon.find(".j-county").text($("#selAreaTxt-Region").val())
                        }
                    };
                    $.ajax(_ajax);


                }
            });
            //更新分诊表单验证
            $updateCounselForm.validate({
                rules: {
                    text: "required"
                },
                messages: {
                    text: "请填写信息"
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

                    var postPath = ROOTPAth + '/admin/sys/setting/update';
                    var _ajax = {
                        type: "POST",
                        dataType: "json",
                        url: postPath,
                        data: $(fm).serialize(),
                        success: function (data) {
                            $updateCounselModal.modal("hide");
                            var $tr = $counselItem.closest("tr");
                            var val = $(fm).find("input[name=text]").val();
                            $tr.data("val", val);
                            $tr.find(".j-counsel-txt").text(val)

                        }
                    };
                    $.ajax(_ajax);


                }
            });

        },
        //
        initRegion: function () {
            selectAreas.initProvince(provinceid, cityid, countyid, true);

        }
    };
    Utilitiy.init();
});
