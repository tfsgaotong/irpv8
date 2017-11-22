<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@ page import="com.tfs.irp.util.LoginUtil"%>
<%@ page import="com.tfs.irp.user.entity.IrpUser"%>

<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	IrpUser loginUser = LoginUtil.getLoginUser();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>  

<link href="<%=rootPath %>client/images/css.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath%>client/css/oacss.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath%>client/js/skins/iblue.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>client/css/easyui.css" />
<style type="text/css">
div,form,img,ul,ol,li,dl,dt,dd {margin: 0; padding: 0; border: 0;}
h1,h2,h3,h4,h5,h6 { margin:0; padding:0;}
table,td,tr,th{font-size:12px;}
/* 星级评分 */
.shop-rating {
height: 25px;
overflow: hidden;
zoom: 1;
padding: 2px 0px;
position: relative;
z-index: 999;
}
.shop-rating span {
height: 23px;
display: block;
line-height: 23px;
float: left;
}
.shop-rating span.title {
width: 125px;
text-align: right;
margin-right: 5px;
}
.shop-rating ul {
float: left;
margin:0;padding:0
}
.shop-rating .result {
margin-left: 20px;
padding-top: 2px;
}
.shop-rating .result span {
color: #ff6d02;
}
.shop-rating .result em {
color: #f60;
font-family: arial;
font-weight: bold;
}
.shop-rating .result strong {
color: #666666;
font-weight: normal;
}
.rating-level,
.rating-level a {
	background: url( <%=rootPath%>client/images/star_v2.png) no-repeat scroll 1000px 1000px;
}
.rating-level {
background-position: 0px 0px;
width: 120px;
height: 23px;
position: relative;
z-index: 1000;
}
.rating-level li {
display: inline;
}
.rating-level a {
line-height: 23px;
height: 23px;
position: absolute;
top: 0px;
left: 0px;
text-indent: -999em;
*zoom: 1;
outline: none;
}
.rating-level a.one-star {
width: 20%;
z-index: 6;
}
.rating-level a.two-stars {
width: 40%;
z-index: 5;
}
.rating-level a.three-stars {
width: 60%;
z-index: 4;
}
.rating-level a.four-stars {
width: 80%;
z-index: 3;
}
.rating-level a.five-stars {
width: 100%;
z-index: 2;
}
.rating-level .current-rating,.rating-level a:hover{background-position:0 -28px;}
.rating-level a.one-star:hover,.rating-level a.two-stars:hover,.rating-level a.one-star.current-rating,.rating-level a.two-stars.current-rating{background-position:0 -116px;}
.rating-level .three-stars .current-rating,.rating-level .four-stars .current-rating,.rating-level .five-stars .current-rating{background-position:0 -28px;}

.ellipsis{
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap; 
}

.zj_wBox{
	margin: 0 auto;
	width: 1000px;
}

#page_break {

}
#page_break .collapse {
	display: none;
}
#page_break .num {
	padding: 10px 0;
	text-align: center;
}
#page_break .num li{
	display: inline;
	margin: 0 2px;
	padding: 3px 5px;
	border: 1px solid #72BBE6;
	background-color: #fff;
	
	color: #72BBE6;
	text-align: center;
	cursor: pointer;
	font-family: Arial;
	font-size: 12px;
	overflow: hidden;
}
#page_break .num li.on{
	background-color: #72BBE6;
	color: #fff;
	font-weight: bold;
} 
.documenttxt td p{
	text-indent:0;
}
</style> 
<script type="text/javascript" src="<%=rootPath%>/client/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/client/js/easyui-lang-zh_CN.js"></script> 
<script type="text/javascript" src="<%=rootPath%>/editor/simple_ckeditor/ckeditor.js"></script>

<script type="text/javascript">

	$(function(){
	
	$('#cahenisdsages').css({
		"height":"29px",
		"margin-top":"32px"
	});
	
	$('#serachText').css({
		"width": "155px",
		"height": "29px",
		"border": "medium none",
		"padding-left": "0px",
		"font-size": "12px",
		"color": "#333",
		"margin-top":"32px",
		"margin-left":"5px"
	});
	var dsize = '<s:property value="docversionlist.size()" />';
	var divmainheight = parseInt(dsize)*40;
	$('#maindivs').css({"margin":"5px 5px "+divmainheight+"px 5px"});
		
	});
	
	
	function page(_num){
		var _docid = '<s:property value="docid" />';
		window.location.href="<%=rootPath%>site/linkdocversionhistory.action?docid="+_docid+"&pageNum="+_num;
	}
	function linkHVersion(_docid){
	
	window.open("<%=rootPath%>site/showdocumentinfoversion.action?refrechstatus=1&docid="+_docid);
	}
	
</script>
</head>

<body> 
<s:include value="../include/enterprise_head.jsp"></s:include>
<div class="area1"></div>
<div id="maindivs" style="padding: 5px 5px 0 5px;">
<s:if test="docversionlist.size()>0">
		<div>
			<ul style="font-size: 120%;color: #A6A600;">
				<li style="float: left;width: 50px;">版本</li>
				<li style="float: left;width: 200px;">标题</li>
				<li style="float: left;width: 520px;">核心提示</li>
				<li style="float: left;width: 200px;">关键字</li>
				<li style="float: left;width: 130px;">修改人</li>
				<li style="float: left;width: 200px;">修改时间</li>
			</ul>
		</div>
	<s:iterator value="docversionlist" >
		<div id="<s:property value="docid" />cc" style="cursor: pointer;" onclick="linkHVersion(<s:property value="docid" />)">
		
			<ul >
				<li style="white-space:nowrap; text-overflow:ellipsis;overflow: hidden;width: 50px;float: left;padding: 5px 0 3px 0;border-bottom: thin dotted; "><s:property value="docversion" />.0版</li>
				<li style="white-space:nowrap; text-overflow:ellipsis;overflow: hidden;width: 200px;float: left;padding: 5px 0 3px 0;border-bottom: thin dotted; " title="<s:property value="doctitle" />"><s:property value="doctitle" /></li>
				<li style="white-space:nowrap; text-overflow:ellipsis;overflow: hidden;width: 520px;float: left;padding: 5px 0 3px 0;border-bottom: thin dotted; " title="<s:property value="docabstract" />"><s:property value="docabstract" /></li>
				<li style="white-space:nowrap; text-overflow:ellipsis;overflow: hidden;width: 200px;float: left;padding: 5px 0 3px 0;border-bottom: thin dotted; " title="<s:property value="dockeywords" />"><s:property value="dockeywords" /></li>
				<li style="white-space:nowrap; text-overflow:ellipsis;overflow: hidden;width: 130px;float: left;padding: 5px 0 3px 0;border-bottom: thin dotted;  "><s:property value="cruser" /></li>
				<li style="white-space:nowrap; text-overflow:ellipsis;overflow: hidden;width: 200px;float: left;padding: 5px 0 3px 0;border-bottom: thin dotted;  "><s:date name="docpubtime" format="yyyy-MM-dd HH:mm:ss" /></li>
			</ul>
					
		</div>
		
	</s:iterator><br/>
	<div class="main ">
		<div class="left">
			<div class="fyh ">
				<ul style="margin-right: -300px;"><s:property value="pageHTML" escapeHtml="false" /></ul>
			</div>
		</div>
	</div>
	
	</s:if>
	<s:else>
	<div style="text-align: center;">
	暂无历史版本
	</div>
	</s:else>
</div>
  <div class="area1" ></div>

  <s:include value="../include/enterprise_foot.jsp"></s:include>  
</body>
</html>