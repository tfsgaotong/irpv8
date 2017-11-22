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
<!-- 
<script type="text/javascript" src="<%=rootPath %>admin/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>/admin/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>/admin/js/jquery.easyui.min.js"></script>

 -->
</head>
<body>
<script type="text/javascript">
//全局变量，
var m_checked = false;

/**
 * 刷新本页面
 */
function refreshCata(){
	$('#asseroomlist').panel('refresh');
}
/**
 * 申请会议室
 */
function addApply(_asseroomid){ 
window.open='<%=rootPath%>asseroomapply/asseroomid.action?searchWord='+_asseroomid;
}
/**
 * 修改
 */
function updateEdit(_asseroomid){
	var dialogdiv=document.createElement("div");
	dialogdiv.id="updateroom";
	document.body.appendChild(dialogdiv);
	$('#updateroom').dialog({
		modal:true,
		href:'<%=rootPath%>asseroom/updatefrom.action?asseroomid='+_asseroomid,
		height:290,
		width:400,
		title:'修改会议室信息',
		resizable:true,
		cache:false,
		buttons:[{
			text:'提交',
			iconCls: 'icon-ok',
			handler:function(){
				$('#editroom').form('submit',{
					url:'<%=rootPath%>asseroom/updateroom.action?asseroomid='+_asseroomid,
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
						$('#updateroom').dialog('destroy');
						if (_updateCataStat==1) {
							$.messager.alert("成功","恭喜你！会议室信息修改成功！"); 
							 $('#asseroomlist').panel('refresh');
						}else if(_updateCataStat==2){
							$.messager.alert("失败","会议室正在被使用，请删除预约申请后，再将会议室状态改为不可用"); 
							$('#asseroomlist').panel('refresh');
						}
						$('#asseroomlist').panel('refresh');
					}
				});
			}
		},{
			text:'取消',
			iconCls:'icon-cancel',
			handler:function(){
				$('#updateroom').dialog('destroy');
			}
		}],
		onClose:function(){
			$('#updateroom').dialog('destroy');
		}
		
	});
	
}
/**
 * 增加会议室
 */
function addEdit(){
	var dialogdiv=document.createElement("div");
	dialogdiv.id="addroom";
	document.body.appendChild(dialogdiv);
	$('#addroom').dialog({
		modal:true,
		cache:false,
		href:'<%=rootPath%>asseroom/roomfrom.action',
		height:290,
		width:400,
		title:'添加会议室',
		resizable:true,
		buttons:[{
			text:'提交',
			iconCls: 'icon-ok',
			handler:function(){
			 $('#editroom').form('submit',{
				 url:'<%=rootPath%>asseroom/addroom.action',
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
					 $('#addroom').dialog('destroy');
					 if (_cataStatus==1) {
						 $('#asseroomlist').panel('refresh');
					 }else{
						$.messager.alert("失败","增加会议室失败了");
						$('#asseroomlist').panel('refresh');
					 }
				 }
			 });
			}
		},{
			text:'取消',
			iconCls:'icon-cancel',
			handler:function(){
				$('#addroom').dialog('destroy');
			}
		}],
		onClose:function(){
			$('#add').dialog('destroy');
		}
		
	});
	
}
/**
 * 删除会议室
 */
 function deleteEdit(_asseroomname,_asseroomid){
	$.messager.confirm("删除会议室","您确定删除名称为"+_asseroomname+"的会议室吗",function(r){
		if(r){
			$.messager.progress({
				text:'提交数据中...'
			});
			$.ajax({
				type:'post',
				url:'<%=rootPath%>asseroom/deleteroom.action?asseroomid='+_asseroomid,
				success:function(_deletecata){
					 $.messager.progress('close');
					if (_deletecata==0) {
						$.messager.alert("操作提示","删除成功了");
						$('#asseroomlist').panel('refresh');
					}else if(_deletecata==2){
						$.messager.alert("操作提示","删除未成功,请先删除此会议室还未开始的会议，再进行删除");
						$('#asseroomlist').panel('refresh');
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
		$("input[name='asseroomcatach']").attr("checked",m_checked); 
	} 
	/**
	*分页
	*/
	function page(_nPageNum){
		$('#pageNum').val(_nPageNum);
		var queryString = $('#pageForm').serialize();
		$('#asseroomlist').panel('refresh',"<%=rootPath%>asseroom/asseroomlist.action?"+queryString);
	}
	/**
	*排序
	*/
	function orderBy(_sFiled,_sOrderBy){
		$('#orderField').val(_sFiled);
		$('#orderBy').val(_sOrderBy);
		var queryString = $('#pageForm').serialize();
		$('#asseroomlist').panel('refresh',"<%=rootPath%>asseroom/asseroomlist.action?"+queryString);
	}	
	/**
	*多选删除
	*/
	function deleteAll(){
	 var _asseroomid="";	
	 $("input[name='asseroomcatach']:checked").each(
			 function(){
				 _asseroomid+=$(this).val() + ',';
			 }
			 );
			 if (_asseroomid==null) {
			 	$.messager.alert("操作提示","请您先选择要删除的对象");
			 	return false;
			 } 
			 var _roomcatalength=$("input[name='asseroomcatach']:checked").length;
			 $.messager.confirm("操作提示","您确定要删除选中的"+_roomcatalength+"条数据吗",function(r){
				 if(r){
				       $.messager.progress({
							text:'提交数据中...'
						});
				   $.ajax({
				    type:'post',
				    url:'<%=rootPath  %>asseroom/deleteall.action?asseroomids='+_asseroomid,
				    success:function(date){
				    	 $.messager.progress('close');
				        if (date==1) {
							$.messager.alert("操作提示","您成功删除了"+_roomcatalength+"条数据");
							$('#asseroomlist').panel('refresh');
						}else if(date==0){
							$.messager.alert("操作提示","删除失败了");
							$('#asseroomlist').panel('refresh');
						}else{
							$.messager.alert("操作提示","会议室"+date+"正在被使用，不能删除！");
							$('#asseroomlist').panel('refresh');
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
		    	var flag=true;
		    	if(name=="asseroompeoples"){
		    		flag=/^[+]?[1-9]+\d*$/i.test(value);
		    	}
		    	if(flag){
			    	$('#pageForm').find('input[name="searchWord"]').val(value);
			    	$('#pageForm').find('input[name="searchType"]').val(name);
			    	$('#pageForm').find('input[name="pageNum"]').val('1');
			    	$('#pageForm').find('input[name="orderField"]').val('');
			    	$('#pageForm').find('input[name="orderBy"]').val('');
			    	var queryString = $('#pageForm').serialize();
			    	$('#asseroomlist').panel('refresh',"<%=rootPath %>asseroom/asseroomlist.action?"+queryString);
		    	}else{
		    		$.messager.alert("提示","容纳人数必须要输入整数数字");
		    		return false;
		    	}
		    },   
		    menu:'#listSearchType',   
		    prompt:'请输入检索词'  
		});
	});
	/*
	一键申请会议室
	*/
	function applyEdit(asseroomid){
		var dialogdiv=document.createElement("div");
		dialogdiv.id="apply";
		document.body.appendChild(dialogdiv);
		$('#apply').dialog({
			modal:true,
			cache:false,
			href:'<%=rootPath%>asseroomapply/oneapplyfrom.action?searchWord='+asseroomid,
			height:400,
			width:500,
			title:'预约会议室',
			resizable:true,
			buttons:[{
				text:'提交',
				iconCls: 'icon-ok',
				handler:function(){
				$('input[name="jsonFile"]').val(JSON.stringify(addJsonFileList));
				 $('#submit').form('submit',{
					 url:'<%=rootPath%>asseroomapply/oneapply.action',
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
						 $('#apply').dialog('destroy');
						 if (_cataStatus==2) {
						 	$.messager.alert("失败","申请失败");
							 $('#asseroomlist').panel('refresh');
						 }else{
							$('#asseroomlist').panel('refresh');
						 }
					 }
				 });
				}
			},{
				text:'取消',
				iconCls:'icon-cancel',
				handler:function(){
					$('#apply').dialog('destroy');
				}
			}],
			onClose:function(){
				$('#apply').dialog('destroy');
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
   <!-- 
   <div data-options="name:'all'">全部&nbsp;&nbsp;&nbsp;&nbsp;</div>
    --> 
   
    <div data-options="name:'asseroomname'">会议室名称</div>
    <div data-options="name:'asseroompeoples'">容纳人数</div>
</div>
 <table width="100%" border="0" align="center" cellpadding="10"
		cellspacing="1"  id="table1" bgcolor="#cad9ea">
		<tr class="list_th" style="position: relative;">
		 <td colspan="3" style="padding-left: 10px;">
		  <a href="javascript:void(0)" onclick="addEdit()">添加</a>
		  <a href="javascript:void(0)" onclick="deleteAll()">删除</a>
		  <a href="javascript:void(0)" onclick="refreshCata()">刷新</a>
		 </td>
		 <td colspan="5" align="right" style="padding-right: 2px;"><input id='listSearchText' /></td>
		</tr>
		<tr>
		 <td align="center" width="60" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="checkAll()">全选</a></td>
		 <td align="center" width="120" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('asseroomname','<s:if test="orderField=='asseroomname'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">会议室名称<s:if test="orderField=='asseroomname'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='asseroomname'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center"	width="200" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('asseroomdesc','<s:if test="orderField=='asseroomdesc'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">会议室描述<s:if test="orderField=='asseroomdesc'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='asseroomdesc'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" width="120" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('asseroomstatus','<s:if test="orderField=='asseroomstatus'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">会议室状态<s:if test="orderField=='asseroomstatus'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='asseroomstatus'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" width="200" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('asseroomaddr','<s:if test="orderField=='asseroomaddr'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">会议室地址<s:if test="orderField=='asseroomaddr'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='asseroomaddr'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" width="120" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('asseroompeoples','<s:if test="orderField=='asseroompeoples'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">容纳人数<s:if test="orderField=='asseroompeoples'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='asseroompeoples'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		  <td align="center" width="250" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('asseroompeoples','<s:if test="orderField=='asseroompeoples'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">会议室设备<s:if test="orderField=='asseroompeoples'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='asseroompeoples'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" width="130" bgcolor="#F5FAFE" class="main_bright">操作</td>
		</tr> 
		<s:iterator value="asseroomList" status="asseroomListstatus">
		<tr>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright"><input name="asseroomcatach" type="checkbox" value="<s:property value='asseroomid' />">&nbsp;<s:property value="(pageNum-1)*pageSize+#asseroomListstatus.count"/></td>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="asseroomname" /></td>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="asseroomdesc" /></td>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:if test="asseroomstatus==1">可用</s:if><s:if test="asseroomstatus==0">不可用</s:if></td>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="asseroomaddr" /></td>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="asseroompeoples" /></td>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="asseroomsbs" /></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright">
		   <img border="0" src="images/icons/pencil.png" title="修改" style="cursor:pointer; margin: 0 5px;" onclick="updateEdit(<s:property value="asseroomid" />)"  />
           <img border="0" src="images/icons/cancel.png" title="删除" style="cursor:pointer; margin: 0 3px;" onclick="deleteEdit('<s:property value="asseroomname" />',<s:property value="asseroomid" />)" />
          <!-- 
          
          
          <a href='javascript:void(0)' onclick="applyEdit(<s:property value="asseroomid" />)" class="linkbh14">一键申请</a>
           -->
		 </td>
		</tr>
		</s:iterator>
		<tr bgcolor="#FFFFFF">
		 <td colspan="8" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
		</tr>
      </table>
</body>
</html>