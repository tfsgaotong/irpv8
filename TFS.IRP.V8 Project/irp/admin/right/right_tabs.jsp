<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>权限管理</title>
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div id="groupTabs" class="easyui-tabs" fit="true" plain="true">
	<div id="site" title="站点权限" href="<%=rootPath %>right/right_site.action?objId=<s:property value="objId"/>&objType=<s:property value="objType"/>" style="overflow:auto;padding:5px;"></div>
	<div id="channel" title="栏目权限" href="<%=rootPath %>right/right_channle.action?objId=<s:property value="objId"/>&objType=<s:property value="objType"/>" style="overflow:auto;padding:5px;"></div>
	<div id="subject" title="专题权限" href="<%=rootPath %>right/right_subject.action?objId=<s:property value="objId"/>&objType=<s:property value="objType"/>" style="overflow:auto;padding:5px;"></div>
	<div id="map" title="地图权限" href="<%=rootPath %>right/right_map.action?objId=<s:property value="objId"/>&objType=<s:property value="objType"/>" style="overflow:auto;padding:5px;"></div>
	<div id="document" title="文章权限"  href="<%=rootPath %>right/right_document.action?objId=<s:property value="objId"/>&objType=<s:property value="objType"/>" style="overflow:auto;padding:5px;"></div>
	<div id="admin" title="后台操作权限" href="<%=rootPath %>right/right_management.action?objId=<s:property value="objId"/>&objType=<s:property value="objType"/>" style="overflow:auto;padding:5px;"></div>
</div>
</body>
</html>