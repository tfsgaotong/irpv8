CKEDITOR.plugins.add('irpext', {
	init : function(editor) {
		if (editor.addMenuItems) {
			editor.addMenuGroup('extract', 999);
			editor.addCommand('extractTitle', {
				exec : function(e) {
					$("input[name='irpDocument.doctitle']").val(e.getSelection().getSelectedText());
				}
			});
			editor.addCommand('extractKeywords', {
				exec : function(e) {
					var ipkw = $("input[name='irpDocument.dockeywords']");
					if($.trim(ipkw.val()).length==0){
						ipkw.val(e.getSelection().getSelectedText());
					}else{
						ipkw.val(ipkw.val()+';'+e.getSelection().getSelectedText());
					}
				}
			});
			editor.addCommand('extractAbstract', {
				exec : function(e) {
					$("textarea[name='irpDocument.docabstract']").val(e.getSelection().getSelectedText());
				}
			});
			editor.addMenuItems({
				extract : {
					label : '提取为',
					group : 'extract',
					order : 21,
					getItems : function() {
						return {
							extract_title : CKEDITOR.TRISTATE_OFF,
							extract_keywords : CKEDITOR.TRISTATE_OFF,
							extract_abstract : CKEDITOR.TRISTATE_OFF
						};
					}
				},
				extract_title : {
					label : '标题',
					group : 'extract',
					command : 'extractTitle',
					order : 22
				},
				extract_keywords : {
					label : '标签',
					group : 'extract',
					command : 'extractKeywords',
					order : 23
				},
				extract_abstract : {
					label : '核心提示',
					group : 'extract',
					command : 'extractAbstract',
					order : 24
				}
			});
		}

		if (editor.contextMenu) {
			editor.contextMenu.addListener(function(element, selection) {
				var selText = editor.getSelection().getSelectedText();
				if (!selText || selText.length == 0) {
					return null;
				}
				return {
					extract : CKEDITOR.TRISTATE_OFF
				};
			});
		}
	}
});
