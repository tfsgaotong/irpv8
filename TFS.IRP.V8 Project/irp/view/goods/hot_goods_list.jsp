<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<ul class="list3">
<s:if test="listgoods!=null && listgoods.size()>0 ">
<s:iterator value="listgoods">
	<li class="ellipsis nowrap">
		<a target="_blank" href="<s:url namespace="/goods" action="goodsDetial"><s:param name="goodsid" value="goodsid" /></s:url>" title="<s:property value='goodsname'/>">
			<s:property value="goodsname" />
		</a>
	</li>
</s:iterator>
</s:if>
<s:else>
	<li>暂时没有知识...</li>
</s:else>
</ul>