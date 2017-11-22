<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<div style="text-align: right;margin-right: 30px;"><a href="javascript:void(0)" class="linkbh14" onclick="emptyIllegality()">清空</a></div>
<s:iterator value="irpMicrobloglist">
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
					
					
				<a href="javascript:void(0)" onclick="thoroughDelete(<s:property value='MICROBLOGID' />)" class="linkc12" style="margin-left: 350px;">彻底删除</a>
				</span>
				
		</dd>
	</dl>
</s:iterator>
<ul>
	<s:property value="pageHTML" escapeHtml="false" />
</ul>


