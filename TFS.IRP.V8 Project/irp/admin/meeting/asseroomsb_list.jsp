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
	$('#asseroomsblist').panel('refresh');
}

/**
 * 修改
 */
function updateEdit(_asseroomsbid){
	var dialogdiv=document.createElement("div");
	dialogdiv.id="updatesb";
	document.body.appendChild(dialogdiv);
	$('#updatesb').dialog({
		modal:true,
		href:'<%=rootPath%>asseroomsb/sbfrom.action?asseroomsbid='+_asseroomsbid,
		height:240,
		width:400,
		title:'修改设备信息',
		resizable:true,
		cache:false,
		buttons:[{
			text:'提交',
			iconCls: 'icon-ok',
			handler:function(){
				$('#editsb').form('submit',{
					url:'<%=rootPath%>asseroomsb/updatesb.action?asseroomsbid='+_asseroomsbid,
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
						$('#updatesb').dialog('destroy');
						if (_updateCataStat==1) {
							 $('#asseroomsblist').panel('refresh');
						}else{
							$.messager.alert("失败","增加会议室设备失败了");
							$('#asseroomsblist').panel('refresh');
						}
					}
				});
			}
		},{
			text:'取消',
			iconCls:'icon-cancel',
			handler:function(){
				$('#updatesb').dialog('destroy');
			}
		}],
		onClose:function(){
			$('#updatesb').dialog('destroy');
		}
		
	});
	
}
/**
 * 增加会议室设备信息
 */
function addEdit(){
	var dialogdiv=document.createElement("div");
	dialogdiv.id="addsb";
	document.body.appendChild(dialogdiv);
	$('#addsb').dialog({
		modal:true,
		cache:false,
		href:'<%=rootPath%>admin/meeting/asseroomsb_edit.jsp',
		height:240,
		width:400,
		title:'添加会议室设备',
		resizable:true,
		buttons:[{
			text:'提交',
			iconCls: 'icon-ok',
			handler:function(){
			 $('#editsb').form('submit',{
				 url:'<%=rootPath%>asseroomsb/addSb.action',
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
					 $('#addsb').dialog('destroy');
					 if (_cataStatus==1) {
						 $('#asseroomsblist').panel('refresh');
					 }else{
						$.messager.alert("失败","增加会议室设备失败了");
						$('#asseroomsblist').panel('refresh');
					 }
				 }
			 });
			}
		},{
			text:'取消',
			iconCls:'icon-cancel',
			handler:function(){
				$('#addsb').dialog('destroy');
			}
		}],
		onClose:function(){
			$('#addsb').dialog('destroy');
		}
		
	});
	
}
/**
 * 删除会议室设备
 */
 function deleteEdit(_asseroomsbname,_asseroomsbid){
	$.messager.confirm("删除会议室设备","您确定删除名称为"+_asseroomsbname+"的会议室设备吗",function(r){
		if(r){
			$.messager.progress({
				text:'提交数据中...'
			});
			$.ajax({
				type:'post',
				url:'<%=rootPath%>asseroomsb/deletesb.action?asseroomsbid='+_asseroomsbid,
				success:function(_deletecata){
					 $.messager.progress('close');
					if (_deletecata==1) {
						$.messager.alert("操作提示","删除成功了");
						$('#asseroomsblist').panel('refresh');
					}else{
						$.messager.alert("操作提示","删除未成功");
						$('#asseroomsblist').panel('refresh');
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
		$("input[name='asseroomsbcatach']").attr("checked",m_checked); 
	} 
	/**
	*分页
	*/
	function page(_nPageNum){
		$('#pageNum').val(_nPageNum);
		var queryString = $('#pageForm').serialize();
		$('#asseroomsblist').panel('refresh',"<%=rootPath%>asseroomsb/asseroomsblist.action?"+queryString);
	}
	/**
	*排序
	*/
	function orderBy(_sFiled,_sOrderBy){
		$('#orderField').val(_sFiled);
		$('#orderBy').val(_sOrderBy);
		var queryString = $('#pageForm').serialize();
		$('#asseroomsblist').panel('refresh',"<%=rootPath%>asseroomsb/asseroomsblist.action?"+queryString);
	}	
	/**
	*多选删除
	*/
	function deleteAll(){
	 var _asseroomsbid="";	
	 $("input[name='asseroomsbcatach']:checked").each(
			 function(){
				 _asseroomsbid+=$(this).val() + ',';
			 }
			 );
			 if (_asseroomsbid==null) {
			 	$.messager.alert("操作提示","请您先选择要删除的对象");
			 	return false;
			 } 
			 var _sbcatalength=$("input[name='asseroomsbcatach']:checked").length;
			 $.messager.confirm("操作提示","您确定要删除选中的"+_sbcatalength+"条数据吗?",function(r){
				 if(r){
				       $.messager.progress({
							text:'提交数据中...'
						});
				   $.ajax({
				    type:'post',
				    url:'<%=rootPath  %>asseroomsb/deleteall.action?asseroomsbids='+_asseroomsbid,
				    success:function(date){
				    	 $.messager.progress('close');
				        if (date==1) {
							$.messager.alert("操作提示","您成功删除了"+_sbcatalength+"条数据");
							$('#asseroomsblist').panel('refresh');
						}else{
							$.messager.alert("操作提示","删除失败了");
							$('#asseroomsblist').panel('refresh');
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
		    	$('#asseroomsblist').panel('refresh',"<%=rootPath %>asseroomsb/asseroomsblist.action?"+queryString);
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
    <div data-options="name:'asseroomsbname'">设备名称</div>
    <div data-options="name:'asseroomsbtype'">设备型号</div>
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
		 <td align="center" width="120" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('asseroomsbname','<s:if test="orderField=='asseroomsbname'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">设备名称<s:if test="orderField=='asseroomsbname'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='asseroomsbname'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('asseroomsbdesc','<s:if test="orderField=='asseroomsbdesc'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">设备描述<s:if test="orderField=='asseroomsbdesc'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='asseroomsbdesc'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" width="200" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('asseroomsbuse','<s:if test="orderField=='asseroomsbuse'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">设备用途<s:if test="orderField=='asseroomsbuse'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='asseroomsbsbuse'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" width="200" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('asseroomsbtype','<s:if test="orderField=='asseroomsbtype'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">设备型号<s:if test="orderField=='asseroomsbtype'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='asseroomsbsbuse'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" width="200" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('asseroomsbstatus','<s:if test="orderField=='asseroomsbstatus'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">设备状态<s:if test="orderField=='asseroomsbstatus'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='asseroomsbstatus'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" width="60" bgcolor="#F5FAFE" class="main_bright">操作</td>
		</tr> 
		<s:iterator value="asseroomsbList" status="asseroomsbListstatus">
		<tr>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright"><input name="asseroomsbcatach" type="checkbox" value="<s:property value='asseroomsbid' />">&nbsp;<s:property value="(pageNum-1)*pageSize+#asseroomsbListstatus.count"/></td>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="asseroomsbname" /></td>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="asseroomsbdesc" /></td>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="asseroomsbuse" /></td>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="asseroomsbtype" /></td>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:if test="asseroomsbstatus==1">可用</s:if><s:if test="asseroomsbstatus==0">不可用</s:if></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright">
		   <img border="0" src="images/icons/pencil.png" title="修改" style="cursor:pointer; margin: 0 5px;" onclick="updateEdit(<s:property value="asseroomsbid" />)"  />
           <img border="0" src="images/icons/cancel.png" title="删除" style="cursor:pointer; margin: 0 3px;" onclick="deleteEdit('<s:property value="asseroomsbname" />',<s:property value="asseroomsbid" />)" />
		 </td>
		</tr>
		</s:iterator>
		<tr bgcolor="#FFFFFF">
		 <td colspan="7" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
		</tr>
      </table>
</body>
</html>