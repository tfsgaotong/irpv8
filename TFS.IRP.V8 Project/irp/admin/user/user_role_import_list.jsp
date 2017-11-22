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
	var m_import_checked = false;
	
	//分页
	function import_page(_nPageNum){
		$('#dialogPageForm').find('input[name="pageNum"]').val(_nPageNum);
		$('#dialogPageForm').find('input[name="searchWord"]').val($('#dialogPageForm').find('input[name="searchWord"]').val());
		var queryString = $('#dialogPageForm').serialize();
		import_dialogJump("<%=rootPath %>user/user_role_import_list.action?"+queryString);
	}
	//排序
	function import_orderBy(_sFiled,_sOrderBy){
		$('#dialogPageForm').find('input[name="orderField"]').val(_sFiled);
		$('#dialogPageForm').find('input[name="orderBy"]').val(_sOrderBy);
		$('#dialogPageForm').find('input[name="searchWord"]').val($('#dialogPageForm').find('input[name="searchWord"]').val());
		var queryString = $('#dialogPageForm').serialize();
		import_dialogJump("<%=rootPath %>user/user_role_import_list.action?"+queryString);
	}
	
	//全选
	function import_checkAll(){
		m_import_checked = !m_import_checked;
		var checks = $('#importUserList').find('input[name="userid"]');
		for(var i=0;i<checks.length;i++){
			if(checks[i] && checks[i].checked!=m_import_checked){
				checks[i].checked = m_import_checked;
				import_checkClick(checks[i]);
			}
		}
	}
	
	//刷新
	function import_dialogJump(_sUrl){
		$('#importUser').dialog('refresh',_sUrl);
	}
	$(function(){
		$('#sWord').searchbox({
			width:240,
		    menu:'#sType',
		    prompt:'请输入检索词',
		    searcher:function(value,name){
		    	$('#dialogPageForm').find('input[name="searchWord"]').val(value);
		    	$('#dialogPageForm').find('input[name="searchType"]').val(name);
		    	$('#dialogPageForm').find('input[name="pageNum"]').val('1');
		    	$('#dialogPageForm').find('input[name="orderField"]').val('');
		    	$('#dialogPageForm').find('input[name="orderBy"]').val('');
		    	var queryString = $('#dialogPageForm').serialize();
				import_dialogJump("<%=rootPath %>user/user_role_import_list.action?"+queryString);
		    } 
		});
		//当前页面是否全部选中
		var isCheckedAll = true;
		var checks = $('#importUserList').find('input[name="userid"]');
		for(var i=0;i<checks.length;i++){
			if(checks[i] && !checks[i].checked){
				isCheckedAll = false;
				break;
			}
		}
		if(isCheckedAll){
			m_import_checked = true;
		}
	});
	
	//记录选择用户
	function import_checkClick(checkBox){
		var sUserIds = $('#dialogPageForm').find('input[name="userIds"]').val();
		var nVal = checkBox.value;
		var arr;
		if(sUserIds){
			arr = sUserIds.split(',');
		}else{
			arr = new Array();
		}
		var nIndex = $.inArray(nVal, arr);
		if(checkBox.checked){
			arr.push(nVal);
		}else{
			arr.splice(nIndex, 1);
		}
		$('#dialogPageForm').find('input[name="userIds"]').val(arr.join(','));
	}
</script>
<form id="dialogPageForm">
	<s:hidden name="roleId" id="roleId" />
	<s:hidden name="userIds" id="userIds" />
	<s:hidden name="pageNum" id="pageNum" />
	<s:hidden name="pageSize" id="pageSize" />
	<s:hidden name="orderField" id="orderField" />
	<s:hidden name="orderBy" id="orderBy" />
	<s:hidden name="searchWord" id="searchWord" />
	<s:hidden name="searchType" id="searchType" />
</form>
<div style="padding:5px; text-align: center;">
<div style="padding:0px 10px 5px 0px; float: right;">
<input name="sWord" id="sWord" />
<div id="sType">  
    <div data-options="name:'all'">全部&nbsp;&nbsp;&nbsp;&nbsp;</div>
    <div data-options="name:'username'">用户名&nbsp;&nbsp;</div>
    <div data-options="name:'truename'">真实姓名</div>
</div>
</div>
<table id="importUserList" width="100%" border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
    <tr>
	    <td width="10%" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="import_checkAll()">全选</a></td>
	    <td width="35%" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="import_orderBy('username','<s:if test="orderField=='username'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">用户名<s:if test="orderField=='username'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='username'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="35%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="import_orderBy('truename','<s:if test="orderField=='truename'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">真实姓名<s:if test="orderField=='truename'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='truename'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="20%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="import_orderBy('crtime','<s:if test="orderField=='crtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">注册时间<s:if test="orderField=='crtime'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='crtime'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
    </tr>
    <s:set var="arr" value='userIds.split(",")' />
    <s:iterator value="irpUsers" status="listStat">
    <tr>
	    <td align="center" bgcolor="#F5FAFE"  class="main_bleft"><input type="checkbox" <s:if test='@org.apache.commons.lang.ArrayUtils@contains(#arr,""+userid)'>checked="true"</s:if> onclick="import_checkClick(this)" name="userid" value="<s:property value="userid" />" /> <s:property value="(pageNum-1)*pageSize+#listStat.count"/></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="username" /></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="truename" /></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:date name="crtime" format="yyyy-MM-dd HH:mm:ss" /></td>
    </tr>
    </s:iterator>
    <tr bgcolor="#FFFFFF">
		<td colspan="9" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
	</tr>
</table>
</div>
</body>
</html>
