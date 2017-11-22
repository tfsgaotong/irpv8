<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
  
  
<s:if test="irpMicrobloglist.size()>0">
<div class="labs">
<s:iterator value="irpMicrobloglist">
   
	<dl id="<s:property value='MICROBLOGID'/>div" onmouseout="microblogdeletejudgeOut(<s:property value='MICROBLOGID' />)" onmouseover="microblogdeletejudge(<s:property value='MICROBLOGID' />)">		 
		  <dt>                	
            <a href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' />" target="_blank">
              <s:if test="USERPIC!=null">
               <img id="microblogPersonalCard<s:property value='MICROBLOGID' />"
					src="<%=rootPath%>file/readfile.action?fileName=<s:property value='USERPIC' />"
					alt="用户头像" width="55px"/>
               </s:if>
              <s:else>
                <img id="microblogPersonalCard<s:property value='MICROBLOGID' />"
					<s:if test="SEX==2">src="<%=rootPath%>view/images/female.jpg"</s:if><s:else>src="<%=rootPath%>view/images/male.jpg"</s:else> alt="用户头像" width="55px"/>
              </s:else>                			
             </a>                		
          </dt>
          	 <dd class="text">
         		<div class="user"><a target="_blank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' />"  ><s:property value="getShowPageViewNameByUserId(USERID)" /></a>
	                <s:if test="USERID==2">
	                  <img style="float: left;margin-top: 5px;" alt="微知助手" src="<%=rootPath %>view/images/micropicicons/mobile-attach-4.png">
	                </s:if>
	                <aside>
		              <span><s:date name="CRTIME" format="yyyy-MM-dd HH:mm" /></span>
		              <span>&nbsp;来自<s:property value="FROMDATA" /></span>
		              <a href="javascript:void(0)" id="microbloginform<s:property value='MICROBLOGID' />"   onclick="informdetail(<s:property value='MICROBLOGID' />)" >举报</a>
		              <s:if test="loginuserid==USERID">&nbsp;<a href="javascript:void(0)" id="microblogDeleteLabel<s:property value='MICROBLOGID' />" onclick="deleteMicroBlog(<s:property value='MICROBLOGID' />)">删除</a></s:if>
		            </aside>
                </div>
                <p><s:property value='MICROBLOGCONTENT' escapeHtml="false" /></p>
                <s:if test="TRANSPONDID!=0 && transpondIrpMicroblog(TRANSPONDID).isdel==0">
 	                        <article class="quote">
	                            <p>
	                            	<a target="_blank" style="float: left;" class="linkb14"  href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='transpondIrpMicroblog(TRANSPONDID).userid' /> ">
	                            	<s:property value="getShowPageViewNameByUserId(transpondIrpMicroblog(TRANSPONDID).userid)" />&nbsp;:&nbsp;</a>
	                            	<s:if test="transpondIrpMicroblog(TRANSPONDID).userid==2"><img style="float: left;margin-top: 5px;" alt="微知助手" src="<%=rootPath %>view/images/micropicicons/mobile-attach-4.png"></s:if>
	                            	<s:property value="transpondIrpMicroblog(TRANSPONDID).microblogcontent" escapeHtml="false" />
								</p>
								
								<div class="shareInfo"><span><s:date name="transpondIrpMicroblog(TRANSPONDID).crtime" format="yyyy-MM-dd HH:mm" /></span>&nbsp;&nbsp;<span>来自<s:property value="transpondIrpMicroblog(TRANSPONDID).fromdata" /> </span><aside><a target="_blank" style="margin-left: 230px;" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='transpondIrpMicroblog(TRANSPONDID).userid' />&focusonfus=3&microblogid=<s:property value='TRANSPONDID' />&micruserid=<s:property value='transpondIrpMicroblog(TRANSPONDID).userid' /> ">原文转发(<s:property value="transpondIrpMicroblog(TRANSPONDID).transpondcount" />)</a><a target="_blank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='transpondIrpMicroblog(TRANSPONDID).userid' />&focusonfus=3&microblogid=<s:property value='TRANSPONDID' />&micruserid=<s:property value='transpondIrpMicroblog(TRANSPONDID).userid' /> ">原文评论(<s:property value="transpondIrpMicroblog(TRANSPONDID).commentcount" />)</a></aside></div>
								
	                        </article>               
                </s:if>  
                <s:if test="transpondIrpMicroblog(TRANSPONDID).isdel==2">
	                        <article class="quote">
		                        <p>
	                        		抱歉，由于此微知内容不符合规定，无法查看。
		                        </p>
		                    </article>    
	                        </s:if>
	                        <s:elseif test="TRANSPONDID!=0 && transpondIrpMicroblog(TRANSPONDID).isdel==1">
	                        <article class="quote">
		                        <p>
		                        	抱歉，此微知已被作者删除。
		                        </p>
		                    </article>    
	                        </s:elseif>
<div class="share">
	                        <aside>
		                        <a href="javascript:void(0)"   onclick="transpond('<s:property value='getShowPageViewNameByUserId(USERID)' />',<s:property value='MICROBLOGID' />,<s:property value='USERID' />,<s:property value='TRANSPONDID' /><s:if test="TRANSPONDID!=0">,<s:property value='transpondIrpMicroblog(TRANSPONDID).isdel' /></s:if>)">转发(<label id="microblogtranspondcount<s:property value='MICROBLOGID' />" ><s:property value='TRANSPONDCOUNT' /></label>)</a>
		                        <a href="javascript:void(0)" onclick="microblogComment(<s:property value='MICROBLOGID' />)">评论(<label id="microblogcommentcount<s:property value='MICROBLOGID' />"><s:property value='COMMENTCOUNT' /></label>)</a>
		                        <s:if test="MICROBLOGID in collectionOfUseridlist">
		                        	<a href="javascript:void(0)" id="<s:property value='MICROBLOGID' />" onclick="collect(<s:property value='MICROBLOGID' />)">取消收藏</a>
		                        </s:if>
		                        <s:else>
		                        	<a href="javascript:void(0)" id="<s:property value='MICROBLOGID' />" onclick="collect(<s:property value='MICROBLOGID' />)">收藏</a>
		                        </s:else>
	                        </aside>
                        </div>
						<!-- 评论 -->
						<div id="commentDiv<s:property value='MICROBLOGID' />" style="display: none;text-align: center;margin-top: 10px; ">
						
   							<p style="text-align: right;padding-right: 14px;   ">
								<span id="microCommentFont_01<s:property value='MICROBLOGID' />"  style="float: right;">还可以输入<label id="microCommentFontCount_01<s:property value='MICROBLOGID' />" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;"></label>个字</span>
							    <span id="microCommentFont_02<s:property value='MICROBLOGID' />"  style="float: right; display: none;">已超出<label id="microCommentFontCount_02<s:property value='MICROBLOGID' />" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color:red;"></label>个字</span>
						    </p>
						    
							<textarea style="height:55px; width: 650px;border: thin solid #bdbdbd;border-radius:5px;font-size:14px;" rows="3" id="commentInfo<s:property value='MICROBLOGID' />" onkeyup="microblogCommentFontInfo(<s:property value='MICROBLOGID' />)"  ></textarea>

							<p style="text-align: right;margin-right: 15px; ">
								<span>
								<a class="trigger"   comid="commentInfo<s:property value='MICROBLOGID' />"  href="javascript:;" style="font-size: 30px;float: left;margin-left: 10px;">☺</a>
								<input onclick="commentReply(<s:property value='MICROBLOGID' />,<s:property value='USERID' />)" style="background: none repeat scroll 0% 0% #5F9DDD;border: medium none;border-radius: 3px;text-align: center;color: #FFF;line-height: 26px;width: 50;margin-top: 4px;padding:0 13px;" type="submit"  value="回复" ></span>
								
							</p><br/>
							<p style="text-align: center;margin-left: 15px;" id="apendSpanMicroComment<s:property value='MICROBLOGID' />"></p>
						</div>	                        	                      
         	 </dd>
	</dl>
</s:iterator>

</div>
</s:if>

<s:else>
<div style="padding-left:15px;">暂时没有收藏的微知</div>
</s:else>