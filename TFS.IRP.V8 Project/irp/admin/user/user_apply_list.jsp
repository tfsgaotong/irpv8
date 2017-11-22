<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		    					$('body').layout('panel','center').panel('refresh');
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
	function page(_nPageNum){
		$('#pageNum').val(_nPageNum);
		var queryString = $('#pageForm').serialize();
		jump("<%=rootPath %>user/apply_user_list.action?"+queryString);
	}
	//排序
	function orderBy(_sFiled,_sOrderBy){
		$('#orderField').val(_sFiled);
		$('#orderBy').val(_sOrderBy);
		var queryString = $('#pageForm').serialize();
		jump("<%=rootPath %>user/apply_user_list.action?"+queryString);
	}
	
	//全选
	function checkAll(){
		m_checked = !m_checked;
		$("input[name='userid']").attr("checked",m_checked); 
	}
	
	//刷新
	function refresh(){
		$('body').layout('panel','center').panel('refresh');
	}
	
	//开通选择的用户
	function openUsers(){
		var userids = "";
    	$("input[name='userid']:checked").each(function(){userids+=','+this.value;});
    	if(userids){
    		userids = userids.substring(1);
    		$.messager.confirm('提示信息','是否要开通这些用户？',function(r){   
    		    if(r){
   		    		$.ajax({
   	    				url: "<%=rootPath %>user/open_user.action",
   	    			   	type: "POST",
   	    			   	data: {userIds: userids},
   	    			   	success: function(msg){
   	    			   		$.messager.alert("提示信息","成功开通了"+msg+"个用户","info");
   	    			   		$('body').layout('panel','center').panel('refresh');
   	    			   	}
   		    		});
    		    }
    		});
    	}else{
    		$.messager.alert("提示信息","请选择要开通的用户。","warning");
    	}
	}
	
	//停用选择的用户
	function forbidUsers(){
		var userids = "";
    	$("input[name='userid']:checked").each(function(){userids+=','+this.value;});
    	if(userids){
    		userids = userids.substring(1);
    		$.messager.confirm('提示信息','是否要停用这些用户？',function(r){   
    		    if(r){
   		    		$.ajax({
   	    				url: "<%=rootPath %>user/forbid_user.action",
   	    			   	type: "POST",
   	    			   	data: {userIds: userids},
   	    			   	success: function(msg){
   	    			   		$.messager.alert("提示信息","成功停用了"+msg+"个用户","info");
   	    			   		$('body').layout('panel','center').panel('refresh');
   	    			   	}
   		    		});
    		    }
    		});
    	}else{
    		$.messager.alert("提示信息","请选择要停用的用户。","warning");
    	}
	}
	
	//修改密码
	function updatePwd(){
		var userids = "";
    	$("input[name='userid']:checked").each(function(){userids+=','+this.value;});
    	if(userids){
    		userids = userids.substring(1);
    		var dialogDiv = document.createElement("div");
    		dialogDiv.id="editPwd";
    		document.body.appendChild(dialogDiv);
    		$('#editPwd').dialog({   
    		    modal:true,
    		    href:'<%=rootPath %>user/user_pwd_edit.action?userIds='+userids,
    		    title:'修改密码',
    		    width:300,
    		    height:140,
    		    resizable:true,
    		    buttons: [{
    		    	text: '提交', 
    		    	iconCls: 'icon-ok', 
    		    	handler: function() {
    		    		$('#pwdForm').form('submit',{
    		    			url : "<%=rootPath %>user/user_pwd_edit_dowith.action",
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
    		    				$('#editPwd').dialog('destroy');
    		    				$.messager.progress('close');
    		    				if(data>0){
    		    					$.messager.alert("提示信息","成功重置了"+data+"个用户密码","info");
    		    					$('body').layout('panel','center').panel('refresh');
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
    		    		$('#editPwd').dialog('destroy');
    		    	} 
    		    }],
    		    onClose:function(){
    		    	$('#editPwd').dialog('destroy');
    		    }
    		});
    	}else{
    		$.messager.alert("提示信息","请选择要修改密码的用户。","warning");
    	}
	}
	
	//获得栏目的权限
	function getChannelRight(){
		if($('#channelRight').length==0){
			return [];
		}
		var row = $('#channelRight').treegrid('getSelected');
		if(row){
			$('#channelRight').treegrid('endEdit', row.operid);
		}
		return $('#channelRight').treegrid('getChanges');
	}
	
	//获得专题的权限
	function getSubjectRight(){
		if($('#subjectRight').length==0){
			return [];
		}
		var row = $('#subjectRight').treegrid('getSelected');
		if(row){
			$('#subjectRight').treegrid('endEdit', row.operid);
		}
		return $('#subjectRight').treegrid('getChanges');
	}
	
	//获得地图的权限
	function getMapRight(){
		if($('#mapRight').length==0){
			return [];
		}
		var row = $('#mapRight').treegrid('getSelected');
		if(row){
			$('#mapRight').treegrid('endEdit', row.operid);
		}
		return $('#mapRight').treegrid('getChanges');
	}
	
	//获得文档权限
	function getDocumentRight(){
		if($('#documentRight').length==0){
			return [];
		}
		var row = $('#documentRight').treegrid('getSelected');
		if(row){
			$('#documentRight').treegrid('endEdit', row.operid);
		}
		return $('#documentRight').treegrid('getChanges');
	}
	
	//获得站点权限
	function getSiteRight(){
		if($('#siteRight').length==0){
			return [];
		}
		var row = $('#siteRight').datagrid('getSelected');
		if(row){
			var index = $('#siteRight').datagrid('getRowIndex',row);
			$('#siteRight').datagrid('endEdit', index);
		}
		return $('#siteRight').datagrid('getChanges');
	}
	
	//获得管理权限
	function getManagementRight(){
		if($('#managementRight').length==0){
			return [];
		}
		var row = $('#managementRight').treegrid('getSelected');
		if(row){
			$('#managementRight').treegrid('endEdit', row.operid);
		}
		return $('#managementRight').treegrid('getChanges');
	}
	
	//编辑权限
	function setRight(_nObjId) {
		var dialogDiv = document.createElement("div");
		dialogDiv.id="editRight";
		dialogDiv.style.padding="5px";
		document.body.appendChild(dialogDiv);
		var sObjType = "<s:property value="@com.tfs.irp.user.entity.IrpUser@TABLE_NAME" />";
		$('#editRight').dialog({   
		    modal:true,
		    href:'<%=rootPath %>right/right_list.action?objType='+sObjType+'&objId='+_nObjId,
		    title:'编辑权限',
		    width:850,
		    height:550,
		    resizable:true,
		    buttons: [{ 
		    	text: '保存', 
		    	iconCls: 'icon-ok', 
		    	handler: function() {
		    		var siteData = getSiteRight();
		    		var channelData = getChannelRight();
		    		var subjectData = getSubjectRight();
		    		var mapData = getMapRight();
		    		var documentData= getDocumentRight();
		    		var managementData = getManagementRight();
		    		var jsonData = siteData.concat(channelData).concat(subjectData).concat(mapData).concat(documentData).concat(managementData);
		    		if(jsonData.length>0){
		    			$.messager.confirm('提示信息','是否保存权限信息？',function(r){
		    				if(r){
				    			$.ajax({
									type: "post",
			    			 	  	url: "<%=rootPath %>right/right_edit.action",
									data: "objType="+sObjType+"&objId="+_nObjId+"&jsonData="+JSON.stringify(jsonData),
									datatype: "json",
									success:function(data){
										var tabs = $('#groupTabs').tabs('tabs');
			    			 	  		$.each(tabs, function(i, n){
			    			 	  			var opt = n.panel('options');
			    			 	  			//宽度为auto表示选项卡没有被点击过。
											if(opt.width!='auto'){
												n.panel('refresh');
											}
			    			 	  		});
				    			 	},
				    			 	error:function(){
				    			 		$.messager.progress('close');
				    			 		$.messager.alert('提示信息','保存数据失败！','error');
				    			 	}
				    			});
		    				}
		    			});
		    		}
		    	} 
		    },{ 
		    	text: '同步子级', 
		    	iconCls: 'icon-reload', 
		    	handler: function() {
		    		var tabs = $('#groupTabs').tabs('getSelected');
		    		var gridObj = tabs.find('.treegridobj');
		    		if(gridObj && gridObj.length>0){
		    			var dataObj = gridObj.treegrid('getSelected');
			    		if(dataObj){
			    			$.messager.confirm('提示信息','是否同步权限到子节点？',function(r){
			    				if(r){
			    					$.ajax({
					    				url:'<%=rootPath %>right/syn_child_node.action',
					    				type:'post',
					    				data:{
					    					objType:sObjType,
					    					objId:_nObjId,
					    					jsonData:JSON.stringify(dataObj)
					    				},
					    				success:function(data){
					    					gridObj.treegrid('reload',dataObj.operid);
					    				}
					    			});
			    				}
			    			});
			    		}
		    		}
		    	} 
		    },{ 
		    	text: '关闭', 
		    	iconCls: 'icon-cancel', 
		    	handler: function() { 
		    		$('#editRight').dialog('destroy');
		    	} 
		    }],
		    onClose:function(){
		    	$('#editRight').dialog('destroy');
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
		    	jump("<%=rootPath %>user/apply_user_list.action?"+queryString);
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
</form>
<div id="listSearchType" style="width:120px;">  
    <div data-options="name:'all'">全部&nbsp;&nbsp;&nbsp;&nbsp;</div>
    <div data-options="name:'username'">用户名&nbsp;&nbsp;</div>
    <div data-options="name:'truename'">真实姓名</div>
</div>
<table width="100%"  border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
	<tr class="list_th" style="position: relative;">
   	  	<td colspan="4" align="left" style="padding-left: 10px;" nowrap="nowrap">
   	  		<a href="javascript:void(0)" onclick="openUsers()">开通用户</a>
   	  		<a href="javascript:void(0)" onclick="forbidUsers()">停用用户</a>
   	  		<a href="javascript:void(0)" onclick="updatePwd()">重置密码</a>
			<a href="javascript:void(0)" onclick="refresh()">刷新 </a>
   	  	</td>
   	  	<td colspan="4" align="right" style="padding-right: 2px;"><input id='listSearchText' /></td>
	</tr>
    <tr>
	    <td width="6%" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="checkAll()">全选</a></td>
	    <td width="21%" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('username','<s:if test="orderField=='username'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">用户名<s:if test="orderField=='username'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='username'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="15%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('truename','<s:if test="orderField=='truename'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">真实姓名<s:if test="orderField=='truename'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='truename'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="21%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('nickname','<s:if test="orderField=='nickname'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">昵称<s:if test="orderField=='nickname'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='nickname'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="12%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('crtime','<s:if test="orderField=='crtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">注册时间<s:if test="orderField=='crtime'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='crtime'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="12%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('regtime','<s:if test="orderField=='regtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">开通时间<s:if test="orderField=='regtime'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='regtime'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="8%" align="center" bgcolor="#F5FAFE"  class="main_bright">权限管理</td>
	    <td width="5%" align="center" bgcolor="#F5FAFE"  class="main_bright">操作</td>
    </tr>
    <s:iterator value="irpUsers" status="listStat">
    <tr>
	    <td align="center" bgcolor="#F5FAFE"  class="main_bleft"><input type="checkbox" name="userid" value="<s:property value="userid" />" /> <s:property value="(pageNum-1)*pageSize+#listStat.count"/></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="username" /></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="truename" /></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="nickname" /></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:date name="crtime" format="yyyy-MM-dd HH:mm:ss" /></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:date name="regtime" format="yyyy-MM-dd HH:mm:ss" /></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><a href="javascript:void(0)" onclick="setRight(<s:property value="userid" />)">编辑权限</a></td>
	    <td align="center" valign="middle" bgcolor="#F5FAFE"  class="main_bright"><img border="0" src="images/icons/pencil.png" title="修改" style="cursor:pointer; margin: 0 5px;" onclick="userEdit(<s:property value="userid" />)" /></td>
    </tr>
    </s:iterator>
    <tr bgcolor="#FFFFFF">
		<td colspan="9" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
	</tr>
</table>
</body>
</html>
