<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<body>
<script type="text/javascript"> 
$(function(){
	$('#checkchannelul').tree({
		url:'<%=rootPath%>site/admin_to_load_channel.action'   
	});
}); 
</script>  
<ul id="checkchannelul"></ul> 
</body>
</html>
