<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>     
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>        
<title>个人具体统计</title>
<body>
<script type="text/javascript" src="js/log/log-format-time.js"></script>
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
 	personValueExpersince(step);
	initSize();
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
function personValueExpersince(step){
	var norJsonString='<s:property value="norJsonString"/>';
	var unorJsonString='<s:property value="unorJsonString"/>';
	var nors=norJsonString.split(",");
	var unnors=unorJsonString.split(",");
	var sumnor=window.parseFloat(nors[0]);
	var sumunnor=0;
	var normal=nors[0];
	var late = unnors[0];
	var early = unnors[1];
	var signAgain = unnors[2];
	for(var i=0 ; i<nors.length ; i++){
		sumunnor=sumunnor+window.parseFloat(unnors[i]);
	}
	$('#container').highcharts({
		chart:{},
		title:{
			text:'个人具体统计趋势图'
		},
		xAxis:{
			categories:<s:property value="xaxisJasonString"/>,
			tickInterval:step,
			labels:{
				rotation:-90,
				y:45,
				formatter:function(){
					var timelimit=$('#timeselect').combobox('getValue');
					if(timelimit=="thisweek"){
				             return "<b>星期  </b>"+this.value;
					}else{
						return this.value;
					} 
				}
			}
		},
		yAxis:{
			min:-1
		}, 
		tooltip:{
			formatter:function(){
				var s; 
				if (this.point.name) { // the pie chart饼图显示
					s =this.point.name +'所占比例: '+ Highcharts.numberFormat(this.percentage, 1)+'% ('+this.y+'个)'; 
				} else { ///折线图显示
					var timelimit=$('#timeselect').combobox('getValue');
					if(timelimit=="thisweek"){
						s = this.series.name+':<br>时间：星期<b>'+ this.x +'</b>: <br>总数:<b>'+ this.y+'</b>'; 
					}else{
						s = this.series.name+':<br>时间：<b>'+ this.x +'</b>: <br>总数:<b>'+ this.y+'</b>'; 
					}
					
				} 
				return s; 
			}
		},
        plotOptions: {
            spline: {
                marker: {
                    radius:0,
                    lineColor: '#666666',
                    lineWidth: 1
                },
                animation:false
            }
        },
		series:[
				{type:'spline',name:'迟到',data:<s:property value="latejson"/>},
				{type:'spline',name:'早退',data:<s:property value="earlyjson"/>},
				{type:'spline',name:'补签',data:<s:property value="signAgainjson"/>},
				{type:'spline',name:'正常',data:<s:property value="amountJsonString"/>},
				{
					type:'pie',
					name:'具体分类',
					data:[ { name: '正常', y: window.parseFloat(normal),color:'#f28f43'}, 
					       { name: '迟到', y: window.parseFloat(late),color:'#3D96AE'},  
							{ name: '早退', y: window.parseFloat(early),color:'#910000'}, 
							{  name: '补签', y: window.parseFloat(signAgain),color:'#a6c96a'}],
					center:[40,20],//中心坐标
					size:120,
					showInLegend: false,
					dataLabels: {enabled: true,
						formatter: function(){
							return this.point.name +'所占比例: '+ Highcharts.numberFormat(this.percentage, 1)+'% ('+this.y+'个)'; 
						}	
					} 
				},{
					type:'pie',
					name:'大分类',
					data:[{ name: '正常', y: sumnor}, 
						  {name: '非正常', y: sumunnor}],
					center:[40,20],//中心坐标
					size:80,
					showInLegend: false,
					dataLabels: {  
						 distance: -20 ,
						 color:'white' 
					} 
				}
		]
	});
}

//选择用户	
function checkUser(){
	var dialogdiv=document.createElement("div");
  	dialogdiv.id="selectUser";
  	document.body.appendChild(dialogdiv);
  	var queryString = '';
  	var userId = $('#userId').val();
  	if(userId.length>0 && !isNaN(userId)){
  		queryString = "?iid="+userId;
  	}
  	$('#selectUser').dialog({
  		modal:true,
  		href:'<%=rootPath%>user/select_user_list.action'+queryString,
  		height:520,
  		width:650,
  		title:'选择用户',
  		resizable:true,
  		cache:false,
  		buttons:[{
  			text:'提交',
  			iconCls: 'icon-ok',
  			handler:function(){
  				var userInfo = getSelectUserInfo();
  				if(userInfo){
  	  				$('#userId').val(userInfo.userid);
  	  				$('#userName').html(userInfo.truename);
  				}
			 	$('#selectUser').dialog('destroy');
  			}
  		},{
  			text:'取消',
  			iconCls:'icon-cancel',
  			handler:function(){
  				$('#selectUser').dialog('destroy');
  			}
  		}],
  		onClose:function(){
  			$('#selectUser').dialog('destroy');
  		}
  		
  	});
}
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
function searchDate(_url){
	var iid = $('#userId').val();
	var limitTime = $('#timeselect').combobox('getValue');
	var date_start_time=$('#starttime').datebox('getValue');
	var date_end_time=$('#endtime').datebox('getValue'); 
	var browerStartTime='${startTime}';
	var browerEndTime='${endTime}';
	toPage(_url,limitTime,date_start_time,date_end_time,browerStartTime,browerEndTime,iid);
} 

function toPage(_url,limitTime,date_start_time,date_end_time,browerStartTime,browerEndTime,iid){
	if(limitTime=="otherdate"){
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
			jump(_url+'?iid='+iid+'&startTime='+date_start_time+'&endTime='+date_end_time+'&timeLimit='+limitTime);	
		}else{
			$.messager.alert("操作提示","结束日期必须在开始日期之后");
		}
	}else{ 
		jump(_url+'?timeLimit='+limitTime);	
	}
}

function showOption(size){
	 var options=$('#container').highcharts().options;
	 options.plotOptions.spline.marker.radius=size;
	 options.plotOptions.spline.marker.states.hover.radius=parseInt(size)+2;
	 $('#container').html('');
	 options.exporting.buttons.contextButton.enable=true;
	 new Highcharts.Chart(options);
}
</script>  
<div style="padding-left: 10px;">
<input type="hidden" id="userId" value="<s:property value='userId'/>" readonly="readonly" />	 
    用户：<a href="javascript:void(0);" id="userName" onclick="checkUser()"><s:property value='userName'/></a>&nbsp;
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

</span>	
<a id="log_search"  class="easyui-linkbutton l-btn" onclick="searchDate('<%=rootPath %>sign/signPersonSpecific.action')" data-options="iconCls:'icon-search'" href="javascript:void(0);">
		查询</a>
		<span>
			<select id="sizeselect">
				<s:iterator begin="1" end="9" status="status">
				<option value="<s:property value='#status.count-1'/>"><s:property value='#status.count-1'/>px</option> 
				</s:iterator>
			</select>
		</span>
</div>
<div> 
<div id="container1"></div>
<div id="container"></div>
</div>
</body>
</html>
