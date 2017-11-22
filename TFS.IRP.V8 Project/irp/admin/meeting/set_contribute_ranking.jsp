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
<title>用户剩余年假</title>
</head>
<body>


<script type="text/javascript">
/*全局变量*/
var m_checked = false;
/**
 * 刷新本页面
 */
function refreshContribute(){
	$('#contributeranking').panel('refresh');
}
/**
 * 全选
 */
function checkRankAll(){
	m_checked = !m_checked;
	$("input[name='irpvalueconfigid']").attr("checked",m_checked); 
	} 
/**
*分页
*/
function page(_nPageNum){
	$('#pageNum').val(_nPageNum);
	var queryString = $('#pageForm').serialize();
	$('#contributeranking').panel('refresh',"<%=rootPath%>set/searchUserLeaveYearDays.action?"+queryString);
}
/**
*排序
*/
function orderByOf(_sFiled,_sOrderBy){
	$('#orderField').val(_sFiled);
	$('#orderBy').val(_sOrderBy);
	var queryString = $('#pageForm').serialize();
	$('#contributeranking').panel('refresh',"<%=rootPath%>set/searchUserLeaveYearDays.action?"+queryString);
}	
/**	
 * 修改
 */
function updateRankEdit(userid){
	
	var dialogdiv=document.createElement("div");
  	dialogdiv.id="updateconfig";
  	document.body.appendChild(dialogdiv);
  	$('#updateconfig').dialog({
  		modal:true,
  		href:'<%=rootPath%>set/toUpdateLeaveYearDays.action?userid='+userid,
  		height:300,
  		width:400,
  		title:'修改用户剩余年假天数',
  		resizable:true,
  		cache:false,
  		buttons:[{
  			text:'提交',
  			iconCls: 'icon-ok',
  			handler:function(){
  				$('#rankupdate').form('submit',{
  					url:'<%=rootPath%>set/updateLeaveYearDays.action',
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
  							$.messager.alert("失败","修改用户剩余年假失败了");
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

function updateAll(){
	
			
			var dialogdiv=document.createElement("div");
		  	dialogdiv.id="updateconfig";
		  	document.body.appendChild(dialogdiv);
		  	$('#updateconfig').dialog({
		  		modal:true,
		  		href:'<%=rootPath%>set/toLeaveYearDays.action',
		  		height:150,
		  		width:250,
		  		title:'一键修改所有用户的天数',
		  		resizable:true,
		  		cache:false,
		  		buttons:[{
		  			text:'提交',
		  			iconCls: 'icon-ok',
		  			handler:function(){
		  				$('#rankupdate').form('submit',{
		  					url:'<%=rootPath%>set/updateALLLeaveYearDays.action',
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
		  							$.messager.alert("失败","一键修改所有用户的天数");
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
	*多选清空
	*/
	function deleteAll(){
		
	 var _configid="";	
	 $("input[name='irpvalueconfigid']:checked").each(
			 function(){
				 _configid+=$(this).val() + ',';
			 }
			 );

			 var _configcatalength=$("input[name='irpvalueconfigid']:checked").length;
			
			 if (_configid==null||_configcatalength<=0) {
			 	$.messager.alert("操作提示","请您先选择要修改的对象");
			 	return false;
			 } 	
			 
			
						var dialogdiv=document.createElement("div");
					  	dialogdiv.id="updateconfig";
					  	document.body.appendChild(dialogdiv);
					  	$('#updateconfig').dialog({
					  		modal:true,
					  		href:'<%=rootPath%>set/toLeaveYearDays.action?userids='+_configid,
					  		height:150,
					  		width:250,
					  		title:'选择修改用户的天数',
					  		resizable:true,
					  		cache:false,
					  		buttons:[{
					  			text:'提交',
					  			iconCls: 'icon-ok',
					  			handler:function(){
					  				$('#rankupdate').form('submit',{
					  					url:'<%=rootPath%>set/updateWithChooseLeaveYearDays.action',
					  										  				
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
					  					  alert(_nStatus);
					  						$.messager.progress('close');
					  						$('#updateconfig').dialog('destroy');		  					
					  						if (_nStatus>0) {
					  							
					  							refreshContribute();
					  						}else{
					  							$.messager.alert("失败","选择修改用户的年假天数失败");
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
		    	$('#contributeranking').panel('refresh',"<%=rootPath %>set/searchUserLeaveYearDays.action?"+queryString);
		    },   
		    menu:'#listSearchType',   
		    prompt:'请输入检索词'  
		});
		
	});   
	
/**
*查看某个用户的详细信息
**/
function searchDetail(userid,username){
 	var dialogdiv=document.createElement("div");
 	dialogdiv.id="userContributeDetail";
 	document.body.appendChild(dialogdiv);
 	$('#userContributeDetail').dialog({
 		modal:true,
 		cache:false,
 		href:"<%=rootPath%>set/loadUserleavedaysDetail.action?useridsearch="+userid+"&datanumid=2&orderField&orderBy&pageNum=1",
 		height:500,
 		width:700,
 		title:'用户['+username+']年假休假情况',
 		resizable:true,
 		buttons:[{
  			text:'关闭',
  			iconCls: 'icon-cancel',
  			handler:function(){
  				$('#userContributeDetail').dialog('destroy');
  		}
  		
  		}],
  		onClose:function(){
  			$('#userContributeDetail').dialog('destroy');
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
<div id="common">
<div id="listSearchType" style="width:120px;"> 
  
    <div data-options="name:'username'">用户名&nbsp;&nbsp;&nbsp;&nbsp;</div>
    <div data-options="name:'holiday'">剩余年假天数</div>
   
</div>
<table width="100%" border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
<tr class="list_th" style="position: relative;">
  <td colspan="3" style="padding-left: 10px;">
  <a href="javascript:void(0)" onclick="refreshContribute()">刷新</a> 
  <a href="javascript:void(0)" onclick="deleteAll()">选中修改</a>
  <a href="javascript:void(0)" onclick="updateAll()">一键修改</a>
  
   <td colspan="4" align="right" style="padding-right: 2px;"><input id='listSearchText' /></td>
   
 </tr>
 
 <tr>
  <td align="center" width="5%" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="checkRankAll()">全选</a></td>
  <td align="center" width="55%" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderByOf('username','<s:if test="orderField=='username'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">用户名<s:if test="orderField=='username'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='username'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
  <td align="center" width="15%" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderByOf('sumscore','<s:if test="orderField=='holiday'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">剩余年假天数<s:if test="orderField=='holiday'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='holiday'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
  <td align="center" width="15%" bgcolor="#F5FAFE" class="main_bright">查看详细</td>
  <td align="center" width="10%" bgcolor="#F5FAFE" class="main_bright">操作</td>
 </tr>
 
 
 <s:iterator value="userlist" status="userliststatus" >
 <tr>
  <td align="left" bgcolor="#F5FAFE" class="main_bright"><input name="irpvalueconfigid" value="<s:property value="userid" />" type="checkbox" >&nbsp;<s:property value="(pageNum-1)*pageSize+#userliststatus.count"/></td>
  <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="username" /></td>
  <td align="center" bgcolor="#F5FAFE" class="main_bright"><s:property value="holiday" /></td>
  <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="searchDetail(<s:property value="userid" />,'<s:property value="username" />')" >查看</a></td>
  <td align="center" bgcolor="#F5FAFE" class="main_bright">
    <img border="0" src="images/icons/pencil.png" title="修改" style="cursor:pointer; margin: 0 5px;" onclick="updateRankEdit(<s:property value="userid" />)"  />
  </td>
 </tr>
 </s:iterator>
 <tr bgcolor="#FFFFFF">
   <td colspan="7" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
  </tr>
</table>
</div>


<div id="container" style="display: none;"></div>



</body>
</html>