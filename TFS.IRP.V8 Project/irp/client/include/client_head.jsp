<%@page import="com.tfs.irp.util.RightUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="com.tfs.irp.solr.entity.IrpSolr"%>
<%@ page import="com.tfs.irp.util.SysConfigUtil"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
	RightUtil rightUtil = new RightUtil();
%>
<style>
	#headDaohang li{ width:42px;}
</style>
<script type="text/javascript">
//全局变量    --检索说明
var searchtitledesc ='<%=SysConfigUtil.getSysConfigValue(IrpSolr.SEARCH_EXPLAINBY_TITLE)%>';
var searchcontentdesc ='<%=SysConfigUtil.getSysConfigValue(IrpSolr.SEARCH_EXPLAINBY_CONTENT)%>';
var searchtype = <s:property value="searchtype" default="0" />;

$(function(){
	<s:if test="searchtype==null">
	$('#searchTitle').val(searchtitledesc);
	$('#searchContent').val(searchcontentdesc);
	</s:if>
	<s:elseif test="searchtype==1">
	$('#searchTitle').val(searchtitledesc);
	$('#searchContent').val('<s:property value='searchInfo' escapeHtml="false" />');
	</s:elseif>
	<s:else>
	var searchInfos = '<s:property value='searchInfo' escapeHtml="false" />';
	if(searchInfos == 'beijing'){
		$('#searchtagresult').append("<br/><div >以下为您显示<span style=\"color:red;\">“北京” “背景”</span>的搜索结果。 仍然搜索：beijing</div>");
	}else{
		$('#searchtagresult').append("");
	}
	
	
	$('#searchTitle').val(searchInfos);
	$('#searchContent').val(searchcontentdesc);
	</s:else>
  	
	//默认加载未读私信
	loadUnReadMessage();
	setInterval("loadUnReadMessage()", '<s:property value="@com.tfs.irp.util.SysConfigUtil@getSysConfigNumValue(@com.tfs.irp.messagecontent.entity.IrpMessageContent@MESSAGELOADUNREADTIME)" default="60000" />');
});

//搜索名字
function searchInfo(){
	var searchInfo,sType;
	//搜索内容
	var title = $.trim($('#searchTitle').val());
	if(title=='beijing'){
		$('#searchtagresult').append("<br/><div>以下为您显示<span style=\"color:red;\">“北京”  “背景”</span>的搜索结果。 仍然搜索：beijing</div>");
	}else{
		$('#searchtagresult').append("");
	}
	//搜索用户
	var content = $.trim($('#searchContent').val());
	//判断搜索类型
	if(title == searchtitledesc && content==searchcontentdesc){
		return false;
	}else if(content!=searchcontentdesc){
		searchInfo = content;
		sType = 1;
	}else if(title!=searchtitledesc){
		if(title==""){
			return false;
		}
		searchInfo = title;
		if(searchtype<2){
			sType = 2;
		}else{
			sType = searchtype;
		}
	}else{
		return false;
	}
	if(searchInfo.length>38){
		searchInfo = searchInfo.substring(0,37);	
	}
	
	var eacapeInfo = encodeURIComponent(searchInfo.replace(":","\\:"));
	var sSearchUrl = '<s:if test='com.tfs.irp.util.SysConfigUtil@getSysConfigValue("searchOptions")=="t"'><%=rootPath%>menu/trs_search.action</s:if><s:else><%=rootPath%>solr/searchcontentofsolr.action</s:else>';
	sSearchUrl += '?searchInfo='+eacapeInfo+'&searchtype='+sType;
	if(<s:property value="searchtype" default="0" />==0)
		window.open(sSearchUrl);
	else
		window.location.href=sSearchUrl;
}

//加载未读私信
function loadUnReadMessage(){
	$.ajax({
		type:"post",
		cache:false,
		async:false,
		url:"<%=rootPath%>microblog/loadUnReadMessage.action",
		success:function(readMsg){
			if(readMsg!=0){
				//有未读私信
				$('#messageReadStatus').attr("title","您有"+readMsg+"条新私信");
				$('#messageReadStatus').html("<img src='<%=rootPath%>client/images/unread_message.gif' style='margin-top: 4px; margin-left: 5px;' />"); 
		 	}else{
				//没有未读私信
				$('#messageReadStatus').attr("title","查看私信");
				$('#messageReadStatus').html("<img src='<%=rootPath%>client/images/message.png'  style='margin-top: 4px; margin-left: 5px;' />");
		 	}
		}
	});
}

//搜知识库/微知
function searchTitleClick(){
	$('#searchTitle').val("");
	$('#searchContent').val(searchcontentdesc);
}
//搜名字
function searchContentClick(){
   $('#searchContent').val("");
   $('#searchTitle').val(searchtitledesc);
}

//选中
function selected(lidom){
	var jqNavigation = $('#headDaohang').find('a');
	if(jqNavigation && jqNavigation.length>0){
		$.each(jqNavigation, function(){
			if(this.id == lidom){
				this.className="hover";
			}else{
				this.className="";
			}
		});
	}
}
</script>
<div class="topcon">
	<div class="huanfu">
		<dl>
			<dt>
				<a href="javascript:void(0)" target="_self"><span class="b11"></span>换肤</a>
				<ul>
					<li><a href="javascript:void(0)" class="huang"
						onClick="changecss('<%=rootPath%>client/css/oapf-orange.css')">橘黄</a>
					</li>
					<li><a href="javascript:void(0)" class="lv"
						onClick="changecss('<%=rootPath%>client/css/oapf-green.css')">新绿</a>
					</li>
					<li><a href="javascript:void(0)" class="lan"
						onClick="changecss('<%=rootPath%>client/css/oapf-blue.css')">水蓝</a>
					</li>
					<li><a href="javascript:void(0)" class="hei"
						onClick="changecss('<%=rootPath%>client/css/oapf-black.css')">雅黑</a>
					</li>
				</ul>
			</dt>
		</dl>
	</div>
	<div class="xinxi">
		<a href="javascript:void(0)" id="mb1" class="easyui-menubutton" menu="#mm1">账号</a>
	</div>
	<div id="mm1" style="width:80px;display: none;">
		<div>
			<a href="<%=rootPath%>user/user_set.action">账号设置</a>
		</div>     
		<%if(rightUtil.isToManagement()){ %>
		<div>
			<s:a action="index" namespace="/admin">后台管理</s:a>
		</div>
		<%} %>
		<div>
			<s:a action="logout" namespace="/">退出</s:a>
		</div>
	</div>
	<div class="xinxi" style="padding-top: 0px;">
		<div style="height: 20px; line-height: 20px;">
			<a id="messageReadStatus" href="<s:url action="linkmessageview" namespace="/microblog" />"></a>
		</div>
		<div><a href="javascript:void(0)" id="mb2" class="easyui-menubutton" menu="#mm2">消息</a></div>
	</div>
	<div id="mm2" style="width:80px;display: none;">
		<div>
			<s:a action="linkcommentview" namespace="/microblog">查看评论</s:a>
		</div>
		<div>
			<s:url var="focusLink" action="microblogFocusListById" namespace="/microblog">
				<s:param name="eachuserid" value="0" />
				<s:param name="focususermenu" value="1" />
				<s:param name="pageNum" value="1" />
			</s:url>
			<s:a href="%{focusLink}">关注/粉丝</s:a>
		</div>
		<div>
			<s:a action="linkmessageview" namespace="/microblog">查看私信</s:a>
		</div>
		<div>
			<s:a action="linkatmeview" namespace="/microblog">查看@我</s:a>
		</div>
	</div>
	<s:set var="loginUser" value="@com.tfs.irp.util.LoginUtil@getLoginUser()" />
	<div class="xinxi" style="width: 70px;overflow: hidden;text-overflow: ellipsis;height: 20px;white-space: nowrap;">
		<a href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value="#loginUser.userid" />" title="<s:property value="#loginUser.getShowName()" />"><s:property value="#loginUser.getShowName()" /></a>
	</div>
	<div class="sousuo">
		<a href="javascript:void(0)" onclick="searchInfo()"></a>
		<input id="searchTitle" type="text" onclick="searchTitleClick()" />
		<input id="searchContent" type="text" onclick="searchContentClick()" />
	</div>
	<ul id="headDaohang">
	<s:if test="#loginUser.privacy==2">
		<li><a href="<s:url action="showallpublicdoc" namespace="/site" />" ><span class="b1"></span>首页</a></li>
		<li><a href="<s:url action="index" namespace="/client" />" id="person"><span class="b9"></span>个人</a>
	</s:if>
	<s:else>
		<li><a id="person" href="<s:url action="index" namespace="/client" />" ><span class="b1"></span>首页</a></li>
		<li><a href="<s:url action="showallpublicdoc" namespace="/site" />" id="knowledgebank"><span class="b9"></span>知识库</a>
	</s:else>
		<li><a href="<s:url action="questionIndex" namespace="/question" />" id="questions"><span class="b8"></span>问答</a></li>
		<li><a href="<s:url action="queryExpert" namespace="/expert" />" id="expert"><span class="b10"></span>专家</a></li>
        <li><a href="<s:url action="projectIndex" namespace="/project" />" id="projectli"><span class="b2"></span>圈子</a></li>
        <li><a href="<s:url action="forum" namespace="/project" />" id="forum"><span class="b2"></span>论坛</a></li>        
		<li><a href="<s:url action="find_vote" namespace="/menu"><s:param name="ismyorall" value="1" /></s:url>" id="votepage"><span class="b3" ></span>投票</a></li>
		<li><a href="<s:url action="jump_tophone" namespace="/menu" />" id="phonepage"><span class="b7" ></span>通讯录</a></li>
		<li><a href="<%=rootPath%>menu/find_app.action" id="appa"><span class="b11"></span>应用</a> </li>
		<li><a href="<s:url action="termlink" namespace="/term"></s:url>" id="wordterm"><span class="b6" ></span>词条</a></li>
		
		<li><a href="<s:url action="exammenu" namespace="/exam"><s:param name="pagenumpaper" value="1" /></s:url>" id="sysexam"><span class="b6" ></span>考试</a></li>
	</ul>
	<div class="logo" style="background: none;"><img src="<s:property value="@com.tfs.irp.util.SysFileUtil@getSiteLogo()" />" width="180" height="56" /></div>
</div>
<div id="specialonbeijing"></div>