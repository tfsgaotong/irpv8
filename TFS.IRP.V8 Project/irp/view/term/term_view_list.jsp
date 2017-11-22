<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<!-- 2 -->
<s:if test="termlist.size()>0">
    <div style="margin: 110px 0 0 0;">
	   <s:iterator value="termlist">
	       <div style="padding: 20px 30px 20px 10px;border-right: 1px solid #f2f2f2;float: left;width: 26.8%;cursor: pointer; " title="<s:property value="termname" />"   id="termview<s:property value="termid" />" onmouseover="choiceTerV('termview',<s:property value="termid" />)" onmouseout="outTerV('termview',<s:property value="termid" />)" onclick="linkVerContent(<s:property value="termid" />)" >
		      <div style="width: 100%;white-space: nowrap;overflow: hidden;text-overflow: ellipsis;">
		          <s:property value="termname" />
		      </div>
	       </div>
	   </s:iterator>
    </div>
    <div style="width: 100%;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
    <div style="margin:0 0 0 0;width: 100%;" class="fyh">
		<ul style="width: 100%;"> 
			<s:property value="pagehtml" escapeHtml="false" />
		</ul>
    </div>
</s:if>
<s:else>
	<br/><br/>
	<div style="width: 100%;margin: 110px 0 0 0;">暂无此词条</div>
</s:else>