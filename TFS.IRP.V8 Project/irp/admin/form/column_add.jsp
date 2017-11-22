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
<title>表单字段增加</title>
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />
</head>

<body>
<script type="text/javascript">
$.extend($.fn.validatebox.defaults.rules, { 
	    isExitcolumnname : {
            validator:function(value){ 
            var msg = false;
         	$.ajax({
							url: '<%=rootPath%>column/isexitname.action',
						    async: false,
						    cache: false,
						    data:{
						    columnname:value,
						    columntablaname:'<%=init %>'
						    },
						    success:function(data){
						    if(data==0){
								msg= true;
								}else{
								$.fn.validatebox.defaults.rules.isExitcolumnname.message = '该字段显示名称已存在';
								msg = false;
							}
						    }
						});
					
			return msg;		
	    	},
	    	message:''
            },
         isExitcolumntablenamecol : {
         validator:function(value){ 
         		 var msg = false;
         		  var  parent=/^[a-zA-Z]+$/;
					  if(!parent.test(value))
					  {
					  $.fn.validatebox.defaults.rules.isExitcolumntablenamecol.message = '该名称需为英文字符';
						return false;
					  }
         		 
         	$.ajax({
							url: '<%=rootPath%>column/isexittablenamecol.action',
						    async: false,
						    cache: false,
						    data:{
						   columntablenamecol:value,
						    columntablaname:'<%=init %>'
						    },
						    success:function(data){
						    if(data==0){
								msg= true;
								}else{
								$.fn.validatebox.defaults.rules.isExitcolumntablenamecol.message = '该数据库表字段名已存在';
								msg = false;
							}
						    }
						});					
			return msg;		 
	    	},
	    	message:''
            },
            columnLength:{
            validator:function(value){
            var type=$('#columntype').val();
            if(type=="整型"||type=="时间"){
            	if(value.length<=2){
            		return true
            	}else{
            	$.fn.validatebox.defaults.rules.columnLength.message = '输入内容长度必须介于1和2之间';
            		return false;
            	}
            }else if(type=="字符串"){
            	if(value.length<5){
            		return true
            	}else{
            	$.fn.validatebox.defaults.rules.columnLength.message = '输入内容长度必须介于1和4之间';
            		return false;
            	}
            }else{
            	return true;
            }
            },
            message:''
            },
            columndefVal:{
            validator:function(value){
            var suzi =/^[0-9]*$/;
            var type=$('#columntype').val();
             var daterul =  /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;
             if(type=="整型"){
            	if(suzi.test(value)){
            			if(value.length<=20){
            			return true;
            	}else{           	
            			$.fn.validatebox.defaults.rules.columndefVal.message = '输入内容长度必须为小于20';
            			return false;
            		}            		
            	}else{
	            	$.fn.validatebox.defaults.rules.columndefVal.message = '输入内容必须为数字';
	            		return false;
            	}
            }else if(type=="字符串"){
            	if(value.length<=600){
            		return true;
            	}else{
            	return false;
            	$.fn.validatebox.defaults.rules.columndefVal.message = '输入内容长度必须小于600';           		
            	}
            		
            }else if(type=="大字段"){           
            		return true;           
            }else{
            if(daterul.test(value)){
            if(value.length<=10){
           		 return true;
            }else{
	            $.fn.validatebox.defaults.rules.columndefVal.message = '输入日期长度必须小于10';
	            return false;
            }
            }else{
            	$.fn.validatebox.defaults.rules.columndefVal.message = '输入日期格式有误,正确的格式为2016-09-30';
            		return false;
            }
            }
            },
            message:''
            },
            displayvaluecheck:{
            	 validator:function(value){ 
            		 var type=$("#displaytype").val();
                  
            			if(type=='radio'||type=='list'||type=='checkbox'){

            		 //if(value!=""&&value!=null){
            			 
            		 var reg=/^\w(\/\w)*$/ ;
            			if(reg.test(value)){
            			
            			
         	            return true;
            			}else{
            			
            				$.fn.validatebox.defaults.rules.displayvaluecheck.message = '输入格式需要是s/s/s类似的';
              	            return false;	
            			}
            		 }else{
            			 return true;
            		 }

             	
    	    	},
    	    	message:''
            }
	});
function selectType(){
	var type=$("#displaytype").val();
	if(type=='radio'||type=='list'||type=='checkbox'){
		$("#displayvalue").hide();
		$("#displayvalue1").hide();
		$("#displayvalue2").show();
		$("#textvalue").attr("disabled","disabled");
		$("#textvalue1").attr("disabled","disabled");
		  $("#textvalue2").attr("disabled",false);
	}else if(type=='tree'){
		 $("#displayvalue").hide();
		  $("#displayvalue2").hide();
		$("#displayvalue1").show(); 
		  $("#textvalue2").attr("disabled","disabled");
		  $("#textvalue").attr("disabled","disabled");
		   $("#textvalue1").combotree("enable");
	}else{
		$("#displayvalue1").hide();
		$("#displayvalue2").hide();
		$("#displayvalue").show();
		 $("#textvalue2").attr("disabled","disabled");
		  $("#textvalue1").attr("disabled","disabled");
		    $("#textvalue").attr("disabled",false);
	}
}
$(document).ready(function(){

	selectType();
});
</script>
<form id="columnForm" method="post">
 <input type="hidden" name="irpFormColumn.columnid" value="<s:property value="irpFormColumn.columnid" />" />
 <input type="hidden" name="irpFormColumn.columntablaname" value="<%=init %>" />
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cad9ea">
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">字段显示名称：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input id="columnname" name="irpFormColumn.columnname"  validType="isExitcolumnname" class="easyui-validatebox" required="true" missingMessage="请填写字段显示名称" type="text"  /></td>
	</tr>
	
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">数据库表字段名：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input name="irpFormColumn.columntablenamecol" validType="isExitcolumntablenamecol" class="easyui-validatebox" required="true" missingMessage="请填写数据库表字段名" type="text"  />
		</td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">字段描述：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><textarea name="irpFormColumn.columndesc" validType="length[2,600]" class="easyui-validatebox" required="true" missingMessage="请填写字段描述" type="text"></textarea></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">字段类型：</td>
		<td bgcolor="#FFFFFF" class="main_bright">
		<select name="irpFormColumn.columntype" id="columntype">
						
						<option value="整型" >整型</option>
						<option value="字符串"  >字符串</option>
						<option value="时间" >时间</option>
						<option value="大字段" >大字段</option>
						
					</select>
		</td>
	</tr>
	<tr id="length">
		<td align="right" bgcolor="#f5fafe" class="main_bleft">字段长度：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input  name="irpFormColumn.columnlongtext" validType="columnLength" class="easyui-numberbox" required="true" missingMessage="请填写字段长度" type="text"  /></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">数据库表字段默认值：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><textarea name="irpFormColumn.columndefval" validType="columndefVal" class="easyui-validatebox"  type="text"  ></textarea></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">字段状态：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input name="irpFormColumn.columnstatus" validType="length[2,60]"   type="radio" value="10" checked="checked" />正常
		<input name="irpFormColumn.columnstatus"   type="radio" value="0" />隐藏</td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">在列表中显示状态：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input name="irpFormColumn.columninliststatus" validType="length[2,60]"   type="radio" value="10" checked="checked" />正常
		<input name="irpFormColumn.columninliststatus"   type="radio" value="0" />隐藏</td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">是否只读：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input name="irpFormColumn.columnisreadonly" validType="length[2,60]"   type="radio" value="10" />是&nbsp;&nbsp;&nbsp;&nbsp;
		<input name="irpFormColumn.columnisreadonly" checked="checked"   type="radio" value="0" />否</td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">是否可以为空：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input name="irpFormColumn.columnempty" validType="length[2,60]"   type="radio" value="10" checked="checked" />是&nbsp;&nbsp;&nbsp;&nbsp;
		<input name="irpFormColumn.columnempty"   type="radio" value="0" />否</td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">字段显示类型：</td>
		<td bgcolor="#FFFFFF" class="main_bright">
		<select onchange="selectType()" name="irpFormColumn.displaytype" id="displaytype">
						
						<option value="text">文本框</option>
                        <option value="password">密码</option>
                        <option value="radio">单选框</option>
                         <option value="checkbox">多选</option>
                      <option value="date">日期(yyyy-MM-dd HH:mm)</option>
                        <option value="file">文件</option>
                    <option value="textarea">多行文本</option>
                   <option value="list">下拉框</option>              						
                   <option value="tree">分类树</option>              						
					</select></td>
	
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">字段显示值：</td>
		<td bgcolor="#FFFFFF" class="main_bright">
		<div id="displayvalue"><textarea id="textvalue"  disabled="disabled"  name="irpFormColumn.displaytypevalue"  class="easyui-validatebox"  type="text"  ></textarea>
		 
		 </div>
		 <div id="displayvalue2" style="display:none"><textarea disabled="disabled" id="textvalue2" name="irpFormColumn.displaytypevalue"  class="easyui-validatebox" required="true"   missingMessage="请填写字段显示值" type="text"  ></textarea><font color="#777">*字段显示值请用“/”隔开</font>
		 
		 </div>	
		 <div id="displayvalue1" style="display:none"><input disabled="disabled" id="textvalue1" name="irpFormColumn.displaytypevalue" url="<%=rootPath  %>site/to_load_site_to_check_channel.action?siteid=2" class="easyui-combotree"   missingMessage="请选择栏目" style="width: 140px;" />
		
		 </div>	
		</td>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">字段校验类型：</td>
		<td bgcolor="#FFFFFF" class="main_bright">
		<select onchange="checkType()" name="irpFormColumn.checktype" id="checktype">
				<option value="0">请选择校验类型</option>
				<option value="strlen">长度限制</option>
                <option value="checkmobile">电话验证</option>
                <option value="checkidcard">身份证号验证</option>
                <option value="checkemail">邮箱验证</option>          						
                <option value="checknumber">数字验证</option>          						
                <option value="checkage">年龄验证</option>          						
                <option value="checkzip">邮编验证</option>          						
		</select>
		</td>
	</tr>
	
	
	

	
</table>
</form>
</body>
</html>
