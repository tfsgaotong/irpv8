<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>

<!doctype html>
<html lang="en" class="no-js">
<head>
<meta charset="utf-8">
<title>
<s:if test="microblogid==null">
个人信息11
</s:if>
<s:elseif test="microblogid!=null">
查看源微知
</s:elseif>
</title>
<link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath %>view/css/easyui.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath %>view/css/icon.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath %>view/css/discuz.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.line{
	border-top: 1px solid #5F9DDD;
}
.type1 li{width:278px;}
</style>

<script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/modernizr.custom.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath %>editor/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<%=rootPath %>editor/applet/ckwordup.js"></script>
<script type="text/javascript">
		function searchInfo1(searchInfo){  
			searchtype = 5;  
			if(searchInfo.length>38){
				searchInfo = searchInfo.substring(0,37);	
			}
			var eacapeInfo = encodeURI(searchInfo);
		    window.open('<%=rootPath%>solr/searchcontentofsolr.action?searchInfo='+eacapeInfo+'&searchtype='+searchtype); 
	}
//分页评论变量
var microblogidpage = 0;
//微知评论“评论”id +
var replycommentidpage=0;
//微知细览的发送评论限制
var microblogfineandlooknumber = 0;
//评论微知字数
var microblgnumberComment = 0;
//转发字数限制
var tranmicronumberwrod = 0;
$(function(){
	
	/* 	var editor=CKEDITOR.replace('editor',{
					smiley_columns:10,
				   language : 'zh-cn',
				  width : 800,
				  height : 150,
				  uiColor : '#FFFFdd',
				   toolbar : 'Basic',
				    toolbar_Basic : [  ['Smiley']  ],
				   toolbarCanCollapse: false,
				    toolbarLocation: 'bottom',
				    resize_enabled : false,
				    dialog_backgroundCoverOpacity : 0.5
				});
			editor.on("key", function (event) {  
          var data = this.getData();
           document.getElementById("publish_info").innerHTML =data; 
          var text= editor.document.getBody().getText();
          microblogInfoControl(text);
    }); */
	//记载评论微知字数
	$.ajax({
		type:"get",
		url:"<%=rootPath%>microblog/initMicroblogCommentNumberWord.action",
		cache:false,
		async:false,
		success:function(msg){
			microblgnumberComment=parseInt(msg);
			
		}
	});
	//配置转发字数限制
	$.ajax({
		type:"get",
		cache:false,
		async:false,
		url:"<%=rootPath%>microblog/initTranMicroblogNumberWord.action",
		success:function(msg){
			tranmicronumberwrod=parseInt(msg);
			
		}
		
	});
	//加载微知细览评论限制
	$.ajax({
		type:"get",
		url:"<%=rootPath%>microblog/initMicroblogFineAndLookNum.action",
		async:false,
		cache:false,
		success:function(msg){
			microblogfineandlooknumber=parseInt(msg);
		}
	});
	
	
	//在Tabs标签中添加个人栏目
	var str=$.ajax({
		type:'post',
		url:'<%=rootPath%>site/personallchannel.action',
		data :{'personid': <s:property value='personid'/>},
		dataType: "json",
		async: false,
	    cache: false 
	}).responseText;
	$('#daohangul').append($.trim(str));
    //关注（选中关注/粉丝）
    var personaltabval = '<s:property value="personaltabval" />';
    var focusonfusval =  '<s:property value="focusonfus"/>';
    if(personaltabval == 'contribute'){
    	$('#mytougao').click();
    }else if(personaltabval == 'microblog'){
    	$('#microblogAllClass').click();
    }else{
    	if(focusonfusval==1){
    		$('#microblogFocusClass').click();
    		//粉丝
    	}else if(focusonfusval==2){
    		$('#microblogFocusClass').click();
    		$('#fans').click();
    		//个人单个微知
    	}else if(focusonfusval==3){
    	
    	
    		
    		
    		$(".mainBox").find(".weibo").empty();
    		$(".mainBox").find(".weibo").addClass("fyh");
    		$('#mainviewoftop').empty();
    		$.ajax({
    			type:"post",
    			url:"<%=rootPath%>microblog/microblogidOftranspondid.action",
    			cache:false,
    			data:{
    				microblogid:'<s:property value="microblogid"/>',
    				userid:'<s:property value="micruserid"/>'		    
    			},
    			success:function(callback){
    				$(".mainBox").find(".weibo").html(callback);
    			}
    		});
    		
    		
    		return false;
    	}else{
    		//微知默认显示
    		$('#microblogAllClass').click();	
    	}
    }
    
	initUserIndex();
});
	/**
	* 加载用户首页
	*/
	function initUserIndex(){
		$(".mainBox").find(".weibo").find("div").css("visibility","visible");
	    findMyMicroblog();
		//专题
	    special();
	  	//投稿
	    submission();
		guanzhu();
		fensi();
		piclist();
		collection_wb();
		//$(".weibo").find(".screening").css("display","block");
	}


	function collection_wb(){
	    var sHtmlConn = $.ajax({ 
	 		type:'post', 
	 		url: '<%=rootPath%>microblog/collectMicroblogList.action',
	 		dataType: "json",
			async: false,
		    cache: false  
	 	}).responseText;
		$('#collection').html(sHtmlConn);		
	}

	function piclist(){
	    var sHtmlConn = $.ajax({ 
	 		type:'post', 
	 		url: '<%=rootPath%>site/alluserfile.action',
	 		data: 'personid=<s:property value="personid"/>',
	 		dataType: "json",
			async: false,
		    cache: false  
	 	}).responseText;
		$('#piclist').html(sHtmlConn);		
	}

	function allpiclist(){
	    var sHtmlConn = $.ajax({ 
	 		type:'post', 
	 		url: '<%=rootPath%>site/allmyuserfile.action',
	 		data: 'personid=<s:property value="personid"/>',
	 		dataType: "json",
			async: false,
		    cache: false  
	 	}).responseText;
		$('#all').html(sHtmlConn);		
	}
	
	function guanzhu(){
	    var sHtmlConn = $.ajax({ 
	 		type:'post', 
	 		url: '<%=rootPath%>microblog/microblogFocusByUser.action',
	 		data: 'eachuserid=0&personid=<s:property value="personid"/>',
	 		dataType: "json",
			async: false,
		    cache: false  
	 	}).responseText;
		$('#personalInfo').html(sHtmlConn);
	}
	
	//加载微知列表
	function findAllMicroblog(){
		var sUrl = "<%=rootPath%>microblog/microblogPersonalPage.action";
		var sHtmlConn = $.ajax({ 
	 		type:'post', 
	 		data:'personid=<s:property value="personid"/>',
	 		url: sUrl,
	 		dataType: "json",
			async: false,
		    cache: false  
	 	}).responseText;
		$("#all").html(sHtmlConn);
	}
	
	function findMyMicroblog(){
		var sUrl = "<%=rootPath%>microblog/mymicroblog.action";
		var sHtmlConn = $.ajax({ 
	 		type:'post', 
	 		data:'personid=<s:property value="personid"/>',
	 		url: sUrl,
	 		dataType: "json",
			async: false,
		    cache: false  
	 	}).responseText;
		$("#Microblog").html(sHtmlConn);
	}
	//关注或粉丝列表
	function Attention(_eachuserid){
	    var sHtmlConn = $.ajax({ 
	 		type:'post', 
	 		url: '<%=rootPath%>microblog/microblogFocusAttention.action?eachuserid='+_eachuserid,
	 		data: 'personid=<s:property value="personid"/>',
	 		dataType: "json",
			async: false,
		    cache: false  
	 	}).responseText;
		$("#all").html(sHtmlConn);
	}
	
	//投稿
	function allsubmission(){
	    var sHtmlConn = $.ajax({ 
	    	type:'post', 
	 		url: '<%=rootPath%>site/myallsubmission.action',
	 		data: 'personid=<s:property value='personid'/>&crtimesort=desc',
	 		dataType: "json",
			async: false,
		    cache: false  
	 	}).responseText;
		$("#all").html(sHtmlConn);
	}
	
	function submission(){
	    var sHtmlConn = $.ajax({ 
	 		type:'post', 
	 		url: '<%=rootPath%>site/mysubmission.action',
	 		data: 'personid=<s:property value="personid"/>&crtimesort=desc',
	 		dataType: "json",
			async: false,
		    cache: false  
	 	}).responseText;
		$('#mytougao').html(sHtmlConn);
	}
	
	function special(){
	    var sHtmlConn = $.ajax({ 
	 		type:'post', 
	 		url: '<%=rootPath %>site/special.action',
	 		dataType: "json",
			async: false,
		    cache: false  
	 	}).responseText;
		$('#special').html(sHtmlConn);
	}
	
	function huida(){
	    var sHtmlConn = $.ajax({ 
	 		type:'post', 
	 		url: '<%=rootPath%>question/meAnswerQuestion.action',
	 		data: 'personid=<s:property value="personid"/>',
	 		dataType: "json",
			async: false,
		    cache: false  
	 	}).responseText;
		$('#huida').html(sHtmlConn);
	}

	
	function fensi(){
	    var sHtmls = $.ajax({ 
	 		type:'post', 
	 		url: '<%=rootPath%>microblog/microblogFocusByUser.action',
	 		data: 'eachuserid=3&personid=<s:property value="personid"/>',
	 		dataType: "json",
			async: false,
		    cache: false  
	 	}).responseText;
		$('#persons').html(sHtmls);
	}
	
	//查看文档
	function documentinfo_see(_docid){ 
		window.open('<%=rootPath %>site/client_documentinfo.action?refrechstatus=1&docid='+_docid);  
	}
	//取消关注
	function cancelFocus(_userid,_microblogid){
		var alreadyFocus_01="#alreadyFocus_01"+_microblogid;
		var alreadyFocus_02="#alreadyFocus_02"+_microblogid;
	    if($(alreadyFocus_01).text()=='未关注'){	
			$.ajax({
				type:"post",
				url:"<%=rootPath%>microblog/okMicroblogFocus.action",
				data:{
					userid:_userid
				},
				success:function(msg){
					if(msg==1){
					   	$(alreadyFocus_01).text("已关注");
					   	$(alreadyFocus_02).text("取消");
					}
				}
			});

		}else{
			$.ajax({
				type:"post",
				url:"<%=rootPath%>microblog/cancelMicroblogFocus.action",
				data:{
					userid:_userid
				},
				success:function(msg){
					if(msg==1){
					   	$(alreadyFocus_01).text("未关注");
					   	$(alreadyFocus_02).text("关注");
					}
				}
			});
			
			
		}
		
	  
		
	}
	//增加关注
	function okFocus(_userid,_microblogid){
		var notFocus_01="#notFocus_01"+_microblogid;
		var notFocus_02="#notFocus_02"+_microblogid;
		
	    if($(notFocus_01).text()=='未关注'){	
			$.ajax({
				type:"post",
				url:"<%=rootPath%>microblog/okMicroblogFocus.action",
				data:{
					userid:_userid
				},
				success:function(msg){
					if(msg==1){
					   	$(notFocus_01).text("已关注");
					   	$(notFocus_02).text("取消");
					}
				}
			});
		}else{
			$.ajax({
				type:"post",
				url:"<%=rootPath%>microblog/cancelMicroblogFocus.action",
				data:{
					userid:_userid
				},
				success:function(msg){
					if(msg==1){
					   	$(notFocus_01).text("未关注");
					   	$(notFocus_02).text("关注");
					}
				}
			});
			
		}	
	}
	//互相关注
	function cancelEachFocus(_userid,_microblogid){
		
		var eachFocus_01="#eachFocus_01"+_microblogid;
		var eachFocus_02="#eachFocus_02"+_microblogid;
		
	 if($(eachFocus_01).text()=='未关注'){	
		 
			$.ajax({
				type:"post",
				url:"<%=rootPath%>microblog/okMicroblogFocus.action",
				data:{
					userid:_userid
				},
				success:function(msg){
					if(msg==1){
					   	$(eachFocus_01).text("互相关注");
					   	$(eachFocus_02).text("取消");
					}
				}
			});
		}else{
			$.ajax({
				type:"post",
				url:"<%=rootPath%>microblog/cancelMicroblogFocus.action",
				data:{
					userid:_userid
				},
				success:function(msg){
					if(msg==1){
					   	$(eachFocus_01).text("未关注");
					   	$(eachFocus_02).text("关注");
					}
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
//微知删除
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
				$(shoucangdiv).fadeOut();
				$(shoucangdiv).fadeOut("slow");
				$(shoucangdiv).fadeOut(3000);
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
//展开评论
var microblogidpage =0;
/**
* 展开评论
*/
function microblogComment(_microblogid){	
		var commentdiv = "#commentDiv"+_microblogid;
		var appandspancomment = "#apendSpanMicroComment"+_microblogid;
		var commentdivinfo="#commentInfo"+_microblogid;
		var commenttextarea = "#microCommentFontCount_01"+_microblogid;
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
//判断删除事件 鼠标移上
function microblogdeletejudge(_microblogid){
	var deleteMicroblogid ="#microblogDeleteLabel"+_microblogid;
	var informmicroblogid = "#microbloginform"+_microblogid;
	$(deleteMicroblogid).css({visibility:"visible"});
	$(informmicroblogid).css({visibility:"visible"});
	microblogidpage=_microblogid;
}
//判断删除事件 鼠标移出
function microblogdeletejudgeOut(_microblogid){
	var deleteMicroblogid ="#microblogDeleteLabel"+_microblogid;
	var informMicroblogid ="#microbloginform"+_microblogid;
	$(deleteMicroblogid).css({visibility:"hidden"});
	$(informMicroblogid).css({visibility:"hidden"});
}
 /*评论分页*/
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
//回复回复的评论
	function replyCommentReply(_microblogid,_showname,_content,_commentid){
		var commentInfoid ="#commentInfo"+_microblogid;
		replycommentidpage=_commentid;
	    $(commentInfoid).val($(commentInfoid).val()+"回复@"+_showname+": ||");
	    $(commentInfoid).focus();
	} 
	//控制回复内容的长度(细览微知)
	function microblogCommentFontInfo(_microblogid){
		//选中的评论框
		var commentTextArea =  "#commentInfo"+_microblogid;
		var microCommentFont_01 = "#microCommentFont_01"+_microblogid;
		var microCommentFontCount_01 = "#microCommentFontCount_01"+_microblogid;
		var microCommentFont_02 = "#microCommentFont_02"+_microblogid;
		var microCommentFontCount_02 = "#microCommentFontCount_02"+_microblogid;
		
		var commentTextAreaLength = $(commentTextArea).val();
		if(microblogfineandlooknumber-$.trim(commentTextAreaLength).length==microblogfineandlooknumber){
			replycommentidpage=0;	
			
		}
		if(microblogfineandlooknumber-$.trim(commentTextAreaLength).length>=0){
			$(microCommentFont_02).css({display:'none'});
			$(microCommentFont_01).css({display:'block'});
			$(microCommentFontCount_01).text(microblogfineandlooknumber-$.trim(commentTextAreaLength).length);
		}else{
			$(microCommentFont_01).css({display:'none'});
			$(microCommentFont_02).css({display:'block'});
			$(microCommentFontCount_02).text(Math.abs(microblogfineandlooknumber-$.trim(commentTextAreaLength).length));
		}
		
		
	}
	//控制回复内容的长度
	function microblogCommentFontInfoReply(_microblogid){
		//选中的评论框
		var commentTextArea =  "#commentInfo"+_microblogid;
		var microCommentFont_01_reply = "#microCommentFont_01_reply"+_microblogid;
		var microCommentFontCount_01_reply = "#microCommentFontCount_01_reply"+_microblogid;
		var microCommentFont_02_reply = "#microCommentFont_02_reply"+_microblogid;
		var microCommentFontCount_02_reply = "#microCommentFontCount_02_reply"+_microblogid;
		
		var commentTextAreaLength = $(commentTextArea).val();
		if(microblgnumberComment-$.trim(commentTextAreaLength).length==microblgnumberComment){
			replycommentidpage=0;	
			
		}
		if(microblgnumberComment-$.trim(commentTextAreaLength).length>=0){
			$(microCommentFont_02_reply).css({display:'none'});
			$(microCommentFont_01_reply).css({display:'block'});
			$(microCommentFontCount_01_reply).text(microblgnumberComment-$.trim(commentTextAreaLength).length);
		}else{
			$(microCommentFont_01_reply).css({display:'none'});
			$(microCommentFont_02_reply).css({display:'block'});
			$(microCommentFontCount_02_reply).text(Math.abs(microblgnumberComment-$.trim(commentTextAreaLength).length));
		}
		
		
		
	}

//评论回复
function commentReply(_microblogid,_userid){
	var commentInfoid ="#commentInfo"+_microblogid;
	var commentInfo =  $(commentInfoid).val();
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
	     
		    		$.ajax({
		    			type:"post",
		    			url:"<%=rootPath%>microblog/microblogComment.action",
		    			cache:false,
		    			data:{
		    			 replyuserid:_userid,
		    			 replaymicroblogid:_microblogid,
		    			 replaycontent:commentInfo,
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

//原文回复  -分页
function pageFineAndLook(_nPageNum){
	$('#pageNum').val(_nPageNum);
	var queryString = $('#pageForm').serialize();
	$.ajax({
		type:"post",
		url:"<%=rootPath%>microblog/microblogidOftranspondid.action?"+queryString,
		data:{
			microblogid:'<s:property value="microblogid"/>',
			userid:'<s:property value="micruserid"/>'		    
		},
		success:function(callback){
			$(".mainBox").find(".weibo").html(callback);
			
		}
	});
	
}
//删除微知评论回复
function deleteMicroBlogComment(_commentid,_microblogid){
	 var microblogCommentSpan ="#commentReply"+_commentid
	 var microblogcommentcount ="#microblogcommentcount"+_microblogid;
	 $.dialog.confirm('你确定要删除这条微知吗',function(){
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
 
//微知转发    
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
		var result = $.ajax({
			url: '<%=rootPath%>microblog/transpondMicroblog.action',
			cache:false,
			async:false,
		    data:{
		    	showname:shownames,
				microblogid:_microblogid,
				//源微知
				micrtranspondid:_transpondid
		    }
		}).responseText;
		
		$.dialog({
			title:'转发',
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
				if($.trim($('#transpondInfo').val()).length>tranmicronumberwrod){	
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
			    	
					if($.trim($('#transpondInfo').val()).length==0){
						$('#transpondInfo').val("转发微知");
						
					}
				$('#transpondcontent1').form('submit',{
					url:'<%=rootPath%>microblog/addTranspondMicroblog.action',
					onSubmit:function(){
						if(commentlimits!="false"){
							if($("input[type='checkbox']").is(':checked')){
						    	//同时评论该微知				    	
						         $.ajax({
				                     type:"post",
				                     url:"<%=rootPath%>microblog/microblogComment.action",
				                     cache:false,
				                     data:{
				                       replyuserid:_userid,
				                       replaymicroblogid:_microblogid,
				                       replaycontent:$('#transpondInfo').val(),
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
									if($("#microblog_menu").attr("class")=="hover"){
										$('#maxdiv').prepend(callback);		
									}
								});
					    	}else{
					    		$.dialog.tips('转发成功',1,'32X32/succ.png',function(){
									$(microblogtranspondcount).text(parseInt($(microblogtranspondcount).text())+1);
									if($("#microblog_menu").attr("class")=="hover"){
										$('#maxdiv').prepend(callback);		
									}
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
 }
//构建微知个人卡片 鼠标移动到上面
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
	//构建微知个人卡片  鼠标从头像上移走
	function personalCardOut(_microblogid){
		var microbligdiv ="#microblogCard"+_microblogid;
		$(microbligdiv).css({
			display:"none"
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
 	//查看文档
	function documentview(_docid){
		window.open('<%=rootPath%>site/client_documentinfo.action?refrechstatus=1&docid='+_docid);  
	}
	//查看文档修改
	function documentinfo(_docid){
		window.open('<%=rootPath%>site/client_toupdate_document.action?docid='+_docid);  
	}
//对文档进行关注以及取消关注
function addfocusdoclink(_documentid){ 
	var tValue=$('#focus'+_documentid).text();
	if(tValue=="收藏"){
	    $.dialog.confirm('您确定要评论这个知识吗？',function(){
	    	$.ajax({
				type:'post',
				url:'<%=rootPath%>site/addfocusdoclink.action',
				data:{'documentid': _documentid},
				success:function(msg){
					if(msg=="1"){ 
						$('#focus'+_documentid).html('取消收藏');
						//撤销他的可点击操作
					} 
				}
			}); 
	     },function(){}); 
	}else{
	    $.dialog.confirm('您确定要评论这个知识吗？',function(){
	    	$.ajax({
				type:'post',
				url:'<%=rootPath%>site/deletefocusdoclink.action',
				data:{'documentid': _documentid},
				success:function(msg){
					if(msg=="1"){ 
						$('#focus'+_documentid).html('收藏');
						//撤销他的可点击操作
					} 
				}
			}); 
	     },function(){}); 
	} 
} 
	//删除对文档的某一条评论。。。
	function deletedocuemntdocrecommend(_docid,_recommendid){ 
	$.dialog.confirm('您确定要删除这个知识吗？',function(){
		$.ajax({
			url:'<%=rootPath%>site/deletedocuemntdocrecommend.action',
			data:{
				'recommendid':_recommendid,
				'docid': _docid
			},
			type:'post',
			success: function(msg){
				 	if(msg=="1"){
				 	//将这个评论数量减去1
				 	 $('#recommendlabel'+_docid).text(parseInt($('#recommendlabel'+_docid).text())-1); 	
				 	 findmydocrecommend(_docid);//调用，刷新
				 	}else{
				 		$.dialog.alert('删除失败',function(){});
				 	}
			} 
		});
	},function(){}); 
	}
 //对某个文档发出评论
function adddocrecommend(_docid){ 
	var value=$('#recommend'+_docid).val();  
	if(value.length>0){
		$.ajax({
			type:'post',
			url:'<%=rootPath%>site/addrecommend.action',
			data:{'docid':_docid, 'recommend':value },
			success:function(msg){ 
					findmydocrecommend(_docid);//调用，刷新
				   $('#recommend'+_docid).val(''); 
				   $('#recommendlabel'+_docid).text(parseInt($('#recommendlabel'+_docid).text())+1); 	
			}
		});
	} else{
		$.dialog.alert('评论不能为空',function(){
			$('#recommend'+_docid).focus();   
		});
	} 
}
//将当前文档的评论显示出来
var _docrecommends=null;
function blockOrIn(_docid){ 
	alert(_docid);
	   $('#recommenddiv'+_docid).show();//将这个div显示出来 
	   $.ajax({
		type:'post',
		url:'<%=rootPath%>site/finddocrecommend.action',
		data:{'docid':_docid},
		success:function(msg){  
		 	_docrecommends=eval(msg);//转换成附件集合   
		 	 var len = _docrecommends.length;  
		   str="";
		   for(var i=0 ;i<len; i++){ 
		   	str+="<a href='javascript:void(0)' class='linkb14' >"+_docrecommends[i].cruser+"：</a>"+_docrecommends[i].recommend+"<br>";
		   }
		    $('#speek'+_docid).append(str);  
		}
	});
} 
//选择人或者组织
function checkuserorgroup(){
	var str=$.ajax({
		url: '<%=rootPath%>site/to_check_group_or_user.action', 
		type:"post", 
	    async: false,
	    cache: false
	}).responseText;   
	 $.dialog({
		 	title:'推荐',
			max:false,
			min:false,
			lock:true,
			resize: false, 
			init : function(){
				$('#tab').tabs({ 
					  border:false,   
  					  onSelect:function(title,index){ 
  					  		tabs(); 
  				      }   
				});
			},
			content:str,
			cancelVal:'关闭',
			okVal:'确定',
			cancel:function(){ 
			},
			ok:function(){  
		    	   var userIds= $('#transdocument').find('#userIds').val();
		        	var userNames= $('#transdocument').find('#userNames').val();
		  	 		$('#transdocument').find('#userdiv').html(userNames);
		  	 		$('#transdocument').find('#userIds').val(userIds);
		    	   var groupIds=$('#transdocument').find('#groupIds').val();
		  	 	   var groupNames= $('#transdocument').find('#groupNames').val();
		  	 	   $('#transdocument').find('#groupdiv').html(groupNames);
		  	 	   $('#transdocument').find('#groupIds').val(groupIds);
				}
	});  
}

//转发某个文档给某些人
var _focusperson=null;
function transdocument(_docid){ 
//弹出框，显示所有人。。。然后选择 
	var result = $.ajax({
		url: '<%=rootPath%>site/findallmicroblogforcusjson.action',
		type:"post",
		dataType: "json", 
	    async: false 
	}).responseText; 
	$.dialog({
		title:'推荐',
		max:false,
		min:false,
		lock:true, 
		resize: false,
		width:'500px',
		height:'150px',
		content:result,
		cancelVal:'关闭',
		okVal:'推荐',
		cancel:function(){ 
		},
		ok:function(){ 
			$('#transdocument').find('#docid').val(_docid);
			$('#transdocument').form('submit',{
				url:'<%=rootPath%>site/addtransmite.action',
				success:function(callback){ 
					var num=parseInt(callback) ;
					if(num>0){
						$.dialog.tips('推荐成功',1,'32X32/succ.png',function(){ 
							//将转发的里面数字加 
							 $('#translabel'+_docid).text(parseInt($('#translabel'+_docid).text())+num); 	
						});						
					}else{
						$.dialog.tips('推荐失败',1,'32X32/fail.png');	
					}
				},
				error:function(){
					$.dialog.tips('推荐失败',1,'32X32/fail.png');
				}
			});
		}
	}); 
} 
	//查看这个文档的所有评论
	function findmydocrecommend(_docid,_cruserid){
		//将当前文档的评论显示出来   
		var displayStyle=$('#speekdiv'+_docid).css('display');
		    if(displayStyle=="block"){
		     $('#speekdiv'+_docid).css({ display:"none"}); 
		    }else{
				  var str=$.ajax({
						type:'post',
						url:'<%=rootPath%>site/findmydocrecommend.action',
						data:{'docid':_docid,'createuserid':_cruserid},
						dataType: "json",
						async: false,
					    cache: false 
						}).responseText;  
				    $('#speek'+_docid).html(str); 
				    $('#speekdiv'+_docid).css({ display:"block"});  
			}
   }
	
	//显示切换Tab标签
	function tabs(lidom){
		$('#daohangul').find('li').each(function(){
			if(this.id == lidom.id){
				this.className="hover";
			}else{
				this.className="";
			}
		});
		page(1);
	}
	
	//个人页面分页方法
	function page(pageNum){ 
		var liDom = $('#daohangul').find('.hover');
		if(liDom!=null){}
			var sUrl = liDom.attr("_href");
			var sData = liDom.attr("_data");
			if(sData==undefined || sData==''){
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
			$('#personalInfo').html(sHtmlConn);
		 
	}
	

	
	//文档的排序按照创建时间排序
  	function documentSort(sort){
  		var liDom = $('#daohangul').find('.hover');
  		var sData = liDom.attr('_data');
  		sData = sData.substring(0,(sData.lastIndexOf('=')+1))+sort;
  		liDom.attr('_data',sData);
  		page(1);
  	}
	
	//展开折叠菜单项
	function moreTabs(domA){
		var nHeight = $('#daohangul').css('height');
		if(nHeight=='34px'){
			$(domA).html('折叠&gt;&gt;');
			$('#daohangul').css('height','auto');
			$('#daohangul').find('li').first().css('display','block');
		}else{
			$(domA).html('更多&gt;&gt;');
			$('#daohangul').css('height','34px');
			$('#daohangul').find('li').first().css('display','inline');
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
	 
	//举报
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
 * 链接到首页收藏下
 */
function linkCollFirstPage(){
	window.location.href="<%=rootPath%>microblog/linkskiphomepage.action?skiptab=collect";
}
function toSearchSubject(_SSId){
	window.open("<%=rootPath%>personalsearch/searchsubject_detail.action?personalSearchId="+_SSId);
}
function goToDetail(id){
	window.open("<%=rootPath%>site/knowledgeSubject.action?chid="+id);
}
</script>
</head>

<body>
<s:include value="../include/client_head.jsp"></s:include>
<section class="mainBox">
		<form id="pageForm">
			<s:hidden name="pageNum" id="pageNum" />
			<s:hidden name="pageSize" id="pageSize" value="10" />
		</form>
		<nav class="publicNav">
	    </nav>
</section>
<section class="mainBox" >
	<section class="weibo"  >
    	<div class="area20"></div>
        <div class="screening" style="visibility: hidden;">
        	<div class="title">
        		<span>当前分类：<em id="classTitle">全部</em></span>
        		<aside class="checkOn">筛选</aside>
       		</div>
            <div class="hidden">
            	<div id="tags" class="tags">
            		<a href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='personid' />" class="current">全部</a>
            		<a style="cursor:pointer;" onclick="Attention(0);">关注</a>
            		<a style="cursor:pointer;" onclick="Attention(3);">粉丝</a>
					<!-- <a href="#">我回答的</a> <a href="#">我提问的</a>  -->
					<a style="cursor:pointer;" onclick="allsubmission();">投稿</a>
					<a style="cursor:pointer;" onclick="allpiclist();">图片中心</a>
					<a style="cursor:pointer;" onclick="findAllMicroblog();" >微知</a>
				</div>
			</div>
            <script>
            	function classTab(oLink){
            		oLink = $(oLink);
            		var tags = $('#tags').find('A');
            		$.each(tags,function(i,n){
            			if($(n).attr('class')=='current')
            				$(n).attr('class','');
            		});
            		oLink.attr('class','current');
            		$('#classTitle').html(oLink.html());
            	}
				$(document).ready(function(){
					$(".checkOn").click(function(){
						$(".hidden").slideToggle();
					});
					$(".tags a").click(function(){
						$(".hidden").slideToggle();
						classTab(this);
					});
				});
			</script>
      	</div>
        <div class="area20"></div>
      	<section id="all" class="discussions">
      	
      	<div  style="visibility: hidden;" id="personalInfo"></div>    
      	<div  style="visibility: hidden;" id="persons"></div> 
      	<div  style="visibility: hidden;" class="labels">微知</div>
      	<div  style="visibility: hidden;" class="line area20"></div>
      	<div  style="visibility: hidden;" id="Microblog"></div>	
      	
      	<div  style="visibility: hidden;" class="labels">微知收藏</div>
      	<div  style="visibility: hidden;" class="line area20"></div>
      	<div  style="visibility: hidden;" id="collection"></div>

		<div style="visibility: hidden;" class="labels">投稿</div>
		<div style="visibility: hidden;" class="line area20"></div>
		<div  style="visibility: hidden;" id="mytougao"></div>		
            <div  style="visibility: hidden;" class="labels">图片</div>
            <div  style="visibility: hidden;" class="line area20"></div>
            <div  style="visibility: hidden;" class="labs" id="piclist"></div>    
          <div  style="visibility: hidden;" class="labels">专题</div>        
          <div  style="visibility: hidden;" class="pan" id="special" style="padding-left:40px;"></div>  

        </section>
    </section>
	<script>
    	$(document).ready(function(){
			$(".infomation").height($(".weibo").height());
		});
    </script>
	<aside class="infomation">
    	<div class="userInfo">
        	<dl class="info">
           	  	<dt class="leftBox"><a href="#"><img src="<s:property value="irpUser.defaultUserPic" />"/ width="63" height="63" ></a></dt>
                <dd class="leftBox">
                	<p><s:property value="irpUser.showName" /></p>
                	<p>邮箱：<em><s:property value="irpUser.email" /></em></p>
                	<p>地区：<em><s:property value="irpUser.province" />， <s:property value="irpUser.city" />，<s:property value="irpUser.area" /> </em></p>
               	</dd>
                <dd class="clear"></dd>
            </dl>
        </div>
        <div class="area10"></div>
    </aside>
</section>
<jsp:include page="../include/client_foot.jsp" />
</body>
</html>
