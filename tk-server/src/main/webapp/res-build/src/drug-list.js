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
    var tool=require("tool");
    var pageIndex;
    var $table = $("#datatable_ajax");
    var $dynamicList = $("#dynamic-list");
    var pagelength = 10; //一页多少条；
    var $body = $('body');
    /*$body.popover({
            selector: '.has-popover'
        });*/
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


    var listTpl = juicer(
        [
            '{@if total === 0}',
            '<tr>',
            '<td colspan="2" style="text-align:center">',
            '暂无记录,请添加',
            '</td>',

            '</tr>',

            '{@else}',
            '{@each data as item,index}',

            '<tr role="row"',
            '{@if index%2==0}',
            ' class="odd" ' ,
            '{@else}',
            ' class="even" ' ,
            '{@/if}',
            'data-drid="${item.id}">',


            '    <td>${item.title}</td>',

            '    <td class="">${item.createdatetime}</td>',
            '    <td class="">',

            '        <a href="' + ROOTPAth + '/admin/drug/updateView?id=${item.id}&pcode=RMdc&subcode=MdcDrug" class="btn btn-default btn-xs j-edit" ><span class="iconfont iconfont-xs">&#xe62d;</span>查看</a> ',
            //删除
            ' <button type="button" class="btn btn-danger btn-xs j-del" data-toggle="confirmation" data-placement="left"><span   class="iconfont iconfont-xs">&#xe61d;</span>删除 </button>',

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
                    url: ROOTPAth + '/admin/drug/list',
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
                            $.each(newData.data, function (i, val) {
                                newData.data[i].createdatetime = newData.data[i].createdate?new Date(newData.data[i].createdate).Format("yyyy-MM-dd hh:mm:ss"):"";
                                if (newData.data[i].status === 1) {
                                    newData.data[i].statustxt = "正常"
                                }
                            });
                            //共多少条记录
                                                        $dynamicList.find(".page-info-num").text(res.total);
                            $table.find("tbody").empty().append(listTpl.render(newData));


                            $dynamicList.find(".j-del").confirmation({
                                title: "确定删除该动态吗？",
                                btnOkLabel: "确定",
                                btnCancelLabel: "取消",
                                onConfirm: function (event, element) {
                                    event.preventDefault();
                                    self.delitem($(element));
                                }/*,
                                 onCancel: function () {
                                 alert('cancel')
                                 }*/
                            });
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
            $dynamicList.on("change", ".j-length", function () {
                var $this = $(this);
                tool.startPageLoading();
                pagelength = $this.val();
                var index = $this.get(0).selectedIndex;
                $dynamicList.find(".j-length").not(this).get(0).selectedIndex = index;
                pageIndex.reset();
            });
        },
        delitem:function($that){
            var $tr = $that.closest("tr");
            var drugid = $tr.data("drid");
            var delPath = ROOTPAth + '/admin/drug/delete';
            tool.startPageLoading("数据删除中...");
            $.ajax({
                url: delPath,
                type: "POST",
                data:{
                    id:drugid
                },
                success: function (data) {

                    pageIndex.reset();

                }
            });
        }

    };
    Utilitiy.init();
});
