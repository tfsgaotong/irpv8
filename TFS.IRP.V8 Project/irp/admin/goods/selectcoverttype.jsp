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
<title>表单增加</title>
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />
</head>

<body>
<script type="text/javascript">
$.extend($.fn.validatebox.defaults.rules, { 
	integer : {// 验证整数
		validator : function(value) {
		return /^[+]?[1-9]+\d*$/i.test(value);
		},
		message : '请输入数字。'
	},
	isExitCode : {
        validator:function(value){ 
        var msg = false;
     	$.ajax({
						url: '<%=rootPath%>goods/isexitcode.action',
					    async: false,
					    cache: false,
					    data:{
					    	goodsno:value
					  
					    },
					    success:function(data){
					    if(data=="0"){
							msg= true;
							}else{
							$.fn.validatebox.defaults.rules.isExitCode.message = '请输入唯一编码';
							msg = false;
						}
					    }
					});
				
		return msg;		
    	},
    	message:''
        },
        isExitformtablename : {
     validator:function(value){ 
     		 var msg = false;
     	$.ajax({
						url: '<%=rootPath%>form/isexitformtablename.action',
					    async: false,
					    cache: false,
					    data:{
					    	formtablename:value
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
								}
					    }
					});					
		return msg;		 
    	},
    	message:''
        }      
});
</script>
<form method="post" >
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cad9ea" id="addCovert">
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">兑换方式：</td>
		<td bgcolor="#FFFFFF" class="main_bright">
		<s:if test="covertState==20">
		<input name="coverttype"  type="radio" value="20" checked="checked" />积分
		</s:if>
		<input name="coverttype"  type="radio" value="10" <s:if test="covertState==10">checked="checked"</s:if> />勋章</td>
	</tr>
	<%-- <tr>
	        <td align="right" bgcolor="#f5fafe" class="main_bleft">勋章名称：</td>
	        <td bgcolor="#FFFFFF" class="main_bright">
				<select id="chnlorder" name="irpGoods.medalid" style=" width: 200px;" >
			   	     <option value="0" title="选择勋章">--选择勋章--</option>
				   	   <s:iterator value="listMedal">
				   		 <option <s:if test="irpGoods.medalid==medalid">selected="selected"</s:if> value="<s:property value='medalid'/>">
							<s:property value='medalname'/>
						</option>
				       </s:iterator>
			   </select>
		    </td>
	     </tr> --%>
</table>
</form>
</body>
</html>
