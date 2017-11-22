<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
   <s:iterator  value="irpMicrobloglist">
         <dl  id="<s:property value='microblogid'/>div" onmouseout="microblogdeletejudgeOut(<s:property value='microblogid' />)" onmouseover="microblogdeletejudge(<s:property value='microblogid' />)">
            
            <s:if test="findUserByUserId(userid).userpic!=null">
        	<dt>
        	<a target="_bank"	href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' /> " >
        	<img src="<%=rootPath%>file/readfile.action?fileName=<s:property value='findUserByUserId(userid).userpic' />"  alt="用户头像" width="48px" onmouseover="personalCard(<s:property value='microblogid' />,<s:property value='userid' />)" onmouseout="personalCardOut(<s:property value='microblogid' />)"   /> 
        	</a>
        	</dt>
        	</s:if>
        	<s:if test="findUserByUserId(userid).userpic==null">
        	<dt>
        	<a target="_bank"	href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' /> " >
        	<img <s:if test="findUserByUserId(userid).sex==2">src="<%=rootPath%>client/images/female.jpg"</s:if><s:else>src="<%=rootPath%>client/images/male.jpg"</s:else> alt="用户头像" width="48px" onmouseover="personalCard(<s:property value='microblogid' />,<s:property value='userid' />)" onmouseout="personalCardOut(<s:property value='microblogid' />)"  />
        	</a>
        	 </dt>
        	
        	</s:if>
        	
            <dd><a target="_bank" style="float: left;" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' />" class="linkb14">
           <s:property value="getShowPageViewNameByUserId(userid)" />
            :
            </a>
            <s:if test="userid==2"><img style="float: left;
margin-top: -7px;" alt="微知助手" src="<%=rootPath %>client/images/micropicicons/mobile-attach-4.png"></s:if>
            <s:property value='getMicroblogContent(microblogcontent)' escapeHtml="false"  /></dd>
            <dd style="margin-left: 12px;"></dd>
            <dd style="padding-bottom: 5px;">
                <s:if test="transpondid!=0 && transpondIrpMicroblog(transpondid).isdel==0">
                  <div style="margin-left: 10px; ">
                    <em ></em>
                    <a target="_bank" style="float: left;" class="linkb14"  href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='transpondIrpMicroblog(transpondid).userid' /> ">
                     <s:property value="getShowPageViewNameByUserId(transpondIrpMicroblog(transpondid).userid)" />:</a>
                     
                     					<s:if test="transpondIrpMicroblog(transpondid).userid==2"><img style="float: left;
margin-top: -7px;" alt="微知助手" src="<%=rootPath %>client/images/micropicicons/mobile-attach-4.png"></s:if>
                     <s:property value="getMicroblogContent(transpondIrpMicroblog(transpondid).microblogcontent)" escapeHtml="false" />
                     
                     <br/>
                     <span><s:date name="transpondIrpMicroblog(transpondid).crtime" format="yyyy-MM-dd HH:mm" /> 来自<s:property value="transpondIrpMicroblog(transpondid).fromdata" />
                     <a target="_bank" style="margin-left: 230px;" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='transpondIrpMicroblog(transpondid).userid' />&focusonfus=3&microblogid=<s:property value='transpondid' />&micruserid=<s:property value='transpondIrpMicroblog(transpondid).userid' />">
                                                                       原文转发(<s:property value="transpondIrpMicroblog(transpondid).transpondcount"/>)</a> 
                     |<a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='transpondIrpMicroblog(transpondid).userid' />&focusonfus=3&microblogid=<s:property value='transpondid' />&micruserid=<s:property value='transpondIrpMicroblog(transpondid).userid' />">原文评论(<s:property value="transpondIrpMicroblog(transpondid).commentcount"/>)</a></span>
                   </div>                                                    
                </s:if>
			   <s:if test="transpondIrpMicroblog(transpondid).isdel==2">
				<!-- 显示原微知举报的 -->
			   <div style="margin-left: 10px;"> 
					<em></em>
					抱歉，由于此微知内容不符合规定，无法查看。
				</div>
			</s:if>
			<s:elseif test="transpondid!=0 && transpondIrpMicroblog(transpondid).isdel==1">
				<!-- 显示原味以删除的 -->
			   <div style="margin-left: 10px;"> 
					<em></em>
					抱歉，此微知已被作者删除。
				</div>
			
			</s:elseif>
			
			
              <div style="background-color:white;border-style: none;height: 12px;text-align: right;width: 570px;">
              <font style="float: left;">
               <s:date name="crtime" format="yyyy-MM-dd HH:mm" />&nbsp;&nbsp;来自<s:property value="fromdata"  />
               
               <a href="javascript:void(0)" id="microbloginform<s:property value='microblogid' />" style="visibility: hidden;"  class="linkc12" onclick="informdetail(<s:property value='microblogid' />)" >举报</a>
               </font>
            	<s:if test="loginuserid==userid">
                <a href="javascript:void(0)"  id="microblogDeleteLabel<s:property value='microblogid' />" style="visibility: hidden;"  class="linkc12" onclick="deleteMicroBlog(<s:property value='microblogid' />)">删除&nbsp;|&nbsp;</a>
                </s:if>
                
                <a href="javascript:void(0)" style="" class="linkc12" onclick="transpond('<s:property value='getShowPageViewNameByUserId(userid)' />',<s:property value='microblogid' />,<s:property value='userid' />,<s:property value='transpondid' /><s:if test="transpondid!=0">,<s:property value='transpondIrpMicroblog(transpondid).isdel' /></s:if>)">转发(<label id="microblogtranspondcount<s:property value='microblogid' />" style="color: rgb(135, 173, 88);"><s:property value='transpondcount' /></label>)</a>  
                &nbsp;|&nbsp;<a href="javascript:void(0)" class="linkc12" onclick="microblogComment(<s:property value='microblogid' />)">评论(<label id="microblogcommentcount<s:property value='microblogid' />" style="color: rgb(135, 173, 88);"><s:property value='commentcount' /></label>)</a>                  
                <s:if test="microblogid in collectionOfUseridlist">
                &nbsp;|&nbsp;<a href="javascript:void(0)" id="<s:property value='microblogid' />"   class="linkc12" onclick="collect(<s:property value='microblogid' />)">取消收藏</a>
                </s:if>
                <s:if test="microblogid not in collectionOfUseridlist">         
                &nbsp;|&nbsp;<a href="javascript:void(0)" id="<s:property value='microblogid' />"   class="linkc12" onclick="collect(<s:property value='microblogid' />)">收藏</a>
                </s:if>

              </div>
              
              
              
              <div id="commentDiv<s:property value='microblogid' />" style="display: none;">
                       <span id="microCommentFont_01_reply<s:property value='microblogid' />"  style="float: right;">还可以输入<label id="microCommentFontCount_01_reply<s:property value='microblogid' />" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;"></label>个字</span>
				       <span id="microCommentFont_02_reply<s:property value='microblogid' />"  style="float: right; display: none;">已超出<label id="microCommentFontCount_02_reply<s:property value='microblogid' />" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color:red;"></label>个字</span>
                  <textarea style="width: 550px;" rows="4" id="commentInfo<s:property value='microblogid' />" onkeyup="microblogCommentFontInfoReply(<s:property value='microblogid' />)"   ></textarea>
                  <p style="text-align: right;"><a href="javascript:void(0)"  onclick="commentReply(<s:property value='microblogid' />,<s:property value='userid' />)">回复</a></p>
                  <p style="text-align: right;" id="apendSpanMicroComment<s:property value='microblogid' />"></p>
              </div>
            </dd>
		 </dl>
		 </s:iterator>
<ul>
	<s:property value="pageHTML" escapeHtml="false" />
</ul>