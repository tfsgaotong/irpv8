<%@page import="com.tfs.irp.util.SysConfigUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>申请会议室</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<style type="text/css">
#loading {
	top: 0px;
	right: 0px;
}
.tooltip {
	padding-bottom: 25px;
	padding-left: 25px;
	width: 160px;
	padding-right: 25px;
	display: none;
	background: url(images/black_arrow.png);
	height: 70px;
	color: #fff;
	font-size: 12px;
	padding-top: 25px;
	z-order: 100;
}
 
#asseroominfo table{
	width:400px;
	margin-left:100px;
	border: 1px gray solid;
}
#table td{
	border: 1px gray solid;
}
#warnid{
	width: 2px;
}
textarea{
height: 30px;
}
.rowElem .easyui-validatebox{
float:left;
}
#table{
border-collapse: collapse;
border:1px gray solid;
font-size: 12px;
}
#table td{
border:1px gray solid;
}
.rowElem .checkboxs{
clear: both;
display: inline;
width: 20px;
padding: 0px;

}
#warntime{
display: inline;
clear: both;
}
#edit{
overflow: auto;
width:800px;
height: 350px;
}

</style>
  </head>
  <script type="text/javascript">
	//当前日期 
	 $.extend($.fn.validatebox.defaults.rules, {   
	    startTime : {// 会议开始时间 
                validator : function(value) {
                    return $.fullCalendar.parseDate(value)<new Date()?false:true;  
                },  
                message : '开始时间必须大于当前时间'  
            },
         endTime : {// 会议结束时间 
            validator : function(value) { 
             	var starttime=$("#starttime").val();
             	 var time2 = $.fullCalendar.parseDate(value).Format("yyyy-MM-dd hh:mm"); 
             	  var time1 = $.fullCalendar.parseDate(starttime).Format("yyyy-MM-dd hh:mm"); 
             	 if(time2.indexOf("aN")!=-1||time1.indexOf("aN")!=-1){
             	 	$.fn.validatebox.defaults.rules.endTime.message ='请填写正确的时间格式';
             	 	return false;
             	 }
             	
                if($.fullCalendar.parseDate(value)<=$.fullCalendar.parseDate(starttime)){
                	$.fn.validatebox.defaults.rules.endTime.message ='结束时间必须大于开始时间';
                	return false;
		           
                }else{
                		//检查会议室在某一段时间是否被占用
                	var starttime=$("#starttime").val();
					var endTime=value;
					var searchWord=$("#asseroomid").val();
					var result=$.ajax({
						type:"get",
						url:"<%=rootPath%>asseroomapply/checkroom.action?start="+starttime+"&end="+endTime+"&searchWord="+searchWord+"&s="+(new Date().getTime()),
						async: false,
						cache : false
					}).responseText;
					if(result!=0){
						$.fn.validatebox.defaults.rules.endTime.message =result+',请调换会议室或时间。';
						return false;
					}else{
					
						var messageuserinfo=$("#messageuserinfo").val();
						var result=$.ajax({
							type:"get",
							url:"<%=rootPath%>asseroomapply/checkuser.action?start="+starttime+"&end="+endTime+"&fromidAll="+messageuserinfo,
							async: false,
							cache : false
						}).responseText;
						if(result!=0){
							$.fn.validatebox.defaults.rules.endTime.message =result+',请调换人员。';
							return false;
						}else{
							return true;
						}
					
					}
                }
            },  
            message : ''  
        },
         warnTime : {// 通知时间 
            validator : function(value) {
            var starttime=$("#starttime").val();
            if($("#warn").checked){
            	return false;
            }else{
            	 if($.fullCalendar.parseDate(value)>new Date()&&$.fullCalendar.parseDate(value)<$.fullCalendar.parseDate(starttime))  
                	return true;  
            	}
            },
            message : '通知时间必须大于当前时间小于会议开始时间'  
        },
         count : {// 通知时间 
            validator : function(value) {
            var num=($("#people").val())*1;
            if((value*1)<=num)  
                return true;  
            },  
            message : '会议室容纳人数小于参会人员人数'  
        },
        checkroom :{
        	validator: function(value,param){   
	           if(!null==value){
	           	//检查会议室在某一段时间是否被占用
				var startTime=$("#starttime").val();
				var endTime=$("#endtime").val();
				var searchWord=$("#asseroomid").val();
				var result=$.ajax({
					type:"get",
					url:"<%=rootPath%>asseroomapply/checkroom.action?start="+startTime+"&end="+endTime+"&searchWord="+searchWord,
					async: false,
					cache : false
				}).responseText;
				if(result!=0){
					$.fn.validatebox.defaults.rules.checkroom.message =result+',请调换会议室或时间。';
					return false;
				}
	          }
	           
	        },   
	        message: ''  
	    },
	    checkuser:{
	    
	    	validator: function(value,param){   
				//查看人员是否有时间冲突
				var messageuserinfo=value;
				var startTime=$("#starttime").val();
				var endTime=$("#endtime").val();
				var result=$.ajax({
					type:"get",
					url:"<%=rootPath%>asseroomapply/checkuser.action?start="+startTime+"&end="+endTime+"&fromidAll="+messageuserinfo,
					async: false,
					cache : false
				}).responseText;
				if(result!=0){
					$.fn.validatebox.defaults.rules.checkuser.message =result+',请调换人员。';
					return false;
				}
	        },   
	        message: ''  
	    },
	  
	});
	


			/*
	获取会议室详细信息
	*/
	function selectAsseroom(flag){
		var result="";
		var asseroomid=$("#asseroomid").val();
		var sbid=$("#asseroomsbids").val();
		if(null==asseroomid||asseroomid==""){
			$.messager.alert("提示","当前无会议室！");
		}else{
			 result= $.ajax({
				url: '<%=rootPath %>asseroomapply/findasseroominfo.action',
				dataType: "json",
			    data: {
			    	asseroomid:asseroomid,sbids_1:sbid
			    },
			    async: false,
			    cache: false
			}).responseText; 
		
			if(flag==1){
				$.dialog({
					title:'会议室详情',
					content: result,
					width:"auto",
					height:"auto",
					modal:false, 
					show: {
						        effect: "blind",
						        duration: 1000
						      },
						      hide: {
						        effect: "explode",
						        duration: 1000
						      } ,
					ok: function(){ 
						var submit=$("#sbid").serialize();
						$.ajax({
	                     type: "POST", //使用post方法访问后台
	                      url: "<%=rootPath %>asseroomapply/getsbids.action", //要访问的后台地址
	                       data: submit, //要发送的数据
	                       success: function (data) {
	                      data = jQuery.parseJSON(data);
	                       		for(var i in data){
	                       			$("#people").val(data[0]);
	                       			$("#asseroomsbids").val(data[1]);
	                       		}
	                       }
			                });
					
				    },
				    okVal:'确定',
				    cancelVal: '关闭',
				    cancel: true,
				    lock: true,
				    padding: 0
				});
			}
		}
	}
	
	<%-- function selectUser(ipt){
	var userNames="";
	var userIds="";
	userNames=$('#'+ipt).val();
	userIds=$('#'+ipt+'id').val();
	var result = $.ajax({
			type: 'POST',
			url: '<%=rootPath %>user/import_user_audit.action?userIds='+userIds+'&userNames='+userNames,
		    async: false,
		    cache: false
		}).responseText;
		
		$.dialog({
			title:'选择用户',
			content: result,
			content: result,
			width:"auto",
			height:"auto",
			modal:false, 
			show: {
				        effect: "blind",
				        duration: 1000
				      },
				      hide: {
				        effect: "explode",
				        duration: 1000
				      } ,
			ok: function(){
			var userNames="";
				var userIds="";
				/* var userData = $("#ff").find('iframe')[0].contentWindow.jsonData; */
				var userData =jsonData;
				for(var i=0;i<userData.length;i++){
					var user = userData[i];
					if(i>0){
						userNames+=";"+user.name;
					}else{
						userNames=user.name;
					}
					if(i>0){
						userIds+=","+user.value;
					}else{
						userIds=user.value;
					}
				}
				$('#'+ipt).val(userNames);
				$('#'+ipt+'id').val(userIds);
				userCount();
				//$('#ff').dialog("destroy");
		    },
		    okVal:'保存',
		    cancelVal: '关闭',
		    cancel: true,
		    lock: true,
		    padding: 0
		});
		
		
	
	}
	 --%>
		
	/**
	选择用户
	**/
	function findSelectUserContent(_postData){
		var result = $.ajax({
			type: 'POST',
			url: '<%=rootPath%>user/select_user.action',
		    data: _postData,
		    async: false,
		    cache: false
		}).responseText;
		return result;
	} 	
	function addUser(){
		//获得内容
		var result = findSelectUserContent('init=0&ismaxamount=false'); 
		//初始化弹出框
		lhbDialog = $.dialog({
		id: 'selectUser', 
			title:'选择用户',
			content: result,
			modal:false,
			max: false,
		    min: false,
			ok: function(){
				$('#dialogPageForm').form('submit',{
	    			url : "<%=rootPath%>microblog/sendmessageselect.action",
	    			success:function(usernames){
	    						$('#messageuserinfo').val(usernames);
	    						userCount();
	    					
	    			}
				});
		    },
		    okVal:'保存',
		    cancelVal: '关闭',
		    cancel: true,
		    lock: true,
		    padding: 0
		});
	}
	function jump(_form){ 
		var sContent = findSelectUserContent(_form.serialize());  
		lhbDialog.get('selectUser',1).content(sContent); 
	}
	/**
	参会人员应到人数
	**/
	function userCount(){
		var count=0;
		var messageuserinfo=$('#messageuserinfo').val().split(",");
		for(var i=0;messageuserinfo.length>0&&i<messageuserinfo.length;i++){
				if(messageuserinfo[i]!="")
					count++;
		}
		$('#shouldman').val(count);
	}
	
	</script>
  
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
        
        </script> 
   <script type="text/javascript">
	$(document).ready(function () {
		 var date = new Date();
		 var d = date.getDate();
		 var m = date.getMonth();
		 var y = date.getFullYear();
		//开始时间
        $("#starttime").datetimepicker({ 
        	dateFormat: 'yy-mm-dd', hourMin: 00, hourMax: 23, hourGrid: 3, minuteGrid: 10, timeText: '时间', hourText: '时', minuteText: '分',
        	monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
			monthNamesShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
			dayNames: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
			dayNamesShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
			dayNamesMin:["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
			closeText:"确定",
			currentText:"现在时间",
        	onClose: function (dateText, inst) {
    	},
	        onSelect: function (selectedDateTime) {
	            $('#endtime').datetimepicker('option', 'minDate', $('#starttime').datetimepicker('getDate'));
	        }
		});
    	//结束时间
		$("#endtime").datetimepicker({
			dateFormat: 'yy-mm-dd', hourMin: 00, hourMax: 23, hourGrid: 3, minuteGrid: 10, timeText: '时间', hourText: '时', minuteText: '分',
			monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
			monthNamesShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
			dayNames: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
			dayNamesShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
			dayNamesMin:["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
			closeText:"确定",
			currentText:"现在时间",
			onClose: function (dateText, inst) {
    	},
        	onSelect: function (selectedDateTime) {
        }
		});
    	//通知时间
		$("#warntime").datetimepicker({
			dateFormat: 'yy-mm-dd', hourMin: 00, hourMax: 23, hourGrid: 3, minuteGrid: 10, timeText: '时间', hourText: '时', minuteText: '分', 
			monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
			monthNamesShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
			dayNames: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
			dayNamesShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
			dayNamesMin:["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
			closeText:"确定",
			currentText:"现在时间",
			onClose: function (dateText, inst) {
			},
		    onSelect: function (selectedDateTime) {
		        $('#warntime').datetimepicker('option', 'maxDate', $("#starttime").datetimepicker('getDate'));
		        var selectdate = $.fullCalendar.formatDate(date, "yyyy-MM-dd");
		        $('#warntime').datetimepicker('option', 'minDate',selectdate);
		    }
		    
		});
	});
	
	
	
				   
Date.prototype.Format = function (fmt) { //author: meizz 
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
				   
				   
	/*
	获取会议室详细信息
	
	*/
	$(function(){
		getData();
		 changeAsseroom();
		 var time2 = new Date().Format("yyyy-MM-dd hh:mm"); 
		$("#data").val(time2);
		testForm = $('#submit');  
		//初始化表单中的验证器  
		$('input[type!="hidden"],select,textarea',testForm ).each(function(){  
		    $(this).validatebox();  
		});	
	});
	/*
		获取数据
	*/
	function getData(){
		$("#asseroomid").val($("#searchWord").val());
		$('#warntime').val($("#warn").val());
		$('#starttime').val($("#start").val());
		$('#endtime').val($("#end").val());
	}
	function changeAsseroom(){
		var asseroomid=$("#asseroomid").val();
		var para = { asseroomid: asseroomid };
        $.ajax({
              type: "POST", //使用post方法访问后台
               url: "<%=rootPath %>asseroomapply/findoneasseroom.action", //要访问的后台地址
               data: para, //要发送的数据
               success: function (data) {
               	data = jQuery.parseJSON(data);
	           		for(var i in data){
	           			$("#people").val(data[0]);
	           			$("#asseroomsbids").val(data[1]);
	           		}
                 }
             });
		
	}
	/*
		控制会议通知日期
	*/
	function isSelect(flag){
		if(flag){
			$('#warntime').attr("disabled",false);
		}else{
			$('#warntime').attr("disabled","disabled");
		}
	}
	
	/*
	单选，选择用户
	*/
	function checkOffpersonId(flag){
	var userids=$("#"+flag+"s").val();	
	 var result = $.ajax({
				url: '<%=rootPath %>asseroomapply/findUser.action?userids='+userids,
			    async: false,
			    cache: false,
			}).responseText;
		$.dialog({		 
				title:'请选择审核人',
				content: result ,
				max: false,
			    min: false,
				ok: function(){
				// $("#checker").val(usernamechecker);
				$("#"+flag).val(usernamechecker);
				$("#"+flag+"s").val(useridchecker);
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
var useridchecker="";
var usernamechecker="";
function checkClick(_roleid){
useridchecker = _roleid;
usernamechecker =$("#leave"+_roleid).val();}
</script>     
  <body>
  <div id="edit">
        
			
			 <!--  -->
			<style type="text/css">
			.rowElem{
				border-bottom:  #E6E6FA	thin solid;
				line-height: 30px;
			
			}	
			
			</style>
			<input type="hidden" name="start" id="start" value="<s:property value='start'/>"/> 
			<input type="hidden" name="end" id="end" value="<s:property value='end'/>"/> 
			<input type="hidden" name="warn" id="warn" value="<s:property value='warn'/>"/> 
			<input type="hidden" name="searchWord" id="searchWord" value="<s:property value='searchWord'/>"/> 
        <div id="reservebox" title="会议室预约">
        
           <form id="submit"> 
            <div class="rowElem" style="margin-top: 20px;">
           		<label style="font-weight: bold;">基本信息</label>
           		
           		
            </div>
            <div class="rowElem" style="margin-left: 20px;margin-top: 10px;">
                <label>会议类型：</label>
				<select id="asseroomapplytypeid" name="asseroomapply.asseroomapplytypeid">
					<s:iterator value="assetypeList" status="assetypeList">
					<option value="<s:property value='asseroomapplytypeid'/>"><s:property value='assetypename'/></option>
					</s:iterator>
				</select>	
            </div>
            <div class="rowElem" style="margin-left: 20px;margin-top: 10px;" >
                <label>会议名称：</label>
                <input id="assename" name="asseroomapply.assename" class="easyui-validatebox" panelWidth="200px" validType="length[2,30]" required='true' missingMessage='请填写会议的名称' value="<s:property value="asseroomapply.assename"/>">
            </div>
            <div class="rowElem" style="margin-left: 20px;margin-top: 10px; padding-bottom: 15px;">
                <label>会议内容：</label>
                <textarea id="asseroomcontent" rowspan="100" cols="52" name="asseroomapply.asseroomcontent" class="easyui-validatebox" panelWidth="200px" validType="length[2,100]" required='true' missingMessage='请填写会议内容'></textarea>
            </div>
            
            <div class="rowElem" style="margin-left: 20px;margin-top: 10px;">
                <label>会议室：</label>
				<select style="float:left;" id="asseroomid" name="asseroomapply.asseroomid" onclick="changeAsseroom()" class="easyui-validatebox" panelWidth="200px" validType="ckeckroom" required='true' missingMessage='请添加会议室'>
					<s:iterator value="asseroomList" status="asseroomList">
					<option value="<s:property value='asseroomid'/>" ><s:property value='asseroomname'/></option>
					</s:iterator>
				</select>	        
				&nbsp;<a style="border:thin solid #D3D3D3;border-radius:9px;padding:6px;background-color:rgb(245, 245, 245);" href="javascript:void(0)" class="linkbh14" onclick="selectAsseroom(1)">会议室详情</a>
            </div>
            
            
            	<input type="hidden" id="asseroomsbids" name="asseroomapply.asseroomsbids"/>
            	<input type="hidden" id="people" value="<s:property value='people'/>"/>
             
             
            
            
            <div class="rowElem" style="margin-left: 20px;margin-top: 10px;">
               <label>附件：</label>
               
               <a style="border:thin solid #D3D3D3;border-radius:9px;padding:6px;background-color:rgb(245, 245, 245);" href="javascript:void(0);" onclick="saveAttacthedByLeave()">添加附件</a>
   	           
   	           <input type="hidden" name="jsonFile"/> 
            </div>
            
            
            
            <div class="rowElem" style="margin-top: 20px;">
           		<label style="font-weight: bold;">人员信息</label>
           		
           		
            </div>
            
            
            <div class="rowElem" style="margin-left: 20px;margin-top: 10px;">
                <label>召集人：</label>
                <input type="hidden"  id="asseconveneruserids" value="<s:property value='conveneruserid'/>"/> 
                <input id="asseconveneruserid"   name="convenerusername" readonly="readonly" value='<s:property value="convenerusername"/>'class="easyui-validatebox" panelWidth="200px" validType="length[2,30]" required='true' missingMessage='请填写会议召集人'>
                &nbsp;<a style="border:thin solid #D3D3D3;border-radius:9px;padding:6px;background-color:rgb(245, 245, 245);" href="javascript:void(0)" class="linkbh14" onclick="checkOffpersonId('asseconveneruserid')">选择用户</a>
            </div>
            <div class="rowElem" style="margin-left: 20px;margin-top: 10px;">
                <label>联系人：</label>
                <input type="hidden"  id="asselinkmanuserids" value="<s:property value='conveneruserid'/>"/> 
                <input id="asselinkmanuserid" readonly="readonly"  name="linkmanusername" value='<s:property value="convenerusername"/>' class="easyui-validatebox" panelWidth="200px" validType="length[2,30]" required='true' missingMessage='请填写会议联系人'>
                &nbsp;<a style="border:thin solid #D3D3D3;border-radius:9px;padding:6px;background-color:rgb(245, 245, 245);" href="javascript:void(0)" class="linkbh14" onclick="checkOffpersonId('asselinkmanuserid')">选择用户</a>
            </div>
            <div class="rowElem" style="margin-left: 20px;margin-top: 10px; padding-bottom: 15px;">
                <label>参会人员：</label>
                 <input type="hidden"  id="messageuserinfoid" value=""/> 
                <textarea id="messageuserinfo" onfocus="userCount()" readonly="readonly" name="fromidAll" cols="40"  rows="2" style="resize: none;" class="easyui-validatebox" panelWidth="200px" validType="ckeckuser" required='true' missingMessage='请填写参会人员'></textarea>
            	&nbsp;<a style="border:thin solid #D3D3D3;border-radius:9px;padding:6px;background-color:rgb(245, 245, 245);" href="javascript:void(0)" class="linkbh14" onclick="addUser()">选择用户</a>
            </div>
            <div class="rowElem" style="margin-left: 20px;margin-top: 10px;">
                <label>参会人数：</label>
                <input id="shouldman" name="asseroomapply.shouldman" value="0" readonly="readonly" class="easyui-validatebox" panelWidth="200px" validType="count" >
            </div>
            <div class="rowElem" style="margin-left: 20px;margin-top: 10px;">
                <label>备注：</label>
                <input id="demo" name="asseroomapply.asseroomapplydemo"  class="easyui-validatebox" panelWidth="200px" validType="length[2,100]" />
            </div>
            
            
            
            <div class="rowElem" style="margin-top: 20px;">
           		<label style="font-weight: bold;">会议时间</label>
           		
           		
            </div>            
 
 
            <div class="rowElem" style="margin-left: 20px;margin-top: 10px;">
                <label>开始时间：</label>
                <input id="starttime"   name="asseroomapply.start" class="easyui-validatebox" panelWidth="200px" validType="startTime" required='true' missingMessage='请填写会议开始时间'>
            </div>
            
            <div class="rowElem" style="margin-left: 20px;margin-top: 10px;">
                <label>结束时间：</label>
                <input id="endtime"   name="asseroomapply.end"class="easyui-validatebox" panelWidth="200px" validType="endTime"   required='true' missingMessage='请填写会议结束时间'>
            </div>
             
            <div class="rowElem" style="margin-left: 20px;margin-top: 10px;">
                <label>通知方式：</label>
				<s:iterator value="assewarnList" status="assewarnList">
				<input name="warnMenthod"  style="width: 20px;"class='checkboxs' id="warnid" type='checkbox'   value="<s:property value='warnid'/>" checked="checked"/>&nbsp;<s:property value='assewarnname'/>
				</s:iterator>
            </div>
            
            <div class="rowElem" style="margin-left: 20px;margin-top: 10px;">
                <label>通知时间：</label>
               	<input id="data" class='checkboxs' name='asseroomapply.warnData'  type='checkbox' checked="checked" />&nbsp;立即通知 
               	<input style="margin-left: 20px;" id="warn" class='checkboxs' name='Data' onclick="isSelect(this.checked)"  type='checkbox' />&nbsp;定时通知
                <input style="clear:right;float: right;margin-right: 220px;" name='asseroomapply.warnData' id="warntime" onblur="getwarnDate()" disabled="disabled" class="easyui-validatebox" panelWidth="200px" validType="warnTime" required='true' missingMessage='请填写通知时间'>
            </div>

   		</form> 
         
        </div>
        
        
    </div>
  </body>
</html>
