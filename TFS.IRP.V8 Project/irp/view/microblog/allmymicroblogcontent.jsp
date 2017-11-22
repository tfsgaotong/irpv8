<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.tfs.irp.util.LoginUtil"%>
<%@ page import="com.tfs.irp.user.entity.IrpUser"%>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	IrpUser loginUser = LoginUtil.getLoginUser();
%>
<div class="labels">微知列表</div> 
			<div class="line area20"></div>
<s:if test="irpMicrobloglist.size()>0">
<s:iterator value="irpMicrobloglist">	
            <div class="labs" id="<s:property value='MICROBLOGID'/>div">
            	<dl>				
                	<dt>                	
                		<a href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' />" target="_blank">
                			<s:if test="findUserByUserId(userid).userpic!=null">
               					<img src="<%=rootPath%>file/readfile.action?fileName=<s:property value='findUserByUserId(userid).userpic' />"  alt="用户头像" width="48px" onmouseover="personalCard(<s:property value='microblogid' />,<s:property value='userid' />)" onmouseout="personalCardOut(<s:property value='microblogid' />)"   />
                			</s:if>
                			<s:else>
                				<img <s:if test="findUserByUserId(userid).sex==2">src="<%=rootPath%>view/images/female.jpg"</s:if><s:else>src="<%=rootPath%>view/images/male.jpg"</s:else> alt="用户头像" width="48px" onmouseover="personalCard(<s:property value='microblogid' />,<s:property value='userid' />)" onmouseout="personalCardOut(<s:property value='microblogid' />)"  />
                			</s:else>                			
               			</a>                		
               		</dt>					
                    <dd class="text">					
                    	<div class="user"><a target="_blank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' />"  ><s:property value="getShowPageViewNameByUserId(userid)" /></a>
	                    	<s:if test="USERID==2">
	                    		<img style="float: left;margin-top: 5px;" alt="微知助手" src="<%=rootPath %>view/images/micropicicons/mobile-attach-4.png">
	                    	</s:if>
	                    	<aside>
		                    	<span><s:date name="crtime" format="yyyy-MM-dd HH:mm" /></span>
		                    	<span>&nbsp;来自<s:property value="fromdata"  /></span>
		                    	<a href="javascript:void(0)" id="microbloginform<s:property value='microblogid' />"   onclick="informdetail(<s:property value='microblogid' />)" >举报</a>
		                    	<s:if test="loginuserid==USERID">&nbsp;<a href="javascript:void(0)" id="microblogDeleteLabel<s:property value='microblogid' />" onclick="deleteMicroBlog(<s:property value='microblogid' />)">删除</a></s:if>
		                    </aside>
                    	</div>
						<!-- 微知内容 -->
                        <p><s:property value='getMicroblogContent(microblogcontent)' escapeHtml="false" /></p>
                        
                        	<!-- 转发开始 -->
	                        <s:if test="transpondid!=0 && transpondIrpMicroblog(transpondid).isdel==0">
	                        <article class="quote">
	                            <p>
	                            	<a target="_blank" style="float: left;" class="linkb14"  href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='transpondIrpMicroblog(transpondid).userid' /> ">
	                            	<s:property value="getShowPageViewNameByUserId(transpondIrpMicroblog(transpondid).userid)" />&nbsp;:&nbsp;</a>
	                            	<s:if test="transpondIrpMicroblog(transpondid).userid==2"><img style="float: left;margin-top: 5px;" alt="微知助手" src="<%=rootPath %>view/images/micropicicons/mobile-attach-4.png"></s:if>
	                            	<s:property value="getMicroblogContent(transpondIrpMicroblog(transpondid).microblogcontent)" escapeHtml="false" />
								</p>
								
								<div class="shareInfo"><span><s:date name="transpondIrpMicroblog(transpondid).crtime" format="yyyy-MM-dd HH:mm" /></span>&nbsp;&nbsp;<span>来自<s:property value="transpondIrpMicroblog(transpondid).fromdata" /> </span><aside><a target="_blank" style="margin-left: 230px;" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='transpondIrpMicroblog(transpondid).userid' />&focusonfus=3&microblogid=<s:property value='transpondid' />&micruserid=<s:property value='transpondIrpMicroblog(transpondid).userid' /> ">原文转发(<s:property value="transpondIrpMicroblog(transpondid).transpondcount" />)</a><a target="_blank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='transpondIrpMicroblog(transpondid).userid' />&focusonfus=3&microblogid=<s:property value='transpondid' />&micruserid=<s:property value='transpondIrpMicroblog(transpondid).userid' /> ">原文评论(<s:property value="transpondIrpMicroblog(transpondid).commentcount" />)</a></aside></div>
								
	                        </article>
	                        </s:if>
	                        <s:if test="transpondIrpMicroblog(transpondid).isdel==2">
	                        <article class="quote">
		                        <p>
	                        		抱歉，由于此微知内容不符合规定，无法查看。
		                        </p>
		                    </article>    
	                        </s:if>
	                        
	                        <s:elseif test="transpondid!=0 && transpondIrpMicroblog(transpondid).isdel==1">
	                        <article class="quote">
		                        <p>
		                        	抱歉，此微知已被作者删除。
		                        </p>
		                    </article>    
	                        </s:elseif>
	                        <!-- 转发结束 -->
						
                        <div class="share">
	                        <aside>
		                        <a href="javascript:void(0)"   onclick="transpond('<s:property value='getShowPageViewNameByUserId(userid)' />',<s:property value='microblogid' />,<s:property value='userid' />,<s:property value='transpondid' /><s:if test="transpondid!=0">,<s:property value='transpondIrpMicroblog(transpondid).isdel' /></s:if>)">转发(<label id="microblogtranspondcount<s:property value='microblogid' />" ><s:property value='transpondcount' /></label>)</a>
		                        <a href="javascript:void(0)" onclick="microblogComment(<s:property value='microblogid' />)">评论(<label id="microblogcommentcount<s:property value='microblogid' />"><s:property value='commentcount' /></label>)</a>
		                        <s:if test="microblogid in collectionOfUseridlist">
		                        	<a href="javascript:void(0)" id="<s:property value='microblogid' />" onclick="collect(<s:property value='microblogid' />)">取消收藏</a>
		                        </s:if>
		                        <s:else>
		                        	<a href="javascript:void(0)" id="<s:property value='microblogid' />" onclick="collect(<s:property value='microblogid' />)">收藏</a>
		                        </s:else>
	                        </aside>
                        </div>
						<!-- 评论 -->
						<div id="commentDiv<s:property value='microblogid' />" style="display: none;text-align: center;margin-top: 10px; ">
   
							<textarea style="width: 600px;" rows="3" id="commentInfo<s:property value='microblogid' />" onkeyup="microblogCommentFontInfo(<s:property value='microblogid' />)" ></textarea>
							<p style="float: left;margin-left: 40px;">
								<span id="microCommentFont_01<s:property value='microblogid' />"  style="float: right;">还可以输入<label id="microCommentFontCount_01<s:property value='microblogid' />" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;"></label>个字</span>
							    <span id="microCommentFont_02<s:property value='microblogid' />"  style="float: right; display: none;">已超出<label id="microCommentFontCount_02<s:property value='microblogid' />" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color:red;"></label>个字</span>
						    </p>
							<p style="text-align: right;margin-right: 40px;">
								<span>
								<input onclick="commentReply(<s:property value='microblogid' />,<s:property value='userid' />)" style="background: none repeat scroll 0% 0% #5F9DDD;border: medium none;border-radius: 3px;text-align: center;color: #FFF;line-height: 26px;width: 50;margin-top: 4px;" type="submit"  value="回复" ></span>
								
							</p><br/>
							<p style="text-align: center;margin-left: 43px;" id="apendSpanMicroComment<s:property value='microblogid' />"></p>
						</div>
						<!-- 评论结束 -->
                    </dd>					
                    <dd class="clear"></dd>										
                </dl>
            </div>	
</s:iterator>
</s:if>

<s:else>
<div style="padding-left:15px;">暂时没有微知</div>
</s:else>
<div class="pages" style="text-align:right;"><s:property value="pageHTML" escapeHtml="false" /></div>