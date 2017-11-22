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
.clearfix:before{}
.clearfix:after {       
    content: ".";
    display: block;
    clear: both; 
    visibility: hidden;
    line-height: 0;
    height: 0;
    font-size:0;
}
</style>
<script type="text/javascript">
var checkstr = "";
var m_checked = false;
/**
 * 全选
 */
function checkAll(){
	m_checked = !m_checked;
	$("input[name='qbankids']").attr("checked",m_checked); 
	$("input[name='qbankids']").each(function(){
	var booldis = 0;
		if(m_checked==true){
				var chstrarray = checkstr.split(",");
				for(var i in chstrarray){
					if(this.value==chstrarray[i]){
						booldis = 1;
					}
				
				}
				if(booldis==0){
					checkstr +=this.value+",";
				}
				
		}else{
			var chstrarray = checkstr.split(",");
			var disstr = "";
			for(var i in chstrarray){
				if(chstrarray[i]!=''){
					if(chstrarray[i]!=this.value){
						disstr += chstrarray[i]+",";
					
					}
				}
			}
			checkstr = disstr;
		
		}
		$("#testpapercontents").val(checkstr);
	});
	
} 
		//list单个选中
		function checkBC(_this){
			if(_this.checked==true){
				checkstr +=_this.value+",";
			}else{
				if(checkstr!=""){
					var chstrarray = checkstr.split(",");
					checkstr ="";
					for(var i in chstrarray){
						if(chstrarray[i]!=""){
							if(_this.value==chstrarray[i]){
								//移除
							}else{
								checkstr +=chstrarray[i]+",";
							}
							
						}
					}
				}
				
			}
			$("#testpapercontents").val(checkstr);
		}
/**
* 引用试题
*/
function quoteqbank(){
	var dialogdiv=document.createElement("div");
 	dialogdiv.id="quoteaddqbank";
 	document.body.appendChild(dialogdiv);
 	$('#quoteaddqbank').dialog({
 		modal:true,
 		cache:false,
 		href:'<%=rootPath%>exam/quoteqbank.action',
 		height:500,
 		width:800,
 		title:'引用试题',
 		resizable:true,
 		buttons:[{
 			text:"提交",
 			iconCls: 'icon-ok',
 			handler:function(){
 				var checkstrarray = checkstr.split(",");
 				var checkstrarrayone = "";
 				for(var i in checkstrarray){
 					if(checkstrarray[i]!=""){
	 					var cta = 0;
	 					if(checkstrarrayone!=""){
	 					var checkstrarrayones = checkstrarrayone.split(",");
		 					for(var j in checkstrarrayones){
		 						if(checkstrarray[i]==checkstrarrayones[j]){
		 							cta = 1;
		 						}
		 					}
	 					}

	 					if(cta==0){
	 						checkstrarrayone+=checkstrarray[i]+",";
	 					}
 					}
 				}
 				checkstr = checkstrarrayone.toString();
 				$("#contentdiv").html("");
	    		$("#testpapercontents").val(checkstr);
	    	if(checkstr!=""){
	    	var zongscore = 0;
	    		$.ajax({
	    			type:'post',
	    			url:'<%=rootPath%>exam/findqbankbyids.action',
	    			data:{
	    				checkstrids:checkstr
	    			},
	    			cache:false,
	    			async:false,
	    			success:function(content){
			    			var jsonobj=eval("("+content+")");
							for(var i in jsonobj){
								var typsnames = "";
								var types = parseInt($.trim(jsonobj[i].type));
								switch(types){
									case 10:
										typsnames = "多选题";
										break;
									case 20:
										typsnames = "单选题";
										break;
									case 30:
										typsnames = "填空题";
										break;
									case 40:
										typsnames = "判断题";
										break;
								}
								$("#contentdiv").append("<li>"
								+"<div style=\" padding:2px 0 2px 0;border-bottom:thin solid #BEBEBE;float:left;width:20px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;\">"+jsonobj[i].score+"分</div>"
								+"<div style=\" padding:2px 0 2px 0;border-bottom:thin solid #BEBEBE;float:left;margin-left:20px;width:40px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;\">"+typsnames+"</div>"
								+"<div style=\" padding:2px 0 2px 0;border-bottom:thin solid #BEBEBE;float:left;margin-left:20px;width:400px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;\" title=\""+jsonobj[i].text+"\" >"+jsonobj[i].text+"</div>"
								+"</li></br>");
								zongscore += parseInt(jsonobj[i].score);
							} 
	    			$("#papertimescores").val(zongscore);
	    			}
	    		
	    		});
				
 			}
 			$('#quoteaddqbank').dialog('destroy');
 			}
 		},{
 			text:"取消",
 			iconCls: 'icon-cancel',
 			handler:function(){
 				$('#quoteaddqbank').dialog('destroy');
 			}
 		
 		}
 		],
 		onClose:function(){
 			$('#quoteaddqbank').dialog('destroy');
 		}
 	
	});

}
/**
选择组卷方式
*/
function tPselStyle(_this){
	if(_this.value==10){
		$("#zujuanstyle").css("display","none");
		$("#btn").css("display","inline-block");
	}else if(_this.value==20){
		$("#zujuanstyle").css("display","block");
		$("#btn").css("display","none");
		
	}
}
/**
* 随机组卷
*/
function randomSure(){
	$("#contentdiv").html("");
	var zongscore = 0;
	var choicenum = $("#choiceques").val();
	var multiselectnum = $("#multiselectques").val();
	var boolquesnum = $("#boolques").val();
	var fullquesnum = $("#fullques").val();
	var checkstrarray = $("#testpapercontents").val();
	$.ajax({
		type:'post',
		url:'<%=rootPath%>exam/randomtest.action',
		data:{
			multiselectnum:multiselectnum,
			choicenum:choicenum,
			boolquesnum:boolquesnum,
			fullquesnum:fullquesnum
		},
		cache:false,
		async:false,
		success:function(content){
			zongscore = 0;
			var jsonobj=eval(content);
			checkstrarray = "";
			for(var i in jsonobj){
				var typsnames = "";
				var types = parseInt($.trim(jsonobj[i].type));
				switch(types){
					case 10:
						typsnames = "多选题";
						break;
					case 20:
						typsnames = "单选题";
						break;
					case 30:
						typsnames = "填空题";
						break;
					case 40:
						typsnames = "判断题";
						break;
				}
				$("#contentdiv").append("<li>"
				+"<div style=\" padding:2px 0 2px 0;border-bottom:thin solid #BEBEBE;float:left;width:20px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;\">"+jsonobj[i].score+"分</div>"
				+"<div style=\" padding:2px 0 2px 0;border-bottom:thin solid #BEBEBE;float:left;margin-left:20px;width:40px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;\">"+typsnames+"</div>"
				+"<div style=\" padding:2px 0 2px 0;border-bottom:thin solid #BEBEBE;float:left;margin-left:20px;width:400px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;\" title=\""+jsonobj[i].text+"\" >"+jsonobj[i].text+"</div>"
				+"</li></br>");
				zongscore += parseInt(jsonobj[i].score);
				checkstrarray+=jsonobj[i].id+",";
			} 
				$("#papertimescores").val(zongscore);
				checkstr = checkstrarray.toString();
				console.log(checkstr);
	    		$("#testpapercontents").val(checkstr);
				
		},
		error:function(e){
			$.messager.alert("操作提示","失败了");
		}
	
	});
}

</script>

<div style="padding: 10px 10px 10px 10px;">
	<form id="addtestpapers" method="post">
	
	<input style="display: none;"  value="<s:property value="cateid" />" name="irpTestPaper.papercate">
	<input style="display: none;" name="irpTestPaper.paperid" value="<s:property value="irpTestPaper.paperid" />"  />
	
	<table class="cqt">
		<tr>
			<td><label>标题:</label></td>		
			
			<td><div><input style="width: 400px;" class="easyui-validatebox"  type="text" name="irpTestPaper.papertitle" value="<s:property value="irpTestPaper.papertitle" />" data-options="required:true"  /></div></td>
			<td style="display: none;">
				
			</td>
		</tr>
		<script type="text/javascript">
			var papertp = '<s:property value="irpTestPaper.extendsone"/>';
			if(papertp==20){
				//随机组卷
				$("#zujuanstyle").css("display","block");
			}
			$('#choiceques').numberbox({
			    min:0,
			    max:50
			}); 
			$('#boolques').numberbox({
			    min:0,
			    max:50
			}); 
			$('#fullques').numberbox({
			    min:0,
			    max:50
			}); 
		</script>	
		<tr class="">
			<td><label>组卷方式:</label></td>		
			<td class="clearfix">
				<div class="" style="">
					<div style="position:relative;float:left">
					<select name="irpTestPaper.extendsone" onchange="tPselStyle(this)">
						<option value="10" <s:if test="irpTestPaper.extendsone==10">selected="selected"</s:if>>选题组卷</option>
						<option value="20" <s:if test="irpTestPaper.extendsone==20">selected="selected"</s:if>>随机组卷</option>
					</select>
					</div>
					<div id="zujuanstyle" style="display: none;position:realtive;float:left;margin-left:8px;">
					单选题&nbsp;:&nbsp;<input id="choiceques" value="0"  style="width: 30px;"> 
					多选题&nbsp;:&nbsp;<input id="multiselectques" value="0"  style="width: 30px;"> 
					判断题&nbsp;:&nbsp;<input id="boolques" value="0"  style="width: 30px;"> 
					填空题&nbsp;:&nbsp;<input id="fullques" value="0"  style="width: 30px;">
						<label>
						<a href="javascript:void(0)" onclick="randomSure()" >确定</a>
						</label>
					
					</div>
				</div>
			</td>
			<td  style="display: none;">

			</td>
		</tr>	

		<tr>
			<td><label>状态:</label></td>		
			<td>
				<div>
					<select name="irpTestPaper.paperstatus">
						<option value="20" <s:if test="irpTestPaper.paperstatus==20">selected="selected"</s:if>>发布</option>
						<option value="10" <s:if test="irpTestPaper.paperstatus==10">selected="selected"</s:if>>未发布</option>
					</select>
					
				</div>
			</td>
			<td style="display: none;">
				
			</td>
		</tr>		
		<tr>
			<td><label>内容:</label></td>		
			<td>
				<div>
					<s:if test="irpTestPaper.extendsone==10">
						<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="quoteqbank()">引用试题</a>
					</s:if>
					<s:else>
						<a id="btn" href="#" style="display:none;" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="quoteqbank()">引用试题</a>
					</s:else>
					<input id="testpapercontents" style="display: none;" name="irpTestPaper.papercontent" value="<s:property value="irpTestPaper.papercontent" />">
					
					<div id="contentdiv"> 
						<s:iterator value="qbankbids" var="qbankbidsid" status="qbankbidsstatus">
								<li>
								<div style="padding:2px 0 2px 0; border-bottom:thin solid #BEBEBE;float:left;width:20px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;"><s:property value="getIrpQuestionBankByIdStr(#qbankbidsid).qbscore"  />分</div>
								<div style="padding:2px 0 2px 0; border-bottom:thin solid #BEBEBE;float:left;margin-left:20px;width:40px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">
									<s:set  var="status" value="getIrpQuestionBankByIdStr(#qbankbidsid).qbtype"></s:set>
									<s:if test="#status==@com.tfs.irp.questionbank.entity.IrpQuestionBank@CHOICEMANYTYPE">
									多选题
									</s:if>
									<s:elseif test="#status==@com.tfs.irp.questionbank.entity.IrpQuestionBank@CHOICESINGLETYPE">
									单选题
									</s:elseif>
									<s:elseif test="#status==@com.tfs.irp.questionbank.entity.IrpQuestionBank@FILLINGTYPE">
									填空题
									</s:elseif>
									<s:elseif test="#status==@com.tfs.irp.questionbank.entity.IrpQuestionBank@BOOLEANTYPE">
									判断题
									</s:elseif>
								</div>
								<div style=" padding:2px 0 2px 0;border-bottom:thin solid #BEBEBE;float:left;margin-left:20px;width:400px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<s:property value="getIrpQuestionBankByIdStr(#qbankbidsid).questiontext" escapeHtml="false" />" ><s:property value="getIrpQuestionBankByIdStr(#qbankbidsid).questiontext" escapeHtml="false" /></div>
								</li></br>
						</s:iterator>
					</div>
				</div>

			</td>
			<td style="display: none;">
				
			</td>
		</tr>
		<tr>
			<td><label>总分:</label></td>		
			<td>
			
				<div><input  id="papertimescores"  name="irpTestPaper.papertime"  <s:if test="irpTestPaper.papertime>0">value="<s:property value="irpTestPaper.papertime" />"</s:if> style="width: 50px;" readonly="readonly" />  
				</div>
			</td>
			<td style="display: none;">
				
			</td>
		</tr>	
		<tr>
			<td><label>描述:</label></td>		
			<td><div><input value="<s:property value="irpTestPaper.paperdesc" />" style="width: 400px;" class="easyui-validatebox"  type="text" name="irpTestPaper.paperdesc" data-options="required:true" /></div></td>
			
			<td style="display: none;">
				
			</td>
		</tr>	


	</table>
	</form>
</div>


