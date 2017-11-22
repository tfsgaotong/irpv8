<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>    	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页</title>
</head>
<body>
<script type="text/javascript">
function jump(_sUrl){
	$('body').layout('panel','center').panel('refresh',_sUrl);
}
$(function(){
	var isInit = false;
	$('#menu').accordion({
		onSelect : function(title){
			if(isInit&&title=="升级信息"){
				jump("<%=rootPath%>home/systemupdateinfo.action?pageNum=1&orderField&searchWord&searchType");
			}else if(title=="系统信息"){
				jump("include/admin_info.jsp");
				
				isInit = true;
			}
		}
	});
});
</script>
<div id="menu"  fit="true" border="false">
	<div title="系统信息" style="padding:10px;" selected="true"></div>
	<div title="升级信息" style="padding:10px;" ></div>
</div>
</body>
</html>
