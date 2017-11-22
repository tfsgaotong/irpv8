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
<title>用户级别配置</title>
</head>
<body>


<script type="text/javascript">
/*全局变量*/
var m_checked = false;
/**
 * 全选
 */
function checkRankAll(){
	m_checked = !m_checked;
	$("input[name='irpvalueconfigid']").attr("checked",m_checked); 
	} 
/**
 * 刷新本页面
 */
function refreshContribute(){
	$('#contributegrade').panel('refresh');	
}
/**
*分页
*/
function page(_nPageNum){
	$('#pageNum').val(_nPageNum);
	var queryString = $('#pageForm').serialize();
	
	$('#contributegrade').panel('refresh',"<%=rootPath%>set/contributelistofgrade.action?"+queryString);
}
/**
*排序
*/
function orderBy(_sFiled,_sOrderBy){
	$('#orderField').val(_sFiled);
	$('#orderBy').val(_sOrderBy);
	var queryString = $('#pageForm').serialize();
	$('#contributegrade').panel('refresh',"<%=rootPath%>set/contributelistofgrade.action?"+queryString);
}	
/**
 * 添加
 */
function addValueSetting(){
	var dialogdiv=document.createElement("div");
 	dialogdiv.id="addconfig";
 	document.body.appendChild(dialogdiv);
 	$('#addconfig').dialog({
 		modal:true,
 		cache:false,
 		href:'<%=rootPath%>set/valueSettingadd.action',
 		height:300,
 		width:400,
 		title:'增加用户级别',
 		resizable:true,
 		buttons:[{
 			text:'提交',
 			iconCls: 'icon-ok',
 			handler:function(){
 			 $('#valuesettingedit').form('submit',{
 				 url:'<%=rootPath%>set/addValueSettingConfig.action',
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
 						$.messager.alert("失败","增加级别配置失败了");
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
 * 修改用户级别配置
 */
function updateGrade(_settingid){
	var dialogdiv=document.createElement("div");
  	dialogdiv.id="updateconfig";
  	document.body.appendChild(dialogdiv);
  	$('#updateconfig').dialog({
  		modal:true,
  		href:'<%=rootPath%>set/loadupdategrade.action?settingid='+_settingid,
  		height:300,
  		width:400,
  		title:'修改用户级别配置',
  		resizable:true,
  		cache:false,
  		buttons:[{
  			text:'提交',
  			iconCls: 'icon-ok',
  			handler:function(){
  				$('#valuesettingedit').form('submit',{
  					url:'<%=rootPath%>set/updateValueSettingConfig.action',
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
  						if (_nStatus==1) {
  							refreshContribute();
  							$('#updateconfig').dialog('destroy');
  						}else{
  							$.messager.alert("失败","修改用户贡献失败了");
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
//删除级别配置
function deleteGrade(settingid,rankname){
	$.messager.confirm("操作提示","您确定删除名为“"+rankname+"”的级别配置吗？",function(r){
 		if(r){
 			$.messager.progress({
 				text:'提交数据中...'
 			});
 			$.ajax({
 				type:'post',
 				url:'<%=rootPath%>set/deleteValueSettingConfig.action?settingid='+settingid,
 				success:function(_nStatus){
 					 $.messager.progress('close');
 					if (_nStatus==1) {
 						$.messager.alert("操作提示","删除级别成功");
 						refreshContribute();
 					}else{
 						$.messager.alert("操作提示","删除级别失败");
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
			    url:'<%=rootPath  %>set/deleteValueSettingAll.action?settingids='+_configid,
			    success:function(_nStatus){
			    	 $.messager.progress('close');
			        if (_nStatus==1) {
						$.messager.alert("操作提示","成功删除了"+_configcatalength+"条数据");
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
	    	$('#contributegrade').panel('refresh',"<%=rootPath %>set/contributelistofgrade.action?"+queryString);
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
    <div data-options="name:'rankname'">名称</div>
    <div data-options="name:'rankdesc'">描述</div>
</div>
<table width="100%" border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
 <tr class="list_th" style="position: relative;">
  <td colspan="4" style="padding-left: 10px;">
   <a href="javascript:void(0)" onclick="addValueSetting()">添加</a>
   <a href="javascript:void(0)" onclick="deleteAll()">删除</a>
   <a href="javascript:void(0)" onclick="refreshContribute()">刷新</a>
  </td>
  <td colspan="4" align="right" style="padding-right: 2px;"><input id='listSearchText' /></td>
 </tr>
  <tr>
  <td align="center" width="60" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="checkRankAll()">全选</a></td>
  <td align="center" width="120" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('rankname','<s:if test="orderField=='rankname'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">级别名称<s:if test="orderField=='rankname'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='rankname'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
  <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('rankdesc','<s:if test="orderField=='rankdesc'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">级别描述<s:if test="orderField=='rankdesc'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='rankdesc'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
  <td align="center" width="80" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('beginscore','<s:if test="orderField=='beginscore'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">开始积分<s:if test="orderField=='beginscore'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='beginscore'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
  <td align="center" width="80" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('endscore','<s:if test="orderField=='endscore'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">结束积分<s:if test="orderField=='endscore'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='endscore'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
  <td align="center" width="80" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('crgroupnum','<s:if test="orderField=='crgroupnum'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">可创建组数<s:if test="orderField=='crgroupnum'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='crgroupnum'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
  <td align="center" width="80" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('crchannelnum','<s:if test="orderField=='crchannelnum'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">可创建栏目数<s:if test="orderField=='crchannelnum'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='crchannelnum'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
  <td align="center" width="60" bgcolor="#F5FAFE" class="main_bright">操作</td>
 </tr>
 <s:iterator value="irpValueSettinglist" status="irpValueSettingliststatus" >
 <tr>
  <td align="left" bgcolor="#F5FAFE" class="main_bright"><input name="irpvalueconfigid" value="<s:property value="settingid" />" type="checkbox" >&nbsp;<s:property value="(pageNum-1)*pageSize+#irpValueSettingliststatus.count"/></td>
  <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="rankname" /> </td>
  <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="rankdesc" /></td>
  <td align="center" bgcolor="#F5FAFE" class="main_bright"><s:property value="beginscore" /></td>
  <td align="center" bgcolor="#F5FAFE" class="main_bright"><s:property value="endscore" /></td>
  <td align="center" bgcolor="#F5FAFE" class="main_bright"><s:property value="crgroupnum" /></td>
  <td align="center" bgcolor="#F5FAFE" class="main_bright"><s:property value="crchannelnum" /></td>
  <td align="center" bgcolor="#F5FAFE" class="main_bright">
    <img border="0" src="images/icons/pencil.png" title="修改" style="cursor:pointer; margin: 0 5px;" onclick="updateGrade(<s:property value="settingid" />)"  />
    <img border="0" src="images/icons/cancel.png" title="删除" style="cursor:pointer; margin: 0 3px;" onclick="deleteGrade(<s:property value="settingid" />,'<s:property value="rankname" />')" />
  </td>
 </tr>
 </s:iterator>
 <tr bgcolor="#FFFFFF">
   <td colspan="8" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
  </tr>
</table>
</body>
</html>