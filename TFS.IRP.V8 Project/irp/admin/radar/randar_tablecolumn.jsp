<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@ page import="com.tfs.irp.util.LoginUtil"%>
<%@ page import="com.tfs.irp.user.entity.IrpUser"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	IrpUser loginUser = LoginUtil.getLoginUser();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head> 
    <title><s:property value="irps.doctitle"/> </title>  


<style type="text/css">
/*文章分页*/
#page_break {

}
#page_break .collapse {
display: none;
}
#page_break .num {
	padding: 10px 0;
	text-align: center;
}
#page_break .num li{
	display: inline;
	margin: 0 2px;
	padding: 3px 5px;
	border: 1px solid #72BBE6;
	background-color: #fff;
	
	color: #72BBE6;
	text-align: center;
	cursor: pointer;
	font-family: Arial;
	font-size: 12px;
	overflow: hidden;
}
#page_break .num li.on{
	background-color: #72BBE6;
	color: #fff;
	font-weight: bold;
} 
 
</style> 
<link href="<%=rootPath%>/client/css/css.css" rel="stylesheet" type="text/css" /> 
<link href="<%=rootPath%>/client/css/oacss.css" rel="stylesheet" type="text/css" /> 
<link href="<%=rootPath %>client/images/css.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath%>/client/js/skins/iblue.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=rootPath%>/client/js/jquery-1.8.0.min.js"></script>
</head> 
 <body>
 <script type="text/javascript">

</script> 
 <form id="dataForm" method="post">
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cad9ea">
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">源栏位</td>
         <td bgcolor="#F5FAFE" class="main_bright">目标位</td>
        </tr>
        <s:iterator var="i" value="tableListResoure">
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright"><s:property value="#i.columnName"/></td>
         <td bgcolor="#F5FAFE" class="main_bright">
           <select  name="columnString">
            <option value="null" selected="selected"></option>
            
           <s:iterator var="j" value="tableListDestin">
           <option value="<s:property value="#j.columnName"/>"  <s:if test="#i.columnName==#j.columnName">selected="selected"</s:if>><s:property value="#j.columnName"/></option>
           </s:iterator>
           </select>
          </td>
          </tr>
        </s:iterator>
        
      
      </table>
    </form>
</body>
</html>
 