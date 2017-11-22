<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<style type="text/css">
.cqt td{
	padding:5px 0 5px 0;
}
label{
	font-weight: bold;
}

</style>
<script type="text/javascript">
$(function(){

});
/**
* 选择类型
*/
function choiceTtyps(_this){
	if(_this.value==20){
		//单选
		$("input[name='irpQuestionBank.finalanswer']").each(function(){
			this.type = "radio";
		});
		
	}else{
		$("input[name='irpQuestionBank.finalanswer']").each(function(){
			this.type = "checkbox";
		});
	}
}

/**
* 新增选项
*/
function addItem(_view,_this){
	$('#'+_this).hide();
	$('#'+_view).show();
}
/**
* 删除选项
*/
function deleteThisItem(_none,_view){
	var fenjie = _none.split("");
	var zh = "";
	for(var i in fenjie){
		zh = fenjie[i];
	}
	var trimanswer = "irpQuestionBank.answer"+zh;
	$("input[name='"+trimanswer+"']").val("");
	$("input[name='irpQuestionBank.finalanswer']").each(function(){
		if($.trim(zh).toUpperCase()==this.value){
			this.checked=false;
		}
	});
	$('#'+_none).hide();
	$('#'+_view).show();
}
</script>

<div style="padding: 10px 10px 10px 10px;">
	<form id="insertchoiceform" method="post">
	<input style="display: none;" value="<s:property value="irpQuestionBank.qbankid" />" name="irpQuestionBank.qbankid" >	
	<table class="cqt">
		<tr>
			<td><label>题干:</label></td>		
			<td>
				<textarea  name="irpQuestionBank.questiontext"><s:property value="irpQuestionBank.questiontext" /></textarea>
				<script type="text/javascript">
				CKEDITOR.replace('irpQuestionBank.questiontext');
				</script>
			</td>
			<td></td>
		</tr>
		<tr>
			<td><label>类型:</label></td>		
			<td>
				<script type="text/javascript">
					var qtypes = '<s:property value="irpQuestionBank.qbtype" />';if(qtypes==10){$("#tentyps").click()}else{$("#twentytyps").click()}
					var qfinalanswers = '<s:property value="irpQuestionBank.finalanswer" />';
					if(qfinalanswers!=''){
						var qanarray = qfinalanswers.split(",");
						for(var i in qanarray){
							switch($.trim(qanarray[i])){
								case 'A':
									$("#istrueanswer1").attr("checked","true");
									break;
								case 'B':
									$("#istrueanswer2").attr("checked","true");
									break;
								case 'C':
									$("#istrueanswer3").attr("checked","true");
									break;
								case 'D':
									$("#istrueanswer4").attr("checked","true");
									break;
								case 'E':
									$("#additemd").click();
									$("#istrueanswer5").attr("checked","true");
									break;
							}
						}
					}
					
				</script>
				<input id="twentytyps" type="radio" name="irpQuestionBank.qbtype" onclick="choiceTtyps(this)" checked="checked" value="20">单选
				<input id="tentyps"  type="radio" name="irpQuestionBank.qbtype" onclick="choiceTtyps(this)" value="10">多选
			</td>
			<td></td>
		</tr>
		<tr id="answereitema">
			<td><label>选项A:</label></td>		
			<td><div style="width:495px; background-color: #CFD1CF;"><input value="<s:property value="irpQuestionBank.answera" />" style="width: 400px;" class="easyui-validatebox"  type="text" name="irpQuestionBank.answera" data-options="required:true" /><label style="cursor: pointer;font-weight: normal;" >&nbsp;<input type="radio" id="istrueanswer1" name="irpQuestionBank.finalanswer" value="A">&nbsp;正确答案</label></div></td>
			<td id="answereitema1" style="display: none;">
				<div id="additema" style="width:16px;height:16px; background-image: url('<%=rootPath %>admin/images/icons/edit_add.png');background-repeat: no-repeat;background-position:center;float:left;cursor:pointer;" title="新增选项" onclick="addItem('answereitemb','answereitema1')">&nbsp;</div>
			</td>
		</tr>
		<tr id="answereitemb">
			<td><label>选项B:</label></td>		
			<td><div style="width:495px; background-color: #CFD1CF;"><input value="<s:property value="irpQuestionBank.answerb" />"  style="width: 400px;" class="easyui-validatebox"  type="text" name="irpQuestionBank.answerb" data-options="required:true" /><label style="cursor: pointer;font-weight: normal;">&nbsp;<input type="radio" id="istrueanswer2" name="irpQuestionBank.finalanswer" value="B">&nbsp;正确答案</label></div></td>
			<td id="answereitemb1" style="display: none;">
				<div style="width:16px;height:16px; background-image: url('<%=rootPath %>admin/images/icons/cancel.png');background-repeat: no-repeat;background-position:center;float:left;cursor:pointer;" title="删除" onclick="deleteThisItem('answereitemb','answereitema1')">&nbsp;</div>
				<div id="additemb" style="width:16px;height:16px; background-image: url('<%=rootPath %>admin/images/icons/edit_add.png');background-repeat: no-repeat;background-position:center;float:left;margin-left:10px;cursor:pointer;" title="新增选项" onclick="addItem('answereitemc','answereitemb1')">&nbsp;</div>
			</td>
		</tr>
		<tr id="answereitemc">
			<td><label>选项C:</label></td>		
			<td><div style="width:495px; background-color: #CFD1CF;"><input  value="<s:property value="irpQuestionBank.answerc" />"  style="width: 400px;" class="easyui-validatebox"  type="text" name="irpQuestionBank.answerc" data-options="required:true" /><label style="cursor: pointer;font-weight: normal;">&nbsp;<input type="radio" id="istrueanswer3" name="irpQuestionBank.finalanswer" value="C">&nbsp;正确答案</label></div></td>
			<td id="answereitemc1" style="display: none;">
				<div style="width:16px;height:16px; background-image: url('<%=rootPath %>admin/images/icons/cancel.png');background-repeat: no-repeat;background-position:center;float:left;cursor:pointer;" title="删除" onclick="deleteThisItem('answereitemc','answereitemb1')">&nbsp;</div>
				<div id="additemc" style="width:16px;height:16px; background-image: url('<%=rootPath %>admin/images/icons/edit_add.png');background-repeat: no-repeat;background-position:center;float:left;margin-left:10px;cursor:pointer;" title="新增选项" onclick="addItem('answereitemd','answereitemc1')">&nbsp;</div>
			</td>
		</tr>
		<tr id="answereitemd">
			<td><label>选项D:</label></td>		
			<td><div style="width:495px; background-color: #CFD1CF;"><input  value="<s:property value="irpQuestionBank.answerd" />"  style="width: 400px;" class="easyui-validatebox"  type="text" name="irpQuestionBank.answerd" data-options="required:true" /><label style="cursor: pointer;font-weight: normal;">&nbsp;<input type="radio" id="istrueanswer4" name="irpQuestionBank.finalanswer" value="D">&nbsp;正确答案</label></div></td>
			<td id="answereitemd1">
				<div style="width:16px;height:16px; background-image: url('<%=rootPath %>admin/images/icons/cancel.png');background-repeat: no-repeat;background-position:center;float:left;cursor:pointer;" title="删除" onclick="deleteThisItem('answereitemd','answereitemc1')">&nbsp;</div>
				<div id="additemd" style="width:16px;height:16px; background-image: url('<%=rootPath %>admin/images/icons/edit_add.png');background-repeat: no-repeat;background-position:center;float:left;margin-left:10px;cursor:pointer;" title="新增选项" onclick="addItem('answereiteme','answereitemd1')">&nbsp;</div>
			</td>
		</tr>
		<tr style="display: none;" id="answereiteme">
			<td><label>选项E:</label></td>		
			<td><div style="width:495px; background-color: #CFD1CF;"><input  value="<s:property value="irpQuestionBank.answere" />"  style="width: 400px;" class="easyui-validatebox"  type="text" name="irpQuestionBank.answere" data-options="required:true" /><label style="cursor: pointer;font-weight: normal;">&nbsp;<input type="radio" id="istrueanswer5" name="irpQuestionBank.finalanswer" value="E">&nbsp;正确答案</label></div></td>
			<td id="answereiteme1"><div style="width:16px;height:16px; background-image: url('<%=rootPath %>admin/images/icons/cancel.png');background-repeat: no-repeat;background-position:center;cursor:pointer;" title="删除" onclick="deleteThisItem('answereiteme','answereitemd1')">&nbsp;</div></td>
		</tr>
		<tr>
			<td><label>解析:</label></td>		
			<td>
				<textarea  name="irpQuestionBank.qexplain"><s:property value="irpQuestionBank.qexplain" /></textarea>
				<script type="text/javascript">
				CKEDITOR.replace('irpQuestionBank.qexplain');
				</script>
			</td>
			<td></td>
		</tr>
		<tr>
			<td><label>级别:</label></td>		
			<td>
				<script type="text/javascript">
					var qblealve = '<s:property value="irpQuestionBank.qblevel" />';
					if(qblealve!=''){
					$("input[name='irpQuestionBank.qblevel']").each(function(){
						if($.trim(qblealve)==this.value){
							this.checked=true;
						}
					});
					}

				</script>
				<input type="radio" name="irpQuestionBank.qblevel" value="10" >简单
				<input type="radio" name="irpQuestionBank.qblevel" checked="checked" value="20" >一般
				<input type="radio" name="irpQuestionBank.qblevel" value="30" >困难
			</td>
			<td></td>
		</tr>
		<tr>
			<td><label>分值:</label></td>		
			<td><input class="easyui-numberspinner"  type="text" name="irpQuestionBank.qbscore" <s:if test="irpQuestionBank.qbscore>0">value="<s:property value="irpQuestionBank.qbscore" />"</s:if><s:else>value="2"</s:else>  /></td>
			<td></td>
		</tr>

	</table>
	</form>
</div>


