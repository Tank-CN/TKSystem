/**
 * Created by feiwen8772 on 15/5/4.
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
    var $roleList = $("#role-list");
    var pagelength = 10; //一页多少条；
    var $body=$('body');
    $body.tooltip({
        selector: '.has-tooltip'
    });
    /*$body.confirmation({
    			selector: '[data-toggle="confirmation"]',
        onConfirm: function() { alert('confirm') }
    		});*/
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
            '<tr role="row" class="odd" data-roleid="${item.id}">',
            '{@else}',
            '<tr role="row" class="even" data-roleid="${item.id}">',
            '{@/if}',
            '    <td>${item.title}</td>',
            '    <td>${item.code}</td>',
            '    <td class="flagtxt">${item.flagtxt}</td>',
            '    <td class="">${item.intro}</td>',
            '    <td class="">',

            '        <a href="' + ROOTPAth + '/admin/sys/roleModule/updateView/${item.id}?pcode=Sys&subcode=SysRole" class="btn btn-default btn-xs j-edit" ><span class="iconfont iconfont-xs">&#xe62d;</span>查看</a> ',
            '{@if item.candel===1}',
            //禁用
            ' <button type="button" class="btn btn-danger btn-xs j-disable" data-toggle="confirmation" data-placement="top"',
            '{@if item.flag === 0}',
            'style="display:none"',
            '{@else}',
            '',
            '{@/if}',
            '>' +
            '<span  class="iconfont iconfont-xs">&#xe61d;</span>禁用 </button>',
            //启用
            ' <button type="button" class="btn btn-default btn-xs j-enable" data-toggle="confirmation" data-placement="top"',
            '{@if item.flag === 0}',
            '',
            '{@else}',
            'style="display:none"',
            '{@/if}',
            '><span    class="iconfont iconfont-xs">&#xe61f;</span>启用 </button>',
            '{@/if}',
            '    </td>',
            '</tr>',
            '{@/each}',

        '{@/if}'
        ].join(""));

    var Utilitiy = {
        init: function () {
            var self = this;
            tool.startPageLoading();
            this.bind();
            pageIndex = new Page({
                ajax: {
                    url: ROOTPAth + '/admin/sys/role/list',
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
                                newData.data[i].flagtxt = val.flag === 1 ? '启用' : '禁用';
                                if(val.id===1||val.id===2){
                                    newData.data[i].candel=0
                                }else{
                                    newData.data[i].candel=1
                                }

                            });
                            $roleList.find(".page-info-num").text(res.data.length);
                            $table.find("tbody").empty().append(listTpl.render(newData));


                            $roleList.find(".j-disable").confirmation({
                                title:"确定禁用该角色吗？",
                                btnOkLabel:"确定",
                                btnCancelLabel:"取消",
                                onConfirm: function (event, element) {
                                    event.preventDefault();
                                    self.disableitem($(element));
                                }/*,
                                onCancel: function () {
                                    alert('cancel')
                                }*/
                            });
                            $roleList.find(".j-enable").confirmation({
                                title: "确定启用该角色吗？",
                                btnOkLabel: "确定",
                                btnCancelLabel: "取消",
                                onConfirm: function (event, element) {
                                    event.preventDefault();
                                    self.enableitem($(element));
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
            $roleList.on("change", ".j-length", function () {
                var $this = $(this);
                pagelength = $this.val();
                var index = $this.get(0).selectedIndex;
                $roleList.find(".j-length").not(this).get(0).selectedIndex = index;
                pageIndex.reset();
            });
            //删除
            /*$roleList.on("click", ".j-del", function (event) {
                event.preventDefault();
                self.delitem($(this));
            });*/

        },
        //停用帐号
        disableitem: function ($that) {
            var $tr = $that.closest("tr");
            var roleid = $tr.data("roleid");
            var delPath = ROOTPAth + '/admin/sys/role/disable/' + roleid;
            $.ajax({
                url: delPath,
                type: "POST",
                success: function (data) {
                    $tr.find(".flagtxt").text("禁用");
                    $that.hide();
                    //$tr.data("flag",1);
                    $tr.find(".j-enable").show();
                }
            });

        },
        //启用帐号
        enableitem: function ($that) {
            var $tr = $that.closest("tr");
            var roleid = $tr.data("roleid");
            var delPath = ROOTPAth + '/admin/sys/role/enable/' + roleid;
            $.ajax({
                url: delPath,
                type: "POST",
                success: function (data) {
                    $tr.find(".flagtxt").text("启用");
                    $that.hide();
                    //$tr.data("flag",1);
                    $tr.find(".j-disable").show();
                }
            });
        }
    };
    Utilitiy.init()

});
/*   var TableAjax = function () {
 var handleRecords = function () {
 var grid = new Datatable();
 grid.init({
 src: $("#datatable_ajax"),
 onSuccess: function (grid) {
 // execute some code on network or other general error
 },
 onError: function (grid) {
 // execute some code on network or other general error
 },
 dataTable: {
 "columnDefs": [
 {
 "targets": 4,
 "render": function (data, type, row, meta) {
 return "<span class='glyphicon glyphicon-pencil link-hand-ioc link-color' data-toggle='tooltip' data-placement='left' title='修改' onclick='modifyView(\""+data.id+"\")'></span>&nbsp" +
 "<span class='glyphicon glyphicon-trash link-hand-ioc link-color' data-toggle='tooltip' data-placement='left' title='禁用' onclick='forbidden(\""+data.id+"\")'></span>&nbsp";
 },
 "orderable": false
 },{
 "targets": 2,
 "render": function (data, type, row, meta) {
 return data === 0 ? '启用' : '停用';
 }
 }
 ],
 "columns": [
 { "title": "角色名称", "data": "title", "width": "280px;" },
 { "title": "角色编号", "data": "code", "width": "120px;" },
 { "title": "角色状态", "data": "flag", "width": "120px;" },
 { "title": "角色说明", "data": "intro", "width": "80px;" },
 { "title": "操作", "data": null, "width": "80px;" }
 ],
 "pageLength": 10,
 "serverSide": true,
 "ajax": {
 "url": "sys/role/list"
 },
 "order": [
 [ 3, "asc" ]
 ]
 }
 });
 }
 return {
 init: function () {
 handleRecords();
 }
 };
 }();

 jQuery(document).ready(function () {
 TableAjax.init();
 });

 function modifyView(objId){
 var updateViewPath = $('#webBasePath').val() + '/admin/sys/roleModule/updateView/' + objId;
 ajaxLoadView(updateViewPath);
 }

 function forbidden(objId){
 var delPath = $('#webBasePath').val() + '/admin/sys/role/delete/' + objId;
 $.ajax({
 url:delPath,
 type:"DELETE",
 success:function(data){
 var url = $('#webBasePath').val() + '/admin/sys/role';
 ajaxLoadView(url);
 }
 });
 }

 function ajaxLoadView(url){
 var pageContentBody = $('.page-content .page-content-body');
 $.ajax({
 type: "GET",
 cache: false,
 url: url,
 dataType: "html",
 success: function (res) {
 Metronic.stopPageLoading();
 pageContentBody.html(res);
 Layout.fixContentHeight(); // fix content height
 Metronic.initAjax(); // initialize core stuff
 },
 error: function (xhr, ajaxOptions, thrownError) {
 pageContentBody.html('<h4>Could not load the requested content.</h4>');
 Metronic.stopPageLoading();
 }
 });
 }*/
