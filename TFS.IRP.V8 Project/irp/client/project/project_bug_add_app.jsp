<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head>
    <title>添加BUG </title>
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
	<!-- 
	<script type="text/javascript" src="<%=rootPath %>editor/simple_ckeditor/ckeditor.js"></script>
	 <script type="text/javascript" src="<%=rootPath %>editor/applet/ckwordup.js"></script>
	 -->
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
<body style="background-image: url('');width: 100%"> 
<script type="text/javascript">
 
 $(function (){
	$('.combo').each(function(){
		$(this).find('input').attr('readonly','true');
	});
 });
 
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

function fixWidth(percent){  
         return document.body.clientWidth * percent;  
 } 
//任务负责人的选择
function checkTaskOffpersonId(){
	var token=$('#tokens').val();
    var projectid=<s:property value="projectId"  />; 
	var taskPersonId=$('#taskPersonId').val();
	var result = $.ajax({
		type: 'POST',
		url: '<%=rootPath %>phone/select_user.action',
		data : {  'userIds':taskPersonId,
				  'projectId':projectid,
				  'token':token,
				  'pageNum':1},
	    async: false,
	    cache: false
	}).responseText; 
	OffPersonDialog=$.dialog({ 
 			id : 'selectUser',
			title:'选择Bug负责人',
			max:false,
			min:false,
			lock:true, 
			init : function(){  
				$('#sWord').searchbox({}); 
				 initDialogShow();
			},
			resize: false,
			width:fixWidth(0.8),
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

function addBug(){ 
	//alert('进入方法成功'); 
	var token=$('#tokens').val();
	var isSubmit=$('#addBugPage').form('validate');
	var projectId=<s:property value="projectId" />;
	var operatorId=$('#taskPersonId').val();
	var title=$('#bugTitle').val();
	if(isSubmit){
	$('input[name="jsonFile"]').val(JSON.stringify(addJsonFileList)); 
	var postStr=$('#addBugPage').serialize();
	/* var describe= encodeURI(editor.getData()); */
	var describe = $('#editor').val();
	$.ajax({
		url: '<%=rootPath%>phone/addBug.action?'+postStr,
		type: 'post',
	    async: true,
	    cache: false,
	    data:{
				'describe':describe,
				'bugTitle':title,
				'projectId':projectId,
				'token':token
			},
	    beforeSend:function (){
	    
	    },
	    success:function (data){
	    	$.dialog.tips('新建Bug成功',1,'32X32/succ.png',function(){ 
	    		//alert(projectId);
				 window.location.href='<%=rootPath%>phone/simplebuginfo.action?serianum='+data+'&token='+$('#tokens').val();
				});						
	    },
	});
	}
}
/***
* 上传附件
*/
//全局变量
var addJsonFileList = new Array();
var editor=null;
function tosaveAttacthed(){  
	var str=$.ajax({
		type:'post',
		dataType: "json",
		url:'<%=rootPath %>phone/client_to_save_attached.action?isqusertionordoc=2&token='+$('#tokens').val(), 
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

/*选中项目  */

</script>
<input type="hidden" value="<s:property value='tokens'/>" id="tokens"/>
<div class="bg01">
<div class="main-gr" style="background-image: url('');width: 100%;"> 
<!--左侧内容结束--> 
<!--右侧内容-->
<div class="right" style="border: none;width: 95%;text-align: left;padding-left: 5%;"> 
 
<form id="addBugPage"  method="post" onsubmit="return false;"  enctype="multipart/form-data">
 
	 	 <s:hidden  id="fileName" name="fileName"/>
	 	 <s:hidden  id="fileTrueName" name="fileTrueName"/>
	 	 <input type="hidden" name="jsonFile"/>
	 	 
	 	 
	 	<div style="width: 100%;float: left;"  class="newForm"> 
	 		<div style="width: 100%;"><label style="color: #171717;font-size: 18px;line-height: 40px;font-weight: normal;margin-left: 14%" >提交BUG（<font style="color: red;">*</font>必须填写项）</label></div>
	 		 
	    </div>
	    <div style="width: 100%;float: left;"  class="newForm"> 
	 		 
		 	<div class="proiterm"  style="width: 100%;">  
		  	<div class="txt_lptaskt"  style="width: 18%;float: left;line-height: 30px;text-align: right;">项目：</div>
		  	<!-- 
		 	<select disabled="disabled" name="projectId"  class="easyui-combobox"  style=" width: 154px; height: 34px; line-height: 34px;">  
			    <s:iterator var="proj" value="irpProjects">   
			    <option   value="<s:property value='#proj.projectid'/>"><s:property value="#proj.prname"/> </option>
			    </s:iterator>  
			</select>   
			 -->&nbsp;&nbsp;
			 <input  class=" easyui-validatebox"   value="<s:property value='irpProjects.get(0).prname'/>"  readonly="readonly"/> 
	        </div> 
        
	    </div>
	 	<div style="float: left;width: 100%;">&nbsp;</div><br/>
	 	<div  class="newForm" style="width: 100%;float: none;margin-top: 30px;"> 
			<div> 
				<div class="txt_lptaskt"  style="width: 18%;float: left;line-height: 30px;text-align: right;">Bug标题：</div>
				&nbsp;&nbsp;<input style="width: 140px" name="bugTitle1" class="artTitle easyui-validatebox" required="true" validtype="length[2,300]" placeholder="添加Bug标题"  id="bugTitle"   />  
			</div> 
		
		
        </div>
        <div style="float: left;width: 100%;">&nbsp;</div><br/>
        <div class="newForm"  style="width: 100%;float: none;margin-top: 20px;"> 
        
        <div class="txt_lptaskt"  style="width: 18%;float: left;line-height: 30px;text-align: right;">版本：</div> 
	    &nbsp;&nbsp;<select  name="versionid" class="easyui-combobox"  style=" float: left;width: 154px; height: 34px; line-height: 34px;"> 
         	 <s:iterator var="version" value="bugversions">
        			<option value="<s:property value="#version.bugconfigid"/>"><s:property value="#version.versionname" /></option>
       		 </s:iterator>
	    </select> 
	    
	    </div> 
	    
	    
	    <div    style="float: left;width: 100%;">&nbsp;</div><br/>
	    <div class="newForm"  style="width: 100%;float: none;margin-top: 20px;" >  
	    
	    <div class="txt_lptaskt"  style="width: 18%;float: left;line-height: 30px;text-align: right;">模块：</div>
	    &nbsp;&nbsp;<select id="bugprojectselect"  name="modulid" class="easyui-combobox"  style=" width: 154px; height: 34px; line-height: 34px;">
	              	 
             		<s:iterator var="modul" value="bugmoduls">
             			<option value="<s:property value='#modul.bugconfigid'/>"><s:property value="#modul.modulname" /></option>
             		</s:iterator>
	    </select>
	    
        </div>

		<div style="float: left;width: 100%;">&nbsp;</div><br/>
	    <div   style="width: 100%;float: none;margin-top: 20px;">  
	    	    <div class="txt_lptaskt"  style="width: 18%;float: left;line-height: 30px;text-align: right;">优先级：</div>
	    		
	    		<div style="line-height: 35px;">&nbsp;&nbsp;
	    		<input type="radio" name="priority" checked="checked" value="1" />&nbsp;低&nbsp;&nbsp;
	    		<input type="radio" name="priority" value="2" />&nbsp;中&nbsp;&nbsp;
	    		<input type="radio" name="priority" value="3" />&nbsp;高&nbsp;&nbsp;
	    		<input type="radio" name="priority" value="4" />&nbsp;紧急&nbsp;&nbsp;
	    		<input type="radio" name="priority" value="5" />&nbsp;严重&nbsp;&nbsp;
	    		</div>
	    </div>
	    <div style="float: left;width: 100%;">&nbsp;</div><br/>
        <div class="newForm"  style="width: 100%;float: none;margin-top: 20px;"> 
        	<div class="txt_lptaskt "  style="width: 18%;float: left;line-height: 30px;text-align: right;">处理人：</div>
        	&nbsp;&nbsp; 
				<input id="offPersonName" class="easyui-validatebox" readonly="readonly" onclick="checkTaskOffpersonId()"  fit=“true”  required="true"  type="text"/> 
				<input id="taskPersonId" type="hidden"  name="operatorId"/> 
        </div>
		<div style="float: left;width: 100%;">&nbsp;</div><br/>
		
	  	<div class="newForm"  style="width: 100%;float: none;margin-top: 20px;"> 
	  		<div class="txt_lptaskt"  style="width: 18%;float: left;line-height: 30px;text-align: right;">描述：</div>
	  		&nbsp;&nbsp; 
		    <div class="intro_ptaskt clearfix" style="background-image: url('');float: left;margin-left: -30px;">
				<textarea id="editor" name="editor" ></textarea>
	        	<%-- <script>
		        	editor= CKEDITOR.replace('editor',{
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
			    </script> --%>
			 </div>  
	  	
	  	</div>
	  	<div style="clear: both;"></div>
	  	<div class="newForm"  style="width: 100%;margin-top: 20px;"> 
	  		<div class="txt_lptaskt"  style="width: 18%;float: left;line-height: 30px;text-align: right;">附件：</div>
	  		&nbsp;&nbsp;
	  		 <div style="float: left; margin-left:10px;">
	  		 <!-- <input name="file"  class="easyui-validatebox" type="file" /> -->
 			<input type="button" id="btnnn"   onclick="tosaveAttacthed()" value="选择附件" />  
 			</div>
 		</div>
 		<div style="clear: both;"></div>
	  	<div class="newForm"  style="width: 100%;margin-top: 20px;"> 
	  		<!-- <div class="txt_lptaskt"  style="width: 22%;float: left;line-height: 30px;text-align: right;">是否发送私信：</div>
	  		&nbsp;&nbsp;
 			<input type="checkbox"  value="1" name=issendmessage /> -->
 		</div>
 		</form>
 		<div>
 		   	<div class="abc"  style="float:left;margin-left: 14%">
			    <a onclick="addBug()" id="addtaskbut" class="btn1 " title="发布" href="javascript:void(0);">发&nbsp;&nbsp;&nbsp;&nbsp;布</a>
 		   	</div>
 		</div>   	
 		   	
		<div style="float: left;width: 100%;">&nbsp;</div><br/>
</div>
</div>
</div>
</body>
</html>