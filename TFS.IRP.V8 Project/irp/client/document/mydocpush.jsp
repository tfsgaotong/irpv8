<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
	 <s:if test="!pushDocs.isEmpty()">
		 <s:iterator value="pushDocs" status="lists"> 
		 <s:if test="#lists.count<=3">
			 <ul class="myDiscuss list4" id="mydocdl<s:property value='DOCID'/>">
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
			 </s:if>
		 </s:iterator>
	 </s:if>
 <s:else><span style="padding-left:15px;">根据您设置的标签，系统没有找到可以推荐给您的知识!</span></s:else>  	
<script type="text/javascript">
	//删除知识
	function clientdeletedocument(_docid){ 
		$.dialog.confirm('您确定要删除这个知识吗？',function(){
			$.ajax({
					type:'post',
					url:'<%=rootPath%>site/clientdeletedocument.action',
					data:{'docid':_docid},
					success: function(msg){
						if(msg=="1"){ //删除成功 
								//然后将这个dl隐藏
						$('#mydocdl'+_docid).fadeOut();
						$('#mydocdl'+_docid).fadeOut("slow");
						$('#mydocdl'+_docid).fadeOut(3000);
						} 
					}
	    	 });
		},function(){}); 
	}	
</script>

