<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
  <body>  
  <script type="text/javascript">
  	function initDesc(){
  	  var chnlname= $('#addchannelfrm').find('input[name="irpChannel.chnlname"]').val();
  	  $('#addchannelfrm').find('input[name="irpChannel.chnldesc"]').val(chnlname);
  	} 
 
$(function(){  
	$.extend($.fn.validatebox.defaults.rules, {	  	
	        //知识库名称的长度和唯一验证   
	        checkName: { 
		        validator: function(value, param){
		            if(value.length<param[0] || value.length>param[1]){
		            	$.fn.validatebox.defaults.rules.checkName.message = $.fn.validatebox.defaults.rules.length.message;
		            	 return false;
		            }else{  
		            	var _siteid="<s:property value='irpChannel.siteid'/>"; 
		             	var data = $.ajax({  
	                        type: "POST", 
	                        async: false,   
                           // url:'<%=rootPath %>site/checkchnlname.action?siteid='+_siteid+'&id=<s:property value="irpChannel.channelid"/>',   
                            data:{'chnlName':value},  
	                        dataType:"json"}
	                        ).responseText;
	                    if(data=='false'){
	                    	$.fn.validatebox.defaults.rules.checkName.message = "输入的分类名称已存在";
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
   		<table width="100%"  border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
         <tr>
	        <td width="30%" align="right" bgcolor="#f5fafe" class="main_bleft">分类名称：</td>
	        <td bgcolor="#FFFFFF" class="main_bright">
	             <input type="hidden" name="irpChannel.siteid" value="1"/>  
	        	 <input type="hidden" name="irpChannel.chnlorder" value="0"/><%--排序 --%>
	        	 <input type="hidden" name="irpChannel.publishpro" value="1"/><%--是否发布 --%>
	        	 <input type="hidden" name="irpChannel.parentid" value="<s:property value='irpChannel.channelid'/>" /><%--用户一级栏目id --%>
			     <input onblur="initDesc()" class="easyui-validatebox" id="chnlnameinput" validType="checkName[2,6]"  type="text" name="irpChannel.chnlname" required="true" />  
		   		 <input type="hidden" name="irpChannel.chnldesc"  />
		   		 <input name="irpChannel.chnltype" type="hidden" value="<s:property value='@com.tfs.irp.channel.entity.IrpChannel@CHANNEL_TYPE_PRIVATE'/>"/>   
		    </td>
	     </tr>  
	    </table>  
  </form>
  </body>
</html>
