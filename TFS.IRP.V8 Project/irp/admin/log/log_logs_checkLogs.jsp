<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
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
      <td bgcolor="#F5FAFE" class="main_bright" width="23%">日志类型</td>
      <s:if test="irpLogs.logtype==1"><td bgcolor="#F5FAFE" class="main_bright">错误</td></s:if>
      <s:if test="irpLogs.logtype==2"><td bgcolor="#F5FAFE" class="main_bright">警告</td></s:if>
      <s:if test="irpLogs.logtype==3"><td bgcolor="#F5FAFE" class="main_bright">信息</td></s:if>
      <s:if test="irpLogs.logtype==4"><td bgcolor="#F5FAFE" class="main_bright">调试</td></s:if>
      
    </tr>
      <tr>
      <td bgcolor="#F5FAFE" class="main_bright" width="23%">操作对象</td>
      <td bgcolor="#F5FAFE" class="main_bright"><s:property value="irpLogs.logobjname" /></td>
    </tr>
      <tr>
      <td bgcolor="#F5FAFE" class="main_bright" width="23%">操作类型</td>
      <td bgcolor="#F5FAFE" class="main_bright"><s:property value="irpLogs.logoptype" /></td>
    </tr>
        <tr>
      <td bgcolor="#F5FAFE" class="main_bright" width="23%">操作用户</td>
      <td bgcolor="#F5FAFE" class="main_bright"><s:property value="irpLogs.loguser" /></td>
    </tr>
        <tr>
      <td bgcolor="#F5FAFE" class="main_bright" width="23%">操作用户ip</td>
      <td bgcolor="#F5FAFE" class="main_bright"><s:property value="irpLogs.loguserip" /></td>
    </tr>
        <tr>
      <td bgcolor="#F5FAFE" class="main_bright" width="23%">操作时间</td>
      <td bgcolor="#F5FAFE" class="main_bright"><s:date name="irpLogs.logoptime"/> </td>
    </tr>
        <tr>
      <td bgcolor="#F5FAFE" class="main_bright" width="23%">操作耗时</td>
      <td bgcolor="#F5FAFE" class="main_bright"><s:property value="irpLogs.exectime" />ms</td>
    </tr>
        <tr>
      <td bgcolor="#F5FAFE" class="main_bright" width="23%">操作结果</td>
      <s:if test="irpLogs.logresult==1"><td bgcolor="#F5FAFE" class="main_bright">成功</td></s:if>
      <s:if test="irpLogs.logresult==2"><td bgcolor="#F5FAFE" class="main_bright">失败</td></s:if>
    </tr>
        <tr style="display: none;">
      <td bgcolor="#F5FAFE" class="main_bright" width="23%">是否存在篡改</td>
      <td bgcolor="#F5FAFE" class="main_bright"></td>
    </tr>
        <tr>
      <td bgcolor="#F5FAFE" class="main_bright" width="23%">详细描述</td>
      <td bgcolor="#F5FAFE" class="main_bright"><s:property value="irpLogs.loginfo" /></td>
    </tr>
  </table>

</body>
</html>