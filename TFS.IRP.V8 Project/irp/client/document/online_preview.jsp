<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文档在线预览</title>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>client/css/flexpaper.css" />

<script type="text/javascript" src="<%=rootPath%>client/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/flexpaper.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/flexpaper_handlers.js"></script>
<style type="text/css" media="screen">
html,body{
	height: 100%;
	width: 100%;
}
body {
	margin: 0;
	padding: 0;
	overflow: auto;
}
#flashContent {
	display: none;
}
</style>
<script type="text/javascript">
$(function(){
	$('#documentViewer').FlexPaperViewer({
		config : {
			SWFFile : '<%=rootPath%>file/readfile.action?fileName=<s:property value="swfFileName" escapeHtml="false" />',
            jsDirectory : '<%=rootPath%>client/js',
            Scale : 0.6,
            ZoomTransition : 'easeOut',
            ZoomTime : 0.5,
            ZoomInterval : 0.2,
            FitPageOnLoad : true,
            FitWidthOnLoad : false,
            FullScreenAsMaxWindow : false,
            ProgressiveLoading : false,
            MinZoomSize : 0.2,
            MaxZoomSize : 5,
            SearchMatchAll : false,
            InitViewMode : 'Portrait',
            RenderingOrder : 'flash',
            StartAtPage : '',
            ViewModeToolsVisible : true,
            ZoomToolsVisible : true,
            NavToolsVisible : true,
            CursorToolsVisible : true,
            SearchToolsVisible : true,
            WMode : 'window',
            localeChain: 'zh_CN'
		}
	});
});
</script>
</head>
<body>
<div id="documentViewer" class="flexpaper_viewer"></div>
</body>
</html>