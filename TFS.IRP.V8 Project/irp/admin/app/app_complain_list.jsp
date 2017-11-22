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
<title>Insert title here</title>
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
	    	$('#appcomplaintab').panel('refresh',"<%=rootPath%>menu/select_allcomplain.action?"+queryString);
	    },   
	    menu:'#listSearchType',   
	    prompt:'请输入检索词'  
	});
});	
 /**
  * 刷新本页面
  */
 function refreshCata(){
		$('#appcomplaintab').panel('refresh');
 }
	/**
	*分页
	*/
  function page(_nPageNum){
	$('#pageNum').val(_nPageNum);
	var queryString = $('#pageForm').serialize();
	$('#appcomplaintab').panel('refresh',"<%=rootPath%>menu/select_allcomplain.action?"+queryString);
  } 
	/**
	*排序
	*/
 function orderBy(_sFiled,_sOrderBy){
	$('#orderField').val(_sFiled);
	$('#orderBy').val(_sOrderBy);
	$('#pageNum').val(1);
	var queryString = $('#pageForm').serialize();
	$('#appcomplaintab').panel('refresh',"<%=rootPath%>menu/select_allcomplain.action?"+queryString);
 }	
 function lookdetil(_value){
	 $.messager.alert('查看详细信息',_value);
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
    <div data-options="name:'ckey'">名称</div>
</div>
    <table width="100%" border="0" align="center" cellpadding="10"cellspacing="1"  id="table1" bgcolor="#cad9ea">
			<tr class="list_th" style="position: relative;">
				 <td colspan="3" style="padding-left: 10px;">
					  <a href="javascript:void(0)" onclick="refreshCata()">刷新</a>
				 </td>
				  <td colspan="4" align="right" style="padding-right: 2px;"><input id="listSearchText" value='<s:property value="searchWord"/>'/></td>
			</tr>
			<tr>
				 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="10%"><a href="javascript:void(0)" onclick="checkAll()">全选</a></td>
				 <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('COMPLAINDESC','<s:if test="orderField=='COMPLAINDESC'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">内容<s:if test="orderField=='COMPLAINDESC'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='COMPLAINDESC'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
				 <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('complaintypeid','<s:if test="orderField=='complaintypeid'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">类型<s:if test="orderField=='complaintypeid'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='complaintypeid'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
				 <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('CREATTIME','<s:if test="orderField=='CREATTIME'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">创建时间<s:if test="orderField=='CREATTIME'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='CREATTIME'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
				 <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('ANSQUES','<s:if test="orderField=='ANSQUES'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">状态<s:if test="orderField=='ANSQUES'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='ANSQUES'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
			</tr> 
		  <s:iterator value="listmap" status="irpInformTypeliststatus">
			<tr>
			 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="10%"><input name="applistid" type="checkbox" value="<s:property value='COMPLAINID' />">&nbsp;<s:property value="(pageNum-1)*pageSize+#irpInformTypeliststatus.count"/></td>
			 <td align="left" bgcolor="#F5FAFE" class="main_bright" width="60%">
			       <s:if test="COMPLAINDESC.length()>30"><span style="cursor:pointer;" title="双击查看更多" ondblclick="lookdetil('<s:property value="COMPLAINDESC"/>')"><s:property value="COMPLAINDESC.substring(0,30)"/></span>…
                    </s:if> <s:else><s:property value="COMPLAINDESC"  /></s:else> 
			 </td>
			 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="13%"><s:property value="INFORMVALUE" /></td>
			 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="12%"><s:date name="CREATTIME" format="yyyy-MM-dd HH:mm" /> </td>
			 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="5%"><s:if test="ANSQUES==1">意见</s:if><s:elseif test="ANSQUES==0">答复</s:elseif><s:else>自带</s:else> </td>
			</tr>
		 </s:iterator>
			<tr bgcolor="#FFFFFF">
			 <td colspan="7" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
			</tr>
      </table>
      <div id="detilcomplain"></div>
</body>
</html>