<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()	+ request.getContextPath() + "/";
%>
<form id="pageForm">
	<s:hidden name="categoryId" id="categoryId" />
	<s:hidden name="pageNum" id="pageNum" />
	<s:hidden name="pageSize" id="pageSize" />
</form>
<ul class="cardList">
<s:if test="expertList.size()>0">
	<s:iterator value="expertList">
		<li>
			<table width="270" border="0" align="center" cellpadding="10" cellspacing="0" style="BORDER: 1px solid #C1DDF2;height: 200px;">
				<tr>
					<td rowspan="2" width="67px" style="padding-top: 5px; padding-left: 5px;">
						<a href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value="userid"/>">
						<s:if test="userpic!=null">
							<img style="width:67px" src="<%=rootPath %>file/readfile.action?fileName=<s:property value='userpic' />" alt="用户图片" />
						</s:if>
						<s:else>
							<s:if test="sex==2">
							<img src="<%=rootPath %>client/images/female.jpg" alt="用户图片" width="67px" />
							</s:if>
							<s:else>
							<img src="<%=rootPath %>client/images/male.jpg" alt="用户图片" width="67px" />
							</s:else>
						</s:else>		
						</a>
					</td>
					<td title="<s:property value="location.trim()" />" valign="top" style="padding-left: 5px;height: 40px;" >
					<font class="linkc12" >
					基本信息:
					</font>
					<s:property value="getShowPageViewNameByUserName(username)"/><br/>
					 <s:if test="sex==1">男</s:if>
				 	<s:elseif test="sex==2">女</s:elseif>
				 	<br/>
					<s:if test="location!=null && location.trim().length()>14">
						<s:property value="location.trim().substring(0,14)+'......'" />
					</s:if>
					<s:elseif test="location!=null && location.trim().length()>0">
						<s:property value="location.trim()"/>
					</s:elseif>
					<s:elseif test="location==null || location.trim()==''"><br/></s:elseif>
					</td>
				</tr>
				<tr>
					<td valign="bottom" style="padding-left: 5px;height: 10px;">
					<font class="linkc12" >
					简介：
					</font>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="padding:0px 10px 10px;height:10px;" title="<s:property value="expertintro.trim()"/>">
						<s:if test="expertintro!=null && expertintro.trim().length()>20">
							<s:property value="expertintro.trim().substring(0,19)+'......'"/>
						</s:if>
						<s:else>
							<s:if test="expertintro==null || expertintro.trim()==''">暂无简介</s:if>
							<s:else>
								<s:property value="expertintro.trim()"/>
							</s:else>
						</s:else>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="margin:10px;padding:10px;margin-top:0px;height: 10px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value="userid"/>"><font class="linkc12" >关注</font></a>：<s:property value="findFocusByUserid(userid)"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value="userid"/>"><font class="linkc12" >粉丝</font></a>：<s:property value="findFocusMeByUserid(userid)"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value="userid"/>"><font class="linkc12" >微知</font></a>：<s:property value="findMicroblogById(userid)"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" bgcolor="#C1DDF2" height="30" align="center">
						<span class="STYLE1"> <a href="javascript:void(0)"
							onclick="askExpert(<s:property value="userid"/>,'<s:property value="username"/>')">+提问+</a>
					</span></td>
				</tr>
			</table>
			<div class="darkSh"></div>
		</li>
	</s:iterator>
	</s:if>
	<div style="clear:both"></div>
</ul>
<!--分页开始 -->
<s:if test="expertList.size()>0">
<div bgcolor="#FFFFFF">
	<div colspan="9" align="right" class="clientpage">
		<ul>
			<s:property value="pageHTML" escapeHtml="false" />
		</ul>
	</div>
</div>
</s:if>
<s:else>
 暂无专家
</s:else>
<!--分页结束 -->
