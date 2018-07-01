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
define(function(require, exports, module) {
    require("res-build/res/plugin/form/jquery.form.js");
    require("res-build/res/plugin/jquery-formautofill/jquery.formautofill.min.js");
    require("res-build/res/plugin/jquery-validation-1.13.1/dist/jquery.validate.min.js");
    require("res-build/res/plugin/jstree/dist/jstree.min.js");
    var fixedOnScroll = require("res-build/src/fixedOnScroll.js");
    var $addForm = $("#form_edit");
    var $etitor = $("#summernote");
    var $allmap = $("#allmap");
    var $tree = $("#tree_3");
    var $treeCon = $("#tree-con");

    var $treeType = $("#tree_type");
    var $treeConType = $("#tree-con-type");
    var editTag = false;
    //选择图片
    var $selImg = $("#selImg");
    var $file = $("#file");
    var tool = require("tool");
    //工具函数
    var hzuitl = {
        formatDate: function(timestamp, format) {
            return formatDate(timestamp, format);
        },
        byteLength: function(str) {
            return byteLength(str);
        }
    };
    fixedOnScroll.scroll($addForm.find(".form-actions-fixtop"));

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

    var map;
    var Utilitiy = {
        init: function() {
            tool.startPageLoading();
            this.bind();
            this.createTree();
            this.initForm();


            map = new BMap.Map("allmap", {
                minZoom: 4,
                maxZoom: 18
            });

        },
        initForm: function() {

            $.ajax({
                type: "POST",
                url: ROOTPAth + "/admin/business/business/detail/" + organizationID,
                dataType: "json",
                success: function(data) {
                    tool.stopPageLoading();
                    console.log(data);
                    $addForm[0].reset();
                    $addForm.autofill(data);
                    $("#summernote-con").html(data.introduce);
                    var regionname_text = "";
                    regionname_text += $addForm.find('input[name="provincename"]').val();
                    regionname_text += $addForm.find('input[name="cityname"]').val();
                    regionname_text += $addForm.find('input[name="districtname"]').val();
                    regionname_text += $addForm.find('input[name="streetname"]').val();
                    regionname_text += $addForm.find('input[name="villagename"]').val();
                    $addForm.find('input[name="regionname"]').val(regionname_text);

                    var btype_text="";
                    btype_text+=$addForm.find('input[name="typename"]').val();
                    btype_text+=$addForm.find('input[name="typenname"]').val();
                    $addForm.find('input[name="btype"]').val(btype_text);

                    if (data.picurl) {
                        $("#imgurl").attr("src", data.picurl).show();
                    }
                    $('#summernote').summernote('destroy');
                    $('#summernote').hide();
                    // $('#summernote').summernote("code",data.introduce);

                    // todo 已有图片设置
                    /*var pos = data.picurl.lastIndexOf("/");
                     $("#selImg span").html((data.picurl.substring(pos + 1).length > 15) ? data.picurl.substring(pos + 1).substring(0, 15) + "..." : data.picurl.substring(pos + 1));*/
                    //设置地图
                    map.centerAndZoom(new BMap.Point(data.longitude, data.latitude), 11);
                    //map.addControl(new BMap.ZoomControl());   //添加地图缩放控件
                    map.enableScrollWheelZoom(true);
                    var marker;
                    var point = new BMap.Point(data.longitude, data.latitude);
                    map.centerAndZoom(point, 20);
                    marker = new BMap.Marker(point); // 创建标注
                    map.addOverlay(marker); // 将标注添加到地图中
                    marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画


                    function showInfo(e) {
                        if (editTag) {
                            $addForm.find('input[name="longitude"]').val(e.point.lng);
                            $addForm.find('input[name="latitude"]').val(e.point.lat);
                            map.removeOverlay(marker);
                            var point = new BMap.Point(e.point.lng, e.point.lat);
                            map.centerAndZoom(point, 20);
                            marker = new BMap.Marker(point); // 创建标注
                            map.addOverlay(marker); // 将标注添加到地图中
                            marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
                        }
                    }

                    map.addEventListener("click", showInfo);

                }
            });
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
                // $('#summernote').summernote({
                //     height: 300,
                //     lang: 'zh-CN',
                //
                // });
                $etitor.summernote({
                    height: 300,
                    callbacks: {
                        onImageUpload: function (files, editor, welEditable) {
                            for (var i = files.length - 1; i >= 0; i--) {
                                sendFile(files[i], this);
                            }
                        }
                    }
                });
                //create record for attachment
                function sendFile(file, el) {
                    data = new FormData();
                    data.append("file", file); // 表单名称

                    $.ajax({
                        type: "POST",
                        url: "/admin/business/fileupload",
                        data: data,
                        cache: false,
                        contentType: false,
                        processData: false,
                        dataType: 'json',
                        success: function (response) {
                            // 这里可能要根据你的服务端返回的上传结果做一些修改哦
                            $(el).summernote('editor.insertImage', response.url, response.filename);
                        },
                        error: function (error) {
                            alert('图片上传失败');
                        },
                        complete: function (response) {
                        }
                    });
                }
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
                    }  ,
                    uid: {
                        digits: true
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
                    ,
                    uid: {
                        digits: "用户ID必须是数字"
                    }
                },
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input


                invalidHandler: function(event, validator) { //display error alert on form submit
                    //	                $('.alert-danger', $('.login-form')).show();
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
                    if ($file.val().length && (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test($file.val()))) {
                        $("#modal-box-error").modal("show");
                        $file.focus();
                        return
                    }
                    $(fm).ajaxSubmit({
                        type: "POST",
                        dataType: "html",
                        url: ROOTPAth + "/admin/business/business/saveorupdate",
                        data: {
                            id: organizationID
                        },
                        beforeSubmit: function(formData, jqForm, options) {
                            if (hzuitl.byteLength($('#summernote').summernote('code')) / (1024 * 1024) > 4) {
                                alert("文本内容总数不得超过4M 请减小图片大小或者精简文字");
                                return false;
                            }
                            var isSuccess = $addForm.validate().form();
                            var markupStr = $('#summernote').summernote("code");

                            $('#summernote').summernote('code', markupStr);
                            /*if(isSuccess)
                             $('body').modalmanager('loading');*/
                            tool.startPageLoading();
                            return isSuccess;
                        },
                        success: function(data) {
                            tool.stopPageLoading();
                            if (data) {
                                var $tipModal = $('#modal-box');
                                var newdata = JSON.parse(data);
                                if (newdata.code === 1) {

                                    $tipModal.on('show.bs.modal', function(event) {
                                        $tipModal.find(".j-modal-closebtn").attr("href", ROOTPAth + "/admin/business/business/updateView?id=" + organizationID + "&pcode=business&subcode=businesslist&currentpage=" + currentpage)
                                    });
                                    $tipModal.modal("show");
                                }


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