<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
String init = request.getParameter("initname");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>表单字段编辑</title>
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />
</head>

<body>
<script type="text/javascript">
var s='<s:property value="irpFormColumn.displaytypevalue"/>'
$.extend($.fn.validatebox.defaults.rules, { 
     editcolumnname : {
            validator:function(value){ 
            var msg = false;
         	$.ajax({
							url: '<%=rootPath%>column/iseditname.action',
						    async: false,
						    cache: false,
						    data:{
						    columnid:<s:property value="irpFormColumn.columnid" />,
						    columnname:value,
						    columntablaname:'<s:property value="irpFormColumn.columntablaname" />'
						    },
						    success:function(data){
						    if(data==0){
								msg= true;
								}else{
								$.fn.validatebox.defaults.rules.editcolumnname.message = '该字段显示名称已存在';
								msg = false;
							}
						    }
						});
					
			return msg;		
	    	},
	    	message:''
            },
         editcolumntablenamecol : {
         validator:function(value){ 
         		 var msg = false;
         		 var  parent=/^[a-zA-Z]+$/;
					  if(!parent.test(value))
					  {
					  $.fn.validatebox.defaults.rules.isExitcolumntablenamecol.message = '该名称需为英文字符';
						return false;
					  }
         	$.ajax({
							url: '<%=rootPath%>column/isedittablenamecol.action',
						    async: false,
						    cache: false,
						    data:{
						    columnid:<s:property value="irpFormColumn.columnid" />,
						   columntablenamecol:value,
						    columntablaname:'<s:property value="irpFormColumn.columntablaname" />'
						    },
						    success:function(data){
						    if(data==0){
								msg= true;
								}else{
								$.fn.validatebox.defaults.rules.editcolumntablenamecol.message = '该数据库表字段名已存在';
								msg = false;
							}
						    }
						});					
			return msg;		 
	    	},
	    	message:''
            },
 	});         
function isShow(_type){
if(_type!="时间"){
$('#length').show();
}else{
$('#length').hide();
}
}
function selectType1(){
	var type=$("#displaytype").val();

	if(type=='radio'||type=='list'||type=='checkbox'||type=='tree'){
		$("#displayvalue").show();
		
	}else{
		$("#displayvalue").hide();
	}
}

function selectType(){
	var type=$("#displaytype").val();
	if(type=='radio'||type=='list'||type=='checkbox'){
		$("#displayvalue").hide();
		$("#displayvalue1").hide();
		$("#displayvalue2").show();
		
		$("#textvalue").attr("disabled","disabled");
		  $("#textvalue2").attr("disabled",false);
		     $("#textvalue").text("");
		 $("#textvalue2").text(s);
		 $('#textvalue1').combotree({ disabled: true,value:"" }); 
	}else if(type=='tree'){
		 $("#displayvalue").hide();
		  $("#displayvalue2").hide();
		$("#displayvalue1").show(); 
		  $("#textvalue2").attr("disabled","disabled");
		  $("#textvalue").attr("disabled","disabled");
		   $('#textvalue1').combotree({ disabled: false,value:s });  
		     $("#textvalue").text("");
		 $("#textvalue2").text("");
	}else{
	
		$("#displayvalue1").hide();
		$("#displayvalue2").hide();
		$("#displayvalue").show();
		 $('#textvalue1').combotree({ disabled: true,value:"" });  
		 $("#textvalue2").text("");
		 $("#textvalue2").attr("disabled","disabled");
		   $("#textvalue").attr("disabled",false);
		     $("#textvalue").text(s);
	}
}

$(document).ready(function(){
	selectType();
});
</script>
<form id="columnForm" method="post">
 <input type="hidden" name="irpFormColumn.columnid" value="<s:property value="irpFormColumn.columnid" />" />
 <input type="hidden" name="irpFormColumn.columntablaname" value="<s:property value="irpFormColumn.columntablaname" />" />
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cad9ea">
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">字段显示名称：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input id="columnname" name="irpFormColumn.columnname"  validType="editcolumnname" class="easyui-validatebox" required="true" missingMessage="请填写字段显示名称" type="text"  value="<s:property value="irpFormColumn.columnname"/>"/></td>
	</tr>
	
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">数据库表字段名：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input name="irpFormColumn.columntablenamecol" validType="editcolumntablenamecol" class="easyui-validatebox" required="true" missingMessage="请填写数据库表字段名" type="text"  value="<s:property value="irpFormColumn.columntablenamecol"/>"/>
		</td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">字段描述：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><textarea name="irpFormColumn.columndesc" validType="length[2,600]" class="easyui-validatebox" required="true" missingMessage="请填写字段描述" type="text"><s:property value="irpFormColumn.columndesc"/></textarea></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">字段类型：</td>
		<td bgcolor="#FFFFFF" class="main_bright">
		<select name="irpFormColumn.columntype" id="columntype" onclick="isShow(this.value)">
						
						<option value="整型" <s:if test="irpFormColumn.columntype in inttype">selected="selected"</s:if>>整型</option>
						<option value="字符串"  <s:if test="irpFormColumn.columntype in stringtype">selected="selected"</s:if>>字符串</option>
						<option value="时间" <s:if test="irpFormColumn.columntype in datetype">selected="selected"</s:if>>时间</option>
						<option value="大字段" <s:if test="irpFormColumn.columntype=='CLOB'">selected="selected"</s:if>>大字段</option>
						
					</select>
		
	</tr>
	<tr id="length">
		<td align="right" bgcolor="#f5fafe" class="main_bleft">字段长度：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input  name="irpFormColumn.columnlongtext" validType="columnLength" class="easyui-numberbox" required="true" missingMessage="请填写字段长度" type="text"  value="<s:property value="irpFormColumn.columnlongtext"/>"/></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">字段状态：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input name="irpFormColumn.columnstatus" <s:if test="irpFormColumn.columnstatus==10">checked="checked"</s:if>  type="radio" value="10"  />正常
		<input name="irpFormColumn.columnstatus"  <s:if test="irpFormColumn.columnstatus==0">checked="checked"</s:if> type="radio" value="0" />隐藏</td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">数据库表字段默认值：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><textarea name="irpFormColumn.columndefval" validType="columndefVal" class="easyui-validatebox"  type="text"  ><s:property value="irpFormColumn.columndefval"/></textarea></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">在列表中显示状态：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input name="irpFormColumn.columninliststatus"  <s:if test="irpFormColumn.columninliststatus==10">checked="checked"</s:if>  type="radio" value="10"  />正常
		<input name="irpFormColumn.columninliststatus"  <s:if test="irpFormColumn.columninliststatus==0">checked="checked"</s:if>  type="radio" value="0" />隐藏</td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">是否只读：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input name="irpFormColumn.columnisreadonly" <s:if test="irpFormColumn.columnisreadonly==10">checked="checked"</s:if>   type="radio" value="10" checked="checked" />是&nbsp;&nbsp;&nbsp;&nbsp;
		<input name="irpFormColumn.columnisreadonly"   <s:if test="irpFormColumn.columnisreadonly==0">checked="checked"</s:if> type="radio" value="0" />否</td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">是否可以为空：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input name="irpFormColumn.columnempty" <s:if test="irpFormColumn.columnempty==10">checked="checked"</s:if>   type="radio" value="10" checked="checked" />是&nbsp;&nbsp;&nbsp;&nbsp;
		<input name="irpFormColumn.columnempty"  <s:if test="irpFormColumn.columnempty==0">checked="checked"</s:if>  type="radio" value="0" />否</td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">字段显示类型：</td>
		<td bgcolor="#FFFFFF" class="main_bright">
		<select onchange="selectType()" name="irpFormColumn.displaytype" id="displaytype">
						
						<option  <s:if test="irpFormColumn.displaytype=='text'">selected="selected"</s:if> value="text">文本框</option>
                        <option <s:if test="irpFormColumn.displaytype=='password'">selected="selected"</s:if> value="password">密码</option>
                        <option <s:if test="irpFormColumn.displaytype=='radio'">selected="selected"</s:if> value="radio">单选框</option>
                         <option <s:if test="irpFormColumn.displaytype=='checkbox'">selected="selected"</s:if> value="checkbox">多选</option>
                          <!--  <option <s:if test="irpFormColumn.displaytype==date'">selected="selected"</s:if> value="date">日期(yyyy-MM-dd)</option> -->
                      <option <s:if test="irpFormColumn.displaytype=='date'">selected="selected"</s:if> value="datetime">日期（yyyy-MM-dd HH:mm:ss）</option>
                        <option <s:if test="irpFormColumn.displaytype=='file'">selected="selected"</s:if> value="file">文件</option>
                    <option <s:if test="irpFormColumn.displaytype=='textarea'">selected="selected"</s:if> value="textarea">多行文本</option>
                   <option <s:if test="irpFormColumn.displaytype=='list'">selected="selected"</s:if> value="list">下拉框</option>
                   <option <s:if test="irpFormColumn.displaytype=='tree'">selected="selected"</s:if> value="tree">分类树</option>
                 
                  <!--  <option <s:if test="irpFormColumn.displaytype=='image'">selected="selected"</s:if> value="image">图片</option> -->
                
						
					</select></td>
					
		
	</tr>
	<tr >
		<td align="right" bgcolor="#f5fafe" class="main_bleft">字段显示值：</td>
		<td bgcolor="#FFFFFF" class="main_bright">
		<div id="displayvalue"><textarea disabled="disabled" id="textvalue" name="irpFormColumn.displaytypevalue"  class="easyui-validatebox"  type="text"  ></textarea>
		 
		 </div>
		 <div id="displayvalue2" style="display:none"><textarea disabled="disabled" id="textvalue2"name="irpFormColumn.displaytypevalue"  class="easyui-validatebox" required="true"   missingMessage="请填写字段显示值" type="text"  ></textarea><font color="#777">*字段显示值请用“/”隔开</font>
		 
		 </div>	
		 <div id="displayvalue1" style="display:none"><input  disabled="disabled"  id="textvalue1" name="irpFormColumn.displaytypevalue"  class="easyui-combotree" url="<%=rootPath  %>site/to_load_site_to_check_channel.action?siteid=2"  style="width: 140px;"  />
		 </div>	
		</td>
	</tr>	
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">字段校验类型：</td>
		<td bgcolor="#FFFFFF" class="main_bright">
		<select onchange="checkType()" name="irpFormColumn.checktype" id="checktype">
				<option <s:if test="irpFormColumn.checktype=='0'">selected="selected"</s:if> value="">请选择校验类型</option>
				<option <s:if test="irpFormColumn.checktype=='strlen'">selected="selected"</s:if> value="strlen">长度限制</option>
                <option <s:if test="irpFormColumn.checktype=='checkmobile'">selected="selected"</s:if> value="checkmobile">电话验证</option>
                <option <s:if test="irpFormColumn.checktype=='checkidcard'">selected="selected"</s:if> value="checkidcard">身份证号验证</option>
                <option <s:if test="irpFormColumn.checktype=='checkemail'">selected="selected"</s:if> value="checkemail">邮箱验证</option>          						
                <option <s:if test="irpFormColumn.checktype=='checknumber'">selected="selected"</s:if> value="checknumber">数字验证</option>          						
                <option <s:if test="irpFormColumn.checktype=='checkage'">selected="selected"</s:if> value="checkage">年龄验证</option>          						
                <option <s:if test="irpFormColumn.checktype=='checkzip'">selected="selected"</s:if> value="checkzip">邮编验证</option>          						
		</select>
		</td>
	</tr>
</table>
</form>
</body>
</html>
