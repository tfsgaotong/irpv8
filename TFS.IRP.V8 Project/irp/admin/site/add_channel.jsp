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
		 	 
 var addJsonFileList = new Array();
function saveAttacthedByLeave(){ 
var dialogDiv = document.createElement("div");
		dialogDiv.id="fileinfo";
		document.body.appendChild(dialogDiv);
		$('#fileinfo').dialog({
			modal:true,
			href:'<%=rootPath %>site/client_to_save_attached.action?isqusertionordoc=2',
			title:'文件上传',
			resizable:true,
		    width:600,
		    height:300,
		    buttons: [{
		    	text: '提交', 
		    	iconCls: 'icon-ok', 
		    	handler: function() {
		    	$('input[name="jsonFile"]').val(JSON.stringify(addJsonFileList));
		    	if(addJsonFileList){ 				
				var id=$('input:radio[name="editversions"]:checked').attr("id"); 
				$('#fileName').text('');
				$('#imgshow1').text('');
				for(var i=0;i<addJsonFileList.length;i++){
					filename=addJsonFileList[i].attfile;
				  	 $.ajax({
		 					type:"post",
		 					data : {'fileName' :filename},
		 					async: false,
		 					url:"<%=rootPath%>site/isradio.action" ,
		 					success:function(msg){
		 						if(msg=="1"){//说明他是图片
		 							
		 							$("#imgshow1").css("display","block");
		 							 $("#imgshow1").append("<img alt=\"显示图片\" src=\"<%=rootPath %>file/readfile.action?fileName="+filename+"\" id=\"mypic\" width=\"100\" height=\"100\"/>");
		 			    	     }else{
		 			    	    	$('#fileName').append(addJsonFileList[i].attdesc);
		 			    	     }
		 					}
		 				});
					
					
					if(addJsonFileList[i].attfile==id){
						addJsonFileList[i].editversions=1; 
					}else{ 
						if(addJsonFileList[i].editversions=="2"){
						}else{
							addJsonFileList[i].editversions=0;
						}
					}
				}
				$('#fileinfo').dialog('destroy');
			}
		    	} 
		    	},{ 
		    	text: '取消', 
		    	iconCls: 'icon-cancel', 
		    	handler: function() { 
		    		$('#fileinfo').dialog('destroy');
		    	} 
		    }],
		    onClose:function(){
		    	$('#fileinfo').dialog('destroy');
		    }
		});
}
var irpChannel="<s:property value='irpChannel'/>";
var _attachedid="<s:property value='irpChannel.imageid'/>";
$(function(){
	if(irpChannel!=null){
		toUpdate(_attachedid);
	}
});
var _allattacheds=null;
function toUpdate(_attachedid){ 
	//发送ajax请求获得所有附件
	$.ajax({ 
		type: "GET", 
		url: "<%=rootPath %>goods/findgoodsallattache.action?attachedids="+_attachedid, 
		success: function(msg){
			_allattacheds=eval(msg);//转换成附件集合    
		}
	});   
}
//修改附件
function upAttacthedByLeave(){ 
 var dialogDiv1 = document.createElement("div");
		dialogDiv1.id="edfileinfo";
		document.body.appendChild(dialogDiv1);		
		$('#edfileinfo').dialog({
			modal:true,
			href:'<%=rootPath %>form/client_to_update_attached.action?isqusertionordoc=2',
			title:'文件修改',
			resizable:true,
		    width:600,
		    height:300,
		    buttons: [{
		    	text: '提交', 
		    	iconCls: 'icon-ok', 
		    	handler: function() {		    	
		    	$('input[name="jsonFile"]').val(JSON.stringify(_allattacheds));
		    	if(_allattacheds){
				if(_allattacheds.length!=0){
					$('#fileName').html('');
					$('#imgshow1').html('');
				for(var i=0;i<_allattacheds.length;i++){
					filename=_allattacheds[i].attfile;
						 $.ajax({
		 					type:"post",
		 					data : {'fileName' :filename},
		 					async: false,
		 					url:"<%=rootPath%>site/isradio.action" ,
		 					success:function(msg){
		 						if(msg=="1"){//说明他是图片		 								
		 							 $("#imgshow1").append("<img alt=\"显示图片\" src=\"<%=rootPath %>file/readfile.action?fileName="+filename+"\" id=\"mypic\" width=\"100\" height=\"100\" />");
		 			    	     }else{
		 			    	    	$('#fileName').append(_allattacheds[i].attdesc);
		 			    	     }
		 					}
		 				});
				}					
		    	
		    	
				}
				var id=$('input:radio[name="editversions"]:checked').attr("id"); 
				for(var i=0;i<_allattacheds.length;i++){
					if(_allattacheds[i].attfile==id){
						_allattacheds[i].editversions=1; 
					}else{ 
						if(_allattacheds[i].editversions=="2"){//一种就是附件，
						}else{
							_allattacheds[i].editversions=0;
						}
					}
				}
				$('#edfileinfo').dialog('destroy');
			}  
		    	} 
		    	},{ 
		    	text: '取消', 
		    	iconCls: 'icon-cancel', 
		    	handler: function() { 
		    	$('#edfileinfo').dialog('destroy');
		    	} 
		    }],
		    onClose:function(){
		    	$('#edfileinfo').dialog('destroy');
		    }
		});   
}	  
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
	     <%
	     	if(!"beforePage".equals(_position)){
	     %>
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
	     <%
	     	}
	     %>
	     <tr>
	        <td width="30%" align="right" bgcolor="#f5fafe" class="main_bleft">绑定表单：</td>
	        <td bgcolor="#FFFFFF" class="main_bright" > 
			      <select name="irpChannel.formid" style=" width: 200px;" >
			   	     <option value="0" title="选择表单">--选择表单--</option>
				   	   <s:iterator value="irpForms">
				   		 <option <s:if test="irpChannel.formid==formid">selected="selected"</s:if> value="<s:property value='formid'/>">
							<s:property value='formname'/>
						</option>
				       </s:iterator>
			   </select>
		    </td>
	     </tr>
	      <tr>
	        <td width="30%" align="right" bgcolor="#f5fafe" class="main_bleft">栏目图片：</td>
	        <td bgcolor="#FFFFFF" class="main_bright" >
	        <s:if test="irpChannel==null"> 
	        <a href="javascript:void(0)" onclick="saveAttacthedByLeave()">选择图片</a>
	        </s:if>
	        <s:else>
	        <a href="javascript:void(0)" onclick="upAttacthedByLeave()">选择图片</a>
	        </s:else>
	        <input type="hidden" name="jsonFile" /><%-- 源文件名称 --%>
		    </td>
	     </tr>
	     
	    </table> 
  </form>
  </body>
</html>
