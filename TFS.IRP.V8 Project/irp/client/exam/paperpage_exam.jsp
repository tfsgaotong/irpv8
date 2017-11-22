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
<meta http-equiv="cache-control" content="public">
<title>考试</title>
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
<script type="text/javascript" src="<%=rootPath%>/client/js/jquery.cookie.js"></script>
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
					if(examtiems!=""){
						var haoshi =parseInt(examtiems)-(parseInt(yongarray[0])*60+parseInt(yongarray[1]));
					}
	
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
										
										if(cons[0].status==0){
											$(titleboolivs).css({"color":"#FF0000"})
										}
										$(".explainandanswerdiv").show();
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
								$("#pagesjiaojuan").hide();
								$("#shengyutimes").hide();
								$("#result").html("考试成绩&nbsp;:&nbsp;"+examtotalscore+"&nbsp;&nbsp;考试状态&nbsp;:&nbsp;"+examresultfont);
								$("#result").show();
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
				if(secods<10){
					secods = "0"+secods;
				}
			}
			if(parseInt(examarray[2])==0 && parseInt(examarray[1])>0){
				mintus = parseInt(examarray[1])-1;
				if(mintus<10){
					mintus = "0"+mintus;
				}
			}else{
				mintus = parseInt(examarray[1]);
				if(mintus<10){
					mintus = "0"+mintus;
				}
			}
			if(parseInt(examarray[2])==0 && parseInt(examarray[1])==0 && parseInt(examarray[0])>=1){
				mintus = 59;
			}
			
			if(parseInt(examarray[1])==0 && parseInt(examarray[2])==0 && parseInt(examarray[0])>=1){
				hours = parseInt(examarray[0])-1;
				if(hours<10){
					hours = "0"+hours;
				}
			}else{
				hours = parseInt(examarray[0]);
				if(hours<10){
					hours = "0"+hours;
				}
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
		$("span[name='rightdaohangmenu']").each(function(){
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
function getVal(obj){
	alert($(obj).val());
	var name = $(obj).attr("name");
	alert(name);
	$.cookie(name,$(obj).val());
}
$(function(){
	alert($.cookie("singleanswers55"));
});

</script>
<style type="text/css">
.explainandanswerdiv{
	background-color: #3CB371;
	border-radius:5px;
	padding:5px;
	display:none;
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
		<div id="pagesjiaojuan" class="time" style="line-height:35px;background-color:#3CB371;margin-right:0"><a id="jiaojuanida" href="javascript:void(0);" onclick="handPaper(<s:property value="irpExam.resultputlic" />)" style="color:#fff">交卷</a></div>
		<div id="shengyutimes" class="time" style="line-height:35px;background-color:#3CB371">剩余时间：<label id="examtimes"></label></div>
		<div id="result" class="time" style="width:300px;line-height:35px;background-color:#3CB371;margin-right:0px;display:none">
					
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
				<ul class="choice_detail" style="text-align:left;">
				<s:iterator value="qbankbids" var="qbankbidsid" status="qbankbidsstatus" >
					<s:if test="getIrpQuestionBankByIdStr(#qbankbidsid).qbtype==20">
					<li   name="singlequestionps" id="<s:property value="#qbankbidsid" />">
						<strong><span id="rightdaohangmenu<s:property value="#qbankbidsstatus.index" />" name="rightdaohangmenu"></span></strong>
						<span style="display:inline-block" id="titlebooliv<s:property value="#qbankbidsid" />"><s:property value="getIrpQuestionBankByIdStr(#qbankbidsid).questiontext" escapeHtml="false" /></span>
						<ul>
							<s:set  var="answera" value="getIrpQuestionBankByIdStr(#qbankbidsid).answera"></s:set>
							<s:set  var="answerb" value="getIrpQuestionBankByIdStr(#qbankbidsid).answerb"></s:set>
							<s:set  var="answerc" value="getIrpQuestionBankByIdStr(#qbankbidsid).answerc"></s:set>
							<s:set  var="answerd" value="getIrpQuestionBankByIdStr(#qbankbidsid).answerd"></s:set>
							<s:set  var="answere" value="getIrpQuestionBankByIdStr(#qbankbidsid).answere"></s:set>
							<s:if test="#answera!='' && #answera!=null">
								<li><span>&nbsp;A:&nbsp;</span><input type="radio" name="singleanswers<s:property value="#qbankbidsid" />" onclick="getVal(this)" value="A">&nbsp;<span><s:property value="#answera"/></span></li>
							</s:if>
							<s:if test="#answerb!='' && #answerb!=null">
								<li><span>&nbsp;B:&nbsp;</span><input type="radio" name="singleanswers<s:property value="#qbankbidsid" />" onclick="getVal(this)" value="B">&nbsp;<span><s:property value="#answerb"/></span></li>
							</s:if>
							<s:if test="#answerc!='' && #answerc!=null">
								<li><span>&nbsp;C:&nbsp;</span><input type="radio" name="singleanswers<s:property value="#qbankbidsid" />" onclick="getVal(this)" value="C">&nbsp;<span><s:property value="#answerc"/></span></li>
							</s:if>
							<s:if test="#answerd!='' && #answerd!=null">
								<li><span>&nbsp;D:&nbsp;</span><input type="radio" name="singleanswers<s:property value="#qbankbidsid" />" onclick="getVal(this)" value="D">&nbsp;<span><s:property value="#answerd"/></span></li>
							</s:if>
							<s:if test="#answere!='' && #answere!=null">
								<li><span>&nbsp;E:&nbsp;</span><input type="radio" name="singleanswers<s:property value="#qbankbidsid" />" onclick="getVal(this)" value="E">&nbsp;<span><s:property value="#answere"/></span></li>
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
					<li   name="singlequestionps" id="<s:property value="#qbankbidsid" />">
						<strong><span id="rightdaohangmenu<s:property value="#qbankbidsstatus.index" />" name="rightdaohangmenu"></span></strong>
						<span id="titlebooliv<s:property value="#qbankbidsid" />" style="display:inline-block"><s:property value="getIrpQuestionBankByIdStr(#qbankbidsid).questiontext" escapeHtml="false" /></span>
							<ul>
								<s:set  var="answera" value="getIrpQuestionBankByIdStr(#qbankbidsid).answera"></s:set>
								<s:set  var="answerb" value="getIrpQuestionBankByIdStr(#qbankbidsid).answerb"></s:set>
								<s:set  var="answerc" value="getIrpQuestionBankByIdStr(#qbankbidsid).answerc"></s:set>
								<s:set  var="answerd" value="getIrpQuestionBankByIdStr(#qbankbidsid).answerd"></s:set>
								<s:set  var="answere" value="getIrpQuestionBankByIdStr(#qbankbidsid).answere"></s:set>
								<s:if test="#answera!='' && #answera!=null">
									<li><span>&nbsp;A:&nbsp;</span><input type="checkbox" name="singleanswers<s:property value="#qbankbidsid" />" value="A">&nbsp;<span><s:property value="#answera"/></span></li>
								</s:if>
								<s:if test="#answerb!='' && #answerb!=null">
									<li><span>&nbsp;B:&nbsp;</span><input type="checkbox" name="singleanswers<s:property value="#qbankbidsid" />" value="B">&nbsp;<span><s:property value="#answerb"/></span></li>
								</s:if>
								<s:if test="#answerc!='' && #answerc!=null">
									<li><span>&nbsp;C:&nbsp;</span><input type="checkbox" name="singleanswers<s:property value="#qbankbidsid" />" value="C">&nbsp;<span><s:property value="#answerd"/></span></li>
								</s:if>
								<s:if test="#answerd!='' && #answerd!=null">
									<li><span>&nbsp;D:&nbsp;</span><input type="checkbox" name="singleanswers<s:property value="#qbankbidsid" />" value="D">&nbsp;<span><s:property value="#answerd"/></span></li>
								</s:if>
								<s:if test="#answere!='' && #answere!=null">
									<li><span>&nbsp;E:&nbsp;</span><input type="checkbox" name="singleanswers<s:property value="#qbankbidsid" />" value="E">&nbsp;<span><s:property value="#answere"/></span></li>
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
								<strong><span id="rightdaohangmenu<s:property value="#qbankbidsstatus.index" />" name="rightdaohangmenu"><s:property value="#qbankbidsstatus.index+1"/>&nbsp;:&nbsp;</span></strong>
								<span style="display:inline-block" id="titlebooliv<s:property value="#qbankbidsid" />"><s:property value="getIrpQuestionBankByIdStr(#qbankbidsid).questiontext" escapeHtml="false" /></span>
								<ul>
									<s:set  var="answera" value="getIrpQuestionBankByIdStr(#qbankbidsid).answera"></s:set>
									<s:set  var="answerb" value="getIrpQuestionBankByIdStr(#qbankbidsid).answerb"></s:set>
									<li><span>A:&nbsp;</span><input type="radio" name="singleanswers<s:property value="#qbankbidsid" />" value="A"><span>&nbsp;正确</span></li>
									<li><span>B:&nbsp;</span><input type="radio" name="singleanswers<s:property value="#qbankbidsid" />" value="B"><span>&nbsp;错误</span></li>
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
						<s:if test="getIrpQuestionBankByIdStr(#qbankbidsid).qbtype==30">
							<li name="singlequestionps" id="<s:property value="#qbankbidsid" />">
								<strong><span id="rightdaohangmenu<s:property value="#qbankbidsstatus.index" />" name="rightdaohangmenu"></span></strong>
								<span id="titlebooliv<s:property value="#qbankbidsid" />" style="display:inline-block"><s:property value="disposeFullContent(getIrpQuestionBankByIdStr(#qbankbidsid).questiontext)" escapeHtml="false" /></span>
								<s:set var="fulllengths" value="disposeFCLength(getIrpQuestionBankByIdStr(#qbankbidsid).questiontext)" ></s:set>
								<s:iterator value="#fulllengths" status="fulllengthsstatus" >
								<div style="margin-top:6px;">
									<div style="width: 20px;float: left;margin-left:20px;">(<s:property value="#fulllengthsstatus.count" />)</div>
									<div style="width: 20px;float: left;">
										<input type="text"  size="25" name="singfull<s:property value="#qbankbidsid" />"  />&nbsp;
											
										<input name="singfullnum<s:property value="#qbankbidsid" />" style="display: none;"  value="<s:property value="#fulllengthsstatus.index" />"  />
									</div>
									<div style="clear:both;"></div>
								</div>
								</s:iterator>
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
	</div>
	<div class="end" style="background:none;"><s:text name="page.foot.info" /></div>
</div>
</body>
</html>