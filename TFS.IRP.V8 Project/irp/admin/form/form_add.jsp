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
	isExitformname : {
        validator:function(value){ 
        var msg = false;
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
				
		return msg;		
    	},
    	message:''
        },
        isExitformtablename : {
     validator:function(value){ 
     		 var msg = false;
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
					    	oldname:""
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
					});					
		return msg;		 
    	},
    	message:''
        }      
});
/*增加字段*/
function createColumn(){
	var dialogDiv = document.createElement("div");
	dialogDiv.id="editColumn";
	document.body.appendChild(dialogDiv);	
	$('#editColumn').dialog({   
	    modal:true,
	    href:'<%=rootPath %>form/column_add.action?initname=<%=uuid%>&formType=20',
	    title:'新建字段',
	    width:700,
	    height:470,
	    resizable:true,
	    buttons: [{
	    	text: '提交', 
	    	iconCls: 'icon-ok', 
	    	handler: function() {
	    		$('#columnForm').form('submit',{
	    			url : "<%=rootPath %>form/savecolumn.action",
	    			onSubmit: function(){
	    				var isValid = $(this).form('validate');
	    				if (isValid){
	    					$.messager.progress({
	    	    				text:'提交数据中...'
	    	    			});
	    				}
	    				return isValid;
	    			},
	    			success:function(data){
	    				$.messager.progress('close');
	    			
	    				if(data!=null){
	    				
	    					var s=$("#columnname").val();
	    					var str="<span id='"+data+"'><a  href=\"javascript:void(0);\" onclick=\"showView('"+data+"')\">"+s+"</a>&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick=\"deleteTheColumn('"+data+"')\">删除</a>&nbsp;<a href=\"javascript:void(0);\" onclick=\"updateColumn('"+data+"')\">修改</a></span></br>";
	    		    		
	    		    		
	    		    		$("#existcolumn").append(str);
	    		    	$("#columnids").append(","+data);
	    				}else{
	    					$.messager.alert("提示信息","保存字段失败！","error");
	    				}
	    				$('#editColumn').dialog('destroy');
	    			}
	    		});
	    		
	    		
	    	} 
	    },{ 
	    	text: '取消', 
	    	iconCls: 'icon-cancel', 
	    	handler: function() { 
	    		$('#editColumn').dialog('destroy');
	    	} 
	    }],
	    onClose:function(){
	    	$('#editColumn').dialog('destroy');
	    }
	});
	
	
}
/*查看字段明细*/
function showView(_id){
	var dialogDiv = document.createElement("div");
	dialogDiv.id="editColumn";
	document.body.appendChild(dialogDiv);	
	$('#editColumn').dialog({   
	    modal:true,
	    href:'<%=rootPath %>column/selectirpfromcolumn.action?type=1&columnid='+_id,
	    title:'字段细览',
	    width:400,
	    height:282,
	    resizable:true,
	    buttons: [{
	    	text: '确定', 
	    	iconCls: 'icon-ok', 
	    	handler: function() {
			$('#editColumn').dialog('destroy');	
	    	} 
	    }],
	    onClose:function(){
	    	$('#editColumn').dialog('destroy');
	    }
	});
}
/*更新字段*/
function updateColumn(_id){
	var dialogDiv = document.createElement("div");
	dialogDiv.id="editColumn";
	document.body.appendChild(dialogDiv);	
	$('#editColumn').dialog({   
	    modal:true,
	    href:'<%=rootPath %>column/selectirpfromcolumn.action?columnid='+_id,
	    title:'修改字段',
	    width:400,
	    height:300,
	    resizable:true,
	    buttons: [{
	    	text: '提交', 
	    	iconCls: 'icon-ok', 
	    	handler: function() {
	    		$('#columnForm').form('submit',{
	    			url : "<%=rootPath %>column/updateirpfromcolumn.action",
	    			onSubmit: function(){
	    				var isValid = $(this).form('validate');
	    				if (isValid){
	    					$.messager.progress({
	    	    				text:'提交数据中...'
	    	    			});
	    				}
	    				return isValid;
	    			},
	    			success:function(data){
	    				$.messager.progress('close');
	    				
	    				if(data!=0){
							$.messager.alert("操作提示", "修改成功！","info");
	    				}else{
	    					$.messager.alert("提示信息","修改失败！","error");
	    				}
	    				$('#editColumn').dialog('destroy');
	    			}
	    		});
	    		
	    		
	    	} 
	    },{ 
	    	text: '取消', 
	    	iconCls: 'icon-cancel', 
	    	handler: function() { 
	    		$('#editColumn').dialog('destroy');
	    	} 
	    }],
	    onClose:function(){
	    	$('#editColumn').dialog('destroy');
	    }
	});
}
/*删除字段*/
function deleteTheColumn(_id){
		  $.messager.confirm("操作提示", "您确定要执行删除操作吗？", function (data) {  
            if (data) {  
                $.ajax({
							url: '<%=rootPath%>column/delcolumn.action',
						    async: false,
						    cache: false,
						    data:{
						   columnid:_id
						    },
						    success:function(data){
						    if(data==1){
								$('#'+_id).remove();
							}else{
							$.messager.alert("提示信息","删除字段失败！","error");
							}
						    }
						});  
            }  
        });
		
}
</script>
<form id="addForm" method="post">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cad9ea">
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">表单显示名称：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input name="formname"  validType="isExitformname" class="easyui-validatebox" required="true" missingMessage="请填写表单显示名称" type="text"  />
		
		</td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">表单描述：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input name="formdesc" validType="length[2,500]" class="easyui-validatebox" required="true" missingMessage="请填写表单描述" type="text"  /></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">数据库表名称：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input name="formtablename" validType="isExitformtablename"  class="easyui-validatebox" required="true" type="text" value="<s:property value="irpForm.Formname" />" />
		<font color="#777">*由大写的英文和_组成，以英文开头和结尾</font>
		</td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">表单html描述数据 ：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><textarea  name="formhtml"     validType="length[2,500]"  style="width:90%;height:40px;"></textarea></td>
	</tr>
	<!-- <tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">表单script算法功能 ：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><textarea  name="formscript"     validType="length[2,500]"  style="width:90%;height:40px;"></textarea></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">表单script算法参数 ：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><textarea  name="formparem"     validType="length[2,500]"  style="width:90%;height:40px;"></textarea><br><span style="color:grey">例如：'参数名1','参数名2'</span></td>
	</tr> -->
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">表单发布状态：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input name="formstatus" validType="length[2,60]"   type="radio" value="20" checked="checked" />发布
		<input name="formstatus" validType="length[2,60]"   type="radio" value="10" />不发布</td>
	</tr>
	<!-- <tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">添加字段：</td>
		<td bgcolor="#FFFFFF" class="main_bright"> <a id="btn6" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="createColumn()"></a><br>
		<span id="existcolumn" ></span>
		<input type="text" name="columnids" style="display:none">
		</td>
		
		<span id="columnids" style="display:none"></span>
	</tr>
	 -->
</table>
</form>
</body>
</html>
