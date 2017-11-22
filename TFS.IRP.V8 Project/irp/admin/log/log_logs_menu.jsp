<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
function jump(_sUrl){
	$('body').layout('panel','center').panel('refresh',_sUrl);
}

function ErrorLogs(_logtype){

	jump("<%=rootPath%>log/logserror.action?irplogstype="+_logtype+"&pageNum=1&orderField");

}



</script>
<div id="menu" class="easyui-accordion" fit="true" border="false">
	<div title="日志管理" style="padding:0px;" selected="true" class="arrowsidemenu">
	<ul class="menucontents">
		<li><a href="javascript:void(0)" onclick="ErrorLogs(1)">错误</a></li>
		<li><a href="javascript:void(0)" onclick="ErrorLogs(2)">警告</a></li>
		<li><a href="javascript:void(0)" onclick="ErrorLogs(3)">信息</a></li>
		<li><a href="javascript:void(0)" onclick="ErrorLogs(4)">调试</a></li>
		</ul>
    </div>
</div>
</body>
</html>