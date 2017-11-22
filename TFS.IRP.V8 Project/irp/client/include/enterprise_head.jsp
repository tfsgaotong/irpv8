<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()	+ request.getContextPath() + "/";
%>
<script type="text/javascript" src="<%=rootPath%>client/js/jquery.autocomplete.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/jquery.autocomplete.css" />
<style>
<!--
.ui-widget{
	font-family: "微软雅黑","黑体";
    font-size: 14px;
}
-->
</style>
<script type="text/javascript"> 
	function showallpublicdoc(){
		window.open('<s:url action="showallpublicdoc" namespace="/site" />','_parent');
	}
	$(function(){
	
		
		$('#serachText').AutoComplete({
			width: 223,
			maxItems: 10,
			data: '<%=rootPath%>solr/spellsearch.action',
			ajaxType: 'POST',
			afterSelectedHandler: function(data){
				searchInfo1(data.value);
	        },
			onerror: function(msg){console.info(msg);}
		});
	});
	//搜索名字
	function searchInfo1(searchInfo){  
	 		searchtype = 5;
			if(searchInfo.length>38){
				searchInfo = searchInfo.substring(0,37);	
			}
	 		var eacapeInfo = encodeURI(searchInfo);
	 	    window.open('<%=rootPath%>solr/searchcontentofsolr.action?searchInfo='+eacapeInfo+'&searchtype='+searchtype); 
	}
	//搜索名字
	 function searchInfobutton(){  
		 var se=$('#serachText').val();
		 if(se == '' || se == null || se == undefined){
			 $.dialog.tips("未发现检索内容",1,"alert.gif");
			 return false;
		 }
		 searchInfo1(se);
	  }
</script>
<div class="zj_wBox1">
	<dl class="zj_hBox11">
    	<dt class="zj_fl1 zj_logo1">
        	<img src="<s:property value="@com.tfs.irp.util.SysFileUtil@getSiteLogo()" />" width="200" height="56" />
        </dt>
        <dt class="zj_fl1 zj_nav1">
	<s:if test="loginuserprivacy==1">
        <a href="<s:url action="index" namespace="/client" />" >首页</a>
        <a href="javascript:void(0);" onclick="showallpublicdoc()">知识库</a>
	</s:if>
	<s:else>
        <a href="javascript:void(0);" onclick="showallpublicdoc()">首页</a>
        <a href="<s:url action="index" namespace="/client" />">个人</a>
	</s:else>
		<a href="<s:url action="questionIndex" namespace="/question" />">问答</a>
		<a href="<s:url action="queryExpert" namespace="/expert" />">专家</a>
		<a href="<s:url action="projectIndex" namespace="/project" />" >圈子</a>
       	<a href="<s:url action="find_vote" namespace="/menu"><s:param name="ismyorall" value="1" /></s:url>">投票</a>
       	<a href="<s:url action="jump_tophone" namespace="/menu" />" id="phonepage"><span class="b7" ></span>通讯录</a>
        </dt>
        <dd class="zj_fr zj_sec">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
					
					
					<input type="text" id="serachText" style="background-color:#FFF"/>
					
					</td>
					<td class="search"><input id="cahenisdsages" type="submit" onclick="searchInfobutton()" value="搜索" style="width:70px; cursor:pointer"/></td>
				</tr>
			</table>
			<span id="someElem"></span>
 		</dd>
 		<dd class="zj_cl1"></dd>
	</dl>
</div>