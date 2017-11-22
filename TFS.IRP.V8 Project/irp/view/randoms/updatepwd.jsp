<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>修改密码</title>
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="favicon.ico"/>
<link rel="stylesheet" href="<%=rootPath%>admin/css/easyui.css" type="text/css"></link> 
<script src="<%=rootPath%>images/modernizr.custom.js"></script>
<script src="<%=rootPath%>images/jquery-1.8.0.min.js"></script>
<link href="<%=rootPath%>images/normalize.css" rel="stylesheet" type="text/css">
<link href="<%=rootPath%>css/common1.css" rel="stylesheet" type="text/css">
<style type="text/css">
.section-focus-pic ul li{left: 996px;}
.aqzx{width: 1000px;background: rgba(255,255,255,0.6);margin:50px auto 50px auto;padding-bottom: 130px}
.aqzx h2{width: 925px;height: 44px;font-size: 22px;color: #333;background: url(<%=rootPath %>view/images/safe_ico_03.jpg) left center no-repeat;text-indent: 60px;line-height: 44px;font-family: "微软雅黑";font-weight:normal;margin:0 auto 0 auto;cursor: default;padding:23px 0 27px 0;}
.aqzx form{position: relative;display: table;margin:70px auto 0 auto;}
.aqzx label{float: right;clear: both;margin-bottom: 40px}
.aqzx input{width: 278px;height: 33px;margin-left: 10px;}
.aqzx input[type="text"]{text-indent: 8px;color: #909090}
.aqzx input[type="button"]{width: 190px;height: 35px;background: #1d5e9e;text-align: center;color: #fff;border: none;margin-right: 90px}
</style>
<link href="<%=rootPath%>view/css/discuz.css" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="<%=rootPath%>admin/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>admin/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath%>admin/js/jquery.easyui.min.js"></script>
</head>
<script type="text/javascript">

$(function (){
	var username=$("#username").val();
	var disposecontent = username.replace(":","\\:");
	var eacapeInfo = encodeURI(disposecontent);
	$('#upUser').form({
		url:'<s:url namespace="/random" action="uPwd" />',
		success:function(data){
			if(data>0){
				window.location.href='<s:url namespace="/" action="login" />';
			}else{
				$.messager.alert("错误信息","密码修改失败，请核实信息!");
			}
		}
	});
$.extend($.fn.validatebox.defaults.rules, {   
    /*必须和某个字段相等*/
    equalTo: { 
        validator:function(value,param){ 
            return $(param[0]).val() == value; 
        }, 
        message:'字段不匹配'
    } 
});
});
function loginSubmit(){
		$('#upUser').submit();
}
</script>
<body style="background: url(<%=rootPath %>images/b1-01.jpg) center top ;">

<div class="header">
     <div class="w1200">
        <a href="<%=rootPath %>" target="_blank"><img src="<s:property value="@com.tfs.irp.util.SysFileUtil@getSiteBanner()" />"/></a>
       
        <ul>
	            <li><a href="<%=rootPath %>reg.action">注册</a></li>
	            <div class="clear"></div>
        </ul>
          <a href="<%=rootPath %>login.action">
        <div class="reg">登录</div>
        </a>
    </div>
    
    

</div>
<div class="clear"></div>

<div class="w1200">
    <div class="aqzx">
        <h2>账号安全中心</h2>
        <div class="clear"></div>
        <div style="width: 925px;height: 34px;line-height: 34px;margin:0 auto;background: #5d8cba;text-indent: 23px;color: #fff">设置新密码</div>
        <form id="upUser" method="post">
            <label>
            	用　户　名:<input name="userName" id="username" readonly="readonly" class="easyui-validatebox" required="true"  missingMessage="请填写登录名称" type="text" value="<s:property value="userName"/>" type="text" name="" />
            </label>
            	<input name="checkCode" id="checkCode" type="hidden" value="<s:property value="checkCode"/>" />
            <br />
            <label>
            	请输入密码:<input name="passWord" id="pwd"  class="easyui-validatebox" required="true" missingMessage="请填写密码" type="password"/>
            </label><br />
            <label>
            	请确认密码:<input name="repassWord" id="pwd2" class="easyui-validatebox" required="true" type="password" missingMessage="请确认密码"  validType="equalTo['#pwd']" invalidMessage="两次输入密码不匹配" />
          	</label>
            <label style="margin-bottom: 0">
            <a href="javascript:void(0)" onclick="loginSubmit()"><input type="button" name="" value="提交" /></a></label>
        </form>
        
        
    </div>
</div>
<jsp:include page="/view/include/client_foot.jsp" />
</body>
</html>