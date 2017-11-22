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
<title>个人概况统计</title>


</head>
<body>
<script type="text/javascript">

$(function () {
 	initTime();
});
function initTime(){
	$('#timeselect').combobox({
		panelHeight:'160',
		width:'50',
		editable:false,
		onSelect:function(){
			var  start_end = $(this).combobox('getValue');
			if(start_end!="otherdate"){ 
				$('#timedatespan').hide(); 
			}else{
				 var date=new Date(<%=new java.util.Date().getTime()%>); 
				 $('#starttime').datebox('setValue',date.getFullYear()+"-"+parseInt(date.getMonth()+1)+"-"+date.getDate());
				 $('#endtime').datebox('setValue',date.getFullYear()+"-"+parseInt(date.getMonth()+1)+"-"+date.getDate());
				 $('#timedatespan').show();
			}
		}
	 }); 
	var  start_end = $('#timeselect').combobox('getValue');
	if(start_end=="otherdate"){
		 showtimedatediv('${startTime}','${endTime}');
	}
}  
//引入扩展验证
$.extend($.fn.validatebox.defaults.rules, {   
    EndTime:{
    	validator:function(){
    		return compareTime($('#endtime').datebox('getValue'),$('#starttime').datebox('getValue'));
    	},
    	message:'结束日期必须在开始日期之后'
    },
    StartTime:{
    	validator:function(){
    		return compareTime($('#endtime').datebox('getValue'),$('#starttime').datebox('getValue'));
    	},
    	message:'开始日期必须在结束日期之前'
    }
	}); 
function searchDate(_url){
	var limitTime = $('#timeselect').combobox('getValue');
	var date_start_time=$('#starttime').datebox('getValue');
	var date_end_time=$('#endtime').datebox('getValue'); 
	var browerStartTime='${startTime}';
	var browerEndTime='${endTime}';
	toPage(_url,limitTime,date_start_time,date_end_time,browerStartTime,browerEndTime);
} 


 $(function(){  
  $('#container').highcharts({
         chart: {
             type: 'column'
         },
         title: {
             text: '个人概况统计'
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
                 text: '次数'
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
             name: '迟到',
             data: [<s:property value="latejson" />]
         },{
        	 name: '补签',
        	 data: [<s:property value="signAgainjson" />]
         },{
       	  name: '早退',
             data: [<s:property value="earlyjson" />] 
         }]
     }); 
 });
 /**
 *分页
 */
 function page(_nPageNum){
 	$('#pageNum').val(_nPageNum);
 	var queryString = $('#pageForm').serialize();
 	jump("<%=rootPath%>sign/signPersonStatement.action?"+queryString);
 }
 function jump(_sUrl){
	$('body').layout('panel','center').panel('refresh',_sUrl);
}
</script>
<form id="pageForm">
<s:hidden name="pageNum" id="pageNum" />
<s:hidden name="pageSize" id="pageSize" />
<s:hidden name="timeLimit" />
<s:hidden name="startTime" />
<s:hidden name="endTime" />
</form>
<div style="padding-left: 10px;">
<select id="timeselect">
	<option value="thismonth" id="thismonth"  <s:if test="timeLimit=='thismonth'">selected="selected"</s:if>>本月</option>
	<option value="thisweek" id="thisweek" <s:if test="timeLimit=='thisweek'">selected="selected"</s:if>>本周</option>
	<option value="thisquarter" id="thisquarter" <s:if test="timeLimit=='thisquarter'">selected="selected"</s:if>>本季</option>
	<option value="otherdate" id="otherdate" <s:if test="timeLimit=='otherdate'">selected="selected"</s:if>>指定</option>
</select>
<span id="timedatespan" style="display: none;">
		开始日期
		<input type="text" id="starttime" class="easyui-datebox" editable="false" validType="StartTime">&nbsp;&nbsp; 
		结束日期
		<input type="text" id="endtime" class="easyui-datebox" editable="false" validType="EndTime">

</span>		<a id="log_search"  class="easyui-linkbutton l-btn" onclick="searchDate('<%=rootPath %>sign/signPersonStatement.action')" data-options="iconCls:'icon-search'" href="javascript:void(0);">
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