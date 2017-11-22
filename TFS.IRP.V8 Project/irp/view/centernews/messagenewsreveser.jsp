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
				<s:set var="resverobj"  value="getIrpMicroblogContent(CRUSERID)" ></s:set>
			    <ul class="myDiscuss list9" id="<s:property value='#resverobj.messageid' />div" >
			   		<li><s:date name="#resverobj.crtime" format="yyyy-MM-dd HH:mm" /><s:if test="selectUnReadMessageDetail(CRUSERID)!=0"><a  href="javascript:void(0)" onclick="loadUserMessage(<s:property value='getIrpMicroblogContent(CRUSERID).messageid' />,<s:property value='CRUSERID' />)" >(<s:property value="selectUnReadMessageDetail(CRUSERID)" />条未读私信)</a></s:if><div class="userIcon" >
			   			
						         <s:if test="findIrpUserByFocusId(CRUSERID).userpic!=null">
						          <img style="border-radius: 20px;" width="60" height="60" src="<%=rootPath%>file/readfile.action?fileName=<s:property value='findIrpUserByFocusId(CRUSERID).userpic' />" onmouseover="personalCard(<s:property value='CRUSERID' />,<s:property value='#resverobj.messageid' />)" onmouseout="personalCardOut(<s:property value='#resverobj.messageid' />)"   >
						         </s:if>
						         <s:elseif test="findIrpUserByFocusId(CRUSERID).userpic==null"> 
						          <img style="border-radius: 20px;" width="60" height="60" <s:if test="findIrpUserByFocusId(CRUSERID).sex==2">src="<%=rootPath%>view/images/female.jpg"</s:if><s:else>src="<%=rootPath%>view/images/male.jpg"</s:else> alt="用户图片"   onmouseover="personalCard(<s:property value='CRUSERID' />,<s:property value='#resverobj.messageid' />)" onmouseout="personalCardOut(<s:property value='#resverobj.messageid' />)"  />
						         </s:elseif>
			   			</div><section><aside></aside>
			   		<div>
			   			 <s:property value="#resverobj.content" escapeHtml="false" />
			   			 
			    	<p>
			    		
			    		<a href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='CRUSERID' />" target="_blank">
			    			
			    			<s:property value="getShowPageViewNameByUserId(CRUSERID)" />
			    			
		    			</a>&nbsp;&nbsp;&nbsp;
		    		
			    		<a href="javascript:void(0)" onclick="deleteMessage(<s:property value="#resverobj.fromUid"/>,<s:property value="CRUSERID"/>,<s:property value='#resverobj.messageid' /> )">删除</a>&nbsp;|&nbsp;
			    		<a  href="javascript:void(0)" onclick="loadUserMessage(<s:property value='#resverobj.messageid' />,<s:property value='CRUSERID' />)">查看 </a></p></div></section><div class="clear"></div></li>
			         	
			         
			    </ul>
			</s:iterator>
			<div class="pages">
				<s:property value="pageHTML" escapeHtml="false" />
			</div>
			
		</s:if>
		<s:else>
			<p style="text-align: center;">没有收到的私信</p>
		
		</s:else>