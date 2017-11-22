<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="/struts-tags" prefix="s" %>     
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
String ischoose = request.getParameter("ischoose");
%>       
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部门统计图</title>


</head>
<body>
<script type="text/javascript">
function initTime(){
	$('#timeselect').combobox({
		panelHeight:'130',
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
	 	 panelHeight:'130',
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
			jump(_url+'starttime='+date_start_time+'&endtime='+date_end_time+'&c_start_end='+limitTime+'&applytypeid='+type)
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
  $(function(){  
  initTime();
	  $('#container').highcharts({
          chart: {
              type: 'column'
          },
          title: {
              text: '部门加班统计图'
          },
          subtitle: {
              text: ''
          },
          xAxis: {
              categories:[<s:property value="groupname" escapeHtml="false" />]
          },
          yAxis: {
        	  labels: { 
                  min:0, 
                  style: { 
                      color: '#89A54E' //设置标签颜色 
                  } 
              }, 
              title: {
                  text: '加班人数/时间'
              }
          },
          tooltip: {
              headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
              
              pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                  '<td style="padding:0"><b>{point.y:0f} </b></td></tr>',
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
          series: [ {
              name: '加班人数',
              data: [<s:property value="worktimeNum"/>]
  
          },{
              name: '加班总时长',
              data: [<s:property value="totalTime"/>]
  
          }
          ]
      }); 
	  
  });
  /**
  *分页
  */
  function page(_nPageNum){
  	$('#pageNum').val(_nPageNum);
  	var queryString = $('#pageForm').serialize();
 
  }
  function caizhi(){
	  var ss =$('#groupstext').val();
	  var ischoose = $('#ischoose').val();
	  var url;
	  if(ischoose==1){
	  url="<%=rootPath %>leave/deptongji.action?type=2&groupIds="+ss+"&ischoose=1&";
	  }else{
	  url="<%=rootPath %>leave/deptongji.action?type=2&groupIds="+ss+"&";
	  }
	  
	  searchDate(url);
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
</script>
<form id="pageForm">
<s:hidden name="pageNum" id="pageNum" />
<s:hidden name="pageSize" id="pageSize" />
<s:hidden name="orderField" id="orderField" />
</form>
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
						 <s:iterator var="eleq" value="irpLeaveconfigs" status="status">
							<option value="<s:property value="#eleq.leaveconfigid" />" <s:if test="#eleq.leaveconfigid==applytypeid">selected="selected"</s:if>><s:property value="#eleq.leaveconfigdesc" /></option>
						</s:iterator>
</select>
<span id="timedatespan" style="display: none;">
		开始日期
		<input type="text" id="starttime" class="easyui-datebox" editable="false"  />&nbsp;&nbsp;
		结束日期
		<input type="text" id="endtime" class="easyui-datebox" editable="false"  />

</span>		<a id="log_search"  class="easyui-linkbutton l-btn" onclick="searchDate('<%=rootPath %>leave/deptongji.action?groupIds=1&type=1&')" data-options="iconCls:'icon-search'" href="javascript:void(0);">
		查询</a>
</div>
<div>
<div>
<input type="hidden" id="groupstext" value='<s:property value='groupIds'/>' />
<img border="0" src="images/icons/bttj.png" title="饼图" style="cursor:pointer; margin: 0 5px;" onclick="caizhi()"/>
</div>
<div id="container"></div>

</div>
</body>
</html>