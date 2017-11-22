<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<style type="text/css">
.ellipsis {
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}

.cRed3 {
	color: #BB2737;
	font: 18px arial;
	padding-right: 8px;
}
</style>
<dt>
	<a href="javascript:void(0);" class="linkbh14">热门投票</a>
</dt>
<s:if test="hotlist!=null && hotlist.size()>0">
	<s:iterator value="hotlist" status="irptopicliststatus">
		<dd style="width: 200px;">
			<span class="cRed3">
				<s:property value="approve" />
			</span>
			的人赞成&nbsp;&nbsp;&nbsp;
			<s:property value="option" />
			<br /> 来自投票：
			<a class="linkbh14" target="_blank" href='<%=rootPath%>menu/vote_detil.action?voteid=<s:property value="voteid"/>'>
				<s:property value='title' />
			</a>
		</dd>
		<s:if test="hotlist.size()>#irptopicliststatus.count">
			<h3 style="font-size: 14px;font-weight: 700; border-bottom: 1px solid rgb(224, 224, 224);padding: 0px 0px 10px;margin-bottom: 10px"></h3>
		</s:if>
	</s:iterator>
</s:if>
<s:else>
暂时没有热门的投票...
</s:else>
