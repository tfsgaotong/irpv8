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
<title>检索知识库微知</title>
<link rel="Bookmark" href="<%=rootPath %>client/images/24pinico.ico" />
<link rel="Shortcut Icon" href="<%=rootPath %>client/images/24pinico.ico" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>client/css/oacss.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>client/css/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>client/css/icon.css" />
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
<script type="text/javascript" src="<%=rootPath%>client/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/easyui-lang-zh_CN.js"></script>
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
/**
 * 标签链接
 */
function searchInfo1(searchInfo){ 
	searchtype = 5;  
	if(searchInfo.length>38){
		searchInfo = searchInfo.substring(0,37);	
	}
	var eacapeInfo = encodeURI(searchInfo);
    window.location.href="<%=rootPath%>solr/searchcontentofsolr.action?searchInfo="+eacapeInfo+"&searchtype="+searchtype; 
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
   function checkMaxPic(_src){
   	$.dialog({
   		title:"查看图片",
   		content: 'url:<%=rootPath%>microblog/checkMaxPic.action?picfile='+_src,
   		cache:false,
   		cancelVal: '关闭',
   	    cancel: true,
   		max: false,
   	    min: false,
   	    lock:true,
   	    width:500,
   	    height:500
   	});
   }	
 //查看文档
	function documentinfo_see(_docid){ 
		window.open('<%=rootPath %>site/client_documentinfo.action?refrechstatus=1&docid='+_docid);  
	}
	/*处理tag事件 ;信息*/
	function tabsSelected(liid){
		$('#searchtagresult').find('a').each(function(){
			if(this.id ==liid.id){
				this.className="over";
			}else{
				this.className="";
			}
		});
		page(1);
	}  
	/*page 处理方法*/
	function page(pageNum){
		var liid = $('#searchtagresult').find('.over');
		var sUrl = liid.attr("_href");
		var sData = liid.attr("_data");
		if(sData==''){
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
		$('#microblogdocumentview').html(sHtmlConn);
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
 
    <h1 class="zl3" id="searchtagresult" style="font-size:18px; font-weight:normal;  line-height:50px; border-bottom:1px  solid #efefef;text-align:left; font-family:&#39;微软雅黑&#39;; margin:0 0 20px 5px; color:#121212;">
	   <a id="no" _href="<%=rootPath %>menu/trs_docu.action" _data="searchtype=<s:property value="searchtype" />&searchInfo=<s:property value="searchInfo" />" class="over" onclick="tabsSelected(this)"> <font color="#CC0000">知识</font></a>
	   <a id="yes" _href="<%=rootPath %>menu/trs_micro.action" _data="searchtype=<s:property value="searchtype" />&searchInfo=<s:property value="searchInfo" />" onclick="tabsSelected(this)"> <font color="#1a8911">微知</font></a>
    </h1>
	<div id="microblogdocumentview" class="fyh">
	<s:if test="bloBs.size()>0">
	   <s:iterator value="bloBs">
	   <s:set var="user" value="getIrpUserByUsername(cruserid)" />
	      <dl>
        	<dt>
        	   <s:if test="#user.userpic!=null">
			      <a href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='cruserid' /> " >
				     <img  src="<%=rootPath %>file/readfile.action?fileName=<s:property value='#user.userpic' />" alt="用户头像" width="55px" />
		          </a>						
		       </s:if>
		       <s:if test="#user.userpic==null">			
			      <a href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='cruserid' /> " >
			        <s:if test="#user.sex==2">
			            <img  src="<%=rootPath %>client/images/female.jpg" alt="用户头像" width="55px"  />
			        </s:if>
				   <s:else>
				        <img src="<%=rootPath %>client/images/male.jpg" alt="用户头像" width="55px" />
				   </s:else>
			    </a>
		      </s:if>
		    </dt>
             <dd>
              <a href="javascript:void(0)" onclick="documentinfo_see(<s:property value="docid2" />)" class="linkb14"><s:property value="doctitle" escapeHtml="false" /></a>&nbsp;.&nbsp;<s:property value="cruser" />&nbsp;.&nbsp;<s:date name="crtime" format="yyyy-MM-dd"/> 
                           <br/>
                                       标签:&nbsp;<s:iterator value="dockeywords.split(',')"  status="st" var="as">
  				 	     <a href="javascript:void:(0)" onclick="searchInfo1('<s:property value="#as"/>')" class="linkc12"><s:property value="#as"/></a>&nbsp;
  				     </s:iterator>
                <span>
                    <s:property value="doccontent" escapeHtml="false" />
                  <br/>
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