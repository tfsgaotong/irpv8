<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.tfs.irp.user.entity.IrpUser,com.tfs.irp.util.LoginUtil"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
    IrpUser loginUser = LoginUtil.getLoginUser();
%>
<h1  class="zl3" id="topicitem" style=" font-weight:normal;  line-height:50px; border-bottom:1px  solid #efefef;text-align:left; font-family:微软雅黑; margin:0 0 20px 5px; color:#121212;">
<a id="topic_all" href="javascript:void(0)" _href="<%=rootPath%>microblog/topicmenulistall.action" _data="" class="over"
			onclick="tabtopic(this.id)"> <font class="linkbh14">全部</font></a>  
 <%if(loginUser.isTopicManager()){ %>
	<a id="topic_notaudit" href="javascript:void(0)" _href="<%=rootPath%>microblog/topicmenuaudit.action" _data=""
			onclick="tabtopic(this.id)"> <font class="linkbh14">待审核</font></a>		
 <%} %>
 <a id="topic_mine" href="javascript:void(0)" _href="<%=rootPath%>microblog/topicmenumine.action" _data="" 
			onclick="tabtopic(this.id)"> <font class="linkbh14">微知专题</font></a>  
 <a id="subject_mine" href="javascript:void(0)" _href="<%=rootPath%>microblog/subjectmenumine.action" _data="" 
			onclick="tabtopic(this.id)"> <font class="linkbh14">知识专题</font></a>		
			<div id="hideknowledge" class="linkbh14" style="text-align: right;margin-top: -40px;">
			 	按照 <select id="topicsortorderval" onchange="topicsortorderval()">
			 			<option value="topicclicknum desc" selected="selected">热度</option>
			 			<option value="crtime desc">时间</option>
			 	   </select>
			 		排序</div>
 			
 </h1>
 
 <div id="topictabmenu">
	<dl class="linkbh14" >
		<dt style="width: 5%">排名</dt>
		<%if(loginUser.isTopicManager()){ %>
		<dt style="width: 30%">专题</dt>
		<%}else{%>
		<dt style="width: 40%">专题</dt>
		<%} %>
		<dt style="width: 10%">热度</dt>
		<dt style="width: 15%">发起人</dt>
		<dt style="width: 18%">发起时间</dt>
		<%if(loginUser.isTopicManager()){ %>
		<dt><a href="javascript:void(0)" onclick="addTopicEmpty()">增加</a></dt>
		<%}%>
	</dl><br/>
	<div id="topicprepend">
	<s:if test="irptopiclist.size()>0">
	   
<s:iterator value="irptopiclist" status="irptopicliststatus">

<div id="topic<s:property value="topicid" />">
	<dl>
	    <dt style="width: 5%; <s:if test="(pageNum-1)*pageSize+#irptopicliststatus.count==1">color: rgb(235, 25, 45);</s:if><s:if test="(pageNum-1)*pageSize+#irptopicliststatus.count==2">color: rgb(255, 102, 0);</s:if><s:if test="(pageNum-1)*pageSize+#irptopicliststatus.count==3">color:  rgb(219, 219, 111);</s:if>"><s:property value="(pageNum-1)*pageSize+#irptopicliststatus.count"/></dt>
	    <%if(loginUser.isTopicManager()){ %>
		<dt style="width: 27%">
		<%}else{%>
		<dt style="width: 37%">
		<%}%>
		<a title="<s:property value="topicname" />" href="javascript:void(0)" onclick="getInfoTopicPage('<s:property value="topicid" />')"><div class="linkb14"  style="height: 26px;width: 192px;white-space: nowrap;text-overflow: ellipsis;overflow: hidden;"><s:property value="topicname" /></div></a>
		</dt>
		<dt style="width: 10%;text-align: center;"><s:property value="topicclicknum" /></dt>
		<dt style="width: 15%;text-align: center;"><div style="height: 26px;width: 90px;white-space: nowrap;text-overflow: ellipsis;overflow: hidden;" title="<s:property value="getShowName(expandfield)" />"><s:property value="getShowName(expandfield)" /></div></dt>
		<dt style="width: 18%;text-align: center;"><s:date  name="crtime" format="yyyy-MM-dd HH:mm" /></dt>
		<%if(loginUser.isTopicManager()){ %>
		<dt><a href="javascript:void(0)" onclick="deletetopic(<s:property value="topicid" />,'<s:property value="topicname" />')">删除</a>&nbsp;&nbsp;<a href="javascript:void(0)" onclick="topicDesc(<s:property value="topicid" />)" >描述</a></dt>
		<%}%>
	</dl><br/>
	<dl style="width: 580px;margin-left: 55px;text-align: left;background-image:url('');margin: 10px auto;"  id="topicdesc<s:property value="topicid" />" title="<s:property value="topicdesc" />">
	      <s:property value="topicdesc" />
	 </dl>
	 <h3 style="font-size: 14px;font-weight: 700; border-bottom: 1px solid rgb(224, 224, 224);padding: 0px 0px 10px;"></h3>
</div>
	
</s:iterator>

<ul>
	<s:property value="pageHTML" escapeHtml="false" />
</ul>
</s:if>
</div>
<s:else>
  <div id="topictalknot">没有讨论的主题</div>
</s:else>
</div>