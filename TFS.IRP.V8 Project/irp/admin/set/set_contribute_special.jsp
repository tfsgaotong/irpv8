<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>     
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>特殊配置</title>
</head>
<body>


<script type="text/javascript">
/*全局变量*/
var m_checked = false;
/**
 * 刷新本页面
 */
function refreshContribute(){
	$('#contributespecial').panel('refresh');
}
/**
 * 全选
 */
function checkRankAll(){
	m_checked = !m_checked;
	$("input[name='irpvalueconfigid']").attr("checked",m_checked); 
	} 
/**
*分页
*/
function page(_nPageNum){
	$('#pageNum').val(_nPageNum);
	var queryString = $('#pageForm').serialize();
	
	$('#contributespecial').panel('refresh',"<%=rootPath%>set/contributelistofspecial.action?"+queryString);
}
/**
*排序
*/
function orderByOf(_sFiled,_sOrderBy){
	$('#orderField').val(_sFiled);
	$('#orderBy').val(_sOrderBy);
	var queryString = $('#pageForm').serialize();
	$('#contributespecial').panel('refresh',"<%=rootPath%>set/contributelistofspecial.action?"+queryString);
}	


/**
 * 删除
 */
 function deleteRankEdit(userid,username){
	 	$.messager.confirm("操作提示","您确定重置"+username+"的积分和经验信息吗",function(r){
	 		if(r){
	 			$.messager.progress({
	 				text:'提交数据中...'
	 			});
	 			$.ajax({
	 				type:'post',
	 				url:'<%=rootPath%>set/emptyUserInfo.action?userid='+userid,
	 				success:function(_nStatus){
	 					 $.messager.progress('close');
	 					if (_nStatus==1) {
	 						$.messager.alert("操作提示","数据重置成功");
	 						refreshContribute();
	 					}else{
	 						$.messager.alert("操作提示","数据重置失败");
	 						refreshContribute();
	 					}
	 				},
	 				error:function(){
	 					 $.messager.progress('close');
	 				}
	 			});
	 		}
	 	});
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
		    	$('#contributespecial').panel('refresh',"<%=rootPath %>set/contributelistofspecial.action?"+queryString);
		    },   
		    menu:'#listSearchType',   
		    prompt:'请输入检索词'  
		});
		
	});   
/**
 * 选择用户
 */
function addUser(){
	var dialogdiv=document.createElement("div");
  	dialogdiv.id="selectuser";
  	document.body.appendChild(dialogdiv);
	$('#selectuser').dialog({
  		modal:true,
  		href:'<%=rootPath%>set/selectspecialuser.action',
  		height:520,
  		width:760,
  		title:'选择用户',
  		resizable:true,
  		cache:false,
  		buttons:[{
  			text:'提交',
  			iconCls: 'icon-ok',
  			handler:function(){
  				$('#dialogPageForm').form('submit',{
	    			url : "<%=rootPath %>set/selectspecialuserdispose.action",
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
	    				$('#selectuser').dialog('destroy');
	    				$.messager.progress('close');
	    				if(data==1){
	    					refreshContribute();
	    				}else{
	    					$.messager.alert("提示信息","添加特殊用户失败！","error");
	    				}
	    			}
	    		});
  			}
  		},{
  			text:'取消',
  			iconCls:'icon-cancel',
  			handler:function(){
  				$('#selectuser').dialog('destroy');
  			}
  		}],
  		onClose:function(){
  			$('#selectuser').dialog('destroy');
  		}
  		
  	});
}
/**
 * 移除用户
 */
function removeUser(){
	var nUserIds = "";
	$('#common').find("input:checkbox[name='irpvalueconfigid']:checked").each(function(){nUserIds+=','+this.value;});
	if(nUserIds){
		nUserIds = nUserIds.substring(1);
		$.messager.confirm('提示信息','是否要移除这些用户？',function(r){   
		    if(r){
		    		$.ajax({
	    				url: "<%=rootPath %>set/removespecialusers.action",
	    			   	type: "POST",
	    			   	data: {
	    			   		userIds: nUserIds
	    			   	},
	    			   	success: function(msg){
	    			   		$.messager.alert("提示信息","成功移除了"+msg+"个用户","info");
	    			   		refreshContribute();
	    			   	}
		    		});
		    }
		});
	}else{
		$.messager.alert("提示信息","请选择要移除的用户。","warning");
	}
	
}
/**
 * 移除单个用户
 */
function removeUserSingle(_userid,_username){
	$.messager.confirm('提示信息','您确定要移除'+_username,function(r){  
		 if(r){
			 $.ajax({
					url: "<%=rootPath %>set/removespecialusers.action",
				   	type: "POST",
				   	data: {
				   		userIds:_userid
				   	},
				   	success: function(msg){
				   		$.messager.alert("提示信息","成功移除了"+_username,"info");
				   		refreshContribute();
				   	}
				});
		 }
	});
}
</script>
<form id="pageForm">
<s:hidden name="searchWord" id="searchWord" />
<s:hidden name="searchType" id="searchType" />
<s:hidden name="pageNum" id="pageNum" />
<s:hidden name="pageSize" id="pageSize" />
<s:hidden name="orderField" id="orderField" />
<s:hidden name="orderBy" id="orderBy" />
</form>
<div id="common">
<div id="listSearchType" style="width:120px;">  
    <div data-options="name:'username'">用户名&nbsp;&nbsp;&nbsp;&nbsp;</div>
</div>
<table width="100%" border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
<tr class="list_th" style="position: relative;">
  <td colspan="2" style="padding-left: 10px;">
  <a href="javascript:void(0)" onclick="addUser()">添加用户</a>
  <a href="javascript:void(0)" onclick="removeUser()">移除用户</a>
  <a href="javascript:void(0)" onclick="refreshContribute()">刷新</a>
  
  
   <td colspan="3" align="right" style="padding-right: 2px;"><input id='listSearchText' /></td>
   
 </tr>
 
 <tr>
  <td align="center" width="60" bgcolor="#F5FAFE" class="main_bright" width="8%"><a href="javascript:void(0)" onclick="checkRankAll()">全选</a></td>
  <td align="center" width="200" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderByOf('username','<s:if test="orderField=='username'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">用户名<s:if test="orderField=='username'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='username'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
  <td align="center" width="200" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderByOf('truename','<s:if test="orderField=='truename'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">真实姓名<s:if test="orderField=='truename'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='truename'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
  <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderByOf('nickname','<s:if test="orderField=='nickname'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">昵称<s:if test="orderField=='nickname'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='nickname'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
  <td align="center" width="40" bgcolor="#F5FAFE" class="main_bright" width="15%">操作</td>
 </tr>
 
 <s:iterator value="userlist" status="userliststatus" >
 <tr>
  <td align="left" bgcolor="#F5FAFE" class="main_bright"><input name="irpvalueconfigid" value="<s:property value="userid" />" type="checkbox" >&nbsp;<s:property value="(pageNum-1)*pageSize+#userliststatus.count"/></td>
  <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="username" /></td>
  <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="truename" /></td>
  <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="nickname" /></td>
  <td align="center" bgcolor="#F5FAFE" class="main_bright">
    <img border="0" src="images/icons/cancel.png" title="移除" style="cursor:pointer; margin: 0 5px;" onclick="removeUserSingle(<s:property value="userid" />,'<s:property value="username" />')"  />
  </td>
 </tr>
 </s:iterator>
 <tr bgcolor="#FFFFFF">
   <td colspan="5" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
  </tr>
</table>
</div>
<div id="container" style="display: none;"></div>
</body>
</html>