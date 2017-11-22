<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<dt><a href="javascript:void(0);" class="linkbh14">正在热议</a></dt>
<s:if test="chnlDocLinks!=null && chnlDocLinks.size()>0">
	<s:iterator value="chnlDocLinks">
	<dd class="ellipsis" style="width: 200px;">
		<a href="javascript:void(0)" onclick="documentview(<s:property value='docid'/>)" title="<s:property value='doctitle'/>"><s:property value='doctitle'/></a>
	</dd>
	</s:iterator>
</s:if>
<s:else>
	<dd class="ellipsis" style="width: 200px;">暂时没有热议的知识...</dd>
</s:else>
<script type="text/javascript">
//查看文档
function documentview(_docid){
	window.open('<%=rootPath %>site/showdocumentinfo.action?refrechstatus=1&docid='+_docid );  
}
</script>