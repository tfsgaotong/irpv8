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
<link rel="stylesheet" type="text/css" href="<%=rootPath%>admin/css/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>admin/css/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>admin/css/css_body.css" />

<script type="text/javascript" src="<%=rootPath%>admin/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>admin/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>admin/js/easyui-lang-zh_CN.js"></script>

</head>

<body>
<script type="text/javascript">
<!--
//已选择的数据对象
var jsonData = new Array();

//刷新列表
function jump(_sUrl){
	$('#importList').layout('panel','center').panel('refresh',_sUrl);
}

//添加已选择页面内容
function eastEdit(_data){
	var east = $('#importList').layout('panel','east');
	if(_data){
		var jDom = $("<div id=\"grp_"+_data.value+"\" style=\"padding-left: 4px;\" _gid=\""+_data.value+"\" onmouseover=\"$(this).find('img').css('display','inline')\" onmouseout=\"$(this).find('img').css('display','none')\">"+_data.name+"<img src=\"<%=rootPath%>admin/images/tree_dnd_no.png\" onclick=\"deleteItem("+_data.value+")\" title=\"删除\" style=\"display: none; position: absolute; cursor: pointer; margin: 4px;\" /></div>");
		east.append(jDom.fadeIn(300));
	}
}

//删除已选择内容
function deleteItem(_groupId){
	var chk = $('#groupList').find("input:checkbox[value="+_groupId+"]");
	if(chk.length>0){
		chk.attr('checked',false);
		selectObj(chk);
	}else{
		for(var i=0;i<jsonData.length;i++){
			var item = jsonData[i];
			if(item && item.value==_groupId){
				jsonData.splice(i,1);
				eastDel(item);
				break;
			}
		}
		var sGroupIds;
		$.each(jsonData, function(i, n){
			if(sGroupIds){
				sGroupIds += ","+n.value;
			}else{
				sGroupIds = n.value;
			}
		});
		$('#groupIds').val(sGroupIds);
	}
}

//删除已选择页面内容
function eastDel(_data){
	var east = $('#importList').layout('panel','east');
	if(_data){
		var jDom = east.find('#grp_'+_data.value);
		jDom.fadeOut(300, function(){
			$(this).remove();
		});
	}
}

$(function (){
	var groupIds = $(parent.window.document).find('#iptGroups').attr('_groupIds').split(",");
	var groupNames = $(parent.window.document).find('#iptGroups').val().split(";");
	for(var i=0;i<groupIds.length;i++){
		if(groupNames[i]==""){
			continue;
		}
		var data = {
			name : groupNames[i],
			value : groupIds[i]
		};
		jsonData.push(data);
		eastEdit(data);
	}
	jump('<%=rootPath %>user/import_group_list.action?groupType=1&parentId=1&groupIds='+$(parent.window.document).find('#iptGroups').attr('_groupIds'));
	
	$('#groupTree').tree({
		url:'<%=rootPath%>user/find_tree_node.action?groupType=1',
		animate:false,
		lines:true,
		onClick: function(node){
			var selectNode = $('#groupTree').tree('getSelected');
			if(selectNode.state=='closed'){
				$('#groupTree').tree('expand',selectNode.target);
			}
			var sGroupIds="";
			$.each(jsonData, function(i, n){
				if(i>0){
					sGroupIds += ","+n.value;
				}else{
					sGroupIds = n.value;
				}
			});
			jump('<%=rootPath %>user/import_group_list.action?groupType=1&parentId='+node.id+'&groupIds='+sGroupIds);
		},
		onLoadSuccess:function(node, data){
			if(!node){
				var root = $('#groupTree').tree('getRoot');
				$('#groupTree').tree('select',root.target);
				$('#groupTree').tree('expand',root.target);
			}
		}
	});
});
//-->
</script>
<div id="importList" class="easyui-layout" style="width:800px;height:441px;">
    <div data-options="region:'west',title:'组织机构',split:true" style="width:150px;">
    	<ul id="groupTree"></ul>
    </div>
    <div data-options="region:'center',title:'<b>组织列表<\/b>'" style="padding:5px;"></div>
    <div data-options="region:'east',title:'已选组织',split:true" style="width:120px;"></div>
</div>
</body>
</html>
