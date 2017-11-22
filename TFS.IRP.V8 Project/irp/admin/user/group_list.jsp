<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户管理</title>
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />
</head>

<body>
<script type="text/javascript">
$(function(){
	$('#gTabs').tabs({
		onSelect:function(title,index){
			var panels = $('#gTabs').tabs('tabs');
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
<div id="gTabs" fit="true" plain="true">
	<div id="groupManager" title="组织管理" width="100" link="<%=rootPath %>user/group_system_list.action?parentId=<s:property value="parentId"/>&groupType=<s:property value="groupType"/>" style="overflow:auto;padding:5px;"></div>
	<div id="grpUserManager" title="组织用户管理" width="100" link="<%=rootPath %>user/group_user_list.action?groupId=<s:property value="parentId"/>" style="overflow:auto;padding:5px;"></div>
</div>
</body>
</html>
