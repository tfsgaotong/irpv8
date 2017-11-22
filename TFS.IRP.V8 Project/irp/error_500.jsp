<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>500错误提示</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<STYLE type=text/css>
body {
	font-size: 9pt; color: #842b00; line-height: 16pt; font-family: "tahoma", "宋体"; text-decoration: none
}
table {
	font-size: 9pt; color: #842b00; line-height: 16pt; font-family: "tahoma", "宋体"; text-decoration: none
}
td {
	font-size: 9pt; color: #842b00; line-height: 16pt; font-family: "tahoma", "宋体"; text-decoration: none
}
body {
	scrollbar-highlight-color: buttonface; scrollbar-shadow-color: buttonface; scrollbar-3dlight-color: buttonhighlight; scrollbar-track-color: #eeeeee; background-color: #ffffff
}
a {
	font-size: 9pt; color: #842b00; line-height: 16pt; font-family: "tahoma", "宋体"; text-decoration: none
}
a:hover {
	font-size: 9pt; color: #0188d2; line-height: 16pt; font-family: "tahoma", "宋体"; text-decoration: underline
}
h1 {
	font-size: 9pt; font-family: "tahoma", "宋体"
}
</style>
</head>

<body>
	<table width="600" align="center" border="0" cellspacing="0" cellpadding="0">
		<tbody>
			<tr>
				<td>
					<h1><!-- 无法找到该页 -->
					
					<%
						String l="错误提示: ";
						l=l + exception; 
					%>
					<%=l %>
					
					</h1> HTTP 错误500。
					<hr noshade size=0>
					<p>☉ 请尝试以下操作：</p>
					<ul>
						<li>确保浏览器的地址栏中显示的网站地址的拼写和格式正确无误。
						<li>如果通过单击链接而到达了该网页，请与网站管理员联系，通知他们该链接的格式不正确。
						<li>单击<a href="javascript:history.back(1)"><font color=#ff0000>后退</font></a>按钮尝试另一个链接。
						</li>
					</ul>
					<p>
						<a href="<%=rootPath%>">返回首页</a>
					<hr noshade size=0>
					<p>
						☉ 如果您对本站有任何疑问、意见、建议、咨询，请联系管理员 <br> &nbsp;&nbsp;&nbsp;<br>
					</p>
					<p>
					
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>
