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
* 全选
*/
function checkAll(){
	m_checked = !m_checked;
	$("input[name='recordid']").attr("checked",m_checked); 
}
/**
* 分页
*/
function pageExam(_pages){
	$("#pagenum").val(_pages);
	var queryString = $('#pageForm').serialize();
	jump("<%=rootPath%>exam/examresultmanger.action?"+queryString);
}
/**
*排序
*/
function orderBy(_sFiled,_sOrderBy){
	$('#orderField').val(_sFiled);
	$('#orderBy').val(_sOrderBy);
	var queryString = $('#pageForm').serialize();
	jump("<%=rootPath%>exam/examresultmanger.action?"+queryString);
}
//删除
function deleteEdit(_recordid){
	 $.messager.confirm("操作提示","您确定要删除这条数据吗",function(r){
	 if(r){
	 	$.ajax({
		     type:'post',
		     url:'<%=rootPath%>exam/deleteexamrecord.action',
		     data:{
		     	recordid:_recordid
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
//多选删除
function deleteAllEdit(){
 var _configid = "";
	 $("input[name='recordid']:checked").each(
			 function(){
				 _configid+=$(this).val() + ',';
			 }
			 );
			 if (_configid=="") {
			 	$.messager.alert("操作提示","请您先选择要删除的对象");
			 	return false;
			 } 	
			 var _configcatalength=$("input[name='recordid']:checked").length;
			 $.messager.confirm("操作提示","您确定要删除选中的"+_configcatalength+"条数据吗",function(r){
				 
				 if(r){
			       $.messager.progress({
						text:'提交数据中...'
					});
				   $.ajax({
				    type:'post',
				    url:'<%=rootPath%>exam/deletemanyexamrecords.action?qtestpidstr='+_configid,
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
				 });
}
//发布
function faBu(_recordid){
	 $.messager.confirm("操作提示","您确定要发布吗？",function(r){
	 if(r){
	 	$.ajax({
		     type:'post',
		     url:'<%=rootPath%>exam/faburecordbyid.action',
		     data:{
		     	recordid:_recordid
		     },
			 cache:false,
			 async:false,
			 success:function(msg){
		        if (msg==1) {
					$.messager.alert("操作提示","发布成功了");
					refreshPage();
				}else{
					$.messager.alert("操作提示","发布失败了");
					refreshPage();
				}
			 }
		 	});
	 }
 });

}
//多选发布
function manysFaBu(){
 var _configid = "";
	 $("input[name='recordid']:checked").each(
			 function(){
				 _configid+=$(this).val() + ',';
			 }
			 );
			 if (_configid=="") {
			 	$.messager.alert("操作提示","请您先选择要发布的对象");
			 	return false;
			 } 	
			 var _configcatalength=$("input[name='recordid']:checked").length;
			 $.messager.confirm("操作提示","您确定要发布选中的"+_configcatalength+"条数据吗",function(r){
				 
				 if(r){
			       $.messager.progress({
						text:'提交数据中...'
					});
				   $.ajax({
				    type:'post',
				    url:'<%=rootPath%>exam/fabumanyexamrecords.action?qtestpidstr='+_configid,
				    success:function(date){
				    	 $.messager.progress('close');
				        if (date==1) {
							$.messager.alert("操作提示","您成功发布了"+_configcatalength+"条数据");
							refreshPage();
						}else{
							$.messager.alert("操作提示","发布失败了");
							refreshPage();
						}
				    },
				    error:function(){
				    	 $.messager.progress('close');
				    }
				 });
				 }
				 });
}

function linkExamDetail(_paperid,_examid,_recordid){
	window.open("<%=rootPath%>exam/linksinglequesalready.action?paper="+_paperid+"&exam="+_examid+"&recordid="+_recordid);
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
   	  	<td colspan="6" align="left" style="padding: 5px 0 5px 10px;" nowrap="nowrap">
			

			<a href="javascript:void(0)" onclick="manysFaBu()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">发布</a>
			<a href="javascript:void(0)" onclick="deleteAllEdit()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">删除</a>
			<a href="javascript:void(0)" onclick="refreshPage()" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新</a>
			
			
			
   	  	</td>   
   	  	<td colspan="6" align="right" style="padding: 5px 0 5px 10px;" nowrap="nowrap">
   	  		
   	  	</td>   	  	
	</tr>

    <tr>

	    <td width="5%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="checkAll()">全选</a></td>
	    <td width="25%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('examid','<s:if test="orderField=='examid'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">考试名称<s:if test="orderField=='examid'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='examid'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    
	    <td width="6%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('examtime','<s:if test="orderField=='examtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">考试用时<s:if test="orderField=='examtime'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='examtime'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    
	    <td width="6%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('extendone','<s:if test="orderField=='extendone'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">状态<s:if test="orderField=='extendone'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='extendone'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    
	    <td width="7%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('examstatus','<s:if test="orderField=='examstatus'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">考试结果<s:if test="orderField=='examstatus'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='examstatus'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="7%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('examgrade','<s:if test="orderField=='examgrade'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">分数<s:if test="orderField=='examgrade'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='examgrade'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="5%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('cruserid','<s:if test="orderField=='cruserid'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">考试人<s:if test="orderField=='cruserid'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='cruserid'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="5%" align="center" bgcolor="#F5FAFE"  class="main_bright">总分</td>
	    <td width="10%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('crtime','<s:if test="orderField=='crtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">交卷时间<s:if test="orderField=='crtime'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='crtime'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="10%" align="center" bgcolor="#F5FAFE"  class="main_bright">所属分类</td>
	    <td width="6%" align="center" bgcolor="#F5FAFE"  class="main_bright">细览</td>
	    <td width="8%" align="center" bgcolor="#F5FAFE"  class="main_bright">操作</td>
	    
    </tr>
    <s:iterator value="recordlist" status="recordliststatus" >
    <tr>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><input name="recordid" type="checkbox" value="<s:property value='recordid' />">&nbsp;<s:property value="(pagenum-1)*10+#recordliststatus.count"/></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:property value="getIrpExamById(examid).examname"  /></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:property value="examtime"  /></td>
	     <td bgcolor="#F5FAFE" align="center" class="main_bright">
	     <s:if test="extendone==10">
	     	已发布
	     </s:if>
	     <s:elseif test="extendone==20">
	     	未发布
	     </s:elseif>
	     </td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright">
	    <s:if test="examstatus==10">
	    	已通过
	    
	    </s:if>
	    <s:elseif test="examstatus==20">
	    	未通过
	    </s:elseif>
	    
	    </td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:property value="examgrade" /></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:property value="getShowPageViewNameByUserId(cruserid)" /></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:property value="getIrpTestpaper(getIrpExamById(examid).examcontent).papertime" /></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:date name="crtime" format="yyyy-MM-dd HH:mm" /></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright">
	    	<s:property value="getIrpCategoryService(getIrpExamById(examid).examcate).cname"  />
	    </td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright">
	    
	    	<a href="javascript:void(0)" onclick="linkExamDetail(<s:property value="getIrpExamById(examid).examcontent" />,<s:property value="examid" />,<s:property value="recordid" />)">查看详细</a>
	    </td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright">
	    	<img border="0" src="images/icons/ok.png" title="发布" style="cursor:pointer; margin: 0 5px;" onclick="faBu(<s:property value="recordid" />)"  />
        	<img border="0" src="images/icons/cancel.png" title="删除" style="cursor:pointer; margin: 0 3px;" onclick="deleteEdit(<s:property value="recordid" />)" />
	    </td>

    </tr>
   </s:iterator>
    <tr bgcolor="#FFFFFF">
		<td colspan="12" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
	</tr>
</table>
</body>
</html>
