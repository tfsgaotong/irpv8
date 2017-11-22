<%@page import="com.tfs.irp.util.LoginUtil"%>
<%@page import="com.tfs.irp.user.entity.IrpUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
	IrpUser loginUser = LoginUtil.getLoginUser();
%> 
<script>
$(function(){
	focusTabDocument('documentid');
});
</script>
  <h1 class="zl3" id="DocumentOrJuBaoP" style="font-size:18px; font-weight:normal;  line-height:50px; border-bottom:1px  solid #efefef;text-align:left; font-family:微软雅黑; margin:0 0 20px 5px; color:#121212;">
	<a  href="javascript:void(0);" id="documentid" class="over"
	onclick="focusTabDocument(this.id)" _href="<%=rootPath%>flow/findFlowInfo.action" _data="sitetype=0" style="font-size: 14px;" >
	<font class="linkbh14">知识</font></a>
	<%if(loginUser.isDocumentManager()){ %>
		<a href="javascript:void(0);" id="jubaoid" 
		onclick="focusTabDocument(this.id)" _href="<%=rootPath%>site/alljubaodocument.action"_data="sitetype=0" style="font-size: 14px;">
		<font class="linkbh14">举报</font></a>
		<a href="javascript:void(0);" id="feifaid" 
		onclick="focusTabDocument(this.id)"  _href="<%=rootPath%>site/allfeifadocument.action"  _data="sitetype=0" style="font-size: 14px;">
		<font class="linkbh14">非法知识</font></a>
		<span style="float: right;padding-right: 10px;padding-top: 15px;">
				<select name="sitetype" id="siteTypeselect" onchange="focusTabDocument('')">
					<option value="0">全部</option>
					<option value='1' <s:if test="'1'==sitetype"></s:if>>企业</option>
					<option value='2' <s:if test="'2'==sitetype"></s:if>>个人</option>
				</select>
				<select name="informkey" id="informkeyselect" onchange="focusTabDocument('')">
					<option value="0">所有分类</option>
					<s:iterator value="irpInformTypelist">
						<option value="<s:property value='informkey' />"><s:property value="informvalue"/></option>
					</s:iterator>
				</select>
		</span>
	<%}%>	
	</h1> 
	
	<div>
	</div>
<div id="daidispose"></div>