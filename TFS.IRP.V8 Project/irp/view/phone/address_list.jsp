<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.tfs.irp.user.entity.IrpUser,com.tfs.irp.util.LoginUtil"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
    IrpUser loginUser = LoginUtil.getLoginUser();
%>
<!-- 通讯录列表页 -->
<form>
	<s:hidden name="sname" id="sm" />
	<s:hidden name="searchWord" id="kd" />
	<s:hidden name="pageNum" id="pageNumform" />
	<s:hidden name="pageSizeclient" id="pageSizeclientform" />
</form>
<s:if test="irpUsers.size!=0">
	<div class="liebiao">
		<table>
            <tr>
				<th style="width:11%">姓名</th>
				<th style="width:11%">性别</th>
				<th style="width:16%">手机号</th>
				<th style="width:24%">邮箱</th>
				<th style="width:38%">住址</th>
			</tr>
			<s:iterator value="irpUsers" status="irpInformTypeliststatus">
				<tr>
                    <td>
                        <a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="userid" /></s:url>" target="_blank" title="进去看看">
							<s:property value="truename" />
						</a>
				    </td>
					<td>
                        <s:if test="sex==2">女</s:if>
						<s:else>男</s:else>
					</td>
					<td><s:property value="mobile" /></td>
					<td><s:property value="email" /></td>
					<td>
                        <s:if test="location.length()>13">
							<s:property value="location.substring(0,13)" />...
						</s:if>
						<s:else>
							<s:property value="location" />
						</s:else>
					</td>
				</tr>
			</s:iterator>
		</table>
	</div>
	<div style="clear:both"></div>
	<div class="main" style="">
		<div class="left" style="width: 96.5%;">
			<div class="fyh" style="width: 100%;">
				<ul style="width: 100%;">
					<s:property value="pageHTML" escapeHtml="false" />
				</ul>
			</div>
		</div>
	</div>
</s:if>
<s:else>
	<div>暂时没有用户</div>
</s:else>
