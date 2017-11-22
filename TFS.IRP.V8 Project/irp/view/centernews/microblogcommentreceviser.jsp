<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<s:if test="irpMicroblogCommentList.size!=0">
 
	<s:iterator value="irpMicroblogCommentList">
	
		<s:set var="fubfId" value="findIrpUserByFocusId(userid)" ></s:set>
		<s:set var="findMBMId" value="findIrpMicroblogByMicroblogid(microblogid)" ></s:set>
		<s:set var="findIMbTId" value="findIrpMicroblogByMicroblogid(#findMBMId.transpondid)" ></s:set>
		<s:set var="thirduserid" value="#findIMbTId.userid" ></s:set>
		
        <ul id="<s:property value='commentid'/>div"  class="myDiscuss list9" >
        	<li><s:date name="crtime" format="yyyy-MM-dd HH:mm"/> 
        	<div class="userIcon">
        		<s:if test="#fubfId.userpic!=null">
        			<img src="<%=rootPath%>file/readfile.action?fileName=<s:property value='#fubfId.userpic'/>" alt="用户图片"   onmouseover="personalCard(<s:property value='userid' />,<s:property value='commentid' />)" width="55" onmouseout="personalCardOut(<s:property value='commentid' />)"   >
        		
        		</s:if>
        		 <s:if test="#fubfId.userpic==null">
        		 	<img <s:if test="#fubfId.sex==2">src="<%=rootPath%>client/images/female.jpg"</s:if><s:else>src="<%=rootPath%>client/images/male.jpg"</s:else> alt="用户图片"  width="55" onmouseover="personalCard(<s:property value='userid' />,<s:property value='commentid' />)" onmouseout="personalCardOut(<s:property value='commentid' />)"  >
        		 
        		 </s:if>
       		</div>
        	<section>
        		<aside></aside>
        		<div>
        			<article style="background: none repeat scroll 0% 0% #DFDFDF;padding: 5px 10px;line-height: 24px;color: #666;font-size: 12px;">
        				<a target="_blank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='replyuserid' /> "><s:property value="getShowPageViewNameByUserId(replyuserid)" />:</a>
        				 	
        				 	
							<s:property value="getMicroblogContent(#findMBMId.microblogcontent)" escapeHtml="false"  />
							<br/>
							<s:property value="getMicroblogContent(#findMBMId.microblogcontentimg)" escapeHtml="false"  />
            	         <s:if test="#findMBMId.transpondid!=0">
            	            <div style="background-color: white;"><em></em>
            	                  <a target="_blank" style="float: left;" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='#thirduserid' /> " class="linkb14">
                                    <s:property value="getShowPageViewNameByUserId(#thirduserid)" />
                                    :
                                   </a>
                                   <s:if test="#thirduserid==2"><img style="float: left;
margin-top: -7px;" alt="微知助手" src="<%=rootPath %>client/images/micropicicons/mobile-attach-4.png"></s:if>
                                   <s:property value="getMicroblogContent(#findIMbTId.microblogcontent)" escapeHtml="false" />
                                   <br/>
                                   <s:property value="getMicroblogContent(#findIMbTId.microblogcontentimg)" escapeHtml="false" /><br/>
                                   <span>
                                    <s:date name="#findIMbTId.crtime" format="yyyy-MM-dd HH:mm" />
                                    &nbsp;&nbsp;来自<s:property value="#findIMbTId.fromdata" />
                                      <a target="_blank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='#thirduserid' />&focusonfus=3&microblogid=<s:property value='#findMBMId.transpondid' />&micruserid=<s:property value='#thirduserid' />" style="margin-left: 150px;">原文转发(<s:property value="#findIMbTId.transpondcount" />)</a>
                                      &nbsp;|&nbsp; <a target="_blank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='#thirduserid' />&focusonfus=3&microblogid=<s:property value='#findMBMId.transpondid' />&micruserid=<s:property value='#thirduserid' />"> 原文评论(<s:property value="#findIMbTId.commentcount" />)</a>
                                   </span>
            	            </div>
            	         </s:if>
       				</article>
       				<article style="background: none repeat scroll 0% 0% #F0EDED;;padding: 5px 10px;line-height: 24px;color: #666;font-size: 12px;">
       				
       					<a target="_blank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' /> " ><s:property value="getShowPageViewNameByUserId(userid)" /></a>:
       				
					<s:property value="content" escapeHtml="false" />
					</article>
       				<p>
       					<a href="javascript:void(0)" class="linkc12" onclick="deleteMicroblogCommentWiteMe(<s:property value='commentid'/>,<s:property value='microblogid'/>)">删除</a>
       					
       					&nbsp;|&nbsp;
       					<a target="_blank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='#findMBMId.userid' />&focusonfus=3&microblogid=<s:property value='microblogid' />&micruserid=<s:property value='#findMBMId.userid' />"  >查看</a>
       					</p>

    			</div>
    		</section>
    		<div class="clear"></div>
    		</li>
      	</ul>
      	
	
	</s:iterator>
	<div class="pages">
		<s:property value="pageHTML" escapeHtml="false" />
	</div>
</s:if>
<s:else>


</s:else>


