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
var pageNumsort = 1;
var typenum =1;
$(function(){
	//加载检索信息
	tabsSelected($('.over').attr('id'));

	//热门检索词
	var str=$.ajax({
		type:'post',
		url:'<%=rootPath%>solr/findhotindexword.action',
		async:false,
   		cache: false
	}).responseText;
	$('#hotindexwords').html(str);
});

/**
 * 标签链接
 */
function searchInfo1(searchInfo){ 
	if(searchInfo.length>38){
		searchInfo = searchInfo.substring(0,37);	
	}
	var eacapeInfo = encodeURIComponent(searchInfo);
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
	/*处理tag事件 ;信息*/
	function tabsSelected(liid){
		$.each($('#searchtagresult').find('a'), function(i, n){
			if(n.id == liid){
				n.className="over";
				searchtype=$(n).attr('_searchtype');
			}else{
				this.className="";
			}
		});
		page(1);
	}  
	/*page 处理方法*/

	function page(pageNum){
		typenum = $('#searchorder option:selected').val();
		pageNumsort = pageNum;
		var liid = $('#searchtagresult').find('.over');
		var sUrl = liid.attr("_href");
		var sData = liid.attr("_searchtype");
		//检索内容
		var searchinfo = '<s:property value='searchInfo' escapeHtml="false" />';
		if(searchinfo.length>38){
			searchinfo = searchinfo.substring(0,37);	
		}
		var eacapeInfocontent = encodeURIComponent(searchinfo);
		if(sData==''){
			sData = "pageNum="+pageNum+'&searchsort='+typenum+'&searchInfo='+eacapeInfocontent;
		}else{
			sData = "searchtype="+sData+"&pageNum="+pageNum+'&searchsort='+typenum+'&searchInfo='+eacapeInfocontent;
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
	
	function hits(questionid){
		$.ajax({
			type:"post",
			url:"<%=rootPath%>question/hits.action",
			cache:false,
			async:false,
			data:{
				questionid:questionid
			}
		});
	}
	//发送排序	
	function searchorderby(){
		if($('#advSearchForm') && $('#advSearchForm').length>0){
			advPage(pageNumsort);
		}else{
			page(pageNumsort);
		}
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
	<div style="border-bottom:1px  solid #efefef;">
		<h1 class="zl3" id="searchtagresult" style="font-size:18px;font-weight:normal;line-height:50px;text-align:left;font-family:&#39;微软雅黑:&#39; margin:0 0 20px 5px; color:#121212;">
			<a id="no" href="javascript:void(0)" _href="<%=rootPath %>solr/searchdocument.action" _searchtype="5" <s:if test="searchtype==5">class="over"</s:if> onclick="tabsSelected(this.id)">
				<font class="linkbh14">企业知识</font>
			</a>	
			<a id="no_personal" href="javascript:void(0)" _href="<%=rootPath %>solr/searchdocumentpersonal.action" _searchtype="4" <s:if test="searchtype==4">class="over"</s:if> onclick="tabsSelected(this.id)">
				<font class="linkbh14">个人知识</font>
			</a>
			<a id="yes" href="javascript:void(0)" _href="<%=rootPath %>solr/searchmicroblog.action" _searchtype="2"  <s:if test="searchtype==2">class="over"</s:if> onclick="tabsSelected(this.id)">
				<font class="linkbh14">微知</font>
			</a>
			<a id="questionsearching" href="javascript:void(0)" _href="<%=rootPath %>solr/searchquestion.action" _searchtype="3" <s:if test="searchtype==3">class="over"</s:if> onclick="tabsSelected(this.id)">
				<font class="linkbh14">问答</font>
			</a>
		</h1>
		<div style="float: right;">
			按<select id="searchorder" onchange="searchorderby()">
				<option value="0" selected="selected">相关度</option>
				<option  value="1">时间</option>
			</select>排序
		</div>
	</div>		
	<div id="microblogdocumentview" class="fyh"></div>
</div>
<div class="right">
	<div class="duo">
		<%--正在热议 --%>
		<dl id="hotindexwords"></dl>
    </div>
</div>
<jsp:include page="../include/client_foot.jsp" />
</div>
<!--左侧内容结束-->
</body>
</html>