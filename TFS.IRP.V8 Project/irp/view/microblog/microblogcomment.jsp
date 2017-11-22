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
  <s:iterator value="rePlyList" status="rePlyList">
  <s:set var="microblogobj" value="findIrpMicroblogByMicroblogid(MICROBLOGID)" ></s:set>
  <s:if test="ISDEL==0">
  <dl style="width: 650px;margin-top: 20px;" id="commentReply<s:property value="COMMENTID"/>"   onmouseover="deleteCommentOver(<s:property value="COMMENTID"/>)" onmouseout="deleteCommentOut(<s:property value="COMMENTID"/>)">
   <dd style="float: left; margin-top: 5px; ">
     <a style="box-shadow: 1px 1px 1px #888;border-radius: 36px;display: block;width: 52px;height: 52px;" target="_blank"	 href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' /> " >
      <s:if test="USERPIC!=null">
         <img width="45" style="border-radius: 30px;margin: 6px;" alt="用户图片"     src="<%=rootPath%>file/readfile.action?fileName=<s:property value="USERPIC" />"> 
      </s:if>
      <s:elseif test="USERPIC==null">
         <img width="45" style="border-radius: 30px;margin: 6px;" alt="用户图片"  <s:if test="SEX==2">src="<%=rootPath%>view/images/female.jpg"</s:if><s:else>src="<%=rootPath%>view/images/male.jpg"</s:else> >
      </s:elseif>
		</a>
   </dd>
   <dd style="text-align:left;margin-left: 70px;">
   <span style="margin-top: -20px;">
     
     <label>
     <a target="_blank" style=""  href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' />" class="linkc12"   >
     <s:property value="SHOWNAME" />
     </a>
     </label>
     <label style="margin-left: 507px;" id="commentfloor"><s:property value="getCountCommentByMicroblog(MICROBLOGID)-((pageNum-1)*pageSize+#rePlyList.count-1)"/></label>楼<br/>
    </span>
    <span style="text-align: left; margin-top: -20px; margin-left: 1px;">
     <label><s:property value="CONTENT" escapeHtml="false" /></label>
     </span>
   </dd> 
   <dd>
   &nbsp;
   </dd>
  <dd style="width: ">
   <span>
    <label style="font-size: 12px;" ><s:date name="CRTIME" format="yyyy-MM-dd HH:mm" /></label>
  

    
    
    
     <s:if test="getLoginUserId()==#microblogobj.userid">
   <a href="javascript:void(0)" style=" visibility: hidden;margin-left: 476px;" id="deletecommentdivbool<s:property value='COMMENTID'/>" class="linkc12" onclick="deleteMicroBlogComment(<s:property value="COMMENTID"/>,<s:property value="MICROBLOGID"/>)">删除</a>
   </s:if>
   <s:elseif test="getLoginUserId()!=#microblogobj.userid">
    <a href="javascript:void(0)" style="visibility: hidden;margin-left: 476px;">其它</a>
   </s:elseif>
   
    <a style=""   href="javascript:void(0)" class="linkc12" onclick="replyCommentReply(<s:property value="MICROBLOGID"/>,'<s:property value="SHOWNAME"/>','<s:property value="CONTENT.replaceAll('<','&lt;').replaceAll('>','&gt;').replaceAll('/', '//'+'/');"/>',<s:property value="COMMENTID"/>)">回复</a>
    </span>
    
  </dd>
 <hr style="width: 640px;float: left;color: #F0F0F0; " />
  
  </dl>
  
  </s:if>
  
  </s:iterator>
  <br/>
  <ul style="margin-left: -130px;"><s:property value="pageHTML" escapeHtml="false" /></ul>