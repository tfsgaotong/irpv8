<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>     
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 

<dl id="<s:property value='irpMessageContent.messageid' />div"> 
      <dt>
      <a target="_bank"  href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='irpMessageContent.cruserid' /> " >
        <s:if test="findIrpUserByFocusId(irpMessageContent.cruserid).userpic!=null">
          <img width="55px" alt="用户图片" src="<%=rootPath%>file/readfile.action?fileName=<s:property value='findIrpUserByFocusId(irpMessageContent.cruserid).userpic' />" onmouseover="personalCard(<s:property value='irpMessageContent.cruserid' />,<s:property value='irpMessageContent.messageid' />)" onmouseout="personalCardOut(<s:property value='irpMessageContent.messageid' />)"   >
         </s:if>
         <s:elseif test="findIrpUserByFocusId(irpMessageContent.cruserid).userpic==null">
          <img <s:if test="findIrpUserByFocusId(irpMessageContent.cruserid).sex==2">src="<%=rootPath%>client/images/female.jpg"</s:if><s:else>src="<%=rootPath%>client/images/male.jpg"</s:else> alt="用户图片" width="55px" onmouseover="personalCard(<s:property value='irpMessageContent.cruserid' />,<s:property value='irpMessageContent.messageid' />)" onmouseout="personalCardOut(<s:property value='irpMessageContent.messageid' />)"  />
         </s:elseif>
        </a> 
      </dt>
      <dd>
      <a target="_bank" class="linkc12" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='irpMessageContent.cruserid' /> " >
             <s:property value="getShowPageViewNameByUserId(irpMessageContent.cruserid)" />
     </a>
      </dd>
      <dd><s:property value="irpMessageContent.content" escapeHtml="false" /></dd>
      <dd>
        <span>
          <s:date name="irpMessageContent.crtime" format="yyyy-MM-dd HH:mm" />
         <a style="margin-left: 350px;" class="linkc12" href="javascript:void(0)" onclick="deleteMessageInfo(<s:property value='irpMessageContent.messageid' />)">删除</a>
        </span>
      </dd>
     </dl>