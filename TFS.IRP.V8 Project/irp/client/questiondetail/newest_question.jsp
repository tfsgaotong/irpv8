<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
  <style type="text/css">
  .ellipsis{
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
  </style> 
   <script type="text/javascript">
    function hits(questionid){
    	$.ajax({
    		type:"post",
    		url:"<%=rootPath%>question/hits.action",
    		cache:false,
    		async:false,
    		data:{
    			questionid:questionid
    		}
    	});
    }
  </script>
      <dt><a href="javascript:void()" class="linkbh14">最新问答</a></dt>
    <s:iterator value="illanswerset" var="illanswersetid">
  	     <dd class="ellipsis" style="width: 200px;">
  	  <a href="<%=rootPath%>question/questionDetail.action?questionid=<s:property value="#illanswersetid"/>" target="_blank" onclick="hits(<s:property value="#illanswersetid"/>)" title="<s:property value='getIrpQuestion(#illanswersetid).title'/>">
  	      <s:if test="getIrpQuestion(#illanswersetid).title!=''">
  	      <s:property value='getIrpQuestion(#illanswersetid).title'/> 
  	      
  	      </s:if>
  	      <s:else>
  	      <s:property value='getIrpQuestion(#illanswersetid).htmlcontent'/> 
  	      
  	      </s:else>
		     
  	  	 </a>
  	   </dd>
  </s:iterator>

  