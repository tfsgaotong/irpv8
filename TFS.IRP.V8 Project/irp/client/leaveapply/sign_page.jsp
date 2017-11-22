<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>请假列表</title>
<!-- Jquery and Jquery UI -->
<link rel="stylesheet" href="<%=rootPath%>view/css/fullCalendar/css/redmond/jquery-ui-1.8.1.custom.css" type="text/css"></link>
<link rel="stylesheet" href="<%=rootPath%>view/js/jqueryui/css/jquery-ui-timepicker-addon.css" type="text/css"></link>
<script type="text/javascript" src="<%=rootPath%>view/js/fullCalendar/js/jquery-1.4.2.min.js"></script>
<!-- dialog弹框 -->
<script type="text/javascript" src="<%=rootPath %>view/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/fullCalendar/js/jquery-ui-1.8.6.custom.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/fullCalendar/js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/jqueryui/jquery-ui-timepicker-zh-CN.js"></script>
<script type="text/javascript" src="<%=rootPath%>admin/js/log/log-format-time.js"></script>
<style type="text/css">


.main_bleft{
	line-height:29px;
	padding-right:10px;
	font-weight:;
	letter-spacing:1px;
	color:#333;
}
.main_bright{
	padding-left:5px;
	padding-right:5px;
	line-height:29px;
	word-break:break-all;
	word-wrap:break-word;
}
.main_bright textarea{
	height: 24px;
	margin-top: 3px;
	margin-bottom: 3px;
}
form td{ 
	font-size: 13px;
	height:40px;
} 

</style>


<style type="text/css">


.left .fyh{width:950px;margin:0px auto 10px 15px; clear:both;}
.left .fyh ul{width:886px;margin:10px auto 20px auto; display:block; text-align:right;}
.left .fyh ul span{color:#999; padding:2px 8px; border:1px solid #dbdbdb;}
.left .fyh ul a {color:#999; padding:2px 8px; border:1px solid #dbdbdb;}
.left .fyh ul a:hover{color:#333; padding:2px 8px; border:1px solid #c8c8c8;background:#ededed;}
.namecomm {white-space: nowrap;overflow: hidden;text-overflow: ellipsis;}
#signTable tr td {border: #eaeaea solid 1px;}
#page div input {margin-left: 5px;margin-right: 5px;}
.sign {cursor:pointer;width:132px;height:34px;background:url(<%=rootPath%>client/images/signPic.JPG) -168px -109px no-repeat;font-size:24px;line-height: 20px;text-align: center;}
.sign-in-al {cursor:pointer;width:132px;height:34px;background:url(<%=rootPath%>client/images/signPic.JPG) -13px -357px no-repeat;font-size:24px;text-align: center;line-height: 34px;}
.sign-change {cursor:pointer;width:132px;height:34px;background:url(<%=rootPath%>client/images/signPic.JPG) -168px -205px no-repeat;font-size:24px;text-align: center;}
.search {height: 27px;;background: url(<%=rootPath %>/client/images/searchbox_button.png) 0px 8px no-repeat ;}
.searchdate {background:url(<%=rootPath%>/client/images/rili.png) -2px 0px no-repeat;}

.tabcheck{
border-bottom: 2px solid #38f;
}

</style>


</head>




<body style="background: url()">
<form id="pageForm">
	<s:hidden name="pageNum" id="pageNum" />
	<s:hidden name="pageSize" id="pageSize" value="10"/>
	<s:hidden name="pagestatus" id="pagestatus" value="0"/>
</form>
<div style="width: 980px;margin-bottom: 2px;text-align: right;" >
	
    <table width="100%"  border="0" align="center" cellpadding="1" cellspacing="1" >
	<tr class="list_th" style="position: relative;">
   	  	<td colspan="4" align="left" style="padding-left: 10px;" class="tableave" nowrap="nowrap">
   	   <a href="javascript:void(0)" class="tabcheck" onclick="listbystatus(this,0)">全部</a>&nbsp;&nbsp;&nbsp;&nbsp;
   	   <a href="javascript:void(0)"  onclick="listbystatus(this,20)">未审核</a>&nbsp;&nbsp;&nbsp;&nbsp;
   	   <a href="javascript:void(0)"   onclick="listbystatus(this,10)">已同意</a> &nbsp;&nbsp;&nbsp;&nbsp;
   	   <a href="javascript:void(0)"   onclick="listbystatus(this,30)">已拒绝</a>
   	  
   	  	</td>
   	  	<td colspan="3" align="right" style="padding-right: 2px;"><a href="javascript:void(0)"   onclick="queryCheck()">查询</a>&nbsp;&nbsp;&nbsp;<input name="signinoper" class="sign" type="button" onclick="addleave()" value="请&nbsp;&nbsp;&nbsp;假" id="inputin" /></td>
	</tr>
	<tr class="list_th" id="query" style="position: relative;display:none;">
   	  	<td colspan="8" align="left" style="padding-left: 10px;">
				<form id="check_logs" method="post">
				<input type="text" name="marking" id="marking" value="10" style="display:none"/>
					申请状态 :<select id="_log_type" name="c_v_log_type">
						<option value="0" selected="selected">全部</option>
						<option value="10">已同意</option>
						<option value="20">未审核</option>
						<option value="30">已拒绝</option>
						
					</select>
					
					&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;
				  紧急程度:<select id="_emergeny" name="_emergeny">
						<option value="0" selected="selected">全部</option>
						<option value="10">正常</option>
						<option value="20">重要</option>
						<option value="30">紧急</option>
						
					</select>
					
					&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;

					  请假类型:<select id="_Irp_type_oper" name="c_v_Irp_type_oper">
						<option value="0">全部</option>						 
						 <s:iterator var="eleq" value="irpLeaveconfigs" status="status">
							<option value="<s:property value="#eleq.leaveconfigid" />"><s:property value="#eleq.leaveconfigdesc" /></option>
						</s:iterator>
					</select>
					&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;
					 请选择时间段:<select id="Irp_logtime" name="c_start_end"
						onchange="ck_date()">
						<option value="logs_week" id="_logs_week">本周</option>
						<option value="logs_month" id="_logs_month" selected="selected">本月</option>
						<option value="logs_quarter" id="_logs_quarter">本季</option>
						<option value="logs_appoint_date" id="_logs_appoint_date">指定</option>
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
				</form></td>
	</tr>
	</table> 
	
</div>
<div id="page" class="left" style="text-align:center">
	    <div style="clear:both;width: 980px;" >
	    	<s:if test="irpLeaveapplyInfos==null">未找到相关信息</s:if>
	    	<s:else>
	    		<table id="signTable" align="center" style="width: 100%;table-layout: fixed;">
	    				<tr bgcolor="#D7DAF0">
	    					<td width="75px;">序号</td>
	    					<td width="200px;">标题</td>
	    					<td width="100px;">开始时间</td>
	    					<td width="100px;">结束时间</td>
	    					<td width="50px;">紧急程度</td>	    					
	    					<td width="50px;">审核进度</td>	    					
	    					<td width="100px;">审核人</td>
	    					<td width="75px;">操作</td>
	    					
	    				</tr>
		    		<s:iterator var="ele" value="irpLeaveapplyInfos" status="status">
		    			<tr
		    				<s:if test="#status.count%2==0">bgcolor="#F6F6F6"</s:if>
		    			>
		    				<td><s:property value="(pageNum-1)*pageSize+#status.count"/></td>
		    				<td><a href="javascript:void(0)" onClick="detailView(<s:property value="#ele.leaveapplyid"/>)"><s:property value="#ele.title"/></a></td>
		    				<td><s:date name="#ele.starttime" format="yyyy-MM-dd HH:mm"/></td>
		    				<td><s:date name="#ele.endtime" format="yyyy-MM-dd HH:mm"/></td>
		    			
		    				<td><s:property value="#ele.emergency" /></td>
  	    			  						    			
		    				
		    				<td><s:property value="#ele.applystatus" /></td>
		    				<td><s:property value="#ele.checker" /></td>
		    				
		    				
		    				
		    				<td><a onclick="detailView(<s:property value="#ele.leaveapplyid"/>)" href="javascript:">
<img src="<%=rootPath%>client/images/view.gif" style="border:0px; vertical-align:middle; margin-right:5px;" title="查看">

</a>&nbsp;&nbsp;
<s:if test="#ele.applystatus=='未审核'">
<a onclick="deleteleave(<s:property value="#ele.leaveapplyid"/>)" href="javascript:void(0)">
<img src="<%=rootPath%>client/images/deleteicon.jpg" style="border:0px;height:12px;width:12px;  margin-right:5px;" title="撤销">
</a>
&nbsp;&nbsp;<a onclick="updateLeave(<s:property value="#ele.leaveapplyid"/>)" href="javascript:void(0)">
<img src="<%=rootPath%>client/images/editsubject.png" style="border:0px;height:12px;width:12px;  margin-right:5px;" title="修改">
</a></s:if>

</td>
		    				
		    			</tr>
		    		</s:iterator>
		    	</table>
	    	</s:else>
	    </div>
	    <div class="fyh" style="width:980px;"><ul><s:property value="pageHTML" escapeHtml="false"/></ul></div>
	</div>
	<script type="text/javascript">
	 var min=<s:property value="@com.tfs.irp.util.SysConfigUtil@getSysConfigValue('MIN_LEAVETIME')"/>; 
	Date.prototype.Format = function (fmt) {
	var o = {    
	      "M+": this.getMonth() + 1, //月份        
	       "d+": this.getDate(), //日       
	      "H+": this.getHours(), //小时     
	       "m+": this.getMinutes(), //分         
	       "s+": this.getSeconds(), //秒       
	       "q+": Math.floor((this.getMonth() + 3) / 3), //季度         
	        "S": this.getMilliseconds() //毫秒    
	     };
	        if (/(y+)/.test(fmt)) 
	        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));   
	          for (var k in o)    
	            if (new RegExp("(" + k + ")").test(fmt)) 
	            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length))); 
	        return fmt; 
	}   
    $.fn.datebox.defaults.formatter = function(date){
    	var y = date.getFullYear();
    	var m = date.getMonth()+1;
    	var d = date.getDate();
    	return m+'/'+d+'/'+y;
    }
	
	
	$.extend($.fn.validatebox.defaults.rules, {   
	    startTime : {
            validator:function(value){ 
            	
            	var s=$("#nowtime").val();
            	
            var time2 = s.replace(/-/g,"/"); 
           
            var time1 = new Date(value.replace(/-/g,"/"));              
	    		if(time1>=new Date(time2)){	    			    			
	    			return true;
	    		}else{	    			
	    			return false;
	    		}	
	    	},
	    	message:'开始日期必须大于当前日期'
            },
         endTime : {
            validator:function(value){
             var endtime = new Date(value.replace(/-/g,"/"));
            var _starttime = $('#starttime').val().replace(/-/g,"/");
	    		if(new Date(_starttime)<endtime){
	    			return true;
	    		}else{	  
	    			return false;
	    		}	
	    	},
	    	message:'结束日期必须大于开始日期'
        }, 
        minTime:{
            validator:function(value){      
    	        if(value>min){
    	        return true;
    	        }else{
    	        return false;
    	        }
            },
            message:'请假允许最小时间为'+min+'天'
            }          
             
	});
	function deleteleave(_id){
		$.dialog.confirm("您确定要撤销这个请假申请么？",function(){
			 $.ajax({
					url: "<%=rootPath%>leave/deleteleave.action?leaveapplyid="+_id,
				    async: false,
				    cache: false,
				    success:function(msg){
				    	if(msg==1){
				    		$.dialog.tips('撤销申请成功',1,'32X32/succ.png',function(){
        						window.location.href='leave/leaveList.action?type=0';
        						});	
				    					    	}else{
				    					    		
				    					    		$.dialog.tips('加班申请已进入审核流程，不可撤销',1,'32X32/fail.png');	
				    	}
				    	
				    }
				})
			
			
		},function(){});
		
	}
	function updateLeave(_id){
		$.ajax({
			url: "<%=rootPath%>leave/booleanleave.action?leaveapplyid="+_id,
		    async: false,
		    cache: false,
		    success:function(msg){
		    	if(msg==1){
		    		//表单可以修改
		    		//获得内容
		    		var result = $.ajax({
		    			url: '<%=rootPath%>leave/toupdate.action?leaveapplyid='+_id,
		    		    async: false,
		    		    cache: false
		    		}).responseText;
		    		//弹出对话框
		    		$.dialog({
		    			title:'修改请假申请',
		    			content: result ,
		    			top:35,
		    			width:810,
		    			height:500,
		    			max: false,
		    		    min: false,
		    			ok: function(){
		    				$('input[name="jsonFile"]').val(JSON.stringify(_allattacheds));
		    				
		    				$.dialog.confirm("您确定要修改这个请假申请么？",function(){
		    				var validate = $('#appaddform').form('validate');
		    				/* 执行提交操作表单 */
		    				$('#appaddform').form('submit', {   
		    				    url:"<%=rootPath%>leave/doupdateleave.action",   
		    				   
		    				    success:function(data){   
		    				    	$.messager.progress('close');
		    				        if(data=='1'){
		    				        	$("#typeadddiv").dialog("close");  
		    				         	$.dialog.tips('修改请假申请成功',1,'32X32/succ.png',function(){
		        						window.location.href='leave/leaveList.action?type=0';
		        						});	
		    				        }else{
		    				        	$.dialog.tips('修改请假申请失败',1,'32X32/fail.png',function(){
		        						window.location.href='leave/leaveList.action?type=0';
		        						});	


		    				        }  
		    				    }   
		    				}); 
		    				},function(){});
		    				return validate;
		    				
		    		    },
		    		    okVal:'确定',
		    		    cancelVal: '取消',
		    		    cancel: function(){
		    		    	$("#typeadddiv").dialog("close");
		    		    },
		    		    lock: true,
		    		    padding: 0
		    		});
		    	
		    		
		    		
		    	}else{
		    		$.dialog.tips('该表单已经在审核，不能修改了',1,'32X32/fail.png');	

		    	}
		    	
		    }
		})
	
	
		
		
	}
function addleave(){	
		//获得内容
		var result = $.ajax({
			url: '<%=rootPath%>leave/addleaveview.action',
		    async: false,
		    cache: false
		}).responseText;
		//弹出对话框
		$.dialog({
			title:'请假申请',
			content: result ,
			top:35,
			width:810,
			height:500,
			max: false,
		    min: false,
			ok: function(){
				$('input[name="jsonFile"]').val(JSON.stringify(addJsonFileList));
				$.dialog.confirm("您确定要提交这个请假申请么？",function(){
				var validate = $('#appaddform').form('validate');
				/* 执行提交操作表单 */
				$('#appaddform').form('submit', {   
				    url:"<%=rootPath%>leave/saveleave.action",   
				   
				    success:function(data){   
				    	$.messager.progress('close');
				        if(data=='1'){
				        	$("#typeadddiv").dialog("close");  
				         	$.dialog.tips('请假申请成功',1,'32X32/succ.png',function(){
    						window.location.href='leave/leaveList.action?type=0';
    						});	
				        }else{
				        	$.dialog.tips('请假申请失败',1,'32X32/fail.png',function(){
    						window.location.href='leave/leaveList.action?type=0';
    						});	


				        }  
				    }   
				}); 
				},function(){});
				return validate;
				
		    },
		    okVal:'确定',
		    cancelVal: '取消',
		    cancel: function(){
		    	$("#typeadddiv").dialog("close");
		    },
		    lock: true,
		    padding: 0
		});
		
		
	
	
	


}

//分页
function page(_nPageNum){
	var status=$("#pagestatus").val();
	$.ajax({
		type:"post",
		url:"<%=rootPath %>leave/leaveList.action?queryType=1&type="+status,
		data:{pageNum:_nPageNum
					
				},
		async:false,
		cache:false,
		success:function(html){
			if(html!=null){
				$('#page').html(html);
			}
		}
	});	
	
	
}


//不同状态跳转
function listbystatus(_this,status){
	$("#query").hide();
	$(".tableave a").removeClass("tabcheck");
	$(_this).addClass("tabcheck");
	$("#pagestatus").val(status);
	
	$.ajax({
		type:"post",
		url:"<%=rootPath %>leave/leaveList.action?queryType=1&type="+status,
				
		async:false,
		cache:false,
		success:function(html){
			if(html!=null){
				$('#page').html(html);
			}
		}
	});	
	
	
}
function queryCheck(){
	var flag=$("#query").is(":hidden");
	if(flag){
		$("#query").show();
	}else{
		$("#query").hide();
		
		
	}
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
      //申请状态
	  $('#_log_type').combobox({
	 	 panelHeight:'120',
	 	 width:'80',
	 	 editable:false
	  });
      //紧急程度
	  $('#_emergeny').combobox({
	 	 panelHeight:'120',
	 	 width:'80',
	 	 editable:false
	  });

     //请假类型
	  $('#_Irp_type_oper').combobox({
	 	 panelHeight:'160',
	 	editable:false
	  });
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
	   var v_Irp_type_oper=$('#_Irp_type_oper').combobox('getValue');
	   var start_end=$('#Irp_logtime').combobox('getValue');	 
	   var v_log_type=$('#_log_type').combobox('getValue');
	   var emergeny=$('#_emergeny').datebox('getValue'); 	
	   
	   
	   if (date_start_time>date_end_time) {
		   $.messager.alert("操作提示","结束日期必须在开始日期之后");
		   return false;
		   }
	  
		$.ajax({
			type:"post",
			url:"<%=rootPath%>leave/leaveListQuery.action",
		   data:{
			   applystatus:v_log_type,
			   starttime:date_start_time,
			   endtime:date_end_time,
			   c_start_end:start_end,
			   emergency:emergeny,
			   applytypeid:v_Irp_type_oper,
			   marking:marking
		   },	
		   
			async:false,
			cache:false,
			success:function(html){
				
				if(html!=null){
					$('#page').html(html);
				}
			}
		});
	  
}
function pageWithCondition(_pageNum){
	
	var date_start_time=$('#start_time').datebox('getValue');   
    var date_end_time=$('#end_time').datebox('getValue'); 		  
    var emergeny=$('#_emergeny').datebox('getValue'); 		  
	   var marking=encodeURIComponent($('#marking').val());
	   var v_Irp_type_oper=$('#_Irp_type_oper').combobox('getValue');
	   var start_end=$('#Irp_logtime').combobox('getValue');	 
	   var v_log_type=$('#_log_type').combobox('getValue');
	$.ajax({
		type:"post",
		url:"<%=rootPath%>leave/leaveListQuery.action",
		async:false,
		cache:false,
		data:{
			   applystatus:v_log_type,
			   starttime:date_start_time,
			   endtime:date_end_time,
			   c_start_end:start_end,
			   applytypeid:v_Irp_type_oper,
			   marking:marking,
			   emergency:emergeny,
			   pageNum:_pageNum
		   },	
		success:function(html){
			if(html!=null){
				$('#page').html(html);
			}
		}
	});	
	
}

function detailView(id){
	
	hrefStr="<%=rootPath%>leave/getleavebyid.action?leaveapplyid="+id;
	window.open(hrefStr);
	
	
}
</script>
</body>
</html>
