<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<ul class="list3">
<s:if test="listmedal!=null && listmedal.size()>0 ">
<s:iterator value="listmedal">
	<li class="ellipsis nowrap">
		<a target="_blank" href="<s:url namespace="/medal" action="medalDetial"><s:param name="medalid" value="medalid" /></s:url>" title="<s:property value='medalname'/>">
			<s:property value="medalname" />
		</a>
	</li>
</s:iterator>
</s:if>
<s:else>
	<li>暂时没有勋章...</li>
</s:else>
</ul>