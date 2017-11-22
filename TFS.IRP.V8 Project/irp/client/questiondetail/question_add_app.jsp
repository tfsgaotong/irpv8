<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.tfs.irp.question.entity.IrpQuestion"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<style type="text/css">
<!--
.resetB{
	background: url('<%=rootPath %>client/images/closes.png') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
	margin-top: 2px;
	cursor:pointer;
}
-->
</style>
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/client/css/easyui.css" />
<jsp:include page="../include/client_skin.jsp" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/client/css/icon.css" />
<link href="<%=rootPath%>/client/css/oacss.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/client/css/easyui.css" />
<link rel="stylesheet" href="<%=rootPath%>client/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css" />
 <link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css"/>
<%-- <link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css"/> --%>
<script type="text/javascript" src="<%=rootPath%>client/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/easyui-lang-zh_CN.js"></script>
	
<script type="text/javascript" src="<%=rootPath%>client/js/questionjson/json2.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript">
<!--
$(function(){
document.getElementById("selectedExpertId").value = 0; 
	$('#showcate').click(function(){
		$('#cate').show(666);
		$('#resetB').show(1000);
	});
});
function resetValue(){
	$('#cate').val('');
	$('#cateIds').val('');
}
//标题字数控制
function questionTitleInfoControl(questionInfo,total){
	if(total-$.trim(questionInfo).length>=0){
		$('#titleAcreaNumChaochu').css({display:'none'});
		$('#titleAcreaNumfont').css({display:'block'});
		$('#titleAcreaNumfontNum').text(total-$.trim(questionInfo).length);
	}else{
		$('#titleAcreaNumfont').css({display:'none'});
		$('#titleAcreaNumChaochu').css({display:'block'});
		$('#titleerrorNumCount').text(Math.abs(total-$.trim(questionInfo).length));
	}
}
//字数限制
function questionInfoControl(questionInfo,total){
	if(total-$.trim(questionInfo).length>=0){
		$('#errorQuestionNum').css({display:'none'});
		$('#textAreaNum').css({display:'block'});
		$('#questionNum').text(total-$.trim(questionInfo).length);
	}else{
		$('#textAreaNum').css({display:'none'});
		$('#errorQuestionNum').css({display:'block'});
		$('#errorNumCount').text(Math.abs(total-$.trim(questionInfo).length));
	}
}
/**
	 * 选择问题的分类
	 */
	function showLeftTree(){
		var categoryName = $('#cateIds').val();
		var result = $.ajax({
			url:"<%=rootPath%>phone/getLeftCategory.action?categoryName="+categoryName+"&token="+$('#tokens').val(),
			cache:false,
			async:false
		}).responseText;
		$.dialog({
	  		content:result,
	  		title:'选择分类',
	  		min:false,
	  		max:false,
		    lock:true,
		    ok:function(){
		    },
		    okVal:'确认',
//		    cancelVal: '取消',
//		    cancel: true,
		    padding: 0
	  	});
	 }
	 
//选择专家
function selectExpert(){
	//获得专家
	var result = $.ajax({
		url: '<%=rootPath%>phone/selectExpert.action?token='+$('#tokens').val(),
		type:"post",
		dataType: "json",
	    async: false
	}).responseText;
	
	$.dialog({
		title:'选择专家',
		max:false,
		min:false,
		lock:true,
		resize: false,
		content:result,
		cancelVal:'关闭',
		okVal:'确定',
		cancel:function(){
			$("#checkBox").attr("checked",false);
			$("#selectedExpert").hide();
		},
		ok:function(){
			var expertId,expertName;
			var expertTab = $('.selectedExpert');
			if(expertTab){
				expertId = expertTab.attr('_eid');
				expertName = expertTab.attr('_ename');
			}
			if(expertName==undefined){
				$("#checkBox").attr("checked",false);
				$("#selectedExpert").hide();
			}else{
				$("#checkBox").attr("checked",true);
				$("#selectedExpert").text(expertName);
				$("#selectedExpertId").val(expertId);
			}
		}
	});
	$("#selectedExpert").show().addClass('expert');
}
//提问
function publish(total) {
	var category = $('#cateIds').val();
	$('#questionpublishfb').attr("onclick","");
	var questiontitle = $('#questiontitle').val();
	var expertId = $('#selectedExpertId').val();
	var question_info = $('#question_info').val();
	if(questiontitle == "在此输入问题概要,150字以内"){
		questiontitle="";
	}
	if($.trim(questiontitle)=="" && $.trim(question_info) == ""){
		$.dialog.tips("标题或者内容必须填写一项",1.5,"alert.gif");
		$('#questionpublishfb').attr("onclick","publish(300)");
		return false;
	}else{
		if(questiontitle.length>150){
			$('#questionpublishfb').attr("onclick","publish(300)");
			$.dialog.tips("请填写150字以下的标题内容",1,"alert.gif");
			return false;
		}else if(question_info.length>total){
			$('#questionpublishfb').attr("onclick","publish(300)");
			$.dialog.tips("请填写"+total+"字以下的问题内容",1,"alert.gif");
			return false;
		}else{
			$.ajax({
				type : "POST",
				url : '<%=rootPath%>phone/ask.action?token='+$('#tokens').val(),
				dataType : "json",
				cache:false,
				async:false,
				data : {
					questionInfo : question_info,
					expertId : expertId,
					/* jsonFile : JSON.stringify(addJsonFileList), */
					questiontitle : questiontitle,
					categories : category
				},
				success : function(nMsg) {
					$('#questiontitle').val("");
					$('#question_info').val("");
					if (nMsg > 0) {
						$.dialog.tips('提问成功',1,'32X32/succ.png',function(){ 
					    	window.location.href='<%=rootPath%>phone/questionDetail.action?questionid='+nMsg+'&token='+$('#tokens').val();
						});
					    config();
						var str=$.ajax({
							type:'post',
							url:'<%=rootPath%>question/questionList.action',
							dataType: "json",
							async: false,
					   		cache: false  
							}).responseText;
						
						if($('#all').attr("class")=="over" || $('#no').attr("class")=="over"){
							$('#questionsAll').html(str);	
							var treeObjr = $.fn.zTree.getZTreeObj("categoryTree");
							var treeObjl = $.fn.zTree.getZTreeObj("treeDemo");
							treeObjr.reAsyncChildNodes(null, "refresh",true);
							treeObjl.reAsyncChildNodes(null, "refresh",true);
						}
						//清空
						addJsonFileList.length=0;  
					} else {
						$.dialog.tips('提问失败', 1, '32X32/fail.png');
					}
					$('#questionpublishfb').attr("onclick","publish(300)");
				}
			});
		}
	}
}
//-->
</script>
<body style="background: url();"> 
<input type="hidden" value="<s:property value='tokens'/>" id="tokens"/>
<div class="main" style="width: 100%;">
<!--左侧内容-->
<div class="left" style="width: 100%;">
<div class="shuru" id="Num" style="width: 94%;padding-left: 3%;" align="center">
<ul style="background:url() no-repeat transparent;width: 100%;">
	<li style="background-color: white;font-size: 20px;padding-bottom: 10px;color: #f1f1f1;">智慧从好问开始</li>
	<li id="titleAcreaNumfont" style="text-align: right; display: block;padding-right: 5%;">
		您还可以输入<label id="titleAcreaNumfontNum" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;">150</label>个字
	</li>
	<li id="titleAcreaNumChaochu" style="text-align: right; display: none;padding-right: 5%;">
			超出了 <label id="titleerrorNumCount" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color:red;"></label>个字
	</li>
	<li><input style="width: 98%;line-height: 180%" id="questiontitle" class="wtgy" type="text" onkeyup="questionTitleInfoControl(this.value,150)" /></li>
	<li id="textAreaNum" style="text-align: right; display: block;padding-right: 5%;">
		您还可以输入 <label id="questionNum"
		style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;">300</label>
		个字</li>
	<li id="errorQuestionNum" style="text-align: right;display: none;padding-right: 5%;">
		超出了 <label id="errorNumCount"
		style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color:red;">
	</label>个字</li>
	<li><textarea style="width: 98%;background: white" name="textarea" rows="4" id="question_info"
			onkeyup="questionInfoControl(this.value,300)"></textarea></li>
	<li style="height:40px;line-height:40px;vertical-align:middle;">
		<div style="float:left;line-height: 40px;margin-left: 10px">
			<span style="cursor:pointer;color:#4b9bc0;" id="showcate" onclick="showLeftTree()">选择类别</span> 
			<span style="margin-top:10px;"><input onclick="showLeftTree()" id="cate" type="text" readonly="readonly" value="" style="display:none;width:120px;margin-right: 5px;margin-left: 8px;"/></span>
			<span style="margin-top:10px;"><input type="button" id="resetB" title="重置" onclick="resetValue()" class="resetB" style="display:none;width:10px;height:10px;" /></span>
			<input id="cateIds" type="hidden" readonly value=""/>
		</div>
	</li>
	<li style="height:40px;line-height:40px;vertical-align:middle;">
		<div style="height:40px;line-height:40px;width:100px;float:left;margin-left: 10px;"><span style="cursor:pointer;color:#4b9bc0;" onclick="selectExpert()">我要向专家提问</span></div>
		<div style="height:40px;line-height:40px;width:70px;"><span id="selectedExpert" style="display:none"></span><input type="hidden" id="selectedExpertId" /></div>
	</li>
	<li style="height:40px;line-height:40px;vertical-align:middle;">
		<div style="height:40px;line-height:40px;text-align: center;"><a href="javascript:void(0)" id="questionpublishfb" onclick="publish(300)"><img src="<%=rootPath%>client/images/tiwen.png" /> </a></div>
		<!-- <label><a id="addattracha" href="javascript:void(0)" onclick="tosaveAttacthedafter()"><font face="微软雅黑" color="#4b9bc0">添加附件</font></a></label> -->
	</li>
	<li style="background-color: #f1f1f1;height: 10px;"></li>
	<!-- <li class="shuruend" style="width: 100%;"></li> -->
</ul>
</div>
</div>
</div>

</body>