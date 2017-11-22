<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.tfs.irp.util.LoginUtil"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
	<input id="sysfriendnumid" type="text" style="display: none;" value="<s:property value="sysfriendnum" />"  >
	<input id="myfriefdstnumchat" type="text" style="display: none;" value="<s:property value="myfriefdstnum" />"  >
<s:iterator value="userlist">
	<s:if test="userid!=loginonlineid">
	<div style="padding: 5px 5px 5px 5px;border-bottom: thin dotted #d0d0d0;cursor: pointer;width: 100%;float: left;" id="dialoguechat<s:property value="userid" />" >
		<div style="float: left;width: 25%;" onclick="popupChat('<s:property value="getShowPageViewNameByUserId(userid)" />',<s:property value="userid" />)">	
		<s:if test="userpic!=null">
			<img src="<%=rootPath%>file/readfile.action?fileName=<s:property value='userpic' />"   width="45px"   />
		</s:if>
		<s:else>
			<img <s:if test="sex==2">src="<%=rootPath%>client/images/female.jpg"</s:if><s:else>src="<%=rootPath%>client/images/male.jpg"</s:else> alt="用户头像" width="45px"   />
		</s:else>
		</div>
		<div style="float: left;width: 75%;">
			<div style="width: 80%;float: left;" onclick="popupChat('<s:property value="getShowPageViewNameByUserId(userid)" />',<s:property value="userid" />)">
				<p style="white-space: nowrap;overflow: hidden; text-overflow:ellipsis; " title="<s:property value="getShowPageViewNameByUserId(userid)" />"><s:property value="getShowPageViewNameByUserId(userid)" /></p>
				<p style="white-space: nowrap;overflow: hidden; text-overflow:ellipsis; " title="<s:property value="expertintro" /> "><s:property value="expertintro" /> </p>
			</div>
			<s:if test="userid!=2">
				<s:if test="boolUserFUser(userid)==true">
					<div onclick="okForcus(<s:property value="userid" />)" id="guanzhusys<s:property value="userid" />" style="width: 20%;float: left;background-repeat: no-repeat;background-position: center;background-image: url('<%=rootPath%>client/images/icons/cancel.png');" title="移除好友">
					&nbsp;
					</div>
				</s:if>
				<s:else>
					<div onclick="okForcus(<s:property value="userid" />)" id="guanzhusys<s:property value="userid" />" style="width: 20%;float: left;background-repeat: no-repeat;background-position: center;background-image: url('<%=rootPath%>client/images/icons/edit_add.png');" title="加为好友">
					&nbsp;
					</div>
				</s:else>
			</s:if>
			
			
			
		</div>

		
	</div>	
	
	</s:if>
</s:iterator>
