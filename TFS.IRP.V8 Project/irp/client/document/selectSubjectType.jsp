<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>   
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=rootPath%>client/js/easyui-lang-zh_CN.js"></script>

<h1  class="zl3" id="voteitem" style=" font-weight:normal;  line-height:50px; border-bottom:1px  solid #efefef;text-align:left; font-family:微软雅黑; margin:0 0 20px 5px; color:#121212;">
 <a id="weibosubj" href="javascript:void(0)" onclick="topicInsert()"> <font class="linkbh14">微知专题</font></a>  
 <a id="knowsubj" href="javascript:void(0)" onclick="createknowSubject()"> <font class="linkbh14">知识专题</font></a>  
</h1>
<div id="votetype">
</div>

