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
<title>导出列表</title>
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
	    	$('#frontexportmenu').panel('refresh',"<%=rootPath%>menu/export_excecltypetab.action?"+queryString);
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
   $('#frontexportmenu').panel('refresh');
}
/**
 * 增加举报种类配置
 */
function addEdit(typeid){
 	var dialogdiv=document.createElement("div");
 	dialogdiv.id="addreportconfig";
 	document.body.appendChild(dialogdiv);
 	$('#addreportconfig').dialog({
 		modal:true,
 		cache:false,
 		href:'<%=rootPath%>admin/app/set_question_export_edit.jsp',
 		height:213,
 		width:320,
 		title:'添加导出',
 		resizable:true,
 		buttons:[{
 			text:'提交',
 			iconCls: 'icon-ok',
 			handler:function(){
 			 $('#editinformtype').form('submit',{
 				 url:'<%=rootPath%>set/addinformtype.action',
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
 						$.messager.alert("失败","添加导出失败了");
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
 * 删除其他配置
 */
 function deleteEdit(_ckey,_configid){
	$.messager.confirm("删除配置","您确定删除名称为"+_ckey+"的配置吗",function(r){
		if(r){
			$.messager.progress({
				text:'提交数据中...'
			});
			$.ajax({
				type:'post',
				url:'<%=rootPath%>set/deleteinformbyid.action?informtypeid='+_configid,
				success:function(_deletecata){
					 $.messager.progress('close');
					if (_deletecata==1) {
						$.messager.alert("操作提示","删除成功了");
						refreshCata();
					}else{
						$.messager.alert("操作提示","删除未成功");
						refreshCata();
					}
				},
				error:function(){
					 $.messager.progress('close');
				}
			});
		}
	});
} 
 /**
  * 修改
  */
 function updateEdit(_configid){
 	var dialogdiv=document.createElement("div");
 	dialogdiv.id="updatereportconfig";
 	document.body.appendChild(dialogdiv);
 	$('#updatereportconfig').dialog({
 		modal:true,
 		href:'<%=rootPath%>menu/updateexportInformType.action?informtypeid='+_configid,
 		height:260,
 		width:400,
 		title:'修改导出',
 		resizable:true,
 		cache:false,
 		buttons:[{
 			text:'提交',
 			iconCls: 'icon-ok',
 			handler:function(){
 				$('#editinformtype').form('submit',{
 					url:'<%=rootPath%>set/updateInformTypeById.action?informtypeid='+_configid,
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
 							$.messager.alert("成功","修改导出成功");
 							refreshCata();
 						}else{
 							$.messager.alert("失败","修改导出列表失败了");
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
	 var _configid;	
	 $("input[name='irpinformtypecatach']:checked").each(
		 function(){
			 _configid+=$(this).val() + ',';
		 }
		 );
		 if (_configid==null) {
		 	$.messager.alert("操作提示","请您先选择要删除的对象");
		 	return false;
		 } 	
		 var _configcatalength=$("input[name='irpinformtypecatach']:checked").length;
		 $.messager.confirm("操作提示","您确定要删除选中的"+_configcatalength+"条数据吗",function(r){
			 if(r){
			       $.messager.progress({
						text:'提交数据中...'
					  });
			   $.ajax({
			      type:'post',
			      url:'<%=rootPath%>set/deleteInformtypeAll.action?informtypeidall='+_configid,
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
	     });
	}
	/**
	 * 全选
	 */
		function checkAll(){
			m_checked = !m_checked;
			$("input[name='irpinformtypecatach']").attr("checked",m_checked); 
		} 
		/**
		*分页
		*/
		function page(_nPageNum){
			$('#pageNum').val(_nPageNum);
			var queryString = $('#pageForm').serialize();
			$('#frontexportmenu').panel('refresh',"<%=rootPath%>menu/export_excecltypetab.action?"+queryString);
		} 
		/**
		*排序
		*/
		function orderBy(_sFiled,_sOrderBy){
			$('#orderField').val(_sFiled);
			$('#orderBy').val(_sOrderBy);
			var queryString = $('#pageForm').serialize();
			$('#frontexportmenu').panel('refresh',"<%=rootPath%>menu/export_excecltypetab.action?"+queryString);
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
			 		height:550,
			 		width:900,
			 		title:'导出问题选择',
			 		resizable:true,
			 		buttons:[{
			 			text:'取消',
			 			iconCls:'icon-cancel',
			 			handler:function(){
			 				closdia();
			 			}
			 		}],
			 		onClose:function(){
			 			$('#addreportconfig').dialog('destroy');
			 		}
			 		
			 	});
		 }
		//关闭并刷新
		function closdia(){
			$('#addreportconfig').dialog('destroy');
			refreshCata();
		}
		//导出反馈列表
		function exportcomplain(){
			 $.messager.confirm("操作提示","您确定导出意见反馈内容吗?",function(r){
				 if(r){
				       $.messager.progress({
							text:'导出数据中...'
						  });
				   $.ajax({
				      type:'post',
				      url:'<%=rootPath%>menu/excel_complain.action',
				      success:function(date){
				    	 $.messager.progress('close');
				         if (date>0) {
							$.messager.alert("操作提示","您成功导出"+date+"条数据");
							refreshCata();
						 }else{
							$.messager.alert("操作提示","导出失败了");
							refreshCata();
						 }
				       },
				     error:function(){
				     	 $.messager.progress('close');
				      }
				   });
			    }
		     });
		}
</script>
<form id="pageForm">
<s:hidden name="searchWord" id="searchWord" />
<s:hidden name="searchType" id="searchType" />
<s:hidden name="pageNum" id="pageNum" />
<s:hidden name="pageSizeshow" id="pageSize" />
<s:hidden name="orderField" id="orderField" />
<s:hidden name="orderBy" id="orderBy" />
</form>
<div id="listSearchType" style="width:120px;">  
    <div data-options="name:'all'">全部&nbsp;&nbsp;&nbsp;&nbsp;</div>
    <div data-options="name:'ckey'">名称</div>
    <div data-options="name:'cvalue'">内容</div>
    <div data-options="name:'cdesc'">说明</div>
</div>
<table width="100%" border="0" align="center" cellpadding="10" cellspacing="1"  id="table1" bgcolor="#cad9ea">
		<tr class="list_th" style="position: relative;">
		 <td colspan="3" style="padding-left: 10px;">
		  <a href="javascript:void(0)" onclick="addEdit()">添加</a>
		  <a href="javascript:void(0)" onclick="deleteAll()">删除</a>
		  <a href="javascript:void(0)" onclick="refreshCata()">刷新</a>
		 </td>
		  <td colspan="4" align="right" style="padding-right: 2px;"><input id="listSearchText" value='<s:property value="searchWord"/>'/></td>
		</tr>
		<tr>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="5%"><a href="javascript:void(0)" onclick="checkAll()">全选</a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('informkey','<s:if test="orderField=='informkey'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">名称<s:if test="orderField=='informkey'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='informkey'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('informvalue','<s:if test="orderField=='informvalue'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">内容<s:if test="orderField=='informvalue'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='informvalue'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('informdesc','<s:if test="orderField=='informdesc'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">说明<s:if test="orderField=='informdesc'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='informdesc'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('crtime','<s:if test="orderField=='crtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">创建时间<s:if test="orderField=='crtime'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='crtime'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright">导出</td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright">操作</td>
		</tr> 
		<s:iterator value="irpInformTypelist" status="irpInformTypeliststatus">
		<tr>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="5%"><input name="irpinformtypecatach" type="checkbox" value="<s:property value='informtypeid' />">&nbsp;<s:property value="(pageNum-1)*pageSize+#irpInformTypeliststatus.count"/></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="20%"><s:property value="informkey" /></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright"><s:property value="informvalue" /></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="20%"><s:property value="informdesc" /></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="12%"><s:date name="crtime" format="yyyy-MM-dd HH:mm:ss" /> </td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="5%"><s:if test="informkey=='问题'"><a href="javascript:void(0)" onclick="exportquestion()">导出 </a></s:if> </td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="10%">
		   <img border="0" src="images/icons/pencil.png" title="修改" style="cursor:pointer; margin: 0 5px;" onclick="updateEdit(<s:property value="informtypeid" />)"  />
           <img border="0" src="images/icons/cancel.png" title="删除" style="cursor:pointer; margin: 0 3px;" onclick="deleteEdit('<s:property value="informkey" />',<s:property value="informtypeid" />)" />
		 </td>
		</tr>
		</s:iterator>
		<tr bgcolor="#FFFFFF">
		 <td colspan="7" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
		</tr>
      </table>
</body>
</html>