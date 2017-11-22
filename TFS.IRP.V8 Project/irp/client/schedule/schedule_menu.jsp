<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>日程</title>
</head>
<script type="text/javascript">
$(function(){
	var dCurDate = new Date();
	$("#nowtime").text(dCurDate.getFullYear()+"年"+(dCurDate.getMonth()+1)+"月"+dCurDate.getDate()+"日");
	$("#selyear").val(dCurDate.getFullYear());
	$("#selmonth").val(dCurDate.getMonth()+1);
	var htmlstr=fDrawCal(dCurDate.getFullYear(), dCurDate.getMonth()+1, 70, 70, "20px", "bold", 1);
	$("#calendarDiv").html(htmlstr);
	for(var i=0;i<$("#daytable").find("td").length;i++){
		if($("#daytable").find("td:eq("+i+")").find("font").text()==dCurDate.getDate()){
			$("#daytable").find("td:eq("+i+")").addClass("today");
		}
	}
})

function changedate(){
	var yearc=$("#selyear").val();
	var monthc=$("#selmonth").val();
	var htmlstr=fDrawCal(yearc, monthc, 70, 70, "20px", "bold", 1);
	$("#calendarDiv").html(htmlstr);
	loadmonththing();
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
		if (!isNaN(parseInt($(myElement).find("font").html()))) {
			myElement.bgColor = "#c0c0c0";
			objPrevElement.bgColor = "";
			document.all.calSelectedDate.value = parseInt(myElement.children["calDateText"].innerText);
			objPrevElement = myElement;
			$("#nowtime").text($("#selyear").val()+"年"+$("#selmonth").val()+"月"+($(myElement).find("font").html())+"日");
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
	aMonth[0][0] = "日";
	aMonth[0][1] = "一";
	aMonth[0][2] = "二";
	aMonth[0][3] = "三";
	aMonth[0][4] = "四";
	aMonth[0][5] = "五";
	aMonth[0][6] = "六";
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
	strHtml+="<table id='daytable' border=1 width='700px;' height='700px;'>";
	strHtml+="<thead><tr>";
	strHtml+="<th align='center'>" + myMonth[0][0] + "</th>";
	strHtml+="<th align='center'>" + myMonth[0][1] + "</th>";
	strHtml+="<th align='center'>" + myMonth[0][2] + "</th>";
	strHtml+="<th align='center'>" + myMonth[0][3] + "</th>";
	strHtml+="<th align='center'>" + myMonth[0][4] + "</th>";
	strHtml+="<th align='center'>" + myMonth[0][5] + "</th>";
	strHtml+="<th align='center'>" + myMonth[0][6] + "</th>";
	strHtml+="</tr></thead><tobdy>";
	for (w = 1; w < 7; w++) {
		strHtml+="<tr style='height:100px;'>";
		for (d = 0; d < 7; d++) {
			if (!isNaN(myMonth[w][d])) {
				strHtml+="<td align='left' valign='top' width='" + iCellWidth + "' height='" + iCellHeight + "' id=calCell style=''  onmouseout='hidedaything()' onmousemove='lookthing(this)' onclick='fSetSelectedDay(this)'  ondblclick='cNewthing(this)'>";
				strHtml+="<font id=calDateText onMouseOver='fToggleColor(this)' style='FONT-FAMILY:Arial;FONT-SIZE:" + sDateTextSize + ";FONT-WEIGHT:" + sDateTextWeight + "' onMouseOut='fToggleColor(this)' onclick=fSetSelectedDay(this)>" + myMonth[w][d] + "</font><br/><span style='width:70px'></span>";
			} else {
				strHtml+="<td align='left' valign='top' width='" + iCellWidth + "' height='" + iCellHeight + "' id=calCell ";
				strHtml+="<font id=calDateText onMouseOver='fToggleColor(this)' style='FONT-FAMILY:Arial;FONT-SIZE:" + sDateTextSize + ";FONT-WEIGHT:" + sDateTextWeight + "' onMouseOut='fToggleColor(this)' onclick=fSetSelectedDay(this)> </font>";
			}
			strHtml+="</td>";
		}
	strHtml+="</tr>";
	}
strHtml+="</tobdy></table>";
return strHtml;
}
</script>
<input type="hidden" name="calSelectedDate" value="">
<table width="700px;" border="1" height="700px;">
<tr>
	<td style="font-size: 22px;">
	<select name="tbSelYear" id="selyear" onchange='changedate()'>
	    <option value="2013">2013</option>
		<option value="2014">2014</option>
		<option value="2015">2015</option>
	</select>
	年
	<select name="tbSelMonth" id="selmonth" onchange='changedate()'>
		<option value="1">一</option>
		<option value="2">二</option>
		<option value="3">三</option>
		<option value="4">四</option>
		<option value="5">五</option>
		<option value="6">六</option>
		<option value="7">七</option>
		<option value="8">八</option>
		<option value="9">九</option>
		<option value="10">十</option>
		<option value="11">十一</option>
		<option value="12">十二</option>
	</select>
	月
	</td>
	<td style="font-size: 22px;">您选择的是：<span id="nowtime" ></span></td>
</tr>
<tr>
	<td colspan="2">
		<div id="calendarDiv"></div>
	</td>
</tr>
</table>
