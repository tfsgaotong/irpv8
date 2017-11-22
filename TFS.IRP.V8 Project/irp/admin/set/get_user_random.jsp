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
<title>随机用户</title>
</head>
<body>


<script type="text/javascript">

/**
*分页
*/
function page(_nPageNum){
	$('#pageNum').val(_nPageNum);
	var queryString = $('#pageForm').serialize();
	
	$('#listrandomuser').panel('refresh',"<%=rootPath%>set/contributelistofspecial.action?"+queryString);
}
 
	$(function(){
	alert(444);
		getrandomuser();
		//var myTable= document.getElementById("#qqqq");
		//myTable.style.display ="none";
		$('#listSearchText').searchbox({
			width:220,
		    searcher:function(value,name){   
		    	$('#pageForm').find('input[name="searchWord"]').val(value);
		    	$('#pageForm').find('input[name="searchType"]').val(name);
		    	$('#pageForm').find('input[name="pageNum"]').val('1');
		    	$('#pageForm').find('input[name="orderField"]').val('');
		    	$('#pageForm').find('input[name="orderBy"]').val('');
		    	var queryString = $('#pageForm').serialize();
		    	$('#listrandomuser').panel('refresh',"<%=rootPath %>set/contributelistofspecial.action?"+queryString);
		    },   
		    menu:'#listSearchType',   
		    prompt:'请输入检索词'  
		});
		
	});   



function getrandomuser(){
alert(1111);
	   var countuser=encodeURIComponent($('#count_user').val());
	   //$('#listrandomuser').panel('refresh','<%=rootPath%>set/getuserrandom1.action?countuser='+countuser);
	   $.ajax({
   			url: "<%=rootPath %>set/getuserrandom1.action",
   			type: "POST",
   			data: {
   				countuser: countuser
   			},
   			success:function(data){
		    	if(data){
		    		//$.messager.alert("提示信息","创建数据表成功！","info");
		    	}
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
<!-- <div id="listSearchType" style="width:120px;">  
    <div data-options="name:'username'">用户名&nbsp;&nbsp;&nbsp;&nbsp;</div>
</div> -->
<form id="check_logs" method="post">
<div>获取用户数量<input name="usercount" id="count_user" class="easyui-validatebox" value="">
<a href="javascript:void(0)" id="user_search" class="easyui-linkbutton" data-options="iconCls:'icon-search'"
						onclick="getrandomuser()">确定</a>
</div>
</form>
<table id="qqqq" width="100%" border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
<!-- <tr class="list_th" style="position: relative;">
  <td colspan="2" style="padding-left: 10px;">
  <a href="javascript:void(0)" onclick="addUser()">添加用户</a>
  <a href="javascript:void(0)" onclick="removeUser()">移除用户</a>
  <a href="javascript:void(0)" onclick="refreshContribute()">刷新</a>
  
  
   <td colspan="3" align="right" style="padding-right: 2px;"><input id='listSearchText' /></td>
   
 </tr> -->
 
 <tr>
  <td align="center" width="33%" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderByOf('username','<s:if test="orderField=='username'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">用户名<s:if test="orderField=='username'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='username'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
  <td align="center" width="33%" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderByOf('truename','<s:if test="orderField=='truename'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">真实姓名<s:if test="orderField=='truename'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='truename'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
  <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderByOf('nickname','<s:if test="orderField=='nickname'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">昵称<s:if test="orderField=='nickname'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='nickname'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
 </tr>
 
 <s:iterator value="irpUsers" status="userliststatus" >
 <tr>
  <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="username" /></td>
  <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="truename" /></td>
  <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="nickname" /></td>
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