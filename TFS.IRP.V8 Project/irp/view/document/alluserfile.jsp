<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
	 <s:if test="personAllFile!=null && personAllFile.size()>0">
               <ul class="myDiscuss list4" id="mydocdl<s:property value='docid'/>">
			     <li>
			     	<section>
			            <p style="padding-top:10px; padding-bottom:5px; ">
              			<s:iterator value="personAllFile" status="lists">
	              			<s:if test="#lists.count<=5">
								<img src="<%=rootPath%>file/readfile.action?fileName=<s:property value='@com.tfs.irp.util.ThumbnailPic@searchFileName(filename,"_150X150")'/>" alt=""  width="145" height="148" />
							</s:if>
						</s:iterator>						
						</p>
			        </section>
			        <div class="clear"></div>
			     </li>
			 </ul>
	 </s:if>
 <s:else><span style="padding-left:15px;">没有个人图片</span></s:else>  	
