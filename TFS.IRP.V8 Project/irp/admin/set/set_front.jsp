<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统前端配置</title>
</head>
<body>
 <script type="text/javascript">
$(function(){
	$('#frontconf').tabs({
		onSelect:function(title,index){
			var panels = $('#frontconf').tabs('tabs');
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
<div id="frontconf" fit="true" plain="true">
   <div title="前台配置" id="frontconfitem" style="overflow:auto;padding:5px;" selected="true" link="<%=rootPath%>set/otherSet.action?pageNum=1&orderField&searchWord&searchType"></div>
   <div title="举报种类配置" id="frontconfreporttype" style="overflow:auto;padding:5px;"  link="<%=rootPath%>set/reportkindset.action?pageNum=1&orderField&searchWord&searchType"></div>
  <!-- 
   <div title="圈子阶段配置" id="projectmenu" style="overflow:auto;padding:5px;"  link="<%=rootPath%>set/project_menu.action?pageNum=1&orderField&searchWord&searchType"></div>
   -->
   <div title="加精理由配置" id="jiajingmenu" style="overflow:auto;padding:5px;"  link="<%=rootPath%>set/jiajing_menu.action?pageNum=1&orderField&searchWord&searchType"></div>
   <div title="快速登录配置" id="oauthlogin" style="overflow:auto;padding:5px;"  link="<%=rootPath%>set/oauth_login_set.action?pageNum=1&orderField&searchWord&searchType"></div>
   <div title="数据管理配置" id="datasetting" style="overflow:auto;padding:5px;"  link="<%=rootPath%>set/dataSetting.action?pageNum=1&orderField&searchWord&searchType"></div>	
   <div title="栏目模块配置" id="chanelsetting" style="overflow:auto;padding:5px;"  link="<%=rootPath%>set/channelSetting.action?pageNum=1&orderField&searchWord&searchType"></div>	
 </div>
</body>
</html>