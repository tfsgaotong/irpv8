<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>  
  <body> 
  <div class="labels">文章订阅</div> 
			<div class="line area20"></div>
	 <s:if test="subscribes!=null &&  subscribes.size()!=0">
		 <s:iterator value="subscribes" status="lists"> 
			 <ul class="myDiscuss list4" id="mydocdl<s:property value='subscribeobjid'/>">
			     <li>
			     	<section><aside></aside>
			     	<s:set var="document" value="getIrpDocumentById(subscribeobjid)" />
			     		<p style="padding-top:5px; overflow:hidden; padding-bottom:5px; height:26px;">
			     			<a href="<s:url namespace="/document" action="document_detail"><s:param name="docid" value="subscribeobjid" /></s:url>" target="_blank" class="linkb14">
			    				<s:property value="#document.doctitle"/><s:if test="subReadStatus==1"><span style="color: red;">(文章已更新)</span></s:if>
			    			</a> 
			    		</p>
			    		 <p style="padding-top:5px; padding-bottom:5px;height:26px;">标签：<s:iterator value="#document.dockeywords.split(',')"  status="st" var="as">
  				 			<a style="font-size:12px;" href="javascript:void(0)" onclick="searchInfo1('<s:property value="#as"/>')" class="linkc12"><s:property value="#as"/></a>&nbsp;&nbsp;
  							</s:iterator>
  						</p>
			            <p style="padding-top:5px; padding-bottom:5px; ">核心提示：<s:property value="#document.docabstract"/></p>
			            <p style="text-align:right; padding-top:5px; padding-bottom:5px; "><span style="margin-left:10px;"><s:date name="#document.crtime" format="yyyy-MM-dd HH:mm:ss" /></span></p>
			        </section>
			        <div class="clear"></div>
			     </li>
			 </ul>
		 </s:iterator>

	 <div class="main" align="right">
    <div class="left">
      <div class="fyh">
       <ul>
        <s:property value="pageHTML" escapeHtml="false" />
        </ul>
      </div>
    </div>
     </div>
    	</s:if>
    	<s:else><br>暂时没有订阅知识!</s:else>  	
  </body>
</html>
