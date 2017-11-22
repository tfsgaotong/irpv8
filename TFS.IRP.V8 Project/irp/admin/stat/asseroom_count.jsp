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
<title>会议室统计图</title>


</head>
<body>
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
  		initTime();
	  $('#container').highcharts({
          chart: {
              type: 'column'
          },
          title: {
              text: '会议室统计'
          },
          subtitle: {
              text: ''
          },
          xAxis: {
              categories:[<s:property value="roomnamejson" escapeHtml="false" />]
          },
          yAxis: {
              min: 0,
              title: {
              text: '会议室使用次数'
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
              name: '会议室使用次数',
              data: [<s:property value="roomcountjson" />]
  
          }]
      }); 
	  
  });
  /**
  *分页
  */
  function page(_nPageNum){
  	$('#pageNum').val(_nPageNum);
  	var queryString = $('#pageForm').serialize();
  	jump("<%=rootPath%>stat/statuserscoreexperience.action?"+queryString);
  }
  function jump(_sUrl){
		$('body').layout('panel','center').panel('refresh',_sUrl);
	}
  /**
  *排序
  */
  function orderBy(ordername){
	    $('#orderField').val(ordername);
		var queryString = $('#pageForm').serialize();
	  	jump("<%=rootPath%>stat/statuserscoreexperience.action?"+queryString);
	  return false;
  }
  //关于时间
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
  	 			$('#start_time').datetimebox();
  	 			$('#end_time').datetimebox();
  	 			$('#starttime').datetimebox('setValue',testDate.format("yyyy-MM-dd hh:mm"));
  	 			$('#endtime').datetimebox('setValue',testDate.format("yyyy-MM-dd hh:mm"));
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
		$('#endtime').datetimebox({
			 value: endDate.format("yyyy-MM-dd hh:mm"),
			 required: true,
    		 showSeconds: false
		});
		$('#starttime').datetimebox({
		 	value: startDate.format("yyyy-MM-dd hh:mm"),
		 	required: true,
    		showSeconds: false
		});
		$('#timedatespan').show();
	
	}
}  
	function searchDate(_url){
		var limitTime = $('#timeselect').combobox('getValue');
		var date_start_time=$('#starttime').datetimebox('getValue');
		var date_end_time=$('#endtime').datetimebox('getValue'); 
		var browerStartTime='${startTime}';
		var browerEndTime='${endTime}';
		var _asseroomid=$("#asseroomids").val();
		if(limitTime=="otherdate"){
			if(date_start_time>=date_end_time){
				$.messager.alert("提示","开始时间不能大于结束时间");
				return false;
			}
		}
		jump("<%=rootPath%>asseroomapply/roomcount.action?pageNum=1&orderField&timeLimit="+limitTime+"&asseroomids="+_asseroomid+"&start="+date_start_time+"&end="+date_end_time);
	}
		
</script>
<form id="pageForm">
<s:hidden name="pageNum" id="pageNum" />
<s:hidden name="pageSize" id="pageSize" />
<s:hidden name="orderField" id="orderField" />
<s:hidden name="timeLimit" />
<s:hidden name="startTime" />
<s:hidden name="endTime" />
</form>
<input type="hidden" id="asseroomids" name="asseroomids" value='<s:property value="asseroomids"/>'/>
<input type="hidden" id="start" name="asseroomids" value='<s:property value="start"/>'/>
<input type="hidden" id="end" name="asseroomids" value='<s:property value="end"/>'/>
<div>
<div style="padding-left: 10px;">
<select id="timeselect">
	<option value="thismonth" id="thismonth"  <s:if test="timeLimit=='thismonth'">selected="selected"</s:if>>本月</option>
	<option value="thisweek" id="thisweek" <s:if test="timeLimit=='thisweek'">selected="selected"</s:if>>本周</option>
	<option value="thisquarter" id="thisquarter" <s:if test="timeLimit=='thisquarter'">selected="selected"</s:if>>本季</option>
	<option value="otherdate" id="otherdate" <s:if test="timeLimit=='otherdate'">selected="selected"</s:if>>指定</option>
</select>
<span id="timedatespan" style="display: none;">
		开始日期
		<input type="text" id="starttime" class="easyui-datetimebox" data-options="required:true,showSeconds:false" editable="false" validType="StartTime" style="width:150px">&nbsp;&nbsp; 
		结束日期
		<input type="text" id="endtime"  class="easyui-datetimebox" data-options="required:true,showSeconds:false" editable="false" validType="EndTime" style="width:150px">
</span>		<a id="log_search"  class="easyui-linkbutton l-btn" onclick="searchDate('<%=rootPath %>asseroomapply/roomcount.action')" data-options="iconCls:'icon-search'" href="javascript:void(0);">
		查询</a>
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