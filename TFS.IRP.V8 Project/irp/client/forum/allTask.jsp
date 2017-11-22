<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<!-- 帖子列表页面 -->
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
<form id="pageForm">
    <s:hidden name="pageNum" id="pageNum" /> 
    <s:hidden name="pageSize" id="pageSize" />  
</form>
 
<div class="tasklist" id="taskList_mypricipal"> 
    <div class="item_tl item-2013-04-09 clearfix" > 
        <ul class="list_tali">
            <li class="clearfix">                    
                <div class="main_ltali" >
                    <table width="100%">
                        <tr>
                            <td width="40%" class="fn">主题名称 </td>
                            <td width="15%" class="fn">作者</td>
                            <td width="15%" class="fn">最后回复人</td>
                            <td width="10%" class="fn" style="text-align: center;">回复数</td>
                            <s:set var="index" value="1" /> 
                            <s:iterator value="irpProjectShareTasks"> 
                                <s:if test="longuserid==offpersonid">
                                    <s:if test="#index==1">  
                                        <s:set var="index" value="2" />  
                                        <td class="fn" style="text-align: center;">操作</td>
                                    </s:if> 
                                </s:if>
                            </s:iterator>
                            <%-- <input value="<s:property value='irpProjectShareTask.offpersonid'/>" /> --%>
                        </tr>
                    	<!-- <s:if test="longuserid==irpProjectShareTasks.offpersonid"><td class="fn" style="text-align: center;">操作</td></s:if><s:else></s:else><input value="<s:property value='irpProjectShareTask.offpersonid'/>" /></tr>-->
                        <s:iterator value="irpProjectShareTasks"> 
                            <tr>
                                <td>
                                    <a href="<%=rootPath %>project/findthemekbyid.action?shareTaskId=<s:property value='sharetaskid'/>&projectId=<s:property value='projectId'/>" class="text_nmltali" target="_blank">
                                    <s:property value='sharedesc'/></a>
                                </td>
                                <td>
                                    <s:property value='getShowPageViewNameByUserId(cruserid)'/>
                                </td>
                                <td>最后回复人</td>
                                <td style="text-align: center;">
                                    <s:property value='countReplayShareTheme(sharetaskid)'/>
                                </td>
                                <s:if test="longuserid==offpersonid">
                                    <td style="text-align: center;">
	                                    <A onclick="toupdateDocument(<s:property value='sharetaskid'/>);" href="">修改</A>|
	                                    <A onclick="deleteShareTaskOff(<s:property value='sharetaskid'/>);"  href="javascript:void(0);">删除</A>|
	                                    <A href="#">置顶</A>
                                    </td>
                                </s:if>
                            </tr>
                        </s:iterator>
                    </table>
                    <div class="info_mltali"></div>
                </div>
            </li>   
        </ul>
    </div>
</div>
<s:if test="irpProjectShareTasks!=null && irpProjectShareTasks.size()!=0">
    <div class="main" align="right">
        <div class="left">
            <div class="fyh">
                <ul>
                    <s:property value="pageHTML" escapeHtml="false" />
                </ul>
            </div>
        </div>
    </div>
</s:if>
 