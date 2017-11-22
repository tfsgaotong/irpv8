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
<title>导出列表</title>
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
 <form id="editinformtype" method="post">
 <input type="hidden" value="60" name="irpInformType.informtype" /> 
 <input type="hidden" id="infokey" value='<s:property value="irpInformType.informkey"/>'/>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cad9ea">
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">名称 </td>
         <td bgcolor="#F5FAFE" class="main_bright"><input  name="irpInformType.informkey" class="easyui-validatebox" panelWidth="200px" validType="checkName[2,50]" required='true' missingMessage='请填写配置的名称' value="<s:property value="irpInformType.informkey"/>"></td>
        </tr>
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">内容</td>
         <td bgcolor="#F5FAFE" class="main_bright"><textarea name="irpInformType.informvalue" class="easyui-validatebox" panelWidth="200px" validType="length[1,300]" required='true' missingMessage='请填写配置的名称' ><s:property value="irpInformType.informvalue" /></textarea></td>
        </tr>
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">说明</td>
         <td bgcolor="#F5FAFE" class="main_bright"><textarea name="irpInformType.informdesc" class="easyui-validatebox" panelWidth="200px"  ><s:property value="irpInformType.informdesc" /></textarea></td>
        </tr>
       
        <tr>
        </tr>
  
      </table>
    </form>
</body>
</html>