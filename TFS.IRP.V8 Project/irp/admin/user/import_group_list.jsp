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
		jump("<%=rootPath %>user/import_group_list.action?"+queryString);
	}
	//排序
	function orderBy(_sFiled,_sOrderBy){
		$('#orderField').val(_sFiled);
		$('#orderBy').val(_sOrderBy);
		var queryString = $('#pageForm').serialize();
		jump("<%=rootPath %>user/import_group_list.action?"+queryString);
	}
	
	//全选
	function checkAll(){
		m_checked = !m_checked;
		$('#groupList').find("input:checkbox[name='groupid']").each(function(){
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
		var sGroupIds;
		$.each(jsonData, function(i, n){
			if(sGroupIds){
				sGroupIds += ","+n.value;
			}else{
				sGroupIds = n.value;
			}
		});
		$('#groupIds').val(sGroupIds);
	}
		
	$(function(){
		//初始化设置checkbox全选是取消还是选择
		$('#groupList').find("input:checkbox[name='groupid']").each(function(){
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
		    	jump("<%=rootPath %>user/import_group_list.action?"+queryString);
		    },   
		    menu:'#listSearchType',   
		    prompt:'请输入检索词'  
		});
	});
</script>
<form id="pageForm">
<s:hidden name="searchWord" id="searchWord" />
<s:hidden name="searchType" id="searchType" />
<s:hidden name="parentId" id="parentId" />
<s:hidden name="groupType" id="groupType" />
<s:hidden name="pageNum" id="pageNum" />
<s:hidden name="pageSize" id="pageSize" />
<s:hidden name="orderField" id="orderField" />
<s:hidden name="orderBy" id="orderBy" />
<s:hidden name="groupIds" id="groupIds" />
</form>
<div style="margin-bottom: 5px;float: right;">
<div id="listSearchType" style="width:120px;">
    <div data-options="name:'all'">全部&nbsp;&nbsp;&nbsp;&nbsp;</div>
    <div data-options="name:'groupname'">组织名称</div>
    <div data-options="name:'gdesc'">组织描述</div>
</div>
<input id='listSearchText' />
</div>
<table width="100%" id="groupList"  border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
    <tr>
	    <td width="10%" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="checkAll()">全选</a></td>
	    <td width="45%" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('groupname','<s:if test="orderField=='groupname'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">组织名称<s:if test="orderField=='groupname'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='groupname'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="45%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('gdesc','<s:if test="orderField=='gdesc'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">组织描述<s:if test="orderField=='gdesc'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='gdesc'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
    </tr>
    <s:set var="arr" value='groupIds.split(",")' />
    <s:iterator value="irpGroups" status="listStat">
    <tr>
	    <td align="center" bgcolor="#F5FAFE"  class="main_bleft"><input type="checkbox" name="groupid" <s:if test='@org.apache.commons.lang.ArrayUtils@contains(#arr,""+groupid)'>checked="true"</s:if> onclick="selectObj(this)" _text="<s:property value="gdesc" />" value="<s:property value="groupid" />" /> <s:property value="(pageNum-1)*pageSize+#listStat.count"/></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="groupname" /></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="gdesc" /></td>
    </tr>
    </s:iterator>
    <tr bgcolor="#FFFFFF">
		<td colspan="9" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
	</tr>
</table>
</body>
</html>
