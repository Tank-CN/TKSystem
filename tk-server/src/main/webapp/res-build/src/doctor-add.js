/**
 * Created by feiwen8772 on 15/5/12.
 
 *
 */
define(function(require, exports, module) {
    require("res-build/res/plugin/form/jquery.form.js");
    require("res-build/res/plugin/jquery-validation-1.13.1/dist/jquery.validate.min.js");
    require("res-build/res/plugin/jstree/dist/jstree.min.js");
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
    var tool = require("tool");
    var $file = $("#file");
    var $file1 = $("#file1");
    var $file2 = $("#file2");
    //工具函数
    var hzuitl = {
        formatDate: function(timestamp, format) {
            return formatDate(timestamp, format);
        },
        byteLength: function(str) {
            return byteLength(str);
        }
    };

    function formatDate(timestamp, format) {
        var d = new Date(timestamp);
        var year = d.getFullYear();
        var month = d.getMonth() + 1;
        var date = d.getDate();
        var hour = d.getHours();
        var minute = d.getMinutes();
        var second = d.getSeconds();
        if (format)
            return year + "-" + month + "-" + date;
        else
            return year + "-" + month + "-" + date + "   " + hour + ":" + minute + ":" + second;
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
            this.bind();
            this.createTree();

        },
        bind: function() {
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
                        url: ROOTPAth + "/admin/bas/doctor/save",
                        beforeSubmit: function(formData, jqForm, options) {

                            // if (hzuitl.byteLength($etitor.summernote("code")) / (1024 * 1024) > 4) {
                            //     alert("文本内容总数不得超过4M 请减小图片大小或者精简文字");
                            //     return false;
                            // }
                            var isSuccess = $addForm.validate().form();
                            /*if(isSuccess)
                             $('body').modalmanager('loading');*/
                            tool.startPageLoading();
                            return isSuccess;
                        },
                        success: function(data) {
                            tool.stopPageLoading();
                            console.log(data);
                            var $tipModal = $('#modal-box');
                            var newdata = JSON.parse(data);
                            if (newdata.code === 1) {

                                $tipModal.on('show.bs.modal', function(event) {
                                    $tipModal.find(".j-modal-closebtn").attr("href", ROOTPAth + "/admin/bas/doctor/updateView?id=" + newdata.data + "&currentpage=1&pcode=2&subcode=5")
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


                // var parentId = data.node.parent;
                // var longcode = [];

                // function getParentLonecode(parentId) {
                //     var pnodeparent = $treeCon.jstree("get_node", parentId).parent;


                //     if (pnodeparent === '#') {
                //         return false
                //     }
                //     longcode.push(pnodeparent);
                //     getParentLonecode(pnodeparent);


                // }
                // console.log(getParentLonecode(parentId));



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

                    if (moduleObj.pid === '#') {
                        var postPath2 = ROOTPAth + '/admin/bas/region/detail/0';
                    } else {
                        var postPath2 = ROOTPAth + '/admin/bas/region/detail/' + moduleObj.pid;
                    };

                    // longcode_arr.push();
                    var obj = $.extend({}, moduleObj, {
                        "pname": parentstext
                    });

                    console.log(obj.longcode);
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

        }
    };
    Utilitiy.init()
});