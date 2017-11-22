<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
<link href="<%=rootPath%>client/css/oacss.css" rel="stylesheet"	type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>client/css/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>client/css/easyui.css" />
<link rel="stylesheet" href="<%=rootPath%>client/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css" />
<jsp:include page="../include/client_skin.jsp" />
<link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css"/>
<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=rootPath %>client/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>/client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>/client/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.excheck-3.5.js"></script>

<style type="text/css">
body {
	behavior: url(hover.htc);
}

.STYLE1 {
	color: #0066FF;
	font-weight: bold;
}

.cardList {
	width: auto;
}

.cardList li {
	float: left;
	display: inline;
	margin: 15px 35px;
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
.wtgy {
    height: 28px;
    line-height:28px;
    border: 1px solid rgb(209, 209, 209);
    color: rgb(102, 102, 102);
    padding: 0px 5px;
}
</style>

<script language="JavaScript" type="text/javascript">
//初始化右侧类别树
$(function(){ 
	initZTree();
	page(1);
});

function initZTree(){
	$.fn.zTree.init($("#categoryTree"), {
		view: {
			showIcon: true
		},
		data: {
			simpleData : {
				enable : !0,
				idKey : "id",
				pIdKey : "pId"
			}
		},
		async: {
			enable: true,
			url:"<%=rootPath%>category/getAllCategory.action?showExpertList=1" 
		},
		callback: {
			onClick: function (event, treeId, treeNode, clickFlag){
				if(treeNode){
					 $('#categoryId').val(treeNode.id);
					 page(1);  
				 }
			}
		} 
	});
}

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
	    	var textareaShowNum=${textareaShowNum};
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
		    		$.dialog.tips("请填写"+300+"字以下的问题内容",1,"alert.gif");
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
		url:'<%=rootPath%>expert/find_expert_list.action',
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

<body onload="selected('expert')">
<div class="bg01">
	<!--头部菜单
	
	<s:include value="../include/client_head.jsp" />
	-->
	
	<jsp:include page="../../view/include/client_head.jsp" />
		   <section class="mainBox">
			<nav class="privateNav">
			</nav>
			</section>
	<!--头部菜单end-->
	<div class="main">
		<!--右侧内容-->
		<div class="left">
			<div class="gr2">
				<dd id="expertList"></dd>
			</div>
		</div>
		<div class="right">
			<%--分类问答 --%>
			<dl style="margin-top:10px;">
				<span class="linkbh14" style="font-weight:bold;cursor:pointer;">专家分类</span>
				<a onclick="javascript:window.location.reload()" style="font-size:12px;margin-left: 15px;" href="javascript:void(0)">刷新</a>
				<ul id="categoryTree" class="ztree" style="background-color: white;height: 100%;border: none;overflow-y:auto;"></ul>
			</dl>
		</div>
		<!--右侧内容结束-->
		<s:include value="../include/client_foot.jsp"></s:include>
	</div>
</div>
</body>
</html>