<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>  
  <body> 
  <div class="labels">知识推荐</div> 
			<div class="line area20"></div>
	 <s:if test="pushDocs!=null &&  pushDocs.size()!=0">
		 <s:iterator value="pushDocs" status="lists"> 
			 <ul class="myDiscuss list4" id="mydocdl<s:property value='docid'/>">
			     <li>
			     	<section><aside></aside>
			     		<p style="padding-top:5px; overflow:hidden; padding-bottom:5px; height:26px;">
			     			<a href="<s:url namespace="/document" action="document_detail"><s:param name="docid" value="DOCID" /></s:url>" target="_blank" class="linkb14">
			    				<s:property value="DOCTITLE.toString().substring(1,DOCTITLE.toString().length()-1)" escapeHtml="false"/>
			    			</a> 
			    		</p>
			    		 <p style="padding-top:5px; padding-bottom:5px;height:26px;">标签：
			    		 
			    		 <s:set var="start" value="@com.tfs.irp.util.SysConfigUtil@getSysConfigValue(@com.tfs.irp.solr.entity.IrpSolr@STAYLE_START)" />
							<s:set var="end" value="@com.tfs.irp.util.SysConfigUtil@getSysConfigValue(@com.tfs.irp.solr.entity.IrpSolr@STAYLE_END)" />
							<s:iterator value="DOCKEYWORDS.toString().substring(1,DOCKEYWORDS.toString().length()-1).split(',')"  status="st" var="as">
							<a href="javascript:void:(0)" onclick="searchInfo1('<s:property value="#as.replaceAll(#start,'').replaceAll(#end,'')" />')" class="linkc12"><s:property value="#as" escapeHtml="false" /></a>&nbsp;
					  		</s:iterator>
  						</p>
			         <%--    <p style="padding-top:5px; padding-bottom:5px; ">核心提示：<s:property value="DOCABSTRACT.toString().substring(1,DOCABSTRACT.toString().length()-1)"/></p> --%>
			            <p style="text-align:right; padding-top:5px; padding-bottom:5px; "><span style="margin-left:10px;"><s:date name="CRTIME" format="yyyy-MM-dd" /><br/></span></p>
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
    	<s:else><br>暂时没有可以推荐的知识!</s:else>  	
  </body>
</html>
