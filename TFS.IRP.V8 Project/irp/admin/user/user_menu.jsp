<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />
<title>用户管理</title>
</head>

<body>
<script type="text/javascript">
function jump(_sUrl){
	$('body').layout('panel','center').panel('refresh',_sUrl);
}
$(function (){
	var isInit = false;
	$('#menu').accordion({
		onSelect : function(title){
			if(isInit&&title=="用户管理"){
				jump('<%=rootPath %>user/use_user_list.action');
			}else if(title=="组织管理"){
				jump('<%=rootPath %>user/group_list.action?parentId=<s:property value="@com.tfs.irp.group.entity.IrpGroup@GROUP_ROOTID_PUBLIC" default="1"/>&groupType=<s:property value="@com.tfs.irp.group.entity.IrpGroup@GROUP_TYPE_PUBLIC" default="1"/>');
				$('#groupTree').tree({
					url:'<%=rootPath%>user/find_tree_node.action',
					animate:false,
					lines:true,
					onClick: function(node){
						var selectNode = $('#groupTree').tree('getSelected');
						$('#groupId').val(node.id);
						if(selectNode.state=='closed'){
							$('#groupTree').tree('expand',selectNode.target);
						}
						jump('<%=rootPath %>user/group_list.action?parentId='+node.id+'&groupType='+node.attributes.groupType);
					},
					onLoadSuccess:function(node, data){
						if(!node){
							var root = $('#groupTree').tree('getRoot');
							$('#groupTree').tree('select',root.target);
							$('#groupTree').tree('expand',root.target);
						}
					}
				});
				isInit = true;
			}else if(title=="角色管理"){
				isInit = true;
				jump('<%=rootPath %>user/role_tabs.action?roleType=1');
			}
		}
	});
});
</script>
<div id="menu" class="easyui-accordion" fit="true" border="false">
	<div title="用户管理" style="padding:0px;" selected="true" class="arrowsidemenu">
	<ul class="menucontents">
		<li><a href="javascript:void(0)" onclick="jump('<%=rootPath %>user/use_user_list.action?pageNum=1')">已开通</a></li>
		<li><a href="javascript:void(0)" onclick="jump('<%=rootPath %>user/apply_user_list.action?pageNum=1')">未开通</a></li>
		<li><a href="javascript:void(0)" onclick="jump('<%=rootPath %>user/forbid_user_list.action?pageNum=1')">已停用</a></li>
	</ul>
	</div>
	<div title="组织管理" style="padding:10px;">
		<ul id="groupTree" ></ul>
	</div>
	<div title="角色管理" style="padding:0px;" class="arrowsidemenu">
	<ul class="menucontents">
		<li><a href="javascript:void(0)" onclick="jump('<%=rootPath %>user/role_tabs.action?roleType=1')">系统角色</a></li>
		<li><a href="javascript:void(0)" onclick="jump('<%=rootPath %>user/role_tabs.action?roleType=2')">特殊角色</a></li>
	</ul>
	</div>
</div>
</body>
</html>
