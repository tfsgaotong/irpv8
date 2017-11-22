<%@page import="com.tfs.irp.util.SysConfigUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!doctype html>
<html lang="en" class="no-js">
<head>
<meta charset="utf-8">
<title>预约会议室</title>
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />
<!-- 
<script type="text/javascript" src="<%=rootPath %>admin/js/fullCalendar/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>admin/js/modernizr.custom.js"></script>
<script type="text/javascript" src="<%=rootPath %>admin/js/easyui-lang-zh_CN.js"></script>
<script type='text/javascript' src='<%=rootPath%>view/js/fullCalendar/js/fullcal/fullcalendar.js'></script>
 -->
<!-- 
<script type="text/javascript" src="<%=rootPath%>admin/js/fullCalendar/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>admin/js/fullCalendar/js/jquery-ui-1.8.6.custom.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>admin/js/fullCalendar/js/jquery-ui-timepicker-addon.js"></script>
<link rel="stylesheet" href="<%=rootPath%>admin/css/fullCalendar/css/redmond/jquery-ui-1.8.1.custom.css">
 -->
<!-- Jquery and Jquery UI-->
<!-- FullCalender-->
</head>
<body>
<script type="text/javascript" src="admin/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>admin/js/jquery.easyui.min.js"></script>
<!-- 
 -->
 
	<script type="text/javascript">
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
                return $.fullCalendar.parseDate(value)>$.fullCalendar.parseDate(starttime)?true:false;  
            },  
            message : '结束时间必须大于开始时间'  
        },
         warnTime : {// 通知时间 
            validator : function(value) {
            var starttime=$("#starttime").val();
            if($.fullCalendar.parseDate(value)>new Date()&&$.fullCalendar.parseDate(value)<$.fullCalendar.parseDate(starttime))  
                return true;  
            },  
            message : '通知时间必须大于当前时间小于会议开始时间'  
        },
         count : {// 通知时间 
            validator : function(value) {
            var num=($("#people").html())*1;
            if((value*1)<=num)  
                return true;  
            },  
            message : '会议室容纳人数小于参会人员人数'  
        },
	  
	});
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
	//全局变量
	var addJsonFileList = new Array();
	var editor=null;
	function saveAttacthedByLeave(){ 
		var str=$.ajax({
			type:'post',
			dataType: "json",
			url:'<%=rootPath %>site/client_to_save_attached.action?isqusertionordoc=2', 
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
	function addUser(flag){
		//获得内容
		var result = findSelectUserContent('init=0&ismaxamount=false'); 
		//初始化弹出框
		lhbDialog = $.dialog({
			id: 'selectUser',
			title:'选择用户',
			content: result,
			max: false,
		    min: false,
			ok: function(){
				$('#dialogPageForm').form('submit',{
	    			url : "<%=rootPath%>microblog/sendmessageselect.action",
	    			success:function(usernames){
	    				if(usernames!=2){
	    					$("#messageuserinfo").val('');
	    					if(flag==1){
	    						var name=usernames.split(",")[0];
	    						$('#asselinkmanuserid').val(name);
	    					}else if(flag==2){
	    						$('#messageuserinfo').val($('#messageuserinfo').val()+usernames);
	    						userCount();
	    					}else{
	    						var name=usernames.split(",")[0];
	    						$('#asseconveneruserid').val(name);
	    					}
	    					
	    				}else{
	    					$('#shouldman').val(2);
	    					if(flag==1){
	    						$('#asselinkmanuserid').val('');
	    					}else if(flag==2){
	    						$("#messageuserinfo").val('');
	    						$('#shouldman').val("0");
	    					}else{
	    						$('#asselinkmanuserid').val('');
	    					}
	    				}
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
<div id="wrap">
		<script type="text/javascript">
	//$(document).ready(function () {
	//function s(){
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
			closeText:"关闭",
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
			closeText:"关闭",
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
			closeText:"关闭",
			currentText:"现在时间",
			onClose: function (dateText, inst) {
		},
		    onSelect: function (selectedDateTime) {
		        $('#warntime').datetimepicker('option', 'maxDate', $("#starttime").datetimepicker('getDate'));
		        var selectdate = $.fullCalendar.formatDate(date, "yyyy-MM-dd");
		        $('#warntime').datetimepicker('option', 'minDate',selectdate);
		    }
		    
		});
		var selectdate = $.fullCalendar.formatDate(new Date(), "yyyy-MM-dd HH:mm");
		$("#starttime").datetimepicker('setDate', selectdate);//给时间空间赋值
		$("#endtime").datetimepicker('setDate', selectdate);//给时间空间赋值
		$("#warntime").datetimepicker('setDate', selectdate);//给时间空间赋值
		$("#warn").val($("#warntime").val());
		$("#assename").val('');
		$("#asseroomapplytypeid").val('');
		$("#asseroomcontent").val('');
		$("#shouldman").val('0');
		$("#messageuserinfo").val('');
		$("#demo").val('');
		$("#data").val($.fullCalendar.formatDate(new Date(), "yyyy-MM-dd HH:mm"));
		$("#asseroomsbids").val('');
		changeAsseroom();
		//}
	//});
	/*
		改变会议室，清空设备
	*/
	function changeAsseroom(){
		var asseroomid=$("#asseroomid").val();
		$.ajax({
			type: "POST", //使用post方法访问后台
			url: "<%=rootPath %>asseroomapply/findoneasseroom.action", //要访问的后台地址
			data:{asseroomid:asseroomid}, //要发送的数据
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
	获取会议室详细信息
	*/
	function selectAsseroom(flag){
		var asseroomid=$("#asseroomid").val();
		var sbid=$("#asseroomsbids").val();
		var result = $.ajax({
			url: '<%=rootPath %>asseroomapply/findasseroominfo.action',
			dataType: "json",
		    data: {
		    	asseroomid:asseroomid,sbids_1:sbid
		    },
		    async: false,
		    cache: false
		}).responseText; 
		//alert("result:"+result);
		if(flag==1){
			$.dialog({
				title:'会议室详情',
				content: result,
				width:"300px",
				height:"400px", 
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
</script>
<style type='text/css'>
    .ui-datepicker
    {
        width: 23em;
        padding: .2em .2em 0;
        font-size: 70%;
        display: none;
    }
    
    #calendar
    {
        width: 900px;
        margin: 0 auto;
    }
    #loading
    {
        top: 0px;
        right: 0px;
    }
    
    .tooltip
    {
        padding-bottom: 25px;
        padding-left: 25px;
        width: 100px !important;
        padding-right: 25px;
        display: none;
        background: #999;
        height: 70px;
        color: red;
        font-size: 12px;
        padding-top: 25px;
        z-order: 10;
    }
    .ui-timepicker-div .ui-widget-header
    {
        margin-bottom: 8px;
    }
    .ui-timepicker-div dl
    {
        text-align: left;
    }
    .ui-timepicker-div dl dt
    {
        height: 25px;
        margin-bottom: -25px;
    }
    .ui-timepicker-div dl dd
    {
        margin: 0 10px 10px 65px;
    }
    .ui-timepicker-div td
    {
        font-size: 90%;
    }
    .ui-tpicker-grid-label
    {
        background: none;
        border: none;
        margin: 0;
        padding: 0;
    }
    .ui-timepicker-rtl
    {
        direction: rtl;
    }
    .ui-timepicker-rtl dl
    {
        text-align: right;
    }
    .ui-timepicker-rtl dl dd
    {
        margin: 0 65px 10px 10px;
    }
</style>
	<form id="submit" method="post">
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cad9ea">
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">会议名称</td>
         <td bgcolor="#F5FAFE" class="main_bright"><input id="assename"
				name="asseroomapply.assename" class="easyui-validatebox"
				panelWidth="200px" validType="length[2,30]" required='true'
				missingMessage='请填写会议的名称'
				value="<s:property value="asseroomapply.assename"/>"></td>
        </tr>
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">会议类型</td>
         <td bgcolor="#F5FAFE" class="main_bright">
         	 <select id="asseroomapplytypeid"
				name="asseroomapply.asseroomapplytypeid">
				<s:iterator value="assetypeList" status="assetypeList">
					<option value="<s:property value='asseroomapplytypeid'/>">
						<s:property value='assetypename' />
					</option>
				</s:iterator>
			</select>
         </td>
        </tr>
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">召集人</td>
         <td bgcolor="#F5FAFE" class="main_bright">
           <input id="asseconveneruserid"
				name="convenerusername" readonly="readonly"
				value='<s:property value="convenerusername"/>'
				class="easyui-validatebox" panelWidth="200px"
				validType="length[2,30]" required='true' missingMessage='请填写会议召集人'>
			<a href="javascript:void(0)" class="linkbh14" onclick="addUser(3)">选择用户</a>
          </td>
        </tr>
         <tr>
         <td bgcolor="#F5FAFE" class="main_bright">备注</td>
         <td bgcolor="#F5FAFE" class="main_bright"><input id="demo"
				name="asseroomapply.asseroomapplydemo" class="easyui-validatebox"
				panelWidth="200px" validType="length[2,100]" /></td>
        </tr>
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">联系人 </td>
         <td bgcolor="#F5FAFE" class="main_bright"><input id="asselinkmanuserid"
				readonly="readonly" name="linkmanusername"
				value='<s:property value="convenerusername"/>'
				class="easyui-validatebox" panelWidth="200px"
				validType="length[2,30]" required='true' missingMessage='请填写会议联系人'>
			<a href="javascript:void(0)" class="linkbh14" onclick="addUser(1)">选择用户</a></td>
        </tr>
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">会议室</td>
          <td bgcolor="#F5FAFE" class="main_bright">
          <select style="float:left;" id="asseroomid" name="asseroomapply.asseroomid" onclick="changeAsseroom()">
			<s:iterator value="asseroomList" status="asseroomList">
			<option value="<s:property value='asseroomid'/>" ><s:property value='asseroomname'/></option>
			</s:iterator>
		</select>	        
				&nbsp;<a style="border:thin solid #D3D3D3;border-radius:9px;padding:6px;background-color:rgb(245, 245, 245);" href="javascript:void(0)" class="linkbh14" onclick="selectAsseroom(1)">会议室详情</a>
         	<input type="text" id="asseroomsbids" name="asseroomapply.asseroomsbids"/>
            <input type="text" id="people" value="<s:property value='people'/>"/>
          </td>
        </tr>
          <tr>
         <td bgcolor="#F5FAFE" class="main_bright">会议内容 </td>
         <td bgcolor="#F5FAFE" class="main_bright">
         	<textarea id="asseroomcontent" 
				name="asseroomapply.asseroomcontent" class="easyui-validatebox"
				panelWidth="200px" validType="length[2,100]" required='true'
				missingMessage='请填写会议内容'></textarea>
         </td>
        </tr>
          <tr>
         <td bgcolor="#F5FAFE" class="main_bright">开始时间 </td>
         <td bgcolor="#F5FAFE" class="main_bright"><input id="starttime"
				name="asseroomapply.start" class="easyui-validatebox"
				panelWidth="200px" validType="startTime" required='true'
				missingMessage='请填写会议开始时间'>
		</td>
        </tr>
          <tr>
         <td bgcolor="#F5FAFE" class="main_bright">结束时间 </td>
         <td bgcolor="#F5FAFE" class="main_bright"><input id="endtime" name="asseroomapply.end"
				class="easyui-validatebox" panelWidth="200px" validType="endTime"
				required='true' missingMessage='请填写会议结束时间'>
				</td>
        </tr>
          <tr>
         <td bgcolor="#F5FAFE" class="main_bright">参会人员 </td>
         <td bgcolor="#F5FAFE" class="main_bright"><textarea id="messageuserinfo" onfocus="userCount()"
				readonly="readonly" name="fromidAll" 
				style="resize: none;" class="easyui-validatebox" panelWidth="200px"
				validType="ckeckuser" required='true' missingMessage='请填写参会人员'></textarea>
			<a href="javascript:void(0)" class="linkbh14" onclick="addUser(2)">选择用户</a>
			</td>
        </tr>
          <tr>
         <td bgcolor="#F5FAFE" class="main_bright">参会人数 </td>
         <td bgcolor="#F5FAFE" class="main_bright"><input id="shouldman"
				name="asseroomapply.shouldman" value="0" readonly="readonly"
				class="easyui-validatebox" panelWidth="200px" validType="count"></td>
        </tr>
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">通知类型 </td>
          <td bgcolor="#F5FAFE" class="main_bright">
			<s:iterator value="assewarnList" status="assewarnList">
				<input name='asseroomapply.warnid' id="warnid" type='checkbox'
					value="<s:property value='warnid'/>" checked="checked" />
				<s:property value='assewarnname' />
			</s:iterator>
		</td>
        </tr>
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">通知时间 </td>
          <td bgcolor="#F5FAFE" class="main_bright">
			<input id="data" class='checkboxs' name='asseroomapply.warnData'  type='checkbox' checked="checked" />立即通知&nbsp;&nbsp;&nbsp;
               	<input id="warn" class='checkboxs' name='asseroomapply.warnData' onclick="isSelect(this.checked)"  type='checkbox' value=""/>
                <input id="warntime" disabled="disabled" class="easyui-validatebox" panelWidth="200px" validType="warnTime" required='true' missingMessage='请填写通知时间'>
		</td>
        </tr>
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">附件 </td>
         <td bgcolor="#F5FAFE" class="main_bright">
         	<input type="button" id="attched"
				onclick="saveAttacthedByLeave()" value="选择附件" /> <input
				type="hidden" name="jsonFile" />
			</td>
        </tr>
      </table>
    </form>
</body>
</html>