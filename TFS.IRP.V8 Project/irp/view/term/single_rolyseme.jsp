<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.tfs.irp.util.LoginUtil"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加义项</title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/oacss.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/icon.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/ztree/css/zTreeStyle/zTreeStyle.css"/>
	<script type="text/javascript" src="<%=rootPath%>view/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/js/lhgdialog.min.js"></script>
  </head>
  <body>
  		<div style="height: 50px;margin: 5px 10px 5px 10px;">您需要给原有的义项和新增加的义项都写一个“义项描述”，让读者能分清楚同名的两个事物。</div>
  		<div style="height: 100px;margin: 5px 10px 5px 10px;border:1px solid black;">
	  		<dl style="margin: 20px 0 0 20px;">
	  			<dt><label style="margin-left: 5px;">示例</label><label style="margin-left: 20px;color: #BEBEBE;">词条名</label><label style="margin-left: 54px;color: #4F4F4F;">环太平洋</label></dt>
	  			<dd><label style="margin-left: 48px;color: #BEBEBE;">原有义项描述</label><label style="margin-left: 20px;color: #4F4F4F;">地理区域</label></dd>
	  			<dd><label style="margin-left: 48px;color: #BEBEBE;">新增义项描述</label><label style="margin-left: 20px;color: #4F4F4F;">2013年美国科幻电影</label></dd>
	  		</dl>	
  		</div>
  		<div style="height: 150px;">
  			<span>词条名</span>
  			<br/>
  			<span>原有义项描述</span>
  			<br/>
  			<span>新增义项描述</span>
  		</div>
  		<div style="height: 50px;"></div>
  </body>
</html>
