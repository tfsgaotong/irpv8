<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>数据修改</title>
<style type="text/css">
.tagcss{
width: 800px;
z-index: 1000px;
}  
.ellipsis{
overflow: hidden;
text-overflow: ellipsis;
white-space: nowrap;
} 
#tagstip{
width: 900px;
}
</style>
<link rel="stylesheet" type="text/css" href="<%=rootPath %>admin/css/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>admin/css/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>admin/css/css_body.css" />

<script type="text/javascript" src="<%=rootPath %>admin/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>admin/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>admin/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath %>editor/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<%=rootPath %>editor/applet/ckwordup.js"></script>
<script type="text/javascript" src="<%=rootPath %>admin/js/json2.js"></script> 
<script type="text/javascript">
function deleteMapDiv(){
	var selects=$('#documentmapdiv').find('select').each(
			function(i){
				 var thisId=$(this).attr('id');
				 $(this).combotree('clear');
			}		
		);
}

	
	function closeWindow(){
		$.messager.confirm('提示信息','您确定要关闭当前页面吗？',function(r){
				if(r){
				   window.close();
				} 
		}); 
	}
 
	//修改文档
		function updatedocument(){
		var editor = CKEDITOR.instances.editor;
		if(editor.document){
			$.messager.confirm('提示信息','您确定要修改数据吗？',function(r){
				$('input[name="doccontent"]').val(editor.getData()); 
				$('#adddocumentfrm').form('submit',{
					url : '<%=rootPath  %>radar/radar_checktit.action',
					onSubmit: function(){
							  var isValid =  $('#adddocumentfrm').form('validate');
							  if(isValid){
							     $.messager.progress({text : '数据提交中...'});
							  }
							  return isValid;
					},
					success : function(data){
					    $.messager.progress('close'); 

						if(data=="1"){                     
							 window.open("<%=rootPath %>/admin/index.action?type=radar");  
							 window.close();
						}else{
							$.messager.alert('提示信息','修改失败','warning');
						}
					} 
				});
		});
		}else{
			$.messager.alert('提示信息','源码编辑下，不能提交知识...','warning');
		}
		} 
		  function tosaveAttacthedtoupdate(_docid){  
				var saveattdiv=document.createElement("div");
				saveattdiv.id="saveattdiv";
				document.body.appendChild(saveattdiv);  
				$('#saveattdiv').dialog({
			        modal:true,
				    cache:false,
			        href:'<%=rootPath %>site/to_updateattached.action?docid='+_docid,
					title:'附件管理11',
					width:600,
					height:350,
					resizable:true,
					maximizable:false, 
					closable:false,
					 buttons:[{
								    	text: '确定',
								    	iconCls: 'icon-ok', 
								        handler: function(){
								         if(_allattacheds){  
									       		 var id=$('input:radio[name="editversions"]:checked').attr("id"); 
									       		 for(var i=0;i<_allattacheds.length;i++){
									       			if(_allattacheds[i].attfile==id){
									       				_allattacheds[i].editversions=1; 
									       			}else{ 
									       				  if(_allattacheds[i].editversions=="2"){//一种就是附件，
									       				  }else{
									       				    _allattacheds[i].editversions=0;
									       				  }
									       			}
									     		  }
									     	   } 
								        $('#saveattdiv').dialog('destroy');
								     }
								    }],
					onClose:function(){
					    	$('#saveattdiv').dialog('destroy');
					}
				});   
		  } 
			 function hidDiv(){
				 $("#tagstip").html("");
			 }
</script> 
</head> 
<body> 

<form id="adddocumentfrm" action="" method="post">  
<input type="hidden" name="doccontent" value="" />
  <s:iterator value="irpRandars" status="listStat" var="irp">
   	<input type="hidden" name="sid" value="<s:property value="sid" />" /><%--html --%>
<div class="editor_body edit_show">
	<div class="edit_left">
    	<table cellpadding="0" cellspacing="0" border="0" width="100%">
        	<tr>
        		<td class="edit_tit"><span>标题</span></td>
        		<td><table cellpadding="0" cellspacing="0" border="0" class="edit_titInpt">
        			<tr>
        				<td width="10"><img src="<%=rootPath %>admin/images/editor/edit_01.jpg" /></td>
        				<td><input class="easyui-validatebox" validType="length[2,300]" missingMessage="请填写文档标题" type="text" name="stitle" value="<s:property value='doctitle' />" required="true" id="doctitlec" /></td>
        				<td width="10"><img src="<%=rootPath %>admin/images/editor/edit_02.jpg" /></td>
        				<td width="20" align="center"><font color="red">*</font></td>
        			</tr>
        		</table></td>
        	</tr>
        </table>
        <div style="margin-left:48px; margin-top:10px;">
			  状态: <s:if test="satus==null||satus==1"><input name="status" type="radio" value="1" checked="checked"/>新稿<input name="status" type="radio" value="2"/>已导入</s:if>
			   <s:else><input name="status" type="radio" value="1" />新稿<input name="status" type="radio" value="2" checked="checked"/>已导入</s:else>
		</div> 
        <div class="area1"></div>
        <div id="editorSpace" class="editor_edit">
        	<!--添加编辑器-->
        	<textarea id="editor" name="editor">
        	<s:property value="doccontent"/>
        	 </textarea>
        	<script>
			CKEDITOR.replace(  'editor',{
				filebrowserUploadUrl: '<%=rootPath%>file/ck_upload.action',
				uiColor: '#EAF2FF'
			});
			//构建图片上传地址
            var sUrl = '<%=rootPath%>file/ck_word_upload.action;jsessionid=<%=session.getId()%>';
            //构建应用名称，如本系统名称为‘wordimg’，如果为根应用，请写为空字符串''        
            var appName = '<%=rootPath%>';
            //创建WordImageUploader对象
            var uploader = new CK_WordImageUploader(sUrl, appName);
            //当ckeditor内容改变时，自动检测并上传内容中的本地图片
            CKEDITOR.instances.editor.on('paste', function(event) {
            	uploader.uploadWordImagesFromCKEditor(CKEDITOR.instances.editor, event);
            });
			</script>
        </div>
        <div class="area1"></div>
                <div class="editor_btns"> 
        			<a href="javascript:void(0)" onclick="updatedocument()">保存</a>
        			<a href="javascript:void(0)" onclick="closeWindow()">关闭</a>
       	 		</div>
    </div>
   
</div> 
	<map name="Map" id="Map">
  		<area shape="rect" coords="3,6,24,25" href="#" />
	</map> 
	 </s:iterator>
</form>
</body>
</html>
