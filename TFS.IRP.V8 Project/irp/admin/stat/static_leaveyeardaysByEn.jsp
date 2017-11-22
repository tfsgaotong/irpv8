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
<title>企业请假天数</title>
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
  <div title="请假天数分布统计" id="contributeranking1" style="overflow:auto;padding:5px;"  selected="true" link="<%=rootPath%>leave/departmenttime.action?orderField&c_start_end=logs_month&marking=10"></div>
  <div title="各部门的请假统计" id="contributeranking" style="overflow:auto;padding:5px;"  link="<%=rootPath%>leave/departmentStatic.action?orderField&type=1&groupIds=1&c_start_end=logs_month"></div>
  <div title="每天的请假统计" id="contributeranking2" style="overflow:auto;padding:5px;"  link="<%=rootPath%>leave/everydaypublishdocumentamount.action?c_start_end=logs_month&marking=10"></div>
  <div title="每个人的请假统计" id="contributeranking3" style="overflow:auto;padding:5px;"  link="<%=rootPath %>leave/statusLeaveApplyByDays.action?pageNum=1&orderField&applystatus=0&c_start_end=logs_month&emergency=0&applytypeid=0&marking=10"></div>
 
</div>
</body>
</html>