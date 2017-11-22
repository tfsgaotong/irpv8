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
	var m_checked = true;
	
	//分页
	function page(_nPageNum){
		$('#pageNum').val(_nPageNum);
		var queryString = $('#pageForm').serialize();
		jump("<%=rootPath %>user/import_user_list.action?"+queryString);
	}
	//排序
	function orderBy(_sFiled,_sOrderBy){
		$('#orderField').val(_sFiled);
		$('#orderBy').val(_sOrderBy);
		var queryString = $('#pageForm').serialize();
		jump("<%=rootPath %>user/import_user_list.action?"+queryString);
	}
	
	//全选
	function checkAll(){
		m_checked = !m_checked;
		$('#userList').find("input:checkbox[name='userid']").each(function(){
			if(this.checked!=m_checked){
				this.checked = m_checked;
				selectObj(this);
			}else{
				this.checked = m_checked;
			}
		});
	}
	
	//单选
	function selectObj(_chk){
		var jqChk = $(_chk);
		if(jqChk.attr("checked")){
			var data = {
				name : jqChk.attr("_text"),
				value : jqChk.val()
			};
			jsonData.push(data);
			eastEdit(data);
		}else{
			for(var i=0;i<jsonData.length;i++){
				var item = jsonData[i];
				if(item && item.value==jqChk.val()){
					jsonData.splice(i,1);
					eastDel(item);
					break;
				}
			}
		}
		var sUserIds;
		$.each(jsonData, function(i, n){
			if(sUserIds){
				sUserIds += ","+n.value;
			}else{
				sUserIds = n.value;
			}
		});
		$('#userIds').val(sUserIds);
	}
		
	$(function(){
		//初始化设置checkbox全选是取消还是选择
		$('#userList').find("input:checkbox[name='userid']").each(function(){
			if(!this.checked){
				m_checked = false;
				return false;
			}
		});
		//初始化检索框
		$('#listSearchText').searchbox({
			width:220,
		    searcher:function(value,name){   
		    	$('#pageForm').find('input[name="searchWord"]').val(value);
		    	$('#pageForm').find('input[name="searchType"]').val(name);
		    	$('#pageForm').find('input[name="pageNum"]').val('1');
		    	$('#pageForm').find('input[name="orderField"]').val('');
		    	$('#pageForm').find('input[name="orderBy"]').val('');
		    	var queryString = $('#pageForm').serialize();
		    	jump("<%=rootPath %>user/import_user_list.action?"+queryString);
		    },   
		    menu:'#listSearchType',   
		    prompt:'请输入检索词'  
		});
	});
</script>
<form id="pageForm">
<s:hidden name="searchWord" id="searchWord" />
<s:hidden name="searchType" id="searchType" />
<s:hidden name="groupId" id="groupId" />
<s:hidden name="pageNum" id="pageNum" />
<s:hidden name="pageSize" id="pageSize" />
<s:hidden name="orderField" id="orderField" />
<s:hidden name="orderBy" id="orderBy" />
<s:hidden name="userIds" id="userIds" />
</form>
<div style="margin-bottom: 5px;float: right;">
<div id="listSearchType" style="width:120px;">  
    <div data-options="name:'all'">全部&nbsp;&nbsp;&nbsp;&nbsp;</div>
    <div data-options="name:'username'">用户名&nbsp;&nbsp;</div>
    <div data-options="name:'truename'">真实姓名</div>
</div>
<input id='listSearchText' />
</div>
<table width="100%" id="userList"  border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
    <tr>
	    <td width="10%" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="checkAll()">全选</a></td>
	    <td width="45%" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('username','<s:if test="orderField=='username'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">用户名<s:if test="orderField=='username'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='username'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="45%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('truename','<s:if test="orderField=='truename'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">真实姓名<s:if test="orderField=='truename'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='truename'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
    </tr>
    <s:set var="arr" value='userIds.split(",")' />
    <s:iterator value="irpUsers" status="listStat">
    <tr>
	    <td align="center" bgcolor="#F5FAFE"  class="main_bleft"><input type="checkbox" name="userid" <s:if test='@org.apache.commons.lang.ArrayUtils@contains(#arr,""+userid)'>checked="true"</s:if> onclick="selectObj(this)" _text="<s:if test="truename!=null && truename.length()>0"><s:property value="truename" /></s:if><s:else><s:property value="username" /></s:else>" value="<s:property value="userid" />" /> <s:property value="(pageNum-1)*pageSize+#listStat.count"/></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="username" /></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="truename" /></td>
    </tr>
    </s:iterator>
    <tr bgcolor="#FFFFFF">
		<td colspan="9" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
	</tr>
</table>
</body>
</html>
