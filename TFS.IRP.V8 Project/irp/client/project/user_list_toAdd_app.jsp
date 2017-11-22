<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>选择用户</title>
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/oacss.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/icon.css" />
<jsp:include page="../include/client_skin.jsp" />
</head> 
<body>
<script type="text/javascript">
	//全局变量，
	var m_checked = false; 
	function jump2(_form){   
		var queryString = _form.serialize();
		var result = $.ajax({
							type: 'POST',
							url: '<%=rootPath %>phone/select_allusertask.action?token='+$('#tokens').val(),
							data:queryString,
						    async: false,
						    cache: false
						}).responseText;    
		ProjectPersonDialog.get('selectUser',1).content(result); 
	} 
	//分页
	function page(_nPageNum){
		$('#dialogPageForm').find('input[name="pageNum"]').val(_nPageNum);
		$('#dialogPageForm').find('input[name="searchWord"]').val($('#dialogPageForm').find('input[name="searchWord"]').val());
		jump2($('#dialogPageForm'));
	}
	//排序
	function orderBy(_sFiled,_sOrderBy){
		$('#dialogPageForm').find('input[name="orderField"]').val(_sFiled);
		$('#dialogPageForm').find('input[name="orderBy"]').val(_sOrderBy);
		$('#dialogPageForm').find('input[name="searchWord"]').val($('#dialogPageForm').find('input[name="searchWord"]').val());
		jump2($('#dialogPageForm'));
	}
	
	//全选
	function checkAll(){
		m_checked = !m_checked;
		var checks = $('#importUserList').find('input[name="userid"]');
		for(var i=0;i<checks.length;i++){
			if(checks[i] && checks[i].checked!=m_checked){
				checks[i].checked = m_checked;
				checkClick(checks[i]);
			}
		}
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
		    	jump2($('#dialogPageForm'));
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
			m_checked = true;
		}
	});
	
	//记录选择用户
	function checkClick(checkBox){
		var sUserIds = $('#dialogPageForm').find('input[name="userIds"]').val();
		var sUserNames = $('#dialogPageForm').find('input[name="userNames"]').val();
		var nVal = $(checkBox).val();
		var sName = $(checkBox).attr('_username');
		var arrIds;
		var arrNames;
		if(sUserIds){
			arrIds = sUserIds.split(',');
		}else{
			arrIds = new Array();
		}
		if(sUserNames){
			arrNames = sUserNames.split(',');
		}else{
			arrNames = new Array();
		}
		if(checkBox.checked){ 
			arrIds.push(nVal);
			arrNames.push(sName);
		}else{
			arrIds.splice($.inArray(nVal, arrIds), 1);
			arrNames.splice($.inArray(sName, arrNames), 1);
		}
		$('#dialogPageForm').find('input[name="userIds"]').val(arrIds.join(','));
		$('#dialogPageForm').find('input[name="userNames"]').val(arrNames.join(','));
	}
</script>
<form id="dialogPageForm">
	<s:hidden name="userIds" id="userIds"/>
	<s:hidden name="pageNum" id="pageNum" />
	<s:hidden name="pageSize" id="pageSize" />
	<s:hidden name="orderField" id="orderField" />
	<s:hidden name="orderBy" id="orderBy" />
	<s:hidden name="searchWord" id="searchWord" />
	<s:hidden name="searchType" id="searchType" />
	<s:hidden name="isPeson" id="isPerson"></s:hidden>
	<s:hidden  name="projectId" id="projectId"></s:hidden>
</form>
<div style="padding:5px; text-align: center;">
<div style="padding:0px 10px 5px 0px; text-align: right;">
<input name="sWord" id="sWord" />
<div id="sType">  
    <div data-options="name:'all'">全部&nbsp;&nbsp;&nbsp;&nbsp;</div>
    <div data-options="name:'username'">用户名&nbsp;&nbsp;</div>
    <div data-options="name:'truename'">真实姓名</div>
</div>
</div>
<input type="hidden" value="<s:property value='tokens'/>" id="tokens"/> 
<table id="importUserList" border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
    <tr>
	    <td width="70" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="checkAll()">全选</a></td>
	    <td width="200" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('username','<s:if test="orderField=='username'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">用户名<s:if test="orderField=='username'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='username'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="200" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('truename','<s:if test="orderField=='truename'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">真实姓名<s:if test="orderField=='truename'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='truename'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="130" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('crtime','<s:if test="orderField=='crtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">注册时间<s:if test="orderField=='crtime'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='crtime'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
    </tr>
    <s:set var="arr" value='userIds.split(",")' />
    <s:iterator value="irpUsers" status="listStat">
    <tr>
	    <td align="center" bgcolor="#F5FAFE"  class="main_bleft">
	    <input type="checkbox"  <s:if test='loginUserid==userid'>disabled </s:if> id="<s:property value="userid" />" <s:if test='@org.apache.commons.lang.ArrayUtils@contains(#arr,""+userid)'>checked="true"</s:if> onchange="checkClick(this)" name="userid" value="<s:property value="userid" />" _username="<s:if test="truename!=null && truename.length()>0"><s:property value="truename" /></s:if><s:else><s:property value="username" /></s:else>" /> <label for="<s:property value="userid" />"><s:property value="(pageNum-1)*pageSize+#listStat.count"/></label></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="username" /></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="truename" /></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:date name="crtime" format="yyyy-MM-dd HH:mm" /></td>
    </tr>
    </s:iterator>
    <tr bgcolor="#FFFFFF">
		<td colspan="4" align="right"><div class="clientpage">
			<ul>
				<s:property value="pageHTML" escapeHtml="false" />
			</ul>
		</div></td>
	</tr>
</table>
</div>
</body>
</html>
