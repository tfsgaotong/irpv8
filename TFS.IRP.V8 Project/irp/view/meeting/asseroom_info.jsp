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
<style type="text/css">
#s{
color:black;
overflow: auto;
margin: 5px;
}
.ui_content{
color:white;
}
table{
color:black;
}
.ui_title {cursor: move;background-color: aliceblue;}.ui_state_focus .ui_title {color: #336699;}.ui_title {height: 8px;line-height: 2px;font-size: 14px;font-weight: bold;color: #888;border-bottom: 1px solid #DDD\9;background: none;padding-left: 6px;padding-top: -5px;}.ui_title {overflow: hidden;text-overflow: ellipsis;white-space: nowrap;display: block;cursor: move;background: #DDD;-moz-user-select: none;-webkit-user-select: none;padding: 0 100px 0 0;}.ui_content, .ui_title, .ui_buttons input {font: 12px/1.333 tahoma,arial,宋体,sans-serif;}.ui_title {display: block;font-weight: bold;height: 22px;line-height: 22px;line-height: 24px\9;color: #FFF;background: none;font-size: 14px;padding-left: 7px;}
</style>
</head>

<body>
<div id="s">
    <form id="sbid" method="post">
      <table width="400" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cad9ea">
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright" width="70">会议室名称</td>
         <td bgcolor="#F5FAFE" class="main_bright">
         <s:property value="asseroom.asseroomname"/>
         </td>
        </tr>
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">会议室描述</td>
         <td bgcolor="#F5FAFE" class="main_bright">
         <s:property value="asseroom.asseroomdesc" />
         </td>
        </tr>
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">会议室状态</td>
         <td bgcolor="#F5FAFE" class="main_bright">
         <s:if test="asseroom.asseroomstatus==1">是</s:if>
         <s:if test="asseroom.asseroomstatus==0">否</s:if>
          </td>
        </tr>
         <tr>
         <td bgcolor="#F5FAFE" class="main_bright">会议室地址 </td>
         <td bgcolor="#F5FAFE" class="main_bright">
         <s:property value="asseroom.asseroomaddr"/>
         </td>
        </tr>
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">容纳人数 </td>
         <td bgcolor="#F5FAFE" class="main_bright">
			<s:property value="asseroom.asseroompeoples"/>
			<input type="hidden" name="people" value='<s:property value="asseroom.asseroompeoples"/>'/>
         </td>
        </tr>
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">会议室设备</td>
          <td bgcolor="#F5FAFE" class="main_bright">
	          <s:iterator value="asseroomsbList" status="sbListstatus">
		           <input name="asseroomsbs" type="checkbox" <s:if test="asseroomsbstatus==3">checked="checked"</s:if>value="<s:property value='asseroomsbid'/>"/><s:property value='asseroomsbname' />&nbsp;&nbsp;
			           <s:if test="#sbListstatus.count==3">
			           	<br>
			           </s:if>
	           </s:iterator>
          </td>
        </tr>
      </table>
    </form>
   </div>
</body>
</html>