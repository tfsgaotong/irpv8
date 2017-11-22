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
<title>查看评论</title>
<link rel="Bookmark" href="images/24pinico.ico" />
<link rel="Shortcut Icon" href="images/24pinico.ico" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/client/css/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/client/css/icon.css" />
<link href="<%=rootPath%>/client/css/oacss.css" rel="stylesheet" type="text/css" />
<jsp:include page="../include/client_skin.jsp" />
<style type="text/css">
body{
	behavior:url(js/hover.htc);
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
</style>
<script type="text/javascript" src="<%=rootPath%>/client/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/client/js/easyui-lang-zh_CN.js"></script>
</head>
<body>
<script type="text/javascript">
$(function(){
	//加载个人基本信息
	$.ajax({
		type:"get",
		cache:false,
		url:"<%=rootPath%>microblog/loadUserInfo.action",
		success:function(callback){
			$('.right').prepend(callback);
		}
	});
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
		url:"<%=rootPath%>microblog/microblogCommentView.action",
		data:{
			pageNum:1
		},
		cache:false,
		async:false,
		success:function(html){
			if(html!=null){
				$('#commentmaindiv').html(html);
			}else{
				$.dialog.tips('获取评论列表失败',1,'32X32/fail.png');
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
		window.open("<%=rootPath%>microblog/searchTopic.action?topicname="+_info);
	}else{
		$.dialog.alert('此专题已被管理员删除',function(){});
	}
	
	
	return true;
}
function tabs(_lidom){
	return false;
}
function tabsPersonalLink(){
	return false;
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

//删除给我的评论
function deleteMicroblogCommentWiteMe(_commentid,_microblogid){
	var commentdiv = "#"+_commentid+"div";
	$.dialog.confirm('你确定要删除这条微知吗',function(){
		$.ajax({
			type:"POST",
			url:"<%=rootPath%>microblog/microblogCommentDeleteWithMe.action",
			data:{
				commentid:_commentid,
				replaymicroblogid:_microblogid
			},
			cache:false,
			async:false,
			success:function(_msg){
				if(_msg==1){
					$(commentdiv).fadeOut();
					$(commentdiv).fadeOut("slow");
					$(commentdiv).fadeOut(3000);
				}else{
					$.dialog.tips('删除失败',1,'32X32/fail.png');	
				}
				
			},
			error:function(){
				$.dialog.tips('删除失败',1,'32X32/fail.png');
					return false;
			}
		});
	});

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
		window.open('<%=rootPath %>site/client_documentinfo.action?refrechstatus=1&docid='+_docid);  
	}
 
 
	function tabcomment(_a){
		$('#commentdivcomment').find('a').each(function(){
			if(this.id ==_a){
				this.className="over";
				
			}else{
				this.className="";
			}
			
		});
		page(1);
	}
	
	function page(pageNum){
	 	
	 	var liDom = $('#commentdivcomment').find('.over');
	 	var sUrl = liDom.attr("_href");
	 	var sData = liDom.attr("_data");
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
	 	$('#commentmaindiv').html(sHtmlConn);
	 }
   	
</script>
	<form id="pageForm">
		<s:hidden name="pageNum" id="pageNum" />
		<s:hidden name="pageSize" id="pageSize" value="10" />
	</form>

<!--头部菜单-->
<jsp:include page="../include/client_head.jsp" />
<div class="main">
<!--左侧内容-->
<div class="left">
<h1 id="commentdivcomment" class="zl3">
<a id="receiveCommentid" class="over"  href="javascript:void(0)" _href="<%=rootPath%>microblog/microblogCommentView.action" _data="" onclick="tabcomment(this.id)"><font class="linkbh14">收到的评论</font></a>
<a id="sendCommentid"  href="javascript:void(0)" _href="<%=rootPath%>microblog/microblogCommentViewSend.action" _data="" onclick="tabcomment(this.id)"><font class="linkbh14">发出的评论</font></a>
</h1>
  <div class="fyh" style="margin-top: 50px;" id="commentmaindiv">
   	  
         
    </div>
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
</div>
<!--左侧内容结束-->
</body>
</html>