<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> <base href="<%=rootPath%>"> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看私信</title>
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

//查看文档
function documentinfo_see(_docid){ 
	window.open('<%=rootPath %>site/client_documentinfo.action?refrechstatus=1&docid='+_docid);  
}
var messageidinfoid = 0;
var cruseridinfoid = 0;
$(function(){
	//改变私信已读未读状态
	$.ajax({
		type:"get",
		cache:false,
		url:"<%=rootPath%>microblog/changeUnReadMessageStatus.action",
		success:function(msg){
		   if(msg>=0){
			   return true;
		   }
			
		}
	});
	//加载个人基本信息
	$.ajax({
		type:"get",
		cache:false,
		url:"<%=rootPath%>microblog/loadUserInfo.action",
		success:function(callback){
			$('.right').prepend(callback);
		}
	});
	//加载收到的私信
	$('#irpmessagediv').empty();
	$.ajax({
		type:"post",
		url:"<%=rootPath%>microblog/searchMessage.action",
		cache:false,
		data:{
			pageNum:1
		},
		success:function(callback){
			$('#sendmessage').css("color","");
			$('#receivemessage').css("color","rgb(242, 130, 30)");
			$('#irpmessagediv').append(callback);
			
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
 
 
//私信分页
function pageMessage(_nPageNum){
	$('#pageNum').val(_nPageNum);
	var queryString = $('#pageForm').serialize();
	
	$.ajax({
		type:"post",
		url:"<%=rootPath%>microblog/searchMessage.action?"+queryString,
		success:function(callback){
			$('#sendmessage').css("color","");
			$('#receivemessage').css("color","rgb(242, 130, 30)");
			$('#irpmessagediv').html(callback);
		}
		
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
/*构建私信框*/
function messageContentViews(_messageuser,_messageid){
	//构建页面结果
	var result = $.ajax({
		url: '<%=rootPath%>microblog/messageContentPage.action',
		type:"post",
		dataType: "json",
		cache:false,
	    data: {
	    	messageuser:_messageuser,
	    	messageid:_messageid
	    },
	    async: false
	}).responseText;
	$.dialog({
		title:'发私信',
		max:false,
		min:false,
		lock:true,
		resize: false,
		cache:false,
		width:'450px',
		height:'180px',
		content:result,
		cancelVal:'关闭',
		okVal:'发送',
		cancel:true,
		ok:function(){
			if($.trim($('#messageuserinfo').val())==""){			
				$.dialog.tips("收信人不能为空",0.3,"alert.gif");	
				return false;
			}else{	
			   if($.trim($('#messageInfo').val()).length>$('#messagecount').val()){
			    	return false;
			    }else if($.trim($('#messageInfo').val()).length<=0){
			    	  $.dialog.tips("私信内容不能为空",0.3,"alert.gif");	
			    	return false;
			    }else{
			    	
			$('#messageContentForm').form('submit',{
				url:'<%=rootPath%>microblog/sendMessageContent.action',
				cache:false,
				success:function(msg){
					
					if(msg==0){
						$.dialog.tips('发送成功',1,'32X32/succ.png');		
					}else if(msg==1){	
						$.dialog.tips('部分发送成功,另一部分由于用户的设置未能发送成功',2,'32X32/hits.png');	
					}else{
						$.dialog.tips('发送失败,由于用户的设置',2,'32X32/fail.png');	
					}	
				}
			});
		}
		}
		}
	});
	if(_messageid==0){
		$('#messageuserinfo').focus();
	}else{
		  $('#messageInfo').focus();	
	}	
}
	
	//我的私信
	function receiveMessageList(){
		$('#irpmessagediv').empty();
		$.ajax({
			type:"post",
			url:"<%=rootPath%>microblog/searchMessage.action",
			data:{
				pageNum:1
			},
			success:function(callback){
				$('#receivemessage').addClass("over");
				$('#sendmessage').removeClass("over");
				
				$('#irpmessagediv').append(callback);
			}
			
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
  
//加载某个私信用户的信息
function loadUserMessage(_messageid,_cruserid){
	$('#irpmessagediv').empty();
	messageidinfoid = _messageid;
	cruseridinfoid = _cruserid;
$.ajax({
	type:"post",
	url:"<%=rootPath%>microblog/searchUserMessage.action",
	data:{
		msssageid:_messageid,
		cruserid:_cruserid
	},
	success:function(callback){
		
		$('#irpmessagediv').append(callback);
		
	}
});	
}
//私信分页(详细信息)
function pageMessageInfo(_nPageNum){
	$('#pageNum').val(_nPageNum);
	var queryString = $('#pageForm').serialize();
	$.ajax({
		type:"post",
		url:"<%=rootPath%>microblog/searchUserMessage.action?"+queryString,
		data:{
			msssageid:messageidinfoid,
			cruserid:cruseridinfoid
		},
		success:function(callback){
			
			$('#irpmessagediv').html(callback);
			
		}
	});
	
}
//控制微知内容的长度
function microblogInfoControl(_microblogcontent){		
	if($('#messagedetailnum').val()-$.trim(_microblogcontent).length>=0){
		$('#microblogContentprompt_02').css({display:'none'});
		$('#microblogContentprompt_01').css({display:'block'});
		$('#microblogContentCount_01').text($('#messagedetailnum').val()-$.trim(_microblogcontent).length);
	}else{
		$('#microblogContentprompt_01').css({display:'none'});
		$('#microblogContentprompt_02').css({display:'block'});
		$('#microblogContentCount_02').text(Math.abs($('#messagedetailnum').val()-$.trim(_microblogcontent).length));
	}
}
//私信回复
function messagePersonalReply(_cruserid){
	//私信内容

	//获取隐私设置
        var messagelimits = $.ajax({
	    	     type:"post",
	    	     url:"<%=rootPath%>microblog/getprivacyofmessage.action",
	    	     data:{
	    	        	userid:_cruserid
	            	},
	            	cache:false,
	            	async:false
	             }).responseText;
	    	if(messagelimits=="false"){
	    		 $.dialog.tips("由于用户设置，您无法发送私信。",1,"alert.gif");	
	    		return false;
	    	}else{
	    		var replyMessageInfo =$('#replyMessageInfo').val();
	    	if($.trim(replyMessageInfo).length>$('#messagedetailnum').val()){
	        	return false;
	        }else if($.trim(replyMessageInfo).length<=0){
	        	 $.dialog.tips("内容不能为空",0.3,"alert.gif");	
	        	return false;
	        }else{
	            $.ajax({
	            	type:"post",
	            	url:"<%=rootPath%>microblog/sendPersonalMessageContent.action",
	            	data:{
	            		cruseridid:_cruserid,
	            		content:replyMessageInfo
	            	},
	            	success:function(callback){
	            		$('#replycommentpersonaldiv').prepend(callback);
	            		$('#replyMessageInfo').val("");
	            		$('#microblogContentCount_01').text($('#messagecountnumconf').val());
	            	}
	            });
	        		
	        }
	    	}

}

//删除某一条详细私信记录
function deleteMessageInfo(_messageid){
	 var messagediv = "#"+_messageid+"div";
	 $.dialog.confirm('您确定要删除此条私信吗',function(){
		 
		 $.ajax({
				type:"POST",
				url:"<%=rootPath%>microblog/deleteMessageInfo.action",
				data:{
					messageid:_messageid	
				},
				success:function(msg){
					if(msg==1){
					   $(messagediv).fadeOut();	
						
					}
					
				}
				
			}); 
		 
	 });
	
}
//删除一组私信
function deleteMessage(_fromuid,_cruserid,_messageid){
	var messageiddiv = "#"+_messageid+"div";
	$.dialog.confirm('您确定要删除本组对话吗',function(){
		
		$.ajax({
	   		type:"post",
	   		url:"<%=rootPath%>microblog/deleteMessage.action",
	   		data:{
	   			cruserid:_cruserid,	
	   			fromuid:_fromuid
	   		},
	   		success:function(msg){
	   			if(msg>=0){
	   				$(messageiddiv).fadeOut();
	   			}
	   			
	   		}
	   	});
		
		
		
	});
   	
	
	
}
//清空所有私信
function messageClear(){
	$.dialog.confirm('您确定要清空所有私信吗',function(){
		$.ajax({
			type:"get",
			url:"<%=rootPath%>microblog/messageClear.action",
			success:function(msg){
				if(msg>=0){
					$('#irpmessagediv').empty();
				}
			}
		});
		
		
	});
}
//已发出的私信
function sendMessageList(){

	$("#irpmessagediv").empty();
	$.ajax({
		type:"get",
		url:"<%=rootPath%>microblog/sendOutMessage.action?pageNum=1",
		cache:false,
		success:function(callback){
			$('#sendmessage').addClass("over");
			$('#receivemessage').removeClass("over");
			$("#irpmessagediv").append(callback);
			
			
		}
	});
}
//已发出的私信(删除)
function deleteMessageSendOut(_messageid){
	 var messagediv = "#"+_messageid+"div";
	 $.dialog.confirm('您确定要删除此条私信吗',function(){
		 
		 $.ajax({
				type:"POST",
				url:"<%=rootPath%>microblog/deleteMessageInfo.action",
				data:{
					messageid:_messageid	
				},
				success:function(msg){
					if(msg==1){
					   $(messagediv).fadeOut();	
						
					}
					
				}
				
			}); 
		 
	 });
}
//已发出的私信分页
function pageMessageSendOut(_nPageNum){
	$('#pageNum').val(_nPageNum);
	var queryString = $('#pageForm').serialize();
	$.ajax({
		type:"get",
		url:"<%=rootPath%>microblog/sendOutMessage.action?"+queryString,
		cache:false,
		success:function(callback){
			$('#sendmessage').css("color","rgb(242, 130, 30)");
			$('#receivemessage').css("color","");
			$("#irpmessagediv").html(callback);
			
			
		}
	});
	
}
/**
 * 选择用户
 */
 function jump(_form){ 
		var sContent = findSelectUserContent(_form.serialize());  
		lhbDialog.get('selectUser',1).content(sContent); 
	}
function findSelectUserContent(_postData){
		var result = $.ajax({
			type: 'POST',
			url: '<%=rootPath %>user/select_user.action',
		    data: _postData,
		    async: false,
		    cache: false
		}).responseText;
		return result;
	} 
function addUser(){
	//获得内容
	var result = findSelectUserContent('init=0&ismaxamount=false'); 
	//初始化弹出框
	lhbDialog = $.dialog({
		id: 'selectUser',
		title:'选择用户',
		content: result,
		max: false,
	    min: false,
		ok: function(){
			$('#dialogPageForm').form('submit',{
    			url : "<%=rootPath %>microblog/sendmessageselect.action",
    			success:function(usernames){
    				if(usernames!=2){
    					$('#messageuserinfo').val($('#messageuserinfo').val()+usernames);
    				}
    			}
			});
	    },
	    okVal:'保存',
	    cancelVal: '关闭',
	    cancel: true,
	    lock: true,
	    padding: 0
	});
}
/**
 * 连接到问题
 */
function connectQuestion(_questionid){
	window.open("<%=rootPath%>question/questionDetail.action?questionid="+_questionid);
} 
function tabs(_lidom){
	return false;
}
function tabsPersonalLink(){
	return false;
}
</script>
	<form id="pageForm">
		<s:hidden name="pageNum" id="pageNum" />
		<s:hidden name="pageSize" id="pageSize" value="10" />
	</form>
<!--头部菜单-->
<jsp:include page="../include/client_head.jsp" />
<div class="main">

<div class="left">

<h1 class="zl3" style=" font-weight:normal;  line-height:50px; border-bottom:1px  solid #efefef;text-align:left; font-family:微软雅黑; margin:0 0 20px 5px; color:#121212;">
<a class="over"  id="receivemessage"  href="javascript:void(0)" onclick="receiveMessageList()"><font class="linkbh14">我的私信</font></a>
<a  id="sendmessage"  href="javascript:void(0)" onclick="sendMessageList()"><font class="linkbh14">发出的私信</font></a>
<a  href="javascript:void(0)" onclick="messageContentViews('',0)" ><font class="linkbh14">发私信</font></a>
<a  href="javascript:void(0)" onclick="messageClear()" ><font class="linkbh14">清空私信</font></a>
</h1>

  <div class="fyh" style="margin-top: 50px;" id="irpmessagediv">
    
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
</body>
</html>