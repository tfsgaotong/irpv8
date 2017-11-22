(function(){
	var pluginName = 'hotword',
		pluginFunction = {
				exec:function(editor){
					replaceHotWord(editor);
				}
			};
	CKEDITOR.plugins.add('hotword', {
		init : function(editor) {
			editor.addCommand(pluginName, pluginFunction);
			editor.ui.addButton('hotword', {
				label : '热词替换',
				command : pluginName,
				toolbar : "hotwrd",
				icon : this.path + "Fire.png"
			});
		}
	});
})();

function replaceHotWord(body) {
	var htmlData = body.getData();
	$.ajax({
		type:'post',
		url:'replaceWithHotWord.action',
		async:false,
		cache:false,
		data:{
			htmlData:htmlData
		},
		success:function(result){
			body.setData(result);
			$.messager.alert("提示信息", "热词已替换成功", "info");
		}
	});
}