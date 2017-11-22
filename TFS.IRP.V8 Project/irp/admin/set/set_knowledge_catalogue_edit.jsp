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
        <s:if test="irpconfig.modified"></s:if>
         <td bgcolor="#F5FAFE" class="main_bright">名称 </td>
         <td bgcolor="#F5FAFE" class="main_bright"><input <s:if test="irpconfig.modified==1">disabled="true"</s:if>   name="irpconfig.ckey" class="easyui-validatebox" panelWidth="200px" validType="configckeyExit[2,30]" required='true' missingMessage='请填写存放目录的名称' value="<s:property value="irpconfig.ckey"/>"></td>
        </tr>
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">内容</td>
         <td bgcolor="#F5FAFE" class="main_bright"><textarea <s:if test="irpconfig.modified==1">disabled="true"</s:if> name="irpconfig.cvalue" class="easyui-validatebox" panelWidth="200px" validType="length[2,300]" required='true' missingMessage='请填写存放目录的内容' ><s:property value="irpconfig.cvalue" /></textarea></td>
        </tr>
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">说明</td>
         <td bgcolor="#F5FAFE" class="main_bright"><textarea <s:if test="irpconfig.modified==1">disabled="true"</s:if> name="irpconfig.cdesc" class="easyui-validatebox" panelWidth="200px" validType="length[2,600]" required='true' missingMessage='请填写存放目录的说明' ><s:property value="irpconfig.cdesc" /></textarea></td>
        </tr>
       
        <tr style="display: none;">
         <td bgcolor="#F5FAFE" class="main_bright">是否加密</td>
           <td bgcolor="#F5FAFE" class="main_bright">
             <select name="irpconfig.encrypted" class="easyui-combobox" style="width: 50px;" editable='false' panelHeight='70'>
              <option value="0" >否</option>
              <option value="1" >是</option>
             </select>
           </td>
        </tr>
       
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">是否可修改</td>
          <td bgcolor="#F5FAFE" class="main_bright">
           <select id="modified" name="irpconfig.modified">
           <option value="0" <s:if test="irpconfig.modified==0">selected="true"</s:if>>是</option>
              <option value="1" <s:if test="irpconfig.modified==1">selected="true"</s:if>>否</option>
              
             </select>
          </td>
        </tr>
     
        <tr style="display: none;">
         <td bgcolor="#F5FAFE" class="main_bright">所属站点</td>
         <td bgcolor="#F5FAFE" class="main_bright"><input name="irpconfig.siteid" class="easyui-validatebox" style="width: 50px;" value="0" disabled='true'></td>
        </tr>
     
      </table>
    </form>
</body>
</html>