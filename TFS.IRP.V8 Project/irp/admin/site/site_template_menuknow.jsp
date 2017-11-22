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
<link rel="stylesheet" href="../css/icon.css" type="text/css"></link>
<script type="text/javascript" src="../js/jquery-1.8.0.min.js"></script> 
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
</head>

<body> 
<script type="text/javascript">
	$(function(){ 
		$('#tab').tabs({
			onSelect:function(title,index){
				var panels = $('#tab').tabs('tabs');
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
   	<div id="tab" fit="true" plain="true">
		<div id="lmtemplate" style="width: 100%; padding: 5px; overflow: auto;" title="知识模版" selected="true" link="<%=rootPath %>set/templateknowlistknow.action?cateid=<s:property value="cateid" />&pageNum=1&orderField&searchWord&searchType"></div>
		<!--<div id="wd" style="width: 100%; padding: 5px; overflow: auto;" title="文档"<s:if test="channelorDocument=='document'">selected="true"</s:if> link="<%=rootPath %>site/suballdocument.action?siteid=<s:property value="siteid"/>&channelorDocument=document&id=<s:property value="id"/>&isGC=0"></div>  -->
     </div> 
</body>
</html>
