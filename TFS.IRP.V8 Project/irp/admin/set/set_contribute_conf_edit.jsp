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
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">

$(function(){
	//积分
	$('#valuescore').numberspinner({   
	    min: -99999999,   
	    max: 99999999,   
	    editable: true,
	    value:<s:if test="_irpValueConfig.score==null">0</s:if><s:else>${_irpValueConfig.score}</s:else>
	});
	//经验
	$('#valueexper').numberspinner({   
	    min: -99999999,   
	    max: 99999999,   
	    editable: true,
	    value:<s:if test="_irpValueConfig.experience==null">0</s:if><s:else>${_irpValueConfig.experience}</s:else>
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
            		url:"<%=rootPath%>set/checkValuekey.action?ck_valuekey=${_irpValueConfig.valuekey}&valuekey="+value,
  					  async: false,
	            	  cache : false
            	}).responseText;
            if(result=='false'){
        		$.fn.validatebox.defaults.rules.configckeyExit.message = '您输入的贡献类型已存在';
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
<form id="contributeedit" method="post">
 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cad9ea">
  <tr>
   <td bgcolor="#F5FAFE" class="main_bright">贡献名称</td>
   <td bgcolor="#F5FAFE" class="main_bright"><input type="text" name="_irpValueConfig.valuekey" validType="configckeyExit[2,50]"  class="easyui-validatebox" panelWidth="200px" required='true' missingMessage='请填写贡献名称' value="<s:property value='_irpValueConfig.valuekey' />"></td>
  </tr>
  <tr>
   <td bgcolor="#F5FAFE" class="main_bright">贡献描述</td>
   <td bgcolor="#F5FAFE" class="main_bright"><textarea name="_irpValueConfig.valuedesc" validType="configckeyExit[2,50]" class="easyui-validatebox" validType="length[2,300]" panelWidth="200px" required='true' missingMessage='请填写贡献描述' ><s:property value='_irpValueConfig.valuedesc' /></textarea></td>
  </tr>
  <tr>
   <td bgcolor="#F5FAFE" class="main_bright">方法名称</td>
   <td bgcolor="#F5FAFE" class="main_bright"><input type="text" name="_irpValueConfig.methodname" validType="length[2,65]"  class="easyui-validatebox" required='true' missingMessage='请填写方法名称' value="<s:property value='_irpValueConfig.methodname' />"></td>
  </tr>
  <tr>
   <td bgcolor="#F5FAFE" class="main_bright">状态参数</td>
   <td bgcolor="#F5FAFE" class="main_bright"><input type="text" name="_irpValueConfig.parameters" validType="length[2,300]"  class="easyui-validatebox" value="<s:property value='_irpValueConfig.parameters' />"></td>
  </tr>
  <tr>
   <td bgcolor="#F5FAFE" class="main_bright">接口层</td>
   <td bgcolor="#F5FAFE" class="main_bright"><input type="text" name="_irpValueConfig.beandao" validType="length[2,130]"  class="easyui-validatebox" value="<s:property value='_irpValueConfig.beandao' />"></td>
  </tr>
  <tr>
   <td bgcolor="#F5FAFE" class="main_bright">参数名称</td>
   <td bgcolor="#F5FAFE" class="main_bright"><input type="text" name="_irpValueConfig.beanidname" validType="length[2,130]"  class="easyui-validatebox" value="<s:property value='_irpValueConfig.beanidname' />"></td>
  </tr>
  <tr>
   <td bgcolor="#F5FAFE" class="main_bright">创建者</td>
   <td bgcolor="#F5FAFE" class="main_bright"><input type="text" name="_irpValueConfig.username" validType="length[2,65]"  class="easyui-validatebox" required='true' missingMessage='请填写创建者' value="<s:property value='_irpValueConfig.username' />"></td>
  </tr>
  <tr>
   <td bgcolor="#F5FAFE" class="main_bright">积分</td>
   <td bgcolor="#F5FAFE" class="main_bright"><input type="text"  name="_irpValueConfig.score" id="valuescore" style="width:80px;" ></td>
  </tr>
  <tr>
   <td bgcolor="#F5FAFE" class="main_bright">经验</td>
   <td bgcolor="#F5FAFE" class="main_bright"><input type="text"  name="_irpValueConfig.experience" id="valueexper" style="width:80px;" ></td>
  </tr>
  <tr>
   <td bgcolor="#F5FAFE" class="main_bright">是否启用</td>
   <td bgcolor="#F5FAFE" class="main_bright">
    <select name="_irpValueConfig.startusing" class="easyui-combobox" style="width: 50px;" editable='false' panelHeight='70'  panelWidth='50'>
     <option value="1" <s:if test="_irpValueConfig.startusing==1">selected="true"</s:if>>是</option>
     <option value="0" <s:if test="_irpValueConfig.startusing==0">selected="true"</s:if>>否</option>
    </select>
   </td>
  </tr>
 </table>
</form>
</body>
</html>