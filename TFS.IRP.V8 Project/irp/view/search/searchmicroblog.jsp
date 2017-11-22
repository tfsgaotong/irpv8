<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.tfs.irp.util.LoginUtil"%>
<%@ page import="com.tfs.irp.user.entity.IrpUser"%>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	IrpUser loginUser = LoginUtil.getLoginUser();
	long loginuserid = LoginUtil.getLoginUserId();
%>
<div class="reorder">
		<section class="classify">
	       	<!-- <div class="title"><p><em style="padding:7px 10px;">主板</em>&nbsp;-&nbsp;详细筛选</p></div> -->
	           <div class="items">
	               <table width="100%">
	                   <tr>
	                   	<td width="120" align="right" valign="top" class="itemClass">时间：</td>
	                       <td width="10"></td>
	                       <td valign="top" class="itemList">
	                       	<strong <s:if test="paramMap!='WEEK' & paramMap !='MONTH' & paramMap !='YEAR'">class="checked"</s:if> onclick="searchVal('<s:property value="searchInfo"/>','rad_weibo')">全部</strong><strong <s:if test="paramMap=='WEEK'">class="checked"</s:if> onclick="searchVal('<s:property value="searchInfo"/>','rad_weibo','WEEK')">最近一周</strong><strong <s:if test="paramMap=='MONTH'">class="checked"</s:if> onclick="searchVal('<s:property value="searchInfo"/>','rad_weibo','MONTH')">最近一月</strong><strong <s:if test="paramMap=='YEAR'">class="checked"</s:if> onclick="searchVal('<s:property value="searchInfo"/>','rad_weibo','YEAR')">最近一年</strong>
	                       </td>
	                   </tr>
	                    <input type="hidden" id="time" value="<s:property value="paramMap"/>">
	                    <input type="hidden" id="sort" value="<s:property value="searchsort"/>">
	                   <tr>
	                   	<td width="120" align="right" valign="top" class="itemClass">排序：</td>
	                       <td width="10"></td>
	                       <td valign="top" class="itemList">
	                       <strong <s:if test="searchsort!=1">class="checked"</s:if> onclick="searchVal('<s:property value="searchInfo"/>','rad_weibo','2')">相关度</strong>	<strong <s:if test="searchsort==1">class="checked"</s:if> onclick="searchVal('<s:property value="searchInfo"/>','rad_weibo','1')">时间</strong>
	                       </td>
	                   </tr>
	               </table>
	               <section class="hidden">
	               </section>
	           </div>
	       </section>
       <div class="area20"></div>
  	 </div>
<s:if test="microbloglist.size()>0">
<section class="weibo" style="margin-left:15px;">
<section class="discussions" id="microblogcontent">
<s:iterator value="microbloglist">
	<s:set var="microblogobj" value="transpondIrpMicroblog(<s:property value='findMicById(MICROBLOGID,1)' />)" ></s:set>
            <div id="<s:property value='MICROBLOGID'/>div" onmouseover="microblogdeletejudge(<s:property value='MICROBLOGID' />,<s:property value='findMicById(MICROBLOGID,1)' />)" class="labs" >
            	<dl>
                	<dt>
                		<a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID.toString().substring(1,USERID.toString().length()-1)' /> ">
                			<s:if test="getIrpUserByUserid(USERID.toString().substring(1,USERID.toString().length()-1)).userpic!=null">
               				<img onmouseover="personalCard(<s:property value='MICROBLOGID' />,<s:property value='USERID.toString().substring(1,USERID.toString().length()-1)' />)" onmouseout="personalCardOut(<s:property value='MICROBLOGID' />)" id="microblogPersonalCard<s:property value='MICROBLOGID' />" src="<%=rootPath%>file/readfile.action?fileName=<s:property value='getIrpUserByUserid(USERID.toString().substring(1,USERID.toString().length()-1)).userpic' />" alt="用户头像" onmouseover="personalCard(<s:property value='MICROBLOGID' />,<s:property value='USERID.toString().substring(1,USERID.toString().length()-1)' />)" onmouseout="personalCardOut(<s:property value='MICROBLOGID' />)" />
                			</s:if>
                			<s:else>
                			<img onmouseover="personalCard(<s:property value='MICROBLOGID' />,<s:property value='USERID.toString().substring(1,USERID.toString().length()-1)' />)" onmouseout="personalCardOut(<s:property value='MICROBLOGID' />)" id="microblogPersonalCard<s:property value='MICROBLOGID' />" <s:if test="SEX==2">src="<%=rootPath%>view/images/female.jpg"</s:if><s:else>src="<%=rootPath%>view/images/male.jpg"</s:else> alt="用户头像" onmouseover="personalCard(<s:property value='MICROBLOGID' />,<s:property value='USERID.toString().substring(1,USERID.toString().length()-1)' />)" onmouseout="personalCardOut(<s:property value='MICROBLOGID' />)" >
                			</s:else>
               			</a>
                		<aside></aside>
               		</dt>
                    <dd class="text" onmouseover="giveImagesValues(<s:property value='MICROBLOGID' />)">
                 
					
                    	<div class="user"><a target="_blank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID.toString().substring(1,USERID.toString().length()-1)' /> "  > <s:property value="showPageName(USERID.toString().substring(1,USERID.toString().length()-1))" /></a><s:if test="USERID.toString().substring(1,USERID.toString().length()-1)==2"><img style="float: left;margin-top: 5px;" alt="微知助手" src="<%=rootPath %>view/images/mobile-attach-4.png"></s:if><aside style="right: 11px;"><span><s:date name="CRTIME" format="yyyy-MM-dd HH:mm" /></span><span>&nbsp;来自<s:property  value="FROMDATA" /></span><a href="javascript:void(0)" id="microbloginform<s:property value='MICROBLOGID' />"   onclick="informdetail(<s:property value='MICROBLOGID' />)" >举报</a><s:if test="loginuserid==USERID.toString().substring(1,USERID.toString().length()-1)">&nbsp;<a href="javascript:void(0)" id="microblogDeleteLabel<s:property value='MICROBLOGID' />" onclick="deleteMicroBlog(<s:property value='MICROBLOGID' />)">删除</a></s:if></aside></div>
						<!-- 微知内容 -->
                        <p><s:property value='MICROBLOGCONTENT' escapeHtml="false" /></p>
                        <p  onclick="checkMaxPic('m_pic','m_pic',<s:property value='MICROBLOGID' />,'1')" id="microcontents<s:property value='MICROBLOGID' />"><s:property value='getMicroblogContent(MICROBLOGCONTENTIMG)' escapeHtml="false" /></p>	
                        	<!-- 转发开始 -->
	                        <s:if test="<s:property value='findMicById(MICROBLOGID,1)' />!=0 && #microblogobj.isdel==0">
	                        <article class="quote">
	                            <p>
	                            	<a target="_blank" style="float: left;" class="linkb14"  href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='transpondIrpMicroblog(TRANSPONDID).userid' /> "><s:property value="getShowPageViewNameByUserId(transpondIrpMicroblog(TRANSPONDID).userid)" />&nbsp;:&nbsp;</a>
	                            	<s:if test="transpondIrpMicroblog(TRANSPONDID).userid==2"><img style="float: left;margin-top: 5px;" alt="微知助手" src="<%=rootPath %>view/images/mobile-attach-4.png"></s:if>
	                            	<span onclick="checkMaxPic('m_pic','m_pic',<s:property value='MICROBLOGID' />,'2')" id="microcontentstran<s:property value='MICROBLOGID' />">
	                            		<s:property value='getMicroblogContent(transpondIrpMicroblog(TRANSPONDID).microblogcontent)' escapeHtml="false" />
	                            	</span>
								</p>
								
								<div class="shareInfo"><span><s:date name="transpondIrpMicroblog(TRANSPONDID).crtime" format="yyyy-MM-dd HH:mm" /></span><span>来自<s:property value="transpondIrpMicroblog(TRANSPONDID).fromdata" /> </span><aside><a target="_blank" style="margin-left: 230px;" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='transpondIrpMicroblog(TRANSPONDID).userid' />&focusonfus=3&microblogid=<s:property value='TRANSPONDID' />&micruserid=<s:property value='transpondIrpMicroblog(TRANSPONDID).userid' /> ">原文转发(<s:property value="transpondIrpMicroblog(TRANSPONDID).transpondcount" />)</a><a target="_blank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='transpondIrpMicroblog(TRANSPONDID).userid' />&focusonfus=3&microblogid=<s:property value='TRANSPONDID' />&micruserid=<s:property value='transpondIrpMicroblog(TRANSPONDID).userid' /> ">原文评论(<s:property value="transpondIrpMicroblog(TRANSPONDID).commentcount" />)</a></aside></div>
								
	                        </article>
	                        </s:if>
	                        <s:if test="#microblogobj.isdel==2">
	                        <article class="quote">
		                        <p>
	                        		抱歉，由于此微知内容不符合规定，无法查看。
		                        </p>
		                    </article>    
	                        </s:if>
	                        <s:elseif test="<s:property value='findMicById(MICROBLOGID,1)' />!=0 && #microblogobj.isdel==1">
	                        <article class="quote">
		                        <p>
		                        	抱歉，此微知已被作者删除。
		                        </p>
		                    </article>    
	                        </s:elseif>
	                        <!-- 转发结束 -->
                        <div class="share"><aside><a href="javascript:void(0)"   onclick="transpond('<s:property value="showPageName(USERID.toString().substring(1,USERID.toString().length()-1))" />',<s:property value='MICROBLOGID' />,<s:property value='USERID.toString().substring(1,USERID.toString().length()-1)' />,<s:property value='findMicById(MICROBLOGID,1)' /><s:if test="findMicById(MICROBLOGID,1)!=0">,<s:property value='findMicById(MICROBLOGID,2)' /></s:if>)">转发(<label id="microblogtranspondcount<s:property value='MICROBLOGID' />" ><s:property value='findMicById(MICROBLOGID,3)' /></label>)</a><a href="javascript:void(0)" onclick="microblogComment(<s:property value='MICROBLOGID' />)">评论(<label id="microblogcommentcount<s:property value='MICROBLOGID' />"><s:property value='findMicById(MICROBLOGID,4)' /></label>)</a><s:if test="MICROBLOGID in collectionOfUseridlist"><a href="javascript:void(0)" id="<s:property value='MICROBLOGID' />" onclick="collect(<s:property value='MICROBLOGID' />)">取消收藏</a></s:if><s:else><a href="javascript:void(0)" id="<s:property value='MICROBLOGID' />" onclick="collect(<s:property value='MICROBLOGID' />)">收藏</a></s:else></aside></div>
						<!-- 评论 -->
						<div id="commentDiv<s:property value='MICROBLOGID' />" style="display: none;text-align: center;margin-top: 10px; ">
							<textarea style="width: 650px;" rows="3" id="commentInfo<s:property value='MICROBLOGID' />" onkeyup="microblogCommentFontInfo(<s:property value='MICROBLOGID' />)" ></textarea>
							<p style="float: left;margin-left: 40px;">
								<span id="microCommentFont_01<s:property value='MICROBLOGID' />"  style="float: right;margin-left: -24px;">还可以输入<label id="microCommentFontCount_01<s:property value='MICROBLOGID' />" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;"></label>个字</span>
							    <span id="microCommentFont_02<s:property value='MICROBLOGID' />"  style="float: right; display: none;margin-left: -24px;">已超出<label id="microCommentFontCount_02<s:property value='MICROBLOGID' />" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color:red;"></label>个字</span>
						    </p>
							<p style="text-align: right;margin-right: 15px;">
								<span>
								<input onclick="commentReply(<s:property value='MICROBLOGID' />,<s:property value='loginuserid' />)" style="background: none repeat scroll 0% 0% #5F9DDD;border: medium none;border-radius: 3px;text-align: center;color: #FFF;line-height: 26px;width: 50;margin-top: 4px;" type="submit"  value="回复" ></span>
							</p><br/>
							<p style="text-align: center;margin-left: 16px;" id="apendSpanMicroComment<s:property value='MICROBLOGID' />"></p>
						</div>
						<!-- 评论结束 -->
                    </dd>
                    <dd class="clear"></dd>
                </dl>
            </div>
	</s:iterator>
	<div class="pages"><s:property value="pageHTML" escapeHtml="false" /></div>
</section>
</section>
</s:if>
<s:else>
<div style="height: 250px;" >没有找到相关记录</div>
</s:else>