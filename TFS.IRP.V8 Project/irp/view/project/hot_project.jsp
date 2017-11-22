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
      <s:iterator value="irpProjects">
	  	  <li style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;font-size:14px;margin:8px 0;">
		  	  <a  href="<%=rootPath %>project/projectinfo.action?projectId=<s:property value='projectid'/>" target="_blank" title="<s:property value="prname"/>">
			  	  	<s:property value="prname"/>
			  </a>
	  	  </li>
  	  </s:iterator>

  