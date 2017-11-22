<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>选择栏目</title>
  </head> 
  <body> 
  <script type="text/javascript"> 
   $(function(){
	   var _siteid=<s:property value='siteid'/>;
      $('#checkchannelul').tree({
      	url:'<%=rootPath%>site/to_load_site_to_check_channel.action?siteid='+_siteid   
      });
       <%--  $('#cc').combotree({  
        url: '<%=rootPath%>site/to_load_site_to_check_channel.action?siteid='+_siteid,  
        required: true  
    }); --%>
  }); 
  
  </script> 
  <form action=""> 
       <ul id="checkchannelul"></ul> 
 <%-- <input id="cc" class='easyui-combotree' onchange="channel()" />  
       <select id="sTwo" name='"+_irpFormColumn.get(i).getColumntablenamecol()+"' class='easyui-combotree' data-options="url:'<%=rootPath%>site/to_load_site_to_check_channel.action?siteid=2',method:'get',onSelect:function(rec){alert(1);var irpChannel=$(rec).combobox('getValues'); }" style="width: 200px;"></select>
    --%> </form>    
  </body>
</html>
