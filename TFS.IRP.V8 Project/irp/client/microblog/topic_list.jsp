<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.tfs.irp.user.entity.IrpUser,com.tfs.irp.util.LoginUtil"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
    IrpUser loginUser = LoginUtil.getLoginUser();
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>专题       	||  <s:property value="getIrpTopic(topicname).topicname" /></title>
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
<script type="text/javascript">
//分页评论变量
var microblogidpage =0;
//分享微知字数
var microlognumber = 0;
//微知评论“评论”id +
var replycommentidpage=0;
//评论微知字数
var microblgnumberComment = 0;
//微知图片
var micrpicall="";
$(function(){	
	$('#publish_info').val($('#topicnameval').text());
	//记载评论微知字数
	$.ajax({
		type:"get",
		url:"<%=rootPath%>microblog/initMicroblogCommentNumberWord.action",
		cache:false,
		success:function(msg){
			microblgnumberComment=parseInt(msg);
			
		}
	});
	//加载分享微知次数
	$.ajax({
		type:"get",
		url:"<%=rootPath%>microblog/initMicroblogNumberWord.action",
		cache:false,
		success:function(msg){
			microblognumber = parseInt(msg);
			$('#microblogContentCount_01').text(microblognumber);
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
//控制微知内容的长度
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





//转发
function transpond(_showname,_microblogid,_userid,_transpondid,_isdel){


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
	var result = $.ajax({
		url: '<%=rootPath%>microblog/transpondMicroblog.action',
		type:"post",
		dataType: "json",
	    data: {
	    	showname:_showname,
			microblogid:_microblogid,
			//源微知
			micrtranspondid:_transpondid
	    },
	    async: false
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
		ok:function(){
			//转发
			if($.trim($('#transpondInfo').val()).length>tranmicronumberwrod){	
				return false;
			}else{
		

				if($("input[type='checkbox']").is(':checked')){
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
								    	}
									
									
								}
				if($.trim($('#transpondInfo').val()).length==0){
					$('#transpondInfo').val("转发微知");
					
				}
			$('#transpondcontent1').form('submit',{
				url:'<%=rootPath%>microblog/addTranspondMicroblog.action',
				onSubmit:function(){
					
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
						
				},
				success:function(callback){
					if(callback!=null){
						$.dialog.tips('转发成功',1,'32X32/succ.png',function(){
							$(microblogtranspondcount).text(parseInt($(microblogtranspondcount).text())+1); 	
							$('#maxdiv').prepend(callback);	
						});						
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
    $('#transpondInfo').focus();
	}
}
//收藏
function collect(_microblogid){
	var shoucang = "#"+_microblogid;
	var collectionvalue = $(shoucang).text();
	if(collectionvalue=='收藏'){
		$.dialog.confirm('您确定要收藏这条微知吗',function(){
		$.ajax({
			type:'POST',
			url:'<%=rootPath%>microblog/microblogcollect.action',
		    dataType: "json",
		    async:false,
			data:{
				microblogid:_microblogid
			},
		    success:function(msg){
		    	$('#mydoccollectioncount').text(parseInt($('#mydoccollectioncount').text())+1);
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
				$('#mydoccollectioncount').text($('#mydoccollectioncount').text()-1);
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
				$('#mymicroblogcount').text($('#mymicroblogcount').text()-1);
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
	//控制回复内容的长度
	function microblogCommentFontInfo(_microblogid){
		//选中的评论框
		var commentTextArea =  "#commentInfo"+_microblogid;
		var microCommentFont_01 = "#microCommentFont_01"+_microblogid;
		var microCommentFontCount_01 = "#microCommentFontCount_01"+_microblogid;
		var microCommentFont_02 = "#microCommentFont_02"+_microblogid;
		var microCommentFontCount_02 = "#microCommentFontCount_02"+_microblogid;
		
		var commentTextAreaLength = $(commentTextArea).val();
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

 //删除微知评论回复
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
	//判断删除事件 鼠标移出
	function microblogdeletejudgeOut(_microblogid){
		var deleteMicroblogid ="#microblogDeleteLabel"+_microblogid;
		var informMicroblogid ="#microbloginform"+_microblogid;
		$(deleteMicroblogid).css({visibility:"hidden"});
		$(informMicroblogid).css({visibility:"hidden"});
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
//刷新
function reloadpage(){
	window.location.reload();
	return true;
}
//分页
function page(_nPageNum){
	$('#pageNum').val(_nPageNum);
	var queryString = $('#pageForm').serialize();
	var info = encodeURIComponent($('#topicnametype').text());
	window.location.href="<%=rootPath%>microblog/searchTopic.action?"+queryString+"&topicname="+info;
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
 /**
  * 专题回复
  */
  function publishbytopic(){
 		$('#replytopiccontent').attr("onclick","");
 		var topicinfo =$('#publish_info').val();
 	if($.trim(topicinfo).length>microblognumber){
 		$('#replytopiccontent').attr("onclick","publishbytopic()");
  	return false;
  }else if($.trim(topicinfo).length<=0){
	  $('#replytopiccontent').attr("onclick","publishbytopic()");
  	 $.dialog.tips("内容不能为空",0.3,"alert.gif");	
  	return false;
  }else{
	  $.ajax({
			type:"POST",
			url:'<%=rootPath%>microblog/microblogShare.action',
			cache:false,
			async:false,
			data:{
				publishInfo:topicinfo,
				microblogType:0
			},
			success:function(callback){
				$('#publish_info').val($('#topicnameval').text());
				if(callback!=null){
					$.dialog.tips('分享成功',1,'32X32/succ.png',function(){
						$('#mymicroblogcount').text(parseInt($('#mymicroblogcount').text())+1);
						$('#microblogContentCount_01').text(microblognumber);
						$('#maxdiv').prepend(callback);	
					});
					$('#replytopiccontent').attr("onclick","publishbytopic()");
					
				}else{
					$('#replytopiccontent').attr("onclick","publishbytopic()");
					$.dialog.tips('发布失败',1,'32X32/fail.png');   	
				}
			}
			
	  });
	  
     }
 }
//增加修改专题描述
  function topicDesc(topicid){
  	//打开更改框
  					var loadPage=$.ajax({
  						url: '<%=rootPath%>microblog/loadupdateframe.action', 
  						data:{
  							topicid:topicid
  						},
  						type:"post", 
  					    async: false,
  					    cache: false
  					}).responseText; 
  					 $.dialog({
  						 title:'修改描述',
  							max:false,
  							min:false,
  							lock:true,
  							resize: false,
  							content:loadPage,
  							okVal:'保存',
  							ok:function(){
  								var topicdescs = $("#topicdescform").val();
  								 if($.trim(topicdescs).length>$('#topicdesccount').val()){
  								    	return false;
  								    }else{
  								    	
  						 				$('#topicform').form('submit',{
  						 					url:'<%=rootPath%>microblog/saveTopicDesc.action',
  						 					cache:false,
  						 					success:function(msg){
  						 						if(msg==1){
  						 							$($("#topicdesclabel")).text(topicdescs);
  						 							$.dialog.tips('保存成功',1,'32X32/succ.png');					
  						 						}else{	
  						 							$.dialog.tips('保存失败',1,'32X32/fail.png');	
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
//删除专题
  function deletetopic(topicid,topicname){
  	 $.dialog.confirm('你确定要删除这个专题吗',function(){
  			$.ajax({
  			  type:"post",
  			  url:"<%=rootPath%>microblog/deletetopic.action",
  			  cache:false,
  			  async:false,
  			  data:{
  				  topicid:topicid,
  				  topicname:topicname
  			  },
  			  success:function(status){
  				  if(status>=1){
  					  window.location.href='<s:url action="index" namespace="/client" />';
  				  }else{
  					  $.dialog.tips('删除专题失败',1,'32X32/fail.png');
  				  } 
  			  },
  			  error:function(){
  				  $.dialog.tips('删除专题失败',1,'32X32/fail.png');
  			  }
  			});
  		 
  	 });
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
  			width:500,
  			resize: false,
  			content:loadPage,
  			okVal:'举报',
  			ok:function(){
  				var informdescs = $("#informdescform").val();
  				if($.trim(informdescs).length<1){
  					$.dialog.tips("举报内容不能为空",1,"alert.gif");
  					return false;
  				}
  				 if($.trim(informdescs).length>$('#informdesccount').val()){
  				    	return false;
  				    }else{
  				    	$('#informform').form('submit',{
  		 					url:'<%=rootPath%>microblog/saveinformdesc.action',
  		 					cache:false,
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

</script>
</head>
<body>
	<form id="pageForm">
		<s:hidden name="pageNum" id="pageNum"  />
		<s:hidden name="pageSize" id="pageSize" value="10" />
		
	</form>
<!--头部菜单-->
<jsp:include page="../include/client_head.jsp" />
<div class="main">

<div class="left">

<h1>
<label id="topicnametype" style=" font-size:18px; font-weight:normal; line-height:50px; border-bottom:1px dashed #efefef; text-align:left; font-family:'微软雅黑'; margin:0 0 20px 5px; color:#121212;" href="javascript:void(0)"><a id="topicnameval" href="javascript:void()" onclick="reloadpage()"><s:property value="getIrpTopic(topicname).topicname"  /></a></label>
<%if(loginUser.isTopicManager()){ %>
		<a href="javascript:void(0)" class="linkb14" onclick="deletetopic(<s:property value="getIrpTopic(topicname).topicid" />,'<s:property value="getIrpTopic(topicname).topicname" />')">删除</a>&nbsp;&nbsp;<a class="linkb14" href="javascript:void(0)" onclick="topicDesc(<s:property value="getIrpTopic(topicname).topicid" />)" >描述</a>
<%}%>
</h1>
<div style="margin-left: 30px;">
 <label id="topicdesclabel" style="font-size: 14px;"> <s:property value="getIrpTopic(topicname).topicdesc"/></label>
</div><br/>
  
  <ul>
  <li id="microblogContentprompt_01" style="text-align: right;width: 676px;">您还可以输入<label id="microblogContentCount_01" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;" ></label>个字</li>
    <li id="microblogContentprompt_02" style="text-align: right;display: none;width: 676px;">超出了<label id="microblogContentCount_02" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color:red;" ></label>个字</li>
    
   <li><textarea id="publish_info" onkeyup="microblogInfoControl(this.value)" style="width: 676px;background-color:	rgb(246, 246, 246);resize: none;" rows="4"></textarea></li>
   <li>
   <a class="zhuanz1" href="javascript:void(0)"style="margin-left: 600px; margin-top: 5px;" id="replytopiccontent"  onclick="publishbytopic()" >回复</a></li>
  </ul>

	
  <div class="fyh" style="margin-top: 50px;" id="maxdiv">
  
    <s:iterator value="topicmicroblog" >
      <dl id="<s:property value='microblogid'/>div"
		onmouseout="microblogdeletejudgeOut(<s:property value='microblogid' />)"
		onmouseover="microblogdeletejudge(<s:property value='microblogid' />)" >
		 
		<s:if test="findUserByUserId(userid).userpic!=null">
			<dt>
			<a target="_bank"	href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' /> " >
				<img id="microblogPersonalCard<s:property value='microblogid' />"
					src="<%=rootPath%>file/readfile.action?fileName=<s:property value='findUserByUserId(userid).userpic' />"
					alt="用户头像" width="48px"
					onmouseover="personalCard(<s:property value='microblogid' />,<s:property value='userid' />)"
					onmouseout="personalCardOut(<s:property value='microblogid' />)" />
		   </a>			
			</dt>
		</s:if>
		<s:if test="findUserByUserId(userid).userpic==null">
			<dt>
			<a target="_bank"	href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' /> " >
				<img id="microblogPersonalCard<s:property value='microblogid' />"
					<s:if test="findUserByUserId(userid).sex==2">src="<%=rootPath%>client/images/female.jpg"</s:if><s:else>src="<%=rootPath%>client/images/male.jpg"</s:else> alt="用户头像" width="48px"
					onmouseover="personalCard(<s:property value='microblogid' />,<s:property value='userid' />)"
					onmouseout="personalCardOut(<s:property value='microblogid' />)" />
					</a>
			</dt>
		</s:if>
		
		<dd>
			<a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' /> " class="linkb14"> 
			       <s:if test="findUserByUserId(userid).nickname!=null">
                      <s:property value="findUserByUserId(userid).nickname"/>  
                   </s:if>
                   <s:if test="findUserByUserId(userid).nickname==null">
                      <s:property value="findUserByUserId(userid).truename"/>
                   </s:if>:
			</a>
			<s:if test="userid==2">
			<img style="float: left;
margin-top: -7px;" alt="微知助手" src="<%=rootPath %>client/images/micropicicons/mobile-attach-4.png">
			</s:if>
			
			<s:property value='getMicroblogContent(microblogcontent)' escapeHtml="false" />
		</dd>

		<dd style="margin-left: 12px; "></dd>
		<dd style="padding-bottom: 5px;">
			<s:if test="transpondid!=0 && transpondIrpMicroblog(transpondid).isdel==0"> 
				<div style="margin-left: 10px;"> 
					<em></em><a target="_bank" class="linkb14"  href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='transpondIrpMicroblog(transpondid).userid' /> ">
					<s:property
						value="showPageName(transpondIrpMicroblog(transpondid).userid)" />:</a>
					<s:if test="transpondIrpMicroblog(transpondid).userid==2">
						<img style="float: left;
	margin-top: -7px;" alt="微知助手" src="<%=rootPath %>client/images/micropicicons/mobile-attach-4.png">
					</s:if>
					
					<s:property value='getMicroblogContent(transpondIrpMicroblog(transpondid).microblogcontent)' escapeHtml="false" />
					<br/> <span><s:date
							name="transpondIrpMicroblog(transpondid).crtime"
							format="yyyy-MM-dd HH:mm" /> 来自<s:property  value="transpondIrpMicroblog(transpondid).fromdata" />
	<a target="_bank" style="margin-left: 230px;" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='transpondIrpMicroblog(transpondid).userid' />&focusonfus=3&microblogid=<s:property value='transpondid' />&micruserid=<s:property value='transpondIrpMicroblog(transpondid).userid' /> ">原文转发(<s:property value="transpondIrpMicroblog(transpondid).transpondcount" />)</a> |
						<a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='transpondIrpMicroblog(transpondid).userid' />&focusonfus=3&microblogid=<s:property value='transpondid' />&micruserid=<s:property value='transpondIrpMicroblog(transpondid).userid' /> ">原文评论(<s:property
								value="transpondIrpMicroblog(transpondid).commentcount" />)</a>
					</span>
				</div>
			</s:if>
			<s:if test="transpondIrpMicroblog(transpondid).isdel==2">
				<!-- 显示原微知举报的 -->
			   <div style="margin-left: 10px;"> 
					<em></em>
					抱歉，由于此微知内容不符合规定，无法查看。
				</div>
			</s:if>
			<s:elseif test="transpondid!=0 && transpondIrpMicroblog(transpondid).isdel==1">
				<!-- 显示原味以删除的 -->
			   <div style="margin-left: 10px;"> 
					<em></em>
					抱歉，此微知已被作者删除。
				</div>
			
			</s:elseif>	
			
			<div style="background-color:white;border-style: none;height: 12px;text-align: right;width: 570px;">
			<font style="float: left;"> 
			 <s:date name="crtime" format="yyyy-MM-dd HH:mm" />&nbsp;&nbsp;来自<s:property
					value="fromdata" />
					<a href="javascript:void(0)" id="microbloginform<s:property value='microblogid' />" style="visibility: hidden;"  class="linkc12" onclick="informdetail(<s:property value='microblogid' />)" >举报</a>
				</font>
				<s:if test="loginuserid==userid">
					<a href="javascript:void(0)"
						id="microblogDeleteLabel<s:property value='microblogid' />"
						style="visibility: hidden;" class="linkc12"
						onclick="deleteMicroBlog(<s:property value='microblogid' />)">删除&nbsp;|&nbsp;</a>
				</s:if> 
					
					<a href="javascript:void(0)"  class="linkc12" onclick="transpond('<s:property value='getShowPageViewNameByUserId(userid)' />',<s:property value='microblogid' />,<s:property value='userid' />,<s:property value='transpondid' /><s:if test="transpondid!=0">,<s:property value='transpondIrpMicroblog(transpondid).isdel' /></s:if>)">
				转发(<label id="microblogtranspondcount<s:property value='microblogid' />"
				
					style="color: rgb(135, 173, 88);"><s:property value='transpondcount' /> </label>)</a>
					 &nbsp;|&nbsp;<a href="javascript:void(0)" class="linkc12"  onclick="microblogComment(<s:property value='microblogid' />)">评论(<label id="microblogcommentcount<s:property value='microblogid' />"
					style="color: rgb(135, 173, 88);"><s:property  value='commentcount' />
				</label>)</a> <s:if test="microblogid in collectionOfUseridlist">
                &nbsp;|&nbsp;<a href="javascript:void(0)"
						id="<s:property value='microblogid' />" class="linkc12"
						onclick="collect(<s:property value='microblogid' />)">取消收藏</a>
				</s:if> <s:if test="microblogid not in collectionOfUseridlist">         
                &nbsp;|&nbsp;<a href="javascript:void(0)"
						id="<s:property value='microblogid' />" class="linkc12"
						onclick="collect(<s:property value='microblogid' />)">收藏</a>
				</s:if>
				</div>
				
				
			<div id="commentDiv<s:property value='microblogid' />" style="display: none; ">
			
			
   			          <span id="microCommentFont_01<s:property value='microblogid' />"  style="float: right;">还可以输入<label id="microCommentFontCount_01<s:property value='microblogid' />" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;"></label>个字</span>
				       <span id="microCommentFont_02<s:property value='microblogid' />"  style="float: right; display: none;">已超出<label id="microCommentFontCount_02<s:property value='microblogid' />" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color:red;"></label>个字</span>
				       
				       
				<textarea style="width: 550px;" rows="4" id="commentInfo<s:property value='microblogid' />" onkeyup="microblogCommentFontInfo(<s:property value='microblogid' />)" ></textarea>
				
				<p style="text-align: right;">
			           	   
					<span>
					<a class="zhuanz1"  href="javascript:void(0)" onclick="commentReply(<s:property value='microblogid' />,<s:property value='userid' />)">回复</a></span>
				</p>
				<p style="text-align: right;" id="apendSpanMicroComment<s:property value='microblogid' />"></p>
			</div>
		</dd>
	</dl>
  
    </s:iterator>
    <ul>
	<s:property value="pageHTML" escapeHtml="false" />
   </ul>
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