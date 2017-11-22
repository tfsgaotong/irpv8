<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>企业社会化智力资源管理系统</title>
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/oacss.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/icon.css" />
<jsp:include page="../include/client_skin.jsp" />

<style type="text/css">
body{
	behavior:url(<%=rootPath %>client/js/hover.htc);
}
.STYLE1 {
	color: #0000FF;
	font-weight: bold;
	font-size:18px;
}
.STYLE1 a:hover{
	color: #0000FF;
	font-weight: bold;
	font-size:18px;
}
.main-gr .right .combo input{
  border: 0px;
}
.edit_usergourp{
  display: none;
}
</style>

<script type="text/javascript" src="<%=rootPath %>client/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
<!--
//保存个人组织
function saveUserGroup(){
	$("#userGroupFrom").form('submit',{
		url : "<%=rootPath %>user/user_group_edit_dowith.action",
		success:function(data){
			if(data==1){
				$.dialog.tips('保存成功',1,'32X32/succ.png',function(){
					location.reload(true);
				});
			}else if(data==-1){
				$.dialog.alert('您创建组织个数已达最大',function(){});
			}else{
				$.dialog.tips('保存失败',1,'32X32/fail.png');
			}
		}
	});
}
//保存个人组织
function editUserGroup(){
	$("#userGroupEditFrom").form('submit',{
		url : "<%=rootPath %>user/user_group_edit_dowith.action",
		success:function(data){
			if(data>0){
				$.dialog.tips('保存成功',1,'32X32/succ.png',function(){
					location.reload(true);
				});
			}else{
				$.dialog.tips('保存失败',1,'32X32/fail.png');
			}
		}
	});
}

//删除个人组织
function deleteUserGroup(_nGroupId){
	$.dialog.confirm('确认是否删除此个人组织？', function(){
		$.ajax({
			cache : false,
			type : 'post',
			url : '<%=rootPath%>/user/delete_group_dowith.action',
			data : {
				parentId: <s:property value="parentId" />,
				groupIds: _nGroupId
			},
			success : function(callback) {
				if(callback>0){
					location.reload(true);
					$.dialog.tips('删除成功',1,'32X32/succ.png');
				}else{
					$.dialog.tips('删除失败',1,'32X32/fail.png');
				}
			}
		});
	});
}

//编辑个人组织
function showUserGroupEdit(_nGroupId){
	var editEdu = undefined;
	var editEdus = $(".edit_usergourp");
	editEdus.each(function(index, domEle){
		var jDom = $(domEle);
		if(jDom){
			if(jDom.attr('id')==_nGroupId){
				$.ajax({
					cache : false,
					type : 'post',
					url : '<%=rootPath%>/user/user_group_edit.action',
					data : {
						groupId: _nGroupId
					},
					success : function(callback) {
						jDom.css("display","block");
						jDom.html(callback);
						jDom.find("input:text[name='irpGroup.groupname']").validatebox();
					}
				});
			}else{
				jDom.html('');
				jDom.css("display","none");
			}
			if($('#oper_'+jDom.attr('id')).find('a').length>2){
				$('#oper_'+jDom.attr('id')).find('a:first').remove();
			}
		}
	});
}

//显示个人组织用户
function showUserGroupList(_nGroupId){
	var editEdu = undefined;
	var editEdus = $(".edit_usergourp");
	editEdus.each(function(index, domEle){
		var jDom = $(domEle);
		if(jDom){
			if(jDom.attr('id')==_nGroupId){
				$.ajax({
					cache : false,
					type : 'post',
					url : '<%=rootPath%>/user/user_group_list.action',
					data : {
						groupId: _nGroupId
					},
					success : function(callback) {
						jDom.css("display","block");
						jDom.html(callback);
						if($('#oper_'+jDom.attr('id')).find('a').length<=2){
							$('#oper_'+jDom.attr('id')).prepend('<a href="javascript:void(0)" onclick="selectUser('+_nGroupId+')">添加用户</a> ');
						}
						jDom.find("input:text[name='irpGroup.groupname']").validatebox();
					}
				});
			}else{
				jDom.html('');
				if($('#oper_'+jDom.attr('id')).find('a').length>2){
					$('#oper_'+jDom.attr('id')).find('a:first').remove();
				}
				jDom.css("display","none");
			}
		}
	});
}

//关闭编辑页面
function cancelEdit(_nGroupId){
	var jDom = $(".edit_usergourp[id='"+_nGroupId+"']");
	if(jDom){
		jDom.html('');
		jDom.css("display","none");
	}
}

function findSelectUserContent(_postData){
	var result = $.ajax({
		type: 'POST',
		url: '<%=rootPath %>user/select_user.action',
	    data: _postData,
	    async: false,
	    cache: false
	}).responseText;
	return result;
}

function jump(_form){ 
	var sContent = findSelectUserContent(_form.serialize());  
	lhbDialog.get('selectUser',1).content(sContent); 
}

//选择用户
function selectUser(_groupId){
	//获得内容
	var result = findSelectUserContent('init=1&groupId='+_groupId); 
	//初始化弹出框
	lhbDialog = $.dialog({
		id: 'selectUser',
		title:'选择用户',
		content: result,
		max: false,
	    min: false,
		ok: function(){
			$('#dialogPageForm').form('submit',{
    			url : "<%=rootPath %>user/user_group_import_dowith.action",
    			success:function(data){
    				if(data=="1"){
    					$.dialog.tips('添加成功',1,'32X32/succ.png',function(){
    						showUserGroupList(_groupId);
    					});
    				}else{
    					$.dialog.tips('添加失败',1,'32X32/fail.png');
    				}
    			}
    		});
	    },
	    okVal:'保存',
	    cancelVal: '关闭',
	    cancel: true,
	    lock: true,
	    padding: 0
	});
}

$(function(){
	//lhbDialog变量
	var lhbDialog;
	
	/* 扩展验证  */
	$.extend($.fn.validatebox.defaults.rules, {   
		isExist: {/* 定义最小长度 */
	        validator: function(value, param){   
	            if(value.length < param[0] || value.length > param[1]){
	            	$.fn.validatebox.defaults.rules.isExist.message = $.fn.validatebox.defaults.rules.length.message;
					return false;
	            }else{
	            	var result = $.ajax({
	            		url:'<%=rootPath %>user/check_groupname.action',
	            		type: "POST",
	            		data: {
	            			parentId: param[2],
	            			groupId: param[3],
	            			groupName: value
	            		},
	            		dataType : "json",
	            		async: false,
	            		cache : false
	            	}).responseText;
	            	if(result=='false'){
	            		$.fn.validatebox.defaults.rules.isExist.message = '输入的组织名称已存在';
						return false;
	            	}else{
	            		return true;
	            	}
	            }
	        },
	        message: ''  
	    }
	}); 
});
//-->
</script>
</head>

<body>
<div class="bg01">
<!--头部菜单-->
<s:include value="../include/client_head.jsp"></s:include>
<!--头部菜单end-->
<div class="main-gr">
<!--左侧内容-->
<div class="left">
	<div class="leftmenu">
    	<h1>账号设置</h1>
        <dl>
        	<dt><em class="a"></em><a href="<%=rootPath %>user/user_set.action">个人资料</a></dt>
            	<dd><a href="<%=rootPath %>user/user_set.action">基本信息</a>
            	<a href="<%=rootPath %>user/user_edu.action">教育信息</a>
            	<a href="<%=rootPath %>user/user_career.action">职业信息</a></dd>
       	 	<dt><em class="b"></em><a href="<%=rootPath %>user/user_pic.action">修改头像</a></dt>
       	  	<dt><em class="c"></em><a href="<%=rootPath %>user/user_tag.action">个人标签</a></dt>
            <dt><em class="d"></em><a href="<%=rootPath %>user/user_privacy.action">隐私设置</a></dt>
			
			<dt><em class="g"></em><a href="<%=rootPath %>user/user_group.action" class="x">个人分组</a></dt>
			<dt><em class="i"></em><a href="<%=rootPath %>site/user_subject.action">个人专题</a></dt>
			<dt><em class="h"></em><a href="<%=rootPath%>site/personallchanneltoshow.action?personid=<s:property value="@com.tfs.irp.util.LoginUtil@getLoginUserId()" />">个人分类</a></dt>
        	<dt><em class="e"></em><a href="<%=rootPath %>user/user_binding.action">账号绑定</a></dt>
        </dl>
    </div>
</div>
<!--左侧内容结束-->
<!--右侧内容-->
<div class="right">
	<div class="gr"><h1>个人分组</h1></div>
	<div id="addEdu" style="padding: 10px 20px;"><form id="userGroupFrom" method="post" onsubmit="return false;">
		<input name="irpGroup.groupname" value="" style="height: 24px; width: 240px;" class="easyui-validatebox" required="true" validType="isExist[2,30,<s:property value="parentId" />,0]" missingMessage="请填写组织名称" />&nbsp;<font color="red">*</font><a href="javascript:void(0);" onclick="saveUserGroup()" class="zhuanz1">添加组织</a>
	</form></div>
	<div style="border-top:1px solid #E9E9E9;">
		<div style="font-size: 14px;font-weight: bold;">我已经添加的组织</div>
		<div>
			<s:iterator value="irpGroups">
			<div class="school_info">
				<div class="school_info_title">
					<span id="oper_<s:property value="groupid" />" style="float: right;"><a href="javascript:void(0)" onclick="showUserGroupEdit(<s:property value="groupid" />)">修改</a> <a href="javascript:void(0)" onclick="deleteUserGroup(<s:property value="groupid" />)">删除</a></span>
					<span class="title"><b><a href="javascript:void(0)" onclick="showUserGroupList(<s:property value="groupid" />)"><s:property value="groupname" /></a></b></span>
				</div>
				<div id="<s:property value="groupid" />" class="edit_usergourp">
				</div>
			</div>
			</s:iterator>
		</div>
	</div>
</div>
<!--右侧内容结束-->
<!--尾部信息-->
<s:include value="../include/client_foot.jsp"></s:include>
<!--尾部信息end-->
</div>
</div>
</body>
</html>
