<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>图片中心</title>
  </head>
  <body>
   <s:iterator value="personAllFile">
   	<span  style="padding-right: 5px;overflow: hidden; width: 300px; height: 145px;">
	  <img   src="<%=rootPath%>file/readfile.action?fileName=<s:property value='@com.tfs.irp.util.ThumbnailPic@searchFileName(filename,"_150X150")'/>" width="23%" height="120"/>
	</span>
	</s:iterator>
		 <ul> <s:property value="pageHTML" escapeHtml="false" /></ul>	
  </body>
</html>
