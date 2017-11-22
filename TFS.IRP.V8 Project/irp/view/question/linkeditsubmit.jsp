<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.tfs.irp.question.entity.IrpQuestion"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- 创建词条页面 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>创建问答</title> 
<%-- <link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
<jsp:include page="../../client/include/client_skin.jsp" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/client/css/icon.css" />
<link href="<%=rootPath%>/client/css/oacss.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/client/css/easyui.css" />
<link rel="stylesheet" href="<%=rootPath%>client/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css" />
 <link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css"/>
<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css"/>
 <script type="text/javascript" src="<%=rootPath%>client/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/easyui-lang-zh_CN.js"></script>
	
<script type="text/javascript" src="<%=rootPath%>client/js/questionjson/json2.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.excheck-3.5.js"></script> --%>
	
<link rel="shortcut icon" href="<%=rootPath%>favicon.ico"
	type="image/x-icon" />
<link rel="bookmark" href="<%=rootPath%>favicon.ico" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/client/css/easyui.css" />
<link href="<%=rootPath%>client/images/normalize.css" rel="stylesheet"
	type="text/css" />
	<link href="<%=rootPath%>client/css/common1.css" rel="stylesheet"
	type="text/css" />
<link href="<%=rootPath%>client/css/oacss.css" rel="stylesheet"
	type="text/css" />
	<link rel="stylesheet" href="<%=rootPath%>client/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css" />
<script src="<%=rootPath%>client/images/modernizr.custom.js"></script>
<script src="<%=rootPath%>client/images/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/modernizr.custom.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/easyui-lang-zh_CN.js"></script>
	
<script type="text/javascript" src="<%=rootPath%>client/js/questionjson/json2.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<jsp:include page="../include/client_skin.jsp" />
<style type="text/css">
.STYLE1 {
	color: #0000FF;
	font-weight: bold;
	font-size: 18px;
}

.STYLE1 a:hover {
	color: #0000FF;
	font-weight: bold;
	font-size: 18px;
}

.question {
	background: url(<%=rootPath%>client/images/ico08.gif);
	width: 640px;
	height: 46px;
	padding: 5px;
	overflow: hidden;
	margin-bottom: 5px;
}

.expert {
	display: inline-block;
	height: 24px;
	padding: 0 10px;
	background: #A7D2F3;
	line-height: 24px;
	color: #fff;
}
.wtgy {
    height: 28px;
    line-height:28px;
    border: 1px solid rgb(209, 209, 209);
    color: rgb(102, 102, 102);
    padding: 0px 5px;
}
.photos{
	clear:both;
	float: left;
	position: relative;
	display:block;
	width: 430px;
}
.photos li{
	float: left;
	padding: 2px;
	margin-right:3px;
    position: relative;
    transition: border-color 0.2s ease-out 0s;
}
#picView{
	width:300px;
	display:block;
	float: left;
	position: relative;
}


ul.ztree {
	width:300px;
	height:350px;
	overflow:auto;
}
</style>

<style type="text/css">
body{behavior:url(hover.htc);}
.searchSec .radios span {
margin-top: 0px;
}

.title{
height: 35px;
line-height: 35px;
font-size: 15px;
color: #225491;
width: 300px;
}	
</style> 

  </head>
<body onload="" style="background: url()"> 
<script type="text/javascript">
var addJsonFileList = new Array();
 //标题字数控制
function questionTitleInfoControl(questionInfo,total){
	if(total-$.trim(questionInfo).length>=0){
		$('#titleAcreaNumChaochu').css({display:'none'});
		$('#titleAcreaNumfont').css({display:'block'});
		$('#titleAcreaNumfontNum').text(total-$.trim(questionInfo).length);
	}else{
		$('#titleAcreaNumfont').css({display:'none'});
		$('#titleAcreaNumChaochu').css({display:'block'});
		$('#titleerrorNumCount').text(Math.abs(total-$.trim(questionInfo).length));
	}
}
//字数限制
function questionInfoControl(questionInfo,total){
	if(total-$.trim(questionInfo).length>=0){
		$('#errorQuestionNum').css({display:'none'});
		$('#textAreaNum').css({display:'block'});
		$('#questionNum').text(total-$.trim(questionInfo).length);
	}else{
		$('#textAreaNum').css({display:'none'});
		$('#errorQuestionNum').css({display:'block'});
		$('#errorNumCount').text(Math.abs(total-$.trim(questionInfo).length));
	}
}
function resetValue(){
	$('#cate').val('');
	$('#cateIds').val('');
}
	
function resetExport(){
	$('#selectedExpertId').val('');
	$('#selectedExpert').val('');
}


//添加附件
function tosaveAttacthedafter(){  
	 var str=$.ajax({
			 type:'post',
			 dataType: "json",
			 url:'<%=rootPath %>site/client_to_save_attached.action?isqusertionordoc=1', 
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
	//关闭页面
	function closeWindow(){
		$.messager.confirm('提示信息','您确定要关闭当前页面吗？',function(r){
				if(r){
				   window.close();
				} 
		}); 
	}
	/**
	 * 选择问题的分类
	 */
	function showLeftTree(){
		var categoryName = $('#cateIds').val();
		var result = $.ajax({
			url:"<%=rootPath%>category/getLeftCategory.action?categoryName="+categoryName,
			cache:false,
			async:false
		}).responseText;
		$.dialog({
	  		content:result,
	  		title:'选择分类',
	  		min:false,
	  		max:false,
		    lock:true,
		    ok:function(){
		    },
		    okVal:'确认',
//		    cancelVal: '取消',
//		    cancel: true,
		    padding: 0
	  	});
	 }
	 
	//提问
function publish(total) {
	var category = $('#cateIds').val();
	$('#questionpublishfb').attr("onclick","");
	var questiontitle = $('#questiontitle').val();
	var expertId = $('#selectedExpertId').val();
	var question_info = $('#question_info').val();
	var cate = $("#cate").val();
	if(questiontitle == "在此输入问题概要,150字以内"){
		questiontitle="";
	}
	if($.trim(questiontitle)=="" && $.trim(question_info) == ""){
		$.dialog.tips("标题或者内容必须填写一项",1.5,"alert.gif");
		$('#questionpublishfb').attr("onclick","publish(300)");
		return false;
	}else{
		if(questiontitle.length>150){
			$('#questionpublishfb').attr("onclick","publish(300)");
			$.dialog.tips("请填写150字以下的标题内容",1,"alert.gif");
			return false;
		}else if(question_info.length>total){
			$('#questionpublishfb').attr("onclick","publish(300)");
			$.dialog.tips("请填写"+total+"字以下的问题内容",1,"alert.gif");
			return false;
		}else if (cate==""){
			$('#questionpublishfb').attr("onclick","publish(300)");
			$.dialog.tips("请选择分类",1,"alert.gif");
			return false;
		}else{
			$.ajax({
				type : "POST",
				url : '<%=rootPath%>question/ask.action',
				dataType : "json",
				cache:false,
				async:false,
				data : {
					questionInfo : question_info,
					expertId : expertId,
					jsonFile : JSON.stringify(addJsonFileList),
					questiontitle : questiontitle,
					categories : category
				},
				success : function(nMsg) {
					$('#questiontitle').val("");
					$('#question_info').val("");
					if (nMsg = 1) {
						$.dialog.tips('提问成功',1,'32X32/succ.png');
					   window.close();
					} else {
						$.dialog.tips('提问失败', 1, '32X32/fail.png');
					}
					$('#questionpublishfb').attr("onclick","publish(300)");
				}
			});
		}
	}
} 
//选择专家
function selectExpert(){
	//获得专家
	var result = $.ajax({
		url: '<%=rootPath%>expert/selectExpert.action',
		type:"post",
		dataType: "json",
	    async: false
	}).responseText;
	
	$.dialog({
		title:'选择专家',
		max:false,
		min:false,
		lock:true,
		resize: false,
		content:result,
		cancelVal:'关闭',
		okVal:'确定',
		cancel:function(){
			$("#checkBox").attr("checked",false);
			//$("#selectedExpert").hide();
		},
		ok:function(){
			var expertId,expertName;
			var expertTab = $('.selectedExpert');
			if(expertTab){
				expertId = expertTab.attr('_eid');
				expertName = expertTab.attr('_ename');
			}
			if(expertName==undefined){
				$("#checkBox").attr("checked",false);
				$("#selectedExpert").val("");
			}else{
				$("#checkBox").attr("checked",true);
				$("#selectedExpert").val(expertName);
				$("#selectedExpertId").val(expertId);
			}
		}
	});
	//$("#selectedExpert").show().addClass('expert');
	//$("#selectedExpert").val(expertName);
}
	 
</script>
	
	<jsp:include page="../../view/include/client_head.jsp" />
	<div class="bg01">
   		<section class="mainBox">
			<nav class="privateNav">
			</nav>
		</section>
		
		<div class="main"  style="width:1200px;background:none;" style="background: url('');">
		<section class="newForm" style="width:870px;float:left;">
		<article class="location"><strong>创建问答（<span style="color:#C00">*</span>必须填写项）</strong></article>
    		<!-- 构建提问框 --> 
    		<li id="titleAcreaNumfont" style="text-align: right;width: 300px;float: right;">
					您还可以输入<label id="titleAcreaNumfontNum" style="font-family:Tahoma,Arial,sans-serif; color: #f28933;font-size: 18px;">150</label>个字
				</li>
				<li id="titleAcreaNumChaochu" style="text-align: right;display: none;width: 300px;float: right;">
						超出了 <label id="titleerrorNumCount" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color:red;"></label>个字
				</li>
	    	 	<div class="title" >请输入标题:
				</div>
				 
    	 	<div id="div_top_title_01" style="border: thin solid #BEBEBE;height: 70px;border-radius:3px;" >
    	 		<textarea id="questiontitle" onkeyup="questionTitleInfoControl(this.value,150)" style="width: 100%;height: 70px;border: none;line-height: 20px;" onfocus="text_border_title(1)" onblur="text_border_title(2)"></textarea>
    	 	</div>
    	 	  <li id="textAreaNum" style="text-align: right;width: 300px; display: block;float: right;padding-top: 3px;">
				您还可以输入 <label id="questionNum"
				style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color: #f28933;">300</label>
				个字</li>
			<li id="errorQuestionNum" style="text-align: right;display: none;width: 300px;float: right;padding-top: 3px;">
				超出了 <label id="errorNumCount"
				style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color:red;">
			</label>个字</li>
    	 	<div class="title">请输入内容:</div>
	     	<div id="div_top_cont_02" style="border: thin solid #BEBEBE;height: 130px;border-radius:3px;" >
	     		<textarea  id="question_info" onkeyup="questionInfoControl(this.value,300)" style="width: 100%;height: 129px;border: none;line-height: 20px;" onfocus="text_border_cont(1)" onblur="text_border_cont(2)"></textarea>
	     	</div>
	     	
     	<script type="text/javascript">
     	//title click
     	function text_border_title(_val){
     		if(_val==1){
     			$("#div_top_title_01").css("border","thin solid #296195");
     		}else if(_val==2){
     			$("#div_top_title_01").css("border","thin solid #BEBEBE"); 
     		} 
     	}  
     	//body click
		function text_border_cont(_val){ 
		     		if(_val==1){
		     			$("#div_top_cont_02").css("border","thin solid #296195");
		     		}else if(_val==2){
		     			$("#div_top_cont_02").css("border","thin solid #BEBEBE"); 
		     		} 
		} 
		</script>
		
		<!-- 
		<div class="title" style="margin-top: 20px;margin-bottom: 30px;">
			<table style="width: 900px;">
			<tr>
				<td style="width:70px;" class="title">选择类别: </td>
				<td style="width:220px;"><input onclick="showLeftTree()" id="cate" type="text" readonly="readonly" value="" style="width:220px;height: 30px;border: thin solid #BEBEBE;border-radius:3px;"/>
				<input id="cateIds" type="hidden" readonly value=""/></td>
				<td style="width:60px;">
					<a  href="javascript:void(0)" onclick="resetValue()">
						<font face="微软雅黑" color="#4b9bc0" style="font-size: 15px;">重置</font>
					</a>
				</td>
				
				<td  style="width:70px;padding-left: 20px;" class="title">选择专家:</td>
				<td style="width:230px;"><input onclick="selectExpert()" id="selectedExpert" type="text" readonly="readonly" value="<s:property value='expertName' />" style="width:220px;height: 30px;border: thin solid #BEBEBE;border-radius:3px;"/>
				<input type="hidden" id="selectedExpertId" value="<s:property value='expertId' />"/></td>
				<td style="width:60px;">
					<a  href="javascript:void(0)" onclick="resetExport()">
						<font face="微软雅黑" color="#4b9bc0"  style="font-size: 15px;">重置</font>
					</a>
				</td>
				
				<td  style="width:90px;padding-left: 20px;" class="title">
				<a id="addattracha" href="javascript:void(0)" onclick="tosaveAttacthedafter()"><font face="微软雅黑" color="#4b9bc0">添加附件</font></a>
					</td>
			</tr>
			</table>
		</div>
		 -->
		 <div class="btns" style="margin-top:25px;padding-right:175px;text-align:right;">
			<input type="button" id="questionpublishfb" onclick="publish(300)" class="btn" onclick="updatedocument()" value="保&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;存" />
			<input type="button" onclick="closeWindow()" class="btn" onclick="updatedocument()" value="关&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;闭" />
			<!-- 
			<input value="保存" id="questionpublishfb" onclick="publish(300)" style="margin:10px 10px 10px 0;border-radius:3px; font-size:18px; width: 150px;height:50px;color: white;background-color: #62a53b;" type="button"  >
			<input value="关闭" onclick="closeWindow()" style="margin:10px 0 10px 10px;border-radius:3px; font-size:18px; width: 150px;height:50px;color: white;background-color: #62a53b;" type="button"  >
			  -->
		</div> 
		</section>
		<section class="layoutRight formInfo">
			<div class="area10"></div>
				<section>
					<div class="title">选择类别<span class="must" style="float:none;">*</span></div>
					<input onclick="showLeftTree()" id="cate" type="text" readonly="readonly" value="" style="width:220px;height: 30px;border: thin solid #BEBEBE;border-radius:3px;"/>
					<input id="cateIds" type="hidden" readonly value=""/>
					<a  href="javascript:void(0)" onclick="resetValue()">
						<font face="微软雅黑" color="#4b9bc0" style="font-size: 15px;">重置</font>
					</a>
					<div class="area10"></div>
					<div class="area10 line"></div>
					<div class="title">选择专家</div>
					<input onclick="selectExpert()" id="selectedExpert" type="text" readonly="readonly" value="<s:property value='expertName' />" style="width:220px;height: 30px;border: thin solid #BEBEBE;border-radius:3px;"/>
					<input type="hidden" id="selectedExpertId" value="<s:property value='expertId' />"/>
					<a  href="javascript:void(0)" onclick="resetExport()">
						<font face="微软雅黑" color="#4b9bc0"  style="font-size: 15px;">重置</font>
					</a>
					<div class="area10"></div>
					<div class="area10 line"></div>
					<div class="title">附件管理</div>
					<input type="button" class="artFile" id="addattracha" href="javascript:void(0)" onclick="tosaveAttacthedafter()" value="选择附件" style="width:100px;"></input>
					
					<div class="area10"></div>
				</section>
		</section>
		</div>
		
		
		
		
		
		<footer>
		<s:include value="../include/client_foot.jsp"></s:include></footer>
	</div>
</body>
</html>