<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.tfs.irp.util.LoginUtil"%>
<%@ page import="com.tfs.irp.user.entity.IrpUser"%>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	IrpUser loginUser = LoginUtil.getLoginUser();
%>					
		<s:if test="irpMessageContentlist.size()>0">
			<s:iterator value="irpMessageContentlist" >
				<s:set var="userobj"  value="findIrpUserByFocusId(fromUid)" ></s:set>
			    <ul class="myDiscuss list9" id="<s:property value='messageid' />div" >
			   		<li><s:date name="crtime" format="yyyy-MM-dd HH:mm" /><div class="userIcon" >
			   			
						         <s:if test="#userobj.userpic!=null">
						          <img style="border-radius: 20px;" width="60" height="60" src="<%=rootPath%>file/readfile.action?fileName=<s:property value='#userobj.userpic' />" onmouseover="personalCard(<s:property value='fromUid' />,<s:property value='messageid' />)" onmouseout="personalCardOut(<s:property value='messageid' />)"   >
						         </s:if>
						         <s:elseif test="#userobj.userpic==null"> 
						          <img style="border-radius: 20px;" width="60" height="60" <s:if test="#userobj.sex==2">src="<%=rootPath%>view/images/female.jpg"</s:if><s:else>src="<%=rootPath%>view/images/male.jpg"</s:else> alt="用户图片"   onmouseover="personalCard(<s:property value='fromUid' />,<s:property value='messageid' />)" onmouseout="personalCardOut(<s:property value='messageid' />)" />
						         </s:elseif>
			   			</div><section><aside></aside>
			   		<div>
			   			 <s:property value="content" escapeHtml="false" />
			   			 
			    	<p><a href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='fromUid' />" target="_blank"><s:property value="getShowPageViewNameByUserId(fromUid)" /></a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="deleteMessageSendOut(<s:property value='messageid' />)">删除</a></p></div></section><div class="clear"></div></li>
			         
			         
			    </ul>
			</s:iterator>
			<div class="pages">
				<s:property value="pageHTML" escapeHtml="false" />
			</div>
		</s:if>
		<s:else>
			<p style="text-align: center;">没有发出的私信</p>
		
		</s:else>