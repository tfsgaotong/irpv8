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
	<s:if test="irpDocument==null">
		添加个人知识页面
	</s:if><s:else>
		修改个人知识页面
	</s:else>
</title>
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/icon.css" />
<link href="<%=rootPath %>client/css/oacss.css" rel="stylesheet" type="text/css" />
<jsp:include page="../include/client_skin.jsp" />

<script type="text/javascript" src="<%=rootPath %>client/js/jquery-1.8.0.min.js"></script> 
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/jquery.easyui.min.js"></script> 
<script type="text/javascript" src="<%=rootPath %>client/js/easyui-lang-zh_CN.js"></script> 
<script type="text/javascript" src="<%=rootPath %>client/js/json2.js"></script>
<script type="text/javascript"> 
var m_path="<%=rootPath %>";
</script>
<script type="text/javascript" src="<%=rootPath %>editor/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<%=rootPath %>editor/applet/ckwordup.js"></script>

<style type="text/css">
body{
	behavior:url(../js/hover.htc);
}
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
width: 845px;
}
</style> 
<script type="text/javascript">  
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
				okVal:'确定',  
				ok:function(){
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
			},
			cancelVal: '取消',
		    cancel: function(){
		    	//清空
				//addJsonFileList.length=0;  
		    }
		  });   
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
  
  
//添加文档
var addJsonFileList = new Array(); 
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
						$('input[name="jsonFile"]').val(JSON.stringify(addJsonFileList));  //点击修改文档时候将它转成字符串发送 
						var isValid =  $('#adddocumentfrm').form('validate');
						if(isValid){
							var chnlid = $('input[name="channelids"]').val();
							if(chnlid.length>0 && !isNaN(chnlid)){
								<s:if test="userGroups.size()>1 && (irpDocument==null || irpDocument.isdraft==0)">
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
							}else{
								submitAddForm();
							}
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
				$('input[name="jsonFile"]').val(JSON.stringify(addJsonFileList));  //点击修改文档时候将它转成字符串发送 
				var isValid =  $('#adddocumentfrm').form('validate');
				if(isValid){
					var chnlid = $('input[name="channelids"]').val();
					if(chnlid.length>0 && !isNaN(chnlid)){
						<s:if test="userGroups.size()>1 && (irpDocument==null || irpDocument.isdraft==0)">
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
					}else{
						submitAddForm();
					}
				}
			}
		},function(){});
	}else{
		$.messager.alert('提示信息','源码编辑下，不能提交知识...','warning');
	}
}

function submitAddForm(){
	var chnlid = $('input[name="channelids"]').val();
	if(chnlid.length==0 || isNaN(chnlid)){
		var jqGroupid = $('#adddocumentfrm').find('input[name="irpDocument.groupid"]');
		if(jqGroupid){
			jqGroupid.val('');
		}
	}
	$.messager.progress({text : '数据提交中...'});
	$('#adddocumentfrm').form('submit',{
		url : '<%=rootPath  %>site/clientadddocument.action?flag=person',
		success : function(data){
	    	$.messager.progress('close'); 
			if(data=="1"){   
				var aLable=window.opener.$('.fy');
				if(aLable){
					var checkLable=$(aLable).find('.hover');
					if(checkLable){
				  		checkLable.click();  
				  	}
				} 
				if(window.opener!=null){
					window.opener.location.reload(true);
				}
			 	window.close();    
			}else{ 
				$.dialog.alert('添加失败');
			}
		} 
	});
}

//修改文档
function updatedocument(_status ){  
	var editor = CKEDITOR.instances.editor;
	if(editor.document){
		$.dialog.confirm('您确定要修改这个知识吗？',function(){
			if(_status){
				$('input[name="irpDocument.docstatus"]').val(_status);
	   	    }
	   		$('input[name="irpDocument.doccontent"]').val(editor.document.getBody().getText());  
	   		$('input[name="irpDocument.dochtmlcon"]').val(editor.getData());
	   		$('input[name="irpDocument.attachedcontent"]').val(editor.getData()); 
	   	    $('input[name="jsonFile"]').val(JSON.stringify(_allattacheds));  //点击修改文档时候将它转成字符串发送
	   	    
			var isValid =  $('#adddocumentfrm').form('validate');
			if(isValid){
				<s:if test="userGroups.size()>1 && (irpDocument==null || irpDocument.isdraft==0)">
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
							submitUpdateForm();
						},
					    cancelVal: '取消',
					    cancel:true,
					    lock: true
					});
				}
		   	    </s:if>
		   	 	<s:else>
		   	 	submitUpdateForm();
		   	 	</s:else>
			}
		},function(){});
    }else{
    	$.messager.alert('提示信息','源码编辑下，不能提交知识...','warning');
    }
}

function submitUpdateForm(){
	var chnlid = $('input[name="channelids"]').val();
	if(chnlid.length==0 || isNaN(chnlid)){
		var jqGroupid = $('#adddocumentfrm').find('input[name="irpDocument.groupid"]');
		if(jqGroupid){
			jqGroupid.val('');
		}
	}
	$.messager.progress({text : '数据提交中...'});
	$('#adddocumentfrm').form('submit',{
		url : '<%=rootPath  %>site/clientupdatedocument.action?flag=person',
		success : function(data){
			$.messager.progress('close'); 
			//得到这些值 
			if(data=="1"){ //将data修改为文档栏目中间表回  
				if(window.opener!=null){
					var aLable=window.opener.$('.fy');
					if(aLable){
						var checkLable=$(aLable).find('.hover');
							if(checkLable){
								checkLable.click();  
							}
					} 
				}
				window.close(); 
			}else{
				$.dialog.alert('修改失败');
			}
		} 
	});
}
	
var _selectchannels=null;
	//进入页面发送ajax请求，然后得到导航栏（二级栏目列表）
	function allchannel(){
			var _channelid='<s:property value="irpDocument.channelid"/>';
			if(_channelid==null || _channelid==""){
				$.ajax({
					type:'post',
					url:'<%=rootPath%>site/selectchannelselect.action',
					success: function(msg){   
						_selectchannels=eval(msg);//转换成附件集合   
						var str='<select name="irpDocument.channelid">';   
							if(_selectchannels!=null){
								var len = _selectchannels.length;  
								for(var i=0 ;i<len ; i++){  
									if(_selectchannels[i].parentid==0){ //这是一级栏目
									 	str+='<option value="'+_selectchannels[i].channelid+'">默认</option>';  
									}else{//二级栏目
									   str+='<option  value="'+_selectchannels[i].channelid+'">'+_selectchannels[i].chnldesc+'</option>';
		        					}
								 }
							} 
						str+='</select>'; 
						$('#channelselect').html(str);
					} 
				});
			}else{
				$.ajax({
					type:'post',
					url:'<%=rootPath%>site/selectchannelselect.action',
						success: function(msg){  
							_selectchannels=eval(msg);//转换成附件集合    
							var str='<select name="irpDocument.channelid">';   
							if(_selectchannels!=null){
								var len = _selectchannels.length;  
								for(var i=0 ;i<len ; i++){   
								var _chnlid=_selectchannels[i].channelid; 
								if(_selectchannels[i].parentid==0){ //这是一级栏目
						 			if(_channelid==_chnlid){
						 				str+='<option value='+_selectchannels[i].channelid+' selected >默认</option>'; 
						 			}else{
						 				str+='<option value='+_selectchannels[i].channelid+'>默认</option>'; 
						 			} 
								}else{//二级栏目 
						  			 if(_channelid==_chnlid){
						  			 str+='<option value='+_selectchannels[i].channelid+' selected>'+_selectchannels[i].chnldesc+'</option>'  ;
						 			}else{
						 				str+='<option value='+_selectchannels[i].channelid+'>'+_selectchannels[i].chnldesc+'</option>'  ;
						 			} 
					  	  	  }
							}
						}
							str+="</select>";
							$('#channelselect').html(str);
						}
					
					});
			}
 } 
	//验证
	$(function(){ 
		//发送一个ajax请求，得到当前的所有栏目
			allchannel();
			toUpdate();
			$.extend($.fn.validatebox.defaults.rules, { 
		        maxLength : {
					validator: function(value, param){   
						return value.length < param[0];  
		            },
					message : '该字符长度至多{0}位'
		        } 
	        }); 
	});
	//关闭页面
	function closeWindow(){ 
		$.dialog.confirm('您确定要关闭当前页面吗？',function(){
		   window.close();
		},function(){},parent);
	} 
	//添加分类
	function addChannel(){ 
		$.ajax({
			type:'post', 
			url:'<%=rootPath%>site/iscreatechannel.action',
			async : false,
		    success : function(msg){ 
		    	if(msg=="1"){ 
				     var str=$.ajax({
					 	type:'post',
						 dataType: "json",
						 url:'<%=rootPath  %>site/client_toadd_channel.action', 
					 	async: false,
		    		 	cache: false
					 }).responseText; 
					 
				    $.dialog({
						title:'新增分类',
						max:false,
						min:false,
						lock:true,
						resize: false,
						width:'250px',
						height:'70px',
						content:str,
						cancelVal:'关闭',
						okVal:'确定',
						init : function(){
							 $('#chnlnameinput').validatebox({ required: true});   
						},
						cancel:function(){},
						ok:function(){  
							var isSubmit=$('#addchannelfrm').form('validate');   
							      $('#addchannelfrm').form('submit',{
										url:'<%=rootPath  %>site/clientaddchannel.action',
										success:function(callback){  
												if(callback!=""){ 
													$.dialog.tips('新增分类成功',1,'32X32/succ.png',function(){ 
													 allchannel();//刷新分类
													 //拿到他的父页面，然后刷新导航 
													 var obj=JSON.parse(callback);  
													 var str="<li id='channelid0' onclick='tabs(this)'  _href='<%=rootPath%>site/selectdoclink.action' _data='id="+obj['channelid']+"&crtimesort=desc'><a  href='javascript:void(0)'>"+obj['chnldesc']+"</a></li>";
													  window.opener.$('#daohangul').append(str); 
													});						
												}else{
													$.dialog.tips('新增分类失败',0.5,'32X32/fail.png');	
												}
										},
										error:function(){
											$.dialog.tips('新增分类失败',0.5,'32X32/fail.png');
										}
									}); 
									return isSubmit;
							   } 
				});  
		    	}else{
		    		$.dialog.alert('您创建分类个数已达最大',function(){});
		    	}
		    }
		});
		
	}  
	function deleteMapDiv(){
		var selects=$('#documentmapdiv').find('select').each(
				function(i){
					 var thisId=$(this).attr('id');
					 $(this).combotree('clear');
				}		
			);
	}
	
	function deleteSubDiv(){
		var checkbox = $('#documentsubdiv').find('input').each(
			function(i){
				$(this).removeAttr('checked');
			}	
		);
	}
	
$(function(){
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
	$('#rightchannel').combotree({
		url: '<%=rootPath%>site/rightchanneltotougao.action',
		width: '200px',
		onSelect:function(){
			$('#preview').show();
			$('#deletepreview').show();
		}
	});
}); 

function deletePreviewDocument(){
	$('#deletepreview').hide();
	$('#preview').hide();
	$('#rightchannel').combotree('clear');
}

//创建一个数组存放当前所有附件 ///修改附件
var _allattacheds=null;//存放所有附件对象从数据库中读出来的 
	function toUpdate(){ 
	//发送ajax请求获得所有附件
		  $.ajax({ 
			   type: "POST", 
			   url: "<%=rootPath %>site/allattachedtodocument.action?docid=<s:property value="irpDocument.docid"/>", 
			   success: function(msg){   
			   		 _allattacheds=eval(msg);//转换成附件集合    
			  } 
		 });   
	}  
function tosaveAttacthedtoupdate(_docid){  
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
				okVal:'确定', 
				ok:function(){
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
	 }
 }
 function hidDiv(){
	 $("#tagstip").html("");
 }
 
$(function(){
	initAllSelfSubject();
})
function initAllSelfSubject(){
	var result = $.ajax({
		url:'<%=rootPath %>site/initAllSelfSubject.action?doorchannelid=<s:property value="doorchannelid" />&docid=<s:property value="docid" />',
		type:'post',
		async: false,
		cache: false
	 }).responseText;
	$('#newSubjectList').html(result);
}
</script>
</head>
<body>
<div class="bg01">
<!--头部菜单-->
<jsp:include page="../include/client_head.jsp" />
<!--头部菜单end-->
<form id="adddocumentfrm" action="" method="post">  
<s:if test="irpDocument==null">
	<input type="hidden" name="irpDocument.siteid" value="<s:property value='@com.tfs.irp.site.entity.IrpSite@PRIVATE_SITE_ID'/>" />  
</s:if><s:else>
	<input type="hidden" name="irpDocument.siteid" value="<s:property value='irpDocument.siteid'/>" /> 
</s:else>
	<input type="hidden" name="irpDocument.docid" value="<s:property value='irpDocument.docid'/>"/>  
   	<input type="hidden" name="irpDocument.docstatus" /> 
   	<input type="hidden" name="irpDocument.doctype" value="<s:property value='irpDocument.doctype'/>" />  
   	<input type="hidden" name="irpDocument.doccontent" value="" /><%--文本 --%> 
   	<input type="hidden" name="irpDocument.dochtmlcon" value="" /><%--html --%> 
   	<input type="hidden" name="irpDocument.attachedcontent" value="" /><%--html --%> 
   	<input type="hidden" name="jsonFile"/><%-- 源文件名称 --%>
<s:if test="userGroups.size()==1 && (irpDocument==null || irpDocument.isdraft==0)">
	<s:iterator value="userGroups">
	<input type="hidden" name="irpDocument.groupid" value="<s:property value="key" />" />
	</s:iterator>
</s:if>
<s:elseif test="userGroups.size()>1 && (irpDocument==null || irpDocument.isdraft==0)">
	<input type="hidden" name="irpDocument.groupid" value="" />
</s:elseif>
   	
<div class="editor_body edit_hidden">
	<div class="edit_left">
    	<table cellpadding="0" cellspacing="0" border="0" width="100%">
        	<tr>
        		<td class="edit_tit" valign="top"><div class="edt_tt"></div></td>
        		<td><table cellpadding="0" cellspacing="0" border="0" class="edit_titInpt">
        			<tr>
        				<td height="15"></td>
        			</tr>
        			<tr>
        				<td width="5"><div class="edt_l_1"></div></td>
        				<td width="576"><input class="easyui-validatebox" validType="length[2,300]" missingMessage="请填写文档标题" type="text" name="irpDocument.doctitle" value="<s:property value='irpDocument.doctitle'/>" required="true" id="doctitlec" /></td>
        				<td width="5"><div class="edt_r_1"></div></td> <td align="center"><font color="red">*</font></td>
        			</tr>
        		</table></td>
        		<td class="edit_tit"><span class="addto">创建到</span></td>
        		<td width="100"><table cellpadding="0" cellspacing="0" border="0" class="edit_tagInpt">
        			<tr>
        				<td height="15"></td>
        			</tr>
        			<tr>
        				<td width="10"><img src="<%=rootPath%>client/images/editor/edit_04.jpg" /></td>
						<td class="edit_titSel" id="channelselect"></td>
						<td width="10"><img src="<%=rootPath%>client/images/editor/edit_06.jpg" /></td>
					</tr>
				</table></td>
				<td width="90" align="center"><a href="javascript:void(0)" onclick="addChannel()" class="creatNew">+新增分类</a></td>
			</tr>
        </table>
        <div class="area1"></div>
        <table cellpadding="0" cellspacing="0" border="0" width="100%">
        	<tr>
        		<td class="edit_tit"><span>标签</span></td>
        		<td width="461"><table cellpadding="0" cellspacing="0" border="0" class="edit_tagInpt">
					<tr>
	        			<td width="10"><img src="<%=rootPath%>client/images/editor/edit_04.jpg" /></td>
			        	<td width="427"><input required="true" validType="maxLength[60]" value="<s:property value='irpDocument.dockeywords'/>" name="irpDocument.dockeywords" class="easyui-validatebox"  type="text" /></td>
		        		<td width="10"><img src="<%=rootPath%>client/images/editor/edit_06.jpg" /></td> 
		        		<td width="14" align="center"><font color="red">*</font></td>
	        		</tr>
	        	</table></td>
        		<td width="60" align="center">
        			<a id="tuijian" href="javascript:void(0)" onclick="findMytag()">推荐</a>
        		</td>
        		<td width="100" align="center" class="autoCheck">
				<s:if test="irpDocument==null">
					<a href="javascript:void(0)" onclick="tosaveAttacthed(<s:property value='irpDocument.docid'/>)">附件</a>
				</s:if><s:else>
 					<a href="javascript:void(0)" onclick="tosaveAttacthedtoupdate(<s:property value='irpDocument.docid'/>)">附件</a>
				</s:else>
				</td>
				<td width="245" align="right" class="addToweibo">同步更新到微知<span><input type="checkbox" name="isTrue" value="1" checked="checked"/></span></td>
			</tr>
			<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
        		<td colspan="4"><div id="tagstip" ></div></td>
        	</tr>
        </table>
              <div class="area1"></div>
          <table cellpadding="0" cellspacing="0" border="0" width="100%">
        	<tr>
        	<td class="edit_tit">
        	<span>核心<br />提示</span></td>
			<td>
			  <textarea name="irpDocument.docabstract" validType="maxLength[600]"  class="easyui-validatebox xm_zhaiyao" required="true" ><s:property value="irpDocument.docabstract"/></textarea> 
			</td>
			<td width="28" align="center"><font color="red">*</font>
			</td>
			</tr>
        </table>
         <div class="area1"></div> 
        <div class="editor_edit">
        	<!--添加编辑器-->
        	<textarea id="editor" name="editor"> 
        	<s:property  value="irpDocument.dochtmlcon"/>
        	</textarea>
        	<script>
	        	var cssUrl = $('#skin').attr('href');
	    		var sColor  = cssUrl.substring(cssUrl.lastIndexOf('/oapf-')+6,cssUrl.lastIndexOf('.css'));
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
	            
	            function moresubject(){
	            	var status = $('#hidesubdiv').css("display");
	            	if(status=="block"){
	            		$('#hidesubdiv').hide(888);
	            	}else{
	            		$('#hidesubdiv').show(888);	
	            	}
	            }
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
	            	    			url : "<%=rootPath %>site/begincreatesubject.action",
	            	    			success:function(data){
	            	    				if(data=='1'){
	            	    					$.dialog.tips('创建成功',1,'32X32/succ.png',function(){
	            	    					initAllSelfSubject();
	            	    					});
	            	    				}else if(data=='2'){
	            	    					$.dialog.tips('专题名称已存在',1,'32X32/fail.png');
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
	            
			</script>
        </div>   
       <div class="area1"></div>
          	<div class="more_tit"  ><span> 知识分类：</span><span><a href="javascript:void(0);"  onclick="deleteMapDiv()">重置</a></span></div>
				<div  id="documentmapdiv">
				<s:iterator value="documentMap">
					<select id="<s:property value='channelid'/>"   name="documentmaps" class="easyui-combotree" style="width: 150px;">
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
		<div class="area1"></div>
	    <div id="newSubjectList"></div>	
       <div class="more_tit"><span>知识级别：</span></div>
         <table cellpadding="0" cellspacing="0" border="0" class="more_options">
         <tr> 
			<td width="22"><input type="radio" name="irpDocument.transformname" value="A" <s:if test='%{IrpDocument.transformname=="A"}'>checked </s:if> /></td><td width="44">所有</td>
			<td width="22"><input type="radio" name="irpDocument.transformname" value="B" <s:if test='%{IrpDocument.transformname=="B"}'>checked </s:if> /></td><td width="44" class="accidence">入门</td>
			<td width="22"><input type="radio" name="irpDocument.transformname" value="C" <s:if test='%{IrpDocument.transformname=="C"}'>checked </s:if> /></td><td width="44" class="junior">初级</td>
			<td width="22"><input type="radio" name="irpDocument.transformname" value="D" <s:if test='%{IrpDocument.transformname=="D"}'>checked </s:if> /></td><td width="44" class="intermediate">中级</td>
			<td width="22"><input type="radio" name="irpDocument.transformname" value="E" <s:if test='%{IrpDocument.transformname=="E"}'>checked </s:if> /></td><td width="44" class="senior">高级</td>
		</tr>
        </table>
	<div class="area1"></div>
			<div class="more_tit">
        <s:if test="irpDocument!=null">
	          <s:if test="%{irpDocument.isdraft==0}">
	        	 <span>投稿&nbsp;到:
		           <select id="rightchannel" name="channelids" class="easyui-combotree" style="width: 200px;"></select> 
		        </span>
	        </s:if>
	        <s:else>
	       		 <div class="more_tit"><span>文章已投稿</span></div>
	        </s:else>  
        </s:if>
        <s:else>
        	<span>投稿&nbsp;到： 
	        	 <select id="rightchannel" name="channelids" class="easyui-combotree" style="width: 200px;" onchange="show()"></select> 
	          </span>
	          <a href="javascript:void(0);" id="deletepreview" style="display: none;" onclick="deletePreviewDocument()" >取消投稿</a>
        </s:else>
			</div>  
        <div class="area1"></div>
         <div class="editor_btns">
         <s:if test="irpDocument!=null">
         	<s:if test="%{irpDocument.isdraft==0}">
         	<a href="javascript:void(0);" id="preview" style="display: none;" onclick="previewDocument()">预览</a>
         	</s:if> 
         </s:if>
         <s:else>
         <a href="javascript:void(0);" style="display: none;" id="preview" onclick="previewDocument()">预览</a>
         </s:else> 
         
         <s:if test="irpDocument!=null">
			<a href="javascript:void(0)" onclick="updatedocument(<s:property value='@com.tfs.irp.document.entity.IrpDocument@DRAFT_STATUS' />)">存草稿</a> 
        	<a href="javascript:void(0)" onclick="updatedocument()">保存</a>
        	<a href="javascript:void(0)" onclick="closeWindow()">关闭</a>
         </s:if><s:else>
			<a href="javascript:void(0)" onclick="adddocument(<s:property value='@com.tfs.irp.document.entity.IrpDocument@DRAFT_STATUS' />)" id="subgao">存草稿</a> 
        	<a href="javascript:void(0)" onclick="adddocument()" id="subsave">保存</a>
        	<a href="javascript:void(0)" onclick="closeWindow()"  >关闭</a>
         </s:else>
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
