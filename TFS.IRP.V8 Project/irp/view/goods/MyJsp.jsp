<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.tfs.irp.util.SysConfigUtil"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	.file {
    position: relative;
    display: inline-block;
    background: #ccc;
    border: 1px solid #333;
    padding: 4px 20px;
    overflow: hidden;
    text-decoration: none;
    text-indent: 0;
    line-height: 20px;
    border-radius: 20px;
    color: #333;
    font-size: 13px;

}
.file input {
    position: absolute;
    font-size: 100px;
    right: 0;
    top: 0;
    opacity: 0;
}
.gradient{
   
    filter:alpha(opacity=100 finishopacity=50 style=1 startx=0,starty=0,finishx=0,finishy=150) progid:DXImageTransform.Microsoft.gradient(startcolorstr=#fff,endcolorstr=#ccc,gradientType=0);
    -ms-filter:alpha(opacity=100 finishopacity=50 style=1 startx=0,starty=0,finishx=0,finishy=150) progid:DXImageTransform.Microsoft.gradient(startcolorstr=#fff,endcolorstr=#ccc,gradientType=0);/*IE8*/    
    background:#ccc; /* 一些不支持背景渐变的浏览器 */  
    background:-moz-linear-gradient(top, #fff, #ccc);  
    background:-webkit-gradient(linear, 0 0, 0 bottom, from(#fff), to(#ccc));  
    background:-o-linear-gradient(top, #fff, #ccc); 
}
	</style>
  </head>
  
  <body>
 <%--  <script type="text/javascript" src="<%=rootPath %>view/js/modernizr.custom.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/easyui-lang-zh_CN.js"></script> --%>
<%-- <script type="text/javascript" src="<%=rootPath %>editor/ckeditor/ckeditor.js"></script> --%>
<%-- <script type="text/javascript" src="<%=rootPath %>editor/applet/ckwordup.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/json2.js"></script> --%>
  <script type="text/javascript">
  $(".file").on("change","input[type='file']",function(){
alert(111);
    var filePath=$(this).val();
    if(filePath.indexOf("jpg")!=-1 || filePath.indexOf("png")!=-1){
        $(".fileerrorTip1").html("").hide();
        var arr=filePath.split('\\');
        var fileName=arr[arr.length-1];
        $(".showFileName1").html(fileName);
    }else{
        $(".showFileName1").html("");
        $(".fileerrorTip1").html("您未上传文件，或者您上传文件类型有误！").show();
        return false
    }
})
  </script>
   <a href="javascript:;" class="file gradient">选择图片
		<input type="file" />
		</a>
		
	 <div>
        	<textarea id="editor" name="editor"></textarea>
        	<script>
	        	CKEDITOR.replace('editor',{
					filebrowserUploadUrl: '<%=rootPath%>file/ck_upload.action',
					width:850
				});
				<%-- //构建图片上传地址
	            var sUrl = '<%=rootPath%>file/ck_word_upload.action;jsessionid=<%=session.getId()%>';
	            //构建应用名称，如本系统名称为‘wordimg’，如果为根应用，请写为空字符串''        
	            var appName = '<%=rootPath%>';
	            //创建WordImageUploader对象
	            var uploader = new CK_WordImageUploader(sUrl, appName);
	            //当ckeditor内容改变时，自动检测并上传内容中的本地图片
	            CKEDITOR.instances.editor.on('paste', function(event) {
	            	uploader.uploadWordImagesFromCKEditor(CKEDITOR.instances.editor, event);
	            }); --%>
		    </script>
        </div>
  </body>
</html>
