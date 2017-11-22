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
<title>请假天数统计</title>
</head>
<body>
<script type="text/javascript" src="js/log/log-format-time.js"></script>
<script type="text/javascript">
$(function () {
 	timeVisity();
});
var start_end;
$(function(){	
	$('#Irp_logtime').combobox({		
	 	 panelHeight:'130',
	 	 editable:false,
	 	 onSelect:function(){
	 		 start_end = $(this).combobox('getValue');
	 		if (start_end=="logs_appoint_date") {
	 			var testDate = new Date();
	 			$('#start_time').datebox();
	 			$('#end_time').datebox();
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
	   var start_end=$('#Irp_logtime').combobox('getValue');	 
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
		 $('#contributeranking1').panel('refresh',"<%=rootPath %>leave/departmenttime.action?orderField&c_start_end="+start_end+"&marking="+marking+"&starttime="+date_start_time+"&endtime="+date_end_time);
		
	  
}
function timeVisity(){
	var daytimes = '<s:property value="daytimes" />';
	var daytimesarray = daytimes.split(",");
	
    $('#container').highcharts({
        chart: {
        },
        title: {
            text: '请假天数统计图'
        },
        xAxis: {
            categories: ['0~1', '1~2', '2~3', '3~4', '4~5','5~10','10~15','15+']
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
        }]
    });
    

}

</script>
<div style="padding-left: 10px;">
<form id="check_logs" method="post">
				<input type="text" name="marking" id="marking" value="10" style="display:none"/>
							
					
					 请选择时间段:<select id="Irp_logtime" name="c_start_end"
						onchange="ck_date()">
						<option value="logs_week" id="_logs_week"  <s:if test="c_start_end=='logs_week'">selected="selected"</s:if>>本周</option>
						<option value="logs_month" id="_logs_month" <s:if test="c_start_end=='logs_month'">selected="selected"</s:if>>本月</option>
						<option value="logs_quarter" id="_logs_quarter" <s:if test="c_start_end=='logs_quarter'">selected="selected"</s:if>>本季</option>
						<option value="logs_appoint_date" id="_logs_appoint_date" <s:if test="c_start_end=='logs_appoint_date'">selected="selected"</s:if>>指定</option>
					</select>&nbsp;&nbsp; <span id="_time_date" style="display: none;">
						开始日期<input type="text" name="" id="start_time"
						class="easyui-datebox" editable="false" validType="StartTime">&nbsp;&nbsp; 结束日期<input
						type="text" name="" id="end_time" class="easyui-datebox"
						editable="false" validType="EndTime['#start_time']"> </span>
					&nbsp;&nbsp;&nbsp;
					 
					 <a href="javascript:void(0)" id="log_search"
						class="easyui-linkbutton" data-options="iconCls:'icon-search'"
						onclick="log_CheckSearch()">检索</a>
				</form></div>
<div id="container"></div>

</body>
</html>