<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>账号绑定</title>
<link href="<%=rootPath%>admin/css/css_login.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=rootPath%>admin/css/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="<%=rootPath%>admin/css/icon.css" type="text/css"></link>
<link rel="stylesheet" href="<%=rootPath%>client/js/skins/iblue.css" type="text/css"></link>
<style type="text/css">
.pf_left{
	float: left;
	width: 450px;
}
.pf_left dt{
	float: left;
	margin: 0 30px 0 0;
	height: 250px;
}
.pf_left dt img {
	border: 1px solid white;
}
.pf_left dd{
	float: left;
}
</style>

<script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" data-appid="101042565" data-redirecturi="http://irp.ruisiming.com.cn/irpdemo" charset="utf-8"></script>
<script type="text/javascript" src="<%=rootPath%>admin/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>admin/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>admin/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript">
$(function (){
	/* 登录 */
	$('#bindingForm').form({
		url:'<s:url action="oauthbinding_dowith" namespace="/" />',
		success:function(data){
			if(data==1){
				window.location.href='<%=rootPath%><s:property value="requesturl" default="client/index.action" />';
			}else if(data==-1){
				$.messager.alert('<s:text name="login.alert.title" />','<s:text name="login.error.apply" />');
			}else if(data==3){
				//企业
				window.location.href='<%=rootPath%><s:property value="requesturl" default="site/showallpublicdoc.action" />';
			}else if(data==2){
				//个人
				window.location.href='<%=rootPath%><s:property value="requesturl" default="client/index.action" />';
			}else{
				$.messager.alert('<s:text name="login.alert.title" />','<s:text name="login.error.pwd" />');
			}
		}
	});
});

function bindingSubmit(){
	$('#bindingForm').submit();
}

function toQQReg(){
	$('#regForm').submit();
}
</script>
</head>

<body>
<div class="top3"><h1>账号绑定</h1>
    <div>
    <form id="bindingForm" method="post">
    	<s:hidden name="type" />
   	<s:if test="'qq'.equals(type)">
   		<s:hidden name="irpUser.qqtoken" />
    	<s:hidden name="irpUser.qquserid" />
   	</s:if>
   	<s:elseif test="'weibo'.equals(type)">
   		<s:hidden name="irpUser.weibotoken" />
    	<s:hidden name="irpUser.weibouserid" />
   	</s:elseif>
    	<s:hidden name="irpUser.userpic" />
    	<dl class="pf_left">
    		<dt><img src="<s:property value="irpUser.userpic" />" /></dt>
    		<dd><input name="irpUser.username" class="easyui-validatebox" required="true" missingMessage="请填写登录名称" type="text" value="" /></dd>
    		<dd><input name="irpUser.password" class="easyui-validatebox" required="true" missingMessage="请填写登录密码" type="password" value="" /></dd>
    		<dd><input type="checkbox" name="saveLogin" id="saveLogin" style="width: auto;float: left;" value="1" /><label for="saveLogin" style="margin-left: 5px;float: left;">使用<s:if test="'qq'.equals(type)">QQ秀</s:if><s:elseif test="'weibo'.equals(type)">新浪微知</s:elseif>形象</label></dd>
    		<dd><a href="javascript:void(0)" onclick="bindingSubmit()" class="dl">绑定账号</a> 没有账号？请直接&nbsp;&nbsp;<a href="javascript:void(0)" onclick="toQQReg()">注册</a></dd>
    	</dl>       
    </form>
    </div>
</div>
<div class="end"><s:text name="page.foot.info" /></div>
<s:form id="regForm" name="regForm" action="oauthloginreg" namespace="/" method="post">
	<s:hidden name="type" />
	<s:hidden name="irpUser.username" />
<s:if test="'qq'.equals(type)">
	<s:hidden name="irpUser.qqtoken" />
   	<s:hidden name="irpUser.qquserid" />
</s:if>
<s:elseif test="'weibo'.equals(type)">
	<s:hidden name="irpUser.weibotoken" />
   	<s:hidden name="irpUser.weibouserid" />
</s:elseif>
	<s:hidden name="irpUser.nickname" />
	<s:hidden name="irpUser.sex" />
</s:form>
</body>
</html>