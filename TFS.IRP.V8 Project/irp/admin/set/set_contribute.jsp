<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <script type="text/javascript">
$(function(){
	$('#contributeconf').tabs({
		onSelect:function(title,index){
			var panels = $('#contributeconf').tabs('tabs');
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
 <div id="contributeconf" fit="true" plain="true">
  <div title="贡献配置项" id="contributeconfitem" style="overflow:auto;padding:5px;" selected="true" link="<%=rootPath%>set/contributelist.action?pageNum=1&orderField&searchWord&searchType"></div>
  <div title="用户级别配置" id="contributegrade" style="overflow:auto;padding:5px;"  link="<%=rootPath%>set/contributelistofgrade.action?pageNum=1&orderField&searchWord&searchType"></div>
  <div title="用户贡献排行" id="contributeranking" style="overflow:auto;padding:5px;" link="<%=rootPath%>set/contributelistofranking.action?pageNum=1&orderField&searchWord&searchType&c_start_end=covert_moren&c_date_start_time&c_date_end_time"  ></div>
  <div title="特殊配置" id="contributespecial" style="overflow:auto;padding:5px;" link="<%=rootPath%>set/contributelistofspecial.action?pageNum=1&orderField&searchWord&searchType"></div>
  <div title="随机用户" id="listrandomuser" style="overflow:auto;padding:5px;" link="<%=rootPath%>admin/set/set_user_random.jsp"></div>
 </div>
</body>
</html>