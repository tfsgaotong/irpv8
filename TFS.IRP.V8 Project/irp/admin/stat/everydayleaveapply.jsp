<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>     
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>        
<title>知识发布量统计图</title>
<body>
<script type="text/javascript" src="js/log/log-format-time.js"></script>
<script type="text/javascript">
var start_end;
$(function(){
	var xaxisJason= "<s:property value='xaxisJasonString'/>";
	var timeLimit="<s:property value='c_start_end'/>";
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
				text:'每天请假统计图...'
			},
			subtitle:{
				text:''
			},
			xAxis:{
				min:0,
				title:{
					text:'请假时间'
				},
				tickInterval:step,
				categories:<s:property value="xaxisJasonString"/>,
				labels:{
					rotation:-90,
					y:45 ,
					formatter:function(){
						var timelimit=$('#Irp_logtime').combobox('getValue');
						if(timelimit=="logs_week"){
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
					text:'请假数量...'
				},
			},
			tooltip:{
				enabled: true,//鼠标移动到上面执行
				formatter:function(){//显示的数据 
					var timeLimit='<s:property value="c_start_end"/>';
				 	if(timeLimit=="logs_month"){
				 		return "本月[ "+this.x+" ]号共有[ "+this.y+" ]次  请假.";
				 	}else if(timeLimit=="logs_week"){
				 		return "[ 星期"+this.x+" ]共有[ "+this.y+" ]次 请假.";
				 	}else if(timeLimit=="logs_quarter"){
				 		return "本季度第[ "+this.x+" ]天共有[ "+this.y+" ]次请假.";
				 	}else {
				 		return this.x+"共有[ "+this.y+" ]次 请假.";
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
						name:'次数',
						data:<s:property value="amountJsonString"/>
					}]
		});
	}

function initTime(){
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

}  
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
		 $('#contributeranking2').panel('refresh',"<%=rootPath %>leave/everydaypublishdocumentamount.action?c_start_end="+start_end+"&marking="+marking+"&starttime="+date_start_time+"&endtime="+date_end_time);
		
	  
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
						<span>
			<select id="sizeselect">
				<s:iterator begin="1" end="9" status="status">
				<option value="<s:property value='#status.count-1'/>"><s:property value='#status.count-1'/>px</option> 
				</s:iterator>
			</select>
		</span>
				</form>
</div>
<div id="container"></div>
</body>
</html>
