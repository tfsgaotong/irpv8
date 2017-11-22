<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>     
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
  <a  href="javascript:void(0)"style=" float: left;font-size: 15px; "class="linkc12"  onclick="receiveMessageList()" >&lt;&lt;返回</a>
  <input id="messagecountnumconf" type="text" style="display: none;" value="<s:property value="messagedetailcount" />">
  <li id="microblogContentprompt_01" style="text-align: right;">您还可以输入<label id="microblogContentCount_01" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;" ><s:property value="messagedetailcount" /></label>个字</li>
    <li id="microblogContentprompt_02" style="text-align: right;display: none;">超出了<label id="microblogContentCount_02" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color:red;" ></label>个字</li>
   	    
   	 <input type="text" id="messagedetailnum" value="<s:property value='messagedetailcount' />" style="display:none; " />   
    <textarea id="replyMessageInfo"  rows="4" style="width: 676px;background-color:	rgb(246, 246, 246);resize: none;" onkeyup="microblogInfoControl(this.value)"></textarea>
    
    <a class="zhuanz1" href="javascript:void(0)"style="margin-left: 600px; margin-top: 5px;"  onclick="messagePersonalReply(<s:property value='cruserid' />)" >回复</a>
    <div id="replycommentpersonaldiv">
    <s:iterator value="irpMessageContentlist"> 
     <dl id="<s:property value='MESSAGEID' />div"> 
      <dt>
      <a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='CRUSERID' /> " >
        <s:if test="findIrpUserByFocusId(CRUSERID).userpic!=null">
          <img width="55px" alt="用户图片" src="<%=rootPath%>file/readfile.action?fileName=<s:property value='findIrpUserByFocusId(CRUSERID).userpic' />" onmouseover="personalCard(<s:property value='CRUSERID' />,<s:property value='MESSAGEID' />)" onmouseout="personalCardOut(<s:property value='MESSAGEID' />)"   >
         </s:if>
         <s:elseif test="findIrpUserByFocusId(CRUSERID).userpic==null">
          <img <s:if test="findIrpUserByFocusId(CRUSERID).sex==2">src="<%=rootPath%>client/images/female.jpg"</s:if><s:else>src="<%=rootPath%>client/images/male.jpg"</s:else> alt="用户图片" width="55px" onmouseover="personalCard(<s:property value='CRUSERID' />,<s:property value='MESSAGEID' />)" onmouseout="personalCardOut(<s:property value='MESSAGEID' />)"  />
         </s:elseif>
        </a> 
      </dt>
      <dd>
      <a target="_bank" class="linkc12" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='CRUSERID' /> " >
             <s:property value="getShowPageViewNameByUserId(CRUSERID)" />
     </a>
      </dd>
      <dd><s:property value="CONTENT" escapeHtml="false" /></dd>
      
      <dd>
        <span>
          <s:date name="CRTIME" format="yyyy-MM-dd HH:mm"  />
         <a style="margin-left: 350px;" class="linkc12" href="javascript:void(0)" onclick="deleteMessageInfo(<s:property value='MESSAGEID' />)">删除</a>
        </span>
      </dd>
     </dl>
    </s:iterator>
    </div>
    <ul>
      <s:property value="pageHTML" escapeHtml="false" />
   </ul>