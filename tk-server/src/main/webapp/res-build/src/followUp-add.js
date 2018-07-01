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
    require("res-build/res/plugin/form/jquery.form.js");
    require("res-build/res/plugin/jquery-validation-1.13.1/dist/jquery.validate.min.js");
    require("res-build/res/plugin/bs-confirmation/bootstrap-confirmation.js");
    
    var $addForm = $("#form_edit");
    
    var Utilitiy = {
    	init: function () {
    		var pollowUpFiledTypeValue = $("#pollowUpFiledTypeValue").val();
    		if(pollowUpFiledTypeValue != undefined && pollowUpFiledTypeValue != null && pollowUpFiledTypeValue != ""){
    			var array = pollowUpFiledTypeValue.split(',');
    			$('#pollowUpFiledType option').each(function () {
                	var $option = $(this);
                	var value = $option.val();
                	for (var i=0;i<array.length;i++){
                		if(array[i] == value){
                			$option.attr("selected","selected");
                		}
                	}
                });
    		}
    		$('#pollowUpFiledType').multiselect({
    	        includeSelectAllOption: true,
    	        allSelectedText: '已全选',
    	        buttonWidth: '220px',
    	        includeSelectAllOption: true,
    	        onChange:function(){
    	        }
    	    });
        	this.bind();
        },
        bind: function () {
            //验证表单
            $addForm.validate({
                rules: {
                	cname: {
                        required: true,
                        maxlength:20
                    },
                    ename: {
                        required: true,
                        maxlength:40
                    },
                    hint: {
                    	required: true,
                    	maxlength:30
                    },
                    sort: {
                    	required: true,
                    	shuZi:true
                    },
                    precise:{
                    	preciseValidate:true,
                    	shuZi:true
                    },
                    rangevalue:{
                    	maxlength:30
                    }
                },
                messages: {
                	cname: {
                        required: "请填写字段的中文名",
                        maxlength:"中文名不能大于20个字符"
                    },
                    ename: {
                        required: "请填写字段的英文名",
                        maxlength:"英文名不能大于40个字符"
                    },
                    hint: {
                    	required: "请填写提示语",
                    	maxlength:"提示语不能大于30个字符"
                    },
                    sort: {
                    	required: "请填写排序"
                    },
                    rangevalue:{
                    	maxlength:"值范围不能大于30个字符"
                    }
                },
                errorElement: 'span', //valideta验证信息所放在的表单
                errorClass: 'help-block', // 验证信息的样式
                focusInvalid: false, // do not focus the last invalid input
                highlight: function (element) {
                    $(element).closest('.errorClasses').addClass('has-error'); // 验证字段高亮显示
                },
                success: function (label) {
                    label.closest('.errorClasses').removeClass('has-error');
                    label.remove();
                },

                errorPlacement: function (error, element) {
                	error.insertAfter(element);
                },
                submitHandler: function (fm) {
                    $(fm).ajaxSubmit({
                        type: "POST",
                        dataType: 'json',
                        beforeSubmit: function (formData, jqForm, options) {
                            var isSuccess = $addForm.validate().form();
                            return isSuccess;
                        },
                        success: function (data) {
                            if(data.code == 1){
                                $.messageBox({message:data.msg,level:"success"});
                        	}else{
                        		$.messageBox({message:data.msg,level:"error"});
                        	}
                        }
                    });
                }
            });
        }
    };
    
    Utilitiy.init();
    
    function trim(str){
    	return str.replace(/(^\s*)|(\s*$)/g,'');
    }
    
    jQuery.validator.addMethod("shuZi", function(value, element) {
    	var reg = new RegExp("^[0-9]*$");  
	    if(!reg.test(value)){  
	    	return false; 
	    } 
    	return true; 
    }, "请输入数字!");
    jQuery.validator.addMethod("defaultvalueValidete", function(value, element) {
    	var defaulttype = $("#defaulttype").val();
    	if(parseInt(defaulttype) == 1){
    		var defaultvalue = $("#defaultvalue").val();
    		if(trim(defaultvalue) == "" || trim(defaultvalue) == null){
    			return false;
    		}
    	}
    	return true; 
    }, "请填写默认值");
    jQuery.validator.addMethod("preciseValidate", function(value, element) {
    	var type = $("#type").val();
    	if(parseInt(type) == 1){
    		var precise = $("#precise").val();
    		if(trim(precise) == "" || trim(precise) == null){
    			return false
    		}
    		//var reg = new RegExp("^[0-9]+(.[0-9]{0,4})?$");  
    	    //if(!reg.test(precise)){  
    	    //    return false;  
    	    //}
    	}
    	return true; 
    }, "请填写精确小数");
    
});
$(document).ready(function(){
    bindDateController();
	$("#nodeDictionary").change(function () {
		getChildListByParent($("#nodeDictionary").val());
	});
	
	visitNodeShowOrHide();
	
	$("#type").on("change", function () {
		visitNodeShowOrHide();
		bindDateController();
		if($("#type").val()!=3){
		    var item = $('#defaultvalue4').clone(true);
	        $(item).show();
	        $("#defaultMain").html(item);
		}
    });
	$("#dataFormat").on("change", function () {
	    bindDateController();
	});
	
	function bindDateController(){
	    if($("#type").val()==3){
	        var dataFormat = $("#dataFormat").val();
	        var item = null;
	        var timeFormat = "";
	        if(dataFormat == 1){
	            item = $('#defaultvalue1').clone(true);
	        }
	        if(dataFormat == 2){
	            item = $('#defaultvalue2').clone(true);
	            timeFormat = "HH:mm";
	        }
	        if(dataFormat == 3){
	            item = $('#defaultvalue3').clone(true);
	            timeFormat = "HH:mm:ss";
	        }
	        $(item).show();
            $(item).attr("id","defaultvalue");
            $("#defaultMain").html(item);
            $('#defaultvalue').datetimepicker({
                timeFormat: timeFormat
            });
        }
	}
	
	$("#nodeDictionaryName").autocomplete({
		source: function(request, response) {
			$.ajax({
				url: ROOTPAth + "/admin/sys/dictionary/findListByTitle",
				type: "POST",
				data:{"title":$("#nodeDictionaryName").val()},
				success: function(data) {
					if(data.length == 0){
						$("#nodeDictionary").val("");
					}else{
						response($.map(data, function(item) {
							return {
								label: item.title,
								value: item.title,
								id: item.iid
							}
						}))
					}
					
				},
				error : function(){
					$.messageBox({
						message:"搜索失败，请重新登入！",
						level:"error"
					});
				}
			})
		},
		select: function(event, ui) {
			$("#nodeDictionary").val(ui.item.id);
			getChildListByParent(ui.item.id);
		}
	});
	
	$("#nodeDictionaryName").blur(function(){
		  var v = $(this).val();
		  if(v == null || v == ""){
			  $("#nodeDictionary").val("");
		  }
	});
});
/**
 * 根据选择的字典加载字典逻辑关系
 * @param parentId：父字典ID
 */
function getChildListByParent(parentId){
	var type = $("#type").val();
	//单选不用去加载字典项
	if(type == 4){
		return;
	}
	var url = ROOTPAth + "/admin/sys/dictionary/querySysDictionaryByPId/"+parentId;
	$.ajax({
        url: url,
        type: "POST",
        success: function (data) {
            if(data != null && data != ""){
            	//更换了选择的字典要把二级字典请空重新设置
            	$("#dictionaryValue").empty();
            	$("#relativelyValue").empty();
            	$('#relativelyValue').multiselect('destroy');
            	$("#cascadeDicid").empty();
            	$("#dictionaryValue").append("<option value=''></option>");
        		$("#cascadeDicid").append("<option value=''></option>");
            	$("#parentDictionary").val(parentId);
            	$("#parentCascade").val(parentId);
            	for(var i=0;i<data.length;i++){
            		var temp = data[i];
            		$("#dictionaryValue").append("<option value='"+temp.iid+"'>"+temp.title+"</option>");
            		$("#relativelyValue").append("<option value='"+temp.iid+"'>"+temp.title+"</option>");
            		$("#cascadeDicid").append("<option value='"+temp.iid+"'>"+temp.title+"</option>");
            	}
            	$('#relativelyValue').multiselect({
                    includeSelectAllOption: true,
                    allSelectedText: '已全选',
                    buttonWidth: '515px',
                    includeSelectAllOption: true
                });
            }
        }
    });
}
function visitNodeShowOrHide(){
	var $this = $("#type");
    if($this.val() == 6){
    	$("#visitNodeTypeContent").show();
    	$("#dataControlsTypeDivContent").hide();
    	$("#defaultDivContent").hide();
    	$("#dictionaryDivMain").hide();
    	$("#dataFormatDicContent").hide();
    }else if($this.val() == 5){
    	$("#dictionaryDivMain").show();
    	$("#dictionaryLofficDivContent").show();
    	$("#dataControlsTypeDivContent").show();
    	$("#dataFormatDicContent").hide();
    	$("#visitNodeTypeContent").hide();
    }else if($this.val() == 4){
    	$("#dictionaryDivMain").show();
    	$("#dataControlsTypeDivContent").show();
    	$("#dataFormatDicContent").hide();
    	$("#dictionaryLofficDivContent").hide();
    	$("#visitNodeTypeContent").hide();
    }else if($this.val() == 3){
    	$("#dataControlsTypeDivContent").hide();
    	$("#dataFormatDicContent").show();
    	$("#dictionaryDivMain").hide();
    	$("#visitNodeTypeContent").hide();
    }else{
    	$("#dataFormatDicContent").hide();
    	$("#dataControlsTypeDivContent").show();
    	$("#defaultDivContent").show();
    	$("#dictionaryDivMain").hide();
    	$("#visitNodeTypeContent").hide();
    }
}

