<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<script type="text/javascript">

</script>
<style>
<!--
.list_tali li {
    border-bottom: 2px dashed #dbdbdb;
}

-->
</style>
 <form id="pageForm">
	<s:hidden name="pageNum" id="pageNum" /> 
	<s:hidden name="pageSize" id="pageSize" />  
</form> 
 <div class="tasklist" id="taskList_mypricipal"> 
     <div class="item_tl item-2013-04-09 clearfix" > 
        <ul class="list_tali">
        <s:iterator value="irpProjectShareTasks"> 
                <li class="clearfix">
                        <a class="finish_ltali" style="background: url('<%=rootPath %>client/images/icons.gif') no-repeat 0 -342px;" href="javascript:void(0)" hidefocus="true"></a>
                    <div class="main_ltali">
                        <p class="name_mltali from_imltali" >任务名称：
                            <a href="<%=rootPath %>project/findsharetaskbyid.action?shareTaskId=<s:property value='sharetaskid'/>" class="text_nmltali" target="_blank">
                            	<s:property value='sharedesc'/></a>
                            	 <s:if test="@com.tfs.irp.projectsharetask.entity.IrpProjectShareTask@TASK_PERSON==isvisible">
                                 	<span class="lock_nmltali">仅任务成员可见</span>
                                </s:if>
                              
                          </p>
                          <div class="info_mltali">
                            <s:if test="isclosed==2">
                                 	<span class="">任务状态：已关闭</span>
                                </s:if>
                                 <s:if test="isclosed==1">
                                 	<span class="">任务状态：正在进行中</span>
                                </s:if>
                          </div>
                        <div class="info_mltali">
                                <span class="expire_imltali">
                                <s:if test="endtime==null ||endtime==''"></s:if>
                                <s:else> 
                                		<script type="text/javascript">  
													$(function(){ 
														var msg='';
														var _sharetaskid="<s:property value='sharetaskid'/>";
														var endtime='<s:date name="endtime" format="yyyy-MM-dd"/> ';  
														var endDate =  new  Date(endtime.replace(/-/g,"/"));   
														var todayTime= new Date(<%=new java.util.Date().getTime()%>); 
														var dayTime=window.parseInt( Math.abs((todayTime.getTime() - endDate.getTime())/(1000*60*60*24)))+1;  
															msg+='剩余'+dayTime+'天';  
													 	if(todayTime>endDate){  msg='已过期'; }
													   $('#tishi'+_sharetaskid).html(msg);
													});
											</script>   
                                </s:else>
                                <i id="tishi<s:property value='sharetaskid'/>"></i>
                                <b>  
                               		 <s:date name="starttime" format="yyyy-MM-dd"/>
                               		 —
                               		 <s:date name="endtime" format="yyyy-MM-dd"/>
                                </b></span>
                                <span class="from_imltali">来自项目
                                    <a target="_blank" href="<%=rootPath %>/project/projectinfo.action?projectId=<s:property value='projectid'/>" hidefocus="true">
                                    	<s:property value="@com.tfs.irp.project.web.ProjectAction@findProjectNameByProjectId(projectid)"/>
									</a>
                                </span>  
                                
                        </div>
                          <div class="info_mltali">
                          <span class="from_imltali">项目状态：
                                   <s:if test="@com.tfs.irp.project.web.ProjectAction@findIsClosedByProjectId(projectid)==@com.tfs.irp.project.entity.IrpProject@IS_CLOSED">
                                   		项目已完成
                                   </s:if><s:else>
                                   		项目正在进行
                                   </s:else>
                                </span>   
                          </div>
                    </div>
                </li>   
        </s:iterator>
        </ul>
    </div>
  </div>
  <s:if test="irpProjectShareTasks==null || irpProjectShareTasks.size()==0">
 <p style="font-size: 15px;padding: 5px;">暂时没有您的任务!<p>
  </s:if>
 <div class="main" style="width:auto">
    <div class="left" style="float: right;width:auto">
      <div class="fyh"  >
       <ul>
        <s:property value="pageHTML" escapeHtml="false" />
        </ul>
      </div>
    </div>
     </div>