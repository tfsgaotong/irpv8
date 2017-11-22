<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
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

function jump(_sUrl){
	$('body').layout('panel','center').panel('refresh',_sUrl);
}
//全选
function checkAll(){
	m_checked = !m_checked;
	$("input[name='jobid']").attr("checked",m_checked); 
}

//分页
function page(_nPageNum){
	$('#pageNum').val(_nPageNum);
	var queryString = $('#pageForm').serialize();
	jump("<%=rootPath%>set/job_set_list.action?"+queryString);
}

//排序
function orderBy(_sFiled,_sOrderBy){
	$('#orderField').val(_sFiled);
	$('#orderBy').val(_sOrderBy);
	var queryString = $('#pageForm').serialize();
	jump("<%=rootPath%>set/job_set_list.action?"+queryString);
}

//刷新
function jobRefresh(){
	$('body').layout('panel','center').panel('refresh');
}

//编辑任务
function jobEdit(_nJobId){
	var dialogDiv = document.createElement("div");
	dialogDiv.id="editJob";
	document.body.appendChild(dialogDiv);
	$('#editJob').dialog({   
	    modal:true,
	    href:'<%=rootPath%>set/job_edit.action?jobId='+_nJobId,
	    title:_nJobId==0?'新建计划调度':'修改计划调度',
	    width:400,
	    height:274,
	    resizable:true,
	    buttons: [{
	    	text: '提交', 
	    	iconCls: 'icon-ok', 
	    	handler: function() {
	    		$('#jobForm').form('submit',{
	    			url : "<%=rootPath%>set/job_edit_dowith.action",
	    			onSubmit: function(){
	    				var isValid = $(this).form('validate');
	    				if (isValid){
	    					$.messager.progress({
	    	    				text:'提交数据中...'
	    	    			});
	    				}
	    				return isValid;
	    			},
	    			success:function(data){
	    				$('#editJob').dialog('destroy');
	    				$.messager.progress('close');
	    				if(data==1){
	    					jobRefresh();
	    				}else{
	    					$.messager.alert("提示信息","编辑计划调度失败！","error");
	    				}
	    			}
	    		});
	    	} 
	    },{ 
	    	text: '取消', 
	    	iconCls: 'icon-cancel', 
	    	handler: function() { 
	    		$('#editJob').dialog('destroy');
	    	} 
	    }],
	    onClose:function(){
	    	$('#editJob').dialog('destroy');
	    }
	});
}

//删除任务
function delJob(){
	var sJobIds = "";
	$('#jobList').find("input:checkbox[name='jobid']:checked").each(function(){sJobIds+=','+this.value;});
	if(sJobIds){
		sJobIds = sJobIds.substring(1);
		$.messager.confirm('提示信息','是否要删除这些任务？',function(r){   
		    if(r){
		    	$.messager.progress({
    				text:'提交数据中...'
    			});
		    		$.ajax({
	    				url: "<%=rootPath%>set/job_delete_dowith.action",
	    			   	type: "POST",
	    			   	data: {
	    			   		jobIds: sJobIds
	    			   	},
	    			   	success: function(msg){
	    			   		$.messager.progress('close');
	    			   		$.messager.alert("提示信息","成功删除了"+msg+"个任务","info");
	    			   		jobRefresh();
	    			   	},
	    			 	error:function(){
	    			 		$.messager.progress('close');
	    			 		$.messager.alert('提示信息','删除数据失败！','error');
    			 		}
		    		});
		    }
		});
	}else{
		$.messager.alert("提示信息","请选择要删除的任务。","warning");
	}
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
	    	jump("<%=rootPath%>set/job_set_list.action?"+queryString);
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
		<div data-options="name:'jobname'">任务名称</div>
		<div data-options="name:'jobdesc'">任务描述</div>
	</div>
	<table width="100%" id="jobList" border="0" align="center"
		cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
		<tr class="list_th"
			style="position: relative;">
			<td colspan="3" style="padding-left: 10px;"><a
				href="javascript:void(0)" onclick="jobEdit(0)">新建计划调度</a> <a
				href="javascript:void(0)" onclick="delJob()">删除计划调度</a> <a
				href="javascript:void(0)" onclick="jobRefresh()">刷新</a></td>
			<td colspan="4" align="right" style="padding-right: 2px;"><input
				id='listSearchText' /></td>
		</tr>
		<tr>
			<td align="center" width="60" bgcolor="#F5FAFE" class="main_bright"><a
				href="javascript:void(0)" onclick="checkAll()">全选</a></td>
			<td align="center" bgcolor="#F5FAFE" class="main_bright" width="200"><a
				href="javascript:void(0)"
				onclick="orderBy('jobname','<s:if test="orderField=='jobname'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">任务名称<s:if
						test="orderField=='jobname'&&orderBy=='desc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_down.gif" />
					</s:if> <s:elseif test="orderField=='jobname'&&orderBy=='asc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_up.gif" />
					</s:elseif>
			</a></td>
			<td align="center" bgcolor="#F5FAFE" class="main_bright"><a
				href="javascript:void(0)"
				onclick="orderBy('jobdesc','<s:if test="orderField=='jobdesc'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">任务描述<s:if
						test="orderField=='jobdesc'&&orderBy=='desc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_down.gif" />
					</s:if> <s:elseif test="orderField=='jobdesc'&&orderBy=='asc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_up.gif" />
					</s:elseif>
			</a></td>
			<td align="center" bgcolor="#F5FAFE" class="main_bright" width="120"><a
				href="javascript:void(0)"
				onclick="orderBy('crtime','<s:if test="orderField=='crtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">创建时间<s:if
						test="orderField=='crtime'&&orderBy=='desc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_down.gif" />
					</s:if> <s:elseif test="orderField=='crtime'&&orderBy=='asc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_up.gif" />
					</s:elseif>
			</a></td>
			<td align="center" bgcolor="#F5FAFE" class="main_bright" width="100"><a
				href="javascript:void(0)"
				onclick="orderBy('cruser','<s:if test="orderField=='cruser'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">创建者<s:if
						test="orderField=='cruser'&&orderBy=='desc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_down.gif" />
					</s:if> <s:elseif test="orderField=='cruser'&&orderBy=='asc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_up.gif" />
					</s:elseif>
			</a></td>
			<td align="center" bgcolor="#F5FAFE" class="main_bright" width="50">状态</td>
			<td align="center" bgcolor="#F5FAFE" class="main_bright" width="40">操作</td>
		</tr>
		<s:iterator value="jobs" status="listStat">
			<tr>
				<td align="left" bgcolor="#F5FAFE" class="main_bright"><input
					name="jobid" type="checkbox" value="<s:property value='jobid'/>">
					<s:property value="(pageNum-1)*pageSize+#listStat.count" /></td>
				<td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property
						value="jobname" /></td>
				<td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property
						value="jobdesc" /></td>
				<td align="center" bgcolor="#F5FAFE" class="main_bright"><s:date
						name="crtime" format="yyyy-MM-dd HH:mm:ss" /></td>
				<td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property
						value="cruser" /></td>
				<td style="text-align: center;" bgcolor="#F5FAFE"
					class="main_bright"><s:if test="status==1"><font color="green">已启动</font></s:if><s:else><font color="red">已停止</font></s:else></td>
				<td style="text-align: center;" bgcolor="#F5FAFE"
					class="main_bright"><img border="0"
					src="images/icons/pencil.png"
					onclick="jobEdit(<s:property value="jobid"/>)" title="修改"
					style="cursor:pointer; margin: 0 5px;" /></td>
			</tr>
		</s:iterator>
		<tr bgcolor="#FFFFFF">
			<td colspan="7" align="right" class="page"><s:property
					value="pageHTML" escapeHtml="false" /></td>
		</tr>
	</table>
</body>
</html>