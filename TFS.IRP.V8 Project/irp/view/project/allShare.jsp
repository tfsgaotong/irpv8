<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<style>
<!--
.itemfeed{
border-bottom: 2px dashed #dbdbdb;
}

-->
</style>
 <form id="pageForm">
	<s:hidden name="pageNum" id="pageNum" /> 
	<s:hidden name="pageSize" id="pageSize" />  
</form> 
  <s:iterator value="irpProjectShareTasks" var="shareshare">
   <div class="itemfeed" id="shareTaskDiv<s:property value='sharetaskid'/>">
	   <div class="left_ifed">
	  <a  target="_blank" hideFocus="true" title="" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='cruserid'/>">
	  <img alt="<s:property  value='userName'/>" src="<s:property value='userPicUrl'/>" width="50" height="50" /></a>
	  </div>
	  <div class="right_ifed">
		 <div onmouseover="mouseinrow(<s:property value='sharetaskid'/>)" onmouseout="mouseoutrow(<s:property value='sharetaskid'/>)">
		   <div class="main_fed">
		 	 <div class="source_fed">
				  <a  target="_blank" hideFocus="true" class="name_sfed skip_mmfed linkbh14" title="" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='cruserid'/>">
				  <s:property value="userName"/> </a>
				  <s:if test="isstate==@com.tfs.irp.projectsharetask.entity.IrpProjectShareTask@IS_SHARE_STATE">
				 		 <span class="cont_sfed">发表动态：</span>
				  </s:if>
				  <s:else>
				  	<span class="cont_sfed">发起的任务</span>
				  </s:else> 
			</div>
		  <div class="master_mfed">
		  		<span class="lquote_mmfed"></span>
		  		<span class="content_mmfed from_imltali">
		  			<a><s:property value="sharedesc"/></a>
		  		</span>
		  		<span class="rquote_mmfed"></span>
			   <div >  
				   	<s:iterator value="attachedList" var="att">
				   <span title="<s:property value='attdesc'/>">
				     	<s:if test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@JPG_FIELD_NAME==typeid">
				     	 	<img style="margin-left: 5px;margin-top: 5px; margin-bottom: 15px;padding-left: 5px;" src="<%=rootPath%>file/readfile.action?fileName=<s:property value='@com.tfs.irp.util.ThumbnailPic@searchFileName(attfile,"_150X150")'/>" width="150px" height="120px"/>  
				     	</s:if>
				     	<s:else>
				     		<img style="margin-left: 5px;margin-top: 5px; margin-bottom: 15px;padding-left: 5px;"  alt="" src="<%=rootPath%>client/images/doc.jpg" width="150px" height="120px">
				     	</s:else>
				     	 <s:if test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@WORD_FIELD_NAME==typeid">
				   	 	 	<a style="margin-left: -90px;" class="preview_faridl" target="_blank" href="<%=rootPath%>file/online_preview.action?fileName=<s:property value='attfile'/>">预览</a>
				   		</s:if>
				   		<s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@JPG_FIELD_NAME==typeid">
				   		 <a style="margin-left: -90px;" class="preview_faridl" target="_blank" href="<%=rootPath%>file/inlinereadfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>">预览</a>
				   		</s:elseif>
				   		 <a  href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>" >下载</a>   
				   </span>
				     </s:iterator> 
			  </div>
	  </div>
		  <div class="action_mfed clearfix">
			  <div class="btn_amfed">
				  <ul>
					 	<li> 
							 	<s:if test="longuserid==cruserid">
							 		<a style="visibility: hidden;" id="deletealable<s:property value='sharetaskid'/>" onclick="deleteShareTask(<s:property value='sharetaskid'/>)" hideFocus="true" href="javascript:void(0);">删除</a>
							 	</s:if> 
							 </li>  
					  <li class="reply_bamfed"> 
					  	<a onclick="showreplayTexarea(<s:property value='sharetaskid'/>,0,'<s:property value="userName"/>')" hideFocus="true" href="javascript:void(0);">回复
					  	<i class="corner_rbamfed" id="replayAmount<s:property value='sharetaskid'/>">
					  	(<s:property value="@com.tfs.irp.projectsharetask.web.IrpProjectShareTaskAction@countReplayShareTask(sharetaskid)"/>)
					  	</i></a>
					  </li>
				  </ul>
			  </div>
		  <s:date name="crtime" format="yyyy-MM-dd HH:mm" />
		  </div>
	  </div>
		 </div>
		 <div>
	  <div class="comment_fed" <s:if test="replayShareTaskList==null || replayShareTaskList.size()==0">style="display: none;"</s:if>  id="hidejiandiv<s:property value='sharetaskid'/>">
	  <div class="corner_lfed"> 
	  <span></span>
	  </div>
	  <!-- replay -->
	  <s:iterator value="replayShareTaskList" var="replayList">
	  <div class="list_fed"  onmouseover="mouseinrow(<s:property value='sharetaskid'/>)" onmouseout="mouseoutrow(<s:property value='sharetaskid'/>)">
		  <div class="cmt_fed">
			  	<div class="item_cfed">
				  <div class="left_icfed">
					  <a  target="_blank" hideFocus="true" title="" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='cruserid'/>">
					  	 <img alt="" src="<s:property value='userPicUrl'/>" width="30" height="30"/>
					  </a>
				  </div>
				  <div class="right_icfed"  >
					  <div class="master_cfed ">
					  <a  target="_blank" hideFocus="true" class="avatar_mcfed skip_cmfed linkc12" title="" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='cruserid'/>">
					   <s:property value="userName"/></a>
					  <span > ：<s:property value="sharedesc"/>
					  	<s:if test="#replayList.isstate==@com.tfs.irp.projectsharetask.entity.IrpProjectShareTask@IS_TASK_STATE">
					  	 				(状态：  <s:if test="isclosed==@com.tfs.irp.projectsharetask.entity.IrpProjectShareTask@NOT_CLOSED">进行中</s:if>
	  		 							<s:else>已关闭</s:else> ) 
					  	</s:if>
					  </span>
					  </div>
					  <div class="action_cfed clearfix">
						  <div class="btn_acfed">
						  <ul>
							  <s:if test="#replayList.cruserid==longuserid">
								  <li class="more_bacfed">
								  <a style="visibility: hidden;" id="deletealable<s:property value='sharetaskid'/>"  onclick="deleteShareTask(<s:property value='sharetaskid'/>)" hideFocus="true" title="" href="javascript:void(0);">删除</a> 
							  </li>
							  </s:if>  
						  </ul>
						  </div>
					  <span class="time_acfed"><s:date name="%{crtime}" format="yyyy-MM-dd HH:mm" /></span>
					  </div>
				  </div>
			  </div>
		  </div> 
	  </div>
	  </s:iterator>
	  <!-- replay -->
	  </div>
	  <%-- 二级回复列表显示及其回复 pubreply_fed这个class去掉就可以显示回复框--%>
	  <!-- qq -->
	  <div id="replayTexarea<s:property value='sharetaskid'/>"  class="pubreply_fed">
		  <div class="notice_prfed clearfix">
		  	<div class="count_cpsw">
            	<span class="num_ccpsw" id="inputcounts">0</span>/<label id="maxinputs"> <s:property  value="maxAmount"/> </label>
        	</div>
			  <div class="who_nprfed"> 
			  	<span>回复</span><span class="toname_wnprfed">tita</span><span>：</span>
			  <a hideFocus="true" class="closed_wnprfed" title="" href="javascript:void(0);">关闭</a>
			  </div>
		  </div>
		  <div class="area_prfed"> 
			  <div class="wrap_aprfed"> 
			  	<textarea onkeyup="showreplayShareDescButton(<s:property value='sharetaskid'/>)" id="replayShareDesc<s:property value='sharetaskid'/>" class="input_waprfed" cols="20" rows="2" asff="true" defaultVal=""></textarea>
			  </div>
		  </div>
	  <div class="action_prfed clearfix">
	  <a onclick="hidereplayTexarea(<s:property value='sharetaskid'/>)" hideFocus="true" class="cancel_aprfed" href="javascript:void(0);"><b><i>取消</i></b></a>
	  <a id="replayShareDescBut<s:property value='sharetaskid'/>" hideFocus="true" class="pub_aprfed disable_pub_aprfed" href="javascript:void(0);"><b><i>回复</i></b></a>
	  </div>
	  	  <div style="display: none;">将文件拖到分享框任何区域并释放鼠标</div>
		  <div style="display: block;" class="preview_prfed">
		 	 <div style="padding-top: 8px; display: none;" id="attachmentViewBox_217452_200149886_1_1_634981573829517681"></div>
		  </div>
	  </div> 
	  <!-- qq -->
		 </div>
	  </div>
  </div> 
  </s:iterator>
<div >
 <s:if test="irpProjectShareTasks!=null && irpProjectShareTasks.size()!=0">
		   <div class="main" align="right">
			    <div class="left">
				      <div class="fyh" >
				       	<ul><s:property value="pageHTML" escapeHtml="false" /></ul>
				      </div>
			    </div>
		</div>
  	</s:if>
</div>
<input id="selfid" type="hidden">
   