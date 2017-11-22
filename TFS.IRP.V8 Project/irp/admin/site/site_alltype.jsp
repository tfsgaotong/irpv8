<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html> 
  <body>
  <script type="text/javascript">
  //修改
  function toupdatetype(_statusid){
  	var updatedocstatusdiv=document.createElement("div");
				updatedocstatusdiv.id="updatedocstatusdiv";
				document.body.appendChild(updatedocstatusdiv);  
				$('#updatedocstatusdiv').dialog({
					    modal:true,
					    cache:false,
				        href:'<%=rootPath%>filetype/typeinfo.action?attachedTypeId='+_statusid, 
						title:'修改文件类型',
						width:400,
						height:200,
						resizable:true,
						maximizable:false,
				        buttons:[{
						    	text: '修改', 
						    	iconCls: 'icon-ok', 
						    	handler: function(){
						    	 $('#updatetypefrm').form('submit',{
						    	 url:"<%=rootPath  %>filetype/updatetype.action", 
						    	 onSubmit : function(){
						    		 $.messager.progress({ text:'提交数据中...' });
					  	 		}, 
						    	  success:function(data){ 
						    			$.messager.progress('close');
						    	  	if(data=="1"){
						    	  		$('#sysfileext').panel('refresh'); 
						    	  		$('#updatedocstatusdiv').dialog('destroy');
						    	  	}else{
						    	  	  	$messager.alert('提示信息','修改失败','warning');
						    	  	} 
						    	  } 
						    	 });
						    	}
						    } ,
						    {
						    	text: '取消',
						    	iconCls: 'icon-no', 
						        handler: function(){
						        $('#updatedocstatusdiv').dialog('destroy');
						     }
						    }],
						       onClose:function(){
						    	$('#updatedocstatusdiv').dialog('destroy');
						    }  
				});  
  } 
  
  </script> 
     <table width="100%"  border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea" >
		<tr>
		 <td width="120" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0);" >类型名称</a></td>
		 <td align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0);">文件后缀 </a></td>
		 <td width="40" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0);">操作 </a></td>
		</tr>
		<s:iterator value="attTypeList">
		<tr>
		 <td align="left" bgcolor="#F5FAFE"  class="main_bright"><s:property value="typename"/></td>
		 <td align="left" bgcolor="#F5FAFE"  class="main_bright"><s:property value="suffix"/></td>
	     <td align="center" style="background:#F5FAFE;" class="main_bright"> <img border="0" src="images/icons/pencil.png" title="修改" style="cursor:pointer; margin: 0 5px;" onclick="toupdatetype(<s:property value='typeid'/>)" /></td>
		</tr>
		</s:iterator>
   </table> 
  </body>
</html>
