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
<title>表单字段修改</title>
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />
</head>

<body>
<script type="text/javascript">
//全选
function checkAll(){
	m_checked = !m_checked;
	$('#columnList').find("input:checkbox[name='columnid']").attr("checked",m_checked); 
}


function updateColumn1(){
	
	var columnid = new Array();
	var displaytypevalue = new Array();
	var displaytype = new Array();
	var columnname = new Array();
	var columntablenamecol = new Array();
	var columndesc = new Array();
	var columnlongtext = new Array();
	var columndefval = new Array();
	var columntype = new Array();
$('#columnList').find("input[name='columnname']").each(function(){
	columnname+=','+this.value;
		});
$('#columnList').find("input[name='columnid']").each(function(){
	columnid+=','+this.value;
		});
$('#columnList').find("input[name='columndesc']").each(function(){
	columndesc+=','+this.value;
		});
$('#columnList').find("input[name='columntablenamecol']").each(function(){
	columntablenamecol+=','+this.value;
		});
$('#columnList').find("input[name='columnlongtext']").each(function(){
	if(this.value==""||this.value==null){
		columnlongtext+=','+"blank";
	}else{
		
	columnlongtext+=','+this.value;
	}
		});
$('#columnList').find("input[name='columndefval']").each(function(){
	columndefval+=','+this.value;
		});
$('#columnList').find("input[name='displaytypevalue']").each(function(){
	if(this.value==""||this.value==null){
		displaytypevalue+=','+"blank";
	}else{
		
	displaytypevalue+=','+this.value;
	}
		});
$('#columnList').find("select[name='columntype']").each(function(){
	columntype+=','+this.value;
	
		});
$('#columnList').find("select[name='displaytype']").each(function(){
	displaytype+=','+this.value;
	
		});




$("#columntype").val(columntype.substring(1));
$("#columndefval").val(columndefval.substring(1));
$("#columnlongtext").val(columnlongtext.substring(1));
$("#columntablenamecol").val(columntablenamecol.substring(1));
$("#columnname").val(columnname.substring(1));
$("#columnid").val(columnid.substring(1));
$("#columndesc").val(columndesc.substring(1));
$("#displaytype5").val(displaytype.substring(1));
$("#displaytypevalue").val(displaytypevalue.substring(1));
	
	
	
}

function delColumn(){
	
	var sRoleIds = "";
	var length=$('#columnList').find("input:checkbox[name='columnid']:checked").length;
	
	if(length>0){
		$.messager.confirm('提示信息','是否要删除这些字段？',function(r){   
		    if(r){
	$('#columnList').find("input:checkbox[name='columnid']:checked").each(function(){
		
		$("#"+this.value).css("display","none");
		var s=this.value;
		
	var sw=s.substring(1,2);
	if(sw=="a"){
		
	}else{
		sRoleIds+=','+this.value;
	}
		
		});
	   $("#columnids_update").val(sRoleIds.substring(1));
	}
		    	
		    
		});
	}else{
		$.messager.alert("提示信息","请选择要删除的字段。","warning");
	}
	
}
/*新增新的字段*/
function newCreateColumn(){
	var _name='<s:property value='irpForm.formtablename'/>';
	var s=$("#columnTablename").val();
	if(s!=""){
		_name=s;
		
	}
		var dialogDiv = document.createElement("div");
		
		dialogDiv.id="editColumn";
		document.body.appendChild(dialogDiv);	
		$('#editColumn').dialog({   
		    modal:true,
		    href:'<%=rootPath %>form/column_add.action?formType=10&formtablename='+_name,
		    title:'新建字段',
		    width:600,
		    height:400,
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
		    					refresh1(_name);
		    			
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
/*新增新的字段不保存到数据库里面*/
function CreateColumnTwo(){
	var length=$('#columnList').find("input[name='columnid']").length;
	//var columnname=$('#columnList').find("tr:last td:last").html();
	//alert(columnname);
	var checkboxId=length+1;
	var id="aa"+checkboxId;
 
	var str=" ";
	str+="<tr id="+id+">";
	str+=" <td align=\"center\"   width=\"16%\" bgcolor=\"#F5FAFE\"  class=\"main_bleft\"><input type=\"checkbox\" name=\"columnid\" value="+id+" />"+checkboxId+" </td>";
	  
	str+=" <td width=\"10%\"  onchange=\"updateColumn1()\"  bgcolor=\"#F5FAFE\"  class=\"main_bright\" ><input type='text'  name=\"columnname\" style=\"width:40px;\"/></td>";
	str+=" <td width=\"10%\"  onchange=\"updateColumn1()\"  bgcolor=\"#F5FAFE\"  class=\"main_bright\" ><input type='text'  name=\"columntablenamecol\" style=\"width:40px;\"/></td>";
	str+=" <td  width=\"10%\"   onchange=\"updateColumn1()\"  bgcolor=\"#F5FAFE\"  class=\"main_bright\" ><input type='text' name=\"columndesc\" style=\"width:40px;\"/></td>";
	str+="  <td width=\"10%\"  onchange=\"updateColumn1()\"  bgcolor=\"#F5FAFE\"  class=\"main_bright\" ><select name=\"columntype\" id=\"columntype5\">";
						
	str+="	<option value=\"整型\" >整型</option>";
	str+="		<option value=\"字符串\"  >字符串</option>";
	str+="		<option value=\"时间\" >时间</option>";
	str+="			<option value=\"大字段\" >大字段</option>";
	str+="		</select></td>";
		str+="<td width=\"10%\"  onchange=\"updateColumn1()\"  bgcolor=\"#F5FAFE\"  class=\"main_bright\"><input type='text'  name=\"columnlongtext\" style=\"width:40px;\"/></td>";
		str+=" <td  width=\"10%\"  onchange=\"updateColumn1()\"   bgcolor=\"#F5FAFE\"  class=\"main_bright\"><input type='text'  name=\"columndefval\" style=\"width:40px;\"/></td>";
		str+="<td width=\"20%\" bgcolor=\"#F5FAFE\" onchange=\"updateColumn1()\" class=\"main_bright\">";
		str+="<select style=\"width:100px;\" name=\"displaytype\" id=\"displaytype\">";
						
		str+="		<option   value=\"text\">文本框</option>";
		str+="       <option  value=\"password\">密码</option>";
		str+="            <option  value=\"radio\">单选框</option>";
		str+="             <option value=\"checkbox\">多选</option>";
		str+="                <option  value=\"date\">日期(yyyy-MM-dd)</option>";
		str+="          <option  value=\"datetime\">日期（yyyy-MM-dd HH:mm:ss）</option>";
		str+="             <option  value=\"file\">文件</option>";
		str+="         <option  value=\"textarea\">多行文本</option>";
		str+="       <option value=\"list\">下拉框</option>";
                 
		str+="     <option  value=\"image\">图片</option>";
                
						
		str+="	</select></td>	";			
			str+="<td width=\"10%\" bgcolor=\"#F5FAFE\" onchange=\"updateColumn1()\" class=\"main_bright\"><input type='text' name=\"displaytypevalue\" style=\"width:40px;\"/></td></tr>";
	
	$("#content").before(str);
}
//刷新
function refresh1(_name){
	var s=$("#columnTablename").val();
	if(s!=""){
		_name=s;
		
	}
	$.ajax({
		url: "<%=rootPath %>form/refreshcolumn.action",
	   	type: "POST",
	   	data: {
	   		formtablename:_name
	   	},
		success:function(html){
			if(html!=null){
				$('#columnList').html(html);
			}
		}
	});
	
}
</script>

<table width="100%" id="columnList" border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
	<tr  style="position: relative;">
   	  	<td colspan="10" align="left" style="padding-left: 10px;" >
   	  		
   	  		<a href="javascript:void(0)" onclick="CreateColumnTwo()">新增</a>
   	  		<a href="javascript:void(0)" onclick="delColumn()">删除</a>
   	  		<a href="javascript:void(0)" onclick="refresh1('<s:property value='irpForm.formtablename'/>')">刷新</a>
   	  		<input type="hidden" id="columnTablename" value="${formtablename}">
   	  	</td>
   	  
	</tr>
    <tr>
	    <td width="10%" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="checkAll()">全选</a></td>
	    <td width="10%" align="center" bgcolor="#F5FAFE" class="main_bright">字段显示名称</td>
	    <td width="10%" align="center" bgcolor="#F5FAFE" class="main_bright">字段名</td>
	    <td width="10%" align="center" bgcolor="#F5FAFE" class="main_bright">字段描述</td>
	    <td width="10%" align="center" bgcolor="#F5FAFE" class="main_bright">字段类型</td>
	    <td width="10%" align="center" bgcolor="#F5FAFE" class="main_bright">字段长度</td>
	    <td width="10%" align="center" bgcolor="#F5FAFE" class="main_bright">默认值</td>
	    <td width="20%" align="center" bgcolor="#F5FAFE" class="main_bright">显示类型</td>
	    <td width="10%" align="center" bgcolor="#F5FAFE" class="main_bright">类型初始值</td>
	 
	    
    </tr>
   
    <s:iterator value="irpFormColumns" status="listStat">
    <tr id="<s:property value="columnid" />">
	    <td align="center" bgcolor="#F5FAFE"  class="main_bleft"><input type="checkbox" name="columnid" value="<s:property value="columnid" />" /> <s:property value="(pageNum-1)*pageSize+#listStat.count"/></td>
  
	    <td  onchange="updateColumn1()" bgcolor="#F5FAFE"  class="main_bright"><input type='text' value='<s:property value="columnname" />' name="columnname" style="width:40px;"/></td>
	    <td  onchange="updateColumn1()"  bgcolor="#F5FAFE"  class="main_bright"><input type='text' value='<s:property value="columntablenamecol"  />' name="columntablenamecol" style="width:40px;"/></td>
	    <td  onchange="updateColumn1()"  bgcolor="#F5FAFE"  class="main_bright"><input type='text' value='<s:property value="columndesc" />' name="columndesc" style="width:40px;"/></td>
	    <td  onchange="updateColumn1()"  bgcolor="#F5FAFE"  class="main_bright" ><select name="columntype" id="columntype5">
						
						<option value="整型" <s:if test="columntype in inttype">selected="selected"</s:if>>整型</option>
						<option value="字符串"  <s:if test="columntype in stringtype">selected="selected"</s:if>>字符串</option>
						<option value="时间" <s:if test="columntype in datetype">selected="selected"</s:if>>时间</option>
							<option value="大字段" <s:if test="columntype=='CLOB'">selected="selected"</s:if>>大字段</option>
					</select></td>
	    <td  onchange="updateColumn1()"  bgcolor="#F5FAFE"  class="main_bright"><input type='text' value='<s:property value="columnlongtext" />' name="columnlongtext" style="width:40px;"/></td>
	    <td  onchange="updateColumn1()"   bgcolor="#F5FAFE"  class="main_bright"><input type='text' value='<s:property value="columndefval" />' name="columndefval" style="width:40px;"/></td>
	   	
		<td bgcolor="#F5FAFE" onchange="updateColumn1()" class="main_bright">
		<select style="width:100px;"  name="displaytype"  id="displaytype">
						
						<option <s:if test="irpFormColumn.displaytype=='text'">selected="selected"</s:if> value="text">文本框</option>
                        <option <s:if test="irpFormColumn.displaytype=='password'">selected="selected"</s:if> value="password">密码</option>
                        <option  <s:if test="irpFormColumn.displaytype=='radio'">selected="selected"</s:if> value="radio">单选框</option>
                         <option  <s:if test="irpFormColumn.displaytype=='checkbox'">selected="selected"</s:if> value="checkbox">多选</option>
                           <option  <s:if test="irpFormColumn.displaytype==date'">selected="selected"</s:if> value="date">日期(yyyy-MM-dd)</option>
                      <option  <s:if test="irpFormColumn.displaytype=='datetime'">selected="selected"</s:if> value="datetime">日期（yyyy-MM-dd HH:mm:ss）</option>
                        <option  <s:if test="irpFormColumn.displaytype=='file'">selected="selected"</s:if> value="file">文件</option>
                    <option  <s:if test="irpFormColumn.displaytype=='textarea'">selected="selected"</s:if> value="textarea">多行文本</option>
                   <option  <s:if test="irpFormColumn.displaytype=='list'">selected="selected"</s:if> value="list">下拉框</option>
                 
                   <option  <s:if test="irpFormColumn.displaytype=='image'">selected="selected"</s:if> value="image">图片</option>
                
						
					</select></td>				
		<td bgcolor="#F5FAFE" onchange="updateColumn1()" class="main_bright"><input type='text' value='<s:property value="displaytypevalue" />' name="displaytypevalue" style="width:40px;"/></td>
	

	 
    </tr>
    </s:iterator>
    <tr id="content"></tr>
    
     
 
</table>
</body>
</html>
