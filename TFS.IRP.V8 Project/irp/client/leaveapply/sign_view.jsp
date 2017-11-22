<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("M");
	int month = Integer.parseInt(sdf.format(date));
	if(month==1){
		month = 12;
	}else{
		month = month - 1;
	}
	String Month="上";
	if(month==1)Month="一";if(month==2)Month="二";if(month==3)Month="三";if(month==4)Month="四";if(month==5)Month="五";if(month==6)Month="六";if(month==7)Month="七";if(month==8)Month="八";if(month==9)Month="九";if(month==10)Month="十";if(month==11)Month="十一";if(month==12)Month="十二";
%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<title>请假明细</title>
	<link rel="Bookmark" href="images/24pinico.ico" />
	<link rel="Shortcut Icon" href="images/24pinico.ico" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/client/css/easyui.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/client/css/icon.css" />
	<link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css">
	<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css">
	<link href="<%=rootPath%>/client/css/oacss.css" rel="stylesheet" type="text/css" />

	<jsp:include page="../include/client_skin.jsp" />
	<script type="text/javascript" src="<%=rootPath%>/client/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/client/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath%>/admin/js/zyz_easydatecheck.js"></script>
<style type="text/css">
body{
	behavior:url(js/hover.htc);
}

.STYLE1 {
	color: #0000FF;
	font-weight: bold;
	font-size:18px;
}
.STYLE1 a:hover{
	color: #0000FF;
	font-weight: bold;
	font-size:18px;
}

body {
	behavior: url(hover.htc);
}

.cardList {
	width: 710px;
}

.cardList li {
	float: left;
	display: inline;
	margin: 8px 10px;
	position: relative;
}

.cardList li table {
	background: #fff;
}

.cardList li .darkSh {
	background: #ccc;
	position: absolute;
	left: 4px;
	right: -4px;
	top: 4px;
	bottom: -4px;
	z-index: -1;
}
#listuserul{
display: block;
width: 710px;
}
#listuserul li{
display: block;
width: 210px;
float: left;
}

.comm {
white-space: nowrap;
overflow: hidden;
text-overflow: ellipsis;
}

#signTable tr {
height: 33px;
}
.searchSec .radios span {
margin-top: 0px;
}

</style>

</head>
<body>	

    
  <jsp:include page="../../view/include/client_head.jsp" />
  <section class="mainBox">
	<nav class="privateNav">
	</nav>
	</section>
<div style="width:950px;margin: 0 auto;">
	<!--左侧内容-->
	<jsp:include page="sign_detail1.jsp"></jsp:include>
    <jsp:include page="../include/client_foot.jsp" />
</div>
<!--左侧内容结束-->


<script type="text/javascript">
//鼠标移动到上面显示
function client_to_index_person(_personid){
		window.open('<%=rootPath%>/site/client_to_index_person.action?personid='+_personid,'_blank');
	}


var useridchecker="";
var usernamechecker="";
function checkClick(_roleid){
useridchecker = _roleid;
usernamechecker =$("#leave"+_roleid).val();}
</script>
</body>
</html>