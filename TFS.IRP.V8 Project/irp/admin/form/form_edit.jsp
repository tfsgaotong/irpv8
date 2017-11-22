<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
java.util.UUID uuid  = java.util.UUID.randomUUID();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>表单修改</title>
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />
</head>

<body>
<script type="text/javascript">
$.extend($.fn.validatebox.defaults.rules, { 
	isExitformname : {
        validator:function(value){ 
        var msg = false;
        var s="<s:property value='irpForm.formname'/>";
     
       if(value==s){
    		msg=true;
    		
       }else{
    	   
    	   $.ajax({
				url: '<%=rootPath%>form/isexitformname.action',
			    async: false,
			    cache: false,
			    data:{
			    	formname:value
			  
			    },
			    success:function(data){
			    if(data=="0"){
					msg= true;
					}else if(data=='1'){
					$.fn.validatebox.defaults.rules.isExitformname.message = '该字段显示名称已存在';
					msg = false;
				}else if(data=='2'){
					$.fn.validatebox.defaults.rules.isExitformname.message = '该字段不能为关键字';
					msg = false;
				}
			    }
			});
       }
     
				
		return msg;		
    	},
    	message:''
        },
        isExitformtablename : {
     validator:function(value){ 
     		 var msg = false;
     		 
     		 var s="<s:property value='irpForm.formtablename'/>";
     	       if(value==s){
     	    		msg=true;
     	    	 
     	       }else{
     		  var  parent=/^[A-Z].[A-Z_].*[A-Z]+$/;
			  if(!parent.test(value))
			  {
			  $.fn.validatebox.defaults.rules.isExitformtablename.message = '该名称不合法';
				return false;
			  }
     	$.ajax({
						url: '<%=rootPath%>form/isexitformtablename.action',
					    async: false,
					    cache: false,
					    data:{
					    	formtablename:value,
					    	oldname:s
					    	
					    },
					    success:function(data){
					    	  if(data=="0"){
									msg= true;
									}else if(data=='1'){
									$.fn.validatebox.defaults.rules.isExitformtablename.message = '该字段数据库名称已存在';
									msg = false;
								}else if(data=='2'){
									$.fn.validatebox.defaults.rules.isExitformtablename.message = '该字段不能为关键字';
									msg = false;
								}else if(data=='3'){
									$.fn.validatebox.defaults.rules.isExitformtablename.message = '该字段不能以IRP_开头';
									msg = false;
									}
					    }
					});	}				
		return msg;		 
    	},
    	message:''
        }      
});


</script>
<form id="addForm1" method="post">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cad9ea">
     <input type="hidden" name="formid" value="<s:property value='irpForm.formid'/>" />
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">表单显示名称：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input name="formname"  validType="isExitformname" class="easyui-validatebox" required="true" missingMessage="请填写表单显示名称" type="text" value="<s:property value='irpForm.formname'/>" /></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">表单描述：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input name="formdesc" validType="length[2,60]" class="easyui-validatebox" required="true" missingMessage="请填写表单描述" type="text"  value="<s:property value='irpForm.formdesc'/>" /></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">数据库表名称：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input id="formtablename" name="formtablename" validType="isExitformtablename"  class="easyui-validatebox" required="true" missingMessage="请填写数据库表名称" type="text" value="<s:property value='irpForm.formtablename'/>"   />
			<font color="#777">*由大写的英文和_组成，以英文开头和结尾</font>
		</td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">表单html描述数据 ：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><textarea  name="formhtml"    validType="length[2,50]"  style="width:90%;height:40px;"><s:property value='irpForm.formhtml'/></textarea></td>
	</tr>
	<!-- <tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">表单script算法功能 ：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><textarea  name="formscript"     validType="length[2,500]"  style="width:90%;height:40px;"><s:property value='irpForm.formscript'/></textarea></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">表单script算法参数 ：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><textarea  name="formparem"     validType="length[2,500]"  style="width:90%;height:40px;"><s:property value='irpForm.formparem'/></textarea><br><span style="color:grey">例如：'参数名1','参数名2'</span></td>
	</tr>
	<tr> -->
		<td align="right" bgcolor="#f5fafe" class="main_bleft">表单发布状态：</td>
		<td bgcolor="#FFFFFF" class="main_bright">
		
		<input name="formstatus" validType="length[2,60]" <s:if test="irpForm.formstatus==10">checked="checked"</s:if>  type="radio" value="10" />不发布
		<input name="formstatus" validType="length[2,60]"   <s:if test="irpForm.formstatus==20">checked="checked"</s:if> type="radio" value="20"  />发布</td>
		 
	     <input type="hidden" id="columnids_update" name="columnids" value="a" style="display:none">
	     <input type="hidden" id="columnid" name="columnid" >
	     <input type="hidden" id="columnname" name="columnname" >
	     <input type="hidden" id="columntablenamecol" name="columntablenamecol" >
	     <input type="hidden" id="columndesc" name="columndesc" >
	     <input type="hidden" id="columnlongtext" name="columnlongtext" >
	     <input type="hidden" id="columndefval" name="columndefval" >
	     <input type="hidden" id="columntype" name="columntype" >
	     <input type="hidden" id="displaytypevalue" name="displaytypevalue" >
	     <input type="hidden" id="displaytype5" name="displaytype" >
	
	     
	   
	</tr>
	
	
</table>

</form>
</body>
</html>
