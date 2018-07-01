define(function (require, exports, module) {
    require("res-build/res/plugin/form/jquery.form.js");
    require("res-build/res/plugin/jquery-validation-1.13.1/dist/jquery.validate.min.js");
    require("res-build/res/plugin/jstree/dist/jstree.min.js");
    var $addForm = $("#form_edit");
    var $etitor = $("#summernote");
    var $allmap = $("#allmap");
    var $tree = $("#tree_3");
    var $treeCon = $("#tree-con");
    var $treeType = $("#tree_type");
    var $treeConType = $("#tree-con-type");
    //选择图片
    var $selImg = $("#selImg");
    var $file = $("#file");
    var tool = require("tool");
    //工具函数
    var hzuitl = {
        formatDate: function (timestamp, format) {
            return formatDate(timestamp, format);
        },
        byteLength: function (str) {
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
        init: function () {
            this.bind();
            this.createTree();
            var map = new BMap.Map("allmap", {
                minZoom: 4,
                maxZoom: 18
            });
            map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);
            //map.addControl(new BMap.ZoomControl());   //添加地图缩放控件
            map.enableScrollWheelZoom(true);
            var marker;

            function showInfo(e) {
                $addForm.find('input[name="longitude"]').val(e.point.lng);
                $addForm.find('input[name="latitude"]').val(e.point.lat);
                map.removeOverlay(marker);
                var point = new BMap.Point(e.point.lng, e.point.lat);
                map.centerAndZoom(point, 15);
                marker = new BMap.Marker(point); // 创建标注
                map.addOverlay(marker); // 将标注添加到地图中
                marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
            }

            map.addEventListener("click", showInfo);
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
            //编辑器
            $etitor.summernote({
                height: 300
            });
            //
            $addForm.on("focusin", 'input[name="regionname"]', function () {
                $tree.show();
            });
            $tree.on("click", ".j-close-tree", function () {
                $tree.hide();
            });
            $addForm.on("focusin", 'input[name="btype"]', function () {
                $treeType.show();
            });
            $treeType.on("click", ".j-close-tree", function () {
                $treeType.hide();
            });
            //验证表单
            $addForm.validate({
                rules: {
                    title: {
                        required: true
                    },
                    regionname: {
                        required: true
                    },
                    serverpay: {
                        number: true
                    },
                    score:{
                        digits:true,
                        range:[0,5]
                    }
                },
                messages: {
                    title: {
                        required: "请填写商户名称"
                    },
                    regionname: {
                        required: "请填写商户所属地区"
                    },
                    serverpay: {
                        number: "请填写费用（必须是数字）"
                    },
                    score:{
                        digits: "评分必须是数字",
                        range:"请填写0到5的数值"
                    }
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
                    if ($file.val().length && (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test($file.val()))) {
                        $("#modal-box-error").modal("show");
                        $file.focus();
                        return
                    }
                    $(fm).ajaxSubmit({
                        type: "POST",
                        dataType: "html",
                        url: ROOTPAth + "/admin/business/business/saveorupdate",
                        beforeSubmit: function (formData, jqForm, options) {
                            if (hzuitl.byteLength($etitor.summernote("code")) / (1024 * 1024) > 4) {
                                alert("文本内容总数不得超过4M 请减小图片大小或者精简文字");
                                return false;
                            }
                            var isSuccess = $addForm.validate().form();
                            /*if(isSuccess)
                             $('body').modalmanager('loading');*/
                            tool.startPageLoading();
                            return isSuccess;
                        },
                        success: function (data) {
                            tool.stopPageLoading();
                            console.log(data);
                            var $tipModal = $('#modal-box');
                            var newdata = JSON.parse(data);
                            if (newdata.code === 1) {

                                $tipModal.on('show.bs.modal', function (event) {
                                    $tipModal.find(".j-modal-closebtn").attr("href", ROOTPAth + "/admin/business/business/updateView?id=" + newdata.data + "&currentpage=1&pcode=business&subcode=businesslist")
                                });
                                $tipModal.modal("show");
                            }
                        },
                        error: function () {
                            tool.stopPageLoading();
                            $("#ajax_fail").modal("show")
                        },
                    });
                }
            });

        },
        createTree: function () {
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
                        'url': function (node) {
                            return ROOTPAth + '/admin/bas/region/list';
                        },
                        type: "POST",
                        'data': function (node) {
                            return {
                                'pid': node.id === "#" ? 1 : node.id
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
                    "contextmenu",
                    "state", "types", "wholerow"
                ]
            });
            $treeConType.jstree({
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
                        'url': function (node) {
                            return ROOTPAth + '/admin/business/business/typelist';
                        },
                        type: "POST",
                        'data': function (node) {
                            return {
                                'pid': node.id === "#" ? 0 : node.id
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
                    "contextmenu",
                    "state", "types", "wholerow"
                ]
            });
            //清楚树形菜单缓存
            $treeCon.jstree(true).clear_state();
            $treeConType.jstree(true).clear_state();
            $treeCon.bind('activate_node.jstree', function (e, data) {
                if (data.node.id === '#') {
                    var postPath = ROOTPAth + '/admin/bas/region/detail/0';
                } else {
                    var postPath = ROOTPAth + '/admin/bas/region/detail/' + data.node.id;
                }
                ;
                var parentstext = "";
                var parents = data.node.parents.sort(function (a, b) {
                    return a - b
                });
                if (parents.length === 1) {
                    parentstext = "中国"
                } else {
                    $.each(parents, function (index, val) {
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

                $.post(postPath, function (moduleObj) {

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
            });

            $treeConType.bind('activate_node.jstree', function (e, data) {
                if (data.node.id === '#') {
                    var postPath = ROOTPAth + '/admin/sys/dictionary/get/0';
                } else {
                    var postPath = ROOTPAth + '/admin/sys/dictionary/get/' + data.node.id;
                }
                ;
                var parentstext = "";
                var parents = data.node.parents.sort(function (a, b) {
                    return a - b
                });
                if (parents.length === 1) {
                    parentstext = "请选择二级"
                } else {
                    $.each(parents, function (index, val) {
                        if (val === "#") {
                            /*parentstext+="中国";*/
                        } else {
                            parentstext += $("#" + val + ">.jstree-anchor").text() + ",";
                        }

                    })
                }
                parentstext += $("#" + data.node.id + ">.jstree-anchor").text() + ",";
                $addForm.find('input[name="btype"]').val(parentstext);
                $treeType.hide();

                $.post(postPath, function (moduleObj) {
                    var obj = $.extend({}, moduleObj);
                    $addForm.find('input[name="typeid"]').val(obj.pid);
                    $addForm.find('input[name="typename"]').val(obj.pname);

                    $addForm.find('input[name="typeiid"]').val(obj.iid);
                    $addForm.find('input[name="typenname"]').val(obj.title);
                });
            });
        }
    };
    Utilitiy.init()
});