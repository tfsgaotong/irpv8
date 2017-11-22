<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>   
  <script type="text/javascript"> 
   $(function(){
      $('#checkchannelul').tree({
      	url:'<%=rootPath%>site/to_load_channeltoshow.action'   
      });
   }); 
  </script>  
  <div style="width:300px;"> 
    <ul id="checkchannelul"></ul> 
  </div> 