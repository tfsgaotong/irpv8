<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.tfs.irp.question.entity.IrpQuestion"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>图片投票细览</title>
	<link rel="shortcut icon" href="<%=rootPath%>favicon.ico" type="image/x-icon" />
	<link rel="bookmark" href="<%=rootPath%>favicon.ico" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/normalize.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/common1.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/oacss.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/icon.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/easyui.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/vote.css"></link>
	<script type="text/javascript" src="<%=rootPath%>view/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/js/lhgdialog.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/js/jquery.uploadify.min.js"></script>
	<jsp:include page="../../view/include/client_skin.jsp" />
	
	<style type="text/css">
	body {
		behavior: url(hover.htc);
	}
	
	.STYLE1 {
		color: #0066FF;
		font-weight: bold;
	}
	
	.bar {
		background: none repeat scroll 0 0 #F0F0F0;
		float: left;
		height: 11px;
		margin-top: 2px;
		width: 100px;
		border: 1px solid red;
	}
	
	input,select,textarea {
		border: 1px solid #CCCCCC;
	}
	</style>
</head>

<script type="text/javascript">
var maxId=3;
var strRegex = "^(http|https|ftp)\\://([a-zA-Z0-9\\.\\-]+(\\:[a-zA-"   
    + "Z0-9\\.&%\\$\\-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{"   
    + "2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}"   
    + "[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|"   
    + "[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-"   
    + "4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0"   
    + "-9\\-]+\\.)*[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,4})(\\:[0-9]+)?(/"   
    + "[^/][a-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)*$"; 
var re = new RegExp(strRegex);
$(function(){
	$("#starttime").datetimebox({editable:false});  
	$("#endtime").datetimebox({editable:false});  
	//引入扩展验证
	$.extend($.fn.validatebox.defaults.rules, {
	    EndTime:{
	    	validator:function(){
	    		try {
	    			if($('#endtime').datetimebox('getValue')>$('#starttime').datetimebox('getValue')){
		    			return true;
		    		}else{	  
		    			return false;
		    		}
				} catch (e) {
				}
	    	},
	    	message:'结束日期必须在开始日期之后'
		 }
	}); 
	 var imgleng=$("div[class='bt']").length;
	 for(var i=0;i<imgleng;i++){
		var fileid= $("div[class='bt']:eq("+i+")").children("input[type='file']").attr("id");
		loadimg($("#"+fileid));
	 }
	 showzoomimg();
});

function showzoomimg(){
	 var len=$("body div[class='photo']").length;
	 for(var i=0;i<len;i++){
		 var imgname=$("body div[class='photo']:eq("+i+")").find("img").attr("name");
		 var index=imgname.lastIndexOf("."); 
		 var newimgname=imgname.substring(0,index)+"_150X150"+imgname.substring(index,imgname.length);
		 $("body div[class='photo']:eq("+i+")").find("img").attr("src","<%=rootPath%>file/readfile.action?fileName="+newimgname);
	 }
}
function checkoneormore(_value){
	if($("#"+_value).val()==2){
		$("#"+_value).next().show();
		$("#"+_value).next().next().show();
		
	}else{
		$("#"+_value).next().hide();
		$("#"+_value).next().next().hide();
	}
}

//update
 function updatetitle(){
	 var fag=false;
		var startd=$("#starttime").datetimebox('getValue');
		var endd=$("#endtime").datetimebox('getValue');
		var startDate =  new  Date(startd.replace(/-/g,"/"));
		var endDate =  new  Date(endd.replace(/-/g,"/"));  
		var todayTime= new Date(<%=new java.util.Date().getTime()%>); 
		var title=$.trim($("#title").val());
		var desc=$.trim($("#votedescription").val());
		
		if(title==""){
			fag=false;
			$.dialog.tips('投票标题或投票问题为空',1,'32X32/fail.png');
		}else if(title.length>100||title.length<2||desc.length>200){
			fag=false;
			$.dialog.tips('投票标题或说明长度不符合',1,'32X32/fail.png');
		}else{
			if($.trim(startd)==""||$.trim(startd)==""){
				fag=false;
				$.dialog.tips('日期不能为空',1,'32X32/fail.png');
			}else if(todayTime>startDate){
				fag=false;
				$.dialog.tips('开始不能小于今天',1,'32X32/fail.png');
			}else if(startd>endd){
				fag=false;
				$.dialog.tips('结束时间不能小于开始时间',1,'32X32/fail.png');
			}else{
				fag=true;
			}
		}
		if(fag){
			 var queryString = $('#titleform').serialize();
			 var voteid=$("#voteid").val();
			 $.ajax({
					type:'get',
					url:"<%=rootPath%>menu/vote_updatetitle.action?irptitle.voteid="+voteid+"&"+queryString,
					dataType: "json",
					async: false,
			   		cache: false,
			   		success:function(data){
			   		    $.dialog.tips(data,1.5,"32X32/succ.png");
			   		}
				});
		}
 }
//取值
 function updateoptions(_index){
	var optionlength=$("#"+_index).children("div[class='px_list']").length;
	var jsonoption="[";
	var fag=false;
	for(var i=0;i<optionlength;i++){
		var vid=$("#"+_index).children("div[class='px_list']:eq("+i+")").find("input[name='voteoption']").attr("id");
		var value=$("#"+_index).children("div[class='px_list']:eq("+i+")").find("input[name='voteoption']").val();
		var urlvalue=$("#"+_index).children("div[class='px_list']:eq("+i+")").find("input[name='optionurl']").val();
		var picvalue=$("#"+_index).children("div[class='px_list']:eq("+i+")").find("div[class='photo']").children("img").attr("name");
		var picname=picvalue;
		if($.trim(value)==null||$.trim(value)==""){
			$.dialog.tips('请填写选项',1,'32X32/fail.png');
			fag=false;
			break;
		}else if($.trim(value).length>200||$.trim(value).length<1){
			$.dialog.tips('选项长对应该在1到200之间',1,'32X32/fail.png');
			fag=false;
			break;
		}else if($.trim(picvalue)=="imgattr"){
			$.dialog.tips('请先上传图片',1,'32X32/fail.png');
			fag=false;
			break;
		}else{
			if($.trim(urlvalue)==null||$.trim(urlvalue)==""){
				urlvalue=null;
				if(i==0){
					jsonoption+="{\"id\":\""+vid+"\",\"value\":\""+value+"\",\"urlvalue\":\""+urlvalue+"\",\"picname\":\""+picname+"\"}";
				}else{
					jsonoption+=",{\"id\":\""+vid+"\",\"value\":\""+value+"\",\"urlvalue\":\""+urlvalue+"\",\"picname\":\""+picname+"\"}";
				}
				fag=true;
			}else{
				if(re.test($.trim(urlvalue))){
					if(i==0){
						jsonoption+="{\"id\":\""+vid+"\",\"value\":\""+value+"\",\"urlvalue\":\""+urlvalue+"\",\"picname\":\""+picname+"\"}";
					}else{
						jsonoption+=",{\"id\":\""+vid+"\",\"value\":\""+value+"\",\"urlvalue\":\""+urlvalue+"\",\"picname\":\""+picname+"\"}";
					}
					fag=true;
				}else{
					 fag= false;
					$.dialog.tips('请输入有效网址',1,'32X32/fail.png');
				}
			}
		}
	 }
	 jsonoption+="]";
	if(fag){
		var queryString = $('#votetitleform'+_index).serialize();
		 $.ajax({
				type:'post',
				data:{'soptionstr':jsonoption},
				url:"<%=rootPath%>menu/vote_titleupdate.action?irpvotetitle.voteid="+_index+"&"+queryString,
				dataType: "json",
				async: false,
		   		cache: false,
		   		success:function(data){
		   		    $.dialog.tips(data,1.5,"32X32/succ.png");
		   		    refesh();
		   		}
			});	
	}
 }
//del
 function voteDel(_index){
	 var leng= $("body").find("div[class='clear']").length;
	 if(leng>1){
		 $.dialog.confirm('你确定要删除这个投票分组吗',function(){
			 $.ajax({
					type:'post',
					url:"<%=rootPath%>menu/votetitle_del.action?voteid="+_index,
					dataType: "json",
					async: false,
			   		cache: false,
			   		success:function(data){
			   		    $.dialog.tips(data,1.5,"32X32/succ.png");
			   		    refesh();
			   		}
				});
		 });
	 }else{
		 $.dialog.tips("至少要有一个分组",1.5,"32X32/fail.png");
	 }
 }
 //添加选项
 function addoptions(_index){
	 var result = $.ajax({
			url: '<%=rootPath%>view/vote/vote_pic_addoption.jsp',
			dataType: "json",
		    async: false,
		    cache: false
		}).responseText;
	 $.dialog({
			title:'添加选项',
			content: result,
			max: false,
		    min: false, 
		    lock:true,
		    width:500,
		    height:200,
		    ok: function(){
		    	var fag=true;
		    	var optionurl=$("#addurl").val();
		    	var option=$("#addoption").val();
		    	var fileimg=$("#fileimg").attr("name");
		    	if($.trim(option)==""||$.trim(option)==null){
		    		 $.dialog.tips("请填写选项",1.5,"32X32/fail.png");
		    		 fag= false;
				}
		    	if($.trim(fileimg)=="imgattr"){
		    		 $.dialog.tips("请上传图片",1.5,"32X32/fail.png");
		    		 fag= false;
				}
		    	if($.trim(option).length>200||$.trim(option).length<1){
		    		 $.dialog.tips("选项长对应该在1到200之间",1.5,"32X32/fail.png");
		    		 fag= false;
				}
		    	if($.trim(optionurl)==""||$.trim(optionurl)==null){
					
				}else{
					if(re.test($.trim(optionurl))){
						
					}else{
						 fag= false;
						$.dialog.tips('请输入有效网址',1,'32X32/fail.png');
					}
				}
		    	if(fag){
		    		$.ajax({
			    		type:"post",
			    		dataType: "json",
			    		data:{'soptionstr':option,'urloption':optionurl,'jsonoptionimg':fileimg,'voteid':_index},
			    	    async: false,
			    	    cache: false,
			    		url:"<%=rootPath%>menu/option_add.action",
			    		success:function(data){
			    			$.dialog.tips(data,1,'32X32/succ.png');
			    		}
			    	});
			    	refesh();
		    	}
		    	return fag;
		    },
		    okVal:'完成',
		    cancelVal: '关闭',
		    cancel: true,
		    padding: 0
		});
 }
//刷新
 function refesh(){
 	var vid=$("#voteid").val();
 	var urlvote="<%=rootPath%>menu/vote_updatevote.action?voteid="+vid;
  	 window.location.href=urlvote;
 }
 //
function loadimg(value){
	var priImg = value.parent().parent().find("div[class='photo']").find("img");
	value.uploadify({
		'auto': true,
		'multi' : false,
		'swf' : '<%=rootPath%>view/js/uploadify.swf',
		'uploader' : '<%=rootPath%>microblog/microblogContentPic.action;jsessionid=<%=session.getId() %>',
		'formData':{
            'userId':'1'
        },
		'queueID' : 'fileList',
		'fileObjName' : 'picFile',
		'buttonImage':'<%=rootPath%>view/images/addicon.jpg',
		'width' : "20",
		'height' : "20",
		'removeCompleted': true,
		'removeTimeout': 0.1,
		'uploadLimit':10,
		'queueSizeLimit': 1,      //允许同时上传文件数量
		'fileSizeLimit' : "10MB",
		'fileTypeExts': '*.jpg;*.jpeg;*.gif;*.png',
        'fileTypeDesc': '图片文件',
        'onUploadStart' : function(file){
        	$.dialog.tips('图片上传中...',600,'loading.gif');
        },
		'onUploadSuccess' : function(file, data, response){	
			priImg.attr("src","<%=rootPath%>file/readfile.action?fileName="+data);
			priImg.attr("name",data);
			$.dialog.tips('图片上传完毕',1,'32X32/succ.png');
		},
        'onUploadError': function (file, errorCode, errorMsg, errorString) {    //错误提示 
        	$.dialog.tips('数据加载完毕',1,'32X32/fail.png',function(){
				alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
			});
        }
	});
}
//delete 
 function deleteoptions(_index){
	 var len=$("#"+_index+"[type='text']").parent().parent().parent().find("div[class='px_list']").length;
	 var minsize=2;
	 if(len>minsize){
		 $.dialog.confirm('你确定要删除这个投票选项吗',function(){
			 $.ajax({
					type:'post',
					url:"<%=rootPath%>menu/voteoption_delete.action?voteid="+_index,
					dataType: "json",
					async: false,
			   		cache: false,
			   		success:function(data){
			   		    $.dialog.tips(data,1.5,"32X32/succ.png");
			   		   //刷新
			   		   $("#"+_index+"[type='text']").parent().parent().parent().remove();
			   		   refesh();
			   		}
				});
		 });
	 }else{
		 $.dialog.tips("至少要有两个选项",1.5,"32X32/fail.png");
	 }
 }
 
//添加分组
 function addGroup(){
	 var result = $.ajax({
			url: '<%=rootPath%>view/vote/vote_pic_addgroup.jsp',
			dataType: "json",
		    async: false,
		    cache: false
		}).responseText;
	 $.dialog({
			title:'添加分组',
			content: result,
			max: false,
		    min: false, 
		    lock:true,
		    width:500,
		    height:200,
		    ok: function(){
		    	var fag=submitaddGroup();
		    	if(fag){
		    		refesh();
		    	}
		    	return fag;
		    },
		    okVal:'提交',
		    cancelVal: '关闭',
		    cancel: true,
		    padding: 0
		});
 }
 //add
 function submitaddGroup(){
	 var fag=false;
	 var pid=$.trim($("#voteid").val());
	 var votetitle=$.trim($("#votetitle").val());
	 if(votetitle==""){
		fag=false;
		$.dialog.tips('投票问题为空',1,'32X32/fail.png');
	 }else if(votetitle.length>100||votetitle.length<2){
		fag=false;
		$.dialog.tips('投票问题长度不符合',1,'32X32/fail.png');
	 }else{
		var opvalue=$("#optionul li input[name='option']");
		var opimgattr=$("#optionul li img");
		var jsonoption="";
		var jsonoptionimg="";
		var urloption="";
		for(var i=0;i<opvalue.length;i++){
			var option=$("#optionul li:eq("+i+") input[name='option']").val();
			var attrimg=$("#optionul li:eq("+i+") img").attr("name");
			var optionurl=$("#optionul li:eq("+i+") input[name='urltext']").val();
			if($.trim(option)==null||$.trim(option)==""){
				$.dialog.tips('请填写选项',1,'32X32/fail.png');
				fag=false;
				break;
			}else if($.trim(option).length>200||$.trim(option).length<1){
				$.dialog.tips('选项长对应该在1到200之间',1,'32X32/fail.png');
				fag=false;
				break;
			}else if($.trim(attrimg)=="imgattr"){
				$.dialog.tips('请先上传图片',1,'32X32/fail.png');
				fag=false;
				break;
			}else{
				if($.trim(optionurl)==""||$.trim(optionurl)==null){
					optionurl=null;
					urloption+=optionurl+",";
					jsonoption+=option+",";
					jsonoptionimg+=attrimg+",";
					fag=true;
				}else{
					if(re.test($.trim(optionurl))){
						urloption+=optionurl+",";
						jsonoption+=option+",";
						jsonoptionimg+=attrimg+",";
						fag=true;
					}else{
						fag=false;
						$.dialog.tips('请输入有效网址',1,'32X32/fail.png');
					}
				}
			}
		 }
		var queryString = $('#addgroupform').serialize();
		if(fag){
			var pid=$("#voteid").val();
			$.ajax({
				type:"post",
				dataType:"json",
				data:{'soptionstr':jsonoption,'urloption':urloption,'jsonoptionimg':jsonoptionimg,'voteid':pid},
			    async: false,
			    cache: false,
				url:"<%=rootPath%>menu/save_picvotesecond.action?"+queryString,
				success:function(data){
					$("#firstul").empty();
					$.dialog.tips(data,1.5,'32X32/succ.png');
				}
			});
		}
	}
	 return fag;
 }
//发布
 function publishvote(){
	 $.dialog.confirm('你确定要发布投票吗',function(){
		 var vid=$("#voteid").val();
		 $.ajax({
	 			type:'get',
	 			url:'<%=rootPath%>menu/vote_publish.action?voteid='+vid,
	 			dataType: "json",
	 			async: false,
	 	   		cache: false,
	 	   		success:function(data){
	 	   		    $.dialog.tips(data,1.5,"32X32/succ.png");
	 	   		 var votetitle=$.trim($("#title").val());
	 	   		    var vid=$.trim($("#voteid").val());
	 	   		    var context="我刚发起了 "+votetitle+" 的投票，大家快来看看吧";
			   		var rsmic="<a class=\"linkbh14\" target=\"_blank\" href=\"<%=rootPath%>menu/vote_detil.action?voteid="+vid+"\" >"+votetitle+"</a>";
			   		sendmic(context,rsmic);
	 	   		}
	 		});
		 
	 })
 }
 
 function sendmic(microblog_text,urltext){
	 var result=" <textarea rows=\"1.5\" cols=\"36\" style=\"width: 400px;height: 100px;\" id=\"votemictext\">"+microblog_text+"</textarea>";
	 $.dialog({
			title:'发投票微知',
			content: result,
			max: false,
		    min: false, 
		    lock:true,
		    width:500,
		    height:200,
		    cancel:function(){ 
	   		},
		    cancelVal: '关闭',
		    ok: function(){
		    	if($.trim($("#votemictext").val())==""){
		    		$.dialog.tips('微知不能为空',1,'32X32/fail.png');
		    	}else{
		    		var microblog_type=0;
		    		var microblog_text= $("#votemictext").val()+urltext;
	    			var microbloggroup = "公开";
	    			$.ajax({
	    				type:"POST",
	    				url:'<%=rootPath%>microblog/microblogShare.action',
	    				cache:false,
	    				data:{
	    					publishInfo:microblog_text,
	    					microblogType:microblog_type,
	    					microbloggroup:microbloggroup
	    				},
	    				success:function(callback){
	    					if(callback!=null){
	    						$.dialog.tips('分享成功',1,'32X32/succ.png');
	    					}else{
	    						$.dialog.tips('分享失败',1,'32X32/fail.png'); 
	    					}
	    					setTimeout("window.close()", 1500);
	    				}
	    			});
		    	}
		    },
		    okVal:'发投票微知',
		    padding: 0
		});
}
 function closewindow(){
	 $.dialog.confirm('你确定要离开这个页面吗',function(){
		 setTimeout("window.close()",0);
	 });
 }
</script>
<body onload="selected('votepage')" style="background: url()">
	<s:hidden name="voteid" id="voteid" />
	<div class="bg01">
		<!--头部菜单-->
		<jsp:include page="../../view/include/client_head.jsp" />
		<section class="mainBox"> <nav class="privateNav" style="background:url(../images/bg-04.gif) repeat-x"> </nav> </section>
		<!--头部菜单end-->
		<div class="main-gr" style="background-image: none;min-height: 55vh;">
			<!--右侧内容结束  -->
			<div>
				<div class="touPiao">
					<form id="titleform">
						<div class="group_list">
							<h1>
								<input style="width: 400px;height: 40px;" id="title" name="irptitle.title" type="text" class="inputvote" value="<s:property value="irptitle.title" />" />
							</h1>
							<span>
								创建日期：
								<s:date name="irptitle.crtime" format="yyyy-MM-dd HH:mm" />
								&nbsp;&nbsp;&nbsp;&nbsp; 开始日期：
								<input id="starttime" name="irptitle.starttime" data-options="required:true,showSeconds:false" value="<s:date name="irptitle.starttime"  format="yyyy-MM-dd HH:mm"/>" style="width:150px" />
								结束日期：
								<input id="endtime" name="irptitle.endtime" data-options="required:true,showSeconds:false" value="<s:date name="irptitle.endtime"  format="yyyy-MM-dd HH:mm"/>" style="width:150px" />
							</span>
							<ul style="font-size: 14px;">
								<li>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<textarea id="votedescription" style="font-size: 12px;" name="irptitle.description" rows="2" cols="140"><s:property value="irptitle.description" /></textarea>
								</li>
							</ul>
							<div style="margin-left: 680px;">
								<input style="background-color: lightblue;" class="btn_touPiao" type="button" value="修改/保存" onclick="updatetitle()" />
								&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="javascript:void(0)" title="添加分组" onclick="addGroup()">添加分组</a>
							</div>
						</div>
					</form>
					<!--分组内容-->
					<s:iterator value="maplist" status="mapliststatus">
						<!-- 跌代第一个map -->
						<s:set var="votekey" value="key" />
						<form id="votetitleform<s:property value='#votekey.voteid'/>">
							<span>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<s:property value="#mapliststatus.index+1" />
								.
								<input style="width: 580px;height: 35px;" type="text" name="irpvotetitle.votetitle" value="<s:property value='#votekey.votetitle'/>" />
								&nbsp;&nbsp;&nbsp;&nbsp;
								<s:if test="#votekey.checktype==1">单选</s:if>
								<s:else>复选    
									<s:if test="#votekey.lesscheck>1"> 
								      	至少选<font color="red">&nbsp;<s:property value="#votekey.lesscheck" /></font> 项
								    </s:if>
									<s:if test="#votekey.morecheck>1">     
								                    至多选<font color="red">&nbsp;<s:property value="#votekey.morecheck" /></font> 项
								    </s:if>
								</s:else>
								<select name="irpvotetitle.checktype" onchange="checkoneormore(this.id)" id='select<s:property value="#mapliststatus.index+1"/>'>
									<option value='<s:property value="#votekey.checktype"/>'>请选择</option>
									<option value="1">单选</option>
									<option value="2">复选</option>
								</select>
								&nbsp;
								<span id="lesscheck" style="display:none;">
									至少选
									<input style="width: 30px;" value='<s:property value="#votekey.morecheck"/>' type="text" name="irpvotetitle.lesscheck" />
									个
								</span>
								&nbsp;
								<span id="morecheck" style="display:none;">
									至多选
									<input style="width: 30px;" value='<s:property value="#votekey.morecheck"/>' type="text" name="irpvotetitle.morecheck" />
									个
								</span>
							</span>
							<img style="cursor: pointer;" onclick="addoptions(<s:property value='#votekey.voteid'/>)" title="添加选项" style="margin-left: 0px;margin-top: 4px;" width="20px;" height="20px;"
								style="cursor: pointer;" src="<%=rootPath%>view/images/addicon.jpg" />
						</form>
						<div id="<s:property value='#votekey.voteid'/>">
							<s:iterator value="value" status="status">
								<div class="px_list">
									<div class="photo">
										<img src="" name="<s:property value='attimg'/>" />
									</div>
									<div id="rock" class="bt" style="width: 180px; ">
										选项
										<input style="width: 170px;height: 30px;" type="text" id="<s:property value='optionid'/>" name="voteoption" value=" <s:property value='voteoption'/>" />
										URL
										<input style="width: 170px;height: 30px;" type="text" name="optionurl" value=" <s:property value='optionurl'/>" />
										<input id="fileimg<s:property value='optionid'/>" style="display: none;" type="file" title="修改图片" />
										<img style="cursor: pointer;margin-left: 50px;margin-top: -20px;" onclick="deleteoptions(<s:property value='optionid'/>)" title="删除选项" style="float:right;margin-top: -17px;" width="20px;"
											height="20px;" src="<%=rootPath%>view/images/deleteicon.jpg" />
									</div>
								</div>
							</s:iterator>
						</div>
						<div class="clear"></div>
						<div style="margin-left: 680px;">
							<input class="btn_touPiao" onclick="updateoptions(<s:property value='#votekey.voteid'/>)" type="button" value="修改/保存" />
							&nbsp;
							<a href="javascript:void(0)" title="删除分组" onclick="voteDel(<s:property value='#votekey.voteid'/>)">删除</a>
						</div>
					</s:iterator>
					<div style="margin-left: 300px;">
						<input class="btn_touPiao" onclick="publishvote()" type="button" value="发布" />
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input class="btn_touPiao" onclick="closewindow()" type="button" value="关闭" />
					</div>
				</div>
			</div>
		</div>
		<!-- end -->
		<s:include value="../../view/include/client_foot.jsp"></s:include>
	</div>
</body>
</html>