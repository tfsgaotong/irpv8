<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
 
			<s:if test="irpChannels==null || irpChannels.size()==0"></s:if>
			<s:else>
			<div class="line area20"></div>
		    <ul class="setList type1" style="padding-left:20px;">
			<s:iterator value="irpChannels">
			<s:if test="%{channelid!=irpChannel.channelid}">
            	<li><a style="cursor:pointer;" target="_blank" onclick="goToDetail(<s:property value='channelid'/>)"><s:property value="%{chnlname}" /></a></li>
            </s:if>
            </s:iterator>
            </ul>				
			</s:else>
            <div class="clear"></div>
            
           	<s:if test="personalSearchs==null || personalSearchs.size()==0"></s:if>
           	<s:else>
           	<div class="line area20"></div>
			    <ul class="setList type1" style="padding-left:20px;">
				<s:iterator value="personalSearchs">
				<s:if test="%{channelid!=irpChannel.channelid}">
	            	<li><a style="cursor:pointer;" target="_blank" onclick="toSearchSubject(<s:property value='personalsearchid'/>)"><s:property value="searchname" /></a></li>
	            </s:if>
	            </s:iterator>
	            </ul>		           	
           	</s:else>

