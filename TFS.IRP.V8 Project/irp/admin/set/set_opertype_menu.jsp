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
<script type="text/javascript">
function jump(_sUrl){
	$('body').layout('panel','center').panel('refresh',_sUrl);
}
/*
 * 全局设置
 */
function opertypeConf(_ctypeId){
	switch (_ctypeId) {
	/*知识相关配置*/
	case 1:
		jump('<%=rootPath  %>admin/set/set_knowledge.jsp');
		break;
	/*系统检索配置*/	
	case 9:
		jump('<%=rootPath %>admin/set/set_search_menu.jsp');
		break;
	/*操作类型配置*/
    case 13:
		jump('<%=rootPath  %>set/opertype_set_list.action?pageNum=1&orderField&searchWord&searchType');
		break;
	/*用户贡献配置*/	
    case 15:
    	jump('<%=rootPath  %>admin/set/set_contribute.jsp');
    	break;
	/*系统其它设置*/
    case 17:
    	jump('<%=rootPath  %>admin/set/set_front.jsp');
    	break;
    	/*系统邮箱配置*/
    case 5:
    	jump('<%=rootPath  %>admin/set/set_email_menu.jsp');
    	break;
    case 8:
    	jump('<%=rootPath %>set/job_set_list.action');
    	break;	
    default:
    	break;
	}
	
}


</script>
<div id="menu" class="easyui-accordion" fit="true" border="false">
	<div title="全局设置" style="padding:0px;" selected="true" class="arrowsidemenu">
	<ul class="menucontents">
	 <s:iterator value="irpConfigType">
	 <li><a href="javascript:void(0)" onclick="opertypeConf(<s:property value="configtypeid" />)"><s:property value="configname" /></a></li>
	 </s:iterator>
	</ul>
	</div>
</body>
</html>