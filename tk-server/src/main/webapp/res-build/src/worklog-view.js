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
    var $allmap = $("#allmap");
    //工具函数
    fixedOnScroll.scroll($addForm.find(".form-actions-fixtop"));
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


    var map;
    var Utilitiy = {
        init: function () {
            this.initForm();


            map = new BMap.Map("allmap", {minZoom: 4, maxZoom: 18});

        },
        initForm: function () {

            $.ajax({
                type: "POST",
                url: ROOTPAth + "/admin/sys/log/get/" + $("#worklogID").val(),
                dataType: "json",
                success: function (data) {
                    $addForm[0].reset();
                    $addForm.autofill(data);
                    var stimeTxt = new Date(data.servicetime).Format("yyyy-MM-dd hh:mm:ss");
                    $("#servicetime").val(stimeTxt);
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
                }
            });
        }
    };
    Utilitiy.init()
});
