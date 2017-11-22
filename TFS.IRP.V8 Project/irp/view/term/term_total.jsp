<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<dt><a href="javascript:void(0);" class="linkbh14">积分排行</a></dt> 
<s:iterator value="irpUsers">
<dd>
	<span><s:if test="sumscore<0">0</s:if><s:else><s:property value="sumscore"/></s:else></span>
	<a href="javascript:void(0)"  onclick="client_to_index_person(<s:property value='userid'/>)">
		<img src="<s:property value='userpic'/>" alt="" width="50px;" height="50px;" />
		<s:property value="username" />
	</a>
</dd>
</s:iterator> 
<dd></dd>