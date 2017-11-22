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
	$('#knowledgeconf').tabs({
		onSelect:function(title,index){
			var panels = $('#knowledgeconf').tabs('tabs');
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
<div id="knowledgeconf" fit="true" plain="true">
  <div title="存放目录" id="cataloguelist" style="overflow:auto;padding:5px;" selected="true" link="<%=rootPath%>set/knowledgecatalogue.action?pageNum=1&orderField&searchWord&searchType"></div>
   <div title="知识状态设置" id="docstatustab" style="overflow:auto;padding:5px;" link="<%=rootPath %>site/alldocstatus.action" ></div>
  <div title="文件类型设置" id="sysfileext" style="overflow:auto;padding:5px;" link="<%=rootPath %>filetype/alltypes.action" ></div>
</div>
</body>
</html>