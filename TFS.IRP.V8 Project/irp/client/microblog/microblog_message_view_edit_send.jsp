<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>  
<head><base href="<%=rootPath%>"></head> 

<s:if test="irpMessageContentlist.size()>0">
  <s:iterator value="irpMessageContentlist">
    <dl id="<s:property value='messageid' />div" > 
      <dt>
        <a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='fromUid' /> " >
         <s:if test="findIrpUserByFocusId(fromUid).userpic!=null">
          <img width="45px" alt="用户图片" src="<%=rootPath%>file/readfile.action?fileName=<s:property value='findIrpUserByFocusId(fromUid).userpic' />" onmouseover="personalCard(<s:property value='fromUid' />,<s:property value='messageid' />)" onmouseout="personalCardOut(<s:property value='messageid' />)"   >
         </s:if>
         <s:elseif test="findIrpUserByFocusId(fromUid).userpic==null">
          <img <s:if test="findIrpUserByFocusId(fromUid).sex==2">src="<%=rootPath%>client/images/female.jpg"</s:if><s:else>src="<%=rootPath%>client/images/male.jpg"</s:else> alt="用户图片" width="45px" onmouseover="personalCard(<s:property value='fromUid' />,<s:property value='messageid' />)" onmouseout="personalCardOut(<s:property value='messageid' />)"  />
         </s:elseif>
         </a>
      </dt>
      <dd>
        <a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='fromUid' /> " >
        <s:property value="getShowPageViewNameByUserId(fromUid)" />
       </a>
      
      </dd>
      <dd>
        
         <s:property value="content" escapeHtml="false" />
      
      </dd>
       <dd>
         <span> 
             <s:date name="crtime" format="yyyy-MM-dd HH:mm" />
             <a class="linkc12" style="margin-left: 350px;" href="javascript:void(0)" onclick="deleteMessageSendOut(<s:property value='messageid' />)" >删除</a>
         </span>
  
       </dd>
    </dl>  
  </s:iterator>
  <ul>
 <s:property value="pageHTML" escapeHtml="false" />
</ul>
  </s:if>
  