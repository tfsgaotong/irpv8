<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>     
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>        
<title>知识分数分布图</title>
<body>
<script type="text/javascript" src="js/log/log-format-time.js"></script>
<script type="text/javascript">
$(function(){ 
	documentsctter();
 	initTime();
 });  
function documentsctter(){
	 var  chart = new Highcharts.Chart({
		 global:{
			useUTC:false 
		 },
		chart:{
			renderTo: 'container', //画布所在的div 
			type:'scatter' 
		} ,
		title:'知识分数分布图.',
		xAxis:{ 
			maxPadding : 0.05,
			minPadding : 0.05,
			type:'datetime',
			tickInterval:24 * 3600 * 1000,
			minorTickInterval:null,////设置是否出现纵向小标尺  可以设置数值，例如0.1 ,'auto'
			labels: { 
				 	rotation : -90 ,//将日期类型竖着显示
				  	// fontStyle : 'italic',//将日期斜着放置
					formatter: function() {
		             return  Highcharts.dateFormat('%Y-%m-%d',this.value);
					} ,
					y:45
			} 
		},
		yAxis:{
			min:0,
			tickInterval:0.5,
			//调整纵坐标的行距tickPixelInterval:100,
			title:{
				text:'分数'
			}
		}  , 	
		tooltip: {
			   formatter: function() {
				      return '<b>'+ this.series.name +'</b><br>'+
				      Highcharts.dateFormat('%Y-%m-%d', this.x) +'<br><b>'+ this.y +'分</b>';// %H:%M:%S
				}
		}, 
		plotOptions:{
			scatter:{
				 marker:{
					// fillColor:'#FF0000',
					 states:{
						 hover:{
							 fillColor:'#FF0000'
						 }
					 }
				 }
			}
		},
		series: [
				{ name: '企业知识', 
					//color: 'rgba(0, 0,200,225)',
					data: [
					       <s:property value="amountJsonString"/>
						]
				   }
		]     
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

</span>		<a id="log_search"  class="easyui-linkbutton l-btn" onclick="searchDate('<%=rootPath %>site/documentsctter.action')" data-options="iconCls:'icon-search'" href="javascript:void(0);">
		查询</a>
</div>
<div id="container"></div>
</body>
</html>
