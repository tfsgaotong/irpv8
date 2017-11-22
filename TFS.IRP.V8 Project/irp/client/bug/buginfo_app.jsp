<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head>

 <title><s:property value="irpBugs.get(0).title"/></title>
  <link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
<link href="<%=rootPath %>client/css/oacss.css" rel="stylesheet" type="text/css" /> 
<link href="<%=rootPath %>client/css/asx.css" rel="stylesheet" type="text/css" /> 
<link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css">
<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css">
<jsp:include page="../include/client_skin.jsp" />  
<script type="text/javascript" src="<%=rootPath %>client/js/jquery-1.8.0.min.js"></script> 
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
 
<style type="text/css">

.abc{
	width: 83px;
	background:#5f9ddd;
	margin-left:200px;
	float:left;
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
.btn1{

}
</style>
<script type="text/javascript">
$(function(){
	var power=<s:property value="power" />;
	//alert(power);
	if(power ==2){
		$('form').hide();
	}else if(power ==3){
		$('#weiwan').hide();
		$('#weiwantext').hide();
	}else if(power ==0){
		$('form').hide();
	}
	$('#disposeUser').hide();
});

function showSelect(){
	$('#disposeUser').show();
}

function hideSelect(){
	$('#disposeUser').hide();
}
</script>
</head>
<body > 
  <input type="hidden" value="<s:property value='tokens'/>" id="tokens"/>
  <div id="page" style="width: 100%;">

<div class="bg01" style="width: 99%;">
<!-- Bug信息 -->
<jsp:include page="buginfo_page_app.jsp" />
<!-- Bug信息end -->
		<s:form id="form" method="post" enctype="multipart/form-data" >
		<div id="state" style="margin-left: 10%;width: 80%;">
			<span style="font-size: 15px;text-align: left;">状态</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" name="dowhat" value="1" checked="checked" onclick="hideSelect()">已解决</input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" name="dowhat" value="2" id="weiwan" onclick="hideSelect()"><span id="weiwantext">未解决&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></input>
			<input type="radio" name="dowhat" value="3" onclick="showSelect()">转发</input>
			<span >
			<select name="disposeUserId" style="width: 80px; " id="disposeUser" >
			<s:iterator var="user" value="projectUsers">
				<option value="<s:property value='#user.userid'/>"><s:property value="#user.truename"/></option>
			</s:iterator>
			</select>
			</span>
		</div>
		<br />
		<div id="beizhu" style="height: 100px;margin-left: 10%;">
			<span style="font-size: 15px;text-align: left;">备注</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<div style="margin-left:30px;">
			 <textarea  id="editor" align="middle"  style="width: 90%;height: 10%" frameborder="0" scrolling="no" width="300" height="300"></textarea>
			</div>
		</div>
		<div style="height: 60px;" >
			<div class="abc" align="right" style="margin-left: 70%;">
					<a class="btn1" href="javascript:void(0)" onclick="transpond()" >提&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;交</a>
			</div>
			<!-- <div class="abc" style="margin-left:30px;">
					<a class="btn1" href="javascript:void(0)" onclick="window.close()" >关&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;闭</a>
			</div> -->
		</div>
		</s:form>
</div>
</div>
</body>
</html>
