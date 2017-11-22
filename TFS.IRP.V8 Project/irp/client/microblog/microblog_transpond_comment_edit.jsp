<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>


<div class="fyh" id='commentReply<s:property value="irpMicroblogComment.commentid"/>' >
   <dl id="<s:property value='irpMicroblogComment.commentid'/>div">
       <dt >
        
         <s:if test="findUserByUserId(irpMicroblogComment.userid).userpic!=null">
        <a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='irpMicroblogComment.userid' /> "> 
          <img  id="microblogPersonalCard<s:property value='irpMicroblogComment.commentid' />"
          alt="用户图片" width="55px" height="55px" 
           src="<%=rootPath%>file/readfile.action?fileName=<s:property value="findUserByUserId(irpMicroblogComment.userid).userpic" />" onmouseover="personalCard(<s:property value='irpMicroblogComment.commentid' />,<s:property value='irpMicroblogComment.userid' />)" onmouseout="personalCardOut(<s:property value='irpMicroblogComment.commentid' />)" />
           </a>
           
         </s:if>
         <s:elseif  test="findUserByUserId(irpMicroblogComment.userid).userpic==null">
         <a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='irpMicroblogComment.userid' /> ">
          <img  id="microblogPersonalCard<s:property value='irpMicroblogComment.commentid' />" 
          alt="用户图片" width="55px" height="55px"  
         <s:if test="findUserByUserId(irpMicroblogComment.userid).sex==2">src="<%=rootPath%>client/images/female.jpg"</s:if><s:else>src="<%=rootPath%>client/images/male.jpg"</s:else> onmouseover="personalCard(<s:property value='irpMicroblogComment.commentid' />,<s:property value='irpMicroblogComment.userid' />)" onmouseout="personalCardOut(<s:property value='irpMicroblogComment.commentid' />)"  />
        </a>  
           
         </s:elseif>
         
         
       </dt>
       <a target="_bank" class="linkc12" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='irpMicroblogComment.userid' />" >
       <s:property value="getShowPageViewNameByUserId(irpMicroblogComment.userid)" />
       </a>
       <dd><s:property value="irpMicroblogComment.content" escapeHtml="false" /></dd>
       <dd>
       <label style="font-size: 12px;"><s:date name="irpMicroblogComment.crtime" format="yyyy-MM-dd HH:mm"/></label>

        <a class="linkc12"  style="float: right;" href="javascript:void(0)" onclick="replyCommentReply(<s:property value="irpMicroblogComment.microblogid"/>,'<s:property value="getShowPageViewNameByUserId(irpMicroblogComment.userid)"/>','<s:property value="irpMicroblogComment.content"/>',<s:property value="irpMicroblogComment.commentid"/>) " >&nbsp;回复</a> 
        <a class="linkc12"  style=" visibility: hidden;float: right;" id="deletecommentdivboolDetail<s:property value='irpMicroblogComment.commentid'/>"     href="javascript:void(0)" onclick="deleteMicroBlogComment(<s:property value="irpMicroblogComment.commentid"/>,<s:property value="irpMicroblogComment.microblogid"/>)">删除&nbsp;</a>
        </dd>
 
   </dl>

   </div>  