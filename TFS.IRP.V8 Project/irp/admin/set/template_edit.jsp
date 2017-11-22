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
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>


</head>
<body>
 <script type="text/javascript">
 
		
 </script>
 <form id="editinformtype" method="post" >
      <table  width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cad9ea">
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">内容</td>
         <td bgcolor="#F5FAFE" class="main_bright"><textarea name="irptemplate.tvalue" class="easyui-validatebox" panelWidth="200px"  required='true' required='true' missingMessage='请填写模版内容' style="height: 200px;width: 80%;" ><s:property value="irptemplate.tvalue" /></textarea></td>
        </tr>
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">描述</td>
         <td bgcolor="#F5FAFE" class="main_bright"><textarea name="irptemplate.tvaluedesc" class="easyui-validatebox" panelWidth="200px" style="width: 80%;"  validType="length[0,600];" ><s:property value="irptemplate.tvaluedesc" /></textarea></td>
        </tr>
      </table>
 </form>
</body>
</html>