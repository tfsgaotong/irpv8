<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>   
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=rootPath%>view/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
function initParm(){
    $("#lesscheck").hide();
    $("#morecheck").hide();
	$('#title').validatebox({   
	    required: true,   
	    validType: 'length[2,100]',
	    invalidMessage:'请输入2到100个字符'
	});
	$('#votetitle').validatebox({   
	    required: true,   
	    validType: 'length[2,70]',
	    invalidMessage:'请输入2到70个字符'
	});
	 var todayTime= new Date(<%=new java.util.Date().getTime()%>); 
	 $('#starttime').datetimebox({  
		editable:false,
		setValue: todayTime 

	});  
	$('#endtime').datetimebox({
		editable:false,
		setValue: todayTime 
	});
	//引入扩展验证
	$.extend($.fn.validatebox.defaults.rules, {
	    EndTime:{
	    	validator:function(){
	    		try {
	    			if($('#endtime').datetimebox('getValue')>=$('#starttime').datetimebox('getValue')){
		    			return true;
		    		}else{	  
		    			return false;
		    		}
				} catch (e) {
				}
	    	},
	    	message:'结束日期必须在开始日期之后'
		 }
	}); 
}
//文字投票
function wordvote(){
	$("#optinion_vote").removeClass("over");
	$("#pic_vote").removeClass("over");
	$("#word_vote").addClass("over");
	$("#votetype").empty();
	var result = $.ajax({
		url: '<%=rootPath%>view/vote/vote_word_form.jsp',
		dataType: "json",
	    async: false,
	    cache: false
	}).responseText;
	$("#votetype").html(result);
	initParm();
	
}


//图片投票
function picvote(){
	$("#word_vote").removeClass("over");
	$("#optinion_vote").removeClass("over");
	$("#pic_vote").addClass("over");
	$("#votetype").empty();
	var result = $.ajax({
		url: '<%=rootPath%>view/vote/vote_pic_form.jsp',
		dataType: "json",
	    async: false,
	    cache: false
	}).responseText;
	$("#votetype").html(result);
	initParm();
}

//正反投票
function optinionvote(){
	$("#word_vote").removeClass("over");
    $("#pic_vote").removeClass("over");	
	$("#optinion_vote").addClass("over");
	$("#votetype").empty();
	var result = $.ajax({
		url: '<%=rootPath%>view/vote/vote_opinion_form.jsp',
		dataType: "json",
	    async: false,
	    cache: false
	}).responseText;
	$("#votetype").html(result);
	initParm();
}
//链接投票
function urlvote(){
	$("#word_vote").removeClass("over");
	$("#pic_vote").removeClass("over");
	$("#url_vote").addClass("over");
	$("#votetype").empty();
	var result = $.ajax({
		url: '<%=rootPath%>view/vote/vote_url_form.jsp',
		dataType: "json",
	    async: false,
	    cache: false
	}).responseText;
	$("#votetype").html(result);
	
	initParm();
}
</script>
<h1  class="zl3" id="voteitem" style=" font-weight:normal;  line-height:50px; border-bottom:1px  solid #efefef;text-align:left; font-family:微软雅黑; margin:0 0 20px 5px; color:#121212;">
    <a id="word_vote" href="javascript:void(0)" onclick="wordvote()"> <font class="linkbh14">文字投票</font></a>  
    <a id="pic_vote" href="javascript:void(0)" onclick="picvote()"> <font class="linkbh14">图片投票</font></a>
    <a id="optinion_vote" href="javascript:void(0)" onclick="optinionvote()"> <font class="linkbh14">正反投票</font></a>  
</h1>
<div id="votetype">
</div>

