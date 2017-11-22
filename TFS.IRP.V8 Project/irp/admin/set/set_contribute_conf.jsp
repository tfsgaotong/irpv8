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
<title></title>
</head>
<body>
<script type="text/javascript">
/*全局变量*/
var m_checked = false;
/**
 * 刷新本页面
 */
function refreshContribute(){
	$('#contributeconfitem').panel('refresh');
}

/**
 * 增加配置项
 */
 function addEdit(){
	 	var dialogdiv=document.createElement("div");
	 	dialogdiv.id="addconfig";
	 	document.body.appendChild(dialogdiv);
	 	$('#addconfig').dialog({
	 		modal:true,
	 		cache:false,
	 		href:'<%=rootPath%>set/loadcontributeeditadd.action',
	 		height:300,
	 		width:400,
	 		title:'增加配置项',
	 		resizable:true,
	 		buttons:[{
	 			text:'提交',
	 			iconCls: 'icon-ok',
	 			handler:function(){
	 			 $('#contributeedit').form('submit',{
	 				 url:'<%=rootPath%>set/addValueConfig.action',
	 				 onSubmit:function(){
	 						var isValid = $(this).form('validate');
	 	    				if (isValid){
	 	    					$.messager.progress({
	 	    	    				text:'提交数据中...'
	 	    	    			});
	 	    				}
	 	    				return isValid;
	 					},		
	 				 success:function(_nStatus){
	 					 $.messager.progress('close');
	 					 $('#addconfig').dialog('destroy');
	 					 if (_nStatus==1) {
	 						refreshContribute();
	 					 }else{
	 						$.messager.alert("失败","增加贡献类型失败了");
	 						refreshContribute();
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
/**
 * 删除配置项
 */
 function deleteEdit(_ckey,_configid){
	 	$.messager.confirm("删除配置","您确定删除名称为"+_ckey+"的配置吗",function(r){
	 		if(r){
	 			$.messager.progress({
	 				text:'提交数据中...'
	 			});
	 			$.ajax({
	 				type:'post',
	 				url:'<%=rootPath%>set/deletevalueconfig.action?valueconfigid='+_configid,
	 				success:function(_nStatus){
	 					 $.messager.progress('close');
	 					if (_nStatus==1) {
	 						refreshContribute();
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
 * 修改删除项
 */
 function updateEdit(_configid){
	  	var dialogdiv=document.createElement("div");
	  	dialogdiv.id="updateconfig";
	  	document.body.appendChild(dialogdiv);
	  	$('#updateconfig').dialog({
	  		modal:true,
	  		href:'<%=rootPath%>set/loadcontributeedit.action?valueconfigid='+_configid,
	  		height:300,
	  		width:400,
	  		title:'修改贡献配置',
	  		resizable:true,
	  		cache:false,
	  		buttons:[{
	  			text:'提交',
	  			iconCls: 'icon-ok',
	  			handler:function(){
	  				$('#contributeedit').form('submit',{
	  					url:'<%=rootPath%>set/updateValueConfig.action?valueconfigid='+_configid,
	  					onSubmit:function(){
	  						var isValid = $(this).form('validate');
	  	    				if (isValid){
	  	    					$.messager.progress({
	  	    	    				text:'提交数据中...'
	  	    	    			});
	  	    				}
	  	    				return isValid;
	  					},		
	  					success:function(_nStatus){
	  						$.messager.progress('close');
	  						$('#updateconfig').dialog('destroy');
	  						if (_nStatus==1) {
	  							refreshContribute();
	  						}else{
	  							$.messager.alert("失败","修改贡献配置失败了");
	  							refreshContribute();
	  						}
	  					}
	  				});
	  			}
	  		},{
	  			text:'取消',
	  			iconCls:'icon-cancel',
	  			handler:function(){
	  				$('#updateconfig').dialog('destroy');
	  			}
	  		}],
	  		onClose:function(){
	  			$('#updateconfig').dialog('destroy');
	  		}
	  		
	  	});
	  	
	  }
   /**
	*多选删除
	*/
	function deleteAll(){
	 var _configid;	
	 $("input[name='irpvalueconfigid']:checked").each(
			 function(){
				 _configid+=$(this).val() + ',';
			 }
			 );
			 if (_configid==null) {
			 	$.messager.alert("操作提示","请您先选择要删除的对象");
			 	return false;
			 } 	
			 var _configcatalength=$("input[name='irpvalueconfigid']:checked").length;
			 $.messager.confirm("操作提示","您确定要删除选中的"+_configcatalength+"条数据吗",function(r){
				 if(r){
				       $.messager.progress({
							text:'提交数据中...'
						});
				   $.ajax({
				    type:'post',
				    url:'<%=rootPath  %>set/deletevalueconfigall.action?valueconfigids='+_configid,
				    success:function(_nStatus){
				    	 $.messager.progress('close');
				        if (_nStatus==1) {
							$.messager.alert("操作提示","您成功删除了"+_configcatalength+"条数据");
							refreshContribute();
						}else{
							$.messager.alert("操作提示","删除失败了");
							refreshContribute();
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
	 * 全选
	 */
		function checkAll(){
			m_checked = !m_checked;
			$("input[name='irpvalueconfigid']").attr("checked",m_checked); 
		} 
		/**
		*分页
		*/
		function page(_nPageNum){
			$('#pageNum').val(_nPageNum);
			var queryString = $('#pageForm').serialize();
			$('#contributeconfitem').panel('refresh',"<%=rootPath%>set/contributelist.action?"+queryString);
		}
		/**
		*排序
		*/
		function orderBy(_sFiled,_sOrderBy){
			$('#orderField').val(_sFiled);
			$('#orderBy').val(_sOrderBy);
			var queryString = $('#pageForm').serialize();
			$('#contributeconfitem').panel('refresh',"<%=rootPath%>set/contributelist.action?"+queryString);
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
	    	var queryString = $('#pageForm').serialize();
	    	$('#contributeconfitem').panel('refresh',"<%=rootPath %>set/contributelist.action?"+queryString);
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
    <div data-options="name:'all'">全部&nbsp;&nbsp;&nbsp;&nbsp;</div>
    <div data-options="name:'valuekey'">贡献名称</div>
    <div data-options="name:'valuedesc'">贡献描述</div>
</div>
<table width="100%" border="0" align="center" cellpadding="10" cellspacing="1"  id="table1" bgcolor="#cad9ea">
 <tr class="list_th" style="position: relative;">
  <td colspan="3" style="padding-left: 10px;">
   <a href="javascript:void(0)" onclick="addEdit()">添加</a>
   <a href="javascript:void(0)" onclick="deleteAll()">删除</a>
   <a href="javascript:void(0)" onclick="refreshContribute()">刷新</a>
  </td>
  <td colspan="4" align="right" style="padding-right: 2px;"><input id='listSearchText' /></td>
 </tr>
 <tr>
  <td align="center" width="60" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="checkAll()">全选</a></td>
  <td align="center" width="200" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('valuekey','<s:if test="orderField=='valuekey'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">贡献名称<s:if test="orderField=='valuekey'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='valuekey'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
  <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('valuedesc','<s:if test="orderField=='valuedesc'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">贡献描述<s:if test="orderField=='valuedesc'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='valuedesc'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
  <td align="center" width="60" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('score','<s:if test="orderField=='score'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">积分<s:if test="orderField=='score'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='score'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
  <td align="center" width="60" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('experience','<s:if test="orderField=='experience'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">经验<s:if test="orderField=='experience'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='experience'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
  <td align="center" width="60" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('startusing','<s:if test="orderField=='startusing'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">是否启用<s:if test="orderField=='startusing'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='startusing'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
  <td align="center" width="60" bgcolor="#F5FAFE" class="main_bright">操作</td>
 </tr>
 <s:iterator value="irpValueConfiglist" status="irpvalueconfigstatus">
  <tr>
   <td align="left" bgcolor="#F5FAFE" class="main_bright"><input name="irpvalueconfigid" type="checkbox" value="<s:property value='valueid' />">&nbsp;<s:property value="(pageNum-1)*pageSize+#irpvalueconfigstatus.count"/></td>
   <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="valuekey" /></td>
   <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="valuedesc" /></td>
   <td align="center" bgcolor="#F5FAFE" class="main_bright"><s:property value="score" /></td>
   <td align="center" bgcolor="#F5FAFE" class="main_bright"><s:property value="experience" /></td>
   <s:if test="startusing==0">
   <td align="center" bgcolor="#F5FAFE" class="main_bright">否</td>
   </s:if>
   <s:if test="startusing==1">
   <td align="center" bgcolor="#F5FAFE" class="main_bright">是</td>
   </s:if>
   <td align="center" bgcolor="#F5FAFE" class="main_bright">
    <img border="0" src="images/icons/pencil.png" title="修改" style="cursor:pointer; margin: 0 5px;" onclick="updateEdit(<s:property value="valueid" />)"  />
    <img border="0" src="images/icons/cancel.png" title="删除" style="cursor:pointer; margin: 0 3px;" onclick="deleteEdit('<s:property value="valuekey" />',<s:property value="valueid" />)" />
   </td>
  </tr>
  </s:iterator>
  <tr bgcolor="#FFFFFF">
   <td colspan="7" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
  </tr>
</table>
</body>
</html>