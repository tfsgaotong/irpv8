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

  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cad9ea" id="Tdetail">
    <tr>
      <td bgcolor="#F5FAFE" class="main_bright" width="23%">请假标题：</td>
      <td bgcolor="#F5FAFE" class="main_bright"><s:property value='irpLeaveapplyInfo.title'/></td>  
      
    </tr>
    <tr>
      <td bgcolor="#F5FAFE" class="main_bright" width="23%">请假类型：</td>
      <td bgcolor="#F5FAFE" class="main_bright"><s:property value='irpLeaveapplyInfo.applytypeid'/></td>  
      
    </tr>
      <tr>
      <td bgcolor="#F5FAFE" class="main_bright" width="23%">请假理由：</td>
      <td bgcolor="#F5FAFE" class="main_bright"><s:property value="irpLeaveapplyInfo.applyreason" /></td>
    </tr>
      <tr>
      <td bgcolor="#F5FAFE" class="main_bright" width="23%">创建时间</td>
      <td bgcolor="#F5FAFE" class="main_bright"><s:date name="irpLeaveapplyInfo.crtime" format="yyyy-MM-dd HH:mm"/></td>
    </tr>
      <tr>
      <td bgcolor="#F5FAFE" class="main_bright" width="23%">请假开始时间</td>
      <td bgcolor="#F5FAFE" class="main_bright"><s:date name="irpLeaveapplyInfo.starttime" format="yyyy-MM-dd HH:mm"/></td>
    </tr>
        <tr>
      <td bgcolor="#F5FAFE" class="main_bright" width="23%">请假结束时间:</td>
      <td bgcolor="#F5FAFE" class="main_bright"><s:date name="irpLeaveapplyInfo.endtime" format="yyyy-MM-dd HH:mm" /></td>
    </tr>
        <tr>
      <td bgcolor="#F5FAFE" class="main_bright" width="23%">审核人:</td>
      <td bgcolor="#F5FAFE" class="main_bright"><s:property value="irpLeaveapplyInfo.checker" /></td>
    </tr>
        <tr>
      <td bgcolor="#F5FAFE" class="main_bright" width="23%">审核进度：</td>
      <td bgcolor="#F5FAFE" class="main_bright"><s:property value="irpLeaveapplyInfo.applystatus"/> </td>
    </tr>
        <tr>
      <td bgcolor="#F5FAFE" class="main_bright" width="23%">紧急程度：</td>
      <td bgcolor="#F5FAFE" class="main_bright"><s:property value="irpLeaveapplyInfo.emergency" /></td>
    </tr>
    
    
     <s:iterator value="attacheds" >
      <tr>
      <td bgcolor="#F5FAFE" class="main_bright" width="23%">相关附件：</td>
      <td bgcolor="#F5FAFE" class="main_bright"><a  href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>"><s:property value="attdesc" /></a>
				&nbsp;&nbsp; 
				<a  href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>">下载</a></td>
    </tr>    

        </s:iterator>
        
       
  </table>

</body>
</html>