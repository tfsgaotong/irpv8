<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.tfs.irp.user.entity.IrpUser,com.tfs.irp.util.LoginUtil"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
    IrpUser loginUser = LoginUtil.getLoginUser();
%>  

	<dl class="linkbh14" >
		<dt style="width: 10%">序号</dt>
		<dt style="width: 40%">专题</dt>
		<dt style="width: 16%">知识数量</dt>
		<dt style="width: 18%">发起时间</dt>
	</dl><br/>
	<div id="topicprepend">
	<s:if test="channels.size()>0">
	   
<s:iterator value="channels" status="irptopicliststatus">

<div id="channel<s:property value="channelid" />">
	<dl>
	    <dt style="width: 8%; <s:if test="(pageNum-1)*pageSize+#irptopicliststatus.count==1">color: rgb(235, 25, 45);</s:if><s:if test="(pageNum-1)*pageSize+#irptopicliststatus.count==2">color: rgb(255, 102, 0);</s:if><s:if test="(pageNum-1)*pageSize+#irptopicliststatus.count==3">color:  rgb(219, 219, 111);</s:if>"><s:property value="(pageNum-1)*pageSize+#irptopicliststatus.count"/></dt>
		<dt style="width: 40%">
<a title="<s:property value="chnlname" />" href="javascript:void(0)" onclick="getKnowPage('<s:property value="channelid" />')"><div class="linkb14"  style="height: 26px;width: 192px;white-space: nowrap;text-overflow: ellipsis;overflow: hidden;"><s:property value="chnlname" /></div></a>
		</dt>
		<dt style="width: 13%;text-align: center;"><s:property value="knowledgeNum" /></dt>
		<dt style="width: 20%;text-align: center;"><s:date  name="crtime" format="yyyy-MM-dd HH:mm" /></dt>
	</dl><br/>
	 <h3 style="font-size: 14px;font-weight: 700; border-bottom: 1px solid rgb(224, 224, 224);padding: 0px 0px 10px;"></h3>
</div>
	
</s:iterator>

<ul>
	<s:property value="pageHTML" escapeHtml="false" />
</ul>
</s:if>
</div>
<s:else>
  <div>未找到知识专题</div>
</s:else>