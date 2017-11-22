<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>      
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
   <!--  <div title="应用配置" id="apptab" style="overflow:auto;padding:5px;"  link="<%=rootPath%>menu/select_appdetil.action?appid=<s:property value="appid"/>&pageNum=1&orderField&searchWord&searchType"></div>
    -->
   <s:if test="tab=='complain'">
	   <div title="应用配置" id="appcomplaintypetab" style="overflow:auto;padding:5px;"  link="<%=rootPath%>menu/complain_menutab.action?pageNum=1&orderField&searchWord&searchType"></div>
	   <div title="应用内容" id="appcomplaintab" style="overflow:auto;padding:5px;"  link="<%=rootPath%>menu/select_allcomplain.action?pageNum=1&orderField&searchWord&searchType"></div>
  </s:if>
  <s:elseif test="tab=='offenmenu'">
       <div title="应用配置" id="frontoffenmenu" style="overflow:auto;padding:5px;"  link="<%=rootPath%>set/offen_menutab.action?pageNum=1&orderField&searchWord&searchType"></div>
  </s:elseif>
   <s:elseif test="tab=='export'">
       <div title="应用配置" id="frontexportmenu" style="overflow:auto;padding:5px;"  link="<%=rootPath%>menu/export_excecltypetab.action?pageNum=1&orderField&searchWord&searchType"></div>
  </s:elseif>
 </div>   
 </body>
</html>