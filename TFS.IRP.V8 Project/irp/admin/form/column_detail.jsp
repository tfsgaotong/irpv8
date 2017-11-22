<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
String init = request.getParameter("initname");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>表单字段细览</title>
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />
</head>

<body>
<form id="columnForm" method="post">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cad9ea">
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft" style="width: 140px;">字段显示名称：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><s:property value="irpFormColumn.columnname"/></td>
	</tr>
	
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft" >数据库表字段名：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><s:property value="irpFormColumn.columntablenamecol"/></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">字段描述：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><s:property value="irpFormColumn.columndesc"/></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">字段类型：</td>
		<td bgcolor="#FFFFFF" class="main_bright">
						
					<s:if test="irpFormColumn.columntype in inttype">整型</s:if>
						 <s:if test="irpFormColumn.columntype in stringtype">字符串</s:if>
						 <s:if test="irpFormColumn.columntype in datetype">时间</s:if>
		
	</tr>
	<tr id="length">
		<td align="right" bgcolor="#f5fafe" class="main_bleft">字段长度：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><s:property value="irpFormColumn.columnlongtext"/></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">字段状态：</td>
		<td bgcolor="#FFFFFF" class="main_bright"> <s:if test="irpFormColumn.columnstatus==10">正常</s:if>
		 <s:if test="irpFormColumn.columnstatus==0">隐藏</s:if> </td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">数据库表字段默认值：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><s:property value="irpFormColumn.columndefval"/></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">在列表中显示状态：</td>
		<td bgcolor="#FFFFFF" class="main_bright"> <s:if test="irpFormColumn.columninliststatus==10">正常</s:if>
		<s:if test="irpFormColumn.columninliststatus==0">隐藏</s:if>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">是否只读：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><s:if test="irpFormColumn.columnisreadonly==10">是</s:if>
		<s:if test="irpFormColumn.columnisreadonly==0">否</s:if>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">是否可以为空：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><s:if test="irpFormColumn.columnempty==10">是</s:if>
		<s:if test="irpFormColumn.columnempty==0">否</s:if>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">是否主键：</td>
		<td bgcolor="#FFFFFF" class="main_bright">
		 <s:if test="irpFormColumn.columnassignkey==10">是</s:if>
		<s:if test="irpFormColumn.columnassignkey==20">否</s:if>
		
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">字段校验类型：</td>
		<td bgcolor="#FFFFFF" class="main_bright">
				<s:if test="irpFormColumn.checktype=='0'">无</s:if>
				<s:if test="irpFormColumn.checktype=='strlen'">长度限制</s:if>
                <s:if test="irpFormColumn.checktype=='checkmobile'">电话验证</s:if>
                <s:if test="irpFormColumn.checktype=='checkidcard'">身份证号验证</s:if>
                <s:if test="irpFormColumn.checktype=='checkemail'">邮箱验证</s:if>        						
		</td>
	</tr>
	
</table>
</form>
</body>
</html>
