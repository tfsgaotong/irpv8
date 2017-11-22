<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<style>

</style> 
</head> 
<s:if test="irpProject.isclosed==@com.tfs.irp.project.entity.IrpProject@NOT_CLOSED"><%--判断圈子是不是关闭 --%>
<s:if test="irpProject.offpersonid==loginUserId">
<span style="float: right; padding-right: 10px;">
<s:if test="isPersonList==true">
		<a class="original_faridl" style="color: #6e91b2;" href="javascript:void(0);"  onclick="addProjectPerson(<s:property value='irpProject.projectid'/>)">添加成员</a>
	</s:if><s:else>
		<a class="original_faridl" style="color: #6e91b2;"href="javascript:void(0);"  onclick="yaoPersonAttention(<s:property value='irpProject.projectid'/>)">@邀请关注</a>
	</s:else>
</span>
</s:if>
</s:if>
 <form id="pageForm">
	<s:hidden name="pageNum" id="pageNum" /> 
	<s:hidden name="pageSize" id="pageSize" />  
</form> 
<s:iterator value="irpUsers">
        <div class="players">
            <ul class="playerList">
            	<li>
                	<div class="userImg"> <a  target="_blank" hideFocus="true" title="" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid'/>"><img src="<s:property value='defaultUserPic'/>"/></a></div>
                    <div class="userInfo" style="float: none;">
                    	<div class="ctrl">
                    	<s:if test=" irpProject.offpersonid==loginUserId">
						<s:if test="loginUserId==userid">
						<a class="master"  href="javascript:void(0);" onclick="toCheckProjectOffPerson(<s:property value='loginUserId'/>)">权限转交</a>
						</s:if>
						<s:else>
						<a class="del" href="javascript:void(0);"  onclick="delPersonFromProject(<s:property value='userid'/>)"></a>
						</s:else>
						</s:if>
                    	<%-- <span class="del"></span> --%>
                    	
                    	
                    	
                    	</div>
                        <h1> <a  target="_blank" hideFocus="true" title="" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid'/>"><s:property value="username"/></a>
			<s:if test="irpProject.offpersonid==userid">( 负责人 )</s:if></h1>
                        <p style="margin-top: 13px;"><span><a href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' />&focusonfus=1 "> 关注 (<s:property value="MicroblogFocusCountUserid(userid)" default="0" />)</a>
					&nbsp;<a href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' />&focusonfus=2 ">粉丝 (<s:property value="MicroblogFocusCountFocusUserid(userid)" default="0" />)</a>
					&nbsp;<a href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' /> ">微知(<s:property value="MicroblogCountByUserid(userid)" default="0" />) </a></span></p>
                    </div>
                </li>
            </ul>
        </div>

</s:iterator>
 <s:if test="irpUsers==null || irpUsers.size()==0">
 <p style="font-size: 15px;padding: 5px;">暂时没有人员!<p>
  </s:if>
<div class="main" align="right">
<div class="left">
   <div class="fyh" >
    	<ul><s:property value="pageHTML" escapeHtml="false" /></ul>
   </div>
</div>
</div>
