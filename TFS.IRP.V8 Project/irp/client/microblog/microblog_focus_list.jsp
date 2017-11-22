<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看粉丝/关注</title>
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
<script language="JavaScript" type="text/javascript">
function documentinfo_see(_docid){ 
	window.open('<%=rootPath %>site/client_documentinfo.action?refrechstatus=1&docid='+_docid);  
}
$(function(){
	if(${focususermenucss}==1){
		$('#focususer_01').addClass("x");
		$('#focususer_02').css({"background-color": ""});
		$('#focususer_03').css({"background-color": ""});
		$('#focususer_04').removeClass();
	}else if(${focususermenucss}==2){
	    $('#focususer_01').removeClass();
		$('#focususer_02').css({"background-color": "rgb(239, 252, 255)"});
		$('#focususer_03').css({"background-color": ""});
		$('#focususer_04').removeClass();
	}else if(${focususermenucss}==3){
		$('#focususer_01').removeClass();
		$('#focususer_02').css({"background-color": ""});
		$('#focususer_03').css({"background-color": "rgb(239, 252, 255)"});
		$('#focususer_04').removeClass();
	}else if(${focususermenucss}==5){
		$('#focususer_01').removeClass();
		$('#focususer_02').css({"background-color": ""});
		$('#focususer_03').css({"background-color": ""});
		$('#focususer_04').addClass("x");
	}else{
		$('#focususer_01').addClass("x");
		$('#focususer_02').css({"background-color": ""});
		$('#focususer_03').css({"background-color": ""});
	}
	
	
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
//人员关注
function focususer(){
	   window.location.href="<%=rootPath %>microblog/microblogFocusListById.action?eachuserid=0&focususermenu=1&pageNum=1";
}
//全部关注
function focususerall(){

	   window.location.href="<%=rootPath %>microblog/microblogFocusListById.action?eachuserid=0&focususermenu=2&pageNum=1";
}
//相互关注
function focususerEach(){

	   window.location.href="<%=rootPath %>microblog/microblogFocusListById.action?eachuserid=1&focususermenu=3&pageNum=1";
}
//粉丝
function userEach(){
	
	   window.location.href="<%=rootPath %>microblog/microblogFocusListById.action?eachuserid=3&focususermenu=5&pageNum=1";
	
}
   /*分页*/
   function page(_nPageNum){
		$('#pageNum').val(_nPageNum);
		var queryString = $('#pageForm').serialize();
	if($('#focususer_01').hasClass("x")==true){
		 window.location.href="<%=rootPath %>microblog/microblogFocusListById.action?eachuserid=0&focususermenu=1&"+queryString;
	}else if($('#focususer_02').css("background-color")=="rgb(239, 252, 255)"){
		window.location.href="<%=rootPath %>microblog/microblogFocusListById.action?eachuserid=0&focususermenu=2&"+queryString;
	}else if($('#focususer_03').css("background-color")=="rgb(239, 252, 255)"){
		   window.location.href="<%=rootPath %>microblog/microblogFocusListById.action?eachuserid=1&focususermenu=3&"+queryString;
	}else if($('#focususer_04').hasClass("x")==true){
		 window.location.href="<%=rootPath %>microblog/microblogFocusListById.action?eachuserid=3&focususermenu=5&"+queryString;
	}else{
		
	}
	}
   /*构建私信框*/
   function messageContentView(_messageuser,_messageid){ 
   	//构建页面结果
   	var result = $.ajax({
   		url: '<%=rootPath%>microblog/messageContentPage.action',
   		type:"post",
   		dataType: "json",
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
   		width:'450px',
   		height:'180px',
   		content:result,
   		cancelVal:'关闭',
   		okVal:'发送',
   		cancel:true,
   		ok:function(){
   			
   		//获取隐私设置
   	        var messagelimits = $.ajax({
   		    	     type:"post",
   		    	     url:"<%=rootPath%>microblog/getprivacyofmessage.action",
   		    	     data:{
   		    	        	userid:_messageid
   		            	},
   		            	cache:false,
   		            	async:false
   		             }).responseText;
   		    	if(messagelimits=="false"){
   		    		 $.dialog.tips("由于用户设置，您无法发送私信。",1,"alert.gif");	
   		    		return false;
   		    	}else{
   		    		
   		    	 if($('#messageInfo').val().trim().length>${messagecount}){
   			    	return false;
   			    }else if($('#messageInfo').val().trim().length<=0){
   			    	
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
   	$('#messageInfo').focus();
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
    
    
   
   
   
   
   
   
   	
</script>
	<form id="pageForm">
		<s:hidden name="pageNum" id="pageNum" />
		<s:hidden name="pageSize" id="pageSize" />
	</form>
<div class="bg01">
<!--头部菜单-->
<jsp:include page="../include/client_head.jsp" />
<!--头部菜单end-->
<div class="main-gr">
<div class="left">
	<div class="leftmenu">
    	<h1>关注/粉丝</h1>
        <dl>
        	<dt><em class="a"></em>
        	        <a id="focususer_01"  href="javascript:void(0)" onclick="focususer()" >关注</a></dt>
            	  <dd><a id="focususer_02" href="javascript:void(0)" onclick="focususerall()" >全部关注</a> 
            	  <a id="focususer_03" href="javascript:void(0)" onclick="focususerEach()">相互关注</a></dd>
            	   
       	 	<dt><em class="b"></em><a id="focususer_04" href="javascript:void(0)" onclick="userEach()">粉丝</a></dt>
        </dl>
    </div>

</div>
<div class="right">
	<div class="gr">
	  <h1 ><a href="<%=rootPath %>microblog/microblogFocusListById.action?eachuserid=0&focususermenu=1">已关注<label id="focususercount"><s:property value="MicroblogFocusCountUserid(loginUserid)" /></label>人</a>
	   <a href="<%=rootPath %>microblog/microblogFocusListById.action?eachuserid=3&focususermenu=5" style="margin-left: 10px;">已有<s:property value="MicroblogFocusCountFocusUserid(loginUserid)" />人关注你</a></h1>
	  </div>
	  <s:if test="focususermenucss!=5">
	  <s:if test="irpMicroblogFocuslist.size!=0">
	  
	  <s:iterator value="irpMicroblogFocuslist">
	  <s:if test="findIrpUserByFocusId(userid).username!=null">
    <div class="gr2">

      <table width="100%" border="0" align="center" cellpadding="10" cellspacing="0">
        <tr>
           <s:if test="findIrpUserByFocusId(userid).userpic!=null">
             <td width="14%" rowspan="4" align="center" valign="top"><a  target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' /> "><img src="<%=rootPath%>file/readfile.action?fileName=<s:property value="findIrpUserByFocusId(userid).userpic" />" alt="用户图片" width="68" height="55" /></a><br/><a href="javascript:void(0)"  onclick="messageContentView('<s:property value='getShowPageViewNameByUserId(userid)' />',<s:property value='userid' />)" >私信</a></td>
           </s:if>
           <s:if test="findIrpUserByFocusId(userid).userpic==null">
             <td width="14%" rowspan="4" align="center" valign="top"><a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' /> "><img src="<%=rootPath%>client/images/zanshi-03.gif" alt="用户图片" width="68" height="55"  /></a><br/><a href="javascript:void(0)"  onclick="messageContentView('<s:property value='getShowPageViewNameByUserId(userid)' />',<s:property value='userid' />)" >私信</a></td>
           </s:if>
          <td width="72%" align="left" style="padding-left:20px;"><a target="_bank" class="linkb14" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' /> "><s:property value="getShowPageViewNameByUserId(userid)" /></a> &nbsp;&nbsp;&nbsp;&nbsp;<s:property value="findIrpUserByFocusId(userid).location" /></td>
          <td width="14%" align="left" style="padding-left:20px; background-color:#EAFDF5">
          
          <s:if test="getLoginUserId()!=userid">
             <s:if test="userid in allUseridByFocusUserId && loginUserid in allUseridByUserid(userid)">
               <span class="STYLE1" id="eachFocus_01<s:property value="focusid" />">互相关注</span><br/><s:if test="userid!=2"><a href="javascript:void(0)" onclick="cancelEachFocus(<s:property value="userid" />,<s:property value="focusid" />)"><label id="eachFocus_02<s:property value="focusid" />">取消</label></a></s:if>
             </s:if>
             <s:elseif test="userid in allUseridByFocusUserId">
               <span class="STYLE1" id="alreadyFocus_01<s:property value="focusid" />">已关注</span><br/><s:if test="userid!=2"><a href="javascript:void(0)" onclick="cancelFocus(<s:property value="userid" />,<s:property value="focusid" />)"><label id="alreadyFocus_02<s:property value="focusid" />">取消</label></a></s:if>
             </s:elseif>
             <s:elseif test="userid not in allUseridByFocusUserId">
               <span class="STYLE1" id="notFocus_01<s:property value="focusid" />">未关注</span><br/><a href="javascript:void(0)" onclick="okFocus(<s:property value="userid" />,<s:property value="focusid" />)"><label id="notFocus_02<s:property value="focusid" />">关注</label></a>
             </s:elseif>
           </s:if>  
          </td>
        </tr>
        <tr>
          <td colspan="2" align="left" style="padding-left:20px;"><a href="/waterwell2012?from=profile&amp;wvr=3.6&amp;loc=infweihao">
                  <a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' />&focusonfus=1 ">关注(<s:property value="MicroblogFocusCountUserid(userid)" />)</a> 
          &nbsp;&nbsp;<a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' />&focusonfus=2 ">粉丝 (<s:property value="MicroblogFocusCountFocusUserid(userid)" />)</a> 
          &nbsp;&nbsp;<a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' /> ">微知(<s:property value="MicroblogCountByUserid(userid)" />)</a></td>
        </tr>
        <tr>
          <td colspan="2" align="left" style="padding-left:20px;">
          <s:if test="userid==2"><img style="float: left;" alt="微知助手" src="<%=rootPath %>client/images/micropicicons/mobile-attach-4.png"></s:if>
          <s:property value="getMicroblogContent(findIrpMicroblogByUserid(userid).microblogcontent)" escapeHtml="false" />
          
          </td>
        </tr>
        <tr>
          <td colspan="2" align="left" style="padding-left:20px;"><p>&nbsp;</p></td>
        </tr>
      </table>
      </div>
      </s:if>
      </s:iterator>
       <div class="main" style="width:710px;">
        <div class="left">
           <div class="fyh">
              <ul><s:property value="pageHTML" escapeHtml="false" /></ul>
          </div>
         </div>
        </div> 
      </s:if>
      <s:else>
      暂无好友信息
      </s:else>
         
      
      </s:if>      
      
         
      <s:if test="focususermenucss==5">
      <s:if test="irpMicroblogFocusUserlist.size!=0">
       <s:iterator value="irpMicroblogFocusUserlist">
       <s:if test="findIrpUserByFocusId(focususerid).username!=null">
    <div class="gr2">

      <table width="100%" border="0" align="center" cellpadding="10" cellspacing="0">
        <tr>
           <s:if test="findIrpUserByFocusId(focususerid).userpic!=null">
             <td width="14%" rowspan="4" align="center" valign="top"><a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='focususerid' /> "><img src="<%=rootPath%>file/readfile.action?fileName=<s:property value="findIrpUserByFocusId(focususerid).userpic" />" alt="用户图片" width="68" height="55" /></a><br/><a href="javascript:void(0)"  onclick="messageContentView('<s:property value='getShowPageViewNameByUserId(focususerid)' />',<s:property value='focususerid' />)" >私信</a></td>
           </s:if>
           <s:if test="findIrpUserByFocusId(focususerid).userpic==null">
             <td width="14%" rowspan="4" align="center" valign="top"><a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='focususerid' /> "><img src="<%=rootPath%>client/images/zanshi-03.gif" alt="用户图片" width="68" height="55" /></a><br/><a href="javascript:void(0)"  onclick="messageContentView('<s:property value='getShowPageViewNameByUserId(focususerid)' />',<s:property value='focususerid' />)" >私信</a></td>
           </s:if>
          <td width="72%" align="left" style="padding-left:20px;"><a target="_bank" class="linkb14" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='focususerid' /> "><s:property value="getShowPageViewNameByUserId(focususerid)" /></a> &nbsp;&nbsp;&nbsp;&nbsp;<s:property value="findIrpUserByFocusId(focususerid).location" /></td>
          <td width="14%" align="left" style="padding-left:20px; background-color:#EAFDF5">
             <s:if test="focususerid in allUseridByFocusUserId && loginUserid in allUseridByUserid(focususerid)">
               <span class="STYLE1" id="eachFocus_01<s:property value="focusid" />">互相关注</span><br/><s:if test="focususerid!=2"><a href="javascript:void(0)" onclick="cancelEachFocus(<s:property value="focususerid" />,<s:property value="focusid" />)"><label id="eachFocus_02<s:property value="focusid" />">取消</label></a></s:if>
             </s:if>
             <s:elseif test="focususerid in allUseridByFocusUserId">
               <span class="STYLE1" id="alreadyFocus_01<s:property value="focusid" />">已关注</span><br/><s:if test="focususerid!=2"><a href="javascript:void(0)" onclick="cancelFocus(<s:property value="focususerid" />,<s:property value="focusid" />)"><label id="alreadyFocus_02<s:property value="focusid" />">取消</label></a></s:if>
             </s:elseif>
             <s:elseif test="focususerid not in allUseridByFocusUserId">
               <span class="STYLE1" id="notFocus_01<s:property value="focusid" />">未关注</span><br/><a href="javascript:void(0)" onclick="okFocus(<s:property value="focususerid" />,<s:property value="focusid" />)"><label id="notFocus_02<s:property value="focusid" />">关注</label></a>
             </s:elseif>
          </td>
        </tr>
        <tr>
          <td colspan="2" align="left" style="padding-left:20px;"><a href="/waterwell2012?from=profile&amp;wvr=3.6&amp;loc=infweihao">
         <a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='focususerid' />&focusonfus=1 "> 关注 (<s:property value="MicroblogFocusCountUserid(focususerid)" />)</a>
&nbsp;&nbsp;<a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='focususerid' />&focusonfus=2 ">粉丝 (<s:property value="MicroblogFocusCountFocusUserid(focususerid)" />)</a>
&nbsp;&nbsp;<a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='focususerid' /> ">微知(<s:property value="MicroblogCountByUserid(focususerid)" />) </a></td>
        </tr>
        <tr>
          <td colspan="2" align="left" style="padding-left:20px;">
          <s:if test="focususerid==2"><img style="float: left;" alt="微知助手" src="<%=rootPath %>client/images/micropicicons/mobile-attach-4.png"></s:if>
          <s:property value="getMicroblogContent(findIrpMicroblogByUserid(focususerid).microblogcontent)" escapeHtml="false" /></td>
        </tr>
        <tr>
          <td colspan="2" align="left" style="padding-left:20px;"><p>&nbsp;</p></td>
        </tr>
      </table>

      </div>
      </s:if>
      </s:iterator>
      <div class="main" style="width:710px;">
        <div class="left">
           <div class="fyh">
       <ul ><s:property value="pageHTML" escapeHtml="false" /></ul>
           </div>
       </div>
       </div>
       </s:if>
       <s:else>
       暂无好友信息
       </s:else>
       
       
  </s:if>
 
</div>
</div>

<!--尾部信息-->
<jsp:include page="../include/client_foot.jsp" />
<!--尾部信息end-->
</div>
</body>
</html>
