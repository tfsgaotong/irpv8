<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()	+ request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<title></title>
   
<script type="text/javascript" src="<%=rootPath%>/editor/ckeditor/skins/zhjs/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/editor/ckeditor/skins/zhjs/diyUpload.js"></script>
<script type="text/javascript" src="<%=rootPath%>/editor/ckeditor/skins/zhjs/webuploader.html5only.min.js"></script>
</link><link rel="stylesheet" href="<%=rootPath%>/editor/ckeditor/skins/zhcss/diyUpload.css" type="text/css"></link>
<link rel="stylesheet" href="<%=rootPath%>/editor/ckeditor/skins/zhcss/webuploader.css" type="text/css"></link>

</head>
 <style>
*{ margin:0; padding:0;}
#box{ margin:50px auto; width:99%; min-height:99%; background:#FF9}
#demo{ margin:50px auto; width:900px; min-height:99%; background:#CF9}
</style>
<script type="text/javascript">
function findsize(){
    $('#as').diyUpload({
    	buttonText : '选择文件',
		chunked:true,
		// 分片大小
		chunkSize:512 * 1024,
		//最大上传的文件数量, 总文件大小,单个文件大小(单位字节);
		fileNumLimit:1,
		fileSizeLimit:500000 * 1024,
		fileSingleSizeLimit:50000 * 1024,
		url:'<%=rootPath%>file/ckEditWordUpload.action',
		success:function( data ) {
				var str = JSON.stringify(data);
			 	console.info( str);	
			 	console.info("0000 +" + data._width);
			 	parent.content = "666666";
										},
		error:function( err ) {
				console.info( err );	
								},
		accept: {}						
				});
}

</script>
<body onload="findsize();">
	<div id="demo">
		<div id="as" ></div>
	</div>
</body>
<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">

</div>
</html>

