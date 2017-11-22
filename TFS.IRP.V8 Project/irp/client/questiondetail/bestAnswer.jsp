<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<s:iterator value="irpQuestion1">
	<s:if test="bestAnswer==0">
		<div id="bestAnswer">
			<div class="tit">最佳答案</div>
			<div id="bestAnswerDiv"></div>
		</div>
	</s:if>
	<s:else>
		<div id="bestAnswer">
			<div class="tit">最佳答案</div>
			<div id="bestAnswerDiv">
				<p>
					<s:property value="htmlcontent" />
				</p>
				<h1>
					回答者：
					<s:property value="getShowPageViewNameByUserName(cruser)" />
				</h1>
			</div>
		</div>
	</s:else>
</s:iterator>