<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>选择申请人</title>
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/oacss.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/icon.css" />
<link id="skin" rel="stylesheet" type="text/css" />
</head> 
<script type="text/javascript">
$(function(){	
var userids='${userids}';
	$('#ttnote').tree({		
					url:'<%=rootPath%>user/find_tree_node.action',
					animate:false,
					lines:true,
					onClick: function(node){
						$('#dialogPageForm').find("input[name='groupId']").val(node.id);
						$('#dialogPageForm').find("input[name='userids']").val(userids);
						var queryString =$('#dialogPageForm').serialize();						
						var type='${type}';				
						userSearch('<%=rootPath %>leave/querychecker.action?userids='+userids+'&type='+type+'&'+queryString);
					},
					onBeforeLoad: function (node, param) {
					if(node==null){
					param.id= 0;
					param.groupType=1;
					}else{
					param.id= node.id
					param.groupType=1;
					}
            },onLoadSuccess:function(node, data){
				if(!node){
					
					var root = $('#ttnote').tree('getRoot');
					$(root.target).find(".tree-checkbox").removeClass("tree-checkbox tree-checkbox0");
					$('#ttnote').tree('expand',root.target);
					 $('#dialogPageForm').find("input[name='groupId']").val(node.id);
					 $('#dialogPageForm').find("input[name='userids']").val(userids);
						var queryString =$('#dialogPageForm').serialize();						
						var type='${type}';				
						userSearch('<%=rootPath %>leave/querychecker.action?userids='+userids+'&type='+type+'&'+queryString);
				}else{
				 		$('#dialogPageForm').find("input[name='groupId']").val(1);
				 		$('#dialogPageForm').find("input[name='userids']").val(userids);
						var queryString =$('#dialogPageForm').serialize();						
						var type='${type}';				
						userSearch('<%=rootPath %>leave/querychecker.action?userids='+userids+'&type='+type+'&'+queryString);
				
				}
			},
	});
});


function searchbox(){
	var name = $('#sType').val();
	$('#dialogPageForm').find('input[name="searchType"]').val(name);
	var queryString =$('#dialogPageForm').serialize(); 
	var type='${type}';
	userSearch('<%=rootPath %>leave/querychecker.action?type='+type+'&'+queryString,queryString);   
}	
function userSearch(_url,queryString){
var value = $('#sWord').val();
	if(value=='请输入检索词'){
	value='';
	}
	var result = $.ajax({
			url: _url,
			type:'post',
			data:{
			searchWord:value,
			},
		    async: false,
		    cache: false
		}).responseText;
	$('#usercontent').html(result);
}

//审核人分页
function pagecheck(_nPageNum){
	$('#dialogPageForm').find('input[name="pageNum"]').val(_nPageNum);	
	var queryString = $('#dialogPageForm').serialize();
	var type='${type}';
	userSearch('<%=rootPath %>leave/querychecker.action?type='+type+'&'+queryString,queryString);
}


//审核人查询分页
function pagequerycheck(_nPageNum){

	
	$('#dialogPageForm').find('input[name="pageNum"]').val(_nPageNum);
	$('#dialogPageForm').find('input[name="searchWord"]').val($('#dialogPageForm').find('input[name="searchWord"]').val());
	
	var queryString = $('#dialogPageForm').serialize();
	var type='${type}';
	userSearch('<%=rootPath %>leave/querychecker.action?type='+type+'&'+queryString,queryString);
	
}
</script>
<body>
 <table width="100%" cellspacing="0" cellpadding="0">
<tbody>
<tr>
<td id="treeTd" style=" vertical-align: top; width: 30%; text-align: left;">
<div class="tab" style="  height: 250px;width:140px">
 <ul id="ttnote"  class="easyui-tree"  >       
                </ul>
</div>
</td>
<td style=" vertical-align: top; width: 70%; text-align: center;">
 <div id="usercontent">
<%--<form id="dialogPageForm" >
	<s:hidden name="pageNum" id="pageNum" />
	<s:hidden name="pageSize" id="pageSize" />
	<s:hidden name="searchType" id="searchType" />
	<s:hidden name="userids" id="userids" />
	<s:hidden name="cruserid" id="cruserid" />
	<s:hidden name="groupId" id="groupId" />    
</form>
<s:hidden name="searchWord" id="searchWord" />
<div style="padding:5px; text-align: center;">
<div style="padding:0px 0px 30px 0px; ">
<div style="padding:0px 0px 5px 0px; float: right;border:1px solid #9ccaf0;height: 15px;">
	<select class="searchbox " style="width: 80px; height: 20px;border: none;background-color: #9ccaf0;" id="sType">
      <option value="all">全部</option>
      <option value="username">用户名</option>
      <option value="truename">真实姓名</option>
  </select>
<input id="sWord" searchboxname="sWord" style=" right;font-size: 12px;color: #ccc;"  value="请输入检索词" onfocus="this.value=''" onblur="if(this.value==''){this.value='请输入检索词'}" >
<a href="javascript:void(0)" onclick="searchbox()"><span class="searchbox-button" style="height: 20px;"></span></a>
</div>
</div>
<table id="importUserList"  border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea" style="width:500px;">
    <tr>
	    <td width="10%" align="center" bgcolor="#F5FAFE" class="main_bright"> </td>
	    <td width="30%" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('username','<s:if test="orderField=='username'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">用户名<s:if test="orderField=='username'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='username'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="30%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('truename','<s:if test="orderField=='truename'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">真实姓名<s:if test="orderField=='truename'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='truename'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="30%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('crtime','<s:if test="orderField=='crtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">注册时间<s:if test="orderField=='crtime'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='crtime'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
    </tr>
    
    <s:iterator value="irpUsers" status="listStat">
    <tr>
	    <td align="center" bgcolor="#F5FAFE"  class="main_bleft">
	    <input type="radio" name="leave" id="leave<s:property value='userid' />"  value="<s:property value="username" />" onclick="checkClick(<s:property value='userid' />)" <s:if test="userid==userids">checked="checked"</s:if>/> <label  for="<s:property value="userid" />"><s:property value="(pageNum-1)*pageSize+#listStat.count"/></label></td>
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
</div> --%>
</div>

</td>
</tr>
</tbody>
</table>
 </table>
</body>
</html>
