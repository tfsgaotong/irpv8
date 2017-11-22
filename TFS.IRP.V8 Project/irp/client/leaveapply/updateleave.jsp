
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
    <%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
    java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(  
    	     "yyyy-MM-dd HH:mm");  
    	   java.util.Date currentTime = new java.util.Date();  
    	   String time = simpleDateFormat.format(currentTime).toString();  

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请假申请</title>



<link rel="stylesheet" href="<%=rootPath%>view/css/fullCalendar/css/redmond/jquery-ui-1.8.1.custom.css" type="text/css"></link>
<link rel="stylesheet" href="<%=rootPath%>view/js/jqueryui/css/jquery-ui-timepicker-addon.css" type="text/css"></link>
<script type="text/javascript" src="<%=rootPath%>view/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/jquery.easyui.min.js"></script>
<script src="<%=rootPath %>view/js/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/fullCalendar/js/jquery-ui-1.8.6.custom.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/fullCalendar/js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/jqueryui/jquery-ui-timepicker-zh-CN.js"></script>
<script type="text/javascript">
    $(function () {
    	
        $("#starttime").datetimepicker({
        	dateFormat: 'yy-mm-dd',minDate: new Date(), hourMin: 00, hourMax: 23, hourGrid: 4, minuteGrid: 10, timeText: '时间', hourText: '时', minuteText: '分', 
        	onClose: function (dateText, inst) {
        		var date1=$("#starttime").datetimepicker('getDate');//时间1
            	if(date1==null){
            	var starttime = $('#starttime').val();
            	date1 = new Date(starttime.replace(/-/g,"/"));
            	}
           		var date2=$("#endtime").datetimepicker('getDate');//时间2        		
           		var date3=date2.getTime()-date1.getTime();  //时间差的毫秒数
    			var days= fomatFloat(date3/(24*3600*1000), 2);
    			if(days>0){
    				var z=days%0.5;
    				if(z==0){
    					$("#leavedays").val(days);
    				}else{
    					
           			if(Math.ceil(days)-days>0.5){
           				
           		$("#leavedays").val(Math.ceil(days)-0.5);
           			}else{
           		$("#leavedays").val(Math.ceil(days)+0.5);
           				
           			}
    				}
           		}else {
           			$("#leavedays").val(0);
           		}
           		
           		var type=$('#_Irp_type_oper').val();
           	    
           	 if(type==58){
           		 
           		if(days>$("#yearDay").val()){
           			$.dialog.tips('年假天数已不足',1,'32X32/fail.png');	
           		  
           		$("#leavedays").val(0);
           		}
           	 }
       		
        	},
        });
        
        $("#endtime").datetimepicker({
        	dateFormat: 'yy-mm-dd',minDate: new Date(),hourMin: 00, hourMax: 23, hourGrid: 4, minuteGrid: 10, timeText: '时间', hourText: '时', minuteText: '分',  
        	onClose: function (dateText, inst) {
        		var date1=$("#starttime").datetimepicker('getDate');//时间1
        		if(date1==null){
                	var starttime = $('#starttime').val();
                	date1 = new Date(starttime.replace(/-/g,"/"));
                	}
       		 var date2=$("#endtime").datetimepicker('getDate');//时间2        		
       		var date3=date2.getTime()-date1.getTime();  //时间差的毫秒数
       		
       		
       		var days= fomatFloat(date3/(24*3600*1000), 2);
       			
       		if(days>0){
				var z=days%0.5;
				if(z==0){
					$("#leavedays").val(days);
				}else{
					
       			if(Math.ceil(days)-days>0.5){
       				
       		$("#leavedays").val(Math.ceil(days)-0.5);
       			}else{
       		$("#leavedays").val(Math.ceil(days)+0.5);
       				
       			}
				}
       		}else {
       			$("#leavedays").val(0);
       		}
       		
       		var type=$('#_Irp_type_oper').val();
       	    
       	 if(type==58){
       		 
       		if(days>$("#yearDay").val()){
       			$.dialog.tips('年假天数已不足',1,'32X32/fail.png');	
       		  
       		$("#leavedays").val(0);
       		}
       	 }
       		
        	},
        })
    })
        function fomatFloat(src,pos){      
             return Math.round(src*Math.pow(10, pos))/Math.pow(10, pos);             

} 
    </script>
</head>
<body style="width: 810px;height: 500px;">


<form style="display: block;" id="appaddform" method="post">
<input name="leaveapplyid" value="<s:property value="irpLeaveapplyInfo.leaveapplyid" />" style="display:none">


<div class=".g_01" style="font-size: 14px;overflow-y:auto;height:520px;overflow-x: hidden;">
		<style type="text/css">
 
 		.g_01_1_one{
 			float: left;
 			width: 100px;
 			color: #2061b0;
 		}
 		.g_01_1_zj{
 			float: left;
 			width: 50px;
 		}		
 		.g_01_1_two{
 			float: left;
 			width: 250px;
 			border-bottom: 1px solid #ccc;
 		}
 		.g_01_1 li{
 			line-height: 35px;
 			
 			 		
 		} 
 		.g_01_1 input{ 
 			margin:2px;
			padding: 7px;
			border: 1px solid #ccc;
			border-radius: 3px;
			margin-bottom: 10px;
			width: 100%;
			box-sizing: border-box;
			font-family: montserrat;
			color: #2C3E50;
			font-size: 13px;
 		}
 		.g_01_1 a{
			border: thin solid rgb(211, 211, 211);
			border-radius: 9px;
			padding: 6px;
			background-color: rgb(245, 245, 245);
 		}
		</style>
		
		<div class="g_01_1"  style="width: 800px;height: 300px;margin-top: 10px;margin-left: 5px;">
		 
			<ul>
				<li class="g_01_1_one">
					<label>标题：</label> 
				</li>
				<li class="g_01_1_two">
				 
					
					<font> <s:property value="irpLeaveapplyInfo.title" /></font> 
				</li>
				<li class="g_01_1_zj">&nbsp;</li>
				
				<li class="g_01_1_one">
					<label>紧急程度：</label> 
				</li>
				<li class="g_01_1_two" >　
				
					<input style="width: auto;" name="emergency" type="radio" value="10"  <s:if test="irpLeaveapplyInfo.emergency=='正常'">checked="checked"</s:if>>正常
					&nbsp;&nbsp;<input style="width: auto;" name="emergency" type="radio" value="20"  <s:if test="irpLeaveapplyInfo.emergency=='重要'">checked="checked"</s:if>>重要
					&nbsp;&nbsp;<input style="width: auto;" name="emergency" type="radio" value="30" <s:if test="irpLeaveapplyInfo.emergency=='紧急'">checked="checked"</s:if> >紧急
				</li>
				<li class="g_01_1_zj">&nbsp;</li>
				 
				 
				<li class="g_01_1_one" style="height: 150px;" >
					<label style="">说明：</label> 
				</li>
				
				<li style="width: 700px;height: 200px;"> 
					<font style="">
							<s:property value="@com.tfs.irp.util.SysConfigUtil@getSysConfigValue('LEAVEAPPLY_FLOW')"/>
					</font>
				</li> 
				
				
				
				<li class="g_01_1_one">
					<label>姓名：</label> 
				</li>
				<li class="g_01_1_two">
					<font><s:property value="irpLeaveapplyInfo.cruserid" /></font> 
				</li>
				<li class="g_01_1_zj">&nbsp;</li>
				
				<li class="g_01_1_one">
					<label>所属部门：</label> 
				</li>
				<li class="g_01_1_two">
					<font>
							&nbsp;
							<s:if test="userGroups.size()>0 && (irpDocument==null || irpDocument.isdraft==0)">
						
							
							<s:property value="userGroups.value" />
							
							</s:if>
					</font> 
				</li>
				<li class="g_01_1_zj">&nbsp;</li>
				
				<li class="g_01_1_one">
					<label>申请日期：</label>  
				</li>
				<li class="g_01_1_two">
					<font >
					<input id="nowtime" value="<s:date name="irpLeaveapplyInfo.crtime" format="yyyy-MM-dd HH:mm"/>" style="display:none">
					<s:date name="irpLeaveapplyInfo.crtime" format="yyyy-MM-dd HH:mm"/>
					</font> 
				</li>
				<li class="g_01_1_zj">&nbsp;</li>
				
				<li class="g_01_1_one">
					<label>剩余年假天数：</label>  
				</li>
				<li class="g_01_1_two">
					<font>&nbsp;<s:property value="irpLeaveapplyInfo.userleavedays" /></font> 
				</li>
				<li class="g_01_1_zj">&nbsp;</li>
				
			</ul>
		
		</div>
		 
		<div class="g_01_1" style="border-top: #B0C4DE	thin solid;width: 800px;height: 200px;">
	 	
				<li class="g_01_1_one">
					<label>请假类型：</label> 
				</li>
				<li class="g_01_1_two" style="border: none;">
            		<select id="_Irp_type_oper" name="leaveconfigid"> 
						<s:iterator var="eleq" value="irpLeaveconfigs" status="status">
							<option   <s:if test="irpLeaveapplyInfo.leavetypeId==#eleq.leaveconfigid">selected="selected"</s:if>  value="<s:property value="#eleq.leaveconfigid" />"><s:property value="#eleq.leaveconfigname" /></option>
						</s:iterator>
					</select>
				</li>
				<li class="g_01_1_zj">&nbsp;</li>
				
				<li class="g_01_1_one">
					<label>选择审核人：</label> 
				</li>
				<li class="g_01_1_two" style="border: none;">
					 
					 
					<input name="checker"  id="checker" type="text"  value="<s:property value="irpLeaveapplyInfo.checker" />" onclick="checkOffpersonId()" validType="length[2,50]" class="easyui-validatebox"    missingMessage="请选择审核人"   data-options="required:true"  >
            		<input name="userids"  id="checkeruserids"   type="text" style="display:none" value="<s:property  value="irpLeaveapplyInfo.checkids"  />" >
            
					 
					 
				</li>
				<li class="g_01_1_zj">&nbsp;</li>
				

				<li class="g_01_1_one">
					<label>开始日期：</label> 
				</li>
				<li class="g_01_1_two" style="border: none;">
					 <input name="starttime1" type="text"   class="easyui-validatebox ui_timepicker"  value="<s:date name="irpLeaveapplyInfo.starttime" format="yyyy-MM-dd HH:mm"/>" missingMessage="请填写开始时间" validType="startTime" data-options="required:true"  id="starttime" >
				</li>
				<li class="g_01_1_zj">&nbsp;</li>
				
				<li class="g_01_1_one">
					<label>结束日期：</label> 
				</li>
				<li class="g_01_1_two" style="border: none;">
					 <input name="endtime1" class="easyui-validatebox ui_timepicker"   missingMessage="请填写结束时间" validType="endTime" value="<s:date name="irpLeaveapplyInfo.endtime" format="yyyy-MM-dd HH:mm"/>"    data-options="required:true"  id="endtime" >
				</li>
				<li class="g_01_1_zj">&nbsp;</li>
				
				
				<li class="g_01_1_one">
					<label>请假天数：</label> 
				</li>
				<li class="g_01_1_two" style="border: none;">
				
					<input   id="leavedays" type="text" value="<s:property value="irpLeaveapplyInfo.leavedays" />" style="border:1px solid #6faadb;width: 50px;" name="leavedays" value="0" readonly="readonly" >
					
				</li>
				<li class="g_01_1_zj">&nbsp;</li>
				
				<li class="g_01_1_one">
					<label>通知方式：</label> 
				</li>
				<li class="g_01_1_two" style="border: none;">
					<s:iterator value="assewarnList" status="assewarnList">
							<input name="warnMenthod"  style="width: 20px;padding:0px 0px;"class='checkboxs' id="warnid" type='checkbox'   value="<s:property value='warnid'/>" <s:if test="cruserid==0"> checked="checked"</s:if> />&nbsp;<s:property value='assewarnname'/>
						</s:iterator>
						<input style="visibility: hidden;width: 1px;" />
				</li>
				<li class="g_01_1_zj">&nbsp;</li>
	 	

				
				<li class="g_01_1_one" style=" " >
					<label style="">请假原因：</label> 
				</li>
				
				<li  class="g_01_1_two" style="width: 650px;border: none; "> 
					 
					 
					 <input id="content" name="applyreason"  value="<s:property value="irpLeaveapplyInfo.applyreason" />"  class="easyui-validatebox"   missingMessage="请填写请假理由" validType="length[2,100]"  data-options="required:true" style="width:100%;height:40px;font-size: 12px;" />
					 
					 
					 
					 
				</li> 
				 	<li class="g_01_1_zj">&nbsp;</li>	
				<li class="g_01_1_one" style=" " >
					<label style="">附件上传：</label> 
				</li>		
				<li style="width:700px; "class="g_01_1_one"> 
					 <s:if test="attachedinfos.size()==0">
					 <a  href="javascript:void(0);" onclick="toupdateAttacthed(<s:property value="irpLeaveapply.Leaveapplyid"/>)">选择附件</a>
					 </s:if>
					 <s:else>
					  <a  href="javascript:void(0);" onclick="toupdateAttacthed(<s:property value="irpLeaveapply.Leaveapplyid"/>)">修改附件</a>
					
					
					 </s:else>
					 	 
					 	 <input type="hidden" name="jsonFile"/><br>
					 	 
					 <span id="imgshow1">
					 <s:iterator value="attachedinfos" >
					<s:if test="flag=='true'">
					 &nbsp;&nbsp;
					  <img alt="显示图片" src="<%=rootPath %>file/readfile.action?fileName=<s:property value="attfile" />" id="mypic" width="100" height="100"/>

				</s:if></s:iterator>	</span> 
					 
					 <span id="fileName">
					 <s:iterator value="attachedinfos" >
					<s:if test="flag=='false'">
					<s:property value="attdesc" /> </s:if>
					 </span>
					 
					 </s:iterator>
					 
				</li> 
					
				
				
				
			 
					
			
				
				
              	
	 	
		</div> 
	</div>







 
	
</form>	
<script type="text/javascript">




function checkOffpersonId(){
	 var result = $.ajax({
				url: '<%=rootPath %>leave/selectchecker.action?type=10',
			    async: false,
			    cache: false,
			     data:{userids:$('#checkeruserids').val()}
			}).responseText;
		$.dialog({		 
				title:'请选择审核人',
				content: result ,
				max: false,
			    min: false,
				ok: function(){
				 $("#checker").val(usernamechecker);
				$("#checkeruserids").val(useridchecker);
				$("#typeadddiv1").dialog("close"); 
			    },
			    okVal:'提交',
			    cancelVal: '取消',
			    cancel: function(){
			    	$("#typeadddiv1").dialog("close");
			    },
			    lock: true,
			    padding: 0,
		});		
		
		
		
	}



 $(function(){
	 toUpdate(<s:property value="irpLeaveapply.Leaveapplyid"/>);
	 });
	 var _allattacheds=null;
	 function toUpdate(_docid){ 
		
	 	//发送ajax请求获得所有附件
	 	$.ajax({ 
	 		type: "GET", 
	 		url: "<%=rootPath %>leave/allattachedtodocument.action?leaveapplyid="+_docid, 
	 		success: function(msg){   
	 			_allattacheds=eval(msg);//转换成附件集合    
	 		}
	 	});   
	 }
	
	 //修改附件
	 function toupdateAttacthed(_docid){  
	 	var str=$.ajax({
	 		type:'GET',
	 		url:'<%=rootPath %>site/client_to_update_attached.action?docid='+_docid+'&isqusertionordoc=2', 
	 		async: false,
	 		cache: false
	 	}).responseText;  
	 	$.dialog({
	 		title:'附件管理',
	 		max:false,
	 		min:false,
	 		lock:true,
	 		resize: false, 
	 		content:str,
	 		cancelVal:'确定', 
	 		cancel:function(){
	 			
	 				
	 			if(_allattacheds){
	 				 				
					var id=$('input:radio[name="editversions"]:checked').attr("id"); 
					$('#fileName').text('');

					var s1=$('#imgshow1').text("");
					
					for(var i=0;i<_allattacheds.length;i++){
						filename=_allattacheds[i].attfile;
					  	 $.ajax({
			 					type:"post",
			 					data : {'fileName' :filename},
			 					async: false,
			 					url:"<%=rootPath%>site/isradio.action" ,
			 					success:function(msg){
			 						if(msg=="1"){//说明他是图片
			 							
			 							$("#imgshow1").css("display","block");
			 							 $("#imgshow1").append("<img alt=\"显示图片\" src=\"<%=rootPath %>file/readfile.action?fileName="+filename+"\" id=\"mypic\" width=\"100\" height=\"100\"/>");
			 			    	     }else{
			 			    	    	$('#fileName').append(_allattacheds[i].attdesc);
			 			    	     }
			 					}
			 				});
					  	
						
						if(_allattacheds[i].attfile==id){
							_allattacheds[i].editversions=1; 
						}else{ 
							if(_allattacheds[i].editversions=="2"){
							}else{
								_allattacheds[i].editversions=0;
							}
						}
					}
				} 
	 		}
	 	});  
	 }

</script>
</body>
</html>