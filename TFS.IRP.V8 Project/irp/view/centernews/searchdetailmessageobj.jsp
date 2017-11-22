  <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>     
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
     	<ul class="myDiscuss list5" id="<s:property value='irpMessageContent.messageid' />div">
     		<li style="margin-top: 154px; margin-bottom: -129px;">
    			<s:date name="irpMessageContent.crtime" format="yyyy-MM-dd HH:mm" />
    			<div class="userIcon" >
			         <s:if test="findIrpUserByFocusId(irpMessageContent.cruserid).userpic!=null">
			          	<img style="border-radius: 20px;" width="60" height="60" src="<%=rootPath%>file/readfile.action?fileName=<s:property value='findIrpUserByFocusId(irpMessageContent.cruserid).userpic' />" onmouseover="personalCard(<s:property value='irpMessageContent.cruserid' />,<s:property value='irpMessageContent.messageid' />)" onmouseout="personalCardOut(<s:property value='irpMessageContent.messageid' />)"   >
			         </s:if>
			         <s:elseif test="findIrpUserByFocusId(irpMessageContent.cruserid).userpic==null"> 
			          	<img style="border-radius: 20px;" width="60" height="60" <s:if test="findIrpUserByFocusId(irpMessageContent.cruserid).sex==2">src="<%=rootPath%>client/images/female.jpg"</s:if><s:else>src="<%=rootPath%>client/images/male.jpg"</s:else> alt="用户图片"    onmouseover="personalCard(<s:property value='irpMessageContent.cruserid' />,<s:property value='irpMessageContent.messageid' />)" onmouseout="personalCardOut(<s:property value='irpMessageContent.messageid' />)"    />
			         </s:elseif>
	         	</div>
		        <section>
		        	<aside></aside>
		        	<div style="width: 553px;">
		        	 	<s:property value="irpMessageContent.content" escapeHtml="false" />
		        	 	<p>
		        	 		<a target="_blank" class="linkc12" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='irpMessageContent.cruserid' /> " >
		        	 			<s:property value="getShowPageViewNameByUserId(irpMessageContent.cruserid)" />
		        	 		</a>
		        	 		<a style="margin-left: 485px;" class="linkc12" href="javascript:void(0)" onclick="deleteMessageInfo(<s:property value='irpMessageContent.messageid' />)">
		        	 			删除
		        	 		</a>
		        	 	</p>
		        	</div>
		        </section> 
     		</li>
   	 	</ul>