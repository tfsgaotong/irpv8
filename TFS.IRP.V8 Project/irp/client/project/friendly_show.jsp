<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>友情提示</title>
  </head>
  
  <body>
  <%--提示关键字 <s:property value="friendlyShow"/> --%>
   <center>
  <img   src=" <%=rootPath %>client/images/404.jpg"  /> 
  </center>
  </body>
</html>
