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
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" >
<title>预约会议室</title>
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css">
<link href="<%=rootPath %>view/css/fullCalendar/css/easyui.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath%>view/css/icon.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath%>view/css/discuz.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=rootPath%>view/css/fullCalendar/css/maincontent.css" type="text/css"></link>
<link rel="stylesheet" href="<%=rootPath%>view/css/fullCalendar/css/mainstructure.css" type="text/css"></link>
<link rel="stylesheet" href="<%=rootPath%>view/css/fullCalendar/css/redmond/jquery-ui-1.8.1.custom.css"/>
<link rel='stylesheet' type='text/css' href='<%=rootPath%>view/js/fullCalendar/js/fullcal/css/fullcalendar.css' />

<!-- Jquery and Jquery UI

<script type="text/javascript" src="<%=rootPath%>view/js/jquery-1.8.0.min.js"></script>
 -->

<script src="<%=rootPath%>view/js/fullCalendar/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="<%=rootPath %>view/js/lhgdialog.min.js" type="text/javascript"></script>
<script src="<%=rootPath %>view/js/fullCalendar/js/jquery.easyui.min.js" type="text/javascript"></script>
<script src="<%=rootPath%>view/js/fullCalendar/js/jquery-ui-1.8.6.custom.min.js" type="text/javascript"></script>



<script src="<%=rootPath %>view/js/modernizr.custom.js"  type="text/javascript"></script>
<script src="<%=rootPath %>view/js/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="<%=rootPath%>view/js/fullCalendar/js/jquery-ui-timepicker-addon.js"  type="text/javascript"></script>
<script src="<%=rootPath%>view/js/fullCalendar/js/formValidator/js/jquery.validationEngine.js" type="text/javascript"></script>
<script src="<%=rootPath%>view/js/fullCalendar/js/formValidator/js/jquery.validationEngine-en.js" type="text/javascript"></script>
<script src='<%=rootPath%>view/js/fullCalendar/js/fullcal/fullcalendar.js'  type='text/javascript'></script>


</head>
<body>

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
 

#table td{
	border: 1px gray solid;
}
#warnid{
	width: 2px;
}
.rowElem{
margin-bottom: 5px;
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
.searchSec{
	width:543px;
}
.searchSec #head_getText{
	width: 441px; -moz-width: 478px;}
.searchSec .getText{-webkit-height: 36px;-moz-height: 40px;
}

</style>

 
	<script type="text/javascript">

	 //会议室查询获取日历
	function query() {
		var view = $('#calendar').fullCalendar('getView');//获取日历的view对象
		var asseroomid=$("#asseroom").val();
		//selFacLoad();  
		$(".cls_gt_dis").remove(); 
		list(view,asseroomid);
	}
		/**
		将封装的数据显示在日历控件中
		 **/
	function list(view,asseroomid){
		var viewStart = $.fullCalendar.formatDate(view.visStart, "yyyy-MM-dd HH:mm:ss");
        var viewEnd = $.fullCalendar.formatDate(view.visEnd, "yyyy-MM-dd HH:mm:ss");
        $("#calendar").fullCalendar('removeEvents');
        var date={ startTime:viewStart, endTime: viewEnd, searchWord: asseroomid };
        $.post('<%=rootPath%>asseroomapply/asseroomapplylist.action',date, function(data) {
			var resultCollection = jQuery.parseJSON(data);
			for ( var i in resultCollection) {
				var a = resultCollection[i];
				for ( var j in a) {
					if (j == "start" || j == "end") {
						var date = new Date(a[j].time);
						a[j] = date;
					}
				}
			}
			var resultCollection_1=resultCollection;
			var inde_num = 0;  
			var s_str = "";  
			var  data_num = ""; 
			var  data_num_1 = "";
			
		 
			if(resultCollection!=""){ 
			
				$.each(resultCollection, function(index, term) { 
				 	var _start = $.fullCalendar.formatDate(term.start, "yyyy-MM-dd"); 
					var count=0;
					for ( var i in resultCollection_1) {
						var a = resultCollection_1[i];
						for ( var j in a) {
							if (j == "start") {
								var _start_1 = $.fullCalendar.formatDate(a[j], "yyyy-MM-dd");
								if(_start_1==_start){
									count++;
								} 
							}
							if(j=="sid"){
								if(a[j]==term.sid&&count<4){
									$("#calendar").fullCalendar('renderEvent', term,true);
								}
							}
						}
					}
					var s_1=_start +":"+count;
					var index=data_num.indexOf(s_1);
					if(index<0){
						data_num = data_num+","+s_1;
					}
				}); 
			} 
			 
			//某个日期，会议数量 
 			var disdatenum = data_num.substring(1); 
 			for(var i = 0;i<=41;i++){ 
				var clsstr = ".fc-day"+i;
				var data_s = $(clsstr).find("div[class='fc-day-number']").find("a:last").attr("data"); 
	 			var jsonstr = disdatenum.split(",");
	 			var huinum = 0;
	 			//var data_s_d = new Date(data_s);  
	 			var s=data_s.split("-");
				s[1]=(s[1]<10?"0"+s[1]:s[1]);
				s[2]=(s[2]<10?"0"+s[2]:s[2]);
				data_s=s[0]+"-"+s[1]+"-"+s[2];
				var data_s_d = $.fullCalendar.parseDate(data_s,"yyyy-MM-dd");
				var data_s_d_dd = $.fullCalendar.formatDate(data_s_d, "yyyy-MM-dd"); 
				
			 	$.each(jsonstr,function(_index,_obj){ 
			 		var data_s_s = $.fullCalendar.parseDate(_obj.split(":")[0],"yyyy-MM-dd");
 					var data_s_s_dd = $.fullCalendar.formatDate(data_s_s, "yyyy-MM-dd"); 
			 		if(data_s_d_dd==data_s_s_dd){
			 			huinum = _obj.split(":")[1];
			 		} 
			 	});
				
				if(huinum == undefined){
					huinum = 0;
				}  
				
			 	if(i>=0 && i<=6){  
			 		$(clsstr).append("<div class=\"cls_gt_dis\" style=\"position:absolute;top:110px;width:14%;text-align:center;cursor:pointer;\" onclick=\"linkRC('"+data_s+"')\">会议:"+huinum+"，详情</div>");
			 	}else if(i>=7 && i<=13){
			 	
			 		$(clsstr).append("<div class=\"cls_gt_dis\" style=\"position:absolute;top:225px;width:14%;text-align:center;cursor:pointer;\" onclick=\"linkRC('"+data_s+"')\">会议:"+huinum+"，详情</div>");
			 	}else if(i>=14 && i<=20){
			 	
			 		$(clsstr).append("<div class=\"cls_gt_dis\" style=\"position:absolute;top:340px;width:14%;text-align:center;cursor:pointer;\" onclick=\"linkRC('"+data_s+"')\">会议:"+huinum+"，详情</div>");
			 	}else if(i>=21 && i<=27){
			 	
			 		$(clsstr).append("<div class=\"cls_gt_dis\" style=\"position:absolute;top:455px;width:14%;text-align:center;cursor:pointer;\" onclick=\"linkRC('"+data_s+"')\">会议:"+huinum+"，详情</div>");
			 	}else if(i>=28 && i<=34){
			 	
			 		$(clsstr).append("<div class=\"cls_gt_dis\" style=\"position:absolute;top:570px;width:14%;text-align:center;cursor:pointer;\" onclick=\"linkRC('"+data_s+"')\">会议:"+huinum+"，详情</div>");
			 	}else if(i>=35 && i<=41){
			 	
			 		$(clsstr).append("<div class=\"cls_gt_dis\" style=\"position:absolute;top:685px;width:14%;text-align:center;cursor:pointer;\" onclick=\"linkRC('"+data_s+"')\">会议:"+huinum+"，详情</div>");
			 	} 
 			}  
 	 
 			 
			
		}); //把从后台取出的数据进行封装以后在页面上以fullCalendar的方式进行显示
	}
	/* 链接到日程详情  */
	function linkRC(_selectdate){
		$('#calendar').fullCalendar('changeView',"agendaDay");
		var selectdate = $.fullCalendar.parseDate(_selectdate,"yyyy-MM-dd");
		var date=new Date();
		if(selectdate.getFullYear()==date.getFullYear()&&selectdate.getMonth()==date.getMonth()&&selectdate.getDate()==date.getDate()){
			return false;
		}else{
			$('#calendar').fullCalendar('gotoDate',selectdate.getFullYear(),selectdate.getMonth(),selectdate.getDate());
		}
	}
	/**
	删除预约
	**/
	function deleteApply(isDelete,event){
	//获得内容
		var result = $.ajax({
			url: '<%=rootPath %>asseroomapply/findone.action',
			dataType: "json",
		    data: {
		    	asseroomapplyid:event.sid
		    },
		    async: false,
		    cache: false
		}).responseText; 
		if(isDelete==1){
			$.dialog({
				title:'会议室预约详情',
				content: result,
				width:"auto",
				height:"auto", 
				ok: function(){ 
				   var aa =window.confirm("警告:确定要删除记录，删除后无法恢复！");
		            if (aa) {
		              var para = { asseroomapplyid: event.sid };
		               $.ajax({
		                     type: "POST", //使用post方法访问后台
		                      url: "<%=rootPath %>asseroomapply/deleteapply.action", //要访问的后台地址
		                       data: para, //要发送的数据
		                       success: function (data) {
		                         //window.alert("成功","取消会议成功");
		                                    //对话框里面的数据提交完成，data为操作结果
		                           $('#calendar').fullCalendar('removeEvents', event.id);
		                            }
		                     });
						}
				
			    },
			    okVal:'删除',
			    cancelVal: '关闭',
			    cancel: true,
			    lock: true,
			    padding: 0
			});
		}else{
			//初始化弹出框
			$.dialog({
				title:'会议室预约详情',
				content: result,
				width:"auto",
				height:"auto", 
			    cancelVal: '关闭',
			    cancel: true,
			    lock: true,
			    padding: 0
			});
		}	 
	}
	</script>
	<s:include value="../include/client_head2.jsp"></s:include>
<section class="mainBox">



	<nav class="privateNav">
    	<dl>
        </dl>
    </nav>
</section>
   <div id="wrap">
   
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
		$("#addhelper").hide();  
		/* 加载日历控件   */
		selFacLoad();   
		
		//goto date function
		if ($.browser.msie) {
		    $("#calendar .fc-header-right table td:eq(0)").before('<td><div class="ui-state-default ui-corner-left ui-corner-right" style="border-right:0px;padding:1px 3px 2px;" ><input type="text" id="selecteddate" size="10" style="padding:0px;"></div></td><td><div class="ui-state-default ui-corner-left ui-corner-right"><a><span id="selectdate" class="ui-icon ui-icon-search">goto</span></a></div></td><td><span class="fc-header-space"></span></td>');
		} else {
		    $("#calendar .fc-header-right table td:eq(0)").before('<td><div class="ui-state-default ui-corner-left ui-corner-right" style="border-right:0px;padding:3px 2px 4px;" ><input type="text" id="selecteddate" size="10" style="padding:0px;"></div></td><td><div class="ui-state-default ui-corner-left ui-corner-right"><a><span id="selectdate" class="ui-icon ui-icon-search">goto</span></a></div></td><td><span class="fc-header-space"></span></td>');
		}
		$("#selecteddate").datepicker({
			dateFormat : 'yy-mm-dd',
			monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
			monthNamesShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
			dayNames: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
			dayNamesShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
			dayNamesMin:["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
			closeText:"关闭",
			currentText:"现在时间",
			beforeShow : function(input, instant) {
			setTimeout(function() {$('#ui-datepicker-div').css("z-index", 15);}, 100);
			}
		});
		$("#selectdate").click(function() {
		 
		
			var selectdstr = $("#selecteddate").val();
			var selectdate = $.fullCalendar.parseDate(selectdstr,"yyyy-MM-dd");
			$('#calendar').fullCalendar('gotoDate',selectdate.getFullYear(),selectdate.getMonth(),selectdate.getDate());
		});
		
		$("#calendar .fc-header-left table td:eq(0)").before('<td><div class="ui-state-default ui-corner-left ui-corner-right" id="selectmeeting"><a><span id="selectdate" onclick="query()" class="ui-icon ui-icon-search" style="float: left;padding-left: 5px; padding-top:1px"></span></a><select id="asseroom" onclick="query()"><option value="all" selected="selected">所有会议室</option><s:iterator value="asseroomList" status="asseroomList"><option value="<s:property value='asseroomid'/>" <s:if test="asseroomid==searchWord">selected</s:if>><s:property value='asseroomname'/></option></s:iterator></select>	</div></td><td><span class="fc-header-space"></span></td>');
		
	});
	
	/* 加载日历控件 */
	function selFacLoad(){
	
			$('#calendar').fullCalendar({ 
				header: {  
				    right: 'prev,next today',
				    center: 'title',
				    left: 'month,agendaWeek,agendaDay'
				},  
				monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
				monthNamesShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
				dayNames: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
				dayNamesShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
				firstDay: 0,
				buttonText: {
				    today: '本月',
				    month: '月',
				    week: '周',
				    day: '日',
				    prev: '上一月',
				    next: '下一月'
				}, 
				viewDisplay: function (view) {//动态把数据查出，按照月份动态查询 
				 	$(".cls_gt_dis").remove(); 
					var asseroomid=$("#asseroom").val();  
				 
					list(view,asseroomid);  
					 
				 	$("a[name='bb_sq']").remove();
				 	$("span[name='bb_sq']").remove();
					$(".ui-state-highlight").css("background-color","");
		 	 	    for(var i = 0;i<=41;i++){ 
						var clsstr = ".fc-day"+i;
		 				$(clsstr).unbind(); 
		 				var datastrs = $(clsstr).find(".fc-day-number").find("a:last").attr("data");
		 				  
						$(clsstr).find("div:eq(0)").prepend("<a name=\"bb_sq\" class=\"cls_s\" href=\"javascript:void(0);\" onclick=\"sqOne('"+datastrs+"')\" style=\"margin-left:-8px;font-size:12px;\" >申请</a><span name=\"bb_sq\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>");
						
					}; 
					
					 
					var datal = new Date(); 
					var lighdate = $.fullCalendar.formatDate(datal,"yyyy-MM-dd");
				 
 		 			for(var i = 0;i<=41;i++){ 
 		 				 
		 				var clsstr = ".fc-day"+i;  
		 				var clasis = $(clsstr).is(".ui-state-highlight");
		 				
		 				var classlights = $(clsstr).find(".fc-day-number").find("a:last").attr("data");
		 				  
		 				var jsondas = "";
		 				var clsl_1 = classlights.split("-");
		 				jsondas = clsl_1[0];
		 				if(clsl_1[1].length<2){
		 					jsondas = jsondas + "-0" +clsl_1[1];
		 				}else{
		 					jsondas = jsondas + "-" +clsl_1[1];
		 				}
		 				if(clsl_1[2].length<2){
		 					jsondas = jsondas + "-0" +clsl_1[2];
		 				}else{
		 					jsondas = jsondas + "-" +clsl_1[2];
		 				} 
		 				
		 				var db_one = $.fullCalendar.parseDate(jsondas,"yyyy-MM-dd");
		 				var db_two = $.fullCalendar.parseDate(lighdate,"yyyy-MM-dd");
		 				
		 				 
		 				var db_onefor = $.fullCalendar.formatDate(db_one,"yyyyMMdd");
		 				var db_twofor = $.fullCalendar.formatDate(db_two,"yyyyMMdd");
		 			 
		 				
		 			  	if(db_one<db_two){
		 			  		$(clsstr).find("div:eq(0)").find(".cls_s").css("display","none");
		 			  		$(clsstr).css({"background-color":"#F0F0F0","cursor":"default"}); 
		 			  	}else{
		 			  		$(clsstr).css("background-color","");
		 			  	}
		 				 
		 			}   
				 	
				 	//selFacLoad(); 
				 	
				},
				theme: true,
				editable: true,
				allDaySlot: false,
				editable: false,//判断该日程能否拖动
				selectHelper:true,
				
				select: function (startDate, endDate, allDay, jsEvent, view) {
				
				 
				 
					sqOneSel(startDate,endDate);
				},
				selectable: true,
				timeFormat: 'HH:mm{ - HH:mm}',
				eventClick: function (event) { 
					var preOrOld=$("#preOrold").val();
					var isDelete=0;
					$.ajax({
						type: "POST", //使用post方法访问后台
						async: true,
						url: "<%=rootPath %>asseroomapply/isDelete.action", //要访问的后台地址
					    data: {asseroomapplyid:event.sid,preOrOld:preOrOld}, //要发送的数据
						success: function (data) {
							isDelete=data;
							deleteApply(isDelete,event);
					    }
					});
				},
				loading: function (bool) { 
				    if (bool) $('#loading').show();
				    else $('#loading').hide();
				},
				eventMouseover: function (calEvent, jsEvent, view) {
				    var fstart = $.fullCalendar.formatDate(calEvent.start, "yyyy/MM/dd HH:mm");
				    var fend = $.fullCalendar.formatDate(calEvent.end, "yyyy/MM/dd HH:mm");
				    $(this).attr('title',"时间："+ fstart + " - " + fend + "会议名称： " + calEvent.fullname+ " ；会议内容: " + calEvent.description);
				    $(this).css('font-weight', 'normal');
				    $(this).tooltip({
				        effect: 'toggle',
				        cancelDefault: true
				    });
				},
				eventMouseout: function (calEvent, jsEvent, view) {
				    $(this).css('font-weight', 'normal');
				},
				eventRender: function (event, element) {
				    var fstart = $.fullCalendar.formatDate(event.start, "HH:mm");
				    var fend = $.fullCalendar.formatDate(event.end, "HH:mm");
				},
				//数据绑定上去后添加相应信息在页面上
				eventAfterRender: function (event, element, view) {
				    var fstart = $.fullCalendar.formatDate(event.start, "HH:mm");
				    var fend = $.fullCalendar.formatDate(event.end, "HH:mm");
				    if (view.name == "month") {//按月份
				        var evtcontent = '<div class="fc-event-vert"  style="width:130px; white-space:nowrap;overflow:hidden;-moz-text-overflow:ellipsis;text-overflow:ellipsis;"><a>';
				        evtcontent = evtcontent + '<span class="fc-event-titlebg">' + fstart + " - " + fend+";   "+ event.fullname + '</span>';
				        evtcontent = evtcontent + '</a><div class="ui-resizable-handle ui-resizable-e"></div></div>';
				        element.html(evtcontent);
				    } else if (view.name == "agendaWeek") {//按周
				        var evtcontent = '<div class="fc-event-vert"  ><a>';
				        evtcontent = evtcontent + '<span class="fc-event-titlebg" style="width:115px; white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">' + fstart + " - " + fend + '</span>';
				        evtcontent = evtcontent + '<span>会议名称: ' + event.fullname + '</span>';
				        evtcontent = evtcontent + '<span>会议室名称: ' + event.confname + '</span>';
				        evtcontent = evtcontent + '<span>会议内容: ' + event.description + '</span>';
				        evtcontent = evtcontent + '</a><div class="ui-resizable-handle ui-resizable-e"></div></div>';
				        element.html(evtcontent);
				       /* evtcontent = evtcontent + '<span class="fc-event-titlebg">' + fstart + " - " + fend+";   "+ event.fullname + '</span>';
				        evtcontent = evtcontent + '</a><div class="ui-resizable-handle ui-resizable-e"></div></div>';
				        element.html(evtcontent);*/
				    } else if (view.name == "agendaDay") {//按日
				         var evtcontent = '<div class="fc-event-vert"><a>';
				        evtcontent = evtcontent + '<span class="fc-event-titlebg"  style="width:130px; white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">' + fstart + " - " + fend + '</span>';
				        evtcontent = evtcontent + '<span>会议名称: ' + event.fullname + '</span>';
				        evtcontent = evtcontent + '<span>会议室名称: ' + event.confname + '</span>';
				        evtcontent = evtcontent + '<span>会议内容: ' + event.description + '</span>';
				        evtcontent = evtcontent + '</a><div class="ui-resizable-handle ui-resizable-e"></div></div>';
				        element.html(evtcontent);
				       /* evtcontent = evtcontent + '<span class="fc-event-titlebg">' + fstart + " - " + fend+";   "+ event.fullname + '</span>';
				        evtcontent = evtcontent + '</a><div class="ui-resizable-handle ui-resizable-e"></div></div>';
				        element.html(evtcontent);*/
				    }
				},
				eventDragStart: function (event, jsEvent, ui, view) {
				    ui.helper.draggable("option", "revert", true);
				},
				eventDragStop: function (event, jsEvent, ui, view) {
				},
				eventDrop: function (event, dayDelta, minuteDelta, allDay, revertFunc, jsEvent, ui, view) {
				    if (1 == 1 || 2 == event.uid) {
				        var schdata = { startdate: event.starttime, enddate: event.end, confid: event.confid, sid: event.sid };
				    } else {
				        revertFunc();
				    }
				
				},
				eventResize: function (event, dayDelta, minuteDelta, revertFunc) {
				    if (1 == 1 || 2 == event.uid) {
				        var schdata = { startdate: event.starttime, enddate: event.end, confid: event.confid, sid: event.sid };
				    } else {
				        revertFunc();
				    }
				}
			});
			
	
	}
	/* 日程弹出框 */
	function sqOneSel(_startDate,_endDate){
				var sdate = $.fullCalendar.formatDate(_startDate, "yyyy-MM-dd HH:mm");
				var edate = $.fullCalendar.formatDate(_endDate, "yyyy-MM-dd HH:mm"); 
				//开始时间
				var starttime=sdate;
				//结束时间
				var endtime=edate;
				//提醒时间
				var warntime=sdate;
				//会议室id
				var asseroomid=$("#asseroom").val();
				//changeAsseroom();
				var view = $('#calendar').fullCalendar('getView');//获取日历的view对象
				$(".ui-datepicker").css("display","");
				var str=$.ajax({//去后台拿取会议室预约索要用到的信息
					type:'post',
					dataType: "json",	     
					url:'<%=rootPath%>asseroomapply/toadd.action',
					data:{
						searchWord:asseroomid,start:starttime,end:endtime,warn:warntime,preOrOld:0,
					},
					async: false,
					cache: false
				}).responseText; 
						$.dialog({
							height:350,
							width: 800,
							title: '预约会议室---' + sdate,
							content:str,
							modal:true,
							ok: function(){ 
							$('input[name="jsonFile"]').val(JSON.stringify(addJsonFileList));
							var submit=$("#submit").serialize();
							var isValid = $("#submit").form('validate');
							if(!isValid)
								return false;
							$.ajax({
								type: "POST", //使用post方法访问后台
								url: "<%=rootPath %>asseroomapply/addapply.action", //要访问的后台地址
								data: submit, //要发送的数据
								success: function (data) {
									if(data==2){
										 $.messager.alert("失败","申请失败");
									}else{
										$.messager.alert("成功","恭喜你，申请成功");
									}
									var asseroomid=$("#asseroom").val();
									//query();
									$(".cls_gt_dis").remove(); 
								   list(view,asseroomid);
								}
							 });
							 $(this).dialog("close");
						    },
						    okVal:'申请',
						    cancelVal: '取消',
						    cancel: true,
						    lock: true,
						    padding: 0 
					});  
	}
	
	
	/* 申请会议室弹出框  */
	function sqOne(_startDate,_endDate){
				var s=_startDate.split("-");
				s[1]=(s[1]<10?"0"+s[1]:s[1]);
				s[2]=(s[2]<10?"0"+s[2]:s[2]);
				_startDate=s[0]+"-"+s[1]+"-"+s[2];
				var selectdateui = $.fullCalendar.parseDate(_startDate,"yyyy-MM-dd");
				var sdate = $.fullCalendar.formatDate(selectdateui, "yyyy-MM-dd HH:mm");
				//开始时间
				var starttime=sdate;
				//结束时间
				var endtime=sdate;
				//提醒时间
				var warntime=sdate;
				//会议室id
				var asseroomid=$("#asseroom").val();
				//changeAsseroom();
				var view = $('#calendar').fullCalendar('getView');//获取日历的view对象
				$(".ui-datepicker").css("display","");
				var str=$.ajax({//去后台拿取会议室预约索要用到的信息
					type:'post',
					dataType: "json",	     
					url:'<%=rootPath%>asseroomapply/toadd.action',
					data:{
						searchWord:asseroomid,start:starttime,end:endtime,warn:warntime,preOrOld:0,
					},
					async: false,
					cache: false
				}).responseText; 
						$.dialog({
							height:350,
							width: 800,
							title: '预约会议室---' + _startDate,
							content:str,
							ok: function(){ 
							$('input[name="jsonFile"]').val(JSON.stringify(addJsonFileList));
							var submit=$("#submit").serialize();
							var isValid = $("#submit").form('validate');
							if(!isValid)
								return false;
							$.ajax({
								type: "POST", //使用post方法访问后台
								url: "<%=rootPath %>asseroomapply/addapply.action", //要访问的后台地址
								data: submit, //要发送的数据
								success: function (data) {
									if(data==2){
										 $.messager.alert("失败","申请失败");
									}else{
										$.messager.alert("成功","恭喜你，申请成功");
									}
									var asseroomid=$("#asseroom").val();
									//query();
									$(".cls_gt_dis").remove(); 
								   list(view,asseroomid);
								}
							 });
							 $(this).dialog("close");
						    },
						    okVal:'申请',
						    cancelVal: '取消',
						    cancel: true,
						    lock: true,
						    padding: 0 
					});  
	}
	
		
</script>     

		<!-- con 1  --> 
        <div id="calendar">
        </div>
        <!-- <a href="javascript:void(0)" class="linkbh14" onclick="jumpData(1)">选择用户</a> -->
        <div>
			 <input type="hidden" id="preOrold" name="preOrOld" value="<s:property value="preOrOld"/>">
			</div>
			
			
			
			
			
			 <!--  -->
			<style type="text/css">
			.rowElem{
				border-bottom:  #E6E6FA	thin solid;
				line-height: 30px;
			
			}	
			
			</style>
        
        
        
        
        
        
        
        
    </div>
<s:include value="../include/client_foot.jsp"></s:include>
</body>
</html>