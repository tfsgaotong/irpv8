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
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
//全局变量，
var m_checked = false;
/**
 * 刷新本页面
 */
function refreshCata(){
	$('#oauthlogin').panel('refresh');
}
/**
 * 修改配置
 */
function updateEdit(_configid){
	var dialogdiv=document.createElement("div");
	dialogdiv.id="updateconfig";
	document.body.appendChild(dialogdiv);
	$('#updateconfig').dialog({
		modal:true,
		href:'<%=rootPath%>set/updateOtherSet.action?configid='+_configid,
		height:200,
		width:400,
		title:'修改快速登录',
		resizable:true,
		cache:false,
		buttons:[{
			text:'提交',
			iconCls: 'icon-ok',
			handler:function(){
				$('#editconfig').form('submit',{
					url:'<%=rootPath%>set/update_oauth_login.action?configid='+_configid,
					onSubmit:function(){
						var isValid = $(this).form('validate');
	    				if (isValid){
	    					$.messager.progress({
	    	    				text:'提交数据中...'
	    	    			});
	    				}
	    				return isValid;
					},		
					success:function(_updateCataStat){
						$.messager.progress('close');
						$('#updateconfig').dialog('destroy');
						if (_updateCataStat==1) {
							refreshCata();
						}else{
							$.messager.alert("失败","修改快速登录失败了");
							refreshCata();
						}
					}
				});
			}
		},{
			text:'取消',
			iconCls:'icon-cancel',
			handler:function(){
				$('#updateconfig').dialog('destroy');
			}
		}],
		onClose:function(){
			$('#updateconfig').dialog('destroy');
		}
		
	});
}
/**
*分页
*/
function page(_nPageNum){
	$('#pageNum').val(_nPageNum);
	var queryString = $('#pageForm').serialize();
	
	$('#oauthlogin').panel('refresh',"<%=rootPath%>set/oauth_login_set.action?"+queryString);
}
/**
*排序
*/
function orderBy(_sFiled,_sOrderBy){
	$('#orderField').val(_sFiled);
	$('#orderBy').val(_sOrderBy);
	var queryString = $('#pageForm').serialize();
	$('#oauthlogin').panel('refresh',"<%=rootPath%>set/oauth_login_set.action?"+queryString);
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
	    	$('#oauthlogin').panel('refresh',"<%=rootPath %>set/oauth_login_set.action?"+queryString);
	    },   
	    menu:'#listSearchType',   
	    prompt:'请输入检索词'  
	});
});		
</script>

<form id="pageForm">
<s:hidden name="searchWord" id="searchWord" />
<s:hidden name="searchType" id="searchType" />
<s:hidden name="pageNum" id="pageNum" />
<s:hidden name="pageSize" id="pageSize" />
<s:hidden name="orderField" id="orderField" />
<s:hidden name="orderBy" id="orderBy" />
</form>
<div id="listSearchType" style="width:120px;">  
    <div data-options="name:'all'">全部&nbsp;&nbsp;&nbsp;&nbsp;</div>
    <div data-options="name:'ckey'">名称</div>
    <div data-options="name:'cvalue'">内容</div>
    <div data-options="name:'cdesc'">描述</div>
</div>
<table width="100%" border="0" align="center" cellpadding="10"
		cellspacing="1"  id="table1" bgcolor="#cad9ea">
		<tr class="list_th" style="position: relative;">
		 <td colspan="2" style="padding-left: 10px;">
		  <a href="javascript:void(0)" onclick="refreshCata()">刷新</a>
		 </td>
		  <td colspan="4" align="right" style="padding-right: 2px;"><input id='listSearchText' /></td>
		</tr>
		<tr>
		 <td align="center" width="60" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="checkAll()">全选</a></td>
		 <td align="center" width="200" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('ckey','<s:if test="orderField=='ckey'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">名称<s:if test="orderField=='ckey'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='ckey'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" width="200" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('cvalue','<s:if test="orderField=='cvalue'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">内容<s:if test="orderField=='cvalue'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='cvalue'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('cdesc','<s:if test="orderField=='cdesc'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">说明<s:if test="orderField=='cdesc'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='cdesc'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" width="60" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('modified','<s:if test="orderField=='modified'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">是否可修改<s:if test="orderField=='modified'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='modified'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" width="60" bgcolor="#F5FAFE" class="main_bright">操作</td>
		</tr> 
		<s:iterator value="irpConfig" status="irpconfigstatus">
		<tr>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright"><input name="irpconfigcatach" type="checkbox" value="<s:property value='configid' />">&nbsp;<s:property value="(pageNum-1)*pageSize+#irpconfigstatus.count"/></td>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="ckey" /></td>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="cvalue" /></td>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="cdesc" /></td>
		 <s:if test="modified==0">
		 <td align="center" bgcolor="#F5FAFE" class="main_bright">是</td>
		 </s:if>
		 <s:elseif test="modified==1">
		 <td align="center" bgcolor="#F5FAFE" class="main_bright">否</td>
		 </s:elseif> 
		 <td align="center" bgcolor="#F5FAFE" class="main_bright">
		   <img border="0" src="images/icons/pencil.png" title="修改" style="cursor:pointer; margin: 0 5px;" onclick="updateEdit(<s:property value="configid" />)"  />
		 </td>
		</tr>
		</s:iterator>
		<tr bgcolor="#FFFFFF">
		 <td colspan="6" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
		</tr>
      </table>
</body>
</html>