<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>  
<li id="myfile" onclick="tabs(this)" _href="<%=rootPath%>site/alluserfile.action" 
 _data="personid=<s:property value='personid'/>">
		<a  href="javascript:void(0);">图片中心</a>
</li>	
<s:iterator value="irpChannels">
	<%--直接利用关注人的栏目id去查看他下面的文档 --%>
	<li id='channelid<s:property value="channelid"/>' onclick="tabs(this)" _href="<%=rootPath%>site/showpersondoclink.action" _data="id=<s:property value='channelid'/>&personid=<s:property value='personid'/>&crtimesort=desc"><a href="javascript:void(0)">
	<s:if test="parentid==0">默认</s:if><s:else><s:property value="chnldesc"/> </s:else>
	</a></li>
</s:iterator>