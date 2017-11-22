<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head>
    <title>修改BUG </title>
 <link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
    <link 	href="<%=rootPath %>client/css/asx.css" rel="stylesheet" type="text/css" /> 
	<link 	href="<%=rootPath %>client/css/workhome-3482.min.css" rel="stylesheet" type="text/css" />
 	<link	href="<%=rootPath %>client/css/lib-3322.min.css" rel="stylesheet" type="text/css" />  
	<link 	href="<%=rootPath %>client/css/easyui.css" rel="stylesheet" type="text/css"  />
	<link   href="<%=rootPath %>client/css/icon.css" rel="stylesheet" type="text/css" />
	<link   href="<%=rootPath %>client/css/oapf-blue.css" rel="stylesheet" type="text/css" />
	<link 	href="<%=rootPath %>client/css/oacss.css" rel="stylesheet" type="text/css" />
	<link 	href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css">
	<link 	href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css"> 
	<link 	href="<%=rootPath%>view/css/discuz.css" rel="stylesheet" type="text/css" />  

	<script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/js/lhgdialog.min.js"></script>
	<script type="text/javascript" src="<%=rootPath %>view/js/modernizr.custom.js"></script>
	<script type="text/javascript" src="<%=rootPath %>view/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=rootPath %>view/js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=rootPath %>editor/ckeditor/ckeditor.js"></script>
 	<script type="text/javascript" src="<%=rootPath %>editor/applet/ckwordup.js"></script>
 
 
		
<style type="text/css">
	.searchSec .radios span {
		margin-top: 0px;
	}
	.term{margin-top: 10px;} 
.combo{
	background: none repeat scroll 0 0 #f7f7f7;
	border: 1px solid #d1d1d1;
	border-radius: 3px;
}
.combo .combo-text{
	font-size: 15px;
}
a{font-size: 14px;   }
#btnnn{
    background: #f7f7f7 none repeat scroll 0 0;
    border: 1px solid #d1d1d1;
    border-radius: 3px;
    font-size: 15px;
    height: 36px;
    line-height: 36px;
    text-indent: 10px;
    width: 100px;
    font-size: 15px;
    text-align: center;
    color: #000;
}

.abc{
	width: 83px;
	background:#5f9ddd;
	margin-left:30px;
	border-radius:3px;
}

.abc:hover{
	background:#79b6f6;
}

.abc a {
	display: inline-block;
	width: 85px;
	height: 36px;
	color: #fff;
	font-size: 15px;
	font-family: "微软雅黑";
	
	line-height: 36px;
	text-decoration: none;
	text-align: center;
	border-radius:3px;
	
}
</style>
</head>
<body style="background-image: url('');"> 
<script type="text/javascript">


$(function (){
	$('.combo').each(function(){
		$(this).find('input').attr('readonly','true');
	});
 });
var editor=null;
//搜索名字
function searchInfo1(searchInfo){  
		searchtype = 5;  
		if(searchInfo.length>38){
			searchInfo = searchInfo.substring(0,37);	
		}
		var eacapeInfo = encodeURI(searchInfo);
	    window.location.href="<%=rootPath%>solr/searchcontentofsolr.action?searchInfo="+eacapeInfo+"&searchtype="+searchtype; 
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
//分页
function pageperson(_nPageNum){
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
//删除上传附件
function delFile(fileId){ 
	 $('#filename'+fileId).val(''); 
	 $('#divfile'+fileId).remove();
	 var numberSpan=$('#showFileList').find('span'); 
	 if(numberSpan.length!=0){
	 	 $('#sharedesctextarea').val("分享了"+numberSpan.length+"个文件");
	 }else{
	 	$('#sharedesctextarea').val("你在忙什么？");
	 } 
}

//项目更改刷新负责人   PS: 暂时不用
function prochange(){
	$('#offPersonName').val('');
	$('#taskPersonId').val('');
	 var pid=$('#bugprojectselect').val();
		 //alert(pid);
	 window.location.href="<%=rootPath%>bug/toAddBugPage.action?projectId="+pid;
}

//任务负责人的选择
function checkTaskOffpersonId(){
    var projectid=<s:property value="projectId"  />; 
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
	OffPersonDialog=$.dialog({ 
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


/**
 * 修改Bug信息
 */
function editBug(){ 
	var projectId=<s:property value="projectId"  />;
	var isSubmit=$('#addBugPage').form('validate');
	var operatorId=$('#taskPersonId').val();
	var title=$('#bugTitle').val();
	var serianum=<s:property value="serianum"  />;
	var attachedids=checkboxs();
	//alert(serianum);
	if(isSubmit){
		var describe = editor.getData();
		//alert(describe);
		$('input[name="jsonFile"]').val(JSON.stringify(addJsonFileList)); 
		var postStr=$('#addBugPage').serialize();
		var saveUrl="<%=rootPath %>/bug/updatebug.action?"+postStr;
		$.ajax({
			type:'post',
			url:saveUrl,
			async: true,
		    cache: false,
			data:{
				'serianum':serianum,
				'describe':describe,
				'projectId':projectId,
				'bugTitle':title,
				'attachedids':attachedids
				
			},
			beforeSend:function(){
				$.dialog.tips('修改成功!',1000,'loading.gif',function(){ 
					});	
			},
			success:function(){
				$.dialog.tips('修改成功!',1,'32X32/succ.png',function(){ 
					 window.location.href=document.referrer;
					});	
			}
			
		});
		
	}
}
/***
* 上传附件
*/
//全局变量
var addJsonFileList = new Array();

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

function checkboxs(){
	var chk_value=$('input[name=attachedids]').val();
	 var delattas =[];    
	  $('input[name="attachedids"]:checked').each(function(){    
		  delattas.push($(this).val());    
	  });    
	return delattas.toString();
}
</script>

<div class="bg01">
<!--头部菜单-->
 <jsp:include page="../../view/include/client_head.jsp" />
 <section class="mainBox">
	<nav class="privateNav">
	</nav>
</section>
<!--头部菜单end-->
<div class="main-gr" style="background-image: url('');"> 
<!--左侧内容结束--> 
<!--右侧内容-->
<div class="right" style="border: none;width: 100%;text-align: left;"> 
 
<form id="addBugPage"  method="post" onsubmit="return false;">
	 	 <s:hidden  id="fileName" name="fileName"/>
	 	 <s:hidden  id="fileTrueName" name="fileTrueName"/>
	 	 <input type="hidden" name="jsonFile"/>
	 	<div style="float: left;width: 100%;">&nbsp;</div><br/>
	 	<div  class="newForm" style="width: 100%;float: none;margin-top: 30px;"> 
			<div> 
				<div class="txt_lptaskt"  style="width: 8%;float: left;line-height: 30px;text-align: right;">Bug标题：</div>
				&nbsp;&nbsp;<input  class="artTitle easyui-validatebox" required="true" validtype="length[2,300]" placeholder="添加Bug标题"  id="bugTitle"  value="<s:property value='irpBugs.get(0).title'/>" />  
			</div> 
        </div>
        <div style="float: left;width: 100%;">&nbsp;</div><br/>
        <div class="newForm"  style="width: 100%;float: none;margin-top: 20px;"> 
        
        <div class="txt_lptaskt"  style="width: 8%;float: left;line-height: 30px;text-align: right;">版本：</div> 
	    &nbsp;&nbsp;<select   class="easyui-combobox"  style=" float: left;width: 154px; height: 34px; line-height: 34px;" name="versionid"> 
       	<s:iterator var="version" value="bugversions">
             		<s:set var="vid" value="irpBugs.get(0).versionid"></s:set>
             			<option value="<s:property value='#version.bugconfigid'/>"   <s:if test="#vid==#version.bugconfigid">selected='selected'</s:if>><s:property value="#version.versionname" /></option>
             		</s:iterator>
	    </select> 
	    </div> 
	    <div    style="float: left;width: 100%;">&nbsp;</div><br/>
	    <div class="newForm"  style="width: 100%;float: none;margin-top: 20px;" >  
	    
	    <div class="txt_lptaskt"  style="width: 8%;float: left;line-height: 30px;text-align: right;">模块：</div>
	    &nbsp;&nbsp;<select id="bugprojectselect"  class="easyui-combobox"  style=" width: 154px; height: 34px; line-height: 34px;"name="modulid">
             		 <s:iterator var="modul" value="bugmoduls">
	             	 <s:set var="mid" value="irpBugs.get(0).moduleid"></s:set>
             			<option  <s:if test="#mid==#modul.bugconfigid">selected='selected'</s:if> value="<s:property value="#modul.bugconfigid"/>"><s:property value="#modul.modulname" /></option>
            		 </s:iterator>
	    </select>
        </div>
		<div style="float: left;width: 100%;">&nbsp;</div><br/>
	    <div   style="width: 100%;float: none;margin-top: 20px;">  
	    	    <div class="txt_lptaskt"  style="width: 8%;float: left;line-height: 30px;text-align: right;">优先级：</div>
	    		<s:set var="pri" value="irpBugs.get(0).priority"></s:set>
	    		<div style="line-height: 35px;">&nbsp;&nbsp;
	    		<input type="radio" name="priority" checked="checked" value="1"  <s:if test="#pri==1">checked='checked'</s:if>/>&nbsp;低&nbsp;&nbsp;
	    		<input type="radio" name="priority" value="2" <s:if test="#pri==2">checked='checked'</s:if>/>&nbsp;中&nbsp;&nbsp;
	    		<input type="radio" name="priority" value="3" <s:if test="#pri==3">checked='checked'</s:if>/>&nbsp;高&nbsp;&nbsp;
	    		<input type="radio" name="priority" value="4" <s:if test="#pri==4">checked='checked'</s:if>/>&nbsp;紧急&nbsp;&nbsp;
	    		<input type="radio" name="priority" value="5" <s:if test="#pri==5">checked='checked'</s:if>/>&nbsp;严重&nbsp;&nbsp;
	    		</div>
	    </div>
	    <div style="float: left;width: 100%;">&nbsp;</div><br/>
        <div class="newForm"  style="width: 100%;float: none;margin-top: 20px;"> 
        	<div class="txt_lptaskt"  style="width: 8%;float: left;line-height: 30px;text-align: right;">处理人：</div>
        	&nbsp;&nbsp; 
				<input class="easyui-validatebox" required="true" id="offPersonName" readonly="readonly" onclick="checkTaskOffpersonId()"    type="text"  value="<s:property value="operatorNameMap.get(irpBugs.get(irpBugs.size()-1).founderid)" />"/> 
				<input id="taskPersonId" type="hidden"  name="operatorId"  value="<s:property   value='irpBugs.get(irpBugs.size()-1).founderid'/>" /> 
        </div>
		<div style="float: left;width: 100%;">&nbsp;</div><br/>
		</form>
	  	<div class="newForm"  style="width: 100%;float: none;margin-top: 20px;"> 
	  		<div class="txt_lptaskt"  style="width: 8%;float: left;line-height: 30px;text-align: right;">描述：</div>
	  		&nbsp;&nbsp; 
		    <div class="intro_ptaskt clearfix" style="background-image: url('');float: left;margin-left: -30px;">
				<textarea id="editor" name="editor" ><s:property value='irpBugs.get(0).describe'/></textarea>
	        	<script>
	        	editor=CKEDITOR.replace('editor',{
						filebrowserUploadUrl: '<%=rootPath%>file/ck_upload.action',
						width:850
					});
					//构建图片上传地址
		            //var sUrl = '<%=rootPath%>file/ck_word_upload.action;jsessionid=<%=session.getId()%>';
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
	  	</div>
	 	<div style="clear: both;"></div>
	  	<div class="newForm"  style="width: 100%;margin-top: 20px;"> 
	  		<div class="txt_lptaskt"  style="width: 8%;float: left;line-height: 30px;text-align: right;">附件：</div>
	  		&nbsp;&nbsp;
	  		<div style="float: left; margin-left:10px;">
				<input type="button" id="btnnn"   onclick="tosaveAttacthed()" value="选择附件" />   
			</div>		
		</div>
 		<div style="clear: both;"></div>
 		<div style="margin-left:53px;width: 100%;color: #666;font-size: 13px;line-height: 60px;">
			<s:iterator value="attacheds" >
			<s:set var="tid" value="typeid"></s:set>
				<s:if test="@com.tfs.irp.attached.entity.IrpAttached@TYPEIDOTHER==#tid">
					<div style="margin-left:30px;">
						<span style="width:40px;float:left;margin:10px 0 0 40px; display: block;text-align: center;background: url(<%=rootPath%>client/images/att.png) no-repeat left center;background-position:  -60px -10px; height:47px; "></span>
						<div style="float:left;margin:20px 0 0 40px; width:250px; "><a href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>" ><s:property value='attdesc'/></a></div>
						<div style="float:left;margin-top:20px;margin-left:30px;">
							<a  href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>">下载</a>
							|&nbsp;&nbsp;<input type="checkbox" name="attachedids" value="<s:property value='attachedid'/>"  />删除
						</div>
					</div>
					<div style="clear: both;"></div>
				</s:if>
				<s:else>
					<div style="margin-left:30px;">
						<span style="width:40px;float:left;margin:10px 0 0 40px; display: block;text-align: center;background: url(<%=rootPath%>client/images/att.png) no-repeat left center;background-position:  -10px -10px; height:47px; "></span>
						<div style="float:left;margin:20px 0 0 40px; width:250px; "><a href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>" ><s:property value='attdesc'/></a></div>
						<div style="float:left;margin-top:20px;margin-left:30px;">
							<a  href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>">下载</a>
							|&nbsp;&nbsp;<input type="checkbox" name="attachedids" value="<s:property value='attachedid'/>"  />删除
						</div>
					</div>
					<div style="clear: both;"></div>
				</s:else>
			</s:iterator>
		</div>
		<!-- 
		<s:if test="attacheds.size()>0">

		<div style="margin-left:80px;">
			<input  type="button"  value="删除选中附件"  onclick="deldel()"/>
		</div>
		
		</s:if>
		 -->
		 <div style="margin-top:30px;">
	   		<div class="abc" style="float:left;">
	             <a onclick="editBug()" id="addtaskbut" class="btn1 " title="提交" href="javascript:void(0);">提&nbsp;&nbsp;&nbsp;&nbsp;交</a>
		    </div> 
		    <div class="abc" style="float:left;">
		    	<a onclick="window.location.href=document.referrer" id="addtaskbut" class="btn1 " title="取消" href="javascript:void(0);">取&nbsp;&nbsp;&nbsp;&nbsp;消</a>
		    </div>
		 </div>

</div>
</div>
<!--尾部信息--> 
<jsp:include page="../include/client_foot.jsp" />
<!--尾部信息end-->
</div>
</body>
</html>