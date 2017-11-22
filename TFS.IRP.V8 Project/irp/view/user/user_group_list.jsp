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
	<ul class="list6">
		<s:iterator value="irpUsers">
			<li style="width:auto;padding:0;background:none;margin-bottom:20px; float:left; display:inline; margin-right:20px; height:28px; overflow:hidden;">
				<a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="userid" /></s:url>" target="_blank"><s:property value="showName" /></a><span></span><aside title="移除用户" onclick="removeUser(<s:property value="userid" />)">X</aside>
			</li>
		</s:iterator>
	</ul>
</s:if>
<s:else>
<div style="padding: 5px 10px;">当前个人组织中暂无用户</div>
</s:else>