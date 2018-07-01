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
    require("res-build/res/plugin/bs-confirmation/bootstrap-confirmation.js");
    var tool = require("tool");
    var pageIndex;
    var $table = $("#datatable_ajax");
    var $worklogList = $("#worklog-list");
    var pagelength = 10; //一页多少条；
    var $searchForm = $("#search-form");
    var $body = $('body');
    
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
            '<td colspan="6" style="text-align:center">',
            '暂无记录,请添加',
            '</td>',

            '</tr>',

            '{@else}',
            '{@each data as item,index}',
            '{@if index%2==0}',
            '<tr role="row" class="odd" data-organizationid="${item.id}">',
            '{@else}',
            '<tr role="row" class="even" data-organizationid="${item.id}">',
            '{@/if}',
            '    <td>${item.id}</td>',
            '    <td>${item.servicetimetxt}</td>',
            '    <td>{@if item.servicecontent==1}健康随访{@/if}{@if item.servicecontent==2}健康档案新建{@/if}{@if item.servicecontent==3}健康档案编辑{@/if}{@if item.servicecontent==4}中医体质{@/if}</td>',
            '    <td>${item.serviceobject}</td>',
            '    <td>${item.gridaddress}</td>',
            '    <td>{@if item.longitude==null}获取失败{@/if}{@if item.longitude!=null}${item.longitude}{@/if}</td>',
            '    <td>{@if item.latitude==null}获取失败{@/if}{@if item.latitude!=null}${item.latitude}{@/if}</td>',
            '    <td class="">',
            '        <a href="' + ROOTPAth + '/admin/sys/log/view?id=${item.id}&currentpage=${currentpage}&pcode=VisitNode&subcode=index" class="btn btn-default btn-xs j-edit" ><span class="iconfont iconfont-xs">&#xe62d;</span>查看</a> ',
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
                    url: ROOTPAth + '/admin/sys/log/list',
                    type: 'POST',
                    dataType: 'json',
                    data: function () {
                        var servicecontent = $searchForm.find("input[name=servicecontent]").val();
                        var data = {
                            length: pagelength,
                            servicecontent: servicecontent
                        };

                        return data;
                    },
                    success: function (res) {
                        tool.stopPageLoading();
                        if (res.code === 1) {
                            var newData = $.extend({}, res);
                            $.each(newData.data, function (i, val) {
                                newData.data[i].servicetimetxt  = new Date(newData.data[i].servicetime).Format("yyyy-MM-dd hh:mm:ss");
                            });
                            //共多少条记录
                            $worklogList.find(".page-info-num").text(res.total);
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
            //pageIndex.resetgoto($("#currentpage").val());
            pageIndex.reset();
        },
        bind: function () {
            
            var self = this;
            //修改每页显示条数
            $worklogList.on("change", ".j-length", function () {
                var $this = $(this);
                pagelength = $this.val();
                var index = $this.get(0).selectedIndex;
                $worklogList.find(".j-length").not(this).get(0).selectedIndex = index;
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
        }

    };
    Utilitiy.init();
});