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
	            }else{
	            	var result = $.ajax({
	            		url:'<%=rootPath %>user/check_groupname.action',
	            		type: "POST",
	            		data: {
	            			parentId: <s:property value="parentId" />,
	            			groupId: <s:property value="irpGroup.groupid" />,
	            			groupName: value
	            		},
	            		dataType : "json",
	            		async: false,
	            		cache : false
	            	}).responseText;
	            	if(result=='false'){
	            		$.fn.validatebox.defaults.rules.isExist.message = '输入的组织名称已存在';
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
	var jqDescTxt = $('#groupForm').find('input:text[name="irpGroup.gdesc"]');
	if(jqDescTxt && $.trim(jqDescTxt.val()).length==0){
		jqDescTxt.val(_value);
	}
}
</script>
<form id="groupForm" method="post">
<input type="hidden" name="irpGroup.groupid" value="<s:property value="irpGroup.groupid" />" />
<input type="hidden" name="irpGroup.parentid" value="<s:property value="parentId" />" />
<input type="hidden" name="irpGroup.grouptype" value="<s:property value="irpGroup.grouptype" />" />
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cad9ea">
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">组织名称：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input name="irpGroup.groupname" class="easyui-validatebox" onblur="autoDesc(this.value)" validType="isExist[2,30]" required="true" missingMessage="请填写组织名称" type="text" value="<s:property value="irpGroup.groupname" />" /></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">组织描述：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input name="irpGroup.gdesc" class="easyui-validatebox" validType="length[2,60]" required="true" missingMessage="请填写组织描述" type="text" value="<s:property value="irpGroup.gdesc" />" /></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">前一个组织：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><select name="irpGroup.grouporder">
			<option value="0" <s:if test="irpGroup.grouporder==1 || irpGroup.grouporder==null">selected="selected"</s:if>>最前面</option>
			<s:iterator value="irpGroups">
			<option value="<s:property value="grouporder" />" <s:if test="(grouporder+1)==irpGroup.grouporder">selected="selected"</s:if>><s:property value="gdesc" /></option>
			</s:iterator>
		</select></td>
	</tr>
</table>
</form>
</body>
</html>
