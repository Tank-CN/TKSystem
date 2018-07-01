/**
 * Created by feiwen8772 on 15/6/11.
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
    var Page = require("page");
    var juicer = require("juicer");
    var tool = require("tool");
    var pageIndex;
    var $table = $("#datatable_ajax");
    var $roleList = $("#role-list");
    var pagelength = 10; //一页多少条；
    var $body = $('body');
    $body.tooltip({
        selector: '.has-tooltip'
    });
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
    var listTpl = juicer(
        [
            '{@if total === 0}',
            '<tr>',
            '<td colspan="5" style="text-align:center">',
            '暂无记录,请添加',
            '</td>',

            '</tr>',

            '{@else}',
            '{@each data as item,index}',

            '<tr role="row" ',
            '{@if index%2==0}',
            'class="odd"',
            '{@else}',
            'class="even"',
            '{@/if}',
            ' data-roleid="${item.id}">',

            '    <td>${item.username}</td>',
            '    <td>${item.realname}</td>',
            '    <td class="">${item.sexcodetxt}</td>',
            '    <td class="">${item.idcard}</td>',
            '    <td class="">${item.birthdatetime}</td>',
            '</tr>',
            '{@/each}',
            '{@/if}'].join(""));

    var Utilitiy = {
        init: function () {
            var self = this;
            tool.startPageLoading();
            pageIndex = new Page({
                ajax: {
                    url: ROOTPAth + '/admin/sys/user/list',
                    type: 'POST',
                    dataType: 'json',
                    data: function () {
                        var data = {
                            length: pagelength
                        };

                        return data;
                    },
                    success: function (res) {
                        tool.stopPageLoading();
                        if (res.code === 1) {
                            var newData = $.extend({}, res);
                            $.each(res.data, function (i, val) {
                                if (val.sexcode === 1) {
                                    newData.data[i].sexcodetxt = '男';
                                } else if (val.sexcode === 2) {
                                    newData.data[i].sexcodetxt = '女';
                                } else {
                                    newData.data[i].sexcodetxt = '未知';
                                }
                                if (newData.data[i].birthdate) {
                                    newData.data[i].birthdatetime = newData.data[i].birthdate ? new Date(newData.data[i].birthdate).Format("yyyy-MM-dd") : "";
                                } else {
                                    newData.data[i].birthdatetime = "";
                                }
                            });
                            $roleList.find(".page-info-num").text(res.total);
                            $table.find("tbody").empty().append(listTpl.render(newData));

                        }

                    }
                },
                pageName: "page",
                /*tpl: {
                 go: true //隐藏跳转到第几页
                 },*/
                getTotalPage: function (res) {
                    //返回总页数
                    return Math.ceil(res.total / pagelength);
                },
                pageWrapper: '.table-page'
            });
            pageIndex.reset();
        }

    };
    Utilitiy.init()

});