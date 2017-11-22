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
	 $('#informkind').combobox({
		 editable:false,
		 panelHeight:70,
		 panelWidth:50
	 });
	
	  
  });
  $.extend($.fn.validatebox.defaults.rules, {   
	  checkName: { 
          validator: function(value, param){
          if(value.length<param[0] || value.length>param[1]){
          	$.fn.validatebox.defaults.rules.checkName.message = $.fn.validatebox.defaults.rules.length.message;
          	return false;
          }else{ 
        	    var ck_ckey=$.trim($("#infokey").val());
	            var data = $.ajax({  
                  type: "POST", 
                  async: false,  
                  url:"<%=rootPath %>set/check_offenmenukeyInformtype.action",   
                  data:{"ck_ckey":ck_ckey,
                     	"ck_ckeytwo":value},  
                          dataType:"json"
           	}).responseText;
				if (data == 'false') {
					$.fn.validatebox.defaults.rules.checkName.message = "输入的唯一标识已存在";
					return false;
				} else {
					return true;
				}
			}
		},
		message : ""
	 } 
  }); 
 </script>
 <s:form id="editinformtype" method="post" theme="simple">
 <input type="hidden" id="infokey" name="irpApp.applistid" value='<s:property value="irpApp.applistid"/>'/>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cad9ea">
        <tr>
         	<td bgcolor="#F5FAFE" class="main_bright">唯一名称 </td>
         	<td bgcolor="#F5FAFE" class="main_bright"><input  name="irpApp.applistname" class="easyui-validatebox" panelWidth="200px" validType="checkName[2,50]" required='true' missingMessage='请填写应用的名称' value="<s:property value="irpApp.applistname"/>"></td>
        </tr>
        <tr>
        	 <td bgcolor="#F5FAFE" class="main_bright">显示名称</td>
        	 <td bgcolor="#F5FAFE" class="main_bright"><textarea name="irpApp.applistalias" class="easyui-validatebox" panelWidth="200px" validType="length[1,300]" required='true' missingMessage='请填写应用显示名称' ><s:property value="irpApp.applistalias" /></textarea></td>
        </tr>
        <tr>
	         <td bgcolor="#F5FAFE" class="main_bright">应用描述</td>
	         <td bgcolor="#F5FAFE" class="main_bright"><textarea name="irpApp.description" class="easyui-validatebox" panelWidth="200px"  ><s:property value="irpApp.description" /></textarea></td>
        </tr>
        <tr>
	         <td bgcolor="#F5FAFE" class="main_bright">是否发布</td>
	         <td bgcolor="#F5FAFE" class="main_bright">
	         <s:select list="#{0:'发布',1:'不发布'}"  name="irpApp.status" listKey="key" listValue="value"> </s:select>
        </tr>
	    <tr>
	         <td bgcolor="#F5FAFE" class="main_bright">版本</td>
	         <td bgcolor="#F5FAFE" class="main_bright"><input  name="irpApp.version" class="easyui-validatebox" panelWidth="200px" validType="checkName[2,50]" required='true' value="<s:property value="irpApp.version"/>"></td>
	    </tr>
        <tr>
	         <td bgcolor="#F5FAFE" class="main_bright">应用类型</td>
	         <td bgcolor="#F5FAFE" class="main_bright">
	          <s:select list="irpApptypes" name="irpApp.category" listKey="appname" listValue="appname" headerKey="0" headerValue="--请选择应用类型--"></s:select> </td>
        </tr>
      </table>
    </s:form>
</body>
</html>