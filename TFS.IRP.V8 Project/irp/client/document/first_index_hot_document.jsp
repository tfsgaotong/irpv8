<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<style>
.ellipsis {
    table-layout: fixed;
}
.ellipsis td div{
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
</style>
<table cellpadding="0" cellspacing="0" border="0" width="482" class="ellipsis  boxcenter listTxt ">
<s:iterator value="chnlDocLinks">
<tr>
	<td width="84" height="26"><a onclick="showDoc(<s:property value='channelid'/>)" href="javascript:void(0);" style="text-decoration: none;">[<s:property value='chnldesc'/>]</a></td>
	<td width="261"><div><a href="javascript:void(0)" onclick="showdocumentinfo(<s:property value='docid'/>)" title="<s:property value='doctitle'/>"><s:property value='doctitle'/></a></div></td>
	<td width="71" align="center"><h1><s:property value="hitscount"/></h1></td>
	<td width="66" align="center"><h2><a href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='cruserid'/>" target="_blank"><s:property value="cruser"/></a></h2></td>
</tr> 
</s:iterator> 
</table>