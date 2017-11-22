<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />
</head>

<body>
<script type="text/javascript">
//全局变量，
var m_checked = false;
$(function(){
$('#searchinput').searchbox({
		width:220,
	    searcher:function(value,name){   
	    	$('#pageForm').find('input[name="searchWord"]').val(value);
	    	$('#pageForm').find('input[name="searchType"]').val(name);
	    	$('#pageForm').find('input[name="pagenum"]').val('1');
	    	$('#pageForm').find('input[name="orderField"]').val('');
	    	$('#pageForm').find('input[name="orderBy"]').val('');
	    	var cateid =  '<s:property value="cateid" />';
	    	if(cateid==""){
				cateid = 0;
			}
	    	$('#pageForm').find('input[name="cateid"]').val(cateid);
	    	var queryString = $('#pageForm').serialize();
	    	jump("<%=rootPath%>exam/testpapermenu.action?"+queryString);
	    },   
	    menu:'#listSearchText',   
	    prompt:'请输入检索词'  
	});
});

/**
* 增加试卷	
*/
function addTestPaper(){
	var cateid = '<s:property value="cateid" />';
    if(cateid==""){
   		$.messager.alert("提示","请先选择左侧分类!","info");
   		return false;
   	}
   	
   	var dialogdiv=document.createElement("div");
 	dialogdiv.id="addtestpaperdiv";
 	document.body.appendChild(dialogdiv);
 	$('#addtestpaperdiv').dialog({
 		modal:true,
 		cache:false,
 		href:'<%=rootPath%>exam/linkaddtestpaper.action?cateid='+cateid,
 		height:600,
 		width:600,
 		title:'添加试卷',
 		resizable:true,
 		buttons:[{
 			text:"提交",
 			iconCls: 'icon-ok',
 			handler:function(){
				$("#addtestpapers").form('submit',{
					url:'<%=rootPath %>exam/addtestpaper.action',
					onSubmit:function(){					
					},
					success:function(){
						$('#addtestpaperdiv').dialog('destroy');
						refreshTestPaper();
					}
				});
				$('#addtestpaperdiv').dialog('destroy');
 			}
 		},{
 			text:"取消",
 			iconCls: 'icon-cancel',
 			handler:function(){
 				$('#addtestpaperdiv').dialog('destroy');
 			}
 		
 		}
 		],
 		onClose:function(){
 			$('#addtestpaperdiv').dialog('destroy');
 		}
 	
	});
}
/**
*排序
*/
function orderBy(_sFiled,_sOrderBy){
	$('#orderField').val(_sFiled);
	$('#orderBy').val(_sOrderBy);
	var cateid = '<s:property value="cateid" />';
	if(cateid==""){
		cateid = 0;
	}
	$('#cateid').val(cateid);
	var queryString = $('#pageForm').serialize();
	jump("<%=rootPath%>exam/testpapermenu.action?"+queryString);
}
function pagePaper(_nums){
	var cateid = '<s:property value="cateid" />';
	if(cateid==""){
		cateid = 0;
	}
	$('#cateid').val(cateid);
	$("#pageadminnum").val(_nums);
	var queryString = $('#pageForm').serialize();
	jump("<%=rootPath%>exam/testpapermenu.action?"+queryString);
}


function jump(_url){
	$('body').layout('panel','center').panel('refresh',_url);	
}

//刷新
function refreshTestPaper(){
	$('body').layout('panel','center').panel('refresh');
}
function checkAll(){
	
	m_checked = !m_checked;
	$("input[name='paperid']").attr("checked",m_checked); 
}
function deletePaper(_paperid){
	
	 $.messager.confirm("操作提示","您确定要删除这条数据吗",function(r){
	 if(r){
	 	$.ajax({
		     type:'post',
		     url:'<%=rootPath%>exam/deletetpaperid.action',
		     data:{
		     	paperid:_paperid
		     },
			 cache:false,
			 async:false,
			 success:function(msg){
		        if (msg==1) {
					$.messager.alert("操作提示","删除成功了");
					refreshTestPaper();
				}else{
					$.messager.alert("操作提示","删除失败了");
					refreshTestPaper();
				}
			 }
		 	});
	 }
 });
}
//多选删除
function deleteTestPaper(){
 var _configid = "";
	 $("input[name='paperid']:checked").each(
			 function(){
				 _configid+=$(this).val() + ',';
			 }
			 );
			 if (_configid=="") {
			 	$.messager.alert("操作提示","请您先选择要删除的对象");
			 	return false;
			 } 	
			 var _configcatalength=$("input[name='paperid']:checked").length;
			 $.messager.confirm("操作提示","您确定要删除选中的"+_configcatalength+"条数据吗",function(r){
				 
				 if(r){
			       $.messager.progress({
						text:'提交数据中...'
					});
				   $.ajax({
				    type:'post',
				    url:'<%=rootPath%>exam/deletetestpaperids.action?qtestpidstr='+_configid,
				    success:function(date){
				    	 $.messager.progress('close');
				        if (date==1) {
							$.messager.alert("操作提示","您成功删除了"+_configcatalength+"条数据");
							refreshTestPaper();
						}else{
							$.messager.alert("操作提示","删除失败了");
							refreshTestPaper();
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
* 修改试卷
*/
function updatePaper(_pid){

   	var dialogdiv=document.createElement("div");
 	dialogdiv.id="updatetestpaperdiv";
 	document.body.appendChild(dialogdiv);
 	$('#updatetestpaperdiv').dialog({
 		modal:true,
 		cache:false,
 		href:'<%=rootPath%>exam/linkupdatetestpaper.action?updatepid='+_pid,
 		height:600,
 		width:600,
 		title:'修改试卷',
 		resizable:true,
 		buttons:[{
 			text:"提交",
 			iconCls: 'icon-ok',
 			handler:function(){
				$("#addtestpapers").form('submit',{
					url:'<%=rootPath %>exam/updatetestpaper.action',
					onSubmit:function(){

					},
					success:function(){
						$('#updatetestpaperdiv').dialog('destroy');
						refreshTestPaper();
					}
				});
				$('#updatetestpaperdiv').dialog('destroy');
 			}
 		},{
 			text:"取消",
 			iconCls: 'icon-cancel',
 			handler:function(){
 				$('#updatetestpaperdiv').dialog('destroy');
 			}
 		
 		}
 		],
 		onClose:function(){
 			$('#updatetestpaperdiv').dialog('destroy');
 		}
 	
	});
}
//编辑权限
function editJurisdiction(_paperid){
   	var dialogdiv=document.createElement("div");
 	dialogdiv.id="jurisdictiondiv";
 	document.body.appendChild(dialogdiv);
 	$('#jurisdictiondiv').dialog({
 		modal:true,
 		cache:false,
 		href:'<%=rootPath%>exam/linkjurisdictiontp.action?paperid='+_paperid,
 		height:600,
 		width:700,
 		title:'编辑权限',
 		resizable:true,
 		buttons:[{
 			text:"提交",
 			iconCls: 'icon-ok',
 			handler:function(){
 				if(groupids!=""){
	 				$.ajax({
	 					type:'post',
	 					url:'<%=rootPath%>exam/addtestpapergroup.action',
	 					data:{
	 						groupids:groupids,
	 						paperid:_paperid
	 					},
	 					cache:false,
	 					async:false,
	 					success:function(msg){
	 					$('#jurisdictiondiv').dialog('destroy');
	 					}
	 				});
 				}else{
 				$('#jurisdictiondiv').dialog('destroy');
 				
 				}
 				
 			}
 		},{
 			text:"取消",
 			iconCls: 'icon-cancel',
 			handler:function(){
 				$('#jurisdictiondiv').dialog('destroy');
 			}
 		
 		}
 		],
 		onClose:function(){
 			$('#jurisdictiondiv').dialog('destroy');
 		}
 	
	});
}
</script>
<form id="pageForm">
	<s:hidden name="searchWord" id="searchWord" />
	<s:hidden name="searchType" id="searchType" />
	<s:hidden name="pageadminnum" id="pageadminnum" />
	<s:hidden name="cateid" id="cateid" />
	<s:hidden name="orderField" id="orderField" />
	<s:hidden name="orderBy" id="orderBy" />
</form>
<table width="100%" id="flowList" border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
	<tr class="list_th" style="position: relative;">
   	  	<td colspan="6" align="left" style="padding: 5px 0 5px 10px;" nowrap="nowrap">
			
			<a href="javascript:void(0)" onclick="addTestPaper()"  class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加试卷</a>
			<a href="javascript:void(0)" onclick="deleteTestPaper()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">删除</a>
			<a href="javascript:void(0)" onclick="refreshTestPaper()" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新</a>
   	  	</td>   
   	  	<td colspan="3" align="right" style="padding: 5px 0 5px 10px;" nowrap="nowrap">
   	  		<input name="searchinput" id="searchinput"  />
				<div id="listSearchText">  
				    <div data-options="name:'title'">标题&nbsp;&nbsp;&nbsp;&nbsp;</div>
				</div>
			
   	  	</td>   	  	
	</tr>

    <tr>

	    <td width="5%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="checkAll()">全选</a></td>
	    <td width="40%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('papertitle','<s:if test="orderField=='papertitle'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">标题<s:if test="orderField=='papertitle'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='papertitle'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="5%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('paperstatus','<s:if test="orderField=='paperstatus'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">状态<s:if test="orderField=='paperstatus'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='paperstatus'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="8%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('extendsone','<s:if test="orderField=='extendsone'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">组卷方式<s:if test="orderField=='extendsone'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='extendsone'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="5%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('papertime','<s:if test="orderField=='papertime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">总分<s:if test="orderField=='papertime'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='papertime'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="6%" align="center" bgcolor="#F5FAFE"  class="main_bright">试题数量</td>
	    <td width="10%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('cruserid','<s:if test="orderField=='cruserid'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">创建人<s:if test="orderField=='cruserid'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='cruserid'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="13%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('crtime','<s:if test="orderField=='crtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">创建时间<s:if test="orderField=='crtime'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='crtime'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		
	    <td width="8%" align="center" bgcolor="#F5FAFE"  class="main_bright">操作</td>
	    
    </tr>
    <s:iterator value="irpTestpaperlist" status="irpTestpaperliststatus" >
    <tr>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><input name="paperid" type="checkbox" value="<s:property value='paperid' />">&nbsp;<s:property value="(pagenumpaper-1)*10+#irpTestpaperliststatus.count"/></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:property value="papertitle" /></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright">
	    	<s:if test="paperstatus==@com.tfs.irp.testpaper.entity.IrpTestpaper@NOFABU">
	    	未发布
	    	</s:if>
	    	<s:elseif test="paperstatus==@com.tfs.irp.testpaper.entity.IrpTestpaper@YESFABU">
	    	已发布
	    	</s:elseif>
		</td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright">
	    	<s:if test="extendsone==@com.tfs.irp.testpaper.entity.IrpTestpaper@SELECTQUESTION">选择组卷</s:if><s:elseif test="extendsone==@com.tfs.irp.testpaper.entity.IrpTestpaper@RADOMQUESTION">随机组卷</s:elseif> 
	    </td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright">
	    	<s:property value="papertime" />
	    </td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright">
	    	<s:property value="getNumBySContent(papercontent)" />
	    </td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:property value="getShowPageViewNameByUserId(cruserid)" /> </td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:date name="crtime" format="yyyy-MM-dd HH:mm" /> </td>
	    
	    
	    <td bgcolor="#F5FAFE" align="center" class="main_bright">
	    	<img border="0" src="images/icons/pencil.png" title="修改" style="cursor:pointer; margin: 0 5px;" onclick="updatePaper(<s:property value="paperid" />)"  />
        	<img border="0" src="images/icons/cancel.png" title="删除" style="cursor:pointer; margin: 0 3px;" onclick="deletePaper(<s:property value="paperid" />)" />
        	
        	
	    </td>

    </tr>
   </s:iterator>
    <tr bgcolor="#FFFFFF">
		<td colspan="9" align="right" class="page"><s:property value="paperpagehtml" escapeHtml="false" /></td>
	</tr>
</table>
</body>
</html>
