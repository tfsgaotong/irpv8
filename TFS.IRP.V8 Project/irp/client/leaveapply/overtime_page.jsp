<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	 java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(  
    	     "yyyy-MM-dd HH:00");  
    	   java.util.Date currentTime = new java.util.Date();  
    	   String time = simpleDateFormat.format(currentTime).toString(); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>加班列表</title>


<!-- Jquery and Jquery UI -->
<link rel="stylesheet" href="<%=rootPath%>view/css/fullCalendar/css/redmond/jquery-ui-1.8.1.custom.css" type="text/css"></link>
<link rel="stylesheet" href="<%=rootPath%>view/js/jqueryui/css/jquery-ui-timepicker-addon.css" type="text/css"></link>
<script type="text/javascript" src="<%=rootPath%>view/js/fullCalendar/js/jquery-1.4.2.min.js"></script>
<!-- dialog弹框 -->
<script type="text/javascript" src="<%=rootPath %>view/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/jquery.easyui.min.js"></script>
<script src="<%=rootPath %>view/js/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/fullCalendar/js/jquery-ui-1.8.6.custom.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/fullCalendar/js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/jqueryui/jquery-ui-timepicker-zh-CN.js"></script>
<script type="text/javascript" src="<%=rootPath%>admin/js/log/log-format-time.js"></script>



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


</style>

</head>


<body style="background: url()">
<form id="pageForm">
	<s:hidden name="pageNum" id="pageNum" />
	<s:hidden name="status" id="status" value="0"/>
	<s:hidden name="pageSize" id="pageSize" value="10"/>
</form>
<div style="width: 980px;margin-bottom: 2px;text-align: right;" >
	
    <table width="100%"  border="0" align="center" cellpadding="1" cellspacing="1" >
	<tr class="list_th" style="position: relative;">
   	  	<td colspan="4" align="left" style="padding-left: 10px;" nowrap="nowrap">
   	   <a href="javascript:void(0)"  onclick="getByStruts(0,this)" id="0" style="border-bottom: 2px solid #38f;">全部</a>&nbsp;&nbsp;&nbsp;&nbsp;
   	   <a href="javascript:void(0)" onclick="getByStruts(20,this)" id="20" style="">未审核</a>&nbsp;&nbsp;&nbsp;&nbsp;
   	   <a href="javascript:void(0)" onclick="getByStruts(10,this)" id="10" style="">已同意</a>&nbsp;&nbsp;&nbsp;&nbsp;
   	   <a href="javascript:void(0)" onclick="getByStruts(30,this)" id="30" style="">已拒绝</a>
   	   <a  id="daochu"></a>
   	  	</td>
   	  	<td colspan="3" align="right" style="padding-right: 2px;"><a href="javascript:void(0)"   onclick="queryCheck()">查询</a>&nbsp;&nbsp;&nbsp;<input name="signinoper" class="sign" type="button" style="border: none;" onclick="addleave()" value="加&nbsp;&nbsp;&nbsp;班" id="inputin" /></td>
	</tr>
	<tr class="list_th" id="query" style="position: relative;display:none;">
   	  	<td colspan="8" align="left" style="padding-left: 10px;">
				<form id="check_logs" method="post">
				<input type="text" name="marking" id="marking" value="20" style="display:none"/>
					申请状态 :<select id="_log_type" name="c_v_log_type">
						<option value="0" selected="selected">全部</option>
						<option value="10">已同意</option>
						<option value="20">未审核</option>
						<option value="30">已拒绝</option>
						
					</select>
					
					&nbsp;&nbsp;&nbsp;
				
				  紧急程度:<select id="_emergeny" name="_emergeny">
						<option value="0" selected="selected">全部</option>
						<option value="10">正常</option>
						<option value="20">重要</option>
						<option value="30">紧急</option>
						
					</select>
					
					&nbsp;&nbsp;&nbsp;
					  请假类型:<select id="_Irp_type_oper" name="c_v_Irp_type_oper">
						<option value="0">全部</option>						 
						 <s:iterator var="eleq" value="irpLeaveconfigs" status="status">
							<option value="<s:property value="#eleq.leaveconfigid" />"><s:property value="#eleq.leaveconfigdesc" /></option>
						</s:iterator>
					</select>
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
					 <a href="javascript:void(0)" id="log_search"
						class="easyui-linkbutton" data-options="iconCls:'icon-search'"
						onclick="log_CheckSearch1()">检索</a>
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
		    				<td><a onclick="detailView(<s:property value="#ele.leaveapplyid"/>)" href="javascript:void(0)">
<img src="<%=rootPath%>client/images/view.gif" style="border:0px;height:12px;width:12px;  margin-right:5px;" title="查看">
</a>&nbsp;&nbsp;
<s:if test="#ele.applystatus=='未审核'">
<a href="javascript:void(0)"  onclick="deleteLeaveapply(<s:property value="#ele.leaveapplyid"/>)">
<img src="<%=rootPath%>client/images/deleteicon.jpg" style="border:0px;height:12px;width:12px;  margin-right:5px;" title="撤销">
</a>&nbsp;&nbsp;
<a href="javascript:void(0)" onclick="upLeaveapply(<s:property value="#ele.leaveapplyid"/>)">
<img src="<%=rootPath%>client/images/editsubject.png" style="border:0px;height:12px;width:12px;  margin-right:5px;" title="修改">
</a>
</s:if>
</td>
		    				
		    			</tr>
		    		</s:iterator>
		    	</table>
	    	</s:else>
	    </div>
	    <div class="fyh" style="width:950px;"><ul><s:property value="pageHTML" escapeHtml="false"/></ul></div>
	</div>
	<script type="text/javascript">
	 var min=<s:property value="@com.tfs.irp.util.SysConfigUtil@getSysConfigValue('MIN_WORKTIME')"/>;  
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
	$.extend($.fn.validatebox.defaults.rules, { 
	    startTime : {
            validator:function(value){ 
            var applytime ="<%=time %>";  
            var time2 = new Date(applytime.replace(/-/g,"/"));   
            var time1 = new Date(value.replace(/-/g,"/"));              
	    		if(time1>=new Date(time2)){	    			    			
	    			return true;
	    		}else{	    			
	    			return false;
	    		}	
	    	},
	    	message:'开始日期必须大于申请日期'
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
	        if(value>=min){
	        return true;
	        }else{
	        return false;
	        }
        },
        message:'加班允许最小时间为'+min+'小时'
        }          
	});
function addleave(){
	var result = $.ajax({
			url: '<%=rootPath%>leave/addovertime.action',
		    async: false,
		    cache: false
		}).responseText;
		//弹出对话框
	$.dialog({
		title:'申请加班',
			content: result ,
			max: false,
		    min: false,
		    top:35,
			ok: function(){
			$('input[name="jsonFile"]').val(JSON.stringify(addJsonFileList));						
			$.dialog.confirm("您确定要提交这个加班申请么？",function(){
			var validate = $('#appaddform').form('validate');
				if(validate){
					/* 执行提交操作表单 */
				$('#appaddform').form('submit', {   
				    url:'<%=rootPath%>leave/saveovertimeinfo.action',   				   
				    success:function(data){   
				    	$.messager.progress('close');
				        if(data=='1'){
				        	$.dialog.tips('加班申请成功',1,'32X32/succ.png',function(){
    						window.location.href='leave/overtime_leaveList.action?type=0';
    						});			    
				        }else{
				        	$.dialog.tips('加班申请失败',1,'32X32/fail.png',function(){
    						window.location.href='leave/overtime_leaveList.action?type=0';
    						});	
				        }  
				    }   
				}); 
				}
				
			},function(){});
				return validate;
		    },
		    okVal:'保存',
		    cancelVal: '取消',
		    cancel: function(){
		    	$("#typeadddiv").dialog("close");
		    },
		    lock: true,
		    padding: 0,
		});
	
}
/**
	 * 分页
	 */
	function page(_nPageNum){
		$('#pageNum').val(_nPageNum);
		var _status=$("#status").val();
		$.ajax({
			type:'post',
			url:'<%=rootPath%>leave/overtime_leaveList.action?nextPage=1&status='+_status,
			data:{
			pageNum:_nPageNum
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
/**
全部、未审和已经审核的加班记录
**/
function getByStruts(_status,_this){
/* $("#query").hide();
$(".tableave a").removeClass("tabcheck");
$(_this).addClass("tabcheck");
$("#pagestatus").val(status); */
var demo ="#"+_status;
var oldstatus = $("#status").val();
if(oldstatus!=_status){
	$("#"+oldstatus).attr("style","");
	$(demo).attr("style","border-bottom: 2px solid #38f;");
}else{
	$(demo).attr("style","border-bottom: 2px solid #38f;");
}

$("#status").val(_status);
$.ajax({
			type:'post',
			url:'<%=rootPath%>leave/overtime_leaveList.action?nextPage=1&status='+_status,
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
//检索
function log_CheckSearch1(){
	var emergeny=$('#_emergeny').datebox('getValue'); 
	   //日期选择one,two
	   var date_start_time=$('#start_time').datebox('getValue');   
    var date_end_time=$('#end_time').datebox('getValue'); 		  
	   var v_log_oper_user=encodeURIComponent($('#log_oper_user').val());
	   var marking=encodeURIComponent($('#marking').val());
	   var v_Irp_type_oper=$('#_Irp_type_oper').combobox('getValue');
	   var start_end=$('#Irp_logtime').combobox('getValue');	 
	   var v_log_type=$('#_log_type').combobox('getValue');
	
	 	   
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
function pageWithCondition(_pageNum){
	
	var date_start_time=$('#start_time').datebox('getValue');   
    var date_end_time=$('#end_time').datebox('getValue'); 		  
	   var v_log_oper_user=encodeURIComponent($('#log_oper_user').val());
	   var marking=encodeURIComponent($('#marking').val());
	   var v_Irp_type_oper=$('#_Irp_type_oper').combobox('getValue');
	   var start_end=$('#Irp_logtime').combobox('getValue');	 
	   var v_log_type=$('#_log_type').combobox('getValue');
	   var emergeny=$('#_emergeny').datebox('getValue'); 
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
			   emergency:emergeny,
			   applytypeid:v_Irp_type_oper,
			   marking:marking,
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
//导出压缩包
function exportToZip(){
	var now=new Date();
	var date=1900+now.getYear()+""+(now.getMonth()+1)+""+now.getDate()+""+now.getHours()+""+now.getMinutes()+""+now.getSeconds();
	var fileName=date;
	document.getElementById("daochu").href="workTime"+fileName+".zip";
	
	var searchName = encodeURI($('#serachText').val());
	var beginTime = $('#starttime').datebox('getValue');
	var finishTime = $('#endtime').datebox('getValue');
	$.ajax({
		type:"post",
		url:"<%=rootPath%>sign/exportToZip.action?searchName="+searchName+"&beginTime="+beginTime+"&finishTime="+finishTime+"&fileName="+fileName,
		async:false,
		success:function(){
			document.getElementById("daochu").click();
		}
        
	});	
	 
	
}
function deleteLeaveapply(_leaveapplyid){
$.ajax({
		type:"post",
		url:"<%=rootPath%>leave/deleteleaveapply.action?leaveapplyid="+_leaveapplyid,
		async:false,
		success:function(data){
			if(data!=0){
			$.dialog.tips('撤销申请成功',1,'32X32/succ.png',function(){
			window.location.reload();
			});	
			}else{
			$.dialog.tips('加班申请已进入审核流程，不可撤销！',1,'32X32/i.png',function(){});	
			}
		}
        
	});	
}
function upLeaveapply(_applyid){
var isok = $.ajax({
			url: '<%=rootPath%>leave/isUpdate.action',
			data:{
			leaveapplyid:_applyid
			},
		    async: false,
		    cache: false
		}).responseText;
	if(isok==1){
	var _content = $.ajax({
			url: '<%=rootPath%>leave/getLeaveapplyInfo.action',
			data:{
			leaveapplyid:_applyid
			},
		    async: false,
		    cache: false
		}).responseText;
		//弹出对话框
	$.dialog({
		title:'申请加班',
			content:_content ,
			max: false,
		    min: false,
		    top:35,
			ok: function(){
			$('input[name="jsonFile"]').val(JSON.stringify(_allattacheds));  //点击修改文档时候将它转成字符串发送						
			$.dialog.confirm("您确定要修改这个加班申请么？",function(){
			var validate = $('#appaddform').form('validate');
				/* 执行提交操作表单 */
				$('#appaddform').form('submit', {   
				    url:'<%=rootPath%>leave/upLeaveapplyinfo.action',   				   
				    success:function(data){  
				    	$.messager.progress('close');
				        if(data=='1'){
				        	$.dialog.tips('加班修改成功',1,'32X32/succ.png',function(){
    						window.location.reload();
    						});			    
				        }else{
				        	$.dialog.tips('加班修改失败',1,'32X32/fail.png',function(){
    						});	
				        }  
				    }   
				}); 
			},function(){});
				return validate;
		    },
		    okVal:'保存',
		    cancelVal: '取消',
		    cancel: function(){
		    	$("#typeadddiv").dialog("close");
		    },
		    lock: true,
		    padding: 0,
		});
	
	}else{
	$.dialog.tips('加班申请已进入审核流程，不可修改！',1,'32X32/i.png',function(){});	
	}		
}

</script>
</body>
</html>
