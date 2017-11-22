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
	var m_roleChecked = false;
	var m_roleId = <s:property value="roleId" />;
	
	//修改用户
	function userEdit(_nUserId){
		var dialogDiv = document.createElement("div");
		dialogDiv.id="editUser";
		document.body.appendChild(dialogDiv);
		$('#editUser').dialog({   
		    modal:true,
		    href:'<%=rootPath %>user/user_edit.action?userId='+_nUserId,
		    title:'修改用户',
		    width:500,
		    height:531,
		    resizable:true,
		    buttons: [{
		    	text: '提交', 
		    	iconCls: 'icon-ok', 
		    	handler: function() {
		    		var sGroupIds = "";
		    		var sRoleIds = "";
		    		$("#selectGroups option").each(function () {
		    			sGroupIds+=","+this.value;
		            });
		    		$("#sysRolesTo option").each(function () {
		    			sRoleIds+=","+this.value;
		            });
		    		$("#speRolesTo option").each(function () {
		    			sRoleIds+=","+this.value;
		            });
		    		if(sGroupIds){
		    			sGroupIds = sGroupIds.substring(1);
		    			$('#addUserForm').find("input:hidden[name='groupIds']").val(sGroupIds);
		    		}
					if(sRoleIds){
						sRoleIds = sRoleIds.substring(1);
						$('#addUserForm').find("input:hidden[name='roleIds']").val(sRoleIds);
		    		}
					if($('#selectClassify')){
						var sClassifyIds = "";
						$("#selectClassify option").each(function () {
							sClassifyIds+=","+this.value;
			            });
						if(sClassifyIds){
							sClassifyIds = sClassifyIds.substring(1);
							$('#addUserForm').find("input:hidden[name='classifyIds']").val(sClassifyIds);
						}
					}
					$('#addUserForm').form('submit',{
		    			url : "<%=rootPath %>user/user_edit_dowith.action",
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
		    				$('#editUser').dialog('destroy');
		    				$.messager.progress('close');
		    				if(data==1){
		    					$.messager.alert("提示信息","修改用户成功","info");
		    					roleUserRefresh();
		    				}else{
		    					$.messager.alert("提示信息","修改用户信息失败！","error");
		    				}
		    			}
		    		});
		    	} 
		    },{ 
		    	text: '取消', 
		    	iconCls: 'icon-cancel', 
		    	handler: function() { 
		    		$('#editUser').dialog('destroy');
		    	} 
		    }],
		    onClose:function(){
		    	$('#editUser').dialog('destroy');
		    }
		});
	}
	
	//分页
	function roleUserPage(_nPageNum){
		$('#roleUserPageForm').find('input[name="pageNum"]').val(_nPageNum);
		var queryString = $('#roleUserPageForm').serialize();
		roleUserRefresh("<%=rootPath %>user/role_user_list.action?"+queryString);
	}
	//排序
	function roleUserOrderBy(_sFiled,_sOrderBy){
		$('#roleUserPageForm').find('input[name="orderField"]').val(_sFiled);
		$('#roleUserPageForm').find('input[name="orderBy"]').val(_sOrderBy);
		var queryString = $('#roleUserPageForm').serialize();
		roleUserRefresh("<%=rootPath %>user/role_user_list.action?"+queryString);
	}
	
	//全选
	function checkRoleUserAll(){
		m_roleChecked = !m_roleChecked;
		$('#roleUserList').find("input:checkbox[name='userid']").attr("checked",m_roleChecked);
	}
	
	//刷新
	function roleUserRefresh(){
		$('#roleTabs').tabs('getSelected').panel('refresh');
	}
	
	//刷新到一个地址
	function roleUserRefresh(_sUrl){
		$('#roleTabs').tabs('getSelected').panel('refresh',_sUrl);
	}
	
	//添加用户到组织
	function importUser(){
		var dialogDiv = document.createElement("div");
		dialogDiv.id="importUser";
		document.body.appendChild(dialogDiv);
		$('#importUser').dialog({
			modal:true,
			href:'<%=rootPath %>user/user_role_import_list.action?init=1&roleId='+m_roleId,
			title:'添加用户',
			resizable:true,
		    width:700,
		    height:484,
		    buttons: [{
		    	text: '提交', 
		    	iconCls: 'icon-ok', 
		    	handler: function() {
		    		$('#dialogPageForm').form('submit',{
		    			url : "<%=rootPath %>user/user_role_import_dowith.action",
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
		    				$('#importUser').dialog('destroy');
		    				$.messager.progress('close');
		    				if(data==1){
		    					roleUserRefresh();
		    				}else{
		    					$.messager.alert("提示信息","添加用户到角色失败！","error");
		    				}
		    			}
		    		});
		    	} 
		    	},{ 
		    	text: '取消', 
		    	iconCls: 'icon-cancel', 
		    	handler: function() { 
		    		$('#importUser').dialog('destroy');
		    	} 
		    }],
		    onClose:function(){
		    	$('#importUser').dialog('destroy');
		    }
		});
	}
	
	//移除角色中的用户
	function removeUser(){
		var nUserIds = "";
		$('#roleUserList').find("input:checkbox[name='userid']:checked").each(function(){nUserIds+=','+this.value;});
		if(nUserIds){
			nUserIds = nUserIds.substring(1);
			$.messager.confirm('提示信息','是否要移除这些用户？',function(r){   
    		    if(r){
   		    		$.ajax({
   	    				url: "<%=rootPath %>user/remove_userrole_dowith.action",
   	    			   	type: "POST",
   	    			   	data: {
   	    			   		roleId: m_roleId,
   	    			   		userIds: nUserIds
   	    			   	},
   	    			   	success: function(msg){
   	    			   		$.messager.alert("提示信息","成功移除了"+msg+"个用户","info");
   	    			   		roleUserRefresh();
   	    			   	}
   		    		});
    		    }
    		});
		}else{
			$.messager.alert("提示信息","请选择要移除的用户。","warning");
		}
	}
	
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
		    	roleUserRefresh("<%=rootPath %>user/role_user_list.action?"+queryString);
		    },   
		    menu:'#listSearchType',   
		    prompt:'请输入检索词'  
		});
	});
</script>
<form id="roleUserPageForm">
<s:hidden name="searchWord" id="searchWord" />
<s:hidden name="searchType" id="searchType" />
<s:hidden name="roleId" id="roleId" />
<s:hidden name="pageNum" id="pageNum" />
<s:hidden name="pageSize" id="pageSize" />
<s:hidden name="orderField" id="orderField" />
<s:hidden name="orderBy" id="orderBy" />
</form>
<div id="listSearchType" style="width:120px;">  
    <div data-options="name:'all'">全部&nbsp;&nbsp;&nbsp;&nbsp;</div>
    <div data-options="name:'username'">用户名&nbsp;&nbsp;</div>
    <div data-options="name:'truename'">真实姓名</div>
</div>
<table id="roleUserList" width="100%"  border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
	<tr class="list_th" style="position: relative;">
   	  	<td colspan="3" align="left" style="padding-left: 10px;" nowrap="nowrap">
   	  		<a href="javascript:void(0)" onclick="importUser()">添加用户</a>
   	  		<a href="javascript:void(0)" onclick="removeUser()">移除用户</a>
			<a href="javascript:void(0)" onclick="roleUserRefresh()">刷新</a>
   	  	</td>
   	  	<td colspan="3" align="right" style="padding-right: 2px;"><input id='listSearchText' /></td>
	</tr>
    <tr>
	    <td width="6%" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="checkRoleUserAll()">全选</a></td>
	    <td width="25%" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="roleUserOrderBy('username','<s:if test="orderField=='username'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">用户名<s:if test="orderField=='username'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='username'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="12%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="roleUserOrderBy('status','<s:if test="orderField=='status'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">状态<s:if test="orderField=='status'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='status'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="25%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="roleUserOrderBy('truename','<s:if test="orderField=='truename'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">真实姓名<s:if test="orderField=='truename'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='truename'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="25%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="roleUserOrderBy('nickname','<s:if test="orderField=='nickname'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">昵称<s:if test="orderField=='nickname'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='nickname'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="7%" align="center" bgcolor="#F5FAFE"  class="main_bright">操作</td>
    </tr>
    <s:iterator value="irpUsers" var="irpuser" status="listStat">
    <tr>
	    <td align="center" bgcolor="#F5FAFE" class="main_bleft"><input type="checkbox" name="userid" <s:if test="roleId==1L && userid==1L">disabled="disabled"</s:if> value="<s:property value="userid" />" /> <s:property value="(pageNum-1)*pageSize+#listStat.count"/></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="username" /></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="statusName" /></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="truename" /></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="nickname" /></td>
	    <td align="center" valign="middle" bgcolor="#F5FAFE"  class="main_bright"><img border="0" src="images/icons/pencil.png" title="修改" style="cursor:pointer; margin: 0 5px;" onclick="userEdit(<s:property value="userid" />)" /></td>
    </tr>
    </s:iterator>
    <tr bgcolor="#FFFFFF">
		<td colspan="9" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
	</tr>
</table>
</body>
</html>
