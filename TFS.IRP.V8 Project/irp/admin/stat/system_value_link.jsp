<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>     
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>        
<title>系统积分,经验综合统计图</title>
<body>
<script type="text/javascript" src="js/log/log-format-time.js"></script>
<script type="text/javascript" src="js/highcharts/highcharts-more.js"></script>
<script type="text/javascript">
$(function(){ 
 	initTime();
	var xaxisJason= "<s:property value='xaxisJasonString'/>";
	var timeLimit="<s:property value='timeLimit'/>";
	var maxNodeAmount=10;
	var xArrays=xaxisJason.split(",");
	var xLen=xArrays.length;
	var step=1;
	if(xLen>maxNodeAmount){
	  step=Math.ceil(xArrays.length/maxNodeAmount);
	}
 	systemVisitedAmount(step);
	initSize();
}); 
function initSize(){
	$('#sizeselect').combobox({
		panelHeight:'160',
		width:'50',
		editable:false,
		onSelect:function(){
			var  size = $(this).combobox('getValue');
			showOption(size);
		}
	 }); 
}
function showOption(size){
	 var options=$('#container').highcharts().options;
	 options.plotOptions.spline.marker.radius=size;
	 options.plotOptions.spline.marker.states.hover.radius=parseInt(size)+2;
	 $('#container').html('');
	 options.exporting.buttons.contextButton.enable=true;
 	 new Highcharts.Chart(options);
}
 function systemVisitedAmount(step){
	 var  chart = new Highcharts.Chart({
		 global:{
				useUTC:false 
			 },
		 chart:{
				renderTo: 'container', //画布所在的div 
				type:'spline' 
			} ,
		title:'系统活跃度图.',
		xAxis:{  
			tickInterval:step,
			minorTickInterval:null,////设置是否出现纵向小标尺  可以设置数值，例如0.1 ,'auto'
			categories:<s:property value="xaxisJasonString"/>,
			labels:{
				rotation:-90,
				y:45,
				formatter:function(){
					var timelimit=$('#timeselect').combobox('getValue');
					if(timelimit=="thisweek"){
				             return "<b>星期  </b>"+this.value;
					} else{
						return this.value;
					} 
				}
			} 
		},
		yAxis:{
			min:0,
			title:{
				text:'访问量...'
			}
		}  ,  
		tooltip: {
			   formatter: function() {
				      return '<b>'+ this.series.name +'</b><br>'+
				      '<b>星期  </b>'+this.x +'<br><b>'+ this.y +'</b>';// %H:%M:%S
				}
		},
		plotOptions:{
			spline: {
                marker: {
                    radius:0,
                    lineColor: '#666666',
                    lineWidth: 1
                },
                animation:false
            } 
		},
		series: [
					{ name: '积分', 
						data:<s:property value="amountJsonString"/>
					},
					{ name: '经验', 
						data:<s:property value="privateAmountJsonString"/>
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

</span>		<a id="log_search"  class="easyui-linkbutton l-btn" onclick="searchDate('<%=rootPath %>site/systemvaluelink.action')" data-options="iconCls:'icon-search'" href="javascript:void(0);">
		查询</a>
		<span>
			<select id="sizeselect">
				<s:iterator begin="1" end="9" status="status">
				<option value="<s:property value='#status.count-1'/>"><s:property value='#status.count-1'/>px</option> 
				</s:iterator>
			</select>
		</span>
		<span>
		截止目前,系统中总积分为：<font color="red"><s:property value="sumScore"/></font>&nbsp;&nbsp;
		总经验为：<font color="red"><s:property value="sumExperence"/></font>
		</span>
		
</div>
<div id="container"></div>
</body>
</html>
