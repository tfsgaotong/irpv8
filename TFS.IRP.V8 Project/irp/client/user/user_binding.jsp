<%@page import="com.tfs.irp.util.LoginUtil"%>
<%@page import="com.tfs.irp.user.entity.IrpUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>企业社会化智力资源管理系统</title>
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/oacss.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/icon.css" />
<jsp:include page="../include/client_skin.jsp" />

<style type="text/css">
body{
	behavior:url(<%=rootPath %>client/js/hover.htc);
}
.STYLE1 {
	color: #0000FF;
	font-weight: bold;
	font-size:18px;
}
.STYLE1 a:hover{
	color: #0000FF;
	font-weight: bold;
	font-size:18px;
}
.main-gr .right .combo input{
  border: 0px;
}
.tagcss{
border: 1px solid #E9E9E9;
width: 250px;
margin-top: -100px;
margin-left: 470px;
padding-left: 4px;
}  

</style>

<script type="text/javascript" src="<%=rootPath %>client/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/easyui-lang-zh_CN.js"></script>
</head>

<body>
<div class="bg01">
<!--头部菜单-->
<s:include value="../include/client_head.jsp"></s:include>
<!--头部菜单end-->
<div class="main-gr">
<!--左侧内容-->
<div class="left">
	<div class="leftmenu">
    	<h1>账号设置</h1>
        <dl>
        	<dt><em class="a"></em><a href="<%=rootPath %>user/user_set.action">个人资料</a></dt>
            	<dd><a href="<%=rootPath %>user/user_set.action">基本信息</a>
            	<a href="<%=rootPath %>user/user_edu.action">教育信息</a>
            	<a href="<%=rootPath %>user/user_career.action">职业信息</a></dd>
       	 	<dt><em class="b"></em><a href="<%=rootPath %>user/user_pic.action">修改头像</a></dt>
       	  	<dt><em class="c"></em><a href="<%=rootPath %>user/user_tag.action">个人标签</a></dt>
            <dt><em class="d"></em><a href="<%=rootPath %>user/user_privacy.action">隐私设置</a></dt>
			<dt><em class="g"></em><a href="<%=rootPath %>user/user_group.action">个人分组</a></dt>
			<dt><em class="i"></em><a href="<%=rootPath %>site/user_subject.action">个人专题</a></dt>
			<dt><em class="h"></em><a href="<%=rootPath%>site/personallchanneltoshow.action?personid=<s:property value="@com.tfs.irp.util.LoginUtil@getLoginUserId()" />">个人分类</a></dt>
        	<dt><em class="e"></em><a href="<%=rootPath %>user/user_binding.action" class="x">账号绑定</a></dt>
        </dl>
    </div>
</div>
<!--左侧内容结束-->
<!--右侧内容-->
<div class="right">
	<div class="gr"><h1>账号绑定</h1></div>
	<div>QQ互联:<s:if test="irpUser.qqtoken!=null && irpUser.qquserid!=null && !irpUser.qqtoken.isEmpty() && !irpUser.qquserid.isEmpty()">
		<a href="javascript:void(0)" onclick="qqUnbinding()">解除绑定</a>
	</s:if><s:else>
		<a href="<%=rootPath %>user/user_bindingoauth.action?type=qq">绑定QQ</a>
	</s:else></div>
	<div>新浪微知:<s:if test="irpUser.weibotoken!=null && irpUser.weibouserid!=null && !irpUser.weibotoken.isEmpty() && !irpUser.weibouserid.isEmpty()">
		<a href="javascript:void(0)" onclick="weiboUnbinding()">解除绑定</a>
	</s:if><s:else>
		<a href="<%=rootPath %>user/user_bindingoauth.action?type=weibo">绑定新浪微知</a>
	</s:else></div>
</div>
<!--右侧内容结束-->
<!--尾部信息-->
<s:include value="../include/client_foot.jsp"></s:include>
<!--尾部信息end-->
</div>
</div>
</body>
</html>
<script type="text/javascript">
<s:if test="irpUser.qqtoken!=null && irpUser.qquserid!=null && !irpUser.qqtoken.isEmpty() && !irpUser.qquserid.isEmpty()">
function qqUnbinding(){
	$.ajax({
		type:"post",
		url:"<%=rootPath %>user/user_unbinding_edit.action",
		data:{
			'type' : 'qq',
			'irpUser.qqtoken' : '',
			'irpUser.qquserid' : ''
		},
		async:false,
		cache:false,
		success:function(callback){
			if(callback==1){
				window.location.reload();
			}
		}
	});
}
</s:if>
<s:if test="irpUser.weibotoken!=null && irpUser.weibouserid!=null && !irpUser.weibotoken.isEmpty() && !irpUser.weibouserid.isEmpty()">
function weiboUnbinding(){
	$.ajax({
		type:"post",
		url:"<%=rootPath %>user/user_unbinding_edit.action",
		data:{
			'type' : 'weibo',
			'irpUser.weibotoken' : '',
			'irpUser.weibouserid' : ''
		},
		async:false,
		cache:false,
		success:function(callback){
			if(callback==1){
				window.location.reload();
			}
		}
	});
}
</s:if>
</script>