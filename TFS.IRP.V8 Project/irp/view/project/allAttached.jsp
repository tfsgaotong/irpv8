<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
</head> 
 <form id="pageForm">
	<s:hidden name="pageNum" id="pageNum" /> 
	<s:hidden name="pageSize" id="pageSize" />  
</form> 
<style>
<!--
.item_dl {
    border-bottom: 2px dashed #dbdbdb;
}

-->
</style>
 	<div>
 		<ul class="item_dll">
 			 <s:iterator value="projectAttacheds"  var='idl'>
 			 	<li class="item_dl">
					<div class="ico_idl">
							<div class="ico_ffed">
							
							<s:if test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@JPG_FIELD_NAME==typeid">
					     		 <img  src="<%=rootPath%>file/readfile.action?fileName=<s:property value='@com.tfs.irp.util.ThumbnailPic@searchFileName(attfile,"_150X150")'/>"  style="width: 48px;height: 48px;"/>  
					     	</s:if> <s:else>
					     	<img src="<%=rootPath%>client/images/doc.jpg"/>
					     	</s:else> 
							</div>
					</div>
					<div class="right_idl">
						<div class="name_ridl">
						<s:if test="taskids.contains(#idl.attprop)" >  
							<s:if test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@WORD_FIELD_NAME==typeid">
			                 	 <a  target="_blank" href="<%=rootPath%>file/online_preview.action?fileName=<s:property value='attfile'/>"><s:property value="attdesc"/></a> 
			               </s:if>
			                <s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@JPG_FIELD_NAME==typeid">
			               <a  target="_blank" href="<%=rootPath%>file/inlinereadfile.action?fileName=<s:property value='attfile'/>"><s:property value="attdesc"/></a> 
	               			</s:elseif>
	               			<s:else>
	               			<s:property value="attdesc"/>
	               			</s:else>
						</s:if>
						<s:else>
							<s:property value="attdesc"/>
						</s:else>  
						</div>
						<div class="action_ridl">
							<div class="who_aridl">
								<a  target="_blank" class="user_waridl" href="<%=rootPath %>site/client_to_index_person.action?personid=<s:property value='cruserid'/>">
									<%--用户名称 --%>
									<s:property value="@com.tfs.irp.projectperson.web.IrpProjectPersonAction@findUserNameByUserId(cruserid)"/>
								</a>
								<span>上传于</span>
								<s:date name="crtime" format="yyyy-MM-dd HH:mm"/>
							</div>
							<div class="fun_aridl">
							<s:if test="taskids.contains(#idl.attprop)" > 
								<s:if test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@WORD_FIELD_NAME==typeid">
				                 	 <a  class="preview_faridl"  target="_blank" href="<%=rootPath%>file/online_preview.action?fileName=<s:property value='attfile'/>">预览</a> 
				               </s:if>
				                <s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@JPG_FIELD_NAME==typeid">
				               <a  class="preview_faridl"  target="_blank" href="<%=rootPath%>file/inlinereadfile.action?fileName=<s:property value='attfile'/>">预览</a> 
		               			</s:elseif>
										<a class="down_faridl" title="" href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>" >下载</a>
							</s:if> 
		 					</div>
	 					</div>
					</div>
				</li>
 			  </s:iterator>
 		</ul>
 </div>
   <s:if test="projectAttacheds==null || projectAttacheds.size()==0">
 <p style="font-size: 15px;padding: 5px;">暂时没有附件!<p>
  </s:if>
<div class="main" align="right">
<div class="left">
   <div class="fyh" >
    	<ul><s:property value="pageHTML" escapeHtml="false" /></ul>
   </div>
</div>
</div>