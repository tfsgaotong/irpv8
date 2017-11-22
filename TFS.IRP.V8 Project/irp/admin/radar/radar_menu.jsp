<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
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
	$.ajax({
		type:'post',
		url:jump("<%=rootPath%>radar/radar_sitenamelist.action?sitname="+_logtype+"&pageNum=1"),
		success:function(){
			
		}
	});	
}



</script>
<div id="menu" class="easyui-accordion" fit="true" border="false">
	<div title="数据管理" style="padding:0px;" selected="true" class="arrowsidemenu">
	<ul class="menucontents" style="white-space:nowrap;  ">
		<s:iterator value="irpt" status="listStat">
		
		<li><a href="javascript:void(0)" onclick="ErrorLogs(this.getAttribute('value'))" value="<s:property />"><s:property /></a></li>
		
	 	</s:iterator>
		</ul>
    </div>
</div>
</body>
</html>