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
<title>访问时间段统计</title>
</head>
<body>
<script type="text/javascript">
$(function () {
 	initTime();
 	timeVisity();
});
function timeVisity(){
	var daytimes = '<s:property value="daytimes" />';
	var daytimesarray = daytimes.split(",");
	
    $('#container').highcharts({
        chart: {
        },
        title: {
            text: '访问时间段统计图'
        },
        xAxis: {
            categories: ['24:00~3:00', '3:00~6:00', '6:00~9:00', '9:00~12:00', '12:00~15:00','15:00~18:00','18:00~21:00','21:00~24:00']
        },
        yAxis: {
            min: 0,
            title: {
                text: '次数'
            }
        },
        tooltip: {
            formatter: function() {
                var s;
                if (this.point.name) { // the pie chart
                    s = ''+
                        this.point.name +': '+ this.y +' 人次';
                } else {
                    s = ''+
                        this.x  +': '+ this.y+' 人次';
                }
                return s;
            }
        },
        labels: {
            items: [{
                html: '比例图',
                style: {
                    left: '40px',
                    top: '8px',
                    color: 'black'
                }
            }]
        },
        series: [{
            type: 'column',
            name: '访问次数',
            data: [<s:property value="hoursarray" />]
             
        }, {
            type: 'spline',
            name: '访问趋势',
            data: [<s:property value="hoursarray" />],
            marker: {
            	lineWidth: 2,
            	lineColor: Highcharts.getOptions().colors[3],
            	fillColor: 'white'
            }
        }, {
            type: 'pie',
            name: 'Total consumption',
            data: [{
                name: '上午',
                y: parseInt(daytimesarray[1]),
                color: Highcharts.getOptions().colors[2] // Jane's color
            }, {
                name: '下午',
                y: parseInt(daytimesarray[2]),
                color: Highcharts.getOptions().colors[3] // John's color
            }, {
                name: '晚上',
                y: parseInt(daytimesarray[3]),
                color: Highcharts.getOptions().colors[1] // Joe's color
            }, {
                name: '凌晨',
                y: parseInt(daytimesarray[0]),
                color: Highcharts.getOptions().colors[5] // Joe's color
            }],
            center: [100, 50],
            size: 100,
            showInLegend: false,
            dataLabels: {
                enabled: false
            }
        }]
    });
    

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

</span>		<a id="log_search"  class="easyui-linkbutton l-btn" onclick="searchDate('<%=rootPath %>stat/systemthequantum.action')" data-options="iconCls:'icon-search'" href="javascript:void(0);">
		查询</a>
</div>
<div id="container"></div>

</body>
</html>