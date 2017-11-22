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
<!-- 
<script type="text/javascript" src="admin/js/jquery-1.8.0.min.js"></script>
 -->
</head>
<body>
<script type="text/javascript">
function jump(_sUrl){
	$('body').layout('panel','center').panel('refresh',_sUrl);
}

$(function(){
	$('#asseroomsb').tabs({
		onSelect:function(title,index){
			var panels = $('#asseroomsb').tabs('tabs');
			for(var i=0;i<panels.length;i++){
				var panel = panels[i];
				if(i==index){
					panel.panel('refresh',panel.attr('link'));
				}else{
					panel.html('');
				}
			}
		}
	});
});
</script>
<div id="asseroomsb" fit="true" plain="true">
  <div title="会议室设备" id="asseroomsblist" style="overflow:auto;padding:5px;" selected="true" link="<%=rootPath%>asseroomsb/asseroomsblist.action?pageNum=1&orderField&searchWord&searchType"></div>
	<div title="会议室" id="asseroomlist" style="overflow:auto;padding:5px;"  link="<%=rootPath%>asseroom/asseroomlist.action?pageNum=1&orderField&searchWord&searchType"></div>
</div>
</body>
</html>