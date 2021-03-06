<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>考试</title>
<link rel="shortcut icon" href="<%=rootPath%>favicon.ico"
	type="image/x-icon" />
<link rel="bookmark" href="<%=rootPath%>favicon.ico" />
<link href="<%=rootPath%>client/images/normalize.css" rel="stylesheet"
	type="text/css" />

	<link href="<%=rootPath%>client/css/common1.css" rel="stylesheet"
	type="text/css" />
<link href="<%=rootPath%>view/css/common.css" rel="stylesheet"
	type="text/css" />
<script src="<%=rootPath%>client/images/modernizr.custom.js"></script>
<script src="<%=rootPath%>client/images/jquery-1.8.0.min.js"></script>
<link href="<%=rootPath%>client/css/oacss.css" rel="stylesheet"
	type="text/css" />
	<link href="<%=rootPath%>client/css/examlist.css" rel="stylesheet" type="text/css"/>
<jsp:include page="../include/client_skin.jsp" />
<script type="text/javascript"
	src="<%=rootPath%>client/js/lhgdialog.min.js"></script>

<script language="JavaScript" type="text/javascript">
$(function(){
	examlist();
});
function examlist(){
	$.ajax({
		type:"post",
		url:"<%=rootPath%>exam/pageexam.action",
		data:{
		},
		async:false,
		cache:false,
		success:function(data){
			$('.examlist').html(data);
		}
	});
}
/**
* 参加考试
*/
function visitExam(_content,_examid){
	$.ajax({
		type:'post',
		url:"<%=rootPath%>exam/joinexam.action",
		async:false,
		cache:false,
		data:{exam:_examid},
		success:function(content){
			var cons = eval(content);
			var time = parseInt(cons[0].exam.begintime);
			var systime = new Date();
			if(parseInt(time)>parseInt(systime.getTime())){
				$.dialog.tips("考试时间未到！",1,"alert.gif");	
			}else{
				if(_content!=undefined){
					window.open("<%=rootPath%>exam/linksingleques.action?paper="+_content+"&exam="+_examid);
				}
			}
		},
		error:function(e){
			
		}
	});
	
}
</script>
</head>

<body onload="selected('expert')" style="background: url()">
	<div class="bg01">
		<jsp:include page="../../view/include/client_head.jsp" />
		<section class="mainBox"> <nav class="privateNav">
		<dl>
			<dt class="leftBox">全部考试分类</dt>
		</dl>
		</nav> </section>
		<section class="mainBox"> 
		<div class="main" style="background-image: url('');width:1200px; box-sizing: border-box;">
		<article class="location">
			
				
				<strong>
				<a href="">待参加考试</a></strong>
			
		</article>
		<!-- <section class="layoutLeft"> -->
		<div style="width: 245px;float: left;display: inline;">
	        <nav class="folders">
	        	<div class="folder">
	            	<p><a href="">待参加</a></p>
	                <s:set var="childCate" value="findChildCategoryByParentId(categoryid)" />
	                
	            </div>
	        </nav>
	       </div>
	   
		<div class="examlist" style="float:right;width:930px;">
		
		</div>
		<div class="area10"></div>
		<footer>
		</div>
		<section class="mainBox">
		<s:include value="../include/client_foot.jsp"></s:include></section></footer>
	</div>
</body>
</html>