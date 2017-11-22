<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
 <title>
<s:if test="microblogid==null">
个人信息
</s:if>
<s:elseif test="microblogid!=null">
查看源微知
</s:elseif>
</title>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>client/css/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>client/css/icon.css" />
<link href="<%=rootPath%>/client/css/oacss.css" rel="stylesheet" type="text/css" />
<jsp:include page="../include/client_skin.jsp" />

<script type="text/javascript" src="<%=rootPath %>client/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/easyui-lang-zh_CN.js"></script>
<style type="text/css">
body {
	behavior: url(hover.htc);
}
</style> 
<link rel="stylesheet" href="<%=rootPath %>client/css/style.css" type="text/css" media="screen" /> 
  <script type="text/javascript" src="<%=rootPath %>client/js/jquery.jcarousellite.min.js"></script>
</head> 
<body>
	<div class="bg01">
		<!--头部菜单-->
		<s:include value="../include/client_head.jsp"></s:include>
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
    		$('#mainView').empty();
    		$('#mainView').addClass("fyh");
    		$('#mainviewoftop').empty();
    		$.ajax({
    			type:"post",
    			url:"<%=rootPath%>microblog/microblogidOftranspondid.action",
    			data:{
    				microblogid:'<s:property value="microblogid"/>',
    				userid:'<s:property value="micruserid"/>'		    
    			},
    			success:function(callback){
    				$('#mainView').append(callback);

    			}
    		});
    	}else{
    		//微知默认显示
    		$('#microblogAllClass').click();	
    	}
    }
	//正在热议
	hottalkdocument();
	//猜你喜欢
	youlovedocument();
	//分享排行
	sharedocument();
	//人气TOP
	hotpersondocument(); 
});

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

//收藏微知
function collect(_microblogid){
	var shoucang = "#"+_microblogid;
	var collectionvalue = $(shoucang).text();
	if(collectionvalue=='收藏'){
		$.ajax({
			type:'POST',
			url:'<%=rootPath%>microblog/microblogcollect.action',
		    dataType: "json",
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
		    		$.dialog.tips('收藏成功',0.5,'32X32/succ.png',function(){
		    		});
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
	});	
}
//展开评论
var microblogidpage =0;
function microblogComment(_microblogid){	
	var commentdiv = "#commentDiv"+_microblogid;
	var appandspancomment = "#apendSpanMicroComment"+_microblogid;
	var commentdivinfo="#commentInfo"+_microblogid;
	$(commentdivinfo).val("");
	$(appandspancomment).empty();
	if($(commentdiv).css("display")=="none"){
		microblogidpage = _microblogid;
		$.ajax({
			type:"post",
			cache:false,
			url:"<%=rootPath%>microblog/microblogReplyList.action",
			data:{
				microblogid:_microblogid
			},
			success:function(callback){
				var textareadiv = "#microCommentFontCount_01_reply"+_microblogid;
			$(textareadiv).text(microblgnumberComment);
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
			$('#mainView').html(callback);
			
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
	
	/*粉丝/关注*/
	function focusTab(focusType,linkId,personid){
		$('#microblogFocusClass').attr('_data','eachuserid='+focusType+'&personid='+personid);
		$('#FocusByFocus').find('a').each(function(){
			if(this.id==linkId){
				this.className="over";
			}else{
				this.className="";
			}
		});
		page(1);
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
</script>
		<form id="pageForm">
			<s:hidden name="pageNum" id="pageNum" />
			<s:hidden name="pageSize" id="pageSize" value="10" />
		</form>

		<!--头部菜单end-->
		<div class="main">
			<!--左侧内容-->
			<div class="left">
				<div id="mainviewoftop">
					<ul>
						<li></li>
						<li>
							<table width="95%" border="0" align="center" cellpadding="10"
								cellspacing="10">
								<tr>
									<td width="32%" rowspan="4" align="center" valign="middle"
										class="zl1">
										<img width="130" height="130" src="<s:property value="irpUser.defaultUserPic" />" />
									</td>
									<s:iterator value="userinfolistpersonal" >
									<td width="68%" align="left"
										style="padding-left:43px; background-position:bottom left; background-repeat:no-repeat"
										background="<%=rootPath%>client/images/np3.gif">
											<s:property value="irpUser.truename"/>
											   <label style="float: right;">
											   <a id="mytougao" onclick="tabs(this)" _href="<%=rootPath%>site/myalltougaodocument.action" class="linkb14" style="cursor: pointer;" onmouseover="this.style.cursor='hand'">投稿:<s:property value='KNOWLEDGECOUNT' /></a>&nbsp;
											   <a id="microblogAllClass" onclick="tabs(this)" _href="<%=rootPath%>microblog/microblogPersonalPage.action" _data="personid=<s:property value='personid'/>"  class="linkb14" style="cursor: pointer;" onmouseover="this.style.cursor='hand'">微知:<s:property value='MICROBLOGCOUNT' /></a>&nbsp;
											   <a href="javascript:void(0)"  style="cursor: pointer;" onmouseover="this.style.cursor='default'"  class="linkb14" >收藏:<s:property value='MICROBLOGCOLLECTCOUNT+KNOWLEDGECOLLECCOUNT' /></a>
											   	</label></td>
													</s:iterator>
								</tr>
								<tr>
									<td align="left"
										style="padding-left:43px; background-position:bottom left; background-repeat:no-repeat"
										background="<%=rootPath%>client/images/np2.gif">
											<s:property value="irpUser.email"/>
										&nbsp;</td>
								</tr>
								<tr>
									<td align="left"
										style="padding-left:43px; background-position:bottom left; background-repeat:no-repeat"
										background="<%=rootPath%>client/images/np4.gif"><s:if
											test="irpUser.province!=null && irpUser.province.length()>0"><s:property value="irpUser.province"/>，</s:if>
										<s:if test="irpUser.city!=null && irpUser.province.length()>0"><s:property value="irpUser.city"/>，</s:if><s:property value="irpUser.area"/>&nbsp;</td>
								</tr>
								<tr>
									<td align="left"
										style="padding-left:43px; background-position:bottom left; background-repeat:no-repeat"
										background="<%=rootPath%>client/images/np5.gif"><p> <s:property value="irpUser.expertintro"/>&nbsp;</p>
									</td>
								</tr>
							</table>
							<div> 
							<s:if test="personAllFile!=null && personAllFile.size()>0"> 
								<div class="zl2">
									<table width="90%" border="0" align="center">
										<tr>
											<td width="79%" style="padding-left:580px;">
											<s:if test="personAllFile==null || personAllFile.size()>4"> 
	 											<button class="prev"><</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											    <button class="next">></button>
											</s:if> 
											<s:else>&nbsp;</s:else> 
											</td>  
										</tr>
									</table>
									<table width="90%" border="0" align="center" cellpadding="10" cellspacing="10">
										 <tr>
										 <td>
										 <div id="jCarouselLiteDemo" >
	 										<div class="carousel">
											 		<div class="jCarouselLite">
									                <ul>
									                	<s:iterator value="personAllFile">
									                	 <li>
										                 	<img src="<%=rootPath%>file/readfile.action?fileName=<s:property value='@com.tfs.irp.util.ThumbnailPic@searchFileName(filename,"_150X150")'/>" alt=""  width="15%" height="100">
									                	 </li>
									                	</s:iterator>
									                 </ul>
		         								    </div>
		         							</div>
		         						</div>
										 </td>
										 </tr>
									</table>
								</div>
							</s:if>
							<s:else>
								<table width="90%" border="0" align="center">
										<tr>
											<td width="79%" style="padding-left:26px;">没有个人图片</td>
										</tr>
								</table>
							</s:else>
							</div>
						</li>
						<li class="shuruend"></li>
					</ul>
				</div>
				<div id="mainView">
					<div class="fy">
						<span><a href="javascript:void(0)" onclick="moreTabs(this)"
							class="linkc12">更多&gt;&gt;</a> </span>
						<ul id="daohangul">
							<li class="hover" id="microblogAllClass" onclick="tabs(this)"
								_href="<%=rootPath%>microblog/microblogPersonalPage.action"
								_data="personid=<s:property value='personid'/>"><a href="javascript:void(0)">所有</a>
							</li>
							<li id="microblogFocusClass" onclick="tabs(this)"
								_href="<%=rootPath%>microblog/microblogFocusByUser.action"
								_data="eachuserid=0&personid=<s:property value='personid'/>"><a
								href="javascript:void(0)">关注/粉丝</a></li>
							<s:if test="personid==loginUserId">
								<li id="meAnswerQuestion" onclick="tabs(this)"
									_href="<%=rootPath%>question/meAnswerQuestion.action" _data="personid=<s:property value='personid'/>"><a
									href="javascript:void(0)">我回答的</a></li>
								<li id="meAskQuestion" onclick="tabs(this)"
									_href="<%=rootPath%>question/meAskQuestion.action" _data="personid=<s:property value='personid'/>"><a
									href="javascript:void(0)">我提问的</a></li>
									<li id="mytougao" onclick="tabs(this)" _href="<%=rootPath%>site/myalltougaodocument.action" 
									 _data="personid=<s:property value='personid'/>&crtimesort=desc">
												<a href="javascript:void(0)">我的投稿</a>
									</li>
							</s:if> 
							<s:else>
									<s:if test="irpUser.sex==1">
										<li id="meAnswerQuestion" onclick="tabs(this)"
											_href="<%=rootPath%>question/meAnswerQuestion.action" _data="personid=<s:property value='personid'/>"><a
											href="javascript:void(0)">他回答的</a></li>
										<li id="meAskQuestion" onclick="tabs(this)"
											_href="<%=rootPath%>question/meAskQuestion.action" _data="personid=<s:property value='personid'/>"><a
											href="javascript:void(0)">他提问的</a></li>
											<li id="mytougao" onclick="tabs(this)" _href="<%=rootPath%>site/myalltougaodocument.action" 
											 _data="personid=<s:property value='personid'/>&crtimesort=desc">
														<a href="javascript:void(0)">他的投稿</a>
											</li>
									</s:if>
									<s:elseif test="irpUser.sex==2">
										<li id="meAnswerQuestion" onclick="tabs(this)"
											_href="<%=rootPath%>question/meAnswerQuestion.action" _data="personid=<s:property value='personid'/>"><a
											href="javascript:void(0)">她回答的</a></li>
										<li id="meAskQuestion" onclick="tabs(this)"
											_href="<%=rootPath%>question/meAskQuestion.action" _data="personid=<s:property value='personid'/>"><a
											href="javascript:void(0)">她提问的</a></li>
											<li id="mytougao" onclick="tabs(this)" _href="<%=rootPath%>site/myalltougaodocument.action" 
										 _data="personid=<s:property value='personid'/>&crtimesort=desc">
													<a href="javascript:void(0)">她的投稿</a>
										</li>
										</s:elseif>
										<s:else>
											<li id="meAnswerQuestion" onclick="tabs(this)"
												_href="<%=rootPath%>question/meAnswerQuestion.action" _data="personid=<s:property value='personid'/>"><a
												href="javascript:void(0)">ta回答的</a></li>
											<li id="meAskQuestion" onclick="tabs(this)"
												_href="<%=rootPath%>question/meAskQuestion.action" _data="personid=<s:property value='personid'/>"><a
												href="javascript:void(0)">ta提问的</a></li>
												<li id="mytougao" onclick="tabs(this)" _href="<%=rootPath%>site/myalltougaodocument.action" 
											 _data="personid=<s:property value='personid'/>&crtimesort=desc">
														<a href="javascript:void(0)">ta的投稿</a>
											</li> 
										</s:else>
								</s:else>
						</ul>
					</div>
					<div class="fyh">
						<div id="personalInfo"></div>
					</div>
				</div>
			</div>
			<!--左侧内容结束-->

			<!--右侧内容-->
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
			<!--右侧内容结束-->
			<s:include value="../include/client_foot.jsp"></s:include>
		</div>
	</div>
</body>
</html>
