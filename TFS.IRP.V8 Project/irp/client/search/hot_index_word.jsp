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
    function searchInfoWord(_searchInfo){
    	var disposecontent =  _searchInfo.replace(":","\\:");
		if(searchInfo.length>38){
			searchInfo = searchInfo.substring(0,37);	
		}
    	var eacapeInfo = encodeURI(disposecontent);
    	//查询数据库配置表的检索开关
    	
    	$.getJSON("<%=rootPath%>menu/search_ts.action",function(data){
    		var result=data.message;
    		if(result=="t"){
    			window.location.href="<%=rootPath%>menu/trs_search.action?searchInfo="+eacapeInfo+"&searchtype=1"; 
    		}else{
    			window.location.href="<%=rootPath%>solr/searchcontentofsolr.action?searchInfo="+eacapeInfo+"&searchtype=1";
    		}
    	});
    	
    }

  </script>
      <dt><a href="javascript:void()" class="linkbh14">热门检索词</a></dt>
    <s:iterator value="tagsearchlist">
  	     <dd>
	  	     <div class="ellipsis" style="width: 160px;float:left;">
		  	     <a href="javascript:void(0)" title="<s:property value='tagname'/>" onclick="searchInfoWord('<s:property value='tagname'/>')">
			    	<s:property value='tagname'/>
			     </a>
	  	     </div>
	  	     <div style="width: 40px;float:left;">
	  	     	<label class="linkbh14" style="font-size: 14px;padding-top: -10px;"><s:property value="ncount" /></label>
  	     	 </div><br/>
  	   </dd>
  </s:iterator>

  