<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
  <body> 
  <script type="text/javascript"> 

	
	
	
</script> 
  	<form id="addprojectfrm" action="project/addproject.action" method="post">
 		<input type="hidden" name="irpProject.projectid"  value="<s:property value='irpProject.projectid'/>"/>
	  	项目名称：<font color="red">*</font>&nbsp;最多不超过20个字
	  	<input type="text" style="background-color: rgb(246, 246, 246); line-height:18px; width: 385px;margin-left:4px;"  class="easyui-validatebox txt_tclwbia" validType="length[0,20]"   required="true" id="prnameinput" name="irpProject.prname" value="<s:property value='irpProject.prname'/>"/>
	  	<br/>
		项目描述：   不超过100个字  <Br/>
	<textarea style="background-color: rgb(246, 246, 246); width: 390px;"  required="true" id="prdescinput" name="irpProject.prdesc"   rows="" cols="6" class="easyui-validatebox description_clwbi" validType="length[0,100]"   ><s:property value="irpProject.prdesc"/></textarea>
	
	<br/> 
	 </form>
  </body>
</html>
