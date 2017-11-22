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
  <dl id="commentReply<s:property value="irpMicroblogComment.commentid"/>" style="width: 510px;" onmouseover="deleteCommentOver(<s:property value="irpMicroblogComment.commentid"/>)" onmouseout="deleteCommentOut(<s:property value="irpMicroblogComment.commentid"/>)">
   <dd style="float: left; margin-top: 5px; margin-left: -60px;">
     <a	 target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='irpMicroblogComment.userid' /> " >
      <s:if test="findUserByUserId(irpMicroblogComment.userid).userpic!=null">
         <img alt="用户图片" width="53px;" height="53px;"  src="<%=rootPath%>file/readfile.action?fileName=<s:property value="findUserByUserId(irpMicroblogComment.userid).userpic" />"> 
      </s:if>
      <s:elseif test="findUserByUserId(irpMicroblogComment.userid).userpic==null">
         <img alt="用户图片" width="53px;" height="53px;"  <s:if test="findUserByUserId(irpMicroblogComment.userid).sex==2">src="<%=rootPath%>client/images/female.jpg"</s:if><s:else>src="<%=rootPath%>client/images/male.jpg"</s:else> >
      </s:elseif>
   </a>
   </dd>
   <dd>
   <span style="margin-top: -10px;">
     
     <label style=" margin-left: -420px; ">
     <a target="_bank" style=" margin-right: 350px; float: left;" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='irpMicroblogComment.userid' />" class="linkc12"   >
                  <s:property value="getShowPageViewNameByUserId(irpMicroblogComment.userid)" />
     </a>
     </label>
     <label style=""><s:property value="getCountCommentByMicroblog(irpMicroblogComment.microblogid)" /></label>楼<br/>
    </span>
    <span style="text-align: left; margin-top: -20px; margin-left: 1px;">
     <label><s:property value="irpMicroblogComment.content" escapeHtml="false" /></label>
     </span>
   </dd> 
   <dd>
   &nbsp;
   </dd>
  <dd style="margin-right: 1px;">
   <span>
    <label ><s:date name="irpMicroblogComment.crtime" format="yyyy-MM-dd HH:mm" /></label>
          <a style="margin-left: 270px;"   href="javascript:void(0)" class="linkc12" onclick="replyCommentReply(<s:property value="irpMicroblogComment.microblogid"/>,'<s:property value="getShowPageViewNameByUserId(irpMicroblogComment.userid)"/>','<s:property value="irpMicroblogComment.content"/>',<s:property value="irpMicroblogComment.commentid"/>)">回复</a> 
     <s:if test="getLoginUserId()==findIrpMicroblogByMicroblogid(irpMicroblogComment.microblogid).userid">
   <a href="javascript:void(0)" style=" visibility: hidden;" id="deletecommentdivbool<s:property value='irpMicroblogComment.commentid'/>" class="linkc12" onclick="deleteMicroBlogComment(<s:property value="irpMicroblogComment.commentid"/>,<s:property value="irpMicroblogComment.microblogid"/>)">删除</a>
   </s:if>
   <s:elseif test="getLoginUserId()!=findIrpMicroblogByMicroblogid(irpMicroblogComment.microblogid).userid">
    <a href="javascript:void(0)" style="visibility: hidden;">其它</a>
   </s:elseif>
   
    
    </span>
    
  </dd>
 
  
  </dl>
  
  </s:if>
  