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
	    	$('#jiajingmenu').panel('refresh',"<%=rootPath%>set/jiajing_menu.action?"+queryString);
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
		$('#jiajingmenu').panel('refresh');
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
 		href:'<%=rootPath%>set/updateJiaJingInformType.action?informtypeid='+_configid,
 		height:260,
 		width:400,
 		title:'修改加精理由配置',
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
 							refreshCata();
 						}else{
 							$.messager.alert("提示信息","修改失败了");
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
			$('#jiajingmenu').panel('refresh',"<%=rootPath%>set/jiajing_menu.action?"+queryString);
		} 
		/**
		*排序
		*/
		function orderBy(_sFiled,_sOrderBy){
			$('#orderField').val(_sFiled);
			$('#orderBy').val(_sOrderBy);
			var queryString = $('#pageForm').serialize();
			$('#jiajingmenu').panel('refresh',"<%=rootPath%>set/jiajing_menu.action?"+queryString);
		}	
 
 /**
  * 增加圈子阶段
  */
 function addEdit(){
 	var dialogdiv=document.createElement("div");
 	dialogdiv.id="addconfig";
 	document.body.appendChild(dialogdiv);
 	$('#addconfig').dialog({
 		modal:true,
 		cache:false,
 		href:'<%=rootPath%>admin/set/set_jiajing_edit.jsp',
 		height:200,
 		width:400,
 		title:'添加加精理由配置',
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
 					 $('#addconfig').dialog('destroy');
 					 if (_cataStatus==1) {
 						refreshCata();
 					 }else{
 						$.messager.alert("失败","增加失败了");
 					 }
 				 }
 			 });
 			}
 		},{
 			text:'取消',
 			iconCls:'icon-cancel',
 			handler:function(){
 				$('#addconfig').dialog('destroy');
 			}
 		}],
 		onClose:function(){
 			$('#addconfig').dialog('destroy');
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
    <div data-options="name:'all'">全部&nbsp;&nbsp;&nbsp;&nbsp;</div>
    <div data-options="name:'ckey'">名称</div>
    <div data-options="name:'cvalue'">内容</div>
</div>
<table width="100%" border="0" align="center" cellpadding="10"
		cellspacing="1"  id="table1" bgcolor="#cad9ea">
		<tr class="list_th" style="position: relative;">
		 <td colspan="3" style="padding-left: 10px;">
		  <a href="javascript:void(0)" onclick="addEdit()">添加</a>
		  <a href="javascript:void(0)" onclick="deleteAll()">删除</a>
		  <a href="javascript:void(0)" onclick="refreshCata()">刷新</a>
		 </td>
		  <td colspan="4" align="right" style="padding-right: 2px;"><input id='listSearchText' /></td>
		</tr>
		<tr>
		 <td align="center" width="60" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="checkAll()">全选</a></td>
		 <td align="center" width="160" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('informkey','<s:if test="orderField=='informkey'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">名称<s:if test="orderField=='informkey'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='informkey'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" width="160" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('informvalue','<s:if test="orderField=='informvalue'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">内容<s:if test="orderField=='informvalue'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='informvalue'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('informdesc','<s:if test="orderField=='informdesc'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">说明<s:if test="orderField=='informdesc'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='informdesc'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" width="120" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('crtime','<s:if test="orderField=='crtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">创建时间<s:if test="orderField=='crtime'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='crtime'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" width="50" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('informtype','<s:if test="orderField=='informtype'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">种类<s:if test="orderField=='informtype'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='informtype'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" width="60" bgcolor="#F5FAFE" class="main_bright">操作</td>
		</tr> 
		<s:iterator value="irpInformTypelist" status="irpInformTypeliststatus">
		<tr>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright"><input name="irpinformtypecatach" type="checkbox" value="<s:property value='informtypeid' />">&nbsp;<s:property value="(pageNum-1)*pageSize+#irpInformTypeliststatus.count"/></td>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="informkey" /></td>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="informvalue" /></td>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="informdesc" /></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright"><s:date name="crtime" format="yyyy-MM-dd HH:mm:ss" /> </td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright"><s:if test="informtype==10">微知</s:if><s:if test="informtype==30">常用</s:if><s:else>知识</s:else> </td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright">
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