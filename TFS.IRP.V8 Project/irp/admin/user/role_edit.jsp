<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户管理</title>
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />
</head>

<body>
<script type="text/javascript">
$(function(){
	/* 扩展验证  */
	$.extend($.fn.validatebox.defaults.rules, {   
		isExist: {/* 定义最小长度 */
	        validator: function(value, param){   
	            if(value.length < param[0] || value.length > param[1]){
	            	$.fn.validatebox.defaults.rules.isExist.message = $.fn.validatebox.defaults.rules.length.message;
					return false;
	            } else {
	            	var result = $.ajax({
	            		url:'<%=rootPath %>user/check_rolename.action',
	            		type: "POST",
	            		data: {
	            			roleId: <s:property value="irpRole.roleid" />,
	            			roleName: value
	            		},
	            		dataType : "json",
	            		async: false,
	            		cache : false
	            	}).responseText;
	            	if(result=='false'){
	            		$.fn.validatebox.defaults.rules.isExist.message = '输入的角色名称已存在';
						return false;
	            	}else{
	            		return true;
	            	}
	            }
	        },
	        message: ''  
	    }
	}); 
});

function autoDesc(_value){
	var jqDescTxt = $('#roleForm').find('input:text[name="irpRole.roledesc"]');
	if(jqDescTxt && $.trim(jqDescTxt.val()).length==0){
		jqDescTxt.val(_value);
	}
}
</script>
<form id="roleForm" method="post">
<input type="hidden" name="irpRole.roleid" value="<s:property value="irpRole.roleid" />" />
<input type="hidden" name="irpRole.roletype" value="<s:property value="roleType" />" />
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cad9ea">
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">角色名称：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input name="irpRole.rolename" onblur="autoDesc(this.value)" validType="isExist[2,60]" class="easyui-validatebox" required="true" missingMessage="请填写角色名称" type="text" value="<s:property value="irpRole.rolename" />" /></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">角色描述：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input name="irpRole.roledesc" validType="length[2,60]" class="easyui-validatebox" required="true" missingMessage="请填写角色描述" type="text" value="<s:property value="irpRole.roledesc" />" /></td>
	</tr>
</table>
</form>
</body>
</html>
