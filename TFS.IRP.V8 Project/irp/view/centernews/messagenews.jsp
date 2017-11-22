<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.tfs.irp.util.LoginUtil"%>
<%@ page import="com.tfs.irp.user.entity.IrpUser"%>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	IrpUser loginUser = LoginUtil.getLoginUser();
%>
<!doctype html>
<html lang="en" class="no-js">
<head>
<meta charset="utf-8">
<title>消息中心</title>
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
<link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css">
<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css">
<link href="<%=rootPath%>view/css/discuz.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/modernizr.custom.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var messageidinfoid = 0;
	var cruseridinfoid = 0;
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
	$(function(){
		receiveMessageList();
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
	});
	/**
	* 收到的私信
	*/
	function receiveMessageList(){
		$('#irpmessagediv').empty();
		$.ajax({
			type:"post",
			url:"<%=rootPath%>microblog/searchMessage.action",
			data:{
				pageNum:1
			},
			success:function(callback){
				$('#irpmessagediv').append(callback);
				$('#irpmessagediv').fadeIn();
			}
			
		});	
	}
	/**
	* 删除一组私信
	*/
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
	/**
	* 删除发出的私信
	*/
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
	/**
	* 构建私信框
	*/
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
			width:'400px',
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
	/**
	* 左侧私信
	*/
	function linkMess(){
		
		window.location.href="<%=rootPath%>microblog/linkmessageview.action";
	
	}
	
	/**
	* 左侧评论
	*/
	function linkComment(){
		
		window.location.href="<%=rootPath%>microblog/linkcommentview.action";
	
	}
	/**
	* 左侧@我的
	*/
	function linkAtme(){
	
		window.location.href="<%=rootPath%>microblog/linkatmeview.action";
	
	}
	/**
	* 收到的私信
	*/
	function linkResverMes(){
		$("div[class='tabTitlenoline']").find("div:eq(0)").addClass("current");
		$("div[class='tabTitlenoline']").find("div:not(:eq(0))").removeClass("current");
		$('#irpmessagediv').css("display","none");
		$('#irpmessagediv').empty();
		$("#setUp")
		$.ajax({
			type:"post",
			url:"<%=rootPath%>microblog/searchMessage.action",
			data:{
				pageNum:1
			},
			success:function(callback){
				$('#irpmessagediv').append(callback);
				$('#irpmessagediv').fadeIn();
				
			}
			
		});	
	}
	/**
	* 发出的私信
	*/
	function linkOutMes(){
	
		$('#irpmessagediv').css("display","none");
		$('#irpmessagediv').empty();
		$("div[class='tabTitlenoline']").find("div:eq(1)").addClass("current");
		$("div[class='tabTitlenoline']").find("div:not(:eq(1))").removeClass("current");
		$.ajax({
			type:"post",
			url:"<%=rootPath%>microblog/sendOutMessage.action",
			data:{
				pageNum:1
			},
			success:function(callback){
				$('#irpmessagediv').append(callback);
				$('#irpmessagediv').fadeIn();
				
			}
			
		});	
	}
	/**
	*加载某个私信用户的信息
	*/
	function loadUserMessage(_messageid,_cruserid){
	$('#irpmessagediv').css("display","none");
	$('#irpmessagediv').empty();
	messageidinfoid = _messageid;
	cruseridinfoid = _cruserid;
	$.ajax({
		type:"post",
		url:"<%=rootPath%>microblog/searchUserMessage.action",
		cache:false,
		async:false,
		data:{
			msssageid:_messageid,
			cruserid:_cruserid
		},
		success:function(callback){
			
			$('#irpmessagediv').append(callback);
			$("#irpmessagediv").fadeIn();
		}
	});	
	}
	
	//私信分页(详细信息)
	function pageMessageInfo(_nPageNum){
	$('#irpmessagediv').css("display","none");
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
				$('#irpmessagediv').fadeIn();
			}
		});
		
	}
	/**
	* 私信回复
	*/
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
	
	/**
	* 删除一组私信详细记录
	*/
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
			url: '<%=rootPath%>user/select_user.action',
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
	    			url : "<%=rootPath%>microblog/sendmessageselect.action",
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
	
//已发出的私信分页
function pageMessageSendOut(_nPageNum){
	$('#pageNum').val(_nPageNum);
	var queryString = $('#pageForm').serialize();
	$('#irpmessagediv').css("display","none");
	$.ajax({
		type:"post",
		url:"<%=rootPath%>microblog/sendOutMessage.action?"+queryString,
		async:false,
		cache:false,
		success:function(callback){
			$("#irpmessagediv").html(callback);
			
			$('#irpmessagediv').fadeIn();
		}
	});
	
}
//私信分页
function pageMessage(_nPageNum){
	$('#pageNum').val(_nPageNum);
	var queryString = $('#pageForm').serialize();
	$('#irpmessagediv').css("display","none");
	$.ajax({
		type:"post",
		url:"<%=rootPath%>microblog/searchMessage.action?"+queryString,
		cache:false,
		async:false,
		success:function(callback){
			$('#irpmessagediv').html(callback);
			$('#irpmessagediv').fadeIn();
		}
		
	});
}

function toBugDoPage(_num){
	var urlStr="<%=rootPath%>bug/simplebuginfo.action?serianum="+_num;
	$.ajax({
		type:"post",
		url:urlStr,
		cache:false,
		async:false,
		success:function(msg){
			if(msg==0){
				$.dialog.tips('您不是这个项目的成员!',1,'alert.gif',function(){ 
					});		
			}else if(msg==1){
				$.dialog.tips('这个Bug已经被删除!',1,'alert.gif',function(){ 
				});	
			}else{
				window.open(urlStr);
			}
		}
		
	});
} 
//查看请假原因
function toleaveDoPage(id){
	$.ajax({
		url: "<%=rootPath%>leave/booleanleave.action?leaveapplyid="+id,
	    async: false,
	    cache: false,
	    success:function(msg){
		    	if(msg==1){
		    		var urlStr="<%=rootPath%>leave/leaveByLimit.action?type=10";
		    		$.ajax({
		    			type:"post",
		    			url:urlStr,
		    			cache:false,
		    			async:false,
		    			success:function(msg){
		    			
		    				if(msg==0){
		    					hrefStr="<%=rootPath%>leave/getleavebyid.action?leaveapplyid="+id;
		    					window.open(hrefStr);
		    				}else{
		    					hrefStr="<%=rootPath%>leave/getleavebyidFromManager.action?isMangcenter=1&leaveapplyid="+id;
		    					window.open(hrefStr);
		    				}
		    			}
		    			
		    		});
		    		
		    					    	}else{
		    					    		
		    					    		$.dialog.tips('该表单已经被撤消了，请刷新',1,'32X32/fail.png');	
		    	}
		    	
		    }
		})
	


}


//查看加班原因
function toworkDoPage(id,_type){
	
	$.ajax({
		url: "<%=rootPath%>leave/booleanisDelete.action?leaveapplyid="+id,
	    async: false,
	    cache: false,
	    success:function(msg){
	    	
		    	if(msg==1){
	var urlStr="<%=rootPath%>leave/leaveByLimit.action?type="+_type+"&leaveapplyid="+id;
	$.ajax({
		type:"post",
		url:urlStr,
		cache:false,
		async:false,
		success:function(msg){
		
			if(msg==0){
				
				hrefStr="<%=rootPath%>leave/getleavebyid.action?leaveapplyid="+id;
				window.open(hrefStr);
			}else{
				
				hrefStr="<%=rootPath%>leave/getleavebyidFromManager.action?isMangcenter=1&leaveapplyid="+id;
				window.open(hrefStr);
			}
		}
		
	});}else if(msg==0){
		
		$.dialog.tips('该表单已经被撤消了，请刷新',1,'32X32/fail.png');	
}

}
})

}
	//回执给会议联系人消息
function send(id,_content){
	//构建页面结果
		var result = $.ajax({
			url: '<%=rootPath%>asseroomapply/tosend.action',
			type:"post",
			dataType: "json",
			cache:false,
		    data: {
		    	sendid:id,
		    	attachsendcontent:_content
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
			width:'400px',
			height:'180px',
			content:result,
			cancelVal:'关闭',
			okVal:'发送',
			cancel:true,
			ok:function(){
				   if($.trim($('#sendcontent').val()).length>$('#messagecount').val()){
				    	return false;
				    }else if($.trim($('#sendcontent').val()).length<=0){
				    	  $.dialog.tips("私信内容不能为空",0.3,"alert.gif");	
				    	return false;
				    }else{
				    	
				$('#asseroomsend').form('submit',{
					url:'<%=rootPath%>asseroomapply/send.action',
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
		});
		/*if(_messageid==0){
			$('#messageuserinfo').focus();
		}else{
			  $('#messageInfo').focus();	
		}	*/
			
}
//查看文档
	function documentinfo_see(_docid){ 
		window.open('<%=rootPath %>document/document_detail.action?docid='+_docid);  
	}
</script>


</head>

<body>
	<form id="pageForm">
		<input type="hidden" name="pageNum" value="1" id="pageNum"/>
		<input type="hidden" name="pageSize" value="10" id="pageSize"/>
	</form>
<s:include value="../include/client_head.jsp"></s:include>

<section class="mainBox">
	<nav class="publicNav">
		
    </nav>
</section>
<section class="mainBox">

	<article class="location"><strong>消息中心</strong></article>
	<section class="layoutLeft">
        <nav class="sets">

        	<div class="folder" >
            	<p class="current" style="cursor: pointer;"  onclick="linkMess()"  ><a>私信</a></p>
            </div>  
                        <div class="folder">
            	<p  style="cursor: pointer;" onclick="linkComment()"> <a>评论</a></p>
            </div>
             <div  class="folder">
            	<p style="cursor: pointer;" onclick="linkAtme()"> <a>@我</a></p>
            </div>

            <div class="folder">             </div>   
        </nav>
  </section>

  
  <section class="setUp">
            <div class="tabTitlenoline">
        	<div class="current"><a href="javascript:void(0)"  onclick="linkResverMes()">收到的私信</a><p class="labBg"></p></div>
            <div><a href="javascript:void(0)"  onclick="linkOutMes()">发出的私信</a><p class="labBg"></p></div>
            	
			<div style="float: right;border-radius: 3px;width: 47px;height: 29px;background: none repeat scroll 0% 0% #2061B0;border: medium none;color: white;padding: 0px 20px 11px ;cursor: pointer;" onclick="messageContentViews('',0)">发私信</div>	
        </div>
                <div class="area20"></div>
         <div class="pan" id="irpmessagediv" style="display: none;">

	
      
      
      
     	 </div>
      
      
    
        <!--我的专题-->
        
    <!--delete-->
        <!--我的分组-->
        <div class="pan"></div><!--delete-->
  <!--个人标签--></section>
    
</section>



<div class="area10"></div>
<footer><section class="mainBox"><span>北京睿思鸣信息技术有限公司版权所有©1997-2014&nbsp;|&nbsp;</span><a href="#" target="_blank">关于</a>&nbsp;<a href="#" target="_blank">商务合作</a>&nbsp;<a href="#" target="_blank">官方博客</a>&nbsp;<a href="#" target="_blank">官方微知</a>&nbsp;&nbsp; </section></footer>
</body>
</html>