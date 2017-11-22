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
			editor.addCommand('extractmaterial', {
				exec : function(e) {
					var urlPath = e.getSelection().getSelectedElement().getAttribute("src");
					var details = e.getSelection().getSelectedElement().getAttribute("alt");
					$("#materialUrlPath").val(urlPath);
					$("#details").val(details);
					var material=document.createElement("div");
					material.id="material";
					document.body.appendChild(material);
					$("#material").dialog({
						modal: true,  
						autoOpen: false,
						title: '图片素材库',
						width: 400,   
					    height: 400,   
					    closed: false,   
					    cache: false,
					    resizable: false,         
					    maximizable:false,
		     			draggable:true,
				        href:'movetomaterialchannel.action',
				        buttons:[{
					    	text: '确定', 
					    	iconCls: 'icon-ok', 
					    	handler: function(){
					    		var _node=$('#materialchannelurl').tree('getSelected');
					    		$('input[name="irpDocument.channelid"]').val(_node.id);
								
								if(_node!=null){
									 $('#adddocumentfrm').form('submit',{
						    	  		url:'extractToMaterialLibrary.action?materialUrlPath='+urlPath+'&details='+details, 
						    	  		onSubmit: function(){
											  var isValid =  $('#adddocumentfrm').form('validate');
											  if(isValid){
												  $.messager.progress({text : '图片抽取中...'});
											  }
											  return isValid;
									},
						    	  		success : function(data){
						    				$.messager.progress('close'); 
						    				if(data=="warning"){    
						    					$.messager.alert('提示信息','抽取失败','warning');
						    				}else{
						    					$.messager.alert('提示信息','成功抽取到图片素材库','warning');
						    				}
						    			} 
								    });
									$('#material').dialog('destroy'); 
					    		 }
					    	}
					    },
					    {
					    	text: '取消',
					    	iconCls: 'icon-no', 
					        handler: function(){
					        $('#material').dialog('destroy');
					     }
					    }],
					       onClose:function(){
					    	$('#material').dialog('destroy');
					    }  
					});
				},
				
			});
			editor.addCommand('extracttitleimg', {
				exec : function(e) {
					if (_allattacheds) {
						for (var i=0; i< _allattacheds.length; i++) {
							_allattacheds[i].editversions = 0;
						}
					}
					var urlPath = e.getSelection().getSelectedElement().getAttribute("src");
					$("#titleImg").val(urlPath.indexOf("http") == 0 ? urlPath : urlPath.substring(urlPath.indexOf("=") + 1));
					$("#attCount").val(_allattacheds.length);
					$.messager.alert('提示信息','已将此图片设置为封面,保存后生效','info');
				}
			});
			editor.addMenuItems({
				extract : {
					label : '提取为',
					group : 'extract',
					order : 21,
					getItems : function() {
						if(editor.getSelection().getSelectedText()&&editor.getSelection().getSelectedElement()==null){
							return {
								extract_title : CKEDITOR.TRISTATE_ON,
								extract_keywords : CKEDITOR.TRISTATE_ON,
								extract_abstract : CKEDITOR.TRISTATE_ON
							};
						}
						else if(editor.getSelection().getSelectedElement()){
							return{
								extract_material : CKEDITOR.TRISTATE_ON,
								extract_titleimg : CKEDITOR.TRISTATE_ON
							};
						}
						else {
							return{
								extract_title : CKEDITOR.TRISTATE_DISABLED,
								extract_keywords : CKEDITOR.TRISTATE_DISABLED,
								extract_abstract : CKEDITOR.TRISTATE_DISABLED,
								extract_material : CKEDITOR.TRISTATE_DISABLED,
								extract_titleimg : CKEDITOR.TRISTATE_DISABLED
							};
						}
					}
				},
				extract_title : {
					label : '标题',
					group : 'extract',
					command : 'extractTitle',
					order : 22
				},
				extract_keywords : {
					label : '关键字',
					group : 'extract',
					command : 'extractKeywords',
					order : 23
				},
				extract_abstract : {
					label : '摘要',
					group : 'extract',
					command : 'extractAbstract',
					order : 24
				},
				extract_material : {
					label : '图片素材库',
					group : 'extract',
					command : 'extractmaterial',
					order : 25
				},
				extract_titleimg : {
					label : '封面图片',
					group : 'extract',
					command : 'extracttitleimg',
					order : 26
				}
			});
		}

		if (editor.contextMenu) {
			editor.contextMenu.addListener(function(element, selection) {
				if(!element ){ //如果在ckeditor编辑框内元素之外的地方按右键，不会反回任何信息（即不显示该菜单项）
                    return null;
				}
				return {
					extract : CKEDITOR.TRISTATE_ON
				};
			});
		}
	}
});
