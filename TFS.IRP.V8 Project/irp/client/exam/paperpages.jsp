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
function jJCont(_resultpid){
					//考试时间
					var examtiems = '<s:property value="irpExam.answertime" />';
					//考试得分
					var examtotalscore = 0;
					//及格分数
					var jigesocre = '<s:property value="irpExam.qualifiedscore" />';
					//答案记录 统一考试时间
					var mysysdate = new Date().getTime();
				
					var yongshitimes = $("#examtimes").html();
					var yongarray = yongshitimes.split(":");
					var haoshi =parseInt(examtiems)-(parseInt(yongarray[0])*60+parseInt(yongarray[1]));
			
	
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
							url:"<%=rootPath%>exam/findquestionanswer.action",
							async:false,
							cache:false,
							data:{
								questionid:this.id,
								answercontent:singleval,
								singfullcontent:singfullcontent,
								singfullcontentindex:singfullcontentindex,
								examid:examid,
								testpaperid:testpaperid,
								resulttype:_resultpid,
								mysysdate:mysysdate
							},
							success:function(content){
							var cons = eval(content);
							examtotalscore += parseInt(cons[0].score);
								if(content!=""){
									if(_resultpid==10){
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
							}
						});
					});
			 if(_resultpid==20){
					//交卷   考完后 不显示答案
					window.close();
				}
			//判断及没及格
			var jigestatus = 10;
			if(parseInt(examtotalscore)-parseInt(jigesocre)>=0){
				//及格了
				jigestatus = 10
			}else{
				jigestatus = 20;
			}
				  
			//增加考试记录
			$.ajax({
				type:"post",
				async:false,
				cache:false,
				url:"<%=rootPath%>exam/addexamrecord.action",
				data:{
					examid:examid,
					examtimes:haoshi,
					jigestatus:jigestatus,
					examtotalscore:examtotalscore,
					mysysdate:mysysdate,
					examstatus:_resultpid
					
				},
				success:function(msg){
						if(_resultpid==10){
							if(msg==1){
								var examresultfont = "没通过";
								if(jigestatus==10){
									examresultfont = "通过";
								}
								window.clearInterval();
								$("#shengyutimes").html("考试得分:"+examtotalscore+"&nbsp;&nbsp;&nbsp;考试结果:"+examresultfont);
								$.dialog.alert("考试得分:"+examtotalscore+"&nbsp;&nbsp;&nbsp;考试结果:"+examresultfont);
							}
						}
				}
			});	
			$("#pagesjiaojuan").remove();
			

}

/**
* 答卷
*/
function handPaper(_resultpid){
	$.dialog.confirm('您确定要提交试卷吗？',function(){
			jJCont(_resultpid);
	});
}


function hasSetCookie(){
	var examid = <s:property value="exam"/>;
    var strCookie = document.cookie;
    var arrCookie = strCookie.split("; ");
    for (var i = 0; i < arrCookie.length; i++) {
      var arr = arrCookie[i].split("=");
      if (arr[0] == examid) {
        return true;
      }
    };
    return false;
}


function addCookie(name, value) {
    var cookieString = name + "=" + escape(value); //escape() 函数可对字符串进行编码，这样就可以在所有的计算机上读取该字符串。
	document.cookie = cookieString;
    alert(1);
}

function editCookie(name, value) {
    var cookieString = name + "=" + escape(value);
    document.cookie = cookieString;
  }
  
function getCookieValue(name) {
    var strCookie = document.cookie;
    var arrCookie = strCookie.split("; ");
    for (var i = 0; i < arrCookie.length; i++) {
      var arr = arrCookie[i].split("=");
      if (arr[0] == name) {
    	  alert(name);
    	  alert(unescape(arr[1]));
        return unescape(arr[1]);
        break;
      } else {
        continue;
      };
    };
  }
/**
* 处理考试时间
*/
function disposeExamTime(){
	//var time = '<s:property value="irpExam.answertime" />';
	//alert(time);
	var time = <s:property value="moretime"/>;
	
	if(time!=null && time!=""){
		if(time!="" && isNaN(time)==false){
			var _second = time % 60;
	        var _minute = parseInt(time / 60) % 60;
	        var _hour = parseInt(parseInt(time / 60) / 60);
	  
	        if (_hour < 10)
	          _hour = "0" + _hour.toString();
	        if (_second < 10)
	          _second = "0" + _second.toString();
	        if (_minute < 10)
	          _minute = "0" + _minute.toString();
		}else{
			hours = 0;
			mintus = 0;
			secods = 0;
		}	
			finaltimes = _hour+":"+_minute+":"+_second;
			$("#examtimes").html(finaltimes);
	}
}

function daoJS(){
	var examid = <s:property value="exam"/>;
	var examt = $("#examtimes").html();
	if(examt!=null && examt!=""){
	var examarray = examt.split(":");
	var finaltimes,hours,mintus,secods = "";
	if(parseInt(examarray[0])>0 || parseInt(examarray[1])>0 || parseInt(examarray[2])>0){
		if(parseInt(examarray[2])<=0){
			secods = 59;
		}else{
			secods = parseInt(examarray[2])-1;
		}
		if(parseInt(examarray[2])==0 && parseInt(examarray[1])>0){
			mintus = parseInt(examarray[1])-1;
		}else{
			mintus = parseInt(examarray[1]);
		}
		if(parseInt(examarray[2])==0 && parseInt(examarray[1])==0 && parseInt(examarray[0])>=1){
			mintus = 59;
		}
		
		if(parseInt(examarray[1])==0 && parseInt(examarray[2])==0 && parseInt(examarray[0])>=1){
			hours = parseInt(examarray[0])-1;
		}else{
			hours = parseInt(examarray[0]);
		}
	}else{
		hours = 0;
		mintus = 0;
		secods = 0;
		//时间到 强制交卷
		jJCont('<s:property value="irpExam.resultputlic" />');
	}
		finaltimes = hours+":"+mintus+":"+secods;
		$("#examtimes").html(finaltimes);
	}
}
var viewleftnum = 1;
var viewrightnum = 1;
var startvsecords = 0;
var endvsecords = 0;
var daojishi;
var bbques;
var endques;
var startexamsv = "";
var endexamsv = "";
var setsecondtongs = 1000;
$(function(){
	
	disposeExamTime();
	daojishi = setInterval("daoJS()",setsecondtongs);
	startexamsv =  '<s:property value="irpExam.startv" />';
	var begintimes = '<s:date name="irpExam.begintime" format="yyyy-MM-dd HH:mm" />';
	var begintime = begintimes.slice(11);
	var timearray = begintime.split(":");
	var sysDate = new Date();
	var syshour = sysDate.getHours();
	var sysminutes = sysDate.getMinutes();
	var totalminutes = parseInt(syshour)*60+parseInt(sysminutes);
	var time =totalminutes-parseInt(timearray[0])*60-parseInt(timearray[1]);
	if(startexamsv!=""){
		var startv = parseInt(startexamsv)-parseInt(time);
		if(startv>0){
		startvsecords = parseInt(startv)*60;	
		}
	}
	bbques = setInterval("boolsBoolQues()",setsecondtongs);
	
	endexamsv =  '<s:property value="irpExam.endv" />';
	if(endexamsv!=""){
		var endv = parseInt(endexamsv)-parseInt(time);
		if(endv>0){
		endvsecords = parseInt(endv)*60;	
		}
	}
	endques = setInterval("booleanEndVQues()",setsecondtongs);
	//左侧标号
	$("strong[name='leftdaohangmenu']").each(function(){
		$("#"+this.id).html(viewleftnum++);
	});
	//右侧题号
	$("div[name='rightdaohangmenu']").each(function(){
		$("#"+this.id).html(viewrightnum+++"&nbsp;:&nbsp;");
	});
	
});
//判断是否开始答题
function boolsBoolQues(){
	if(startexamsv!=""){
		startvsecords = startvsecords-1;
		if(startvsecords<=1){
			$("input").removeAttr("disabled");
			window.clearInterval(bbques);
		}else{
			$("input").attr("disabled","disabled");	
		}
	}
	
}
//判断是否可以交卷
function booleanEndVQues(){
	if(endexamsv!=""){
		endvsecords = endvsecords-1;
		if(endvsecords<=1){
			//接触交卷绑定
			$("#pagesjiaojuan").css("display","block");
			$("#jiaojuanida").bind("click",function(){
				handPaper('<s:property value="irpExam.resultputlic" />');
			});
		}else{
			$("#pagesjiaojuan").css("display","none");
			$("#jiaojuanida").unbind();
		}
	}
}

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
			<label id="shengyutimes" style="margin-left: 500px;" >剩余时间&nbsp;:&nbsp;<label id="examtimes"></label></label>
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
							<s:if test="#answera!=''">
								<input type="radio" name="singleanswers<s:property value="#qbankbidsid" />" value="A">&nbsp;A&nbsp;:&nbsp;<s:property value="#answera"/><br/>
							</s:if>
							<s:if test="#answerb!=''">
								<input type="radio" name="singleanswers<s:property value="#qbankbidsid" />" value="B">&nbsp;B&nbsp;:&nbsp;<s:property value="#answerb"/><br/>
							</s:if>
							<s:if test="#answerc!=''">
								<input type="radio" name="singleanswers<s:property value="#qbankbidsid" />" value="C">&nbsp;C&nbsp;:&nbsp;<s:property value="#answerc"/><br/>
							</s:if>
							<s:if test="#answerd!=''">
								<input type="radio" name="singleanswers<s:property value="#qbankbidsid" />" value="D">&nbsp;D&nbsp;:&nbsp;<s:property value="#answerd"/><br/>
							</s:if>
							<s:if test="#answere!=''">
								<input type="radio" name="singleanswers<s:property value="#qbankbidsid" />" value="E">&nbsp;E&nbsp;:&nbsp;<s:property value="#answere"/><br/>
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
							<s:if test="#answera!=''">
								<input type="checkbox" name="singleanswers<s:property value="#qbankbidsid" />" value="A">&nbsp;A&nbsp;:&nbsp;<s:property value="#answera"/><br/>
							</s:if>
							<s:if test="#answerb!=''">
								<input type="checkbox" name="singleanswers<s:property value="#qbankbidsid" />" value="B">&nbsp;B&nbsp;:&nbsp;<s:property value="#answerb"/><br/>
							</s:if>
							<s:if test="#answerc!=''">
								<input type="checkbox" name="singleanswers<s:property value="#qbankbidsid" />" value="C">&nbsp;C&nbsp;:&nbsp;<s:property value="#answerc"/><br/>
							</s:if>
							<s:if test="#answerd!=''">
								<input type="checkbox" name="singleanswers<s:property value="#qbankbidsid" />" value="D">&nbsp;D&nbsp;:&nbsp;<s:property value="#answerd"/><br/>
							</s:if>
							<s:if test="#answere!=''">
								<input type="checkbox" name="singleanswers<s:property value="#qbankbidsid" />" value="E">&nbsp;E&nbsp;:&nbsp;<s:property value="#answere"/><br/>
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
						<input type="radio" name="singleanswers<s:property value="#qbankbidsid" />" value="A">&nbsp;正确<br/>
						<input type="radio" name="singleanswers<s:property value="#qbankbidsid" />" value="B">&nbsp;错误<br/>
						
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
								<input type="text"  size="25" name="singfull<s:property value="#qbankbidsid" />"  />&nbsp;
									
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
					</s:if>
				</s:iterator>
				
				
				<div id="pagesjiaojuan" style="font-size: 15px;text-align: center;"><a id="jiaojuanida" href="javascript:void(0);" onclick="handPaper(<s:property value="irpExam.resultputlic" />)">交卷</a></div>
				
			</div>
			
		</div>
	</div>
	<jsp:include page="../include/client_foot.jsp" />
</div>
</body>
</html>