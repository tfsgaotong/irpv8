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
			<td><label>注意:填空题空项用[ ]代替,如果有多个值需用  | 中间隔开,例如输入“今年<br/>是[2014|马]年”，回答“2014”或者“马”都算答对。</label>
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
				<input type="radio" name="irpQuestionBank.qbtype" onclick="choiceTtyps(this)" checked="checked" value="30">
			</td>
			<td></td>
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


