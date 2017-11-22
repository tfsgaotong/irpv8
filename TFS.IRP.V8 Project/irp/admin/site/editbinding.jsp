<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
String _position = request.getParameter("_position");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <body>  
  <script type="text/javascript">
  	function initDesc(){
  	  var chnlname= $('#addchannelfrm').find('input[name="irpChannel.chnlname"]').val();
  	  $('#addchannelfrm').find('input[name="irpChannel.chnldesc"]').val(chnlname);
  	} 
 
$(function(){ 
	$.extend($.fn.validatebox.defaults.rules, {	 
	        maxLength : {
				validator: function(value, param){   
					return value.length < param[0];  
	            },
				message : '该字符长度至多{0}位'
	        },	
	        //绑定字段库名称的长度和唯一验证   
	        checkName: { 
		        validator: function(value, param){
		            if(value.length>param[0]){
		            	$.fn.validatebox.defaults.rules.checkName.message = '该字符长度至多{0}位';
		            	return false;
		            }else{
		            	var show_name = decodeURI(value);
		            	//var chnlNameToConfim='${irpChannel.chnlname}';
		              	var _bindingid="<s:property value='irpBinding.bindingid'/>";
		             	var data = $.ajax({  
	                        type: "POST", 
	                        async: false,  
                            url:"<%=rootPath %>site/checkchnlname.action",   
                            data:{"chnlName":show_name,
                            		"channelid":_channelid,
                           },  
	                       	dataType:"json"}).responseText;
	                    if(data=='false'){
	                    	$.fn.validatebox.defaults.rules.checkName.message = "输入的唯一标识已存在";
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
  <form   id="updateBinding" method="post">
  	<input type="hidden" name="irpBinding.bindingid" value="<s:property value='irpBinding.bindingid'/>"> 
	<input type="hidden" name="irpBinding.channelid" value="<s:property value='irpBinding.channelid'/>"> 
   		<table width="100%"  border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
         <tr>
	        <td width="30%" align="right" bgcolor="#f5fafe" class="main_bleft">绑定表单字段：</td>
	        <td bgcolor="#FFFFFF" class="main_bright">
				<select id="chnlorder" name="irpBinding.bindingcolumn" style=" width: 200px;" >
			   	     <option value="0" title="选择字段">--选择字段--</option>
				   	   <s:iterator value="irpFormColumnList">
						 <option <s:if test="irpBinding.bindingcolumn==columnname">selected="selected"</s:if> value="<s:property value='columnname'/>">
							<s:property value='columnname'/>
						</option>
				       </s:iterator>
			   </select>
		    </td>
	     </tr> 
	     <tr>
	        <td width="30%" align="right" bgcolor="#f5fafe" class="main_bleft">文章表字段：</td>
		     <td bgcolor="#FFFFFF" width="30%" class="main_bright">
				<select id="chnlorder" name="irpBinding.doccolumn" style=" width: 200px;" >
			   	     <option value="0" title="选择字段">--选择字段--</option>
						 <s:iterator value="columnlist" var="wang">
						<option <s:if test="irpBinding.doccolumn==#wang">selected="selected"</s:if> value="<s:property value='#wang'/>">
							<s:property value='#wang'/>
						</option>
						</s:iterator>
			   </select>
			   <!-- <input style="width: 200px;" class="easyui-validatebox"  type="text" name="irpBinding.doccolumn" required="true" /> -->
		    </td>
	     </tr>  
	     
	    </table>
	    <%-- <table width="100%" id="indingbList" border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
	<tr  style="position: relative;">
   	  	<td colspan="10" align="left" style="padding-left: 10px;" >
   	  		
   	  		<a href="javascript:void(0)" onclick="addBinding(<s:property value="id"/>)">新增</a>
   	  		<a href="javascript:void(0)" onclick="delColumn()">删除</a>
   	  		<a href="javascript:void(0)" onclick="refresh1('<s:property value='irpForm.formtablename'/>')">刷新</a>
   	  		<input type="hidden" id="columnTablename" value="${formtablename}">
   	  	</td>
   	  
	</tr>
    <tr>
	    <td width="10%" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="checkAll()">全选</a></td>
	    <td width="10%" align="center" bgcolor="#F5FAFE" class="main_bright">绑定表单字段</td>
	    <td width="10%" align="center" bgcolor="#F5FAFE" class="main_bright">文章表字段</td>
	 
	    
    </tr>
   
    <s:iterator value="irpFormColumns" status="listStat">
    <tr id="<s:property value="columnid" />">
	    <td align="center" bgcolor="#F5FAFE"  class="main_bleft"><input type="checkbox" name="columnid" value="<s:property value="columnid" />" /> <s:property value="(pageNum-1)*pageSize+#listStat.count"/></td>
  
	    <td  onchange="updateColumn1()" bgcolor="#F5FAFE"  class="main_bright"><input type='text' value='<s:property value="columnname" />' name="columnname" style="width:40px;"/></td>
	    <td  onchange="updateColumn1()"  bgcolor="#F5FAFE"  class="main_bright"><input type='text' value='<s:property value="columntablenamecol"  />' name="columntablenamecol" style="width:40px;"/></td>
    </tr>
    </s:iterator>
    
     
 
</table>  --%>
  </form>
  </body>
</html>
