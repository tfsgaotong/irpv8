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
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<title>日程</title>
<link rel="Bookmark" href="<%=rootPath %>client/images/24pinico.ico" />
<link rel="Shortcut Icon" href="<%=rootPath %>client/images/24pinico.ico" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/client/css/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/client/css/icon.css" />
<style type="text/css">
body{
	behavior:url(<%=rootPath%>client/js/hover.htc);
}
<style type="text/css">

table,td{
				border:1px solid #9DABCE;
				border-collapse:collapse;
			}
font{
margin-left: 3px;
}
.today{
background-color: blue;
}
.handcss{
cursor: hand;
}
</style>
<script type="text/javascript" src="<%=rootPath%>client/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
$(function(){
	var dCurDate = new Date();
	$("#selyear").val(dCurDate.getFullYear());
	$("#selmonth").val(dCurDate.getMonth()+1);
	var htmlstr=fDrawCal(dCurDate.getFullYear(), dCurDate.getMonth()+1, 30, 30, "20px", "bold", 1);
	$("#calendarDiv").html(htmlstr);
	for(var i=0;i<$("#daytable").find("td").length;i++){
		if($("#daytable").find("td:eq("+i+")").find("font").text()==dCurDate.getDate()){
			var sattr=$("#daytable").find("td:eq("+i+")").addClass("today");
		}
	}
	lookthing();
})

function lookthing(){
	 $('#daytable tbody tr td').hover(function() {
		
		 //查询
     }, function() {
    	 //离开
     });
	 $('#daytable tr td').click(function() {
		 //新建
		//获得内容
			var result = $.ajax({
				url: '<%=rootPath%>client/schedule/schedule_addth.jsp',
				dataType: "json",
			    async: false,
			    cache: false
			}).responseText;
			//初始化弹出框
			$.dialog({
				title:'新建日程',
				content: result,
				max: false,
			    min: false, 
			    lock:true,
			    width:500,
			    height:200,
			    ok: function(){
			    	
			    },
			    okVal:'提交',
			    cancelVal: '关闭',
			    cancel: true
			});
     });
}

function changedate(){
	var yearc=$("#selyear").val();
	var monthc=$("#selmonth").val();
	var htmlstr=fDrawCal(yearc, monthc, 30, 30, "20px", "bold", 1);
	$("#calendarDiv").html(htmlstr);
}

var dDate = new Date();
var dCurMonth = dDate.getMonth();
var dCurDayOfMonth = dDate.getDate();
var dCurYear = dDate.getFullYear();
var objPrevElement = new Object();

function fToggleColor(myElement) {
	var toggleColor = "#ff0000";
	if (myElement.id == "calDateText") {
		if (myElement.color == toggleColor) {
			myElement.color = "";
		} else {
			myElement.color = toggleColor;
		   }
		} else if (myElement.id == "calCell") {
			for (var i in myElement.children) {
				if (myElement.children[i].id == "calDateText") {
					if (myElement.children[i].color == toggleColor) {
						myElement.children[i].color = "";
					} else {
						myElement.children[i].color = toggleColor;
		            }
		         }
		      }
	    }
}

function fSetSelectedDay(myElement){
	if (myElement.id == "calCell") {
		if (!isNaN(parseInt(myElement.children["calDateText"].innerText))) {
			myElement.bgColor = "#c0c0c0";
			objPrevElement.bgColor = "";
			document.all.calSelectedDate.value = parseInt(myElement.children["calDateText"].innerText);
			objPrevElement = myElement;
	      }
   }
}
function fGetDaysInMonth(iMonth, iYear) {
	var dPrevDate = new Date(iYear, iMonth, 0);
	return dPrevDate.getDate();
}
function fBuildCal(iYear, iMonth, iDayStyle) {
var aMonth = new Array();
aMonth[0] = new Array(7);
aMonth[1] = new Array(7);
aMonth[2] = new Array(7);
aMonth[3] = new Array(7);
aMonth[4] = new Array(7);
aMonth[5] = new Array(7);
aMonth[6] = new Array(7);
var dCalDate = new Date(iYear, iMonth-1, 1);
var iDayOfFirst = dCalDate.getDay();
var iDaysInMonth = fGetDaysInMonth(iMonth, iYear);
var iVarDate = 1;
var i, d, w;
if (iDayStyle == 2) {
aMonth[0][0] = "日";
aMonth[0][1] = "一";
aMonth[0][2] = "二";
aMonth[0][3] = "三";
aMonth[0][4] = "四";
aMonth[0][5] = "五";
aMonth[0][6] = "六";
} else if (iDayStyle == 1) {
aMonth[0][0] = "日";
aMonth[0][1] = "一";
aMonth[0][2] = "二";
aMonth[0][3] = "三";
aMonth[0][4] = "四";
aMonth[0][5] = "五";
aMonth[0][6] = "六";
} else {
aMonth[0][0] = "日";
aMonth[0][1] = "一";
aMonth[0][2] = "二";
aMonth[0][3] = "三";
aMonth[0][4] = "四";
aMonth[0][5] = "五";
aMonth[0][6] = "六";
}
	for (d = iDayOfFirst; d < 7; d++) {
		aMonth[1][d] = iVarDate;
		iVarDate++;
	}
	for (w = 2; w < 7; w++) {
		for (d = 0; d < 7; d++) {
			if (iVarDate <= iDaysInMonth) {
			aMonth[w][d] = iVarDate;
			iVarDate++;
	      }
	   }
	}
return aMonth;
}
function fDrawCal(iYear, iMonth, iCellWidth, iCellHeight, sDateTextSize, sDateTextWeight, iDayStyle) {
	var myMonth;
	myMonth = fBuildCal(iYear, iMonth, iDayStyle);
	var strHtml="";
	strHtml+="<table id='daytable' border='1'  width='490px;' height='490px;'>";
	strHtml+="<tr>";
	strHtml+="<td align='center'>" + myMonth[0][0] + "</td>";
	strHtml+="<td align='center'>" + myMonth[0][1] + "</td>";
	strHtml+="<td align='center'>" + myMonth[0][2] + "</td>";
	strHtml+="<td align='center'>" + myMonth[0][3] + "</td>";
	strHtml+="<td align='center'>" + myMonth[0][4] + "</td>";
	strHtml+="<td align='center'>" + myMonth[0][5] + "</td>";
	strHtml+="<td align='center'>" + myMonth[0][6] + "</td>";
	strHtml+="</tr>";
	for (w = 1; w < 7; w++) {
		strHtml+="<tr style='height:70px;'>";
		for (d = 0; d < 7; d++) {
			strHtml+="<td align='left' valign='top' width='" + iCellWidth + "' height='" + iCellHeight + "' id=calCell style='' onMouseOver='fToggleColor(this)' onMouseOut='fToggleColor(this)' onclick='fSetSelectedDay(this)'>";
			if (!isNaN(myMonth[w][d])) {
				strHtml+="<font id=calDateText onMouseOver='fToggleColor(this)' style='CURSOR:Hand;FONT-FAMILY:Arial;FONT-SIZE:" + sDateTextSize + ";FONT-WEIGHT:" + sDateTextWeight + "' onMouseOut='fToggleColor(this)' onclick=fSetSelectedDay(this)>" + myMonth[w][d] + "</font>";
			} else {
				strHtml+="<font id=calDateText onMouseOver='fToggleColor(this)' style='CURSOR:Hand;FONT-FAMILY:Arial;FONT-SIZE:" + sDateTextSize + ";FONT-WEIGHT:" + sDateTextWeight + "' onMouseOut='fToggleColor(this)' onclick=fSetSelectedDay(this)> </font>";
			}
			strHtml+="</td>";
		}
	strHtml+="</tr>";
	}
strHtml+="</table>";
return strHtml;
}
function fUpdateCal(){
	var iYear=$("#selyear").val();
	var iMonth=$("#selmonth").val();
	myMonth = fBuildCal(iYear, iMonth);
	objPrevElement.bgColor = "";
	document.all.calSelectedDate.value = "";
	for (w = 1; w < 7; w++) {
		for (d = 0; d < 7; d++) {
			if (!isNaN(myMonth[w][d])) {
				calDateText[((7*w)+d)-7].innerText = myMonth[w][d];
			}else {
				calDateText[((7*w)+d)-7].innerText = " ";
			     }
		 }
	}
}
</script>
</head>
<body>

<input type="hidden" name="calSelectedDate" value="">

<table border="1" width="490px;" height="490px;">
<tr>
	<td >
	<select name="tbSelYear" id="selyear" onchange='changedate()'>
	    <option value="2013">2013</option>
		<option value="2014">2014</option>
		<option value="2015">2015</option>
	</select>
	<select name="tbSelMonth" id="selmonth" onchange='changedate()'>
		<option value="1">一月</option>
		<option value="2">二月</option>
		<option value="3">三月</option>
		<option value="4">四月</option>
		<option value="5">五月</option>
		<option value="6">六月</option>
		<option value="7">七月</option>
		<option value="8">八月</option>
		<option value="9">九月</option>
		<option value="10">十月</option>
		<option value="11">十一月</option>
		<option value="12">十二月</option>
	</select>
	</td>
</tr>
<tr>
	<td >
		<div id="calendarDiv"></div>
	</td>
</tr>
</table>

</body>
</html>