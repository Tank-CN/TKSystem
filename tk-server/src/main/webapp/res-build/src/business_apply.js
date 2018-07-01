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
    var $hospitalList = $("#hospital-list");
    var pagelength = 10; //一页多少条；
    var $searchForm = $("#search-form");
    var $body = $('body');
    //选择图片
    var $selImg = $("#selImg");
    var $file = $("#file");

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
            '<tr role="row" class="odd" data-organizationid="${item.id}" data-flag="${item.flag}">',
            '{@else}',
            '<tr role="row" class="even" data-organizationid="${item.id}" data-flag="${item.flag}">',
            '{@/if}',
            '    <td>${item.title}</td>',
            '    <td>${item.typename}</td>',
            '    <td>${item.telephone}</td>',
            '    <td>${item.createdate}</td>',
            '    <td>${item.flagstr}</td>',
            '    <td>${item.uid}</td>',
            '    <td>${item.address}</td>',
            '    <td>${item.info}</td>',
            '    <td class="">',

            // '        <a href="' + ROOTPAth + '/admin/business/business/updateView?id=${item.id}&currentpage=${item.currentpage}&pcode=business&subcode=businesslist" class="btn btn-default btn-xs j-edit" ><span class="iconfont iconfont-xs">&#xe62d;</span>查看</a> ',
            '{@if item.flag==0}',
            ' <button type="button" class="btn btn-danger btn-xs j-del" data-toggle="confirmation" data-placement="left"><span   class="iconfont iconfont-xs"></span>1.更改为审核中</button>',
            '{@else if item.flag==1}',
            ' <button type="button" class="btn btn-danger btn-xs j-del" data-toggle="confirmation" data-placement="left"><span   class="iconfont iconfont-xs"></span>2.更改为处理完</button>',
            '{@else if item.flag==2}',
            ' <button type="button" class="btn btn-default btn-xs j-edit" data-toggle="confirmation" data-placement="left"><span   class="iconfont iconfont-xs"></span>3.已处理结束</button>',
            '{@/if}',

            '    </td>',
            '</tr>',
            '{@/each}',
            '{@/if}'
        ].join(""));
    var Utilitiy = {
        init: function () {
            var self = this;
            this.bind();
            tool.startPageLoading();

            pageIndex = new Page({
                ajax: {
                    url: ROOTPAth + '/admin/business/business/applylist',
                    type: 'POST',
                    dataType: 'json',
                    data: function () {
                        var title = $searchForm.find("input[name=title]").val();
                        var phone = $searchForm.find("input[name=phone]").val();
                        var flag = $searchForm.find("select[name=flag]").val();
                        var data = {
                            length: pagelength,
                            phone: phone,
                            flag: flag,
                            title: title
                        };
                        return data;
                    },
                    success: function (res) {
                        console.log(res);
                        tool.stopPageLoading();
                        if (res.code === 1) {
                            var newData = $.extend({}, res);
                            if (res.total > 0) {
                                $.each(newData.data, function (i, val) {
                                    newData.data[i].currentpage = pageIndex.current;
                                    if (newData.data[i].flag == 0) {
                                        newData.data[i].flagstr="申请中";
                                    } else if (newData.data[i].flag == 1) {
                                        newData.data[i].flagstr="审核中";
                                    } else if (newData.data[i].flag == 2) {
                                        newData.data[i].flagstr="已处理";
                                    }
                                    newData.data[i].createdate=new Date(newData.data[i].createdate).Format("yyyy-MM-dd hh:mm:ss");
                                });
                            }
                            //共多少条记录
                            $hospitalList.find(".page-info-num").text(res.total);
                            $table.find("tbody").empty().append(listTpl.render(newData));

                            $hospitalList.find(".j-del").confirmation({
                                title: "确定要更改状态吗？",
                                btnOkLabel: "确定",
                                btnCancelLabel: "取消",
                                onConfirm: function (event, element) {
                                    event.preventDefault();
                                    self.delitem($(element));
                                }
                            });
                        }

                    },
                    error: function () {
                        tool.stopPageLoading();
                        $("#ajax_fail").modal("show")
                    },
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
            var windowurl = window.location.href;
            var returnUrl = windowurl.indexOf("currentpage=");

            if (returnUrl == -1 || returnUrl == "") {
                pageIndex.resetgoto(1);
            } else {
                var returnUrl_val = parseInt(windowurl.substring(returnUrl + 12));
                if (returnUrl_val != 1) {
                    pageIndex.resetgoto(returnUrl_val);
                } else {
                    pageIndex.reset()
                }
            }

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

            var self = this;
            //修改每页显示条数
            $hospitalList.on("change", ".j-length", function () {
                var $this = $(this);
                pagelength = $this.val();
                var index = $this.get(0).selectedIndex;
                $hospitalList.find(".j-length").not(this).get(0).selectedIndex = index;
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
            var organizationid = $tr.data("organizationid");
            var flag = $tr.data("flag");
            // var delPath = ROOTPAth + '/admin/business/applychange/' + organizationid+'/'+flag;
            var delPath = ROOTPAth + '/admin/business/business/applychange';
            $.ajax({
                url: delPath,
                data:{
                    id:organizationid,
                    flag:flag
                },
                type: "POST",
                success: function (data) {
                    pageIndex.resetgoto(pageIndex.current)
                },
                error: function () {
                    tool.stopPageLoading();
                    $("#ajax_fail").modal("show")
                }
            });
        }

    };
    Utilitiy.init();
});