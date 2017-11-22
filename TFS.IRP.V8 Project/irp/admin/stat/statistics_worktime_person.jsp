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
<title>个人加班统计</title>


</head>
<body>
<script type="text/javascript">
$(function () {
 	initTime();
});
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
	    $('#selectscoreorexper').combobox({
	 	 panelHeight:'160',
	 	editable:false,
	 	onSelect:function(){
			var  start_end = $(this).combobox('getValue');
			 $('#orderField').val(start_end);
			var queryString = $('#pageForm').serialize();
			 $('#personal').panel('refresh','<%=rootPath %>leave/workTimePersonStatement.action?c_start_end=logs_month&'+queryString);
		}
	  }); 
	var  start_end = $('#timeselect').combobox('getValue');
	if(start_end=="logs_appoint_date"){
		 showtimedatediv('${starttime1}','${endtime1}');
	}
}  

function searchDate(_url){
	var limitTime = $('#timeselect').combobox('getValue');
	var date_start_time=$('#starttime').datebox('getValue');
	var date_end_time=$('#endtime').datebox('getValue'); 
	var browerStartTime='${starttime1}';
	var browerEndTime='${endtime1}';
	var type = $('#_Irp_type_oper').combobox('getValue');
	toPage1(_url,limitTime,date_start_time,date_end_time,browerStartTime,browerEndTime,type);
} 
function toPage1(_url,limitTime,date_start_time,date_end_time,browerStartTime,browerEndTime,type){
	if(limitTime=="logs_appoint_date"){
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
			if(type!=""){
			 $('#personal').panel('refresh',_url+'?starttime='+date_start_time+'&endtime='+date_end_time+'&c_start_end='+limitTime+'&applytypeid='+type);
				}else{
			 $('#personal').panel('refresh',_url+'?starttime='+date_start_time+'&endtime='+date_end_time+'&c_start_end='+limitTime);
			}					
		}else{
			$.messager.alert("操作提示","结束日期必须在开始日期之后");
		}
	}else{ 
		if(type!=0){
		$('#personal').panel('refresh',_url+'?c_start_end='+limitTime+'&applytypeid='+type);	
		}else{
		$('#personal').panel('refresh',_url+'?c_start_end='+limitTime);	
		}		
	}
}


 $(function(){  
  $('#container').highcharts({
         chart: {
             type: 'column'
         },
         title: {
             text: '个人加班统计'
         },
         subtitle: {
             text: ''
         },
         xAxis: {
             categories:[<s:property value="usernamejson" escapeHtml="false" />]
         },
         yAxis: {
             min: 0,
          // tickInterval:1,
             title: {
                 text: '加班次数'
             }
         },
         tooltip: {
             headerFormat: '<span style="font-size:14px">{point.key}</span><table>',
             
             pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                 '<td style="padding:0"><b>{point.y:.0f} </b></td></tr>',
             footerFormat: '</table>',
             shared: true,
             useHTML: true
         },
         plotOptions: {
             column: {
                 pointPadding: 0.2,
                 borderWidth: 0
             }
         },
         series: [{
             name: '正常',
             data: [<s:property value="normaljson" />]
 
         }, {
             name: '紧急',
             data: [<s:property value="urgentjson" />]
         },{
        	 name: '重要',
        	 data: [<s:property value="importantjson" />]
         },{
        	 name: '时长',
        	 data: [<s:property value="worktoataltime" />]
         }]
     }); 
 });
 /**
 *分页
 */
 function page(_nPageNum){
 	$('#pageNum').val(_nPageNum);
 	var queryString = $('#pageForm').serialize();
 	jump("<%=rootPath%>leave/workTimePersonStatement.action?"+queryString);
 }
 function jump(_sUrl){
	$('body').layout('panel','center').panel('refresh',_sUrl);
}
</script>
<form id="pageForm">
<s:hidden name="pageNum" id="pageNum" />
<s:hidden name="pageSize" id="pageSize" />
<s:hidden name="c_start_end" />
<s:hidden name="starttime" id="startTime"/>
<s:hidden name="endtime" id="endTime"/>
<s:hidden name="orderField" id="orderField" />
</form>
<div style="padding-left: 10px;">
 <select id="selectscoreorexper">
  	  <option value="0" onclick="">排序</option>
      <option value="10" onclick="orderBy(this.value)" <s:if test="orderField==10">selected="selected"</s:if>  >正常</option>
      <option value="30" onclick="orderBy(this.value)" <s:if test="orderField==30">selected="selected"</s:if> >紧急</option>
      <option value="20" onclick="orderBy(this.value)" <s:if test="orderField==20">selected="selected"</s:if>  >重要</option>
       <option value="40" onclick="orderBy(this.value)" <s:if test="orderField==20">selected="selected"</s:if>  >时长</option>
  </select>
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
		<input type="text" id="starttime" class="easyui-datebox" editable="false"  />&nbsp;&nbsp;
		结束日期
		<input type="text" id="endtime" class="easyui-datebox" editable="false"  />

</span>		<a id="log_search"  class="easyui-linkbutton l-btn" onclick="searchDate('<%=rootPath %>leave/workTimePersonStatement.action')" data-options="iconCls:'icon-search'" href="javascript:void(0);">
		查询</a>
</div>
<div id="container"></div>
<table width="100%" border="0" align="center" cellpadding="10" >
 <tr >
   <td  align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
  </tr>
</table>
</body>
</html>