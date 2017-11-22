<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>


 
<script type="text/javascript">
	//全局变量，
	var m_checked = false; 
	//分页
	function page(_nPageNum){
		$('#dialogPageForm').find('input[name="pageNum"]').val(_nPageNum);
		$('#dialogPageForm').find('input[name="searchWord"]').val($('#dialogPageForm').find('input[name="searchWord"]').val());
		jump($('#dialogPageForm'));
	}
	//排序
	function orderBy(_sFiled,_sOrderBy){
		$('#dialogPageForm').find('input[name="orderField"]').val(_sFiled);
		$('#dialogPageForm').find('input[name="orderBy"]').val(_sOrderBy);
		$('#dialogPageForm').find('input[name="searchWord"]').val($('#dialogPageForm').find('input[name="searchWord"]').val());
		jump($('#dialogPageForm'));
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
		 var messageuserinfouser =  $('#messageuserinfo').val();
		 if(messageuserinfouser!=null){
			 var messageuserinfouserarray = messageuserinfouser.split(",");
			 for(var i = 0;i<messageuserinfouserarray.length;i++){
				 if(messageuserinfouserarray[i]!=null){
						$("input[name='userid']").each(function(){
							if($(this).attr('_id')==messageuserinfouserarray[i]){
								$("input[_id='"+messageuserinfouserarray[i]+"']").attr("checked",true);
							}
				 		});
				 }
			 }
		 }
	/*	$('#sWord').searchbox({
			width:240,
		    menu:'#sType',
		    prompt:'请输入检索词',
		    searcher:function(value,name){
		    	$('#dialogPageForm').find('input[name="searchWord"]').val(value);
		    	$('#dialogPageForm').find('input[name="searchType"]').val(name);
		    	$('#dialogPageForm').find('input[name="pageNum"]').val('1');
		    	$('#dialogPageForm').find('input[name="orderField"]').val('');
		    	$('#dialogPageForm').find('input[name="orderBy"]').val('');
		    	jump($('#dialogPageForm'));
		    } 
		});*/
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
			if(${maxAddUserNum}>0 && arrIds.length>=${maxAddUserNum}){
				checkBox.checked = false;
				return false;
			}
			arrIds.push(nVal);
			arrNames.push(sName);
		}else{
			arrIds.splice($.inArray(nVal, arrIds), 1);
			arrNames.splice($.inArray(sName, arrNames), 1);
		}
		$('#dialogPageForm').find('input[name="userIds"]').val(arrIds.join(','));
		$('#dialogPageForm').find('input[name="userNames"]').val(arrNames.join(','));
		var transdocument=$('#transdocument');
		if(typeof transdocument != "undefined"){
			$('#transdocument').find('#userIds').val($('#dialogPageForm').find('input[name="userIds"]').val()); 
			$('#transdocument').find('#userNames').val($('#dialogPageForm').find('input[name="userNames"]').val());
		}
}
	function serch(){
		$('#dialogPageForm').find('input[name="searchWord"]').val($("#sWord").val());
	   	$('#dialogPageForm').find('input[name="searchType"]').val("all");
	   	$('#dialogPageForm').find('input[name="pageNum"]').val('1');
	   	$('#dialogPageForm').find('input[name="orderField"]').val('');
	   	$('#dialogPageForm').find('input[name="orderBy"]').val('');
	   	jump($('#dialogPageForm'));
	}
	
</script>
<style>
<!--
.ss{
border:1px solid white;
}
#ss td{
border:1px solid white;
}
-->
</style>
<form id="dialogPageForm" method="post">
	<s:hidden name="groupId" id="groupId" />
	<s:hidden name="userIds" id="userIds" />
	<s:hidden name="userNames" id="userNames" />
	<s:hidden name="pageNum" id="pageNum" />
	<s:hidden name="pageSize" id="pageSize" />
	<s:hidden name="orderField" id="orderField" />
	<s:hidden name="orderBy" id="orderBy" />
	<s:hidden name="searchWord" id="searchWord" />
	<s:hidden name="searchType" id="searchType" />
	<s:hidden name="ismaxamount"></s:hidden>
	<s:hidden name="projectId"></s:hidden>
</form>
<div style="padding:5px; text-align: center; width: 610px;">
<div style="padding:0px 10px 5px 0px; float: right;">
<table id="ss">
<tr>
<td><input name="sWord" id="sWord" /></td>
<td><a style="border:thin solid #D3D3D3;border-radius:9px;padding:5px;background-color:rgb(245, 245, 245);" href="javascript:void(0)" class="linkbh14" onclick="serch()">搜索</a></td>
</tr>
</table>
<div id="sType" style="display:none;">  
    <div data-options="name:'all'">全部&nbsp;&nbsp;&nbsp;&nbsp;</div>
    <div data-options="name:'username'">用户名&nbsp;&nbsp;</div>
    <div data-options="name:'truename'">真实姓名</div>
</div>
</div>
<table id="importUserList" width="600" border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
    <tr>
	    <td width="70" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="checkAll()">全选</a></td>
	    <td width="200" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('username','<s:if test="orderField=='username'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">用户名<s:if test="orderField=='username'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='username'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="200" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('truename','<s:if test="orderField=='truename'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">真实姓名<s:if test="orderField=='truename'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='truename'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="130" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('crtime','<s:if test="orderField=='crtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">注册时间<s:if test="orderField=='crtime'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='crtime'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
    </tr>
    <s:set var="arr" value='userIds.split(",")' />
    <s:iterator value="irpUsers" status="listStat">
    <tr>
	    <td align="left" bgcolor="#F5FAFE" style="padding-left: 5px;" class="main_bleft"><input type="checkbox" _id="<s:property value="username" />" id="<s:property value="userid" />" <s:if test='@org.apache.commons.lang.ArrayUtils@contains(#arr,""+userid)'>checked="true"</s:if> onchange="checkClick(this)" name="userid" value="<s:property value="userid" />" _username="<s:if test="truename!=null && truename.length()>0"><s:property value="truename" /></s:if><s:else><s:property value="username" /></s:else>" /> <label for="<s:property value="userid" />"><s:property value="(pageNum-1)*pageSize+#listStat.count"/></label></td>
	    <td align="left" bgcolor="#F5FAFE" style="padding-left: 5px;" class="main_bright"><s:property value="username" /></td>
	    <td align="left" bgcolor="#F5FAFE" style="padding-left: 5px;" class="main_bright"><s:property value="truename" /></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:date name="crtime" format="yyyy-MM-dd HH:mm:ss" /></td>
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
