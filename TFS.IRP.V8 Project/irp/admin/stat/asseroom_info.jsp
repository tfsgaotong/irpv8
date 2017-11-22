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
<!-- 
<script type="text/javascript" src="<%=rootPath %>admin/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>/admin/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>/admin/js/jquery.easyui.min.js"></script>

 -->
</head>
<body>
<script type="text/javascript">
//全局变量，
var m_checked = false;


/**
 * 全选
 */
	function checkAll(){
		m_checked = !m_checked;
		$("input[name='asseroomcatach']").attr("checked",m_checked); 
	} 
	/**
	*分页
	*/
	function page(_nPageNum){
		$('#pageNum').val(_nPageNum);
		var queryString = $('#pageForm').serialize();
		$('#createasseroom').panel('refresh',"<%=rootPath %>asseroom/asseroominfo.action?"+queryString);
	}
	/**
	*排序
	*/
	function orderBy(_sFiled,_sOrderBy){
		$('#orderField').val(_sFiled);
		$('#orderBy').val(_sOrderBy);
		var queryString = $('#pageForm').serialize();
		$('#createasseroom').panel('refresh',"<%=rootPath %>asseroom/asseroominfo.action?"+queryString);
	}	
	$(function(){
		$('#listSearchText').searchbox({
			width:220,
		    searcher:function(value,name){
		    	var flag=true;
		    	if(name=="asseroompeoples"){
		    		flag=/^[+]?[1-9]+\d*$/i.test(value);
		    	}
		    	if(flag){
			    	$('#pageForm').find('input[name="searchWord"]').val(value);
			    	$('#pageForm').find('input[name="searchType"]').val(name);
			    	$('#pageForm').find('input[name="pageNum"]').val('1');
			    	$('#pageForm').find('input[name="orderField"]').val('');
			    	$('#pageForm').find('input[name="orderBy"]').val('');
			    	var queryString = $('#pageForm').serialize();
			    	$('#createasseroom').panel('refresh',"<%=rootPath %>asseroom/asseroominfo.action?"+queryString);
		    	}else{
		    		$.messager.alert("提示","容纳人数必须要输入整数数字");
		    		return false;
		    	}
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
   <!-- 
   <div data-options="name:'all'">全部&nbsp;&nbsp;&nbsp;&nbsp;</div>
    --> 
   
    <div data-options="name:'asseroomname'">会议室名称</div>
    <div data-options="name:'asseroompeoples'">容纳人数</div>
</div>
 <table width="600" border="0" align="center" cellpadding="10"
		cellspacing="1"  id="table1" bgcolor="#cad9ea">
		<tr class="list_th" style="position: relative;">
		 <td colspan="3" style="padding-left: 10px; display:none">
		  <a href="javascript:void(0)" onclick="addEdit()">添加</a>
		  <a href="javascript:void(0)" onclick="deleteAll()">删除</a>
		  <a href="javascript:void(0)" onclick="refreshCata()">刷新</a>
		 </td>
		 <td colspan="7" align="right" style="padding-right: 2px;"><input id='listSearchText' /></td>
		</tr>
		<tr>
		 <td align="center" width="60" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="checkAll()">全选</a></td>
		 <td align="center" width="200" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('asseroomname','<s:if test="orderField=='asseroomname'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">会议室名称<s:if test="orderField=='asseroomname'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='asseroomname'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center"	width="200" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('asseroomdesc','<s:if test="orderField=='asseroomdesc'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">会议室描述<s:if test="orderField=='asseroomdesc'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='asseroomdesc'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" width="70" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('asseroomstatus','<s:if test="orderField=='asseroomstatus'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">状态<s:if test="orderField=='asseroomstatus'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='asseroomstatus'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" width="200" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('asseroomaddr','<s:if test="orderField=='asseroomaddr'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">会议室地址<s:if test="orderField=='asseroomaddr'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='asseroomaddr'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" width="120" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('asseroompeoples','<s:if test="orderField=='asseroompeoples'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">容纳人数<s:if test="orderField=='asseroompeoples'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='asseroompeoples'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		  <td align="center" width="250" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('asseroompeoples','<s:if test="orderField=='asseroompeoples'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">会议室设备<s:if test="orderField=='asseroompeoples'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='asseroompeoples'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		</tr> 
		<s:iterator value="asseroomList" status="asseroomListstatus">
		<tr>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright"><input name="asseroomcatach" checked="checked" type="checkbox" value="<s:property value='asseroomid' />">&nbsp;<s:property value="(pageNum-1)*pageSize+#asseroomListstatus.count"/></td>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="asseroomname" /></td>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="asseroomdesc" /></td>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:if test="asseroomstatus==1">可用</s:if><s:if test="asseroomstatus==0">不可用</s:if></td>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="asseroomaddr" /></td>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="asseroompeoples" /></td>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="asseroomsbs" /></td>
          <!-- 
          
          
          <a href='javascript:void(0)' onclick="applyEdit(<s:property value="asseroomid" />)" class="linkbh14">一键申请</a>
           -->
		 </td>
		</tr>
		</s:iterator>
		<tr bgcolor="#FFFFFF">
		 <td colspan="8" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
		</tr>
      </table>
</body>
</html>