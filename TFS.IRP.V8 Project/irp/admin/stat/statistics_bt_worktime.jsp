<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>     
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	String ischoose = request.getParameter("ischoose");
%>        
<title>部门统计图</title>
<body>

<script type="text/javascript">
$(function(){ 	
	hotKey();
	initTime();
});
function initTime(){
	$('#timeselect').combobox({
		panelHeight:'160',
		width:'50',
		editable:false,
		onSelect:function(){
			var  start_end = $(this).combobox('getValue');
			if(start_end!="logs_appoint_date"){ 
				$('#timedatespan').hide(); 
			}else{
				 var date=new Date(<%=new java.util.Date().getTime()%>); 
				 $('#starttime').datebox('setValue',date.getFullYear()+"-"+parseInt(date.getMonth()+1)+"-"+date.getDate());
				 $('#endtime').datebox('setValue',date.getFullYear()+"-"+parseInt(date.getMonth()+1)+"-"+date.getDate());
				 $('#timedatespan').show();
			}
		}
	 }); 
	 $('#_Irp_type_oper').combobox({
	 	 panelHeight:'120',
	 	editable:false
	  });
	var  start_end = $('#timeselect').combobox('getValue');
	if(start_end=="logs_appoint_date"){
		 showtimedatediv('${starttime1}','${endtime1}');
	}
}  

function searchDate(_url){
	var limitTime = $('#timeselect').combobox('getValue');
	var date_start_time=$('#starttime').datebox('getValue');
	var date_end_time=$('#endtime').datebox('getValue'); 
	var browerStartTime='${starttime1}';
	var browerEndTime='${endtime1}';
	var type = $('#_Irp_type_oper').combobox('getValue');
	toPage1(_url,limitTime,date_start_time,date_end_time,browerStartTime,browerEndTime,type);
} 
function toPage1(_url,limitTime,date_start_time,date_end_time,browerStartTime,browerEndTime,type){
	if(limitTime=="logs_appoint_date"){
		if(date_start_time==""){
			date_start_time=new Date(browerStartTime);
			var  year=date_start_time.getFullYear();
			var month=parseInt(date_start_time.getMonth()+1);
			var day=date_start_time.getDate();
			date_start_time=year+"-"+month+"-"+day;
		}
		if(date_end_time==""){
			date_end_time=new Date(browerEndTime);
			var  year=date_end_time.getFullYear();
			var month=parseInt(date_end_time.getMonth()+1);
			var day=date_end_time.getDate();
			date_end_time=year+"-"+month+"-"+day;
		} 
		if(compareTime(date_start_time,date_end_time)){
			if(type!=0){
			if(<%=ischoose %>==1){
			jump(_url+'starttime='+date_start_time+'&endtime='+date_end_time+'&c_start_end='+limitTime+'&applytypeid='+type);
			}else{
			$('#department').panel('refresh',_url+'starttime='+date_start_time+'&endtime='+date_end_time+'&c_start_end='+limitTime+'&applytypeid='+type);
			}
				}else{
				if(<%=ischoose %>==1){
				jump(_url+'starttime='+date_start_time+'&endtime='+date_end_time+'&c_start_end='+limitTime);
				}else{
				$('#department').panel('refresh',_url+'starttime='+date_start_time+'&endtime='+date_end_time+'&c_start_end='+limitTime);
				}				
			}					
		}else{
			$.messager.alert("操作提示","结束日期必须在开始日期之后");
		}
	}else{ 
		if(type!=0){
		if(<%=ischoose %>==1){
		jump(_url+'c_start_end='+limitTime+'&applytypeid='+type);
		}else{
		$('#department').panel('refresh',_url+'c_start_end='+limitTime+'&applytypeid='+type);
		}		
		}else{
		if(<%=ischoose %>==1){
		jump(_url+'c_start_end='+limitTime);
		}else{
		$('#department').panel('refresh',_url+'c_start_end='+limitTime);
		}
				
		}		
	}
}
function hotKey(){
	 new Highcharts.Chart({
		 chart: { 
				renderTo: 'container', //画布所在的div 
				plotBackgroundColor: null,
				plotBorderWidth: null,
			 	plotShadow: false ,
			 	events:{
					load:function(){
						var amountStr="<s:property value='bttext'/>;";
						if(amountStr!=null && amountStr.length>0){
							var data = []; 
							var series = this.series[0]; 
							var arrays=amountStr.split("-");
							for(var i=0;i<arrays.length;i++){
								var  str=arrays[i];
								var datas= decodeURI(str);
								var options=datas.split(",");
								var name=options[0];
								var value=window.parseInt(options[1]);
								data.push([name, value]);
							}
							series.setData(data); 
						}
					}
				} 
		},
		title:{text:'部门加班人数统计图'},
		tooltip:{
			 pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		},
		plotOptions:{
			pie:{
				allowPointSelect: true,
				cursor: 'pointer', 
				size:'80%', //圆形的直径大小比例
				 dataLabels: {  
	                    enabled: true,  
	                    color: '#000000',
	                    connectorColor: '#000000',  
	                    formatter: function() {  
	                        return '<b>'+ this.point.name +'</b>: '+ Highcharts.numberFormat(this.percentage, 1) +'% ('+this.y+' 人)';
	                    }  
	                }  ,
				showInLegend: false //不显示名称在下面
			}
		},
		series: [
		         {
		        	 type: 'pie',
		        	 name: '加班人数百分比' 
		         }]
		
			 	
			 	
		 
	 });
}

function zhuxing(){
	 var ss =$('#groupstext').val(); 
	   var ischoose = $('#ischoose').val();
	  var url;
	  if(ischoose==1){
	  url="<%=rootPath %>leave/deptongji.action?type=1&groupIds="+ss+"&ischoose=1&";
	  }else{
	 url="<%=rootPath %>leave/deptongji.action?type=1&groupIds="+ss+"&";
	  }
	   searchDate(url);
}
</script>  
<div style="padding-left: 10px;">
<input type="hidden" id="ischoose" value="<%=ischoose %>"/>
<div style="padding-left: 10px;">
<select id="timeselect">
	<option value="logs_month" id="logs_month"  <s:if test="c_start_end=='logs_month'">selected="selected"</s:if>>本月</option>
	<option value="logs_week" id="logs_week" <s:if test="c_start_end=='logs_week'">selected="selected"</s:if>>本周</option>
	<option value="logs_quarter" id="logs_quarter" <s:if test="c_start_end=='logs_quarter'">selected="selected"</s:if>>本季</option>
	<option value="logs_appoint_date" id="logs_appoint_date" <s:if test="c_start_end=='logs_appoint_date'">selected="selected"</s:if>>指定</option>
</select>
<select id="_Irp_type_oper" name="c_v_Irp_type_oper" >
						<option value="0">加班类型</option>						 
						 <s:iterator id="eleq" value="irpLeaveconfigs" status="status">
							<option value="<s:property value="#eleq.leaveconfigid" />" <s:if test="#eleq.leaveconfigid==applytypeid">selected="selected"</s:if>><s:property value="#eleq.leaveconfigdesc" /></option>
						</s:iterator>
</select>
<span id="timedatespan" style="display: none;">
		开始日期
		<input type="text" id="starttime" class="easyui-datebox" editable="false"  />&nbsp;&nbsp;
		结束日期
		<input type="text" id="endtime" class="easyui-datebox" editable="false"  />

</span>		<a id="log_search"  class="easyui-linkbutton l-btn" onclick="searchDate('<%=rootPath %>leave/deptongji.action?groupIds=1&type=2&')" data-options="iconCls:'icon-search'" href="javascript:void(0);">
		查询</a>
</div>
<input type="hidden" id="groupstext" value='<s:property value='groupIds'/>' />
<img border="0" src="images/icons/zztj.png" title="柱状" style="cursor:pointer; margin: 0 5px;" onclick="zhuxing()"/>
</div>
<div id="container"></div>
<div id="time"></div>
</body>
</html>
