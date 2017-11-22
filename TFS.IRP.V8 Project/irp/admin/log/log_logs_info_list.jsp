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
	<script type="text/javascript" src="js/log/log-format-time.js"></script>
	<script type="text/javascript">
var logstypes = '<s:property value = "irplogstype"/>';
//全局变量，
var m_checked = false;
function jump(_sUrl){
	$('body').layout('panel','center').panel('refresh',_sUrl);
}
//详细
 function checkLog(_logid){
	 var dialogdiv=document.createElement("div");
	 dialogdiv.id="checklogs";
	 document.body.appendChild(dialogdiv);
	 $('#checklogs').dialog({
		 modal:true,
		 title:'查看系统日志信息',
		 href:'<%=rootPath%>log/logsCheck.action?_logid='+_logid,
		 width:400,
		 height:500,
		 resizable:true,
		 maximizable:false,
		 buttons:[{
			 text:'关闭',
			 iconCls:'icon-cancel',
			 handler:function()
			 {
			$('#checklogs').dialog('destroy');	 
			 }
		 }],
		 onClose:function(){
		$('#checklogs').dialog('destroy');
		 }
		 
	 });
	 
 }

 
 //时间段
 
//获得页面选择内容
 var start_end;
 $(function(){
  $('#Irp_logtime').combobox({
 	 panelHeight:'130',
 	 editable:false,
 	 onSelect:function(){
 		 start_end = $(this).combobox('getValue');
 		if (start_end=="logs_appoint_date") {
 			var testDate = new Date();
 			$('#end_time').datebox('setValue',testDate.format("yyyy-MM-dd"))
 			$('#start_time').datebox('setValue',testDate.format("yyyy-MM-dd"))
 			$('#_time_date').show();
 		}
 		if (start_end=="logs_week") {
 			$('#_time_date').hide();
 		}
 		if (start_end=="logs_month") {
 			$('#_time_date').hide();
 		}
 		if (start_end=="logs_quarter") {
 			$('#_time_date').hide();
 		}
 	 }
 	 
  });

  $('#_log_type').combobox({
 	 panelHeight:'160',
 	 width:'50',
 	 editable:false
  });

  $('#_log_obj_oper').combobox({
 	 panelHeight:'160',
 	 editable:false
  });

  $('#_Irp_type_oper').combobox({
 	 panelHeight:'160',
 	editable:false
  });

  $('#Irp_oper_result').combobox({
 	 panelHeight:'100',
 	editable:false
  });

 });
 //检索
   function log_CheckSearch(){
	   //日期选择one,two
	   var date_start_time=$('#start_time').datebox('getValue');   
       var date_end_time=$('#end_time').datebox('getValue');    
	   var v_log_type=$('#_log_type').combobox('getValue');
	   var v_log_obj_oper=$('#_log_obj_oper').combobox('getValue');
	   var v_log_oper_obj_id=encodeURIComponent($('#log_oper_obj_id').val());
	   var v_log_oper_user=encodeURIComponent($('#log_oper_user').val());
	   var v_Irp_type_oper=$('#_Irp_type_oper').combobox('getValue');
	   var start_end=$('#Irp_logtime').combobox('getValue');
	   var v_Irp_oper_result=$('#Irp_oper_result').combobox('getValue');
	
	 
	   if (/^[+]?[0-9]+\d*$/i.test($('#log_oper_obj_id').val())==false && $('#log_oper_obj_id').val()!=""){
		   $.messager.alert("操作提示","请输入合法的操作对象id");
		   return false;
	   }
	   if (date_start_time>date_end_time) {
		   $.messager.alert("操作提示","结束日期必须在开始日期之后");
		   return false;
	   }

	   jump('<%=rootPath%>log/log_checkSearch_action.action?c_v_log_type='+logstypes+'&c_v_log_obj_oper='+v_log_obj_oper+'&c_v_log_oper_obj_id='+v_log_oper_obj_id+'&c_v_log_oper_user='+v_log_oper_user+'&c_v_Irp_type_oper='+v_Irp_type_oper+'&c_start_end='+start_end+'&c_v_Irp_oper_result='+v_Irp_oper_result+'&c_date_start_time='+date_start_time+'&c_date_end_time='+date_end_time+'&pageNum=1&orderField&irplogstype='+logstypes );
	
 	  
   }

 //分页
	function page(_nPageNum){
		$('#pageNum').val(_nPageNum);
		var queryString = $('#pageForm').serialize();
	   var date_start_time=$('#start_time').datebox('getValue');   
       var date_end_time=$('#end_time').datebox('getValue');    
	   var v_log_type=$('#_log_type').combobox('getValue');
	   var v_log_obj_oper=$('#_log_obj_oper').combobox('getValue');
	   var v_log_oper_obj_id=encodeURIComponent($('#log_oper_obj_id').val());
	   var v_log_oper_user=encodeURIComponent($('#log_oper_user').val());
	   var v_Irp_type_oper=$('#_Irp_type_oper').combobox('getValue');
	   var start_end=$('#Irp_logtime').combobox('getValue');
	   var v_Irp_oper_result=$('#Irp_oper_result').combobox('getValue');
		
		jump('<%=rootPath%>log/log_logs_list_error.action?'+queryString+'&c_v_log_type='+logstypes+'&c_v_log_obj_oper='+v_log_obj_oper+'&c_v_log_oper_obj_id='+v_log_oper_obj_id+'&c_v_log_oper_user='+v_log_oper_user+'&c_v_Irp_type_oper='+v_Irp_type_oper+'&c_start_end='+start_end+'&c_v_Irp_oper_result='+v_Irp_oper_result+'&c_date_start_time='+date_start_time+'&c_date_end_time='+date_end_time+'&irplogstype='+logstypes);
	}
	  
	//排序
	function orderBy(_sFiled,_sOrderBy){
		$('#orderField').val(_sFiled);
		$('#orderBy').val(_sOrderBy);
		var queryString = $('#pageForm').serialize();
	   var date_start_time=$('#start_time').datebox('getValue');   
       var date_end_time=$('#end_time').datebox('getValue');    
	   var v_log_type=$('#_log_type').combobox('getValue');
	   var v_log_obj_oper=$('#_log_obj_oper').combobox('getValue');
	   var v_log_oper_obj_id=encodeURIComponent($('#log_oper_obj_id').val());
	   var v_log_oper_user=encodeURIComponent($('#log_oper_user').val());
	   var v_Irp_type_oper=$('#_Irp_type_oper').combobox('getValue');
	   var start_end=$('#Irp_logtime').combobox('getValue');
	   var v_Irp_oper_result=$('#Irp_oper_result').combobox('getValue');
		
		jump('<%=rootPath%>log/log_logs_list_error.action?'+queryString+'&c_v_log_type='+logstypes+'&c_v_log_obj_oper='+v_log_obj_oper+'&c_v_log_oper_obj_id='+v_log_oper_obj_id+'&c_v_log_oper_user='+v_log_oper_user+'&c_v_Irp_type_oper='+v_Irp_type_oper+'&c_start_end='+start_end+'&c_v_Irp_oper_result='+v_Irp_oper_result+'&c_date_start_time='+date_start_time+'&c_date_end_time='+date_end_time+'&irplogstype='+logstypes);
	}
	//全选
	function checkAll(){
		m_checked = !m_checked;
		$("input[name='checkbox_logid']").attr("checked",m_checked); 
	}
	//刷新
	function logRefresh(){
		$('body').layout('panel','center').panel('refresh');
	}


	
	
//引入扩展验证
$.extend($.fn.validatebox.defaults.rules, {   
	    Maxlength:{//最大输入长度
	    	validator:function(value){ 
	    		if (/^[+]?[0-9]+\d*$/i.test(value)) {
				return true;	
				}else{
				return false;
				}
	        }, 
	            message:'请输入合法整数'
	        },
	        EndTime:{
		    	validator:function(){
		    		if($('#end_time').datebox('getValue')>=$('#start_time').datebox('getValue')){
		    			return true;
		    		}else{	  
		    			return false;
		    		}	
		    	},
		    	message:'结束日期必须在开始日期之后'
		    },
		    StartTime:{
		    	validator:function(){
		    		if($('#end_time').datebox('getValue')>=$('#start_time').datebox('getValue')){
		    			
		    			return true;
		    		}else{
		    			
		    			return false;
		    		}	
		    	},
		    	message:'开始日期必须在结束日期之前'
		    }
	
}); 


</script>
	<form id="pageForm">
		<s:hidden name="pageNum" id="pageNum" />
		<s:hidden name="pageSize" id="pageSize" />
		<s:hidden name="orderField" id="orderField" />
		<s:hidden name="orderBy" id="orderBy" />
	</form>
	<table width="100%" border="0" align="center" cellpadding="10"
		cellspacing="1" bgcolor="#cad9ea">
		<tr class="list_th"
			style="position: relative;">
			<td colspan="8" style="padding-left: 10px;">
				<form id="check_logs" method="post">
					日志类型 :<select id="_log_type" name="c_v_log_type">
						<option value="0" selected="selected">全部</option>
						<option value="1">错误</option>
						<option value="2">警告</option>
						<option value="3">信息</option>
						<option value="4">调试</option>
					</select>
					&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;
					操作对象:<select id="_log_obj_oper" panelHeight="150"
						name="c_v_log_obj_oper">
						<option value="0" selected="selected">全部</option>
					    <s:iterator value="listIrpTableid">
							<option value="<s:property value='TABLEOBJTYPE' />"><s:property value='TABLEOBJTYPE' /></option>
						</s:iterator>
					</select>
					&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;
					操作对象id:<input name="c_v_log_oper_obj_id"
						id="log_oper_obj_id" class="easyui-validatebox"
						data-options="validType:'Maxlength'">
					&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;
					操作用户: <input name="c_v_log_oper_user" id="log_oper_user"
						class="easyui-validatebox"><br/>

					操作类型:<select id="_Irp_type_oper" name="c_v_Irp_type_oper">
						<option value="0">全部</option>
						<s:iterator value="irpOpertype">
							<option value="<s:property value='opername' />"><s:property value="opername" /></option>
						</s:iterator>
					</select>
					&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;
					 请选择时间段:<select id="Irp_logtime" name="c_start_end"
						onchange="ck_date()">
						<option value="logs_week" id="_logs_week">本周</option>
						<option value="logs_month" id="_logs_month" selected="selected">本月</option>
						<option value="logs_quarter" id="_logs_quarter">本季</option>
						<option value="logs_appoint_date" id="_logs_appoint_date">指定</option>
					</select>&nbsp;&nbsp; <span id="_time_date" style="display: none;">
						开始日期<input type="text" name="" id="start_time"
						class="easyui-datebox" editable="false" validType="StartTime">&nbsp;&nbsp; 结束日期<input
						type="text" name="" id="end_time" class="easyui-datebox"
						editable="false" validType="EndTime"> </span>
					&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp; 
						操作结果:<select id="Irp_oper_result" panelHeight="100" name="c_v_Irp_oper_result">
						<option value="0" selected="selected">全部</option>
						<option value="1">成功</option>
						<option value="2">失败</option>
					</select> <a href="javascript:void(0)" id="log_search"
						class="easyui-linkbutton" data-options="iconCls:'icon-search'"
						onclick="log_CheckSearch()">检索</a>&nbsp;&nbsp;<a href="javascript:void(0)"
						onclick="logRefresh()" class="easyui-linkbutton"
						data-options="iconCls:'icon-reload'">刷新</a>
				</form></td>
		</tr>

		<tr>
			<td align="center" bgcolor="#F5FAFE" class="main_bright"><a
				href="javascript:void(0)" onclick="checkAll()">序号</a>
			</td>
			<td align="center" bgcolor="#F5FAFE" class="main_bright">查看</td>
			<td align="center" bgcolor="#F5FAFE" class="main_bright"><a
				href="javascript:void(0)"
				onclick="orderBy('logtype','<s:if test="orderField=='logtype'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">日志类型<s:if
						test="orderField=='logtype'&&orderBy=='desc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_down.gif" />
					</s:if>
					<s:elseif test="orderField=='logtype'&&orderBy=='asc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_up.gif" />
					</s:elseif>
			</a>
			</td>
			<td align="center" bgcolor="#F5FAFE" class="main_bright"><a
				href="javascript:void(0)"
				onclick="orderBy('logobjname','<s:if test="orderField=='logobjname'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">操作对象<s:if
						test="orderField=='logobjname'&&orderBy=='desc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_down.gif" />
					</s:if>
					<s:elseif test="orderField=='logobjname'&&orderBy=='asc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_up.gif" />
					</s:elseif>
			</a>
			</td>
			<td align="center" bgcolor="#F5FAFE" class="main_bright"><a
				href="javascript:void(0)"
				onclick="orderBy('logoptype','<s:if test="orderField=='logoptype'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">操作类型<s:if
						test="orderField=='logoptype'&&orderBy=='desc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_down.gif" />
					</s:if>
					<s:elseif test="orderField=='logoptype'&&orderBy=='asc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_up.gif" />
					</s:elseif>
			</a>
			</td>
			<td align="center" bgcolor="#F5FAFE" class="main_bright"><a
				href="javascript:void(0)"
				onclick="orderBy('loguser','<s:if test="orderField=='loguser'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">操作用户<s:if
						test="orderField=='loguser'&&orderBy=='desc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_down.gif" />
					</s:if>
					<s:elseif test="orderField=='loguser'&&orderBy=='asc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_up.gif" />
					</s:elseif>
			</a>
			</td>
			<td align="center" bgcolor="#F5FAFE" class="main_bright"><a
				href="javascript:void(0)"
				onclick="orderBy('logoptime','<s:if test="orderField=='logoptime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">操作时间<s:if
						test="orderField=='logoptime'&&orderBy=='desc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_down.gif" />
					</s:if>
					<s:elseif test="orderField=='logoptime'&&orderBy=='asc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_up.gif" />
					</s:elseif>
			</a>
			</td>
			<td align="center" bgcolor="#F5FAFE" class="main_bright" width="7%"><a
				href="javascript:void(0)"
				onclick="orderBy('logresult','<s:if test="orderField=='logresult'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">操作结果<s:if
						test="orderField=='logresult'&&orderBy=='desc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_down.gif" />
					</s:if>
					<s:elseif test="orderField=='logresult'&&orderBy=='asc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_up.gif" />
					</s:elseif>
			</a>
			</td>
		</tr>
		<s:if test="irpLogs.size()!=0">
			<s:iterator var="irpLogs_type" value="irpLogs"
				status="irpopertypestate">
				<tr>
					<td align="center" bgcolor="#F5FAFE" class="main_bright" width="5%"><input
						type="checkbox" name="checkbox_logid"
						value="<s:property value='logid' />"> <s:property
							value="(pageNum-1)*pageSize+#irpopertypestate.count" />
					</td>
					<td align="center" bgcolor="#F5FAFE" class="main_bright" width="5%"><a
						href="javascript:void(0)"
						onclick="checkLog('<s:property value="logid" />')">查看</a>
					</td>
					<td align="center" bgcolor="#F5FAFE" class="main_bright" width="5%">信息</td>
					<td align="left" bgcolor="#F5FAFE" class="main_bright" width="25%">
					<s:if test="logobjname.length()>40"><s:property value="logobjname.substring(0,40)" />.....</s:if>
					<s:if test="logobjname.length()<40"><s:property value="logobjname" /></s:if>
					</td>
					<td align="left" bgcolor="#F5FAFE" class="main_bright" width="25%"><s:property
							value="logoptype" />
					</td>
					<td align="center" bgcolor="#F5FAFE" class="main_bright" width="15%"><s:property
							value="loguser" />
					</td>
					<td align="center" bgcolor="#F5FAFE" class="main_bright" width="15%"><s:date
							name="logoptime" format="yyyy-MM-dd HH:mm:ss" /></td>
					<s:if test="logresult==1">
						<td align="center" bgcolor="#F5FAFE" class="main_bright"
							width="5%">成功</td>
					</s:if>
					<s:if test="logresult==2">
						<td align="center" bgcolor="#F5FAFE" class="main_bright"
							width="5%">失败</td>
					</s:if>
				</tr>
			</s:iterator>

			<tr bgcolor="#FFFFFF">
				<td colspan="8" align="right" class="page"><s:property
						value="pageHTML" escapeHtml="false" />
				</td>
			</tr>

		</s:if>
		<s:if test="irpLogs.size()==0">
			<tr>
				<td colspan="8" align="right">暂时没有匹配的日志</td>
			</tr>
		</s:if>
	</table>
</body>
</html>