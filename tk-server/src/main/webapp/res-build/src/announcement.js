define(function (require, exports, module) {
    var Page = require("page");
    var juicer = require("juicer");
    require("res-build/res/plugin/bs-confirmation/bootstrap-confirmation.js");
    var tool = require("tool");
    var pageIndex;
    var $table = $("#datatable_ajax");
    var $announceList = $("#announce-list");
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
            '<td colspan="5" style="text-align:center">',
            '暂无记录,请添加',
            '</td>',

            '</tr>',

            '{@else}',
            '{@each data as item,index}',
            '{@if index%2==0}',
            '<tr role="row" class="odd" data-announcementid="${item.id}">',
            '{@else}',
            '<tr role="row" class="even" data-announcementid="${item.id}">',
            '{@/if}',
            '    <td>${item.publishtimestr}</td>',
            '    <td>${item.title}</td>',
            '    <td>${item.publishusername}</td>',
            '    <td>${item.createdName}</td>',
            '    <td class="">',
                        //点击查看的接口
            '        <a href="' + ROOTPAth + '/admin/doc/announcement/updateView?id=${item.id}&currentpage=${item.currentpage}&pcode=DmdRe&subcode=DocAnnouncement" class="btn btn-default btn-xs j-edit" ><span class="iconfont iconfont-xs">&#xe62d;</span>查看</a> ',
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
                   // url: ROOTPAth + '/admin/bas/organization/list',//接口
                   url: ROOTPAth + '/admin/doc/announcement/list',//接口
//                    url:ROOTPAth + '/test/json.json',
                    type: 'post',
                    dataType: 'json',
                    data: function () {
                        var $searchScope=$("#searchScope");
                        var title = $searchForm.find("input[name=title]").val(); 
                        var data = {};
                        //分类搜索
                         	var thisVal=$searchScope.val();
                         	if(thisVal=="标题"){
                         	    data = {
                         	    length: pagelength,
                         	    title: title
                         	    };
                         	    $searchForm.find("input[name=title]").attr({
                         	        placeholder: '请输入要查找的'+thisVal,
                         	    });


                         	}else if(thisVal=="创建者"){
                         	    data = {
                         	    length: pagelength,
                         	    createdName: title
                         	    };
                         	    $searchForm.find("input[name=title]").attr({
                         	        placeholder: '请输入要查找的'+thisVal,
                         	    });
                         	    
                         	}else{
                         	    data = {
                         	    length: pagelength,
                         	    publishusername: title
                         	    };
                         	    $searchForm.find("input[name=title]").attr({
                         	        placeholder: '请输入要查找的'+thisVal,
                         	    });
                         	    
                         	}
                        return data;

                    },
                    success: function (res) {
                         console.log(res);
                        tool.stopPageLoading();
                        if (res.code === 1) {
                            var newData = $.extend({}, res);
                            $.each(newData.data, function (i, val) {
                            	newData.data[i].publishtimestr = newData.data[i].publishtime?new Date(newData.data[i].publishtime).Format("yyyy-MM-dd hh:mm:ss"):"";
                                //console.log(newData.data);
                                newData.data[i].currentpage=pageIndex.current;
                            });
                            //共多少条记录
                            $announceList.find(".page-info-num").text(res.total);
                            $table.find("tbody").empty().append(listTpl.render(newData));
                              $announceList.find(".j-del").confirmation({
                                title: "确定删除吗？",
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
        	 //选择图片
            $selImg.on("click", function () {
                $file.click();
            });

            $file.on("change", function () {

                $selImg.find(".has-img").remove();
                $selImg.append('<span class="has-img">' + $file.val() + '</span>');
            });
            
            $("#download").on("click", function () {
            	var url="/admin/sys/file/download/orgs";
                window.open(url);
            });
            
            var self = this;
            //修改每页显示条数
            $announceList.on("change", ".j-length", function () {
                var $this = $(this);
                
                pagelength = $this.val();
                var index = $this.get(0).selectedIndex;
                console.log( $announceList.find(".j-length").not(this).get(0).selectedIndex);
                $announceList.find(".j-length").not(this).get(0).selectedIndex = index;
                pageIndex.reset();
            });
            $searchForm.on("submit", function (e) {
                e.preventDefault();
               // console.log(data);
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
            var announcementid = $tr.data("announcementid");
            var delPath = ROOTPAth + '/admin/doc/announcement/delete/' + announcementid;
            $.ajax({
                url: delPath,
                type: "POST",
                success: function (data) {

                    $tr.hide();

                }
            });
        }
    };
    Utilitiy.init();
});