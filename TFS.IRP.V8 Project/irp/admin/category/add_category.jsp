<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <body>  
  <script type="text/javascript">
function initDesc(){
	 var cname= $('#addCategoryfrm').find('input[name="category.cname"]').val();
	 $('#addCategoryfrm').find('input[name="category.cdesc"]').val(cname);
} 
 
$(function(){ 
	$.extend($.fn.validatebox.defaults.rules, {	 
	        maxLength : {
				validator: function(value, param){   
					return value.length < param[0];  
	            },
				message : '该字符长度至多{0}位'
	        },	
	        //知识库名称的长度和唯一验证   
	        checkName: { 
		        validator: function(value, param){
		            if(value.length>param[0]){
		            	$.fn.validatebox.defaults.rules.checkName.message = '该字符长度至多{0}位';
		            	return false;
		            }else{
		            	var show_name = decodeURI(value);
		              	var _categoryId="<s:property value='category.categoryid'/>";
		              	if(_categoryId==null || _categoryId==''){
		              		_categoryId==-1;
		              	}
		              	var parentid="<s:property value='cid'/>";
		             	var data = $.ajax({  
	                        type: "POST", 
	                        async: false,  
                            url:"<%=rootPath %>category/checkCategoryName.action",   
                            data:{"categoryName":show_name,
                            		"selfId":_categoryId,
                            		"parentId":parentid
                           },  
	                       	dataType:"json"}).responseText;
	                    if(data>0){
	                    	$.fn.validatebox.defaults.rules.checkName.message = "输入的类别名称已存在";
	                    	return false;
	                    }else{
	                    	return true;
	                    }
		           }
		        }, 
	         	message: ""
	      }
	}); 
});  	  
  </script>
  <form   id="addCategoryfrm" method="post">
	<input type="hidden" name="category.categoryid" value="<s:property value='category.categoryid'/>"> 
	<input type="hidden" name="category.ctype" value="<s:property value='category.ctype'/>"> 
	<input type="hidden" name="category.cparentid" value="<s:property value='category.cparentid'/>"> 
	<input type="hidden" name="category.cruserid" value="<s:property value='category.cruserid'/>">
	<input type="hidden" name="category.crtime" value="<s:property value='category.crtime'/>">
   		<table width="100%"  border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
         <tr>
	        <td width="30%" align="right" bgcolor="#f5fafe" class="main_bleft">类别名称：</td>
	        <td bgcolor="#FFFFFF" class="main_bright">
			   <input value="<s:property value='category.cname'/>" style="width: 200px;" onblur="initDesc()" class="easyui-validatebox"  validType="checkName[150]"  type="text" name="category.cname" required="true" />  
		    </td>
	     </tr> 
	     <tr>
	        <td width="30%" align="right" bgcolor="#f5fafe" class="main_bleft">类别描述：</td>
	        <td bgcolor="#FFFFFF" class="main_bright">
			   <input value="<s:property value='category.cdesc'/>"  class="easyui-validatebox"  style="width: 200px;" type="text" name="category.cdesc" />  
		    </td>
	     </tr>
	     <tr>
	        <td width="30%" align="right" bgcolor="#f5fafe" class="main_bleft">链接地址：</td>
	        <td bgcolor="#FFFFFF" class="main_bright">
			   <input value="<s:property value='category.linkurl'/>"  class="easyui-validatebox"  style="width: 200px;" type="text" name="category.linkurl" />  
		    </td>
	     </tr>
	     <tr>
	        <td width="30%" align="right" bgcolor="#f5fafe" class="main_bleft">类别图片：</td>
	        <td bgcolor="#FFFFFF" class="main_bright">
			   <input value="<s:property value='category.categorypic'/>"  class="easyui-validatebox"  style="width: 200px;" type="text" name="category.categorypic" />  
		    </td>
	     </tr>  
 		<tr>
	        <td width="30%" align="right" bgcolor="#f5fafe" class="main_bleft">是否开启：</td>
	        <td bgcolor="#FFFFFF" class="main_bright"> 
	       
			   <input name="category.status" type="radio" value="<s:property value='@com.tfs.irp.category.entity.IrpCategory@STATUS_START'/>"  <s:if test="category.status==@com.tfs.irp.category.entity.IrpCategory@STATUS_START">checked</s:if>/>开启
			   <input name="category.status" type="radio" value="<s:property value='@com.tfs.irp.category.entity.IrpCategory@STATUS_FORBID'/>"  <s:if test="category.status==@com.tfs.irp.category.entity.IrpCategory@STATUS_FORBID">checked</s:if>/>禁用
		    </td>
	       </tr>
	     <tr>
	        <td width="30%" align="right" bgcolor="#f5fafe" class="main_bleft">前一个类别：</td>
	        <td bgcolor="#FFFFFF" class="main_bright"> 
			      <select id="categoryorder" name="category.categoryorder" style=" width: 200px;">
			   	     <option value="0" title="最前面">--最前面--</option>
				   	   <s:iterator value="categoryList">
				   		 <option data="<s:property value='id'/>" value="<s:property value='categoryorder'/>" title="<s:property value='cname'/>" <s:if test="%{category.categoryorder-1==categoryorder}">selected</s:if>>
							<s:property value='cname'/>
						</option>
				       </s:iterator>
			   </select>
		    </td>
	     </tr> 
	    </table> 
  </form>
  </body>
</html>
