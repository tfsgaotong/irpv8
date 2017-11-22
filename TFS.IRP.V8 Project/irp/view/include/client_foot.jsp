<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<div class="area10"></div>
<footer style="width:100%;background: #fff;bottom: 0;padding-top:4px">
	<section class="mainBox">
		<%-- <span>
		<s:text name="page.foot.info" />&nbsp;|&nbsp;</span>
		
	<a href="http://www.ruisiming.com.cn/gyrsm/125_20170314024419.html " target="_blank">关于我们</a>&nbsp;<a href="http://www.ruisiming.com.cn/lxwm/127_20170314031306.html " target="_blank">商务合作</a>&nbsp;<a href="http://www.ruisiming.com.cn/ " target="_blank">官方网站</a>
 --%>	
 	<s:property value="@com.tfs.irp.util.SysConfigUtil@getSysConfigValue('PAGE_FOOT_INFO')" escapeHtml="false"/>
 
 </section>
</footer>