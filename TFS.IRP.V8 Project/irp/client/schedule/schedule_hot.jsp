<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
  <head>  
  <style type="text/css">
  .ellipsis{
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
.cRed3 {
    color: #BB2737;
    font: 18px arial;
    padding-right: 8px;
}
  </style> 
      <dt><a href="javascript:void(0);" class="linkbh14">代办事项</a></dt>
<s:if test="irpSchedules!=null && irpSchedules.size()>0">
	<s:iterator value = "irpSchedules" status="irptopicliststatus">
  	     <dd style="width: 200px;">
  		     <s:date name="starttime" format="MM-dd-yyyy HH:mm"/>在<s:property value="place"/> 有 <a href="" ><s:property value="schname"/></a>  要做
  	    </dd>
	</s:iterator>
</s:if>
<s:else>
暂时没有热门的投票...
</s:else>
  