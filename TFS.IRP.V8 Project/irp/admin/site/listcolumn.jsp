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

function findChannel(){
var channel= $("#channel").val();
	$.ajax({
		url: "../site/findchannelbyformid.action",
		type: "POST",
		data: {channelid: channel},
		success: function (data) {
			var datas= eval(data);
			$("#selectid").empty();
			var sel= document.getElementById("selectid");
			sel.options.add(new Option("--选择字段--",0));
		 	for(var i=0; i<datas.length;i++){
		 		var data_1=datas[i];
		 		var columnname="";
		 		for(var j in data_1){
		 			if(j="columnname"){
		 				columnname=data_1[j];
		 			}
		 		}
		 	sel.options.add(new Option(columnname,columnname));
		 	}	
		 }
	});	 
}; 
	  
  </script>
  <form   id="binding" method="post">
  	<input type="hidden" name="irpBinding.bindingid" value="<s:property value='irpBinding.bindingid'/>"> 
	
   		<table width="100%"  border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
         <s:if test="channelid==0"> 
         <tr>
	        <td width="30%" align="right" bgcolor="#f5fafe" class="main_bleft">栏目：</td>
	        <td bgcolor="#FFFFFF" class="main_bright">
				<select id="channel" name="irpBinding.channelid" style=" width: 200px;" >
			   	     <option value="0" title="选择栏目">--选择栏目--</option>
				   	   <s:iterator value="listChannel">
				   		 <option value="<s:property value='channelid'/>" onclick="findChannel()">
							<s:property value='chnlname'/>
						</option>
				       </s:iterator>
			   </select>
		    </td>
	     </tr> 
	      <tr>
	        <td width="30%" align="right" bgcolor="#f5fafe" class="main_bleft">绑定表单字段：</td>
	        <td bgcolor="#FFFFFF" class="main_bright">
				<select id="selectid" name="irpBinding.bindingcolumn" style=" width: 200px;" >
			   	     <option value="0" title="选择字段">--选择字段--</option>
				   	   <%-- <s:iterator value="irpFormColumnList">
				   		 <option value="<s:property value='columnname'/>">
							<s:property value='columnname'/>
						</option>
				       </s:iterator> --%>
			   </select>
		    </td>
	     </tr> 
         </s:if>
         <s:else>
         	<input type="hidden" name="irpBinding.channelid" value="<s:property value='channelid'/>"> 
         <tr>
	        <td width="30%" align="right" bgcolor="#f5fafe" class="main_bleft">绑定表单字段：</td>
	        <td bgcolor="#FFFFFF" class="main_bright">
				<select id="chnlorder" name="irpBinding.bindingcolumn" style=" width: 200px;" >
			   	     <option value="0" title="选择字段">--选择字段--</option>
				   	   <s:iterator value="irpFormColumnList">
				   		 <option value="<s:property value='columnname'/>">
							<s:property value='columnname'/>
						</option>
				       </s:iterator>
			   </select>
		    </td>
	     </tr> 
         </s:else>
	     <tr>
	        <td width="30%" align="right" bgcolor="#f5fafe" class="main_bleft">文章表字段：</td>
		     <td bgcolor="#FFFFFF" width="30%" class="main_bright">
				<select id="chnlorder" name="irpBinding.doccolumn" style=" width: 200px;" >
			   	     <option value="0" title="选择字段">--选择字段--</option>
						 <s:iterator value="columnlist" var="wang">
						<option value="<s:property value='#wang'/>">
							<s:property value='#wang'/>
						</option>
						</s:iterator>
			   </select>
			   <!-- <input style="width: 200px;" class="easyui-validatebox"  type="text" name="irpBinding.doccolumn" required="true" /> -->
		    </td>
	     </tr>  
	     <tr>
	     <td width="30%" align="right" bgcolor="#f5fafe" class="main_bleft">是否显示：</td>
	   <td bgcolor="#FFFFFF" width="30%" class="main_bright"><input type="radio" name="show"  value="1" checked="checked"/> 是
	   <input type="radio" name="show"  value="0" /> 否</td>
	    </tr>
	    </table>
  </form>
  </body>
</html>
