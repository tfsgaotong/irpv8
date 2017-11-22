<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<form id="tagTypeEditFrom" method="post" onsubmit="return false;">
<s:hidden name="irpTagType.typeid" />
<section>
	<div class="inputText">
		<label style="font-size:15px;color:#333;">标签类型名称：</label><input type="text" name="irpTagType.typename" value="<s:property value="irpTagType.typename" />" class="easyui-validatebox" required="true" validType="isExist[2,30,<s:property value="irpTagType.typeid" />]" missingMessage="请填写标签类型名称" />
	</div>
	<div style="line-height:40px;">
		<a href="javascript:void(0)" onclick="editTagType()" class="zhuanz1">修&nbsp;&nbsp;改</a>
		<a href="javascript:void(0)" onclick="cancelEdit(<s:property value="irpTagType.typeid" />)" class="zhuanz1">取&nbsp;&nbsp;消</a>
	</div>
</section>
</form>