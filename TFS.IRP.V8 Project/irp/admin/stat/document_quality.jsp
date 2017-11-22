<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>     
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>        
<title>知识的质量统计图</title>
<body>
<script type="text/javascript" src="js/log/log-format-time.js"></script>
<script type="text/javascript">
$(function(){ 
	documentscore();
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
//评分统计图
function documentscore(){
	    chart = new Highcharts.Chart({ 
	    	 colors:['#46cbee','#fec157', '#e57244', '#cfd17d', '#24CBE5', '#64E572','#FF9655','#FFF263', '#6AF9C4' ] ,////不同组数据的显示背景色，循环引用 
	        chart: {  
	            renderTo: 'container', //画布所在的div 
	            // plotBackgroundColor: '#f5f2ec',//设置画布所在的背景色  
	            plotBorderWidth: null, //画布的边框 
	            plotShadow: false, 
	            margin:[0,0,0,0],//画布外边框
	            events: {  
	              load: function() {
	            	  var amountStr='<s:property value="amountJsonString"/>';
	            	  if(amountStr!=""){
							arrays=amountStr.split(",");
							var series = this.series[0]; 
							var one=arrays[0];
							var two=arrays[1];
							var three=arrays[2];
							var four=arrays[3];
							var five=arrays[4];
							var other=arrays[5];
							var data = [];  
							data.push(['1分', window.parseInt(one)]);  
							data.push(['2分', window.parseInt(two)]);  
							data.push(['3分', window.parseInt(three)]);  
							data.push(['4分', window.parseInt(four)]);  
							data.push(['5分', window.parseInt(five)]);  
							data.push(['未评分', window.parseInt(other)]);  
							series.setData(data); 
	            	  }
						 
	              }  
	           }  
	        },  
	        title: {  
	            text: '<b>知识质量统计图.</b>'  
	        },  
	        tooltip: {
	        	enable:false,//去掉浮动显示
	        	shared:true,
	            formatter: function() {  
	                 return '<b>'+ this.point.name +'</b>: '+ Highcharts.numberFormat(this.percentage, 1) +'% ('+this.y+' 篇)';
	            }  
	        },  
	        plotOptions: {  
	            pie: {  
	                allowPointSelect: true,  // 点击标签时允许序列的点被选择
	                cursor: 'pointer', 
	                size:'60%', //圆形的直径大小比例
		            showInLegend:true,//每个部分的名称在最下面显示
	                dataLabels: {  
	                    enabled: true,  
	                    color: '#000000',
	                    connectorColor: '#000000',  
	                    formatter: function() {  
	                        return '<b>'+ this.point.name +'</b>: '+ Highcharts.numberFormat(this.percentage, 1) +'% ('+this.y+' 篇)';
	                    }  
	                }  
	            } 
	        },   
	        series: [{  
	            type: 'pie',
	            name:'占比例'
	        }]  
	    });  
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

</span>		<a id="log_search"  class="easyui-linkbutton l-btn" onclick="searchDate('<%=rootPath %>site/documentquality.action')" data-options="iconCls:'icon-search'" href="javascript:void(0);">
		查询</a>
</div>
<div id="container"></div>
</body>
</html>
