<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<html lang="en" class="no-js">
<head>
<meta charset="utf-8">
<title>搜索结果</title>
<link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css">
<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css">
<link href="<%=rootPath%>view/css/discuz.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/modernizr.custom.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>editor/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<%=rootPath %>editor/applet/ckwordup.js"></script>
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
</style>
<script type="text/javascript">
var microblogcontentxfid = 0;
var microblgnumberComment = 0;
var replycommentidpage=0;
$(function(){
	findRefreshSContent(1);
	
});
//取消关注
function cancelFocus(_userid,_microblogid){
	var alreadyFocus_01="#alreadyFocus_01"+_userid;
	var alreadyFocus_02="#alreadyFocus_02"+_userid;
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
	var notFocus_01="#notFocus_01"+_userid;
	var notFocus_02="#notFocus_02"+_userid;
	alert($(notFocus_01).text());
    if($(notFocus_01).text()=='未关注'){	
    	alert("123");
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
	
	var eachFocus_01="#eachFocus_01"+_userid;
	var eachFocus_02="#eachFocus_02"+_userid;
	
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

function findRefreshSContent(_pageNum){
	var paramMap = "<s:property value='paramMap' />";
	if(paramMap==null || paramMap==undefined || paramMap==""){
		paramMap="ALL";
	}
	var searchInfo = '<s:property value="searchInfo" />';
	var searchtype = '<s:property value="searchtype" />';
	var searchsort = '<s:property value="searchsort" />';
	if($.trim(searchtype)!="" && $.trim(searchInfo)!=""){
		$("div[class='rightBox searchSec']").find("input[type='text']").val(searchInfo);
		if(searchtype=='2'){
			//microblog
			$("#rad_weibo").attr("checked","checked");
			$("#rad_weibo").attr("searchtype","2");
			$.ajax({
				type:"post",
				url:"<%=rootPath%>solr/searchmicroblog.action",
				data:{
					searchtype:searchtype,
					pageNum:_pageNum,
					paramMap:paramMap,
					searchInfo:searchInfo,
					searchsort:searchsort
				},
				cache:false,
				async:false,
				success:function(content){
					if(content!=""){
						$("#searchcontentview").html(content);
					
					}else{
					
					} 
				} 
			}); 
		}else if(searchtype=='5'){
			//enterprise knowledge
			$("#rad_doc").attr("checked","checked"); 
			$("#rad_doc").attr("searchtype","5");
			$.ajax({
				type:"post",
				url:"<%=rootPath%>solr/searchdocument.action",
				data:{
					searchtype:searchtype,
					pageNum:_pageNum,
					paramMap:paramMap,
					searchInfo:searchInfo,
					searchsort:searchsort
				},
				cache:false,
				async:false,
				success:function(content){
					if(content!=""){
						$("#searchcontentview").html(content);
					
					}else{
					
					} 
				} 
			}); 
			
		}else if(searchtype=='51'){
			//enterprise knowledge
			$("#rad_doc").attr("checked","checked"); 
			$("#rad_doc").attr("searchtype","51");
			$.ajax({
				type:"post",
				url:"<%=rootPath%>solr/searchdocument.action",
				data:{
					searchtype:searchtype,
					pageNum:_pageNum,
					paramMap:paramMap,
					searchInfo:searchInfo,
					searchsort:searchsort
				},
				cache:false,
				async:false,
				success:function(content){
					if(content!=""){
						$("#searchcontentview").html(content);
					
					}else{
					
					} 
				} 
			}); 
			
		}else if(searchtype=='52'){
			//enterprise knowledge
			$("#rad_doc").attr("checked","checked");
			$("#rad_doc").attr("searchtype","52"); 
			$.ajax({
				type:"post",
				url:"<%=rootPath%>solr/searchdocument.action",
				data:{
					searchtype:searchtype,
					pageNum:_pageNum,
					paramMap:paramMap,
					searchInfo:searchInfo,
					searchsort:searchsort
				},
				cache:false,
				async:false,
				success:function(content){
					if(content!=""){
						$("#searchcontentview").html(content);
					
					}else{
					
					} 
				} 
			}); 
			
		}else if(searchtype=='6'){//问答
			//person
			$("#rad_que").attr("checked","checked"); 
			$("#rad_que").attr("searchtype","6");
			$.ajax({
				type:"post",
				url:"<%=rootPath%>solr/searchquestion.action",
				data:{
					searchtype:searchtype,
					pageNum:_pageNum,
					paramMap:paramMap,
					searchInfo:searchInfo,
					searchsort:searchsort
				},
				cache:false,
				async:false,
				success:function(content){
					if(content!=""){
						$("#searchcontentview").html(content);
					
					}else{
					
					} 
				} 
			}); 
			
		} else if(searchtype=='1'){
			//person
			$("#rad_user").attr("checked","checked"); 
			$.ajax({
				type:"post",
				url:"<%=rootPath%>solr/searchuser.action",
				data:{
					searchtype:searchtype,
					pageNum:_pageNum,
					paramMap:paramMap,
					searchInfo:searchInfo,
					searchsort:searchsort
				},
				cache:false,
				async:false,
				success:function(content){
					if(content!=""){
						$("#searchcontentview").html(content);
					
					}else{
					
					} 
				} 
			}); 
			
		}
	}
} 
function page(_nPageNum){
	findRefreshSContent(_nPageNum);
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
function giveImagesValues(_microblogid){
	microblogcontentxfid = _microblogid;
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
		       	 	 var editor= CKEDITOR.instances["transpondInfo"];
	        	    if (tea.setSelectionRange){ 
	        	        editor.focus();
	        	        setTimeout(function() { 
	        	        tea.setSelectionRange(0, 0); //将光标定位在textarea的开头，需要定位到其他位置的请自行修改 
	        	        tea.focus(); 
	        	        }, 0); 
	        	        }else if(tea.createTextRange){
	        	        // 将光标移至最末
						    var range = editor.createRange();
						    range.moveToElementEditEnd(editor.editable());
						    range.select();
						    range.scrollIntoView(); 
	        	        	 range = tea.createTextRange();
	        	        	range.moveStart("character",1);
	        	        	range.collapse(true);
	        	        	//alert(range.text.length);
	        	        	//txt.moveStart('character',obj.value.length);
	        	        	range.select();
	        	         	editor.focus();
    					
	        	        } 
		},
		ok:function(){
				//转发
				var editor= CKEDITOR.instances["transpondInfo"];
				var length=editor.document.getBody().getText();
				var transpondInfo =  editor.getData();
				if($.trim(length).length>tranmicronumberwrod){	
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
			    	
					if($.trim(length).length==0){
						$('#transpondInfo').val("转发微知");
					}
					transpondInfo =  editor.getData();
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
				                       replaycontent:transpondInfo,
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
* 展开评论
*/
function microblogComment(_microblogid){	
		var commentdiv = "#commentDiv"+_microblogid;
		var appandspancomment = "#apendSpanMicroComment"+_microblogid;
		var commentdivinfo="#commentInfo"+_microblogid;
		var commenttextarea = "#microCommentFontCount_01"+_microblogid;
		$(commentdivinfo).val("");
		$(appandspancomment).empty();
		var twoname="commentInfo"+_microblogid;
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
    				var editor1=CKEDITOR.replace(''+twoname,{
					smiley_columns:10,
				   language : 'zh-cn',
				  	width : 650,
				  	height : 150,
				  	uiColor : '#FFFFddf',
				  	toolbar : 'Basic',
				    toolbar_Basic : [  ['Smiley']  ],
				   	toolbarCanCollapse: false,
				    toolbarLocation: 'bottom',
				    allowedContent :true,
					format_p :{ element: 'p', attributes: { 'class': 'normalPara' } },
				    resize_enabled : false,
				    dialog_backgroundCoverOpacity : 0.5
				});

	   		 editor1.on("key", function (event) {  
	          var data = this.getData();
	           document.getElementById(""+twoname).innerHTML =data; 
	          var text= editor1.document.getBody().getText();
	          microblogCommentFontInfo(_microblogid);
	    });
			
			
			  $(commenttextarea).text(microblgnumberComment);
			  $(commentdiv).slideDown();
			  $(appandspancomment).append(callback);
			  }
		});
	}else{
		 $(commentdiv).slideUp();
		  CKEDITOR.instances[''+twoname].destroy();
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
		 var editor= CKEDITOR.instances[''+twoname];
		var commentInfoid ="#commentInfo"+_microblogid;
		var commentInfo =  editor.getData();
		var length=editor.document.getBody().getText();
		if(commentInfo!=""){
		commentInfo=commentInfo.replace("<p>","").replace("</p>","");
		}
		editor.document.getBody().setHtml(commentInfo+"回复@"+_showname+": ||"+_content);
		editor.focus();
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
    	 var editor= CKEDITOR.instances[''+twoname];
		var commentTextAreaLength = editor.document.getBody().getText();
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
     var editor= CKEDITOR.instances[''+twoname];
		var commentInfoid ="#commentInfo"+_microblogid;
		var commentInfo =  editor.getData();
		var length=editor.document.getBody().getText();
		editor.document.getBody().setHtml("");
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
		  if($.trim(length).length>microblgnumberComment){
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
		    					editor.document.getBody().setHtml("");
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
microblogComNum();
</script>
</head>

<body>
<s:include value="../include/client_head.jsp"></s:include>

<section class="mainBox">
	<nav class="privateNav">
    	<dl>
        </dl>
    </nav>
</section>
<section class="mainBox">
	<article class="location"><strong><a href="#" target="_blank">检索</a><a href="#" target="_blank"></a></strong>  <a href="#" target="_blank"></a>  <a href="#" target="_blank"></a></article>
	<section class="layoutLeft">
        
        <nav class="folders">
        	<div id="enterpriseknowleftmenus" class="folder" style="cursor: pointer;">
            	<p style="<s:if test="searchtype==5">height:40px; line-height:40px; background:url(<%=rootPath %>view/images/icon-06.png) no-repeat 9px center #5f9ddd; color:#fff; border:none;</s:if>" onclick="searchVal('<s:property value="searchInfo"/>','rad_doc')">企业知识</p>
            </div>
            <div id="enterpriseknowlef" class="folder" style="cursor: pointer;width: 215px;padding-left: 30px;">
            	<p style="<s:if test="searchtype==51">height:40px; line-height:40px; background:url(<%=rootPath %>view/images/icon-06.png) no-repeat 9px center #5f9ddd; color:#fff; border:none;</s:if>" onclick="searchVal('<s:property value="searchInfo"/>','rad_doc_keyword')">标签</p>
            </div>
             <div id="enterpriseknowlef" class="folder" style="cursor: pointer;width: 215px;padding-left: 30px;">
            	<p style="<s:if test="searchtype==52">height:40px; line-height:40px; background:url(<%=rootPath %>view/images/icon-06.png) no-repeat 9px center #5f9ddd; color:#fff; border:none;</s:if>" onclick="searchVal('<s:property value="searchInfo"/>','rad_doc_user')">人员</p>
            </div>
        	<div id="microblogleftmenus" class="folder" style="cursor: pointer;">
            	<p style="<s:if test="searchtype==2">height:40px; line-height:40px; background:url(<%=rootPath %>view/images/icon-06.png) no-repeat 9px center #5f9ddd; color:#fff; border:none;</s:if>" onclick="searchVal('<s:property value="searchInfo"/>','rad_weibo')">微知</p>
            </div>
            <div id="microblogleftmenus" class="folder" style="cursor: pointer;">
            	<p style="<s:if test="searchtype==6">height:40px; line-height:40px; background:url(<%=rootPath %>view/images/icon-06.png) no-repeat 9px center #5f9ddd; color:#fff; border:none;</s:if>" onclick="searchVal('<s:property value="searchInfo"/>','rad_que')">问答</p>
            </div>
           <%--  <div id="userleftmenus" class="folder" style="cursor: pointer;">
            	<p style="<s:if test="searchtype==1">height:40px; line-height:40px; background:url(<%=rootPath %>view/images/icon-06.png) no-repeat 9px center #5f9ddd; color:#fff; border:none;</s:if>" onclick="searchVal('<s:property value="searchInfo"/>','rad_user')">用户</p>
            </div> --%>
        </nav>
  </section>
    
    <section class="result" id="searchcontentview"></section>
</section>

<div class="area10"></div>
<s:include value="../include/client_foot.jsp"></s:include>
</body>
</html>
