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
<style type="text/css">
.tagcss ul li{
display: block;
width: 80px;
float: left;
cursor: hand;
}
</style>
<body>
<ul>
<s:if test="mytags.size<=10">
	<s:iterator value="mytags" status="status">
		<li class="ellipsis">
			<a onclick="addtoText(this)" href="javascript:void(0)" title='<s:property value="mytags[#status.index]"/>'>
				<s:property value="mytags[#status.index]"/>
			</a>
		</li>
	</s:iterator>
</s:if>
<s:elseif test="mytags.size>10">
	<s:iterator value="mytags" status="status" begin="0" end="9">
		<li class="ellipsis">
			<a onclick="addtoText(this)" href="javascript:void(0)" title='<s:property value="mytags[#status.index]"/>'>
				<s:property value="mytags[#status.index]"/>
			</a>
		</li>
	</s:iterator>
	<s:if test="mytags.size<=20">
	<s:iterator value="mytags" var="var" begin="10" >
		<li style="display: none;" class="ellipsis">
			<a onclick="addtoText(this)" href="javascript:void(0)" title='<s:property value="var"/>'>
				<s:property value="var"/>
		   </a>
	   </li>
	</s:iterator>
	</s:if>
	<s:elseif test="mytags.size>20">
		<s:iterator value="mytags" var="var" begin="10" end="20">
			<li style="display: none;" class="ellipsis">
				<a onclick="addtoText(this)" href="javascript:void(0)" title='<s:property value="var"/>'>
					<s:property value="var"/>
			   </a>
		   </li>
		</s:iterator>
	</s:elseif>
	<li style="width: 30px;"><a href="javascript:void(0)" onclick="toMoretag(this)" class="newabtngrn">更&nbsp;&nbsp;&nbsp;&nbsp;多</a></li>
</s:elseif>
</ul>
</body>
</html>