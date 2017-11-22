<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>考试</title>
<link rel="shortcut icon" href="<%=rootPath%>favicon.ico"
	type="image/x-icon" />
<link rel="bookmark" href="<%=rootPath%>favicon.ico" />
<link href="<%=rootPath%>client/images/normalize.css" rel="stylesheet"
	type="text/css" />
	<link href="<%=rootPath%>client/css/common1.css" rel="stylesheet"
	type="text/css" />
<link href="<%=rootPath%>client/css/oacss.css" rel="stylesheet"
	type="text/css" />
	<link href="<%=rootPath%>client/css/examlist.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
.allBtns ul li{
	margin-top:0;
}
</style>
<link rel="stylesheet" href="<%=rootPath %>view//css/jquery.slideBox.css" type="text/css"></link>
<script src="<%=rootPath%>client/images/modernizr.custom.js"></script>
<script src="<%=rootPath%>client/images/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/modernizr.custom.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="<%=rootPath%>view/js/jquery.slideBox.js"></script>
<jsp:include page="../include/client_skin.jsp" />
<script type="text/javascript">
$(function(){
	//选择头部标签
	
});
/**
* 进入到试卷内部
*/
function linkPaperContent(_paperid){
	window.open("<%=rootPath%>exam/linksingleques.action?paper="+_paperid);
}
/**
* 改变状态
*/
function changeY(_this){
	if(_this.id=="nocanexam"){
		window.location.href="<%=rootPath%>exam/exammenu.action?pagenumpaper=1";
	}else if(_this.id=="yescanexam"){
		window.location.href="<%=rootPath%>exam/alreadyexammenu.action?pagenumpaper=1";		
	}	
}
/**
* 参加考试
*/
function visitExam(_content,_examid){
	$.ajax({
		type:'post',
		url:"<%=rootPath%>exam/joinexam.action",
		async:false,
		cache:false,
		data:{exam:_examid},
		success:function(content){
			var cons = eval(content);
			var time = parseInt(cons[0].exam.begintime);
			var systime = new Date();
			if(parseInt(time)>parseInt(systime.getTime())){
				$.dialog.tips("考试时间未到！",1,"alert.gif");	
			}else{
				if(_content!=undefined){
					window.open("<%=rootPath%>exam/linksingleques.action?paper="+_content+"&exam="+_examid);
				}
			}
		},
		error:function(e){
			
		}
	});
	
}
/**
* 前台待参加考试分页
*/
function pageEMenu(_pages){
	$.ajax({
		type:"post",
		url:"<%=rootPath%>exam/pageexam.action?exammenunum="+_pages,
		data:{
		},
		async:false,
		cache:false,
		success:function(data){
			$('.examlist').html(data);
		}
	});
}
/**
* 前台已参加考试分页
*/
function pageAlreadyeMenu(_pages){
	$.ajax({
		type:"post",
		url:"<%=rootPath%>exam/alreadypageexam.action?exammenunum="+_pages,
		data:{
		},
		async:false,
		cache:false,
		success:function(data){
			$('.leftmenu').html(data);
		}
	});
}
/**
*查看详情
*/
function searchDetail(_examid,_testpaperid,_recordid){
	window.open("<%=rootPath%>exam/linksinglequesalready.action?paper="+_testpaperid+"&exam="+_examid+"&recordid="+_recordid);
}

jQuery(function($){


	$('#slide').slideBox({

		duration : 0.1,//滚动持续时间，单位：秒

		easing : 'linear',//swing,linear//滚动特效

		delay : 5,//滚动延迟时间，单位：秒

		hideClickBar : false,//不自动隐藏点选按键

		clickBarRadius : 0

	});
});

</script>
</head>

<body onload="selected('expert')" style="background: url()">
	<div class="bg01">
		<jsp:include page="../../view/include/client_head.jsp" />
		<section class="mainBox"> 
			<nav class="privateNav" style="background:url(../images/bg-04.gif) repeat-x">
				<dl>
	                <dt class="leftBox">全部考试分类</dt>
	                <dd class="leftBox navBtns">
	                </dd>
	                <dd class="clear"></dd>
	            </dl>
			</nav> 
		</section>
		<section class="mainBox"></section> 
		<div class="main" style="background-image: url('');width:1200px; box-sizing: border-box;">
		<div style="" class="leftmenu" style="">
			<nav class="allBtns">
                <ul>
                	<li>待参加的考试</li>
                	<li>参加过的考试</li>
                </ul>
            </nav>
		<div class="title" style="margin-top: 10px;">
			<h1 style="padding-left: 10px;"><a style="border-bottom: solid 2px #186fd2;padding-bottom:6px;">参加过的考试</a></h1>
		</div>
			<ul>
				<s:iterator value="examrecordlist" >
					<li>
						<div>
							<div class="leftexamimg" style="background:url('<%=rootPath %>/client/images/paper.jpg') no-repeat;background-size:100% 100%;font-size:15px;">
								 <p style="height:30px;line-height:60px;text-align:center;"><s:property value="examgrade" /></p>
								 <p style="font-size:13px;height:40px;line-height:40px;text-align:center;"><s:if test="examstatus==10">通过</s:if><s:elseif test="examstatus==20">未通过</s:elseif></p>
							</div>	
							<div class="leftexaminfo">
								<p style="margin-top:0"><span><span>考试名称: </span><s:if test="getIrpExamByExamId(examid).examname.length>3"><s:property value="getIrpExamByExamId(examid).examname.substring(0,3)" />...</s:if><s:else><s:property value="getIrpExamByExamId(examid).examname" /></s:else></span><a style="cursor:pointer;color:#225491;margin-left: 5px" onclick="searchDetail(<s:property value="examid" />,<s:property value="getIrpExamByExamId(examid).examcontent" />,<s:property value="recordid" />)" >[查看详情]</a></p>
								<p><span>开始时间：</span><span><s:date name="getIrpExamByExamId(examid).begintime" format="yyyy-MM-dd HH:mm" /></span></p>
								<span>结束时间：</span><span><s:date name="getIrpExamByExamId(examid).endtime" format="yyyy-MM-dd HH:mm" /></span>
								<p><span>考试分数:</span><span><s:property value="examgrade" /></span><span>分</span></p>
								<span>考试结果:</span><span><s:if test="examstatus==10">通过</s:if><s:elseif test="examstatus==20">未通过</s:elseif></span>
							</div>
						</div>
						<div style="clear: both"></div>
					</li>
				</s:iterator>
				
			</ul>
	</div>
	<div class="layoutMiddle1">
		<div class="banner">
        
		          <!-- 轮播 -->
		            
		        <div id="slide" class="slideBox" >
				<ul class="items" >
				 <s:iterator value="chnlDocLinks15" status="wt">
			       <s:if test="#wt.index<4">
			         <s:set var="document" value="getIrpDocumentById(docid)" />
			        	 <s:if test="#document.urladdress!='' && #document.urladdress!=null">
							<li>
								<a href="<s:property value='#document.urladdress'/>" target="_blank">
									<img src="<s:property value='docCoverPathSource(docid,docflag)' />" width="935" height="400"/>
									<p class="img_title"><s:property value='doctitle'/></p>
								</a>
							</li>
						</s:if>
						<s:else>
							<li>
								<a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>"  target="_blank">
									<img src="<s:property value='docCoverPathSource(docid,docflag)' />" width="935" height="400"/>
									<p class="img_title"><s:property value='doctitle'/></p>
								</a>
								</li>
						</s:else>
					</s:if>
				</s:iterator>
				</ul>
			</div>    
		    <!-- /轮播 -->

        </div>
		<div class="title1">
			<div style="font-size:15px;color:#171717;"><a style="border-bottom: solid 2px #186fd2;padding-bottom:2px;">待参加考试</a></div>
		</div>
		<div class="examlist">
		<ul class="clearfix">
			<s:iterator value="examlist" >
				<li>
					<div>
						<div class="examimg">
							<img src="<%=rootPath %>/client/images/testpaper.jpg" alt="">
						</div>
						<div class="examinfo">
							<p style="margin-top:0"><span>考试名称: </span><span><s:property value="examname" /></span></p>
							<p><span>开始时间：</span><span><s:date name="begintime" format="yyyy-MM-dd HH:mm" /></span></p>
							<p><span>结束时间：</span><span><s:date name="endtime" format="yyyy-MM-dd HH:mm" /></span></p>
							<span>考试时长:</span><span><s:property value="answertime" /></span><span>分钟</span>
							<div id="joinexam" onclick="visitExam(<s:property value="examcontent" />,<s:property value="examid" />)" style="cursor:pointer;margin-top:10px;background-color:#2061b0;color:#fff;width:70px;text-align:center;border-radius:5px;">
								参加考试
							</div>
						</div>
					</div>
					<div style="clear: both"></div>
				</li>
			</s:iterator>
			<div>
			<div class="left">
				<div class="fyh">
					<ul style="width:880px">
					<s:property value="pagemenuhtmlexam" escapeHtml="false" />
					</ul>
				</div>
			</div>	
			</div>
		</ul>
		</div>
		</div>
		</div>
		</div>
		<jsp:include page="../include/client_foot.jsp" />
</body>
</html>