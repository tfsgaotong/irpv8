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
      <td bgcolor="#F5FAFE" class="main_bright" width="23%">表单名称：</td>
      <td bgcolor="#F5FAFE" class="main_bright"><s:property value='irpForm.formname'/></td>  
      
    </tr>
    <tr>
      <td bgcolor="#F5FAFE" class="main_bright" width="23%">表单描述：</td>
      <td bgcolor="#F5FAFE" class="main_bright"><s:property value='irpForm.formdesc'/></td>  
      
    </tr>
      <tr>
      <td bgcolor="#F5FAFE" class="main_bright" width="23%">数据库表名称：</td>
      <td bgcolor="#F5FAFE" class="main_bright"><s:property value="irpForm.formtablename" /></td>
    </tr>
      <tr>
      <td bgcolor="#F5FAFE" class="main_bright" width="23%">表单状态</td>
      <td bgcolor="#F5FAFE" class="main_bright"><s:if test="irpForm.formisdel==10">
			    正常
	    </s:if>
	    <s:else>
			    回收站
	    </s:else></td>
    </tr>
      <tr>
      <td bgcolor="#F5FAFE" class="main_bright" width="23%">发布状态</td>
      <td bgcolor="#F5FAFE" class="main_bright"> <s:if test="irpForm.formstatus==10">
		    未发布
	    </s:if>
	    <s:else>
		    已发布
	    </s:else></td>
    </tr>
        <tr>
      <td bgcolor="#F5FAFE" class="main_bright" width="23%">创建时间:</td>
      <td bgcolor="#F5FAFE" class="main_bright"><s:date name="irpForm.crtime" format="yyyy-MM-dd HH:mm" /></td>
    </tr>
        <tr>
      <td bgcolor="#F5FAFE" class="main_bright" width="23%">创建人:</td>
      <td bgcolor="#F5FAFE" class="main_bright"> <s:property value="@com.tfs.irp.util.LoginUtil@findUserById(irpForm.cruserid).getShowName()" /></td>
    </tr>
        
     <table width="100%" id="roleList" border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
	<%-- <tr  style="position: relative;">
   	  	<td colspan="8" align="left" style="padding-left: 10px;" >
   	  		
   	  		<a href="javascript:void(0)" >该表单包含的字段</a>
   	  	
   	  		
   	  	</td>
   	  
	</tr>
    <tr>
	    <td width="16%" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="checkAll()">全选</a></td>
	    <td width="13%" align="center" bgcolor="#F5FAFE" class="main_bright">字段显示名称</td>
	    <td width="13%" align="center" bgcolor="#F5FAFE" class="main_bright">字段名</td>
	    <td width="15%" align="center" bgcolor="#F5FAFE" class="main_bright">字段描述</td>
	    <td width="13%" align="center" bgcolor="#F5FAFE" class="main_bright">字段类型</td>
	    <td width="13%" align="center" bgcolor="#F5FAFE" class="main_bright">字段长度</td>
	    <td width="13%" align="center" bgcolor="#F5FAFE" class="main_bright">默认值</td>
	  
	    
    </tr>
    <s:iterator value="irpFormColumns" status="listStat">
    <tr>
	    <td align="center" bgcolor="#F5FAFE"  class="main_bleft"><input type="checkbox" name="columnid" value="<s:property value="columnid" />" /> <s:property value="(pageNum-1)*pageSize+#listStat.count"/></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="columnname" /></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="columntablenamecol" /></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="columndesc" /></td>
	    <td bgcolor="#F5FAFE"  class="main_bright">
	    <s:if test="columntype in inttype">整型</s:if>
	    <s:if test="columntype in stringtype">字符串</s:if>
	    <s:if test="columntype in datetype">时间</s:if>
	    
	    </td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="columnlongtext" /></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="columndefval" /></td>
	   
	
	   
    </tr>
    </s:iterator> --%>
   
</table>

    

        
       
  </table>

</body>
</html>