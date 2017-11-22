<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head> 
    <title>问答达人</title>  
  </head>
  <dt><a href="javascript:void(0)" class="linkbh14">问答达人</a></dt>
  <s:if test="illquestionlist.size()>0">
  <s:iterator value="illquestionlist">
  	   <dd> <span><s:property value="USERCOUNT"/></span>
  	   <a href="javascript:void(0)"  onclick="client_to_index_person(<s:property value='CRUSERID'/>)" >
  	   <s:if test="getIrpUser(CRUSERID).userpic!=null">
  	   	<img src="<%=rootPath%>file/readfile.action?fileName=<s:property value="getIrpUser(CRUSERID).userpic"/>" alt="" width="50px;" height="50px;"/>
  	   </s:if>
  	   <s:else>
  	   	 <img <s:if test="getIrpUser(CRUSERID).sex==2">src="<%=rootPath%>client/images/female.jpg"</s:if><s:else>src="<%=rootPath%>client/images/male.jpg"</s:else> alt="" width="50px;" height="50px;"/>
  	   </s:else>
  	 <s:property value="getShowPageViewNameByUserId(CRUSERID)" />
	   </a>
	   
	  </dd>
	   <dd> 
  </s:iterator> 
  </s:if>
  <script>
	function client_to_index_person(_personid){
		window.open('<%=rootPath%>/site/client_to_index_person.action?personid='+_personid,'_parent');
	}
  </script>
