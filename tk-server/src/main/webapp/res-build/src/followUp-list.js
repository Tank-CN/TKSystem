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
    require("res-build/res/plugin/jstree/dist/jstree.min.js");
    require("res-build/res/plugin/bs-confirmation/bootstrap-confirmation.js");
    require("res-build/res/plugin/bs-confirmation/bootstrap-confirmation.js");
    var tool=require("tool");
    var Store = require("./store.js");
    var pageIndex;
    var $doctorListwrap = $("#doctor-list-wrap");
    var $doctorNoneListwrap = $("#doctor-nonelist-wrap");
    var $table = $("#datatable_ajax");
    var $dynamicList = $("#dynamic-list");
    var pagelength = 10; //一页多少条；
    var $body = $('body');
    var $tree = $("#tree_3");
    var classify;
    
    var listTpl = juicer(
        [
            '{@if total === 0}',
            '<tr>',
            '<td colspan="10" style="text-align:center">',
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
            'data-id="${item.id}">',
            '    <td><input type="checkbox" name="visitID" data-dictionary="${item.dictionary}" data-classify="${item.classify}" value="${item.id}" /></td>',
            '    <td>${item.sort}</td>',
            '    <td>${item.cname}</td>',
            '    <td>${item.ename}</td>',
            '    <td>{@if item.required==1}是{@/if}{@if item.required==2}否{@/if}</td>',
            '    <td>${item.classifyText}</td>',
            '    <td>${item.dictionaryText}</td>',
            '    <td>{@if item.type==1}数值{@/if}{@if item.type==2}字符串{@/if}{@if item.type==3}日期{@/if}{@if item.type==4}单选{@/if}{@if item.type==5}多选{@/if}{@if item.type==6}特殊{@/if}</td>',
            '    <td class="">',

            '        <a href="' + ROOTPAth + '/admin/sys/visitnode/updateView?currentpage=${currentpage}&moudleId='+Store.get("classify")+'&id=${item.id}&mode=view&pcode=VisitNode&subcode=VisitNodeList" class="btn btn-default btn-xs j-edit" ><span class="iconfont iconfont-xs">&#xe62d;</span>查看</a> ',
            '        <a href="' + ROOTPAth + '/admin/sys/visitnode/updateView?currentpage=${currentpage}&moudleId='+Store.get("classify")+'&id=${item.id}&mode=update&pcode=VisitNode&subcode=VisitNodeList" class="btn btn-default btn-xs j-edit" ><span class="iconfont iconfont-xs">&#xe62d;</span>修改</a> ',
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
            pageIndex = new Page({
                ajax: {
                    url: ROOTPAth + '/admin/sys/visitnode/list',
                    type: 'POST',
                    dataType: 'json',
                    data: function () {
                        var data = {length: pagelength,classfy:classify};
                        return data;
                    },
                    success: function (res) {
                        tool.stopPageLoading();
                        $doctorListwrap.show();
                        $doctorNoneListwrap.hide();
                        if (res.code === 1) {
                            var newData = $.extend({}, res);
                            //共多少条记录
                            $dynamicList.find(".page-info-num").text(res.total);
                            $table.find("tbody").empty().append(listTpl.render(newData));
                            $("#datatable_ajax").find("tbody tr").each(function(i){
                        		$(this).on("click",function(){
                        			var checkbox = $(this).find("[name='visitID']")[0];
                        			if(!$(checkbox).prop("checked")){
                        				$(checkbox).prop("checked",true);
                        			}else{
                        				$(checkbox).prop("checked",false);
                        			}
                        		});
                        		//复选框添加点击事件并防止向上冒泡
                        		var checkboxItem = $(this).find("[name='visitID']")[0];
                        		$(checkboxItem).click(function(event){
                        			event.stopPropagation();
                        		});
                        	});
                            $dynamicList.find(".j-del").confirmation({
                                title: "确定删除该字段吗？",
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
                getTotalPage: function (res) {
                    return Math.ceil(res.total / pagelength);
                },
                pageWrapper: '.table-page'
            });
            var moudleId = $("#classifyId").val();
        	if(moudleId != null && moudleId != ""){
        		classify = moudleId;
        		pageIndex.resetgoto($("#currentpage").val());//第一次进来加载右边的列表
        		
        	}
        },
        bind: function () {
            var self = this;
            $table.on("change", ".j-length", function () {
                var $this = $(this);
                pagelength = $this.val();
                var index = $this.get(0).selectedIndex;
                $table.find(".j-length").not(this).get(0).selectedIndex = index;
                pageIndex.reset();
            });
            $tree.bind('activate_node.jstree', function (e, data) {
                var parent = data.node.parent;
                //console.log(data.node.parents);
                //console.log(data.node.children);
                if(parent != "#"){
                    tool.startPageLoading("数据加载中...");
                    //根据分组来查询节点数据
                    classify = data.node.id;
                    $("#classifyId").val(data.node.id);
                    Store.set("classify", classify);
                    pageIndex.reset();
                }else{
                    $doctorListwrap.hide();
                    $doctorNoneListwrap.show();
                }
            });
            //修改每页显示条数
            $dynamicList.on("change", ".j-length", function () {
                var $this = $(this);
                pagelength = $this.val();
                var index = $this.get(0).selectedIndex;
                $dynamicList.find(".j-length").not(this).get(0).selectedIndex = index;
                pageIndex.reset();
            });
        },
        delitem:function($that){
            var $tr = $that.closest("tr");
            var id = $tr.data("id");
            var delPath = ROOTPAth + '/admin/sys/visitnode/delete';
            $.ajax({
                url: delPath,
                type: "POST",
                data:{
                    id:id
                },
                success: function (data) {

                    pageIndex.reset();

                }
            });
        }

    };
    Utilitiy.init();
    
   
});

$(document).ready(function(){
    $("#export").click(function(){
        var url = ROOTPAth + "/admin/sys/visitnode/download";
        window.open(url);
    });
});
function addView(){
	var classify = $("#classifyId").val();
	var url = ROOTPAth + "/admin/sys/visitnode/addVisitNodeView?classifyId="+classify+"&pcode=Business&subcode=followUpList";
	window.location.href = url;
}

