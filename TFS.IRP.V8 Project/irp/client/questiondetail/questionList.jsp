<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>问答</title>
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/client/css/easyui.css" />
<jsp:include page="../include/client_skin.jsp" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/client/css/icon.css" />
<link href="<%=rootPath%>/client/css/oacss.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/client/css/easyui.css" />
<link rel="stylesheet" href="<%=rootPath%>client/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css" />
 <link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css"/>
<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css"/>
 <script type="text/javascript" src="<%=rootPath%>client/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/easyui-lang-zh_CN.js"></script>
	
<script type="text/javascript" src="<%=rootPath%>client/js/questionjson/json2.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.excheck-3.5.js"></script>

	
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
body{behavior:url(hover.htc);}
.searchSec .radios span {
margin-top: 0px;
}
.ui_content table td {
 border: 0px none rgb(209, 209, 209);
   
    }	
</style> 

  </head>
<body style="background: url()"> 
<script language="JavaScript" type="text/javascript">
var addJsonFileList = new Array();
var textareanum = 0;
var searchQuestionDate = "";

$(function(){ 
	//加载个人基本信息
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
//最新问答 
function newestQuestion(){
	$.ajax({
		type:"post",
		cache:false,
		url:"<%=rootPath%>question/newestQuestion.action",
		success:function(html){
			$('#hottalkdocument').html(html);
		}
	});
}
//常见问题汇总
function connQuesCollect(){
$.ajax({
		type:"post",
		cache:false,
		url:"<%=rootPath%>question/connquescollect.action",
		success:function(html){
			$('#commonquesitoncollect').html(html);
		}
	});

}


//最热问答
function hotQuestion(){
	$.ajax({
		type:"post",
		cache:false,
		url:"<%=rootPath%>question/hotQuestion.action",
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
		url:"<%=rootPath%>question/userQuestionill.action",
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

</script>
	<!-- <body onload="selected('questions')"> -->
	<!-- 分页参数开始 -->
	<form id="pageForm">
		<s:hidden name="pageNum" id="pageNum" />
		<s:hidden name="pageSize" id="pageSize" />
	</form>
	<!-- 分页参数结束 -->
	<div class="bg01">
		<!--头部菜单
		<jsp:include page="../include/client_head.jsp" />
		-->
		<jsp:include page="../../view/include/client_head.jsp" />
		   <section class="mainBox">
			<nav class="privateNav">
			</nav>
			</section>
		<!--头部菜单end-->
		<div class="main">
			<!--左侧内容-->
			<div class="left">
				<div class="shuru" id="Num"></div>
				<h1 class="zl3" id="allQuestions"
					style="font-size:18px; font-weight:normal; 
					line-height:50px; border-bottom:1px  solid #efefef;
					text-align:left; font-family:&#39;微软雅黑&#39;; margin:0 0 20px 5px; color:#121212;">
					<a id="all" href="javascript:void(0)"
						_href="<%=rootPath%>question/questionList.action" class="over" _data=""
						onclick="tabsSelected(this)"><font class="linkbh14">全部</font></a>
				   <a id="no"
						 href="javascript:void(0)"
						_href="<%=rootPath%>question/questionListOfNoResoule.action" _data=""
						onclick="tabsSelected(this)"> <font class="linkbh14">待解决</font>
					</a>
					 <a id="yes" href="javascript:void(0)"
						_href="<%=rootPath%>question/questionListOfResoule.action" _data=""
						onclick="tabsSelected(this)"> <font class="linkbh14">已解决</font>
					</a>
					 <a id="minequestion" href="javascript:void(0)"
						_href="<%=rootPath%>question/questionListofminequestion.action" _data=""
						onclick="tabsSelected(this)"> <font class="linkbh14">我的提问</font>
					</a>
					 <a id="mineanswer" href="javascript:void(0)"
						_href="<%=rootPath%>question/questionListofmineanswer.action" _data=""
						onclick="tabsSelected(this)"> <font class="linkbh14">我回答的</font>
					</a>
					<s:if test="@com.tfs.irp.util.LoginUtil@getLoginUser().isExpert()">
					<a id="minenoanswer" href="javascript:void(0)"
						_href="<%=rootPath%>expert/questionToExpert.action" _data=""
						onclick="tabsSelected(this)"> <font class="linkbh14">待解答</font>
					</a>
					</s:if>					
				</h1>
				<div class="fyh" id="questionsAll">
					<!-- 问题主题开始 -->
					<!-- 问题主题结束-->
				</div>
			</div>
			<!--左侧内容结束-->

			<!--右侧内容-->
			<div class="right">
				<div class="duo">
					<%--分类问答 --%>
					<dl style="margin-top:40px;">
						<span class="linkbh14" style="font-weight:bold;cursor:pointer;">分类问答</span><a onclick="javascript:window.location.reload()" style="font-size:12px;margin-left: 15px;" href="javascript:void(0)">刷新</a>
						<ul id="categoryTree" class="ztree" style="background-color: white;height: 100%;border: none;overflow-y:auto;"></ul>
					</dl>
					<%--最新问答 --%>
					<dl id="hottalkdocument"></dl>
					<%--最热问答--%>
					<dl id="youlovedocument"></dl>
					<%--常见问题汇总--%>
					<dl id="commonquesitoncollect"></dl>
					<%--问答达人 --%>
					<dl class="bz" id="sharedocument"></dl>
				</div>
			</div>
			<!--右侧内容结束-->
			<!--尾部信息-->
			<s:include value="../include/client_foot.jsp" />
			<!--尾部信息end-->
		</div>
	</div>
</body>
</html>