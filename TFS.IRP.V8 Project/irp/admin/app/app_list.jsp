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
jump('<%=rootPath  %>menu/select_allAppright.action?'+queryString);
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
	jump('<%=rootPath  %>menu/select_allAppright.action');
	
}
/**
 * 增加
 */
function addEdit(typeid){
 	var dialogdiv=document.createElement("div");
 	dialogdiv.id="addreportconfig";
 	document.body.appendChild(dialogdiv);
 	$('#addreportconfig').dialog({
 		modal:true,
 		cache:false,
 		href:'<%=rootPath%>menu/app_formedit.action',
 		height:300,
 		width:380,
 		title:'添加新应用',
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
/**
 * 删除
 */
 function deleteEdit(_ckey,_configid){
	$.messager.confirm("删除配置","您确定删除名称为"+_ckey+"的配置吗",function(r){
		if(r){
			$.messager.progress({
				text:'提交数据中...'
			});
			$.ajax({
				type:'post',
				url:'<%=rootPath%>set/deleteappbyid.action?appid='+_configid,
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
 		href:'<%=rootPath%>menu/update_appById.action?aid='+_configid,
 		height:300,
 		width:400,
 		title:'修改应用',
 		resizable:true,
 		cache:false,
 		buttons:[{
 			text:'提交',
 			iconCls: 'icon-ok',
 			handler:function(){
 				$('#editinformtype').form('submit',{
 					url:'<%=rootPath%>menu/update_app.action',
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
 							$.messager.alert("成功","修改应用成功");
 							refreshCata();
 						}else{
 							$.messager.alert("失败","修改应用失败");
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
			$('#frontoffenmenu').panel('refresh',"<%=rootPath%>set/offen_menutab.action?"+queryString);
		} 
		/**
		*排序
		*/
		function orderBy(_sFiled,_sOrderBy){
			$('#orderField').val(_sFiled);
			$('#orderBy').val(_sOrderBy);
			var queryString = $('#pageForm').serialize();
			$('#frontoffenmenu').panel('refresh',"<%=rootPath%>set/offen_menutab.action?"+queryString);
		}	
		/**
		*修改发布
		*/
		function gopublic(_aid,_status){
			$.ajax({
			      type:'post',
			      url:'<%=rootPath%>menu/update_appstatus.action?aid='+_aid+'&status='+_status,
			      success:function(data){
			    	 $.messager.progress('close');
			         if (data==1) {
						$.messager.alert("操作提示","修改成功了");
						refreshCata();
					 }else{
						$.messager.alert("操作提示","修改失败了");
						refreshCata();
					 }
			       },
			     error:function(){
			     	 $.messager.progress('close');
			      }
			   });
		}
</script>
<form id="pageForm">
<s:hidden name="pageNum" id="pageNum" />
<s:hidden name="pageSize" id="pageSize" />
</form>
<div id="listSearchType" style="width:120px;">  
    <div data-options="name:'all'">全部&nbsp;&nbsp;&nbsp;&nbsp;</div>
    <div data-options="name:'ckey'">名称</div>
    <div data-options="name:'cvalue'">内容</div>
</div>
<form action="" id="updateappform">
<table width="100%" border="0" align="center" cellpadding="10" cellspacing="1"  id="table1" bgcolor="#cad9ea">
	<tr class="list_th" style="position: relative;">
		 <td colspan="3" style="padding-left: 10px;">
		  <a href="javascript:void(0)" onclick="refreshCata()">刷新</a>
		 </td>
		  <td colspan="4" align="right" style="padding-right: 2px;"><input id="listSearchText" value='<s:property value="searchWord"/>'/></td>
	</tr>
	<tr>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="5%"><a href="javascript:void(0)" onclick="checkAll()">全选</a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('informkey','<s:if test="orderField=='informkey'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">名称<s:if test="orderField=='informkey'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='informkey'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('informvalue','<s:if test="orderField=='informvalue'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">显示名称<s:if test="orderField=='informvalue'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='informvalue'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('informdesc','<s:if test="orderField=='informdesc'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">应用描述<s:if test="orderField=='informdesc'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='informdesc'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('crtime','<s:if test="orderField=='crtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">创建时间<s:if test="orderField=='crtime'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='crtime'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('informtype','<s:if test="orderField=='informtype'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">状态<s:if test="orderField=='informtype'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='informtype'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright">操作</td>
	</tr> 
		<s:iterator value="irpApps" status="irpInformTypeliststatus">
	<tr>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="5%"><input name="applistid" type="checkbox" value="<s:property value='applistid' />">&nbsp;<s:property value="(pageNum-1)*pageSize+#irpInformTypeliststatus.count"/></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="20%"><s:property value="applistname" /></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="20%"><s:property value="applistalias" /></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="23%"><s:property value="description" /></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="12%"><s:date name="crtime" format="yyyy-MM-dd HH:mm:ss" /> </td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="10%"><s:if test="status==0">已发布&nbsp;<a href="javascript:void(0)" onclick="gopublic(<s:property value='applistid' />,1)">取消发布</a></s:if><s:else>未发布&nbsp;<a href="javascript:void(0)" onclick="gopublic(<s:property value='applistid' />,0)">发布</a></s:else> </td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="10%">
		   <img border="0" src="images/icons/pencil.png" title="修改" style="cursor:pointer; margin: 0 5px;" onclick="updateEdit(<s:property value="applistid" />)"  />
		 </td>
	</tr>
		</s:iterator>
	<tr bgcolor="#FFFFFF">
		 <td colspan="7" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
	</tr>
</table>
  </form>
</body>
</html>