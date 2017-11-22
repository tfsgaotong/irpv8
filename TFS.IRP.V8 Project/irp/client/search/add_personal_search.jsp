<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>创建个性化检索</title>
<style>
<!--
input {
	border:1px solid #E4E4E4;
	color:#A0A0A0;
	font-size:13px;
	list-style:none outside none;
}
.gray6{
	font-weight:normal;
	line-height:20px;
}
-->
</style>
</head>

<body>
<div class="gr2">
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="10">
		<tr>
			<th width="40" class="gray6">名称：</th>
			<td><input id="inputSearchName" value="" style="width:100%" class="easyui-validatebox" required="true" validType="length[1,100]" missingMessage="请填写名称" type="text" /></td>
		</tr>
		<tr>
			<th width="40" class="gray6">描述：</th>
			<td><textarea id="inputSearchDesc" style="width:100%;height: 50px;border: 1px solid #E4E4E4;font-size: 13px;color:#A0A0A0"></textarea></td>
		</tr>
	</table>
</div>
</body>
</html>
