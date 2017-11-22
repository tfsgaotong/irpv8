<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!doctype html>
<html lang="en" class="no-js">
<head>
<meta charset="utf-8">
<title>企业社会化智力资源管理系统</title>
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
<link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath %>view/css/easyui.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath %>view/css/icon.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath %>view/css/discuz.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.main-gr .right .combo input{
  border: 0px;
}
.edit_usergourp{
  display: none;
}
.index_title span {font-size:13px;}
.school_info_title, .school_info_title .title a {background-color:#f7f7f7;}
.list6{}
.list6 li{margin-bottom:20px; float:left; display:inline; margin-right:20px; height:28px; overflow:hidden;}
.list6 li a{line-height:28px; height:28px; float:left; display:inline-block; padding:0 15px; background:#5f9ddd; color:#fff; text-decoration:none; font-size:13px;}
.list6 li span{float:left; display:inline-block; height:0; width:0; border-left:6px solid #5f9ddd; border-top:3px solid transparent; border-bottom:3px solid transparent; margin-top:11px;}
.list6 li aside{position:inherit;margin-left:5px; float:left; display:inline-block; font-size:15px; color:#999; line-height:28px; height:28px; cursor:pointer; overflow:hidden; display:none;}
.list6 li:hover aside{display:inline-block;}
.list6 li aside:hover{ color:#5f9ddd;}
.list6 li a:hover{background:#79b6f6;}
</style>

<script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/modernizr.custom.js"></script>

<script type="text/javascript" >
<!--
//lhbDialog变量
var lhbDialog;

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
							$('#oper_'+jDom.attr('id')).prepend('<a id="add" title="添加用户" href="javascript:void(0)" onclick="selectUser('+_nGroupId+')"></a>');
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
<jsp:include page="../include/client_head.jsp" />
<section class="mainBox">
	<nav class="publicNav">
    </nav>
</section>
<section class="mainBox">
	<article class="location"><strong>个人分组</strong></article>
	<section class="layoutLeft">
        <nav class="sets">
        	<div class="folder">
            	<p><a href="<%=rootPath %>user/user_set.action" target="_self">基本资料</a></p>
            </div>
            <div class="folder">
            	<p><a href="<%=rootPath %>user/user_privacy.action" target="_self">隐私设置</a></p>
            </div>
            <div class="folder">
            	<p><a href="<%=rootPath %>user/user_pic.action" target="_self">设置头像</a></p>
            </div>
           <div class="folder">
            	<p class="current"><a href="<%=rootPath %>user/user_group.action" target="_self">个人分组</a></p>
            </div>
            <div class="folder">
            	<p><a href="<%=rootPath %>user/user_tag.action" target="_self">个人标签</a></p>
            </div>
            <div class="folder">
            	<p><a href="<%=rootPath %>user/user_binding.action" target="_self">账号绑定</a></p>
            </div>
        </nav>
  </section>
  <section class="setUp">
        <div class="pan title"><span>个人分组</span></div>
   		<div class="line area20"></div>
        <div class="pan"><em>你可以在这里设置个人分组、在分组中添加用户</em></div>
        <div class="pan">
        <form id="userGroupFrom" method="post" onsubmit="return false;">
            <table>
                <tr>
                    <td class="inputText"><input name="irpGroup.groupname" value="" style="height: 24px; width: 240px;" class="easyui-validatebox" required="true" validType="isExist[2,30,<s:property value="parentId" />,0]" missingMessage="请填写组织名称" /></td><td width="3"></td><td width="20"><font color="red">*</font></td><td class="sub"><input onclick="saveUserGroup()" type="button" value="添加分组"/></td>
                </tr>
            </table>
         </form>
        </div>
        <div class="area20"></div>
        <div class="pan title"><span>我添加的分组</span></div>
		<div class="pan">
			<ul class="setList type1">
			<s:iterator value="irpGroups">
            	<li style="width:830px;">
            		<a href="javascript:void(0)" onclick="showUserGroupList(<s:property value="groupid" />)"><s:property value="groupname" /></a>
            		<aside id="oper_<s:property value="groupid" />">
            			<a id="edit" title="修改" href="javascript:void(0)" onclick="showUserGroupEdit(<s:property value="groupid" />)"></a><a id="del" title="删除" href="javascript:void(0)" onclick="deleteUserGroup(<s:property value="groupid" />)"></a>
           			</aside>
        		</li>
        		<div id="<s:property value="groupid" />" class="edit_usergourp"></div>
			</s:iterator>
            </ul>
		</div>
	</section>
</section>
<jsp:include page="../include/client_foot.jsp" />
</body>
</html>
