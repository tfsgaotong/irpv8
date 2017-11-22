<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>

<s:iterator value="irpMicrobloglist">
<div id="informdiv<s:property value='MICROBLOGID'/>">
	<div style="margin-left: 20px;">
	   <div>
		   <div style="width: 450px;float: left;" ><font style="color: black;font-size: 14px;">举报人:</font><font size="2"><a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERIDER' /> " class="linkb14"><s:property value="findUserByUserId(USERIDER).username" /></a></font></div>
		   <div style="float: left;width: 200px;"><font  style="color: black;font-size: 14px;">举报时间:</font><font size="2"><s:date name="CRTIMEER" format="yyyy-MM-dd HH:mm" /></font></div>
	   </div>
		  <div style="float: left;width: 650px;"><font style="color: black;font-size: 14px;">举报内容:</font><font size="2"><s:property value="INFORMCONTENT" /></font></div>
		  <div style="float: left;width: 650px;text-align: right;">
		        <label style="margin-left:200px;">
		             <a href="javascript:void(0)" class="linkb14" onclick="recoverInformMicroblog(<s:property value='MICROBLOGID'/>,<s:property value='INFORMID'/>)">恢复</a>&nbsp;
		        </label> 
		  </div>
		  
	</div>
 <div>
	<dl id="<s:property value='MICROBLOGID'/>div"
		onmouseout="microblogdeletejudgeOut(<s:property value='MICROBLOGID' />)"
		onmouseover="microblogdeletejudge(<s:property value='MICROBLOGID' />)" >
	
		 
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
			<a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' /> " >
				<img id="microblogPersonalCard<s:property value='MICROBLOGID' />"
					 <s:if test="SEX==2">src="<%=rootPath%>client/images/female.jpg"</s:if><s:else>src="<%=rootPath%>client/images/male.jpg"</s:else> alt="用户头像" width="55px"
					onmouseover="personalCard(<s:property value='MICROBLOGID' />,<s:property value='USERID' />)"
					onmouseout="personalCardOut(<s:property value='MICROBLOGID' />)" />
					</a>
			</dt>
		</s:if>
		
		<dd>
			<a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' /> " class="linkb14"> <s:property value="SHOWNAME" /> </a>:
			<s:property value='getMicroblogContent(MICROBLOGCONTENT)' escapeHtml="false" />
		</dd>

		<dd style="margin-left: 12px; "></dd>
		<dd style="padding-bottom: 5px;">
			<s:if test="TRANSPONDID!=0 && transpondIrpMicroblog(TRANSPONDID).isdel==0"> 
				<div style="margin-left: 10px;"> 
					<em></em><a target="_bank" class="linkb14"  href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='transpondIrpMicroblog(TRANSPONDID).userid' /> ">
					<s:property value="getShowPageViewNameByUserId(transpondIrpMicroblog(TRANSPONDID).userid)" /></a>
					:
					<s:property value='getMicroblogContent(transpondIrpMicroblog(TRANSPONDID).microblogcontent)' escapeHtml="false" />
					<br/> <span><s:date
							name="transpondIrpMicroblog(TRANSPONDID).crtime"
							format="yyyy-MM-dd HH:mm" /> 来自<s:property
							value="transpondIrpMicroblog(TRANSPONDID).fromdata" /> 
	<a target="_bank" style="margin-left: 230px;" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='transpondIrpMicroblog(TRANSPONDID).userid' />&focusonfus=3&microblogid=<s:property value='TRANSPONDID' />&micruserid=<s:property value='transpondIrpMicroblog(TRANSPONDID).userid' /> ">原文转发(<s:property value="transpondIrpMicroblog(TRANSPONDID).transpondcount" />)</a> |
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
			<span style="margin-left: 10px;"> <s:date name="CRTIME"
					format="yyyy-MM-dd HH:mm" />&nbsp;&nbsp;来自<s:property
					value="FROMDATA" />
					
				
				
				</span>
				
			<div id="commentDiv<s:property value='MICROBLOGID' />" style="display: none; ">
			
			
   			          <span id="microCommentFont_01<s:property value='MICROBLOGID' />"  style="float: right;">还可以输入<label id="microCommentFontCount_01<s:property value='MICROBLOGID' />" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;"></label>个字</span>
				       <span id="microCommentFont_02<s:property value='MICROBLOGID' />"  style="float: right; display: none;">已超出<label id="microCommentFontCount_02<s:property value='MICROBLOGID' />" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color:red;"></label>个字</span>
				       
				       
				<textarea style="width: 550px;" rows="4" id="commentInfo<s:property value='MICROBLOGID' />" onkeyup="microblogCommentFontInfo(<s:property value='MICROBLOGID' />)" ></textarea>
				
				<p style="text-align: right;">
			           	   
					<span>
					<a class="zhuanz1"  href="javascript:void(0)" onclick="commentReply(<s:property value='MICROBLOGID' />,<s:property value='USERID' />)">回复</a></span>
				</p>
				<p style="text-align: right;" id="apendSpanMicroComment<s:property value='MICROBLOGID' />"></p>
			</div>
		</dd>
	</dl>
</div>	
</div>
</s:iterator>
<ul>
	<s:property value="pageHTML" escapeHtml="false" />
</ul>

