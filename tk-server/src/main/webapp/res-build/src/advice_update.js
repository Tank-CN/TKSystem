define(function (require, exports, module) {
    require("res-build/res/plugin/form/jquery.form.js");
    require("res-build/res/plugin/jquery-formautofill/jquery.formautofill.min.js");
    require("res-build/res/plugin/jquery-validation-1.13.1/dist/jquery.validate.min.js");
    require("res-build/res/plugin/jstree/dist/jstree.min.js");
    var fixedOnScroll = require("res-build/src/fixedOnScroll.js");
    var $addForm = $("#form_edit");
    var $etitor = $("#summernote");
    var editTag = false;
    //工具函数
    var hzuitl = {
        formatDate: function (timestamp, format) {
        	return new Date(timestamp).Format(format);
        },
        byteLength: function (str) {
            return byteLength(str);
        }
    };
    fixedOnScroll.scroll($addForm.find(".form-actions-fixtop"));
 // 对Date的扩展，将 Date 转化为指定格式的String 
    // 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
    // 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
    // 例子： 
    // (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
    // (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
    Date.prototype.Format = function(fmt) 
    { //author: meizz 
      var o = { 
        "M+" : this.getMonth()+1,                 //月份 
        "d+" : this.getDate(),                    //日 
        "h+" : this.getHours(),                   //小时 
        "m+" : this.getMinutes(),                 //分 
        "s+" : this.getSeconds(),                 //秒 
        "q+" : Math.floor((this.getMonth()+3)/3), //季度 
        "S"  : this.getMilliseconds()             //毫秒 
      }; 
      if(/(y+)/.test(fmt)) 
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
      for(var k in o) 
        if(new RegExp("("+ k +")").test(fmt)) 
      fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length))); 
      return fmt; 
    }

    function byteLength(str) {
        var byteLen = 0, len = str.length;
        if (!str) return 0;
        for (var i = 0; i < len; i++)
            byteLen += str.charCodeAt(i) > 255 ? 2 : 1;
        return byteLen;
    }

    var Utilitiy = {
        init: function () {
            this.initForm();
        },
        initForm: function () {
            $.ajax({
                type: "POST",
                url: ROOTPAth + "/admin/advice/detail/" + adviceID,
                dataType: "json",
                success: function (data) {
                    $addForm[0].reset();
                    $addForm.autofill(data);
//                    $("#summernote-con").html(data.introduce);
//                    $etitor.code(data.introduce);
                }
            });
        },
    };
    Utilitiy.init()
});
