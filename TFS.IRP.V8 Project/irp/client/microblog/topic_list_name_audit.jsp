<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.tfs.irp.user.entity.IrpUser,com.tfs.irp.util.LoginUtil"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
    IrpUser loginUser = LoginUtil.getLoginUser();
%>  

	<dl class="linkbh14" >
		<dt style="width: 5%">排名</dt>
		<dt style="width: 30%">专题</dt>
		<dt style="width: 10%">热度</dt>
		<dt style="width: 15%">发起人</dt>
		<dt style="width: 18%">发起时间</dt>
		<dt>操作</dt>
	</dl><br/>
	<div id="topicprepend">
	<s:if test="irptopiclist.size()>0">
	   
<s:iterator value="irptopiclist" status="irptopicliststatus">

<div id="topic<s:property value="topicid" />">
	<dl>
	    <dt style="width: 5%; <s:if test="(pageNum-1)*pageSize+#irptopicliststatus.count==1">color: rgb(235, 25, 45);</s:if><s:if test="(pageNum-1)*pageSize+#irptopicliststatus.count==2">color: rgb(255, 102, 0);</s:if><s:if test="(pageNum-1)*pageSize+#irptopicliststatus.count==3">color:  rgb(219, 219, 111);</s:if>"><s:property value="(pageNum-1)*pageSize+#irptopicliststatus.count"/></dt>
		<dt style="width: 27%">
<a title="<s:property value="topicname" />" href="javascript:void(0)" onclick="getInfoTopicPage('<s:property value="topicid" />')"><div class="linkb14"  style="height: 26px;width: 192px;white-space: nowrap;text-overflow: ellipsis;overflow: hidden;"><s:property value="topicname" /></div></a>
		</dt>
		<dt style="width: 10%;text-align: center;"><s:property value="topicclicknum" /></dt>
		<dt style="width: 15%;text-align: center;"><div style="height: 26px;width: 90px;white-space: nowrap;text-overflow: ellipsis;overflow: hidden;" title="<s:property value="getShowName(expandfield)" />"><s:property value="getShowName(expandfield)" /></div></dt>
		<dt style="width: 18%;text-align: center;"><s:date  name="crtime" format="yyyy-MM-dd HH:mm" /></dt>
		<%if(loginUser.isTopicManager()){ %>
		<dt>
		<a href="javascript:void(0)" onclick="topicPass(<s:property value="topicid" />,'<s:property value="topicname" />')">通过</a>&nbsp;&nbsp;
		<a href="javascript:void(0)" onclick="deletetopic(<s:property value="topicid" />,'<s:property value="topicname" />')">删除</a>
		</dt>
		<%}%>
	</dl><br/>
	 <dl style="width: 580px;margin-left: 55px;text-align: left;background-image:url('');margin: 10px auto;" id="topicdesc<s:property value="topicid" />" title="<s:property value="topicdesc" />">
	 
	         <s:property value="topicdesc" />
	 </dl>
	 <h3 style="font-size: 14px;font-weight: 700; border-bottom: 1px solid rgb(224, 224, 224);padding: 0px 0px 10px;"></h3>
</div>
	
</s:iterator>

<ul>
	<s:property value="pageHTML" escapeHtml="false" />
</ul>
</s:if>
</div>
<s:else>
  <div>没有讨论的主题</div>
</s:else>