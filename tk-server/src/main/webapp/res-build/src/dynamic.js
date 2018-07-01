define(function (require, exports, module) {
    var Page = require("page");
    var juicer = require("juicer");
    require("res-build/res/plugin/bs-confirmation/bootstrap-confirmation.js");
    var tool = require("tool");
    var pageIndex;
    var $table = $("#datatable_ajax");
    var $adviceList = $("#advice-list");
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
            '    <td>${item.uid}</td>',
            '    <td>${item.content}</td>',
            '    <td><a href="#" class="has-popover" data-img="${item.imgurl}">查看图片</a></td>',
            '    <td>${item.replycount}</td>',
            '    <td>${item.createdate}</td>',
            '    <td class="">',

            // '        <a href="' + ROOTPAth + '/admin/bas/adbanner/updateView?id=${item.id}&currentpage=${item.currentpage}&pcode=AD&subcode=ADBanner" class="btn btn-default btn-xs j-edit" ><span class="iconfont iconfont-xs">&#xe62d;</span>查看</a> ',
            //删除
            ' <button type="button" class="btn btn-danger btn-xs j-del" data-toggle="confirmation" data-placement="left"><span   class="iconfont iconfont-xs">&#xe61d;</span>删除 </button>',

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
                   url: ROOTPAth + '/admin/business/dynamic/list',//接口
                    type: 'post',
                    dataType: 'json',
                    data: function () {
                        var nickname = $searchForm.find("input[name=nickname]").val();
                        var data = {
                            length: pagelength,
                            nickname: nickname
                        };
                        return data;
                    },
                    success: function (res) {
                        tool.stopPageLoading();
                        if (res.code === 1) {
                            var newData = $.extend({}, res);
                            if (res.total > 0) {
                                $.each(newData.data, function (i, val) {
                                    newData.data[i].createdate = newData.data[i].createdate ? new Date(newData.data[i].createdate).Format("yyyy-MM-dd hh:mm:ss") : "";
                                    newData.data[i].currentpage = pageIndex.current;
                                });
                            }
                            //共多少条记录
                            $adviceList.find(".page-info-num").text(res.total);
                            $table.find("tbody").empty().append(listTpl.render(newData));
                            $adviceList.find(".has-popover").popover({
                                content: function() {
                                    var $this = $(this);
                                    var url = $this.data("img");
                                    return '<img src="' + url + '" style="width: 100px;height: 100px" alt=""/>'
                                },
                                html: true,
                                trigger: "hover",
                                placement: "top",
                                title: "图片预览"
                            });
                            $adviceList.find(".j-del").confirmation({
                                title: "确定删除吗？",
                                btnOkLabel: "确定",
                                btnCancelLabel: "取消",
                                onConfirm: function (event, element) {
                                    event.preventDefault();
                                    self.delitem($(element));
                                }
                            });
                        }

                    }
                },
                pageName: "page",
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
            $adviceList.on("change", ".j-length", function () {
                var $this = $(this);
                
                pagelength = $this.val();
                var index = $this.get(0).selectedIndex;
                console.log( $adviceList.find(".j-length").not(this).get(0).selectedIndex);
                $adviceList.find(".j-length").not(this).get(0).selectedIndex = index;
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
        delitem: function($that) {
            var $tr = $that.closest("tr");
            var organizationid = $tr.data("organizationid");
            var delPath = ROOTPAth + '/admin/business/dynamic/delete/' + organizationid;
            $.ajax({
                url: delPath,
                type: "POST",
                success: function(data) {
                    $tr.hide();
                }
            });
        }
    };
    Utilitiy.init();
});