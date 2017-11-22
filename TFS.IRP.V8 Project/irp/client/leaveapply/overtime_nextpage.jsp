<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	    <div style="clear:both;width: 950px;" >
	    	<s:if test="irpLeaveapplyInfos==null">未找到相关信息</s:if>
	    	<s:else>
	    		<table id="signTable" align="center" style="width: 100%;table-layout: fixed;">
	    				<tr bgcolor="#D7DAF0">
	    					<td width="75px;">序号</td>
	    					<td width="200px;">标题</td>
	    					<td width="100px;">开始时间</td>
	    					<td width="100px;">结束时间</td>
	    					<td width="50px;">紧急程度</td>	    					
	    					<td width="50px;">审核进度</td>	    					
	    					<td width="100px;">审核人</td>
	    					<td width="75px;">操作</td>
	    					
	    				</tr>
		    		<s:iterator var="ele" value="irpLeaveapplyInfos" status="status">
		    			<tr
		    				<s:if test="#status.count%2==0">bgcolor="#F6F6F6"</s:if>
		    			>
		    				<td><s:property value="(pageNum-1)*pageSize+#status.count"/></td>
		    				<td><a href="javascript:void(0)" onClick="detailView(<s:property value="#ele.leaveapplyid"/>)"><s:property value="#ele.title"/></a></td>
		    				<td><s:date name="#ele.starttime" format="yyyy-MM-dd HH:mm"/></td>
		    				<td><s:date name="#ele.endtime" format="yyyy-MM-dd HH:mm"/></td>
		    			
		    				<td><s:property value="#ele.emergency" /></td>
		    				
		    				
		    				<td><s:property value="#ele.applystatus" /></td>
		    				<td><s:property value="#ele.checker" /></td>
		    				<td><a onclick="detailView(<s:property value="#ele.leaveapplyid"/>)" href="javascript:void(0)">
<img src="<%=rootPath%>client/images/view.gif" style="border:0px;height:12px;width:12px;  margin-right:5px;" title="查看">
</a>&nbsp;&nbsp;
<s:if test="#ele.applystatus=='未审核'">
<a href="javascript:void(0)"  onclick="deleteLeaveapply(<s:property value="#ele.leaveapplyid"/>)">
<img src="<%=rootPath%>client/images/deleteicon.jpg" style="border:0px;height:12px;width:12px;  margin-right:5px;" title="撤销">
</a>&nbsp;&nbsp;
<a href="javascript:void(0)" onclick="upLeaveapply(<s:property value="#ele.leaveapplyid"/>)">
<img src="<%=rootPath%>client/images/editsubject.png" style="border:0px;height:12px;width:12px;  margin-right:5px;" title="修改">
</a>
</s:if>
</td>
		    				
		    			</tr>
		    		</s:iterator>
		    	</table>
	    	</s:else>
	    </div>
	    <div class="fyh" style="width:980px;"><ul><s:property value="pageHTML" escapeHtml="false"/></ul></div>