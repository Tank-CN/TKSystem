/**
 * Created by feiwen8772 on 15/5/12.
 *    
 *
 */
define(function(require, exports, module) {
    require("res-build/res/plugin/form/jquery.form.js");
    require("res-build/res/plugin/jquery-formautofill/jquery.formautofill.min.js");
    require("res-build/res/plugin/jquery-validation-1.13.1/dist/jquery.validate.min.js");
    require("res-build/res/plugin/jstree/dist/jstree.min.js");
    // var fixedOnScroll = require("res-build/src/fixedOnScroll.js");
    // var Page = require("page");
    // var juicer = require("juicer");
    // require("res-build/res/plugin/bs-confirmation/bootstrap-confirmation.js");
    // var tool = require("tool");
    // var pageIndex;
    // var $table = $("#datatable_ajax");
    // var $listbydocList = $("#listbydoc-list");
    var Page = require("page");
    var juicer = require("juicer");
    require("res-build/res/plugin/bs-confirmation/bootstrap-confirmation.js");
    var tool = require("tool");
    var pageIndex;
    var $table = $("#datatable_ajax");
    var $listbydocList = $("#listbydoc-list");
    var pagelength = 100; //一页多少条；
    var tool = require("tool");
    var $addForm = $("#form_edit");
    var $etitor = $("#summernote");
    var $allmap = $("#allmap");
    var $tree = $("#tree_3");
    var $treeCon = $("#tree-con");
    var $tree4 = $("#tree_4");
    var $treeCon4 = $("#tree-con4");
    //选择图片
    var $selImg = $("#selImg");
    var $selImg1 = $("#selImg1");
    var $selImg2 = $("#selImg2");
    var $file = $("#file");
    var $file1 = $("#file1");
    var $file2 = $("#file2");
    var $tabIndex = $(".page-tab");
    var $tabCon = $(".tabcon");

    //工具函数
    var hzuitl = {
        formatDate: function(timestamp, format) {
            return formatDate(timestamp, format);
        },
        byteLength: function(str) {
            return byteLength(str);
        }
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
            '<tr role="row" class="odd" data-serviceid="${item.id}">',
            '{@else}',
            '<tr role="row" class="even" data-serviceid="${item.id}">',
            '{@/if}',
            '    <td>${item.id}</td>',
            '    <td>${item.title}</td>',
            '    <td>${item.kinds}</td>',
            '    <td class="">',

            '        <a href="' + ROOTPAth + '/admin/bussiness/serviceitem/updateViewByDoc?id=${item.id}&pcode=2&subcode=5&docid=${item.docid}&scurrentpage=${item.scurrentpage}&currentpage=${item.currentpage}" class="btn btn-default btn-xs j-edit"  ><span class="iconfont iconfont-xs">&#xe62d;</span>查看</a> ',
            //删除
            ' <button type="button" class="btn btn-danger btn-xs j-del" data-toggle="confirmation" data-placement="left"><span   class="iconfont iconfont-xs">&#xe61d;</span>删除 </button>',

            '    </td>',
            '</tr>',
            '{@/each}',
            '{@/if}'
        ].join(""));

    function formatDate(timestamp, format) {
        var d = new Date(timestamp);
        var year = d.getFullYear();
        var month = d.getMonth() + 1;
        var date = d.getDate();
        var hour = d.getHours();
        var minute = d.getMinutes();
        var second = d.getSeconds();
        if (format) {
            if (month >= 10) {
                return year + "-" + month + "-" + date;
            } else {
                return year + "-0" + month + "-" + date;
            }
        } else {
            return year + "-" + month + "-" + date + "   " + hour + ":" + minute + ":" + second;
        }
    }


    function byteLength(str) {
        var byteLen = 0,
            len = str.length;
        if (!str) return 0;
        for (var i = 0; i < len; i++)
            byteLen += str.charCodeAt(i) > 255 ? 2 : 1;
        return byteLen;
    }

    var Utilitiy = {
        init: function() {
            tool.startPageLoading();
            var self = this;
            this.bind();
            this.createTree();
            this.initForm();
            pageIndex = new Page({
                ajax: {
                    // url: ROOTPAth + '/admin/bas/serviceitem/listbydoc',
                    url: ROOTPAth + '/admin/bussiness/serviceitem/listbydoc',
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        length: pagelength,
                        id: doctorId
                    },
                    // data: function() {
                    //     var name = $searchForm.find("input[name=name]").val();
                    //     var regionname = $searchForm.find("input[name=regionname]").val();
                    //     var data = {
                    //         length: pagelength,
                    //         regionname: regionname,
                    //         name: name
                    //     };
                    //     return data;
                    // },
                    success: function(res) {
                        console.log(res);
                        tool.stopPageLoading();
                        if (res.code === 1) {
                            var newData = $.extend({}, res);
                            $.each(newData.data, function(i, val) {

                                newData.data[i].currentpage = currentpage;
                                newData.data[i].scurrentpage = pageIndex.current;
                                if (newData.data[i].kinds == 1) {
                                    newData.data[i].kinds = "公有"
                                }
                                if (newData.data[i].kinds == 2) {
                                    newData.data[i].kinds = "私有"
                                }
                                newData.data[i].sourcestext = val.sources === 1 ? "机构添加" : "系统添加";
                                /*if (newData.data[i].status === 1) {
                                 newData.data[i].statustxt = "正常"
                                 }*/
                            });
                            //共多少条记录
                            $listbydocList.find(".page-info-num").text(res.total);
                            $table.find("tbody").empty().append(listTpl.render(newData));

                            $listbydocList.find(".j-del").confirmation({
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

                    }
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
            var returnUrl_end = windowurl.indexOf("&currentpage");

            if (returnUrl == -1 || returnUrl == "") {
                pageIndex.resetgoto(1);
            } else {
                var returnUrl_val = parseInt(windowurl.substring(returnUrl + 13), returnUrl_end);
                if (returnUrl_val != 1) {
                    pageIndex.resetgoto(returnUrl_val);
                } else {
                    pageIndex.reset()
                }
            }


        },
        initForm: function() {

            $.ajax({
                type: "POST",
                url: ROOTPAth + "/admin/bas/doctor/detail/",
                dataType: "json",
                data: {
                    id: doctorId
                },
                success: function(data) {
                    tool.stopPageLoading();
                    console.log(data);
                    $addForm[0].reset();
                    $addForm.autofill(data);
                    var birthdate_val = $("#birthdate").val();
                    if (birthdate_val) {
                        $("#birthdate").val(formatDate(parseInt(birthdate_val), "2016-05-31"));
                    }
                    $("#summernote-con").html(data.introduce);
                    var regionname_text = "";
                    regionname_text += $addForm.find('input[name="provincename"]').val();
                    regionname_text += $addForm.find('input[name="cityname"]').val();
                    regionname_text += $addForm.find('input[name="districtname"]').val();
                    regionname_text += $addForm.find('input[name="streetname"]').val();
                    regionname_text += $addForm.find('input[name="villagename"]').val();
                    $addForm.find('input[name="regionname"]').val(regionname_text);
                    if (data.header) {
                        $("#imgurl").attr("src", data.header).show();
                    }
                    if (data.imageUrl1) {
                        $("#imgurl1").attr("src", data.imageUrl1).show();
                    }
                    if (data.imageUrl2) {
                        $("#imgurl2").attr("src", data.imageUrl2).show();
                    }
                    $('#summernote').summernote('destroy');
                    $('#summernote').hide();
                    // $('#summernote').summernote("code",data.introduce);



                }
            });
        },
        bind: function() {
            //tab切换
            var url = window.location.href;
            var tabindex = url.indexOf("tabindex=");
            var tabnum_end = url.indexOf("&scurrentpage");
            var tabnum = url.substring(tabindex + 9, tabnum_end);


            // alert(1111111111111111);
            console.log(tabnum);
            $tabIndex.find("li").eq(tabnum).addClass('cur').siblings('li').removeClass('cur');
            $tabCon.eq(tabnum).removeClass('none').siblings('.tabcon').addClass('none');
            $tabIndex.on('click', 'li', function(event) {
                event.preventDefault();
                $(this).addClass('cur').siblings('li').removeClass('cur');
                var indexTab = $(this).index();
                $tabCon.eq(indexTab).removeClass('none').siblings('.tabcon').addClass('none');
            });
            //选择图片
            $selImg.on("click", function() {
                $file.click();
            });
            $selImg1.on("click", function() {
                $file1.click();
            });
            $selImg2.on("click", function() {
                $file2.click();
            });

            $file.on("change", function() {

                $selImg.find(".has-img").remove();
                $selImg.append('<span class="has-img">' + $file.val() + '</span>');
            });
            $file1.on("change", function() {

                $selImg1.find(".has-img").remove();
                $selImg1.append('<span class="has-img">' + $file1.val() + '</span>');
            });
            $file2.on("change", function() {

                $selImg2.find(".has-img").remove();
                $selImg2.append('<span class="has-img">' + $file2.val() + '</span>');
            });
            //编辑器
            $etitor.summernote({
                height: 300
                    /*lang: 'zh-CN',*/
                    /*onblur: function(e) {
                     $etitor.val($etitor.code())
                     }*/
            });
            //我要编辑
            $addForm.on("click", ".j-edit", function(event) {
                editTag = true;

                var formDom = event.delegateTarget;
                /*$(formDom).find(".form-actions-top").hide();*/
                //$(this).hide();
                $(formDom).find(".j-edit").hide();
                $(formDom).find(".j-save").show();
                $(formDom).find("[disabled]").prop("disabled", false);
                $(formDom).find("[name=spell]").prop("disabled", true);
                $(formDom).find("#summernote-con").remove();
                //编辑器
                $('#summernote').summernote({
                    height: 300,
                    lang: 'zh-CN',

                    // keyup: function(e) {
                    //   //$('#summernote').summernote('code').val("");

                    //   $('#summernote').summernote('code').html("");
                    //  $('#summernote').summernote('code').val($('#summernote').summernote('code').code())
                    // }
                });
                // $addForm[0].find(".form-body").find("input").eq(0).focusin();
                // fixedOnScroll.scroll($addForm.find(".form-actions-fixtop"));

            });
            //
            $addForm.on("focusin", 'input[name="regionname"]', function() {
                $tree.show();
            });

            $addForm.on("focusin", 'input[name="orgname"]', function() {
                $tree4.show();
            });
            $tree.on("click", ".j-close-tree", function() {
                $tree.hide();
            });
            $tree4.on("click", ".j-close-tree", function() {
                $tree4.hide();
            });
            // Datepicker
            $('#birthdate').datepicker({
                maxDate: '+0d',
                dateFormat: "yy-mm-dd",
                selectOtherMonths: true,
                yearRange: "-100:+0",
                changeMonth: true,
                changeYear: true,
                inline: true
            });
            //验证表单
            $addForm.validate({
                rules: {
                    name: {
                        required: true
                    },
                    regionname: {
                        required: true
                    },
                    // idcard: {
                    //     required: true
                    // },
                    cardtype: {
                        required: true
                    },
                    // code: {
                    //     required: true,
                    //     remote: { //自带远程验证存在的方法
                    //         url: ROOTPAth + '/admin/bas/doctor/code/check',
                    //         type: "GET",
                    //         dataType: "json",
                    //         data: {
                    //             /*username: function () {
                    //              return $UserForm.find('input[name="userName"]').val();
                    //              }*/
                    //         }
                    //     }
                    // }
                },
                messages: {
                    name: {
                        required: "请填写医生姓名"
                    },
                    regionname: {
                        required: "请填写医生所属地区"
                    },
                    cardtype: {
                        required: "请选择证件类型"
                    },
                    // idcard: {
                    //     required: "请填写证件号码"
                    // },
                    // code: {
                    //     required: "请填写工号",
                    //     remote: "工号重复"
                    // }
                },
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input


                invalidHandler: function(event, validator) { //display error alert on form submit
                    //                  $('.alert-danger', $('.login-form')).show();
                },
                highlight: function(element) { // hightlight error inputs
                    $(element)
                        .closest('.form-group').addClass('has-error'); // set error class to the control group
                },

                success: function(label) {
                    label.closest('.form-group').removeClass('has-error');
                    label.remove();

                },

                errorPlacement: function(error, element) {
                    error.insertAfter(element);
                },
                submitHandler: function(fm) {

                    // if ($file.val().length && (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test($file.val()))) {
                    //     $("#modal-box-error").modal("show");
                    //     $file.focus();
                    //     return
                    // }

                    $(fm).ajaxSubmit({
                        type: "POST",
                        dataType: "html",
                        url: ROOTPAth + "/admin/bas/doctor/update",
                        data: {
                            id: doctorId
                        },
                        beforeSubmit: function(formData, jqForm, options) {

                            // if (hzuitl.byteLength($etitor.summernote("code")) / (1024 * 1024) > 4) {
                            //     alert("文本内容总数不得超过4M 请减小图片大小或者精简文字");
                            //     return false;
                            // }
                            var isSuccess = $addForm.validate().form();
                            /*if(isSuccess)
                             $('body').modalmanager('loading');*/
                            var markupStr = $('#summernote').summernote("code");

                            $('#summernote').summernote('code', markupStr);
                            tool.startPageLoading();
                            return isSuccess;
                        },
                        success: function(data) {
                            tool.stopPageLoading();
                            console.log(data);
                            var $tipModal = $('#modal-box');
                            var newdata = JSON.parse(data);
                            if (newdata.code === 1) {
                                // var url = window.location.href;
                                // var returnUrl = url.indexOf("currentpage=");
                                // var pcodeindex = url.indexOf("&pcode");
                                // var returnUrl_val = url.substring(returnUrl + 12, pcodeindex);
                                // console.log(returnUrl_val);
                                $tipModal.on('show.bs.modal', function(event) {
                                    $tipModal.find(".j-modal-closebtn").attr("href", ROOTPAth + "/admin/bas/doctor/updateView?id=" + doctorId + "&pcode=2&subcode=5&currentpage=" + currentpage)
                                        // $tipModal.find("#returnUrl").attr("href", ROOTPAth + "/admin/bas/doctor?pcode=2&subcode=5&currentpage=" + returnUrl_val);

                                });
                                $tipModal.modal("show");
                            }
                        },
                        error: function() {
                            tool.stopPageLoading();
                            $("#ajax_fail").modal("show")
                        },



                    });
                }
            });

        },
        createTree: function() {
            $treeCon.jstree({
                "state": {
                    "key": "demo"
                },
                "core": {
                    "themes": {
                        "responsive": false
                    },
                    // so that create works
                    "check_callback": true,
                    'data': {
                        async: true,
                        'url': function(node) {
                            return ROOTPAth + '/admin/bas/region/list';
                        },
                        type: "POST",
                        'data': function(node) {
                            return {
                                'pid': node.id === "#" ? 1 : node.id
                            };
                        }

                        // 'data': function(node) {
                        //     var pid = node.id === "#" ? 1 : node.id;
                        //     var children = true;
                        //     if (pid !== 1) {
                        //         children = node.original.level > 2 ? false : true
                        //     }
                        //     return {
                        //         'pid': pid,
                        //         "children": children
                        //     };
                        // }
                    }
                },
                "types": {
                    "#": {
                        "max_children": 1,
                        "max_depth": 4,
                        "valid_children": ["root"]
                    },
                    "default": {
                        "icon": "icon-folder"
                    },
                    "file": {
                        "icon": "icon-file"
                    }
                },
                "plugins": [
                    "contextmenu",
                    "state", "types", "wholerow"
                ]
            });
            //清楚树形菜单缓存
            $treeCon.jstree(true).clear_state();
            $treeCon.bind('activate_node.jstree', function(e, data) {
                if (data.node.id === '#') {
                    var postPath = ROOTPAth + '/admin/bas/region/detail/0';
                } else {
                    var postPath = ROOTPAth + '/admin/bas/region/detail/' + data.node.id;
                };
                // if (data.node.original.level < 4) {
                //     $treeCon.jstree("toggle_node", "#" + data.node.id);
                // } else if (data.node.original.level === 4) {
                var parentstext = "";
                var parents = data.node.parents.sort(function(a, b) {
                    return a - b
                });
                //var parentstext=$("#"+parents+">.jstree-anchor").text();
                if (parents.length === 1) {
                    parentstext = "中国"
                } else {
                    $.each(parents, function(index, val) {
                        if (val === "#") {
                            /*parentstext+="中国";*/
                        } else {
                            parentstext += $("#" + val + ">.jstree-anchor").text() + ",";
                        }

                    })
                }
                parentstext += $("#" + data.node.id + ">.jstree-anchor").text() + ",";
                $addForm.find('input[name="regionname"]').val(parentstext);
                $tree.hide();

                $.post(postPath, function(moduleObj) {

                    var obj = $.extend({}, moduleObj, {
                        "pname": parentstext
                    });


                    var parentstext_arr = parentstext.split(",", 6);
                    $addForm.find('input:lt(10)').val("");
                    if (obj.longcode.length >= 2) {
                        $addForm.find('input[name="provinceid"]').val(obj.longcode.substring(0, 2));
                        $addForm.find('input[name="provincename"]').val(parentstext_arr[0]);
                    }
                    if (obj.longcode.length >= 4) {
                        $addForm.find('input[name="cityid"]').val(obj.longcode.substring(0, 4));
                        $addForm.find('input[name="cityname"]').val(parentstext_arr[1]);
                    }
                    if (obj.longcode.length >= 6) {
                        $addForm.find('input[name="districtid"]').val(obj.longcode.substring(0, 6));
                        $addForm.find('input[name="districtname"]').val(parentstext_arr[2]);
                    }
                    if (obj.longcode.length >= 9) {
                        $addForm.find('input[name="streetid"]').val(obj.longcode.substring(0, 9));
                        $addForm.find('input[name="streetname"]').val(parentstext_arr[3]);
                    }
                    if (obj.longcode.length >= 11) {
                        $addForm.find('input[name="villageid"]').val(obj.longcode.substring(0, 11));
                        $addForm.find('input[name="villagename"]').val(parentstext_arr[4]);
                    }



                });

                // }
                //todo 当选择不是第3级的时候，如何提示
            });

            $treeCon4.jstree({
                "state": {
                    "key": "doctortree"
                },
                "core": {
                    "themes": {
                        "responsive": false
                    },
                    // so that create works
                    "check_callback": true,
                    'data': {
                        async: true,
                        'url': function(node) {
                            return ROOTPAth + '/admin/bas/dept/listtree'; //admin/bas/dept/ listtree?level=1&pid=0
                        },
                        type: "POST",
                        'data': function(node) {

                            return {
                                'pid': node.id === "#" ? 0 : node.original.did,
                                'level': node.id === "#" ? 1 : node.original.level + 1
                            };
                        }
                    }
                },
                "types": {
                    "#": {
                        "max_children": 1,
                        "max_depth": 4,
                        "valid_children": ["root"]
                    },
                    "default": {
                        "icon": "icon-folder"
                    },
                    "file": {
                        "icon": "icon-file"
                    }
                },
                "plugins": [
                    "state", "types", "wholerow"
                ]
            });
            //清楚树形菜单缓存
            $treeCon4.jstree(true).clear_state();
            $treeCon4.bind('activate_node.jstree', function(e, data) {

                var node = data.node;
                // if (node.original.level < 3) {
                //     $treeCon4.jstree("toggle_node", "#" + node.id);
                // } else if (data.node.original.level === 3) {
                var orgid = node.original.orgid,
                    orgname = node.original.orgname;

                var pnode = $treeCon4.jstree("get_node", node.parent);

                var deptid = node.original.did;
                var deptname = node.text;
                // var pdeptid = pnode.original.did;
                var pdeptname = pnode.text;
                $addForm.find('input[name="orgid"]').val(orgid);
                $addForm.find('input[name="orgname"]').val(orgname);
                $addForm.find('input[name="deptid"]').val(deptid);
                $addForm.find('input[name="deptname"]').val(deptname);
                // $addForm.find('input[name="orgdept"]').val(orgname + " " + pdeptname + " " + deptname);

                $tree4.hide();
                // }


                //todo 当选择不是第3级的时候，如何提示
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
    Utilitiy.init()
});