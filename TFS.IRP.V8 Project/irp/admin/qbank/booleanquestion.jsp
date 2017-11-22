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
		<tr style="display: none;">
			<td><label>类型:</label></td>		
			<td>
				<input type="radio" name="irpQuestionBank.qbtype" onclick="choiceTtyps(this)" checked="checked" value="40">
			</td>
			<td></td>
		</tr>
		<script type="text/javascript">
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
							}
						}
					}	
		</script>
		<tr id="answereitema">
			<td><label>&nbsp;</label></td>		
			<td><div style="width:495px; background-color: #CFD1CF;">&nbsp;<input style="width: 400px;display:none;" class="easyui-validatebox"  type="text" name="irpQuestionBank.answera" data-options="required:true" value="<s:property value="irpQuestionBank.answera" />"  /><label style="cursor: pointer;font-weight: normal;">&nbsp;<input type="radio" id="istrueanswer1" name="irpQuestionBank.finalanswer" value="B">&nbsp;错误</label></div></td>
			<td id="answereitema1" style="display: none;">
				<div id="additema" style="width:16px;height:16px; background-image: url('<%=rootPath %>admin/images/icons/edit_add.png');background-repeat: no-repeat;background-position:center;float:left;cursor:pointer;" title="新增选项" onclick="addItem('answereitemb','answereitema1')">&nbsp;</div>
			</td>
		</tr>
		<tr id="answereitemb">
			<td><label>&nbsp;</label></td>		
			<td><div style="width:495px; background-color: #CFD1CF;">&nbsp;<input style="width: 400px;display: none;" class="easyui-validatebox"  type="text" name="irpQuestionBank.answerb" data-options="required:true" value="<s:property value="irpQuestionBank.answerb" />" /><label style="cursor: pointer;font-weight: normal;">&nbsp;<input type="radio" id="istrueanswer2" name="irpQuestionBank.finalanswer" value="A">&nbsp;正确</label></div></td>
			<td id="answereitemb1" style="display: none;">
				<div style="width:16px;height:16px; background-image: url('<%=rootPath %>admin/images/icons/cancel.png');background-repeat: no-repeat;background-position:center;float:left;cursor:pointer;" title="删除" onclick="deleteThisItem('answereitemb','answereitema1')">&nbsp;</div>
				<div id="additemb" style="width:16px;height:16px; background-image: url('<%=rootPath %>admin/images/icons/edit_add.png');background-repeat: no-repeat;background-position:center;float:left;margin-left:10px;cursor:pointer;" title="新增选项" onclick="addItem('answereitemc','answereitemb1')">&nbsp;</div>
			</td>
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
			<td><input class="easyui-numberspinner"  type="text" name="irpQuestionBank.qbscore" <s:if test="irpQuestionBank.qbscore>0">value="<s:property value="irpQuestionBank.qbscore" />"</s:if><s:else>value="2"</s:else> /></td>
			<td></td>
		</tr>

	</table>
	</form>
</div>


