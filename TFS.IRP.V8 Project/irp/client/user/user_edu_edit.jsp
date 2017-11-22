<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<form id="eduEditFrom" method="post" onsubmit="return false;">
<s:hidden name="irpEducation.educationid" />
<table border="0" cellpadding="0" cellspacing="10">
	<tr>
		<td align="right">学校类型：</td>
		<td><select name="irpEducation.schooltype">
				<s:iterator value="schoolTypeInfos" var="schoolType">
					<option value="<s:property value="key" />" <s:if test="key==irpEducation.schooltype">selected="selected"</s:if>>
						<s:property value="value" />
					</option>
				</s:iterator>
		</select>
		</td>
	</tr>
	<tr>
		<td align="right">入学年份：</td>
		<td><select name="irpEducation.startyear">
				<s:iterator value="startYears" var="year">
					<option value="<s:property value="year" />" <s:if test="#year==irpEducation.startyear">selected="selected"</s:if>>
						<s:property value="year" />
					</option>
				</s:iterator>
		</select>
		</td>
	</tr>
	<tr>
		<td align="right">学校名称：</td>
		<td><input type="text" name="irpEducation.schoolname" value="<s:property value="irpEducation.schoolname" />"
			required="true" validType="length[2,60]"
			missingMessage="请填写学校名称" />
		</td>
	</tr>
	<tr>
		<td align="right">班级/院系：</td>
		<td><input type="text" name="irpEducation.schoolclass" value="<s:property value="irpEducation.schoolclass" />"
			validType="maxLength[60]" />
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<a href="javascript:void(0);" onclick="editEdu()" class="zhuanz1">修改</a>
			<a href="javascript:void(0);" onclick="cancelEdit(<s:property value="irpEducation.educationid" />)" class="zhuanz1">取消</a>
		</td>
	</tr>
</table>
</form>