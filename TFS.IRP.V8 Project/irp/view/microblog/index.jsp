<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.tfs.irp.util.LoginUtil"%>
<%@ page import="com.tfs.irp.user.entity.IrpUser"%>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	IrpUser loginUser = LoginUtil.getLoginUser();
%>
<html lang="en" class="no-js">
<head>
<meta charset="utf-8">
<title>微知</title>
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
 <link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css">
<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css">
<link href="<%=rootPath%>view/css/discuz.css" rel="stylesheet" type="text/css" /> 

<style type="text/css">
	li.jinzhao0{ position:relative; display:block; width:50px; height:50px; float:left;}
	li.jinzhao0 .jinzhao1{ display:none;}
	li.jinzhao0:hover .jinzhao1{ display:block; position:absolute;bottom:53px;left:-15px; width:370px; height:;border-radius:4px;-moz-border-radius:4px;-webkit-border-radius:4px; background-color:#f2f2f2;}
	.jinzhao2{ display:block;width:360px;border:1px solid #c6c7cb; margin:4px; background-color:#fff; position:relative;}
	.jinzhao2 span.b222{ display:block;width:360px; height:8px; position:absolute; bottom:-8px; left:0px; background:url(<%=rootPath%>view/images/microblogdivjianjiao.gif) no-repeat 30px 0;}
	.jinzhao2 font,.jinzhao2 font a{ color:#206c7c;}
	.jinzhao2 a:hover{ text-decoration:underline;}
	.jz3{ }
	.jz3 p{ color:#696969;padding:5px 15px;}
	.jz3 p span{ display:block; line-height:20px;}
	.jz4{ line-height:35px; background-color:#f5f5f5; padding:0 15px;overflow:hidden;zoom:1;clear:both ; height:35px; margin:0;}
	.jz4 span{ padding:2px 9px;float:right; border:1px solid #dedede; line-height:20px; height:20px; margin:5px 0;border-radius:1px;-moz-border-radius:1px;-webkit-border-radius:1px; background-color:#fff;}
	.labs dt aside{position:absolute; float:right; left:70px; top:10px; width:0; height:0; border-left:20px solid transparent; border-top:20px solid #ececec;}
select {
    background: none repeat scroll 0 0 #f7f7f7;
    border: 1px solid #bdbdbd;
    border-radius: 3px;
    color: #333;
    font-size: 13px;
    height: 34px;
    line-height: 34px;
    min-width: 80px;
    overflow: hidden;
}
.mainchatwindwsel{
	float: left;
	width: 33%;	
	text-align: center;
	line-height: 40px;
	cursor: pointer;
	background-color: #ADADAD;
}
</style>


<script type="text/javascript" src="<%=rootPath %>view/js/modernizr.custom.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/swfobject.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.Jcrop.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jQuery.UtrialAvatarCutter.js"></script>

<script type="text/javascript" src="<%=rootPath%>dwr/engine.js"></script>  
<script type="text/javascript" src="<%=rootPath%>dwr/util.js"></script> 
<script type="text/javascript" src="<%=rootPath%>dwr/interface/chatvolet.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/ztree/js/jquery.ztree.excheck-3.5.js"></script>
 
<script type="text/javascript" src="<%=rootPath %>view/css/emoticons/code/jquery.emoticons.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/css/emoticons/code/bqth.js"></script>

<link rel="stylesheet" type="text/css" href="<%=rootPath %>view/css/emoticons/public/style/cssreset-min.css">
<link rel="stylesheet" type="text/css" href="<%=rootPath %>view/css/emoticons/public/style/common.css">
 
<script type="text/javascript"> 
	$("#publish_info").val();
	var formatapi;
	//微知输入字数
	var microblognumber = 0;
	//微知图片
	var micrpicall="";
	//一共能上传多少图片
	var uploadpicinall = 3;	
	//微知评论“评论”id +
	var replycommentidpage=0;
	//评论微知字数
	var microblgnumberComment = 0;
	//微知评论 分页id标识
	var microblogidpage =0;
	//微知图片队列
	var microbloguploadfilenum = 1;
	//
	var startloadfilenum = 0;
	//微知图片请求数据
	var microimagescontent = "";
	//微知图片请求名字用于覆盖
	var microblogimagesnamesv = "";
	
	var micropictranids = 1;
	//
	var microblogcontentxfid = 0;
	var namechats,chatuserids,groupchats,groupchatsid;
	var lrefreshs;
	var msgcountreads = 0;
	var unreadarray = new Array();//未读消息数组
	var recordPages = 10;
	var recordPagesByGroup = 10;
	var chatcontentnums =  <s:property value="chatcontentnums" default="600" />;
	var microblogid='<s:property value='MICROBLOGID' />';
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
		loadPersonalCard();
		findAllMicroblog(1);
		initMicroShareNum();
		microblogComNum();
		loadUploadPic();
		
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
					var chatlimits = $.ajax({
			    	     type:"post",
			    	     url:"<%=rootPath%>microblog/getprivacyofmessage.action",
			    	     data:{
			    	        	userid:_userid
			            	},
			            	cache:false,
			            	async:false
			             }).responseText;
					if(chatlimits=="false"){
			    		 $.dialog.tips("由于用户设置，您无法私信。",1,"alert.gif");	
			    		 return false;
			    	}else{
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
					}
		 });
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
	
	
	/**
	* 右侧加载个人基本资料
	*/
	function loadPersonalCard(){
		$.get("<%=rootPath%>microblog/loadUserInfo.action",function(content){
			$(".infomation").prepend(content);
		});
	}
	/**
	* 加载微知评论字数
	*/
	function microblogComNum(){
		$.ajax({
			type:"get",
			url:"<%=rootPath%>microblog/initMicroblogCommentNumberWord.action",
			async:false,
			cache:false,
			success:function(msg){
				microblgnumberComment=parseInt(msg);
			}
		});
	}
	/**
	 *获取微知列表
	 */
	function findAllMicroblog(_num){
		var sUrl = "<%=rootPath%>microblog/microblogAll.action";
		var sHtmlConn = $.ajax({ 
	 		type:'post', 
	 		url: sUrl,
	 		data:{
	 			pageNum:_num
	 		},
	 		dataType: "json",
			async: false,
		    cache: false  
	 	}).responseText;
		$("#microblogcontent").html(sHtmlConn);
		$(".infomation").height($(".weibo").height()-95);
	}
	/**
	* 微知分页
	*/
	function page(pagenums){
		findAllMicroblog(pagenums);
	}
	/**
	 *获取分享微知需要输入的字数
	 */
	function initMicroShareNum(){
		$.ajax({
			type:"get",
			url:"<%=rootPath%>microblog/initMicroblogNumberWord.action",
			async:false,
			cache:false,
			success:function(msg){
				microblognumber = parseInt(msg);
				$('#microblogContentCount_01').text(microblognumber);
			}
		});
	}
	/**
	 *微知内容字数控制
	 */
	function microblogInfoControl(_microblogcontent){
		if(microblognumber-$.trim(_microblogcontent).length>=0){
			$('#microblogContentprompt_02').css({display:'none'});
			$('#microblogContentprompt_01').css({display:'block'});
			$('#microblogContentCount_01').text(microblognumber-$.trim(_microblogcontent).length);
		}else{
			$('#microblogContentprompt_01').css({display:'none'});
			$('#microblogContentprompt_02').css({display:'block'});
			$('#microblogContentCount_02').text(Math.abs(microblognumber-$.trim(_microblogcontent).length));
		}
	}
	personalCard(5,1);
	/**
	* 微知卡片 移进
	*/
	function personalCard(_microblogid,_userid){ 
	 
		var personalCard = $("#"+_microblogid+"div");
		  if(personalCard && !personalCard.attr("binit")){
			  $.ajax({
				  type:"POST",
				  url:"<%=rootPath%>microblog/searchPersonalCard.action",
				  data:{
					  userid:_userid,
					  microblogid:_microblogid
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
			  $('#microblogCard'+_microblogid).css({display:""});
		  }
	}
	/**
	 * 微知卡片移出
	 */
	function personalCardOut(_microblogid){ 
		var microbligdiv ="#microblogCard"+_microblogid;
		$(microbligdiv).css({
			display:"none"
		});
		
	}
	 
	/**
	 *发布微知
	 */
	function publish(){
	
		$('#publishclassselec').unbind('click');
	
		var MicroPicValue_01="";
		var micrarray;
		//判断分享的内容是否存在图片(存在则存入正文图片)
	   	if(micrpicall!=""){
		  $.ajax({
			  type:"post",
			  url:"<%=rootPath%>microblog/microblogContentPicTrue.action",
			  data:{
				  microbloguploadpic:micrpicall
			  },
			  cache:false,
			  async:false,
			  success:function(disposemsg){
			     micrarray = disposemsg.split(" ");
			       for(var i =0;i<micrarray.length-1;i++){
			       	   var disposeid = micrarray[0].toString().substring(0,17);
			    	   var disposeiddics =  disposeid+i;
			    	   if(i==micrarray.length-2){
			    		   MicroPicValue_01+="<img src=\"<%=rootPath %>file/readfile.action?fileName="+micrarray[i]+"\" id="+disposeiddics+" name="+micrarray.toString()+"    height=\"150px\" width=\"140px\" style=\"float:none;margin:3px;cursor:url(<%=rootPath%>view/images/maxpic_03.cur),auto;\"  onclick=\"checkMaxPic(this.src,this.name,this.id,1)\" />";   
			    	   }else{
			    		   MicroPicValue_01+="<img src=\"<%=rootPath %>file/readfile.action?fileName="+micrarray[i]+"\" id="+disposeiddics+" name="+micrarray.toString()+"   height=\"150px\" width=\"140px\" style=\"float:left;margin:3px;cursor:url(<%=rootPath%>view/images/maxpic_03.cur),auto;\" onclick=\"checkMaxPic(this.src,this.name,this.id,1)\" />";
			    	   }
			    	    	
			       }
			  }
		  });
	   	}
	   	//
		var publish_info = $("#publish_info").val();; 
		
	    if(MicroPicValue_01!="" && $.trim(publish_info)==""){
	    	$('#publish_info').val("#分享图片#");
	    }
	    publish_info =  $('#publish_info').val().replace(/</g,"&lt;").replace(/>/g,"&gt;");
  
	    microblogInfoControl(publish_info);
	    
	    if($.trim(publish_info).length>microblognumber){
	    	$('#publishclassselec').bind('click','publish()');
	    	microbloguploadfilenum=1;
	    	return false;
	    }else if($.trim(publish_info).length<=0){
	    	  $.dialog.tips("微知内容不能为空",0.3,"alert.gif");
	    	  $('#publishclassselec').bind('click','publish()');
	    	  microbloguploadfilenum=1;
	    	return false;
	    }else{
	    	var microblog_type;
			var microblog_text= $('#microblogtypetext').val();
			var microbloggroup = null; 
			
			  
			  
	    		$.ajax({
						type:"POST",
						url:'<%=rootPath%>microblog/microblogShare.action',
						cache:false,
						async:false,
						data:{
							publishInfo:formatapi.format(publish_info),
							publishInfoimg:MicroPicValue_01,
							microblogType:microblog_text,
							microbloggroup:microbloggroup
						},
						success:function(callback){
							$('#publish_info').val("");
							
							 document.getElementById("publish_info").innerHTML="";
							$('#microblogContentCount_01').text(microblognumber);
							if(callback!=null){

								$.dialog.tips('分享成功',1,'32X32/succ.png',function(){
									var mymicroblogcount = $('#mymicroblogcount').text();
									if(mymicroblogcount==""){
										mymicroblogcount = 0;
									}
									$('#mymicroblogcount').text(parseInt(mymicroblogcount)+1);

									$("div[name='weibolypics']").remove();
									
									microbloguploadfilenum=1;
									MicroPicValue_01="";
									micrpicall="";
									$('#uploadfilefont').css({display:'none'});
									$("#microblogSharePiclittle").css("display","none");
									startloadfilenum = 0; 
									
									$('#microblogcontent').prepend(callback);		
									
									$('#publishclassselec').bind('click','publish()');
								});
							}else{
								microbloguploadfilenum=1;
								
								$.dialog.tips('分享失败',1,'32X32/fail.png'); 
								$('#publishclassselec').bind('click','publish()');
							}
								$("#microblogPicFile").uploadify('destroy');
							 	loadUploadPic();
						},
						error:function(){
							$("#microblogPicFile").uploadify('destroy');
							loadUploadPic();
							microbloguploadfilenum=1;
							$.dialog.tips('分享失败',1,'32X32/fail.png');
							$('#publishclassselec').bind('click','publish()');
						}
					});
	    }
	}
	/**
	* 添加知识
	*/
	function toaddDocument(){
			window.open('<%=rootPath %>site/client_toadd_document.action');  
	}
	/**
	* 提问
	*/
	function askQuestion(){
		var result = $.ajax({
			url: '<%=rootPath%>expert/askExpert.action',
			dataType: "json",
		    async: false,
		    cache: false
		}).responseText;
		$.dialog({
			title:'提问',
			content: result,
			max: false,
		    min: false, 
		    lock:true,
		    ok: function(){
		    	var questiontitlecontent = $("#questiontitlecontent").val();
		    	var questionInfo=$("#question_info").val();
		    	if(questiontitlecontent == "在此输入问题概要,150字以内"){
		    		questiontitlecontent="";
		    	}
		    	if($.trim(questiontitlecontent)=="" && $.trim(questionInfo)=="" ){
		    		$.dialog.tips("标题或者内容必须填写一项",1.5,"alert.gif");
		    		return false;
		    	}else{
		    		if(questiontitlecontent.length>150){
		    			$.dialog.tips("请填写150字以下的标题内容",1,"alert.gif");
			    		return false;
		    		}else if(questionInfo.length>300){
		    			$.dialog.tips("请填写300字以下的问题内容",1,"alert.gif");
						return false;
		    		}else{
		    			$.ajax({
				    		type:"POST",
				    		data:{
				    			questionInfo:questionInfo,
				    			questiontitle:questiontitlecontent
				    		},
				    		url:'<%=rootPath%>question/ask.action',
				    		dataType: "json",
				    		success:function(Msg){
			    				if(Msg==1){
			    					$.dialog.tips('提问成功',1,'32X32/succ.png');
			    				}else{
			    					$.dialog.tips('提问失败',1,'32X32/fail.png');
			    				}
			    			}
			    		});
		    		}
		    	}
		    },
		    okVal:'提问',
		    cancelVal: '关闭',
		    cancel: true,
		    lock: true,
		    padding: 0
		});
	}
	/**
	* 添加专题
	*/
	function selectSubjectType(){
		var result = $.ajax({
			url : "<%=rootPath %>site/selectNameType.action",
		    async: false,
		    cache: false
		}).responseText;
		$.dialog({
			id:'subjectNameDialog',
			title:'选择专题类型',
			content: result,
			max: false,
		    min: false, 
		    lock:true,
		    width:500,
		    height:150,
		    cancel:function(){ 
	   		},
	   		close:function(){
	   			
		    },
		    cancelVal: '关闭',
		    padding: 0
		});
	}

	/**
	* 转发
	*/	
	function transpond(_showname,_microblogid,_userid,_transpondid,_isdel){
	 
		var commentlimits = "";
		if(_isdel==2){
			$.dialog.tips("抱歉，此微知为非法微知，无法进行转发!",1.5,"tips.gif");
		}else if(_isdel==1){
			//原微知是否已经删除	
			$.dialog.tips("抱歉，此微知已经被删除，无法进行转发!",1.5,"tips.gif");
		}else{
		//同时作为评论给用户评论
		var  microblogtranspondcount="#microblogtranspondcount"+_microblogid;
		var microblogcommentcount ="#microblogcommentcount"+_microblogid;
		//获得内容
		var shownames = encodeURI(_showname);
		
		$.ajax({
			url:'<%=rootPath%>microblog/transpondMicroblog.action',
			type:'post',
			cache:false,
			async:false,
		    data:{
		    	showname:shownames,
				microblogid:_microblogid,
				micrtranspondid:_transpondid
		    },
		    success:function(result){
		$.dialog({
			title:'转发至我的微知',
			max:false,
			min:false,
			lock:true,
			resize: false,
			width:'500px',
			height:'150px',
			content:result,
			cancelVal:'关闭',
			okVal:'转发',
			cancel:true,
	        init:function(){
	        	 var tea = document.getElementById("transpondInfo");
	        	    if (tea.setSelectionRange){ 
	        	        setTimeout(function() { 
	        	        tea.setSelectionRange(0, 0); //将光标定位在textarea的开头，需要定位到其他位置的请自行修改 
	        	        tea.focus(); 
	        	        }, 0); 
	        	        }else if(tea.createTextRange){ 
	        	        
	        	        	var range = tea.createTextRange();
	        	        	range.moveStart("character",1);
	        	        	range.collapse(true);
	        	        	//alert(range.text.length);
	        	        	//txt.moveStart('character',obj.value.length);
	        	        	range.select();
	        	        } 
			},
			ok:function(){
				//转发 
				var transpondInfo =  $("#transpondInfo").val();
				if($.trim(transpondInfo).length>tranmicronumberwrod){	
					return false;
				}else{
					if($("input[type='checkbox']").is(':checked')){
						//获取隐私设置
				         commentlimits = $.ajax({
					    	     type:"post",
					    	     url:"<%=rootPath%>microblog/getprivacyofcomment.action",
					    	     data:{
					    	        	userid:_userid
					            	},
					            	cache:false,
					            	async:false
					             }).responseText;
					    	
					}
			    	
					if($.trim(transpondInfo).length==0){
						$('#transpondInfo').val("转发微知");
					}
					//transpondInfo =  editor.getData();
				$('#transpondcontent1').form('submit',{
					url:'<%=rootPath%>microblog/addTranspondMicroblog.action',
					onSubmit:function(){
						//处理表单数据
						$("#div_07").css("display","none");
					    var trancontent = $("#transpondInfo").val(); 
					    trancontent =  trancontent.replace(/</g,"&lt;").replace(/>/g,"&gt;");
					    $("#transpondInfo").val(formatapi.format(trancontent));
					     
					    
						if(commentlimits!="false"){
							if($("input[type='checkbox']").is(':checked')){
						    	//同时评论该微知		
						    	
						    	var content_1 = $.trim(transpondInfo);
						    	content_1 =  content_1.replace(/</g,"&lt;").replace(/>/g,"&gt;");
						    	content_1 = formatapi.format(content_1); 
						    	
						         $.ajax({
				                     type:"post",
				                     url:"<%=rootPath%>microblog/microblogComment.action",
				                     cache:false,
				                     data:{
				                       replyuserid:_userid,
				                       replaymicroblogid:_microblogid,
				                       replaycontent:content_1,
				                       replycommentidpage:replycommentidpage 
				                       },
				                       success:function(callback){
				               			if(callback!=null){
				               				$(microblogcommentcount).text(parseInt($(microblogcommentcount).text())+1);
				               				return true;
				               			}else{
				               				$.dialog.tips('转发时同时评论失败',1,'32X32/fail.png');
				               				return false;
				               			}
				               			
				                       }
						           });
						    }else{
				                return true;
				            }
						}
					},
					success:function(callback){
						if(callback!=null){
							if(commentlimits=="false"){
					    		$.dialog.tips('转发成功,但是由于用户设置,评论失败',2,'32X32/hits.png',function(){
									$(microblogtranspondcount).text(parseInt($(microblogtranspondcount).text())+1);
							
										$('#microblogcontent').prepend(callback);		
									
								});
					    	}else{
					    		$.dialog.tips('转发成功',1,'32X32/succ.png',function(){
									$(microblogtranspondcount).text(parseInt($(microblogtranspondcount).text())+1);
					
										$('#microblogcontent').prepend(callback);		
								
								});		
					    	}		
						}else{
							$.dialog.tips('转发失败',1,'32X32/fail.png');	
						}
					},
					error:function(){
						$.dialog.tips('转发失败',1,'32X32/fail.png');
					}
				});
				}
			}
		});
		
		
		
		
		
		
		
		
		    }
		
		});
		

		}
	}
	/**
	* 收藏
	*/
	function collect(_microblogid){
	var shoucang = "#"+_microblogid;
	var collectionvalue = $(shoucang).text();
	if(collectionvalue=='收藏'){
			$.ajax({
				type:'POST',
				url:'<%=rootPath%>microblog/microblogcollect.action',
			    dataType: "json",
			    async:false,
				data:{
					microblogid:_microblogid
				},
			    success:function(msg){
			    	var mydoccollectioncount = $('#mydoccollectioncount').text();
			    	if(mydoccollectioncount==""){
			    		mydoccollectioncount = 0;
			    	}
			    	$('#mydoccollectioncount').text(parseInt(mydoccollectioncount)+1);
			    	if(msg==1){
			    		$.dialog.tips('收藏成功',0.5,'32X32/succ.png',function(){});
			    		$(shoucang).prepend("取消");
			    	}else{
			    		$.dialog.tips('收藏失败',1,'32X32/fail.png');
			    	}
			    },
			    error:function(){
			    	$.dialog.tips('收藏失败',1,'32X32/fail.png');
			    }
			});
		}else{
			$.dialog.confirm('您确定要取消收藏这条微知吗',function(){
			$.ajax({
				type:'POST',
				url:'<%=rootPath%>microblog/microblogcollectcancel.action',
				dataType:"json",
				async:false,
				data:{
					microblogid:_microblogid
				},
				success:function(data){
					var mydoccollectioncount = $('#mydoccollectioncount').text();
					if(mydoccollectioncount==""){
						mydoccollectioncount = 0;
					}
					$('#mydoccollectioncount').text(parseInt(mydoccollectioncount)-1);
					if(data==1){
						$(shoucang).text("收藏");
					}else{
						$.dialog.tips('取消错误',1,'32X32/fail.png');
					}
				},
				error:function(){
					$.dialog.tips('取消错误',1,'32X32/fail.png');
					
				}
			});
			});
		}
	}
	/**
	* 微知删除
	*/
	function deleteMicroBlog(_microblogid){
	var shoucangdiv = "#"+_microblogid+"div";
	$.dialog.confirm('你确定要删除这条微知吗',function(){
		$.ajax({
			type:"POST",
			url:'<%=rootPath%>microblog/deletemicroblog.action',
			dataType:"json",
			data:{
			microblogid:_microblogid	
			},
			success:function(msg){
				
				if(msg==1){
					$('#mymicroblogcount').text($('#mymicroblogcount').text()-1);
					$(shoucangdiv).fadeOut();
				}else{
					$.dialog.tips('删除失败',1,'32X32/fail.png');
				}
			},
			error:function(){
				$.dialog.tips('删除失败',1,'32X32/fail.png');
			}
		});
	}
	);	
	}
	/**
	* 微知举报
	*/
	function informdetail(microblogid){
		var loadPage=$.ajax({
			url: '<%=rootPath%>microblog/informframe.action', 
			data:{
				microblogid:microblogid
			},
			type:"post", 
		    async: false,
		    cache: false
		}).responseText; 
		 $.dialog({
			 title:'举报',
				max:false,
				min:false,
				lock:true,
				resize: false,
				width:500,
				content:loadPage,
				okVal:'举报',
				ok:function(){
					var informkeykind=$("input[name=informkeykind]:checked").val();
							
					if(informkeykind==undefined){
						$.dialog.tips("请选择举报类型",1.5,"alert.gif");
						return false;
					}		
					$("#informdescform").val($("#informdescform").val()+"  "+$("input[name=informkeykind]:checked").attr("title"));
					var informdescs = $("#informdescform").val();
				
					 if($.trim(informdescs).length>$('#informdesccount').val()){
					    	return false;
					    }else{
					    	$('#informform').form('submit',{
			 					url:'<%=rootPath%>microblog/saveinformdesc.action',
			 					cache:false,
			 					data:{
			 						informkeykind:informkeykind
			 					},
			 					success:function(msg){
			 						if(msg==1){
			 							
			 							$.dialog.tips('提交成功',1,'32X32/succ.png');					
			 						}else{	
			 							$.dialog.tips('提交失败',1,'32X32/fail.png');	
			 						}	
			 					}
			 				});	
					    
					    }
				},
				cancelVal:"关闭",
				cancel:function(){
					  
				}
		 });
	
	}
	/**
	* 展开评论
	*/
	function microblogComment(_microblogid){	
	 
			var commentdiv = "#commentDiv"+_microblogid;
			var appandspancomment = "#apendSpanMicroComment"+_microblogid;
			var commentdivinfo="#commentInfo"+_microblogid;
			var commenttextarea = "#microCommentFontCount_01"+_microblogid;
			var twoname="commentInfo"+_microblogid;
			
			$(commentdivinfo).val("");
			$(appandspancomment).empty(); 
			
			if($(commentdiv).css("display")=="none"){
				microblogidpage = _microblogid;
				$.ajax({
					type:"post",
					cache:false,
					url:"<%=rootPath%>microblog/microblogReplyList.action",
				cache:false,
				data:{
					microblogid:_microblogid
				},
				success:function(callback){
    			 
				 
				  $(commenttextarea).text(microblgnumberComment);
				  $(commentdiv).slideDown();
				  $(appandspancomment).append(callback);
				}
			});
		}else{
			 $(commentdiv).slideUp(); 
		}
	}
	/**
	* 评论分页
	*/
	function pageComment(_nPageNum){
	 var appandspancomment = "#apendSpanMicroComment"+microblogidpage;
		$('#pageNum').val(_nPageNum);
		var queryString = $('#pageForm').serialize();	     
		$.ajax({
			type:"post",
			cache:false,
			url:"<%=rootPath%>microblog/microblogReplyList.action?microblogid="+microblogidpage+"&"+queryString,
			success:function(callback){
				$(appandspancomment).empty();
			  	$(appandspancomment).append(callback);
			  }
		});	
	}
	/**
	* 回复回复的评论
	*/
	function replyCommentReply(_microblogid,_showname,_content,_commentid){
		var commentInfoid ="#commentInfo"+_microblogid;
		replycommentidpage=_commentid;
		 var twoname="commentInfo"+_microblogid; 
		var commentInfoid ="#commentInfo"+_microblogid;
		var commentInfo =  $.trim($(commentInfoid).val()); 
		if(commentInfo!=""){
			commentInfo=commentInfo.replace("<p>","").replace("</p>","");
		} 
	    $(commentInfoid).val($(commentInfoid).val()+"回复@"+_showname+": ||"+_content);
	    $(commentInfoid).focus();
	} 
	/**
	* 控制回复内容的长度
	*/
	function microblogCommentFontInfo(_microblogid){
		//选中的评论框
		var commentTextArea =  "#commentInfo"+_microblogid;
		var microCommentFont_01 = "#microCommentFont_01"+_microblogid;
		var microCommentFontCount_01 = "#microCommentFontCount_01"+_microblogid;
		var microCommentFont_02 = "#microCommentFont_02"+_microblogid;
		var microCommentFontCount_02 = "#microCommentFontCount_02"+_microblogid;
		 var twoname="commentInfo"+_microblogid;
    
		var commentTextAreaLength = $.trim($("#"+twoname).val());
		if(microblgnumberComment-$.trim(commentTextAreaLength).length==microblgnumberComment){
			replycommentidpage=0;	
			
		}
		if(microblgnumberComment-$.trim(commentTextAreaLength).length>=0){
			$(microCommentFont_02).css({display:'none'});
			$(microCommentFont_01).css({display:'block'});
			$(microCommentFontCount_01).text(microblgnumberComment-$.trim(commentTextAreaLength).length);
		}else{
			$(microCommentFont_01).css({display:'none'});
			$(microCommentFont_02).css({display:'block'});
			$(microCommentFontCount_02).text(Math.abs(microblgnumberComment-$.trim(commentTextAreaLength).length));
		}
		
		
	}
	/**
	* 评论回复
	*/
	function commentReply(_microblogid,_userid){
	 var twoname="commentInfo"+_microblogid;
     
		var commentInfoid ="#commentInfo"+_microblogid;
		var commentInfo =  $.trim($(commentInfoid).val());
		 
		 
		var appandspancomment = "#apendSpanMicroComment"+_microblogid;
		var microblogcommentcount ="#microblogcommentcount"+_microblogid;
		var microCommentFontCount_01 = "#microCommentFontCount_01"+_microblogid;
		microblogCommentFontInfo(_microblogid); 
	  	//获取隐私设置
	    var commentlimits = $.ajax({
	    	     type:"post",
	    	     url:"<%=rootPath%>microblog/getprivacyofcomment.action",
	    	     data:{
	    	        	userid:_userid
	            	},
	            	cache:false,
	            	async:false
	             }).responseText;
	    	if(commentlimits=="false"){
	    		 $.dialog.tips("由于用户设置，您无法回复评论。",1,"alert.gif");	
	    		return false;
	    	}else{
		  if($.trim(commentInfo).length>microblgnumberComment){
		    	return false;
		    }else if($.trim(commentInfo).length<=0){
		    	 $.dialog.tips("评论内容不能为空",0.3,"alert.gif");	
		    	return false;
		    }else{	
		    	commentInfo =  commentInfo.replace(/</g,"&lt;").replace(/>/g,"&gt;");
		    	  
		    		$.ajax({
		    			type:"post",
		    			url:"<%=rootPath%>microblog/microblogComment.action",
		    			cache:false,
		    			data:{
		    			 replyuserid:_userid,
		    			 replaymicroblogid:_microblogid,
		    			 replaycontent:formatapi.format(commentInfo),
		    			 replycommentidpage:replycommentidpage 
		    			},
		    			success:function(callback){
		    				$(microCommentFontCount_01).text(microblgnumberComment);
		    				if(callback!=null){
		    					$(microblogcommentcount).text(parseInt($(microblogcommentcount).text())+1);
		    					$(commentInfoid).val(""); 
		    					replycommentidpage=0;
		    					$(appandspancomment).prepend(callback);
		    					
		    				}else{
		    					
		    					$.dialog.tips('评论失败',1,'32X32/fail.png');	
		    				}
		    			  },
		    			error:function(){
		    				$(microCommentFontCount_01).text(microblgnumberComment);
		    				$.dialog.tips('评论失败',1,'32X32/fail.png');
		    				
		    			}
		    		});	    		
		    	}
		    }
	}
	/**
	* 删除微知评论回复
	*/
	function deleteMicroBlogComment(_commentid,_microblogid){
	 var microblogCommentSpan ="#commentReply"+_commentid
	 var microblogcommentcount ="#microblogcommentcount"+_microblogid;
	 $.dialog.confirm('你确定要删除这条评论吗',function(){
		 $.ajax({
			 type:"POST",
			url:'<%=rootPath%>microblog/deletemicroblogComment.action',
			data:{
				commentid:_commentid,
				replaymicroblogid:_microblogid
			},
			success:function(_msg){
				if(_msg==1){
					$(microblogcommentcount).text(parseInt($(microblogcommentcount).text())-1);
					  $(microblogCommentSpan).fadeOut();
					  $(microblogCommentSpan).fadeOut("slow");
					  $(microblogCommentSpan).fadeOut(2000);
				}else{
					$.dialog.tips('删除评论回复失败',1,'32X32/fail.png');
				}
			},
			error:function(){
				$.dialog.tips('删除评论回复失败',1,'32X32/fail.png');
			}
		 });  
	 });
	}
	/**
	* 变换评论分页针对微知id
	*/
	function microblogdeletejudge(_microblogid,_transponids){
		microblogidpage=_microblogid;
	
		if(_transponids==0){
			micropictranids = 1;
		}else{
			micropictranids = 2;
		}
			
	}
	/**
	* 微知预览 增加 新图片
	*/
	function addMicroPDiv(){
		//$("#microblogPicFile").uploadify("upload");	
	}
	/**
	* 加载上传组件
	*/
	function loadUploadPic(){
		//加载上传图片张数
		$.ajax({
			type:"get",
			url:"<%=rootPath%>microblog/initMicrobloguploadpicnum.action",
			async:false,
			cache:false,
			success:function(msg){
				uploadpicinall = parseInt(msg);
			}
		});
		$("#microblogPicFile").uploadify({
			'auto': true,
			'multi' : false,
			'swf' : '<%=rootPath%>view/js/uploadify.swf',
			'uploader' : '<%=rootPath%>microblog/microblogContentPic.action;jsessionid=<%=session.getId() %>',
			'formData':{
	            'userId':'1'
	        },
			'queueID' : 'fileList',
			'fileObjName' : 'picFile',
			'buttonText' : '',
			'width' : "95",
			'height' : "95",
			'removeCompleted': true,
			'removeTimeout': 0.1,
			'uploadLimit':999,
			'queueSizeLimit': 3,      //允许同时上传文件数量
			'fileSizeLimit' : "1000MB",
			'fileTypeExts': '*.jpg;*.jpeg;*.gif;*.png',
	        'fileTypeDesc': '图片文件',
	        'onUploadStart' : function(file){
	        	$.dialog.tips('图片上传中...',600,'loading.gif');
	        }, 
			'onUploadSuccess' : function(file, data, response){	
				$.dialog.tips('图片上传完毕',1,'32X32/succ.png',function(){
					//$("#addmicrodivxvs").remove();
					$("#microblogSharePiclittle").css("display","block");
					var imgStr = '<%=rootPath%>file/readfile.action?fileName='+data;
					var microspicid = data.split(".")[0];
					
					
					var micropicdesc = microspicid+"picdesc";
					var microblogwdivs = microspicid+"picdivw";
					
					$('#microblogPicFile').before("<div id="+microblogwdivs+" name=\"weibolypics\" style=\"width:100px;height:100px;display:inline;float:left;margin-left:2px;margin-top:10px;margin-right:5px;\"><img id="+microspicid+" style=\"margin-left: 9px; \" src=\""+imgStr+"\" width=\"100px\" height=\"100px\" /><a  id="+micropicdesc+"  style=\"background-color: black;color: white;position: absolute;z-index:10;margin-left: -28px;font-size:12px;padding:0 2px 0 2px;\"  href=\"javascript:void(0)\" title=\"取消选择的图片\" onclick=\"micrPicCancel('"+data+"')\">取消</a></div>");
					microbloguploadfilenum++;
					micrpicall+=data+";";
					
					$('#uploadfilefont').css({display:'block'});
				   	startloadfilenum = parseInt(startloadfilenum+1);
		            if(startloadfilenum>=uploadpicinall){
		            	$('#microblogPicFile-button').css("display","none");
		            	$("#microblogPicFile").uploadify('disable',true);
					}else{
						$("#microblogPicFile").uploadify('disable',false);
					}
					$('#uploadfilefont').html("本地上传&nbsp;|&nbsp;共"+startloadfilenum+"张,还能上传"+parseInt(uploadpicinall-startloadfilenum)+"张<img src=\"<%=rootPath%>view/images/microadddivs.png\" title=\"关闭\" style=\"float:right;margin-right:12px;margin-top:2px;cursor:pointer;\" onclick=\"closeMDiv()\" />");
				});
			},
			'onUploadError': function (file, errorCode, errorMsg, errorString) {
				$.dialog.tips('数据加载完毕',1,'32X32/fail.png',function(){
					alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
				});
			}
		});
		
		$("#microblogPicFile").css({
			"margin-left":"12px",
			"margin-top":"12px",
			"float":"left",
			"width":"95px",
			"height":"95px"
		});
	}
	/**
	* 显示增加图片图层
	*/
	function addMicroPic(){		
		var microblogSharePiclittletext = $("#microblogSharePiclittle").css("display");
		if(microblogSharePiclittletext=='block'){
			return false;
		}
		
		
		
		$("#microblogSharePiclittle").css("display","block");
		$('#uploadfilefont').css({display:'block'});
		
		$('#uploadfilefont').html("本地上传&nbsp;|&nbsp;共"+startloadfilenum+"张,还能上传"+parseInt(uploadpicinall-startloadfilenum)+"张<img src=\"<%=rootPath%>view/images/microadddivs.png\" title=\"关闭\" style=\"float:right;margin-right:12px;margin-top:2px;cursor:pointer;\" onclick=\"closeMDiv()\" />");

		$('#microblogPicFile-button').append("<div id=\"addmicrodivxvs\" style=\"width:100px;height:100px;display:inline;\"><img onclick=\"addMicroPDiv()\"  style=\"margin-top: -2px;cursor:pointer;\" src=\"<%=rootPath%>view/images/addmicrodivs.jpg\" width=\"100px\" height=\"100px\" /></div>");
		if(parseInt(uploadpicinall-startloadfilenum)<=0){
			$('#microblogPicFile-button').css("display","none");
		}
		
	}
	/**
	* 取消上传图片
	*/
	function micrPicCancel(_cancelpic){
		var disposecancelpic = _cancelpic.split(".")[0];
		var micropidcesc = "#"+disposecancelpic+"picdesc";
		var micropidivw = "#"+disposecancelpic+"picdivw";
		var cancelpicdiv = "#"+disposecancelpic;
		
		$.ajax({
			type:"POST",
			url:"<%=rootPath %>microblog/microblogPicDelete.action",
			data:{
				microblogPic:_cancelpic
			},
			async:false,
			success:function(msg){
			  if(msg==1){
			  	$(micropidivw).fadeOut("fast",function(){
			  		$(cancelpicdiv).fadeOut("fast",function(){
			  			$(micropidcesc).remove();
			  			
					   startloadfilenum = parseInt(startloadfilenum-1);
			            if(startloadfilenum>=uploadpicinall){
			            	$("#microblogPicFile").uploadify('disable',true);
						}else{
							$("#microblogPicFile").css("display","block");
							$("#microblogPicFile-button").css("display","block");
							$("#microblogPicFile").uploadify('disable',false);
							
						}
						$('#uploadfilefont').html("本地上传&nbsp;|&nbsp;共"+startloadfilenum+"张,还能上传"+parseInt(uploadpicinall-startloadfilenum)+"张<img src=\"<%=rootPath%>view/images/microadddivs.png\" title=\"关闭\" style=\"float:right;margin-right:12px;margin-top:2px;cursor:pointer;\" onclick=\"closeMDiv()\" />");
			  		});
			  	
			  	});
				}  
			}
			
		});
		
	}
	/**
	* 关闭微知添加图片层
	*/	
	function closeMDiv(){
		$("#microblogSharePiclittle").css("display","none");
		$('#uploadfilefont').css({display:'none'});
		$("#microblogPicFile").uploadify('destroy');
	 	loadUploadPic();
	}
	/**
	* 点击查看微知图片
	*/
	
	function checkMaxPic(_src,_name,_ids,_bid){
		if("m_pic"==_src && _src==_name){
			if(microimagescontent!=""){
				var idsdiv = "#"+_ids+"div";
				$(idsdiv).find("img[name='"+microblogimagesnamesv+"']").css("display","none");
		
				if(_bid==1){
					var imagesdiv =	"#microcontents"+_ids;
				}else if(_bid==2){
				
					var imagesdiv =	"#microcontentstran"+_ids;
				}
			    var shownobiaoshis ="shownobiaoshis"+_ids;
			    $(imagesdiv).attr("onclick","");
			    $(imagesdiv).append("<div id='"+shownobiaoshis+"'>"+microimagescontent+"</div>");
			}else{
				
			
			}
		}else{
			microblogimagesnamesv = _name;
		    $.ajax({
		    	type:"get",
		    	url:"<%=rootPath%>microblog/checkMaxPic.action?picfile="+_src+"&picfilenamelist="+_name+"&picfileids="+_ids+"&boolcid="+_bid+"&micropicblogid="+microblogidpage,
		    	cache:false,
		    	async:false,
		    	success:function(content){
		    		microimagescontent = content;
		    		
		    	}
		    
		    });
		}

	}

	function giveImagesValues(_microblogid){
			microblogcontentxfid = _microblogid;
	}
</script>

</head>
<body>
	<form id="pageForm">
		<s:hidden name="pageNum" id="pageNum" />
		<s:hidden name="pageSize" id="pageSize" />
	</form> 
<s:include value="../include/client_head.jsp"></s:include>

<section class="mainBox">
	<nav class="publicNav">
		
    </nav>
</section>

		
		
<section class="mainBox">
	<section class="weibo" style="margin-left:15px;">
	
    	<div class="area20" style="background-image: url('../images/02.jpg');"></div>
    	<!--微知用输入框--> 
    	
    	
        <div class="maintext">
			
			
			
			
			<div style="text-align: right;float: right;"> 
				&nbsp;<section  id="microblogContentprompt_01" class="textCount" >您还可以输入<font id="microblogContentCount_01"></font>字</section><section id="microblogContentprompt_02" class="textCount" style="display: none;">超出了<font id="microblogContentCount_02"></font>字</section>
			</div>
			
			
			<!-- 微知框输入 -->
			<div class="emoticons">
				<div class="publishercss" atype="nx" id="nxid">
					<p><textarea style="font-size: 14px;" name="content" onkeyup="microblogInfoControl(this.value)" id="publish_info"></textarea></p>
					 
				</div> 
			</div>
			<!-- 微知框输出 -->
			
			
			
			
			
			
			
			
				<script type="text/javascript">
				$(function(){
					$.emoticons({
						'activeCls':'trigger-active', 
						'publisherCls':'publishercss',
						'path':'<%=rootPath%>view/css/emoticons/public/image/',
					},function(api){ 
						formatapi = api; 
					});
					
				 
				});
				</script>
				<style type="text/css"> 
					
			.widget-layer{
				position: relative;
				width: 410px;
				margin-top: -25px;
				margin-left:6px;
				background: #fff;
				border: 1px solid #dbdbdb;
				border-radius: 2px; 
				z-index: 111111;
			}
			.widget-layer:before{
				position: absolute;
				top: -16px;
				left: 2px;
				display: block;
				content: '';
				width: 0;
				height: 0;
				border: 8px solid transparent;
				border-bottom-color: #dbdbdb;
				z-index: 111111;
			}
			.widget-layer:after{
				position: absolute;
				top: -15px;
				left: 2px;
				display: block;
				content: '';
				width: 0;
				height: 0;
				border: 8px solid transparent;
				border-bottom-color: #f0f0f0;
				z-index: 111111;
			}
			.widget-layer .widget-tool{
				height: 28px;
				background: #f0f0f0;
				z-index: 111111;
			}
			.widget-layer .widget-close{
				float: right;
				width: 28px;
				height: 28px;
				line-height: 28px;
				text-align: center;
				font-family: Arial;
				z-index: 111111;
			}
			.widget-layer ul{
				width: 372px;
				margin: 0 auto;
				padding: 15px 5px 20px;
				overflow: hidden;
				z-index: 111111;
			}
			.widget-layer li{
				position: relative;
				z-index: 8;
				float: left;
				width: 22px;
				height: 22px;
				padding: 4px;
				margin-left: -1px;
				margin-top: -1px;
				border: 1px solid #e8e8e8;
				cursor: pointer;
				z-index: 111111;
			}
			.widget-layer li:hover{
				z-index: 111111;
				border-color: #eb7350;
			}
					
					
				</style> 
           		<a class="trigger"   comid="publish_info" href="javascript:;" style="font-size: 30px;float: left;">☺</a>
           		<div style="float: left;width: 430px;">&nbsp;</div> 
           	 
            <aside class="sub" style="float: left;">

            <!-- 	<a href="javascript:void(0)" onclick="toaddDocument()" style="background: url('');">知识</a> -->
            	<a href="javascript:void(0)" style="background: url('');" onclick="addMicroPic()" >图片</a>
            	
            	
            	<a href="javascript:void(0)" onclick="selectSubjectType()" style="background: url('');">专题</a>
            	
				<a style="background: url('');border: none;border-radius:0;width: auto;padding: 0px 10px 10px 10px;"  ><select id="microblogtypetext"><option value="0">公开</option><option value="1" >私密</option></select> </a>
            	<input id="publishclassselec" type="submit" onclick="publish()" value="发&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;布"/>
            	
            </aside>
       
            <div class="clear">
            
            	<div id="microblogSharePiclittle" style="border:1px solid #E0E0E0;padding: 0px 2px 15px 2px;display: none;">
            		
            		<div  style="width: 796px;height: 30px;border-bottom: 1px solid #E0E0E0;">
            			<li id="uploadfilefont" style="display: none;padding: 5px 0 2px 4px; font-size: 13px;color: #666;"></li>
            		</div>
            		<input  type="file" name="microblogPicFile" id="microblogPicFile" />
            		<div style="clear:both;"></div>
            	</div>
            </div>
        </div>
        
        
        
        
        
        
        
        
        
       
        <div class="area20"></div>
      	<section class="discussions" id="microblogcontent">
			
        </section>
    </section>
	<script>
    	$(document).ready(function(){
			$(".infomation").height($(".weibo").height());
			});
		
    </script>
	<aside class="infomation">
			<div class="area10"></div>
			<div id="mainchatwindw" class="chartsiggle" style="border-left:thin solid #E0E0E0; position: fixed;bottom: 41px;right: 73px;height: 475px;width:346px ;background-color:#FDFFFF ;display: none; ">
				<div id="mypersoninfo" style="background-color:#6C6C6C;text-align: left;height: 57px;color: white;padding: 15px 0 0 5px;">
						<div style="float: left;width: 25%;">
						<s:if test="loginuserobj.userpic!=null">
							<img style="border-radius:25px;" src="<%=rootPath%>file/readfile.action?fileName=<s:property value='loginuserobj.userpic' />"   width="45px"   />
						</s:if>
						<s:else>
							<img style="border-radius:25px;" <s:if test="loginuserobj.sex==2">src="<%=rootPath%>client/images/female.jpg"</s:if><s:else>src="<%=rootPath%>client/images/male.jpg"</s:else> alt="用户头像" width="45px"   />
						</s:else>
						</div>
						<div style="float: left;width: 60%;color: white;">
							<p style="white-space: nowrap;overflow: hidden; text-overflow:ellipsis; " title="<s:property value="getShowPageViewNameByUserId(loginuserobj.userid)" />"><s:property value="getShowPageViewNameByUserId(loginuserobj.userid)" /></p>
							<p style="white-space: nowrap;overflow: hidden; text-overflow:ellipsis; " title="<s:property value="loginuserobj.expertintro" /> "><s:property value="loginuserobj.expertintro" /> </p> 
						</div>
						 
						<div style="cursor:pointer; float:right;width: 15%;color: white;background-repeat: no-repeat;background-position: center;background-image: url('<%=rootPath%>client/images/close2.gif');" title="关闭" onclick="closeSolidFrames()">&nbsp;</div>
				</div>
				<div style="height: 47px;">
					<div id="cmfriend" href="<%=rootPath%>user/myfriendsuser.action" data="pageNum=1" style="border-bottom:thin solid #BEBEBE; border-right:thin solid #BEBEBE; " class="mainchatwindwsel">我的好友</div>
					<div id="cmgroup" href="<%=rootPath%>user/mygroups.action" data="pageNum=1" style="border-bottom:thin solid #BEBEBE; " class="mainchatwindwsel">我的分组</div>
					<div id="cmsysfriend" href="<%=rootPath%>user/systemfriendsuser.action" data="pageNumSysfriend=1" style="border-bottom:thin solid #BEBEBE; border-right:thin solid #BEBEBE; " class="mainchatwindwsel">系统用户</div>		
							
				</div>
				<div id="chatcontent"  style="overflow-y:scroll;height: 356px;overflow-x:hidden;" >
			 
				</div> 
			</div>
			
             <div style="float: left;width: 320px;padding: 0 20px 20px 20px;"> 
                <s:iterator value="listmedal" status="listStat">
                	 	<s:if test="medalimage!=null">
                	 		<s:iterator value="medalimage.split(',')" var="attid"  status="index1">
		                 		<s:if test="#index1.first">
		                 			 <div class="medal" style="width: 80px;margin-top: 10px;float: left;" >
		                  			 <a title="<s:property value='medalname' />:<s:property value="userCount" />" style="border-radius: 0px;height: auto;width: 60px;"href="<s:url action="medalDetial" namespace="/medal"><s:param name="medalid" value="medalid" /></s:url>"  target="_blank">
		                    		 <img src="<s:property value='coverPath(#attid)' />"  width="60" height="60" style="cursor: pointer;" title="<s:property value='medalname' />:<s:property value="userCount" />"><br/> 
		                    		 <span><s:property value='medalname' /></span>
		                    		 </a>
		                 		 </div>
		                    </s:if>
		                   </s:iterator>
                	 	</s:if>
                	  <s:else>
                	  <div  class="medal" >
                	   <a title="<s:property value='medalname' />:<s:property value="userCount" />"style="border-radius: 0px;height: auto;width: 60px;" href="<s:url action="medalDetial" namespace="/medal"><s:param name="medalid" value="medalid" /></s:url>"  target="_blank">
                    <img src="<%=rootPath %>client/images/bjt.png"  width="15" height="15" style="border-radius: 10px;cursor: pointer;" title="<s:property value='medalname' />:<s:property value="userCount" />"> 
                    <span><s:property value='medalname' /></span>
                     </a>
               	</div>
                </s:else> 
                </s:iterator>
            </div>
			<div id="footerchatwindw" style="position: fixed;bottom: 1px;right: 73px;width:346px ;height:50px;cursor: pointer;background-color:#6C6C6C;line-height: 50px;color: #FFFFF4;text-align: center;" title=""  >
				<input type="text" value="1" id="showordisval" style="display: none;" >
				即时通讯
			</div>
    </aside>
</section>
<div class="area10"></div>
<s:include value="../include/client_foot.jsp"></s:include>
</body>
</html>
