<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.tfs.irp.util.LoginUtil"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<s:if test="templatecatelist.size()>0">
<s:iterator value="templatecatelist">
	<div style="width: 100%;height: 350px;overflow: scroll;">
		<s:property value="tvalue" escapeHtml="false" />
	</div>
	
	<s:if test="templatequotenums>1">
	
		<div class="main" style="width: 100%;height: 50px;">
			<div class="left" style="width: 100%;">
				<div class="fyh" style="width: 100%;">
					<ul style="width: 90%;">
					<s:property value="pagetemhtml" escapeHtml="false" />
					</ul>
				</div>
			</div>
		</div>
	</s:if>
	<div style="width: 100%;height: 70px;border-top: 1px solid;">
		<s:property value="tvaluedesc" />
	</div>
	
	<input id="temphiddenval" style="display: none;" type="text" value="<s:property value="tid" />">
</s:iterator>
</s:if>
<s:else>
此分类下暂无模版!

</s:else>

