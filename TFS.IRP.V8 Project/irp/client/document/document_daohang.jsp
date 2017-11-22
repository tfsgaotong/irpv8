<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
  <li id="mytougao" onclick="tabs(this)" _href="<%=rootPath%>site/myalltougaodocument.action" _data="crtimesort=desc">
			<a href="javascript:void(0)">我的投稿</a>
		</li>	
<s:iterator value="irpChannels">
	<s:if test="%{parentid==0}">
	 <li id='channelid0' onclick="tabs(this)"  _href="<%=rootPath%>site/selectdoclink.action" _data="id=<s:property value='channelid'/>&crtimesort=desc"><a  href="javascript:void(0)">默认</a></li> 
	</s:if>
	<s:else>
	 <li id='channelid<s:property value='channelid'/>' onclick="tabs(this)" _href="<%=rootPath%>site/selectdoclink.action" _data="id=<s:property value='channelid'/>&crtimesort=desc" ><a href="javascript:void(0)" title="<s:property value='chnldesc'/>"><s:property value='chnldesc'/></a></li> 
	</s:else> 
</s:iterator>