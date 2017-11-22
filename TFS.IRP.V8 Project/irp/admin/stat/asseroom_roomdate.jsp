<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>     
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>        
<title>知识发布量统计图</title>
<body>
<script type="text/javascript" src="js/log/log-format-time.js"></script>
<script type="text/javascript">
Date.prototype.format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
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
	asseroom();
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
function asseroom(){
	$('#asseroomid').combobox({
		panelHeight:'160',
		width:'100',
		editable:false,
		onSelect:function(){
			
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
				text:'会议室使用量统计图...'
			},
			subtitle:{
				text:''
			},
			xAxis:{
				min:0,
				title:{
					text:'使用时间'
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
					text:'使用次数...'
				},
			},
			tooltip:{
				enabled: true,//鼠标移动到上面执行
				formatter:function(){//显示的数据 
					var timeLimit='<s:property value="timeLimit"/>';
				 	if(timeLimit=="thismonth"){
				 		return "本月[ "+this.x+" ]号使用了[ "+this.y+" ]次 ";
				 	}else if(timeLimit=="thisweek"){
				 		return "[ 星期"+this.x+" ]使用了[ "+this.y+" ]次";
				 	}else if(timeLimit=="thisquarter"){
				 		return "本季度第[ "+this.x+" ]天使用了[ "+this.y+" ]次";
				 	}else {
				 		return this.x+"使用了[ "+this.y+" ]次";
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
						name:'会议室使用次数',
						data:[<s:property value="roomcountjson"/>]
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
					var testDate = new Date();
	  	 			$('#start_time').datebox();
	  	 			$('#end_time').datebox();
	  	 			$('#starttime').datebox('setValue',testDate.format("yyyy-MM-dd"));
	  	 			$('#endtime').datebox('setValue',testDate.format("yyyy-MM-dd"));
					$('#timedatespan').show();
				}
			}
		 }); 
		var  start_end = $('#timeselect').combobox('getValue');
		if(start_end=="otherdate"){
		var start=$("#start").val();
		var end =$("#end").val();
		var startDate=new Date(start);
		var endDate=new Date(end);
		$('#endtime').datebox({
			 value: endDate.format("yyyy-MM-dd"),
			 required: true,
    		 showSeconds: false
		});
		$('#starttime').datebox({
		 	value: startDate.format("yyyy-MM-dd"),
		 	required: true,
    		showSeconds: false
		});
		$('#timedatespan').show();
		}
	}  
 	  function jump(_sUrl){
		$('body').layout('panel','center').panel('refresh',_sUrl);
	}
	function searchDate(_url){
		var limitTime = $('#timeselect').combobox('getValue');
		var _asseroomid = $('#asseroomid').combobox('getValue');
		var date_start_time=$('#starttime').datebox('getValue');
		var date_end_time=$('#endtime').datebox('getValue'); 
		var browerStartTime='${startTime}';
		var browerEndTime='${endTime}';
		if(limitTime=="otherdate"){
			if(date_start_time>=date_end_time){
				$.messager.alert("提示","开始时间不能大于结束时间");
				return false;
			}
		}
		jump("<%=rootPath %>asseroomapply/roomdate.action?timeLimit="+limitTime+"&searchWord="+_asseroomid+"&start="+date_start_time+"&end="+date_end_time);
	} 
	</script>
<input type="hidden" id="start" name="asseroomids" value='<s:property value="start"/>'/>
<input type="hidden" id="end" name="asseroomids" value='<s:property value="end"/>'/>
<div style="padding-left: 10px;">
	<select  id="asseroomid">
	<option value="all" selected="selected">所有会议室</option>
	<s:iterator value="asseroomList" status="asseroomList">
	<option value="<s:property value='asseroomid'/>" <s:if test="asseroomid==searchWord">selected</s:if>><s:property value='asseroomname'/></option>
		</s:iterator>
	</select>
	&nbsp;&nbsp;&nbsp;&nbsp;
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

</span>		<a id="log_search"  class="easyui-linkbutton l-btn" onclick="searchDate('<%=rootPath %>aseroomapply/roomdate.action')" data-options="iconCls:'icon-search'" href="javascript:void(0);">
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
