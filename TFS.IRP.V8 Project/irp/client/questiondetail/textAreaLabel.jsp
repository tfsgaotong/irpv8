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
<script type="text/javascript">
<!--
$(function(){
	$('#showcate').click(function(){
		$('#cate').show(666);
		$('#resetB').show(1000);
	});
});
function resetValue(){
	$('#cate').val('');
	$('#cateIds').val('');
}
//-->
</script>
<ul style="background:url(<%=rootPath%>client/images/ico04-1.gif) no-repeat transparent;">
	<li id="titleAcreaNumfont" style="text-align: right; display: block;">
		您还可以输入<label id="titleAcreaNumfontNum" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;">150</label>个字
	</li>
	<li id="titleAcreaNumChaochu" style="text-align: right; display: none;">
			超出了 <label id="titleerrorNumCount" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color:red;"></label>个字
	</li>
	<li><input style="width: 636px;" id="questiontitle" class="wtgy" type="text" onkeyup="questionTitleInfoControl(this.value,150)" /></li>
	<li id="textAreaNum" style="text-align: right; display: block;">
		您还可以输入 <label id="questionNum"
		style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;">300</label>
		个字</li>
	<li id="errorQuestionNum" style="text-align: right;display: none;">
		超出了 <label id="errorNumCount"
		style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color:red;">
	</label>个字</li>
	<li><textarea name="textarea" rows="4" id="question_info"
			onkeyup="questionInfoControl(this.value,300)"></textarea></li>
	<li style="height:40px;line-height:40px;vertical-align:middle;">
		<div style="float:left;line-height: 40px;">
			<span style="cursor:pointer;color:#4b9bc0;" id="showcate" onclick="showLeftTree()">选择类别</span> 
			<span style="margin-top:10px;"><input onclick="showLeftTree()" id="cate" type="text" readonly="readonly" value="" style="display:none;width:120px;margin-right: 5px;margin-left: 8px;"/></span>
			<span style="margin-top:10px;"><input type="button" id="resetB" title="重置" onclick="resetValue()" class="resetB" style="display:none;width:10px;height:10px;" /></span>
			<input id="cateIds" type="hidden" readonly value=""/>
		</div>
		<div style="height:40px;line-height:40px;width:100px;float:left;margin-left: 10px;"><span style="cursor:pointer;color:#4b9bc0;" onclick="selectExpert()">我要向专家提问</span></div>
		<div style="height:40px;line-height:40px;width:70px;float:left;padding-top:8px;"><span id="selectedExpert" style="display:none"></span><input type="hidden" id="selectedExpertId" /></div>
		<div style="height:40px;line-height:40px;float:right;"><a href="javascript:void(0)" id="questionpublishfb" onclick="publish(300)"><img src="<%=rootPath%>client/images/tiwen.png" /> </a></div>
		<label><a id="addattracha" href="javascript:void(0)" onclick="tosaveAttacthedafter()"><font face="微软雅黑" color="#4b9bc0">添加附件</font></a></label>
	</li>
	
	<li class="shuruend"></li>
</ul>
