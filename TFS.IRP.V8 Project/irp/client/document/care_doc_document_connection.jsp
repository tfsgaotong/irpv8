<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>  
<style>
.ellipsis {
    table-layout: fixed;
}
.ellipsis td{
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
</style> 
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="olbg11 ellipsis ellipsis">
	<tr>
		<td>
			<ul class="ul_dtlist black12">
			<s:if test="irpDocumentList!=null && irpDocumentList.size()>0">
				<s:iterator value="irpDocumentList">
				<li><a href="<%=rootPath %>site/showdocumentinfo.action?refrechstatus=1&docid=<s:property value="docid" />" target="_blank"><s:property value="doctitle" /></a></li>
				</s:iterator>
			</s:if><s:else>
				<li>暂时没有系统推荐的相关知识...</li>
			</s:else>
			</ul>
		</td>
	</tr>
</table>