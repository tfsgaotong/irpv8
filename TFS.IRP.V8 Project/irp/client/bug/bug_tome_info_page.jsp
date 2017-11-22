<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
</head>
  <body>
    <div id="buginfotop">
    <jsp:include page="bug_tome_top.jsp"/>
    </div>
    <div id="buginfobottom">
    <jsp:include page="bug_tome_bottom.jsp"/>
    </div>
  </body>
</html>
