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
    var selectArea = require("res-build/src/select_area.js");
    var selectAreas = new selectArea("Region");
    var $updateRegionModal = $("#updateRegionModal");
    var $updateRegionForm = $("#updateRegionForm");
    var $regionCon = $("#regionCon");
    var $counselCon = $("#counsel-con");
    var $updateCounselModal = $("#updateCounselModal");
    var $updateCounselForm = $("#updateCounselForm");
    var provinceid = $regionCon.find(".j-province").data("id"), cityid = $regionCon.find(".j-city").data("id"), countyid = $regionCon.find(".j-county").data("id");
    var Utilitiy = {
        init: function () {
            this.bind();
            //初始化日期
            //this.initRegion();

        },
        bind: function () {
            var self=this;
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
            //更新项目所属地区表单验证
            $updateRegionForm.validate({
                rules: {
                    provinceid: "required",
                    cityid: "required",
                    countyid: "required"
                },
                messages: {
                    provinceid: "请选择省/自治区/直辖市",
                    cityid: "请选择地级市/自治区/区",
                    countyid: "请选择县级市/县/区"
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

                    var postPath = ROOTPAth + '/admin/sys/setting/updateregion';
                    var _ajax = {
                        type: "POST",
                        dataType: "json",
                        url: postPath,
                        data: $(fm).serialize(),
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
