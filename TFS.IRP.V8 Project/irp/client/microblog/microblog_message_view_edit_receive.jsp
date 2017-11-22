<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<head>
<base href="<%=rootPath%>" >    
</head>
<s:if test="irpMessageContentlist.size()>0">
   <input type="text" id="messagecount" value="<s:property value='messagecount' />" style="display: none;" />
<s:iterator value="irpMessageContentlist">

<dl  id="<s:property value='getIrpMicroblogContent(CRUSERID).messageid' />div"   >
  <dt>
        <a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='CRUSERID' /> " >
         <s:if test="findIrpUserByFocusId(CRUSERID).userpic!=null">
          <img width="45px" alt="用户图片" src="<%=rootPath%>file/readfile.action?fileName=<s:property value='findIrpUserByFocusId(CRUSERID).userpic' />" onmouseover="personalCard(<s:property value='CRUSERID' />,<s:property value='getIrpMicroblogContent(CRUSERID).messageid' />)" onmouseout="personalCardOut(<s:property value='getIrpMicroblogContent(CRUSERID).messageid' />)"   >
         </s:if>
         <s:elseif test="findIrpUserByFocusId(CRUSERID).userpic==null">
          <img <s:if test="findIrpUserByFocusId(CRUSERID).sex==2">src="<%=rootPath%>client/images/female.jpg"</s:if><s:else>src="<%=rootPath%>client/images/male.jpg"</s:else> alt="用户图片" width="45px" onmouseover="personalCard(<s:property value='CRUSERID' />,<s:property value='getIrpMicroblogContent(CRUSERID).messageid' />)" onmouseout="personalCardOut(<s:property value='getIrpMicroblogContent(CRUSERID).messageid' />)"  />
         </s:elseif>
         </a>
  </dt>
  <dd>
     <a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='CRUSERID' /> " >
        <s:property value="getShowPageViewNameByUserId(CRUSERID)" />
       </a>
       <s:if test="selectUnReadMessageDetail(CRUSERID)!=0">
        <a style="float: right;" href="javascript:void(0)" onclick="loadUserMessage(<s:property value='getIrpMicroblogContent(CRUSERID).messageid' />,<s:property value='CRUSERID' />)" ><s:property value="selectUnReadMessageDetail(CRUSERID)" />条未读私信</a>
       </s:if>
  </dd>
  <dd> 
 
     <s:property value="getIrpMicroblogContent(CRUSERID).content" escapeHtml="false" />
 </dd>
  <dd>
    <span>
      <s:date name="getIrpMicroblogContent(CRUSERID).crtime" format="yyyy-MM-dd HH:mm" />

               <a class="linkc12" style="margin-left: 360px;" href="javascript:void(0)" onclick="messageContentViews('<s:property value='getShowPageViewNameByUserId(CRUSERID)' />',<s:property value='CRUSERID' />)" >回复</a>  
          
     
             
             &nbsp;|&nbsp;&nbsp;<a class="linkc12" href="javascript:void(0)" onclick="deleteMessage(<s:property value="getIrpMicroblogContent(CRUSERID).fromUid"/>,<s:property value="CRUSERID"/>,<s:property value='getIrpMicroblogContent(CRUSERID).messageid' /> )">删除</a>
          
              <a class="linkc12" href="javascript:void(0)" onclick="loadUserMessage(<s:property value='getIrpMicroblogContent(CRUSERID).messageid' />,<s:property value='CRUSERID' />)" style="display: block; float: right;">|&nbsp;&nbsp;查看详细</a>
              
              
              
     </span>
  </dd>
   
</dl>     
</s:iterator>
<ul>
 <s:property value="pageHTML" escapeHtml="false" />
</ul>
</s:if>