/**
 * 封装jquery一些简单的方法
 */
$.extend({
	messageBox : function(options) {
		var dfop={
				message: false,
				level: "success",
				speed: 500,
				life:3000
		};
		$.extend(dfop, options);
		if(options=='close'){
			$("#jGrowl").removeAttr("style").empty();
			return false;
		}
		if(!$("#jGrowl")[0]){
			$("body").append("<div id='jGrowl'></div>")
		}else{
			$("#jGrowl").removeAttr("style").empty();
		}
		dfop.message='<div class="'+dfop.level+'"><span></span>'+dfop.message+'</div>';
		$("#jGrowl").addClass("jGrowl").append(dfop.message).animate({top:0},dfop.speed);
		function hideMessageBox(){
			clearTimeout(window._messageBox);
			window._messageBox=setTimeout(function(){
				$("#jGrowl").remove();
			},dfop.life);
		}
		hideMessageBox();
		$("#jGrowl").hover(function(){
			clearTimeout(window._messageBox);
		},function(){
			hideMessageBox();
		})
	},
	confirm : function(o){
		var dfop={
		    level: "info",//TODO 确认 warn 警告,alert,info
		    message: "",
		    title:"确认",
		    okFunc: false,
		    cancelFunc:false,
		    cancelBtnName:"取消",
		    okbtnName :"确定"
		};
		$.extend(dfop, o);
		var div=
			'<div title="'+dfop.title+'" >'
			+'<p><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>'
			+dfop.message
			+'</p>'
			+'</div>';
		var buttonStr="{'"+dfop.okbtnName+"':function(){" +
		'if(o.okFunc){'+
		'o.okFunc.call(dfop.okFunc,$(this));'+
		'}'+
		"$(this).dialog('destroy');$(this).remove();"+
		"},'"+dfop.cancelBtnName+"':function(){" +
		'if(o.cancelFunc){'+
		'o.cancelFunc.call(dfop.cancelFunc,$(this));'+
		'}'+
		"$(this).dialog('destroy');$(this).remove();"+
		"}}";

		var buttons=eval("("+buttonStr+")");

		dfop = {
			autoOpen: true,
			resizable : false,
			modal: true,
			buttons: buttons
		};
		$.extend(dfop, o);

		$(div).dialog(dfop);
	}
});