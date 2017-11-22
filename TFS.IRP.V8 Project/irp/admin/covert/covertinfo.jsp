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

  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cad9ea" id="Tdetail">
    <tr>
      <td bgcolor="#F5FAFE" class="main_bright" width="23%">兑换商品：</td>
      <td bgcolor="#F5FAFE" class="main_bright"><s:property value='covert.covertgoods'/></td>  
      
    </tr>
    <tr>
      <td bgcolor="#F5FAFE" class="main_bright" width="23%">兑换用户：</td>
      <td bgcolor="#F5FAFE" class="main_bright"><s:property value='covert.covertuser'/></td>  
      
    </tr>
      <tr>
      <td bgcolor="#F5FAFE" class="main_bright" width="23%">兑换数量：</td>
      <td bgcolor="#F5FAFE" class="main_bright"><s:property value="covert.covertnum" /></td>
    </tr>
        <tr>
      <td bgcolor="#F5FAFE" class="main_bright" width="23%">兑换时间:</td>
      <td bgcolor="#F5FAFE" class="main_bright"><s:date name="covert.coverttime" format="yyyy-MM-dd HH:mm" /></td>
    </tr>
        
       
  </table>

</body>
</html>