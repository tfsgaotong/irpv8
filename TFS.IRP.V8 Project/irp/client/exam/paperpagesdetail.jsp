<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.tfs.irp.util.LoginUtil,com.tfs.irp.user.entity.IrpUser"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="<%=rootPath%>/client/css/oacss.css" rel="stylesheet"type="text/css" />
<link href="<%=rootPath%>/client/css/easyui.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath%>/client/css/icon.css" rel="stylesheet" type="text/css"  />
<link rel="stylesheet" href="<%=rootPath%>client/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css" />
<jsp:include page="../include/client_skin.jsp" />
<style type="text/css">

</style>
<script type="text/javascript" src="<%=rootPath%>/client/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/client/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript">
function disposeContent(){
	var papercontent =  '<s:property value="irpTestpaper.papercontent" />';
	

}
var viewleftnum = 1;
var viewrightnum = 1;
$(function(){

	
	//左侧标号
	$("strong[name='leftdaohangmenu']").each(function(){
		$("#"+this.id).html(viewleftnum++);
	});
	//右侧题号
	$("div[name='rightdaohangmenu']").each(function(){
		$("#"+this.id).html(viewrightnum+++"&nbsp;:&nbsp;");
	});
	
});
//左侧链接指定锚点
function linkPageQues(_qid){
	window.location.href="#"+_qid;

}

</script>
<style type="text/css">
.explainandanswerdiv{
	background-color: #BBFFBB;

}

</style>
<title></title>
</head>
<body>
<div class="main" style="background-image: url('');">
	<div style="min-height: 500px;margin-top: 50px;">
		<div  style="background-color: #F5FFE8;padding: 3px 5px 3px 5px;font-size: 15px;border: thin solid;">
			考试名称&nbsp;:&nbsp;<s:property value="irpExam.examname" />
			
			<label id="shengyutimes" style="margin-left: 350px;" >
			总分&nbsp;:&nbsp;<s:property value="irpTestpaper.papertime" />&nbsp;&nbsp;
			考试成绩&nbsp;:&nbsp;<s:property value="irpExamRecord.examgrade" />&nbsp;&nbsp;考试状态&nbsp;:&nbsp;<s:if test="irpExamRecord.examstatus==10">通过</s:if><s:elseif test="irpExamRecord.examstatus==20">未通过</s:elseif>
			
			</label>
		</div>
		<div style="margin-top:10px;">
			<div style="width:250px;float: left;padding: 5px 5px 5px 5px;">
			
				<div>
					<dl>
						<dt>一&nbsp;:&nbsp;单选</dt>
						<dd style="line-height: 40px;">
							<s:iterator value="qbankbids" var="qbankbidsid" status="qbankbidsstatus">
								<s:if test="getIrpQuestionBankByIdStr(#qbankbidsid).qbtype==20">
								<strong id="leftdaohangmenu<s:property value="#qbankbidsstatus.index" />" onclick="linkPageQues(<s:property value="#qbankbidsid" />)"  name="leftdaohangmenu" style="border:thin solid;padding:3px 8px 3px 8px;margin-left:15px;cursor:pointer;">1</strong>	
								</s:if>
							</s:iterator>
						</dd>	
					</dl>
				</div>
				<div>
					<dl>
						<dt>二&nbsp;:&nbsp;多选</dt>
						<dd style="line-height: 40px;">
							<s:iterator value="qbankbids" var="qbankbidsid" status="qbankbidsstatus">
								<s:if test="getIrpQuestionBankByIdStr(#qbankbidsid).qbtype==10">
								<strong id="leftdaohangmenu<s:property value="#qbankbidsstatus.index" />" onclick="linkPageQues(<s:property value="#qbankbidsid" />)"  name="leftdaohangmenu" style="border:thin solid;padding:3px 8px 3px 8px;margin-left:15px;cursor:pointer;">1</strong>	
								</s:if>
							</s:iterator>
						</dd>	
					</dl>
				</div>
				<div>
					<dl>
						<dt>三&nbsp;:&nbsp;判断</dt>
						<dd style="line-height: 40px;">
							<s:iterator value="qbankbids" var="qbankbidsid" status="qbankbidsstatus">
								<s:if test="getIrpQuestionBankByIdStr(#qbankbidsid).qbtype==40">
								<strong id="leftdaohangmenu<s:property value="#qbankbidsstatus.index" />" onclick="linkPageQues(<s:property value="#qbankbidsid" />)"  name="leftdaohangmenu" style="border:thin solid;padding:3px 8px 3px 8px;margin-left:15px;cursor:pointer;">1</strong>	
								</s:if>
							</s:iterator>
						</dd>	
					</dl>
				</div>
				<div>
					<dl>
						<dt>四&nbsp;:&nbsp;填空</dt>
						<dd style="line-height: 40px;">
							<s:iterator value="qbankbids" var="qbankbidsid" status="qbankbidsstatus">
								<s:if test="getIrpQuestionBankByIdStr(#qbankbidsid).qbtype==30">
								<strong id="leftdaohangmenu<s:property value="#qbankbidsstatus.index" />" onclick="linkPageQues(<s:property value="#qbankbidsid" />)"  name="leftdaohangmenu" style="border:thin solid;padding:3px 8px 3px 8px;margin-left:15px;cursor:pointer;">1</strong>	
								</s:if>
							</s:iterator>
						</dd>	
					</dl>
				</div>
			</div>
			<div  style="width:678px;float: left;padding: 5px 5px 5px 5px;border: thin solid;">
				
				<!-- single -->
				<div style="font-size: 14px;">一&nbsp;:&nbsp;单选题</div>
				<s:iterator value="qbankbids" var="qbankbidsid" status="qbankbidsstatus" >
					<s:if test="getIrpQuestionBankByIdStr(#qbankbidsid).qbtype==20">
					<div style="border-top: thin solid;padding: 10px 0 0 0;" name="singlequestionps" id="<s:property value="#qbankbidsid" />"  >
						<div>
							
							<div id="rightdaohangmenu<s:property value="#qbankbidsstatus.index" />" name="rightdaohangmenu" style="float: left;"></div>
							<div id="titlebooliv<s:property value="#qbankbidsid" />" style="float: left;"><s:property value="getIrpQuestionBankByIdStr(#qbankbidsid).questiontext" escapeHtml="false" /></div>
						</div>
						<br/>
						
						<div>
							<s:set  var="answera" value="getIrpQuestionBankByIdStr(#qbankbidsid).answera"></s:set>
							<s:set  var="answerb" value="getIrpQuestionBankByIdStr(#qbankbidsid).answerb"></s:set>
							<s:set  var="answerc" value="getIrpQuestionBankByIdStr(#qbankbidsid).answerc"></s:set>
							<s:set  var="answerd" value="getIrpQuestionBankByIdStr(#qbankbidsid).answerd"></s:set>
							<s:set  var="answere" value="getIrpQuestionBankByIdStr(#qbankbidsid).answere"></s:set>
							<s:set var="qanswerssinals" value="getIrpExamAnswer(exam,paper,#qbankbidsid,irpExamRecord.examidtimes).answer" ></s:set>
							
							<s:if test="#answera!=''">
								<input <s:if test="#qanswerssinals==\"A\"">checked="checked"</s:if>  type="radio" name="singleanswers<s:property value="#qbankbidsid" />" value="A">&nbsp;A&nbsp;:&nbsp;<s:property value="#answera"/><br/>
							</s:if>
							<s:if test="#answerb!=''">
								<input <s:if test="#qanswerssinals==\"B\"">checked="checked"</s:if> type="radio" name="singleanswers<s:property value="#qbankbidsid" />" value="B">&nbsp;B&nbsp;:&nbsp;<s:property value="#answerb"/><br/>
							</s:if>
							<s:if test="#answerc!=''">
								<input <s:if test="#qanswerssinals==\"C\"">checked="checked"</s:if> type="radio" name="singleanswers<s:property value="#qbankbidsid" />" value="C">&nbsp;C&nbsp;:&nbsp;<s:property value="#answerc"/><br/>
							</s:if>
							<s:if test="#answerd!=''">
								<input <s:if test="#qanswerssinals==\"D\"">checked="checked"</s:if> type="radio" name="singleanswers<s:property value="#qbankbidsid" />" value="D">&nbsp;D&nbsp;:&nbsp;<s:property value="#answerd"/><br/>
							</s:if>
							<s:if test="#answere!=''">
								<input <s:if test="#qanswerssinals==\"E\"">checked="checked"</s:if> type="radio" name="singleanswers<s:property value="#qbankbidsid" />" value="E">&nbsp;E&nbsp;:&nbsp;<s:property value="#answere"/><br/>
							</s:if>
						</div>
						
						<div class="explainandanswerdiv"  id="finaldiv<s:property value="#qbankbidsid" />">
							<div id="finalanswer<s:property value="#qbankbidsid" />"></div>
							<div id="finalexaplin<s:property value="#qbankbidsid" />"></div>
						</div>
						
					<br/>
					</div>
					</s:if>
				</s:iterator>
				
				
				
				
				<!-- millis -->
				<div style="font-size: 14px;margin-top: 20px;">二&nbsp;:&nbsp;多选题</div>
				<s:iterator value="qbankbids" var="qbankbidsid" status="qbankbidsstatus" >
					<s:if test="getIrpQuestionBankByIdStr(#qbankbidsid).qbtype==10">
					<div style="border-top: thin solid;padding: 10px 0 0 0;" name="singlequestionps" id="<s:property value="#qbankbidsid" />">
						
						<div>
							<div id="rightdaohangmenu<s:property value="#qbankbidsstatus.index" />" name="rightdaohangmenu" style="float: left;"></div>
							<div id="titlebooliv<s:property value="#qbankbidsid" />" style="float: left;"><s:property value="getIrpQuestionBankByIdStr(#qbankbidsid).questiontext" escapeHtml="false" /></div>
						</div>
						<br/>
						<div>
							<s:set  var="answera" value="getIrpQuestionBankByIdStr(#qbankbidsid).answera"></s:set>
							<s:set  var="answerb" value="getIrpQuestionBankByIdStr(#qbankbidsid).answerb"></s:set>
							<s:set  var="answerc" value="getIrpQuestionBankByIdStr(#qbankbidsid).answerc"></s:set>
							<s:set  var="answerd" value="getIrpQuestionBankByIdStr(#qbankbidsid).answerd"></s:set>
							<s:set  var="answere" value="getIrpQuestionBankByIdStr(#qbankbidsid).answere"></s:set>
							<s:set var="qanswerssinals" value="getIrpExamAnswer(exam,paper,#qbankbidsid,irpExamRecord.examidtimes).answer" ></s:set>
							

							
							
							<s:if test="#answera!=''">
								<input id="Aanservs"  type="checkbox" name="singleanswers<s:property value="#qbankbidsid" />" value="A">&nbsp;A&nbsp;:&nbsp;<s:property value="#answera"/><br/>
							</s:if>
							<s:if test="#answerb!=''">
								<input id="Banservs"  type="checkbox" name="singleanswers<s:property value="#qbankbidsid" />" value="B">&nbsp;B&nbsp;:&nbsp;<s:property value="#answerb"/><br/>
							</s:if>
							<s:if test="#answerc!=''">
								<input id="Canservs"  type="checkbox" name="singleanswers<s:property value="#qbankbidsid" />" value="C">&nbsp;C&nbsp;:&nbsp;<s:property value="#answerc"/><br/>
							</s:if>
							<s:if test="#answerd!=''">
								<input id="Danservs" type="checkbox" name="singleanswers<s:property value="#qbankbidsid" />" value="D">&nbsp;D&nbsp;:&nbsp;<s:property value="#answerd"/><br/>
							</s:if>
							<s:if test="#answere!=''">
								<input id="Eanservs"  type="checkbox" name="singleanswers<s:property value="#qbankbidsid" />" value="E">&nbsp;E&nbsp;:&nbsp;<s:property value="#answere"/><br/>
							</s:if>
							<script type="text/javascript">
							var qanswerssinalsar =  '<s:property value="qanswerssinals"/>';
							var qanswerssinalsararray = qanswerssinalsar.split(",");
							for(var  i in qanswerssinalsararray){
								 var cksanswerss = "#"+$.trim(qanswerssinalsararray[i])+"anservs";
								 $(cksanswerss).attr("checked","checked");
							}
							
							
							</script>
						</div>
						
						<div class="explainandanswerdiv"  id="finaldiv<s:property value="#qbankbidsid" />">
							<div id="finalanswer<s:property value="#qbankbidsid" />"></div>
							<div id="finalexaplin<s:property value="#qbankbidsid" />"></div>
						</div>
						
					<br/>
					</div>
					</s:if>
				</s:iterator>
				
				
				<!-- boolean -->
				<div style="font-size: 14px;">三&nbsp;:&nbsp;判断题</div>
				<s:iterator value="qbankbids" var="qbankbidsid" status="qbankbidsstatus" >
					<s:if test="getIrpQuestionBankByIdStr(#qbankbidsid).qbtype==40">
					<div style="border-top: thin solid;padding: 10px 0 0 0;" name="singlequestionps" id="<s:property value="#qbankbidsid" />">
						
						<div>
							<div  id="rightdaohangmenu<s:property value="#qbankbidsstatus.index" />" name="rightdaohangmenu"  style="float: left;"><s:property value="#qbankbidsstatus.index+1"/>&nbsp;:&nbsp;</div>
							<div  id="titlebooliv<s:property value="#qbankbidsid" />" style="float: left;"><s:property value="getIrpQuestionBankByIdStr(#qbankbidsid).questiontext" escapeHtml="false" /></div>
						</div>
						<br/>
						<s:set  var="answera" value="getIrpQuestionBankByIdStr(#qbankbidsid).answera"></s:set>
						<s:set  var="answerb" value="getIrpQuestionBankByIdStr(#qbankbidsid).answerb"></s:set>
						<s:set var="qanswerssinals" value="getIrpExamAnswer(exam,paper,#qbankbidsid,irpExamRecord.examidtimes).answer" ></s:set>
					
						<input <s:if test="#qanswerssinals==\"A\"">checked="checked"</s:if> type="radio" name="singleanswers<s:property value="#qbankbidsid" />" value="A">&nbsp;正确<br/>
						<input <s:if test="#qanswerssinals==\"B\"">checked="checked"</s:if> type="radio" name="singleanswers<s:property value="#qbankbidsid" />" value="B">&nbsp;错误<br/>
						
						<div class="explainandanswerdiv" id="finaldiv<s:property value="#qbankbidsid" />">
							<div id="finalanswer<s:property value="#qbankbidsid" />"></div>
							<div id="finalexaplin<s:property value="#qbankbidsid" />"></div>
						</div>
					<br/>	
					</div>
					</s:if>
				</s:iterator>
				
				
				
				<!-- full -->
				<div style="font-size: 14px;">四&nbsp;:&nbsp;填空题</div>
				<s:iterator value="qbankbids" var="qbankbidsid" status="qbankbidsstatus" >
				<s:if test="getIrpQuestionBankByIdStr(#qbankbidsid).qbtype==30">
					<div style="border-top: thin solid;padding: 10px 0 0 0;" name="singlequestionps" id="<s:property value="#qbankbidsid" />">
						
						<div>
							<div id="rightdaohangmenu<s:property value="#qbankbidsstatus.index" />" name="rightdaohangmenu" style="float: left;"></div>
							<div id="titlebooliv<s:property value="#qbankbidsid" />" style="float: left;"><s:property value="disposeFullContent(getIrpQuestionBankByIdStr(#qbankbidsid).questiontext)" escapeHtml="false" /></div>
							<s:set var="fulllengths" value="disposeFCLength(getIrpQuestionBankByIdStr(#qbankbidsid).questiontext)" ></s:set>
						</div>
						<br/><br/>
						
						<s:iterator value="#fulllengths" status="fulllengthsstatus" >
						<div>
							<div style="width: 20px;float: left;">(<s:property value="#fulllengthsstatus.count" />)</div>
							<div style="border: thin solid;width: 200px;float: left;">
								<input  type="text"  size="25" name="singfull<s:property value="#qbankbidsid" />"  />&nbsp;
									
								<input name="singfullnum<s:property value="#qbankbidsid" />" style="display: none;"  value="<s:property value="#fulllengthsstatus.index" />"  />
							</div>
						</div><br/><br/>
						</s:iterator>
						<div class="explainandanswerdiv" id="finaldiv<s:property value="#qbankbidsid" />">
										<div id="finalanswer<s:property value="#qbankbidsid" />"></div>
										<div id="finalexaplin<s:property value="#qbankbidsid" />"></div>
						</div>
					<br/>
					</div>
					<s:set var="qanswerssinals" value="getIrpExamAnswer(exam,paper,#qbankbidsid,irpExamRecord.examidtimes).answer" ></s:set>
						<script type="text/javascript">
						var qanswerssinalsar =  '<s:property value="qanswerssinals"/>';
						var singfullsad = "singfull"+'<s:property value="#qbankbidsid" />';
						var qanswerssinalsararrays = qanswerssinalsar.split("]");
						var inputnumi = 0;
						$("input[name='"+singfullsad+"']").each(function(){
							this.value = qanswerssinalsararrays[inputnumi].replace("[","");
							inputnumi++;
						});
						
						
						</script>
						
					</s:if>
				</s:iterator>
				
				
			</div>
			
		</div>
	</div>
	<script type="text/javascript">
	function disposeAWS(){
					
					//考试得分
					var examtotalscore = 0;
					//及格分数
					var jigesocre = '<s:property value="irpExam.qualifiedscore" />';
					//答案记录 统一考试时间
					var mysysdate = new Date().getTime();
				
					

			
	
						var examid = '<s:property value="exam"/>';
						var testpaperid = '<s:property value="paper"/>';
				
					$("div[name='singlequestionps']").each(function(){
						//填空
						var singfullid = "singfull"+this.id;
						var singfullidnums = "singfullnum"+this.id;
						
						var singfullcontent = "";
						$("input[name='"+singfullid+"']").each(function(){
							singfullcontent+="["+this.value+"]";
						});
						
						var singfullcontentindex = $("input[name='"+singfullidnums+"']").val();

					
						var singids = "singleanswers"+this.id;
						var singleval = "";
						$("input[name='"+singids+"']:checked").each(function(){
							if(this.type=="checkbox"){
								if(singleval!=""){
									singleval = singleval +","+this.value; 
								}else{
									singleval = this.value; 
								}
								
							}else if(this.type=="radio"){
								singleval = this.value;
							}
						});

						
						var finalanswerids = "#finalanswer"+this.id;
						var finalexaplinids = "#finalexaplin"+this.id;
						var finaldivids = "#finaldiv"+this.id;
						var titleboolivs = "#titlebooliv"+this.id;
						$.ajax({
							type:'post',
							url:"<%=rootPath%>exam/findquestionansweralready.action",
							async:false,
							cache:false,
							data:{
								questionid:this.id,
								answercontent:singleval,
								singfullcontent:singfullcontent,
								singfullcontentindex:singfullcontentindex,
								examid:examid,
								testpaperid:testpaperid,
								resulttype:10,
								mysysdate:mysysdate
							},
							success:function(content){
							var cons = eval(content);
							examtotalscore += parseInt(cons[0].score);
								if(content!=""){
										//允许交卷后立即显示得分和答案  
										
										$(finaldivids).css({
											"border-top":"thin solid"	
										});
										if(cons[0].status==0){
											$(titleboolivs).css({"color":"#FF0000"})
										}
										
										$(finalanswerids).html("正确答案&nbsp;:&nbsp;"+cons[0].answer);
										$(finalexaplinids).html("<div style=\"float:left;width:6%;\">解析&nbsp;:&nbsp;</div><div  style=\"float:left;width:94%;\">"+cons[0].explain+"</div><br/>");
								
									
											
								}
							}
						});
					});
	
	}
	
	disposeAWS();
	$("input").attr("disabled","disabled");
	
	</script>
	<jsp:include page="../include/client_foot.jsp" />
</div>
</body>
</html>