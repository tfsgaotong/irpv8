<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<%@ page import="com.tfs.irp.util.LoginUtil"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- 通讯录首页 -->
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<title>通讯录</title>
	<link rel="shortcut icon" href="<%=rootPath%>favicon.ico" type="image/x-icon" />
	<link rel="bookmark" href="<%=rootPath%>favicon.ico" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/normalize.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/common1.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/oacss.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/easyui.css"/>
	<script type="text/javascript" src="<%=rootPath%>view/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/js/lhgdialog.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/js/modernizr.custom.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>dwr/engine.js"></script>  
	<script type="text/javascript" src="<%=rootPath%>dwr/util.js"></script> 
	<script type="text/javascript" src="<%=rootPath%>dwr/interface/chatvolet.js"></script>  
	<jsp:include page="../../view/include/client_skin.jsp" />
	<style type="text/css">
	
	body {
		behavior: url(js/hover.htc);
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
	
	#listuserul {
		display: block;
		width: 710px;
	}
	
	#listuserul li {
		display: block;
		width: 210px;
		float: left;
	}
	
	.overb {
		font-weight: bold;
	}
	
	.mainchatwindwsel {
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
	/* //正在热议
	hottalkdocument();
	//猜你喜欢
	youlovedocument();
	//分享排行
	sharedocument();
	//人气TOP
	hotpersondocument();  */
	//显示所有通讯录列表信息
	selectuserbysm();
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
			unreadarray.length = 0;
			$('#footerchatwindw').html("即时通讯<input type=\"text\" value=\"1\" id=\"showordisval\" style=\"display: none;\" >");
			lrefreshs = window.clearInterval(lrefreshs);
			$('#footerchatwindw').fadeIn(1);
			$('#footerchatwindw').attr("title","");

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
/**
 * 首页加载显示所有通讯录信息
 */
function selectuserbysm(){
	var leng=$("#sm").find("a").length;
	for(var i=0;i<leng;i++){
		  if($("#sm").find("a:eq("+i+")").text()=='全部'){
			  $("#sm").find("a:eq("+i+")").addClass("overb");
		  }else{
			  $("#sm").find("a:eq("+i+")").removeClass("overb");
		  }
	  }
	var showway=$("#showway").val();
	$("#sname").val("null");
	var result= $.ajax({
		type:'post',
		url:"<%=rootPath%>menu/find_userbysm.action?showway="+showway+"&sname=null",
		dataType: "json",
		async: false,
   		cache: false
	}).responseText;
	$("#userlist").html(result);
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
	var showway=$("#showway").val();
	$("#pageNum").val(_pagenum);
	$("#pageSizeclient").val($("#pageSizeclientform").val());
	$("#keyword").val($("#searchWord").val());
	$("#sname").val($("#sm").val());
	var queryString = $('#pageForm').serialize();
	$('#userlist').empty();
	var result=$.ajax({
		type:'post',
		url:"<%=rootPath%>menu/find_userbysm.action?showway="+showway+"&"+queryString,
		dataType: "json",
		async: false,
   		cache: false
	}).responseText;
	$("#userlist").html(result);
}
//按回车键搜索用户
function searchUserByEnter(event){
	if(event.keyCode === 13){
		searchUser();
	}
}
//搜索用户
function searchUser(){
	var showway=$("#showway").val();
	var _searchword = $("#searchWord").val();
	$("#keyword").val(_searchword);
	$("#pageSizeclient").val($("#pageSizeclientform").val());
	$("#pageNum").val($("#pageNumform").val());
	$("#sname").val($("#sm").val());
	var queryString = $('#pageForm').serialize();
	$('#userlist').empty();
	var result=$.ajax({
		type:'post',
		url:"<%=rootPath%>menu/find_userbysm.action?showway="+showway+"&"+queryString,
		dataType: "json",
		async: false,
   		cache: false
	}).responseText;
	$("#userlist").html(result);
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
		$("#userlist").html(result);
}

//加为好友
function okForcus(_userid){

	var guanzhus = "#guanzhusys"+_userid;
	
	if($(guanzhus).attr("title")=="加为好友"){
		if(_userid!=""){
		$.post("<%=rootPath%>microblog/okMicroblogFocus.action",{userid:_userid},function(msg){
			if(msg==1){
				$(guanzhus).css({
					"background-image":"url('<%=rootPath%>view/images/icons/cancel.png')"
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
						"background-image":"url('<%=rootPath%>view/images/icons/edit_add.png')"
					});
						$(guanzhus).attr("title","加为好友");
					}
				});
				
			});
		}
	}
}
</script>

<body onload="selected('votepage')" style="background: url()">
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
			<nav class="privateNav">
				<dl>
					<dt class="leftBox">全部用户分组</dt>
					<dd class="clear"></dd>
				</dl>
			</nav>
		</section>
		<section class="mainBox" style="min-height: 60vh">
			<section class="layoutLeft" style="display:inline-block; width:245px;min-height: 0.1vh">
				<nav class="allBtns">
					<ul>
						<s:iterator value="groupList">
							<li>
								<a href="<s:url namespace="/tongxunlu" action="userGroup"><s:param name="groupid" value="groupid" /></s:url>">
									<s:property value="groupname" />
								</a>
								<s:set var="childGroup" value="findChildGroupByParentId(groupid)" />
								<s:if test="#childGroup!=null && #childGroup.size()>0">
									<div class="moreBtns">
										<div class="line">
											<s:iterator value="#childGroup">
												<h1>
													<a href="<s:url namespace="/tongxunlu" action="userGroup"><s:param name="groupid" value="groupid" /></s:url>">
														<s:property value="groupname" />
													</a>
												</h1>
												<s:set var="grandsonGroup" value="findChildGroupByParentId(groupid)" />
												<s:if test="#grandsonGroup!=null && #grandsonGroup.size()>0">
													<s:iterator value="#grandsonGroup">
														<P>
															<a href="<s:url namespace="/tongxunlu" action="userGroup"><s:param name="groupid" value="groupid" /></s:url>">
																<s:property value="groupname" />
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
			</section> 
	        <section class="layoutMiddle" style="display:inline-block; width:931px;float:none;margin:20px 0 0 20px">
				<ul class="sy">
					<li class="qb txl_current">
						<a class="linkbh14" href="javascript:void(0)" onclick="selectuserbysm()">全部</a>
						&nbsp;
					</li>
					<li>
						<a class="linkbh14" href="javascript:void(0)" onclick="selectuserbySM(this)">A</a>
						&nbsp;
					</li>
					<li>
						<a class="linkbh14" href="javascript:void(0)" onclick="selectuserbySM(this)">B</a>
						&nbsp;
					</li>
					<li>
						<a class="linkbh14" href="javascript:void(0)" onclick="selectuserbySM(this)">C</a>
						&nbsp;
					</li>
					<li>
						<a class="linkbh14" href="javascript:void(0)" onclick="selectuserbySM(this)">D</a>
						&nbsp;
					</li>
					<li>
						<a class="linkbh14" href="javascript:void(0)" onclick="selectuserbySM(this)">E</a>
						&nbsp;
					</li>
					<li>
						<a class="linkbh14" href="javascript:void(0)" onclick="selectuserbySM(this)">F</a>
						&nbsp;
					</li>
					<li>
						<a class="linkbh14" href="javascript:void(0)" onclick="selectuserbySM(this)">G</a>
						&nbsp;
					</li>
					<li>
						<a class="linkbh14" href="javascript:void(0)" onclick="selectuserbySM(this)">H</a>
						&nbsp;
					</li>
					<li>
						<a class="linkbh14" href="javascript:void(0)" onclick="selectuserbySM(this)">I</a>
						&nbsp;
					</li>
					<li>
						<a class="linkbh14" href="javascript:void(0)" onclick="selectuserbySM(this)">J</a>
						&nbsp;
					</li>
					<li>
						<a class="linkbh14" href="javascript:void(0)" onclick="selectuserbySM(this)">K</a>
						&nbsp;
					</li>
					<li>
						<a class="linkbh14" href="javascript:void(0)" onclick="selectuserbySM(this)">L</a>
						&nbsp;
					</li>
					<li>
						<a class="linkbh14" href="javascript:void(0)" onclick="selectuserbySM(this)">M</a>
						&nbsp;
					</li>
					<li>
						<a class="linkbh14" href="javascript:void(0)" onclick="selectuserbySM(this)">N</a>
						&nbsp;
					</li>
					<li>
						<a class="linkbh14" href="javascript:void(0)" onclick="selectuserbySM(this)">O</a>
						&nbsp;
					</li>
					<li>
						<a class="linkbh14" href="javascript:void(0)" onclick="selectuserbySM(this)">P</a>
						&nbsp;
					</li>
					<li>
						<a class="linkbh14" href="javascript:void(0)" onclick="selectuserbySM(this)">Q</a>
						&nbsp;
					</li>
					<li>
						<a class="linkbh14" href="javascript:void(0)" onclick="selectuserbySM(this)">R</a>
						&nbsp;
					</li>
					<li>
						<a class="linkbh14" href="javascript:void(0)" onclick="selectuserbySM(this)">S</a>
						&nbsp;
					</li>
					<li>
						<a class="linkbh14" href="javascript:void(0)" onclick="selectuserbySM(this)">T</a>
						&nbsp;
					</li>
					<li>
						<a class="linkbh14" href="javascript:void(0)" onclick="selectuserbySM(this)">U</a>
						&nbsp;
					</li>
					<li>
						<a class="linkbh14" href="javascript:void(0)" onclick="selectuserbySM(this)">V</a>
						&nbsp;
					</li>
					<li>
						<a class="linkbh14" href="javascript:void(0)" onclick="selectuserbySM(this)">W</a>
						&nbsp;
					</li>
					<li>
						<a class="linkbh14" href="javascript:void(0)" onclick="selectuserbySM(this)">X</a>
						&nbsp;
					</li>
					<li>
						<a class="linkbh14" href="javascript:void(0)" onclick="selectuserbySM(this)">Y</a>
						&nbsp;
					</li>
					<li>
						<a class="linkbh14" href="javascript:void(0)" onclick="selectuserbySM(this)">Z</a>
						&nbsp;
					</li>
					<div class="clear"></div>
				</ul>
				<div class="xsfs_bg">
					<div class="xsfs" style="float: left;padding-right: 32px;">
						显示方式
						<select id="showway" onchange="phoneshowway()">
							<option value="0">图标</option>
							<option value="1">列表</option>
						</select>
					</div>
					<div class="xsfs">
						<span style="float: right;">
							搜索 &nbsp;&nbsp;&nbsp;
							<input value='<s:property value="searchWord"/>' class="searchbox-text searchbox-prompt" type="text" name="searchWord" id="searchWord"
								style="width: 180px; height: 20px; line-height: 20px;border:1px solid #8fb3ee; border-radius: 5px" onkeyup="searchUserByEnter(event);">
							<span>
								<span class="searchbox-button" style="height: 20px;" onclick="searchUser()">
									<img src="<%=rootPath %>view/images/icons/search.png"></img>
								</span>
							</span>
						</span>
					</div>
					<div class="clear"></div>
				</div>
				<input type="hidden" name="groupId" id="groupId" value="<s:property value="groupid"/>" />
				<div id="userlist"></div>
			</section>
		</section>
		<div id="mainchatwindw" class="chartsiggle" style="border-left:thin solid #E0E0E0; position: fixed;bottom: 41px;right: 1px;height: 475px;width:230px ;background-color:#FDFFFF ;display: none; ">
			<div id="mypersoninfo" style="background-color:#6C6C6C;text-align: left;height: 57px;color: white;padding: 15px 0 0 5px;">
				<div style="float: left;width: 25%;">
					<s:if test="loginuserobj.userpic!=null">
						<img src="<%=rootPath%>file/readfile.action?fileName=<s:property value='loginuserobj.userpic' />" width="45px" />
					</s:if>
					<s:else>
						<img <s:if test="loginuserobj.sex==2">src="<%=rootPath%>view/images/female.jpg"</s:if> <s:else>src="<%=rootPath%>view/images/male.jpg"</s:else> alt="用户头像" width="45px" />
					</s:else>
				</div>
				<div style="float: left;width: 60%;color: white;">
					<p style="white-space: nowrap;overflow: hidden; text-overflow:ellipsis; " title="<s:property value="getShowPageViewNameByUserId(loginuserobj.userid)" />">
						<s:property value="getShowPageViewNameByUserId(loginuserobj.userid)" />
					</p>
					<p style="white-space: nowrap;overflow: hidden; text-overflow:ellipsis; " title="<s:property value="loginuserobj.expertintro" /> ">
						<s:property value="loginuserobj.expertintro" />
					</p>
				</div>
				<div style="cursor:pointer; float: left;width: 15%;color: white;background-repeat: no-repeat;background-position: center;background-image: url('<%=rootPath%>view/images/close2.gif');" title="关闭"
					onclick="closeSolidFrames()">&nbsp;</div>
			</div>
			<div style="height: 47px;">
				<div id="cmfriend" href="<%=rootPath%>user/myfriendsuser.action" data="pageNum=1" style="border-bottom:thin solid #BEBEBE; border-right:thin solid #BEBEBE; " class="mainchatwindwsel">我的好友</div>
				<div id="cmgroup" href="<%=rootPath%>user/mygroups.action" data="pageNum=1" style="border-bottom:thin solid #BEBEBE; " class="mainchatwindwsel">我的分组</div>
				<div id="cmsysfriend" href="<%=rootPath%>user/systemfriendsuser.action" data="pageNumSysfriend=1" style="border-bottom:thin solid #BEBEBE; border-right:thin solid #BEBEBE; "
					class="mainchatwindwsel">系统用户</div>
			</div>
			<div id="chatcontent" style="overflow-y:scroll;height: 356px;overflow-x:hidden;"></div>
		</div>
		<div id="footerchatwindw" style="position: fixed;bottom: 1px;right: 1px;width:230px ;height:40px;cursor: pointer;background-color:#6C6C6C;line-height: 40px;color: #FFFFF4;text-align: center;"
			title="">
			<input type="text" value="1" id="showordisval" style="display: none;">
			即时通讯
		</div>
	</div>
	<div class="footer">
	   <!-- 尾部菜单 -->
	   <jsp:include page="../../view/include/client_foot.jsp" />
    </div>
</body>
</html>