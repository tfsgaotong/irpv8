<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>文档附件</title> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page"> 
	<style type="text/css">
	.ellipsis {
    table-layout: fixed;
}
.ellipsis td{
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
	</style>
</head> 
<body>    
   <table width="100%" class="ellipsis">
   		<tr>
   			<td width="45%" bgcolor="#F5FAFE"  class="main_bright">原文件名称</td>
   			<td width="45%" bgcolor="#F5FAFE"  class="main_bright">显示名称</td>
   			<td width="10%" bgcolor="#F5FAFE"  class="main_bright">下载</td>
   		</tr>
   		<s:iterator value="attacheds">
   			<tr>
   				<td width="45%" bgcolor="#F5FAFE"  class="main_bright"><s:property value='attdesc'/></td>
   				<td width="45%" bgcolor="#F5FAFE"  class="main_bright"><s:property value='attlinkalt'/></td>
   				<td width="10%" bgcolor="#F5FAFE"  class="main_bright">
   					下载
   				</td>
   			</tr>
   		</s:iterator>
   </table> 
   
</body>
</html>