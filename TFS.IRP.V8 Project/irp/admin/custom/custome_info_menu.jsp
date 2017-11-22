<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />
<!-- 
<script type="text/javascript" src="admin/js/jquery-1.8.0.min.js"></script>
 -->
<title>会议室管理</title>
</head>
<body>
<script type="text/javascript">
function jump(_sUrl){
	$('body').layout('panel','center').panel('refresh',_sUrl);
}
$(function (){
	var isInit = false;
	$('#custome').accordion({
		onSelect : function(title){ 
			 if(title=="年假管理"){
				jump('<%=rootPath %>admin/meeting/user_leaveyeardays.jsp');
				isInit = true;
				
			}
				
		}
	});  
});

</script>
<div id="custome" class="easyui-accordion" fit="true" border="false" >
	
	
	<div title="年假管理" style="padding:0px;"  class="asseroomsbmenu"></div>
	
	 
</div>
</body>
</html>