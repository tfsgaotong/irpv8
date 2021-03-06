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
<title>导出成excel</title>
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

<script type="text/javascript" src="js/log/log-format-time.js"></script>
<script type="text/javascript">
/*全局变量*/
var m_checked = false;
/**
 * 刷新本页面
 */
function logRefresh(){
	$('body').layout('panel','center').panel('refresh');
}
/**
 * 全选
 */
function checkRankAll(){
	m_checked = !m_checked;
	$("input[name='leaveapplyid']").attr("checked",m_checked); 
	} 
/**
*分页
*/
function page(_nPageNum){
	$('#pageNum').val(_nPageNum);
	var queryString = $('#pageForm').serialize();
	
	$('body').layout('panel','center').panel('refresh',"<%=rootPath %>leave/getAllLeaveQuery.action?"+queryString);
}
	//导出成zip的excel
	function exportToZIP(){
		var now=new Date();
		var date=1900+now.getYear()+""+(now.getMonth()+1)+""+now.getDate()+""+now.getHours()+""+now.getMinutes()+""+now.getSeconds();
		var fileName=date;
		document.getElementById("daochu").href="leave"+fileName+".zip";
		
	  	   var date_start_time=$('#start_time').datebox('getValue');   
	  	  
	       var date_end_time=$('#end_time').datebox('getValue'); 		  
	   	   var marking=encodeURIComponent($('#marking').val());
	   	   var v_Irp_type_oper=$('#_Irp_type_oper').combobox('getValue');
	   	   var start_end=$('#Irp_logtime').combobox('getValue');	 
	   	   var v_log_type=$('#_log_type').combobox('getValue');
	   	   var emergeny=$('#_emergeny').datebox('getValue'); 	
	   	 var searchName=encodeURIComponent($('#searchName').val());
	 	   if(start_end=="logs_appoint_date"){
	 			if(date_start_time==""){
	 				
	 				date_start_time=new Date('${starttime}');
	 				var  year=date_start_time.getFullYear();
	 				var month=parseInt(date_start_time.getMonth()+1);
	 				var day=date_start_time.getDate();
	 				date_start_time=year+"-"+month+"-"+day;
	 			}
	 			if(date_end_time==""){
	 				date_end_time=new Date('${endtime}');
	 				var  year=date_end_time.getFullYear();
	 				var month=parseInt(date_end_time.getMonth()+1);
	 				var day=parseInt(date_end_time.getDate()-1);
	 				date_end_time=year+"-"+month+"-"+day;
	 			}
	 	   }
	   	   
	   	   if (date_start_time>date_end_time) {
	   		   $.messager.alert("操作提示","结束日期必须在开始日期之后");
	   		   return false;
	   		   }
	
	$.ajax({
		type:"post",
		url:"<%=rootPath%>leave/exportToZip.action?searchName="+searchName+"&fileName="+fileName,
		async:false,
		data:{
			applystatus:v_log_type,
			c_start_end:start_end,
			emergency:emergeny,
			applytypeid:v_Irp_type_oper,
			marking:marking,
			starttime:date_start_time,
			endtime:date_end_time
		},
			
		success:function(){
			
			document.getElementById("daochu").click();
		}
        
	});	}
	
	
	 var start_end;
	  $(function(){	
	  	$('#Irp_logtime').combobox({		
	  	 	 panelHeight:'130',
	  	 	 editable:false,
	  	 	 onSelect:function(){
	  	 		 start_end = $(this).combobox('getValue');
	  	 		if (start_end=="logs_appoint_date") {
	  	 			var testDate = new Date();
	  	 			$('#end_time').datebox();
	  	 			$('#start_time').datebox();
	  	 			$('#end_time').datebox('setValue',testDate.format("yyyy-MM-dd"));
	  	 			$('#start_time').datebox('setValue',testDate.format("yyyy-MM-dd"));
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
	  	var  start_end = $('#Irp_logtime').combobox('getValue');
		if(start_end=="logs_appoint_date"){
			$('#end_time').datebox({});
			$('#start_time').datebox({});
			$('#_time_date').show();
		var startDate=new Date('${starttime}');
		var endDate=new Date('${endtime}');
		
		var monthfir = parseInt(startDate.getMonth()+1);
		$('#start_time').datebox('setValue',startDate.getFullYear()+"-"+monthfir+"-"+startDate.getDate());
		var monthsec = parseInt(endDate.getMonth()+1);
		var monthsec1 = parseInt(endDate.getDate()-1);
		
		$('#end_time').datebox('setValue',endDate.getFullYear()+"-"+monthsec+"-"+monthsec1);
			
		$('#_time_date').show();	
			
			
		}
	        //申请状态
	  	  $('#_log_type').combobox({
	  	 	 panelHeight:'130',
	  	 	 width:'80',
	  	 	 editable:false
	  	  });
	        //紧急程度
	  	  $('#_emergeny').combobox({
	  	 	 panelHeight:'120',
	  	 	 width:'80',
	  	 	 editable:false
	  	  });

	       //请假类型
	  	  $('#_Irp_type_oper').combobox({
	  	 	 panelHeight:'160',
	  	 	editable:false
	  	  });
	  	    $('#end_time').datebox({
	  	    	onSelect: function(date){
	  	    		$('#end_time').datebox('setValue',date.format("yyyy-MM-dd"));
	  	    	}
	  	    });
	  	    $('#start_time').datebox({
	  	    	onSelect: function(date){
	  	    		$('#start_time').datebox('setValue',date.format("yyyy-MM-dd"));
	  	    	}
	  	    });

	  	
	  });
	  //检索
	  function log_CheckSearch(){
	  	   //日期选择one,two
	  	   var date_start_time=$('#start_time').datebox('getValue');   
	      var date_end_time=$('#end_time').datebox('getValue'); 		  
	  	   var marking=encodeURIComponent($('#marking').val());
	  	   var v_Irp_type_oper=$('#_Irp_type_oper').combobox('getValue');
	  	   var start_end=$('#Irp_logtime').combobox('getValue');	 
	  	   var v_log_type=$('#_log_type').combobox('getValue');
	  	   var emergeny=$('#_emergeny').datebox('getValue'); 	
	  	   var searchName=encodeURIComponent($('#searchName').val());
		   if(start_end=="logs_appoint_date"){
				if(date_start_time==""){
					date_start_time=new Date('${starttime}');
					var  year=date_start_time.getFullYear();
					var month=parseInt(date_start_time.getMonth()+1);
					var day=date_start_time.getDate();
					date_start_time=year+"-"+month+"-"+day;
				}
				if(date_end_time==""){
					date_end_time=new Date('${endtime}');
					var  year=date_end_time.getFullYear();
					var month=parseInt(date_end_time.getMonth()+1);
					var day=parseInt(date_end_time.getDate()-1);
					date_end_time=year+"-"+month+"-"+day;
				}
		   }
	  	   
	  	   if (date_start_time>date_end_time) {
	  		   $.messager.alert("操作提示","结束日期必须在开始日期之后");
	  		   return false;
	  		   }
	  	 $('body').layout('panel','center').panel('refresh',"<%=rootPath %>leave/exportQuery.action?pageNum=1&orderField&applystatus="+v_log_type+"&c_start_end="+start_end+"&emergency="+emergeny+"&applytypeid="+v_Irp_type_oper+"&marking="+marking+"&starttime="+date_start_time+"&endtime="+date_end_time+"&searchName="+searchName);
	  		 
	  	  
	  }
	  /*分页*/  
	  function page(_pageNum){
		  var searchName=encodeURIComponent($('#searchName').val());
	  	var date_start_time=$('#start_time').datebox('getValue');   
	      var date_end_time=$('#end_time').datebox('getValue'); 		  
	      var emergeny=$('#_emergeny').datebox('getValue'); 		  
	  	   var marking=encodeURIComponent($('#marking').val());
	  	   var v_Irp_type_oper=$('#_Irp_type_oper').combobox('getValue');
	  	   var start_end=$('#Irp_logtime').combobox('getValue');	 
	  	   var v_log_type=$('#_log_type').combobox('getValue');
	  	 if(start_end=="logs_appoint_date"){
				if(date_start_time==""){
					date_start_time=new Date('${starttime}');
					var  year=date_start_time.getFullYear();
					var month=parseInt(date_start_time.getMonth()+1);
					var day=date_start_time.getDate();
					date_start_time=year+"-"+month+"-"+day;
				}
				if(date_end_time==""){
					date_end_time=new Date('${endtime}');
					var  year=date_end_time.getFullYear();
					var month=parseInt(date_end_time.getMonth()+1);
					var day=parseInt(date_end_time.getDate()-1);
					date_end_time=year+"-"+month+"-"+day;
				}
		   }
	  	   
	  	 $('body').layout('panel','center').panel('refresh',"<%=rootPath %>leave/exportQuery.action?pageNum="+_pageNum+"&orderField&applystatus="+v_log_type+"&c_start_end="+start_end+"&emergency="+emergeny+"&applytypeid="+v_Irp_type_oper+"&marking="+marking+"&starttime="+date_start_time+"&endtime="+date_end_time+"&searchName="+searchName);
	  	
	  	
	  }
	  /**
	  *查看明细
	  */
	  function leaveapplyView(_id){
			 var dialogdiv=document.createElement("div");
			 dialogdiv.id="checklogs";
			 document.body.appendChild(dialogdiv);
			 $('#checklogs').dialog({
				 modal:true,
				 title:'查看请假信息',
				 href:'<%=rootPath%>leave/getleavebyidForAdmin.action?leaveapplyid='+_id,
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
</script>
<form id="pageForm">
<s:hidden name="searchWord" id="searchWord" />
<s:hidden name="searchType" id="searchType" />
<s:hidden name="pageNum" id="pageNum" />
<s:hidden name="pageSize" id="pageSize" />

</form>
<div id="common">

<table width="100%" border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
<tr class="list_th" style="position: relative;">
	<td colspan="8" style="padding-left: 10px;">
<form id="check_logs" method="post">
				<input type="text" name="marking" id="marking" value="10" style="display:none"/>
					申请状态 :<select id="_log_type" name="c_v_log_type">
						<option value="0" <s:if test="applystatus==0">selected="selected"</s:if>>全部</option>
						<option value="10" <s:if test="applystatus==10">selected="selected"</s:if>>已同意</option>
						<option value="20" <s:if test="applystatus==20">selected="selected"</s:if>>未审核</option>
						<option value="30" <s:if test="applystatus==30">selected="selected"</s:if>>已拒绝</option>
						
					</select>
					
					&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;
				  紧急程度:<select id="_emergeny" name="_emergeny">
						<option value="0" <s:if test="emergency==0">selected="selected"</s:if>>全部</option>
						<option value="10" <s:if test="emergency==10">selected="selected"</s:if>>正常</option>
						<option value="20" <s:if test="emergency==20">selected="selected"</s:if>>重要</option>
						<option value="30" <s:if test="emergency==30">selected="selected"</s:if>>紧急</option>
						
					</select>
					
					&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;
					
					
					申请人：<input type="text" name="searchName" id="searchName"  <s:if test="searchName!=''">value='${searchName}'</s:if>>
					&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;

					  请假类型:<select id="_Irp_type_oper" name="c_v_Irp_type_oper">
						<option value="0"  <s:if test="applytypeid==0">selected="selected"</s:if>>全部</option>						 
						 <s:iterator var="eleq" value="irpLeaveconfigs" status="status">
							<option value="<s:property value="#eleq.leaveconfigid" />"  <s:if test="applytypeid==#eleq.leaveconfigid">selected="selected"</s:if>><s:property value="#eleq.leaveconfigdesc" /></option>
						</s:iterator>
					</select>
					&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;
					 请选择时间段:<select id="Irp_logtime" name="c_start_end"
						onchange="ck_date()">
						<option value="logs_month" id="_logs_month" <s:if test="c_start_end=='logs_month'">selected="selected"</s:if>>本月</option>
						<option value="logs_week" id="_logs_week"  <s:if test="c_start_end=='logs_week'">selected="selected"</s:if>>本周</option>
						<option value="logs_quarter" id="_logs_quarter" <s:if test="c_start_end=='logs_quarter'">selected="selected"</s:if>>本季</option>
						<option value="logs_appoint_date" id="_logs_appoint_date" <s:if test="c_start_end=='logs_appoint_date'">selected="selected"</s:if>>指定</option>
					</select>&nbsp;&nbsp; <span id="_time_date" style="display: none;">
						开始日期<input type="text" name="" id="start_time"
						class="easyui-datebox" editable="false" validType="StartTime">&nbsp;&nbsp; 结束日期<input
						type="text" name="" id="end_time" class="easyui-datebox"
						editable="false" validType="EndTime['#start_time']"> </span>
					&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp; 
					 <a href="javascript:void(0)" id="log_search"
						class="easyui-linkbutton" data-options="iconCls:'icon-search'"
						onclick="log_CheckSearch()">检索</a>&nbsp;&nbsp;<a href="javascript:void(0)"
						onclick="logRefresh()" class="easyui-linkbutton"
						data-options="iconCls:'icon-reload'">刷新</a>&nbsp;&nbsp;<a href="javascript:void(0)"
						onclick="exportToZIP()" class="easyui-linkbutton"
						>导出</a><a id="daochu"></a>
				</form></td>
 </tr>
 
 <tr>
  <td align="center" width="8%" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="checkRankAll()">全选</a></td>
  <td align="center" width="20%" bgcolor="#F5FAFE" class="main_bright">申请标题</td>
  <td align="center" width="10%" bgcolor="#F5FAFE" class="main_bright">紧急程度</td>
  <td align="center" width="14%" bgcolor="#F5FAFE" class="main_bright">创建时间</td>
  <td align="center" width="14%" bgcolor="#F5FAFE" class="main_bright">开始时间</td>
  <td align="center" width="14%" bgcolor="#F5FAFE" class="main_bright">结束时间</td>
  <td align="center" width="10%" bgcolor="#F5FAFE" class="main_bright">审核人</td>
  <td align="center" width="10%" bgcolor="#F5FAFE" class="main_bright">审核状态</td>
 </tr>
 
 
 <s:iterator value="irpLeaveapplyInfos" status="userliststatus" >
 <tr>
  <td align="center" bgcolor="#F5FAFE" class="main_bright"><input name="leaveapplyid" value="<s:property value="leaveapplyid" />" type="checkbox" >&nbsp;<s:property value="(pageNum-1)*pageSize+#userliststatus.count"/></td>
  <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="leaveapplyView('<s:property value="leaveapplyid" />')"><s:property value="title" /></a></td>
  <td align="center" bgcolor="#F5FAFE" class="main_bright"><s:property value="emergency" /></td>
  <td align="center" bgcolor="#F5FAFE" class="main_bright"><s:date name="starttime" format="yyyy-MM-dd HH:mm"/></td>
  <td align="center" bgcolor="#F5FAFE" class="main_bright"><s:date name="endtime" format="yyyy-MM-dd HH:mm"/></td>
  <td align="center" bgcolor="#F5FAFE" class="main_bright"><s:date name="crtime" format="yyyy-MM-dd HH:mm"/></td>
  <td align="center" bgcolor="#F5FAFE" class="main_bright"><s:property value="checker" /></td>
  <td align="center" bgcolor="#F5FAFE" class="main_bright"><s:property value="applystatus" /></td>
 </tr>
 </s:iterator>
 <tr bgcolor="#FFFFFF">
   <td colspan="8" align="right" class="page" style="width:100%"><s:property value="pageHTML" escapeHtml="false" /></td>
  </tr>
</table>
</div>


<div id="container" style="display: none;"></div>



</body>
</html>