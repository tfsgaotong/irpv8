<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>

<p id="FocusByFocus" class="zl3" style=" font-weight:normal;  line-height:50px; border-bottom:1px  solid #efefef;text-align:left; font-family:微软雅黑; margin:0 0 20px 5px; color:#121212;">
	<a href="javascript:void(0)"   <s:if test="eachuserid==0">class="over"</s:if> id="focusOn" onclick="focusTab(0,this.id,${personid})" style="margin-left: 10px;"><font class="linkbh14">关注</font></a>
	<a href="javascript:void(0)" id="fans"  <s:if test="eachuserid==3">class="over"</s:if> onclick="focusTab(3,this.id,${personid})" style="margin-left: 10px;"><font class="linkbh14">粉丝</font></a>
</p>
<s:if test="eachuserid==3">
<s:iterator value="irpMicroblogFocusUserlist" > 
<s:if test="findIrpUserByFocusId(focususerid).username!=null">
<div class="gr2">
  <dl> 
      
       <s:if test="findIrpUserByFocusId(focususerid).userpic!=null">
          <dt>
          <a target="_bank"	href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='focususerid' /> " >
          <img src="<%=rootPath%>file/readfile.action?fileName=<s:property value='findIrpUserByFocusId(focususerid).userpic' />"  alt="用户头像" width="55px"    /> 
          </a>
          </dt>
       </s:if>
       <s:if test="findIrpUserByFocusId(focususerid).userpic==null">
          <dt>
          <a	target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='focususerid' /> " >
          <img <s:if test="findIrpUserByFocusId(focususerid).sex==2">src="<%=rootPath%>client/images/female.jpg"</s:if><s:else>src="<%=rootPath%>client/images/male.jpg"</s:else> alt="用户头像" width="55px"   /> 
          </a>   
          </dt>
       </s:if>
          <dd>
         <a target="_bank"	href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='focususerid' /> "  class="linkb14">   
          <s:property value="getShowPageViewNameByUserId(focususerid)" />
          </a>
          </dd>
    <dd>
        <span>
          <a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='focususerid' />&focusonfus=1 "> 关注 (<s:property value="MicroblogFocusCountUserid(focususerid)" />)</a>
          <a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='focususerid' />&focusonfus=2 ">粉丝 (<s:property value="MicroblogFocusCountFocusUserid(focususerid)" />)</a>
          <a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='focususerid' /> ">微知(<s:property value="MicroblogCountByUserid(focususerid)" />) </a>
          
            
           <s:if test="getLoginUserId()!=focususerid">
             <s:if test="focususerid in allUseridByUserid(getLoginUserId()) && getLoginUserId() in allUseridByUserid(focususerid)">
                <label style="margin-left: 340px;" id="eachFocus_01<s:property value="focusid" />" >互相关注</label><s:if test="focususerid!=2">(<a href="javascript:void(0)" onclick="cancelEachFocus(<s:property value="focususerid" />,<s:property value="focusid" />)"  ><label id="eachFocus_02<s:property value="focusid" />">取消</label></a>)</s:if>                                         
             </s:if>
             <s:elseif test="focususerid in allUseridByUserid(getLoginUserId())">
               <label style="margin-left: 350px;" id="alreadyFocus_01<s:property value="focusid" />" >已关注</label><s:if test="focususerid!=2">(<a href="javascript:void(0)" onclick="cancelFocus(<s:property value="focususerid" />,<s:property value="focusid" />)" ><label id="alreadyFocus_02<s:property value="focusid" />" >取消</label></a>)</s:if>
             </s:elseif>
             <s:elseif test="focususerid not in allUseridByUserid(getLoginUserId())">
               <label style="margin-left: 350px;" id="notFocus_01<s:property value="focusid" />" >未关注</label>(<a href="javascript:void(0)" onclick="okFocus(<s:property value="focususerid" />,<s:property value="focusid" />)" ><label id="notFocus_02<s:property value="focusid" />">关注</label></a>)
             </s:elseif>
           </s:if>                          
       </span>                   
    </dd>
    <dd  style="padding-bottom: 10px; "><s:property value="getMicroblogContent(findIrpMicroblogByUserid(focususerid).microblogcontent)" escapeHtml="false" /></dd>
  </dl>
</div> 
</s:if>
</s:iterator>
</s:if>

<s:else>
<s:iterator value="irpMicroblogFocuslist" > 
<s:if test="findIrpUserByFocusId(userid).username!=null">
<div class="gr2">
  <dl> 
       
       <s:if test="findIrpUserByFocusId(userid).userpic!=null">
          <dt>
          <a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' /> " >
          <img src="<%=rootPath%>file/readfile.action?fileName=<s:property value='findIrpUserByFocusId(userid).userpic' />"  alt="用户头像" width="55px"    /> 
          </a>
          </dt>
       </s:if>
       <s:if test="findIrpUserByFocusId(userid).userpic==null">
          <dt>
          <a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' /> " >
          <img <s:if test="findIrpUserByFocusId(userid).sex==2">src="<%=rootPath%>client/images/female.jpg"</s:if><s:else>src="<%=rootPath%>client/images/male.jpg"</s:else> alt="用户头像" width="55px"   /> 
          </a>
          </dt>
       </s:if>
          <dd>
          <a target="_bank"	href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' /> "  class="linkb14">
          <s:property value="getShowPageViewNameByUserId(userid)" />
          </a>
          </dd>
    <dd>
        <span>
          <a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' />&focusonfus=1 ">关注(<s:property value="MicroblogFocusCountUserid(userid)" />)</a> 
          <a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' />&focusonfus=2 ">粉丝 (<s:property value="MicroblogFocusCountFocusUserid(userid)" />)</a> 
          <a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' /> ">微知(<s:property value="MicroblogCountByUserid(userid)" />)</a>
          <s:if test="getLoginUserId()!=userid">
             <s:if test="userid in allUseridByUserid(getLoginUserId()) && getLoginUserId() in allUseridByUserid(userid)">
                <label style="margin-left: 340px;" id="eachFocus_01<s:property value="focusid" />" >互相关注</label><s:if test="userid!=2">(<a href="javascript:void(0)" onclick="cancelEachFocus(<s:property value="userid" />,<s:property value="focusid" />)"  ><label id="eachFocus_02<s:property value="focusid" />">取消</label></a>)</s:if>                                         
             </s:if>
             <s:elseif test="userid in allUseridByUserid(getLoginUserId())">
               <label style="margin-left: 350px;" id="alreadyFocus_01<s:property value="focusid" />" >已关注</label><s:if test="userid!=2">(<a href="javascript:void(0)" onclick="cancelFocus(<s:property value="userid" />,<s:property value="focusid" />)" ><label id="alreadyFocus_02<s:property value="focusid" />" >取消</label></a>)</s:if>
             </s:elseif>
             <s:elseif test="userid not in allUseridByUserid(getLoginUserId())">
               <label style="margin-left: 350px;" id="notFocus_01<s:property value="focusid" />" >未关注</label>(<a href="javascript:void(0)" onclick="okFocus(<s:property value="userid" />,<s:property value="focusid" />)" ><label id="notFocus_02<s:property value="focusid" />">关注</label></a>)
             </s:elseif>
            </s:if>                  
       </span>                   
    </dd>
    <dd  style="padding-bottom: 10px; "><s:property value="getMicroblogContent(findIrpMicroblogByUserid(userid).microblogcontent)" escapeHtml="false" /></dd>
  </dl>
</div> 
</s:if>
</s:iterator>
</s:else>
<ul style="text-align: right;"><s:property value="pageHTML" escapeHtml="false" /></ul>
 