<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>
	<s:if test="irpDocument==null">
	添加知识页面
	</s:if>
	<s:else>
	修改知识页面
	</s:else>
</title>
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


//重置扩展分类
function deleteMapDiv(_obj){
	$('#'+_obj).find('select').each(function(i){
		$(this).combotree();
	});
}
///添加附件
  function tosaveAttacthed(){  
		var saveattdiv=document.createElement("div");
		saveattdiv.id="saveattdiv";
		document.body.appendChild(saveattdiv);  
		$('#saveattdiv').dialog({
	        modal:true,
		    cache:false,
	        href:'<%=rootPath %>site/to_saveattached.action',
			title:'附件管理',
			width:600,
			height:350,
			resizable:true,
			maximizable:false,	
			closable:false,
			buttons:[{
						    	text: '确定',
						    	iconCls: 'icon-ok', 
						        handler: function(){ 
							        if(addJsonFileList){  
							       		 var id=$('input:radio[name="editversions"]:checked').attr("id"); 
							       		 for(var i=0;i<addJsonFileList.length;i++){
							       			if(addJsonFileList[i].attfile==id){
							       				addJsonFileList[i].editversions=1; 
							       			}else{ 
							       				  if(addJsonFileList[i].editversions=="2"){//一种就是附件，
							       				 	 addJsonFileList[i].editversions=2;
							       				  }else{
							       				    addJsonFileList[i].editversions=0;
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
  /**
  *	判断文章相似性
  */
  function similarityDoc(){
	  var dval = $('#doctitlec').val();
	  if($.trim(dval)!=''){
		    $.ajax({
		    	type:'post',
		    	url:'<%=rootPath%>site/booldocsimilarity.action',
		    	async:false,
		    	cache:false,
		    	data:{
		    		simistr:dval
		    	},
		    	success:function(msg){  
		    		 
		    		if(parseInt(msg)>0){
		    			$.messager.confirm('提示信息','知识库里已存在'+msg+'篇相同的知识,您确定提交吗？',function(boor){
		    				if(boor==false){
		    					return false;
		    				}
		    			
		    		    });	
		    		}
			  	}
			  }); 
	  }
  }
  
  
//添加文档
	var addJsonFileList = new Array(); 
	function adddocument(){
	var dval = $('#doctitlec').val();
	var boolboor = 0;
	$.ajax({
		type:'post',
		url:'<%=rootPath%>site/booldocsimilarity.action',
    	async:false,
    	cache:false,
    	data:{
    		simistr:dval
    	},
    	success:function(msg){  
    		
    		 boolboor = msg;
	  	}
	}); 
    var editor = CKEDITOR.instances.editor;
	//验证提交文章相似度
	if(editor.document){
		$.messager.confirm('提示信息','您确定要保存知识吗？',function(r){
			if(r){
				if(boolboor>0){
					$.messager.confirm('提示信息','知识库里已存在'+boolboor+'篇相同的知识,您确定提交吗？',function(boor){
						if(boor==true){
							 //修改文档的状态值   
							$('input[name="irpDocument.doccontent"]').val(editor.document.getBody().getText());  
							$('input[name="irpDocument.dochtmlcon"]').val(editor.getData()); 
							$('input[name="irpDocument.attachedcontent"]').val(editor.getData()); 
							$('input[name="jsonFile"]').val(JSON.stringify(addJsonFileList));  //点击修改文档时候将它转成字符串发送  
							var isValid = $('#adddocumentfrm').form('validate');
							if(isValid){
								<s:if test="irpDocument==null && userGroups.size()>1">
								var groupid = $('#adddocumentfrm').find('input[name="irpDocument.groupid"]').val();
								if(groupid==''||isNaN(groupid)){
									var saveattdiv=document.createElement("div");
									saveattdiv.id="selectGrpDiv";
									saveattdiv.style.padding='5px';
									document.body.appendChild(saveattdiv);
									var strConn='';
									<s:iterator value="userGroups" status="status">
									strConn+='<p><input type="radio" <s:if test="#status.first">checked="checked"</s:if> id="grp_<s:property value="key" />" name="selectGrp" value="<s:property value="key" />" /><label for="grp_<s:property value="key" />"><s:property value="value" escapeJavaScript="true" /></label></p>';
									</s:iterator>
									$('#selectGrpDiv').html(strConn);
									$('#selectGrpDiv').dialog({
								        modal:true,
									    cache:false,
										title:'选择组织',
										resizable:true,
										maximizable:false,
										closable:true,
										buttons:[{
											text: '确定',
									    	iconCls: 'icon-ok', 
									        handler: function(){ 
									        	var selScore = $('input[name="selectGrp"]:checked').val();
												if(!isNaN(selScore)){
													$('#adddocumentfrm').find('input[name="irpDocument.groupid"]').val(selScore);
												}
												submitAddForm();
											}
								    	}], 
										onClose:function(){
							    			$('#selectGrpDiv').dialog('destroy');
										}
									});
								}
								</s:if>
								<s:else>
								submitAddForm();
								</s:else>
							}
						}
					});	
				}else{
				
					//修改文档的状态值   
					$('input[name="irpDocument.doccontent"]').val(editor.document.getBody().getText());  
					$('input[name="irpDocument.dochtmlcon"]').val(editor.getData()); 
					$('input[name="irpDocument.attachedcontent"]').val(editor.getData()); 
					$('input[name="jsonFile"]').val(JSON.stringify(addJsonFileList));  //点击修改文档时候将它转成字符串发送 
					var isValid = $('#adddocumentfrm').form('validate');
					if(isValid){
						<s:if test="irpDocument==null && userGroups.size()>1">
						var groupid = $('#adddocumentfrm').find('input[name="irpDocument.groupid"]').val();
						if(groupid==''||isNaN(groupid)){
							var saveattdiv=document.createElement("div");
							saveattdiv.id="selectGrpDiv";
							saveattdiv.style.padding='5px';
							document.body.appendChild(saveattdiv);
							var strConn='';
							<s:iterator value="userGroups" status="status">
							strConn+='<p><input type="radio" <s:if test="#status.first">checked="checked"</s:if> id="grp_<s:property value="key" />" name="selectGrp" value="<s:property value="key" />" /><label for="grp_<s:property value="key" />"><s:property value="value" escapeJavaScript="true" /></label></p>';
							</s:iterator>
							$('#selectGrpDiv').html(strConn);
							$('#selectGrpDiv').dialog({
						        modal:true,
							    cache:false,
								title:'选择组织',
								resizable:true,
								maximizable:false,
								closable:true,
								buttons:[{
									text: '确定',
							    	iconCls: 'icon-ok', 
							        handler: function(){ 
							        	var selScore = $('input[name="selectGrp"]:checked').val();
										if(!isNaN(selScore)){
											$('#adddocumentfrm').find('input[name="irpDocument.groupid"]').val(selScore);
										}
										submitAddForm();
									}
						    	}], 
								onClose:function(){
					    			$('#selectGrpDiv').dialog('destroy');
								}
							});
						}
						</s:if>
						<s:else>
						submitAddForm();
						</s:else>
					}
				}
			}
		});
	}else{
		$.messager.alert('提示信息','源码编辑下，不能提交知识...','warning');
	}   
}

function submitAddForm(){
 var dense = $("#dense").val();
 $("#denseValue").val(dense);
	$.messager.progress({text : '数据提交中...'});
	$('#adddocumentfrm').form('submit',{
		url : '<%=rootPath  %>site/adddocument.action',
		success : function(data){
			$.messager.progress('close'); 
			if(data=="1"){    
				window.opener.$('#mb').panel('refresh');  
				window.close();
			}else{
				$.messager.alert('提示信息','添加失败','warning');
			}
		} 
	});
}

	//验证
	$(function(){
	      toUpdate(<s:property value="irpDocument.docid"/>); //进入这个页面就发送请求获得jsonfile数组
			$.extend($.fn.validatebox.defaults.rules, { 
		        maxLength : {
					validator: function(value, param){   
						return value.length < param[0];  
		            },
					message : '该字符长度至多{0}位'
		        } 
	        }); 
	        initDocMapTree();
		 	<%--  $('#documentmapdiv').find('select').each(
						function(i){
							 var thisId=$(this).attr('id'); 
							 $(this).combotree({
									url: '<%=rootPath%>site/clientdocumentmaptree.action?channelid='+thisId,
									width: '200px' 
								});
						}		
					); --%>
	});
	
	
	function initDocMapTree(){
	var _obj=['One','Two','Three','Other'];
	for(var i in _obj ){
		
	
	$('#documentmapdiv'+_obj[i]).find('select').each(function(i){
		var thisId=$(this).attr('id');
		$(this).combotree({
			url: '<%=rootPath%>site/clientdocumentmaptree.action?channelid='+thisId,
			width: '300px',
			onLoadSuccess:function(){
				var node = $(this).tree('find',parseInt(thisId));
				if(node){
					$(this).tree('expand',node.target);
				}
			}
		});
		
		thisId=$(this).combotree('getValue');
		if(0==0){
		var data = thisId.split(',');
			var mycars = new Array();
		 for(var i=0;i<data.length;i++){
			var t=data[i];
			mycars[i]=t;
		} 
		$(this).combotree('setValues',mycars);
		}
	});
	}
}
	//关闭页面
	function closeWindow(){
		$.messager.confirm('提示信息','您确定要关闭当前页面吗？',function(r){
				if(r){
				   window.close();
				} 
		}); 
	}
  //创建一个数组存放当前所有附件 ///修改附件
  var _allattacheds;//存放所有附件对象从数据库中读出来的 
  function toUpdate(_docid){ 
  //发送ajax请求获得所有附件
 	  $.ajax({ 
		   type: "get", 
		   data : {"docid" : _docid},
		   async: false,
		   url: "<%=rootPath %>site/allattachedtodocument.action", 
		   success: function(msg){ 
	    	 _allattacheds=eval(msg);//转换成附件集合    
		  } 
	  });   
  } 
	//修改文档
		function updatedocument(){
		var editor = CKEDITOR.instances.editor;
		if(editor.document){
			$.messager.confirm('提示信息','您确定要修改知识吗？',function(r){
				$('input[name="irpDocument.doccontent"]').val(editor.document.getBody().getText());  
				$('input[name="irpDocument.dochtmlcon"]').val(editor.getData()); 
				$('input[name="irpDocument.attachedcontent"]').val(editor.getData()); 
			    $('input[name="jsonFile"]').val(JSON.stringify(_allattacheds));  //点击修改文档时候将它转成字符串发送
			    //获取值
			    var onlydocid = $("#onlydocid").val();
			      var dense = $("#dense").val();
			    $("#denseValue").val(dense);
				$('#adddocumentfrm').form('submit',{
					url : '<%=rootPath  %>site/updatedocument.action?flag=qiye&docid='+'<s:property value="docid"/>&onlydocid='+onlydocid,
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
							 window.opener.$('#mb').panel('refresh');  
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
					title:'附件管理',
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
		  
		 //推荐
		 function findMytag(){
			if($.trim($("#tuijian").text())=="推荐"){
				var str=$.ajax({
					 type:'post',
					 dataType: "json",
					 url:'<%=rootPath %>menu/find_mytag.action', 
					 async: false,
					 cache: false
				 }).responseText;
			 $("#tagstip").addClass("tagcss");
			 $("#tagstip").html(str);
			 $("#tuijian").text("取消");
			}else{
				 $("#tagstip").html("");
				 $("#tuijian").text("推荐");
			}
		 }
		  function addtoText(_value){
				var tag= $.trim($(_value).attr("title"));
				var keytext=$.trim($("input[name='irpDocument.dockeywords']").val());
				if(keytext==null||keytext.length==0){
					$("input[name='irpDocument.dockeywords']").val(tag);
				}else{
					$("input[name='irpDocument.dockeywords']").val(keytext+","+tag);
				}
			 }

			 var count_index=1;
			 function toMoretag(_value){
				 if(count_index==1){
					 $("#tagstip").find("li:gt(9)").show();
					 $(_value).text("取消");
					 count_index=2;
				 }
				 else{
					 $("#tagstip").html("");
					 $("#tuijian").text("推荐");
					 $(_value).text("更多");
					 count_index=1;
					 count_index=1;
				 }
			 }
			 function hidDiv(){
				 $("#tagstip").html("");
			 }
</script> 
</head> 
<body> 

<form id="adddocumentfrm" action="" method="post">  
   	<input type="hidden" name="irpDocument.channelid" value="<s:property value='id'/>" />
	<input type="hidden" name="irpDocument.attachedcontent" value="" /><%--html --%>
	<input type="hidden" name="irpDocument.docid" value="<s:property value='irpDocument.docid'/>" /> 
   	<input type="hidden" name="irpDocument.docstatus" value="<s:property value='irpDocument.docstatus'/>" /> 
   	<input type="hidden" name="irpDocument.doctype" value="<s:property value='irpDocument.doctype'/>" />  
   	<input type="hidden" name="irpDocument.doccontent" value="" /><%--文本 --%> 
   	<input type="hidden" name="irpDocument.dochtmlcon" value="" /><%--html --%>
	<input type="hidden" name="irpDocument.attachedcontent" value="" /><%--html --%>
	<input type="hidden" name="irpDocument.transformname" id="denseValue" value="<s:property value='irpDocument.transformname'/>" /><%--html --%>  
   	<input type="hidden" name="jsonFile" /><%-- 源文件名称 --%>
   	<s:if test="irpDocument==null && userGroups.size()==1">
	<s:iterator value="userGroups">
	<input type="hidden" name="irpDocument.groupid" value="<s:property value="key" />" />
	</s:iterator>
	</s:if>
	<s:elseif test="irpDocument==null && userGroups.size()>1">
	<input type="hidden" name="irpDocument.groupid" value="" />
	</s:elseif>
<div class="editor_body edit_show">
	<div class="edit_left">
    	<table cellpadding="0" cellspacing="0" border="0" width="100%">
        	<tr>
        		<td class="edit_tit"><span>标题</span></td>
        		<td><table cellpadding="0" cellspacing="0" border="0" class="edit_titInpt">
        			<tr>
        				<td width="10"><img src="<%=rootPath %>admin/images/editor/edit_01.jpg" /></td>
        				<td><input class="easyui-validatebox" validType="length[2,300]" missingMessage="请填写文档标题" type="text" name="irpDocument.doctitle" value="<s:property value='irpDocument.doctitle' />" required="true" id="doctitlec" /></td>
        				<td width="10"><img src="<%=rootPath %>admin/images/editor/edit_02.jpg" /></td>
        				<td width="20" align="center"><font color="red">*</font></td>
        			</tr>
        		</table></td>
        	</tr>
        </table>
        <div class="area1"></div>
        <table cellpadding="0" cellspacing="0" border="0" width="100%">
        	<tr>
        		<td class="edit_tit"><span>标签</span></td>
        		<td><table cellpadding="0" cellspacing="0" border="0" class="edit_tagInpt">
        			<tr>
	        			<td width="10"><img src="<%=rootPath %>admin/images/editor/edit_04.jpg" /></td>
	        			<td><input name="irpDocument.dockeywords" required="true" validType="maxLength[60]" value="<s:property value='irpDocument.dockeywords' />" class="easyui-validatebox" type="text" /></td>
	        			<td width="10"><img src="<%=rootPath %>admin/images/editor/edit_06.jpg" /></td>
	        			<td width="20" align="center"><font color="red">*</font></td>
	        			<td width="80"><a id="tuijian" href="javascript:void(0)" onclick="findMytag()">推荐</a></td>
        			</tr>
        		</table></td>
       			<td width="155" align="right"><table align="right" cellpadding="0" cellspacing="0" border="0" class="edit_tagInpt">
       				<tr>
       					<td class="edit_tit"><span>作者</span></td>
        				<td width="10"><img src="<%=rootPath %>admin/images/editor/edit_04.jpg" /></td>
        				<td><input value="<s:property value='irpDocument.docauthor' />"  class="easyui-validatebox" validType="length[2,50]" missingMessage="请填写文档作者" type="text" name="irpDocument.docauthor" required="true" /></td>
        				<td width="10"><img src="<%=rootPath %>admin/images/editor/edit_06.jpg" /></td>
        				<td width="20" align="center"><font color="red">*</font></td>
        			</tr>
        		</table></td>
		    </tr>
		    <tr>
        		<td colspan="3" style="padding-left:8px;height:24px;"><div id="tagstip" ></div></td>
        	</tr>
        </table>
        <div class="area1"></div>
        <div id="editorSpace" class="editor_edit">
        	<!--添加编辑器-->
        	<textarea id="editor" name="editor">
        	<s:property value="irpDocument.dochtmlcon"/>
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
       <s:if test="irpDocument==null">
         <div class="editor_btns">
        	<a href="javascript:void(0)" onclick="adddocument()">保存</a>
        	<a href="javascript:void(0)" onclick="closeWindow()">关闭</a>
        </div>
        </s:if><s:else>
                <div class="editor_btns"> 
        			<a href="javascript:void(0)" onclick="updatedocument()">保存</a>
        			<a href="javascript:void(0)" onclick="closeWindow()">关闭</a>
       	 		</div>
        </s:else>
    </div>
    <div class="edit_right">
    	<div class="more_tit"><span>基本属性</span></div>
        <table cellpadding="0" cellspacing="0" border="0" width="100%" class="more_options">
        	<tr><td class="xm_tit">所在栏目：</td><td><span> 
        	 <s:property value="irpChannel.chnldesc"/> 
        	</span></td></tr>
        	<tr><td class="xm_tit">
        	<s:if test="irpDocument==null">
				<a href="javascript:void(0)" onclick="tosaveAttacthed(<s:property value='docid'/>)">附件</a>
        	</s:if><s:else>
				<a href="javascript:void(0)" onclick="tosaveAttacthedtoupdate(<s:property value='docid'/>)">附件</a>
        	</s:else>

        	</td></tr>
        	 <tr><td class="xm_tit">核心提示：</td><td></td></tr>
        	 
        </table>
        <textarea  name="irpDocument.docabstract" validType="maxLength[600]"  class="easyui-validatebox" style="  margin-left:10px; border:1px solid #c7c7c7; font-size:12px; width:180px; height:100px;"><s:property value="irpDocument.docabstract"/></textarea>
        <div class="area1"></div>
		<%-- <div class="more_tit"><span>知识分类：</span><span><a href="javascript:void(0);"  onclick="deleteMapDiv()">重置</a></span> </div>
		<div  id="documentmapdiv">
			<s:iterator value="documentMap">
				<select id="<s:property value='channelid'/>" name="documentmaps" class="easyui-combotree" style="width: 200px;">
					<s:if test="docid==null || docid==''">
						<option>请选择-<s:property value="chnlname" /></option>
					</s:if>
					<s:else>
						<s:if test="getmapid(docid,channelid)==null">
							<option>请选择-<s:property value="chnlname" /></option>
						</s:if>
						<s:else>
							<option><s:property value="getmapid(docid,channelid)" /></option>
						</s:else>
					</s:else>
				</select><div class="area1"></div>
			</s:iterator>
   		</div> --%>
   		<s:if test="documentMapOne!=null && documentMapOne.size()>0">
   		<div class="more_tit"><span>知识分类一维：</span><span><a href="javascript:void(0);"  onclick="deleteMapDiv('documentmapdivOne')">重置</a></span> </div>
        		<div id="documentmapdivOne">
	        	<s:iterator value="documentMapOne">
	        	<select id="<s:property value='channelid'/>" name="documentmaps" data-options="multiple:true"style="width:200px;height:36px;">
					<s:if test="docid==null || docid==''">
						<option>请选择-<s:property value="chnlname" /></option>
					</s:if>
					<s:else>
						<s:if test="getmapid(docid,channelid)==null">
							<option>请选择-<s:property value="chnlname" /></option>
						</s:if>
						<s:else>
							<option><s:property value="getmapids(docid,channelid)" /></option>
						</s:else>
					</s:else>
				</select>
				<div class="area10"></div>
	        	</s:iterator>
        	</div>
        </s:if>
        	
        	<s:if test="documentMapTwo!=null && documentMapTwo.size()>0">
        		<div class="more_tit"><span>知识分类二维：</span><span><a href="javascript:void(0);"  onclick="deleteMapDiv('documentmapdivTwo')">重置</a></span> </div>
        		<div id="documentmapdivTwo">
	        	<s:iterator value="documentMapTwo">
	        	<select id="<s:property value='channelid'/>" name="documentmaps" data-options="multiple:true"style="width:200px;height:36px;">
					<s:if test="docid==null || docid==''">
						<option>请选择-<s:property value="chnlname" /></option>
					</s:if>
					<s:else>
						<s:if test="getmapid(docid,channelid)==null">
							<option>请选择-<s:property value="chnlname" /></option>
						</s:if>
						<s:else>
							<option><s:property value="getmapids(docid,channelid)" /></option>
						</s:else>
					</s:else>
				</select>
				<div class="area10"></div>
	        	</s:iterator>
        	</div>
        </s:if>
        		<s:if test="documentMapThree!=null && documentMapThree.size()>0">
        		<div class="more_tit"><span>知识分类三维：</span><span><a href="javascript:void(0);"  onclick="deleteMapDiv('documentmapdivThree')">重置</a></span> </div>
        		<div id="documentmapdivThree">
	        	<s:iterator value="documentMapThree">
	        	<select id="<s:property value='channelid'/>" name="documentmaps" data-options="multiple:true"style="width:200px;height:36px;">
					<s:if test="docid==null || docid==''">
						<option>请选择-<s:property value="chnlname" /></option>
					</s:if>
					<s:else>
						<s:if test="getmapid(docid,channelid)==null">
							<option>请选择-<s:property value="chnlname" /></option>
						</s:if>
						<s:else>
							<option><s:property value="getmapids(docid,channelid)" /></option>
						</s:else>
					</s:else>
				</select>
				<div class="area10"></div>
	        	</s:iterator>
        	</div>
        </s:if>
        	<s:if test="documentMapOther!=null && documentMapOther.size()>0">
        	 <div class="more_tit"><span>知识分类其他：</span><span><a href="javascript:void(0);"  onclick="deleteMapDiv('documentmapdivOther')">重置</a></span> </div>
        		<div id="documentmapdivOther">
	        	<s:iterator value="documentMapOther">
	        	<select id="<s:property value='channelid'/>" name="documentmaps" data-options="multiple:true"style="width:200px;height:36px;">
					<s:if test="docid==null || docid==''">
						<option>请选择-<s:property value="chnlname" /></option>
					</s:if>
					<s:else>
						<s:if test="getmapid(docid,channelid)==null">
							<option>请选择-<s:property value="chnlname" /></option>
						</s:if>
						<s:else>
							<option><s:property value="getmapids(docid,channelid)" /></option>
						</s:else>
					</s:else>
				</select>
				<div class="area10"></div>
	        	</s:iterator>
        	</div>
        </s:if>
        	
   		<div class="more_tit">
			<span>是否保存新版本:</span>	
		</div>
		<div id="documentmapdiv">
			<select id="onlydocid" >
				<option value="1" selected="selected">请选择--否</option>
				<option value="2" >请选择--是</option>
			</select>
		</div>
		<div class="area1"></div> 
		<div class="more_tit">
			<span>密级:</span>	
		</div>
		<div id="documentmapdiv">
			<select id="dense" style="width: 100px;" >
				<s:iterator value="denseMap" status="index">
					<option <s:if test="irpDocument.transformname==key">selected="selected"</s:if>  value='<s:property value="key"/>'><s:property value="value"/></option>
				</s:iterator>
			</select>
		</div>
   		<div class="more_tit">
			<span>链接:</span>	
		</div>
		<textarea  name="irpDocument.urladdress" validType="maxLength[600]"  class="easyui-validatebox" style="border:1px solid #c7c7c7; font-size:12px; width:190px; height:100px;"><s:property value="irpDocument.urladdress"/></textarea>
        <div class="area1"></div>
        <div class="area1"></div> 
    </div>
</div> 
	<map name="Map" id="Map">
  		<area shape="rect" coords="3,6,24,25" href="#" />
	</map> 
</form>
</body>
</html>
