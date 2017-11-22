<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>     
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>        
<title>系统活跃度统计图 </title>
<body>
<script type="text/javascript" src="js/log/log-format-time.js"></script>
<script type="text/javascript" src="js/highcharts/highcharts-more.js"></script>
<script type="text/javascript">
$(function(){ 
	systemVitality();
 	initTime();
}); 
function systemVitality(){
    $('#container').highcharts({
	    chart: {
	        type: 'gauge',
	        plotBackgroundColor: null,
	        plotBackgroundImage: null,
	        plotBorderWidth: 0,
	        plotShadow: false
	    },
	    title: {
	        text: '系统活跃度统计.'
	    },
	    pane: {
	        startAngle: -150,
	        endAngle: 150,
	        background: [{
	            backgroundColor: {
	                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
	                stops: [
	                    [0, '#FFF'],
	                    [1, '#333']
	                ]
	            },
	            borderWidth: 0,
	            outerRadius: '109%'
	        }, {
	            backgroundColor: {
	                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
	                stops: [
	                    [0, '#333'],
	                    [1, '#FFF']
	                ]
	            },
	            borderWidth: 1,
	            outerRadius: '107%'
	        }, {
	            // default background
	        }, {
	            backgroundColor: '#DDD',
	            borderWidth: 0,
	            outerRadius: '105%',
	            innerRadius: '103%'
	        }]
	    },
	    // the value axis
	    yAxis: {
	        min: 0,
	        max: 100,
	        
	        minorTickInterval: 'auto',
	        minorTickWidth: 1,
	        minorTickLength: 5,
	        minorTickPosition: 'inside',
	        minorTickColor: '#666',
	
	        tickPixelInterval: 30,
	        tickWidth: 2,
	        tickPosition: 'inside',
	        tickLength: 10,
	        tickColor: '#666',
	        labels: {
	            step: 2,
	            rotation: 'auto'
	        },
	        title: {
	            text: '活跃度'
	        },
	        plotBands: [{
	            from: 0,
	            to: 50,
	            color: '#55BF3B' // green
	        }, {
	            from: 50,
	            to: 80,
	            color: '#DDDF0D' // yellow
	        }, {
	            from: 80,
	            to: 100,
	            color: '#DF5353' // red
	        }]        
	    },
	    series: [{
	        name: '活跃度',
	        data: [<s:property value="amountJsonString"/>],
	        tooltip: {
	            valueSuffix: ' %'
	        }
	    }]
	}
	);				
} 

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
				 showtimedatediv(date,date);
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
</script>  
<div style="padding-left: 10px;">
<select id="timeselect">
<option value="thisday" id="thisday"  <s:if test="timeLimit=='thisday'">selected="selected"</s:if>>今天</option>
<option value="yesterday" id="yesterday"  <s:if test="timeLimit=='yesterday'">selected="selected"</s:if>>昨天</option>
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

</span>		<a id="log_search"  class="easyui-linkbutton l-btn" onclick="searchDate('<%=rootPath %>site/systemvitality.action')" data-options="iconCls:'icon-search'" href="javascript:void(0);">
		查询</a>
</div>
<div id="container"></div>
</body>
</html>
