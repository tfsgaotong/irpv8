<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<style>
.logsList{
	width:450px;
}
.logsList p{
	color: #333;
    font-size: 14px;
    height: 26px;
    line-height: 26px;
    overflow: hidden;
}
.logsList p a{
	color: #225491;
}
</style>
<div class="logsList">
<s:if test="docLogs!=null && docLogs.size()>0">
<s:iterator value="docLogs">
	<s:set var="readUser" value="getIrpUser(cruserid)" />
	<p class="nowrap">
		用户&nbsp;<a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="#readUser.userid" /></s:url>" target="_blank"><s:property value="#readUser.showName" /></a>&nbsp;于&nbsp;<s:date name="opertime" format="yyyy-MM-dd HH:mm" />&nbsp;<s:property value="opername" />
	</p>
</s:iterator>
	<div class="clientpage" style="text-align:right;">
		<s:property value="pageHTML" escapeHtml="false" />
	</div>
</s:if>
<s:else>
	<p>知识暂时还没有人阅读...</p>
</s:else>
</div>