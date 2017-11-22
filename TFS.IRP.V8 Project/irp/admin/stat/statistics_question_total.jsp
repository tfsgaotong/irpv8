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
	topvalue();
    $('#container').highcharts({                                          
        chart: {
            type: 'line'
        },
        title: {
            text: '问答情况统计'
        },
        xAxis: {
            categories: [<s:property value='xvalue' />]
        },
        yAxis: {
            title: {
                text: '单位 (个)'
            },
            allowDecimals:false,
            min:0
        },  
        tooltip: {
            enabled: false,
            formatter: function() {
                return '<b>'+ this.series.name +'</b><br/>'+
                    this.x +': '+ this.y +'°C';
            }
        },
        plotOptions: {
            line: {
                dataLabels: {
                    enabled: true
                },
                enableMouseTracking: false
            }
        },
        series: [{
            name: '问题',
            data: [<s:property value='lineofquestion' />]
        },{
            name: '答案',
            data: [<s:property value='lineofanswer' />]
        },{
            name: '以解决的问题',
            data: [<s:property value='lineofsolve' />]
        }]                                                           
    }); 

});
function topvalue(){
    $('#containerclumn').highcharts({
        chart: {                                                           
            type: 'bar'                                                    
        },                                                                 
        title: {                                                           
            text: ''                    
        },                                                                 
        xAxis: {                                                           
            categories: ['问答总体情况']                                                     
        },                                                                 
        yAxis: {                                                           
            min: 0,                                                        
            title: {                                                       
                text: '',                             
                align: 'high'                                              
            },                                                             
            labels: {                                                      
                overflow: 'justify'                                        
            }                                                              
        },                                                                 
        tooltip: {                                                         
            valueSuffix: ' 个'                                       
        },                                                                 
        plotOptions: {                                                     
            bar: {                                                         
                dataLabels: {                                              
                    enabled: true                                          
                }                                                          
            }                                                              
        },                                                                 
        legend: {                                                          
            layout: 'vertical',                                            
            align: 'right',                                                
            verticalAlign: 'top',                                          
            x: -40,                                                        
            y: 100, 
            enabled:false,
            floating: true,                                                
            borderWidth: 1,                                                
            backgroundColor: '#FFFFFF',                                    
            shadow: true                                                   
        },                                                                 
        credits: {                                                         
            enabled: false                                                 
        },                                                                 
        series: [{                                                         
            name: '所有的问题',                                             
            data: [<s:property value='sumquestion' />]                                   
        }, {     
        	 name: '所有的回答',                                             
             data: [<s:property value='sumanswer' />]           
        }, {                                                               
        	 name: '已解决的问题',                                             
             data: [<s:property value='sumsalovequestion' />]                   
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

</span>		<a id="log_search"  class="easyui-linkbutton l-btn" onclick="searchDate('<%=rootPath %>stat/statquestiontrend.action')" data-options="iconCls:'icon-search'" href="javascript:void(0);">
		查询</a>
</div>

<div id="container"></div>
<div id="containerclumn" style="height: 120px;margin-top: -15px;"></div>
</body>
</html>