<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
  <body>  
  <script type="text/javascript">
  	function initDescChannel(){
  	  var chnlname= $('#updatechannelfrm').find('input[name="irpChannel.chnlname"]').val();
  	  $('#updatechannelfrm').find('input[name="irpChannel.chnldesc"]').val(chnlname);
  	} 
 
$(function(){ 
	$.extend($.fn.validatebox.defaults.rules, {	 
	        maxLength : {
				validator: function(value, param){   
					return value.length < param[0];  
	            },
				message : '该字符长度至多{0}位'
	        },	 
	        checkName: { 
		        validator: function(value, param){
		            if(value.length<param[0] || value.length>param[1]){
		            	$.fn.validatebox.defaults.rules.checkName.message = $.fn.validatebox.defaults.rules.length.message;
		            	return false;
		            }else{ 
		            	var data = $.ajax({  
	                        type: "POST", 
	                        async: false,  
                            url:'<%=rootPath %>site/clientcheckchnlname.action',   
                            data:{"chnlName":value,
                            		'id':<s:property value='irpChannel.channelid'/>},  
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
  <form   id="updatechannelfrm" method="post">  
   		<table width="100%"  border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
         <tr>
	        <td width="30%" align="right" bgcolor="#f5fafe" class="main_bleft">分类名称：</td>
	        <td bgcolor="#FFFFFF" class="main_bright">    
	        	 <input name="irpChannel.channelid" type="hidden" value="<s:property value='irpChannel.channelid'/>">
			     <input onblur="initDescChannel()"  validType="checkName[2,6]"  id="chnlnameinputupdate" class="easyui-validatebox"   missingMessage="请填写分类名称" type="text" name="irpChannel.chnlname" value="<s:property value='irpChannel.chnlname'/>" required="true" />  
		   		 <input type="hidden" name="irpChannel.chnldesc"  />   
		    </td>
	     </tr>  
	    </table>  
  </form>
  </body>
</html>
