<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统邮箱配置</title>
</head>
<body>
 <script type="text/javascript">
$(function(){
	$('#sysemaildiv').tabs({
		onSelect:function(title,index){
			var panels = $('#sysemaildiv').tabs('tabs');
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
<div id="sysemaildiv" fit="true" plain="true">
  <div title="系统邮箱配置" id="sysemailtab" style="overflow:auto;padding:5px;" selected="true" link="<%=rootPath%>set/setemaillist.action?pageNum=1&orderField&searchWord&searchType"></div>
 </div>
</body>
</html>