<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<div id='commentReply<s:property value="irpMicroblogComment.commentid"/>' class="labs">
	<dl id="<s:property value='irpMicroblogComment.commentid'/>div">
    	<dt>
    		<a href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='irpMicroblogComment.userid' />" target="_blank">
    			<s:if test="findUserByUserId(irpMicroblogComment.userid).userpic!=null">
    				<img id="microblogPersonalCard<s:property value='irpMicroblogComment.commentid' />" alt="用户图片" width="55px" src="<%=rootPath%>file/readfile.action?fileName=<s:property value="findUserByUserId(irpMicroblogComment.userid).userpic" />"   />
    			</s:if>
    			<s:else>
    				<img id="microblogPersonalCard<s:property value='irpMicroblogComment.commentid' />"  alt="用户图片" width="55px" <s:if test="findUserByUserId(irpMicroblogComment.userid).sex==2">src="<%=rootPath%>client/images/female.jpg"</s:if><s:else>src="<%=rootPath%>client/images/male.jpg"</s:else>    />
    			</s:else>
    		</a>
    		
    	</dt>
    	
        <dd class="text">
        	<div class="user">
        		<a href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='irpMicroblogComment.userid' />" target="_blank"><s:property value="getShowPageViewNameByUserId(irpMicroblogComment.userid)" /></a>
       			<aside>
       				<span><s:date name="irpMicroblogComment.crtime" format="yyyy-MM-dd HH:mm"/></span>
       			
       			</aside>
       		</div>

            <p><s:property value="irpMicroblogComment.content" escapeHtml="false" /></p>
            <div class="share">
            	<aside>
            		<s:if test="getLoginUserId()==transpondIrpMicroblog(irpMicroblogComment.microblogid).userid">
            		<a href="javascript:void(0)" onclick="deleteMicroBlogComment(<s:property value='irpMicroblogComment.commentid'/>,<s:property value='irpMicroblogComment.microblogid'/>)" id="deletecommentdivboolDetail<s:property value='irpMicroblogComment.commentid'/>" >删除</a>
            		</s:if>
            		<a href="javascript:void(0)" onclick="replyCommentReply(<s:property value="irpMicroblogComment.microblogid"/>,'<s:property value="getShowPageViewNameByUserId(irpMicroblogComment.userid)"/>','<s:property value="irpMicroblogComment.content.replaceAll('<','&lt;').replaceAll('>','&gt;').replaceAll('/', '//'+'/');"/>',<s:property value="irpMicroblogComment.commentid"/>)">回复</a>
            	</aside>
            </div>
        </dd>
        <dd class="clear"></dd>
    </dl>
</div>