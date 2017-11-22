<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
java.util.UUID uuid  = java.util.UUID.randomUUID();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>表单数据</title>
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />

<body>

<script type="text/javascript">
var tablename = '<s:property value="formtablename"/>';		
var m_roleChecked = false;
	$(function(){
		$('#listSearchText').searchbox({
			width:220,
		    searcher:function(value,name){   
		    	$('#roleUserPageForm').find('input[name="searchWord"]').val(value);
		    	$('#roleUserPageForm').find('input[name="searchType"]').val(name);
		    	$('#roleUserPageForm').find('input[name="pageNum"]').val('1');
		    	$('#roleUserPageForm').find('input[name="orderField"]').val('');
		    	$('#roleUserPageForm').find('input[name="orderBy"]').val('');
		    	var queryString = $('#roleUserPageForm').serialize();
		    	roleUserRefresh("<%=rootPath %>form/forminfocolumn.action?"+queryString);
		    },   
		    menu:'#listSearchType',   
		    prompt:'请输入检索词'  
		});
	});
	
	//分页
	function page(_nPageNum){
		$('#roleUserPageForm').find('input[name="pageNum"]').val(_nPageNum);
		var queryString = $('#roleUserPageForm').serialize();
		roleUserRefresh("<%=rootPath %>form/forminfocolumn.action?"+queryString);
	}
	//排序
	function roleUserOrderBy(_sFiled,_sOrderBy){
		$('#roleUserPageForm').find('input[name="orderField"]').val(_sFiled);
		$('#roleUserPageForm').find('input[name="orderBy"]').val(_sOrderBy);
		var queryString = $('#roleUserPageForm').serialize();
		roleUserRefresh("<%=rootPath %>form/forminfoview.action?"+queryString);
	}
	
	//全选
	function checkRoleUserAll(){
		m_roleChecked = !m_roleChecked;
		$('#roleUserList').find("input:checkbox[name='forminfoid']").attr("checked",m_roleChecked);
	}
	
	//刷新
	function roleUserRefresh(){
		$('#roleTabs').tabs('getSelected').panel('refresh');
	}
	
	//刷新到一个地址
	function roleUserRefresh(_sUrl){
		$('#roleTabs').tabs('getSelected').panel('refresh',_sUrl);
	}
	/*增加字段*/
function createColumn(){
	var dialogDiv = document.createElement("div");
	dialogDiv.id="editColumn";
	document.body.appendChild(dialogDiv);	
	$('#editColumn').dialog({   
	    modal:true,
	    href:'<%=rootPath %>form/column_add.action?initname='+tablename+'&formType=20',
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
	    				$('#editColumn').dialog('destroy');
	    				$.messager.progress('close');
	    				roleUserRefresh();
	    				if(data!=null){
	    					copyinfoaddjsp(tablename);
	    					var s=$("#columnname").val();
/* 	    					var str="<span id='"+data+"'><a  href=\"javascript:void(0);\" onclick=\"showView('"+data+"')\">"+s+"</a>&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick=\"deleteTheColumn('"+data+"')\">删除</a>&nbsp;<a href=\"javascript:void(0);\" onclick=\"updateColumn('"+data+"')\">修改</a></span></br>";
 */	    		    		
	    		    		
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
function delColumn(){
	
	var sRoleIds = "";
	$('#roleUserList').find("input:checkbox[name='forminfoid']:checked").each(function(){sRoleIds+=','+this.value;});
	
	if(sRoleIds){
		sRoleIds = sRoleIds.substring(1);
		$.messager.confirm('提示信息','是否要删除这些字段？',function(r){   
		    if(r){
		    	$.messager.progress({
	    				text:'提交数据中...'
	    			});
	    		$.ajax({
   	    				url: "<%=rootPath %>form/delete_column_to.action",
   	    			   	type: "POST",
   	    			   	data: {
   	    			   	columnids: sRoleIds
   	    			   	},
   	    			   	success: function(msg){
   	    			   		$.messager.progress('close');
   	    			   		copyinfoaddjsp(tablename);
   	    			   		$.messager.alert("提示信息","成功删除了"+msg+"个字段","info");
   	    			   		roleUserRefresh();
   	    			   	},
   	    			 	error:function(){
	    			 		$.messager.progress('close');
	    			 		$.messager.alert('提示信息','删除字段失败！','error');
	    			 	}
   		    		});
				} 
		});
	}else{
		$.messager.alert("提示信息","请选择要删除的字段。","warning");
	}
	
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
								roleUserRefresh();
							}else{
							$.messager.alert("提示信息","删除字段失败！","error");
							}
						    }
						});  
            }  
        });
		
}
/*修改字段*/
function updateColumn(_id){
	var dialogDiv = document.createElement("div");
	dialogDiv.id="editColumn";
	document.body.appendChild(dialogDiv);	
	$('#editColumn').dialog({   
	    modal:true,
	    href:'<%=rootPath %>column/selectirpfromcolumn.action?columnid='+_id,
	    title:'修改字段',
	    width:700,
	    height:470,
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
	    				roleUserRefresh();
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


/*修改字段部分*/
function updateColumnSome(_id){
	var dialogDiv = document.createElement("div");
	dialogDiv.id="editColumnSome";
	document.body.appendChild(dialogDiv);	
	$('#editColumnSome').dialog({   
	    modal:true,
	    href:'<%=rootPath %>column/updatecolumn_some.action?columnid='+_id,
	    title:'修改字段',
	    width:700,
	    height:470,
	    resizable:true,
	    buttons: [{
	    	text: '提交', 
	    	iconCls: 'icon-ok', 
	    	handler: function() {
	    		$('#columnFormSome').form('submit',{
	    			url : "<%=rootPath %>column/updateirpfromcolumnSome.action",
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
	    				roleUserRefresh();
	    				if(data!=0){
							$.messager.alert("操作提示", "修改成功！","info");
	    				}else{
	    					$.messager.alert("提示信息","修改失败！","error");
	    				}
	    				$('#editColumnSome').dialog('destroy');
	    			}
	    		});
	    		
	    		
	    	} 
	    },{ 
	    	text: '取消', 
	    	iconCls: 'icon-cancel', 
	    	handler: function() { 
	    		$('#editColumnSome').dialog('destroy');
	    	} 
	    }],
	    onClose:function(){
	    	$('#editColumnSome').dialog('destroy');
	    }
	});
}
//查看字段详情
	function columnView(_id){
		var dialogDiv = document.createElement("div");
		dialogDiv.id="columnview";
		document.body.appendChild(dialogDiv);
		
		$('#columnview').dialog({   
		    modal:true,
		    href:'<%=rootPath %>column/selectirpfromcolumn.action?type=1&&columnid='+_id,
		    title:'字段明细',
		    width:500,
		    height:532,
		    resizable:true,
		    buttons: [{ 
		   	 text:'关闭',
			 iconCls:'icon-cancel',
			 handler:function()
			 {
			$('#columnview').dialog('destroy');	 
			 }
		    }],
		    onClose:function(){
		    	$('#columnview').dialog('destroy');
		    }
		});
		
		
		
	}
	//添加表单数据
	function importInfo(){
		var count = $.ajax({
			type:'post',
			url:'<%=rootPath %>form/countcolumn.action?formtablename='+tablename,
			async: false,
			cache: false
		}).responseText;
		var dheight=225;
		if(count>0){
		dheight = count*70;
		if(dheight>400){
		dheight=400;
		}else if(dheight<=70){
		dheight=120;
		}
		}
		var dialogDiv = document.createElement("div");
		dialogDiv.id="importinfo";
		document.body.appendChild(dialogDiv);
		$('#importinfo').dialog({
			modal:true,
			href:'<%=rootPath %>form/toaddforminfopage.action?formtablename='+tablename,
			title:'添加数据',
			resizable:true,
		    width:350,
		    height:dheight,
		    buttons: [{
		    	text: '提交', 
		    	iconCls: 'icon-ok', 
		    	handler: function() {
		    		$('#addForm').form('submit',{
		    			url : '<%=rootPath %>form/addforminfo.action?formtablename='+tablename,
		    			onSubmit: function(){
		    				//var isValid = $(this).form('validate');
		    				//if (isValid){
		    					$.messager.progress({
		    	    				text:'提交数据中...'
		    	    			});
		    				//}
		    				//return isValid;
		    			},
		    			success:function(data){
		    				$('#importinfo').dialog('destroy');
		    				$.messager.progress('close');
		    				if(data>0){
		    					roleUserRefresh();
		    				}else{
		    					$.messager.alert("提示信息","添加表单数据失败！","error");
		    				}
		    			}
		    		});
		    	} 
		    	},{ 
		    	text: '取消', 
		    	iconCls: 'icon-cancel', 
		    	handler: function() { 
		    		$('#importinfo').dialog('destroy');
		    	} 
		    }],
		    onClose:function(){
		    	$('#importinfo').dialog('destroy');
		    }
		});
	}
	
	//删除数据
	function delFormInfos(){
		var ninfoIds = "";
		$('#roleUserList').find("input:checkbox[name='forminfoid']:checked").each(function(){ninfoIds+=','+this.value;});
		if(ninfoIds){
			ninfoIds = ninfoIds.substring(1);
			$.messager.confirm('提示信息','是否要删除这些数据？',function(r){   
    		    if(r){
   		    		$.ajax({
   	    				url: "<%=rootPath %>form/delforminfos.action",
   	    			   	type: "POST",
   	    			   	data: {
   	    			   		formid: ninfoIds,
   	    			   		formtablename: tablename
   	    			   	},
   	    			   	success: function(msg){
   	    			   		$.messager.alert("提示信息","成功删除 了"+msg+"条数据","info");
   	    			   		roleUserRefresh();
   	    			   	}
   		    		});
    		    }
    		});
		}else{
			$.messager.alert("提示信息","请选择要删除的数据","warning");
		}
	}
	function delFormInfo(_forminfoid){
			$.messager.confirm('提示信息','是否要删除这条数据？',function(r){   
    		    if(r){
   		    		$.ajax({
   	    				url: "<%=rootPath %>form/delforminfo.action",
   	    			   	type: "POST",
   	    			   	data: {
   	    			   		formid: _forminfoid,
   	    			   		formtablename: tablename
   	    			   	},
   	    			   	success: function(msg){
   	    			   		$.messager.alert("提示信息","成功删除了这条数据","info");
   	    			   		roleUserRefresh();
   	    			   	}
   		    		});
    		    }
    		});
	}
	function formInfoEdit(_forminfoid){
		var count = $.ajax({
			type:'post',
			url:'<%=rootPath %>form/countcolumn.action?formtablename='+tablename,
			async: false,
			cache: false
		}).responseText;
		var dheight=225;
		if(count>0){
		dheight = count*60;
		if(dheight>400){
		dheight=400;
		}
		}
		var dialogDiv = document.createElement("div");
		dialogDiv.id="editinfo";
		document.body.appendChild(dialogDiv);
		$('#editinfo').dialog({
			modal:true,
			href:'<%=rootPath %>form/tofindobjetpage.action?formtablename='+tablename+'&formid='+_forminfoid,
			title:'数据修改',
			resizable:true,
		    width:350,
		    height:dheight,
		    buttons: [{
		    	text: '提交', 
		    	iconCls: 'icon-ok', 
		    	handler: function() {
		    		$('#editForm').form('submit',{
		    			url : '<%=rootPath %>form/updateforminfo.action?formtablename='+tablename+'&formid='+_forminfoid,
		    			onSubmit: function(){
		    				//var isValid = $(this).form('validate');
		    				//if (isValid){
		    					$.messager.progress({
		    	    				text:'提交数据中...'
		    	    			});
		    				//}
		    				//return isValid;
		    			},
		    			success:function(data){
		    				$('#editinfo').dialog('destroy');
		    				$.messager.progress('close');
		    				if(data==1){
		    					roleUserRefresh();
		    				}else{
		    					$.messager.alert("提示信息","修改表单数据失败！","error");
		    				}
		    			}
		    		});
		    	} 
		    	},{ 
		    	text: '取消', 
		    	iconCls: 'icon-cancel', 
		    	handler: function() { 
		    		$('#editinfo').dialog('destroy');
		    	} 
		    }],
		    onClose:function(){
		    	$('#editinfo').dialog('destroy');
		    }
		});
	}
	//导出数据
	function exportToZIP(){
		
		
	}
	//导入数据
	function importtotable(){
		
	}
	function showTabsData(_link){
		var _tableName = _link.getAttribute("_tableName");
		var tableName=$('.tabs-selected').text();
		var sRoleName = tableName+"数据";
		var bExist = $('#roleTabs').tabs('exists', sRoleName);
		if(bExist){
			$('#roleTabs').tabs('select',sRoleName);
		}else{
			$('#roleTabs').tabs('add',{
				title:sRoleName,
				closable:true,
				fit:true,
				style:{
					overflow:'auto',
					padding:'5px'
				}
			});
			var panel = $('#roleTabs').tabs('getSelected');
			panel.attr('link','<%=rootPath %>form/forminfoview.action?columninliststatus=10&formtablename='+_tableName);
			panel.panel('refresh',panel.attr('link'));
		}
	}
	function copyinfoaddjsp(tablename){
		//if(randomdata==2){
			randomdata=Math.random();
		//}
		var url=$.ajax({
		 				url: '<%=rootPath %>form/toaddforminfopage.action?formtablename='+tablename,
		  			   	type: 'POST',
		  			   	async: false,
						cache: false
		   		}).responseText;
		   		//alert(url);
		   		var now=new Date();
				var date=1900+now.getYear()+""+(now.getMonth()+1)+""+now.getDate()+""+now.getHours()+""+now.getMinutes()+""+now.getSeconds();
				var fileName=date;
				$.ajax({
		 				url: "<%=rootPath %>sheet/copyjsp.action",
		  			   	type: "POST",
		  			   	data:{content:url,formtablename:tablename,randomdata:randomdata},
		  			   	success: function(data){
		  			   	if(data!=0){
							
		  			   	}
		  			   	}
		   		});
	}
	/*新建表单*/
	function createTabs(tablename){
	$.messager.confirm('提示信息','是否要在数据库创建表单？',function(r){   
	    if(r){
		$.ajax({
   			url: "<%=rootPath %>form/createtables.action",
   			type: "POST",
   			data: {
   				formtablename: tablename
   			},
   			success:function(data){
   				roleUserRefresh();
		    	if(data==1){
		    		//copyinfoaddjsp(tablename);
		    		$.messager.alert("提示信息","创建数据表成功！","info");
		    	}else{
		    		$.messager.alert("提示信息","创建数据表失败！","error");
		    	};
		    }
   		});
   		}
   		})
	}
</script>
<form id="roleUserPageForm">
<s:hidden name="searchWord" id="searchWord" />
<s:hidden name="searchType" id="searchType" />
<s:hidden name="formtablename" id="formtablename" />
<s:hidden name="pageNum" id="pageNum" />
<s:hidden name="pageSize" id="pageSize" />
<s:hidden name="orderField" id="orderField" />
<s:hidden name="orderBy" id="orderBy" />
</form>
<div id="listSearchType" style="width:120px;"> 
<div data-options="name:'all'">全部&nbsp;&nbsp;</div>
	<div data-options="name:'columnname'">字段显示名称</div>
    <div data-options="name:'columnnamecol'">字段名</div>
    <div data-options="name:'columndesc'">字段描述</div>
    <div data-options="name:'columntype'">字段类型</div>
    <div data-options="name:'displaytype'">字段显示类型</div>
</div>
<table id="roleUserList" width="100%"  border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
	<tr class="list_th" style="position: relative;">
   	  	<td colspan="3" align="left" style="padding-left: 10px;" nowrap="nowrap">
   	  		<!-- <s:if test="tableIsExit==1"> -->
   	  		<!-- </s:if> -->
   	  		<s:if test="irpForm.formisdel==10">
	   	  		<a href="javascript:void(0)" onclick="createColumn()">添加字段</a>
	   	  		<a href="javascript:void(0)" onclick="delColumn()">删除字段</a>
				<a href="javascript:void(0)" onclick="roleUserRefresh()">刷新</a>
				<s:if test="tableIsExit==1">
				<a href="javascript:void(0)" onclick="createTabs('<s:property value="columntablaname" />')" >生成数据表</a>
				</s:if>
			</s:if>
			<s:if test="tableIsExit==0">
<%-- 			<a href="javascript:void(0)" onclick="showTabs('<s:property value="columntablaname" />','<s:property value="isnewpage"/>')" _tableName="">查看数据表</a>
 --%>			<a href="javascript:void(0)" onclick="showTabsData(this)" _tableName="<s:property value="columntablaname" />">查看数据表</a>
			</s:if>
   	  	</td>
   	  	<td colspan="6" align="right" style="padding-right: 2px;"><input id='listSearchText' /></td>
	</tr>
	 <tr>
     	<td width="6%" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="checkRoleUserAll()">全选</a></td>
     	<td width="15%" align="center" bgcolor="#F5FAFE" class="main_bright">字段显示名称</td>  
    	<td width="12%" align="center" bgcolor="#F5FAFE" class="main_bright">字段名</td>  
    	<td width="12%" align="center" bgcolor="#F5FAFE" class="main_bright">字段描述</td>  
    	<td width="12%" align="center" bgcolor="#F5FAFE" class="main_bright">字段类型</td>  
    	<td width="12%" align="center" bgcolor="#F5FAFE" class="main_bright">字段长度</td>  
    	<td width="10%" align="center" bgcolor="#F5FAFE" class="main_bright">默认值</td>  
    	<td width="10%" align="center" bgcolor="#F5FAFE" class="main_bright">显示类型</td>  
	    <td width="10%" align="center" bgcolor="#F5FAFE"  class="main_bright">操作</td>
    </tr>
    
     <s:set var="wd" value="irpFormColumnList.size()"></s:set>
    	<s:iterator value="irpFormColumnList" status="listStat">
    	<tr>
    	<td align="center" bgcolor="#F5FAFE" class="main_bleft"><input type="checkbox" name="forminfoid"  value="<s:property value="columnid" />" /> <s:property value="(pageNum-1)*pageSize+#listStat.count"/></td>
    	<td bgcolor="#F5FAFE"  class="main_bright"><s:property value="columnname" /></td>
    	<td bgcolor="#F5FAFE"  class="main_bright"><s:property value="columntablenamecol" /></td>
    	<td bgcolor="#F5FAFE"  class="main_bright"><s:property value="columndesc" /></td>
    	<td bgcolor="#F5FAFE"  class="main_bright"><s:property value="columntype" /></td>
    	<td bgcolor="#F5FAFE"  class="main_bright"><s:property value="columnlongtext" /></td>
    	<td bgcolor="#F5FAFE"  class="main_bright"><s:property value="columnassignkey" /></td>
    	<td bgcolor="#F5FAFE"  class="main_bright"><s:property value="displaytype" /></td>
    	<td align="center" valign="middle" bgcolor="#F5FAFE"  class="main_bright">
	    	<s:if test="irpForm.formisdel==10">
		    	<s:if test="tableIsExit==1">
		    	<img border="0" src="images/icons/pencil.png" title="修改" style="cursor:pointer; margin: 0 5px;" onclick="updateColumn(<s:property value="columnid" />)" />
		    	<img border="0" src="images/icons/cancel.png" title="删除" style="cursor:pointer; margin: 0 5px;" onclick="deleteTheColumn(<s:property value="columnid" />)"/>
		    	</s:if><s:else>
		    		<img border="0" src="images/icons/pencil.png" title="修改" style="cursor:pointer; margin: 0 5px;" onclick="updateColumnSome(<s:property value="columnid" />)" />
		    	</s:else>
	    	 </s:if> 
	    		<img border="0" src="<%=rootPath%>client/images/view.gif" title="查看" style="width:15px;height:15px;cursor:pointer; margin: 0 5px;" onclick="columnView(<s:property value="columnid" />)" />
    	</td>
    	 </tr>
    	</s:iterator>	   
   
    

    <tr bgcolor="#FFFFFF">
		<td colspan="9" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
	</tr>
</table>
</body>
</html>
