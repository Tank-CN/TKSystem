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
    var Page = require("page");
    var juicer = require("juicer");
    //require("res-build/res/plugin/bs-confirmation/bootstrap-confirmation.js");
    var tool = require("tool");
    var pageIndex;
    var $table = $("#datatable_ajax");
    var $templateList = $("#template-list");
    var pagelength = 10; //一页多少条；
    var $body = $('body');
    var $searchForm = $("#search-form");


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

            '<tr role="row"',
            '{@if index%2==0}',
            ' class="odd" ',
            '{@else}',
            ' class="even" ',
            '{@/if}',
            'data-id="${item.id}">',


            '    <td>${item.name}</td>',
            '    <td>${item.text}</td>',
            '    <td>${item.typetext}</td>',
            '    <td>${item.code}</td>',
            '    <td class="">',

            '        <a href="' + ROOTPAth + '/admin/sys/template/updateView?id=${item.id}&pcode=Sys&subcode=Template" class="btn btn-default btn-xs j-edit" ><span class="iconfont iconfont-xs">&#xe62d;</span>查看</a> ',


            '    </td>',
            '</tr>',
            '{@/each}',
            '{@/if}'].join(""));
    var Utilitiy = {
        init: function () {
            var self = this;
            this.bind();
            tool.startPageLoading();

            pageIndex = new Page({
                ajax: {
                    url: ROOTPAth + '/admin/sys/template/list',
                    type: 'POST',
                    dataType: 'json',
                    data: function () {
                        var name = $searchForm.find("input[name=name]").val();
                        var type = $searchForm.find("input[name=type]:checked").val();
                        var data = {
                            length: pagelength,
                            type: type,
                            name: name
                        };

                        return data;

                        /*  var data = {
                         length: pagelength
                         };

                         return data;*/
                    },
                    success: function (res) {
                        tool.stopPageLoading();

                        if (res.code === 1) {
                            var newData = $.extend({}, res);
                            $.each(newData.data, function (i, val) {
                                newData.data[i].typetext = val.type === 1 ? "消息" : "短信";
                                /*if (newData.data[i].status === 1) {
                                 newData.data[i].statustxt = "正常"
                                 }*/
                            });
                            //共多少条记录
                            $templateList.find(".page-info-num").text(res.total);
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
        },
        bind: function () {
            var self = this;
            //修改每页显示条数
            $templateList.on("change", ".j-length", function () {
                var $this = $(this);
                pagelength = $this.val();
                var index = $this.get(0).selectedIndex;
                $templateList.find(".j-length").not(this).get(0).selectedIndex = index;
                pageIndex.reset();
            });
            $searchForm.on("submit", function (e) {
                e.preventDefault();
                pageIndex.reset();
            });
            $searchForm.on("click", ".j-showall", function (e) {
                e.preventDefault();
                $searchForm[0].reset();
                pageIndex.reset();
            });
        },
        delitem: function ($that) {
            var $tr = $that.closest("tr");
            var id = $tr.data("id");
            var delPath = ROOTPAth + '/admin/activity/delete            ';
            $.ajax({
                url: delPath,
                type: "POST",
                data: {
                    id: id
                },
                success: function (data) {

                    pageIndex.reset();

                }
            });
        }

    };
    Utilitiy.init();
});
