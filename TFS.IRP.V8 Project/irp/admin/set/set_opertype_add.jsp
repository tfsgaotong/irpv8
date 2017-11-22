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
$.extend($.fn.validatebox.defaults.rules, {   
    opertypeExit: {   
        validator: function(value,param){   
            if(value.length<param[0] || value.length>param[1] ){
            	$.fn.validatebox.defaults.rules.opertypeExit.message=$.fn.validatebox.defaults.rules.length.message;
            	return false;
            }else{
            var result=	$.ajax({
            	   type:"get",
            		url:"<%=rootPath%>set/findopernames.action?ck_opertypetwo=${irpOpertype.opertype}&ck_opertype="+value,
  					  async: false,
	            	  cache : false
            	}).responseText;
            if(result=='false'){
        		$.fn.validatebox.defaults.rules.opertypeExit.message = '您输入的操作类型已存在';
				return false;
        	}else{
        		return true;
        	}
            }
        },   
        message: ''  
    }   
}); 

</script>
  <form id="opertypeInsert" method="post">
     <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cad9ea">
      <tr> 
       <td bgcolor="#F5FAFE" class="main_bright">操作类型</td>
       <td bgcolor="#F5FAFE" class="main_bright"><input type="text" <s:if test="irpOpertype.modified==0">disabled="true"</s:if> name="irpOpertype.opertype" id="_opertype"  class="easyui-validatebox" validType="opertypeExit[2,50]"  required="true" missingMessage='请输入操作类型' value="<s:property value="irpOpertype.opertype" />"  ></td>
      </tr>
      <tr> 
       <td bgcolor="#F5FAFE" class="main_bright">操作名称</td>
       <td bgcolor="#F5FAFE" class="main_bright"><input type="text" <s:if test="irpOpertype.modified==0">disabled="true"</s:if> name="irpOpertype.opername" id="_opername" class="easyui-validatebox" validType="length[2,50]" required="true" missingMessage="请输入操作名称" value="<s:property value="irpOpertype.opername" />" ></td>
      </tr>
      <tr>
       <td bgcolor="#F5FAFE" class="main_bright">操作描述</td>
       <td bgcolor="#F5FAFE" class="main_bright"><input type="text" <s:if test="irpOpertype.modified==0">disabled="true"</s:if> name="irpOpertype.operdesc" id="_operdesc" class="easyui-validatebox" validType="length[0,300]" value="<s:property value="irpOpertype.operdesc" />"></td>
      </tr>
        <tr>
       <td bgcolor="#F5FAFE" class="main_bright">是否可修改</td>
       <td bgcolor="#F5FAFE" class="main_bright">
        <select name="irpOpertype.modified" id="_operdesc" class="easyui-combobox" editable="false" panelHeight="70px" panelWidth="50px" width="70px">
         <option value="1" <s:if test="irpOpertype.modified==1">selected="true"</s:if> >是</option>
         <option value="0" <s:if test="irpOpertype.modified==0">selected="true"</s:if>>否</option>
        </select>
      </tr>
     </table>
  </form>
</body>
</html>