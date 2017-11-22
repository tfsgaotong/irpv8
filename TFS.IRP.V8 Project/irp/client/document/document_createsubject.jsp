<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>创建专题</title>
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
<form id="createsubjectform" method="post" onsubmit="return false;">
<div class="gr2">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="10">
	<tr>
		<th width="40" class="gray6">名称：</th>
		<td><input id="inputSubjectName" value="<s:property value='irpChannel.chnlname' />" style="width:100%" name="irpChannel.chnlname" required="true" validType="length[2,50]" missingMessage="请填写专题名称" type="text" /></td>
	</tr>
	<tr>
		<th width="40" class="gray6">描述：</th>
		<td><textarea style="width:100%;height: 50px;border: 1px solid #E4E4E4;font-size: 13px;color:#A0A0A0" name="irpChannel.chnldesc"><s:property value="irpChannel.chnldesc"/></textarea></td>
	</tr>
	<tr><td>
		
	</td></tr>
</table>
</div>
</form>
</body>
</html>
