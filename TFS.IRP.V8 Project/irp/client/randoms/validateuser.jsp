<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户验证</title>
<link href="<%=rootPath %>admin/css/css_login.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=rootPath %>admin/css/icon.css" type="text/css"></link>
<link rel="stylesheet" href="<%=rootPath %>admin/css/easyui.css" type="text/css"></link>
<script type="text/javascript" src="<%=rootPath %>admin/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>admin/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath %>admin/js/jquery.easyui.min.js"></script>
<script type="text/javascript">

$(function (){	
	var error =$("#error").val();
	if(error){
		$.messager.alert("错误信息",error);
	}
	/* 增加回车提交功能 */
	$('form input').bind('keyup', function(event) { 
		if (event.keyCode == '13') {
			$(this).submit();
		}
	});
});

function loginSubmit(){
	var user =$("#username").val();
	if(user==""){
		$.messager.alert("错误信息","用户名为空");
	}else{
		$('#findUser').submit();
	}
}
</script>
</head>

<body>
	<div class="top1"></div>
	<div class="top2">
		<div class="logo">
			<img src="<%=rootPath %>client/images/24pin.png" width="200" height="56" />
		</div>
		<div class="right">
			<s:form id="findUser" action="finds" namespace="/random" method="post">
			<span class="x">
				输入用户名:<input name="irpUser.username" style="margin-left: 15px;" id="username" class="easyui-validatebox" validType="isExist[2,50]" required="true" missingMessage="请填写用户名称" type="text" value="" />
			</span>
			<span>
				<a href="javascript:void(0)" onclick="loginSubmit()" class="dl" style="margin-left:77px">下一步</a>
			</span>
			</s:form>
			<input type="hidden" id="error" value='<s:property value="friendlyshow" />'/>
		</div>
	</div>
	<div class="end"><s:text name="page.foot.info" /></div>
</body>
</html>