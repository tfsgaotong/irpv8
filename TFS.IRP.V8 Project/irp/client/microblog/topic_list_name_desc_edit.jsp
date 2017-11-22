<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.tfs.irp.user.entity.IrpUser,com.tfs.irp.util.LoginUtil"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
    IrpUser loginUser = LoginUtil.getLoginUser();
%>  
<div id="topic<s:property value="irpTopicObj.topicid" />">
	<dl>
	    <dt style="width: 5%;">&nbsp;</dt>
		<dt style="width: 27%">
<a title="<s:property value="irpTopicObj.topicname" />" href="javascript:void(0)" onclick="getInfoTopicPage('<s:property value="irpTopicObj.topicid" />')"><div class="linkb14"  style="height: 26px;width: 192px;white-space: nowrap;text-overflow: ellipsis;overflow: hidden;"><s:property value="irpTopicObj.topicname" /></div></a>
			
		</dt>
		<dt style="width: 10%;text-align: center;"><s:property value="irpTopicObj.topicclicknum" /></dt>
		<dt style="width: 15%;text-align: center;"><div style="height: 26px;width: 90px;white-space: nowrap;text-overflow: ellipsis;overflow: hidden;" title="<s:property value="getShowName(irpTopicObj.expandfield)" />"><s:property value="getShowName(irpTopicObj.expandfield)" /></div></dt>
		<dt style="width: 18%;text-align: center;"><s:date  name="irpTopicObj.crtime" format="yyyy-MM-dd HH:mm" /></dt>
		<%if(loginUser.isTopicManager()){ %>
		<dt><a href="javascript:void(0)" onclick="deletetopic(<s:property value="irpTopicObj.topicid" />,'<s:property value="irpTopicObj.topicname" />')">删除</a>&nbsp;&nbsp;<a href="javascript:void(0)" onclick="topicDesc(<s:property value="irpTopicObj.topicid" />)" >描述</a></dt>
		<%}%>
	</dl><br/>
	 <dl  id="topicdesc<s:property value="irpTopicObj.topicid" />" title="<s:property value="irpTopicObj.topicdesc" />" style="text-align: left;background-image: none;">
	 
	         <s:property value="irpTopicObj.topicdesc" />
	 </dl>
	 <h3 style="font-size: 14px;font-weight: 700; border-bottom: 1px solid rgb(224, 224, 224);padding: 0px 0px 10px;"></h3>
</div>