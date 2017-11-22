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
<title>忘记密码</title>
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
	var error =$("#error").val();
	if(error){
		$.messager.alert("错误信息",error);
	}
	/* 增加回车提交功能 */
	$('form input').bind('keyup', function(event) { 
		if (event.keyCode == '13') {
			$(this).submit();
		}
	});
	kaptcreate();
});

function loginSubmit(){
	var validate = $('#findUser').form('validate');
	if(validate){
		$('#findUser').submit();
	}
}

//生成验证码         
function kaptcreate(){
    $('#kaptchaImage').click(function () {  
    $(this).hide().attr('src', '<%=rootPath %>Kaptcha.jpg?' + Math.floor(Math.random()*100) ).fadeIn(); });    
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
        <div style="width: 925px;height: 34px;line-height: 34px;margin:0 auto;background: #5d8cba;text-indent: 23px;color: #fff"></div>
        <form id="findUser" action="<%=rootPath %>/random/finds.action" method="post">
            <label>登录账号
            <input type="text" id="username" name="irpUser.username" class="easyui-validatebox" validType="isExist[2,50]" required="true" missingMessage="请填写用户名称"/>
            </label><br />
            <label>验证码<input type="text" name="checkalphabet"  class="easyui-validatebox"  required="true" missingMessage="请填写验证码" maxlength="5" style="width: 186px;" />
            <div style="width: 81px;height: 37px;background: #fff;float: right;margin-left: 10px">
				<img src="<%=rootPath%>Kaptcha.jpg" id="kaptchaImage" style="height:42px;cursor:pointer;width: 100%;"/>
			</div>
            </label>
           <input type="hidden" id="error" value='<s:property value="friendlyshow" />'/>
            <label style="margin-bottom: 0">
           <input type="button" name=""  onclick="loginSubmit()" value="下一步" />
            </label>
        </form>
        			<input type="hidden" id="error" value='<s:property value="friendlyshow" />'/>
        
    </div>
</div>
<jsp:include page="/view/include/client_foot.jsp" />
</body>
</html>