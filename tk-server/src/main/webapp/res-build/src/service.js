/**
 * Created by feiwen8772 on 15/5/12.
 *
 */
define(function(require, exports, module) {
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
            '<tr role="row" class="odd" data-serviceid="${item.id}">',
            '{@else}',
            '<tr role="row" class="even" data-serviceid="${item.id}">',
            '{@/if}',
            '    <td>${item.id}</td>',
            '    <td>${item.title}</td>',
            '    <td>${item.kinds}</td>',
            '    <td class="">',

            '        <a href="' + ROOTPAth + '/admin/bussiness/serviceitem/updateView?id=${item.id}&scurrentpage=${item.scurrentpage}&pcode=2&subcode=6" class="btn btn-default btn-xs j-edit" ><span class="iconfont iconfont-xs">&#xe62d;</span>查看</a> ',
            //删除
            ' <button type="button" class="btn btn-danger btn-xs j-del" data-toggle="confirmation" data-placement="left"><span   class="iconfont iconfont-xs">&#xe61d;</span>删除 </button>',

            '    </td>',
            '</tr>',
            '{@/each}',
            '{@/if}'
        ].join(""));
    var Utilitiy = {
        init: function() {
            var self = this;
            this.bind();
            tool.startPageLoading();
            console.log(page);
            pageIndex = new Page({
                ajax: {
                    url: ROOTPAth + '/admin/bussiness/serviceitem/list',
                    type: 'POST',
                    dataType: 'json',
                    data: function() {
                        var name = $searchForm.find("input[name=name]").val();
                        var regionname = $searchForm.find("input[name=regionname]").val();
                        var data = {
                            length: pagelength,
                            regionname: regionname,
                            name: name
                        };
                        return data;
                    },
                    success: function(res) {
                        tool.stopPageLoading();
                        if (res.code === 1) {
                            var newData = $.extend({}, res);
                            $.each(newData.data, function(i, val) {

                                newData.data[i].scurrentpage = pageIndex.current;
                                newData.data[i].sourcestext = val.sources === 1 ? "机构添加" : "系统添加";
                                /*if (newData.data[i].status === 1) {
                                 newData.data[i].statustxt = "正常"
                                 }*/
                                if (newData.data[i].kinds == 1) {
                                    newData.data[i].kinds = "公有"
                                }
                                if (newData.data[i].kinds == 2) {
                                    newData.data[i].kinds = "私有"
                                }
                            });
                            //共多少条记录
                            $hospitalList.find(".page-info-num").text(res.total);
                            $table.find("tbody").empty().append(listTpl.render(newData));

                            $hospitalList.find(".j-del").confirmation({
                                title: "确定删除吗？",
                                btnOkLabel: "确定",
                                btnCancelLabel: "取消",
                                onConfirm: function(event, element) {
                                        event.preventDefault();
                                        self.delitem($(element));
                                    }
                                    /*,
                                                                     onCancel: function () {
                                                                     alert('cancel')
                                                                     }*/
                            });
                        }

                    },
                    error: function(error) {
                        console.log(error);
                    },
                },
                pageName: "page",
                /*tpl: {
                 go: true //隐藏跳转到第几页
                 },*/
                getTotalPage: function(res) {
                    //返回总页数
                    return Math.ceil(res.total / pagelength);
                },
                pageWrapper: '.table-page'
            });
            var windowurl = window.location.href;
            var returnUrl = windowurl.indexOf("scurrentpage=");
            if (returnUrl == -1 || returnUrl == "") {
                pageIndex.resetgoto(1);
            } else {
                var returnUrl_val = parseInt(windowurl.substring(returnUrl + 13));
                if (returnUrl_val != 1) {
                    pageIndex.resetgoto(returnUrl_val);
                } else {
                    pageIndex.reset()
                }
            }

        },
        bind: function() {
            //选择图片
            $selImg.on("click", function() {
                $file.click();
            });

            $file.on("change", function() {

                $selImg.find(".has-img").remove();
                $selImg.append('<span class="has-img">' + $file.val() + '</span>');
            });

            $("#download").on("click", function() {
                var url = "/admin/sys/file/download/orgs";
                window.open(url);
            });

            var self = this;
            //修改每页显示条数
            $hospitalList.on("change", ".j-length", function() {
                var $this = $(this);
                pagelength = $this.val();
                var index = $this.get(0).selectedIndex;
                $hospitalList.find(".j-length").not(this).get(0).selectedIndex = index;
                pageIndex.reset();
            });
            $searchForm.on("submit", function(e) {
                e.preventDefault();
                pageIndex.reset();
            });
            $searchForm.on("click", ".j-showall", function(e) {
                e.preventDefault();
                $searchForm[0].reset();
                pageIndex.reset();
            });
        },
        delitem: function($that) {
            var $tr = $that.closest("tr");
            var serviceid = $tr.data("serviceid");
            var delPath = ROOTPAth + '/admin/bussiness/serviceitem/delete/';
            $.ajax({
                url: delPath,
                type: "POST",
                data: {
                    id: serviceid
                },
                success: function(data) {

                    $tr.hide();

                }
            });
        }

    };
    Utilitiy.init();
});