<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>     
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
$(function(){
	
	$('#holiday').numberspinner({   
	    min: -99999999,   
	    max: 99999999,   
	    editable: true,
	    value:<s:if test="irpUser.holiday==null">0</s:if><s:else>${irpUser.holiday}</s:else>
	});
	

	
	
});
</script>
<form id="rankupdate" method="post">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cad9ea">
<input type="text" name="irpUser.userid" class="easyui-validatebox" value="${irpUser.userid}" style="display:none">
  <tr>
   <td bgcolor="#F5FAFE" class="main_bright" width="40%">用户名</td>
   <td bgcolor="#F5FAFE" class="main_bright" width="60%"><input type="text" name="" id="" disabled="disabled" class="easyui-validatebox" value="${ irpUser.username}"></td>
  </tr>
  <tr>
   <td bgcolor="#F5FAFE" class="main_bright" width="40%">剩余年假天数</td>
   <td bgcolor="#F5FAFE" class="main_bright" width="60%"><input type="text"  name="irpUser.holiday" id="holiday" style="width:80px;"></td>
  </tr>
  
</table>
</form>
</body>
</html>