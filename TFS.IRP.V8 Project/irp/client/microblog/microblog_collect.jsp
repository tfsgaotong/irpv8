<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<p id="FocusByFocusDocumentOrWeibo">
	<h1  class="zl3" style=" font-weight:normal;  line-height:50px; border-bottom:1px  solid #efefef;text-align:left; font-family:微软雅黑; margin:0 0 20px 5px; color:#121212;">
		<a href="javascript:void(0)" id="focusOn" onclick="focusTab(this.id,'<%=rootPath%>site/myalldoccollection.action')"  ><font class="linkbh14">知识</font></a>
		<a href="javascript:void(0)" id="fans" onclick="focusTab(this.id,'<%=rootPath%>microblog/collectMicroblogList.action')" class="over" ><font class="linkbh14">微知</font></a>
	</h1>
</p> 

<s:if test="irpMicrobloglist.size()>0">
<s:iterator value="irpMicrobloglist">
   
	<dl id="<s:property value='MICROBLOGID'/>div"
		onmouseout="microblogdeletejudgeOut(<s:property value='MICROBLOGID' />)"
		onmouseover="microblogdeletejudge(<s:property value='MICROBLOGID' />)">
		 
		<s:if test="USERPIC!=null">
			<dt>
			<a target="_bank"	href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' /> " >
				<img id="microblogPersonalCard<s:property value='MICROBLOGID' />"
					src="<%=rootPath%>file/readfile.action?fileName=<s:property value='USERPIC' />"
					alt="用户头像" width="55px"
					onmouseover="personalCard(<s:property value='MICROBLOGID' />,<s:property value='USERID' />)"
					onmouseout="personalCardOut(<s:property value='MICROBLOGID' />)" />
			</a>		
			</dt>
		</s:if>
		<s:if test="USERPIC==null">
			<dt>
			<a target="_bank"	href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' /> " >
				<img id="microblogPersonalCard<s:property value='MICROBLOGID' />"
					<s:if test="SEX==2">src="<%=rootPath%>client/images/female.jpg"</s:if><s:else>src="<%=rootPath%>client/images/male.jpg"</s:else> alt="用户头像" width="55px"
					onmouseover="personalCard(<s:property value='MICROBLOGID' />,<s:property value='USERID' />)"
					onmouseout="personalCardOut(<s:property value='MICROBLOGID' />)" />
					</a>
			</dt>
		</s:if>
		<s:if test="ISDEL==0">
		<dd>
			<a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' /> " class="linkb14"> <s:property value="SHOWNAME" /> </a>:
			<s:property value='getMicroblogContent(MICROBLOGCONTENT)' escapeHtml="false" />
		</dd>

		<dd style="margin-left: 12px; "></dd>
		<dd style="padding-bottom: 5px;">
			<s:if test="TRANSPONDID!=0 && transpondIrpMicroblog(TRANSPONDID).isdel==0">
			   
				<div style="margin-left: 10px;"> 
					<em></em>@<a target="_bank" class="linkb14"  href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='transpondIrpMicroblog(TRANSPONDID).userid' /> ">
					<s:property
						value="getShowPageViewNameByUserId(transpondIrpMicroblog(TRANSPONDID).userid)" /></a>
					:
					<s:property value='getMicroblogContent(transpondIrpMicroblog(TRANSPONDID).microblogcontent)' escapeHtml="false" />
					<br /> <span><s:date
							name="transpondIrpMicroblog(TRANSPONDID).crtime"
							format="yyyy-MM-dd HH:mm" /> 来自<s:property
							value="transpondIrpMicroblog(TRANSPONDID).fromdata" /> 
	<a target="_bank" style="margin-left: 230px;" href="<%=rootPath%>site/client_to_index_person.action.action?personid=<s:property value='transpondIrpMicroblog(TRANSPONDID).userid' />&focusonfus=3&microblogid=<s:property value='TRANSPONDID' />&micruserid=<s:property value='transpondIrpMicroblog(TRANSPONDID).userid' /> ">原文转发(<s:property value="transpondIrpMicroblog(TRANSPONDID).transpondcount" />)</a> |
						<a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='transpondIrpMicroblog(TRANSPONDID).userid' />&focusonfus=3&microblogid=<s:property value='TRANSPONDID' />&micruserid=<s:property value='transpondIrpMicroblog(TRANSPONDID).userid' /> ">原文评论(<s:property
								value="transpondIrpMicroblog(TRANSPONDID).commentcount" />)</a>
					</span>
				</div>
			</s:if>
			<s:if test="transpondIrpMicroblog(TRANSPONDID).isdel==2">
				<!-- 显示原微知举报的 -->
			   <div style="margin-left: 10px;"> 
					<em></em>
					抱歉，由于此微知内容不符合规定，无法查看。
				</div>
			</s:if>
			<s:elseif test="TRANSPONDID!=0 && transpondIrpMicroblog(TRANSPONDID).isdel==1">
				<!-- 显示原味以删除的 -->
			   <div style="margin-left: 10px;"> 
					<em></em>
					抱歉，此微知已被作者删除。
				</div>
			
			</s:elseif>
			</s:if>
			<s:if test="ISDEL==1">
			<dd>
			抱歉，此微知已被作者删除。
		    </dd>
		    <dd style="margin-left: 12px; ">&nbsp;</dd>
			</s:if>
		    <s:if test="ISDEL==2">
			<dd>
			 抱歉，由于此微知内容不符合规定，无法查看。
		    </dd>
		    <dd style="margin-left: 12px; ">&nbsp;</dd>
			</s:if>
			<s:if test="ISDEL==0">
			<span style="margin-left: 10px;"> <s:date name="CRTIME"
					format="yyyy-MM-dd HH:mm" />&nbsp;&nbsp;来自<s:property
					value="FROMDATA" />
					
					<a href="javascript:void(0)" id="microbloginform<s:property value='MICROBLOGID' />" style="visibility: hidden;"  class="linkc12" onclick="informdetail(<s:property value='MICROBLOGID' />)" >举报</a>
					
					<a href="javascript:void(0)" style="margin-left: 175px;" class="linkc12" onclick="transpond('<s:property value='SHOWNAME' />',<s:property value='MICROBLOGID' />,<s:property value='USERID' />,<s:property value='TRANSPONDID' /><s:if test="TRANSPONDID!=0">,<s:property value='transpondIrpMicroblog(TRANSPONDID).isdel' /></s:if>)">
				转发(<label id="microblogtranspondcount<s:property value='MICROBLOGID' />"
				
					style="color: rgb(135, 173, 88);"><s:property value='TRANSPONDCOUNT' /> </label>)</a>
					 &nbsp;|&nbsp;<a href="javascript:void(0)" class="linkc12"  onclick="microblogComment(<s:property value='MICROBLOGID' />)">评论(<label id="microblogcommentcount<s:property value='MICROBLOGID' />"
					style="color: rgb(135, 173, 88);"><s:property  value='COMMENTCOUNT' />
				</label>)</a> <s:if test="MICROBLOGID in collectionOfUseridlist">
                &nbsp;|&nbsp;
                
                <a href="javascript:void(0)"
						id="<s:property value='MICROBLOGID' />" class="linkc12"
						onclick="collect(<s:property value='MICROBLOGID' />)">取消收藏</a>
				</s:if> <s:if test="MICROBLOGID not in collectionOfUseridlist">         
                &nbsp;|&nbsp;<a href="javascript:void(0)"
						id="<s:property value='MICROBLOGID' />" class="linkc12"
						onclick="collect(<s:property value='MICROBLOGID' />)">收藏</a>
						
						
				</s:if> <s:if test="loginuserid==USERID">
					<a href="javascript:void(0)"
						id="microblogDeleteLabel<s:property value='MICROBLOGID' />"
						style="visibility: hidden;" class="linkc12"
						onclick="deleteMicroBlog(<s:property value='MICROBLOGID' />)">&nbsp;|&nbsp;删除</a>
				</s:if> </span>
				</s:if>
				<s:if test="ISDEL==1 || ISDEL==2">
				<span style="margin-left: 500px;"> 
				<s:if test="MICROBLOGID in collectionOfUseridlist">
               
                <a href="javascript:void(0)"
						id="<s:property value='MICROBLOGID' />" class="linkc12"
						onclick="collect(<s:property value='MICROBLOGID' />)">取消收藏</a>
				</s:if> <s:if test="MICROBLOGID not in collectionOfUseridlist">         
                &nbsp;|&nbsp;<a href="javascript:void(0)"
						id="<s:property value='MICROBLOGID' />" class="linkc12"
						onclick="collect(<s:property value='MICROBLOGID' />)">收藏</a>
						
						
				</s:if> </span>
				
				</s:if>
				
			<div id="commentDiv<s:property value='MICROBLOGID' />" style="display: none; ">
			
			
   			          <span id="microCommentFont_01<s:property value='MICROBLOGID' />"  style="float: right;">还可以输入<label id="microCommentFontCount_01<s:property value='MICROBLOGID' />" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;"></label>个字</span>
				       <span id="microCommentFont_02<s:property value='MICROBLOGID' />"  style="float: right; display: none;">已超出<label id="microCommentFontCount_02<s:property value='MICROBLOGID' />" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color:red;"></label>个字</span>
				       
				       
				<textarea style="width: 550px;" rows="4" id="commentInfo<s:property value='MICROBLOGID' />" onkeyup="microblogCommentFontInfo(<s:property value='MICROBLOGID' />)" ></textarea>
				
				<p style="text-align: right;">
			           	   
					<span>
					<a href="javascript:void(0)" onclick="commentReply(<s:property value='MICROBLOGID' />,<s:property value='USERID' />)">回复</a></span>
				</p>
				<p style="text-align: right;" id="apendSpanMicroComment<s:property value='MICROBLOGID' />"></p>
			</div>
		</dd>
	</dl>
</s:iterator>
<ul>
	<s:property value="pageHTML" escapeHtml="false" />
</ul>
</s:if>
<s:else>
 <div>暂时没有收藏的微知</div>
</s:else>