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
	        //知识库名称的长度和唯一验证   
	        checkName: { 
		        validator: function(value, param){
		            if(value.length>param[0]){
		            	$.fn.validatebox.defaults.rules.checkName.message = '该字符长度至多{0}位';
		            	return false;
		            }else{
		            	var show_name = decodeURI(value);
		            	var chnlNameToConfim='${irpChannel.chnlname}';
		              	var _channelid="<s:property value='irpChannel.channelid'/>";
		             	var data = $.ajax({  
	                        type: "POST", 
	                        async: false,  
                            url:"<%=rootPath %>site/checkchnlname.action",   
                            data:{"chnlName":show_name,
                            		"id":<s:property value='id'/>,
                            		"siteid":<s:property value='siteid'/>,
                            		"channelid":_channelid,
                            		"chnlNameToConfim":chnlNameToConfim 
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
  <form   id="addchannelfrm" method="post">
	<input type="hidden" name="irpChannel.channelid" value="<s:property value='irpChannel.channelid'/>"> 
	<input type="hidden" name="irpChannel.siteid" value="<s:property value='siteid'/>"> 
	<input type="hidden" name="irpChannel.parentid" value="<s:property value='id'/>"> 
	<input type="hidden" name="irpChannel.chnltype" value="<s:property value='chnlType'/>">
   		<table width="100%"  border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
         <tr>
	        <td width="30%" align="right" bgcolor="#f5fafe" class="main_bleft">唯一标识：</td>
	        <td bgcolor="#FFFFFF" class="main_bright">
			   <input value="<s:property value='irpChannel.chnlname'/>" style="width: 200px;" onblur="initDesc()" class="easyui-validatebox"  validType="checkName[150]"  type="text" name="irpChannel.chnlname" required="true" />  
		    </td>
	     </tr> 
	     <tr>
	        <td width="30%" align="right" bgcolor="#f5fafe" class="main_bleft">显示名称：</td>
	        <td bgcolor="#FFFFFF" class="main_bright">
			   <input value="<s:property value='irpChannel.chnldesc'/>"  class="easyui-validatebox"  style="width: 200px;"  validType="maxLength[150]" missingMessage="请填写显示名称" type="text" name="irpChannel.chnldesc" required="true" />  
		    </td>
	     </tr>
	     <tr>
	        <td width="30%" align="right" bgcolor="#f5fafe" class="main_bleft">关键字：</td>
	        <td bgcolor="#FFFFFF" class="main_bright">
			   <input value="<s:property value='irpChannel.chnlquery'/>"  class="easyui-validatebox"  style="width: 200px;"  validType="maxLength[150]" missingMessage="请填写关键字" type="text" name="irpChannel.chnlquery" required="true" />  
		    </td>
	     </tr>   
 		<tr>
	        <td width="30%" align="right" bgcolor="#f5fafe" class="main_bleft">是否发布：</td>
	        <td bgcolor="#FFFFFF" class="main_bright"> 
	       
			   <input name="irpChannel.publishpro" type="radio" value="<s:property value='@com.tfs.irp.channel.entity.IrpChannel@CHANNEL_NOT_PUBLISH'/>"  <s:if test="irpChannel.publishpro==@com.tfs.irp.channel.entity.IrpChannel@CHANNEL_NOT_PUBLISH">checked</s:if>/>不发布
			   <input name="irpChannel.publishpro" type="radio" value="<s:property value='@com.tfs.irp.channel.entity.IrpChannel@CHANNEL_IS_PUBLISH'/>"  <s:if test="irpChannel.publishpro==@com.tfs.irp.channel.entity.IrpChannel@CHANNEL_IS_PUBLISH">checked</s:if>/>发布
		    </td>
	       </tr>
	     <s:if test="isChannelOrMapAddOrUp=='addchannel' ||isChannelOrMapAddOrUp=='updatechannel'">
	       <tr>
	       		<td width="30%" align="right" bgcolor="#f5fafe" class="main_bleft">审核流程：</td>
		        <td bgcolor="#FFFFFF" class="main_bright">
			    	<select name="irpChannel.schedule" style="width: 100px;">
			    		<option value="0" <s:if test="irpChannel.schedule==null || irpChannel.schedule==0">selected="selected"</s:if>>--请选择--</option>
			    		<s:iterator value="workFlows">
			    		<option value="<s:property value='flowid'/>" <s:elseif test="irpChannel.schedule==flowid">selected="selected"</s:elseif>><s:property value='flowname'/></option>
			    		</s:iterator>
			    	</select>
			    </td>
	       </tr>  
	     </s:if>
	     <tr>
	        <td width="30%" align="right" bgcolor="#f5fafe" class="main_bleft">前一个栏目：</td>
	        <td bgcolor="#FFFFFF" class="main_bright"> 
			      <select id="chnlorder" name="irpChannel.chnlorder" style=" width: 200px;">
			   	     <option value="0" title="最前面">--最前面--</option>
				   	   <s:iterator value="irpChannels">
				   		 <option data="<s:property value='channelid'/>" value="<s:property value='chnlorder'/>" title="<s:property value='chnldesc'/>" <s:if test="%{irpChannel.chnlorder-1==chnlorder}">selected</s:if>>
							<s:property value='chnldesc'/>
						</option>
				       </s:iterator>
			   </select>
		    </td>
	     </tr> 
	    </table> 
  </form>
  </body>
</html>
