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
<title>专家</title>
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
<jsp:include page="../include/client_skin.jsp" />
<script type="text/javascript"
	src="<%=rootPath%>client/js/lhgdialog.min.js"></script>

<script language="JavaScript" type="text/javascript">
//初始化右侧类别树
$(function(){ 
	page(1);
});


//向专家提问
function askExpert(expertId,expertName) {
	//获得内容
	var result = $.ajax({
		url: '<%=rootPath%>expert/askExpert.action',
		dataType: "json",
	    async: false,
	    cache: false
	}).responseText;
	//初始化弹出框
	$.dialog({
		title:'向专家提问',
		content: result,
		max: false,
	    min: false, 
	    ok: function(){
	    	var questiontitlecontent = $("#questiontitlecontent").val();
	    //	var textareaShowNum='${textareaShowNum}';
	    	var questionInfo=$("#question_info").val();
	    	if(questiontitlecontent == "在此输入问题概要,150字以内"){
	    		questiontitlecontent="";
	    	}
	    	if($.trim(questiontitlecontent)=="" && $.trim(questionInfo)==""){
	    		$.dialog.tips("标题或者内容必须填写一项",1.5,"alert.gif");
	    		return false;
	    	}else{
	    		if(questiontitlecontent.length>150){
		    		$.dialog.tips("请填写150字以下的标题内容",1,"alert.gif");
		    		return false;
		    	}else if(questionInfo.length>300){
		    		$.dialog.tips("请填写300字以下的问题内容",1,"alert.gif");
		    		return false;
		    	}else{
		    		$.ajax({
			    		type:"POST",
			    		data:{
			    			questionInfo:questionInfo,
			    			expertName:expertName,
			    			expertId:expertId,
			    			questiontitle:questiontitlecontent
			    		},
			    		url:'<%=rootPath%>expert/ask.action',
			    		dataType: "json",
			    		success:function(Msg){
		    				if(Msg==1){
		    					$.dialog.tips('提问成功',1,'32X32/succ.png');
		    				}else{
		    					$.dialog.tips('提问失败',1,'32X32/fail.png');
		    				}
		    			}
		    		});
		    	}
	    	}
	    },
	    okVal:'提问',
	    cancelVal: '关闭',
	    cancel: true,
	    lock: true,
	    padding: 0
	});
}

//分页
function page(_nPageNum){
	$.ajax({
		type:'post',
		url:'<%=rootPath%>expert/getallexpert.action',
		data:{
			pageNum: _nPageNum,
			pageSize: $('#pageSize').val(),
			categoryId: $('#categoryId').val()
		},
		success:function(html){
			$('#expertList').html(html);
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
			<dt class="leftBox">全部专家分类</dt>
		</dl>
		</nav> </section>
		<section class="mainBox"> 
		<article class="location">
			<s:iterator value="listCategory" status="index">
				<s:if test="#index.first">
				<strong>
				<a href="<s:url namespace="/expert" action="listcategory"><s:param name="categoryId" value="categoryid" /></s:url>">
					<s:property value="cdesc" /></a></strong>
				</s:if>
				<s:else>
				><a href="<s:url namespace="/expert" action="listcategory"><s:param name="categoryId" value="categoryid" /></s:url>">
					<s:property value="cdesc" /></a>
				</s:else>
			</s:iterator>
		</article>
		<!-- <section class="layoutLeft"> -->
		<div style="width: 245px;float: left;display: inline;">
	        <nav class="folders">
			<s:iterator value="list">
	        	<div class="folder">
	            	<p><a href="<s:url namespace="/expert" action="listcategory"><s:param name="categoryId" value="categoryid" /></s:url>">
				<s:property value="cdesc" /></a></p>
	                <s:set var="childCate" value="findChildCategoryByParentId(categoryid)" />
	                <s:if test="#childCate!=null && #childCate.size()>0">
	               	 <section>
	                	<ul>
	                	<s:iterator value="#childCate">
	                    	<li><a href="<s:url namespace="/expert" action="listcategory"><s:param name="categoryId" value="categoryid" /></s:url>">
				<s:property value="cdesc" /></a></li>
	                    </s:iterator>
	                    </ul>
	                </section>
	                </s:if>
	            </div>
			</s:iterator>
	        </nav>
	       </div>
		<!-- </section> -->
		<div class="layoutMiddle1" style="width: 950px;margin: 0px 0 0 0px;">
		<input type="hidden" name="categoryId" id="categoryId" value="<s:property value="categoryId"/>" />
		<div class="rmzj">
			<div id="expertList"></div>
		</div>
		</div>
		</section>
		<div class="area10"></div>
		<footer>
		<section class="mainBox">
		<s:include value="../include/client_foot.jsp"></s:include></section></footer>
	</div>
</body>
</html>