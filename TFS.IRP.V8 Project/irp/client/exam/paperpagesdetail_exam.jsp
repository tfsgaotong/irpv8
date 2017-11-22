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
<title>考试详情</title>
<link rel="shortcut icon" href="<%=rootPath%>favicon.ico"
	type="image/x-icon" />
<link rel="bookmark" href="<%=rootPath%>favicon.ico" />
<link href="<%=rootPath%>/client/css/oacss.css" rel="stylesheet"type="text/css" />
<link href="<%=rootPath%>/client/css/paperpage_exam.css" rel="stylesheet" type="text/css"  />
<jsp:include page="../include/client_skin.jsp" />
<style type="text/css">
body{
	background:none;
}
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
		$("span[name='rightdaohangmenu']").each(function(){
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
	background-color:#3CB371;
	padding:5px;
	border-radius:5px;
}
.explainandanswerdiv div{
	color:#fff;
}
</style>
</head>
<body>
	<div class="main" style="background-image: url('');">
		<div  style="position:fixed;width:1200px;">
			<div class="header">考试名称：<s:property value="irpExam.examname" />
				<div id="shengyutimes" class="time" style="width:300px;line-height:35px;background-color:#3CB371;margin-right:0px;">
					总分&nbsp;:&nbsp;<s:property value="irpTestpaper.papertime" />
					考试成绩&nbsp;:&nbsp;<s:property value="irpExamRecord.examgrade" />&nbsp;&nbsp;考试状态&nbsp;:&nbsp;<s:if test="irpExamRecord.examstatus==10">通过</s:if><s:elseif test="irpExamRecord.examstatus==20">未通过</s:elseif>
				</div>
			</div>	
		</div>
	<div class="question">
		<ul class="question_detail">
			<li style="color:#225491">
				<a>单选</a>
			</li>
			<li>
				<a>多选</a>
				
			</li>
			<li>
				<a>判断</a>
				
			</li>
			<li>
				<a>填空</a>
				
			</li>
		</ul>
	</div>
	<div class="question_num">
		<ul class="question_detail">
			<s:iterator value="qbankbids" var="qbankbidsid" status="qbankbidsstatus">
				<s:if test="getIrpQuestionBankByIdStr(#qbankbidsid).qbtype==20">
					<li>
						<a>
							<strong id="leftdaohangmenu<s:property value="#qbankbidsstatus.index" />" onclick="linkPageQues(<s:property value="#qbankbidsid" />)"  name="leftdaohangmenu" style="margin-left:15px;cursor:pointer;">1</strong>	
						</a>
					</li>
			</s:if>
			</s:iterator>
			<s:iterator value="qbankbids" var="qbankbidsid" status="qbankbidsstatus">
				<s:if test="getIrpQuestionBankByIdStr(#qbankbidsid).qbtype==10">
					<li>
						<a>
							<strong id="leftdaohangmenu<s:property value="#qbankbidsstatus.index" />" onclick="linkPageQues(<s:property value="#qbankbidsid" />)"  name="leftdaohangmenu" style="margin-left:15px;cursor:pointer;">1</strong>	
						</a>
					</li>
				</s:if>
			</s:iterator>
			<s:iterator value="qbankbids" var="qbankbidsid" status="qbankbidsstatus">
				<s:if test="getIrpQuestionBankByIdStr(#qbankbidsid).qbtype==40">
					<li>
						<a>
							<strong id="leftdaohangmenu<s:property value="#qbankbidsstatus.index" />" onclick="linkPageQues(<s:property value="#qbankbidsid" />)"  name="leftdaohangmenu" style="margin-left:15px;cursor:pointer;">1</strong>	
						</a>
					</li>
				</s:if>
			</s:iterator>
			<s:iterator value="qbankbids" var="qbankbidsid" status="qbankbidsstatus">
				<s:if test="getIrpQuestionBankByIdStr(#qbankbidsid).qbtype==30">
					<li>
						<a>
							<strong id="leftdaohangmenu<s:property value="#qbankbidsstatus.index" />" onclick="linkPageQues(<s:property value="#qbankbidsid" />)"  name="leftdaohangmenu" style="margin-left:15px;cursor:pointer;">1</strong>	
						</a>
					</li>
				</s:if>
			</s:iterator>
		</ul>
	</div>
	<div class="quesiton_list">
		<div class="choice">
			<div class="title">
				<span>单选题</span>
			</div>
			<div class="details">
				<ul class="choice_detail">
				<s:iterator value="qbankbids" var="qbankbidsid" status="qbankbidsstatus" >
					<s:if test="getIrpQuestionBankByIdStr(#qbankbidsid).qbtype==20">
						<li name="singlequestionps" id="<s:property value="#qbankbidsid" />">
							<span style="display:inline-block" id="rightdaohangmenu<s:property value="#qbankbidsstatus.index" />" name="rightdaohangmenu"></span>
							<span style="display:inline-block;word-break:break-all;" id="titlebooliv<s:property value="#qbankbidsid" />"><s:property value="getIrpQuestionBankByIdStr(#qbankbidsid).questiontext" escapeHtml="false" /></span>
							<ul>
								<s:set  var="answera" value="getIrpQuestionBankByIdStr(#qbankbidsid).answera"></s:set>
								<s:set  var="answerb" value="getIrpQuestionBankByIdStr(#qbankbidsid).answerb"></s:set>
								<s:set  var="answerc" value="getIrpQuestionBankByIdStr(#qbankbidsid).answerc"></s:set>
								<s:set  var="answerd" value="getIrpQuestionBankByIdStr(#qbankbidsid).answerd"></s:set>
								<s:set  var="answere" value="getIrpQuestionBankByIdStr(#qbankbidsid).answere"></s:set>
								<s:set var="qanswerssinals" value="getIrpExamAnswerAdmin(exam,paper,#qbankbidsid,irpExamRecord.examidtimes,irpExamRecord.cruserid).answer" ></s:set>
								<s:if test="#answera!='' && #answera!=null">
									<li><span>&nbsp;A:&nbsp;</span><input <s:if test="#qanswerssinals==\"A\"">checked="checked"</s:if>  type="radio" name="singleanswers<s:property value="#qbankbidsid" />" value="A">&nbsp;<span><s:property value="#answera"/></span></li>
								</s:if>
								<s:if test="#answerb!='' && #answerb!=null">
									<li><span>&nbsp;B:&nbsp;</span><input <s:if test="#qanswerssinals==\"B\"">checked="checked"</s:if> type="radio" name="singleanswers<s:property value="#qbankbidsid" />" value="B">&nbsp;<span><s:property value="#answerb"/></span></li>
								</s:if>
								<s:if test="#answerc!='' && #answerc!=null">
									<li><span>&nbsp;C:&nbsp;</span><input <s:if test="#qanswerssinals==\"C\"">checked="checked"</s:if> type="radio" name="singleanswers<s:property value="#qbankbidsid" />" value="C">&nbsp;<span><s:property value="#answerc"/></span></li>
								</s:if>
								<s:if test="#answerd!='' && #answerd!=null">
									<li><span>&nbsp;D:&nbsp;</span><input <s:if test="#qanswerssinals==\"D\"">checked="checked"</s:if> type="radio" name="singleanswers<s:property value="#qbankbidsid" />" value="D">&nbsp;<span><s:property value="#answerd"/></span></li>
								</s:if>
								<s:if test="#answere!='' && #answere!=null">
									<li><span>&nbsp;E:&nbsp;</span><input <s:if test="#qanswerssinals==\"E\"">checked="checked"</s:if> type="radio" name="singleanswers<s:property value="#qbankbidsid" />" value="E">&nbsp;<span><s:property value="#answere"/></span></li>
								</s:if>
							</ul>
							<div class="explainandanswerdiv"  id="finaldiv<s:property value="#qbankbidsid" />">
								<div id="finalanswer<s:property value="#qbankbidsid" />"></div>
								<div id="finalexaplin<s:property value="#qbankbidsid" />"></div>
							</div>
						</li>
					</s:if>
					</s:iterator>
				</ul>
			</div>
		</div>
		
		<div class="choice">
			<div class="title">
				<span>多选题</span>
			</div>
			<div class="details">
				<ul class="choice_detail">
					<s:iterator value="qbankbids" var="qbankbidsid" status="qbankbidsstatus" >
						<s:if test="getIrpQuestionBankByIdStr(#qbankbidsid).qbtype==10">
							<li name="singlequestionps" id="<s:property value="#qbankbidsid" />">
								<span id="rightdaohangmenu<s:property value="#qbankbidsstatus.index" />" name="rightdaohangmenu"></span>
								<span id="titlebooliv<s:property value="#qbankbidsid" />" style="display:inline-block"><s:property value="getIrpQuestionBankByIdStr(#qbankbidsid).questiontext" escapeHtml="false" /></span>
									<ul>
										<s:set  var="answera" value="getIrpQuestionBankByIdStr(#qbankbidsid).answera"></s:set>
										<s:set  var="answerb" value="getIrpQuestionBankByIdStr(#qbankbidsid).answerb"></s:set>
										<s:set  var="answerc" value="getIrpQuestionBankByIdStr(#qbankbidsid).answerc"></s:set>
										<s:set  var="answerd" value="getIrpQuestionBankByIdStr(#qbankbidsid).answerd"></s:set>
										<s:set  var="answere" value="getIrpQuestionBankByIdStr(#qbankbidsid).answere"></s:set>
										<s:set var="qanswerssinals" value="getIrpExamAnswerAdmin(exam,paper,#qbankbidsid,irpExamRecord.examidtimes,irpExamRecord.cruserid).answer" ></s:set>
										
										<s:if test="#answera!='' && #answera!=null">
											<li><span>&nbsp;A:&nbsp;</span><input id="Aanservs"  type="checkbox" name="singleanswers<s:property value="#qbankbidsid" />" value="A">&nbsp;<span><s:property value="#answera"/></span></li>
										</s:if>
										<s:if test="#answerb!='' && #answerb!=null">
											<li><span>&nbsp;B:&nbsp;</span><input id="Banservs"  type="checkbox" name="singleanswers<s:property value="#qbankbidsid" />" value="B">&nbsp;<span><s:property value="#answerb"/></span></li>
										</s:if>
										<s:if test="#answerc!='' && #answerc!=null">
											<li><span>&nbsp;C:&nbsp;</span><input id="Canservs"  type="checkbox" name="singleanswers<s:property value="#qbankbidsid" />" value="C">&nbsp;<span><s:property value="#answerd"/></span></li>
										</s:if>
										<s:if test="#answerd!='' && #answerd!=null">
											<li><span>&nbsp;D:&nbsp;</span><input id="Danservs"  type="checkbox" name="singleanswers<s:property value="#qbankbidsid" />" value="D">&nbsp;<span><s:property value="#answerd"/></span></li>
										</s:if>
										<s:if test="#answere!='' && #answere!=null">
											<li><span>&nbsp;E:&nbsp;</span><input id="Eanservs"  type="checkbox" name="singleanswers<s:property value="#qbankbidsid" />" value="E">&nbsp;<span><s:property value="#answere"/></span></li>
										</s:if>
									</ul>
									<div class="explainandanswerdiv"  id="finaldiv<s:property value="#qbankbidsid" />">
										<div id="finalanswer<s:property value="#qbankbidsid" />"></div>
										<div id="finalexaplin<s:property value="#qbankbidsid" />"></div>
									</div>
							</li>
						</s:if>
					</s:iterator>
				</ul>
			</div>
		</div>
		
		<div class="judge">
			<div class="title">
				<span>判断题</span>
			</div>
			<div class="details">
				<ul class="choice_detail">
					<s:iterator value="qbankbids" var="qbankbidsid" status="qbankbidsstatus" >
						<s:if test="getIrpQuestionBankByIdStr(#qbankbidsid).qbtype==40">
							<li  name="singlequestionps" id="<s:property value="#qbankbidsid" />">
								<span id="rightdaohangmenu<s:property value="#qbankbidsstatus.index" />" name="rightdaohangmenu"><s:property value="#qbankbidsstatus.index+1"/>&nbsp;:&nbsp;</span>
								<span id="titlebooliv<s:property value="#qbankbidsid" />" style="display:inline-block"><s:property value="getIrpQuestionBankByIdStr(#qbankbidsid).questiontext" escapeHtml="false" /></span>
									<ul>
										<s:set  var="answera" value="getIrpQuestionBankByIdStr(#qbankbidsid).answera"></s:set>
										<s:set  var="answerb" value="getIrpQuestionBankByIdStr(#qbankbidsid).answerb"></s:set>
										<s:set var="qanswerssinals" value="getIrpExamAnswerAdmin(exam,paper,#qbankbidsid,irpExamRecord.examidtimes,irpExamRecord.cruserid).answer" ></s:set>
										<li><span>A:&nbsp;</span><input <s:if test="#qanswerssinals==\"A\"">checked="checked"</s:if> type="radio" name="singleanswers<s:property value="#qbankbidsid" />" value="A"><span>&nbsp;正确</span></li>
										<li><span>B:&nbsp;</span><input <s:if test="#qanswerssinals==\"B\"">checked="checked"</s:if> type="radio" name="singleanswers<s:property value="#qbankbidsid" />" value="B"><span>&nbsp;错误</span></li>
									</ul>
								<div class="explainandanswerdiv" id="finaldiv<s:property value="#qbankbidsid" />">
									<div id="finalanswer<s:property value="#qbankbidsid" />"></div>
									<div id="finalexaplin<s:property value="#qbankbidsid" />"></div>
								</div>
							</li>
						</s:if>
					</s:iterator>
				</ul>
			</div>
		</div>
		
		<div class="Multiselect">
			<div class="title">
				<span>填空题</span>
			</div>
			<div class="details">
				<ul class="choice_detail">
					<s:iterator value="qbankbids" var="qbankbidsid" status="qbankbidsstatus" >
						<s:set var="qanswerssinal" value="getIrpExamAnswerAdmin(exam,paper,#qbankbidsid,irpExamRecord.examidtimes,irpExamRecord.cruserid).answer" ></s:set>
						<s:if test="getIrpQuestionBankByIdStr(#qbankbidsid).qbtype==30">
							<li name="singlequestionps" id="<s:property value="#qbankbidsid" />">
								<span id="rightdaohangmenu<s:property value="#qbankbidsstatus.index" />" name="rightdaohangmenu"></span>
								<span id="titlebooliv<s:property value="#qbankbidsid" />" style="display:inline-block"><s:property value="disposeFullContent(getIrpQuestionBankByIdStr(#qbankbidsid).questiontext)" escapeHtml="false" /></span>
								<s:set var="fulllengths" value="disposeFCLength(getIrpQuestionBankByIdStr(#qbankbidsid).questiontext)" ></s:set>
								<s:iterator value="#fulllengths" status="fulllengthsstatus" >
									<div style="margin-top:6px;">
										<div style="width: 20px;float: left;margin-left:20px;">(<s:property value="#fulllengthsstatus.count" />)</div>
										<div style="width: 20px;float: left;">
											<input class="singfull" type="text"  size="25" data="<s:property value="#qanswerssinal" />" name="singfull<s:property value="#qbankbidsid" />">&nbsp;</input>
											<input name="singfullnum<s:property value="#qbankbidsid" />" style="display: none;"  value="<s:property value="#fulllengthsstatus.index" />"  />
										</div>
									</div>
									<br/><br/>
								</s:iterator>
								<div style="" class="explainandanswerdiv" id="finaldiv<s:property value="#qbankbidsid" />">
									<div id="finalanswer<s:property value="#qbankbidsid" />"></div>
									<div id="finalexaplin<s:property value="#qbankbidsid" />"></div>
								</div>
							</li>
						
						</s:if>
					</s:iterator>
				</ul>
			</div>
		</div>
	</div>
	<script type="text/javascript">
			$(".singfull").each(function(){
				var singfullsad = $(this).attr("name");
				var qanswerssinalsar =  $(this).attr("data");;
				var qanswerssinalsararrays = qanswerssinalsar.split("]");
				var inputnumi = 0;
				$("input[name='"+singfullsad+"']").each(function(){
					this.value = qanswerssinalsararrays[inputnumi].replace("[","");
					inputnumi++;
				});
			})
			
	</script>
	<script type="text/javascript">
			var qanswerssinalsar =  '<s:property value="qanswerssinals"/>';
			var qanswerssinalsararray = qanswerssinalsar.split(",");
			for(var  i in qanswerssinalsararray){
				 var cksanswerss = "#"+$.trim(qanswerssinalsararray[i])+"anservs";
				 $(cksanswerss).attr("checked","checked");
			}
	</script>
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
				
					$("li[name='singlequestionps']").each(function(){
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
										if(cons[0].status==0){
											$(titleboolivs).css({"color":"#FF0000"});
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
	<div class="end" style="background:none;"><s:text name="page.foot.info" /></div>
	</div>
</body>
</html>