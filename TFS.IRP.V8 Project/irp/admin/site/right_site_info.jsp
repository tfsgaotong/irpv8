<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib  prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>修改知识库详细信息</title> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page"> 
  </head> 
  <body>
  <script type="text/javascript">
  function addsitetrue(){  
	 	  $('#updatesiteidfrm').form('submit',{
  	 		url:'<%=rootPath%>site/addsite.action',
  	 		onSubmit : function(){ 
  	 		 	  var isValid =  $('#updatesiteidfrm').form('validate');// 表单提交之前所需要做的事情，对表单进行验证 
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
  	function deletesite(){
  		//先弹出一个框提示是否确定删除
  		var _sitename="<s:property value='irpSite.sitename'/>";
  		$.messager.confirm('Confirm','你确定要删除'+_sitename+'知识库吗？',function(r){
		  		 	if(r){
		  			 $('#deletesitefrm').form('submit',{
		  	    	   url : '<%=rootPath%>site/delete_site_bysiteid.action',
		  	    	   success:function(data){
		  	    	   		if(data!="0"){ 
		  	    	   		 $.messager.alert('提示信息','您成功删除了【'+data+'】个知识库','warning');
		  	    	   		 $('body').layout('panel','west').panel('refresh');
		  	    	   		}
		  	    	   }	
			  	     });  
		  		}
  		}); 
  	} 
  	//刷新当前页面
  	function updatesiteinit(){  
  		$('#site').panel('refresh');
  	}
  	//修改该知识库详细信息
	function updatesite(){
		$('#updatesiteidfrm').form('submit',{
			url : '<%=rootPath%>site/update_site_bysiteid.action',
			onSubmit : function(){
				var isValid= $('#updatesiteidfrm').form('validate'); 
				  if (isValid){
   					$.messager.progress({
   	    				text:'提交数据中...'
   	    			});
   				}
   				return isValid;
			},
			success:function(data){ 
			 $.messager.progress('close');
				if(data=="1"){ 
				  var _siteid="<s:property value='irpSite.siteid'/>";
				  var sSiteName = $('#updatesiteidfrm').find("input[name='irpSite.sitename']").val();
					$('#allsitediv').accordion('getSelected').panel('setTitle',sSiteName);
					var rootNode = $('#chanels'+_siteid).tree('getRoot');
					$('#chanels'+_siteid).tree('update', {
						target: rootNode.target,
						text: sSiteName
					}); 
					 $('body').layout('panel','center').panel('refresh'); 
				}
				if(data=="2"){
	  	 		     $.messager.alert("提示信息","上传文件的格式有问题","warning");
	  	 	    }
				if(data=="0"){
				     $.messager.alert('提示信息','修改失败','warning');
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
		            	var show_name = decodeURI(value);
		            	var siteName="${irpSite.sitename}";
		            	var siteId="<s:property value='irpSite.siteid'/>";
		             	var data = $.ajax({  
	                        type: "POST", 
	                        async: false,  
                            url:'<%=rootPath %>site/checksitenameisonly.action',   
                            data:{"siteName":show_name,
                            	"siteNameConfim":siteName,
                            	"siteid":siteId},  
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
  <form  id="deletesitefrm" name="deletesitefrm" method="post">
      <input type="hidden" name="irpSite.siteid" value="<s:property value='irpSite.siteid'/>">
  </form>
  <form id="updatesiteidfrm" action="" method="post"  ENCTYPE="multipart/form-data">
       <table width="100%"  border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
							<tr>
					 	           <td colspan="3" nowrap="nowrap" align="right" bgcolor="#cad9ea"> 
					 	               <%--  <a href="javascript:void(0)" onclick="deletesite()">删除</a>  --%>
					 	                <a href="javascript:void(0)" onclick="updatesiteinit()">刷新</a>  
					 	      </td>
 	     				    </tr>
						  <tr>
						    <td width="25%" align="right" bgcolor="#f5fafe" class="main_bleft">知识库名称：</td>
						    <td bgcolor="#FFFFFF" class="main_bright"> 
				                <input  name="irpSite.sitename"  validType="checkName[2,8]" value="<s:property value='irpSite.sitename'/>"  missingMessage="请填写知识库名称"  class="easyui-validatebox"  type="text" required="true" />  
								<input type="hidden" name="irpSite.siteid" value="<s:property value='irpSite.siteid'/>">
								<%--<input type="hidden" name="irpSite.status"  value="<s:property value='irpSite.status' />"/> --%>
								<%--站点的状态1正常，0删除 --%>
		    					<input type="hidden" name="irpSite.status" value="1"/>  
							</td>
						  </tr>
						  <tr>
						    <td width="25%" align="right" bgcolor="#f5fafe" class="main_bleft">logo： </td>
						    <td bgcolor="#FFFFFF" class="main_bright"> 
								 <input name="myFile"  size="40" type="file" accept="image/*"    class="easyui-validatebox" > 
								 <input type="hidden" name="irpSite.logo" value="<s:property value='irpSite.logo'/>">
								 <s:if test="irpSite!=null&&irpSite.logo!=null && irpSite.logo!=''">
								 	 <img  border="0" src="<%=rootPath%>file/readfile.action?fileName=<s:property value='irpSite.logo'/>" height="70px" width="70px"/>  
								 </s:if>
							</td>
						  </tr>	
						  <tr>
						    <td width="25%" align="right" bgcolor="#f5fafe" class="main_bleft">buaner：</td>
						    <td bgcolor="#FFFFFF" class="main_bright">
								<input name="myFile1"  size="40" type="file" accept="image/*"    class="easyui-validatebox" >
								 <input type="hidden" name="irpSite.baner"  value="<s:property value='irpSite.baner'/>">  
								 <s:if test="irpSite!=null && irpSite.baner!=null&&irpSite.baner!=''">
									<img  border="0" src="<%=rootPath%>file/readfile.action?fileName=<s:property value='irpSite.baner'/>" height="70px" width="70px"/>  
								</s:if>
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
								   				<option value="<s:property value='siteorder'/>" <s:if test="%{irpSite.siteorder-1==siteorder}">selected</s:if>><s:property value='sitename'/></option>
								   	</s:iterator>
								   </select> 
							    </td>
				    	 </tr>   
						    <tr>
						        <td width="25%" align="right" bgcolor="#f5fafe" class="main_bleft">知识库是否发布：</td>
						        <td bgcolor="#FFFFFF" class="main_bright"> 
						          <input name="irpSite.ispublic" type="radio" value="0"  ${irpSite.ispublic eq '0'? 'checked':''} >不发布
								  <input name="irpSite.ispublic" type="radio" value="1"  ${irpSite.ispublic eq '1'? 'checked':''} >发布
								 </td>
					     </tr> --%> 
						  <tr>
						    <td width="25%" align="right" height="120"  bgcolor="#f5fafe" class="main_bleft">知识库描述：</td>
						    <td bgcolor="#FFFFFF" class="main_bright">
						   			 <textarea  style=" font-size: 12px; " class="easyui-validatebox" validType="maxLength[60]" name="irpSite.sitedesc" id="textarea" cols="45" rows="5" ><s:property value="irpSite.sitedesc"/></textarea> 
						    </td>
						  </tr>  
						  <tr>
						    <td width="25%" align="right" bgcolor="#f5fafe" class="main_bleft">&nbsp;</td>
						    <td bgcolor="#FFFFFF" class="main_bright">
								<s:if test="irpSite!=null">
								<a href="javascript:void(0)" id="addOne" class="easyui-linkbutton" onclick="updatesite()">确定</a>  
								</s:if><s:else>
								 <a href="javascript:void(0)" onclick="addsitetrue()" id="addsitebt" class="easyui-linkbutton"  data-options="iconCls:'icon-ok'">确定</a> 
								</s:else>
							</td>
							
						  </tr>  
					</table> 
  </form>
  </body>
</html>
