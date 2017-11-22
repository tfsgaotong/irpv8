
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html> 
  <body>
  <script type="text/javascript">
  
  //全局变量，
var m_checked = false;
/**
 * 刷新本页面
 */
function refreshCata(){
		$('#lmtemplate').panel('refresh');
}
/**
 * 增加百科模版配置
 */
function addEdit(){
 	var dialogdiv=document.createElement("div");
 	dialogdiv.id="addreportconfig";
 	document.body.appendChild(dialogdiv);
 	$('#addreportconfig').dialog({
 		modal:true,
 		cache:false,
 		href:'<%=rootPath%>admin/set/template_edit.jsp',
 		height:400,
 		width:600,
 		title:'添加百科模版配置',
 		resizable:true,
 		buttons:[{
 			text:'提交',
 			iconCls: 'icon-ok',
 			handler:function(){
 			 $('#editinformtype').form('submit',{
 				 url:'<%=rootPath%>set/addtemplate.action',
 				 onSubmit:function(){
 						var isValid = $(this).form('validate');
 	    				if (isValid){
 	    					$.messager.progress({
 	    	    				text:'提交数据中...'
 	    	    			});
 	    				}
 	    				return isValid;
 					},		
 				 success:function(_cataStatus){
 					 $.messager.progress('close');
 					 $('#addreportconfig').dialog('destroy');
 					 if (_cataStatus==1) {
 						refreshCata();
 					 }else{
 						$.messager.alert("失败","增加模版配置失败了");
 						refreshCata();
 					 }
 				 }
 			 });
 			}
 		},{
 			text:'取消',
 			iconCls:'icon-cancel',
 			handler:function(){
 				$('#addreportconfig').dialog('destroy');
 			}
 		}],
 		onClose:function(){
 			$('#addreportconfig').dialog('destroy');
 		}
 		
 	});
 	
 }  
 /**
 * 修改百科模版配置
 */
 function updateEdit(_configid){
 	var dialogdiv=document.createElement("div");
 	dialogdiv.id="updatereportconfig";
 	document.body.appendChild(dialogdiv);
 	$('#updatereportconfig').dialog({
 		modal:true,
 		href:'<%=rootPath%>set/updateTemplate.action?tid='+_configid,
 		height:400,
 		width:600,
 		title:'修改百科模版配置',
 		resizable:true,
 		cache:false,
 		buttons:[{
 			text:'提交',
 			iconCls: 'icon-ok',
 			handler:function(){
 				$('#editinformtype').form('submit',{
 					url:'<%=rootPath%>set/updatetemplatebyid.action?tid='+_configid,
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
 							refreshCata();
 						}else{
 							$.messager.alert("失败","修改百科模版配置失败了");
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
   /**
	*多选删除
	*/
	function deleteAll(){
	 var _configid = "";
	 $("input[name='irptemplateckid']:checked").each(
			 function(){
				 _configid+=$(this).val() + ',';
			 }
			 );
			 if (_configid=="") {
			 	$.messager.alert("操作提示","请您先选择要删除的对象");
			 	return false;
			 } 	
			 var _configcatalength=$("input[name='irptemplateckid']:checked").length;
			 $.messager.confirm("操作提示","您确定要删除选中的"+_configcatalength+"条数据吗",function(r){
				 
				 if(r){
			       $.messager.progress({
						text:'提交数据中...'
					});
				   $.ajax({
				    type:'post',
				    url:'<%=rootPath%>set/deletetemplatebyids.action?tids='+_configid,
				    success:function(date){
				    	 $.messager.progress('close');
				        if (date==1) {
							$.messager.alert("操作提示","您成功删除了"+_configcatalength+"条数据");
							refreshCata();
						}else{
							$.messager.alert("操作提示","删除失败了");
							refreshCata();
						}
				    },
				    error:function(){
				    	 $.messager.progress('close');
				    }
				 });
				 }
				 }
				 );
	}
	/**
	* 编辑删除
	*/	
	function deleteEdit(_tid){
	 $.messager.confirm("操作提示","您确定要删除这条数据吗",function(r){
	 if(r){
	 	$.ajax({
		     type:'post',
		     url:'<%=rootPath%>set/deletetemplatebyids.action',
		     data:{
		     	tids:_tid
		     },
			 cache:false,
			 async:false,
			 success:function(msg){
		        if (msg==1) {
					$.messager.alert("操作提示","删除成功了");
					refreshCata();
				}else{
					$.messager.alert("操作提示","删除失败了");
					refreshCata();
				}
			 }
		 	});
	 }

	 });
	 
	}
/**
* 全选
*/
function checkAll(){
	m_checked = !m_checked;
	$("input[name='irptemplateckid']").attr("checked",m_checked); 
} 
/**
*分页
*/
function page(_nPageNum){
	$('#pageNum').val(_nPageNum);
	var queryString = $('#pageForm').serialize();
	
	$('#baiketemplatemenu').panel('refresh',"<%=rootPath%>set/encyclopediacomplates.action?"+queryString);
}
  
  
 $(function(){
	$('#checkallchannel').searchbox({
		width:220,
	    searcher:function(value,name){   
	    	$('#pageForm').find('input[name="searchWord"]').val(value);
	    	$('#pageForm').find('input[name="searchType"]').val(name);
	    	$('#pageForm').find('input[name="pageNum"]').val('1');
	    	$('#pageForm').find('input[name="orderField"]').val('');
	    	$('#pageForm').find('input[name="orderBy"]').val('');
	    	$('#pageForm').find('input[name="cateid"]').val('<s:property value="cateid" />');
	    	var queryString = $('#pageForm').serialize();
	    	$('#lmtemplate').panel('refresh',"<%=rootPath %>set/templateknowlist.action?"+queryString);
	    },   
	    menu:'#listSearchText',   
	    prompt:'请输入检索词'  
	});
});	
  </script> 
  <form id="pageForm">
<s:hidden name="searchWord" id="searchWord" />
<s:hidden name="searchType" id="searchType" />
<s:hidden name="pageNum" id="pageNum" />
<s:hidden name="pageSize" id="pageSize" />
<s:hidden name="orderField" id="orderField" />
<s:hidden name="orderBy" id="orderBy" />
<s:hidden name="cateid" id="cateid" ></s:hidden>
</form>	 
  		<table width="100%" cellpadding="10" cellspacing="0">  
			<tr class="main_qbut">
 	           <td colspan="5" nowrap="nowrap" bgcolor="#cad9ea">
 	           <table width="100%" cellpadding="0" cellspacing="0">
 	           <tr>
 	           	<td width="50%" style="padding-left: 5px;">
 	           	        <a href="javascript:void(0)" onclick="checkallsite()">全选</a> 
	 	           		<a href="javascript:void(0)" onclick="addChannel(<s:property value=''/>,<s:property value='id'/>)">增加</a>
	 	           	    <a href="javascript:void(0)" onclick="deletechannel(<s:property value=''/>)">删除</a>  
	 	           	    <a href="javascript:void(0)" onclick="lminfoinit()">刷新</a> 
	 	        </td>
 	           	<td width="50%" align="right">
 	           		<input name="checkallchannel" id="checkallchannel"  />
					<div id="listSearchText">  
					    <div data-options="name:'all'">全部&nbsp;&nbsp;&nbsp;&nbsp;</div>
					    <div data-options="name:'tvaluedesc'">描述</div>
					</div>
				</td>
 	           </tr>
 	           </table></td>
 	        </tr> 
  			<tr class="main_qbut">   
  				<s:iterator value="templatelist">
  					<td width="20%" style="padding-top: 5px;" align="center">
  						<div style="background-position:center 10px;" onclick="clickDom(this)">
  							<a href="javascript:void(0)" title="<s:property value='tvaluedesc'/>" style=" text-decoration: none;">
  							<input type="checkbox" name="channelids" onclick="this.checked=!this.checked" value="<s:property value='tid'/>">
				  			    <s:if test="tvaluedesc.length()>4">
								    <s:property value='tvaluedesc.substring(0,4)'/>...
								 </s:if>   
								 <s:else>      
								     <s:property value='tvaluedesc'/>
								 </s:else>
  						</div>
  						<img onclick="updatechannelbychannelid(<s:property value='tid'/>)" style="position: relative; ;top:-50px;left: 30px; cursor: pointer;" border="0" alt="修改"  src="<%=rootPath %>admin/images/24.png" />
  					</td> 
  				</s:iterator>
  			</tr>  
	       <tr bgcolor="#FFFFFF">
	       	<td colspan="5" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
	       </tr>
  		</table> 
  </body>
</html>
