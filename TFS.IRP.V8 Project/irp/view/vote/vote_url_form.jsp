<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>   
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
.inputvote{
height:30px;
width: 300px;
}
.optoninpt{
height:30px;
width: 150px;
}
input,select{
border: 1px solid #CCCCCC;
}
textarea{
height:60px;
width: 300px;
border: 1px solid #CCCCCC;
}

#firstul li,#secondul li{
padding:0px 0px 2px 2px;
}
#firstul li ul,#secondul li ul{
padding-left: 60px;
margin-top: -20px;
}
#firstul,#secondul{
margin-left: 40px;
}
</style>
</head>
<body>
<script type="text/javascript">
var htmlquestion,htmloption,htmlcheckli;
$(function(){
	htmlquestion=$("#questionvote").html();
	htmloption=$("#opteionvote").html();
	htmlcheckli=$("#checkli").html();
	
	
});
//添加说明
function addDesc(){
	var $oc=$("#adddesc a").text();
	if($.trim($oc)=="关闭说明"){
		$("#adddesc a").text("添加说明");
		$("#description").val("");
		$("#titledesc").hide();
	}else{
		$("#adddesc a").text("关闭说明");
		$("#titledesc").show();
	}
}
//再加一项
function addoption(){
	//获取li
	var index=$("#optionul li").length;
	$("#optionul").append("<li><span>"+(index+1)+"</span>.&nbsp;<input name=\"option\" type=\"text\" class=\"optoninpt\" required/>&nbsp;&nbsp;&nbsp;URL:&nbsp;&nbsp;<input name=\"urltext\" type=\"text\" class=\"optoninpt\"/></li>");
	$("#optionul li a").remove();
	$("#optionul li").append("<a onclick=\"deleteoption(this)\" > X</a>");
}
//删除选项
function deleteoption(value){
	//删除
	$(value).parent().remove();
	//判断
	var index=$("#optionul li").length;
	if(index<=2){
		$("#optionul li a").remove();
	}
	//排序
	for(var i=0;i<index;i++){
		$("#optionul li span:eq("+i+")").html(i+1);
	}
}
function checkchose(_value){
	var ischeck= $(_value).attr("checked");
	if(ischeck=="checked"){
		$(_value).attr("checked","checked");
	}else{
		$(_value).attr("checked",false);
	}
 }

//保存标题和选项
function savevote(){
	var fag=false;
	var title=$.trim($("#title").val());
	var votetitle=$.trim($("#votetitle").val());
	if(title==""||votetitle==""){
		fag=false;
		$.dialog.tips('投票标题或投票问题为空',1,'32X32/fail.png');
	}else if(title.length>100||title.length<2||votetitle.length>70||votetitle.length<2){
		fag=false;
		$.dialog.tips('投票标题或投票长度不符合',1,'32X32/fail.png');
	}else{
		var queryString = $('#votefirst').serialize();
		alert(queryString);
		var isph=$("#ispublish").attr("checked");
		if(isph=="checked"){
			$("#ispublish").val(2);
			queryString+="&irptitle.ispublish=2";
		}else{
			$("#ispublish").val(1);
			queryString+="&irptitle.ispublish=1";
		}
		$("#titlename").val(title);
		var opvalue=$("#optionul li input[name='option']");
		var jsonoption="";
		var urloption="";
		for(var i=0;i<opvalue.length;i++){
			var option=$("#optionul li:eq("+i+") input[name='option']").val();
			var optionurl=$("#optionul li:eq("+i+") input[name='urltext']").val();
			if($.trim(option)==null||$.trim(option)==""){
				$.dialog.tips('请填写选项',1,'32X32/fail.png');
				fag=false;
				break;
			}else if($.trim(option).length>100||$.trim(option).length<2){
				$.dialog.tips('选项长度符合',1,'32X32/fail.png');
				fag=false;
				break;
			}else{
				if($.trim(optionurl)==""||$.trim(optionurl)==null){
					optionurl=null;
				}
				jsonoption+=option+",";
				urloption+=optionurl+",";
				fag=true;
			}
		 }
		if(fag){
			$.ajax({
				type:"post",
				dataType: "json",
			    async: false,
			    cache: false,
				url:"<%=rootPath%>menu/save_votefirst.action?soptionstr="+jsonoption+"&urloption="+urloption+"&"+queryString,
				success:function(votedid){
					$("#voteid").val(votedid);
					$("#firstul").empty();
					$.dialog.tips('文字投票保存成功',1,'32X32/succ.png');
					mictext(votedid);
				}
			});
		}
	}
	return fag;
}
//保存选项
function savevotesecond(){
	var fag=false;
	var votetitle=$.trim($("#votetitle").val());
	if(votetitle==""){
		fag=false;
		$.dialog.tips('投票问题为空',1,'32X32/fail.png');
	}else if(votetitle.length>100||votetitle.length<2){
		fag=false;
		$.dialog.tips('投票问题长度不符合',1,'32X32/fail.png');
	}else{
		var queryString = $('#votefirst').serialize();
		var opvalue=$("#optionul li input[name='option']");
		var jsonoption="";
		var urloption="";
		for(var i=0;i<opvalue.length;i++){
			var option=$("#optionul li:eq("+i+") input[name='option']").val();
			var optionurl=$("#optionul li:eq("+i+") input[name='urltext']").val();
			if($.trim(option)==null||$.trim(option)==""){
				$.dialog.tips('请填写选项',1,'32X32/fail.png');
				fag=false;
				break;
			}else if($.trim(option).length>100||$.trim(option).length<2){
				$.dialog.tips('选项长度符合',1,'32X32/fail.png');
				fag=false;
				break;
			}else{
				if($.trim(optionurl)==""||$.trim(optionurl)==null){
					optionurl=null;
				}
				jsonoption+=option+",";
				urloption+=optionurl+",";
				fag=true;
			}
		 }
		var vid=$("#voteid").val();
		if(fag){
			$.ajax({
				type:"post",
				dataType: "json",
			    async: false,
			    cache: false,
				url:"<%=rootPath%>menu/save_votesecond.action?voteid="+vid+"&soptionstr="+jsonoption+"&urloption="+urloption+"&"+queryString,
				success:function(html){
					$("#firstul").empty();
					$.dialog.tips('文字投票分组保存成功',1,'32X32/succ.png');
				}
			});
		}
	}
	return fag;
}

//保存并添加1
function addQuestion(){
	var fag=savevote();
	if(fag){
		addHtml();
	}
	
}
//保存并添加2
function addQuestionsecond(){
	var fag=savevotesecond();
	if(fag){
		addHtml();
	}
	
}

function addHtml(){
	var htmltext="<li id=\"checkli\">"+htmlcheckli+"</li>";
	htmltext+="<li id=\"questionvote\">"+htmlquestion+"</li>";
	htmltext+="<li id=\"opteionvote\">"+htmloption+"</li>";
	htmltext+=" <li><a href=\"javascript:void(0)\" onclick=\"savevotesecond()\">保存</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:void(0)\" onclick=\"addQuestionsecond()\">保存并添加分组</a></li>";
	$("#firstul").html(htmltext);
	$(" #lesscheck").hide();
}

function mictext(votedid){
	var context="我刚发起了<a class=\"linkbh14\" target=\"_blank\" href=\"<%=rootPath%>menu/vote_detil.action?voteid="+votedid+"\" >"+$("#titlename").val()+"</a>的投票，大家快来看看吧";
	$("#sendmictext").val(context);
}
function checkoneormore(_value){
	if($(_value).val()==2){
		$("body #lesscheck").show();
	}else{
		$("body #lesscheck").hide();
	}
}
function addlastoption(){
	//获取li
	var index=$("#optionul li").length;
	$("#optionul").append("<li><span>"+(index+1)+"</span>.&nbsp;<input value=\"说不清\" name=\"option\" type=\"text\" class=\"optoninpt\" required/>&nbsp;&nbsp;&nbsp;URL:&nbsp;&nbsp;<input name=\"urltext\" type=\"text\" class=\"optoninpt\"/></li>");
	$("#optionul li a").remove();
	$("#optionul li").append("<a onclick=\"deleteoption(this)\" > X</a>");
}



</script>
<s:hidden type="text" id="titlename" />
<s:hidden type="text" id="voteid" />
<form id="votefirst">
<s:hidden name="irpvotetitle.lesscheck" value="0"/>
<s:hidden name="irpvotetitle.checktype" value="0"/>
<s:hidden name="votetype" value="3"/>

<ul style="float: left;" id="firstul"> 
	 <li><div><span style="color:red;">*</span>投票标题：
	          <ul><li><input id="title" name="irptitle.title" type="text" class="inputvote"  /></li><li id="adddesc"><a href="javascript:void(0)" onclick="addDesc()">添加说明</a></li><li id="titledesc" style="display: none;"><textarea id="description" style="font-size: 12px;" name="irptitle.description" rows="2" cols="46" ></textarea></li></ul>
	     </div>
	 </li>
	 <li><div><span style="color:red;">*</span>结果可见：<input type="radio" checked="checked" value="0" name="irptitle.resultshow"/>投票后可见&nbsp;&nbsp;&nbsp;<input type="radio" value="2" name="irptitle.resultshow"/>所有人可见
	     </div>
	 </li>
	 <li><div><span style="color:red;">*</span>投票时间：
	          <ul><li>从&nbsp;<input id="starttime" type="text" name="irptitle.starttime"  />&nbsp;到&nbsp;<input validType="EndTime" id="endtime" type="text" name="irptitle.endtime" /></li></ul>
	     </div>
	 </li>
	 <li id="questionvote">
	     <div><span style="color:red;">*</span>投票问题：
	          <ul><li><input id="votetitle" name="irpvotetitle.votetitle" type="text" class="inputvote" required/></li></ul>
	     </div>
	 </li>
	 <li id="opteionvote">
	     <div><span style="color:red;">*</span>投票选项：
	        <ul id="optionul">
	            <li><span>1</span>.&nbsp;<input name="option" type="text" class="optoninpt" required/>&nbsp;&nbsp;&nbsp;URL:&nbsp;&nbsp;<input name="urltext" type="text" class="optoninpt"/> </li>
	        </ul>  
	     </div>
	     <div>
	        <ul style="margin-top: 3px;">
	            <li id="addoptionli">
		            <span style="float:right;padding-right: 76px;color:gray;">至少设置一项，每项最多50个字 </span>
		        </li>
	        </ul>
	     </div>
	 </li>
	 <li><input type="checkbox" id="ispublish" onclick="checkchose(this)"/>立即发布 </li>
	 <li style="font-size: 20px;"><a href="javascript:void(0)" onclick="savevote()">保存</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="addQuestion()">保存并添加分组</a></li>
  </ul>
  </form>
  <ul style="float: left;" id="secondul"> 
     <li id="microblogtext" ><span style="color:red;">*</span>微知文字：<ul><li><textarea rows="1.5" cols="36" id="sendmictext"></textarea></li><li><span style="padding-right: 68px;" style="color:gray;">投票发起成功后将以此文字发送一条微知，其他用户参与投票后将转发此条微知</span></li></ul> </li>
  </ul> 
