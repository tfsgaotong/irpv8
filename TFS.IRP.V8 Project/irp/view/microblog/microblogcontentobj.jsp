<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.tfs.irp.util.LoginUtil"%>
<%@ page import="com.tfs.irp.user.entity.IrpUser"%>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	IrpUser loginUser = LoginUtil.getLoginUser();
%>
			<s:iterator  value="irpMicrobloglist">
			<s:set var="microobj" value="transpondIrpMicroblog(TRANSPONDID)" ></s:set>	
            <div id="<s:property value='MICROBLOGID'/>div" onmouseover="microblogdeletejudge(<s:property value='MICROBLOGID' />,<s:property value="TRANSPONDID" />)" class="labs" >
            	<dl>
				
                	<dt>
                	
                		<a href="#" target="_blank">
                			<s:if test="USERPIC!=null">
               				<img onmouseover="personalCard(<s:property value='MICROBLOGID' />,<s:property value='USERID' />)" onmouseout="personalCardOut(<s:property value='MICROBLOGID' />)"  id="microblogPersonalCard<s:property value='MICROBLOGID' />" src="<%=rootPath%>file/readfile.action?fileName=<s:property value='USERPIC' />" alt="用户头像" onmouseover="personalCard(<s:property value='MICROBLOGID' />,<s:property value='USERID' />)" onmouseout="personalCardOut(<s:property value='MICROBLOGID' />)" />
                			</s:if>
                			<s:else>
                			<img onmouseover="personalCard(<s:property value='MICROBLOGID' />,<s:property value='USERID' />)" onmouseout="personalCardOut(<s:property value='MICROBLOGID' />)" id="microblogPersonalCard<s:property value='MICROBLOGID' />" <s:if test="SEX==2">src="<%=rootPath%>view/images/female.jpg"</s:if><s:else>src="<%=rootPath%>view/images/male.jpg"</s:else> alt="用户头像" onmouseover="personalCard(<s:property value='MICROBLOGID' />,<s:property value='USERID' />)" onmouseout="personalCardOut(<s:property value='MICROBLOGID' />)" >
                			</s:else>
                			
               			</a>
                		<aside></aside>
               		</dt>
					
                    <dd class="text" onmouseover="giveImagesValues(<s:property value='MICROBLOGID' />)">
					
                    	<div class="user"><a target="_blank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' /> "  > <s:property value="SHOWNAME" /></a><s:if test="USERID==2"><img style="float: left;margin-top: 5px;" alt="微知助手" src="<%=rootPath %>view/images/mobile-attach-4.png"></s:if><aside  style="right: 11px;"><span><s:date name="CRTIME" format="yyyy-MM-dd HH:mm" /></span><span>&nbsp;来自<s:property  value="FROMDATA" /></span><a href="javascript:void(0)" id="microbloginform<s:property value='MICROBLOGID' />"   onclick="informdetail(<s:property value='MICROBLOGID' />)" >举报</a><s:if test="loginuserid==USERID">&nbsp;<a href="javascript:void(0)" id="microblogDeleteLabel<s:property value='MICROBLOGID' />" onclick="deleteMicroBlog(<s:property value='MICROBLOGID' />)">删除</a></s:if></aside></div>
						<!-- 微知内容 -->
						<p><s:property value='MICROBLOGCONTENT' escapeHtml="false" /></p>
                        <p onclick="checkMaxPic('m_pic','m_pic',<s:property value='MICROBLOGID' />,'1')" id="microcontents<s:property value='MICROBLOGID' />"><s:property value='getMicroblogContent(MICROBLOGCONTENTIMG)' escapeHtml="false" /></p>
                        
                        	<!-- 转发开始 -->
	                        <s:if test="TRANSPONDID!=0 && #microobj.isdel==0">
	                        <article class="quote">
	                            <p>
	                            	<a target="_blank" style="float: left;" class="linkb14"  href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='#microobj.userid' /> "><s:property value="getShowPageViewNameByUserId(#microobj.userid)" />&nbsp;:&nbsp;</a>
	                            	<s:if test="#microobj.userid==2"><img style="float: left;margin-top: 5px;" alt="微知助手" src="<%=rootPath %>view/images/mobile-attach-4.png"></s:if>
	                            	<span>
	                            		<p><s:property value='#microobj.microblogcontent' escapeHtml="false" /></p>
	                            		<p  onclick="checkMaxPic('m_pic','m_pic',<s:property value='MICROBLOGID' />,'2')" id="microcontentstran<s:property value='MICROBLOGID' />"><s:property value='getMicroblogContent(#microobj.microblogcontentimg)' escapeHtml="false" /></p>
	                            	</span>
								</p>
								
								<div class="shareInfo"><span><s:date name="#microobj.crtime" format="yyyy-MM-dd HH:mm" /></span><span>来自<s:property value="#microobj.fromdata" /> </span><aside><a target="_blank" style="margin-left: 230px;" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='#microobj.userid' />&focusonfus=3&microblogid=<s:property value='TRANSPONDID' />&micruserid=<s:property value='#microobj.userid' /> ">原文转发(<s:property value="#microobj.transpondcount" />)</a><a target="_blank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='#microobj.userid' />&focusonfus=3&microblogid=<s:property value='TRANSPONDID' />&micruserid=<s:property value='#microobj.userid' /> ">原文评论(<s:property value="#microobj.commentcount" />)</a></aside></div>
								
	                        </article>
	                        </s:if>
	                        <s:if test="#microobj.isdel==2">
	                        <article class="quote">
		                        <p>
	                        		抱歉，由于此微知内容不符合规定，无法查看。
		                        </p>
		                    </article>    
	                        </s:if>
	                        <s:elseif test="TRANSPONDID!=0 && #microobj.isdel==1">
	                        <article class="quote">
		                        <p>
		                        	抱歉，此微知已被作者删除。
		                        </p>
		                    </article>    
	                        </s:elseif>
	                        <!-- 转发结束 -->
						
                        <div class="share"><aside><a href="javascript:void(0)"   onclick="transpond('<s:property value='SHOWNAME' />',<s:property value='MICROBLOGID' />,<s:property value='USERID' />,<s:property value='TRANSPONDID' /><s:if test="TRANSPONDID!=0">,<s:property value='#microobj.isdel' /></s:if>)">转发(<label id="microblogtranspondcount<s:property value='MICROBLOGID' />" ><s:property value='TRANSPONDCOUNT' /></label>)</a><a href="javascript:void(0)" onclick="microblogComment(<s:property value='MICROBLOGID' />)">评论(<label id="microblogcommentcount<s:property value='MICROBLOGID' />"><s:property  value='COMMENTCOUNT' /></label>)</a><s:if test="MICROBLOGID in collectionOfUseridlist"><a href="javascript:void(0)" id="<s:property value='MICROBLOGID' />" onclick="collect(<s:property value='MICROBLOGID' />)">取消收藏</a></s:if><s:else><a href="javascript:void(0)" id="<s:property value='MICROBLOGID' />" onclick="collect(<s:property value='MICROBLOGID' />)">收藏</a></s:else></aside></div>
                        
                        
                        
                        
						<!-- 评论 -->
						<div id="commentDiv<s:property value='MICROBLOGID' />" style="display: none;text-align: center;margin-top: 10px; ">
   							<div class="publishercss" atype="cn" id="cnpub<s:property value='MICROBLOGID' />">
								<p style="float: right;margin-right: 15px;">&nbsp;
									<span id="microCommentFont_01<s:property value='MICROBLOGID' />"  style="float: right;margin-left: -24px;">还可以输入<label id="microCommentFontCount_01<s:property value='MICROBLOGID' />" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;"></label>个字</span>
								    <span id="microCommentFont_02<s:property value='MICROBLOGID' />"  style="float: right; display: none;margin-left: -24px;">已超出<label id="microCommentFontCount_02<s:property value='MICROBLOGID' />" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color:red;"></label>个字</span>
							    </p>
								<textarea style="border: thin solid #bdbdbd;border-radius:5px;width: 650px;font-size: 14px;"    id="commentInfo<s:property value='MICROBLOGID' />" onkeyup="microblogCommentFontInfo(<s:property value='MICROBLOGID' />)" ></textarea>
							</div>

							<p style="text-align: right;margin-right: 15px;">
								<span>
								<a class="trigger"   comid="commentInfo<s:property value='MICROBLOGID' />"  href="javascript:;" style="font-size: 30px;float: left;margin-left: 10px;">☺</a>
								<input onclick="commentReply(<s:property value='MICROBLOGID' />,<s:property value='USERID' />)" style="background: none repeat scroll 0% 0% #5F9DDD;border: medium none;border-radius: 3px;text-align: center;color: #FFF;line-height: 26px;width: 50;margin-top: 4px;" type="submit"  value="回复" ></span>
								
							</p><br/>
							<p style="text-align: center;margin-left: 16px;" id="apendSpanMicroComment<s:property value='MICROBLOGID' />"></p>
						</div>
						
						
						
						
						
						
						
						<!-- 评论结束 -->
                    </dd>
					
                    <dd class="clear"></dd>
					
					
                </dl>
            </div>
            </s:iterator>