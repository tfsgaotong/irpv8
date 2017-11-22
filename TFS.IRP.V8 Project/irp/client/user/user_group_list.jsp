<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<script type="text/javascript">
<!--
function removeUser(_userId){
	$.ajax({
		url: "<%=rootPath %>user/remove_usergroup_dowith.action",
	   	type: "POST",
	   	data: {
	   		groupId: <s:property value="groupId" />,
	   		userIds: _userId
	   	},
	   	success: function(msg){
	   		if(msg=="1"){
				$.dialog.tips('删除成功',1,'32X32/succ.png',function(){
					showUserGroupList(<s:property value="groupId" />);
				});
			}else{
				$.dialog.tips('删除失败',1,'32X32/fail.png');
			}
	   	}
	});
}
//-->
</script>
<s:if test="irpUsers!=null && irpUsers.size()>0">
<div class="tagList" style="background-color:#FFFFFF;border:0px none;">
	<ul>
		<s:iterator value="irpUsers">
			<li>
				<span class="tagName"><s:if test="truename!=null && truename.length()>0"><s:property value="truename" /></s:if><s:else><s:property value="username" /></s:else></span>
				<a href="javascript:void(0)" class="tagDelete" onclick="removeUser(<s:property value="userid" />)" title="移除用户"></a>
			</li>
		</s:iterator>
	</ul>
</div>
</s:if>
<s:else>
<div style="padding: 5px 10px;">当前个人组织中暂无用户</div>
</s:else>