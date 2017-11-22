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
function findTreeALlName(_sTreeId, _sAllName, _node){
	var parent = $(_sTreeId).tree('getParent', _node.target);
	if(parent){
		_sAllName = parent.text+"\\"+_sAllName;
		return findTreeALlName(_sTreeId, _sAllName, parent);
	}else{
		return _sAllName;
	}
}
$(function(){
	//初始化组织树
	$('#groupAddTree').tree({
		url:'<%=rootPath%>user/find_tree_node.action?userId=<s:property value="irpUser.userid" />&groupType=1',
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
		},
		onCheck:function(node, checked){
			if(checked){
				var allName = findTreeALlName('#groupAddTree', node.text, node);
				var isExist = false;
				$("#selectGroups option").each(function () {
					if(this.id=="opt"+node.id){
						isExist = true;
						return;
					}
	            });
				if(!isExist){
					$("#selectGroups").append("<option id='opt"+node.id+"' title='"+allName+"' value='"+node.id+"'>"+allName+"</option>");	
				}
			}else{
				$("#selectGroups option[id='opt"+node.id+"']").remove();
			}
		}
	});
	
<s:if test="irpUser.isExpert()">
	//初始化专家分类树
	$('#classifyAddTree').tree({
		url:'<%=rootPath%>category/find_tree_node.action?userId=<s:property value="irpUser.userid" />',
		animate:false,
		lines:true,
		checkbox:true,
		cascadeCheck:false,
		onLoadSuccess:function(node, data){
			if(!node){
				var root = $('#classifyAddTree').tree('getRoot');
				$(root.target).find(".tree-checkbox").removeClass("tree-checkbox tree-checkbox0");
				$('#classifyAddTree').tree('expand',root.target);
			}
		},
		onCheck:function(node, checked){
			if(checked){
				var allName = findTreeALlName('#classifyAddTree', node.text, node);
				var isExist = false;
				$("#selectClassify option").each(function () {
					if(this.id=="expopt"+node.id){
						isExist = true;
						return;
					}
	            });
				if(!isExist){
					$("#selectClassify").append("<option id='expopt"+node.id+"' title='"+allName+"' value='"+node.id+"'>"+allName+"</option>");
				}
			}else{
				$("#selectClassify option[id='expopt"+node.id+"']").remove();
				
				
			}
		}
	});
</s:if>
	
	//选择一项
	$("#addSysOne").click(function(){
		$("#sysRoles option:selected").clone().appendTo("#sysRolesTo");
		$("#sysRoles option:selected").remove();
	});
	$("#addSpeOne").click(function(){
		$("#speRoles option:selected").clone().appendTo("#speRolesTo");
		$("#speRoles option:selected").remove();
	});
	
	//移除一项
	$("#removeSysOne").click(function(){
		$("#sysRolesTo option:selected").clone().appendTo("#sysRoles");
		$("#sysRolesTo option:selected").remove();
	});
	$("#removeSpeOne").click(function(){
		$("#speRolesTo option:selected").clone().appendTo("#speRoles");
		$("#speRolesTo option:selected").remove();
	});
	
	//双击左侧
	$("#sysRoles").dblclick(function(){
		$("#sysRoles option:selected").clone().appendTo("#sysRolesTo");
		$("#sysRoles option:selected").remove();
	});
	$("#speRoles").dblclick(function(){
		$("#speRoles option:selected").clone().appendTo("#speRolesTo");
		$("#speRoles option:selected").remove();
	});
	
	//双击右侧
	$("#sysRolesTo").dblclick(function(){
		$("#sysRolesTo option:selected").clone().appendTo("#sysRoles");
		$("#sysRolesTo option:selected").remove();
	});
	$("#speRolesTo").dblclick(function(){
		$("#speRolesTo option:selected").clone().appendTo("#speRoles");
		$("#speRolesTo option:selected").remove();
	});
	
	/* 扩展验证  */
	$.extend($.fn.validatebox.defaults.rules, {   
		mobile : {
			validator : function(value, param) {
				return /^1[34578][0-9]\d{8}$/.test(value);
			},
			message : '请输入有效的手机号码'
		},
		maxLength : {
			validator: function(value, param){   
	            return value.length < param[0];   
	        },
	        message: '输入内容长度不能超过{0}'  
		},
		extEmail: {
			validator: function(value, param){
				if(!/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i.test(value)){
					$.fn.validatebox.defaults.rules.extEmail.message = $.fn.validatebox.defaults.rules.email.message;
					return false;
				}else if(value.length > param[0]){
					$.fn.validatebox.defaults.rules.extEmail.message = '输入内容长度不能超过{0}';
					return false;
				}else {
					return true;
				}
	        },
	        message: ''  
		}
	});
});
</script>

<div id="userAdd" class="easyui-tabs" plain="true" style="width:476px;height:450px; padding: 5px;">  
    <div title="用户信息" selected="true" style="overflow:auto;padding:20px;">
    <form id="addUserForm" method="post">
    <input type="hidden" name="irpUser.userid" value="<s:property value="irpUser.userid" />" />
    <input type="hidden" name="groupIds" />
    <input type="hidden" name="roleIds" />
    <input type="hidden" name="classifyIds" />
    <input type="hidden" name="experttype" />
		<table width="100%" cellpadding="0" cellspacing="4">
			<tr>
				<td width="100">用户名：</td>
				<td><input type="text" value="<s:property value="irpUser.username" />" disabled="disabled" /></td>
			</tr>
			<tr>
				<td>真实姓名：</td>
				<td><input type="text" name="irpUser.truename" value="<s:property value="irpUser.truename" />" class="easyui-validatebox" required="true" validType="length[2,20]" missingMessage="请填写真实姓名" /></td>
			</tr>
			<tr>
				<td>昵称：</td>
				<td><input type="text" name="irpUser.nickname" value="<s:property value="irpUser.nickname" />" class="easyui-validatebox" validType="maxLength[20]" /></td>
			</tr>
			<tr>
				<td>电子邮箱：</td>
				<td><input type="text" name="irpUser.email" value="<s:property value="irpUser.email" />" class="easyui-validatebox" validType="extEmail[30]" /></td>
			</tr>
			<tr>
				<td>手机号码：</td>
				<td><input type="text" name="irpUser.mobile" value="<s:property value="irpUser.mobile" />" class="easyui-validatebox" validType="mobile" /></td>
			</tr>
			<tr>
				<td>联系电话：</td>
				<td><input type="text" name="irpUser.tel" value="<s:property value="irpUser.tel" />" class="easyui-validatebox" validType="maxLength[30]" /></td>
			</tr>
			<tr>
				<td>详细地址：</td>
				<td><input type="text" name="irpUser.location" value="<s:property value="irpUser.location" />" class="easyui-validatebox" validType="maxLength[60]" /></td>
			</tr>
			<tr>
				<td>密级：</td>
				<td>
				<select name="irpUser.dense">
				<s:iterator value="denseMap" status="index">
					<option <s:if test="irpUser.dense==key">selected="selected"</s:if>  value='<s:property value="key"/>'><s:property value="value"/></option>
				</s:iterator>
				</select></td>
			</tr>
		</table>
	</form>
    </div>
    <div title="所属组织" style="overflow:auto;padding:20px;">
    	<table width="100%">
    		<tr>
    			<td width="50%" align="left" valign="top" rowspan="2"><ul id="groupAddTree" style="width: 200px;float: left;"></ul></td>
    			<td width="50%" align="center" valign="top">已选择的组织</td>
    		</tr>
    		<tr>
    			<td align="right" valign="top"><select id="selectGroups" multiple="multiple" size="10" style="width: 200px; height: 330px;overflow: auto; border: 1px #cad9ea solid;">
    			<s:iterator value="groupsInfo">
				<option id="opt<s:property value="key" />" title="<s:property value="value" />" value="<s:property value="key" />"><s:property value="value" /></option>
				</s:iterator>
    			</select></td>
    		</tr>
    	</table>
    </div>
    <s:set var="arr" value='roleIds.split(",")' />
    <div title="系统角色" style="overflow:auto;padding:20px;">
    	<table width="100%">
    		<tr>
    			<td width="40%" align="center">
    				可选的系统角色
    				<select name="sysRoles" id="sysRoles" multiple="multiple" size="10" style="width: 170px; height: 330px; border: 1px #cad9ea solid;">
					<s:iterator value="irpSysRoles">
						<s:if test='!@org.apache.commons.lang.ArrayUtils@contains(#arr,""+roleid)'>
						<option value="<s:property value="roleid" />"><s:property value="roledesc" /></option>
						</s:if>
					</s:iterator>
					</select>
				</td>
    			<td width="20%" align="center" valign="middle">
    				<div style="margin: 5px;"><a href="javascript:void(0)" id="addSysOne" class="easyui-linkbutton">添加&gt;&gt;</a></div>
					<div style="margin: 5px;"><a href="javascript:void(0)" id="removeSysOne" class="easyui-linkbutton">&lt;&lt;删除</a></div>
				</td>
    			<td width="40%" align="center">
    				已有的系统角色
    				<select name="sysRolesTo" id="sysRolesTo" multiple="multiple" size="10" style="width: 170px; height: 330px; border: 1px #cad9ea solid;">
    				<s:iterator value="irpSysRoles">
						<s:if test='@org.apache.commons.lang.ArrayUtils@contains(#arr,""+roleid)'>
						<option value="<s:property value="roleid" />" <s:if test="irpUser.userid==1L && roleid==1L">disabled="disabled"</s:if>><s:property value="roledesc" /></option>
						</s:if>
					</s:iterator>
    				</select>
    			</td>
    		</tr>
    	</table>
    </div>
    <div title="特殊角色" style="overflow:auto;padding:20px;">
    	<table width="100%">
    		<tr>
    			<td width="40%" align="center">
    				可选的特殊角色
    				<select name="speRoles" id="speRoles" multiple="multiple" size="10" style="width: 170px; height: 330px; border: 1px #cad9ea solid;">
						<s:iterator value="irpSpeRoles">
						<s:if test='!@org.apache.commons.lang.ArrayUtils@contains(#arr,""+roleid)'>
						<option value="<s:property value="roleid" />"><s:property value="roledesc" /></option>
						</s:if>
						</s:iterator>
					</select>
				</td>
    			<td width="20%" align="center" valign="middle">
    				<div style="margin: 5px;"><a href="javascript:void(0)" id="addSpeOne" class="easyui-linkbutton">添加&gt;&gt;</a></div>
					<div style="margin: 5px;"><a href="javascript:void(0)" id="removeSpeOne" class="easyui-linkbutton">&lt;&lt;删除</a></div>
				</td>
    			<td width="40%" align="center">
    				已有的特殊角色
    				<select name="speRolesTo" id="speRolesTo" multiple="multiple" size="10" style="width: 170px; height: 330px; border: 1px #cad9ea solid;">
    				<s:iterator value="irpSpeRoles">
					<s:if test='@org.apache.commons.lang.ArrayUtils@contains(#arr,""+roleid)'>
					<option value="<s:property value="roleid" />"><s:property value="roledesc" /></option>
					</s:if>
					</s:iterator>
    				</select>
    			</td>
    		</tr>
    	</table>
    </div>
    <s:if test="irpUser.isExpert()">
    <div title="专家分类" style="overflow:auto;padding:20px;">
    	<table width="100%">
    		<tr>
    			<td width="50%" align="left" valign="top" rowspan="2"><ul id="classifyAddTree" style="width: 200px;float: left;"></ul></td>
    			<td width="50%" align="center" valign="top">已选择的分类</td>
    		</tr>
    		<tr>
    			<td align="right" valign="top">
    			<select id="selectClassify" multiple="multiple" size="10" style="width: 200px; height: 330px;overflow: auto; border: 1px #cad9ea solid;">
    			<s:iterator value="categorysInfo">
				<option id="expopt<s:property value="key" />" title="<s:property value="value" />" value="<s:property value="key" />"><s:property value="value" /></option>
				</s:iterator>
    			</select>
    			<s:if test="irpUser.experttype==1">
    			是否推荐：
    			<select id="isRecommend" onclick="recommend(this)">
    				<option value ="0">否</option>
  					<option selected="selected" value="1">是</option>
    			</select>
    			</s:if>
    			<s:else>
    			是否推荐：
    			<select id="isRecommend" onclick="recommend(this)">
    				<option selected="selected" value ="0">否</option>
  					<option value="1">是</option>
    			</select>
    			</s:else>
    			</td>
    		</tr>
    	</table>
    </div>
    </s:if>
</div>  
</body>
</html>
