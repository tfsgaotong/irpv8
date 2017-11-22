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
</head>
<body>
<script type="text/javascript">
//全局变量，
var m_checked = false;
/**
 * 刷新本页面
 */
function refreshCata(){
		$('#lmtemplate').panel('refresh');
}
/**
 * 增加百科模版配置
 */
function addEdit(){
 	var dialogdiv=document.createElement("div");
 	dialogdiv.id="addreportconfig";
 	document.body.appendChild(dialogdiv);
 	$('#addreportconfig').dialog({
 		modal:true,
 		cache:false,
 		href:'<%=rootPath%>admin/set/template_edit.jsp',
 		height:400,
 		width:600,
 		title:'添加百科模版配置',
 		resizable:true,
 		buttons:[{
 			text:'提交',
 			iconCls: 'icon-ok',
 			handler:function(){
 			 $('#editinformtype').form('submit',{
 				 url:'<%=rootPath%>set/addtemplateknow.action?cateid='+'<s:property value="cateid" />',
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
 						refreshCata();
 					 }else{
 						$.messager.alert("失败","增加模版配置失败了");
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
 /**
 * 修改百科模版配置
 */
 function updateEdit(_configid){
 	var dialogdiv=document.createElement("div");
 	dialogdiv.id="updatereportconfig";
 	document.body.appendChild(dialogdiv);
 	$('#updatereportconfig').dialog({
 		modal:true,
 		href:'<%=rootPath%>set/updateTemplate.action?tid='+_configid,
 		height:400,
 		width:600,
 		title:'修改百科模版配置',
 		resizable:true,
 		cache:false,
 		buttons:[{
 			text:'提交',
 			iconCls: 'icon-ok',
 			handler:function(){
 				$('#editinformtype').form('submit',{
 					url:'<%=rootPath%>set/updatetemplatebyid.action?tid='+_configid,
 					onSubmit:function(){
 						var isValid = $(this).form('validate');
 	    				if (isValid){
 	    					$.messager.progress({
 	    	    				text:'提交数据中...'
 	    	    			});
 	    				}
 	    				return isValid;
 					},		
 					success:function(_updateCataStat){
 						$.messager.progress('close');
 						$('#updatereportconfig').dialog('destroy');
 						if (_updateCataStat==1) {
 							refreshCata();
 						}else{
 							$.messager.alert("失败","修改百科模版配置失败了");
 							refreshCata();
 						}
 					}
 				});
 			}
 		},{
 			text:'取消',
 			iconCls:'icon-cancel',
 			handler:function(){
 				$('#updatereportconfig').dialog('destroy');
 			}
 		}],
 		onClose:function(){
 			$('#updatereportconfig').dialog('destroy');
 		}
 		
 	});
 	
 }
   /**
	*多选删除
	*/
	function deleteAll(){
	 var _configid = "";
	 $("input[name='irptemplateckid']:checked").each(
			 function(){
				 _configid+=$(this).val() + ',';
			 }
			 );
			 if (_configid==null) {
			 	$.messager.alert("操作提示","请您先选择要删除的对象");
			 	return false;
			 } 	
			 var _configcatalength=$("input[name='irptemplateckid']:checked").length;
			 $.messager.confirm("操作提示","您确定要删除选中的"+_configcatalength+"条数据吗",function(r){
				 
				 if(r){
			       $.messager.progress({
						text:'提交数据中...'
					});
				   $.ajax({
				    type:'post',
				    url:'<%=rootPath%>set/deletetemplatebyids.action?tids='+_configid,
				    success:function(date){
				    	 $.messager.progress('close');
				        if (date==1) {
							$.messager.alert("操作提示","您成功删除了"+_configcatalength+"条数据");
							refreshCata();
						}else{
							$.messager.alert("操作提示","删除失败了");
							refreshCata();
						}
				    },
				    error:function(){
				    	 $.messager.progress('close');
				    }
				 });
				 }
				 }
				 );
	}
	/**
	* 编辑删除
	*/	
	function deleteEdit(_tid){
	 $.messager.confirm("操作提示","您确定要删除这条数据吗",function(r){
	 if(r){
	 	$.ajax({
		     type:'post',
		     url:'<%=rootPath%>set/deletetemplatebyids.action',
		     data:{
		     	tids:_tid
		     },
			 cache:false,
			 async:false,
			 success:function(msg){
		        if (msg==1) {
					$.messager.alert("操作提示","删除成功了");
					refreshCata();
				}else{
					$.messager.alert("操作提示","删除失败了");
					refreshCata();
				}
			 }
		 	});
	 }

	 });
	 
	}
/**
* 全选
*/
function checkAll(){
	m_checked = !m_checked;
	$("input[name='irptemplateckid']").attr("checked",m_checked); 
} 
/**
*分页
*/
function page(_nPageNum){
	$('#pageNum').val(_nPageNum);
	$('#pageForm').find('input[name="cateid"]').val('<s:property value="cateid" />');
	var queryString = $('#pageForm').serialize();
	
	$('#lmtemplate').panel('refresh',"<%=rootPath%>set/templateknowlist.action?"+queryString);
}
/**
*排序
*/
function orderBy(_sFiled,_sOrderBy){
	$('#orderField').val(_sFiled);
	$('#orderBy').val(_sOrderBy);
	$('#pageForm').find('input[name="cateid"]').val('<s:property value="cateid" />');
	var queryString = $('#pageForm').serialize();
	$('#lmtemplate').panel('refresh',"<%=rootPath%>set/templateknowlist.action?"+queryString);
}	
$(function(){
	$('#listSearchText').searchbox({
		width:220,
	    searcher:function(value,name){   
	    	$('#pageForm').find('input[name="searchWord"]').val(value);
	    	$('#pageForm').find('input[name="searchType"]').val(name);
	    	$('#pageForm').find('input[name="pageNum"]').val('1');
	    	$('#pageForm').find('input[name="orderField"]').val('');
	    	$('#pageForm').find('input[name="orderBy"]').val('');
	    	$('#pageForm').find('input[name="cateid"]').val('<s:property value="cateid" />');
	    	
	    	var queryString = $('#pageForm').serialize();
	    	$('#lmtemplate').panel('refresh',"<%=rootPath %>set/templateknowlist.action?"+queryString);
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
<s:hidden name="cateid" id="cateid" ></s:hidden>
</form>


<div id="listSearchType" style="width:120px;">  
    <div data-options="name:'all'">全部&nbsp;&nbsp;&nbsp;&nbsp;</div>
    <div data-options="name:'tvaluedesc'">描述</div>
</div>
<table width="100%" border="0" align="center" cellpadding="10"
		cellspacing="1"  id="table1" bgcolor="#cad9ea">
		<tr class="list_th" style="position: relative;">
		 <td colspan="2" style="padding-left: 10px;">
	 		  <a href="javascript:void(0)" onclick="addEdit()">添加</a>
			  <a href="javascript:void(0)" onclick="deleteAll()">删除</a>
			  <a href="javascript:void(0)" onclick="refreshCata()">刷新</a>
		 </td>
		  <td colspan="4" align="right" style="padding-right: 2px;"><input id='listSearchText' /></td>
		</tr>
		<tr>
		 <td align="center" width="5%" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="checkAll()">全选</a></td>
		 <td align="center" width="45%" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('tvalue','<s:if test="orderField=='tvalue'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">内容<s:if test="orderField=='tvalue'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='tvalue'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" width="15%" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('tvaluedesc','<s:if test="orderField=='tvaluedesc'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">描述<s:if test="orderField=='tvaluedesc'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='tvaluedesc'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 
		 <td align="center" width="10%" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('tcate','<s:if test="orderField=='tcate'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">所属分类<s:if test="orderField=='tcate'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='tcate'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 
		 <td align="center" width="10%" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('crtime','<s:if test="orderField=='crtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">创建时间<s:if test="orderField=='crtime'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='crtime'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" width="10%" bgcolor="#F5FAFE" class="main_bright">操作</td>
		</tr> 
		<s:iterator value="templatelist" status="templateliststatus">
		<tr>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright"><input name="irptemplateckid" type="checkbox" value="<s:property value='tid' />">&nbsp;<s:property value="(pageNum-1)*pageSize+#templateliststatus.count"/></td>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="tvalue" /></td>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="tvaluedesc" /></td>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="findCateById(tcate)" /></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" ><s:date name="crtime" format="yyyy-MM-dd HH:mm" /> </td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright">
		   <img border="0" src="images/icons/pencil.png" title="修改" style="cursor:pointer; margin: 0 5px;" onclick="updateEdit(<s:property value="tid" />)"  />
		   <img border="0" src="images/icons/cancel.png" title="删除" style="cursor:pointer; margin: 0 3px;" onclick="deleteEdit(<s:property value="tid" />)" />
		 </td>
		</tr>
		</s:iterator>
		<tr bgcolor="#FFFFFF">
		 <td colspan="6" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
		</tr>
      </table>
</body>
</html>