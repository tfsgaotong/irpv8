<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>
<s:if test="irpDocument==null">提交知识</s:if>
<s:else>
<s:property value="irpDocument.doctitle"/>
</s:else> 
</title>
<link rel="stylesheet" href="<%=rootPath %>client/css/css.css" type="text/css"/>
<link rel="stylesheet" href="<%=rootPath %>client/images/commit_subject.css" type="text/css"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/easyui.css" />
<link  href="<%=rootPath %>client/css/oacss.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath%>/client/js/skins/iblue.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>client/css/icon.css" />
 <link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/commit_subject2.css" /> 
 <link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css"/>
<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css"/> 
<script type="text/javascript" src="<%=rootPath %>client/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath %>editor/ckeditor/ckeditor.js"></script> 
<script type="text/javascript" src="<%=rootPath %>editor/applet/ckwordup.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/json2.js"></script>
 <script type="text/javascript"> 
 var m_path="<%=rootPath %>";
 </script>
<style type="text/css">
.STYLE1 {
	color: #0000FF;
	font-weight: bold;
	font-size:18px;
}
.STYLE1 a:hover{
	color: #0000FF;
	font-weight: bold;
	font-size:18px;
}   
</style>
<style type="text/css">
body{behavior:url(hover.htc);}
.searchSec .radios span {
margin-top: 0px;
}	
</style> 

  </head>
<body style="background: url()"> 
<script type="text/javascript">
function deleteMapDiv(){
	var selects=$('#documentmapdiv').find('select').each(
			function(i){
				 var thisId=$(this).attr('id');
				 $(this).combotree('clear');
			}		
		);
}
 function  initAlert(){
	 var allrightchannel=$.ajax({
		 type:'post',
		 dataType: "json",
		 url:'<%=rootPath%>site/rightchanneltotougao.action',
		 async: false,
		 cache: false
	 }).responseText; 
	 if(allrightchannel==null|| allrightchannel==""||allrightchannel=="[]"){
		 $.dialog.alert('目前您没有栏目权限',function(){
		 	 window.close();
	   	})  
	 }else{
		 var allChannelIds = "<s:property value="allChannelIds" />";
		 var channelid = "<s:property value="irpDocument.channelid" />";
		 var checkChannelId = "<s:property value='id'/>";
		 var arrChannelIds = allChannelIds.split(',');
		 $('#rightchannel').combotree({
				url: '<%=rootPath%>site/rightchanneltotougao.action',
				required: 'true',
				panelWidth: 200,
				onLoadSuccess:function(node, data){
					for(var i=0;i<data.length;i++){
						var treeId = data[i].id;
						if(exist(arrChannelIds,treeId)){
							var tree = $('#rightchannel').combotree('tree');
							var node = tree.tree('find',treeId);
							tree.tree('expand',node.target);
							if(channelid==treeId){
								tree.tree('select',node.target);
 								$('#rightchannel').combo('setText','<s:property value="chnldesc" escapeHtml="false" />');
								$('#rightchannel').combo('setValue',channelid);
							}else if(checkChannelId==treeId){
								tree.tree('select',node.target);
 								$('#rightchannel').combo('setText','<s:property value="chnldesc" escapeHtml="false" />');
								$('#rightchannel').combo('setValue',treeId);
							}
						}
					}
				}
		 }); 
	 }
 }
 
 function exist(arr,value){
	 if(arr){
		 for(var i=0;i<arr.length;i++){
			 if(arr[i]==value){
				 return true;
			 }
		 }
	 }
	 return false;
 }
 
 ///添加附件
  function tosaveAttacthed(){  
 	 var str=$.ajax({
			 type:'post',
			 dataType: "json",
			 url:'<%=rootPath %>site/client_to_save_attached.action?isqusertionordoc=2', 
			 async: false,
    		 cache: false
		 }).responseText;  
	   $.dialog({
				title:'附件管理',
				max:false,
				min:false,
				lock:true,
				resize: false,  
				content:str,
				cancelVal:'确定', 
				cancel:function(){
 				 if(addJsonFileList){  
		       		 var id=$('input:radio[name="editversions"]:checked').attr("id"); 
		       		 for(var i=0;i<addJsonFileList.length;i++){ 
		       			if(addJsonFileList[i].attfile==id){
		       				addJsonFileList[i].editversions=1; 
		       			}else{ 
		       				  if(addJsonFileList[i].editversions=="2"){//一种就是附件，
		       				  }else{
		       				    addJsonFileList[i].editversions=0;
		       				  }
		       			}
		     		  }
		     	   }  
				}  
		  });   
  }  
   //创建一个数组存放当前所有附件 ///修改附件
  var _allattacheds=null;//存放所有附件对象从数据库中读出来的 
  function toUpdate(_docid){ 
  //发送ajax请求获得所有附件
 	  $.ajax({ 
		   type: "POST", 
		   url: "<%=rootPath %>site/allattachedtodocument.action?docid="+_docid, 
		   success: function(msg){   
	    	 _allattacheds=eval(msg);//转换成附件集合    
		  } 
	  });   
  }  
    function toupdateAttacthed(_docid){   
 	 	 var str=$.ajax({
			 type:'post',
			 dataType: "json",
			 url:'<%=rootPath %>site/client_to_update_attached.action?docid='+_docid, 
			 async: false,
    		 cache: false
		 }).responseText;  
	   $.dialog({
				title:'附件管理',
				max:false,
				min:false,
				lock:true,
				resize: false, 
				content:str,
				cancelVal:'确定', 
				cancel:function(){
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
				}  
		  });  
  }  

var addJsonFileList = new Array();

//添加文档
function adddocument(_status){
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
	if(editor.document){
		$.dialog.confirm('您确定要保存这个知识吗？',function(){
			if(boolboor>0){
				$.messager.confirm('提示信息','知识库里已存在'+boolboor+'篇相同的知识,您确定提交吗？',function(boor){
					if(boor==true){
						if(_status){
				    		$('input[name="irpDocument.docstatus"]').val(_status);
					    }
						$('input[name="irpDocument.doccontent"]').val(editor.document.getBody().getText());  
						$('input[name="irpDocument.dochtmlcon"]').val(editor.getData()); 
						$('input[name="irpDocument.attachedcontent"]').val(editor.getData());
						$('input[name="irpDocument.doctype"]').val(<s:property value="doctype" />);
						var addJsonFileListstring = JSON.stringify(addJsonFileList);
						var attachedsarraylist ='<s:property value="irpAttachedarraylist" />';
						if(attachedsarraylist!=""){
							addJsonFileListstring = attachedsarraylist;
						}
						$('input[name="jsonFile"]').val(addJsonFileListstring);  //点击修改文档时候将它转成字符串发送
						
						var isValid = $('#adddocumentfrm').form('validate');
						if(isValid){
							<s:if test="irpDocument==null && userGroups.size()>1">
							var groupid = $('#adddocumentfrm').find('input[name="irpDocument.groupid"]').val();
							if(groupid==''||isNaN(groupid)){
								var strConn='';
								<s:iterator value="userGroups" status="status">
								strConn+='<p><input type="radio" <s:if test="#status.first">checked="checked"</s:if> id="grp_<s:property value="key" />" name="selectGrp" value="<s:property value="key" />" /><label for="grp_<s:property value="key" />"><s:property value="value" escapeJavaScript="true" /></label></p>';
								</s:iterator>
								$.dialog({
									title:'选择组织',
									content:strConn,
									max: false,
								    min: false,
								    okVal:'确定',
									ok: function(){
										var selScore = $('input[name="selectGrp"]:checked').val();
										if(!isNaN(selScore)){
											$('#adddocumentfrm').find('input[name="irpDocument.groupid"]').val(selScore);
										}
										submitAddForm();
									},
								    cancelVal: '取消',
								    cancel:true,
								    lock: true
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
				if(_status){
		    		$('input[name="irpDocument.docstatus"]').val(_status);
			    }
				$('input[name="irpDocument.doccontent"]').val(editor.document.getBody().getText());  
				$('input[name="irpDocument.dochtmlcon"]').val(editor.getData()); 
				$('input[name="irpDocument.attachedcontent"]').val(editor.getData());
				$('input[name="irpDocument.doctype"]').val(<s:property value="doctype" />);
				var addJsonFileListstring = JSON.stringify(addJsonFileList);
				var attachedsarraylist ='<s:property value="irpAttachedarraylist" />';
				if(attachedsarraylist!=""){
					addJsonFileListstring = attachedsarraylist;
				}
				$('input[name="jsonFile"]').val(addJsonFileListstring);  //点击修改文档时候将它转成字符串发送
				
				var isValid = $('#adddocumentfrm').form('validate');
				if(isValid){
					<s:if test="irpDocument==null && userGroups.size()>1">
					var groupid = $('#adddocumentfrm').find('input[name="irpDocument.groupid"]').val();
					if(groupid==''||isNaN(groupid)){
						var strConn='';
						<s:iterator value="userGroups" status="status">
						strConn+='<p><input type="radio" <s:if test="#status.first">checked="checked"</s:if> id="grp_<s:property value="key" />" name="selectGrp" value="<s:property value="key" />" /><label for="grp_<s:property value="key" />"><s:property value="value" escapeJavaScript="true" /></label></p>';
						</s:iterator>
						$.dialog({
							title:'选择组织',
							content:strConn,
							max: false,
						    min: false,
						    okVal:'确定',
							ok: function(){
								var selScore = $('input[name="selectGrp"]:checked').val();
								if(!isNaN(selScore)){
									$('#adddocumentfrm').find('input[name="irpDocument.groupid"]').val(selScore);
								}
								submitAddForm();
							},
						    cancelVal: '取消',
						    cancel:true,
						    lock: true
						});
					}
					</s:if>
					<s:else>
					submitAddForm();
					</s:else>
				}
			}
		},function(){});
	}else{
		$.messager.alert('提示信息','源码编辑下，不能提交知识...','warning');
	}
}

function submitAddForm(){
	$.messager.progress({text : '数据提交中...'});
	$('#adddocumentfrm').form('submit',{
		url : '<%=rootPath  %>site/addsubject.action?flag=qiye',
		success : function(data){
		    $.messager.progress('close'); 
			if(data=="1"){ 
				if(window.opener!=null){
					window.opener.location.reload(true);
				}
				window.close(); 
			}else{ 
				$.dialog.alert("提交知识失败");
			}
		}
	});
}

	//修改文档并提交为新历史版本
	function updatedocument(_status , _type){ 
		  var editor = CKEDITOR.instances.editor;
		if(editor.document){
			$.dialog.confirm('您确定修改知识吗？',function(){
				$('input[name="irpDocument.docstatus"]').val(_status); //修改文档的状态值  
				$('input[name="irpDocument.doctype"]').val(_type); //修改文档类型，10为html 20 为文本 
				$('input[name="irpDocument.doccontent"]').val(editor.document.getBody().getText());  
				$('input[name="irpDocument.dochtmlcon"]').val(editor.getData()); 
			    $('input[name="jsonFile"]').val(JSON.stringify(_allattacheds));  //点击修改文档时候将它转成字符串发送
			    //huo qu zhi
			    var onlydocid = $('#onlydocid').val();
			    
				$('#adddocumentfrm').form('submit',{
					url : '<%=rootPath  %>site/updatedocument.action?flag=qiye&docid='+'<s:property value="docid"/>&onlydocid='+onlydocid,
					onSubmit: function(){
							  var isValid =  $('#adddocumentfrm').form('validate');
							  var docvalidtime=$('input[name="irpDocument.docvalidtime"]').val();  
				    	   	  var now = new Date();
				    	   	  var nowtime = new Date(now.getFullYear(), now.getMonth(), now.getDate());
				    	   	  var doctime=new Date(docvalidtime); 
			    	   	      if(nowtime>=doctime){ 
			    	   	         $.messager.alert("提示信息","有效期必须大于当前时间","warning");
			    	   	          return false;
			    	   	      }
							  if(isValid){
							     $.messager.progress({text : '数据提交中...'});
							  }
							  return isValid;
					},
					success : function(data){
					    $.messager.progress('close'); 
						if(data=="1"){                                                    
							 $.dialog.tips('修改知识成功',0.5,'32X32/succ.png',function(){ 
							 	if(window.opener!=null){
							 		window.opener.location.reload(true);
							 	}
							 	 window.close();
							});  
						}else {
							
							$.dialog.alert("修改知识失败");
						}
					} 
				});
				},function(){});
		}else{
			$.messager.alert('提示信息','源码编辑下，不能提交知识...','warning');
		}
	}     
	
	var _selectchannels=null;
	//查看他有权限的栏目
	function userrightchannel(){
	//进入页面发送ajax请求，然后得到导航栏（二级栏目列表）
	 	$.ajax({
					type:'post',
					url:'<%=rootPath%>site/userrightchannel.action',
					success: function(msg){   
					  if(msg!=""){
						  _selectchannels=eval(msg);//转换成附件集合   
							var str='<select name="irpDocument.channelid">';   
								if(_selectchannels!=null){
									var len = _selectchannels.length;  
									for(var i=0 ;i<len ; i++){  
										   str+='<option  value="'+_selectchannels[i].channelid+'">'+_selectchannels[i].chnldesc+'</option>';
			        				  }
								} 
							str+='</select>'; 
							$('#channelselect').html(str);
						 } else{  
							  $.dialog.alert('目前您没有栏目权限',function(){
								 	 window.close();
							   })  
						}
					}
				});
	  
	}
	//验证
	$(function(){
			var thisid="<s:property value='id'/>";
			//查看有权限的栏目  
	        var _toU='<s:property value='toUpdate'/>';
			initAlert(); 
			 
			var toupdateDocid=<s:property value='irpDocument.docid'/>+"";
			if(toupdateDocid!=''){
				toUpdate(<s:property value='irpDocument.docid'/>);
			} 
			$.extend($.fn.validatebox.defaults.rules, { 
		        maxLength : {
					validator: function(value, param){   
						return value.length < param[0];  
		            },
					message : '该字符长度至多{0}位'
		        } 
	        }); 
			
		 	var selects=$('#documentmapdiv').find('select').each(
					function(i){
						 var thisId=$(this).attr('id'); 
						 $(this).combotree({
								url: '<%=rootPath%>site/clientdocumentmaptree.action?channelid='+thisId,
								width: '200px' ,
								onLoadSuccess:function(){
									var node = $(this).tree('find',parseInt(thisId));
									if(node){
										$(this).tree('expand',node.target);
									}
								}
							});
					}		
				);
	});
	//关闭页面
	function closeWindow(){ 
		$.dialog.confirm('您确定要关闭当前页面吗？',function(){
		   window.close();
		},function(){},parent);
	} 
	  function previewDocument(){ 
		  var editor = CKEDITOR.instances.editor;
		  if(editor.document){
				 $('input[name="irpDocument.doccontent"]').val(editor.document.getBody().getText());  
				 $('input[name="irpDocument.dochtmlcon"]').val(editor.getData()); 
				 $('input[name="irpDocument.attachedcontent"]').val(editor.getData()); 
				 $('input[name="jsonFile"]').val(JSON.stringify(addJsonFileList));  //点击修改文档时候将它转成字符串发送 
				 $('#adddocumentfrm').attr('target','_blank');
				 $('#adddocumentfrm').attr('action','<%=rootPath  %>site/previewDocument.action');
				$('#adddocumentfrm').submit();
			}else{
				$.messager.alert('提示信息','源码编辑下，不能预览知识...','warning');
			}
		}
	  
	  //创建专题权限
	$(function(){
		 $.ajax({
			url: '<%=rootPath%>site/subclientrightadddoc.action',
			type:'post', 
		    async: false ,
		    success:function(mes){
		    	if(mes=="double" || mes =="create"){
		    		$('#createqiyesub').show();
	    		}
		    }
		}) ;
	});
	
/**
*添加基本模版
*/
function addTemplate(){
	 var editor = CKEDITOR.instances.editor;
var result = $.ajax({
					url:"<%=rootPath%>set/quotedittemplateknow.action",
					cache:false,
					async:false
				}).responseText;
		$.dialog({
  		content:result,
  		width:800,
  		height:600,
  		title:'引用模版',
  		min:false,
  		max:false,
	    lock:true,
	    ok:function(){
	    	var temhval = $('#temphiddenval').val();
	    	if(temhval!=''){
	    		$.ajax({
	    			type:'post',
	    			url:'<%=rootPath%>term/findqtemcate.action',
	    			async:false,
	    			cache:false,
	    			data:{
	    				tid:temhval
	    			},
	    			success:function(content){
	    				if(content!=''){
	    				
	    					editor.document.getBody().setHtml(editor.document.getBody().getHtml()+"<br/>"+content);
	    				}
	    			}
	    		});
	    		
	    	}
	    },
	    okVal:'确认',
//	    cancelVal: '取消',
//	    cancel: true,
	    padding: 0
  	});
		
}
/**
* 添加内链
*/
function insideLink(){

	var value="";
	var dialogdiv=document.createElement("div");
 	dialogdiv.id="quotedivs";
 	document.body.appendChild(dialogdiv);
 	$('#quotedivs').dialog({
 		modal:true,
 		cache:false,
 		href:'<%=rootPath%>client/document/quote_knowledgeknow.jsp',
 		height:600,
 		width:800,
 		title:'添加内链',
 		resizable:true,
 		buttons:[{
 			text:'提交',
 			iconCls: 'icon-ok',
 			handler:function(){
	 			var radval = $('input[name="chnlDocLinkscatach"]:checked').val();
	 			if(radval!='' && radval!=null){
			 			var eidor = CKEDITOR.instances.editor;
					    var mySelection = eidor.getSelection();
					    var data = mySelection.getNative();
					    var paths = "<%=rootPath %>";
					    var findtypeidkqt = $("#types").val();
					    if(findtypeidkqt==0){
						    	 value ="<a style=\"color:#9393FF;\" href=\""+paths+"site/showdocumentinfo.action?refrechstatus=1&docid="+radval+"\" target=\"_blank\" >"+data+"</a>";						    
					    }if(findtypeidkqt==1){
					    	 value ="<a style=\"color:#9393FF;\" href=\""+paths+"question/questionDetail.action?refrechstatus=1&questionid="+radval+"\" target=\"_blank\" >"+data+"</a>";
					    }if(findtypeidkqt==2){
					    	 value ="<a style=\"color:#9393FF;\" href=\""+paths+"term/termlinklist.action?refrechstatus=1&termid="+radval+"\" target=\"_blank\" >"+data+"</a>";
					    }
					    
					    if ( eidor.mode == 'wysiwyg' )
					    {
							eidor.insertHtml(value);
					    }else{
					    	return false;
					    }
	 			}
		  $('#quotedivs').dialog('destroy');
 			}
 		},{
 			text:'取消',
 			iconCls:'icon-cancel',
 			handler:function(){
 				$('#quotedivs').dialog('destroy');
 			}
 		}],
 		onClose:function(){
 			$('#quotedivs').dialog('destroy');
 		}
 		});
	
}

</script>

  <jsp:include page="../../view/include/client_head.jsp" />
		   <section class="mainBox">
			<nav class="privateNav">
			</nav>
			</section>
		   
<div class="bg01">
<!--头部菜单-->

<!--头部菜单end-->
<form id="adddocumentfrm" action="" method="post">  
	<input type="hidden" name="irpDocument.siteid" value="1" />  
	<input type="hidden" name="irpDocument.docid" value="<s:property value='irpDocument.docid'/>" />  
   	<input type="hidden" name="irpDocument.docstatus" value="<s:property value='irpDocument.docstatus'/>" />  
   	<input type="hidden" name="irpDocument.doccontent" value="" /><%--文本 --%> 
   	<input type="hidden" name="irpDocument.dochtmlcon" value="" /><%--html --%>
   	<input type="hidden" name="irpDocument.attachedcontent" value="" /><%--html --%>
   	<input type="hidden" name="irpDocument.doctype" value="" />
  	<input type="hidden" name="jsonFile" /><%-- 源文件名称 --%>
	<s:if test="irpDocument==null && userGroups.size()==1">
	<s:iterator value="userGroups">
	<input type="hidden" name="irpDocument.groupid" value="<s:property value="key" />" />
	</s:iterator>
	</s:if>
	<s:elseif test="irpDocument==null && userGroups.size()>1">
	<input type="hidden" name="irpDocument.groupid" value="" />
	</s:elseif>
<div class="editor_body edit_hidden">
	<div class="edit_left">
    	<table cellpadding="0" cellspacing="0" border="0" width="100%">
        	<tr>
        		<td class="edit_tit" valign="top"><div class="edt_tt"></div></td>
        		<td><table cellpadding="0" cellspacing="0" border="0" class="edit_titInpt">
        			<tr>
        				<td height="15" colspan="5"></td>
        			</tr>
        			<tr>
        				<td width="5"><div class="edt_l_1"></div></td>
        				<td width="576"><input id="doctitlec" class="easyui-validatebox" validType="length[2,300]" missingMessage="请填写文档标题" type="text" name="irpDocument.doctitle" required="true" value="<s:property value='irpDocument.doctitle'/><s:property value="irpQuestionofquestionid.title" />"/></td>
        				<td width="5"><div class="edt_r_1"></div></td>
        				<td align="center"><font color="red">*</font></td>
        			</tr>
        		</table></td>
        		<td width="50" align="center"></td>
        			<td class="edit_tit" align="right"><span class="addto">创建到</span></td>
        		<td width="100"><table cellpadding="0" cellspacing="0" border="0" >
        			<tr>
        				<td height="15"></td>
        			</tr>
        			<tr>
        				<td width="10"><img src="<%=rootPath%>client/images/editor/edit_04.jpg" /></td>
        				<td class="edit_titSel" id="channelselect"> 
        	 				<select id="rightchannel" name="irpDocument.channelid" data-options="cascadeCheck:false"  class="easyui-combotree" style="width: 100px;"></select> 
        				</td>
        				<td width="10"><img src="<%=rootPath%>client/images/editor/edit_06.jpg" /></td>
        			</tr>
        		</table></td>
        		<td width="40" align="center"></td>
        	</tr>
       	</table>
        <div class="area1"></div>
        <table cellpadding="0" cellspacing="0" border="0" width="100%">
        	<tr>
        		<td class="edit_tit"><span>标签</span></td>
        		<td><table cellpadding="0" cellspacing="0" border="0" class="edit_tagInpt">
        			<tr>
        				<td width="10"><img src="<%=rootPath%>client/images/editor/edit_04.jpg" /></td>
        				<td width="733"><input style="line-height:31px;" required="true" validType="maxLength[60]" value="<s:property value='irpDocument.dockeywords'/>" name="irpDocument.dockeywords" class="easyui-validatebox" type="text" /></td>
        				<td width="10"><img src="<%=rootPath%>client/images/editor/edit_06.jpg" /></td>
						<td align="center"><font color="red">*</font></td>
        			</tr>
        		</table></td>
        		<td class="autoCheck">
					&nbsp;&nbsp;&nbsp;&nbsp;
				<s:if test="toUpdate=='true'">
					<a href="javascript:void(0)" onclick="toupdateAttacthed(<s:property value='irpDocument.docid'/>)">附件</a>
				</s:if>
				<s:else>
					<a href="javascript:void(0)" onclick="tosaveAttacthed()">附件</a>
				</s:else>
				</td>
				<td align="right" class="addToweibo"> </td><td width="20" align="center"></td></tr>
        </table>
          <div class="area1"></div>
          <table cellpadding="0" cellspacing="0" border="0" width="100%">
        	<tr>
        	<td class="edit_tit">
        	<span>核心<br />提示</span></td>
			<td>
			 <textarea style="overflow:auto;" name="irpDocument.docabstract" validType="maxLength[600]"  class="easyui-validatebox xm_zhaiyao" required="true" ><s:property value="irpDocument.docabstract"/></textarea> 
			</td>
			<td width="28" align="center"><font color="red">*</font></td>
			</tr>
        </table>
         <div class="area1"></div>
        <div class="editor_edit">
        	<!--添加编辑器-->
        	<textarea id="editor" name="editor"  style="resize:none;" ><s:property value="irpDocument.dochtmlcon"/><s:property value="irpQuestionofquestionid.htmlcontent" /><br/><s:property value="irpQuestionofparentid.htmlcontent" /></textarea>
        	<script>
        		var cssUrl = $('#skin').attr('href');
        		var sColor;
        		if(cssUrl!=null && cssUrl!=""){
        			sColor  = cssUrl.substring(cssUrl.lastIndexOf('/oapf-')+6,cssUrl.lastIndexOf('.css'));
        		}
        		if(sColor=="black"){
	    			sColor='#EEEEEE';
				}else if(sColor=="orange"){
					sColor='#F7EDD7';
				}else if(sColor=="green"){
					sColor='#EBF4DA';
				}else if(sColor=="blue"){
					sColor='#ECFAFB';
				}else{
					sColor='#ECFAFB';
				}
				CKEDITOR.replace('editor',{
					filebrowserUploadUrl: '<%=rootPath%>file/ck_upload.action',
					uiColor: sColor
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
	            /*
	             * 新建专题
	             */
	            function createknowSubject(){
	            	 var str=$.ajax({
	            		 type:'post',
	            		 url:'<%=rootPath %>site/addsubjectnow.action', 
	            		 async: false,
	            		 cache: false
	            	 }).responseText;
	            	 $.dialog({
	            			title:'创建专题',
	            			content: str ,
	            			max: false,
	            		    min: false,
	            			ok: function(){
	            				var validate = $('#createsubjectform').form('validate');
	            				$('#createsubjectform').form('submit',{
	            	    			url : "<%=rootPath %>site/begincreateqiyesubject.action?id=<s:property value='id'/>",
	            	    			success:function(data){
	            	    				if(data=='1'){
	            	    					$.dialog.tips('创建成功',1,'32X32/succ.png',function(){
	            	    						window.location.reload(true);
	            	    					});
	            	    				}else if(data=='2'){
	            	    					$.dialog.tips('专题名称已存在',1,'32X32/fail.png');
	            	    				}else if(data=='3'){
	            	    					$.dialog.tips('企业专题目录丢失,请与管理员联系',1,'32X32/fail.png');
	            	    				}else{
	            	    					$.dialog.tips('创建失败',1,'32X32/fail.png');
	            	    				}
	            	    			}
	            	    		});
	            				return validate;
	            		    },
	            		    okVal:'创建',
	            		    cancelVal: '取消',
	            		    cancel:true,
	            		    lock: true,
	            		    padding: 0
	            		});
	            	  $('#createsubjectform').find("input[name='irpChannel.chnlname']").validatebox();
	            }
	            function deleteSubDiv(){
	        		var checkbox = $('#documentsubdiv').find('input').each(
	        			function(i){
	        				$(this).removeAttr('checked');
	        			}	
	        		);
	        	}
	            function moresubject(){
	            	var status = $('#hidesubdiv').css("display");
	            	if(status=="block"){
	            		$('#hidesubdiv').hide(888);
	            	}else{
	            		$('#hidesubdiv').show(888);	
	            	}
	            }
 	  		</script>
        </div>   
            <div class="more_tit">
            	<span>知识分类 :</span>
            	<a style="margin-left:8px;font-size: 16px;" href="javascript:void(0);"  onclick="deleteMapDiv()">重置</a>
            </div>
			<div id="documentmapdiv">
				<s:iterator value="documentMap">
					<select id="<s:property value='channelid'/>" name="documentmaps" class="easyui-combotree" style="width: 150px;">
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
					</select>
				</s:iterator> 
			</div>
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
			<s:if test="irpChannels!=null">
          	<div class="more_tit">
          		<span> 引用到专题 : </span>
          		<span><a href="javascript:void(0);"  onclick="deleteSubDiv()">重置</a></span>
          	</div>
          	<style>
          		#documentsubdiv{
				    display:inline-block;
          		}
          		#documentsubdiv div{
          			float:left;
          			margin:3px 8px;
          		}
          		#documentsubdiv label{
          			cursor:pointer;
          		}
          	</style>
			<div id="documentsubdiv">
			<s:iterator value="irpChannels" status="status">
				<div>
					<input <s:if test="selectstatus==1">checked="checked"</s:if> id="check<s:property value='channelid' />" value="<s:property value='channelid' />" name="subjectselect" type="checkbox" />
					<label for="check<s:property value='channelid' />" title="<s:property value='chnlname'/>"><s:property value="chnlname"/></label>
				</div>
			</s:iterator>
			
			</div>
			</s:if>
			<div class="more_tit">
				<span>知识级别：</span>
				<table cellpadding="0" cellspacing="0" border="0" class="more_options">
					<tr> 
						<td width="22"><input type="radio" name="irpDocument.transformname" value="A" <s:if test="irpDocument.transformname='A'">checked</s:if>/></td><td width="44">所有</td>
						<td width="22"><input type="radio" name="irpDocument.transformname" value="B" <s:if test="irpDocument.transformname='B'">checked</s:if>/></td><td width="44" class="accidence">入门</td>
						<td width="22"><input type="radio" name="irpDocument.transformname" value="C" <s:if test="irpDocument.transformname='C'">checked</s:if>/></td><td width="44" class="junior">初级</td>
						<td width="22"><input type="radio" name="irpDocument.transformname" value="D" <s:if test="irpDocument.transformname='D'">checked</s:if>/></td><td width="44" class="intermediate">中级</td>
						<td width="22"><input type="radio" name="irpDocument.transformname" value="E" <s:if test="irpDocument.transformname='E'">checked</s:if>/></td><td width="44" class="senior">高级</td>
					</tr>
        		</table>
			</div>
			<div class="area1"></div>
			<div class="editor_btns">
				<a href="javascript:void(0);" id="preview" onclick="previewDocument()">预览</a>  
       		<s:if test="toUpdate=='true'">
	        	<a href="javascript:void(0)" onclick="updatedocument()">保存</a>
       		</s:if>
        	<s:else>
	        	<a href="javascript:void(0)" onclick="adddocument()">保存</a>
        	</s:else>
        	<a href="javascript:void(0)" onclick="closeWindow()">关闭</a>
        </div>
    </div> 
</div>    
</form>

<!--尾部信息-->
<jsp:include page="../include/client_foot.jsp" />
<!--尾部信息end-->
</div>  
</body>
</html>
