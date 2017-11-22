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
 //导出问题
 function exportquestion(){
	 var dialogdiv=document.createElement("div");
	 	dialogdiv.id="addreportconfig";
	 	document.body.appendChild(dialogdiv);
	 	$('#addreportconfig').dialog({
	 		modal:true,
	 		cache:false,
	 		href:'<%=rootPath%>menu/appquestion_exportform.action',
	 		height:580,
	 		width:600,
	 		title:'导出问题选择',
	 		resizable:true,
	 		buttons:[{
	 			text:'提交',
	 			iconCls: 'icon-ok',
	 			handler:function(){
	 			$('#editinformtype').form('submit',{
	 				 url:'<%=rootPath%>menu/add_newapp.action',
	 				 onSubmit:function(){
	 						var isValid = $(this).form('validate');
	 	    				if (isValid){
	 	    					$.messager.progress({
	 	    	    				text:'提交数据中...'
	 	    	    			});
	 	    				}
	 	    				return isValid;
	 					},		
	 				 success:function(_cataStatus){
	 					 $.messager.progress('close');
	 					 $('#addreportconfig').dialog('destroy');
	 					 if (_cataStatus==1) {
	 						$.messager.alert("成功","添加新应用成功了");
	 						refreshCata();
	 					 }else{
	 						$.messager.alert("失败","添加新应用失败了");
	 						refreshCata();
	 					 }
	 				 }
	 			 });
	 			}
	 		},{
	 			text:'取消',
	 			iconCls:'icon-cancel',
	 			handler:function(){
	 				$('#addreportconfig').dialog('destroy');
	 			}
	 		}],
	 		onClose:function(){
	 			$('#addreportconfig').dialog('destroy');
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
				 <td align="center" bgcolor="#F5FAFE" class="main_bright">名称</td>
				 <td align="center" bgcolor="#F5FAFE" class="main_bright">内容</td>
				 <td align="center" bgcolor="#F5FAFE" class="main_bright">说明</td>
				 <td align="center" bgcolor="#F5FAFE" class="main_bright">创建时间</td>
				 <td align="center" bgcolor="#F5FAFE" class="main_bright">操作</td>
			</tr> 
		    <tr>
				 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="10%"><a href="javascript:void(0)" onclick="checkAll()">1</a></td>
				 <td align="center" bgcolor="#F5FAFE" class="main_bright">问题</td>
				 <td align="center" bgcolor="#F5FAFE" class="main_bright">回答内容的导出</td>
				 <td align="center" bgcolor="#F5FAFE" class="main_bright">问题</td>
				 <td align="center" bgcolor="#F5FAFE" class="main_bright"></td>
				 <td align="center" bgcolor="#F5FAFE" class="main_bright" onclick="exportquestion()"><a href="javascript:void(0)">导出</a></td>
			</tr> 
			<tr>
				 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="10%"><a href="javascript:void(0)" onclick="checkAll()">2</a></td>
				 <td align="center" bgcolor="#F5FAFE" class="main_bright">新闻</td>
				 <td align="center" bgcolor="#F5FAFE" class="main_bright">00000</td>
				 <td align="center" bgcolor="#F5FAFE" class="main_bright">00000</td>
				 <td align="center" bgcolor="#F5FAFE" class="main_bright">00000</td>
				 <td align="center" bgcolor="#F5FAFE" class="main_bright">导出</td>
			</tr> 
			<tr bgcolor="#FFFFFF">
			 <td colspan="7" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
			</tr>
      </table>
      <div id="detilcomplain"></div>
</body>
</html>