<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
 <style>
 
.ellipsis{
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
</style>   
  <dt><a href="javascript:void(0);" class="linkbh14">相关知识</a></dt> 
<s:if test="chnlDocLinks!=null">
  <s:iterator value="chnlDocLinks">
  	     <dd style=" width: 180px;" class="ellipsis">
  	   <img src="<%=rootPath %>client/images/arrow_right.gif" alt="" width="7px;" height="7px;" /><a href="javascript:void(0)" onclick="documentview(<s:property value='docid'/>)" title="<s:property value='doctitle'/>">  
		     <s:property value='doctitle'/> 
  	   </a></dd>
  </s:iterator>
</s:if>
<s:else>
暂时没有相关知识...
</s:else>
  <script type="text/javascript">
  	//查看文档
	function documentview(_docid){
		window.open('<%=rootPath %>site/showdocumentinfo.action?refrechstatus=1&docid='+_docid );  
	}
  </script>
