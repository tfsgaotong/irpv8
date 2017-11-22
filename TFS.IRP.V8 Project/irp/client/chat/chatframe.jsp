<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<div style="height: 265px;width: 380px;" id="chatbyuserprepend">
	<s:if test="chatlist.size()<chatbyusercontentmaxnum">
	<div id="chatbyusermorerecord" style="text-align: center;height: 30px;background-color: #FFF7FB;cursor: pointer;" onclick="searchMoreRecord()">------------------------查看更多记录------------------------</div>
	</s:if>
	<div id="messagerecordcontent" style="height: 235px;background-color: #FCFCFC;overflow-y:scroll; " >
		<div id="minechatlocationtops"></div>
		<s:iterator value="chatlist">
			<s:if test="loginuserid==receiveruserid">
				<p style="margin: 15px 0 -10px 270px;"><s:date name="senddate" format="MM-dd HH:mm:ss" /></p>
			</s:if>
			<s:elseif test="loginuserid==senderuserid">
				<p style="margin: 15px 0 -10px 0;"><s:date name="senddate" format="MM-dd HH:mm:ss" /></p>
			</s:elseif>
			<div style="margin: 15px 0 10px 10px;">
			<s:if test="loginuserid==receiveruserid">
				<span style="max-width:90%;float: right;background-color: #B7FF4A;padding:3px 5px 3px 5px;-moz-border-radius:20px; -webkit-border-radius:20px; border-radius:7px;  "> 
				<s:property value="getShowPageViewNameByUserId(senderuserid)" />:<s:property value="chatcontent" />
				</span>
			</s:if>
			<s:elseif test="loginuserid==senderuserid">
				<span style="max-width:90%;float: left;background-color: #C48888;padding:3px 5px 3px 5px;-moz-border-radius:20px; -webkit-border-radius:20px; border-radius:7px;  ">
				<s:property value="getShowPageViewNameByUserId(senderuserid)" />:<s:property value="chatcontent" />
				</span>
			</s:elseif>
			</div><br/>
		</s:iterator>
		<div id="minechatlocation"></div>
	</div>
</div>
<div  style="height: 95px;width: 380px;border-top: thin solid #E0E0E0;background-color: #F0F0F0;text-align: center;">
	<textarea rows="2" cols="35" id="chatcontentstr" style="margin-top: 10px;padding: 8px 5px 8px 5px;"></textarea>
</div>

