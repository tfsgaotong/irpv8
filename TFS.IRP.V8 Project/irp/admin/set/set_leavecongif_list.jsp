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
<title>列表</title>
</head>
<body>
<script type="text/javascript">
//全局变量，
var m_checked = false;

/**
 * 刷新本页面
 */
function refreshCata(){
var _type = $("#marking").val();
var id ="#cataloguelist"+_type;
$(id).panel('refresh');
}

/**
 * 修改
 */
function updateEdit(_configid){
	var dialogdiv=document.createElement("div");
	dialogdiv.id="updateconfig";
	document.body.appendChild(dialogdiv);
	$('#updateconfig').dialog({
		modal:true,
		href:'<%=rootPath%>leaveconfig/leaveconfigform.action?leaveconfigid='+_configid,
		height:210,
		width:400,
		title:'修改存放目录',
		resizable:true,
		cache:false,
		buttons:[{
			text:'提交',
			iconCls: 'icon-ok',
			handler:function(){
				$('#editconfig').form('submit',{
					url:'<%=rootPath%>leaveconfig/upleaveconfiginfo.action?leaveconfigid='+_configid,
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
						$('#updateconfig').dialog('destroy');
						if (_updateCataStat==1) {
							refreshCata();
						}else{
							$.messager.alert("失败","增加存放目录失败了");
							refreshCata();
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
 * 增加
 */
function addEdit(){
	var _type = $("#marking").val();
	var type;
	if(_type==10){
		type="请假";
	}else{
		type="加班";
	}
	var dialogdiv=document.createElement("div");
	dialogdiv.id="addconfig";
	document.body.appendChild(dialogdiv);
	$('#addconfig').dialog({
		modal:true,
		cache:false,
		href:'<%=rootPath%>leaveconfig/leaveconfigAddform.action',
		height:168,
		width:264,
		title:'添加'+type+'配置',
		resizable:true,
		buttons:[{
			text:'提交',
			iconCls: 'icon-ok',
			handler:function(){
			 $('#editconfig').form('submit',{
				 url:'<%=rootPath%>leaveconfig/toaddfrom.action?marking='+_type,
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
						$.messager.alert("失败","增加"+type+"配置失败了");
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
 * 删除
 */
 function deleteEdit(_ckey,_configid){
 var _type = $("#marking").val();
	var type;
	if(_type==10){
		type="请假";
	}else{
		type="加班";
	}
	$.messager.confirm("删除存放目录","您确定删除名称为"+_ckey+"的"+type+"配置吗",function(r){
		if(r){
			$.messager.progress({
				text:'提交数据中...'
			});
			$.ajax({
				type:'post',
				url:'<%=rootPath%>leaveconfig/delleaveconfig.action?leaveconfigid='+_configid,
				success:function(_deletecata){
					 $.messager.progress('close');
					if (_deletecata==1) {
						$.messager.alert("操作提示","删除成功了");
						refreshCata();
					}else{
						$.messager.alert("操作提示","删除未成功");
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
		$("input[name='irpconfigcatach']").attr("checked",m_checked); 
	} 
	/**
	*分页
	*/
	function page(_nPageNum){
		var _type = $("#marking").val();
		var id ="#cataloguelist"+_type;
		$('#pageNum').val(_nPageNum);
		var queryString = $('#pageForm').serialize();
		$(id).panel('refresh',"<%=rootPath%>leaveconfig/allleaveconfigList.action?"+queryString);
	}
	/**
	*排序
	*/
	function orderBy(_sFiled,_sOrderBy){
		$('#orderField').val(_sFiled);
		$('#orderBy').val(_sOrderBy);
		var queryString = $('#pageForm').serialize();
		var _type = $("#marking").val();
		var id ="#cataloguelist"+_type;
		$(id).panel('refresh',"<%=rootPath%>leaveconfig/allleaveconfigList.action?"+queryString);
	}	
	/**
	*多选删除
	*/
	function deleteAll(){
	 var _configid='';	
	 $("input[name='irpconfigcatach']:checked").each(
			 function(){
				 _configid+=$(this).val() + ',';
			 }
			 );
			 if (_configid==null) {
			 	$.messager.alert("操作提示","请您先选择要删除的对象");
			 	return false;
			 } 	
			 var _configcatalength=$("input[name='irpconfigcatach']:checked").length;
			 $.messager.confirm("操作提示","您确定要删除选中的"+_configcatalength+"条数据吗",function(r){
				 if(r){
				       $.messager.progress({
							text:'提交数据中...'
						});
				   $.ajax({
				    type:'post',
				    url:'<%=rootPath%>leaveconfig/delallleaveconfig.action?leaveconfigids='+_configid,
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
		    	var _type = $("#marking").val();
				var id ="#cataloguelist"+_type;
				console.log(queryString);
		    	$(id).panel('refresh',"<%=rootPath%>leaveconfig/allleaveconfigList.action?"+queryString);
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
<s:hidden name="marking" id="marking" />
</form>
<div id="listSearchType" style="width:120px;">  
    <div data-options="name:'all'">全部&nbsp;&nbsp;&nbsp;&nbsp;</div>
    <div data-options="name:'leaveconfigname'">名称</div>
</div>
 <table width="100%" border="0" align="center" cellpadding="10"
		cellspacing="1"  id="table1" bgcolor="#cad9ea">
		<tr class="list_th" style="position: relative;">
		 <td colspan="3" style="padding-left: 10px;">
		  <a href="javascript:void(0)" onclick="addEdit()">添加</a>
		  <a href="javascript:void(0)" onclick="deleteAll()">删除</a>
		  <a href="javascript:void(0)" onclick="refreshCata()">刷新</a>
		 </td>
		 <td colspan="3" align="right" style="padding-right: 2px;"><input id='listSearchText' /></td>
		</tr>
		<tr>
		 <td align="center" width="60" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="checkAll()">全选</a></td>
		 <td align="center" width="120" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('leaveconfigname','<s:if test="orderField=='leaveconfigname'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">名称<s:if test="orderField=='leaveconfigname'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='leaveconfigname'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('leaveconfigdesc','<s:if test="orderField=='leaveconfigdesc'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">说明<s:if test="orderField=='leaveconfigdesc'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='leaveconfigdesc'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" width="200" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" >创建人</a></td>
		 <td align="center" width="70" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)">状态</a></td>
		 <td align="center" width="60" bgcolor="#F5FAFE" class="main_bright">操作</td>
		</tr> 
		<s:iterator value="irpLeaveconfigs" status="irpLeaveconfigs">
		<tr>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright"><input name="irpconfigcatach" type="checkbox" value="<s:property value='leaveconfigid' />">&nbsp;<s:property value="(pageNum-1)*pageSize+#irpLeaveconfigs.count"/></td>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="leaveconfigname" /></td>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="leaveconfigdesc" /></td>
		  <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="@com.tfs.irp.util.LoginUtil@findUserById(cruserid).getUsername()" /></td>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright">
		 <s:if test="applystatus==10">
		 	正常
		 </s:if>
		 <s:else>
		 	非正常
		 </s:else>
		 </td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright">
		   <img border="0" src="images/icons/pencil.png" title="修改" style="cursor:pointer; margin: 0 5px;" onclick="updateEdit(<s:property value="leaveconfigid" />)"  />
           <img border="0" src="images/icons/cancel.png" title="删除" style="cursor:pointer; margin: 0 3px;" onclick="deleteEdit('<s:property value="leaveconfigname" />',<s:property value="leaveconfigid" />)" />
		 </td>
		</tr>
		</s:iterator>
		<tr bgcolor="#FFFFFF">
		 <td colspan="6" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
		</tr>
      </table>
</body>
</html>