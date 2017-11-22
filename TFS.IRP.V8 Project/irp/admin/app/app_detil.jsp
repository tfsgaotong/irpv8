<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>
<script type="text/javascript">
$(function(){
	$("input").attr("readonly","readonly");
	//下拉框不可用
	$("select").attr("enable",false);
	//enable="false"
});
//全局变量，
var m_checked = false;
/**
 * 刷新本页面
 */
function refreshCata(){
	$("input").attr("readonly","readonly");
	var appid=$.trim($("#appid").val());
	jump('<%=rootPath  %>menu/menu/jump_to.action?appid='+appid);
}
 /**
  * 修改
  */
 function updateEdit(_configid){
 	var dialogdiv=document.createElement("div");
 	dialogdiv.id="updatereportconfig";
 	document.body.appendChild(dialogdiv);
 	$('#updatereportconfig').dialog({
 		modal:true,
 		href:'<%=rootPath%>menu/update_appById.action?aid='+_configid,
 		height:260,
 		width:400,
 		title:'修改应用',
 		resizable:true,
 		cache:false,
 		buttons:[{
 			text:'提交',
 			iconCls: 'icon-ok',
 			handler:function(){
 				$('#editinformtype').form('submit',{
 					url:'<%=rootPath%>menu/update_appById.action?aid='+_configid,
 					onSubmit:function(){
 						var isValid = $(this).form('validate');
 	    				if (isValid){
 	    					$.messager.progress({
 	    	    				text:'提交数据中...'
 	    	    			});
 	    				}
 	    				return isValid;
 					},		
 					success:function(_updateCataStat){
 						$.messager.progress('close');
 						$('#updatereportconfig').dialog('destroy');
 						if (_updateCataStat==1) {
 							$.messager.alert("成功","修改常用菜单成功");
 							refreshCata();
 						}else{
 							$.messager.alert("失败","修改常用菜单失败了");
 							refreshCata();
 						}
 					}
 				});
 			}
 		},{
 			text:'取消',
 			iconCls:'icon-cancel',
 			handler:function(){
 				$('#updatereportconfig').dialog('destroy');
 			}
 		}],
 		onClose:function(){
 			$('#updatereportconfig').dialog('destroy');
 		}
 		
 	});
 }
 //开启修改  文本框为可编辑状态
 function openUpdate(){
	 $("input:not(0)").removeAttr("readonly");
     if($("#surebtn").length>0){
    	 $("#surebtn").remove();
     }else{
    	 //追加一个确认按钮
    	 var _sureBtn="<a id=\"surebtn\" onclick=\"toUpdate()\" class=\"l-btn\" href=\"javascript:void(0)\"><span class=\"l-btn-left\"><span class=\"l-btn-text icon-ok l-btn-icon-left\">提交</span></span> </a>";
	     $("#ableinput").parent().append(_sureBtn);
     }
 }
 //修改应用配置
function toUpdate(){
	var appid=$.trim($("#appid").val());
	var queryString = $('#updateApp').serialize();
	$.messager.progress({
		text:'提交数据中...'
	});
	$.ajax({
		   type: "POST",
		   url: "<%=rootPath%>menu/update_appinfo.action?"+queryString,
		   dataType:"json",	   
		   success: function(data){
			   $.messager.progress('close');
			   $('#updatereportconfig').dialog('destroy');
			   if (data==1) {
					$.messager.alert("成功","修改应用成功");
				}else{
					$.messager.alert("失败","修改应用失败");
				}
			   $("input").attr("readonly","readonly");
		   }
		});
 }
	
</script>
<form id="updateApp" method="post">
<input type="hidden" name="irpApp.applistid" id="appid" value='<s:property value="irpApp.applistid"/>'/>
<table width="100%" border="0" align="center" cellpadding="10" cellspacing="1"  id="table1" bgcolor="#cad9ea">
		<tr class="list_th" style="position: relative;">
			 <td colspan="2" style="padding-left: 10px;">
			  <a href="javascript:void(0)" onclick="refreshCata()">刷新</a>
			 </td>
		</tr>
		<tr>
		    <td align="center" bgcolor="#F5FAFE" class="main_bright" width="20%">名称</td>
			 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="80%"><input  name="irpApp.applistname" class="easyui-validatebox" panelWidth="200px" validType="checkName[2,50]" required='true' missingMessage='请填写应用的名称' value="<s:property value="irpApp.applistname"/>"></td>
		</tr>
		<tr>
		     <td align="center" bgcolor="#F5FAFE" class="main_bright" width="20%">显示名称</td>
			 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="80%"><input  name="irpApp.applistalias" class="easyui-validatebox" panelWidth="200px" validType="checkName[2,50]" required='true' missingMessage='请填写应用的显示名称' value="<s:property value="irpApp.applistalias"/>"></td>
		</tr>
		<tr>
		     <td align="center" bgcolor="#F5FAFE" class="main_bright" width="20%">描述</td>
			 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="80%"><input  name="irpApp.description" class="easyui-validatebox" panelWidth="200px" validType="checkName[2,50]" required='true' missingMessage='请填写应用的描述信息' value="<s:property value="irpApp.description"/>"></td>
		</tr>
		<tr>
		     <td align="center" bgcolor="#F5FAFE" class="main_bright" width="20%">是否发布</td>
			 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="80%"><select name="irpApp.status"><option value="0">发布</option><option value="1">不发布</option></select></td>
		</tr>
		<tr>
		    <td align="center" bgcolor="#F5FAFE" class="main_bright" width="20%">版本</td>
			 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="80%"><input  name="irpApp.version" class="easyui-validatebox" validType="checkName[2,50]" required='true' value="<s:property value="irpApp.version"/>"> </td>
		</tr>
		<tr>
		     <td align="center" bgcolor="#F5FAFE" class="main_bright" width="20%">分类</td>
			 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="80%"><s:property value="irpApp.category" /></td>
		</tr>
		
		<tr>
		     <td align="center" bgcolor="#F5FAFE" class="main_bright" width="20%">开发时间</td>	 
			 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="80%"><input  name="irpApp.releasetime"  value="<s:date name="irpApp.releasetime" format="yyyy-MM-dd HH:mm:ss" />">  </td>
		</tr>
		<tr>
		    <td align="center" bgcolor="#F5FAFE" class="main_bright" width="20%">更新时间</td>
			<td align="center" bgcolor="#F5FAFE" class="main_bright" width="80%"><input  name="irpApp.lastupdatetime" value="<s:date name="irpApp.lastupdatetime" format="yyyy-MM-dd HH:mm:ss" />"></td>
		</tr>
		<tr>
		    <td align="center" bgcolor="#F5FAFE" class="main_bright" width="20%">图标</td>
			<td align="center" bgcolor="#F5FAFE" class="main_bright" width="80%"><input  name="irpApp.iconurl"  value="<s:property value="irpApp.iconurl" />"> </td>
		</tr>
		<tr>
		    <td align="center" bgcolor="#F5FAFE" class="main_bright" width="20%">作者</td>
			<td align="center" bgcolor="#F5FAFE" class="main_bright" width="80%"><input  name="irpApp.authorname" value="<s:property value="irpApp.authorname" />"></td>
		</tr>
		<tr>
		    <td align="center" bgcolor="#F5FAFE" class="main_bright" width="20%">贡献者</td>
			<td align="center" bgcolor="#F5FAFE" class="main_bright" width="80%"><input  name="irpApp.contributorname" value="<s:property value="irpApp.contributorname" />"></td>
		</tr>
		<tr align="center">	
		   <td colspan="2" align="center">
			<div class="dialog-button">
		        <a id="ableinput" class="l-btn" href="javascript:void(0)">
					<span class="l-btn-left">
					    <span  class="l-btn-text icon-reload l-btn-icon-left" onclick="openUpdate();return false;">修改</span>
					</span>
	           </a>
	       </div>
          </td>
		</tr>
    </table>
 </form>
</body>
</html>