<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>计划调度配置</title>
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />
</head>

<body>
<form id="jobForm" method="post">
<input type="hidden" name="irpJob.jobid" value="<s:property value="irpJob.jobid" />" />
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cad9ea">
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">任务名称：</td>
		<td bgcolor="#FFFFFF" class="main_bright">
			<input name="irpJob.jobname" class="easyui-validatebox" validType="length[2,60]" required="true" missingMessage="请填写任务名称" type="text" value="<s:property value="irpJob.jobname" />" />
		</td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">任务描述：</td>
		<td bgcolor="#FFFFFF" class="main_bright">
			<input name="irpJob.jobdesc" class="easyui-validatebox" validType="length[2,300]" required="true" missingMessage="请填写任务描述" type="text" value="<s:property value="irpJob.jobdesc" />" />
		</td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">任务状态：</td>
		<td bgcolor="#FFFFFF" class="main_bright">
			<input name="irpJob.status" id="jobstart" type="radio" value="1" <s:if test="irpJob.status==1">checked="checked" </s:if>/><label for="jobstart" style="cursor:pointer;">启动</label>
			<input name="irpJob.status" id="jobstop" type="radio" value="0" <s:if test="irpJob.status==0">checked="checked" </s:if>/><label for="jobstop" style="cursor:pointer;">停止</label>
		</td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">Bean：</td>
		<td bgcolor="#FFFFFF" class="main_bright" style="padding-top: 3px;">
			<input name="irpJob.jobclass" <s:if test="irpJob.jobid>0">readonly="readonly"</s:if> class="easyui-validatebox" validType="length[2,60]" required="true" missingMessage="请填写调度类名" type="text" value="<s:property value="irpJob.jobclass" />" />
			<br /><font color="red">Bean必须从org.quartz.Job继承</font>
		</td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">执行时间：</td>
		<td bgcolor="#FFFFFF" class="main_bright" style="padding-top: 3px;">
			<input name="irpJob.jobtime" class="easyui-validatebox" validType="length[2,20]" required="true" missingMessage="请填写执行时间" type="text" value="<s:property value="irpJob.jobtime" />" />
			<br /><font color="red">执行时间必须是[* * * * * ?]格式的corn表达式</font>
		</td>
	</tr>
</table>
</form>
</body>
</html>
