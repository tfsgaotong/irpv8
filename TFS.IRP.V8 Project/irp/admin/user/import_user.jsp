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
		var jDom = $("<div id=\"user_"+_data.value+"\" style=\"padding-left: 4px;\" _gid=\""+_data.value+"\" onmouseover=\"$(this).find('img').css('display','inline')\" onmouseout=\"$(this).find('img').css('display','none')\">"+_data.name+"<img src=\"<%=rootPath%>admin/images/tree_dnd_no.png\" onclick=\"deleteItem("+_data.value+")\" title=\"删除\" style=\"display: none; position: absolute; cursor: pointer; margin: 4px;\" /></div>");
		east.append(jDom.fadeIn(300));
	}
}

//删除已选择内容
function deleteItem(_userId){
	var chk = $('#userList').find("input:checkbox[value="+_userId+"]");
	if(chk.length>0){
		chk.attr('checked',false);
		selectObj(chk);
	}else{
		for(var i=0;i<jsonData.length;i++){
			var item = jsonData[i];
			if(item && item.value==_userId){
				jsonData.splice(i,1);
				eastDel(item);
				break;
			}
		}
		var sUserIds;
		$.each(jsonData, function(i, n){
			if(sUserIds){
				sUserIds += ","+n.value;
			}else{
				sUserIds = n.value;
			}
		});
		$('#userIds').val(sUserIds);
	}
}

//删除已选择页面内容
function eastDel(_data){
	var east = $('#importList').layout('panel','east');
	if(_data){
		var jDom = east.find('#user_'+_data.value);
		jDom.fadeOut(300, function(){
			$(this).remove();
		});
	}
}

$(function (){
	var userIds = $(parent.window.document).find('#iptUsers').attr('_userIds').split(",");
	var userNames = $(parent.window.document).find('#iptUsers').val().split(";");
	for(var i=0;i<userIds.length;i++){
		if(userNames[i]==""){
			continue;
		}
		var data = {
			name : userNames[i],
			value : userIds[i]
		};
		jsonData.push(data);
		eastEdit(data);
	}
	jump('<%=rootPath %>user/import_user_list.action?groupId=1&userIds='+$(parent.window.document).find('#iptUsers').attr('_userIds'));
	
	$('#groupUserList').tree({
		url:'<%=rootPath%>user/find_tree_node.action?groupType=1',
		animate:false,
		lines:true,
		onClick: function(node){
			var selectNode = $('#groupTree').tree('getSelected');
			if(selectNode.state=='closed'){
				$('#groupTree').tree('expand',selectNode.target);
			}
			var sUserIds="";
			$.each(jsonData, function(i, n){
				if(i>0){
					sUserIds += ","+n.value;
				}else{
					sUserIds = n.value;
				}
			});
			jump('<%=rootPath %>user/import_user_list.action?groupId='+node.id+'&userIds='+sUserIds);
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
    <div data-options="region:'center',title:'<b>用户列表<\/b>'" style="padding:5px;"></div>
    <div data-options="region:'east',title:'已选用户',split:true" style="width:120px;"></div>
</div>
</body>
</html>
