<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>密码修改</title>
<link href="<%=rootPath %>admin/css/css_login.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=rootPath %>admin/css/icon.css" type="text/css"></link>
<link rel="stylesheet" href="<%=rootPath %>admin/css/easyui.css" type="text/css"></link>
<script type="text/javascript" src="<%=rootPath %>admin/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>admin/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath %>admin/js/jquery.easyui.min.js"></script>
<script type="text/javascript">

$(function (){
	var username=$("#username").val();
	var disposecontent = username.replace(":","\\:");
	var eacapeInfo = encodeURI(disposecontent);
	$('#upUser').form({
		url:'<s:url namespace="/random" action="uPwd" />',
		success:function(data){
			if(data>0){
				window.location.href='<s:url namespace="/" action="login" />';
			}else{
				$.messager.alert("错误信息","密码修改失败，请核实信息!");
			}
		}
	});
$.extend($.fn.validatebox.defaults.rules, {   
    /*必须和某个字段相等*/
    equalTo: { 
        validator:function(value,param){ 
            return $(param[0]).val() == value; 
        }, 
        message:'字段不匹配'
    } 
});
});
function loginSubmit(){
		$('#upUser').submit();
}
</script>
</head>

<body>
	<div>
		<div class="top1"></div>
	<div class="top2">
		<div class="logo">
			<img src="<%=rootPath %>client/images/24pin.png" width="200" height="56" />
		</div>
		<div class="right">
			<form id="upUser" method="post">
			<span class="x">
				用　户　名:<input name="userName" id="username" style="margin-left: 15px" readonly="readonly" class="easyui-validatebox" required="true"  missingMessage="请填写登录名称" type="text" value="<s:property value="userName"/>" />
				<input name="checkCode" id="checkCode" type="hidden" value="<s:property value="checkCode"/>" />
			</span>
			<span class="x">
				请输入密码:<input name="passWord" id="pwd" style="margin-left: 15px" class="easyui-validatebox" required="true" missingMessage="请填写密码" type="password" value="" />
			</span>
			<span class="x">
				请确认密码:<input name="repassWord" id="pwd2" style="margin-left: 15px" class="easyui-validatebox" required="true" type="password" missingMessage="请确认密码"  validType="equalTo['#pwd']" invalidMessage="两次输入密码不匹配" />
			</span>
			<span>
				<a href="javascript:void(0)" onclick="loginSubmit()" class="dl" style="margin-left:77px">保存</a>
			</span>
			</form>
		</div>
	</div>
	<div class="end"><s:text name="page.foot.info" /></div>
	</div>
</body>
</html>