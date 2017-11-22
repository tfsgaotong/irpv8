<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>问答</title>

<link rel="shortcut icon" href="<%=rootPath%>favicon.ico"
	type="image/x-icon" />
<link rel="bookmark" href="<%=rootPath%>favicon.ico" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/client/css/easyui.css" />
<link href="<%=rootPath%>client/images/normalize.css" rel="stylesheet"
	type="text/css" />
	<link href="<%=rootPath%>client/css/common1.css" rel="stylesheet"
	type="text/css" />
<link href="<%=rootPath%>client/css/oacss.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" href="<%=rootPath %>view//css/jquery.slideBox.css" type="text/css"></link>
<script src="<%=rootPath%>client/images/modernizr.custom.js"></script>
<script src="<%=rootPath%>client/images/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/modernizr.custom.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/easyui-lang-zh_CN.js"></script>
	
<script type="text/javascript" src="<%=rootPath%>client/js/questionjson/json2.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/jquery.slideBox.js"></script>
<jsp:include page="../include/client_skin.jsp" />
<style type="text/css">
body {
	behavior: url(<%=rootPath%>client/js/hover.htc);
}

.STYLE1 {
	color: #0000FF;
	font-weight: bold;
	font-size: 18px;
}

.STYLE1 a:hover {
	color: #0000FF;
	font-weight: bold;
	font-size: 18px;
}

.question {
	background: url(<%=rootPath%>client/images/ico08.gif);
	width: 640px;
	height: 46px;
	padding: 5px;
	overflow: hidden;
	margin-bottom: 5px;
}

.expert {
	display: inline-block;
	height: 24px;
	padding: 0 10px;
	background: #A7D2F3;
	line-height: 24px;
	color: #fff;
}
.wtgy {
    height: 28px;
    line-height:28px;
    border: 1px solid rgb(209, 209, 209);
    color: rgb(102, 102, 102);
    padding: 0px 5px;
}
.photos{
	clear:both;
	float: left;
	position: relative;
	display:block;
	width: 430px;
}
.photos li{
	float: left;
	padding: 2px;
	margin-right:3px;
    position: relative;
    transition: border-color 0.2s ease-out 0s;
}
#picView{
	width:300px;
	display:block;
	float: left;
	position: relative;
}
textarea{
resize: none;
}

ul.ztree {
	width:300px;
	height:350px;
	overflow:auto;
}
</style>
<style type="text/css">
.banner{position: relative;float: none;}
.banner .wd_tx{position: absolute;right:20px;top:20px;width: 252px;height: 158px;background: rgba(255,255,255,0.8);float: right;}
.banner .wd_tx .tx{width: 60px;height: 60px;border-radius: 30px;overflow: hidden;margin:6px auto 0 auto;}
.banner .bzrs{line-height: 40px;text-align: center;font-size: 14px;}
.banner .bzrs span{color:#2866a0;}
.banner .wd_tx li{width: 50%;height: 48px;background: #2866a0;float: left;text-align: center;padding-top:4px;color: #fff}
.banner .wd_wt{position: absolute;left: 0;bottom: 10px;width: 478px;height: 80px;background: rgba(0,0,0,0.4);padding:10px 20px;overflow: hidden;}
.banner .wd_wt a{font-size: 22px;color:#fff;line-height: 36px;}
.tui-banner{position:absolute;right:0;bottom:20px;width:414px;overflow:hidden;z-index:20}
.tui-banner ul{width:608px}
.tui-banner li{float:left;display:inline;margin-right:14px;position:relative;width: 190px;background: #f0f0f0}
.tui-banner li a{display:block;padding:4px 3px;height:72px;width:184px}
.tui-banner .bg-li{width:100%;height:100%;position:absolute;left:0;top:0;background:#fff;opacity:.9;filter:alpha(Opacity=90);z-index:-1}
.tui-banner li .gg-pic{margin-right:7px}
.tui-banner li p{width:100px;float: right;line-height:22px;padding-top:2px;color:#000;font-size: 14px;}
.zl3 a { font-size:15px; color:#383838; display:inline-block; padding:0px 12px; height:50px; overflow:hidden; line-height:54px;}
.zl3 a:hover { font-size:15px; color:#383838; display:inline-block; padding:0px 12px; height:47px; overflow:hidden; line-height:54px; border-bottom:3px #c9c9c9 solid;}
.zl3 a.over { font-size:15px; color:#383838; display:inline-block; padding:0px 12px; height:47px; overflow:hidden; line-height:54px; border-bottom:3px #448cc8 solid;}
.wd_right ul{padding-top:4px;}
<%-- .wd_right li{background: url(<%=rootPath%>client/images/dot_08.jpg) left center no-repeat;} --%>
.wd_right li a{width: 100%;display: block;line-height: 30px;font-size: 14px;text-overflow: ellipsis;white-space: nowrap;overflow: hidden;text-indent: 14px;}
.iask-brain-list{width:1029px;height:400px;overflow:hidden;position:relative}
.iask-brain{overflow:hidden;margin-left:-12px}
.iask-brain li{width:306px;float:left;display:inline}
.brain-item{margin:66px 12px 0;width:280px;border:1px solid #ece3d2;background-color:#fffbf3;position:relative;padding-top:76px}
.brain-img-con{width:128px;height:128px;display:block;position:absolute;left:50%;margin-left:-64px;background: url(../images/bg_brain_img.jpg) left bottom no-repeat;top:-66px;overflow:hidden}
.brain-img{position:absolute;width:120px;height:120px;top:50%;left:50%;border-radius:50%;overflow:hidden;margin:-60px 0 0 -60px}
.brain-img img{width:100%;height:100%}
.brain-info{padding:0 18px;line-height:24px;color:#333;font-size:14px}
.brain-info-txt{height:72px}
.brain-info p{overflow:hidden;white-space:nowrap;text-overflow:ellipsis;width:240px}
.brain-name{height:24px;line-height:24px;font-size:16px;color:#f08a68}
.c999{color:#999}
.btn-brain{width:100%;height:28px;line-height:28px;border-radius:14px;overflow:hidden;text-align:center;display:block;background-color:#f08a68;font-size:14px;color:#fff;margin-top:12px}
.btn-brain:hover{background-color:#f0987b;color:#fff}
.brain-question{margin-top:17px;background-color:#fff;padding:18px 18px 15px;font-size:14px;line-height:28px}
.brain-question p{white-space:nowrap;overflow:hidden;text-overflow:ellipsis}
.brain-question a{color:#333}
.brain-question a:hover{color:#ef5231}
.brain-img-con:hover .brain-img{width:122px;height:122px;border:3px solid #f25126;margin:-64px 0 0 -64px}
.wthz ul{width: 50%;float: left;padding-top:10px;}
<%-- .wthz li{background: url(<%=rootPath%>client/images/dot_08.jpg) left center no-repeat;} --%>
.wthz li a{width: 92%;display: block;line-height: 30px;font-size: 14px;text-overflow: ellipsis;white-space: nowrap;overflow: hidden;text-indent: 14px;}
</style>
<style type="text/css">
body{behavior:url(hover.htc);}
.searchSec .radios span {
margin-top: 0px;
}
.ui_content table td {
 border: 0px none rgb(209, 209, 209);
   
    }	
</style> 

  </head>
<body style="background: url()"   > 
<script language="JavaScript" type="text/javascript">
var addJsonFileList = new Array();
var textareanum = 0;
var searchQuestionDate = "";
jQuery(function($){


	$('#demo3').slideBox({

		duration : 0.3,//滚动持续时间，单位：秒

		easing : 'linear',//swing,linear//滚动特效

		delay : 5,//滚动延迟时间，单位：秒

		hideClickBar : false,//不自动隐藏点选按键

		clickBarRadius : 0

	});

	$('#demo4').slideBox({

		hideBottomBar : true//隐藏底栏

	});

});
$(function(){ 
	//加载个人基本信息
	myanswer();
	myquestion();
	hotQuestion();
	newestQuestion();
	userQuestion();
	connQuesCollect();
	config();
	$("#all").click();
});
//取配置信息
function config(){
	var str=$.ajax({
		type:'post',
		url:'<%=rootPath%>question/config.action',
		dataType: "json",
		async: false,
   		cache: false  
		}).responseText;
		$('#Num').html(str);
}
//我回答的 
function myanswer(){
	$.ajax({
		type:"post",
		cache:false,
		url:"<%=rootPath%>question/questionListofmineanswerindex.action",
		success:function(html){
			$('#myanswer').html(html);
		}
	});
}
//常见问题汇总
function connQuesCollect(){
$.ajax({
		type:"post",
		cache:false,
		url:"<%=rootPath%>question/connquescollectNew.action",
		success:function(html){
			$('#commonquesitoncollect').html(html);
		}
	});

}


//我的提问
function myquestion(){
	$.ajax({
		type:"post",
		cache:false,
		url:"<%=rootPath%>question/questionListofminequestionindex.action",
		success:function(html){
			$('#myquestion').html(html);
		}
	});
}
//热点问题
function hotQuestion(){
	$.ajax({
		type:"post",
		cache:false,
		url:"<%=rootPath%>question/hotQuestion.action",
		success:function(html){
			$('#hottalkdocument').html(html);
		}
	});
}
//最新问题
function newestQuestion(){
	$.ajax({
		type:"post",
		cache:false,
		url:"<%=rootPath%>question/newestQuestionNew.action",
		success:function(html){
			$('#youlovedocument').html(html);
		}
	});
}
//问答达人
function userQuestion(){
	$.ajax({
		type:"post",
		cache:false,
		url:"<%=rootPath%>question/userQuestionillnew.action",
		success:function(html){
			$('#sharedocument').html(html);
		}
	});
}
//删除问题
function delQuestion(questionid) {
	$.dialog.confirm('你确定要删除吗',function() {
		$.ajax({
			type : "POST",
			data : {
				questionid : questionid
			},
			url : '<%=rootPath%>question/delQuestion.action',
			dataType : "json",
			success : function(nMsg) {
					  if (nMsg = 1) {
						  	$("#questionDiv"+questionid).fadeOut();
					  } else {
					        $.dialog.tips('删除失败', 1,'32X32/fail.png');
					  }
			}
		});
	});
}

//分页
function page(_nPageNum){
    var _url=self.window.document.location.href;
	$('#pageNum').val(_nPageNum);
	var queryString = $('#pageForm').serialize();
	window.location.href=_url.substring(0,_url.indexOf("?"))+"?"+queryString;
}

//选择专家
function selectExpert(){
	//获得专家
	var result = $.ajax({
		url: '<%=rootPath%>expert/selectExpert.action',
		type:"post",
		dataType: "json",
	    async: false
	}).responseText;
	
	$.dialog({
		title:'选择专家',
		max:false,
		min:false,
		lock:true,
		resize: false,
		content:result,
		cancelVal:'关闭',
		okVal:'确定',
		cancel:function(){
			$("#checkBox").attr("checked",false);
			$("#selectedExpert").hide();
		},
		ok:function(){
			var expertId,expertName;
			var expertTab = $('.selectedExpert');
			if(expertTab){
				expertId = expertTab.attr('_eid');
				expertName = expertTab.attr('_ename');
			}
			if(expertName==undefined){
				$("#checkBox").attr("checked",false);
				$("#selectedExpert").hide();
			}else{
				$("#checkBox").attr("checked",true);
				$("#selectedExpert").text(expertName);
				$("#selectedExpertId").val(expertId);
			}
		}
	});
	$("#selectedExpert").show().addClass('expert');
}

//字数限制
function questionInfoControl(questionInfo,total){
	if(total-$.trim(questionInfo).length>=0){
		$('#errorQuestionNum').css({display:'none'});
		$('#textAreaNum').css({display:'block'});
		$('#questionNum').text(total-$.trim(questionInfo).length);
	}else{
		$('#textAreaNum').css({display:'none'});
		$('#errorQuestionNum').css({display:'block'});
		$('#errorNumCount').text(Math.abs(total-$.trim(questionInfo).length));
	}
}
//标题字数控制
function questionTitleInfoControl(questionInfo,total){
	if(total-$.trim(questionInfo).length>=0){
		$('#titleAcreaNumChaochu').css({display:'none'});
		$('#titleAcreaNumfont').css({display:'block'});
		$('#titleAcreaNumfontNum').text(total-$.trim(questionInfo).length);
	}else{
		$('#titleAcreaNumfont').css({display:'none'});
		$('#titleAcreaNumChaochu').css({display:'block'});
		$('#titleerrorNumCount').text(Math.abs(total-$.trim(questionInfo).length));
	}
}
//得到自己收藏数量
function mydocumentcountajax(){
			$.ajax({
				type:'post',
				url:'<%=rootPath%>site/mydocumentcount.action',
				success:function(msg){ 
					$('#mydocumentcount').html(msg);
				}
			});
}
//得到自己所有知识数量
function mydoccollectioncountajax(){
		$.ajax({
				type:'post',
				url:'<%=rootPath%>site/mydoccollectioncount.action',
				success:function(msg){ 
					$('#mydoccollectioncount').html(msg);
				}
			});		
}

/*处理tag事件 ;信息*/
function tabsSelected(liid){
	$('#allQuestions').find('a').each(function(){
		if(this.id ==liid.id){
			this.className="over";
		}else{
			this.className="";
		}
	});
	page(1);
}
/*page 处理方法*/
function page(pageNum){
	var liid = $('#allQuestions').find('.over');
	var sUrl = liid.attr("_href");
	var sData = liid.attr("_data");
	if(sData==''){
		sData = "pageNum="+pageNum;
	}else{
		sData += "&pageNum="+pageNum;
	}
	var sHtmlConn = $.ajax({ 
 		type:'post', 
 		url: sUrl,
 		data: sData,
 		dataType: "json",
		async: false,
	    cache: false  
 	}).responseText;
	$('#questionsAll').html(sHtmlConn);
}

//显示
function showpicno(picname,ispic){
	if(ispic==0){
	    $("#imgshow").attr("style","display: block");
	    $("#mypic").attr("src","<%=rootPath %>file/readfile.action?fileName="+picname);
	}
}

function hidepic(){
	$("#imgshow").attr("style","display: none");
}
//添加附件
function tosaveAttacthedafter(){  
	 var str=$.ajax({
			 type:'post',
			 dataType: "json",
			 url:'<%=rootPath %>site/client_to_save_attached.action?isqusertionordoc=1', 
			 async: false,
		 cache: false
		 }).responseText; 
	   $.dialog({
				title:'附件管理',
				max:false,
				min:false,
				lock:true,
				resize: false,  
				content:str,
				cancelVal:'确定', 
				cancel:function(){
				 if(addJsonFileList){  
		       		 var id=$('input:radio[name="editversions"]:checked').attr("id"); 
		       		 for(var i=0;i<addJsonFileList.length;i++){
		       			if(addJsonFileList[i].attfile==id){
		       				addJsonFileList[i].editversions=1; 
		       			}else{ 
		       				  if(addJsonFileList[i].editversions=="2"){//一种就是附件，
		       				  }else{
		       				    addJsonFileList[i].editversions=0;
		       				  }
		       			}
		     		  }
		     	   }  
				}  
		  });   
}

//提问
function publish(total) {
	var category = $('#cateIds').val();
	$('#questionpublishfb').attr("onclick","");
	var questiontitle = $('#questiontitle').val();
	var expertId = $('#selectedExpertId').val();
	var question_info = $('#question_info').val();
	if(questiontitle == "在此输入问题概要,150字以内"){
		questiontitle="";
	}
	if($.trim(questiontitle)=="" && $.trim(question_info) == ""){
		$.dialog.tips("标题或者内容必须填写一项",1.5,"alert.gif");
		$('#questionpublishfb').attr("onclick","publish(300)");
		return false;
	}else{
		if(questiontitle.length>150){
			$('#questionpublishfb').attr("onclick","publish(300)");
			$.dialog.tips("请填写150字以下的标题内容",1,"alert.gif");
			return false;
		}else if(question_info.length>total){
			$('#questionpublishfb').attr("onclick","publish(300)");
			$.dialog.tips("请填写"+total+"字以下的问题内容",1,"alert.gif");
			return false;
		}else{
			$.ajax({
				type : "POST",
				url : '<%=rootPath%>question/ask.action',
				dataType : "json",
				cache:false,
				async:false,
				data : {
					questionInfo : question_info,
					expertId : expertId,
					jsonFile : JSON.stringify(addJsonFileList),
					questiontitle : questiontitle,
					categories : category
				},
				success : function(nMsg) {
					$('#questiontitle').val("");
					$('#question_info').val("");
					if (nMsg = 1) {
						$.dialog.tips('提问成功',1,'32X32/succ.png');
					    config();
						var str=$.ajax({
							type:'post',
							url:'<%=rootPath%>question/questionList.action',
							dataType: "json",
							async: false,
					   		cache: false  
							}).responseText;
						
						if($('#all').attr("class")=="over" || $('#no').attr("class")=="over"){
							$('#questionsAll').html(str);	
							var treeObjr = $.fn.zTree.getZTreeObj("categoryTree");
							var treeObjl = $.fn.zTree.getZTreeObj("treeDemo");
							treeObjr.reAsyncChildNodes(null, "refresh",true);
							treeObjl.reAsyncChildNodes(null, "refresh",true);
						}
						//清空
						addJsonFileList.length=0;  
					} else {
						$.dialog.tips('提问失败', 1, '32X32/fail.png');
					}
					$('#questionpublishfb').attr("onclick","publish(300)");
				}
			});
		}
	}
}
/**
 *展开知识
 */
function showdocumentinfo(_docid){
	window.open('<%=rootPath %>site/showdocumentinfo.action?refrechstatus=1&docid='+_docid); 
}

//判断删除事件 鼠标移出
function questionDeleteJudgeOut(_questionid){
	var deletequestionid ="#deletefontquestion"+_questionid;
	$(deletequestionid).css({visibility:"hidden"});
}
//判断删除事件 鼠标移上
function questionDeleteJudge(_questionid){
	var deletequestionid ="#deletefontquestion"+_questionid;
	$(deletequestionid).css({visibility:"visible"});
}

//初始化右侧类别树
$(function(){ 
	var setting = {
			view: {
				showIcon: true
			},
			 data: {
                     simpleData : {
                         enable : !0,
                         idKey : "id",
                         pIdKey : "pId"
                    }
                },
			async: {
				enable: true,
				url:"<%=rootPath%>category/getAllCategory.action" 
			},
			  callback: {
				onClick: oncClick
			} 
		};
	$.fn.zTree.init($("#categoryTree"), setting);
});

	function oncClick(event, treeId, treeNode, clickFlag){
		if(treeNode){
			 var liid = $('#allQuestions').find('a');
			 if(liid.size()>0){
				 for(var i=0;i<liid.length;i++){
					 var obj = liid[i];
					 $(obj).attr("_data","id="+treeNode.id);
				 }
			 }
			 page(1);  
		 }
	}
	
	function gotocategory(_categoryId){
		var liid = $('#allQuestions').find('a');
		 if(liid.size()>0){
			 for(var i=0;i<liid.length;i++){
				 var obj = liid[i];
				 $(obj).attr("_data","id="+_categoryId);
			 }
		 }
		 page(1); 
	}
	
	/**
	 * 选择问题的分类
	 */
	function showLeftTree(){
		var categoryName = $('#cateIds').val();
		var result = $.ajax({
			url:"<%=rootPath%>category/getLeftCategory.action?categoryName="+categoryName,
			cache:false,
			async:false
		}).responseText;
		$.dialog({
	  		content:result,
	  		title:'选择分类',
	  		min:false,
	  		max:false,
		    lock:true,
		    ok:function(){
		    },
		    okVal:'确认',
//		    cancelVal: '取消',
//		    cancel: true,
		    padding: 0
	  	});
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
	<!-- <body onload="selected('questions')"> -->
	<!-- 分页参数开始 -->
	<form id="pageForm">
		<s:hidden name="pageNum" id="pageNum" />
		<s:hidden name="pageSize" id="pageSize" />
	</form>
	<!-- 分页参数结束 -->
	<div class="bg01">
		<!--头部菜单-->
		<jsp:include page="../../view/include/client_head.jsp" />
		<section class="mainBox">
	        <nav class="privateNav" style="background:url(../images/bg-04.gif) repeat-x">
	            <dl>
	                <dt class="leftBox">全部问答分类</dt>
	                <dd class="clear"></dd>
	            </dl>
	        </nav>
    	</section>
		<!--头部菜单end-->
		<section class="mainBox">
	 	<!-- 左侧区域 -->
        <section class="layoutLeft">
        	<!-- 分类列表 -->
            <nav class="allBtns">
                <ul>
                    <s:iterator value="listCategory">
                        <li>
                            <a href="<s:url namespace="/question" action="toquecategory"><s:param name="categoryId" value="categoryid" /></s:url>">
                                <s:property value="cdesc" />
				            </a>
				            <s:set var="childCate" value="findChildCategoryByParentId(categoryid)" /> 
				            <s:if test="#childCate!=null && #childCate.size()>0">
                                <div class="moreBtns">
                                    <div class="line">
                                        <s:iterator value="#childCate">
                                            <h1>
                                                <a href="<s:url namespace="/question" action="toquecategory"><s:param name="categoryId" value="categoryid" /></s:url>" title="<s:property value="chnldesc" />">
                                                    <s:property value="cdesc" />
                                                </a>
									        </h1>
									        <s:set var="grandsonCate" value="findChildCategoryByParentId(categoryid)" />
									        <s:if test="#grandsonCate!=null && #grandsonCate.size()>0">
										        <s:iterator value="#grandsonCate">
											        <P>
												        <a href="<s:url namespace="/question" action="toquecategory"><s:param name="categoryId" value="categoryid" /></s:url>" title="<s:property value="chnldesc" />">
												            <s:property value="cdesc" />
												        </a>
											        </P>
										        </s:iterator>
									        </s:if>
								        </s:iterator>
							        </div>
						        </div>
					        </s:if>
					    </li>
			        </s:iterator>
		        </ul>
		    </nav>
		    <div class="wd_right" style="width:245px;float: left; margin-top: 10px;">
		    <div class="title1"><h4 style="padding-left: 15px;"><a href="javascript:void(0)">我提问的</a></h4></div>
            <ul id="myquestion">
            </ul>
            <div class="title1"><h4 style="padding-left: 15px;"><a href="javascript:void(0)">我回答的</a></h4></div>
            <ul id="myanswer">
            </ul>
            </div>
          </section>
    	  <section class="layoutMiddle1">
    	  
	    	  <div class="banner">
	        
	          <!-- 轮播 -->
	            
	        <div id="slide" class="slideBox" >
				<ul class="items" >
				 <s:iterator value="chnlDocLinks51" status="wt">
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
    	  
	        <div style="float: left;width: 636px;"> 
				<h1 class="zl3" id="allQuestions" style="font-size:18px; font-weight:normal;line-height:50px; border-bottom:1px  solid #efefef;text-align:left; font-family:&#39;微软雅黑&#39;; margin:0 0 20px 5px; color:#121212;">
					<a id="all" href="javascript:void(0)" _href="<%=rootPath%>question/questionList.action?&isPage=0" class="over" _data="" onclick="tabsSelected(this)"><font class="linkbh14">全部</font></a>
				   	<a id="no" href="javascript:void(0)"  _href="<%=rootPath%>question/questionListOfNoResoule.action?&isPage=0" _data="" onclick="tabsSelected(this)"> <font class="linkbh14">待解决</font></a>
					<a id="yes" href="javascript:void(0)" _href="<%=rootPath%>question/questionListOfResoule.action?&isPage=0" _data="" onclick="tabsSelected(this)"> <font class="linkbh14">已解决</font></a>
					<a id="commonquestion" href="javascript:void(0)" _href="<%=rootPath%>question/connquescollectNew.action?&isPage=0" _data="" onclick="tabsSelected(this)"> <font class="linkbh14">常见问题</font></a>
					<!-- 
					<a id="minequestion" href="javascript:void(0)" _href="<%=rootPath%>question/questionListofminequestion.action" _data="" onclick="tabsSelected(this)"> <font class="linkbh14">我的提问</font></a>
					<a id="mineanswer" href="javascript:void(0)" _href="<%=rootPath%>question/questionListofmineanswer.action" _data="" onclick="tabsSelected(this)"> <font class="linkbh14">我回答的</font></a>
					<s:if test="@com.tfs.irp.util.LoginUtil@getLoginUser().isExpert()">
					<a id="minenoanswer" href="javascript:void(0)" _href="<%=rootPath%>expert/questionToExpert.action" _data="" onclick="tabsSelected(this)"> <font class="linkbh14">待解答</font></a>
					</s:if>		
					-->			
				</h1>
				<div  id="questionsAll" class="fyh" style="width:636px;overflow: hidden;margin:0">
					<!-- 问题主题开始 -->
					<!-- 问题主题结束-->
				</div>
	        </div>
	         
	        <div class="wd_right" style="width:250px;float: right;">
	        	<input value="创建问答" onclick="window.open('<%=rootPath %>question/linkeditsubmit.action')" style="background: url(../images/icon-02.png) no-repeat 9px center #62a53b;margin:10px 0 10px 0px;border-radius:5px; font-size:18px; width: 250px;height:65px;color: white;background-color: #62a53b;" type="button"  >
	        	<div class="title1"><h4><a href="javascript:void(0)">最热回答</a></h4></div>
	            <ul id="hottalkdocument">
	            </ul>
	            <div class="title1"><h4><a href="javascript:void(0)">最新问答</a></h4></div>
	            <ul id="youlovedocument">
	            </ul>
	        </div>
			 
	        <div style="clear: both;"></div>
	
	        <div style="margin:0 0 0 0px;">
	            <div class="title1"><h4><a href="#">问答达人</a></h4></div>
	            <div class="iask-brain-list" style="height: auto;">
	                <ul id="sharedocument" class="iask-brain swiper-wrapper cf">
	                  
	                    
	                </ul>
	            </div>
	        </div>
	        <div style="height: 20px;"></div>
			<!-- 
	        <div class="wthz">
	            <div class="title1"><h4><a href="#">常见问题汇总</a></h4></div>
	            <div id="commonquesitoncollect"></div>
	           <ul></ul>
	        </div>
	         -->
    </section>
    
</section>
	
			<!--右侧内容结束-->
			<!--尾部信息-->
			<s:include value="../include/client_foot.jsp" />
			<!--尾部信息end-->
	</div>
</body>
</html>