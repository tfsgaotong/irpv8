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
	    nameExit: {   
	        validator: function(value,param){   
	            if(value.length<param[0] || value.length>param[1] ){
	            	$.fn.validatebox.defaults.rules.nameExit.message=$.fn.validatebox.defaults.rules.length.message;
	            	return false;
	            }else{
	            var result=	$.ajax({
	            	   type:"get",
	            		url:"<%=rootPath%>asseroom/checkname.action?ck_nametwo=${asseroom.asseroomname}&ck_name="+value,
	  					  async: false,
		            	  cache : false
	            	}).responseText;
	            if(result=='false'){
	        		$.fn.validatebox.defaults.rules.nameExit.message = '您输入的会议室名称已存在';
					return false;
	        	}else{
	        		return true;
	        	}
	            }
	        },   
	        message: ''  
	    },
	    integer : {// 验证整数  
                validator : function(value) {  
                    return /^[+]?[1-9]+\d*$/i.test(value);  
                },  
                message : '请输入整数'  
            }
	  
	}); 
</script>
    <form id="editroom" method="post">
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cad9ea">
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">会议室名称</td>
         <td bgcolor="#F5FAFE" class="main_bright"><input  name="asseroom.asseroomname" class="easyui-validatebox" panelWidth="200px" validType="nameExit[2,30]" required='true' missingMessage='请填写会议室的名称' value="<s:property value="asseroom.asseroomname"/>"></td>
        </tr>
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">会议室描述</td>
         <td bgcolor="#F5FAFE" class="main_bright"><textarea name="asseroom.asseroomdesc" class="easyui-validatebox" panelWidth="200px" validType="length[2,600]" ><s:property value="asseroom.asseroomdesc" /></textarea></td>
        </tr>
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">会议室状态</td>
         <td bgcolor="#F5FAFE" class="main_bright">
           <select  name="asseroom.asseroomstatus">
           <option value="1" <s:if test="asseroom.asseroomstatus==1">selected</s:if>>是</option>
           <option value="0" <s:if test="asseroom.asseroomstatus==0">selected</s:if>>否</option>
           </select>
          </td>
        </tr>
         <tr>
         <td bgcolor="#F5FAFE" class="main_bright">会议室地址 </td>
         <td bgcolor="#F5FAFE" class="main_bright"><input  name="asseroom.asseroomaddr" class="easyui-validatebox" panelWidth="200px" validType="length[2,30]" required='true' missingMessage='请填写会议室地址' value="<s:property value="asseroom.asseroomaddr"/>"></td>
        </tr>
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">容纳人数 </td>
         <td bgcolor="#F5FAFE" class="main_bright"><input  name="asseroom.asseroompeoples" class="easyui-validatebox" panelWidth="200px" validType="integer" required='true' missingMessage='请填写会议室所容纳的人数' value="<s:property value="asseroom.asseroompeoples"/>"></td>
        </tr>
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">会议室设备</td>
          <td bgcolor="#F5FAFE" class="main_bright">
          <s:iterator value="sbList" status="sbListstatus">
           <input name="asseroomsbids" type="checkbox" checked="checked" value="<s:property value='asseroomsbid'/>"/><s:property value='asseroomsbname' />&nbsp;&nbsp;
           <s:if test="#sbListstatus.count==3">
           <br>
           </s:if>
           </s:iterator>
           <br>
           <s:iterator value="asseroomsbList" status="asseroomsbListstatus">
           <input name="asseroomsbids" type="checkbox" value="<s:property value='asseroomsbid'/>"/><s:property value='asseroomsbname' />&nbsp;&nbsp;
           <s:if test="#asseroomsbListstatus.count==3">
           <br>
           </s:if>
           </s:iterator>
          </td>
        </tr>
      </table>
    </form>
</body>
</html>