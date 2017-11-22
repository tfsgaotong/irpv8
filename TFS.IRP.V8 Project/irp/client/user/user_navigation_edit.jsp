<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/oacss.css" />
<script type="text/javascript" src="<%=rootPath %>client/js/jquery.easyui.min.js"></script>
<script type="text/javascript">
$(function(){
	/* 扩展验证  */
	$.extend($.fn.validatebox.defaults.rules, {   
		urls : {
			validator : function(value, param) {
			    var strRegex = "^((https|http|ftp|rtsp|mms)://)?[a-z0-9A-Z]{3}\.[a-z0-9A-Z][a-z0-9A-Z]{0,61}?[a-z0-9A-Z]\.com|net|cn|cc (:s[0-9]{1-4})?/$";
				var re = new RegExp(strRegex);
				return re.test($.trim(value));
			},
			message : '请输入有效的网址'
		},
		maxLength : {
			validator: function(value, param){   
	            return value.length < param[0];   
	        },
	        message: '输入内容长度不能超过{0}'  
		}
	});
});
</script>
<title>Insert title here</title>
</head>
<body>
	<div style="background-color: rgb(250, 250, 250);width: 520px;height: 90px;text-align: center;">
		<input style="display: none;" id="navigationidofmine" value="<s:property value="irpUserNavigation.navigationid" />">
			<div>
				<form id="navigationform" onsubmit="return false;"> 
					<label>名称&nbsp;:&nbsp;</label>
					<input style="margin-top: 40px;" id="navigationnameofmine" class="easyui-validatebox"  required="true" validType="maxLength[250]" missingMessage="请输入名称" type="text" value="<s:property value="irpUserNavigation.navigationname" />">
	                <label style="margin-left: 40px;">网址&nbsp;:&nbsp;</label>
					<input id="navigationvalueofmine" class="easyui-validatebox" required="true" validType="url" invalidMessage="请输入有效的网址"  missingMessage="网址不能为空" type="text" value="<s:property value="irpUserNavigation.navigationvalue" />">
				</form>
			</div>
	</div>
</body>
</html>