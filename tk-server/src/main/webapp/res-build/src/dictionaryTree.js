/**
 * 数据字典插件
 */
jQuery.fn.extend({
  dictionaryTree: function() {
	  this.jstree({
          "core": {
              "themes": {
                  "responsive": false
              },
              "check_callback": true,
              'data': {
                  async: true,
                  'url': function (node) {
                      return ROOTPAth + '/admin/sys/visitmodule/list';
                  },
                  'data': function (node) {
                      return {'id': node.id};
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
          }
      });
  }
});
/*下拉框多选代码
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
    includeSelectAllOption: true
});
*/