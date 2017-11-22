<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<script type="text/javascript" src="<%=rootPath %>editor/simple_ckeditor/ckeditor.js"></script> 

</head>
<body>
<!--添加编辑器-->
<textarea id="editor" name="editor"></textarea>
<script> 
CKEDITOR.replace('editor',{
	filebrowserUploadUrl: '<%=rootPath%>file/ck_upload.action'
});
</script>
</body>
</html>
