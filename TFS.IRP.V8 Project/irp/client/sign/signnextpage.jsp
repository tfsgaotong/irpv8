<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	    <div style="clear:both;width: 950px;" >
	    	<s:if test="signList==null">未找到相关信息</s:if>
	    	<s:else>
	    		<table id="signTable" align="center" style="width: 100%;table-layout: fixed;">
	    				<tr bgcolor="#D7DAF0">
	    					<td width="50px;">序号</td>
	    					<td width="85px;">姓名</td>
	    					<td width="110px;">签到时间</td>
	    					<td width="100px;">签到IP地址</td>
	    					<td width="110px;">签退时间</td>
	    					<td width="100px;">签退IP地址</td>
	    					<td width="75px;">签到/签退</td>
	    					<td width="75px;">去向</td>
	    					<td>备注</td>
	    					<td width="45px;">操作</td>
	    				</tr>
		    		<s:iterator var="ele" value="signList" status="status">
		    			<tr
		    				<s:if test="#status.count%2==0">bgcolor="#F6F6F6"</s:if>
		    			>
		    				<td><s:property value="(pageNum-1)*pageSize+#status.count"/></td>
		    				<td style="text-align: left;" title="<s:property value="nameMap.get(#ele.signid)" />" class="namecomm">
		    					<s:property value="nameMap.get(#ele.signid)" />
		    					<input type="hidden" value="${ele.signid }" name="usrId"/>
		    				</td>
		    				<td><s:date name="#ele.signintime" format="yyyy-MM-dd HH:mm"/></td>
		    				<td><s:property value="#ele.signinip" /></td>
		    				<td>
		    					<s:if test="#ele.signouttime==null">未签退</s:if>
		    					<s:else><s:date name="#ele.signouttime" format="yyyy-MM-dd HH:mm"/></s:else>
		    				</td>
		    				<td><s:property value="#ele.signoutip" default="未签退" /></td>
		    				<td>
		    					<s:property value="#ele.signinstatus"/>
		    					<s:if test="#ele.signoutstatus!=null">/<s:property value="#ele.signoutstatus" /></s:if>
		    				</td>
		    				<td style="text-align: left;" title="<s:property value="#ele.signindirection"/>" class="comm"><s:property value="#ele.signindirection"/></td>
		    				<s:if test="#ele.signcomment!=null && #ele.signcomment!=''">
		    					<td style="text-align: left;" title="<s:property value="#ele.signcomment"/>" class="comm"><s:property value="#ele.signcomment" /></td>
		    				</s:if>
		    				<s:else><td></td></s:else>
		    				<td>
		    					<s:if test="notSignOutRecord.get(#ele.id)!=null">
		    						<a href="javascript:void(0)" ><img onclick="signagain(<s:property value='#ele.id' />)" alt="补签" title="补签" src="<%=rootPath %>client/images/signagain.JPG" /></a>
		    					</s:if>
		    				</td>
		    			</tr>
		    		</s:iterator>
		    	</table>
	    	</s:else>
	    </div>
	    <div class="fyh" style="width:950px;"><ul><s:property value="pageHTML" escapeHtml="false"/></ul></div>