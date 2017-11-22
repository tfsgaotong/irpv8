<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	String status = request.getParameter("status");
%>


<div id="flowmanagemenu">
	<div id="flowmanageprepend">
	<s:if test="irpLeaveapplyList.size()>0">
		<div class="pan">
			<ul class="myDiscuss list5">
				<s:iterator value="irpLeaveapplyInfos">
				<s:set var="flowUser" value="@com.tfs.irp.util.LoginUtil@findUserById(cruseridl)" />
				<li>
					<s:date name="crtime" format="yyyy-MM-dd HH:mm" />
					<div class="userIcon">
						<dl>
							<dt>
								<a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="#flowUser.userid" /></s:url>" target="_blank" title="<s:property value="#flowUser.showName" />">
									<img src="<s:property value="#flowUser.defaultUserPic" />" alt="用户头像" width="60" height="60" /> 
								</a>
							</dt>
						</dl>
					</div>
					<section>
					<aside></aside>
					<div>	
						<p style="font-size:13px;">
						<s:if test="type==20">
						<s:property value="#flowUser.showName" /> 加班申请
						</s:if>
						<s:else>
						<s:property value="#flowUser.showName" /> 请假申请
						</s:else>
						</p>	
						<p style="font-size:13px;">
							申请理由：
							<a href="javascript:void(0)" onClick="detailView(<s:property value="leaveapplyid"/>,1)"><s:property value="applyreason" /></a>
						</p>			
						<p style="font-size:13px;">						
							审核状态：
							<s:property value="advise"/>
						</p>
						<p>
						<a href="javascript:void(0)" onclick="detailView(<s:property value="leaveapplyid"/>,1)">查看详情</a>
							<s:if test='#parameters.status[0]==20'>
							&nbsp;&nbsp;&nbsp;|
		    				<a href="javascript:void(0)" onclick="handel(<s:property value="leaveapplyid" />,20)">同意</a>&nbsp;&nbsp;&nbsp;|
		    				<a href="javascript:void(0)" onclick="refuse(<s:property value="leaveapplyid" />,10)">拒绝</a>
		    				</s:if>	
						</p>
					</div>
					</section>
					<div class="clear"></div>
				</li>
				</s:iterator>
			</ul>
		</div>
		<div class="pages">
			<s:property value="pageHTML" escapeHtml="false" />
		</div>
	</s:if>
	</div>
<s:else>
<s:if test='#parameters.status[0]==20'>
	<div class="emptyDiv">没有待办信息</div>
</s:if>
<s:else>
	<div class="emptyDiv">没有已办信息</div>
</s:else>
</s:else>
</div>