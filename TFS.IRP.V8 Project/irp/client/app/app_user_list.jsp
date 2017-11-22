<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<title>我的工具箱</title>

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
<jsp:include page="../include/client_skin.jsp" />
<style type="text/css">
body{
	behavior:url(js/hover.htc);
	background:none;
}
.STYLE1 {
	color: #0000FF;
	font-weight: bold;
	font-size:18px;
}
.STYLE1 a:hover{
	color: #0000FF;
	font-weight: bold;
	font-size:18px;
}

body {
	behavior: url(hover.htc);
}

.cardList {
	width: 930px;
}

.cardList li {
	float: left;
	display: inline;
	margin: 10px 20px;
	position: relative;
}

.cardList li table {
	background: #fff;
}

.cardList li .darkSh {
	background: #ccc;
	position: absolute;
	left: 4px;
	right: -4px;
	top: 4px;
	bottom: -4px;
	z-index: -1;
}
</style>
</head>
<body onload="selected('appa')">
<script type="text/javascript">
$(function(){
	//正在热议
	hottalkdocument();
	//猜你喜欢
	youlovedocument();
	//分享排行
	sharedocument();
	//人气TOP
	hotpersondocument(); 
	$.ajax({
		type:"post",
		url:"<%=rootPath%>menu/select_usernotappi.action?pageNum=1",
		beforeSend :function(xmlHttp){
			xmlHttp.setRequestHeader("If-Modified-Since","0");
			xmlHttp.setRequestHeader("Cache-Control","no-cache");
	     } ,
		success:function(html){
			if(html!=null){
				$('#commentmaindiv').html(html);
			}else{
				$.dialog.tips('获取安装应用列表失败',1,'32X32/fail.png');
			}
		}
	});

});
/**
 * 链接到主题
 */
function getInfoTopicPage(_info){

	/*判断此话题是否通过审核*/
	var linkstatu = $.ajax({
    	type:"post",
    	url:"<%=rootPath%>microblog/booleanaudit.action",
    	data:{
    		topicname:_info
    	},
    	cache:false,
    	async:false
    	 }).responseText;

if(linkstatu==10){
	$.dialog.alert('此专题尚未通过管理员审核',function(){});
	return false;
}

    var status = $.ajax({
    	type:"post",
    	url:"<%=rootPath%>microblog/searchtopicnum.action",
    	data:{
    		topicname:_info
    	},
    	cache:false,
    	async:false
    	 }).responseText;
	if(status>=1){
		window.location.href="<%=rootPath%>microblog/searchTopic.action?topicname="+_info;	
	}else{
		$.dialog.alert('此专题已被管理员删除',function(){});
	}
	return true;
}
 
 
//猜你喜欢
function youlovedocument(){
	var str=$.ajax({
		type:'post',
		url:'<%=rootPath%>site/guesslovedocument.action',
		dataType: "json",
		async: false,
   		cache: false  
		}).responseText; 
		$('#youlovedocument').html(str);
}
//人气TOP
function hotpersondocument(){
	var str=$.ajax({
		type:'post',
		url:'<%=rootPath%>site/hothumandocument.action',
		dataType: "json",
		async: false,
   		cache: false  
		}).responseText;
		$('#hotpersondocument').html(str);
}
//正在热议
function hottalkdocument(){
	var str=$.ajax({
		type:'post',
		url:'<%=rootPath%>site/hottalkdocument.action',
		dataType: "json",
		async: false,
   		cache: false  
		}).responseText;
		$('#hottalkdocument').html(str);
}
//分享排行
function sharedocument(){
	var str=$.ajax({
		type:'post',
		url:'<%=rootPath%>site/sharedocument.action',
		dataType: "json",
		async: false,
   		cache: false  
		}).responseText;
		$('#sharedocument').html(str);
} 


//构建微知个人卡片 鼠标移动到上面
function personalCard(_userid,_commentid){
  var personalCard = $("#"+_commentid+"div");
  if(personalCard && !personalCard.attr("binit")){
	  $.ajax({
		  type:"POST",
		  url:"<%=rootPath%>microblog/searchPersonalCard.action",
		  data:{
			  userid:_userid,
			  microblogid:_commentid
		  },
		  success:function(html){
			  personalCard.prepend(html);
			  personalCard.attr("binit", false);
		  },
		  error:function(){
			  $.dialog.tips('加载好友卡片失败',1,'32X32/fail.png');
		  }
	  });
  }else{
	  $('#microblogCard'+_commentid).css({display:""});
  }
  
}
//构建微知个人卡片  鼠标从头像上移走
function personalCardOut(_commentid){
	var personalcardout ='#microblogCard'+_commentid;
	$(personalcardout).css({
		display:"none"
	});
}

/*我安装的应用*/
function commentReceive(){
	$("#receiveCommentid").addClass("over") 
    $("#sendCommentid").removeClass("over");
	$(".gt_removeclass").remove();
	$('#commentmaindiv').empty();
	$.ajax({
		type:"post",
		url:"<%=rootPath%>menu/select_userappisdisplay.action?appstatus=0&isdisplay=0&pageNum=1",
		beforeSend :function(xmlHttp){
			xmlHttp.setRequestHeader("If-Modified-Since","0");
			xmlHttp.setRequestHeader("Cache-Control","no-cache");
	     } ,
		success:function(html){
			if(html!=null){
				$('#commentmaindiv').html(html);
			}else{
				$.dialog.tips('获取我的应用失败',1,'32X32/fail.png');
			}
		}
	});
	
} 
/*查看没有安装的应用*/
function commentSend(){
	$("#sendCommentid").addClass("over") 
    $("#receiveCommentid").removeClass("over");
	$(".gt_removeclass").remove();
	$('#commentmaindiv').empty();
	$.ajax({
		type:"post",
		url:"<%=rootPath%>menu/select_usernotappi.action?appstatus=0&isdisplay=1&pageNum=1",
		beforeSend :function(xmlHttp){
			xmlHttp.setRequestHeader("If-Modified-Since","0");
			xmlHttp.setRequestHeader("Cache-Control","no-cache");
	     } ,
		success:function(html){
			if(html!=null){
				$('#commentmaindiv').html(html);
			}else{
				$.dialog.tips('获取安装列表失败',1,'32X32/fail.png');
			}
		}
	});
	
}
//修改
function updatedisplay(appid){
	var value=$.trim($("#statusa").text());
	var isdis="";
	if(value=="安装"){
		isdis=0;
	}else{
		isdis=1;
	}
	$.ajax({
		 url: "<%=rootPath%>menu/update_appdisplay.action",
		 cache: false,
		 data: "applistid="+appid+"&isdisplay="+isdis,
		 success: function(html){
			 $.dialog.tips(value+'成功',1,"32X32/succ.png");
			 window.location.reload(); 
		  }
	});
}
/*查看安装分页*/
   function page(_nPageNum){
	   $('#pageNum').val(_nPageNum);
		//判断选中评论模式
		if($("#receiveCommentid").css("color")!="rgb(85, 85, 85)"){
			$('#commentmaindiv').empty();	
			var queryString = $('#pageForm').serialize();
			$.ajax({
				type:"post",
				url:"<%=rootPath%>menu/select_userappisdisplay.action?"+queryString,
				success:function(html){
					if(html!=null){
						$('#commentmaindiv').html(html);
					}else{
						$.dialog.tips('获取评论列表失败',1,'32X32/fail.png');
					}
				}
			});	
		}else{
			$('#commentmaindiv').empty();	
			var queryString = $('#pageForm').serialize();
			$.ajax({
				type:"post",
				url:"<%=rootPath%>menu/select_usernotappi.action?"+queryString,
				success:function(html){
					if(html!=null){
						$('#commentmaindiv').html(html);
					}else{
						$.dialog.tips('获取评论列表失败',1,'32X32/fail.png');
					}
				}
			});
			
		}
	}
	
   /*
    * 查看某个图片的大图
    */
    function checkMaxPic(_src,_name,_ids){
      	$.dialog({
      		title:"查看图片",
      		content: 'url:<%=rootPath%>microblog/checkMaxPic.action?picfile='+_src+'&picfilenamelist='+_name+'&picfileids='+_ids,
      		cache:false,
      		cancelVal: '关闭',
      	    cancel: true,
      		max: false,
      	    min: false,
      	    lock:true,
      	    width:650,
      	    height:500
      	});
      }		
 //查看文档
	function documentinfo_see(_docid){ 
		window.open('<%=rootPath %>document/document_detail.action?docid='+_docid);  
	}
   	
</script>
	<form id="pageForm">
		<s:hidden name="pageNum" id="pageNum" />
		<s:hidden name="pageSize" id="pageSize"/>
	</form>
  <!--头部菜单
  
  <jsp:include page="../include/client_head.jsp" />
  -->
  <jsp:include page="../../view/include/client_head.jsp" />
	<section class="mainBox"> 
			<nav class="privateNav" style="background:url(../images/bg-04.gif) repeat-x">
				<dl>
	                <dt class="leftBox">工具箱分类</dt>
	                <dd class="leftBox navBtns">
	                </dd>
	                <dd class="clear"></dd>
	            </dl>
			</nav> 
		</section>
		<div class="main" style="background-image: url('');width:1200px; box-sizing: border-box;">
			<section class="layoutLeft">
				<nav class="allBtns">
	                <ul>
	                	<li id="receivemessage" onclick="commentSend()" style="cursor:pointer">可安装的应用</li>
	                	<li id="receiveCommentid" onclick="commentReceive()" style="cursor:pointer">已安装的应用</li>
	                </ul>
	            </nav>
			
			</section>
			<div class="layoutMiddle1" style="margin: 20px 0 0 0px;width: 940px;">
    			<div class="fyh" id="commentmaindiv">  </div>
			</div>
		</div>
<!-- <div class="main">
左侧内容
<div class="left">
	<!-- 
	<h1 class="zl3" style=" font-weight:normal;  line-height:50px; border-bottom:1px  solid #efefef;text-align:left; font-family:微软雅黑; margin:0 0 20px 5px;">
		<a id="sendCommentid" class="over" style="color:rgb(242, 130, 30);"  id="receivemessage"  href="javascript:void(0)" onclick="commentSend()"><font class="linkbh14" >可安装的应用</font></a>&nbsp;&nbsp;
		<a id="receiveCommentid"  href="javascript:void(0)" onclick="commentReceive()"><font class="linkbh14">已安装的应用</font></a>
	</h1>
    <div class="fyh" style="margin-top: 20px;" id="commentmaindiv">  </div>
     
</div>
 <div class="right">
	<div class="duo">
		<%--正在热议 --%>
		<dl id="hottalkdocument"></dl> 
		<%--猜你喜欢--%> 
        <dl id="youlovedocument"></dl> 
        <%--分享排行 --%>
        <dl class="bz" id="sharedocument"></dl>
        <%--人气TOP10 --%>
        <dl class="pw" id="hotpersondocument"></dl>
    </div>
</div>
   <jsp:include page="../include/client_foot.jsp" />
</div>-->
<!--左侧内容结束-->
 <jsp:include page="../include/client_foot.jsp" />
</body>
</html>