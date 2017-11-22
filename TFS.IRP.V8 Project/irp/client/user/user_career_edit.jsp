<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<form id="careerEditFrom" method="post" onsubmit="return false;">
<s:hidden name="irpCareer.careerid" />
<table border="0" cellpadding="0" cellspacing="10">
	<tr>
		<td align="right">所在地：</td>
		<td>
			<select name="irpCareer.province" id="province"></select> 
	    	<select name="irpCareer.city" id="city"></select>
	    	<select name="irpCareer.area" id="area"></select>
	    	<script type="text/javascript">
	    		addressInit('province', 'city', 'area', '<s:property value="irpCareer.province" escapeHtml="false" />', '<s:property value="irpCareer.city" escapeHtml="false" />', '<s:property value="irpCareer.area" escapeHtml="false" />');
			</script>
	    </td>
	</tr>
	<tr>
		<td align="right">工作时间：</td>
		<td><select name="irpCareer.startyear" id="startyear">
			<s:iterator value="years" var="year">
			<option value="<s:property value="year" />" <s:if test="#year==irpCareer.startyear">selected="selected"</s:if>><s:property value="year" /></option>
			</s:iterator>
		</select>&nbsp;至&nbsp;<select name="irpCareer.endyear" validType="year['#careerEditFrom [id=startyear]']">
			<option value="9999" <s:if test="irpCareer.endyear==9999">selected="selected"</s:if>>至今</option>
			<s:iterator value="years" var="year">
			<option value="<s:property value="year" />" <s:elseif test="irpCareer.endyear==#year">selected="selected"</s:elseif>><s:property value="year" /></option>
			</s:iterator>
		</select></td>
	</tr>
	<tr>
		<td align="right">单位名称：</td>
		<td><input type="text" name="irpCareer.companyname" value="<s:property value="irpCareer.companyname" />" required="true" validType="length[2,60]" missingMessage="请填写单位名称" /></td>
	</tr>
	<tr>
		<td align="right">部门/职位：</td>
		<td><input type="text" name="irpCareer.department" value="<s:property value="irpCareer.department" />" validType="maxLength[60]" /></td>
	</tr>
	<tr>
		<td colspan="2">
			<a href="javascript:void(0);" onclick="editCareer()" class="zhuanz1">修改</a>
			<a href="javascript:void(0);" onclick="cancelEdit(<s:property value="irpCareer.careerid" />)" class="zhuanz1">取消</a>
		</td>
	</tr>
</table>
</form>