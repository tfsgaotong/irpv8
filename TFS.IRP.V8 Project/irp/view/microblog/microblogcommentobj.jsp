 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>    
<script type="text/javascript">
//鼠标悬浮到某条私信框
function  deleteCommentOver(_commentid){
	var commentdeletediv = "#deletecommentdivbool"+_commentid;
	$(commentdeletediv).css("visibility","visible");
}
//鼠标移出到某条私信框
function deleteCommentOut(_commentid){
	var commentdeletediv = "#deletecommentdivbool"+_commentid;
	$(commentdeletediv).css("visibility","hidden");
	
}

</script>
  <s:if test="irpMicroblogComment.isdel==0">
  <dl style="width: 650px;margin-top: 20px;" id="commentReply<s:property value="irpMicroblogComment.commentid"/>"   onmouseover="deleteCommentOver(<s:property value="irpMicroblogComment.commentid"/>)" onmouseout="deleteCommentOut(<s:property value="irpMicroblogComment.commentid"/>)">
   <dd style="float: left; margin-top: 5px; ">
     <a style="box-shadow: 1px 1px 1px #888;border-radius: 36px;display: block;width: 52px;height: 52px;" target="_blank"	 href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='irpMicroblogComment.userid' /> " >
      <s:if test="findUserByUserId(irpMicroblogComment.userid).userpic!=null">
         <img width="45" style="border-radius: 30px;margin: 6px;" alt="用户图片"     src="<%=rootPath%>file/readfile.action?fileName=<s:property value="findUserByUserId(irpMicroblogComment.userid).userpic" />"> 
      </s:if>
      <s:elseif test="findUserByUserId(irpMicroblogComment.userid).userpic==null">
         <img width="45" style="border-radius: 30px;margin: 6px;" alt="用户图片"  <s:if test="findUserByUserId(irpMicroblogComment.userid).sex==2">src="<%=rootPath%>view/images/female.jpg"</s:if><s:else>src="<%=rootPath%>view/images/male.jpg"</s:else> >
      </s:elseif>
		</a>
   </dd>
   <dd style="text-align:left;margin-left: 70px;">
   <span style="margin-top: -20px;">
     
     <label>
     <a target="_blank" style=""  href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='irpMicroblogComment.userid' />" class="linkc12"   >
     <s:property value="getShowPageViewNameByUserId(irpMicroblogComment.userid)" />
     </a>
     </label>
     <label style="margin-left: 507px;" id="commentfloor"><s:property value="getCountCommentByMicroblog(irpMicroblogComment.microblogid)"/></label>楼<br/>
    </span>
    <span style="text-align: left; margin-top: -20px; margin-left: 1px;">
     <label><s:property value="irpMicroblogComment.content" escapeHtml="false" /></label>
     </span>
   </dd> 
   <dd>
   &nbsp;
   </dd>
  <dd style="width: ">
   <span>
    <label style="font-size: 12px;" ><s:date name="irpMicroblogComment.crtime" format="yyyy-MM-dd HH:mm" /></label>
  

    
    
    
     <s:if test="getLoginUserId()==findIrpMicroblogByMicroblogid(irpMicroblogComment.microblogid).userid">
   <a href="javascript:void(0)" style=" visibility: hidden;margin-left: 476px;" id="deletecommentdivbool<s:property value='irpMicroblogComment.commentid'/>" class="linkc12" onclick="deleteMicroBlogComment(<s:property value="irpMicroblogComment.commentid"/>,<s:property value="irpMicroblogComment.microblogid"/>)">删除</a>
   </s:if>
   <s:elseif test="getLoginUserId()!=findIrpMicroblogByMicroblogid(irpMicroblogComment.microblogid).userid">
    <a href="javascript:void(0)" style="visibility: hidden;margin-left: 476px;">其它</a>
   </s:elseif>
   
    <a style=""   href="javascript:void(0)" class="linkc12" onclick="replyCommentReply(<s:property value="irpMicroblogComment.microblogid"/>,'<s:property value="getShowPageViewNameByUserId(irpMicroblogComment.userid)"/>','<s:property value="irpMicroblogComment.content.replaceAll('<','&lt;').replaceAll('>','&gt;').replaceAll('/', '//'+'/');"/>',<s:property value="irpMicroblogComment.commentid"/>)">回复</a>
    </span>
    
  </dd>
 
  <hr style="width: 640px;float: left;color: #F0F0F0; " />
  </dl>
  
  </s:if>
  