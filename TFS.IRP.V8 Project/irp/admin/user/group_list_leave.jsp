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
function findALlGroupName(_sAllName, _node){
	var parent = $('#groupAddTree').tree('getParent', _node.target);
	if(parent){
		_sAllName = parent.text+"\\"+_sAllName;
		return findALlGroupName(_sAllName, parent);
	}else{
		return _sAllName;
	}
}


$(function(){
	//初始化组织树
	$('#groupAddTree').tree({
		url:'<%=rootPath%>user/find_tree_node1.action?groupType=1',
		animate:false,
		lines:true,
		checkbox:true,
		cascadeCheck:false,
			onLoadSuccess:function(node, data){
				if(!node){
					var root = $('#groupAddTree').tree('getRoot');
					$(root.target).find(".tree-checkbox").removeClass("tree-checkbox tree-checkbox0");
					$('#groupAddTree').tree('expand',root.target);
				}
			},onCheck:function(node, checked){
				
				if(checked){             
					var sid=$("#parentId").val();
					var allName = findALlGroupName(node.text, node);
					var pare="";
					if(sid=="a"){
						pare=node.id;
					}else if(sid!=node.id){
						if(sid==""){
							pare=node.id;	
						}else{
							pare=sid+","+node.id;
						}
						
					}
					$("#parentId").val(pare); 
				}else{
					var sid=$("#parentId").val();
					var strs= new Array();
					strs=sid.split(",");
					var pare="";
					for(var p in strs){
						
						if(strs[p]!=node.id){
							if(pare==""){
								pare=strs[p]
							}else{
							pare=strs[p]+","+pare;
							}
						}
					}
					$("#parentId").val(pare); 
				}
			}
		

	});
})
</script>

<div id="userAdd" class="easyui-tabs" plain="true" style="width:476px;height:450px; padding: 5px;">  
    <div title="部门信息" selected="true" style="overflow:auto;padding:20px;">
    <form id="addUserForm" method="post">
        <input type="hidden" id="parentId"  name="groupId" value="a"/>
	</form>     

    <div title="所属部门" style="overflow:auto;padding:20px;">
    	<table width="100%">
    		<tr>
    			<td width="50%" align="left" valign="top" rowspan="2"><ul id="groupAddTree" style="width: 200px;float: left;"></ul></td>
    		</tr>
    	</table>
    </div>
   </div>
</div>  
</body>
</html>
