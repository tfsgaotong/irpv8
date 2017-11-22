<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>显示所有的文档状态</title> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">  
  </head> 
  <body>
  <script type="text/javascript">
   //刷新当前
  function docstatusrefresh(){ 
		  $('#docstatustab').panel('refresh');
  }  
  function adddocstatus(){   
		var adddocstatusdiv=document.createElement("div");
				adddocstatusdiv.id="adddocstatusdiv";
				document.body.appendChild(adddocstatusdiv);  
				$('#adddocstatusdiv').dialog({
					    modal:true,
					    cache:false,
				        href:'<%=rootPath%>site/toinsertdocustatus.action', 
						title:'添加知识状态',
						width:400,
						height:300,
						resizable:true,
						maximizable:false,
				        buttons:[{
						    	text: '添加', 
						    	iconCls: 'icon-ok', 
						    	handler: function(){
						    	 $('#adddocstatusfrm').form('submit',{
						    	 url:"<%=rootPath  %>site/insertdocustatus.action",
						    		onSubmit : function(){
						  	 		 	  var isValid =  $('#adddocstatusfrm').form('validate');// 表单提交之前所需要做的事情，对表单进行验证
						  	 		 	   if (isValid){
							    					$.messager.progress({
							    	    				text:'提交数据中...'
							    	    			});
							    				}
							    		   return isValid;
						  	 		}, 
						    	  success:function(data){
							    	  if(data!="0"){
							    		$.messager.progress('close');
							    	 	$('#docstatustab').panel('refresh'); 
							    	    $('#adddocstatusdiv').dialog('destroy');
							    	  }  
						    	  } 
						    	 });
						    	}
						    	
						    } ,
						    {
						    	text: '取消',
						    	iconCls: 'icon-no', 
						        handler: function(){
						        $('#adddocstatusdiv').dialog('destroy');
						     }
						    }],
						       onClose:function(){
						    	$('#adddocstatusdiv').dialog('destroy');
						    }  
				}); 
			   
  } 
  //修改
  function toupdatedocustatus(_statusid){
  	var updatedocstatusdiv=document.createElement("div");
				updatedocstatusdiv.id="updatedocstatusdiv";
				document.body.appendChild(updatedocstatusdiv);  
				$('#updatedocstatusdiv').dialog({
					    modal:true,
					    cache:false,
				        href:'<%=rootPath%>site/toupdatedocustatus.action?docstatus.statusid='+_statusid, 
						title:'修改知识状态',
						width:400,
						height:300,
						resizable:true,
						maximizable:false,
				        buttons:[{
						    	text: '修改', 
						    	iconCls: 'icon-ok', 
						    	handler: function(){
						    	 $('#adddocstatusfrm').form('submit',{
						    	 url:"<%=rootPath  %>site/updatedocustatus.action", 
						    	 onSubmit : function(){
					  	 		 	  var isValid =  $('#adddocstatusfrm').form('validate');// 表单提交之前所需要做的事情，对表单进行验证
					  	 		 	   if (isValid){
					  	 		 		   $.messager.progress({text:'提交数据中...'});
					  	 		 		}
						    		   return isValid;
					  	 		},success:function(data){ 
					  	 			$.messager.progress('close');
						    	  	if(data=="1"){
						    	  		$('#docstatustab').panel('refresh'); 
						    	  		$('#updatedocstatusdiv').dialog('destroy');
						    	  	}else{
						    	  		$.messager.alert('提示信息','修改失败','warning');
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
   //排序
	function orderBy(_sFiled,_sOrderBy){
		$('#orderField').val(_sFiled);
		$('#orderBy').val(_sOrderBy);
		var queryString = $('#docstatuspageForm').serialize(); 
	    $('#docstatustab').panel('refresh',"<%=rootPath %>site/alldocstatus.action?"+queryString);   
	}
   // 分页 
	function page(_nPageNum){
		$('#pageNum').val(_nPageNum); 
	     $('#docstatuspageForm').find('input[name="searchWord"]').val(encodeURIComponent($('#checkstatus').val()));
		var queryString = $('#docstatuspageForm').serialize();  
	 	var isGCChannel=$('#isGCChannel').val();
	    $('#docstatustab').panel('refresh',"<%=rootPath %>site/alldocstatus.action?"+queryString); 
	} 
	///当进入到这个页面的时候就想该页面的搜索框初始化
	$(function(){
		 $('#checkstatus').searchbox({
				width:240,
			    menu:'#statusType', 
			    prompt:'请输入检索词',
			    searcher:function(value,name){  
			    	$('#docstatuspageForm').find('input[name="searchWord"]').val(encodeURIComponent(value));
			    	$('#docstatuspageForm').find('input[name="searchType"]').val(name);
			    	$('#docstatuspageForm').find('input[name="pageNum"]').val('1');
			    	$('#docstatuspageForm').find('input[name="orderField"]').val('');
			    	$('#docstatuspageForm').find('input[name="orderBy"]').val('');   
			    	var queryString = $('#docstatuspageForm').serialize(); 
			        $('#docstatustab').panel('refresh',"<%=rootPath %>site/alldocstatus.action?"+queryString); 
				 } 
			}); 
	}); 
	//全选
	  var m_checked = false;//判定复选框的全选变量
	  		//全选
	    function checkAll(){
			m_checked = !m_checked;
			$("input[name='statusids']").attr("checked",m_checked); 
		}  
			//单独删除一个
		function deletedocustatsbystausid(_statusid){ 
			 $.messager.confirm('提示信息','您确定要删除吗？',function(r){
			 if(r){
			    $('#docstatustab').panel('refresh','<%=rootPath%>site/deletedocustatus.action?docstatus.statusid='+_statusid);
			 	}
			 });
		} 
  </script>
  <form id="docstatuspageForm" name="docstatuspageForm" method="post">
          <s:hidden name="pageNum" id="pageNum" />
		  <s:hidden name="pageSize" id="pageSize" />
		  <s:hidden name="orderField" id="orderField" />
		  <s:hidden name="orderBy" id="orderBy" />  
		  <s:hidden name="searchWord" id="searchWord"  />
	      <s:hidden name="searchType" id="searchType" /> 
  </form>
  <form name="showalldocustatusfrm" action="" method="post"> 
     <table width="100%"  border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea" >
	   <tr>
		<td colspan="3" style=" padding-left: 5px;">  
  	    <a href="javascript:void(0)" onclick="adddocstatus()">添加</a>   
  	    <a href="javascript:void(0)" onclick="docstatusrefresh()">刷新</a>
  	 	 </td> 
  	 	 <td colspan="4" align="right" style=" padding-right: 5px;">
  	 	   	<input name="checkstatus" id="checkstatus" value="<s:property  value='searchWord'/>" />
							<div id="statusType">  
							    <div data-options="name:'all'">全部&nbsp;&nbsp;&nbsp;&nbsp;</div>
							    <div data-options="name:'sname'">唯一标识&nbsp;&nbsp;</div>
							    <div data-options="name:'sdisp'">显示名称</div>
							</div>
  	 	 </td>
		 </tr>
		<tr>
		 <td width="60" align="center" bgcolor="#F5FAFE"  class="main_bright"> 
		     <a href="javascript:void(0);" onclick="checkAll()">全选</a>
		 </td>
		 <td width="130" align="center" bgcolor="#F5FAFE"  class="main_bright">
		  <a href="#" onclick="orderBy('sname','<s:if test="orderField=='sname'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">
		 唯一标识<s:if test="orderField=='sname'&&orderBy=='desc'">
						<img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" />
			</s:if>
			<s:elseif test="orderField=='sname'&&orderBy=='asc'">
						<img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" />
			</s:elseif>
		 </a></td>
		 <td align="center" bgcolor="#F5FAFE"  class="main_bright">
		  <a href="#" onclick="orderBy('sdesc','<s:if test="orderField=='sdesc'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">
		 状态描述<s:if test="orderField=='sdesc'&&orderBy=='desc'">
						<img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" />
			</s:if>
			<s:elseif test="orderField=='sdesc'&&orderBy=='asc'">
						<img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" />
			</s:elseif>
		  </a></td>
		 <td width="80" align="center" bgcolor="#F5FAFE"  class="main_bright">
		   <a href="#" onclick="orderBy('sdisp','<s:if test="orderField=='sdisp'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">
		显示名称<s:if test="orderField=='sdisp'&&orderBy=='desc'">
						<img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" />
			</s:if>
			<s:elseif test="orderField=='sdisp'&&orderBy=='asc'">
						<img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" />
			</s:elseif>
		  </a></td>
		 <td width="100" align="center" bgcolor="#F5FAFE"  class="main_bright">
		  <a href="#" onclick="orderBy('cruser','<s:if test="orderField=='cruser'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">
		 创建用户<s:if test="orderField=='cruser'&&orderBy=='desc'">
						<img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" />
			</s:if>
			<s:elseif test="orderField=='cruser'&&orderBy=='asc'">
						<img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" />
			</s:elseif>
		 </a> </td>
		 <td width="120" align="center" bgcolor="#F5FAFE"  class="main_bright">
		  <a href="#" onclick="orderBy('crtime','<s:if test="orderField=='crtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">
		 创建时间<s:if test="orderField=='crtime'&&orderBy=='desc'">
						<img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" />
			</s:if>
			<s:elseif test="orderField=='crtime'&&orderBy=='asc'">
						<img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" />
			</s:elseif>
		 </a> </td>
		 <td width="40" align="center" bgcolor="#F5FAFE"  class="main_bright">
		 操作
		 </td>
		</tr>
		<s:iterator value="irpDocstatuss" status="irpDocstatuss">
		<tr>
		 <td align="left" bgcolor="#F5FAFE"  class="main_bright">
		 	<input type="checkbox" name="statusids" id="statusids" value="<s:property value='statusid'/>"> 
		 	<s:property value="(pageNum-1)*pageSize+#irpDocstatuss.count"/>
		 </td>
		 <td align="left" bgcolor="#F5FAFE"  class="main_bright"><s:property value='sname'/>[状态Id-<s:property value='statusid'/>]</td>
		 <td align="left" bgcolor="#F5FAFE"  class="main_bright"> <s:property value='sdesc'/></td>
		 <td align="left" bgcolor="#F5FAFE"  class="main_bright"> <s:property value='sdisp'/></td> 
		 <td align="left" bgcolor="#F5FAFE"  class="main_bright"> <s:property value='cruser'/></td>
	     <td align="center" bgcolor="#F5FAFE"  class="main_bright"> 
	     	<s:date name="%{crtime}" format="yyyy-MM-dd HH:mm:ss"/> 
	     </td>
	     <td align="center" style="background: #F5FAFE" class="main_bright"> 
	    		<img border="0" src="images/icons/pencil.png" title="修改" style="cursor:pointer; margin: 0 5px;" onclick="toupdatedocustatus(<s:property value='statusid'/>)" />
	    		<s:if test="%{statusid>20}">
					&nbsp;<img border="0" src="images/icons/cancel.png" title="删除" style="cursor:pointer; margin: 0 3px;"  onclick="deletedocustatsbystausid(<s:property value='statusid'/>)"/>
	            </s:if> 
	     </td>
		</tr>
		</s:iterator>
		  <tr bgcolor="#FFFFFF">
	       	<td colspan="7" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
	    </tr>
   </table> 
  </form>
  </body>
</html>
