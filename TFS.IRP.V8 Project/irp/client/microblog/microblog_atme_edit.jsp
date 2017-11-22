<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>  
  <s:iterator value="irpMicroblogAtmeList">
        <dl id="<s:property value='atmeid'/>div">
         <dt>  
         <a target="_bank"	 href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='findIrpMicroblogByMicroblogid(microblogid).userid' /> " >
           <s:if test="findIrpUserByFocusId(findIrpMicroblogByMicroblogid(microblogid).userid).userpic!=null">
        	 <img src="<%=rootPath%>file/readfile.action?fileName=<s:property value='findIrpUserByFocusId(findIrpMicroblogByMicroblogid(microblogid).userid).userpic'/>" alt="用户图片" width="45px" onmouseover="personalCard(<s:property value='findIrpMicroblogByMicroblogid(microblogid).userid' />,<s:property value='atmeid' />)" onmouseout="personalCardOut(<s:property value='atmeid' />)"  />  
           </s:if>
           <s:if test="findIrpUserByFocusId(findIrpMicroblogByMicroblogid(microblogid).userid).userpic==null">
        	 <img <s:if test="findIrpUserByFocusId(findIrpMicroblogByMicroblogid(microblogid).userid).sex==2">src="<%=rootPath%>client/images/female.jpg"</s:if><s:else>src="<%=rootPath%>client/images/male.jpg"</s:else> alt="用户图片" width="45px" onmouseover="personalCard(<s:property value='findIrpMicroblogByMicroblogid(microblogid).userid' />,<s:property value='atmeid' />)" onmouseout="personalCardOut(<s:property value='atmeid' />)"   />
           </s:if>  
           </a>
         </dt>
         <dd>
           <a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='findIrpMicroblogByMicroblogid(microblogid).userid' /> " class="linkb14">
               <s:property value="getShowPageViewNameByUserId(findIrpMicroblogByMicroblogid(microblogid).userid)" />
            </a>
         </dd>
         <dd>
         <s:if test="findIrpMicroblogByMicroblogid(microblogid).userid==2"><img style="float: left;margin-top: 5px;" alt="微知助手" src="<%=rootPath %>client/images/micropicicons/mobile-attach-4.png"></s:if>
         <s:property value="getMicroblogContent(findIrpMicroblogByMicroblogid(microblogid).microblogcontent)" escapeHtml="false" /></dd>
         <dd>
         <s:if test="findIrpMicroblogByMicroblogid(microblogid).transpondid!=0">
            <div >
              <em></em>
              <a target="_bank" style="float: left;" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='findIrpMicroblogByMicroblogid(findIrpMicroblogByMicroblogid(microblogid).transpondid).userid' /> " class="linkb14">
                 <s:property value="getShowPageViewNameByUserId(findIrpMicroblogByMicroblogid(findIrpMicroblogByMicroblogid(microblogid).transpondid).userid)" />
                 :
              </a>
              <s:if test="findIrpMicroblogByMicroblogid(findIrpMicroblogByMicroblogid(microblogid).transpondid).userid==2"><img style="float: left;margin-top: 5px;" alt="微知助手" src="<%=rootPath %>client/images/micropicicons/mobile-attach-4.png"></s:if>
             <s:property value='getMicroblogContent(findIrpMicroblogByMicroblogid(findIrpMicroblogByMicroblogid(microblogid).transpondid).microblogcontent)' escapeHtml="false" />
            </div>
         </s:if>
         <span><s:date name="crtime" format="yyyy-MM-dd HH:mm" />
         
         <a class="linkc12" style="margin-left: 350px" href="javascript:void(0)" onclick="deleteMicroblogAtme(<s:property value='atmeid'/>)">删除</a>
         &nbsp;|&nbsp;
         <a target="_bank" class="linkc12"  href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='findIrpMicroblogByMicroblogid(microblogid).userid' />&focusonfus=3&microblogid=<s:property value='microblogid' />&micruserid=<s:property value='findIrpMicroblogByMicroblogid(microblogid).userid' />"  >查看</a>
         
         </span></dd>
        </dl>
      
  </s:iterator>
<ul><s:property value="pageHTML" escapeHtml="false" /></ul>