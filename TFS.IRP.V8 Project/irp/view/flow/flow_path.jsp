<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
<title>审核流转轨迹</title> 
<style type="text/css">
body{
	margin: 0px;
	padding: 0px;
}
td{
    color: #666666;
    font-family: "微软雅黑","黑体";
    font-size: 12px;
    line-height: 18px;
    padding: 6px;
    border-color: #666666;
}
table{
	border-collapse:collapse;
	border-style: solid;
	border-color: #666666;
}
.flowPath1 td{
	color: #62A53B;
}
.flowPath2 td{
	color: #E69521;
}
.flowPath3 td{
	color: #225491;
}
</style>
</head>

<body>
<table width="600" border="1" cellpadding="0" cellspacing="0">
	<tr>
		<td align="center" width="100">审核节点</td>
		<td align="center" width="120">审核人</td>
		<td align="center" width="140">审核时间</td>
		<td align="center" width="240">意见</td>
	</tr>
	<s:iterator value="irpWorkflowObjs">
	<tr class="flowPath<s:property value="transfertype" />">
		<td><s:property value="findFlowNodeNameByFlowNodeId(nodeid)" /></td>
		<td><s:property value="findUserNameByUserId(postuserid)" /></td>
		<td align="center"><s:date name="posttime" format="yyyy-MM-dd HH:mm:dd" /></td>
		<td><s:property value="postdesc" /></td>
	</tr>
	</s:iterator>
</table>
</body>
</html>
