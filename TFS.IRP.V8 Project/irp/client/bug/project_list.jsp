<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
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
<div> 
    <div id="workList">
        <ul class="worklist" id="workListUl">
        	<s:iterator value="irpProjects">
        		 <li class="li_wl">
                    <div class="title_wl clearfix">
                        <div class="workname_twl">
                            <s:if test="projectfile!=null && projectfile!=''">
                            	 <img class="img_twl" src="<%=rootPath %>file/readfile.action?fileName=<s:property value='@com.tfs.irp.util.ThumbnailPic@searchFileName(projectfile,"_150X150")'/>" />
                            </s:if>
                            <s:else> 
                             <img  class="img_twl" src="<%=rootPath %>client/images/rl.jpg"></img>
                            </s:else>
                            <a target="_blank"  class="name_twl" href="<%=rootPath %>bug/projectinfo.action?projectId=<s:property value='projectid'/>"> <s:property value="prname"/>  </a>
                        </div>  
                         <span class="btn_twl">
                           <s:if test="loginUserid==offpersonid"> 
                           		<a id="deleteprojecta<s:property value='projectid'/>" onclick="deleteproject(<s:property value='projectid'/>)" class="redborderbtn deletebtn deletework" href="javascript:void(0);"><b><i>删除</i></b></a>
                                <s:if test="isclosed==@com.tfs.irp.project.entity.IrpProject@NOT_CLOSED">
                               	   <a id="closeprojecta<s:property value='projectid'/>" href="javascript:void(0);" class="redborderbtn completebtn closework"><b>
                               	   <i id="stopprojecti<s:property value='projectid'/>" onclick="startOrCloseProject(<s:property value='projectid'/>,<s:property value='@com.tfs.irp.project.entity.IrpProject@IS_CLOSED'/>)" >结束项目</i></b></a>
                                </s:if>
                                <s:else>
                                	<a id="closeprojecta<s:property value='projectid'/>"  href="javascript:void(0);" class="redborderbtn completebtn startwork"><b>
                                	<i id="startprojecti<s:property value='projectid'/>" onclick="startOrCloseProject(<s:property value='projectid'/>,<s:property value='@com.tfs.irp.project.entity.IrpProject@NOT_CLOSED'/>)" >重启项目</i></b></a>
                                </s:else> 
                          </s:if>
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
                          				var msg='<b><i>正在参与</i></b>';
                          				$('#person'+_projectId).addClass('redborderbtn');
                          				$('#person'+_projectId).html(msg);
                          				$('#deleteprojecta'+_projectId).hide();
                          				$('#closeprojecta'+_projectId).hide();
                          			}else if(_attentionState==_irpProjectPersonStateAttention && _personState!=_irpProjectPersonStatePerson){//他不是成员，并且然后是关注者
                          				var msg='<b><i>正在关注</i></b>';
                          				$('#attention'+_projectId).addClass('redborderbtn');
                          				$('#attention'+_projectId).html(msg);
                          				$('#deleteprojecta'+_projectId).hide();
                          				$('#closeprojecta'+_projectId).hide();
                          			}else if(_attentionState!=_irpProjectPersonStateAttention && _personState!=_irpProjectPersonStatePerson && _offpersonid!=_loginUserid){//他不是成员，并且不是关注者也不是负责人
                          				var _static_isPub="<s:property value='@com.tfs.irp.project.entity.IrpProject@NOT_PUBLISH'/>";
                          			    var projectIsPub= "<s:property value='ispublish'/>"; 
                          				if(projectIsPub==_static_isPub){//圈子是私密圈子
                          					var msg='<b><i>私密圈子</i></b>';
                          					$('#simi'+_projectId).addClass('redborderbtn');
	                          				$('#simi'+_projectId).html(msg);
	                          				$('#deleteprojecta'+_projectId).hide();
	                          				$('#closeprojecta'+_projectId).hide();	
                          				}else{ 
	                          				var msg='<b><i>关注圈子</i></b>';
	                          				$('#attention'+_projectId).addClass('redborderbtn');
	                          				$('#attention'+_projectId).html(msg);
	                          				$('#deleteprojecta'+_projectId).hide();
	                          				$('#closeprojecta'+_projectId).hide();	
                          				}
                          			}
                          		});
                          </script> 
                      		  <a  onclick="deletePerson(<s:property value='projectid'/>)" onmouseover="changestateValueOne(<s:property value='projectid'/>)" onmouseout="changestateValueTwo(<s:property value='projectid'/>)" href="javascript:void(0);"  id="person<s:property value='projectid'/>"class=" completebtn closework"></a>
                              <a onmouseout="changeTextTwo(<s:property value='projectid'/>)"  onmouseover="changeTextOne(<s:property value='projectid'/>)" id="attention<s:property value='projectid'/>" onclick="addOrDeleteAttaction(<s:property value='projectid'/>)"  href="javascript:void(0);" class="completebtn closework"></a>
                              <a id="simi<s:property value='projectid'/>" href="javascript:void(0);" class="completebtn closework"></a>
                        </span>
                    </div>
                    <table class="workinfo_wl " cellpadding="0" cellspacing="0">
                        <tr>
                            <td id="dayLable<s:property value='projectid'/>" align="left" style="padding-left: 20px;" >
                                       &nbsp;&nbsp; 
                                        	<script type="text/javascript">  
													$(function(){ 
														var msg=' <span class="remaind_rwwl">剩余</span> <b class="remaindnum_rwwl"> ' ;
														var _projectId='<s:property value="projectid"/>';  
														var starttime='<s:date name="starttime" format="yyyy-MM-dd"/>';
														var endtime='<s:date name="endtime" format="yyyy-MM-dd"/> '; 
														var startDate =  new  Date(starttime.replace(/-/g,"/"));
														var endDate =  new  Date(endtime.replace(/-/g,"/"));   
														var todayTime=new Date(<%=new java.util.Date().getTime()%>); 
														var dayTime=window.parseInt( Math.abs((todayTime.getTime() - endDate.getTime())/(1000*60*60*24)))+1;  
															msg+=dayTime+'<span class="remaind_rwwl">天</span>'; 
															msg+=' </b>';
													 	if(todayTime>=endDate){  msg='  <div class="remainder_wwl "><b class="remaindnum_rwwl">快处理吧，已到期<b></div>'; }
														
													   $('#dayLable'+_projectId).html(msg);
													});
											</script>   
                            </td>
                            <td class="feednum_wwl">
                                <a>
                                <s:property value="@com.tfs.irp.projectsharetask.web.IrpProjectShareTaskAction@countShareTaskCollectByShareTaskId(projectid)"/> 
                                 <br /><b>动态</b></a>
                            </td>
                            <td class="feednum_wwl ">
                                <a>
                                <s:property value="@com.tfs.irp.bug.web.BugAction@bugCountForProject(projectid)"/>
                                <br /><b>任务</b></a>
                            </td>
                            <td class="feednum_wwl">
                                <a>
                                <s:property value="@com.tfs.irp.projectperson.web.IrpProjectPersonAction@findProjectPersonCount(projectid)"/>
                                <br /><b>成员</b></a>
                            </td>
                        </tr>
                    </table>
                </li>
        	</s:iterator>  
        </ul>
    </div> 
    <s:if test="irpProjects!=null && irpProjects.size()!=0">
	     <div class="main1" align="right">
			    <div class="left">
				      <div class="fyh">
				       	<ul><s:property value="pageHTML" escapeHtml="false" /></ul>
				      </div>
			    </div>
		</div>
    </s:if>
 </div> 