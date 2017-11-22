<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <body>  
  
  <form  id="addchannelfrm" method="post">
  <s:iterator value="irpCeshis" status="listStat" var="irp">
 		<input type="hidden" name="sid" value="<s:property value='sid'/>" />
   		<table width="100%"  border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
         <tr>
	        <td width="30%" align="right" bgcolor="#f5fafe" class="main_bleft">标题名称：</td>
	        <td bgcolor="#FFFFFF" class="main_bright">
	        <input value="<s:property value='doctitle'/>"  class="easyui-validatebox"  style="width: 200px;"  validType="checkName[150]" missingMessage="请填写标题" type="text" name="stitle" required="true" />			  
		    </td>
	     </tr> 
	     <tr>
	        <td width="30%" align="right" bgcolor="#f5fafe" class="main_bleft">状态：</td>
	        <td bgcolor="#FFFFFF" class="main_bright">			   
			   <s:if test="satus==1"><input name="status" type="radio" value="1" checked="checked"/>新稿<input name="status" type="radio" value="2"/>已导入</s:if>
			   <s:else><input name="status" type="radio" value="1" />新稿<input name="status" type="radio" value="2" checked="checked"/>已导入</s:else>  
		    </td>
	     </tr>
	    </table> 
	     </s:iterator>
  </form>
  </body>
</html>
