<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<form id="dialogPageForm" >
	<s:hidden name="pageNum" id="pageNum" />
	<s:hidden name="pageSize" id="pageSize" />
	<s:hidden name="searchType" id="searchType" />
	<s:hidden name="userids" id="userids" />
	<s:hidden name="cruserid" id="cruserid" />
	<s:hidden name="groupId" id="groupId" />   
</form>
<s:hidden name="searchWord" id="searchWord" />
<div style="padding:5px; text-align: center;">
<div style="padding:0px 0px 25px 0px; ">
<div style="padding:0px 0px 5px 0px; float: right;border:1px solid #9ccaf0;height: 15px;">
	<select class="searchbox " style="width: 80px; height: 20px;border: none;background-color: #9ccaf0;" id="sType">
      <option value="all">全部</option>
      <option value="username">用户名</option>
      <option value="truename">真实姓名</option>
  </select>
<input id="sWord" searchboxname="sWord" style="height:20px; right;font-size: 12px;color: #ccc;"  value="请输入检索词" onfocus="this.value=''" onblur="if(this.value==''){this.value='请输入检索词'}" >
<a href="javascript:void(0)" onclick="searchbox()"><span class="searchbox-button" style="height: 20px;"></span></a>
</div>
</div>
<table id="importUserList"  border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea" style="width:500px;">
    <tr>
	    <td width="10%" align="center" bgcolor="#F5FAFE" class="main_bright"> </td>
	    <td width="30%" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('username','<s:if test="orderField=='username'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">用户名<s:if test="orderField=='username'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='username'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="30%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('truename','<s:if test="orderField=='truename'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">真实姓名<s:if test="orderField=='truename'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='truename'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="30%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('crtime','<s:if test="orderField=='crtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">注册时间<s:if test="orderField=='crtime'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='crtime'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
    </tr>
    
    <s:iterator value="irpUsers" status="listStat">
    <tr>
	    <td align="center" bgcolor="#F5FAFE"  class="main_bleft">
	    <input type="radio" name="leave" id="leave<s:property value='userid' />"  value="<s:property value="username" />" onclick="checkClick(<s:property value='userid' />)" <s:if test="userid==userids">checked="checked"</s:if>/> <label  for="<s:property value="userid" />"><s:property value="(pageNum-1)*pageSize+#listStat.count"/></label></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="username" /></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="truename" /></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:date name="crtime" format="yyyy-MM-dd HH:mm" /></td>
    </tr>
    </s:iterator>
    <tr bgcolor="#FFFFFF">
		<td colspan="4" align="right"><div class="clientpage">
			<ul>
				<s:property value="pageHTML" escapeHtml="false" />
			</ul>
		</div></td>
	</tr>
</table>
</div>