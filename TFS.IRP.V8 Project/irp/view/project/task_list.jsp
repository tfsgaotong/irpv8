<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.tfs.irp.util.LoginUtil"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<style>

a b{
    border-radius: 3px;
    border: none;
    background: #5f9ddd;
    overflow: hidden;
    font-size: 15px;
    color: #fff;
   padding: 5px 10px;
	margin-left: 10px;
    

}

 .editBar {
  float: right;
display: inline;
height: auto;
line-height: 20px;
    
}

.info1{
float: left;
width: 590px;
}


.info1 section {
    float: left;
    display: inline;
    padding-top: 10px;
    text-align: center;
    margin: 0 5px;}
    
    
.title{
font-size:14px;
}
</style>
 <form id="pageForm">
	<s:hidden name="pageNum" id="pageNum" /> 
	<s:hidden name="pageSize" id="pageSize" />  
</form>
<script type="text/javascript">
    window.G_WorkGuideInfo = {
        "GetCreateWorkUrl": '/Work/200149886/Work/CreateWorkUrl' 
    }
</script>
        <s:iterator value="irpProjectShareTasks">
        	<ul class="listQz01">
            	<li>
                	<section class="picQz">
                	<s:set var="project" value="@com.tfs.irp.project.web.ProjectAction@findProjectByProjectId(projectid)"></s:set>
                    	 <s:if test="#project.projectfile!=null && #project.projectfile!=''">
                            	 <img class="img_twl" src="<%=rootPath %>file/readfile.action?fileName=<s:property value='@com.tfs.irp.util.ThumbnailPic@searchFileName(#project.projectfile,"_150X150")'/>" />
                            </s:if>
                            <s:else> 
                             <img  class="img_twl" src="<%=rootPath %>client/images/rl.jpg"></img>
                            </s:else>
                    </section>
                    <section class="infoQz">
                    	<div class="lineUp">
                        	<div class="editBar">
                        			<s:if test="@com.tfs.irp.project.web.ProjectAction@findIsClosedByProjectId(projectid)==@com.tfs.irp.project.entity.IrpProject@IS_CLOSED">
	                                   	<a href="<%=rootPath %>project/findsharetaskbyid.action?shareTaskId=<s:property value='sharetaskid'/>" target="_blank"><b>查看任务</b></a>
	                                   </s:if>
	                                   <s:else>
	                                   		<s:if test="isclosed==2">
	                                   				<s:if test="@com.tfs.irp.util.LoginUtil@getLoginUserId()==offpersonid"> 
		                                 			<a href="<%=rootPath %>project/findsharetaskbyid.action?shareTaskId=<s:property value='sharetaskid'/>" target="_blank"><b>编辑任务</b></a>
				                                	</s:if>
				                                	<s:else>
				                                	<a href="<%=rootPath %>project/findsharetaskbyid.action?shareTaskId=<s:property value='sharetaskid'/>" target="_blank"><b>查看任务</b></a>
				                                
				                                	</s:else>
				                                </s:if>
				                                 <s:if test="isclosed==1">
				                                 	<s:if test="@com.tfs.irp.util.LoginUtil@getLoginUserId()==offpersonid"> 
		                                 			<a href="<%=rootPath %>project/findsharetaskbyid.action?shareTaskId=<s:property value='sharetaskid'/>" target="_blank"><b>编辑任务</b></a>
				                                	</s:if>
				                                	<s:else>
				                                	<a href="<%=rootPath %>project/findsharetaskbyid.action?shareTaskId=<s:property value='sharetaskid'/>" target="_blank"><b>回复任务</b></a>
				                                
				                                	</s:else>
				                                </s:if>
	                                   </s:else>
                        	
                            </div>
                            <h1>  <a href="<%=rootPath %>project/findsharetaskbyid.action?shareTaskId=<s:property value='sharetaskid'/>" class="text_nmltali" target="_blank"> <s:property value='sharedesc'/>  </a>
	                            <s:if test="@com.tfs.irp.projectsharetask.entity.IrpProjectShareTask@TASK_PERSON==isvisible">
	                                 	<span class="lock_nmltali">仅任务成员可见</span>
	                                </s:if>
                            
                            </h1>
                        </div>
                        <div class="lineDown" >
                        
                        	
                                <div>
	                                <div class="info1">
	                                <section>
		                                <span class="title">所属项目:
		                                    <a target="_blank" href="<%=rootPath %>/project/projectinfo.action?projectId=<s:property value='projectid'/>" hidefocus="true">
		                                    	<s:property value="@com.tfs.irp.project.web.ProjectAction@findProjectNameByProjectId(projectid)"/>
											</a>
		                                </span>
	                                 </section>
	                                </div>
	                                <div style="float: right;display: inline;">
	                              	<div class="ending"><span>  
	                               		 <s:date name="starttime" format="yyyy-MM-dd"/>
	                               		 —
	                               		 <s:date name="endtime" format="yyyy-MM-dd"/>
	                                </span></div>
	                               
	                                </div>
                                </div>
                                <div>
                            <div class="info1">
                            	<section>
                                	
                                    <span>
								 <s:if test="isclosed==2">
                                 		<span class="title">任务状态：已关闭</span>
		                                </s:if>
		                                 <s:if test="isclosed==1">
		                                 	<span class="title">任务状态：正在进行中</span>
		                                </s:if>
								</span>
                                </section>
                                <section>
                                    <span>
	                                    <span class="title">项目状态：
	                                   <s:if test="@com.tfs.irp.project.web.ProjectAction@findIsClosedByProjectId(projectid)==@com.tfs.irp.project.entity.IrpProject@IS_CLOSED">
	                                   		项目已完成
	                                   </s:if><s:else>
	                                   		项目正在进行
	                                   </s:else>
	                                </span>
                                </span>
                                </section>
                            </div>
                            <div style="float: right;display: inline;line-height: 140%" id="tishi<s:property value='sharetaskid'/>"></div>
                                  <s:if test="endtime==null ||endtime==''"></s:if>
                                <s:else> 
                                		<script type="text/javascript">  
													$(function(){ 
														var msg='<div class="ending"><span>剩余<font>';
														var _sharetaskid="<s:property value='sharetaskid'/>";
														var endtime='<s:date name="endtime" format="yyyy-MM-dd"/> ';  
														var endDate =  new  Date(endtime.replace(/-/g,"/"));   
														var todayTime= new Date(<%=new java.util.Date().getTime()%>); 
														var dayTime=window.parseInt( Math.abs((todayTime.getTime() - endDate.getTime())/(1000*60*60*24)))+1;  
															msg+=dayTime+'</font>天</span>';  
															msg+='</div>';
													 	if(todayTime>endDate){ msg='  <div class="ended"><span>任务已到期，请尽快处理</span></div>';  }
													   $('#tishi'+_sharetaskid).html(msg);
													});
											</script>   
                                </s:else>
                             </div>
                            
                            
                        </div>
                    </section>
                </li>
            </ul>
           </s:iterator>
<s:if test="irpProjectShareTasks==null || irpProjectShareTasks.size()==0">
	<p style="font-size: 15px;padding: 5px;">暂时没有您的圈子!<p>
  </s:if>
 <div class="main" align="right">
    <div class="left">
      <div class="fyh" style="width:900px;" >
       <ul>
        <s:property value="pageHTML" escapeHtml="false" />
        </ul>
      </div>
    </div>
     </div>