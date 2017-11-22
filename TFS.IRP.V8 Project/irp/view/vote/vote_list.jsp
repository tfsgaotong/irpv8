<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- 投票首页 -->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <title>投票</title>
    <link rel="shortcut icon" href="<%=rootPath%>favicon.ico" type="image/x-icon"/>
    <link rel="bookmark" href="<%=rootPath%>favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/normalize.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/common1.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/oacss.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/easyui.css"/>
    <script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="<%=rootPath %>view/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=rootPath %>view/js/lhgdialog.min.js"></script>
    <script type="text/javascript" src="<%=rootPath %>view/js/modernizr.custom.js"></script>
    <script type="text/javascript" src="<%=rootPath %>view/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="<%=rootPath %>view/js/swfobject.js"></script>
    <script type="text/javascript" src="<%=rootPath %>view/js/jquery.uploadify.min.js"></script>
    <jsp:include page="../../view/include/client_skin.jsp" />
	<style type="text/css">
	body {
		behavior: url(js/hover.htc);
	}
	
	.STYLE1 {
		color: #0000FF;
		font-weight: bold;
		font-size: 18px;
	}
	
	.STYLE1 a:hover {
		color: #0000FF;
		font-weight: bold;
		font-size: 18px;
	}
	
	body {
		behavior: url(hover.htc);
	}
	
	.cardList {
		width: 930px;
	}
	
	.cardList li {
		float: left;
		display: inline;
		margin: 10px 20px;
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
	
	.bar {
		background: none repeat scroll 0 0 #F0F0F0;
		float: left;
		height: 11px;
		margin-top: 2px;
		width: 100px;
	}
	
	.resultbar {
		float: right;
	}
	
	.colors {
		float: left;
		height: 11px;
		margin-top: 2px;
		margin-left: -100px;
		width: 100px;
	}
	
	.sgn_0,.sgn_10 {
		background: url("../view/images/bg_bar.gif") repeat-x scroll left 0;
	}
	
	.sgn_1,.sgn_11 {
		background: url("../view/images/bg_bar.gif") repeat-x scroll left
			-13px;
	}
	
	.sgn_2,.sgn_12 {
		background: url("../view/images/bg_bar.gif") repeat-x scroll left
			-26px;
	}
	
	.sgn_3,.sgn_13 {
		background: url("../view/images/bg_bar.gif") repeat-x scroll left
			-39px;
	}
	
	.sgn_4,.sgn_14 {
		background: url("../view/images/bg_bar.gif") repeat-x scroll left
			-52px;
	}
	
	.sgn_5,.sgn_15 {
		background: url("../view/images/bg_bar.gif") repeat-x scroll left
			-65px;
	}
	
	.sgn_6,.sgn_16 {
		background: url("../view/images/bg_bar.gif") repeat-x scroll left
			-78px;
	}
	
	.sgn_7,.sgn_17 {
		background: url("../view/images/bg_bar.gif") repeat-x scroll left
			-91px;
	}
	
	.sgn_8,.sgn_18 {
		background: url("../view/images/bg_bar.gif") repeat-x scroll left
			-104px;
	}
	
	.sgn_9,.sgn_19 {
		background: url("../view/images/bg_bar.gif") repeat-x scroll left
			-117px;
	}
	</style>
</head>
<script type="text/javascript">
$(function(){
	var ismyorall= '<s:property value="ismyorall" />';
	if(ismyorall=="1"){
		$("#vote_all").addClass("over");
		loadvote();
	}else{
		$("#vote_launch").addClass("over");
		votemy();
	}
	hotVote();
});

function loadvote(){
	$("#leftvote").empty();
	var result=$.ajax({
		type:'post',
		url:'<%=rootPath%>menu/vote_list.action',
		dataType: "json",
		async: false,
   		cache: false  
		}).responseText;
	$("#leftvote").html(result);	
}

function votemy(){
	$("#leftvote").empty();
	var result=$.ajax({
		type:'post',
		url:'<%=rootPath%>menu/vote_myvote.action',
		dataType: "json",
		async: false,
   		cache: false  
		}).responseText;
	$("#leftvote").html(result);
}

/*处理投票*/
function tabtopic(_a){
	$('#topicitem').find('a').each(function(){
		if(this.id ==_a){
			this.className="over";
			
		}else{
			this.className="";
		}
	});
	pagetopic(1);
}

/*page 处理专题项*/
function pagetopic(pageNum){
	var liDom = $('#topicitem').find('.over');
	var sUrl = liDom.attr("_href");
	var sData = liDom.attr("_data");
	if(sData==''){
		sData = "pageNum="+pageNum;
	}else{
		sData += "&pageNum="+pageNum;
	}
	var sHtmlConn = $.ajax({ 
 		type:'post', 
 		url: sUrl,
 		data: sData,
 		dataType: "json",
		async: false,
	    cache: false  
 	}).responseText;
	$("#leftvote").empty();
	$('#leftvote').html(sHtmlConn);
}

function page(pageNum){
	$('#leftvote').empty();
	var liDom = $('#topicitem').find('.over');
	var sUrl = liDom.attr("_href");
	var sData = liDom.attr("_data");
	if(sData==''){
		sData = "pageNum="+pageNum;
	}else{
		sData += "&pageNum="+pageNum;
	}
	var sHtmlConn = $.ajax({ 
 		type:'post', 
 		url: sUrl,
 		data: sData,
 		dataType: "json",
		async: false,
	    cache: false  
 	}).responseText;
	$('#leftvote').html(sHtmlConn);
}

//发起投票
function clickvote(){
		//获得内容
		var result = $.ajax({
			url: '<%=rootPath%>view/vote/vote_menu.jsp',
			dataType: "json",
		    async: false,
		    cache: false
		}).responseText;
		//初始化弹出框
		$.dialog({
			id:'votedialog',
			title:'发起投票',
			content: result,
			max: false,
		    min: false, 
		    lock:true,
		    width:500,
		    height:400,
		    close:function(){
		    	location.reload();
		    },
		    cancel:function(){ 
	   		},
		    cancelVal: '关闭',
		    padding: 0
		});
	}


function hotVote(){
	$('#hotvote').empty();
	var sUrl="<%=rootPath%>menu/hot_vote.action";
	var sHtmlConn = $.ajax({ 
 		type:'post', 
 		url: sUrl,
 		dataType: "json",
		async: false,
	    cache: false  
 	}).responseText;
	$('#hotvote').html(sHtmlConn);
}

function sendmic(microblog_text,urltext){
	 var result=" <textarea rows=\"1.5\" cols=\"36\" style=\"width: 400px;height: 100px;\" id=\"votemictext\">"+microblog_text+"</textarea>";
	 $.dialog({
			title:'发投票微知',
			content: result,
			max: false,
		    min: false, 
		    lock:true,
		    width:500,
		    height:200,
		    cancel:function(){ 
	   		},
		    cancelVal: '关闭',
		    ok: function(){
		    	if($.trim($("#votemictext").val())==""){
		    		$.dialog.tips('微知不能为空',1,'32X32/fail.png');
		    	}else{
		    		var microblog_type=0;
		    		var microblog_text= $("#votemictext").val()+urltext;
	    			var microbloggroup = "公开";
	    			$.ajax({
	    				type:"POST",
	    				url:'<%=rootPath%>microblog/microblogShare.action',
	    				cache:false,
	    				data:{
	    					publishInfo:microblog_text,
	    					microblogType:microblog_type,
	    					microbloggroup:microbloggroup
	    				},
	    				success:function(callback){
	    					if(callback!=null){
	    						$.dialog.tips('分享成功',1,'32X32/succ.png');
	    					}else{
	    						$.dialog.tips('分享失败',1,'32X32/fail.png'); 
	    					}
	    				}
	    			});
		    	}
		    },
		    okVal:'发投票微知',
		    padding: 0
		});
}
</script>
<body onload="selected('votepage')" style="background-image: url()">
	<form id="pageForm">
		<s:hidden name="pageNum" id="pageNum" />
		<s:hidden name="pageSize" id="pageSize"/>
	</form>
	
	<div class="bg01">
        <jsp:include page="../../view/include/client_head.jsp" />
        <section class="mainBox">
            <nav class="privateNav" style="background:url(../images/bg-04.gif) repeat-x">
                <dl>
                    <dt class="leftBox">投票分类</dt>
                    <dd class="clear"></dd>
	            </dl>
	        </nav>
	    </section>
        <div class="main" style="width: 1200px;background:url()">
            <!-- 分类列表 -->
	        <section class="layoutLeft">
	            
	            <nav class="allBtns">
	                <ul id="topicitem">
	                   <li>
	                       <a id="vote_all" href="javascript:void(0)" _href="<%=rootPath%>menu/vote_allvote.action" _data="" onclick="tabtopic(this.id)">全部投票</a> 
	                   </li>
	                   <li>
	                       <a id="vote_launch" href="javascript:void(0)" _href="<%=rootPath%>menu/vote_myvote.action" _data="" onclick="tabtopic(this.id)">我发起的</a> 
	                   </li>
	                   <li>
	                       <a id="vote_participation" href="javascript:void(0)" _href="<%=rootPath%>menu/vote_partmevote.action" _data="" onclick="tabtopic(this.id)">我参与的</a> 
	                   </li>
	                </ul>
	            </nav>
            </section>
        
            <!--投票列表-->
            <div class="left" style="margin:20px 0 0 20px;background:url(); width: 660px">
	            <div id="leftvote" class="fyh" style="margin: 0px;width: 100%"></div>
            </div>
            
            <!-- 右侧栏 -->
            <div class="right" style="width: 275px">
                <input value="发起投票" onclick="clickvote()" style="background: url(../images/icon-02.png) no-repeat 9px center #62a53b;margin:20px 0 0 0;border-radius:3px; font-size:18px; width: 275px;height:65px;color: white;background-color: #62a53b;" type="button">
				<div class="duo">
					<dl id="hotvote" style="border-bottom: none;width: 255px"></dl> 
				</div>
			</div>
            <jsp:include page="../../view/include/client_foot.jsp" />
        </div>
    </div>
</body>
</html>