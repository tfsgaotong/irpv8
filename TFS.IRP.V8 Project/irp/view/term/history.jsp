<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 历史版本细览 -->
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><s:property value="qhobj.termname" />_百科词条</title>
	<link rel="shortcut icon" href="<%=rootPath%>favicon.ico" type="image/x-icon"/>
	<link rel="bookmark" href="<%=rootPath%>favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/icon.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/oacss.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/asx.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/normalize.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/ztree/css/zTreeStyle/zTreeStyle.css"/>
	<script type="text/javascript" src="<%=rootPath%>view/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/js/lhgdialog.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/js/easyui-lang-zh_CN.js"></script>
	<jsp:include page="../../view/include/client_skin.jsp" />
	<style type="text/css">
	.wtgy {
		height: 28px;
		line-height: 28px;
		border: 1px solid rgb(209, 209, 209);
		color: rgb(102, 102, 102);
		padding: 0px 5px;
		width: 300px;
		margin: 0 0 0 5px;
	}
	
	.btn_ccw {
		background: none repeat scroll 0 0 #63C7E6;
		color: #FFFFFF;
		display: block;
		width: 100px;
		float: left;
		line-height: 28px;
		font-size: 18px;
		padding: 0px 5px;
		font-style: normal;
		height: 30px;
		text-align: center;
	}
	
	body {
		behavior: url(hover.htc);
	}
	
	.searchSec .radios span {
		margin-top: 0px;
	}
	</style>
</head>
 
<script type="text/javascript">
$(function(){
	//选择头部标签
//	selected('wordterm');
	
});
</script>

<body style="background: url()">
    <jsp:include page="../../view/include/client_head.jsp" />
    <section class="mainBox">
	   <nav class="privateNav"></nav>
	</section>
    <div class="main" style="background: url(''); width: 1200px">
        <div style="min-height: 60vh">
            <div style="font-size: 24px;line-height: 34px;border-bottom: 1px solid;padding:0 0 10px 0;"><s:property value="qhobj.termname" />&nbsp;<lable style="font-size:14px;">(历史版本<s:property value="termhobj.termversion" />)</lable></div>
            <div style="margin: 20px 2px 0 1px;line-height: 24px;padding: 1% 1% 1% 1%;font-size: 14px;"><s:property value="termhobj.termexplain" escapeHtml="false" /></div>
        </div>	
        <jsp:include page="../../view/include/client_foot.jsp" />
    </div>
</body>
</html>