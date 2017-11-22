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
</head>

<form action="" id="addScheduleform">
<table>
	<tr>
		<td>名称</td>
		<td><input type="text" name="irpSchedule.schname"/> </td>
	</tr>
	<tr>
		<td>地点</td>
		<td><input type="text" name="irpSchedule.place"/> </td>
	</tr>
	<tr>
		<td>开始时间</td>
		<td><input id="starttime" type="text" name="irpSchedule.starttime" ></td>
	</tr>
	<tr>
		<td>结束时间</td>
		<td><input id="endtime" type="text" name="irpSchedule.endtime"> </td>
	</tr>
	<tr>
		<td>重复</td>
		<td><select><option>无</option><option>每天</option><option>每周</option><option>每月</option> </select>  </td>
	</tr>
	<tr>
		<td>备注</td>
		<td><input type="text" name="irpSchedule.schbak"/> </td>
	</tr>
	<tr>
		<td>提醒</td>
		<td>前<input type="text" name="irpSchedule.remind" style="width: 50px;"/>分钟 (只能输入数字)</td>
	</tr>
	<tr>
		<td>通知</td>
		<td>(如果不选为自己)&nbsp;&nbsp;&nbsp;&nbsp;
	    	<a href="javascript:void()" title="圈人" onclick="addUser()">圈人</a>&nbsp;&nbsp;&nbsp;&nbsp;
	    	<a href="javascript:void()" title="圈组织" onclick=""> 圈组织</a>
		    <p><textarea id="messageuserinfo"  name="irpSchedule.lookusers" cols="10"  rows="3" style="background-color:rgb(246, 246, 246);resize: none;width: 200px;"></textarea></p>     
		</td>
	</tr>
</table>
</form>
