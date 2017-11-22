<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.tfs.irp.user.entity.IrpUser,com.tfs.irp.util.LoginUtil"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
    IrpUser loginUser = LoginUtil.getLoginUser();
%>  
 <div id="knownormalmenu">
	<div id="knownormalprepend">
	<s:if test="irpChannels.size()>0">
			<div class="pan">
				<ul class="myDiscuss list5">
					 <s:iterator value="irpChannels" var="map">
						<li id="subject<s:property value="#map.channelid" />"><s:date name="#map['crtime']" format="yyyy-MM-dd HH:mm" />
							<div class="userIcon">
								<dl>
								<s:if test="findIrpUserByFocusId(#map.cruserid).userpic!=null">
						          <dt>
						          <a target="_blank"	href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='#map.cruserid' /> " >
						          <img src="<%=rootPath%>file/readfile.action?fileName=<s:property value='findIrpUserByFocusId(#map.cruserid).userpic' />"  alt="用户头像" width="55px"    /> 
						          </a>
						          </dt>
						       </s:if>
						       <s:if test="findIrpUserByFocusId(#map.cruserid).userpic==null">
						          <dt>
						          <a target="_blank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='#map.cruserid' /> " >
						          <img <s:if test="findIrpUserByFocusId(#map.cruserid).sex==2">src="<%=rootPath%>view/images/female.jpg"</s:if><s:else>src="<%=rootPath%>view/images/male.jpg"</s:else> alt="用户头像" width="55px"   /> 
						          </a>   
						          </dt>
						       </s:if>
						       </dl>
							</div> <section> <aside></aside>
							<div>
								<a href="javascript:void(0)" onclick="updatechannelbychannelid('know',<s:property value='#map.parentid' />,<s:property value='#map.channelid'/>)" ><s:property value="#map.chnlname" /></a><a href="#"></a>
								<p>
									<a target="_blank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='#map.cruserid' /> " class="linkb14">发起人 : <s:property value="#map.cruser" /></a> <a href="javascript:void(0)" onclick="deleteSubject('subject',<s:property value='#map.parentid' />,<s:property value='#map.channelid'/>)" > 删除</a> | <a href="javascript:void(0)" onclick="updatechannelbychannelid('know',<s:property value='#map.parentid' />,<s:property value='#map.channelid'/>)" >修改</a> 
								</p>
							</div>
							</section>
							<div class="clear"></div>
						</li>
					</s:iterator>
				</ul>
			</div>
		<ul style="float:right;margin-right:65px;">
			<s:property value="pageHTML" escapeHtml="false" />
		</ul>
		</s:if>
	</div>
<s:else>
  <div>未找普通专题</div>
</s:else>
</div>