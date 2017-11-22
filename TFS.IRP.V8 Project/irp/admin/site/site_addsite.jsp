<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>添加知识库页面</title> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">  
  </head> 
  <body> 
  <script type="text/javascript">
  	 function addsitetrue(){  
  	 	  $('#addsitefrm').form('submit',{
	  	 		url:'<%=rootPath%>site/addsite.action',
	  	 		onSubmit : function(){ 
	  	 		 	  var isValid =  $('#addsitefrm').form('validate');// 表单提交之前所需要做的事情，对表单进行验证 
	  	 		 	   if (isValid){
		    					$.messager.progress({
		    	    				text:'提交数据中...'
		    	    			});
		    				}
		    		   return isValid;
	  	 		},
	  	 		success:function(data){
	  	 		    $.messager.progress('close'); 
	  	 		    //成功之后需要刷新左边所有知识库,或者在actgion里面重定向//表单提交成功的时候触发  
	  	 		    if( data=="1" ){ 
	  	 		      $('body').layout('panel','west').panel('refresh');
	  	 		    }
	  	 		    else{
	  	 		        $.messager.alert("提示信息","添加失败","warning");
	  	 		    } 
	  	 		 }
	  	 }); 
  	 } 
  $(function(){ 
	$.extend($.fn.validatebox.defaults.rules, {	
			//验证上传文件类型
			checkFileType : {
				validator : function(value,param){ 
					return value==param[0];
				},
				message : '上传图片格式只能是{0}类型'
			},
			 //页面头信息关键字的长度验证
	        maxLength : {
				validator: function(value, param){   
					return value.length < param[0];  
	            },
				message : '该字符长度至多{0}位'
	        },	
	        //知识库名称的长度和唯一验证   
	        checkName: { 
		        validator: function(value, param){
		            if(value.length<param[0] || value.length>param[1]){
		            	$.fn.validatebox.defaults.rules.checkName.message = $.fn.validatebox.defaults.rules.length.message;
		            	return false;
		            }else{ 
		             	var data = $.ajax({  
	                        type: "POST", 
	                        async: false, 
	                        url:'<%=rootPath %>site/checksitenameisonly.action',   
	                        data:{"irpSite.sitename":value},  
	                        dataType:"json"}).responseText;
	                    if(data=='false'){
	                    	$.fn.validatebox.defaults.rules.checkName.message = "输入的知识库名称已存在";
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
   <form id="addsitefrm" method="post" ENCTYPE="multipart/form-data" >  
       <table width="100%"  border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
         <tr>
	        <td width="25%" align="right" bgcolor="#f5fafe" class="main_bleft">知识库名称：</td>
	        <td bgcolor="#FFFFFF" class="main_bright">
			   <input id="name1"  validType="checkName[2,8]" class="easyui-validatebox" missingMessage="请填写知识库名称" type="text" name="irpSite.sitename" required="true" />  
		    	<%--站点的状态1正常，0删除 --%>
		    	<input type="hidden" name="irpSite.status" value="1"/>  
		    	<span>
		    		<font color="red" id="onesite"></font>
		    	</span>
		    </td>
	     </tr>  
	      <tr>
	        <td width="25%" align="right" bgcolor="#f5fafe" class="main_bleft">logo：</td>
	        <td bgcolor="#FFFFFF" class="main_bright">
			   <input name="myFile"  size="40" type="file"  accept="image/*"   class="easyui-validatebox"> 
		    </td>
	     </tr> 
	      <tr>
	        <td width="25%" align="right" bgcolor="#f5fafe" class="main_bleft">baner：</td>
	        <td bgcolor="#FFFFFF" class="main_bright"> 
		    	<input name="myFile1"  size="40" type="file" accept="image/*" class="easyui-validatebox" > 
		    </td>
	     </tr> 
	     <%-- <tr>
	        <td width="25%" align="right" bgcolor="#f5fafe" class="main_bleft">允许发布的文档状态：</td>
	        <td bgcolor="#FFFFFF" class="main_bright">
			   <s:iterator value="irpDocstatus">
			   		<input type="checkbox" value="<s:property value='statusid'/>" <s:if test="%{statusid==10}">checked</s:if>>&nbsp;<s:property value='sdisp'/>&nbsp;
			   </s:iterator>
		    </td>
	     </tr>  
	      <tr>
	        <td width="25%" align="right" bgcolor="#f5fafe" class="main_bleft">前一个知识库：</td>
	        <td bgcolor="#FFFFFF" class="main_bright"> 
			   <select name="irpSite.siteorder">
			   	<option value="0">--最前面--</option>
			   	<s:iterator value="allsiteList">
			   				<option value="<s:property value='siteorder'/>"><s:property value='sitename'/></option>
			   	</s:iterator>
			   </select>
		    </td>
	     </tr> 
	      <tr>
	        <td width="25%" align="right" bgcolor="#f5fafe" class="main_bleft">知识库是否发布：</td>
	        <td bgcolor="#FFFFFF" class="main_bright">
	          <input name="irpSite.ispublic" type="radio" value="0" checked>不发布
			  <input name="irpSite.ispublic" type="radio" value="1">发布
			 </td>
	     </tr> --%>
	      <tr>
	        <td width="25%" align="right" bgcolor="#f5fafe" class="main_bleft">知识库描述：</td>
	        <td bgcolor="#FFFFFF" class="main_bright">
			   <textarea rows="5" cols="40%" name="irpSite.sitedesc" class="easyui-validatebox"  validType="maxLength[60]" style=" font-size: 12px; "></textarea> 
		    </td>
	     </tr>  
	      <tr>
	        <td width="25%" align="right" bgcolor="#f5fafe" class="main_bleft">&nbsp;</td>
	        <td bgcolor="#FFFFFF" class="main_bright">
			    <a href="javascript:void(0)" onclick="addsitetrue()" id="addsitebt" class="easyui-linkbutton"  data-options="iconCls:'icon-ok'">
			    	确定
			    </a> 
			    <a href="javascript:void(0)" onclick="addsitefrm.reset()"  id="nositebt" class="easyui-linkbutton"  data-options="iconCls:'icon-no'">
			    	取消
			    </a> 
		    </td>
	     </tr> 
       </table>
  </form>   
  </body>
</html>
