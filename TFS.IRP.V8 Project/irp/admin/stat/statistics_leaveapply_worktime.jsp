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
<style type="text/css">
.main_bright{
	padding-left: 7px;
    padding-right: 5px;
    line-height: 29px;
    word-break: break-all;
    word-wrap: break-word;
}
</style>
</head>
<body>
	<script type="text/javascript">
//全局变量，
var m_checked = false;

/**
 * 刷新本页面
 */
function refreshCata(){
	$('body').layout('panel','center').panel('refresh','<%=rootPath %>leave/getAllWorkTimeInfo.action?c_start_end=logs_month');
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
		href:'<%=rootPath%>set/catalogueform.action?configid='+_configid,
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
					url:'<%=rootPath%>set/updatecateEmail.action?configid='+_configid,
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
							 $('#sysemailtab').panel('refresh');
						}else{
							$.messager.alert("失败","增加存放目录失败了");
							$('#sysemailtab').panel('refresh');
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
 * 删除存放目录
 */
 function deleteEdit(_ckey,_configid){
	$.messager.confirm("删除存放目录","您确定删除名称为"+_ckey+"的存放目录吗",function(r){
		if(r){
			$.messager.progress({
				text:'提交数据中...'
			});
			$.ajax({
				type:'post',
				url:'<%=rootPath%>set/deletecatelague.action?configid='+_configid,
				success:function(_deletecata){
					 $.messager.progress('close');
					if (_deletecata==1) {
						$.messager.alert("操作提示","删除成功了");
						$('#sysemailtab').panel('refresh');
					}else{
						$.messager.alert("操作提示","删除未成功");
						$('#sysemailtab').panel('refresh');
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
		$('#pageNum').val(_nPageNum);
		var queryString = $('#pageForm').serialize();
		$('body').layout('panel','center').panel('refresh','<%=rootPath %>leave/getAllWorkTimeInfo.action?'+queryString);
	}
	/**
	*排序
	*/
	function orderBy(_sFiled,_sOrderBy){
		$('#orderField').val(_sFiled);
		$('#orderBy').val(_sOrderBy);
		var queryString = $('#pageForm').serialize();
		$('body').layout('panel','center').panel('refresh','<%=rootPath %>leave/getAllWorkTimeInfo.action?'+queryString);
	}	
	/**
	*多选删除
	*/
	function deleteAll(){
	 var _configid;	
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
				    url:'<%=rootPath%>set/configcatadeleteall.action?configidall='+_configid,
				    success:function(date){
				    	 $.messager.progress('close');
				        if (date==1) {
							$.messager.alert("操作提示","您成功删除了"+_configcatalength+"条数据");
							$('#sysemailtab').panel('refresh');
						}else{
							$.messager.alert("操作提示","删除失败了");
							$('#sysemailtab').panel('refresh');
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
		initTime();
	});
	function exportToZip(){
	var now=new Date();
	var date=1900+now.getYear()+""+(now.getMonth()+1)+""+now.getDate()+""+now.getHours()+""+now.getMinutes()+""+now.getSeconds();
	var fileName=date;
	document.getElementById("daochu").href="<%=rootPath%>/worktime/worktime"+fileName+".zip";
	var queryString = $('#pageForm').serialize();
	var limitTime = $('#timeselect').combobox('getValue');
	var _starttime = null;
	var _endtime = null;
	if(limitTime=="logs_appoint_date"){
	var browerStartTime='${starttime1}';
	var browerEndTime='${endtime1}';
	var date_start_time=$('#starttime').datebox('getValue');
	var date_end_time=$('#endtime').datebox('getValue'); 
	if(date_start_time==""){
			date_start_time=new Date(browerStartTime);
			var  year=date_start_time.getFullYear();
			var month=parseInt(date_start_time.getMonth()+1);
			var day=date_start_time.getDate();
			date_start_time=year+"-"+month+"-"+day;
		}
		if(date_end_time==""){
			date_end_time=new Date(browerEndTime);
			var  year=date_end_time.getFullYear();
			var month=parseInt(date_end_time.getMonth()+1);
			var day=date_end_time.getDate();
			date_end_time=year+"-"+month+"-"+day;
		} 
		if(compareTime(date_start_time,date_end_time)){
			_starttime=date_start_time;
			_endtime=date_end_time;
		}else{
			$.messager.alert("操作提示","结束日期必须在开始日期之后");
			return false;
		}
	}
	$.ajax({
		type:'post',
		url:'<%=rootPath%>leave/worktimeexportToZip.action?'+queryString+'&fileName='+fileName,
		data:{
		c_start_end:limitTime,
		starttime:_starttime,
		endtime:_endtime,
		applytypeid:$('#_Irp_type_oper').combobox('getValue')
		},
		async:false,
		success:function(){
			document.getElementById("daochu").click();
		}
        
	});	
	 
	
	}
	function initTime(){
	$('#timeselect').combobox({
		panelHeight:'130',
		width:'50',
		editable:false,
		onSelect:function(){
			var  start_end = $(this).combobox('getValue');
			if(start_end!="logs_appoint_date"){ 
				$('#timedatespan').hide(); 
			}else{
				 var date=new Date(<%=new java.util.Date().getTime()%>); 
				 $('#starttime').datebox('setValue',date.getFullYear()+"-"+parseInt(date.getMonth()+1)+"-"+date.getDate());
				 $('#endtime').datebox('setValue',date.getFullYear()+"-"+parseInt(date.getMonth()+1)+"-"+date.getDate());
				 $('#timedatespan').show();
			}
		}
	 }); 
	 $('#_Irp_type_oper').combobox({
	 	 panelHeight:'130',
	 	editable:false
	  });
	  $('#status').combobox({
	 	 panelHeight:'130',
	 	editable:false
	  });
	var  start_end = $('#timeselect').combobox('getValue');
	if(start_end=="logs_appoint_date"){
		 showtimedatediv('${starttime1}','${endtime1}');
	}
} 
function searchDate(){
var limitTime = $('#timeselect').combobox('getValue');
	var _starttime = '';
	var _endtime = '';
	if(limitTime=="logs_appoint_date"){
	var browerStartTime='${starttime1}';
	var browerEndTime='${endtime1}';
	var date_start_time=$('#starttime').datebox('getValue');
	var date_end_time=$('#endtime').datebox('getValue'); 
	if(date_start_time==""){
			date_start_time=new Date(browerStartTime);
			var  year=date_start_time.getFullYear();
			var month=parseInt(date_start_time.getMonth()+1);
			var day=date_start_time.getDate();
			date_start_time=year+"-"+month+"-"+day;
		}
		if(date_end_time==""){
			date_end_time=new Date(browerEndTime);
			var  year=date_end_time.getFullYear();
			var month=parseInt(date_end_time.getMonth()+1);
			var day=date_end_time.getDate();
			date_end_time=year+"-"+month+"-"+day;
		} 
		if(compareTime(date_start_time,date_end_time)){
			_starttime=date_start_time;
			_endtime=date_end_time;
		}else{
			$.messager.alert("操作提示","结束日期必须在开始日期之后");
			return false;
		}
	}
	var status = $('#status').combobox('getValue');
	$('#pageForm').find('input[name="searchWord"]').val($("#cruser").val());
	$('#pageForm').find('input[name="searchType"]').val("cruser");
	$('#pageForm').find('input[name="applystatus"]').val(status);
	var queryString = $('#pageForm').serialize();
$('body').layout('panel','center').panel('refresh','<%=rootPath %>leave/getAllWorkTimeInfo.action?'+queryString+'&c_start_end='+limitTime+'&starttime='+_starttime+'&endtime='+_endtime+'&applytypeid='+$('#_Irp_type_oper').combobox('getValue'));
}  
function page(_pageNum){
var limitTime = $('#timeselect').combobox('getValue');
var _starttime = '';
	var _endtime = '';
	if(limitTime=="logs_appoint_date"){
	var browerStartTime='${starttime1}';
	var browerEndTime='${endtime1}';
	var date_start_time=$('#starttime').datebox('getValue');
	var date_end_time=$('#endtime').datebox('getValue'); 
	if(date_start_time==""){
			date_start_time=new Date(browerStartTime);
			var  year=date_start_time.getFullYear();
			var month=parseInt(date_start_time.getMonth()+1);
			var day=date_start_time.getDate();
			date_start_time=year+"-"+month+"-"+day;
		}
		if(date_end_time==""){
			date_end_time=new Date(browerEndTime);
			var  year=date_end_time.getFullYear();
			var month=parseInt(date_end_time.getMonth()+1);
			var day=date_end_time.getDate();
			date_end_time=year+"-"+month+"-"+day;
		} 
		if(compareTime(date_start_time,date_end_time)){
			_starttime=date_start_time;
			_endtime=date_end_time;
		}else{
			$.messager.alert("操作提示","结束日期必须在开始日期之后");
			return false;
		}
	}
	$('#pageForm').find('input[name="searchWord"]').val($("#cruser").val());
	$('#pageForm').find('input[name="searchType"]').val("cruser");
	$('#pageForm').find('input[name="applystatus"]').val(status);
	if($('#applystatus').val()==""){
	$('#pageForm').find('input[name="applystatus"]').val(0);
	}
	$('#pageForm').find('input[name="pageNum"]').val(_pageNum);
var queryString = $('#pageForm').serialize();
$('body').layout('panel','center').panel('refresh','<%=rootPath %>leave/getAllWorkTimeInfo.action?'+queryString+'&c_start_end='+limitTime+'&starttime='+_starttime+'&endtime='+_endtime+'&applytypeid='+$('#_Irp_type_oper').combobox('getValue'));
}
</script>
	<form id="pageForm">
		<s:hidden name="searchWord" id="searchWord" />
		<s:hidden name="searchType" id="searchType" />
		<s:hidden name="pageNum" id="pageNum" />
		<s:hidden name="pageSize" id="pageSize" />
		<s:hidden name="orderField" id="orderField" />
		<s:hidden name="orderBy" id="orderBy" />
		<s:hidden name="applystatus" id="applystatus" />
	</form>
	<table width="100%" border="0" align="center" cellpadding="10"
		cellspacing="1" id="table1" bgcolor="#cad9ea">
		<tr class="list_th" style="position: relative;">
			<td colspan="10" style="padding-left: 10px;"> <a
				href="javascript:void(0)" onclick="refreshCata()">刷新</a>&nbsp;
<select id="timeselect">
	<option value="logs_month" id="logs_month"  <s:if test="c_start_end=='logs_month'">selected="selected"</s:if>>本月</option>
	<option value="logs_week" id="logs_week" <s:if test="c_start_end=='logs_week'">selected="selected"</s:if>>本周</option>
	<option value="logs_quarter" id="logs_quarter" <s:if test="c_start_end=='logs_quarter'">selected="selected"</s:if>>本季</option>
	<option value="logs_appoint_date" id="logs_appoint_date" <s:if test="c_start_end=='logs_appoint_date'">selected="selected"</s:if>>指定</option>
</select>
<select id="_Irp_type_oper" name="c_v_Irp_type_oper" >
						<option value="0">加班类型</option>						 
						 <s:iterator var="eleq" value="irpLeaveconfigs" status="status">
							<option value="<s:property value="#eleq.leaveconfigid" />" <s:if test="#eleq.leaveconfigid==applytypeid">selected="selected"</s:if>><s:property value="#eleq.leaveconfigdesc" /></option>
						</s:iterator>
</select>
<span id="timedatespan" style="display: none;">
		开始日期
		<input type="text" id="starttime" class="easyui-datebox" editable="false"  />
		结束日期
		<input type="text" id="endtime" class="easyui-datebox" editable="false"  />

</span>	
<select id="status">
	<option value="0" id="unpass"  <s:if test="applystatus==0">selected="selected"</s:if>>状态</option>
	<option value="20" id="unpass"  <s:if test="applystatus==20">selected="selected"</s:if>>未审核</option>
	<option value="10" id="pass" <s:if test="applystatus==10">selected="selected"</s:if>>已同意</option>
	<option value="30" id="refuse" <s:if test="applystatus==30">selected="selected"</s:if>>已拒绝</option>
</select>
<span id="cruser">
申请人<input type="text" id="starttime" class="easyui-textbox" name="cruser" id="cruser"/>
</span>	
<a id="log_search"  class="easyui-linkbutton l-btn" onclick="searchDate('<%=rootPath %>leave/workTimePersonStatement.action')" data-options="iconCls:'icon-search'" href="javascript:void(0);">
		查询</a>
				<a href="javascript:void(0)" class="easyui-linkbutton l-btn" <s:if test="irpLeaveapplyInfos.size()>0">onclick="exportToZip()"</s:if>>导出</a>
				<a  id="daochu"></a>
				</td>
		</tr>
		<tr>
			<td align="center" width="30" bgcolor="#F5FAFE" class="main_bright"><a
				href="javascript:void(0)" onclick="checkAll()">全选</a></td>
			<td align="center" width="180" bgcolor="#F5FAFE" class="main_bright"><a
				href="javascript:void(0)"
				onclick="orderBy('title','<s:if test="orderField=='title'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">标题<s:if
						test="orderField=='title'&&orderBy=='desc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_down.gif" />
					</s:if>
					<s:elseif test="orderField=='title'&&orderBy=='asc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_up.gif" />
					</s:elseif></a></td>
			<td align="center" width="100" bgcolor="#F5FAFE" class="main_bright"><a
				href="javascript:void(0)"
				onclick="orderBy('starttime','<s:if test="orderField=='starttime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">开始时间<s:if
						test="orderField=='starttime'&&orderBy=='desc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_down.gif" />
					</s:if>
					<s:elseif test="orderField=='starttime'&&orderBy=='asc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_up.gif" />
					</s:elseif></a></td>
					<td align="center" width="100" bgcolor="#F5FAFE" class="main_bright"><a
				href="javascript:void(0)"
				onclick="orderBy('endtime','<s:if test="orderField=='endtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">结束时间<s:if
						test="orderField=='endtime'&&orderBy=='desc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_down.gif" />
					</s:if>
					<s:elseif test="orderField=='endtime'&&orderBy=='asc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_up.gif" />
					</s:elseif></a></td>
					<td align="center" width="60" bgcolor="#F5FAFE" class="main_bright"><a
				href="javascript:void(0)"
				onclick="orderBy('emergency','<s:if test="orderField=='emergency'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">紧急程度<s:if
						test="orderField=='emergency'&&orderBy=='desc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_down.gif" />
					</s:if>
					<s:elseif test="orderField=='emergency'&&orderBy=='asc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_up.gif" />
					</s:elseif></a></td>
					<td align="center" width="60" bgcolor="#F5FAFE" class="main_bright"><a
				href="javascript:void(0)"
				onclick="orderBy('applystatus','<s:if test="orderField=='applystatus'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">状态<s:if
						test="orderField=='applystatus'&&orderBy=='desc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_down.gif" />
					</s:if>
					<s:elseif test="orderField=='applystatus'&&orderBy=='asc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_up.gif" />
					</s:elseif></a></td>
					<td align="center" width="60" bgcolor="#F5FAFE" class="main_bright"><a
				href="javascript:void(0)"
				onclick="orderBy('cruserid','<s:if test="orderField=='cruserid'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">创建人<s:if
						test="orderField=='cruserid'&&orderBy=='desc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_down.gif" />
					</s:if>
					<s:elseif test="orderField=='cruserid'&&orderBy=='asc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_up.gif" />
					</s:elseif></a></td>
			<td align="center" bgcolor="#F5FAFE" class="main_bright" width="60"><a
				href="javascript:void(0)"
				onclick="orderBy('crtime','<s:if test="orderField=='crtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">创建时间<s:if
						test="orderField=='crtime'&&orderBy=='desc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_down.gif" />
					</s:if>
					<s:elseif test="orderField=='crtime'&&orderBy=='asc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_up.gif" />
					</s:elseif></a></td>
			
		</tr>
		<s:iterator value="irpLeaveapplyInfos" status="status">
			<tr>
				<td align="left" bgcolor="#F5FAFE" class="main_bright" ><input
					name="irpconfigcatach" type="checkbox"
					value="<s:property value='irpLeaveapplyid' />">&nbsp;<s:property
						value="(pageNum-1)*pageSize+#status.count" /></td>
				<td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property
						value="title" /></td>
						<td align="left" bgcolor="#F5FAFE" class="main_bright">
						<s:date name="starttime" format="yyyy-MM-dd HH:mm"/>
						</td>
						<td align="left" bgcolor="#F5FAFE" class="main_bright">
						<s:date name="endtime" format="yyyy-MM-dd HH:mm"/>
						</td>
						<td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property
						value="emergency" /></td>
						<td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property
						value="applystatus" /></td>
				<td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property
						value="cruserid" /></td>
				<td align="left" bgcolor="#F5FAFE" class="main_bright" >
				<s:date name="crtime" format="yyyy-MM-dd HH:mm"/></td>
			</tr>
		</s:iterator>
		<tr bgcolor="#FFFFFF">
			<td colspan="8" align="right" class="page"><s:property
					value="pageHTML" escapeHtml="false" /></td>
		</tr>
	</table>
</body>

</html>
