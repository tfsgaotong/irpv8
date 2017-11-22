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
<title>部门请假统计图</title>


</head>
<body>
<script type="text/javascript" src="js/log/log-format-time.js"></script>
<script type="text/javascript">

  $(function(){  InitTime();  });
  function InitTime(){
	 
	  $('#container').highcharts({
          chart: {
              type: 'column'
          },
          title: {
              text: '各部门请假统计'
          },
          subtitle: {
              text: ''
          },
          xAxis: {
              categories:[<s:property value="groupname" escapeHtml="false" />]
          },
          yAxis: {
              min: 0,
              title: {
                  text: '用户请假数量'
              }
          },
          tooltip: {
              headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
              
              pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                  '<td style="padding:0"><b>{point.y:.0f} </b></td></tr>',
              footerFormat: '</table>',
              shared: true,
              useHTML: true
          },
          plotOptions: {
              column: {
                  pointPadding: 0.2,
                  borderWidth: 0
              }
          },
          series: [{
              name: '次数',
              data: [<s:property value="worktimeNum" />]
  
          }, {
              name: '天数',
              data: [<s:property value="totalTime" />]
  
          }]
      }); 
  }


  function jump(_sUrl){
		$('body').layout('panel','center').panel('refresh',_sUrl);
	} 
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
  	 $('#contributeranking').panel('refresh',"<%=rootPath %>leave/departmentStatic.action?type=1&groupIds=1&orderField&c_start_end="+start_end+"&starttime="+date_start_time+"&endtime="+date_end_time);
  		
  	  
  }
  
  function caizhi(){
	  
	  var ss =$('#groupstext').val();
	  
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
 	 $('#contributeranking').panel('refresh',"<%=rootPath %>leave/departmentStatic.action?type=2&orderField&c_start_end="+start_end+"&starttime="+date_start_time+"&endtime="+date_end_time+"&groupIds="+ss);
  }
  function page(_pageNum){
	$("#pageNum").val(_pageNum);
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
	 $('#contributeranking').panel('refresh',"<%=rootPath %>leave/departmentStatic.action?type=1&pageNum="+_pageNum+"&orderField&c_start_end="+start_end+"&marking="+marking+"&starttime="+date_start_time+"&endtime="+date_end_time);
	  
  }
</script>
<form id="pageForm">
<s:hidden name="pageNum" id="pageNum" />
<s:hidden name="pageSize" id="pageSize" />
<s:hidden name="orderField" id="orderField" />
</form>
<div>
<div style="padding-left: 10px;">
<form id="check_logs" method="post">
				<input type="text" name="marking" id="marking" value="10" style="display:none"/>
							<input type="hidden" id="groupstext" value='<s:property value='groupIds'/>' />
					
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
					&nbsp;&nbsp;&nbsp; 
					 <a href="javascript:void(0)" id="log_search"
						class="easyui-linkbutton" data-options="iconCls:'icon-search'"
						onclick="log_CheckSearch()">检索</a>
				</form>
</div>
<div>
<input type="hidden" id="groupstext" value='<s:property value='groupIds'/>' />
<img border="0" src="images/icons/bttj.png" title="饼图" style="cursor:pointer; margin: 0 5px;" onclick="caizhi()"/>
</div>
<div id="container"></div>
<table width="100%" border="0" align="center" cellpadding="10" >
 <tr >
   <td  align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
  </tr>
</table>
</div>
</body>
</html>