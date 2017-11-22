<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录</title>
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="favicon.ico"/>
<link rel="stylesheet" href="admin/css/easyui.css" type="text/css"></link> 
<script src="images/modernizr.custom.js"></script>
<script src="images/jquery-1.8.0.min.js"></script>
<link href="images/normalize.css" rel="stylesheet" type="text/css">
<link href="css/common1.css" rel="stylesheet" type="text/css">
<style type="text/css">
.section-focus-pic ul li{left: 996px;}
.bd form input[type="password"] {
    width: 290px;
    height: 42px;
    line-height: 42px;
    border: solid 1px #cacaca;
    margin-bottom: 30px;
    text-indent: 12px;
    font-size: 15px;
    color: #b4b4b4;
}
</style>
<link href="<%=rootPath%>view/css/discuz.css" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="admin/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="admin/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="admin/js/jquery.easyui.min.js"></script>

<script type="text/javascript">
$(function (){
	/* 登录 */
 
	$('#loginForm').form({
		url:'<s:url action="login_dowith" namespace="/" />',
		success:function(data){
				if(data==1){
				window.location.href='<%=rootPath%><s:property value="requesturl" default="document/index.action" />';
			}else if(data==-1){
				$.messager.alert('<s:text name="login.alert.title" />','<s:text name="login.error.apply" />');
				
			}else if(data==3){
				//企业
				window.location.href='<%=rootPath%><s:property value="requesturl" default="document/index.action" />';
			}else if(data==2){
				//个人
				window.location.href='<%=rootPath%><s:property value="requesturl" default="document/index.action" />';
			}else{
				$.messager.alert('<s:text name="login.alert.title" />','<s:text name="login.error.pwd" />');
			}
		}
	});
	
	/* 增加回车提交功能 */
	$('form input').bind('keyup', function(event) { 
		if (event.keyCode == '13') {
			$(this).submit();
		}
	});
});

function loginSubmit(){
	if(usernamevalid()&&passvalid()){
		$('#loginForm').submit();
	}
}
</script>
</head>

<body style="background: url(images/b1-01.jpg) center top ;">
	<script type="text/javascript"> 
	$(function(){
		//getLG_s(); 
	});
	function sMLogin(){
		var getlg_s = setInterval(getLG_s,2000); 
    	var divobj_01 = document.createElement("div");
    	divobj_01.id = "divobj_01";
    	document.body.appendChild(divobj_01);
		$('#divobj_01').dialog({   
		    title: '二维码登录',   
		    width: 400,   
		    height: 400,   
		    closed: false,   
		    cache: false,   
		    href: '<%=rootPath%>user/ewm.action',   
		    modal: true,
		    onClose:function(){
		    	clearInterval(getlg_s);
    	    	$("#divobj_01").dialog("destroy"); 
    	    } 
		});  
		
	}
    function getLG_s(){
		$.ajax({
			type:"post",
			url:"<%=rootPath%>user/lgusergs.action",
			cache:false,
			async:false,
			success:function(msg){ 
				if(parseInt(msg)>0){ 
					window.location.href="<%=rootPath%>document/index.action";
				}  
			}
		});
	}  
	
	function passvalid(){
		var password = $("#password").val();
		if(password==""){
			$("#mima").text("请输入密码！");
			return false;
		}else{
			$("#mima").text("");
			return true;
		}
	}
	function usernamevalid(){
		var username = $("#username").val();
		if(username==""){
			$("#zhanghao").text("请输入用户名！");
			return false;
		}else{
			$("#zhanghao").text("");
			return true;
		}
	}
	</script>
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
    <div class="bd">
        <ul>
            <li class="current">密码登录</li>
            <li><a href="javascript:void(0);" onclick="sMLogin()"  class="dl">扫码登录</a></li>
        </ul>
        <div class="clear"></div>
        <form id="loginForm" method="post">
            <input id="username" type="text" name="irpUser.username"  onblur="usernamevalid();" onfocus="usernamevalid();"  onkeyup="usernamevalid();" value="" placeholder="邮箱账号/管理员账号"/>
            <input id="password" type="password" name="irpUser.password" onblur="passvalid();" onfocus="passvalid();" onkeyup="passvalid();" value="" placeholder="密码"/>
            <input type="checkbox" /><label>5天内自动登录</label>
            <input type="button" onclick="loginSubmit()" name="" value="登录" />
        </form>
        <span style="float: left;font-size: 14px;margin-left: 46px;margin-top:20px;">正在使用https方式登录</span>
        <span style="float: right;margin-right: 46px;margin-top:18px;"><a href="<%=rootPath %>random/validateuser.action" style="font-size: 14px;color: #355b8c">忘记密码？</a></span>
        <span id="zhanghao" style="position: absolute;left: 46px;top:146px;font-size: 12px;color: #f00"></span>
        <span id="mima" style="position: absolute;left: 46px;top:220px;font-size: 12px;color: #f00"></span>
    </div>
    <div id="ShowIOS" style="position:absolute;z-index:100;">
	<div style="float: left;">ios</div><div style="width:135;height:18px;font-size:14px;font-weight:bold;text-align:left;cursor:hand;text-align: right;" onClick="closead1();"><font color="ff0000">关闭</font></div>
	<a href="#"><img width="100px" height="100px" src="<%=rootPath %>images/ewm/ios.png"></a>
	</div>
	<div id="ShowAND" style="position:absolute;z-index:100;">
	<div style="float: left;">android</div><div style="width:135;height:18px;font-size:14px;font-weight:bold;text-align:left;cursor:hand;text-align: right;" onClick="closead2();"><font color="ff0000">关闭</font></div>
	<a href="#"><img width="100px" height="100px" src="http://www.xiliwang.com.cn/images/xiliwang.com.cn.png"></a>
	</div>
    <script language="javascript">
	var bodyfrm = ( document.compatMode.toLowerCase()=="css1compat" ) ? document.documentElement : document.body;
	var ios = document.getElementById("ShowIOS").style;
	var android = document.getElementById("ShowAND").style;
	ios.top = ( bodyfrm.clientHeight -330-22 ) + "px";
	ios.left = ( bodyfrm.clientWidth -105 ) + "px";
	
	android.top = ( bodyfrm.clientHeight -200-22 ) + "px";
	android.left = ( bodyfrm.clientWidth -105 ) + "px";
	function moveR() {
	ios.top = ( bodyfrm.scrollTop + bodyfrm.clientHeight - 330-22) + "px";
	ios.left = ( bodyfrm.scrollLeft + bodyfrm.clientWidth - 105 ) + "px";
	
	android.top = ( bodyfrm.scrollTop + bodyfrm.clientHeight - 200-22) + "px";
	android.left = ( bodyfrm.scrollLeft + bodyfrm.clientWidth - 105 ) + "px";
	}
	setInterval("moveR();", 80);
	function closead1()
	{
	ios.display='none';
	}
	function closead2()
	{
	android.display='none';
	}
	</script>
</div>
<jsp:include page="/view/include/client_foot.jsp" />
</body>
</html>