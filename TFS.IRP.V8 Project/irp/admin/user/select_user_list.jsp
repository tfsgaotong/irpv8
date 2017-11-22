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
	//分页
	function select_page(_nPageNum){
		$('#dialogPageForm').find('input[name="pageNum"]').val(_nPageNum);
		$('#dialogPageForm').find('input[name="searchWord"]').val($('#dialogPageForm').find('input[name="searchWord"]').val());
		var queryString = $('#dialogPageForm').serialize();
		select_dialogJump("<%=rootPath %>user/select_user_list.action?"+queryString);
	}
	//排序
	function select_orderBy(_sFiled,_sOrderBy){
		$('#dialogPageForm').find('input[name="orderField"]').val(_sFiled);
		$('#dialogPageForm').find('input[name="orderBy"]').val(_sOrderBy);
		$('#dialogPageForm').find('input[name="searchWord"]').val($('#dialogPageForm').find('input[name="searchWord"]').val());
		var queryString = $('#dialogPageForm').serialize();
		select_dialogJump("<%=rootPath %>user/select_user_list.action?"+queryString);
	}
	
	//刷新
	function select_dialogJump(_sUrl){
		$('#selectUser').dialog('refresh',_sUrl);
	}
	
	//初始化
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
				select_dialogJump("<%=rootPath %>user/select_user_list.action?"+queryString);
		    } 
		});
	});
	
	function changeUser(_userId,_userName,_trueName){
		$('#dialogPageForm').find('[name="irpUser.userid"]').val(_userId);
		$('#dialogPageForm').find('[name="irpUser.username"]').val(_userName);
		$('#dialogPageForm').find('[name="irpUser.truename"]').val(_trueName);
	}
	
	function getSelectUserInfo(){
		var userInfo;
		var userId = $('#dialogPageForm').find('[name="irpUser.userid"]').val();
		var userName = $('#dialogPageForm').find('[name="irpUser.username"]').val();
		var trueName = $('#dialogPageForm').find('[name="irpUser.truename"]').val();
		if(userId && !isNaN(userId)){
			if(!trueName || trueName.length==0){
				trueName = userName;
			}
			userInfo = {
				userid: userId,
				username: userName,
				truename:trueName
			};
		}
		return userInfo;
	}
</script>
<form id="dialogPageForm">
	<s:hidden name="irpUser.userid" id="userId" />
	<s:hidden name="irpUser.username" id="userName" />
	<s:hidden name="irpUser.truename" id="trueName" />
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
<table id="selectUserList" width="100%" border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
    <tr>
	    <td width="10%" align="center" bgcolor="#F5FAFE" class="main_bright">序号</td>
	    <td width="35%" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="select_orderBy('username','<s:if test="orderField=='username'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">用户名<s:if test="orderField=='username'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='username'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="35%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="select_orderBy('truename','<s:if test="orderField=='truename'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">真实姓名<s:if test="orderField=='truename'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='truename'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="20%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="select_orderBy('crtime','<s:if test="orderField=='crtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">注册时间<s:if test="orderField=='crtime'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='crtime'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
    </tr>
    <s:iterator value="irpUsers" status="listStat">
    <tr>
	    <td align="left" bgcolor="#F5FAFE" style="padding-left: 5px;" class="main_bleft"><input type="radio" <s:if test='userId==userid'>checked="true"</s:if> name="userid" id="radio_<s:property value="userid" />" value="<s:property value="userid" />" onclick="changeUser(<s:property value="userid" />,'<s:property value="username" />','<s:property value="truename" />')" /><label for="radio_<s:property value="userid" />" style="cursor:pointer;"> <s:property value="(pageNum-1)*pageSize+#listStat.count"/></label></td>
	    <td align="left" bgcolor="#F5FAFE" style="padding-left: 5px;" class="main_bright"><s:property value="username" /></td>
	    <td align="left" bgcolor="#F5FAFE" style="padding-left: 5px;" class="main_bright"><s:property value="truename" /></td>
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
