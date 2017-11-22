<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.tfs.irp.util.LoginUtil,com.tfs.irp.user.entity.IrpUser"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="<%=rootPath%>/client/css/oacss.css" rel="stylesheet"type="text/css" />
<link href="<%=rootPath%>/client/css/easyui.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath%>/client/css/icon.css" rel="stylesheet" type="text/css"  />
<link rel="stylesheet" href="<%=rootPath%>client/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css" />
<jsp:include page="../include/client_skin.jsp" />
<link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css"/>
	<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css"/>
<style type="text/css">

</style>
<script type="text/javascript" src="<%=rootPath%>/client/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/client/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript">
$(function(){
	//选择头部标签
	
});
/**
* 进入到试卷内部
*/
function linkPaperContent(_paperid){
	window.open("<%=rootPath%>exam/linksingleques.action?paper="+_paperid);
}
/**
* 改变状态
*/
function changeY(_this){
	if(_this.id=="nocanexam"){
		$("#nocanexam").addClass("over");
		$("#yescanexam").removeClass();
		window.location.href="<%=rootPath%>exam/exammenu.action?pagenumpaper=1";
	}else if(_this.id=="yescanexam"){
		$("#yescanexam").addClass("over");
		$("#nocanexam").removeClass();
		window.location.href="<%=rootPath%>exam/alreadyexammenu.action?pagenumpaper=1";		
	}	
}
/**
* 参加考试
*/
function visitExam(_content,_examid){
	$.ajax({
		type:'post',
		url:"<%=rootPath%>exam/joinexam.action",
		async:false,
		cache:false,
		data:{exam:_examid},
		success:function(content){
			var cons = eval(content);
			var time = parseInt(cons[0].exam.begintime);
			var systime = new Date();
			if(parseInt(time)>parseInt(systime.getTime())){
				alert("考试时间未到！");
			}else{
				if(_content!=undefined){
					window.open("<%=rootPath%>exam/linksingleques.action?paper="+_content+"&exam="+_examid);
				}
			}
		},
		error:function(e){
			
		}
	});
	
}
/**
* 前台考试分页
*/
function pageEMenu(_pages){
	window.location.href="<%=rootPath%>exam/exammenu.action?exammenunum="+_pages;
	
}
</script>
<style type="text/css">
.over{
border-bottom: 3px solid #448CC8;
}
</style>
<title>考试管理</title>
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
<!-- 

<jsp:include page="../include/client_head.jsp" />
 -->
 <jsp:include page="../../view/include/client_head.jsp" />
		   <section class="mainBox">
			<nav class="privateNav">
			</nav>
			</section>
</head>
<body>
<div class="main" style="background-image: url('');">
	<div style="padding: 30px 0 0 0;float: left;width: 100%;">
	
		<div id="nocanexam" style="float: left;padding: 5px  0 5px 0;cursor: pointer;" onclick="changeY(this)" class="over">
			<font class="linkbh14" >待参加的考试</font>
		</div>
		<div id="yescanexam" style="float: left;padding: 5px  0 5px 0;cursor: pointer;margin-left: 50px;" onclick="changeY(this)">
			<font class="linkbh14" >参加过的考试</font>
		</div>
		
	</div><br/>
	<div style="width: 70%;float: left;margin-top: 30px;">
		<s:iterator value="examlist" >
		
			<div style="width:200px; height: 150px;background-color:#ECF5FF;margin-top: 2px;padding: 4px 2px 2px 5px;cursor: pointer;float: left;margin: 4px 5px 4px 5px;line-height:30px;" onclick="">
			
			<p style="font-size: 16px;"><s:property value="examname" /></p>
			<p>
				<label>开始时间&nbsp;:&nbsp;<s:date name="begintime" format="yyyy-MM-dd HH:mm" /></label><br/>
				<label>结束时间&nbsp;:&nbsp;<s:date name="endtime" format="yyyy-MM-dd HH:mm" /></label><br/>
				<label>考试时长&nbsp;:&nbsp;<s:property value="answertime" /></label>
			</p>
			
			<p>
				<a id="joinexam" style="border: 1px solid #DBDBDB;padding: 2px 8px;" href="javascript:void(0)" onclick="visitExam(<s:property value="examcontent" />,<s:property value="examid" />)" >参加考试 </a>
			</p>
			
			</div>
		</s:iterator>
		<div>
			<div class="left">
				<div class="fyh">
					<ul>
					<s:property value="pagemenuhtml" escapeHtml="false" />
					</ul>
				</div>
			</div>	
		</div>
	</div>
	<div style="position:absolute;bottom: 0;height:60px;">
	<jsp:include page="../include/client_foot.jsp" />
	</div>
</div>
</body>
</html>