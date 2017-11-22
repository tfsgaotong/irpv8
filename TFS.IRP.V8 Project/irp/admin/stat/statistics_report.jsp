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
<script type="text/javascript" src="js/log/log-format-time.js"></script>
<title>问答统计</title>
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
	
	function downloadfile(){
		var limitTime = $('#timeselect').combobox('getValue');
		var date_start_time=$('#starttime').datebox('getValue');
		var date_end_time=$('#endtime').datebox('getValue'); 
		if(limitTime=="otherdate"){
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
				alert(1);
				window.location.href="<%=rootPath %>stat/exportstatreport.action?timeLimit="+limitTime+"&startTime="+date_start_time+"&endTime="+date_end_time;
			}else{
				$.messager.alert("操作提示","结束日期必须在开始日期之后");
			}
		}else{ 
			window.location.href="<%=rootPath %>stat/exportstatreport.action?timeLimit="+limitTime;
		}
	}
</script>
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

</span>		
		<a id="log_search" class="easyui-linkbutton l-btn" onclick="downloadfile()" data-options="iconCls:'icon-search'" href="javascript:void(0);">
		生成文档</a>
</div>


</body>
</html>