<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<s:iterator value="irpUsers" status="index">
	<s:if test="#index.count==1">
<div class="area10"></div>
<dl class="first">
	<dt><a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="userid" /></s:url>" target="_blank"><img src="<s:property value="defaultUserPic" />" width="82" height="82" title="<s:property value="showName" />" /></a></dt>
	<dd>
		<h1 style="width:140px;" class="ellipsis nowrap"><a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="userid" /></s:url>" target="_blank" title="<s:property value="showName" />"><s:property value="showName" /></a></h1>
		<p>排名：<em><s:property value="#index.count"/></em></p>
		<p>用户积分：<strong><s:property value="sumscore"/></strong>&nbsp;分</p>
	</dd>
</dl>
<div class="area10"></div>
	</s:if>
	<s:elseif test="#index.count==2">
<div class="labs second"><strong><s:property value="#index.count"/></strong><a style="width:160px;" class="ellipsis nowrap" href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="userid" /></s:url>" target="_blank"><s:property value="showName" /></a><aside><s:property value="sumscore" /></aside></div>
	</s:elseif>
	<s:elseif test="#index.count==3">
<div class="labs third"><strong><s:property value="#index.count"/></strong><a style="width:160px;" class="ellipsis nowrap" href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="userid" /></s:url>" target="_blank"><s:property value="showName" /></a><aside><s:property value="sumscore" /></aside></div>
	</s:elseif>
	<s:else>
<div class="labs"><strong><s:property value="#index.count"/></strong><a style="width:160px;" class="ellipsis nowrap" href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="userid" /></s:url>" target="_blank"><s:property value="showName" /></a><aside><s:property value="sumscore" /></aside></div>
	</s:else>
</s:iterator>