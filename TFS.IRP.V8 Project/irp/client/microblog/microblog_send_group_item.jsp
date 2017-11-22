<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>

                                <div onclick="microblogtypeItemPublic('公开')">公开</div>  
							    <div onclick="microblogtypeItemPrivate('私密')">私密</div>  
							    <div style="width:150px;" onmouseover="sendTypeItemGroupItem()">
									 <span>分组</span>  
									 <div style="width:150px;">  
									 <s:if test="irpGroups.size()>0">
										<s:iterator value="irpGroups">
											<div onclick="microblogtypeItemOther('<s:property value="groupname" />')"><s:property value="groupname" /></div>
										</s:iterator>
										</s:if>
										<s:else>
											<div>无分组,<a href="<%=rootPath %>user/user_group.action"  class="linkbh14">创建</a>分组?</div>
										</s:else>  
									 </div>
							    </div>  



 	