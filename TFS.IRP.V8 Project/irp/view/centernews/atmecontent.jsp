<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 

  <s:iterator value="irpMicroblogAtmeList">
  	<s:set var="fimById" value="findIrpMicroblogByMicroblogid(microblogid)" ></s:set>
  	<s:set var="fimByTId" value="findIrpMicroblogByMicroblogid(#fimById.transpondid)"></s:set>
  	<s:set var="fBIU" value="#fimById.userid" ></s:set>
  	
  	
      <ul class="myDiscuss list9" id="<s:property value='atmeid'/>div">
        <li>
        	<div class="userIcon">
	           <s:if test="findIrpUserByFocusId(#fBIU).userpic!=null">
	        	 <img src="<%=rootPath%>file/readfile.action?fileName=<s:property value='findIrpUserByFocusId(#fBIU).userpic'/>" alt="用户图片" width="55" onmouseover="personalCard(<s:property value='#fBIU' />,<s:property value='atmeid' />)" onmouseout="personalCardOut(<s:property value='atmeid' />)"  />  
	           </s:if>
	           <s:if test="findIrpUserByFocusId(#fBIU).userpic==null">
	        	 <img <s:if test="findIrpUserByFocusId(#fBIU).sex==2">src="<%=rootPath%>client/images/female.jpg"</s:if><s:else>src="<%=rootPath%>client/images/male.jpg"</s:else> alt="用户图片" width="55" onmouseover="personalCard(<s:property value='#fBIU' />,<s:property value='atmeid' />)" onmouseout="personalCardOut(<s:property value='atmeid' />)"   />
	           </s:if>  
        	</div>
        	<section>
        		<aside></aside>
        		<div>
        		  <a target="_blank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='#fBIU' /> " >
       					   		<s:property value="getShowPageViewNameByUserId(#fBIU)" />
     				 </a>:
        			<s:property value="getMicroblogContent(#fimById.microblogcontent)" escapeHtml="false" /><br/>
        			<s:property value="getMicroblogContent(#fimById.microblogcontentimg)" escapeHtml="false" />
        			
        			         <s:if test="#fimById.transpondid!=0">
					            <div style="background-color: white;">
					              <em></em>
					              <a target="_blank" style="float: left;" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='#fimByTId.userid' /> " class="linkb14">
					                 <s:property value="getShowPageViewNameByUserId(#fimByTId.userid)" />
					                 :
					              </a>
					              </br>
					              <s:if test="#fimByTId.userid==2"><img style="float: left;margin-top: 5px;" alt="微知助手" src="<%=rootPath %>client/images/micropicicons/mobile-attach-4.png"></s:if>
					             <s:property value='getMicroblogContent(#fimByTId.microblogcontent)' escapeHtml="false" /><br/>
					             <s:property value='getMicroblogContent(#fimByTId.microblogcontentimg)' escapeHtml="false" />
					            </div>
					         </s:if>
        			
          			<p>
   				         
          				<a   href="javascript:void(0)" onclick="deleteMicroblogAtme(<s:property value='atmeid'/>)">删除</a>
         				&nbsp;|&nbsp;
         				<a target="_blank"    href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='#fBIU' />&focusonfus=3&microblogid=<s:property value='microblogid' />&micruserid=<s:property value='#fBIU' />"  >查看</a>
          			</p>
          		</div>
          	</section>
          	<div class="clear"></div>
        </li>
      </ul>
  
  </s:iterator>
  <div class="pages"><s:property value="pageHTML" escapeHtml="false" /></div>