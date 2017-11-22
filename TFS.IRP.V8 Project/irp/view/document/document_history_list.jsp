<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!doctype html>
<html lang="en" class="no-js">
<head>
<meta charset="utf-8">
<title>历史版本</title>  
<link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css">
<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css">
<style type="text/css">
.resultList .version{width:80px;}
.resultList .title{width:290px;}
.resultList .abstract{width:300px;}
.resultList .keywords{width:220px;}
.resultList .author{width:130px;}
.resultList .editdate{width:150px;text-align:center;}
.resultList .reorder{padding:0;}
.resultList .reorder span{display:inline-block;text-align:center;font-size:14px;}
.resultList .list1{padding:0;}
.resultList .list1 li{cursor:pointer;color:#666;border-bottom:1px dotted #ececec;}
.resultList .list1 li:hover{background-color:#f1f2f4;}
.resultList .list1 li span{text-indent:10px;}
</style>

<script type="text/javascript" src="<%=rootPath %>view/js/modernizr.custom.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript">
function page(_num){
	var _docid = '<s:property value="docid" />';
	window.location.href="<%=rootPath%>document/document_history_list.action?docid="+_docid+"&pageNum="+_num;
}
function showVersion(_docid){
	window.open("<%=rootPath%>document/document_version_detail.action?docid="+_docid);
}
	
</script>
</head>

<body>
<s:include value="../include/client_head.jsp"></s:include>
<section class="mainBox">
	<nav class="privateNav">
    	<dl></dl>
    </nav>
</section>
<section class="mainBox">
	<section class="resultList" id="list">
		<div class="reorder">
			<span class="version">版本</span>
			<span class="title">标题</span>
			<span class="abstract">核心提示</span>
			<span class="keywords">关键字</span>
			<span class="author">修改人</span>
			<span class="editdate">修改时间</span>
		</div>
	<s:if test="docversionlist!=null && docversionlist.size()>0">
		<ul class="list1">
		<s:iterator value="docversionlist">
          	<li onclick="showVersion(<s:property value="docid" />)">
          		<span class="version ellipsis"><s:property value="docversion" />.0版</span>
				<span class="title ellipsis" title="<s:property value="doctitle" />"><s:property value="doctitle" /></span>
				<span class="abstract ellipsis" title="<s:property value="docabstract" />"><s:property value="docabstract" /></span>
				<span class="keywords ellipsis" title="<s:property value="dockeywords" />"><s:property value="dockeywords" /></span>
				<span class="author ellipsis" title="<s:property value="cruser" />"><s:property value="cruser" /></span>
				<span class="editdate ellipsis"><s:date name="docpubtime" format="yyyy-MM-dd HH:mm:ss" /></span>
          	</li>
        </s:iterator>
		</ul>
		<div class="area20"></div>
		<div class="pages">
			<s:property value="pageHTML" escapeHtml="false" />
		</div>
	</s:if>
	<s:else>
		<div style="color:#666;font-size:14px;height:30px;line-height:30px;">
			该知识暂时没有历史版本记录...
		</div>
	</s:else>
	</section>
</section>
<s:include value="../include/client_foot.jsp"></s:include>
</body>
</html>