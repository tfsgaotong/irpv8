<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<%@ page import="com.tfs.irp.util.LoginUtil"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<title>商品兑换</title>


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
	<script type="text/javascript" src="<%=rootPath%>dwr/engine.js"></script>  
<script type="text/javascript" src="<%=rootPath%>dwr/util.js"></script> 
<script type="text/javascript" src="<%=rootPath%>dwr/interface/chatvolet.js"></script>  
<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.excheck-3.5.js"></script>
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

body {
	behavior: url(hover.htc);
}

.cardList {
	width: 710px;
}

.cardList li {
	float: left;
	display: inline;
	margin: 8px 10px;
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
#listuserul{
display: block;
width: 710px;
}
#listuserul li{
display: block;
width: 210px;
float: left;
}
.overb{
font-weight: bold;
}
.mainchatwindwsel{
	float: left;
	width: 33%;	
	text-align: center;
	line-height: 40px;
	cursor: pointer;
	background-color: #ADADAD;
}
.searchSec .radios span {
margin-top: 0px;
}
</style>

</head>
<body onload="selected('phonepage')">

<script type="text/javascript">
var namechats,chatuserids,groupchats,groupchatsid;
var lrefreshs;
var msgcountreads = 0;
var unreadarray = new Array();//未读消息数组
var recordPages = 10;
var recordPagesByGroup = 10;
var chatcontentnums =  <s:property value="chatcontentnums" default="600" />;
//查看更多记录(人)
function searchMoreRecord(){
		recordPages = recordPages+10;
	 	var chatframe = $.ajax({
		url: '<%=rootPath%>user/findchatframe.action', 
		data:{
			fromuserid:chatuserids,
			chatbyuserpages:recordPages
		},
		type:"post", 
	    async: false,
	    cache: false
		}).responseText;	
	 var _title = '正在与&nbsp;['+namechats+']&nbsp;对话中';
	 $.dialog({id:'chatframess'}).close();
	 	 $.dialog({
	 		id:'chatframess',
		 	title:_title,
			max:false,
			min:false,
			lock:false,
			resize: false,
			width:400,
			height:350,
			fixed:true,
			left:680,
			top:150,
			content:chatframe,
			cancel:function(){
				namechats = undefined;
				chatuserids = undefined;	
			},
			init:function(){
				window.location.href="#minechatlocationtops";
			},
			okVal:'发送',
			ok:function(){
				var boolgp = 10;
				var chatcontent = $('#chatcontentstr').val();
				if(chatcontent.length>chatcontentnums){
					$.dialog.tips("内容超出了"+chatcontentnums+"字",1,"alert.gif");
					return false;
				}
				if($.trim(chatcontent)!=''){
					chatvolet.insertChartData(chatuserids,boolgp,chatcontent,<%=LoginUtil.getLoginUserId() %>);
				}
				return false;
			}
	 });
}
//查看更多记录组织
function searchMoreRecordByGroups(){
	recordPagesByGroup = recordPagesByGroup + 10;
	var ftitle = "正在["+groupchats+"]组内讨论中";
	var chatframegroup = $.ajax({
		url: '<%=rootPath%>user/findgroupinmygroups.action', 
		data:{
			groupid:groupchatsid,
			chatbyuserpagesgroup:recordPagesByGroup
		},
		type:"post", 
	    async: false,
	    cache: false
		}).responseText;
	 $.dialog({id:'chatgroupframe'}).close();
	 $.dialog({
	 		id:'chatgroupframe',
		 	title:ftitle,
			max:false,
			min:false,
			lock:false,
			resize: false,
			width:400,
			height:350,
			fixed:true,
			left:680,
			top:150,
			content:chatframegroup,
			cancel:function(){
				groupchats = undefined;
				groupchatsid = undefined;
			
			},
			init:function(){
				window.location.href="#mygrouplocationbygroupbytops";
			},
			okVal:'发送',
			ok:function(){
				var ccfg = $("#chatcontentstrformygroup").val();
				if(ccfg.length>chatcontentnums){
					$.dialog.tips("内容超出了"+chatcontentnums+"字",1,"alert.gif");
					return false;
				}
				if($.trim(ccfg)!=''){
					chatvolet.insertChartData(groupchatsid,20,ccfg,<%=LoginUtil.getLoginUserId() %>);
				}
				return false;
			}
	 });
	
	
	
}
$(function(){
	//open dwr push 开启dwr推送
	dwr.engine.setActiveReverseAjax(true);
	//dwr.engine.setAsync(false);
	//正在热议
	hottalkdocument();
	//猜你喜欢
	youlovedocument();
	//分享排行
	sharedocument();
	//人气TOP
	hotpersondocument(); 
	selectuserbysm(14);
	var chatpagesindex = 1;
	var chatpagesindexbymf = 1;
	//
	chatvolet.addScriptSession(<%=LoginUtil.getLoginUserId() %>);
	
	//鼠标滚动
	$("#chatcontent").scroll(function(){
		var sctops = $("#chatcontent")[0].scrollTop;
		var scmaxtops = $("#chatcontent")[0].clientHeight;
		var scmaxheights = $("#chatcontent")[0].scrollHeight;
		
		var cmsysfriendbgcolor = $("#cmsysfriend").css("background-color");
		var myfriendsbgcolor = $("#cmfriend").css("background-color");
		
		if(cmsysfriendbgcolor=='rgb(253, 255, 255)'){
			var sysfriends = $('#sysfriendnumid').val();
			var sysnumsize = 0;
			if(sysfriends>10){
				if(sysfriends%10==0){
					sysnumsize = parseInt(sysfriends/10);
				}else{
					sysnumsize = parseInt(sysfriends/10)+1;
				}
			}
			var gunnums = parseInt(scmaxheights-scmaxtops);
			if(gunnums-sctops<20){
				if(chatpagesindex<sysnumsize){
					chatpagesindex = chatpagesindex+1;
					var _href = $("#cmsysfriend").attr("href");
					var _data = "pageNumSysfriend="+chatpagesindex;
						$.ajax({
							type:'post',
							url:_href,
							data:_data,
							cache:false,
							async:false,
							success:function(content){
								if(content!=''){
									$('#chatcontent').append(content);
									window.location.href="#minechatlocation";
								}
							}
						});
				}
			}
		}
		if(myfriendsbgcolor=='rgb(253, 255, 255)'){
			var myfriends = $('#myfriefdstnumchat').val();
			var myfriendsnumsize = 0;
			if(myfriends>10){
				if(myfriends%10==0){
					myfriendsnumsize = parseInt(myfriends/10);
				}else{
					myfriendsnumsize = parseInt(myfriends/10)+1;
				}
			}
			var gunnums = parseInt(scmaxheights-scmaxtops);
			if(gunnums-sctops<20){
				if(chatpagesindexbymf<myfriendsnumsize){
					chatpagesindexbymf = chatpagesindexbymf+1;
					var _href = $("#cmfriend").attr("href");
					var _data = "pageNummyfriend="+chatpagesindexbymf;
						$.ajax({
							type:'post',
							url:_href,
							data:_data,
							cache:false,
							async:false,
							success:function(content){
								if(content!=''){
									$('#chatcontent').append(content);
									window.location.href="#minechatlocation";
								}
							}
						});
					
					
				
				}
			
			}
		}
	

	});
	//菜单切换
	$('.mainchatwindwsel').click(function(){
		var beforeid = this.id;
		$('.mainchatwindwsel').each(function(){
		    var afterid ="#"+this.id;
			if(beforeid==this.id){
				//selected
				chatpagesindex = 1;
				chatpagesindexbymf = 1;
				$(afterid).css({"border-bottom":"none","background-color":"#FDFFFF"});
				$('#chatcontent').html("");
				
				findChatData($(afterid).attr("href"),$(afterid).attr("data"));
			}else{
				$(afterid).css({"border-bottom":"thin solid #BEBEBE","background-color":"#ADADAD"});
			}
		});
	});
	//加载未读
	detectionUnreadChat();
	//点击好友在线
	$('#footerchatwindw').click(function(){
		var showordisval = $('#showordisval').val();
		var displaystr = $("#mainchatwindw").css("display");
		var showval =  $("#showordisval").val();
		if(displaystr=="block" && showval==2){
			clidkdong();
			return false;
		}
		
		$("#mainchatwindw").slideToggle("fast",function(){
			var boolcnum = 0;
			$('.mainchatwindwsel').each(function(){
				var afterid ="#"+this.id;
				var bcolor = $(afterid).css("background-color");
				if(bcolor=='rgb(253, 255, 255)'){
					boolcnum = 1;
				}
			});
			if(boolcnum!=1){
				//default selected myfriends
				$('#chatcontent').html("");
				$("#cmfriend").click();
			}
		});
		//获取未读信息
		clidkdong();
	});
});
/**
* 关闭frame框
*/
function closeSolidFrames(){

	$("#mainchatwindw").slideToggle("fast");

}
function clidkdong(){
		if(unreadarray.length>=1){
			//数组长度大于1  表示有数据
			//最新数据
			var readjsondata = eval(unreadarray[0]);
			//样式 进行下一个
			if(unreadarray.length>1){
				var styledata =  eval(unreadarray[1]);
				if(styledata.status==20){
					unreadStyle("来自["+styledata.uname+"]组内有新消息!",unreadarray.length-1);
				}else{
					unreadStyle(styledata.uname,unreadarray.length-1);
				}
			}else{
				nobodyStrle();
			}
			//点击事件
			if(readjsondata.status == 10){
				//人
				popupChat(readjsondata.uname,readjsondata.userid);
			}else if(readjsondata.status == 20){
				//组
				var groupobj = {
					idkey:0,
					idvalue:readjsondata.userid,
					nametitle:readjsondata.uname
				};
				beforeClick(readjsondata.userid,groupobj);
			}
			unreadarray.shift();
		}else{
			nobodyStrle();
		}

}


/**
detection boolean unread by loginuser
当前用户是否有未读消息
*/
function detectionUnreadChat(){
	$.get("<%=rootPath%>user/detectionunread.action",function(msg){
		if(msg!=''){
	
		var msgjson = eval(msg);
		
		for(var i in msgjson){
		
			unreadarray[i] = msgjson[i];
		}	
		if(msgjson[0].status==20){
			unreadStyle("来自["+msgjson[0].uname+"]组内有新消息!",unreadarray.length);
		}else{
			unreadStyle(msgjson[0].uname,unreadarray.length);
		}
		}else{
			nobodyStrle();
		}
	});
}
//未读消息样式
function unreadStyle(_uname,_count){
			$('#footerchatwindw').html(_uname+"<input type=\"text\" value=\"2\" id=\"showordisval\" style=\"display: none;\" >");
			lrefreshs = window.clearInterval(lrefreshs);
		    lrefreshs = setInterval(function(){
				$('#footerchatwindw').fadeIn(200,function(){
				}).fadeOut(200,function(){
				});
			},400);
			$('#footerchatwindw').attr("title",_count+"人发来消息");

}
//没有样式
function nobodyStrle(){
	//unreadarray.length = 0;
	//$('#footerchatwindw').html("即时通讯<input type=\"text\" value=\"1\" id=\"showordisval\" style=\"display: none;\" >");
	//lrefreshs = window.clearInterval(lrefreshs);
	//$('#footerchatwindw').fadeIn(1);
	//$('#footerchatwindw').attr("title","");

}

/**
* acquire chat data
* 获取聊天数据
*/
function findChatData(_url,_data){
	if(_url==''){
		return false;
	}
	$.ajax({
		type:'post',
		url:_url,
		data:_data,
		cache:false,
		async:false,
		success:function(content){
			$('#chatcontent').append(content);
		}
	});
}
/**
* forthwith chat click   即时消息
*/
function forthWithChat(){
		detectionUnreadChat();
		popupChat(namechats,chatuserids);
}

/**
* forthwith chat click即时消息 （组）
*/
function forthWithChatGroup(){
		detectionUnreadChat();
		
		var treenode;
		if(groupchatsid==undefined || groupchatsid==''){
		treenode = undefined;
		}else{
		
		treenode = {
			idkey:0,
			idvalue:groupchatsid,
			nametitle:groupchats
		};
		
		}
	
		beforeClick(0,treenode);
}

function trueT(_cuserid,_loginid,_pointobj){
	if(_loginid==<%=LoginUtil.getLoginUserId()%>){
		if(_pointobj==20){
			//组
			
			forthWithChatGroup();
		}else{
			//人
		
			forthWithChat();
		}
	}else{
	if(_pointobj==20){
		//组
		if(groupchatsid==undefined){
			forthWithChatGroup();
		
		}else{
		
			if(_cuserid!=groupchatsid  ){
				forthWithChatGroup();
			}else{
				var treenode = {
					idkey:0,
					idvalue:groupchatsid,
					nametitle:groupchats
				};
				beforeClick(0,treenode);
				nobodyStrle();
			}
		}
	
	}else{
		if(chatuserids==undefined){
			//对话框属于关闭状态
			forthWithChat();
		}else{
			if(_cuserid!=<%=LoginUtil.getLoginUserId()%>){
				forthWithChat();
			}else{
				popupChat(namechats,chatuserids);
				nobodyStrle();
			}
		}
	
	}
	}
}
//点击事件(组)
function beforeClick(treeId, treeNode){
	recordPagesByGroup = 10;
	if(treeNode==undefined){
		return false;
	}
	if(treeNode.idkey==1){
	popupChat(treeNode.name,treeNode.idvalue);
	return false;
	}
var ftitle = "正在["+treeNode.nametitle+"]组内讨论中";
	groupchats = treeNode.nametitle;
	groupchatsid = treeNode.idvalue;
	//mark the chat alreadyread 标记为已读
$.post("<%=rootPath%>user/chatmarkalreadygroup.action",{groupmarkid:treeNode.idvalue});
var chatframegroup = $.ajax({
		url: '<%=rootPath%>user/findgroupinmygroups.action', 
		data:{
			groupid:treeNode.idvalue
		},
		type:"post", 
	    async: false,
	    cache: false
		}).responseText;
	 $.dialog({id:'chatgroupframe'}).close();
	 $.dialog({
	 		id:'chatgroupframe',
		 	title:ftitle,
			max:false,
			min:false,
			lock:false,
			resize: false,
			width:400,
			height:350,
			fixed:true,
			left:680,
			top:150,
			content:chatframegroup,
			cancel:function(){
				groupchats = undefined;
				groupchatsid = undefined;
			
			},
			init:function(){
				window.location.href="#mygrouplocationbygroup";
			},
			okVal:'发送',
			ok:function(){
				var ccfg = $("#chatcontentstrformygroup").val();
				if(ccfg.length>chatcontentnums){
					$.dialog.tips("内容超出了"+chatcontentnums+"字",1,"alert.gif");
					return false;
				}
				if($.trim(ccfg)!=''){
					chatvolet.insertChartData(treeNode.idvalue,20,ccfg,<%=LoginUtil.getLoginUserId() %>);
				}
				return false;
			}
	 });
}

/**
* popup dialogue frame
* 弹出对话框
*/
function popupChat(_name,_userid){
	recordPages = 10;
	if(_name==undefined || _userid==undefined){
		return false;
	}
	
	//mark the chat alreadyread 标记为已读
	$.post("<%=rootPath%>user/chatmarkalready.action",{fromuserid:_userid});
	namechats = _name;
	chatuserids = _userid;
	var _title = '正在与&nbsp;['+_name+']&nbsp;对话中';
	var chatframe = $.ajax({
		url: '<%=rootPath%>user/findchatframe.action', 
		data:{
			fromuserid:_userid
		},
		type:"post", 
	    async: false,
	    cache: false
		}).responseText;	
	 $.dialog({id:'chatframess'}).close();
	 $.dialog({
	 		id:'chatframess',
		 	title:_title,
			max:false,
			min:false,
			lock:false,
			resize: false,
			width:400,
			height:350,
			fixed:true,
			left:680,
			top:150,
			content:chatframe,
			cancel:function(){
				namechats = undefined;
				chatuserids = undefined;	
			},
			init:function(){
				window.location.href="#minechatlocation";
			},
			okVal:'发送',
			ok:function(){
				var boolgp = 10;
				var chatcontent = $('#chatcontentstr').val();
				if(chatcontent.length>chatcontentnums){
					$.dialog.tips("内容超出了"+chatcontentnums+"字",1,"alert.gif");
					return false;
				}
				if($.trim(chatcontent)!=''){
					chatvolet.insertChartData(_userid,boolgp,chatcontent,<%=LoginUtil.getLoginUserId() %>);
				}
				return false;
			}
	 });
}
function selectuserbysm(coverstate){
	if(coverstate==14){
		$("#sendCommentid").addClass("over"); 
   		$("#receiveCommentid").removeClass("over");
   		$("#activeCommentid").removeClass("over");
   		$("#icanCovertGoods").removeClass("over");
   		$('#goodslist').empty();
	}else if (coverstate==17){
		$("#sendCommentid").removeClass("over"); 
   		$("#receiveCommentid").removeClass("over");
   		$("#activeCommentid").removeClass("over");
   		$("#icanCovertGoods").addClass("over");
	}else if (coverstate==16) {
		$("#sendCommentid").removeClass("over"); 
   		$("#receiveCommentid").removeClass("over");
   		$("#activeCommentid").addClass("over");
   		$("#icanCovertGoods").removeClass("over");
   		$('#goodslist').empty();
	}else{
		$("#sendCommentid").removeClass("over"); 
   		$("#receiveCommentid").addClass("over");
   		$("#activeCommentid").removeClass("over");
   		$("#icanCovertGoods").removeClass("over");
   		$('#goodslist').empty();
	}
	var result= $.ajax({
		type:'post',
		url:"<%=rootPath%>goods/listGoodsbyfu.action?coverstate="+coverstate,
		dataType: "json",
		async: false,
   		cache: false
	}).responseText;
	$("#goodslist").html(result);
}
function covertGoods(){
	var result= $.ajax({
		type:'post',
		url:"<%=rootPath%>goods/listGoodsbyfu.action?coverstate=1",
		dataType: "json",
		async: false,
   		cache: false
	}).responseText;
	$("#goodslist").html(result);
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
//根据生母找用户
function selectuserbySMother(){
   var showway=$("#showway").val();
   $("#sm").val($.trim("other"));
   $('#userlist').empty();
	//去空格
	var result= $.ajax({
		type:'post',
		url:"<%=rootPath%>menu/find_userbysm.action?showway="+showway+"&sname=other",
		dataType: "json",
		async: false,
   		cache: false
	}).responseText;
	$("#userlist").html(result);
}
//根据生母找用户
function selectuserbySM(_value){
	var leng=$("#sm").find("a").length;
	for(var i=0;i<leng;i++){
		  if($("#sm").find("a:eq("+i+")").text()==$(_value).text()){
			  $("#sm").find("a:eq("+i+")").addClass("overb");
		  }else{
			  $("#sm").find("a:eq("+i+")").removeClass("overb");
		  }
	  }
   value=$(_value).text();
   var showway=$("#showway").val();
   $("#sname").val($.trim(value));
   $("#sm").val($.trim(value));
   $('#userlist').empty();
	//去空格
	var result= $.ajax({
		type:'post',
		url:"<%=rootPath%>menu/find_userbysm.action?showway="+showway+"&sname="+$.trim(value),
		dataType: "json",
		async: false,
   		cache: false
	}).responseText;
	$("#userlist").html(result);
}
function page(_pagenum){
	var coverstate="";
	if($("#sendCommentid").hasClass("over")){
		coverstate="";
	}
	if($("#receiveCommentid").hasClass("over")){
		coverstate=20;
	}
	if($("#activeCommentid").hasClass("over")){
		coverstate=10;
	}
	if($("#icanCovertGoods").hasClass("over")){
		coverstate=1;
	}
	var showway=$("#showway").val();
	$("#pageNum").val(_pagenum);
	$("#pageSizeclient").val($("#pageSizeclientform").val());
	$("#keyword").val($("#searchWord").val());
	$("#sname").val($("#sm").val());
	var queryString = $('#pageForm').serialize();
	$('#goodslist').empty();
	var result=$.ajax({
		type:'post',
		url:"<%=rootPath%>goods/listGoodsbyfu.action?coverstate="+coverstate+"&"+queryString,
		dataType: "json",
		async: false,
   		cache: false
	}).responseText;
	$("#goodslist").html(result);
}
//搜索用户
function searchUser(){
	var coverstate="";
	if($("#sendCommentid").hasClass("over")){
		coverstate="";
	}
	if($("#receiveCommentid").hasClass("over")){
		coverstate=20;
	}
	if($("#activeCommentid").hasClass("over")){
		coverstate=10;
	}
	if($("#icanCovertGoods").hasClass("over")){
		coverstate=1;
	}
	var _searchword = $("#searchWord").val();
	$("#keyword").val(_searchword);
	$("#pageSizeclient").val($("#pageSizeclientform").val());
	$("#pageNum").val($("#pageNumform").val());
	$("#goodsname").val($("#goodsname").val());
	$("#goodsdesc").val($("#goodsdesc").val());
	var queryString = $('#pageForm').serialize();
	$('#goodslist').empty();
	var result=$.ajax({
		type:'post',
		url:"<%=rootPath%>goods/listGoodsbyfu.action?coverstate="+coverstate+"&"+queryString,
		dataType: "json",
		async: false,
   		cache: false
	}).responseText;
	$("#goodslist").html(result);
}
//显示方式
function phoneshowway(){
	var value=$("#showway").val();
	var sname=$("#sm").val();
	var searchword = $("#searchWord").val();
	$("#keyword").val(searchword);
	var _url="";
	if(value==0){
		//图片显示
		_url="<%=rootPath%>menu/find_userbysm.action?showway=0&searchWord="+searchword+"&sname="+$.trim(sname);
	}else{
		//列表显示
		_url="<%=rootPath%>menu/find_userbysm.action?showway=1&searchWord="+searchword+"&sname="+$.trim(sname);
	}
	$("#sname").val($.trim($("#sm").val()));
	   $('#userlist').empty();
		//去空格
		var result= $.ajax({
			type:'post',
			url:_url,
			dataType: "json",
			async: false,
	   		cache: false
		}).responseText;
		$("#goodslist").html(result);
}

//加为好友
function okForcus(_userid){

	var guanzhus = "#guanzhusys"+_userid;
	
	if($(guanzhus).attr("title")=="加为好友"){
		if(_userid!=""){
		$.post("<%=rootPath%>microblog/okMicroblogFocus.action",{userid:_userid},function(msg){
			if(msg==1){
				$(guanzhus).css({
					"background-image":"url('<%=rootPath%>client/images/icons/cancel.png')"
				});
				$(guanzhus).attr("title","移除好友");
			}
			
		});
		}
	}else{
		if(_userid!=""){
		
			$.dialog.confirm('您确定要移除此好友吗?',function(){
				$.post("<%=rootPath%>microblog/cancelMicroblogFocus.action",{userid:_userid},function(msg){
					if(msg==1){	
						$(guanzhus).css({
						"background-image":"url('<%=rootPath%>client/images/icons/edit_add.png')"
					});
						$(guanzhus).attr("title","加为好友");
					}
				});
				
			});
		}
	}
}
</script>
<body style="background: url()">
	<form id="pageForm">
        <s:hidden name="searchWord" id="keyword"/>
	    <s:hidden name="sname" id="sname"/>
		<s:hidden name="pageNum" id="pageNum" />
		<s:hidden name="pageSizeclient" id="pageSizeclient"/>
	</form>
<div class="bg01">
  <!--头部菜单-->
 <jsp:include page="../../view/include/client_head.jsp" />
 <section class="mainBox">
	<nav class="privateNav" style="background:url(../images/bg-04.gif) repeat-x">
         <dl>
             <dt class="leftBox">全部兑换分类</dt>
             <dd class="clear"></dd>
         </dl>
     </nav>
	</section>
<section class="mainBox">
	<section class="layoutLeft">
       	<!-- 分类列表 -->
           <nav class="allBtns">
               <ul>
                   <s:iterator value="listCategory">
                       <li>
                        <a href="javaScript:void(0)" onclick="selectuserbysm(<s:property value="categoryid" />)">
                               <s:property value="cdesc" />
			            </a>
			            <s:set var="childCate" value="findChildCategoryByParentId(categoryid)" /> 
			           <!-- <s:if test="#childCate!=null && #childCate.size()>0">
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
				         --> 
				    </li>
		        </s:iterator>
	        </ul>
	    </nav>
    </section>
    <section class="layoutMiddle1">	
    <div class="main" style="background: transparent none repeat scroll 0px 0px;">
	<!--左侧内容-->
	      <!--    显示方式&nbsp;<select id="showway" onchange="phoneshowway()"><option value="0">图标</option><option value="1">列表</option></select> -->
	    <div id="goodslist" style="width: 950px;" > </div>
	<div id="mainchatwindw" class="chartsiggle" style="border-left:thin solid #E0E0E0; position: fixed;bottom: 41px;right: 1px;height: 475px;width:230px ;background-color:#FDFFFF ;display: none; ">
	
		<div style="height: 47px;">
			<div id="cmfriend" href="<%=rootPath%>user/myfriendsuser.action" data="pageNum=1" style="border-bottom:thin solid #BEBEBE; border-right:thin solid #BEBEBE; " class="mainchatwindwsel">我的好友</div>
			<div id="cmgroup" href="<%=rootPath%>user/mygroups.action" data="pageNum=1" style="border-bottom:thin solid #BEBEBE; " class="mainchatwindwsel">我的分组</div>
			<div id="cmsysfriend" href="<%=rootPath%>user/systemfriendsuser.action" data="pageNumSysfriend=1" style="border-bottom:thin solid #BEBEBE; border-right:thin solid #BEBEBE; " class="mainchatwindwsel">系统用户</div>		
					
		</div>
		<div id="chatcontent"  style="overflow-y:scroll;height: 356px;overflow-x:hidden;" >
	
		
		
		</div>
	
	</div>
	<div id="footerchatwindw" style="position: fixed;bottom: 1px;right: 1px;width:230px ;height:40px;cursor: pointer;background-color:#6C6C6C;line-height: 40px;color: #FFFFF4;text-align: center;display: none;" title=""  >
		<input type="text" value="1" id="showordisval" style="display: none;" >
		即时通讯
	</div>
	</div>
	</section>
</section>
	<!-- 尾部菜单 -->
   <jsp:include page="../include/client_foot.jsp" />
</div>
<!--左侧内容结束-->
</body>
</html>