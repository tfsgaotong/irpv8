<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	//全局变量，
	var m_checked = false;
	
	//分页
	function page(_nPageNum){
		$('#pageNum').val(_nPageNum);
		var queryString = $('#pageForm').serialize();
		refresh("<%=rootPath %>form/form_list.action?"+queryString);
	}
	//排序
	function orderBy(_sFiled,_sOrderBy){
		$('#orderField').val(_sFiled);
		$('#orderBy').val(_sOrderBy);
		var queryString = $('#pageForm').serialize();
		refresh("<%=rootPath %>form/form_list.action?"+queryString);
	}
	
	//全选
	function checkAll(){
		m_checked = !m_checked;
		$('#roleList').find("input:checkbox[name='formid']").attr("checked",m_checked); 
	}
	
	//刷新
	function refreshNoUrl(){
		$('#deleteForm').panel('refresh');
	}
	
	//刷新到一个地址
	function refresh(_sUrl){
		$('#deleteForm').panel('refresh',_sUrl);
	}
	
	//恢复表单
	function recovery(){
		var sRoleIds = "";
		$('#roleList').find("input:checkbox[name='formid']:checked").each(function(){sRoleIds+=','+this.value;});
		if(sRoleIds){
			sRoleIds = sRoleIds.substring(1);
			$.messager.confirm('提示信息','是否要恢复这些表单？',function(r){   
    		    if(r){
    		    	$.messager.progress({
	    				text:'提交数据中...'
	    			});
   		    		$.ajax({
   	    				url: "<%=rootPath %>form/recoverform.action",
   	    			   	type: "POST",
   	    			   	data: {
   	    			 	columnids: sRoleIds
   	    			   	},
   	    			   	success: function(msg){
   	    			   		$.messager.progress('close');
   	    			   		$.messager.alert("提示信息","成功恢复了"+msg+"个表单","info");
   	    			   	    refreshNoUrl();
   	    			   	},
   	    			 	error:function(){
	    			 		$.messager.progress('close');
	    			 		$.messager.alert('提示信息','恢复表单失败！','error');
	    			 	}
   		    		});
    		    }
    		});
		}else{
			$.messager.alert("提示信息","请选择要恢复的表单。","warning");
		}
	}
	
	
	//显示角色用户的Tabs页
	function showTabs(_link){
		var _tableName = _link.getAttribute("_tableName");
		var sRoleName = _link.text;
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
			<%-- panel.attr('link','<%=rootPath %>form/forminfoview.action?formtablename='+_tableName); --%>
				panel.attr('link','<%=rootPath %>form/forminfocolumn.action?formtablename='+_tableName);
			panel.panel('refresh',panel.attr('link'));
		}
	}
	
	//删除选择的表单
	function realDelete(){
		var sRoleIds = "";
		$('#roleList').find("input:checkbox[name='formid']:checked").each(function(){sRoleIds+=','+this.value;});
		if(sRoleIds){
			sRoleIds = sRoleIds.substring(1);
			$.messager.confirm('提示信息','是否要删除这些表单？',function(r){   
    		    if(r){
    		    	$.messager.progress({
	    				text:'提交数据中...'
	    			});
   		    		$.ajax({
   	    				url: "<%=rootPath %>form/realdelete.action",
   	    			   	type: "POST",
   	    			   	data: {
   	    			   	columnids: sRoleIds
   	    			   	},
   	    			   	success: function(msg){
   	    			   		
   	    			   		$.messager.progress('close');
   	    			   		
   	    			   			
   	    			   		$.messager.alert("提示信息","成功删除了不含有数据的表单","info");
   	    			   	     refreshNoUrl();
   	    			   		
   	    			   	},
   	    			 	error:function(){
	    			 		$.messager.progress('close');
	    			 		$.messager.alert('提示信息','删除数据失败！','error');
	    			 	}
   		    		});
    		    }
    		});
		}else{
			$.messager.alert("提示信息","请选择要删除的表单。","warning");
		}
	}
	
//单个删除操作
function deleteRowForm(_id){
	$.messager.confirm('提示信息','是否要删除这条数据？',function(r){   
	    if(r){
	    		$.ajax({
   				url: "<%=rootPath %>form/realdelete.action",
   			   	type: "POST",
   			   	data: {
   			   	columnids: _id
   			   		
   			   	},
   			   	success: function(msg){
   			   		
   			if(msg=="-2"){
  	    			   		$.messager.alert("提示信息","数据中存在数据表单不能删除","info");
   	    			   	refreshNoUrl();
   	    			   		}else{
   	    			   		$.messager.alert("提示信息","成功删除了表单","info");
   	    			   	    refreshNoUrl();
   	    			   			
   	    			   		}
   			   	}
	    		});
	    }
	});
}

	

	$(function(){
		$('#listSearchText').searchbox({
			width:220,
		    searcher:function(value,name){   
		    	$('#pageForm').find('input[name="searchWord"]').val(value);
		    	$('#pageForm').find('input[name="searchType"]').val(name);
		    	$('#pageForm').find('input[name="pageNum"]').val('1');
		    	$('#pageForm').find('input[name="orderField"]').val('');
		    	$('#pageForm').find('input[name="orderBy"]').val('');
		    	var queryString = $('#pageForm').serialize();
		    	
		    	refresh("<%=rootPath %>form/form_list.action?"+queryString);
		    },   
		    menu:'#listSearchType',   
		    prompt:'请输入检索词'  
		});
	});
</script>
<form id="pageForm">
<s:hidden name="searchWord" id="searchWord" />
<s:hidden name="searchType" id="searchType" />
<s:hidden name="pageNum" id="pageNum" />
<s:hidden name="pageSize" id="pageSize" />
<s:hidden name="orderField" id="orderField" />
<s:hidden name="orderBy" id="orderBy" />
<s:hidden name="formType" id="formType" value="20"/>
</form>
<div id="listSearchType" style="width:120px;">  
    <div data-options="name:'all'">全部&nbsp;&nbsp;&nbsp;&nbsp;</div>
    <div data-options="name:'formname'">表单名称</div>
    <div data-options="name:'formdesc'">表单描述</div>
    <div data-options="name:'formtablename'">数据库表名</div>
    <div data-options="name:'formstatus'">发布状态</div>
</div>
<table width="100%" id="roleList" border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
	<tr class="list_th" style="position: relative;">
   	  	<td colspan="4" align="left" style="padding-left: 10px;" nowrap="nowrap">
   	  		
   	  		<a href="javascript:void(0)" onclick="recovery(0)">恢复表单</a>
   	  		<a href="javascript:void(0)" onclick="realDelete()">彻底删除表单</a>
   	  		
			<a href="javascript:void(0)" onclick="refreshNoUrl()">刷新 </a>
   	  	</td>
   	  	<td colspan="5" align="right" style="padding-right: 2px;"><input id='listSearchText' /></td>
	</tr>
    <tr>
	    <td width="6%" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="checkAll()">全选</a></td>
	    <td width="15%" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('formname','<s:if test="orderField=='formname'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">表单名称<s:if test="orderField=='formname'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='formname'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="15%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('formdesc','<s:if test="orderField=='formdesc'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">表单描述<s:if test="orderField=='formdesc'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='formdesc'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="15%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('formtablename','<s:if test="orderField=='formtablename'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">数据库表名称<s:if test="orderField=='formtablename'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='formtablename'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="12%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('formisdel','<s:if test="orderField=='formisdel'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">表单状态<s:if test="orderField=='formisdel'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='formisdel'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="7%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('formstatus','<s:if test="orderField=='formstatus'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">发布状态<s:if test="orderField=='formstatus'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='formstatus'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="12%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('crtime','<s:if test="orderField=='crtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">创建时间<s:if test="orderField=='crtime'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='crtime'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="12%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('cruserid','<s:if test="orderField=='cruserid'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">创建人<s:if test="orderField=='cruserid'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='cruserid'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="6%" align="center" bgcolor="#F5FAFE"  class="main_bright">操作</td>
    </tr>
    <s:iterator value="irpForms" status="listStat">
    <tr>
	    <td align="center" bgcolor="#F5FAFE"  class="main_bleft"><input type="checkbox" name="formid" value="<s:property value="formid" />" /> <s:property value="(pageNum-1)*pageSize+#listStat.count"/></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="showTabs(this)" _tableName="<s:property value="formtablename" />"><s:property value="formname" /></a></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="formdesc" /></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="formtablename" /></td>
	   <td bgcolor="#F5FAFE"  class="main_bright">
	    <s:if test="formisdel==10">
			    正常
	    </s:if>
	    <s:else>
			    回收站
	    </s:else>
	    </td>
	    <td bgcolor="#F5FAFE"  class="main_bright">
	    <s:if test="formstatus==10">
		    未发布
	    </s:if>
	    <s:else>
		    已发布
	    </s:else>
	    </td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:date name="crtime" format="yyyy-MM-dd HH:mm:ss" /></td>
	    <td bgcolor="#F5FAFE"  class="main_bright">
	     <s:property value="@com.tfs.irp.util.LoginUtil@findUserById(cruserid).getShowName()" />
	    </td>
	    <td align="center" valign="middle" bgcolor="#F5FAFE"  class="main_bright"><img border="0" src="images/icons/cancel.png" title="删除" style="cursor:pointer; margin: 0 5px;" onclick="deleteRowForm(<s:property value="formid" />)" /></td>
    </tr>
    </s:iterator>
    <tr bgcolor="#FFFFFF">
		<td colspan="9" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
	</tr>
</table>
</body>
</html>
