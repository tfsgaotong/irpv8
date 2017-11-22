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
  $(function(){
	 $('#modified').combobox({
		 editable:false,
		 panelHeight:70,
		 panelWidth:50
	 });
  });
  
  $.extend($.fn.validatebox.defaults.rules, {   
	    configckeyExit: {   
	        validator: function(value,param){   
	            if(value.length<param[0] || value.length>param[1] ){
	            	$.fn.validatebox.defaults.rules.configckeyExit.message=$.fn.validatebox.defaults.rules.length.message;
	            	return false;
	            }else{
	            var result=	$.ajax({
	            	   type:"get",
	            		url:"<%=rootPath%>set/checkckey.action?ck_ckeytwo=${irpconfig.ckey}&ck_ckey="+value,
	  					  async: false,
		            	  cache : false
	            	}).responseText;
	            if(result=='false'){
	        		$.fn.validatebox.defaults.rules.configckeyExit.message = '您输入的存放目录类型已存在';
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
    <form id="editconfig" method="post">
    
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cad9ea">
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">名称 </td>
         <td bgcolor="#F5FAFE" class="main_bright"><input name="irpLeaveconfig.leaveconfigname" class="easyui-validatebox" panelWidth="200px" validType="length[2,600]" required='true' missingMessage='请填写要保存的名称' value="<s:property value="irpLeaveconfig.leaveconfigname"/>"></td>
        </tr>
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">说明</td>
         <td bgcolor="#F5FAFE" class="main_bright"><textarea  name="irpLeaveconfig.leaveconfigdesc" class="easyui-validatebox" panelWidth="200px" validType="length[2,600]" required='true' missingMessage='请填写要保存的说明' ><s:property value="irpLeaveconfig.leaveconfigdesc" /></textarea></td>
        </tr>
         <tr>
         <td bgcolor="#F5FAFE" class="main_bright">状态</td>
         <td bgcolor="#F5FAFE" class="main_bright">
         <select name="irpLeaveconfig.applystatus">
         <option value="10" <s:if test="irpLeaveconfig.applystatus==10">selected</s:if>>正常</option>
         <option value="20" <s:if test="irpLeaveconfig.applystatus==20">selected</s:if>>非正常</option>
         <option></option>
         </select>
         </td>
        </tr>     
      </table>
    </form>
</body>
</html>