<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <body>
    <form id="updatetypefrm" action="" method="post"> 
    <input type="hidden" name="attachedType.typeid" value="<s:property value='attachedType.typeid'/>">
    	<table style="margin-top: 5px;" width="100%"  border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
    		<tr>
    			<td width="30%" align="center" bgcolor="#f5fafe" class="main_bleft">类型名称：</td>
    			<td width="70%"   bgcolor="#f5fafe" class="main_bleft">
					<input  name="docstatus.sname"  value="<s:property  value='attachedType.typename'/>" class="easyui-validatebox"    missingMessage="请填写类型名称">
				</td>
    		</tr> 
    		<tr>
    			 <td width="30%" align="center" bgcolor="#f5fafe" class="main_bleft">文件后缀：</td>
    			<td width="70%"   bgcolor="#f5fafe" class="main_bleft">
					<textarea rows="4" cols="17" name="attachedType.suffix"  style=" font-size: 12px; "><s:property value="attachedType.suffix"/></textarea>
				</td>
    		</tr> 
    	</table>
    </form>
  </body>
</html>
