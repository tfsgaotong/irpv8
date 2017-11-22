(function(){
	var pluginName = 'hotwordclose',
		pluginFunction = {
				exec:function(editor){
					hotWordclose(editor);
				}
			};
	CKEDITOR.plugins.add('hotwordclose', {
		init : function(editor) {
			editor.addCommand(pluginName, pluginFunction);
			editor.ui.addButton('hotwordclose', {
				label : '热词替换关闭',
				command : pluginName,
				toolbar : "hotwordclose",
				icon : this.path + "Fire.png"
			});
		}
	});
})();

function hotWordclose(body) {
	var htmlData = body.getData();
	$.ajax({
		type:'post',
		url:'hotWordclose.action',
		async:false,
		cache:false,
		data:{
			htmlData:htmlData
		},
		success:function(result){
			body.setData(result);
			$.messager.alert("提示信息", "热词替换已关闭", "info");
		}
	});
}