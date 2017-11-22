<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>


<style type="text/css">
.div_one_tab{
	float: left;
	width: 25%;
	line-height: 40px;
	text-align: center; 
	background: url("<%=rootPath%>client/images/search_bug.gif"); 
	}
.div_one_tab_border{
	border: thin solid #F0F0F0;
}
</style>
<script type="text/javascript">
function bugInfo(serianum){
	//alert(bugId);
	hrefStr="<%=rootPath%>bug/simplebuginfo.action?serianum="+serianum;
	//alert(hrefStr);
	window.open(hrefStr);
}

</script>

</head>
  <body>
    <div>
    <div id="selectiterms">
    	<jsp:include page="bug_tome_iterm.jsp"></jsp:include>
    </div>
    <div id="info_page">
   		<jsp:include page="bug_tome_info_page.jsp"></jsp:include>
   	</div>	
    </div>
  </body>
</html>
