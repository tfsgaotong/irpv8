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
      <dt><a href="javascript:void()" class="linkbh14">常见问题汇总</a></dt>
    <s:iterator value="connqlist">
  	     <dd class="ellipsis" style="width: 200px;">
  	  <a href="<%=rootPath%>question/questionDetail.action?questionid=<s:property value="questionid"/>" target="_blank" onclick="hits(<s:property value="questionid"/>)" title="<s:property value='title'/>">
  	  <s:if test="title!=''">
  	  <s:property value='title'/>
  	  </s:if>
  	  <s:else>
  	  <s:property value='htmlcontent'/>
  	  </s:else>
		      
  	  	 </a>
  	   </dd>
  </s:iterator>

  