<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>  
   <style> 
.ellipsis{
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
</style>  
  </head>
  <dt><a href="javascript:void(0);" class="linkbh14">相关专题</a></dt> 
<s:if test="irpTopIcList!=null">
  <s:iterator value="irpTopIcList">
  	     <dd class="ellipsis" style=" width: 180px;">
  	   <a href="javascript:void(0)" onclick="documentview(<s:property value='topicid'/>)" title="<s:property value='topicname'/>">     
		     <s:property value='topicname'/>
  	   </a></dd>
  </s:iterator>
</s:if>
<s:else>
暂时没有相关专题...
</s:else>
  <script type="text/javascript">
  	//查看文档
	function documentview(_docid){
		window.open('<%=rootPath %>site/showdocumentinfo.action?refrechstatus=1&docid='+_docid );  
	}
  </script>
