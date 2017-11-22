<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
 
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>

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
	            data:{
	            	  'irpProject.projectid':projectid,
	            	  'irpProject.isclosed':_isClosed
	            },
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
                            <table style="float:left;">
                                <tr>
                                    <td>
                                        <a target="_blank"  class="name_twl" href="<%=rootPath %>project/foruminfo.action?projectId=<s:property value='projectid'/>"> <s:property value="prname"/></a>
                                    </td>
                                </tr>
                            	<tr>
                            	   <td>
                            	       <span style="float:left; font-size:14px;"><s:property value="prdesc"/> </span>
                            	   </td>
                            	</tr>
                            </table>
                        </div>
                         
                        <div style="margin-left:500px;">
                            <table style="float:left;">
                                <tr>
                                    <td>主题:</td>
                                    <td>
                                        <a target="_blank"   class="name_twl" href="<%=rootPath %>project/foruminfo.action?projectId=<s:property value='projectid'/>"><s:property value="countTheme(projectid)"/></a>
                                    </td>
                                </tr>
                            	<tr>
                            	   <td>成员:</td>
                            	   <td>
                            	       <span style="float:left; font-size:14px;"><s:property value="prdesc"/> </span>
                            	   </td>
                            	</tr>
                            </table>
                        </div> 
                        <span class="btn_twl">
                            <s:if test="loginUserid==offpersonid"> 
                                <a id="deleteprojecta<s:property value='projectid'/>" onclick="deleteproject(<s:property value='projectid'/>)" class="redborderbtn deletebtn deletework" href="javascript:void(0);">
                                    <b><i>删除</i></b>
                                </a>
                                <a href="javascript:void(0);" class="redborderbtn completebtn startwork">
                                    <b><i id="startprojecti<s:property value='projectid'/>" onclick="toupdateproject(<s:property value='projectid'/>)" >
	                                	 修改
	                                </i></b>
	                            </a>
                                <s:if test="isclosed==@com.tfs.irp.project.entity.IrpProject@NOT_CLOSED">
                               	    <a id="closeprojecta<s:property value='projectid'/>" href="javascript:void(0);" class="redborderbtn completebtn closework">
                                        <b>
                               	            <i id="stopprojecti<s:property value='projectid'/>" onclick="addProjectPerson(<s:property value='projectid'/>,<s:property value='offpersonid'/>,<s:property value='loginUserid'/>)" >
                               	                                             添加成员
                               	            </i>
                               	        </b>
                               	    </a>
                                </s:if>
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