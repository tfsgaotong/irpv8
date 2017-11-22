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
		refresh("<%=rootPath %>user/group_system_list.action?"+queryString);
	}
	//排序
	function orderBy(_sFiled,_sOrderBy){
		$('#orderField').val(_sFiled);
		$('#orderBy').val(_sOrderBy);
		var queryString = $('#pageForm').serialize();
		refresh("<%=rootPath %>user/group_system_list.action?"+queryString);
	}
	
	//全选
	function checkAll(){
		m_checked = !m_checked;
		$('#groupList').find("input:checkbox[name='groupid']").attr("checked",m_checked); 
	}
	
	//刷新
	function refresh(){
		$('#groupManager').panel('refresh');
	}
	
	//刷新到一个地址
	function refresh(_sUrl){
		$('#groupManager').panel('refresh',_sUrl);
	}
	
	//编辑组织
	function groupEdit(_nGroupId){
		var dialogDiv = document.createElement("div");
		dialogDiv.id="editGroup";
		document.body.appendChild(dialogDiv);
		var nParentId = $('#parentId').val();
		var nGroupType = $('#groupType').val();
		$('#editGroup').dialog({   
		    modal:true,
		    href:'<%=rootPath %>user/group_edit.action?parentId='+nParentId+'&groupId='+_nGroupId+'&groupType='+nGroupType,
		    title:_nGroupId==0?'添加子组':'修改子组',
		    width:300,
		    height:162,
		    resizable:true,
		    buttons: [{
		    	text: '提交', 
		    	iconCls: 'icon-ok', 
		    	handler: function() {
		    		$('#groupForm').form('submit',{
		    			url : "<%=rootPath %>user/group_edit_dowith.action",
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
		    				$('#editGroup').dialog('destroy');
		    				$.messager.progress('close');
		    				if(data==1){
		    					var node = $('#groupTree').tree('getSelected');
		    					//如果当前节点没有展开则刷父节点
		    					if(!node.state){
		    						var _b0 = $(node.target);
		    						var _b1=_b0.find("span.tree-icon");
		    						if(_b1.hasClass("tree-file")){
		    							_b1.removeClass("tree-file").addClass("tree-folder tree-folder-open");
		    							var hit=$("<span class=\"tree-hit tree-expanded\"></span>").insertBefore(_b1);
		    							if(hit.prev().length){
		    								hit.prev().remove();
		    							}
		    						}
		    					}
		    					$('#groupTree').tree('reload',node.target);
		    					refresh();
		    				}else{
		    					$.messager.alert("提示信息","修改组织信息失败！","error");
		    				}
		    			}
		    		});
		    	} 
		    },{ 
		    	text: '取消', 
		    	iconCls: 'icon-cancel', 
		    	handler: function() { 
		    		$('#editGroup').dialog('destroy');
		    	} 
		    }],
		    onClose:function(){
		    	$('#editGroup').dialog('destroy');
		    }
		});
	}
	
	//删除所选组织
	function delGroup(){
		var sGroupIds = "";
		var nParentId = $('#pageForm').find("input:hidden[name='parentId']").val();
		$('#groupList').find("input:checkbox[name='groupid']:checked").each(function(){sGroupIds+=','+this.value;});
		if(sGroupIds){
			sGroupIds = sGroupIds.substring(1);
			$.messager.confirm('提示信息','是否要删除这些组织？',function(r){   
    		    if(r){
    		    	$.messager.progress({
	    				text:'提交数据中...'
	    			});
   		    		$.ajax({
   	    				url: "<%=rootPath %>user/delete_group_dowith.action",
   	    			   	type: "POST",
   	    			   	data: {
   	    			   		parentId: nParentId,
   	    			   		groupIds: sGroupIds
   	    			   	},
   	    			   	success: function(msg){
   	    			   		$.messager.progress('close');
   	    			   		$.messager.alert("提示信息","成功删除了"+msg+"个组织","info");
	   	    			   	var node = $('#groupTree').tree('getSelected');
	   	    			   	var arrGroupIds = sGroupIds.split(",");
	   	    			   	for(var i=0;i<arrGroupIds.length;i++){
	   	    			   		var groupId = arrGroupIds[i];
	   	    			   		var delNode = $('#groupTree').tree('find', parseInt(groupId));
	   	    			   		$('#groupTree').tree('remove', delNode.target);
	   	    			   	}
	    					refresh();
   	    			   	},
   	    			 	error:function(){
	    			 		$.messager.progress('close');
	    			 		$.messager.alert('提示信息','删除数据失败！','error');
	    			 	}
   		    		});
    		    }
    		});
		}else{
			$.messager.alert("提示信息","请选择要删除的组织。","warning");
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
		var sObjType = "<s:property value="@com.tfs.irp.group.entity.IrpGroup@TABLE_NAME" />";
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
	
	//跳转到子组织
	function toChild(_nGroupId,_nGroupType){
		var selectNode = $('#groupTree').tree('getSelected');
		if(selectNode.state=='closed'){
			$('#groupTree').tree('expand',selectNode.target);
		}
		var node = $('#groupTree').tree('find', _nGroupId);
		$('#groupTree').tree('select', node.target);
		if(node.state=='closed'){
			$('#groupTree').tree('expand',node.target);
		}
		jump('<%=rootPath %>user/group_list.action?parentId='+node.id+'&groupType='+_nGroupType);
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
		    	refresh("<%=rootPath %>user/group_system_list.action?"+queryString);
		    },   
		    menu:'#listSearchType',   
		    prompt:'请输入检索词'  
		});
	});
</script>
<form id="pageForm">
<s:hidden name="searchWord" id="searchWord" />
<s:hidden name="searchType" id="searchType" />
<s:hidden name="parentId" id="parentId" />
<s:hidden name="groupType" id="groupType" />
<s:hidden name="pageNum" id="pageNum" />
<s:hidden name="pageSize" id="pageSize" />
<s:hidden name="orderField" id="orderField" />
<s:hidden name="orderBy" id="orderBy" />
</form>
<div id="listSearchType" style="width:120px;">
    <div data-options="name:'all'">全部&nbsp;&nbsp;&nbsp;&nbsp;</div>
    <div data-options="name:'groupname'">组织名称</div>
    <div data-options="name:'gdesc'">组织描述</div>
</div>
<table width="100%" id="groupList"  border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
	<tr class="list_th" style="position: relative;">
   	  	<td colspan="4" align="left" style="padding-left: 10px;" nowrap="nowrap">
   	  		<a href="javascript:void(0)" onclick="groupEdit(0)">新建子组</a>
   	  		<a href="javascript:void(0)" onclick="delGroup()">删除组织</a>
			<a href="javascript:void(0)" onclick="refresh()">刷新 </a>
   	  	</td>
   	  	<td colspan="3" align="right" style="padding-right: 2px;"><input id='listSearchText' /></td>
	</tr>
    <tr>
	    <td width="6%" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="checkAll()">全选</a></td>
	    <td width="26%" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('groupname','<s:if test="orderField=='groupname'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">组织名称<s:if test="orderField=='groupname'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='groupname'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="26%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('gdesc','<s:if test="orderField=='gdesc'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">组织描述<s:if test="orderField=='gdesc'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='gdesc'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="16%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('cruser','<s:if test="orderField=='cruser'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">创建用户<s:if test="orderField=='cruser'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='cruser'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="12%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('crtime','<s:if test="orderField=='crtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">创建时间<s:if test="orderField=='crtime'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='crtime'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="8%" align="center" bgcolor="#F5FAFE"  class="main_bright">权限管理</td>
	    <td width="6%" align="center" bgcolor="#F5FAFE"  class="main_bright">操作</td>
    </tr>
    <s:iterator value="irpGroups" status="listStat">
    <tr>
	    <td align="center" bgcolor="#F5FAFE"  class="main_bleft"><input type="checkbox" name="groupid" value="<s:property value="groupid" />" /> <s:property value="(pageNum-1)*pageSize+#listStat.count"/></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="toChild(<s:property value="groupid" />,<s:property value="grouptype" />)"><s:property value="groupname" /></a></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="gdesc" /></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="cruser" /></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:date name="crtime" format="yyyy-MM-dd HH:mm:ss" /></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><a href="javascript:void(0)" onclick="setRight(<s:property value="groupid" />)">编辑权限</a></td>
	    <td align="center" valign="middle" bgcolor="#F5FAFE"  class="main_bright"><img border="0" src="images/icons/pencil.png" title="修改" style="cursor:pointer; margin: 0 5px;" onclick="groupEdit(<s:property value="groupid" />)" /></td>
    </tr>
    </s:iterator>
    <tr bgcolor="#FFFFFF">
		<td colspan="9" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
	</tr>
</table>
</body>
</html>
