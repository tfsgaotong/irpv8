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
	var m_nSiteId=<s:property value="siteid"/>;
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
   		<s:if test="id==-1">
   		<div id="site" style="width: 100%; padding: 5px; overflow: auto;"  title="基本属性" <s:if test="id==null">selected="true"</s:if> link="<%=rootPath %>site/site_info.action?irpSite.siteid=<s:property value="siteid"/>"></div>
   		</s:if>
		<div id="lm" style="width: 100%; padding: 5px; overflow: auto;" title="栏目" <s:if test="channelorDocument=='channel'">selected="true"</s:if> link="<%=rootPath %>site/channel_info.action?siteid=<s:property value="siteid"/>&channelorDocument=channel&id=<s:property value="id"/>&isGCChannel=1"></div>
		<div id="mb" style="width: 100%; padding: 5px; overflow: auto;" title="文档" <s:if test="channelorDocument=='document'">selected="true"</s:if> link="<%=rootPath %>site/site_or_chan_alldocLink.action?siteid=<s:property value="siteid"/>&channelorDocument=document&id=<s:property value="id"/>&isGC=0"></div> 
		<%-- <s:if test="siteid==2">
		<div id="bd" style="width: 100%; padding: 5px; overflow: auto;" title="表单" <s:if test="channelorDocument=='form'">selected="true"</s:if> link="<%=rootPath %>site/site_or_chan_allformLink.action?siteid=<s:property value="siteid"/>&id=<s:property value="id"/>"></div> 
     	</s:if> --%>
     </div> 
</body>
</html>
