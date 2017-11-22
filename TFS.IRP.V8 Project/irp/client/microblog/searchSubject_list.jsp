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
<title>我的检索专题<s:property value="getIrpTopic(topicname).topicname" /></title>
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
	$('#microblogContentprompt_02').find('a').each(function(){
		$(this).hover(
			function(){
				$(this).css({"font-size":"14px","font-weight":"bold"});
			},
			function(){
				$(this).css({"font-size":"14px","font-weight":"normal"});
			}
		);
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

//刷新
function reloadpage(){
	window.location.reload();
	return true;
}
//分页
function page(_nPageNum){
	$('#pageNum').val(_nPageNum);
	var queryString = $('#pageForm').serialize();
	window.location.href="<%=rootPath%>personalsearch/searchsubject_detail.action?"+queryString+"&personalSearchId="+<s:property value="personalSearchId"/>;
}

//跳到添加文档
function documentinfo_see(_docid){ 
	window.open('<%=rootPath %>site/client_documentinfo.action?refrechstatus=1&docid='+_docid);  
}

//搜索名字
function searchInfo1(searchInfo){  
	searchtype = 5;  
	if(searchInfo.length>38){
		searchInfo = searchInfo.substring(0,37);	
	}
	var eacapeInfo = encodeURI(searchInfo);
	   window.open('<%=rootPath%>solr/searchcontentofsolr.action?searchInfo='+eacapeInfo+'&searchtype='+searchtype); 
}
</script>
</head>
<body>
<form id="pageForm">
<s:hidden name="pageNum" id="pageNum"  />
</form>
<!--头部菜单-->
<jsp:include page="../include/client_head.jsp" />
<div class="main">
	<div class="left">
		<div style="margin: 20px auto;">
			<label style="font-size: 16px;font-weight:bold;">检索专题 :  <s:property value="personalSearch.searchname"/></label>
		</div>
		<ul>
			<li style="text-align: left;width: 676px;font-size:14px;">
				描述 : &nbsp;&nbsp;<s:property value="personalSearch.searchdesc"/><div style="float:right; "></div>
			</li>
		</ul>
		<div style="border-bottom: 1px solid #ECECEC;height: 10px;"></div>
		<div class="fyh" style="margin-top: 20px;" id="maxdiv">
<s:if test="documentlist!=null & documentlist.size()>0">
	<s:iterator value="documentlist" >
		<s:set var="doccruser" value="findUserByUserId(CRUSERID.toString().substring(1,CRUSERID.toString().length()-1))" />
			<dl>
		<s:if test="#doccruser.userpic!=null">
				<dt>
					<a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='#doccruser.userid' /> " >
						<img src="<%=rootPath%>file/readfile.action?fileName=<s:property value='#doccruser.userpic' />" alt="用户头像" width="48px" />
					</a>			
				</dt>
		</s:if>
		<s:else>
				<dt>
					<a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='#doccruser.userid' /> " >
						<img id="microblogPersonalCard<s:property value='DOCID' />"	<s:if test="#doccruser.sex==2">src="<%=rootPath%>client/images/female.jpg"</s:if><s:else>src="<%=rootPath%>client/images/male.jpg"</s:else> alt="用户头像" width="48px" />
					</a>
				</dt>
		</s:else>
				<dd>
					<a class="linkb14" onclick="documentinfo_see(<s:property value='DOCID' />)" href="javascript:void(0)"><s:property value='DOCTITLE.toString().substring(1,DOCTITLE.toString().length()-1)' escapeHtml="false" /></a>&nbsp;.
					<a style="color: black" target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='#doccruser.userid' /> " class="linkb14"> 
			       		<s:property value="#doccruser.showName" />
					</a>
					<s:date name="CRTIME" format="yyyy-MM-dd" />
					<br/>
					标签 :
					<s:iterator value="DOCKEYWORDS.toString().substring(1,DOCKEYWORDS.toString().length()-1).split(',')"  status="st" var="as">
						<a style="color: #6CAAD1" href="javascript:void(0);" onclick="searchInfo1('<s:property value="#as"/>')"><s:property value="#as" /></a>&nbsp;&nbsp;
					</s:iterator>
					<br/>
					核心提示 : <s:property value="DOCABSTRACT.toString().substring(1,DOCABSTRACT.toString().length()-1)"/>
				</dd>
				<dd style="height:20px;">&nbsp;</dd>
			</dl>
	</s:iterator>
</s:if>
<s:else>未找到引用的知识</s:else>
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