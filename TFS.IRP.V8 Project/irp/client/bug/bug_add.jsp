<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!doctype html>
<html lang="en" class="no-js">
<head>
<meta charset="utf-8">
<title>创建Bug</title>
<link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css">
<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css">
<link href="<%=rootPath %>view/css/easyui.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath%>view/css/icon.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath%>view/css/discuz.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.combo{
	background: none repeat scroll 0 0 #f7f7f7;
	border: 1px solid #d1d1d1;
	border-radius: 3px;
}
.combo .combo-text{
	font-size: 15px;
}
</style>

<script type="text/javascript" src="<%=rootPath %>view/js/modernizr.custom.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath %>editor/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<%=rootPath %>editor/applet/ckwordup.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/json2.js"></script>
<script type="text/javascript">
//全局变量
var addJsonFileList = new Array();

$(function(){
	$.extend($.fn.validatebox.defaults.rules, { 
        maxLength : {
			validator: function(value, param){   
				return value.length < param[0];  
            },
			message : '该字符长度至多{0}位'
        } 
    });
	initChnlTree();
	initDocMapTree();
	<s:if test="toUpdate=='true'">
	toUpdate(<s:property value='irpDocument.docid'/>);
	</s:if>
});

//初始化栏目树
function initChnlTree(){
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
			panelWidth: 178,
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

function initDocMapTree(){
	$('#documentmapdiv').find('select').each(function(i){
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
	});
}

//是否包含value
function exist(arr,value){
	if(arr){
		for(var i=0;i<arr.length;i++){
			if(arr[i]==value)return true;
		}
	}
	return false;
}

//重置扩展分类
function deleteMapDiv(){
	$('#documentmapdiv').find('select').each(function(i){
		$(this).combotree();
	});
}
//重置专题
function deleteSubDiv(){
	$('input[name="subjectselect"]').each(function(i){
		$(this).removeAttr('checked');
	});
}

//关闭页面
function closeWindow(){ 
	$.dialog.confirm('您确定要关闭当前页面吗？',function(){
	   window.close();
	},function(){},parent);
}

//预览知识
function previewDocument(){ 
	var editor = CKEDITOR.instances.editor;
	if(editor.document){
		$('input[name="irpDocument.doccontent"]').val(editor.document.getBody().getText());  
		$('input[name="irpDocument.dochtmlcon"]').val(editor.getData()); 
		$('input[name="irpDocument.attachedcontent"]').val(editor.getData()); 
		$('input[name="jsonFile"]').val(JSON.stringify(addJsonFileList));  //点击修改文档时候将它转成字符串发送 
		$('#adddocumentfrm').attr('target','_blank');
		$('#adddocumentfrm').attr('action','<%=rootPath %>document/previewDocument.action');
		$('#adddocumentfrm').submit();
	}else{
		$.messager.alert('提示信息','源码编辑下，不能预览知识...','warning');
	}
}

//提交表单
function submitAddForm(){
	$.messager.progress({text : '数据提交中...'});
	$('#adddocumentfrm').form('submit',{
		url : '<%=rootPath %>site/addsubject.action?flag=qiye',
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
<s:if test="toUpdate=='true'">
var _allattacheds=null;
function toUpdate(_docid){ 
	//发送ajax请求获得所有附件
	$.ajax({ 
		type: "GET", 
		url: "<%=rootPath %>site/allattachedtodocument.action?docid="+_docid, 
		success: function(msg){   
			_allattacheds=eval(msg);//转换成附件集合    
		}
	});   
}
//修改附件
function toupdateAttacthed(_docid){   
	var str=$.ajax({
		type:'GET',
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

//修改文档并提交为新历史版本
function updatedocument(){ 
	var editor = CKEDITOR.instances.editor;
	if(editor.document){
		$.dialog.confirm('您确定修改知识吗？',function(){
			$('input[name="irpDocument.doccontent"]').val(editor.document.getBody().getText());  
			$('input[name="irpDocument.dochtmlcon"]').val(editor.getData()); 
			$('input[name="jsonFile"]').val(JSON.stringify(_allattacheds));  //点击修改文档时候将它转成字符串发送
			$('#adddocumentfrm').form('submit',{
				url : '<%=rootPath %>site/updatedocument.action?flag=qiye&docid='+<s:property value="docid"/>,
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
</s:if>
<s:else>
//添加附件
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
						if(addJsonFileList[i].editversions=="2"){
						}else{
							addJsonFileList[i].editversions=0;
						}
					}
				}
			}
		}
	});   
}
//添加文档
function adddocument(){
	var dval = $('#doctitle').val();
	var boolboor = $.ajax({
		type:'post',
    	url:'<%=rootPath%>site/booldocsimilarity.action',
    	async:false,
    	cache:false,
    	data:{
    		simistr:dval
    	}
	}).responseText; 
    var editor = CKEDITOR.instances.editor;
	if(editor.document){
		$.dialog.confirm('您确定要保存这个知识吗？',function(){
			if(boolboor>0){
				$.messager.confirm('提示信息','知识库里已存在'+boolboor+'篇相同的知识,您确定提交吗？',function(boor){
					if(boor==true){
						$('input[name="irpDocument.doccontent"]').val(editor.document.getBody().getText());
						$('input[name="irpDocument.dochtmlcon"]').val(editor.getData());
						$('input[name="irpDocument.attachedcontent"]').val(editor.getData());
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
				$('input[name="irpDocument.doccontent"]').val(editor.document.getBody().getText());  
				$('input[name="irpDocument.dochtmlcon"]').val(editor.getData()); 
				$('input[name="irpDocument.attachedcontent"]').val(editor.getData());
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
</s:else>

//自动提取
function autoExtract(){
	var sDocContent;
	var editor = CKEDITOR.instances.editor;
	if(editor.document){
		sDocContent = editor.document.getBody().getText();
		sDocContent = $.trim(sDocContent);
	}
	if(sDocContent && sDocContent.length>0){
		var sDocTitle = $('#doctitle').val();
		$.ajax({ 
			type:'post',
	    	url:'<%=rootPath%>document/auto_extract.action',
	    	async:false,
	    	cache:false,
	    	data:{
	    		'irpDocument.doctitle':sDocTitle,
	    		'irpDocument.doccontent':sDocContent
	    	},
			success: function(msg){   
				var obj = $.parseJSON(msg);
				$('#dockeywords').val(obj.dockeywords);
				$('#docabstract').val(obj.docabstract);
			}
		});
	}
}

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

//任务负责人的选择
function checkTaskOffpersonId(){
    var projectid=<s:property value="projectId"/>; 
	var taskPersonId=$('#taskPersonId').val();
	var result = $.ajax({
		type: 'POST',
		url: '<%=rootPath %>project/select_user.action',
		data : {  'userIds':taskPersonId,
				  'projectId':projectid,
				  'pageNum':1},
	    async: false,
	    cache: false
	}).responseText; 
	OffPersonDialog= $.dialog({ 
		 			id : 'selectUser',
					title:'选择任务负责人',
					max:false,
					min:false,
					lock:true, 
					init : function(){  
						$('#sWord').searchbox({}); 
						 initDialogShow();
					},
					resize: false,
					width:'500px',
					height:'150px',
					content:result,
					cancelVal:'关闭',
					okVal:'确定',
					cancel:true,
					ok:function(){    
						 var _personId=$('#userIds').val();//以逗号分割的所有用户id  
						 var _personNames=$('#userNames').val(); 
						 $('#offPersonName').val(_personNames);
						 $('#taskPersonId').val(_personId);
						 //销毁掉上次查询的条件
					} 
				});  	 
}

//初始化dialog弹框选择人
function initDialogShow(){
	$('#dialogPageForm').find('searchWord').val('');
	$('#dialogPageForm').find('searchType').val('');
	$('#dialogPageForm').find('orderField').val('');
	$('#dialogPageForm').find('pageNum').val(1);  
}
var OffPersonDialog;//任务负责人 //圈子负责人 
var ProjectPersonDialog;//圈子成员/圈子关注者///任务成员
//分页
function page(_nPageNum){
	$('#pageForm').find('input[name="pageNum"]').val(_nPageNum);
	$('#tabMenu').find('a').each(function(){   
			if(this.className=="over"){  
				 var sData =$(this).attr('_link');
				 var pageString=$('#pageForm').serialize(); 
				 sData=sData+"?"+pageString; 
				 showMyShareTask(sData);
			}    
	}); 
}  
</script>
</head>

<body>
<s:include value="../../view/include/client_head.jsp"></s:include>
<form id="adddocumentfrm" action="" method="post">  
	<input type="hidden" name="irpDocument.siteid" value="<s:property value='@com.tfs.irp.site.entity.IrpSite@SITE_TYPE_PUBLISH'/>" />  
	<input type="hidden" name="irpDocument.docid" value="<s:property value='irpDocument.docid'/>" />  
   	<input type="hidden" name="irpDocument.doccontent" value="" /><%--文本 --%> 
   	<input type="hidden" name="irpDocument.dochtmlcon" value="" /><%--html --%>
   	<input type="hidden" name="irpDocument.attachedcontent" value="" /><%--html --%>
  	<input type="hidden" name="jsonFile" /><%-- 源文件名称 --%>
<s:if test="irpDocument==null && userGroups.size()==1">
	<s:iterator value="userGroups">
	<input type="hidden" name="irpDocument.groupid" value="<s:property value="key" />" />
	</s:iterator>
</s:if>
<s:elseif test="irpDocument==null && userGroups.size()>1">
	<input type="hidden" name="irpDocument.groupid" value="" />
</s:elseif>
<section class="mainBox">
	<nav class="privateNav">
    	<dl>
        </dl>
    </nav>
</section>
<section class="mainBox">
	<section class="newForm">
    	<article class="location"><strong>创建Bug（<span>*</span>必须填写项）</strong></article>
        <div class="area10"></div>
        <table>
        	<tr>
            	<td>
            		<input type="text" placeholder="添加标题" id="doctitle" name="irpDocument.doctitle" class="artTitle easyui-validatebox" required="true" validType="length[2,300]" value="<s:property value='irpDocument.doctitle'/><s:property value="irpQuestionofquestionid.title" />" />
            	</td>
                <td width="20"><span class="must">*</span></td>
                <td>
                	<!-- <select id="rightchannel"  name="irpDocument.channelid" data-options="cascadeCheck:false" style="width:178px;height:36px;"></select> -->
                	<!-- 
                	<select class="easyui-combobox" name="dept" style="width:180px;height: 38px" title="处理人"  >   
                		<s:iterator var="user" value="projectUsers">
							<option  value="<s:property value='#user.userid'/>"><s:property value="#user.truename"/></option>
						</s:iterator>
                	</select>
                	-->
                	<input id="offPersonName" placeholder="选择处理人" readonly="readonly" onclick="checkTaskOffpersonId()"  class="txt_ptaskt user_lptaskt" type="text" style="width: 178px;"/> 
					<input id="taskPersonId" type="hidden"  name="operatorId"/> 
                </td>
                <td width="20"><span class="must">*</span></td>
            </tr>
        </table>
        
        <div class="area10"></div>
        <table>
        	<tr>
            	<td>
            		<!-- <input type="text" placeholder="添加标签" id="dockeywords" name="irpDocument.dockeywords" class="artTag easyui-validatebox" required="true" validType="maxLength[60]" value="<s:property value='irpDocument.dockeywords'/>" />  -->
            		 <span>截止时间</span>
            		 <span class="end_ptask date_lptaskt clearfix " style="height: 55px;margin-left: 20px;">
		                 <input title="何时开始" placeholder="截止时间" style="width:180px;height: 35px;" name="createTime" type="text"   id="taskStart" class="easyui-datebox" editable="false" validType="StartTime">&nbsp;&nbsp; 
				   	     <!-- 
				   	     —— 
				         <input title="何时完成" style="width:180px;height: 35px" name="requiredTime" type="text" id="taskEnd" class="easyui-datebox" editable="false" validType="EndTime">
						  --> 			   	    	
		   	    	</span>
		   	    	<span class="must">*</span>
		   	    	</td>
		   	    	
		   	    	<td>
		   	    	<span style="margin-left: 30px;">优先级</span>
		   	    	<span style="margin-left: 20px;">
		   	    		<select class="easyui-combobox" name="dept" style="width:180px;height: 38px" title="处理人"  >   
                		<s:iterator var="user" value="projectUsers">
							<option  value="<s:property value='#user.userid'/>"><s:property value="#user.truename"/></option>
						</s:iterator>
                	</select>
		   	    	</span>
		   	    	</td>
		   	    	<td>
		   	    	<span style="margin-left: 39px;">模块</span>
		   	    	<span style="margin-left: 30px;">
		   	    		<select class="easyui-combobox" name="dept" style="width:170px;height: 38px;" title="处理人"  >   
                		<s:iterator var="user" value="projectUsers">
							<option  value="<s:property value='#user.userid'/>"><s:property value="#user.truename"/></option>
						</s:iterator>
                	</select>
		   	    	</span>
            	</td>
                <td><div class="title">附件管理</div>
                	<input type="button" class="artFile" value="选择附件" style="width:100px;" onclick="tosaveAttacthed()" />
                </td>
            </tr>
        </table>
          
        <div class="area10"></div>
        <div>
        	<textarea id="editor" name="editor"><s:property value="irpDocument.dochtmlcon"/><s:property value="irpQuestionofquestionid.htmlcontent" /><s:property value="irpQuestionofparentid.htmlcontent" /></textarea>
        	<script>
	        	CKEDITOR.replace('editor',{
					filebrowserUploadUrl: '<%=rootPath%>file/ck_upload.action',
					width:850
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
        <div class="area20"></div>
        <div class="btns">
        	<input type="button" class="btn" onclick="previewDocument()" value="预&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;览" />
        	<s:if test="toUpdate=='true'">
        	<input type="button" class="btn" onclick="updatedocument()" value="保&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;存" />
        	</s:if>
        	<s:else>
        	<input type="button" class="btn" onclick="adddocument()" value="保&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;存" />
        	</s:else>
        	<input type="button" class="btn" onclick="closeWindow()" value="关&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;闭" />
        </div>
    </section>
    <!--  
    <section class="layoutRight formInfo">
    	 
    	<div class="area10"></div>
        <section>
        
        	<input type="button" class="artFile" value="智能提取" style="width:100px;" onclick="autoExtract()" />
        	<div class="title">核心提示<span class="must">*</span></div>
        	<textarea placeholder="核心提示" style="overflow:auto;" id="docabstract" name="irpDocument.docabstract" validType="maxLength[600]"  class="easyui-validatebox xm_zhaiyao" required="true" ><s:property value="irpDocument.docabstract"/></textarea>
            <div class="area10"></div>
            <div class="area10 line"></div>
        	<div class="title">附件管理</div>
        	<s:if test="toUpdate=='true'">
        	<input type="button" class="artFile" value="修改附件" style="width:100px;" onclick="toupdateAttacthed(<s:property value='irpDocument.docid'/>)" />
        	</s:if>
        	<s:else>
        	<input type="button" class="artFile" value="选择附件" style="width:100px;" onclick="tosaveAttacthed()" />
        	</s:else>
            <div class="area10"></div>
            <div class="area10 line"></div>
            <div class="reset"><a href="javascript:void(0)" onclick="deleteMapDiv()">重置</a></div>
        	<div class="title">知识分类</div>
        	<div id="documentmapdiv">
	        	<s:iterator value="documentMap">
	        	<select id="<s:property value='channelid'/>" name="documentmaps" style="width:300px;height:36px;">
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
				<div class="area10"></div>
	        	</s:iterator>
        	</div>
            <div class="area10 line"></div>
            <div class="reset"><a href="javascript:void(0)" onclick="deleteSubDiv()">重置</a></div>
        	<div class="title">引用到专题</div>
        	<s:iterator value="irpChannels" status="status">
        	<input <s:if test="selectstatus==1">checked="checked"</s:if> class="artCheck" id="check<s:property value='channelid' />" value="<s:property value='channelid' />" name="subjectselect" type="checkbox" /><label for="check<s:property value='channelid' />" title="<s:property value='chnlname'/>"><s:property value="chnlname"/></label>
        	</s:iterator>
        	<s:if test="toUpdate=='true'">
            <div class="area10"></div>
            <div class="area10 line"></div>
        	<div class="title">是否保存新版本</div>
            <input class="artCheck" id="onlyNo" value="1" name="onlydocid" type="radio" checked /><label for="onlyNo">否</label>
            <input class="artCheck" id="onlyYes" value="2" name="onlydocid" type="radio" /><label for="onlyYes">是</label>
        	</s:if>
        	
        	  <span class="end_ptask date_lptaskt clearfix ">
                 <input title="何时开始" name="createTime" type="text"   id="taskStart" class="easyui-datebox" editable="false" validType="StartTime">&nbsp;&nbsp; 
		   	     —— 
		         <input title="何时完成" name="requiredTime" type="text" id="taskEnd" class="easyui-datebox" editable="false" validType="EndTime">
		   	    </span>
        </section>
         
        <div class="area20"></div>
    </section>
    -->
</section>
</form>
<s:include value="../../view/include/client_foot.jsp"></s:include>
</body>
</html>