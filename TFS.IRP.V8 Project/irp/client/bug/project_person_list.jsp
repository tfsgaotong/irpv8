<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
</head> 
<s:if test="irpProject.isclosed==@com.tfs.irp.project.entity.IrpProject@NOT_CLOSED"><%--判断圈子是不是关闭 --%>
<s:if test="irpProject.offpersonid==loginUserId">
<span style="float: right; padding-right: 10px;">
<s:if test="isPersonList==true">
		<a class="original_faridl" href="javascript:void(0);"  onclick="addProjectPerson(<s:property value='irpProject.projectid'/>)">添加成员</a>
	</s:if><s:else>
		<a class="original_faridl" href="javascript:void(0);"  onclick="yaoPersonAttention(<s:property value='irpProject.projectid'/>)">@邀请关注</a>
	</s:else>
</span>
<br/> 
</s:if>
</s:if>
 <form id="pageForm">
	<s:hidden name="pageNum" id="pageNum" /> 
	<s:hidden name="pageSize" id="pageSize" />  
</form> 
<s:iterator value="irpUsers">
	<li class="item_dl"  id="user<s:property value='userid'/>">
		<div class="ico_idl">
			<div class="ico_ffed">
				<img  href="<%=rootPath %>site/client_to_index_person.action?personid=<s:property value='userid'/>" src="<s:property value='userpic'/>" style="width: 48px;height: 48px;"/>  
			</div>
		</div>
		<div class="right_idl">
			<div class="name_ridl">
			<s:property value="username"/>
			<s:if test="irpProject.offpersonid==userid">( 负责人 )</s:if>
			</div>
				<div class="action_ridl">
				<div class="who_aridl">
					<a href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' />&focusonfus=1 "> 关注 (<s:property value="MicroblogFocusCountUserid(userid)" default="0" />)</a>
					<a href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' />&focusonfus=2 ">粉丝 (<s:property value="MicroblogFocusCountFocusUserid(userid)" default="0" />)</a>
					<a href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' /> ">微知(<s:property value="MicroblogCountByUserid(userid)" default="0" />) </a>
				</div>
				<div class="fun_aridl"> 
				<s:if test=" irpProject.offpersonid==loginUserId">
					<s:if test="loginUserId==userid">
						<a class="original_faridl"  href="javascript:void(0);" onclick="toCheckProjectOffPerson(<s:property value='loginUserId'/>)">权限转交</a>
					</s:if>
					<s:else>
						<a class="original_faridl" href="javascript:void(0);"  onclick="delPersonFromProject(<s:property value='userid'/>)">删除</a>
					</s:else>
				</s:if>
				</div>
				</div>
		</div>
	</li>
</s:iterator> 
<div class="main" align="right">
<div class="left">
   <div class="fyh">
    	<ul><s:property value="pageHTML" escapeHtml="false" /></ul>
   </div>
</div>
</div>
