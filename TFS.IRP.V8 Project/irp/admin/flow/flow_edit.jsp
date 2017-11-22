<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>流程管理</title>
<link type="text/css" href="<%=rootPath %>admin/css/smoothness/jquery-ui.css" rel="stylesheet" />

<script type="text/javascript" src="<%=rootPath %>admin/js/raphael-min.js"></script>
<script type="text/javascript" src="<%=rootPath %>admin/js/jquery-1.8.0.min.js"></script>

<script type="text/javascript" src="<%=rootPath %>admin/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>admin/js/myflow.js"></script>
<script type="text/javascript" src="<%=rootPath %>admin/js/myflow.editors.js"></script>


<script type="text/javascript">
//选择组织
function selectGroup(ipt){
	$('<div><iframe name="groupframe" src="<%=rootPath %>user/import_group.action" frameborder="0" scrolling="no" width="800" height="441" marginheight="0" marginwidth="0"></iframe></div>').dialog({
		autoopen: true, 
		modal: true,
		resizable: false,
		title: "选择组织",
		width: 825,
		height: 554,
		buttons: [{
			text: "确定",
			click: function() {
				var groupNames="";
				var groupIds="";
				var groupData = $(this).find('iframe')[0].contentWindow.jsonData;
				for(var i=0;i<groupData.length;i++){
					var group = groupData[i];
					if(i>0){
						groupNames+=";"+group.name;
					}else{
						groupNames=group.name;
					}
					if(i>0){
						groupIds+=","+group.value;
					}else{
						groupIds=group.value;
					}
				}
				$('#iptGroups').val(groupNames);
				$('#iptGroups').attr('_groupIds', groupIds);
				$(this).dialog("destroy");
			}
		},
		{
			text: "取消",
			click: function() {
				$(this).dialog("destroy");
			}
		}],
		beforeClose: function() {
			$(this).dialog("destroy");
	    }
	});
}

//选择用户
function selectUser(ipt){
	$('<div><iframe name="userframe" src="<%=rootPath %>user/import_user.action" frameborder="0" scrolling="no" width="800" height="441" marginheight="0" marginwidth="0"></iframe></div>').dialog({
		autoopen: true, 
		modal: true,
		resizable: false,
		title: "选择组织",
		width: 825,
		height: 554,
		buttons: [{
			text: "确定",
			click: function() {
				var userNames="";
				var userIds="";
				var userData = $(this).find('iframe')[0].contentWindow.jsonData;
				for(var i=0;i<userData.length;i++){
					var user = userData[i];
					if(i>0){
						userNames+=";"+user.name;
					}else{
						userNames=user.name;
					}
					if(i>0){
						userIds+=","+user.value;
					}else{
						userIds=user.value;
					}
				}
				$('#iptUsers').val(userNames);
				$('#iptUsers').attr('_userIds', userIds);
				$(this).dialog("destroy");
			}
		},
		{
			text: "取消",
			click: function() {
				$(this).dialog("destroy");
			}
		}],
		beforeClose: function() {
			$(this).dialog("destroy");
	    }
	});
}

$(function() {
	var myflow = $.myflow;

	$.extend(true,myflow.config.rect,{
		attr : {
			r : 8,
			fill : '#F6F7FF',
			stroke : '#03689A',
			"stroke-width" : 2
		}
	});

	$.extend(true,myflow.config.tools.states,{
		start : {
			showType: 'image',
			type : 'start',
			name : {text:'<<start>>'},
			text : {text:'开始'},
			img : {src : '<%=rootPath %>admin/images/workflow/48/start_event_empty.png',width : 48, height:48},
			attr : {width:50 ,heigth:50 },
			props : {
				text: {name:'text',label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'开始'},
				status: {name:'status', label : '状态', value:'1', editor: function(){return new myflow.editors.selectEditor(<s:property value="statusData" escapeHtml="false" />);}}
			}},
		end : {
			showType: 'image',
			type : 'end',
			name : {text:'<<end>>'},
			text : {text:'结束'},
			img : {src : '<%=rootPath %>admin/images/workflow/48/end_event_terminate.png',width : 48, height:48},
			attr : {width:50 ,heigth:50 },
			props : {
				text: {name:'text',label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'结束'},
				status: {name:'status', label : '状态', value:'4', editor: function(){return new myflow.editors.selectEditor(<s:property value="statusData" escapeHtml="false" />);}}
			}},
		node : {
			showType: 'text',
			type : 'node',
			name : {text:'<<node>>'},
			text : {text:'节点'},
			img : {src : '<%=rootPath %>admin/images/workflow/48/task_empty.png',width : 48, height:48},
			props : {
				text: {name:'text',label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'用户'},
				desc: {name:'desc', label : '描述', value:'', editor: function(){return new myflow.editors.inputEditor();}},
				user: {name:'user', label : '用户', value:'', editor: function(){return new myflow.editors.inputUserEditor();}},
				group: {name:'group', label : '组织', value:'', editor: function(){return new myflow.editors.inputGroupEditor();}},
				status: {name:'status', label : '状态', value:'', editor: function(){return new myflow.editors.selectEditor(<s:property value="statusData" escapeHtml="false" />);}}
			}}
	});
	
	$('#myflow').myflow({
		basePath : "",
		restore : eval('(<s:property value="flowData" escapeHtml="false" />)'),
		tools : {
			save : {
				onclick : function(data) {
					var jsonData = eval('(' + data + ')');
					if(jsonData.props.props.name.value.length>0 && jsonData.props.props.desc.value.length>0){
						$.ajax({
	   	    				url: "<%=rootPath %>flow/flow_edit_dowith.action",
	   	    			   	type: "POST",
	   	    			   	data: {
	   	    			   		flowId: <s:property value="flowId" />,
	   	    			   		flowData: data
	   	    			   	},
	   	    			   	success: function(msg){
	   	    			   		if (window.opener && !window.opener.closed) {
	   	    			       		window.opener.refresh();
	   	    					}
	   	    			   		window.close();
	   	    			   	}
	   		    		});
					} else if(jsonData.props.props.name.value.length==0){
						alert("请填写流程名称！");
						$('#pname').find('input').select();
					} else if(jsonData.props.props.desc.value.length==0){
						alert("请填写流程描述！");
						$('#pdesc').find('input').select();
					} 
				}
			}
		}
	});
});
</script>
<style type="text/css">
body {
	margin: 0;
	pading: 0;
	text-align: left;
	font-family: Arial, sans-serif, Helvetica, Tahoma;
	font-size: 12px;
	line-height: 1.5;
	color: black;
	background-image: url(<%=rootPath %>admin/images/workflow/bg.png);
}

.node {
	width: 70px;
	text-align: center;
	vertical-align: middle;
	border: 1px solid #fff;
}

.mover {
	border: 1px solid #ddd;
	background-color: #ddd;
}

.selected {
	background-color: #ddd;
}

.state {
	
}

#myflow_props table {
	
}

#myflow_props th {
	letter-spacing: 2px;
	text-align: left;
	padding: 6px;
	background: #ddd;
}

#myflow_props td {
	background: #fff;
	padding: 6px;
}

#pointer {
	background-repeat: no-repeat;
	background-position: center;
}

#path {
	background-repeat: no-repeat;
	background-position: center;
}

#task {
	background-repeat: no-repeat;
	background-position: center;
}

#state {
	background-repeat: no-repeat;
	background-position: center;
}
</style>
</head>

<body>
	<div id="myflow_tools"
		style="position: absolute; top: 10; left: 10; background-color: #fff; width: 70px; cursor: default; padding: 3px;"
		class="ui-widget-content">
		<div id="myflow_tools_handle" style="text-align: center;"
			class="ui-widget-header">工具集</div>
		<div class="node" id="myflow_save">
			<img src="<%=rootPath %>admin/images/workflow/save.gif" />&nbsp;&nbsp;保存
		</div>
		<div>
			<hr />
		</div>
		<div class="node selectable" id="pointer">
			<img src="<%=rootPath %>admin/images/workflow/select16.gif" />&nbsp;&nbsp;选择
		</div>
		<div class="node selectable" id="path">
			<img src="<%=rootPath %>admin/images/workflow/16/flow_sequence.png" />&nbsp;&nbsp;转换
		</div>
		<div>
			<hr />
		</div>
		<div class="node state" id="start" type="start">
			<img src="<%=rootPath %>admin/images/workflow/16/start_event_empty.png" />&nbsp;&nbsp;开始
		</div>
		<div class="node state" id="node" type="node">
			<img src="<%=rootPath %>admin/images/workflow/16/task_empty.png" />&nbsp;&nbsp;节点
		</div>
		<div class="node state" id="end" type="end">
			<img src="<%=rootPath %>admin/images/workflow/16/end_event_terminate.png" />&nbsp;&nbsp;结束
		</div>
	</div>
	<div id="myflow_props" style="position: absolute; top: 30; right: 50; background-color: #fff; width: 220px; padding: 3px;" class="ui-widget-content">
		<div id="myflow_props_handle" class="ui-widget-header">属性</div>
		<table border="1" width="100%" cellpadding="0" cellspacing="0"></table>
		<div>&nbsp;</div>
	</div>
	<div id="myflow"></div>
</body>
</html>
