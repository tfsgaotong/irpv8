<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<ul class="list3">
<s:if test="chnlDocLinks!=null && chnlDocLinks.size()>0">
<s:iterator value="chnlDocLinks">
	<li class="ellipsis nowrap">
		<a target="_blank" href="<s:url namespace="/document" action="document_detail"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>">
			<s:property value="doctitle" />
		</a>
	</li>
</s:iterator>
</s:if>
<s:else>
	<li>暂时没有知识...</li>
</s:else>
</ul>