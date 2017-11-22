<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>  
<script type="text/javascript">
<!--
	$(function(){
		var _id="<s:property value='id'/>";
		 $.ajax({
				url: '<%=rootPath%>site/clientrightadddoc.action',
				data:{'id':_id},
				type:'post', 
			    async: false ,
			    success:function(mes){
			    	if(mes=="success"){
			    		$('#addsubjecta').show();
		    		} 
			    }
			}) ;
		
	});
//-->
</script>
<div class="zj_title1">
            	 <div class="zj_ttj">
            	 <h1><s:property value="findChnlName(id)"/>
            	 <div style="float: right;padding-top: 6px;">
            	<a id="addsubjecta" style="display: none;"  target="_blank" href="<%=rootPath %>site/to_add_subject.action?id=<s:property value='id'/>" title="提交知识">
            	<img src="<%=rootPath %>client/images/addbuddy.gif"/>
            	</a>
            	
            	 </div>
            	 </h1><label style="float: right;margin: 0 10px 0 0;cursor: pointer;" onclick="switchOverViewSel()" >切换列表</label> </div>
            	 <div class="zj_leibie"></div>
</div>
        <div class="zj_boxk" style="height: auto;">
         <s:if test="chnlDocLinks!=null &&chnlDocLinks.size()>0">
          	 <s:iterator value="chnlDocLinks">
          	 	<div style="padding: 5px 5px 5px 5px; ">
						<dl>
							<dt style="width: 10%;float: left;">
							<s:if test="irpChannel==null">
								<img src="<%=rootPath %>file/readfile.action?fileName=<s:property value='@com.tfs.irp.util.ThumbnailPic@searchFileName("AP2013102331467.gif","_150X150")'/>" width="160"  height="120"/>						
							</s:if>
							<s:else>			
								<img src=" <s:property value='docCoverPath(docid,docflag)'/>" width="60"  height="60" />
							</s:else>
							</dt>
							<dd  style="width: 90%;float: left;">
								<span>
								<a href="javascript:void(0)" onclick="showdocumentinfo(<s:property value='docid'/>)" title="<s:property value='doctitle'/>">
								<s:property value="doctitle" />&nbsp;.&nbsp;<s:property value="cruser"/>&nbsp;.&nbsp;<s:date name="docpubtime" format="yyyy-MM-dd" />
								</a>
									
								</span><br/>
								<span>
									标签:&nbsp;<s:property value="getIrpDocumentById(docid).dockeywords" />
								</span><br/>
								<span>
								<a href="javascript:void(0)" onclick="showdocumentinfo(<s:property value='docid'/>)" title="<s:property value='getIrpDocumentById(docid).docabstract'/>">
									核心提示:&nbsp;<s:property value="getIrpDocumentById(docid).docabstract" />
								</a>	
								</span>
								
							</dd>
						
						</dl>
						<div style="border-bottom:thin dashed; width:100%;">&nbsp;</div>
          	 	</div>

          	 </s:iterator>
          	   <div class="main" align="right" style="width: 650px;margin-top: 50px;" >
	                <div class="left" style="width: 650px;" >
		                  <div class="fyh"  style="width: 650px;" >
		                   <ul style="width: 650px;margin-top: -30px;"  >
		                    <s:property value="pageHTML" escapeHtml="false" />
		                    </ul>
		                  </div>
	                </div>
                </div>
         </s:if>
         <s:else>
       		 <span style=" padding-left: 10px;">  该分类下暂时没有知识...</span>
         </s:else>
            </div>
<form id="pageForm">   
		<s:hidden name="pageNum" id="pageNum" />
		<s:hidden name="pageSize" id="pageSize" />
		<s:hidden name="id" id="id"></s:hidden>  
		<s:hidden name="orderField" id="orderField"></s:hidden>
		<s:hidden name="orderBy" id="orderBy"></s:hidden>
		<s:hidden name="viewcheckone" id="viewcheckone" value="viewcheckone"></s:hidden>
	</form>