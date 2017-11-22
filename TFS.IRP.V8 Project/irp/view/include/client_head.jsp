<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@page import="com.tfs.irp.managementoper.entity.IrpManagementOper"%>
<%@page import="java.util.List"%>
<%@page import="com.tfs.irp.util.RightUtil"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	String sType = request.getParameter("type");
	RightUtil rightUtil = new RightUtil();
	List<IrpManagementOper> managementOpers = rightUtil.findHeadManagementOper();
	if((sType == null || "".equals(sType)) && managementOpers!=null && managementOpers.size()>0){
		sType = managementOpers.get(0).getOpername();
	}
%>
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/jquery.autocomplete.css" />
<link href="<%=rootPath%>view/css/discuz.css" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="<%=rootPath%>client/js/jquery.autocomplete.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/lhgdialog.min.js"></script>
<script type="text/javascript">

//判断检索类型
function searchContent(_radioname){	
	$("input[name='"+_radioname+"']:checked").each(function(){
		var searchcontent = $("div[class='rightBox searchSec']").find("input[type='text']").val();
		if($.trim(searchcontent)==""){
			return false;
		}
		if(searchcontent.length>38){
			searchcontent = searchcontent.toString().substring(0,37);
		}
		var value=this.id;
		if(this.id=="rad_doc"){
			value=$("#"+this.id).attr("searchtype");
		}
		return searchVal(searchcontent,value);
	});
}

//检索
function searchVal(_searchWord, _searchType,_paramMap){
var searchsort;
	if(_paramMap==undefined || _paramMap==null || _paramMap==""){
		_paramMap ="ALL";
		searchsort=$("#sort").val();
	} else if(_paramMap=="1"){
		searchsort=1;
		_paramMap=$("#time").val();
		if(_paramMap==undefined || _paramMap==null || _paramMap==""){
		_paramMap ="ALL";
		}
	}else if(_paramMap=="2"){
		searchsort="";
		_paramMap=$("#time").val();
		if(_paramMap==undefined || _paramMap==null || _paramMap==""){
		_paramMap ="ALL";
		}
	}else{
		searchsort=$("#sort").val();
	}
	var searchcontent = window.encodeURI(_searchWord);
	if(_searchType=="rad_weibo"){
		//搜索微知  two microblog
		if(searchsort==undefined || searchsort==null || searchsort==""){
		window.location.href="<%=rootPath%>solr/searchcontentofsolr.action?searchInfo="+searchcontent+"&searchtype=2&paramMap="+_paramMap;
		}else{
		window.location.href="<%=rootPath%>solr/searchcontentofsolr.action?searchInfo="+searchcontent+"&searchtype=2&paramMap="+_paramMap+"&searchsort="+searchsort;
		}
	}else if(_searchType=="rad_doc"||_searchType=="5"){ 
		//搜素知识    默认企业知识     four personal five enterprise
		if(searchsort==undefined || searchsort==null || searchsort==""){
		window.location.href="<%=rootPath%>solr/searchcontentofsolr.action?searchInfo="+searchcontent+"&searchtype=5&paramMap="+_paramMap;
		}else{
		window.location.href="<%=rootPath%>solr/searchcontentofsolr.action?searchInfo="+searchcontent+"&searchtype=5&paramMap="+_paramMap+"&searchsort="+searchsort;
		}
	}else if(_searchType=="rad_user"){
		//搜索人    默认企业知识     four personal five enterprise
		if(searchsort==undefined || searchsort==null || searchsort==""){
		window.location.href="<%=rootPath%>solr/searchcontentofsolr.action?searchInfo="+searchcontent+"&searchtype=1&paramMap="+_paramMap;
		}else{
		window.location.href="<%=rootPath%>solr/searchcontentofsolr.action?searchInfo="+searchcontent+"&searchtype=1&paramMap="+_paramMap+"&searchsort="+searchsort;
		}
	}else if(_searchType=="rad_doc_user"||_searchType=="52"){
		//搜索作者    默认企业知识     four personal five enterprise
		if(searchsort==undefined || searchsort==null || searchsort==""){
		window.location.href="<%=rootPath%>solr/searchcontentofsolr.action?searchInfo="+searchcontent+"&searchtype=52&paramMap="+_paramMap;
		}else{
		window.location.href="<%=rootPath%>solr/searchcontentofsolr.action?searchInfo="+searchcontent+"&searchtype=52&paramMap="+_paramMap+"&searchsort="+searchsort;
		}
	}else if(_searchType=="rad_doc_keyword"||_searchType=="51"){
		//搜索标签    默认企业知识     four personal five enterprise
		if(searchsort==undefined || searchsort==null || searchsort==""){
			window.location.href="<%=rootPath%>solr/searchcontentofsolr.action?searchInfo="+searchcontent+"&searchtype=51&paramMap="+_paramMap;
		
		}else{
		window.location.href="<%=rootPath%>solr/searchcontentofsolr.action?searchInfo="+searchcontent+"&searchtype=51&paramMap="+_paramMap+"&searchsort="+searchsort;
		}
	}else if(_searchType=="rad_que"||_searchType=="6"){
		//搜索问答     four personal five enterprise
		if(searchsort==undefined || searchsort==null || searchsort==""){
			window.location.href="<%=rootPath%>solr/searchcontentofsolr.action?searchInfo="+searchcontent+"&searchtype=6&paramMap="+_paramMap;
		}else{
			window.location.href="<%=rootPath%>solr/searchcontentofsolr.action?searchInfo="+searchcontent+"&searchtype=6&paramMap="+_paramMap+"&searchsort="+searchsort;
		}
	}else{
		return false;
	}
}



//加载未读私信
function loadUnReadMessage(){
	$.ajax({
		type:"get",
		cache:false,
		async:true,
		url:"<%=rootPath%>microblog/loadUnReadMessage.action",
		success:function(readMsg){
			if(readMsg>0){
				$('#messageTips').show();
		 	}else{
		 		$('#messageTips').hide();
		 	}
		}
	});
}
//加载未签收审核信息
function loadNotSignFlow(){
	$.ajax({
		type:"get",
		cache:false,
		async:true,
		url:"<%=rootPath%>flow/notsign_flow.action",
		success:function(readMsg){
			if(readMsg>0){
				$('#manageTips').show();
				$('#manageLink').attr('href','<%=rootPath%>user/user_manage.action?showway=flowP');
		 	}else{
		 		$('#manageTips').hide();
		 		$('#manageLink').attr('href','<%=rootPath%>user/user_manage.action');
		 	}
		}
	});
}

function loadAllUnread(){
	loadNotSignFlow();
	loadUnReadMessage();
	$("input[name='search_type']:checked").each(function(){
			var value=this.id;
			value=$("#"+this.id).attr("searchtype");
		initText(value);
	});
	
}

$(function(){
 var address=document.referrer;
 if(address.indexOf("login.action")!=-1){
 	mustfocus();
 }
	loadAllUnread();
	var m = setInterval('loadAllUnread()', <s:property value="@com.tfs.irp.util.SysConfigUtil@getSysConfigNumValue(@com.tfs.irp.messagecontent.entity.IrpMessageContent@MESSAGELOADUNREADTIME)" default="60000" />);
});

/*
推荐关注
**/

function mustfocus(){
	$.ajax({
			url:'<%=rootPath%>microblog/mustfocus.action',
			type:'post',
			cache:false,
			async:false,
		    success:function(result){
		    	if(result==0){
		    		var loadPage=$.ajax({
						url: '<%=rootPath%>microblog/tofocus.action', 
						type:"post", 
					    async: false,
					    cache: false
					}).responseText; 
				 $.dialog({
					 title:'推荐关注',
						max:false,
						min:false,
						lock:true,
						resize: false,
						width:400,
						content:loadPage,
						okVal:'推荐关注',
						cancelVal:"确定",
						cancel:function(){
							  
						}
				 });
		    	
		    	}
		    }
	});
	
	}
//加为好友
	function okMustForcus(_userid){
	
		var guanzhus = "#guanzhusys"+_userid;
		
		if($(guanzhus).attr("title")=="加为好友"){
			if(_userid!=""){
			$.post("<%=rootPath%>microblog/okMicroblogFocus.action",{userid:_userid},function(msg){
				if(msg==1){
					$(guanzhus).css({
						"background-image":"url('<%=rootPath%>view/phone/images/concern_blue.png')"
					});
					$(guanzhus).attr("title","移除好友");
				}
				
			});
			}
		}else{
			if(_userid!=""){
			
					$.post("<%=rootPath%>microblog/cancelMicroblogFocus.action",{userid:_userid},function(msg){
						if(msg==1){	
							$(guanzhus).css({
							"background-image":"url('<%=rootPath%>view/phone/images/concern_gray.png')"
						});
							$(guanzhus).attr("title","加为好友");
						}
					});
					
			}
		}
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
 function initText(_type){
 	$('#serachText').AutoComplete({
			width: 490,
			maxItems: 10,
			data: '<%=rootPath%>solr/spellsearchbycore.action?searchtype='+_type,
			ajaxType: 'POST',
			afterSelectedHandler: function(data){
				$("#serachText").val(data.value);
				searchContent('search_type');
	        },
			onerror: function(msg){console.info(msg);}
		});
}

</script>
<s:set var="loginUser" value="@com.tfs.irp.util.LoginUtil@getLoginUser()" />
<s:bean name="com.tfs.irp.util.RightUtil" var="rightUtil"></s:bean>
<header class="mainHeader">
    <nav class="headerNav">
        <section class="mainBox">
        	<ul class="labels">
        	 
 				<s:iterator var="mo" value="#rightUtil.findHeadManagementOper()">
					<li style="padding : 0 8px;">
						<a href="<%=rootPath %><s:property value="#mo.operuris" />"
						><s:property value="#mo.operdesc" />
						</a>
					</li>
				</s:iterator>
        	</ul>
      		<section class="userControlBar">
      			<div class="userControlBar">
				<label style="margin: 0 3px;"><a id="manageLink" href="<%=rootPath%>user/user_manage.action">管理中心</a><strong id="manageTips"></strong></label>
				<span>|</span>
      			<label style="margin: 0 3px;"><a href="<%=rootPath%>microblog/linkmessageview.action">消息中心</a><strong id="messageTips"></strong></label>
      			<span>|</span>
      			<label style="margin: 0 3px;"><a href="<%=rootPath%>user/user_set.action">个人设置</a></label>
      			<span>|</span>
      			<s:set var="loginUser" value="@com.tfs.irp.util.LoginUtil@getLoginUser()" />
      			<label style="margin: 0 3px;"><em><a href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='#loginUser.userid'/>" title="<s:property value="#loginUser.getShowName()" />"><s:property value="#loginUser.getShowName()" /></a></em></label>
      			<s:if test="#rightUtil.isToManagement()">
      			<span>|</span>
      			<label style="margin: 0 3px;"><s:a action="index" namespace="/admin">后台管理</s:a></label>
      			</s:if>
      			<span>|</span>
      			<label style="margin: 0 3px;"><s:a action="logout" namespace="/">退出</s:a></label>
      			</div>
      		</section>
		</section>
    </nav>
</header>
<!--[if IE 7]> <div style="height:35px;"></div> <![endif]-->
<section class="mainBox">
	<div class="leftBox logoSec">
		<a href="#" target="_blank"><img src="<s:property value="@com.tfs.irp.util.SysFileUtil@getSiteLogo()" />"/></a>
	<%-- <a href="#" target="_blank"><img src="<%=rootPath%>images/logo.png"/></a> --%>
	</div>
    <div class="rightBox searchSec">
    	<div>
			<input type="text" id="serachText" placeholder="请输入关键词" class="getText" />
			<input type="submit" value="GO" onclick="searchContent('search_type')" class="sub" />
		</div>
		<div class="radios" style="margin-top: 37px;">
			<input type="radio" onclick="initText(5)" id="rad_doc" searchtype="5" name="search_type" checked="checked" /><label for="rad_doc"><span>知识</span></label>
			<input type="radio" onclick="initText(2)" id="rad_weibo" name="search_type" /><label for="rad_weibo"><span>微知</span></label>
			<input type="radio" onclick="initText(6)" id="rad_que" name="search_type" /><label for="rad_que"><span>问答</span></label>
			<%-- <input type="radio" id="rad_user" name="search_type" /><label for="rad_user"><span>用户</span></label> --%>
		</div>
    </div>
    <div class="clear"></div>
</section>