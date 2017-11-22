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
      <s:iterator value="listQuestion">
	  	  <li style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;font-size:14px;margin:8px 0;">
		  	  <a  href="<%=rootPath%>question/questionDetail.action?questionid=<s:property value="questionid"/>" target="_blank" onclick="hits(<s:property value="questionid"/>)" title="<s:property value='title'/>">
			  	  <s:if test="title!=''">
			  	  	<s:property value='title'/>
			  	  </s:if>
			  	  <s:else>
			  	  	<s:property value='htmlcontent'/>
			  	  </s:else>
			  </a>
	  	  </li>
  	  </s:iterator>

  