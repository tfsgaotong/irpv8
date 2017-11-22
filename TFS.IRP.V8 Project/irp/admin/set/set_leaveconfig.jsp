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

$(function(){
	$('#leaveconfig').tabs({
		onSelect:function(title,index){
			var panels = $('#leaveconfig').tabs('tabs');
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
<div id="leaveconfig" fit="true" plain="true">
  <div title="加班类型配置" id="cataloguelist20" style="overflow:auto;padding:5px;" selected="true" link="<%=rootPath%>leaveconfig/allleaveconfigList.action?pageNum=1&marking=20"></div>
  <div title="请假类型配置" id="cataloguelist10" style="overflow:auto;padding:5px;" link="<%=rootPath %>leaveconfig/allleaveconfigList.action?pageNum=1&marking=10" ></div>
</div>
</body>
</html>