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
<title>升级信息</title>
</head>
<body>

<script type="text/javascript">
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
	    	$('body').layout('panel','center').panel('refresh',"<%=rootPath%>home/systemupdateinfo.action?"+queryString);
	    },   
	    menu:'#listSearchType',   
	    prompt:'请输入检索词'  
	});
});	
//全局变量，
var m_checked = false;
/**
 * 刷新本页面
 */
function refreshCata(){
		$('body').layout('panel','center').panel('refresh');
}
	/**
	 * 全选
	 */
		function checkAll(){
			m_checked = !m_checked;
			$("input[name='irpDbupdateliststatus']").attr("checked",m_checked); 
		} 
		/**
		*分页
		*/
		function page(_nPageNum){
			$('#pageNum').val(_nPageNum);
			var queryString = $('#pageForm').serialize();
			$('body').layout('panel','center').panel('refresh',"<%=rootPath%>home/systemupdateinfo.action?"+queryString);
			
		} 
		/**
		*排序
		*/
		function orderBy(_sFiled,_sOrderBy){
			$('#orderField').val(_sFiled);
			$('#orderBy').val(_sOrderBy);
			var queryString = $('#pageForm').serialize();
			$('body').layout('panel','center').panel('refresh',"<%=rootPath%>home/systemupdateinfo.action?"+queryString);

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
<div id="listSearchType" style="width:120px;">  
    <div data-options="name:'all'">全部&nbsp;&nbsp;&nbsp;&nbsp;</div>
    <div data-options="name:'ckey'">操作类型</div>
    <div data-options="name:'cvalue'">用户</div>
    <div data-options="name:'cdesc'">详细信息</div>
</div>
<table width="100%" border="0" align="center" cellpadding="10"
		cellspacing="1"  id="table1" bgcolor="#cad9ea">
		<tr class="list_th" style="position: relative;">
		  <td colspan="1" style="padding-left: 10px;">
		  <a href="javascript:void(0)" onclick="refreshCata()">刷新</a>
		  </td>
		  <td colspan="6" align="right" style="padding-right: 2px;"><input id='listSearchText' /></td>
		</tr>
		<tr>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="5%"><a href="javascript:void(0)" onclick="checkAll()">全选</a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('dbupname','<s:if test="orderField=='dbupname'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">圈子名称<s:if test="orderField=='dbupname'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='dbupname'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('dbupversion','<s:if test="orderField=='dbupversion'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">版本<s:if test="orderField=='dbupversion'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='dbupversion'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('dbupinfo','<s:if test="orderField=='dbupinfo'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">操作类型<s:if test="orderField=='dbupinfo'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='dbupinfo'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('dbupuser','<s:if test="orderField=='dbupuser'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">执行人<s:if test="orderField=='dbupuser'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='dbupuser'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('dbuptime','<s:if test="orderField=='dbuptime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">日期<s:if test="orderField=='dbuptime'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='dbuptime'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('dbupapptype','<s:if test="orderField=='dbupapptype'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">详细描述<s:if test="orderField=='dbupapptype'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='dbupapptype'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		</tr> 
		<s:iterator value="irpDbupdatelist" status="irpDbupdateliststatus">
		<tr>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="5%"><input name="irpDbupdatelistcatach" type="checkbox" value="<s:property value='dbupid' />">&nbsp;<s:property value="(pageNum-1)*pageSize+#irpDbupdateliststatus.count"/></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="10%"><s:property value="dbupname" /></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright"><s:property value="dbupversion" /></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="10%"><s:property value="dbupinfo" /></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="12%"><s:property value="dbupuser" /></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="12%"><s:date name="dbuptime" format="yyyy-MM-dd HH:mm:ss" /> </td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright"><s:property value="dbupapptype" /></td>
		</tr>
		</s:iterator>
		<tr bgcolor="#FFFFFF">
		 <td colspan="7" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
		</tr>
      </table>
</body>
</html>