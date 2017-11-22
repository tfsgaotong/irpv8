<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript">
	
	</script>
	<title>相关信息</title>
  </head>
  
  <body>
	<div>
		<ul>
			<s:iterator value="termcatelist" status="termcateliststatus" >
			
			<li onclick="linkVerContent(<s:property value="termid" />)" title="<s:property value="termname" />" style="white-space: nowrap;overflow: hidden;text-overflow: ellipsis;cursor:pointer;background-color:<s:if test="#termcateliststatus.index%4==0">#DEFFAC</s:if><s:elseif test="#termcateliststatus.index%4==1">#DDDDFF</s:elseif><s:elseif test="#termcateliststatus.index%4==2">#C4E1FF</s:elseif><s:else>#D6D6AD</s:else>; padding :3px 0 2px 0; text-align:center; float:left; width: 33%;"><s:property value="termname" /></li>
			</s:iterator>
		</ul>
  	</div>			
  </body>
</html>
