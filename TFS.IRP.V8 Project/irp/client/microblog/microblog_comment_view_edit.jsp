<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<s:if test="irpMicroblogCommentList.size!=0">
<s:iterator value="irpMicroblogCommentList">
        <dl id="<s:property value='commentid'/>div" class="gt_removeclass">
        	<dt>
        	<a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' /> " >
        	  <s:if test="findIrpUserByFocusId(userid).userpic!=null">
        	    <img src="<%=rootPath%>file/readfile.action?fileName=<s:property value='findIrpUserByFocusId(userid).userpic'/>" alt="用户图片" width="45px" onmouseover="personalCard(<s:property value='userid' />,<s:property value='commentid' />)" onmouseout="personalCardOut(<s:property value='commentid' />)"   >  
        	  </s:if>
        	  <s:if test="findIrpUserByFocusId(userid).userpic==null">
        	    <img <s:if test="findIrpUserByFocusId(userid).sex==2">src="<%=rootPath%>client/images/female.jpg"</s:if><s:else>src="<%=rootPath%>client/images/male.jpg"</s:else> alt="用户图片" width="45px" onmouseover="personalCard(<s:property value='userid' />,<s:property value='commentid' />)" onmouseout="personalCardOut(<s:property value='commentid' />)"  >
        	  </s:if>
        	  </a> 
        	</dt>
            <dd><a target="_bank" style="float: left;" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' /> " class="linkb14">
                  <s:property value="getShowPageViewNameByUserId(userid)" />
                  :
                 </a>
                 <s:if test="userid==2"><img style="float: left;
margin-top: -7px;" alt="微知助手" src="<%=rootPath %>client/images/micropicicons/mobile-attach-4.png"></s:if>
                 <s:property value="content" escapeHtml="false" />
            	    <div><em></em>
                        <a target="_bank" style="float: left;" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='replyuserid' /> " class="linkb14">
                          <s:property value="getShowPageViewNameByUserId(replyuserid)" />
                          :
                          </a>
                          <s:if test="replyuserid==2"><img style="float: left;
margin-top: -7px;" alt="微知助手" src="<%=rootPath %>client/images/micropicicons/mobile-attach-4.png"></s:if>
                        <s:property value="getMicroblogContent(findIrpMicroblogByMicroblogid(microblogid).microblogcontent)" escapeHtml="false"  />
            	      <!-- 此处存放分享图片 -->
            	         <s:if test="findIrpMicroblogByMicroblogid(microblogid).transpondid!=0">
            	            <div style="background-color: white;"><em></em>
            	                  <a target="_bank" style="float: left;" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='findIrpMicroblogByMicroblogid(findIrpMicroblogByMicroblogid(microblogid).transpondid).userid' /> " class="linkb14">
                                    <s:property value="getShowPageViewNameByUserId(findIrpMicroblogByMicroblogid(findIrpMicroblogByMicroblogid(microblogid).transpondid).userid)" />
                                    :
                                   </a>
                                   <s:if test="findIrpMicroblogByMicroblogid(findIrpMicroblogByMicroblogid(microblogid).transpondid).userid==2"><img style="float: left;
margin-top: -7px;" alt="微知助手" src="<%=rootPath %>client/images/micropicicons/mobile-attach-4.png"></s:if>
                                   <s:property value="getMicroblogContent(findIrpMicroblogByMicroblogid(findIrpMicroblogByMicroblogid(microblogid).transpondid).microblogcontent)" escapeHtml="false" />
                                   <span>
                                    <s:date name="findIrpMicroblogByMicroblogid(findIrpMicroblogByMicroblogid(microblogid).transpondid).crtime" format="yyyy-MM-dd HH:mm" />
                                    &nbsp;&nbsp;来自<s:property value="findIrpMicroblogByMicroblogid(findIrpMicroblogByMicroblogid(microblogid).transpondid).fromdata" />
                                      <a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='findIrpMicroblogByMicroblogid(findIrpMicroblogByMicroblogid(microblogid).transpondid).userid' />&focusonfus=3&microblogid=<s:property value='findIrpMicroblogByMicroblogid(microblogid).transpondid' />&micruserid=<s:property value='findIrpMicroblogByMicroblogid(findIrpMicroblogByMicroblogid(microblogid).transpondid).userid' />" style="margin-left: 150px;">原文转发(<s:property value="findIrpMicroblogByMicroblogid(findIrpMicroblogByMicroblogid(microblogid).transpondid).transpondcount" />)</a>
                                      &nbsp;|&nbsp; <a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='findIrpMicroblogByMicroblogid(findIrpMicroblogByMicroblogid(microblogid).transpondid).userid' />&focusonfus=3&microblogid=<s:property value='findIrpMicroblogByMicroblogid(microblogid).transpondid' />&micruserid=<s:property value='findIrpMicroblogByMicroblogid(findIrpMicroblogByMicroblogid(microblogid).transpondid).userid' />"> 原文评论(<s:property value="findIrpMicroblogByMicroblogid(findIrpMicroblogByMicroblogid(microblogid).transpondid).commentcount" />)</a>
                                   </span>
            	            </div>
            	         </s:if>
            	      
                       <span><s:date name="findIrpMicroblogByMicroblogid(microblogid).crtime" format="yyyy-MM-dd HH:mm" />&nbsp;&nbsp;  
                                                                                          来自<s:property value="findIrpMicroblogByMicroblogid(microblogid).fromdata"/> 
                       </span> 
                       
                    </div>
                <span> <s:date name="crtime" format="yyyy-MM-dd HH:mm"/> 
                
                <p><a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='findIrpMicroblogByMicroblogid(microblogid).userid' />&focusonfus=3&microblogid=<s:property value='microblogid' />&micruserid=<s:property value='findIrpMicroblogByMicroblogid(microblogid).userid' />" class="linkc12">查看</a>
                 &nbsp;|&nbsp;
                  <a href="javascript:void(0)" class="linkc12" onclick="deleteMicroblogCommentWiteMe(<s:property value='commentid'/>,<s:property value='microblogid'/>)">删除</a></p>
              </span>
          </dd>
	    </dl>
	    
	    </s:iterator>
	      <ul>
      <s:property value="pageHTML" escapeHtml="false" />
      
      </ul>
</s:if>
<s:else>
暂无评论信息
</s:else>	    
    