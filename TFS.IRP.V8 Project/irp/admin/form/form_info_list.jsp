<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>表单数据</title>
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />
</head>

<body>
<script type="text/javascript">
var tablename = '<s:property value="formtablename"/>';		
var address = '<s:property value="newpage"/>';		
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
		    	roleUserRefresh("<%=rootPath %>form/forminfoview.action?"+queryString);
		    },   
		    menu:'#listSearchType',   
		    prompt:'请输入检索词'  
		});
	});
	
	//分页
	function page(_nPageNum){
		$('#roleUserPageForm').find('input[name="pageNum"]').val(_nPageNum);
		var queryString = $('#roleUserPageForm').serialize();
		roleUserRefresh("<%=rootPath %>form/forminfoview.action?"+queryString);
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
		    				var isValid = $(this).form('validate');
		    				if (isValid){
		    					$.messager.progress({
		    	    				text:'提交数据中...'
		    	    			});
		    				}
		    				return isValid;
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
	function importInfo1(){
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
		dialogDiv.id="importinfo";
		document.body.appendChild(dialogDiv);
		
		$('#importinfo').dialog({
			modal:true,
			href:'<%=rootPath %>'+address,
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
		    				var isValid = $(this).form('validate');
		    				if (isValid){
		    					$.messager.progress({
		    	    				text:'提交数据中...'
		    	    			});
		    				}
		    				return isValid;
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
		$('#roleUserList').find("input:checkbox[name='forminfoid']:checked").each(function(){
		var l=this.value*1;
		ninfoIds+=','+l;
		});
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
		    			url : '<%=rootPath %>form/updateforminfo.action?formtablename='+tablename,
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
	<s:iterator value="irpFormColumnList"> 
    <div data-options="name:'<s:property value="columntablenamecol"/>'"><s:property value="columnname"/>&nbsp;&nbsp;</div>
    </s:iterator>
</div>
 <s:set var="wd" value="irpFormColumnList.size()"></s:set>
<table id="roleUserList" width="100%"  border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
	<tr class="list_th" style="position: relative;">
   	  	<td colspan="3" align="left" style="padding-left: 10px;" nowrap="nowrap">
   	  		<!-- <a href="javascript:void(0)" onclick="importInfo()">添加数据</a> -->
   	  		<s:if test="irpForm.formisdel==10">
   	  		<a href="javascript:void(0)" onclick="importInfo()">添加数据</a>
   	  		<a href="javascript:void(0)" onclick="delFormInfos()">删除数据</a>
			<a href="javascript:void(0)" onclick="roleUserRefresh()">刷新</a>
			<s:iterator value="attacheds" >
			<a  href="<%=rootPath%>file/readfileformexcel.action?srcfile=<s:property value='srcfile'/>&fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>">下载模版</a></s:iterator>
			<a href="javascript:void(0)" onclick="importtotable()">导入数据</a>
			<a href="javascript:void(0)" onclick="exportToZIP()">导出数据</a><a id="daochu"></a>
			</s:if>
   	  	</td>
   	  	<td colspan="<s:property value='#wd'/>" align="right" style="padding-right: 2px;"><input id='listSearchText' /></td>
	</tr>
    <tr>
     <td width="6%" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="checkRoleUserAll()">全选</a></td>
    	<s:iterator value="irpFormColumnList">
    	
    	 <td width="<s:property value="87/#wd"/>%" align="center" bgcolor="#F5FAFE" class="main_bright">
    	  	<a href="javascript:void(0)" onclick="roleUserOrderBy('<s:property value="columntablenamecol"/>','<s:if test="orderField==columntablenamecol&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">
    	  		<s:property value="columnname"/>
    	  	<s:if test="orderField==columntablenamecol&&orderBy=='desc'">
    	  	<img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" />
    	  	</s:if>
    	  	<s:elseif test="orderField==columntablenamecol&&orderBy=='asc'">
    	  	<img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" />
    	  	</s:elseif>
    	  	</a>
    	  </td>
    	</s:iterator>	   
	    <td width="7%" align="center" bgcolor="#F5FAFE"  class="main_bright">操作</td>
    </tr>
    
    
    
    
    
    <s:iterator value="formInfoList" var="list" status="listStat">
    <tr>
	    <td align="center" bgcolor="#F5FAFE" class="main_bleft"><input type="checkbox" name="forminfoid"  value="<s:property value="FORMINFOID" />" /> <s:property value="(pageNum-1)*pageSize+#listStat.count"/></td>	     
	     	<s:iterator value="irpFormColumnList">
	     	<s:iterator  value="#list" status="listststsu">
	     	<s:if test="#listststsu.first==false">
	     		<s:if test="key.indexOf(columntablenamecol)!=-1">
			     	<td bgcolor="#F5FAFE"  class="main_bright"> 
			     		<s:property value="value"/>
			     	</td>
	     		</s:if>
	     	</s:if>
	     	</s:iterator>
	     	</s:iterator>   
	     <td align="center" valign="middle" bgcolor="#F5FAFE"  class="main_bright">
	     <img border="0" src="images/icons/pencil.png" title="修改" style="cursor:pointer; margin: 0 5px;" onclick="formInfoEdit(<s:property value="FORMINFOID" />)" />
	     <img border="0" src="images/icons/cancel.png" title="删除" style="cursor:pointer; margin: 0 5px;" onclick="delFormInfo(<s:property value="FORMINFOID" />)"/>
	     </td>
    </tr>
    </s:iterator>
    <tr bgcolor="#FFFFFF">
		<td colspan="<s:property value='#wd+2'/>" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
	</tr>
</table>
</body>
</html>
