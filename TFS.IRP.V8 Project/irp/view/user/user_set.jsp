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
<link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css">
<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css">
<link href="<%=rootPath %>view/css/easyui.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath %>view/css/icon.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath %>view/css/discuz.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.combo{
	background: none repeat scroll 0 0 #f7f7f7;
	border: 1px solid #d1d1d1;
	border-radius: 3px;
}
.combo .combo-text{
	font-size: 13px;
	color: #333;
}
.combo input,.combo-arrow{background-color:#f7f7f7}
select{
	background: none repeat scroll 0 0 #f7f7f7;
    border: 1px solid #bdbdbd;
    border-radius: 3px;
    color: #333;
    font-size: 13px;
    height: 34px;
    line-height: 34px;
    overflow: hidden;
    min-width:80px;
}
.setUp .divrow{
	padding-top:15px;
}
.setUp .divrow .title{
	color:#2061b0;
	width: 80px;
	text-align:right;
	display:inline-block;
}
</style>

<script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jsAddress.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/modernizr.custom.js"></script>
<script type="text/javascript" >
//保存
function save(){
	$('#userInfo').form('submit',{
		url : "<%=rootPath %>user/user_set_dowith.action",
		success:function(data){
			if(data>0){
				$.dialog.tips('保存成功',1,'32X32/succ.png',function(){
					location.reload(true);
				});
			}else{
				$.dialog.tips('保存失败',1,'32X32/fail.png');
			}
		}
	});
}

//修改密码
function editPass(){
	//获得内容
	var result = $.ajax({
		url: '<%=rootPath %>user/user_pwd_set.action',
		dataType: "json",
	    data: {
	    	userIds: <s:property value="irpUser.userid" />
	    },
	    async: false,
	    cache: false
	}).responseText;
	//初始化弹出框
	$.dialog({
		title:'修改密码',
		content: result,
		max: false,
	    min: false,
		ok: function(){
		
			var validate = $('#pwdForm').form('validate');
			if(validate){
				var oldPassWord=$('#pwdForm').find("input[name='oldPassWord']").val();
				oldPassWord = $.ajax({
					url: '<%=rootPath %>user/user_pwd_validate.action',
					dataType: "json",
				    data: {
				    	oldPassWord:oldPassWord
				    },
				    async: false,
				    cache: false
				}).responseText;
				if(oldPassWord==0){
					$.dialog.tips('输入原密码有误',1,'32X32/fail.png');
					$.dialog.list[0].close();
				}else{
					$('#pwdForm').form('submit',{
		    			url : "<%=rootPath %>user/user_pwd_update.action",
		    			success:function(data){
		    				if(data>0){
		    					$.dialog.tips('修改成功',1,'32X32/succ.png');
		    				}else{
		    					$.dialog.tips('修改失败',1,'32X32/fail.png');
		    				}
		    				$.dialog.list[0].close();
		    			}
		    		});}
			}else{
				return validate;
			}
	    },
	    okVal:'保存',
	    cancelVal: '关闭',
	    cancel: true,
	    lock: true,
	    padding: 0
	});
	//添加验证
	$('#pwdForm').find("input[name='passWord']").validatebox();
	$('#pwdForm').find("input[name='oldPassWord']").validatebox();
	$('#pwdForm').find("input[name='rePassWord']").validatebox();  
}

$(function(){
	/* 扩展验证  */
	$.extend($.fn.validatebox.defaults.rules, {   
		mobile : {
			validator : function(value, param) {
				return /^1[34578][0-9]\d{8}$/.test(value);
			},
			message : '请输入有效的手机号码'
		},
		maxLength : {
			validator: function(value, param){   
	            return value.length < param[0];   
	        },
	        message: '输入内容长度不能超过{0}'  
		},
		qq : {
			validator: function(value, param){
				if(isNaN(value)){
					$.fn.validatebox.defaults.rules.qq.message = '请输入正确的QQ号码';
					return false;
				}
	            return value.length < param[0];   
	        },
	        message: '输入内容长度不能超过{0}'  
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
		eqPassword : {/* 扩展验证两次密码 */
			validator : function(value, param) {
				return value == $(param[0]).val();
			},
			message : '输入两次密码不一致'
		} 
	});
});
</script>
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
            	<p class="current"><a href="<%=rootPath %>user/user_set.action" target="_self">基本资料</a></p>
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
            	<p><a href="<%=rootPath %>user/user_binding.action" target="_self">账号绑定</a></p>
            </div>
        </nav>
  </section>
  <section class="setUp">
        <div class="pan title"><span>基本资料</span><em>(*  必須填写项)</em></div>
   		<div class="line area20"></div>
   		<form id="userInfo" method="post" onsubmit="return false;" class="newForm">
   		<input type="hidden" name="irpUser.userid" value="<s:property value="irpUser.userid" />" />
   		<div class="rightN">
	   		<div class="pan title" style="padding-left:0px;"><em>以下信息将显示在个人资料页，方便大家了解你。</em></div>
	   		<div class="divrow">
	   			<span class="title">登&nbsp;录&nbsp;名：</span>
	   			<span style="color: rgb(153, 153, 153);font-size: 14px;" >&nbsp;&nbsp;&nbsp;<s:property value="irpUser.username" />&nbsp;&nbsp;&nbsp;</span>
	   			<a href="javascript:void(0)" onclick="editPass()">修改密码</a>
	   		</div>
			<div class="divrow">
	   			<span class="title">真实姓名：</span>
	   			<span class="inputText">&nbsp;&nbsp;<input type="text" class="easyui-validatebox" required="true" validType="length[2,20]" missingMessage="请填写真实姓名" value="<s:property value="irpUser.truename" />" name="irpUser.truename" id="irpUser.truename" /></span>
	   			<font color="red">*</font>
	   		</div>
	   		<div class="divrow">
	   			<span class="title">昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</span>
	   			<span class="inputText">&nbsp;&nbsp;<input type="text" class="easyui-validatebox" validType="maxLength[20]" value="<s:property value="irpUser.nickname" />" name="irpUser.nickname" id="irpUser.nickname" /></span>
	   		</div>
	   		<div class="divrow">
	   			<span class="title">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</span>
	   			<span>&nbsp;&nbsp;<input style="height:auto;" id="male" name="irpUser.sex" type="radio" value="1" <s:if test="irpUser.sex==1">checked="checked"</s:if> /><label for="male" style="color:#333;">&nbsp;男</label>&nbsp;&nbsp;&nbsp;&nbsp;<input style="height:auto;" id="female" name="irpUser.sex" type="radio" value="2" <s:if test="irpUser.sex==2">checked="checked"</s:if> /><label for="female" style="color:#333;">&nbsp;女</label></span>
	   		</div>
			<div class="divrow">
				<span class="title">生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日：</span>
				<span>&nbsp;&nbsp;<input type="text" class="easyui-datebox" data-options="currentText:'',editable:false" style="width:236px;height:36px;" value="<s:date name="irpUser.birthday" format="yyyy-MM-dd" />" name="irpUser.birthday" id="irpUser.birthday" /></span>
			</div>
	   		<div class="divrow">
	   			<span class="title">联系邮箱：</span>
	   			<span class="inputText">&nbsp;&nbsp;<input type="text" class="easyui-validatebox" validType="extEmail[30]" value="<s:property value="irpUser.email" />" name="irpUser.email" id="irpUser.email" /></span>
	   		</div>
	   		<div class="divrow">
	   			<span class="title">手机号码：</span>
	   			<span class="inputText">&nbsp;&nbsp;<input type="text" class="easyui-validatebox" validType="mobile" value="<s:property value="irpUser.mobile" />" name="irpUser.mobile" id="irpUser.mobile" /></span>
	   		</div>
	   		<div class="divrow">
	   			<span class="title">联系电话：</span>
	   			<span class="inputText">&nbsp;&nbsp;<input type="text" class="easyui-validatebox" validType="maxLength[30]" value="<s:property value="irpUser.tel" />" name="irpUser.tel" id="irpUser.tel" /></span>
	   		</div>
	   		<div class="divrow">
	   			<span class="title">&nbsp;&nbsp;Q&nbsp;&nbsp;&nbsp;&nbsp;Q&nbsp;&nbsp;：</span>
	   			<span class="inputText">&nbsp;&nbsp;<input type="text" class="easyui-validatebox" validType="qq[20]" value="<s:property value="irpUser.qq" />" name="irpUser.qq" id="irpUser.qq" /></span>
	   		</div>
	   		<div class="divrow">
	   			<span class="title">所在区域：</span>&nbsp;&nbsp;
	   			<select name="irpUser.province" id="province"></select> 
			    <select name="irpUser.city" id="city"></select>
			    <select name="irpUser.area" id="area"></select>
			    <script type="text/javascript">
					addressInit('province', 'city', 'area', '<s:property value="irpUser.province" escapeHtml="false" />', '<s:property value="irpUser.city" escapeHtml="false" />', '<s:property value="irpUser.area" escapeHtml="false" />');
				</script>
	   		</div>
	   		<div class="divrow">
	   			<span class="title">详细地址：</span>
	   			<span class="inputText">&nbsp;&nbsp;<input type="text" class="easyui-validatebox" validType="maxLength[60]" value="<s:property value="irpUser.location" />" name="irpUser.location" id="irpUser.location" /></span>
	   		</div>
	   		<div class="divrow">
	   			<div class="title">一句话介绍：</div>
	   			<span class="inputText">&nbsp;&nbsp;<textarea style="color: #333;font-size:13px;" cols="30" rows="4" class="infoText info_txtarea easyui-validatebox" validType="maxLength[300]" id="irpUser.expertintro" name="irpUser.expertintro"><s:property value="irpUser.expertintro" /></textarea></span>
	   		</div>
	   		<div class="sub" style="text-align:left;padding:20px 0 0 80px;">
	   			<input style="text-indent:0px;" type="button" value="保 &nbsp;&nbsp;&nbsp;&nbsp;存" onclick="save()">
	   		</div>
        </div>
        </form>
  </section>
</section>
<jsp:include page="../include/client_foot.jsp" />
</body>
</html>
