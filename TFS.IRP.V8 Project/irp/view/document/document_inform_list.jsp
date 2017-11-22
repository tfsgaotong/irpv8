<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<s:if test="docClassicls!=null &&docClassicls.size()>0">
<div class="title">专家推荐</div>
<s:iterator value="docClassicls">
<s:set var="cruser" value="getIrpUser(userid)" />
<div class="labs">
<dl>
   	<dt><a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="#cruser.userid" /></s:url>" target="_blank"><img src="<s:property value="#cruser.defaultUserPic" />"/></a></dt>
    <dd class="text">
    	<div class="user">
    		<a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="#cruser.userid" /></s:url>" target="_blank"><s:property value="#cruser.showName" /></a>
    		<aside><span><s:date name="crtime" format="yyyy-MM-dd HH:mm"/></span></aside>
    	</div>
        <p><s:property value="informcontent" /></p>
    </dd>
    <dd class="clear"></dd>
</dl>
</div>
</s:iterator>
</s:if>