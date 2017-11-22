<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html> 
  <body>
    <form id="adddocstatusfrm" action="" method="post"> 
      <input type="hidden" name="docstatus.statusid" value="<s:property value='docstatus.statusid'/>">
    	<table style="margin-top: 5px;" width="100%"  border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
    		<tr>
    			 <td width="25%" align="right" bgcolor="#f5fafe" class="main_bleft">名称：</td>
    			<td width="25%"   bgcolor="#f5fafe" class="main_bleft">
					<input name="docstatus.sname" value="<s:property  value='docstatus.sname'/>"  class="easyui-validatebox"  invalidMessage="该名称已经被使用" validType="remote['<%=rootPath %>site/checkdocstatusname.action?docstatusid=<s:property value="docstatus.statusid"/>','docstatusname']"  missingMessage="请填写唯一标识别">
				</td>
    		</tr>
    		<tr>
    			 <td width="25%" align="right" bgcolor="#f5fafe" class="main_bleft">说明：</td>
    			<td width="25%"  bgcolor="#f5fafe" class="main_bleft">
				<input name="docstatus.sdesc" value="<s:property value='docstatus.sdesc'/>"  class="easyui-validatebox" missingMessage="请填写说明">
				</td>
    		</tr>
    		<tr>
    			 <td width="25%" align="right" bgcolor="#f5fafe" class="main_bleft">显示：</td>
    			<td width="25%"   bgcolor="#f5fafe" class="main_bleft">
					<textarea rows="2" cols="15" name="docstatus.sdisp"  style=" font-size: 12px; "><s:property value="docstatus.sdisp"/></textarea>
				</td>
    		</tr> 
    		<tr>
    			 <td width="25%" align="right" bgcolor="#f5fafe" class="main_bleft">是否启用：</td>
    			 <td width="25%"   bgcolor="#f5fafe" class="main_bleft">
					<select name="docstatus.sused">
						<option value="0" <s:if test="%{docstatus.sused==0}">selected</s:if>>否</option>
						<option value="1" <s:if test="%{docstatus.sused==1}">selected</s:if>>是</option>
					</select>
				</td>
    		</tr>
    	</table>
    
    </form>
  </body>
</html>
