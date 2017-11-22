<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>检索用户</title>
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
</head>
<body>
<script type="text/javascript">
$(function(){
	//加载个人基本信息
	$.ajax({
		type:"get",
		cache:false,
		url:"<%=rootPath%>microblog/loadUserInfo.action",
		success:function(callback){
			$('.right').prepend(callback);
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
				   	$('#focususercount').text(parseInt($('#focususercount').text())+1);
				}
			}
		});
	}else{
		$.dialog.confirm('您确定要取消关注此人吗',function(){
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
					$('#focususercount').text(parseInt($('#focususercount').text())-1);
				}
			}
		});
		
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
					$('#focususercount').text(parseInt($('#focususercount').text())+1);
				}
			}
		});
	}else{
		$.dialog.confirm('您确定要取消关注此人吗',function(){
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
					$('#focususercount').text(parseInt($('#focususercount').text())-1);
				}
			}
		});
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
				   	$('#focususercount').text(parseInt($('#focususercount').text())+1);
				}
			}
		});
	}else{
		$.dialog.confirm('您确定要取消关注此人吗',function(){
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
				   	$('#focususercount').text(parseInt($('#focususercount').text())-1);
				}
			}
		});
		});
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
 //查看文档
	function documentinfo_see(_docid){ 
		window.open('<%=rootPath %>site/client_documentinfo.action?refrechstatus=1&docid='+_docid);  
	}
	//分页
	function page(_nPageNum){
		$('#pageNum').val(_nPageNum);
		var queryString = $('#pageForm').serialize();
		  var titleinfo =  '<s:property value="searchInfo" escapeHtml="false" />';
		    var titletype = '<s:property value="searchtype" />';
			if(titleinfo.length>38){
				titleinfo = titleinfo.substring(0,37);	
			}
		
		window.location.href="<%=rootPath%>solr/searchcontentofsolr.action?"+queryString+"&searchInfo="+titleinfo+"&searchtype="+titletype;
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
	    
	    
	   
</script>
<!--头部菜单-->
<jsp:include page="../include/client_head.jsp" />
<div class="main">
<!--左侧内容-->
<div class="left">
  <form id="pageForm">
		<s:hidden name="pageNum" id="pageNum" />
		<s:hidden name="pageSize" id="pageSize" />
	</form>
 <div id="" class="fyh">
      <s:if test="userlist.size()>0">
			  <s:iterator value="userlist">
	             <dl>
        	<dt><s:if test="USERPIC!=null">
			  <a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' /> " >
				<img  src="<%=rootPath%>file/readfile.action?fileName=<s:property value='USERPIC' />"
					alt="用户头像" width="55px" />
		      </a>						
		      </s:if>
		      <s:if test="USERPIC==null">			
			  <a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' /> " >
				<img <s:if test="SEX==2">src="<%=rootPath%>client/images/female.jpg"</s:if><s:else>src="<%=rootPath%>client/images/male.jpg"</s:else> alt="用户头像" width="55px" />
			  </a>
		      </s:if></dt>
            <dd><a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' /> " class="linkb14"><s:property value="USERNAME" /></a>
            <span><s:property value="getMicroblogContent(findIrpMicroblogByUserid(USERID).microblogcontent)" escapeHtml="false" /></span>
            
                <span><a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' />&focusonfus=1 ">关注(<s:property value="MicroblogFocusCountUserid(USERID)" />)</a>&nbsp;
                      <a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' />&focusonfus=2 "> 粉丝(<s:property value="MicroblogFocusCountFocusUserid(USERID)" />)</a>&nbsp;
                      <a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' /> "> 微知(<s:property value="MicroblogCountByUserid(USERID)" />)</a>&nbsp;
                 <p  style="background-color:#EAFDF5;margin-top: -18px;">
                       <s:if test="USERID in allUseridByFocusUserId && loginUserid in allUseridByUserid(USERID)">
                        <span><label class="STYLE1" id="eachFocus_01<s:property value="USERID" />" >互相关注</label><a href="javascript:void(0)" onclick="cancelEachFocus(<s:property value="USERID" />,<s:property value="USERID" />)">
                        <s:if test="USERID!=2"><label id="eachFocus_02<s:property value="USERID" />">取消</label></s:if></span></a>
                       </s:if>
                       <s:elseif test="USERID in allUseridByFocusUserId">
                        <span><label id="alreadyFocus_01<s:property value="USERID" />" class="STYLE1" >已关注</label><a href="javascript:void(0)" onclick="cancelFocus(<s:property value="USERID" />,<s:property value="USERID" />)">
                        <s:if test="USERID!=2"><label id="alreadyFocus_02<s:property value="USERID" />">取消</label></s:if></span></a>
                       </s:elseif>
                       <s:elseif test="USERID not in allUseridByFocusUserId">
                        <span><label id="notFocus_01<s:property value="USERID" />" class="STYLE1" >未关注</label><a href="javascript:void(0)" onclick="okFocus(<s:property value="USERID" />,<s:property value="USERID" />)">
                        <label id="notFocus_02<s:property value="USERID" />">关注</label></span></a>
                       </s:elseif>
                  </p>
                </span>
              </dd>
		      </dl>
	     </s:iterator>
	        <ul>
         <s:property value="pageHTML" escapeHtml="false" />
       </ul>
       </s:if>
       <s:else>
       <span>没有找到相关记录</span>
       </s:else>
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
<!--左侧内容结束-->
</body>
</html>