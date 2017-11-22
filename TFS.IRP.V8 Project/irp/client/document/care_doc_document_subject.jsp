<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><html>
  <head>  
    <style>
.ellipsis {
    table-layout: fixed;
}
.ellipsis td{
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
</style>
  </head> 
   <link href="<%=rootPath %>client/css/css.css" rel="stylesheet" type="text/css" /> 
  <table  width="100%" border="0" cellspacing="0" cellpadding="0" class="olbg11 ellipsis">
                <tr>
                  <td>
                   <ul class="ul_dtlist black12">
	                 <s:if test="chnlDocLinks!=null && chnlDocLinks.size()>0">
	                  <s:iterator value="chnlDocLinks">
	                    <a href="javascript:void(0)" onclick="documentview(<s:property value='docid'/>)">
	                    	<li>  
								  ·<s:property value='doctitle'/> 
	                    	</li>
	                    </a>
	                  </s:iterator>
	                 </s:if>
	                 <s:else>
	                 	<li>暂时没有精华知识...</li>
	                 </s:else>
                    </ul>
                   </td>
                </tr>
              </table>
  <script type="text/javascript">
  	//查看文档
	function documentview(_docid){
	window.open('<%=rootPath %>site/showdocumentinfo.action?refrechstatus=1&docid='+_docid );  
	}
  </script>
