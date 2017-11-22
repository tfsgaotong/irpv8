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
<title>加班申请</title>
<link rel="stylesheet" href="<%=rootPath%>view/css/fullCalendar/css/redmond/jquery-ui-1.8.1.custom.css" type="text/css"></link>
<link rel="stylesheet" href="<%=rootPath%>view/js/jqueryui/css/jquery-ui-timepicker-addon.css" type="text/css"></link>
<script type="text/javascript" src="<%=rootPath%>view/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/fullCalendar/js/jquery-ui-1.8.6.custom.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/fullCalendar/js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/jqueryui/jquery-ui-timepicker-zh-CN.js"></script>
<script type="text/javascript">
    $(function () { 
        $("#starttime").datetimepicker({
        	dateFormat: 'yy-mm-dd', hourMin: 00, hourMax: 23, hourGrid: 4, minuteGrid: 10, timeText: '时间', hourText: '时', minuteText: '分',value:'2016-09-14 00:00',step:10,  onClose: function (dateText, inst) {
        	var date1=$("#starttime").datetimepicker('getDate');//时间1
        	if(date1==null){
        	var starttime = $('#starttime').val();
        	date1 = new Date(starttime.replace(/-/g,"/"));
        	}
       		var date2=$("#endtime").datetimepicker('getDate');//时间2        		
       		var date3=date2.getTime()-date1.getTime();  //时间差的毫秒数
			var hours= fomatFloat(date3/(3600*1000), 1);
       		if(hours>=0){
			$("#leavedays").val(parseInt(hours));
			}else{
			$("#leavedays").val(0);
			}
       		
        	} 		
        });
         $("#endtime").datetimepicker({
        	dateFormat: 'yy-mm-dd', hourMin: 00, hourMax: 23, hourGrid: 4, minuteGrid: 10, timeText: '时间', hourText: '时', minuteText: '分',  onClose: function (dateText, inst) {
        	var date1=$("#starttime").datetimepicker('getDate');//时间1
        	if(date1==null){
        	var starttime = $('#starttime').val();
        	date1 = new Date(starttime.replace(/-/g,"/"));
        	}
       		var date2=$("#endtime").datetimepicker('getDate');//时间2        		
       		var date3=date2.getTime()-date1.getTime();  //时间差的毫秒数
			var hours= fomatFloat(date3/(3600*1000), 1);
       		if(hours>=0){
			$("#leavedays").val(parseInt(hours));
			}else{
			$("#leavedays").val(0);
			}
       		
        	},
        })       
    })
    function fomatFloat(src,pos){      
             return Math.round(src*Math.pow(10, pos))/Math.pow(10, pos);             

} 
    </script>
</head>
<body>
<form style="display: block;" id="appaddform" method="post">
<input name="Leaveapplyid" value="<s:property value="irpLeaveapply.Leaveapplyid"/>" type="hidden">
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
 			line-height: 26px;
 			
 			 		
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
 			width: 55px;
  			height:23px;
 			display:block;
			border: thin solid rgb(211, 211, 211);
			border-radius: 9px;
			padding: 6px;
			background-color: rgb(245, 245, 245);
			margin-top: 6px;
 		}	
		</style>
		
		<div class="g_01_1"  style="width: 800px;height: 264px;margin-top: 10px;margin-left: 5px;" id="createAlbum">
		 
			<ul>
				<li class="g_01_1_one">
					<label>标题：</label> 
				</li>
				<li class="g_01_1_two">
					<font><input type="hidden" name="title" value="<s:property value='irpLeaveapply.title' />"/><s:property value='irpLeaveapply.title' /> </font> 
				</li>
				<li class="g_01_1_zj">&nbsp;</li>
				
				<li class="g_01_1_one">
					<label>紧急程度：</label> 
				</li>
				<li class="g_01_1_two" >　
					<input style="width: auto;" name="emergency" type="radio" value="10" <s:if test="irpLeaveapply.emergency==10">checked="checked"</s:if>>正常
					&nbsp;&nbsp;<input style="width: auto;" name="emergency" type="radio" value="20"  <s:if test="irpLeaveapply.emergency==20">checked="checked"</s:if>>重要
					&nbsp;&nbsp;<input style="width: auto;" name="emergency" type="radio" value="30"  <s:if test="irpLeaveapply.emergency==30">checked="checked"</s:if>>紧急
				</li>
				<li class="g_01_1_zj">&nbsp;</li>
				 
				 
				<li class="g_01_1_one" style="height: 110px;" >
					<label style="">说明：</label> 
				</li>
				
				<li style="width: 700px;height: 160px;"> 
					<font style="">
							<s:property value="@com.tfs.irp.util.SysConfigUtil@getSysConfigValue('OVERTIME_FLOW')"/>
					</font>
				</li> 
				
				
				
				<li class="g_01_1_one">
					<label>姓名：</label> 
				</li>
				<li class="g_01_1_two">
					<font><s:property value="@com.tfs.irp.util.LoginUtil@findUserById(irpLeaveapply.cruserid).getShowName()" /></font> 
				</li>
				<li class="g_01_1_zj">&nbsp;</li>
				<li class="g_01_1_one">
					<label>所属部门：</label> 
				</li>
				<li class="g_01_1_two">
				&nbsp;
					<font>
							<s:if test="userGroups.size()>0 && (irpDocument==null || irpDocument.isdraft==0)">
							<select  name="leaveconfigid121">
							<s:iterator  value="userGroups">
								<option value="<s:property value="key" />" <s:if test="groupId==key">selected</s:if>>
							<s:property value="value" /></option></s:iterator>
							</select>
							</s:if>
					</font> 
				</li>
				<li class="g_01_1_zj">&nbsp;</li>
				<li class="g_01_1_zj">&nbsp;</li>
				<li class="g_01_1_one">
					<label>申请日期：</label>  
				</li>
				<li class="g_01_1_two">
					<font><s:date name="irpLeaveapply.crtime" format="yyyy-MM-dd HH:mm"/></font> 
				</li>
				<li class="g_01_1_zj">&nbsp;</li>
				
			</ul>
		
		</div>
		 
		<div class="g_01_1" style="border-top: #B0C4DE	thin solid;width: 800px;height: 190px;padding:10px;" >
				<li class="g_01_1_one">
					<label>加班类型：</label> 
				</li>
				<li class="g_01_1_two" style="border: none;">
            		<select id="_Irp_type_oper" name="leaveconfigid"> 
						<s:iterator var="eleq" value="irpLeaveconfigs" status="status">
							<option value="<s:property value="#eleq.leaveconfigid" />" <s:if test="#eleq.leaveconfigid==irpLeaveapply.applytypeid">selected</s:if>><s:property value="#eleq.leaveconfigname" /></option>
						</s:iterator>
					</select>
				</li>
				<li class="g_01_1_zj">&nbsp;</li>
				
				<li class="g_01_1_one">
					<label>选择审核人：</label> 
				</li>
				<li class="g_01_1_two" style="border: none;">
					 
					
					<input name="checker"  id="checker" type="text" value=" <s:property value="@com.tfs.irp.util.LoginUtil@findUserById(userids).getUsername()"/>" onclick="checkOffpersonId()" validType="length[2,50]" class="easyui-validatebox"    missingMessage="请选择审核人"   data-options="required:true"  >
            		<input name="userids"  id="checkeruserids"   type="text" style="display:none" value="<s:property value="userids" />" >
            
					 
					 
				</li>
				<li class="g_01_1_zj">&nbsp;</li>
				

				<li class="g_01_1_one">
					<label>开始日期：</label> 
				</li>
				
				<li class="g_01_1_two" style="border: none;">
					 <input name="starttime1" type="text"   class="easyui-validatebox ui_timepicker"  value="<s:date name="irpLeaveapply.starttime" format="yyyy-MM-dd HH:mm"/>" missingMessage="请填写开始时间" validType="startTime" data-options="required:true"  id="starttime" >
				</li>
				<li class="g_01_1_zj">&nbsp;</li>
				
				<li class="g_01_1_one">
					<label>结束日期：</label> 
				</li>
				
				<li class="g_01_1_two" style="border: none;">
					 <input name="endtime1" class="easyui-validatebox ui_timepicker"   missingMessage="请填写结束时间" validType="endTime" value="<s:date name="irpLeaveapply.endtime" format="yyyy-MM-dd HH:mm"/>"    data-options="required:true"  id="endtime" >
				</li>
				<li class="g_01_1_zj">&nbsp;</li>
				
				
				<li class="g_01_1_one">
					<label>加班时长(小时)：</label> 
				</li>
				<li class="g_01_1_two" style="border: none;">				
					<input  name="leavedays" id="leavedays" type="text" class="easyui-validatebox"    validType="minTime"  data-options="required:false" style="border:1px solid #6faadb;width: 50px;" name="leavedays" value="<s:property value="irpLeaveapply.leavedays"/>" readonly="readonly" >
					
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
					<label style="">加班原因：</label> 
				</li>
				
				<li  class="g_01_1_two" style="width: 650px;border: none; "> 
					 
					
					 <textarea id="content" name="applyreason"   class="easyui-validatebox"   missingMessage="请填写加班理由" validType="length[2,100]"  data-options="required:true" style="width:100%;height:40px;font-size: 12px;padding: 6px 0 0 6px;"><s:property value="irpLeaveapply.applyreason"/> </textarea>
					 
					 
					 
					 
				</li> 
				<li class="g_01_1_zj">&nbsp;</li>
				<li class="g_01_1_zj">&nbsp;</li>	
				<li class="g_01_1_one" style=" " >
					<label style="">加班工作内容：</label> 
				</li>
				
				<li  class="g_01_1_two" style="width: 650px;border: none; "> 
					 
					 
					<textarea id="content" name="content"  style="width:100%;height:40px;font-size: 12px;padding: 6px 0 0 6px;" validType="length[2,100]" class="easyui-validatebox" data-options="required:true" ><s:property value="irpLeaveapply.content"/></textarea>
					 
					 
					 
					 
				</li> 
				<li class="g_01_1_zj">&nbsp;</li>
				<li class="g_01_1_zj">&nbsp;</li>
				<li class="g_01_1_one" style=" " >
					<label style="">加班工作地点：</label> 
				</li>
				
				<li  class="g_01_1_two" style="width: 650px;border: none; "> 
					 
					 
					<textarea id="address" name="address"  style="width:100%;height:40px;font-size: 12px;padding: 6px 0 0 6px;" validType="length[2,50]" class="easyui-validatebox" data-options="required:true"><s:property value="irpLeaveapply.address"/></textarea>
					 
					 
					 
					 
				</li> 
				<li class="g_01_1_zj">&nbsp;</li>	
				<li class="g_01_1_one" style=" " >
					<label style="">附件上传：</label> 
				</li>
				
				<li style="width:700px; "class="g_01_1_one" id="showFile"> 
					 <s:if test="attacheds.size()==0">
					 <a  href="javascript:void(0);" onclick="toupdateAttacthed(<s:property value="irpLeaveapply.Leaveapplyid"/>)">选择附件</a>
					 </s:if>
					 <s:else>
					  <a  href="javascript:void(0);" onclick="toupdateAttacthed(<s:property value="irpLeaveapply.Leaveapplyid"/>)">修改附件</a>
					 </s:else>
					 	 
					 	 <input type="hidden" name="jsonFile"/>
					 	 
					 &nbsp;&nbsp;
					 					
					 <span id="imgshow1">
					 <s:iterator value="attachedinfos" >
					<s:if test="flag=='true'">
					 <img alt="显示图片" src="<%=rootPath %>file/readfile.action?fileName=<s:property value="attfile" />" id="mypic" width="100" height="100" title="<s:property value="attdesc" />"/>
					 </s:if>
					 </s:iterator>
					 </span>
					 &nbsp;&nbsp;
					 <span id="fileName">					
					<s:iterator value="attachedinfos" >
					 <s:if test="flag!='true'">
					<s:property value="attdesc" />
					</s:if>
					</s:iterator>
					 </span>
					 
				</li> 

	 	
		</div> 
	</div>







 
	
</form>	
<script type="text/javascript">

//全局变量
var addJsonFileList = new Array();
var editor=null;
function saveAttacthedByLeave(){ 
	var str=$.ajax({
		type:'post',
		dataType: "json",
		url:'<%=rootPath %>site/client_to_save_attached.action?check=1&isqusertionordoc=2', 
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
			if(addJsonFileList){ 
				if(addJsonFileList.length!=0){
					$('#fileName').text('');
				for(var i=0;i<addJsonFileList.length;i++){
					filename=addJsonFileList[i].attdesc;
					$('#fileName').append(filename);
				}					
				}
				var id=$('input:radio[name="editversions"]:checked').attr("id"); 
				for(var i=0;i<addJsonFileList.length;i++){ 
					if(addJsonFileList[i].attfile==id){
						addJsonFileList[i].editversions=1; 
					}else{ 
						if(addJsonFileList[i].editversions=="2"){
						}else{
							addJsonFileList[i].editversions=0;
						}
					}
				}
			}
		}
	});  
	


}

	
	

function checkOffpersonId(){
 var result = $.ajax({
			url: '<%=rootPath %>leave/selectchecker.action?type=20',
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
	//初始化dialog弹框选择人
	function initDialogShow(){
		$('#dialogPageForm').find('searchWord').val('');
		$('#dialogPageForm').find('searchType').val('');
		$('#dialogPageForm').find('orderField').val('');
		$('#dialogPageForm').find('pageNum').val(1);  
	}
function getleavedays(){
var date1=new Date($('#starttime').val());  //开始时间
var date2=new Date($('#endtime').val());    //结束时间
var date3=date2.getTime()-date1.getTime()  //时间差的毫秒数
var newhours = '';
if(date1<date2){
//计算出相差天数
var days=Math.floor(date3/(24*3600*1000))
//计算出小时数
var leave1=date3%(24*3600*1000)    //计算天数后剩余的毫秒数
var hours=Math.floor(leave1/(3600*1000))
//计算相差分钟数
var leave2=leave1%(3600*1000)        //计算小时数后剩余的毫秒数
var minutes=Math.floor(leave2/(60*1000));
$('#leavedays').val(hours);
}
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
			if(_allattacheds.length!=0){
					$('#fileName').html('');
					$('#imgshow1').html('');
				for(var i=0;i<_allattacheds.length;i++){
					filename=_allattacheds[i].attfile;
						 $.ajax({
		 					type:"post",
		 					data : {'fileName' :filename},
		 					async: false,
		 					url:"<%=rootPath%>site/isradio.action" ,
		 					success:function(msg){
		 						if(msg=="1"){//说明他是图片		 								
		 							 $("#imgshow1").append("<img alt=\"显示图片\" src=\"<%=rootPath %>file/readfile.action?fileName="+filename+"\" id=\"mypic\" width=\"100\" height=\"100\" />");
		 			    	     }else{
		 			    	    	$('#fileName').append(_allattacheds[i].attdesc);
		 			    	     }
		 					}
		 				});
				}					
				}
				var id=$('input:radio[name="editversions"]:checked').attr("id"); 
				for(var i=0;i<_allattacheds.length;i++){
					if(_allattacheds[i].attfile==id){
						_allattacheds[i].editversions=1; 
					}else{ 
						if(_allattacheds[i].editversions=="2"){//一种就是附件，
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