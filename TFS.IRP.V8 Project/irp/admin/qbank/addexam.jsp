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
 	$('#jigescore').numberbox({   
      	min:1,
      	max:9999 
  	});  
    $('#startetime').datetimebox({   
        showSeconds:false  
    });  
  	$('#endetime').datetimebox({   
        showSeconds:false  
    }); 
 	$('#dajuantime').numberbox({   
      	min:1,
      	max:9999 
  	}); 
 	$('#startstopvisittime').numberbox({   
      	min:0,
      	max:9999 
  	}); 
 	$('#startstopleavetime').numberbox({   
      	min:0,
      	max:9999 
  	}); 
  	
  	$("#resultputlictwo").click(function(){
  		if(this.checked==true){
  			$("#resultputlicone").val(10);
  		}else{
  			$("#resultputlicone").val(20);
  		}
  	});
  	
});
/**
* 请选择试卷
*/
function quoteTestPaper(){
	var dialogdiv=document.createElement("div");
 	dialogdiv.id="selecttestpaper";
 	document.body.appendChild(dialogdiv);
 	$('#selecttestpaper').dialog({
 		modal:true,
 		cache:false,
 		href:'<%=rootPath%>exam/selecttestpaper.action',
 		height:500,
 		width:800,
 		title:'选择试卷',
 		resizable:true,
 		buttons:[{
 			text:"提交",
 			iconCls: 'icon-ok',
 			handler:function(){
 				var selcheckval,selcheckvalname,selscore;
				selcheckval = $("input[name='selcheckpid']:checked").val();
				selcheckvalname = $("input[name='selcheckpid']:checked").attr("_vals");
				selscore = $("input[name='selcheckpid']:checked").attr("_score");
				if(selcheckval!=undefined){
					$("#selcontentvalues").val(selcheckval);
					$("#selcontentnames").html("<div style=\"width:150px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;\" title="+selcheckvalname+">总分&nbsp;:&nbsp;"+selscore+"分&nbsp;&nbsp;试卷名称&nbsp;:&nbsp;"+selcheckvalname+"</div>");
					$('#selecttestpaper').dialog('destroy');
				}
 			
				
 			}
 		},{
 			text:"取消",
 			iconCls: 'icon-cancel',
 			handler:function(){
 				$('#selecttestpaper').dialog('destroy');
 			}
 		}
 		],
 		onClose:function(){
 			$('#selecttestpaper').dialog('destroy');
 		}
	});
}

$("#jigescore").blur(function(){
	var allscore = '<s:property value="getTPObjById(irpexam.examcontent).papertime" />';
	var score = $(this).val();
	if(parseInt(allscore)<parseInt(score)){
		$.messager.alert('提示信息','及格分不能大于总分','warning');
		$(this).val("");
	}
})

</script>

<div style="padding: 10px 10px 10px 10px;">
	<form id="addexamform" method="post">
	
	<input style="display: none;"  value="<s:property value="cateid" />" name="irpexam.examcate">
	<input style="display: none;"  value="<s:property value="irpexam.examid" />" name="irpexam.examid">
	
	<table class="cqt">
		<tr>
			<td><label>考试名称:</label></td>		
			
			<td><div><input style="width: 300px;" class="easyui-validatebox"  type="text" name="irpexam.examname" value="<s:property value="irpexam.examname" />" data-options="required:true"  /></div></td>
			<td style="display: none;">
				
			</td>
		</tr>	
		<tr>
			<td><label>内容:</label></td>		
			<td>
					<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="quoteTestPaper()">请选择试卷</a>
					<input id="selcontentvalues" style="display: none;" name="irpexam.examcontent" value="<s:property value="irpexam.examcontent" />" >
			</td>
			<td id="selcontentnames" style="display: block;">
				<s:if test="irpexam.examcontent.length()>0">
					<div style="width:150px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<s:property value="getTPObjById(irpexam.examcontent).papertitle" />">
					总分&nbsp;:&nbsp;<s:property value="getTPObjById(irpexam.examcontent).papertime" /> &nbsp;&nbsp;试卷名称&nbsp;:&nbsp;<s:property value="getTPObjById(irpexam.examcontent).papertitle" />
					</div>
				
				</s:if>
				
			</td>
		</tr>	
		<tr>
			<td><label>考试状态:</label></td>		
			<td>
				
				<select name="irpexam.examstatus">
					<option value="10" <s:if test="irpexam.examstatus==10">selected="selected"</s:if>>正常</option>
					<option value="20" <s:if test="irpexam.examstatus==20">selected="selected"</s:if>>禁用</option>
				</select>
			</td>
			<td style="display: none;">
				
			</td>
		</tr>	
		<tr>
			<td><label>及格分数:</label></td>		
			<td>
				<div>
				 	
					<input name="irpexam.qualifiedscore" id="jigescore"  style="width:80px;" value="<s:property value="irpexam.qualifiedscore" />">分
				</div>
			</td>
			<td style="display: none;">
				
			</td>
		</tr>	
		<tr>
			<td><label>考试时间:</label></td>		
			<td>
				<div>  
				
				 <input name="begintimestr" value="<s:date name="irpexam.begintime" format="yyyy-MM-dd HH:mm" />" id="startetime" type="text"></input>&nbsp;到&nbsp;<input name="endtimestr" value="<s:date name="irpexam.endtime" format="yyyy-MM-dd HH:mm" />" id="endetime" type="text"></input>  
				</div>
			</td>
			<td style="display: none;">
				
			</td>
		</tr>
		<tr>
			<td><label>答卷时长:</label></td>		
			<td>
				<div>
				<input name="irpexam.answertime" value="<s:property value="irpexam.answertime" />"  id="dajuantime"  style="width:80px;">分钟  
				
				</div>
			</td>
			<td style="display: none;">
				
			</td>
		</tr>	
		
		<tr>
			<td colspan="2">
				<label>
				开始考试&nbsp;<input name="irpexam.startv" value="<s:property value="irpexam.startv" />" id="startstopvisittime"  style="width:50px;">&nbsp;分钟后禁止考生参加 
				</label>
			</td>		
			
			<td style="display: none;">
				
			</td>
		</tr>
		
		<tr>
			<td colspan="2">
				<label> 
				开始考试&nbsp;<input name="irpexam.endv" value="<s:property value="irpexam.endv" />" id="startstopleavetime"   style="width:50px;">&nbsp;分钟内禁止考生交卷 
				</label>
			</td>		
			
			<td style="display: none;">
				
			</td>
		</tr>
		<tr>
			<td><label>成绩发布:</label></td>		
			<td>
				<div>  
				
				
				<input id="resultputlicone" style="display: none;" name="irpexam.resultputlic"  <s:if test="irpexam.resultputlic!=10">value="20"</s:if><s:else>value="<s:property value="irpexam.resultputlic" />"</s:else>>
				<input id="resultputlictwo" type="checkbox" <s:if test="irpexam.resultputlic==10">checked="checked"</s:if> >允许考生交卷后立即显示得分及答案 
				</div>
			</td>
			<td style="display: none;">
				
			</td>
		</tr>
		
	</table>
	</form>
</div>


