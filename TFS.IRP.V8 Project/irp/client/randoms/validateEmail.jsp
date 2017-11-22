<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>验证邮箱</title>
<link href="<%=rootPath%>admin/css/css_login.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=rootPath%>admin/css/icon.css" type="text/css"></link>
<link rel="stylesheet" href="<%=rootPath%>admin/css/easyui.css" type="text/css"></link>
<script type="text/javascript" src="<%=rootPath%>admin/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>admin/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath%>admin/js/jquery.easyui.min.js"></script>
<script type="text/javascript">
$(function (){	
	$('#vEmail').form({
		url:'<s:url namespace="/random" action="sendEmail" />',
		success:function(data){
			if(data==1){
				$.messager.alert("操作提示", "邮件发送成功,请尽快修改！", "info", function () { 
					window.location.href='<s:url namespace="/" action="login" />';
				});
				
			}else if(data==0){
				$.messager.alert("错误信息","用户邮箱错误,请核实信息。");
			}
			else{
				$.messager.alert("错误信息","系统异常");
			}
		}
	});	
});
function loginSubmit(){
				$('#vEmail').submit();	
}
</script>
</head>

<body>
	<div class="top1"></div>
	<div class="top2">
		<div class="logo">
			<img src="<%=rootPath%>client/images/24pin.png" width="200" height="56" />
		</div>
		<div class="right">
			<form id="vEmail" method="post">			
			<span class="x">
				输入邮箱地址:<input name="email" id="emails" style="margin-left: 15px" class="easyui-validatebox" missingMessage="邮件必须填写" validType="email" invalidMessage="请填写正确的邮件格式" required="true" type="text" value="" />
				<input name="irpUser.username" id="email" type="hidden" value='<s:property value="irpUser.username"/>'/>
			</span>
			<span>
				<a href="javascript:void(0)" onclick="loginSubmit()" class="dl" style="margin-left:90px">完成</a>
			</span>
			</form>
		</div>
	</div>
	<div class="end"><s:text name="page.foot.info" /></div>
</body>
</html>