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
		url:'<%=rootPath%>user/find_tree_node.action?groupType=1',
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
				var allName = findALlGroupName(node.text, node);
				$("#selectGroups").append("<option id='opt"+node.id+"' title='"+allName+"' value='"+node.id+"'>"+allName+"</option>"); 
			}else{
				$("#selectGroups option[id='opt"+node.id+"']").remove();
			}
		}
	});
	
	//双击已选择组织时，取消选择的组织
	$("#selectGroups").dblclick(function(){
		var opt = $("#selectGroups option:selected");
		if(opt){
			var node = $("#groupAddTree").tree("find", opt.val());
			if(node){
				$("#groupAddTree").tree("uncheck",node.target);
			}
			opt.remove();
		}
	});
	
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
		isExist: {/* 定义最小长度 */
	        validator: function(value, param){   
	            if(value.length < param[0] || value.length > param[1]){
	            	$.fn.validatebox.defaults.rules.isExist.message = $.fn.validatebox.defaults.rules.length.message;
					return false;
	            }else{
	            	var result = $.ajax({
	            		url:'<%=rootPath %>user/check_username.action',
	            		type: "POST",
	            		data: {
	            			userId: <s:property value="irpUser.userid" />,
	            			userName: value
	            		},
	            		dataType : "json",
	            		async: false,
	            		cache : false
	            	}).responseText;
	            	if(result=='false'){
	            		$.fn.validatebox.defaults.rules.isExist.message = '输入的用户名已存在';
						return false;
	            	}else{
	            		return true;
	            	}
	            }
	        },
	        message: ''  
	    },
	    eqPassword : {/* 扩展验证两次密码 */
			validator : function(value, param) {
				return value == $(param[0]).val();
			},
			message : '输入两次密码不一致'
		},
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
		<table width="100%" cellpadding="0" cellspacing="4">
			<tr>
				<td width="100">用户名：</td>
				<td><input type="text" name="irpUser.username" class="easyui-validatebox" required="true" validType="isExist[2,50]" missingMessage="请填写用户名" /></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" name="repassword" class="easyui-validatebox" required="true" validType="length[6,50]" missingMessage="请填写密码" /></td>
			</tr>
			<tr>
				<td>确认密码：</td>
				<td><input type="password" name="irpUser.password" class="easyui-validatebox" required="true" missingMessage="请填写确认密码" validType="eqPassword['#addUserForm input[name=repassword]']"  /></td>
			</tr>
			<tr>
				<td>真实姓名：</td>
				<td><input type="text" name="irpUser.truename" class="easyui-validatebox" required="true" validType="length[2,50]" missingMessage="请填写真实姓名" /></td>
			</tr>
			<tr>
				<td>昵称：</td>
				<td><input type="text" name="irpUser.nickname" class="easyui-validatebox" validType="maxLength[50]" /></td>
			</tr>
			<tr>
				<td>电子邮箱：</td>
				<td><input type="text" name="irpUser.email" class="easyui-validatebox" validType="extEmail[30]" /></td>
			</tr>
			<tr>
				<td>手机号码：</td>
				<td><input type="text" name="irpUser.mobile" class="easyui-validatebox" validType="mobile" /></td>
			</tr>
			<tr>
				<td>联系电话：</td>
				<td><input type="text" name="irpUser.tel" class="easyui-validatebox" validType="maxLength[30]" /></td>
			</tr>
			<tr>
				<td>详细地址：</td>
				<td><input type="text" name="irpUser.location" class="easyui-validatebox" validType="maxLength[60]" /></td>
			</tr>
			<tr>
				<td>密级：</td>
				<td><select name="irpUser.dense">
				<s:iterator value="denseMap" status="index">
				<s:if test="#index.first">
				<option selected="selected" value='<s:property value="key"/>'><s:property value="value"/></option>
				</s:if>
				<s:else>
					<option value='<s:property value="key"/>'><s:property value="value"/></option>
				</s:else>
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
    			<td align="right" valign="top"><select id="selectGroups" multiple="multiple" size="10" style="width: 200px; height: 330px;overflow: auto;border: 1px #cad9ea solid;"></select></td>
    		</tr>
    	</table>
    </div>
    <s:set var="everyone" />
    <div title="系统角色" style="overflow:auto;padding:20px;">
    	<table width="100%">
    		<tr>
    			<td width="40%" align="center">
    				可选的系统角色
    				<select name="sysRoles" id="sysRoles" multiple="multiple" size="10" style="width: 170px; height: 330px;border: 1px #cad9ea solid;">
						<s:iterator value="irpSysRoles" var="irpRole">
						<s:if test="roleid!=@com.tfs.irp.role.entity.IrpRole@EVERYONE_ROLE_ID">
						<option value="<s:property value="roleid" />"><s:property value="roledesc" /></option>
						</s:if>
						<s:else>
						<s:set var="everyone" value="#irpRole" />
						</s:else>
						</s:iterator>
					</select>
				</td>
    			<td width="20%" align="center" valign="middle">
    				<div style="margin: 5px;"><a href="javascript:void(0)" id="addSysOne" class="easyui-linkbutton">添加&gt;&gt;</a></div>
					<div style="margin: 5px;"><a href="javascript:void(0)" id="removeSysOne" class="easyui-linkbutton">&lt;&lt;删除</a></div>
				</td>
    			<td width="40%" align="center">
    				已有的系统角色
    				<select name="sysRolesTo" id="sysRolesTo" multiple="multiple" size="10" style="width: 170px; height: 330px;border: 1px #cad9ea solid;">
						<s:if test="#everyone!=null">
						<option value="<s:property value="#everyone.roleid" />"><s:property value="#everyone.roledesc" /></option>
						</s:if>
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
    				<select name="speRoles" id="speRoles" multiple="multiple" size="10" style="width: 170px; height: 330px;border: 1px #cad9ea solid;">
						<s:iterator value="irpSpeRoles">
						<option value="<s:property value="roleid" />"><s:property value="roledesc" /></option>
						</s:iterator>
					</select>
				</td>
    			<td width="20%" align="center" valign="middle">
    				<div style="margin: 5px;"><a href="javascript:void(0)" id="addSpeOne" class="easyui-linkbutton">添加&gt;&gt;</a></div>
					<div style="margin: 5px;"><a href="javascript:void(0)" id="removeSpeOne" class="easyui-linkbutton">&lt;&lt;删除</a></div>
				</td>
    			<td width="40%" align="center">
    				已有的特殊角色
    				<select name="speRolesTo" id="speRolesTo" multiple="multiple" size="10" style="width: 170px; height: 330px;border: 1px #cad9ea solid;"></select>
    			</td>
    		</tr>
    	</table>
    </div>
</div>  
</body>
</html>
