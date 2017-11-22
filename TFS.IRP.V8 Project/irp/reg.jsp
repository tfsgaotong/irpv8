<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册</title>
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
.section-focus-pic ul li{left: 996px;}
.aqzx{width: 1000px;background: rgba(255,255,255,0.6);margin:50px auto 50px auto;padding-bottom: 40px}
.aqzx h3{width: 925px;height: 90px;font-size: 30px;color: #333;text-align: center;line-height: 90px;font-family: "苹方";font-weight:normal;margin:0 auto 0 auto;cursor: default;}
.aqzx h3 span{float: right;position: absolute;font-size: 12px;margin-left: 100px}
.aqzx h3 span a{color: #1d5e9e}
.aqzx form{position: relative;display: table;margin:0 auto 0 auto;}
.aqzx label{float: right;clear: both;margin-bottom: 18px}
.aqzx input[type="text"]{width: 318px;height: 38px;margin-left: 10px;border: thin solid #aeaeae;}
.aqzx input[type="text"]{text-indent: 8px;color: #909090;border: thin solid #aeaeae;}

.aqzx input[type="password"]{width: 318px;height: 38px;margin-left: 10px;border: thin solid #aeaeae;}
.aqzx input[type="password"]{text-indent: 8px;color: #909090;border: thin solid #aeaeae;}
.aqzx .sm{font-size: 12px;line-height: 60px;float: left;margin-left: 10px;}
.aqzx .sm a{color: #1d5e9e}
.aqzx .sm a:hover{text-decoration: none}
</style>
<script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" data-appid="101042565" data-redirecturi="http://irp.ruisiming.com.cn/irpdemo" charset="utf-8"></script>
<script type="text/javascript" src="<%=rootPath%>admin/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>admin/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>admin/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript">
$(function (){
	//qq登录
	if(QC.Login.check()){
		//从页面收集OpenAPI必要的参数。get_user_info不需要输入参数，因此paras中没有参数
	var paras = {};
	
	//用JS SDK调用OpenAPI
	QC.api("get_user_info", paras)
		//指定接口访问成功的接收函数，s为成功返回Response对象
		.success(function(s){
			//成功回调，通过s.data获取OpenAPI的返回数据
			$('#regForm').find('[name="irpUser.username"]').val(s.data.nickname+new Date().getTime());
			$('#regForm').find('[name="irpUser.truename"]').val(s.data.nickname);
		})
		//指定接口访问失败的接收函数，f为失败返回Response对象
		.error(function(f){
			//失败回调
			alert("获取用户信息失败！");
		})
		//指定接口完成请求后的接收函数，c为完成请求返回Response对象
		.complete(function(c){
			
		});
	}

	/* 登录 */
	$('#regForm').form({
		url : '<s:url action="reg_dowith" namespace="/" />',
		success:function(data){
			if(data==1){
				$.dialog.tips('注册成功！',1.5,"32X32/succ.png",function(){
					window.location.href='<s:url action="login" namespace="/" />';
				});
			}else if(data==2){
				$.dialog.tips('注册成功,等待审核中！',1.5,"32X32/succ.png",function(){
					window.location.href='<s:url action="login" namespace="/" />';
				});
			}else if(data==3){
				$.messager.alert('<s:text name="reg.alert.title" />','<s:text name="reg.error.registered" />','info',function(){
					$("[name='irpUser.username']").select();
				});
			}else if(data==4){
				$.messager.alert('<s:text name="reg.alert.title" />','验证码输入有误','info',function(){
				});
			}else{
				$.messager.alert('<s:text name="reg.alert.title" />','<s:text name="reg.error.failed" />');
			}
		}
	});
	
	/* 扩展验证  */
	$.extend($.fn.validatebox.defaults.rules, {   
	    minLength: {/* 定义最小长度 */
	        validator: function(value, param){   
	            return value.length >= param[0];   
	        },
	        message: '<s:text name="reg.validator.minimum" />'  
	    },
	    eqPassword : {/* 扩展验证两次密码 */
			validator : function(value, param) {
				return value == $(param[0]).val();
			},
			message : '<s:text name="reg.validator.repassword" />'
		},
		maxLength : {
			validator: function(value, param){   
	            return value.length < param[0];   
	        },
	        message: '输入内容长度不能超过{0}'  
		},
		isExist: {/* 定义最小长度 */
	        validator: function(value, param){   
	            if(value.length < param[0] || value.length > param[1]){
	            	$.fn.validatebox.defaults.rules.isExist.message = $.fn.validatebox.defaults.rules.length.message;
					return false;
	            }else{
	            	var result = $.ajax({
	            		url:'<s:url action="check_username" namespace="/user" />',
	            		type: "POST",
	            		data: {
	            			userId: 0,
	            			userName: value
	            		},
	            		dataType : "json",
	            		async: false,
	            		cache : false
	            	}).responseText;
	            	if(result=='false'){
	            		$.fn.validatebox.defaults.rules.isExist.message = '输入的用户名已存在';
						return false;
	            	}else{
	            		return true;
	            	}
	            }
	        },
	        message: ''  
	    },
	    extEmail: {
			validator: function(value, param){
				if(!/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i.test(value)){
					$.fn.validatebox.defaults.rules.extEmail.message = $.fn.validatebox.defaults.rules.email.message;
					return false;
				}else if(value.length > param[0]){
					$.fn.validatebox.defaults.rules.extEmail.message = '输入内容长度不能超过{0}';
					return false;
				}else {
					return true;
				}
	        },
	        message: ''  
		},
		 extPhone: {
			validator: function(value, param){
				if(!/^((13[0-9])|(15[^4,\D])|(18[0,5-9]))\d{8}$/.test(value)){
					$.fn.validatebox.defaults.rules.extPhone.message = '请输入正确的手机号';
					return false;
				}else if(value.length > param[0]){
					$.fn.validatebox.defaults.rules.extPhone.message = '输入内容长度不能超过{0}';
					return false;
				}else {
					return true;
				}
	        },
	        message: ''  
		}
		
	}); 
});

function regSubmit(){
	$('#regForm').submit();
}
</script>
</head>

<body style="background: url(images/b1-01.jpg) center top ;">
	<script type="text/javascript"> 
	$(function(){  //生成验证码         
	    $('#kaptchaImage').click(function () {  
	    $(this).hide().attr('src', 'Kaptcha.jpg?' + Math.floor(Math.random()*100) ).fadeIn(); });      
	});
	
	function phonenumbervalid(){
		var phonenumber = $("#phonenumber").val();
		if(phonenumber==""){
			$.messager.alert('提示','<s:text name="请输入手机号！" />');
			return false;
		} if(!(/^((13[0-9])|(15[^4,\D])|(18[0,5-9]))\d{8}$/.test(phonenumber))){
			$.messager.alert('提示','<s:text name="请输入正确的手机号！" />');
			return false;
		}
		return true;
	}
	function sendSmss(){
				if(phonenumbervalid()){
				$.ajax({
					url : '<%=rootPath%>sendSmsForLogin.action',
					data : {
						'phonenumber' : $("#phonenumber").val(),
					},
					type:'post',
					cache:false,
					async:false,
					success:function(data){
						if(data == 1){
							$.messager.alert('提示','<s:text name="验证码已发送成功！" />');
						}
					}
				});
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
      <div class="aqzx">
        <h3>欢迎加入IRP<span>已加入IRP账号<a href="<%=rootPath %>login.action">快速登录></a></span></h3>
       
        <form id="regForm" method="post">
	    	<s:if test="irpUser.qqtoken!=null && irpUser.qquserid!=null && !irpUser.qqtoken.isEmpty() && !irpUser.qquserid.isEmpty()">
	    	<s:hidden name="irpUser.qqtoken" />
	    	<s:hidden name="irpUser.qquserid" />
	    	<s:hidden name="irpUser.nickname" />
	    	<s:hidden name="irpUser.sex" />
	    	</s:if>
	    	<s:if test="irpUser.weibotoken!=null && irpUser.weibouserid!=null && !irpUser.weibotoken.isEmpty() && !irpUser.weibouserid.isEmpty()">
	    	<s:hidden name="irpUser.weibotoken" />
	    	<s:hidden name="irpUser.weibouserid" />
	    	<s:hidden name="irpUser.nickname" />
	    	<s:hidden name="irpUser.sex" />
	    	</s:if>
            <label><input type="text" name="irpUser.username" value="<s:property value="irpUser.username" />" class="easyui-validatebox" required="true" validType="isExist[2,20]" missingMessage="请填写登录名"placeholder="设置用户名" /></label><br />
            <label><input type="text" name="irpUser.truename" value="" class="easyui-validatebox" required="true" validType="length[2,20]" missingMessage="请填写真实姓名" placeholder="请输入你的真实姓名" /></label><br />
            <label><input type="text" name="irpUser.email" value="" class="easyui-validatebox" validType="extEmail[30]" missingMessage="请填写电子邮箱" placeholder="设置你的电子邮箱"/></label><br />
            <label><input id="repassword" type="password" name="repassword" value="" class="easyui-validatebox" required="true" validType="minLength[6]" missingMessage="请填写登录密码" placeholder="设置你的登录密码" /></label><br />
            <label><input type="password" name="irpUser.password" value="" class="easyui-validatebox" required="true" missingMessage="请填写确认密码" validType="eqPassword['#regForm input[name=repassword]']" placeholder="请再次输入密码" /></label><br />
            <s:if test="@com.tfs.irp.util.SysConfigUtil@getSysConfigValue('Multilevel_Verification')==1">
	            <label><input id="alphabet" type="text"  class="easyui-validatebox" required="true" missingMessage="请填写验证码" name="checkalphabet" maxlength="5" style="width: 50%" value="" placeholder="验证码"/>
	            <div class="square" style="width:130px;float:right;border:none;margin-left: 15px;">
					<img src="<%=rootPath%>Kaptcha.jpg" id="kaptchaImage" style="height:42px;cursor:pointer;width: 100%;"/>
				</div>
				</label>
            </s:if>
            <s:if test="@com.tfs.irp.util.SysConfigUtil@getSysConfigValue('Multilevel_Verification')==2">
           	<label><input id="phonenumber" maxlength="20" type="text" name="irpUser.mobile" class="easyui-validatebox" required="true" missingMessage="请填写手机号" validType="extPhone[30]" value="" placeholder="手机号"/></label>
             <label><input id="alphabet" type="text"  class="easyui-validatebox" required="true" missingMessage="请填写验证码" name="checkalphabet" maxlength="5" style="width: 50%" value="" placeholder="验证码"/>
	            <div class="square" style="width:130px;float:right;border:none;margin-left: 15px;">
					<input onclick="sendSmss()"  style="width: 100%;margin-top: 0px;font-size: 15px;background: #61bb4e;color: #fff;text-align: center;line-height: 40px;height: 40px;border: 1px;cursor: pointer;" type="button" value="发送短信验证码" >
				</div>
				</label>
	            <div class="square" style="width:130px;float:right;border:none;display: none;">
					<img src="<%=rootPath%>Kaptcha.jpg" id="kaptchaImage" style="height:42px;cursor:pointer;width: 100%;"/>
				</div>
            </s:if>
            <label style="margin-bottom: 0"><a href="javascript:void(0)" onclick="regSubmit()" ><img src="<%=rootPath%>images/zc_btn_03.jpg" /></a></label>
            <label class="sm"><input type="checkbox" name="" id="sm" class="easyui-validatebox" checked="checked" required="true"  missingMessage="请同意此条款"><a href="#">《IRP网站服务条款》《法律声明和隐私权政策》</a></label>
        </form>
        
        
</div>
</div>
<jsp:include page="/view/include/client_foot.jsp" />
</body>
</html>