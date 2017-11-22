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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>专题       	||  <s:property value="getIrpTopic(topicname).topicname" /></title>
<link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css">
<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css">
<link href="<%=rootPath%>view/css/discuz.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=rootPath %>view/js/modernizr.custom.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/easyui-lang-zh_CN.js"></script>
</head>
<body>
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
	//$('#publish_info').val($('#topicnameval').text());
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
<s:include value="../include/client_head.jsp"></s:include>

<section class="mainBox">
	<nav class="publicNav">
		
    </nav>
</section>
<section class="mainBox">
	<section class="weibo" style="margin-left:15px;">
    <div class="area20"></div>
    <div class="titlezt">
    	<span>
    		<strong>导语:</strong>
    	</span>
   		<a id="topicnameval" href="javascript:void()" onclick="reloadpage()">
   			<s:property value="getIrpTopic(topicname).topicname"  />
   		</a>
    	<label id="topicdesclabel"><s:property value="getIrpTopic(topicname).topicdesc"/></label>
    </div>
    <div class="titler">
    	<%if(loginUser.isTopicManager()){ %>
    		<aside>
				<a href="javascript:void(0)" class="linkb14" onclick="deletetopic(<s:property value="getIrpTopic(topicname).topicid" />,'<s:property value="getIrpTopic(topicname).topicname" />')">删除</a>&nbsp;&nbsp;<a class="linkb14" href="javascript:void(0)" onclick="topicDesc(<s:property value="getIrpTopic(topicname).topicid" />)" >描述</a>
    		</aside>
    	<%}%>
    	
    </div>
    <!--微知用输入框-->
     <div class="maintext">
     	<textarea id="publish_info" onkeyup="microblogInfoControl(this.value)" ><s:property value="getIrpTopic(topicname).topicname"  /></textarea>
     	
       	<section id="microblogContentprompt_01" class="textCount">您还可以输入<font id="microblogContentCount_01"></font>字</section>
       	<section style="display:none;" id="microblogContentprompt_02" class="textCount" >您还可以输入<font id="microblogContentCount_02">240</font>字</section>
       	
       	<aside class="sub">
       		<input type="submit"　id="replytopiccontent"  onclick="publishbytopic()" value="发&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;布"/>
       	</aside>
       	
        <div class="clear"></div>
  	 </div>
     	 
     <section class="discussions">
	     <s:iterator value="topicmicroblog" >
	     <s:set var="microblogobj" value="transpondIrpMicroblog(TRANSPONDID)" ></s:set>
			<div id="<s:property value='microblogid'/>div" onmouseover="microblogdeletejudge(<s:property value='microblogid' />,<s:property value="transpondid" />)" class="labs" >
            	<dl>
				
                	<dt>
                	
                		<a href="#" target="_blank">
                			<s:if test="findUserByUserId(userid).userpic!=null">
               				<img  id="microblogPersonalCard<s:property value='microblogid' />" src="<%=rootPath%>file/readfile.action?fileName=<s:property value='findUserByUserId(userid).userpic' />" alt="用户头像"  />
                			</s:if>
                			<s:else>
                			<img  id="microblogPersonalCard<s:property value='microblogid' />" <s:if test="SEX==2">src="<%=rootPath%>view/images/female.jpg"</s:if><s:else>src="<%=rootPath%>view/images/male.jpg"</s:else> alt="用户头像"  >
                			</s:else>
                			
               			</a>
                		<aside></aside>
               		</dt>
					
                    <dd class="text" onmouseover="giveImagesValues(<s:property value='microblogid' />)">
					
                    	<div class="user"><a target="_blank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' /> "  > <s:property value="SHOWNAME" /></a><s:if test="userid==2"><img style="float: left;margin-top: 5px;" alt="微知助手" src="<%=rootPath %>view/images/mobile-attach-4.png"></s:if><aside style="right: 11px;"><span><s:date name="CRTIME" format="yyyy-MM-dd HH:mm" /></span><span>&nbsp;来自<s:property  value="FROMDATA" /></span><a href="javascript:void(0)" id="microbloginform<s:property value='microblogid' />"   onclick="informdetail(<s:property value='microblogid' />)" >举报</a><s:if test="loginuserid==userid">&nbsp;<a href="javascript:void(0)" id="microblogDeleteLabel<s:property value='microblogid' />" onclick="deleteMicroBlog(<s:property value='microblogid' />)">删除</a></s:if></aside></div>
						<!-- 微知内容 -->
                        <p><s:property value='microblogcontent' escapeHtml="false" /></p>
                        <p  onclick="checkMaxPic('m_pic','m_pic',<s:property value='microblogid' />,'1')" id="microcontents<s:property value='microblogid' />"><s:property value='getMicroblogContent(MICROBLOGCONTENTIMG)' escapeHtml="false" /></p>	
                        
                        	<!-- 转发开始 -->
	                        <s:if test="transpondid!=0 && #microblogobj.isdel==0">
	                        <article class="quote">
	                            <p>
	                            	<a target="_blank" style="float: left;" class="linkb14"  href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='#microblogobj.userid' /> "><s:property value="getShowPageViewNameByUserId(#microblogobj.userid)" />&nbsp;:&nbsp;</a>
	                            	<s:if test="#microblogobj.userid==2"><img style="float: left;margin-top: 5px;" alt="微知助手" src="<%=rootPath %>view/images/mobile-attach-4.png"></s:if>
	                            	<span>
	                            		<p><s:property value='#microblogobj.microblogcontent' escapeHtml="false" /></p>
	                            		<p  onclick="checkMaxPic('m_pic','m_pic',<s:property value='microblogid' />,'2')" id="microcontentstran<s:property value='microblogid' />"><s:property value='getMicroblogContent(#microblogobj.microblogcontentimg)' escapeHtml="false" /></p>
	                            	</span>
	                            	
	                            	
								</p>
								
								<div class="shareInfo"><span><s:date name="#microblogobj.crtime" format="yyyy-MM-dd HH:mm" /></span><span>来自<s:property value="#microblogobj.fromdata" /> </span><aside><a target="_blank" style="margin-left: 230px;" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='#microblogobj.userid' />&focusonfus=3&microblogid=<s:property value='transpondid' />&micruserid=<s:property value='#microblogobj.userid' /> ">原文转发(<s:property value="#microblogobj.transpondcount" />)</a><a target="_blank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='#microblogobj.userid' />&focusonfus=3&microblogid=<s:property value='transpondid' />&micruserid=<s:property value='#microblogobj.userid' /> ">原文评论(<s:property value="#microblogobj.commentcount" />)</a></aside></div>
								
	                        </article>
	                        </s:if>
	                        <s:if test="#microblogobj.isdel==2">
	                        <article class="quote">
		                        <p>
	                        		抱歉，由于此微知内容不符合规定，无法查看。
		                        </p>
		                    </article>    
	                        </s:if>
	                        <s:elseif test="transpondid!=0 && #microblogobj.isdel==1">
	                        <article class="quote">
		                        <p>
		                        	抱歉，此微知已被作者删除。
		                        </p>
		                    </article>    
	                        </s:elseif>
	                        <!-- 转发结束 -->
						
                        <div class="share"><aside><a href="javascript:void(0)"   onclick="transpond('<s:property value='SHOWNAME' />',<s:property value='microblogid' />,<s:property value='USERID' />,<s:property value='transpondid' /><s:if test="transpondid!=0">,<s:property value='#microblogobj.isdel' /></s:if>)">转发(<label id="microblogtranspondcount<s:property value='microblogid' />" ><s:property value='TRANSPONDCOUNT' /></label>)</a><a href="javascript:void(0)" onclick="microblogComment(<s:property value='microblogid' />)">评论(<label id="microblogcommentcount<s:property value='microblogid' />"><s:property  value='COMMENTCOUNT' /></label>)</a><s:if test="microblogid in collectionOfUseridlist"><a href="javascript:void(0)" id="<s:property value='microblogid' />" onclick="collect(<s:property value='microblogid' />)">取消收藏</a></s:if><s:else><a href="javascript:void(0)" id="<s:property value='microblogid' />" onclick="collect(<s:property value='microblogid' />)">收藏</a></s:else></aside></div>
						<!-- 评论 -->
						<div id="commentDiv<s:property value='microblogid' />" style="display: none;text-align: center;margin-top: 10px; ">
   
							<textarea style="width: 650px;" rows="3" id="commentInfo<s:property value='microblogid' />" onkeyup="microblogCommentFontInfo(<s:property value='microblogid' />)" ></textarea>
							<p style="float: left;margin-left: 40px;">
								<span id="microCommentFont_01<s:property value='microblogid' />"  style="float: right;margin-left: -24px;">还可以输入<label id="microCommentFontCount_01<s:property value='microblogid' />" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;"></label>个字</span>
							    <span id="microCommentFont_02<s:property value='microblogid' />"  style="float: right; display: none;margin-left: -24px;">已超出<label id="microCommentFontCount_02<s:property value='microblogid' />" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color:red;"></label>个字</span>
						    </p>
							<p style="text-align: right;margin-right: 15px;">
								<span>
								<input onclick="commentReply(<s:property value='microblogid' />,<s:property value='USERID' />)" style="background: none repeat scroll 0% 0% #5F9DDD;border: medium none;border-radius: 3px;text-align: center;color: #FFF;line-height: 26px;width: 50;margin-top: 4px;" type="submit"  value="回复" ></span>
								
							</p><br/>
							<p style="text-align: center;margin-left: 16px;" id="apendSpanMicroComment<s:property value='microblogid' />"></p>
						</div>
						<!-- 评论结束 -->
                    </dd>
					
                    <dd class="clear"></dd>
					
					
                </dl>
            </div>
	     </s:iterator>

     
     </section> 
     	 
     	 
     	 
     	 
    </section>
	<script>
    	$(document).ready(function(){
			$(".infomation").height($(".weibo").height());
			});
		
    </script>
	<aside class="infomation">
			<div class="area10"></div>
    </aside>
</section>
<div class="area10"></div>
<footer><section class="mainBox"><span>北京睿思鸣信息技术有限公司版权所有©1997-2014&nbsp;|&nbsp;</span><a href="#" target="_blank">关于</a>&nbsp;<a href="#" target="_blank">商务合作</a>&nbsp;<a href="#" target="_blank">官方博客</a>&nbsp;<a href="#" target="_blank">官方微知</a>&nbsp;&nbsp; </section></footer>

</body>
</html>