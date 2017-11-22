<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<dt>
	<a href="javascript:void(0);" class="linkbh14">人气TOP排行10</a>
</dt>
<s:if test="chnlDocLinks!=null">
<s:iterator value="chnlDocLinks" status="count">
	<dd class="ellipsis" style="table-layout: fixed;">
		<s:if test="#count.index==9">
			<a class="pw10" href="javascript:void(0);" onclick="documentview(<s:property value='docid'/>)" title="<s:property value='doctitle'/>">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value='doctitle' />
			</a>
		</s:if>
		<s:else>
			<a class="pw0<s:property value='#count.index+1'/>" href="javascript:void(0);" onclick="documentview(<s:property value='docid'/>)" title="<s:property value='doctitle'/>">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value='doctitle' />
			</a>
		</s:else>
	</dd>
</s:iterator>
</s:if>
<s:else>
 	 <dd class="ellipsis" style="table-layout: fixed;">暂时没有TOP排行...</dd>
</s:else>
<script type="text/javascript">
//查看文档
function documentview(_docid){
	window.open('<%=rootPath%>site/showdocumentinfo.action?refrechstatus=1&docid='+_docid );  
}
</script>