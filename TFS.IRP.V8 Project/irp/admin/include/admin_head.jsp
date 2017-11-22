<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>

<link rel="stylesheet" type="text/css" href="../css/css_body.css">
</head>
<body>
<div class="topbg">
	<div class="top">
		<div class="logo">
			<img src="./images/logo.jpg" width="206" height="100" />
		</div>
		<div class="right">
			<div class="rt">
				<s:property value="@com.tfs.irp.util.LoginUtil@getLoginUser().getUsername()" />，你好 欢迎登录
				<s:a action="logout" namespace="/">注销登录</s:a>
				<span>
					<a href="<s:url action="index" namespace="/document" />" target="_top">前台首页</a>
					<a href="<s:url action="index" namespace="/document" />" style="padding-left: 20px;padding-right: 20px;" target="_top">知识库</a>
				</span>
			</div>
			<s:bean var="rightUtil" name="com.tfs.irp.util.RightUtil" />
			<ul class="right">
				<s:iterator var="mo" value="#rightUtil.findManagementOper()">
					<li>
						<a href="<s:url action="index" namespace="/admin">
						<s:param name="type" value="#mo.opername" />
						</s:url>"<s:if test="#parameters.MenuType[0]==#mo.opername">class="x"</s:if> target="_top"><s:property value="#mo.operdesc" />
						</a>
					</li>
				</s:iterator>
			</ul>
		</div>
	</div>
	<div class="topend"></div>
</div>
<div class="br-1"></div>
</body>
</html>