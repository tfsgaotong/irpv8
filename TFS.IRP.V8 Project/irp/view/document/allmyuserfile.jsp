<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<div class="labels">图片中心</div> 
			<div class="line area20"></div>
	 <s:if test="personAllFile!=null && personAllFile.size()>0">
               <ul class="myDiscuss list4" id="mydocdl<s:property value='docid'/>">
			     <li>
			     	<section>
			            <p style="padding-top:10px; padding-bottom:5px; ">
              		<s:iterator value="personAllFile">
						<img src="<%=rootPath%>file/readfile.action?fileName=<s:property value='@com.tfs.irp.util.ThumbnailPic@searchFileName(filename,"_150X150")'/>" alt=""  width="145" height="148" />
					</s:iterator>						
						</p>
			        </section>
			        <div class="clear"></div>
			        <div class="pages" style="text-align:right;margin-top:10px;"><s:property value="pageHTML" escapeHtml="false" /></div>
			     </li>
			 </ul>
	 </s:if>
 <s:else><span style="padding-left:15px;">没有个人图片</span></s:else>  	
