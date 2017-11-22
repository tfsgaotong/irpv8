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
	$(function(){
		showResMComment();
	
	
	
	
	});
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
	* 收到的评论
	*/
	function showResMComment(){
		$('#commentmaindiv').css("display","none");
		$.ajax({
			type:"post",
			url:"<%=rootPath%>microblog/microblogCommentView.action",
			data:{
				pageNum:1
			},
			cache:false,
			async:false,
			success:function(html){
					$('#commentmaindiv').html(html);
					$('#commentmaindiv').fadeIn();
			}
		});
	}
	//受到的
	function pageShow(_pages){
		$('#commentmaindiv').css("display","none");
		$.ajax({
			type:"post",
			url:"<%=rootPath%>microblog/microblogCommentView.action",
			data:{
				pageNum:_pages
			},
			cache:false,
			async:false,
			success:function(html){
					$('#commentmaindiv').html(html);
					$('#commentmaindiv').fadeIn();
			
			}
		});
	}
	//发出的
	function page(_pages){
		$('#commentmaindiv').css("display","none");
		$.ajax({
			type:"post",
			url:"<%=rootPath%>microblog/microblogCommentViewSend.action",
			data:{
				pageNum:_pages
			},
			cache:false,
			async:false,
			success:function(content){
					$('#commentmaindiv').html(content);
					$('#commentmaindiv').fadeIn();
			}
		});
	}
	/**
	* 收到的评论
	*/
	function linkResverComment(){
	
		$("section[class='setUp']").find("div[class='tabTitlenoline']").find("div:eq(0)").addClass("current");
		$("section[class='setUp']").find("div[class='tabTitlenoline']").find("div:not(:eq(0))").removeClass("current");

		showResMComment();
	}
	/**
	* 发出的评论
	*/
	function linkOutComment(){
		$("section[class='setUp']").find("div[class='tabTitlenoline']").find("div:eq(1)").addClass("current");
		$("section[class='setUp']").find("div[class='tabTitlenoline']").find("div:not(:eq(1))").removeClass("current");
		$('#commentmaindiv').css("display","none");
		$.ajax({
			type:"post",
			url:"<%=rootPath%>microblog/microblogCommentViewSend.action",
			data:{
				pageNum:1
			},
			cache:false,
			async:false,
			success:function(content){
					$('#commentmaindiv').html(content);
					$('#commentmaindiv').fadeIn();
			}
		});
	
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
	//删除给我的评论
	function deleteMicroblogCommentWiteMe(_commentid,_microblogid){
		var commentdiv = "#"+_commentid+"div";
		$.dialog.confirm('你确定要删除这条微知吗',function(){
			$.ajax({
				type:"POST",
				url:"<%=rootPath%>microblog/microblogCommentDeleteWithMe.action",
				data:{
					commentid:_commentid,
					replaymicroblogid:_microblogid
				},
				cache:false,
				async:false,
				success:function(_msg){
					if(_msg==1){
						$(commentdiv).fadeOut();
						$(commentdiv).fadeOut("slow");
						$(commentdiv).fadeOut(3000);
					}else{
						$.dialog.tips('删除失败',1,'32X32/fail.png');	
					}
					
				},
				error:function(){
					$.dialog.tips('删除失败',1,'32X32/fail.png');
						return false;
				}
			});
		});
	
	}
</script>


</head>

<body>

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
            	<p style="cursor: pointer;"  onclick="linkMess()"  ><a>私信</a></p>
            </div>  
                        <div class="folder">
            	<p  class="current" style="cursor: pointer;" onclick="linkComment()"><a>评论</a></p>
            </div>
             <div  class="folder">
            	<p  style="cursor: pointer;" onclick="linkAtme()"> <a>@我</a></p>
            </div>

            <div class="folder">             </div>   
        </nav>
  </section>

  
  <section class="setUp">
            <div class="tabTitlenoline">
        	<div class="current">
        		<a href="javascript:void(0)"  onclick="linkResverComment()">收到的评论</a><p class="labBg"></p>
       		</div>
            <div>
            	<a href="javascript:void(0)"  onclick="linkOutComment()">发出的评论</a><p class="labBg"></p>
            </div>
            	
				
        </div>
                <div class="area20"></div>
         <div  class="pan" id="commentmaindiv" style="display: none;">

	
      
      
      
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