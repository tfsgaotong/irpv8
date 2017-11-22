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
	    	jump("<%=rootPath%>exam/linkexammenu.action?"+queryString);
	    },   
	    menu:'#listSearchText',   
	    prompt:'请输入检索词'  
	});
});

/**
* 增加考试
*/
function addExam(){
	var cateid = '<s:property value="cateid" />';
	var dialogdiv=document.createElement("div");
 	dialogdiv.id="addexamdiv";
 	document.body.appendChild(dialogdiv);
 	$('#addexamdiv').dialog({
 		modal:true,
 		cache:false,
 		href:'<%=rootPath%>exam/linkaddexam.action?cateid='+cateid,
 		height:600,
 		width:700,
 		title:'添加考试',
 		resizable:true,
 		buttons:[{
 			text:"提交",
 			iconCls: 'icon-ok',
 			handler:function(){
				$('#addexamform').form('submit',{   
				    url:"<%=rootPath%>exam/addexamcontent.action",   
				    onSubmit:function(){   
				    
				    
				    
							
						
				    },   
				    success:function(data){   
				       $('#addexamdiv').dialog('destroy');
				       refreshPages();
				    }   
				});   
			
 			}
 		},{
 			text:"取消",
 			iconCls: 'icon-cancel',
 			handler:function(){
 				$('#addexamdiv').dialog('destroy');
 			}
 		
 		}
 		],
 		onClose:function(){
 			$('#addexamdiv').dialog('destroy');
 		}
 	
	});
	
	

}
/**
* 刷新本页面
*/
function refreshPages(){
	$('body').layout('panel','center').panel('refresh');
}
function jump(_surl){
	$('body').layout('panel','center').panel('refresh',_surl);
}
/**
选择组织
*/
function selectGroups(_examid){
   	var dialogdiv=document.createElement("div");
 	dialogdiv.id="jurisdictiondiv";
 	document.body.appendChild(dialogdiv);
 	$('#jurisdictiondiv').dialog({
 		modal:true,
 		cache:false,
 		href:'<%=rootPath%>exam/linkjurisdictiontp.action?paperid='+_examid,
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
	 						paperid:_examid
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
/**
分页
*/
function pageExam(_nums){
	var cateid = '<s:property value="cateid" />';
	if(cateid==""){
		cateid = 0;
	}
	$('#cateid').val(cateid);
	$("#pagenum").val(_nums);
	var queryString = $('#pageForm').serialize();
	jump("<%=rootPath%>exam/linkexammenu.action?"+queryString);
	
}
/**
* 排序
*/
function orderExamBy(_sFiled,_sOrderBy){
	$("#orderFieldex").val(_sFiled);
	$("#orderByex").val(_sOrderBy);
	var cateid = '<s:property value="cateid" />';
	if(cateid==""){
		cateid = 0;
	}
	$('#cateid').val(cateid);
	var queryString = $('#pageForm').serialize();
	jump("<%=rootPath%>exam/linkexammenu.action?"+queryString);
}
/**
* 修改
*/
function updateExam(_examid){
   	var dialogdiv=document.createElement("div");
 	dialogdiv.id="updateexamdiv";
 	document.body.appendChild(dialogdiv);
 	$('#updateexamdiv').dialog({
 		modal:true,
 		cache:false,
 		href:'<%=rootPath%>exam/linkupdateexamdiv.action?examid='+_examid,
 		height:600,
 		width:700,
 		title:'修改考试',
 		resizable:true,
 		buttons:[{
 			text:"提交",
 			iconCls: 'icon-ok',
 			handler:function(){
 			
				$('#addexamform').form('submit',{   
				    url:"<%=rootPath%>exam/updateexamcontent.action",   
				    onSubmit:function(){   
				    
				    
				    
							
						
				    },   
				    success:function(data){   
				       $('#updateexamdiv').dialog('destroy');
				       refreshPages();
				    }   
				});  
				
 			}
 		},{
 			text:"取消",
 			iconCls: 'icon-cancel',
 			handler:function(){
 				$('#updateexamdiv').dialog('destroy');
 			}
 		
 		}
 		],
 		onClose:function(){
 			$('#updateexamdiv').dialog('destroy');
 		}
 	
	});
}
/**
* 删除
*/
function deleteExam(_examid){
	$.messager.confirm("操作提示","您确定要删除这条数据吗",function(r){
		if(r){
			$.ajax({
				type:"post",
				url:"<%=rootPath%>exam/dropexaminid.action",
				data:{
					examid:_examid
				},
				async:false,
				cache:false,
				success:function(msg){
			        if (msg==1) {
						$.messager.alert("操作提示","删除成功了");
						refreshPages();
					}else{
						$.messager.alert("操作提示","删除失败了");
						refreshPages();
					}
				
				}
			
			});
		}
 	});
}
/**
* 全选
*/
var m_checked = false;
function checkAllExam(){
	m_checked = !m_checked;
	$("input[name='examid']").attr("checked",m_checked); 
}
/**
* 多选删除
*/
function deleteExams(){
	var _configid = "";
	 $("input[name='examid']:checked").each(
			 function(){
				 _configid+=$(this).val() + ',';
			 }
			 );
			 if (_configid=="") {
			 	$.messager.alert("操作提示","请您先选择要删除的对象");
			 	return false;
			 } 	
			 var _configcatalength=$("input[name='examid']:checked").length;
			 $.messager.confirm("操作提示","您确定要删除选中的"+_configcatalength+"条数据吗",function(r){
				 
				 if(r){
			       $.messager.progress({
						text:'提交数据中...'
					});
				   $.ajax({
				    type:'post',
				    url:'<%=rootPath%>exam/deleteexamfromids.action?examids='+_configid,
				    success:function(date){
				    	 $.messager.progress('close');
				        if (date==1) {
							$.messager.alert("操作提示","您成功删除了"+_configcatalength+"条数据");
							refreshPages();
						}else{
							$.messager.alert("操作提示","删除失败了");
							refreshPages();
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
</script>
<form id="pageForm">
	<s:hidden name="searchWord" id="searchWord" />
	<s:hidden name="searchType" id="searchType" />
	<s:hidden name="pageexamnum" id="pagenum" />
	<s:hidden name="cateid" id="cateid" />
	<s:hidden name="orderField" id="orderFieldex" />
	<s:hidden name="orderBy" id="orderByex" />
</form>
<table width="100%" id="flowList" border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
	<tr class="list_th" style="position: relative;">
   	  	<td colspan="5" align="left" style="padding: 5px 0 5px 10px;" nowrap="nowrap">
			
			<a href="javascript:void(0)" onclick="addExam()"  class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加考试</a>
			<a href="javascript:void(0)" onclick="deleteExams()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">删除</a>
			
   	  	</td>   
   	  	<td colspan="3" align="right" style="padding: 5px 5px 5px 0;" nowrap="nowrap">
   	  		<input name="searchinput" id="searchinput"  />
				<div id="listSearchText">  
				    <div data-options="name:'examname'">考试名称&nbsp;&nbsp;&nbsp;&nbsp;</div>
				</div>
   	  	</td>   	  	
	</tr>
    <tr>
	    <td width="8%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="checkAllExam()">全选</a></td>
	    <td width="38%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderExamBy('examname','<s:if test="orderField=='examname'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">考试名称<s:if test="orderField=='examname'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='examname'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="6%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderExamBy('examstatus','<s:if test="orderField=='examstatus'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">状态<s:if test="orderField=='examstatus'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='examstatus'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="10%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderExamBy('begintime','<s:if test="orderField=='begintime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">开始时间<s:if test="orderField=='begintime'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='begintime'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="10%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderExamBy('endtime','<s:if test="orderField=='endtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">结束时间<s:if test="orderField=='endtime'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='endtime'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="10%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderExamBy('crtime','<s:if test="orderField=='crtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">创建时间<s:if test="orderField=='crtime'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='crtime'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="10%" align="center" bgcolor="#F5FAFE"  class="main_bright">指定考试对象</td>
	    <td width="8%" align="center" bgcolor="#F5FAFE"  class="main_bright">操作</td>
    </tr>
    <s:iterator value="exammanagerlist" status="exammanagerliststatus" >
    <tr>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><input name="examid" type="checkbox" value="<s:property value='examid' />">&nbsp;<s:property value="(pageexamnum-1)*10+#exammanagerliststatus.count"/></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:property value="examname" /></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright">
	    	<s:if test="examstatus==@com.tfs.irp.exam.entity.IrpExam@EXAMNORMAL">
	    		正常
	    	</s:if>
	    	<s:elseif test="examstatus==@com.tfs.irp.exam.entity.IrpExam@EXAMFORBIDDEN">
	    		禁用
	    	</s:elseif>
	    </td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:date name="begintime" format="yyyy-MM-dd HH:mm" /> </td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:date name="endtime" format="yyyy-MM-dd HH:mm" /> </td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:date name="crtime" format="yyyy-MM-dd HH:mm" /> </td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><a href="javascript:void(0)" onclick="selectGroups(<s:property value="examid" />)">选择组织</a></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright">
	    	<img border="0" src="images/icons/pencil.png" title="修改" style="cursor:pointer; margin: 0 5px;" onclick="updateExam(<s:property value="examid" />)"  />
        	<img border="0" src="images/icons/cancel.png" title="删除" style="cursor:pointer; margin: 0 3px;" onclick="deleteExam(<s:property value="examid" />)" />
	    </td>

    </tr>
   </s:iterator>
    <tr bgcolor="#FFFFFF">
		<td colspan="8" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
	</tr>
</table>
</body>
</html>
