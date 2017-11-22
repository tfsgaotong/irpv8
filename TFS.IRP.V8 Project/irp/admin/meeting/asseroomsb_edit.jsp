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
    <form id="editsb" method="post">
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cad9ea">
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">设备名称 </td>
         <td bgcolor="#F5FAFE" class="main_bright"><input  name="asseroomsb.asseroomsbname" class="easyui-validatebox" panelWidth="200px" validType="length[2,30]" required='true' missingMessage='请填写设备的名称' value="<s:property value="asseroomsb.asseroomsbname"/>"></td>
        </tr>
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">设备描述</td>
         <td bgcolor="#F5FAFE" class="main_bright"><textarea name="asseroomsb.asseroomsbdesc" class="easyui-validatebox" panelWidth="200px" validType="length[2,600]" ><s:property value="asseroomsb.asseroomsbdesc" /></textarea></td>
        </tr>
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">设备用途</td>
         <td bgcolor="#F5FAFE" class="main_bright"><textarea name="asseroomsb.asseroomsbuse" class="easyui-validatebox" panelWidth="200px" validType="length[2,300]" required='true' missingMessage='请填写设备的用途' ><s:property value="asseroomsb.asseroomsbuse" /></textarea></td>
        </tr>
         <tr>
         <td bgcolor="#F5FAFE" class="main_bright">设备型号 </td>
         <td bgcolor="#F5FAFE" class="main_bright"><input  name="asseroomsb.asseroomsbtype" class="easyui-validatebox" panelWidth="200px" validType="length[2,30]" required='true' missingMessage='请填写设备的型号' value="<s:property value="asseroomsb.asseroomsbtype"/>"></td>
        </tr>
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">是否可用</td>
          <td bgcolor="#F5FAFE" class="main_bright">
           <select  name="asseroomsb.asseroomsbstatus">
           <option value="1" <s:if test="asseroomsb.asseroomsbstatus==1">selected</s:if>>是</option>
           <option value="0" <s:if test="asseroomsb.asseroomsbstatus==0">selected</s:if>>否</option>
             </select>
          </td>
        </tr>
      </table>
    </form>
</body>
</html>