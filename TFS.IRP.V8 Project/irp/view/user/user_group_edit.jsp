<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<form id="userGroupEditFrom" method="post" onsubmit="return false;">
<s:hidden name="irpGroup.groupid" />
<section>
	<div class="inputText">
		<label style="font-size:15px;color:#333;">个人组织名称：</label><input type="text" name="irpGroup.groupname" value="<s:property value="irpGroup.groupname" />" class="easyui-validatebox" required="true" validType="isExist[2,30,<s:property value="irpGroup.parentid" />,<s:property value="irpGroup.groupid" />]" missingMessage="请填写组织名称" />
	</div>
	<div style="line-height:40px;">
		<a href="javascript:void(0)" onclick="editUserGroup()" class="zhuanz1">修&nbsp;&nbsp;改</a>
		<a href="javascript:void(0)" onclick="cancelEdit(<s:property value="irpGroup.groupid" />)" class="zhuanz1">取&nbsp;&nbsp;消</a>
	</div>
</section>
</form>