<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<style>
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
</style>
<form id="pwdForm" method="post" onsubmit="return false;">
<div class="gr2">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="10">
	<tr>
		<th width="80" class="gray6">输入旧密码：</th>
		<td><input name="oldPassWord" required="true" validType="length[6,50]" missingMessage="请填写旧密码" type="password" /></td>
	</tr>
	<tr>
		<th width="80" class="gray6">输入新密码：</th>
		<td><input name="passWord" required="true" validType="length[6,50]" missingMessage="请填写新密码" type="password" /></td>
	</tr>
	<tr>
		<th width="80" class="gray6">确认新密码：</th>
		<td><input name="rePassWord" required="true" validType="eqPassword['#pwdForm input[name=passWord]']" type="password" /></td>
	</tr>
</table>
</div>
</form>
