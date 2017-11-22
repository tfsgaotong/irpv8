<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!doctype html>
<html lang="en" class="no-js">
<head>
<meta charset="utf-8">
<title>企业社会化智力资源管理系统</title>
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
<link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath %>view/css/easyui.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath %>view/css/icon.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath %>view/css/discuz.css" rel="stylesheet" type="text/css" />
<style type="text/css">
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
.index_title {margin: 10px 0;}
.index_title span{font-size:13px;}
</style>

<script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jsAddress.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/modernizr.custom.js"></script>
</head>

<body>
<jsp:include page="../include/client_head.jsp" />
<section class="mainBox">
	<nav class="publicNav">
    </nav>
</section>
<section class="mainBox">
	<article class="location"><strong>个人设置</strong></article>
	<section class="layoutLeft">
        <nav class="sets">
        	<div class="folder">
            	<p><a href="<%=rootPath %>user/user_set.action" target="_self">基本资料</a></p>
            </div>
            <div class="folder">
            	<p><a href="<%=rootPath %>user/user_privacy.action" target="_self">隐私设置</a></p>
            </div>
            <div class="folder">
            	<p><a href="<%=rootPath %>user/user_pic.action" target="_self">设置头像</a></p>
            </div>
           <div class="folder">
            	<p><a href="<%=rootPath %>user/user_group.action" target="_self">个人分组</a></p>
            </div>
            <div class="folder">
            	<p><a href="<%=rootPath %>user/user_tag.action" target="_self">个人标签</a></p>
            </div>
            <div class="folder">
            	<p class="current"><a href="<%=rootPath %>user/user_binding.action" target="_self">账号绑定</a></p>
            </div>
        </nav>
  </section>
  <section class="setUp">
        <div class="pan title"><span>账号绑定</span></div>
   		<div class="line area20"></div>
   		<div class="rightN">
	   		<em>绑定社区帐号后，你可以直接通过社区账号登录系统。</em>
	        <div class="index_title sub">
	        	<span>Q&nbsp;Q互联：</span><s:if test="irpUser.qqtoken!=null && irpUser.qquserid!=null && !irpUser.qqtoken.isEmpty() && !irpUser.qquserid.isEmpty()">
				<input type="button" value="解&nbsp;除&nbsp;绑&nbsp;定" onclick="qqUnbinding()" />
			</s:if><s:else>
				<input type="button" value="绑&nbsp;&nbsp;定&nbsp;&nbsp;Q&nbsp;&nbsp;Q" onclick="window.location.href='<%=rootPath %>user/user_bindingoauth.action?type=qq'" />
			</s:else>
			</div>
	        <div class="index_title sub"><span>新浪微知：</span><s:if test="irpUser.weibotoken!=null && irpUser.weibouserid!=null && !irpUser.weibotoken.isEmpty() && !irpUser.weibouserid.isEmpty()">
				<input type="button" value="解&nbsp;除&nbsp;绑&nbsp;定" onclick="weiboUnbinding()" />
			</s:if><s:else>
				<input type="button" value="绑&nbsp;定&nbsp;新&nbsp;浪&nbsp;微&nbsp;博" onclick="window.location.href='<%=rootPath %>user/user_bindingoauth.action?type=weibo'" />
			</s:else>
	        </div>
        </div>
  </section>
    
</section>



<jsp:include page="../include/client_foot.jsp" />
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
