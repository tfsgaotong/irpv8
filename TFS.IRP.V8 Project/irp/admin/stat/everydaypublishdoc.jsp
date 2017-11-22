<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>     
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>        
<title>知识发布量统计图</title>
<body>
<script type="text/javascript" src="js/log/log-format-time.js"></script>
<script type="text/javascript">
$(function(){
	var xaxisJason= "<s:property value='xaxisJasonString'/>";
	var timeLimit="<s:property value='timeLimit'/>";
	var maxNodeAmount=10;
	var xArrays=xaxisJason.split(",");
	var xLen=xArrays.length;
	var step=1;
	if(xLen>maxNodeAmount){
	  step=Math.ceil(xArrays.length/maxNodeAmount);
	}
 	initTime();
 	showData(step);
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
function showData(step){
		$('#container').highcharts({
			 colors:[ '#0d233a', '#f28f43'] ,////不同组数据的显示背景色，循环引用 
			chart:{
				type:'spline'//区域图
			},
			title:{
				text:'知识发布量统计图...'
			},
			subtitle:{
				text:''
			},
			xAxis:{
				min:0,
				title:{
					text:'发布时间'
				},
				tickInterval:step,
				categories:<s:property value="xaxisJasonString"/>,
				labels:{
					rotation:-90,
					y:45 ,
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
					text:'发布数量...'
				},
			},
			tooltip:{
				enabled: true,//鼠标移动到上面执行
				formatter:function(){//显示的数据 
					var timeLimit='<s:property value="timeLimit"/>';
				 	if(timeLimit=="thismonth"){
				 		return "本月[ "+this.x+" ]号发布了[ "+this.y+" ]篇 "+this.series.name+" 知识.";
				 	}else if(timeLimit=="thisweek"){
				 		return "[ 星期"+this.x+" ]发布了[ "+this.y+" ]篇"+this.series.name+" 知识.";
				 	}else if(timeLimit=="thisquarter"){
				 		return "本季度第[ "+this.x+" ]天发布了[ "+this.y+" ]篇"+this.series.name+" 知识.";
				 	}else {
				 		return this.x+"发布了[ "+this.y+" ]篇"+this.series.name+" 知识.";
				 	}
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
	            },
				 series: {
		                //lineWidth: 1//设置线条的宽度
		            },
				pointStart:1,
				animation:true//是否使用动画 
			},
			series:[
			        {
						name:'企业',
						data:<s:property value="amountJsonString"/>
					},
					{
						name:'个人',
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

</span>		<a id="log_search"  class="easyui-linkbutton l-btn" onclick="searchDate('<%=rootPath %>site/everydaypublishdocumentamount.action')" data-options="iconCls:'icon-search'" href="javascript:void(0);">
		查询</a>
		<span>
			<select id="sizeselect">
				<s:iterator begin="1" end="9" status="status">
				<option value="<s:property value='#status.count-1'/>"><s:property value='#status.count-1'/>px</option> 
				</s:iterator>
			</select>
		</span>
</div>
<div id="container"></div>
</body>
</html>
