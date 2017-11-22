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
<title>查看@我的</title>
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
	//获取@给我的信息
	$.ajax({
		type:"post",
		url:"<%=rootPath%>microblog/microblogAtmeView.action",
		success:function(html){
			if(html!=null){
				$('#atmediv').append(html);
			}else{
				$.dialog.tips('获取评论列表失败',1,'32X32/fail.png');
			}
		}
	});
});
function tabs(_lidom){
	return false;
}
function tabsPersonalLink(){
	return false;
}
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
/*分页*/
 function page(_nPageNum){
	 $('#atmediv').empty();	
		$('#pageNum').val(_nPageNum);
		var queryString = $('#pageForm').serialize();
		$.ajax({
			type:"post",
			url:"<%=rootPath%>microblog/microblogAtmeView.action?"+queryString,
			success:function(html){
				if(html!=null){
					$('#atmediv').append(html);
				}else{
					$.dialog.tips('获取@列表失败',1,'32X32/fail.png');
				}
			}
		});	
	
    }
/*删除*/
 function deleteMicroblogAtme(_atmeid){
	 var atmediv = "#"+_atmeid+"div";
	 $.dialog.confirm('你确定要删除吗',function(){
		 $.ajax({
				type:"POST",
				url:"<%=rootPath%>microblog/microblogAtmeDelete.action",
				data:{
					atmeid:_atmeid
				},
				cache:false,
				async:false,
				success:function(_msg){
					if(_msg==1){
						$(atmediv).fadeOut();
						$(atmediv).fadeOut("slow");
						$(atmediv).fadeOut(3000);
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
/*清空*/ 
function microblogAtmeEmpty(){
	$.dialog.confirm('你确定要清除全部私信吗',function(){
		
		$.ajax({
			type:"post",
			url:"<%=rootPath%>microblog/microblogAtmeEmpty.action",
			success:function(msg){
				if(msg>=0){
					$('#atmediv').fadeOut(1000);
				}else{
				 $.dialog.tips('清空失败',1,'32X32/fail.png');
					return false;
				}
				
			}
		});		
	});
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
/**
 * 刷新页面
 */
 function refresh(){
	
	window.location.reload();
	
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
  <a  style="  font-weight:normal; line-height:50px; border-bottom:1px dashed #efefef; text-align:left; font-family:'微软雅黑'; margin:0 0 20px 5px; color:#121212;" href="javascript:void(0)" onclick="refresh()"><font class="linkbh14">@提到我的</font></a>
  <a  style=" font-weight:normal; line-height:50px; border-bottom:1px dashed #efefef; text-align:left; font-family:'微软雅黑'; margin-left:450px; color:#121212;" href="javascript:void(0)" onclick="microblogAtmeEmpty()"><font class="linkbh14">清空</font></a>
   <div class="fyh" style="margin-top: 50px;" id="atmediv">
   	  
         
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