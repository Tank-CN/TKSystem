/**
 * Created by feiwen8772 on 15/5/7.
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
    require("res-build/res/plugin/jquery-validation-1.13.1/dist/jquery.validate.min.js");


    var Page = require("page");
    var juicer = require("juicer");
    var pageIndex,
        pagelength=10,
        $table = $("#datatable_ajax"),
        $appversionList = $("#appversion-list");
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
    //列表模板
    var listTpl=juicer(
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
                '<tr role="row" class="odd" data-id="${item.id}">',
                '{@else}',
                '<tr role="row" class="even" data-id="${item.id}">',
                '{@/if}',
                '    <td>${item.appname}</td>',
                '    <td>${item.appcode}</td>',
                '    <td>${item.appversion}</td>',
                '    <td>${item.modifydatetime}</td>',
                '    <td class=" heading">',
                '  <a href="' + ROOTPAth + '/admin/sys/appversion/updateview/${item.id}?pcode=Sys&subcode=APPUpdate" class="btn btn-default btn-xs j-edit"    data-appname="${item.appname}" data-appcode="${item.appcode}" data-appversion="${item.appversion}"  data-id="${item.id}" data-type="${item.type}" data-des="${item.des}" ><span class="iconfont iconfont-xs">&#xe61c;</span>修改</a>',
                '    </td>',
                '</tr>',
                '{@/each}',
                '{@/if}'].join(""));
             /*<span     class="has-tooltip" <!--data-toggle="modal" data-target="#modifyModal"-->    data-toggle="tooltip" data-placement="top" title="修改"><a href="#" class=" j-edit iconfont" data-toggle="modal" data-target="#modifyModal"                         data-usernema="${item.username}" data-email="${item.email}" data-mobile="${item.mobile}"  data-id="${item.id}">&#xe617;</a></span> ',
                             '<span class="has-tooltip" data-toggle="tooltip" data-placement="top" title="添加角色"><a href="#" class=" j-add iconfont"  data-toggle="modal" data-target="#addRoleModal" data-usernema="${item.username}" data-email="${item.email}" data-mobile="${item.mobile}"  data-id="${item.id}">&#xe610;</a></span>',
                             ' <a  href="#" class="has-tooltip  j-del iconfont" data-toggle="tooltip" data-placement="top" title="禁用">&#xe618;</a>*/
    var $modifyModal=$('#modifyModal'),$modifyForm=$('#appModifyForm');
    $modifyModal.on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the modal

        var appname = button.data('appname'); // Extract info from data-* attributes
        var appcode = button.data('appcode');
        var appversion = button.data('appversion');
        var id = button.data('id');
        var type = button.data('type');
        var des = button.data('des');
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this);
        $modifyForm[0].reset();
        //modal.find('.modal-title').text('New message to ' + recipient)
        modal.find('input[name=appname]').val(appname);
        //.selDate[0].selectedIndex
        var $appcodeSelect=modal.find('select[name=appcode]');
        var appcodeOptionIndex=$appcodeSelect.find('option').index($appcodeSelect.find('option[value='+appcode+']'));
        if(appcodeOptionIndex>0){
        $appcodeSelect[0].selectedIndex=appcodeOptionIndex;
        }
        //modal.find('input[name=appcode]').val(appcode);
        modal.find('input[name=appversion]').val(appversion);
        modal.find('input[name=id]').val(id);
        var $apptypeSelect = modal.find('select[name=type]');
        var apptypeOptionIndex = $apptypeSelect.find('option').index($apptypeSelect.find('option[value=' + type + ']'));
        if (apptypeOptionIndex > 0) {
            $apptypeSelect[0].selectedIndex = apptypeOptionIndex;
        }
        //modal.find('input[name=type]').val(type);
        modal.find('textarea[name=des]').val(des);
    });

    var Utilitiy = {
        init: function () {
            this.bind();
        },
        bind: function () {
            var self = this;
            pageIndex = new Page({
                ajax: {
                    url: ROOTPAth + '/admin/sys/appversion/list',
                    type: 'POST',
                    dataType: 'json',
                    data: function () {
                        var data = {
                            length: pagelength
                        };

                        return data;
                    },
                    success: function (res) {

                        var newData = $.extend({}, res);
                        $.each(newData.data, function (i, val) {
                               newData.data[i].modifydatetime=new Date(newData.data[i].modifydate).Format("yyyy-MM-dd hh:mm:ss");

                       });
                        //console.log(newData);
                        $table.find("tbody").empty().append(listTpl.render(newData));

                        //$table.find("tbody").empty().append("");
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
            //分页，修改每页显示数据
            $appversionList.on("change", ".j-length", function () {
                var $this = $(this);
                pagelength = $this.val();
                var index = $this.get(0).selectedIndex;
                $appversionList.find(".j-length").not(this).get(0).selectedIndex = index;
                pageIndex.reset();
            });
            //表单验证-修改用户
            $modifyForm.validate({
                rules: {
                    appname: "required",
                    appversion: "required",
                    des: "required"
                },
                messages: {
                    appname: "App名称不能为空",
                    appversion: "App版本不能为空",
                    des: "描述不能为空"
                },
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input


                invalidHandler: function (event, validator) { //display error alert on form submit
                    //	                $('.alert-danger', $('.login-form')).show();
                },
                highlight: function (element) { // hightlight error inputs
                    $(element)
                        .closest('.form-group').addClass('has-error'); // set error class to the control group
                },

                success: function (label) {
                    label.closest('.form-group').removeClass('has-error');
                    label.remove();
                },

                errorPlacement: function (error, element) {
                    error.insertAfter(element);
                },
                submitHandler: function (fm) {


                    //var updatePath = ;
                    function getDoc(frame) {
                         var doc = null;

                         // IE8 cascading access check
                         try {
                             if (frame.contentWindow) {
                                 doc = frame.contentWindow.document;
                             }
                         } catch(err) {
                         }

                         if (doc) { // successful getting content
                             return doc;
                         }

                         try { // simply checking may throw in ie8 under ssl or mismatched protocol
                             doc = frame.contentDocument ? frame.contentDocument : frame.document;
                         } catch(err) {
                             // last attempt
                             doc = frame.document;
                         }
                         return doc;
                     }


                        var formObj = $(fm);
                        var formURL = ROOTPAth + '/admin/sys/appversion/update';

                        if(window.FormData !== undefined)  // for HTML5 browsers
                        {

                            var formData = new FormData(fm);
                            $.ajax({
                                url: formURL,
                                type: 'POST',
                                data:  formData,
                                mimeType:"multipart/form-data",
                                contentType: false,
                                cache: false,
                                processData:false,
                                success: function(data, textStatus, jqXHR)
                                {
                                    $modifyModal.modal('hide');
                                    window.location.reload();
                                },
                                error: function(jqXHR, textStatus, errorThrown)
                                {

                                }
                           });
                           /* e.preventDefault();
                            e.unbind();*/
                       }
                       else  //for olden browsers
                        {
                            //generate a random id
                            var  iframeId = 'unique' + (new Date().getTime());

                            //create an empty iframe
                            var iframe = $('<iframe src="javascript:false;" name="'+iframeId+'" />');

                            //hide it
                            iframe.hide();

                            //set form target to iframe
                            formObj.attr('target',iframeId);

                            //Add iframe to body
                            iframe.appendTo('body');
                            iframe.load(function(e)
                            {
                                var doc = getDoc(iframe[0]);
                                var docRoot = doc.body ? doc.body : doc.documentElement;
                                var data = docRoot.innerHTML;
                                //data is returned from server.
                                $modifyModal.modal('hide');
                                                                                                                                                    window.location.reload();
                            });

                        }

                    /*var ajaxOption = {
                                    "url": updatePath,
                                    "data": $modifyForm.serialize(),
                                    "dataType": "json",
                                    "method": "POST",
                                    success: function (data) {
                                        *//*var $list = $("#Type");
                                        $list.html("");
                                        for (var i = 0, len = data.length; i < len; i++) {
                                            city += '<option value="' + data[i].Code + '">' + data[i].Value + '</option>';
                                        }
                                        $list.append(city);*//*
                                        $modifyModal.modal('hide');
                                                                window.location.reload();
                                    }
                                };
                                $.ajax(ajaxOption);*/

                    /*$.post(updatePath, $modifyForm.serialize(), function (data) {
                        $modifyModal.modal('hide');
                        window.location.reload();
                    });*/

                }
            });
        }
    };
    Utilitiy.init();
});


/*
<script>
    var modalid;

    var TableAjax = function () {

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
                            "targets": 3,
                            "render": function (data, type, row, meta) {
                                //alert(data)
                                if(data!=undefined&&data!=null)
                                    return  hzuitl.formatDate(data);
                                else
                                    return  "";
                            }
                        },
                        {
                            "targets": 4,
                            "render": function (data, type, row, meta) {
                                return "<a class='btn btn-xs default btn-editable' data-toggle='modal'  onclick='updateItem("+data.id+")'  ><i class='fa fa-pencil'></i> 编辑 </a>"+
                                        "<a class='btn btn-xs default btn-editable' data-toggle='modal'  onclick='deleteItem(" + data.id + ")'><i class='fa fa-times'></i> 删除 </a>";
                                */
/*'<span data-toggle="tooltip" data-placement="left" title="修改" onclick="updateItem(\'' + data.did + '\')" class="glyphicon glyphicon-pencil"></span>&nbsp;' +
                                        '<span data-toggle="tooltip" data-placement="left" title="禁用" onclick="deleteItem(\'' + data.did + '\')" class="glyphicon glyphicon-trash"></span>';*//*

                            },
                            "orderable": false
                        }
                    ],
                    "columns": [
                        { "title": "app名称", "data": "appname", "width": "120px;","orderable": true  },
                        { "title": "App类型", "data": "appcode", "width": "120px;", "orderable": true  },
                        { "title": "App版本", "data": "appversion", "width": "120px;", "orderable": true  },
                        { "title": "创建时间", "data": "modifydate", "width": "120px;", "orderable": true  },
                        { "title": "操作", "data": null, "width": "80px;" }
                    ],
                    "pageLength": 10,
                    "serverSide": true,
                    "ajax": {
                        "url": "${ctx}/admin/sys/appversion/list"
                    },
                    "order": [
                        [ 3, "desc" ]
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

    function updateItem(itemId){
        var updatePath = $('#webBasePath').val() + '/admin/sys/appversion/updateview/' + itemId;
        ajaxLoadView(updatePath);
    }



    function deleteItem(itemId){
        if(!window.confirm("确定要删除?"))
            return false;
        var delPath = $('#webBasePath').val() + '/admin/sys/appversion/del/' + itemId;
        $.ajax({
            url:delPath,
            type:"DELETE",
            success:function(data){
                var url = $('#webBasePath').val() + '/admin/sys/appversion';
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
    }
</script>*/
