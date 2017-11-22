<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
</style>
<script>
	//分页
	function page(_nPageNum){
		$('#pageForm').find('input[name="pageNum"]').val(_nPageNum);
		refrechProjectList();
	}
	function startOrCloseProject(projectid,_isClosed){ 
		 var textval=$('#stopprojecti'+projectid).html(); 
		 var msgshow='';  
		 if($.trim(textval)=='结束圈子')  msgshow='确定该圈子已完成？'; 
		 else  msgshow='确认要重启该圈子？';  
		  $.dialog.confirm(msgshow,function(){
				$.ajax({
						url: '<%=rootPath%>project/startorcloseproject.action',
						data:{'irpProject.projectid':projectid,
							  'irpProject.isclosed':_isClosed},
						type:"post",
						dataType: "json", 
					    async: false ,
					    success:function(msg){ 
					    	if(msg=="1"){
					    	   window.location.reload(true);
					    	} 
					    }
					}); 
			}); 
	}
</script> 
 <form id="pageForm">
	<s:hidden name="pageNum" id="pageNum" /> 
	<s:hidden name="pageSize" id="pageSize" />  
</form>
<script type="text/javascript">
    window.G_WorkGuideInfo = {
        "GetCreateWorkUrl": '/Work/200149886/Work/CreateWorkUrl' 
    }
</script>
        <s:iterator value="irpProjects">
        	<ul class="listQz01">
            	<li>
                	<section class="picQz">
                    	 <s:if test="projectfile!=null && projectfile!=''">
                            	 <img class="img_twl" src="<%=rootPath %>file/readfile.action?fileName=<s:property value='@com.tfs.irp.util.ThumbnailPic@searchFileName(projectfile,"_150X150")'/>" />
                            </s:if>
                            <s:else> 
                             <img  class="img_twl" src="<%=rootPath %>client/images/rl.jpg"></img>
                            </s:else>
                    </section>
                    <section class="infoQz">
                    	<div class="lineUp">
                        	<div class="editBar">
                                <s:if test="loginUserid==offpersonid"> 
                           		<a id="deleteprojecta<s:property value='projectid'/>" onclick="deleteproject(<s:property value='projectid'/>)"  href="javascript:void(0);"><b>删除圈子</b></a>
                                <s:if test="isclosed==@com.tfs.irp.project.entity.IrpProject@NOT_CLOSED">
                               	   <a  style="padding: 2px 0px;" id="closeprojecta<s:property value='projectid'/>" href="javascript:void(0);" >
                               	   <b id="stopprojecti<s:property value='projectid'/>" onclick="startOrCloseProject(<s:property value='projectid'/>,<s:property value='@com.tfs.irp.project.entity.IrpProject@IS_CLOSED'/>)" >结束圈子</b></a>
                                </s:if>
                                <s:else>
                                	<a  style="padding: 2px 0px;" id="closeprojecta<s:property value='projectid'/>"  href="javascript:void(0);" >
                                	<b id="startprojecti<s:property value='projectid'/>" onclick="startOrCloseProject(<s:property value='projectid'/>,<s:property value='@com.tfs.irp.project.entity.IrpProject@NOT_CLOSED'/>)" >重启圈子</b></a>
                                </s:else> 
                          </s:if>
                               <%--  <span  style="padding: 3px 0px;">结束圈子</span> --%>
                                <script type="text/javascript">
                          		$(function(){
                          			var _personState="<s:property value='@com.tfs.irp.projectperson.web.IrpProjectPersonAction@findIsStateByProjectIds(projectid)'/>";
                          			var _attentionState="<s:property value='@com.tfs.irp.projectperson.web.IrpProjectPersonAction@findNotStateByProjectIds(projectid)'/>";
                          			var _irpProjectPersonStatePerson="<s:property value='@com.tfs.irp.projectperson.entity.IrpProjectPerson@IS_STATE'/>";
                          			var _irpProjectPersonStateAttention="<s:property value='@com.tfs.irp.projectperson.entity.IrpProjectPerson@NOT_STATE'/>";
                          			var _projectId="<s:property value='projectid'/>"; 
                          			var _offpersonid="<s:property value='offpersonid'/>"; 
                          			var _loginUserid="<s:property value='loginUserid'/>";  
                          			if(_personState==_irpProjectPersonStatePerson && _offpersonid!=_loginUserid){//说明是成员
                          				var msg='<b >正在参与</b>';
                          				$('#person'+_projectId).addClass('ends');
                          				$('#person'+_projectId).html(msg);
                          				$('#deleteprojecta'+_projectId).hide();
                          				$('#closeprojecta'+_projectId).hide();
                          			}else if(_attentionState==_irpProjectPersonStateAttention && _personState!=_irpProjectPersonStatePerson){//他不是成员，并且然后是关注者
                          				var msg='<b >正在关注</b>';
                          				$('#attention'+_projectId).addClass('ends');
                          				$('#attention'+_projectId).html(msg);
                          				$('#deleteprojecta'+_projectId).hide();
                          				$('#closeprojecta'+_projectId).hide();
                          			}else if(_attentionState!=_irpProjectPersonStateAttention && _personState!=_irpProjectPersonStatePerson && _offpersonid!=_loginUserid){//他不是成员，并且不是关注者也不是负责人
                          				var _static_isPub="<s:property value='@com.tfs.irp.project.entity.IrpProject@NOT_PUBLISH'/>";
                          			    var projectIsPub= "<s:property value='ispublish'/>"; 
                          				if(projectIsPub==_static_isPub){//圈子是私密圈子
                          					var msg='<b >私密圈子</b>';
                          					$('#simi'+_projectId).addClass('ends');
	                          				$('#simi'+_projectId).html(msg);
	                          				$('#deleteprojecta'+_projectId).hide();
	                          				$('#closeprojecta'+_projectId).hide();	
                          				}else{ 
	                          				var msg='<b >关注圈子</b>';
	                          				$('#attention'+_projectId).addClass('ends');
	                          				$('#attention'+_projectId).html(msg);
	                          				$('#deleteprojecta'+_projectId).hide();
	                          				$('#closeprojecta'+_projectId).hide();	
                          				}
                          			}
                          		});
                          </script> 
                           <a  style="padding: 2px 0px;"  onclick="deletePerson(<s:property value='projectid'/>)" onmouseover="changestateValueOne(<s:property value='projectid'/>)" onmouseout="changestateValueTwo(<s:property value='projectid'/>)" href="javascript:void(0);"  id="person<s:property value='projectid'/>"></a>
                              <a  style="padding: 2px 0px;" onmouseout="changeTextTwo(<s:property value='projectid'/>)"  onmouseover="changeTextOne(<s:property value='projectid'/>)" id="attention<s:property value='projectid'/>" onclick="addOrDeleteAttaction(<s:property value='projectid'/>)"  href="javascript:void(0);" ></a>
                              <a  style="padding: 2px 0px;" id="simi<s:property value='projectid'/>" href="javascript:void(0);" ></a>
                            </div>
                            <h1>  <a target="_blank"  href="<%=rootPath %>project/projectinfo.action?projectId=<s:property value='projectid'/>"> <s:property value="prname"/>  </a></h1>
                        </div>
                        <div class="lineDown" >
                        	<div style="float: right;display: inline;" id="dayLable<s:property value='projectid'/>"></div>
                                       &nbsp;&nbsp; 
                                        	<script type="text/javascript">  
													$(function(){ 
														var msg=' <div class="ending"><span>剩余<font> ' ;
														var _projectId='<s:property value="projectid"/>';  
														var starttime='<s:date name="starttime" format="yyyy-MM-dd"/>';
														var endtime='<s:date name="endtime" format="yyyy-MM-dd"/> '; 
														var startDate =  new  Date(starttime.replace(/-/g,"/"));
														var endDate =  new  Date(endtime.replace(/-/g,"/"));   
														var todayTime=new Date(<%=new java.util.Date().getTime()%>); 
														var dayTime=window.parseInt( Math.abs((todayTime.getTime() - endDate.getTime())/(1000*60*60*24)))+1;  
															msg+=dayTime+'</font>天</span>'; 
															msg+='</div>';
													 	if(todayTime>=endDate){  msg='  <div class="ended"><span>圈子已到期，请尽快处理</span></div>'; }
														
													   $('#dayLable'+_projectId).html(msg);
													});
											</script>   
                            <div class="info">
                            	<section>
                                	<p> <s:property value="@com.tfs.irp.projectsharetask.web.IrpProjectShareTaskAction@countShareTaskCollectByShareTaskId(projectid)"/> </p>
                                    <span>动态</span>
                                </section>
                                <section>
                                	<p><s:property value="@com.tfs.irp.projectsharetask.web.IrpProjectShareTaskAction@countTaskCollectByShareTaskId(projectid)"/></p>
                                    <span>任务</span>
                                </section>
                                <section>
                                	<p><s:property value="@com.tfs.irp.projectperson.web.IrpProjectPersonAction@findProjectPersonCount(projectid)"/></p>
                                    <span>成员</span>
                                </section>
                            </div>
                        </div>
                    </section>
                </li>
            </ul>
           </s:iterator>
<s:if test="irpProjects==null || irpProjects.size()==0">
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