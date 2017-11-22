<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> 

	

  <script type="text/javascript">
 /** 首先进入，   */
	 $(function(){
		 	//alert();
		/*     allprojectLeft(); *///初始化左侧菜单
	 		//alert('左侧菜单加载完毕');
		   initWindowOpen();//初始化弹框，这样隐私圈子，被邀请关注的人先关注才可以看这个圈子的
	 		//showXiangguanDocumentToProject();//相关知识
	 		 tabs(); 	
	 		 $('#addTaskDiv').hide(); 
	 		 initProjectTime();// 初始化圈子剩余时间
	 		 loadUploadPic();//初始化上传插件
	 		 loadUploadPicTask();//初始化任务上传c
	 		$('#TaskInput').val('要做什么事？');
	 		 $('#taskDescTextArea').val('更详细的说明');
	 		 $('#inputcounttask').val(0); 
	 		 $('#sharedesctextarea').val('你在忙什么？');
	 		 $('#projectStatus').combotree({
					url: '<%=rootPath%>site/findprojecttree.action',
					width: '200px' 
			});
	 });
</script>
<body > 



<!--左侧内容结束--> 
<!--右侧内容-->

		<div class="myQz">
            <div class="myQzTitle">
                    <div class="editBar">
                     	 <%--如果他是负责人 --%>
                             <s:if test="loginUserid==irpProject.offpersonid">
                              <a class="edit"  href="javascript:void(0);" 
                              	 id="startprojecti<s:property value='irpProject.projectid'/>" onclick="toupdateproject(<s:property value='irpProject.projectid'/>)" >
                              	</a>
                        	<a class="del" id="deleteprojecta<s:property value='projectid'/>" onclick="delteProject(<s:property value='irpProject.projectid'/>)" href="javascript:void(0);"></a> 
                              <s:if test="irpProject.isclosed==@com.tfs.irp.project.entity.IrpProject@NOT_CLOSED">
                             	   <a class="end" style="padding: 2px 0px;margin-top: -22px;margin-left: 60px;" href="javascript:void(0);" ><b 
                             	    id="stopprojecti<s:property value='irpProject.projectid'/>" onclick="startOrCloseProject(<s:property value='irpProject.projectid'/>,<s:property value='@com.tfs.irp.project.entity.IrpProject@IS_CLOSED'/>)" >
                             	   结束圈子</b></a>
                              </s:if>
                              <s:else>
                              	<a class="end" style="padding: 2px 0px;margin-top: -22px;margin-left: 60px;" href="javascript:void(0);"><b
                              	 id="startprojecti<s:property value='irpProject.projectid'/>" onclick="startOrCloseProject(<s:property value='irpProject.projectid'/>,<s:property value='@com.tfs.irp.project.entity.IrpProject@NOT_CLOSED'/>)" >
                              	重启圈子</b></a>
                              </s:else>
                             
                         </s:if> 
                         <%--如果他是成员 --%>
                         <s:elseif test="@com.tfs.irp.projectperson.entity.IrpProjectPerson@IS_STATE==@com.tfs.irp.projectperson.web.IrpProjectPersonAction@findIsStateByProjectIds(irpProject.projectid)">
                      				<a class="end" style="padding: 2px 0px;" onclick="deletePersonMe( <s:property value='irpProject.projectid'/>)" 
                         				id="person <s:property value='irpProject.projectid'/>" 
                         				onmouseover="changestateValueOne( <s:property value='irpProject.projectid'/>)" 
                         				onmouseout="changestateValueTwo( <s:property value='irpProject.projectid'/>)"  
                         				href="javascript:void(0);" ><b>
                             	   正在参与</b></a></s:elseif>
                         <%--如果他是关注者 --%>
                         <s:elseif test="@com.tfs.irp.projectperson.entity.IrpProjectPerson@NOT_STATUS==@com.tfs.irp.projectperson.web.IrpProjectPersonAction@findNotStateByProjectIds(irpProject.projectid)">
                         			<a class="end" style="padding: 2px 0px;" id="attention<s:property value='irpProject.projectid'/>"  
                           		  onmouseout="changeTextTwo( <s:property value='irpProject.projectid'/>)" 
                           		  onmouseover="changeTextOne( <s:property value='irpProject.projectid'/>)" 
                           		  onclick="addOrDeleteAttactionMe( <s:property value='irpProject.projectid'/>)"  
                           		  href="javascript:void(0);" ><b>
                           		 正在关注</b></a> 
                         
                         </s:elseif>
                         <%--不是关注者，也不是成员，圈子公开情况 --%>
                         <s:else >
                           		<a class="end" style="padding: 2px 0px;" id="attention<s:property value='irpProject.projectid'/>"   
                           		  onclick="addOrDeleteAttactionMe( <s:property value='irpProject.projectid'/>)"  
                           		  href="javascript:void(0);" ><b>
                           		 关注圈子</b></a> 
                         </s:else>
                         </div>
                         <h1><a href="#" target="_blank" style="font-size: 25px;"> <s:property value='irpProject.prname'/></a></h1>
            </div>
            <div class="qzInfo">
            	<div style="font-size: 13px;">私密性：<s:if test="@com.tfs.irp.project.entity.IrpProject@IS_PUBLISH==irpProject.ispublish">公开</s:if>
                   <s:if test="@com.tfs.irp.project.entity.IrpProject@NOT_PUBLISH==irpProject.ispublish">私密</s:if>
                     &nbsp;&nbsp;	&nbsp;&nbsp;
                                       <%--  圈子阶段：<s:property value="@com.tfs.irp.project.web.ProjectAction@findProjectStatus(irpProject.prostatus)"/> --%></div>
                <div style="font-size: 13px;"><span class="qzDec">描述：
                                       	 	<s:property value='irpProject.prdesc'/>
                                       	 	</span></div>
            </div>
            <div class="timeInfo">
            <s:if test="irpProject.isclosed==@com.tfs.irp.project.entity.IrpProject@NOT_CLOSED">
                                        	 <p class="ending" id="dayP"></p>
                                        	 <s:if test="irpProject.isclosed==@com.tfs.irp.project.entity.IrpProject@NOT_CLOSED"> 
                                        			 <p class="ending" id="dayP"></p> 
                               	       		</s:if>
                               	       		</s:if><s:else>
                               	       				 <p class="ending" >已完成</p>
			                                 </s:else>    
                                            <div class="node_trwbi" id="starEndDate" align="right" style="font-size: 13px">  
                                            <span id="starData" class="timeLine"> 
                                                	<b>
													<s:date name="irpProject.starttime" format="yyyy-MM-dd" />
	                                        		</b></span>—<span id="endData"><b>
											    		<s:date name="irpProject.endtime" format="yyyy-MM-dd"/> 
											    	</b>
											     </span>
                                            </div>
            </div>
            <div class="clear"></div>
        </div>
		<div class="qzSelTitleBar" id="tabMenu">
        	 <span class="on"   id="sharea" href="javascript:void(0);"  onclick="tabs(this)" _link="<%=rootPath %>project/link_me.action" >
                <b>动态</b>
                <span id="tabShare" style="height: 0px;">
             (<s:property value="@com.tfs.irp.projectsharetask.web.IrpProjectShareTaskAction@countShareTaskCollectByShareTaskId(irpProject.projectid)"/>)
               </span>
        	</span> |
            <span id="taska" href="javascript:void(0);"  onclick="tabs(this)" _link="<%=rootPath %>project/alltask.action">
                  <b>任务</b><span id="tabTask" style="height: 0px;">
                 (<s:property value="@com.tfs.irp.projectsharetask.web.IrpProjectShareTaskAction@countTaskCollectByShareTaskId(irpProject.projectid)"/>)
               </span>

			</span>|
            <span id="attacheda" href="javascript:void(0);"  onclick="tabs(this)" _link="<%=rootPath %>/project/allprojectattached.action">
                                      <b>附件</b>
                                          <span id="tabDocument" style="height: 0px;">
											(<s:property value="@com.tfs.irp.project.web.ProjectAction@countProjectAttached(irpProject.projectid)"/>)
										</span>
                                    </span>|
            <span id="attacheda" href="javascript:void(0);"  onclick="tabs(this)" _link="<%=rootPath %>/project/projectallperson.action">
                                  <b>成员</b>
                                  <span id="tabPerson" style="height: 0px;">
			(<s:property value="@com.tfs.irp.projectperson.web.IrpProjectPersonAction@findProjectPersonCount(irpProject.projectid)" default="0"/>)
					</span>
                          </span>|
            <span  id="attacheda" href="javascript:void(0);"  onclick="tabs(this)" _link="<%=rootPath %>/project/projectallpersonstate.action">
                           <b>关注者</b>
                           <span id="tabPersonState" style="height: 0px;">
						(<s:property value="@com.tfs.irp.projectperson.web.IrpProjectPersonAction@findProjectPersonAttCount(irpProject.projectid)" default="0"/>)
						</span>
                   </span>
        </div>
        
<!--z-->
<s:if test="irpProject.isclosed==@com.tfs.irp.project.entity.IrpProject@NOT_CLOSED">
  <s:if test="@com.tfs.irp.projectperson.web.IrpProjectPersonAction@findIsStateByProjectIds(irpProject.projectid)==@com.tfs.irp.projectperson.entity.IrpProjectPerson@IS_STATE">

 <div class="area20"></div>
        <div class="qzNews">
        	<div class="maintext" id="publishShareT">
        	<textarea name="irpProjectShareTask.sharedesc" onkeyup="changeTextAreaText(this.value)" onblur="blurTextAreaText()" onfocus="focusTextAreaText()" id="sharedesctextarea" class="write_wtcpsw" defaultvalue="你在忙什么？"></textarea>
            <div class="textCount"id="topicFont_01" >您还可以输入<font id="topicFontCount_01" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;"><s:property value="maxAmount" /></font>字</div>
             <div class="textCount" style="display: none;"id="topicFont_02" >您已超出<font id="topicFontCount_02" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color:red;"><s:property value="maxAmount" /></font>字</div>
             <div class="action_cpsw">
            <ul class="list_acpsw" style="float: left;padding-top: 10px; padding-left: 489px;">
                <li class="ament_lacpsw">
                <a href="javascript:void(0);"  title=""><input type="file" name="" id="projectFileBut" /></a></li> 
            </ul>  
        </div>
        
            <div class="sub" >
            <input type="button" id="sharebutton" value="分&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;享"/></div>
            <div class="clear"></div>
             <div id="showFileList"></div>  
        </div>
       </div>
   <%--task --%>                                
<!--z-->
  <!--2--> 
  
 <%--task --%>  
 <div  id="addTaskDiv" class="publishsharewrap line_publishsharewrap"  style="border: 2px solid #e9e9e9;overflow:hidden; padding:20px;background: white;width: 95%;">
		<div class="name_ptaskt clearfix">
			<input id="TaskInput" class="txt_ptaskt" style="width: 620px;" onfocus="focusTaskInput()" onblur="blurTaskInput()" onkeyup="blurTaskInput()"  value="要做什么事？" type="text" />  
		</div> 
		<div class="leader_ptaskt clearfix"> 
			<label></label> 
			<input id="offPersonName" readonly="readonly" onclick="checkTaskOffpersonId()" placeholder="负责人"  class="txt_ptaskt user_lptaskt" type="text"/> 
			<input id="taskPersonId" type="hidden" /> 
			<%-- <span class="txt_lptaskt">（负责人）</span>  --%>
	    </div>  
	     
	    <div class="name_ptaskt clearfix leader_ptaskt" >
	    	<input name="taskPersonIds"  id="taskPersonIds" type="hidden"/> 
			<input style="width: 620px;" class="txt_ptaskt" name="taskPersonNames" placeholder="还有谁参与？" readonly="readonly"   id="taskPersonNames"  onclick="toCheckTaskPerson(<s:property value='irpProject.projectid'/>)" type="text" /> 
			 <%-- <span class="txt_lptaskt">（还有谁参与？）</span>  --%>
		</div> 
	    <div class="intro_ptaskt clearfix">
        	<textarea style="width: 620px;height: 60px;"onfocus="changeTaskTextArea()" onblur="changeTaskTextArea()" onkeyup="checkTaskDescAmount(this.value)" id="taskDescTextArea" class="txt_ptaskt">更详细的说明</textarea> 
	     <div class="count_cpsw" style="padding-right: 5px;">
            <input type="hidden" value="0" id="inputcounttask"/>
            <input type="hidden" id="maxinputtask" value="maxAmount"/>
            <span id="topicFont_011"  class="num_ccpsw" style="float: right;">
          	还可以输入
          <label id="topicFontCount_011" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;">
          	<s:property value="maxAmount" />
          </label>个字
          </span>
		  <span id="topicFont_021"  class="num_ccpsw" style="display: none;">
			  已超出
		  <label id="topicFontCount_021" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color:red;"></label>个字
		  </span>
             
        </div> 
		</div>  
         <div class=" action_cpsw action_ptaskt" >
            <ul class="list_acpsw">
                <li class="ament_lacpsw">
                <a href="javascript:void(0);" title="">
                	<input type="file" name="" id="projectFileButTask" />
                </a>
                </li> 
                 <span class="end_ptask date_lptaskt clearfix ">
                 <input title="何时开始" name="irpProjectShareTask.starttime" type="text"   id="taskStart" class="easyui-datebox" editable="false" validType="StartTime">&nbsp;&nbsp; 
		   	     —— 
		         <input title="何时完成" name="irpProjectShareTask.endtime" type="text" id="taskEnd" class="easyui-datebox" editable="false" validType="EndTime">
		   	    </span> 
            </ul> 
	    </div> 
	 <!--    <div class="action_cpsw " >
              <a  id="addtaskbut" class="pub_aptaskt disable_pub_aptaskt" title="发布" href="javascript:void(0);"><b><i>发布</i></b></a>
	    </div>  -->

	    
	     <div class="sub" style="float: right;">
            <input id="addtaskbut" type="button" style="color: white;font-size: 16px;" value="发&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;布"/></div>
            <div class="clear"></div>
	    <div id="showFileListTask" class=" action_cpsw action_ptaskt" style="float: left;padding-top: 20px;width: 600px;"></div>
 </div> 
  <%--task --%>
 </s:if>
 </s:if>
  <!--2-->   
  <div id="showlinkprojectdiv"></div>
  </div>
</div>
<!--right E-->
<!--leftcontent end-->
<!--右侧内容结束-->
<!--尾部信息--> 
<jsp:include page="../include/client_foot.jsp" />
<!--尾部信息end-->
</body>
</html>