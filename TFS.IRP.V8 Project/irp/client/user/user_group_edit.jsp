<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<form id="userGroupEditFrom" method="post" onsubmit="return false;">
<s:hidden name="irpGroup.groupid" />
<div style="padding-left: 50px;">
<table border="0" cellpadding="0" cellspacing="10">
	<tr>
		<td align="right">个人组织名称：</td>
		<td><input type="text" name="irpGroup.groupname" value="<s:property value="irpGroup.groupname" />" class="easyui-validatebox" required="true" validType="isExist[2,30,<s:property value="irpGroup.parentid" />,<s:property value="irpGroup.groupid" />]" missingMessage="请填写组织名称" /></td>
	</tr>
	<tr>
		<td colspan="2">
			<a href="javascript:void(0);" onclick="editUserGroup()" class="zhuanz1">修改</a>
			<a href="javascript:void(0);" onclick="cancelEdit(<s:property value="irpGroup.groupid" />)" class="zhuanz1">取消</a>
		</td>
	</tr>
</table>
</div>
</form>