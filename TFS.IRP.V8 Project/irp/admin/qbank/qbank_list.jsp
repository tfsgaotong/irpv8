<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>流程管理</title>
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />
</head>

<body>
<script type="text/javascript">
//全局变量，
var m_checked = false;
function jump(_sUrl){
	$('body').layout('panel','center').panel('refresh',_sUrl);
}
/**
* 刷新页面
*/	
function refreshPage(){
	$('body').layout('panel','center').panel('refresh');
}	
	
/**
* 选择题
*/	
function addChoice(){
	var cateid = '<s:property value="cateid" />';
    if(cateid==""){
   		$.messager.alert("提示","请先选择左侧分类!","info");
   		return false;
   	}
	var dialogdiv=document.createElement("div");
 	dialogdiv.id="addchoicequestion";
 	document.body.appendChild(dialogdiv);
 	$('#addchoicequestion').dialog({
 		modal:true,
 		cache:false,
 		href:'<%=rootPath%>exam/linkchoicequestion.action',
 		height:600,
 		width:700,
 		title:'添加选择题',
 		resizable:true,
 		buttons:[{
 			text:"提交",
 			iconCls: 'icon-ok',
 			handler:function(){
 				$("#insertchoiceform").form('submit',{
 					url:'<%=rootPath%>exam/addchoicequestion.action?cateid='+cateid,
				    onSubmit:function(){   

				    },   
				    success:function(data){   
				    	if(data=='true'){
				    		$('#addchoicequestion').dialog('destroy');
				    		refreshPage();
				    	}
				    }  
 				});
 			}
 		},{
 			text:"取消",
 			iconCls: 'icon-cancel',
 			handler:function(){
 				$('#addchoicequestion').dialog('destroy');
 			}
 		
 		}
 		],
 		onClose:function(){
 			$('#addchoicequestion').dialog('destroy');
 		}
 	
	});
}
/**
* 填空题
*/
function addFilling(){
	var cateid = '<s:property value="cateid" />';
    if(cateid==""){
   		$.messager.alert("提示","请先选择左侧分类!","info");
   		return false;
   	}
	var dialogdiv=document.createElement("div");
 	dialogdiv.id="fulluestion";
 	document.body.appendChild(dialogdiv);
	$('#fulluestion').dialog({
 		modal:true,
 		cache:false,
 		href:'<%=rootPath%>exam/linkfullquestion.action',
 		height:600,
 		width:700,
 		title:'添加填空题',
 		resizable:true,
 		buttons:[{
 			text:"提交",
 			iconCls: 'icon-ok',
 			handler:function(){
 				$("#insertchoiceform").form('submit',{
 					url:'<%=rootPath%>exam/addchoicequestion.action?cateid='+cateid,
				    onSubmit:function(){   

				    		
				    	
				    },   
				    success:function(data){   
				    	if(data=='true'){
				    		$('#fulluestion').dialog('destroy');
				    		refreshPage();
				    	}
				    }  
 				});
 			}
 		},{
 			text:"取消",
 			iconCls: 'icon-cancel',
 			handler:function(){
 				$('#fulluestion').dialog('destroy');
 			}
 		
 		}
 		],
 		onClose:function(){
 			$('#fulluestion').dialog('destroy');
 		}
 	
	});
}
/**
* 判断题
*/
function addBool(){
	var cateid = '<s:property value="cateid" />';
    if(cateid==""){
   		$.messager.alert("提示","请先选择左侧分类!","info");
   		return false;
   	}
	var dialogdiv=document.createElement("div");
 	dialogdiv.id="addbooleanquestion";
 	document.body.appendChild(dialogdiv);
 	$('#addbooleanquestion').dialog({
 		modal:true,
 		cache:false,
 		href:'<%=rootPath%>exam/linkbooleanquestion.action',
 		height:600,
 		width:700,
 		title:'添加判断题',
 		resizable:true,
 		buttons:[{
 			text:"提交",
 			iconCls: 'icon-ok',
 			handler:function(){
 				$("#insertchoiceform").form('submit',{
 					url:'<%=rootPath%>exam/addchoicequestion.action?cateid='+cateid,
				    onSubmit:function(){   

				    		
				    	
				    },   
				    success:function(data){   
				    	if(data=='true'){
				    		$('#addbooleanquestion').dialog('destroy');
				    		refreshPage();
				    	}
				    }  
 				});
 			}
 		},{
 			text:"取消",
 			iconCls: 'icon-cancel',
 			handler:function(){
 				$('#addbooleanquestion').dialog('destroy');
 			}
 		
 		}
 		],
 		onClose:function(){
 			$('#addbooleanquestion').dialog('destroy');
 		}
 	
	});
}
/**
 * 全选
 */
function checkAll(){
	m_checked = !m_checked;
	$("input[name='qbankids']").attr("checked",m_checked); 
} 
/**
*分页
*/
function page(_nPageNum){
	$('#pagenum').val(_nPageNum);
	var cateid = '<s:property value="cateid" />';
	if(cateid==""){
		cateid = 0;
	}
	$('#cateid').val(cateid);
	var queryString = $('#pageForm').serialize();
	jump("<%=rootPath%>exam/qbanklist.action?"+queryString);
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
	jump("<%=rootPath%>exam/qbanklist.action?"+queryString);
}	
//删除
function deleteEdit(_qbankid){
 $.messager.confirm("操作提示","您确定要删除这条数据吗",function(r){
	 if(r){
	 	$.ajax({
		     type:'post',
		     url:'<%=rootPath%>exam/deleteqbankbyids.action',
		     data:{
		     	qbankid:_qbankid
		     },
			 cache:false,
			 async:false,
			 success:function(msg){
		        if (msg==1) {
					$.messager.alert("操作提示","删除成功了");
					refreshPage();
				}else{
					$.messager.alert("操作提示","删除失败了");
					refreshPage();
				}
			 }
		 	});
	 }
 });
}
/**
* 多选删除
*/
function deleteAllEdit(){
	 var _configid = "";
	 $("input[name='qbankids']:checked").each(
			 function(){
				 _configid+=$(this).val() + ',';
			 }
			 );
			 if (_configid=="") {
			 	$.messager.alert("操作提示","请您先选择要删除的对象");
			 	return false;
			 } 	
			 var _configcatalength=$("input[name='qbankids']:checked").length;
			 $.messager.confirm("操作提示","您确定要删除选中的"+_configcatalength+"条数据吗",function(r){
				 
				 if(r){
			       $.messager.progress({
						text:'提交数据中...'
					});
				   $.ajax({
				    type:'post',
				    url:'<%=rootPath%>exam/deleteqbankbyidss.action?qbankidstr='+_configid,
				    success:function(date){
				    	 $.messager.progress('close');
				        if (date==1) {
							$.messager.alert("操作提示","您成功删除了"+_configcatalength+"条数据");
							refreshPage();
						}else{
							$.messager.alert("操作提示","删除失败了");
							refreshPage();
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
	    	jump("<%=rootPath%>exam/qbanklist.action?"+queryString);
	    },   
	    menu:'#listSearchText',   
	    prompt:'请输入检索词'  
	});
});
/**
* 修改
*/
function updateEdit(_qbankid){

var dialogdiv=document.createElement("div");
 	dialogdiv.id="updatequestion";
 	document.body.appendChild(dialogdiv);
 	$('#updatequestion').dialog({
 		modal:true,
 		cache:false,
 		href:'<%=rootPath%>exam/linkupdatequestion.action?qbankid='+_qbankid,
 		height:600,
 		width:700,
 		title:'修改',
 		resizable:true,
 		buttons:[{
 			text:"提交",
 			iconCls: 'icon-ok',
 			handler:function(){
 				$("#insertchoiceform").form('submit',{
 					url:'<%=rootPath%>exam/updatechoicequestion.action',
				    onSubmit:function(){   

				    		
				    	
				    },   
				    success:function(data){   
				    	if(data=='1'){
				    		$('#updatequestion').dialog('destroy');
				    		refreshPage();
				    	}
				    }  
 				});
 			}
 		},{
 			text:"取消",
 			iconCls: 'icon-cancel',
 			handler:function(){
 				$('#updatequestion').dialog('destroy');
 			}
 		
 		}
 		],
 		onClose:function(){
 			$('#updatequestion').dialog('destroy');
 		}
 	
	});
	
	
}
</script>
<form id="pageForm">
	<s:hidden name="searchWord" id="searchWord" />
	<s:hidden name="searchType" id="searchType" />
	<s:hidden name="pagenum" id="pagenum" />
	<s:hidden name="cateid" id="cateid" />
	<s:hidden name="orderField" id="orderField" />
	<s:hidden name="orderBy" id="orderBy" />
</form>
<table width="100%" id="flowList" border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
	<tr class="list_th" style="position: relative;">
   	  	<td colspan="5" align="left" style="padding: 5px 0 5px 10px;" nowrap="nowrap">
			
			<a href="javascript:void(0)" onclick="addChoice()"  class="easyui-linkbutton" data-options="iconCls:'icon-add'">选择题</a>
			<a href="javascript:void(0)" onclick="addFilling()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">填空题</a>
			<a href="javascript:void(0)" onclick="addBool()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">判断题</a>
			<a href="javascript:void(0)" onclick="deleteAllEdit()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">删除</a>
			<a href="javascript:void(0)" onclick="refreshPage()" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新</a>
			
			
			
   	  	</td>   
   	  	<td colspan="5" align="right" style="padding: 5px 0 5px 10px;" nowrap="nowrap">
   	  		<input name="searchinput" id="searchinput"  />
				<div id="listSearchText">  
				    <div data-options="name:'all'">全部&nbsp;&nbsp;&nbsp;&nbsp;</div>
				    <div data-options="name:'qtext'">题干</div>
				    <div data-options="name:'qexplain'">解析</div>
				</div>
			
   	  	</td>   	  	
	</tr>

    <tr>

	    <td width="5%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="checkAll()">全选</a></td>
	    <td width="25%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('questiontext','<s:if test="orderField=='questiontext'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">题干<s:if test="orderField=='questiontext'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='questiontext'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    
	    <td width="18%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('qexplain','<s:if test="orderField=='qexplain'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">解析<s:if test="orderField=='qexplain'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='qexplain'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    
	    <td width="7%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('qbtype','<s:if test="orderField=='qbtype'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">题型<s:if test="orderField=='qbtype'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='qbtype'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="7%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('qbcate','<s:if test="orderField=='qbcate'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">类别<s:if test="orderField=='qbcate'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='qbcate'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="5%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('qbscore','<s:if test="orderField=='qbscore'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">分值<s:if test="orderField=='qbscore'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='qbscore'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="5%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('qblevel','<s:if test="orderField=='qblevel'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">级别<s:if test="orderField=='qblevel'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='qblevel'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="10%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('cruser','<s:if test="orderField=='cruser'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">出题人<s:if test="orderField=='cruser'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='cruser'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="10%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('crtime','<s:if test="orderField=='crtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">出题时间<s:if test="orderField=='crtime'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='crtime'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="8%" align="center" bgcolor="#F5FAFE"  class="main_bright">操作</td>
	    
    </tr>
    <s:iterator value="questionbanklist" status="questionbankliststatus" >
    <tr>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><input name="qbankids" type="checkbox" value="<s:property value='qbankid' />">&nbsp;<s:property value="(pagenum-1)*10+#questionbankliststatus.count"/></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:property value="questiontext" escapeHtml="false" /></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:property value="qexplain" escapeHtml="false" /></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:property value="getTypeStrByTypes(qbtype)" /></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:property value="getCateNameById(qbcate)" /></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:property value="qbscore" /></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:property value="getLevleByStatus(qblevel)" /></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:property value="getShowPageViewNameByUserId(cruser)" /></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:date name="crtime" format="yyyy-MM-dd HH:mm" /> </td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright">
	    	<img border="0" src="images/icons/pencil.png" title="修改" style="cursor:pointer; margin: 0 5px;" onclick="updateEdit(<s:property value="qbankid" />)"  />
        	<img border="0" src="images/icons/cancel.png" title="删除" style="cursor:pointer; margin: 0 3px;" onclick="deleteEdit(<s:property value="qbankid" />)" />
	    </td>

    </tr>
   </s:iterator>
    <tr bgcolor="#FFFFFF">
		<td colspan="10" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
	</tr>
</table>
</body>
</html>
