<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>流程管理</title>
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />
</head>

<body>
<script type="text/javascript">
	//全局变量，
	var m_checked = false;
	
	//雷达后台导入数据
    function importRadarsData(){
    	$.messager.confirm('提示信息','确认导入雷达数据？',function(r){ 
    		if(r){
    			$.messager.progress({text:'正在导入数据...'});
		    	$.ajax({
		    		type: 'post',
		    		url: '<%=rootPath%>radar/radar_import.action',
		    		cache: false,
		    		success: function(data){
		    			$.messager.progress("close");
		    			if(data === 'timeout'){
		    				$.messager.alert("提示信息", "数据库连接失败，请检查连接配置！", "warning");
		    			}else if(data === 'error'){
		    				$.messager.alert("提示信息", "导入失败！", "error");
		    			}else{
		    				$.messager.alert("提示信息", "导入"+data+"条数据", "info");
		    			}
		    			refresh();
		    		}
		    	});
    		}
    	});
    }
	
	//分页
	function page(_nPageNum){
		$('#pageNum').val(_nPageNum);
		var queryString = $('#pageForm').serialize();
		jump("<%=rootPath %>radar/radar_list.action?"+queryString);
	}
	
	//排序
	function orderBy(_sFiled,_sOrderBy){
		$('#orderField').val(_sFiled);
		$('#orderBy').val(_sOrderBy);
		var queryString = $('#pageForm').serialize();
		jump("<%=rootPath %>radar/radar_list.action?"+queryString);
	}
	
	//全选
	function checkAll(){
		m_checked = !m_checked;
		$("input[name='flowid']").attr("checked",m_checked); 
	}
	
	//刷新
	function refresh(){
		$('body').layout('panel','center').panel('refresh');
	}
	
	//删除指定流程
		function delFlow(sid){
			$.messager.confirm('提示信息','是否要删除这条数据？',function(r){ 
				if(r){
					$.ajax({
	    				url: "<%=rootPath %>radar/del_randar.action",
	    			   	type: "POST",
	    			   	data: {
	    			   		stitle: sid
	    			   	},
	    			   	success: function(msg){
	    			   		$.messager.progress('close');
	    			   		$.messager.alert("提示信息","成功删除了"+msg+"个流程","info");
	    			   		refresh();
	    			   	},
	    			 	error:function(){
	   			 			$.messager.progress('close');
	   			 			$.messager.alert('提示信息','删除数据失败！','error');
	   			 		}
		    		});					
				}
			});
	}

	function delFlows(){		 
		var sFlowIds = "";
		$('#flowList').find("input:checkbox[name='flowid']:checked").each(function(){sFlowIds+=','+this.value;});
		if(sFlowIds){
			sFlowIds = sFlowIds.substring(1);
			$.messager.confirm('提示信息','是否要删除这些数据？',function(r){   
    		    if(r){
    		    	$.messager.progress({
	    				text:'提交数据中...'
	    			});
   		    		$.ajax({
   	    				url: "<%=rootPath %>radar/del_randar.action",
   	    			   	type: "POST",
   	    			   	data: {
   	    			   	  stitle: sFlowIds
   	    			   	},
   	    			 success: function(msg){
	    			   		$.messager.progress('close');
	    			   		$.messager.alert("提示信息","成功删除了"+msg+"条数据","info");
	    			   		refresh();
	    			   	},
	    			   	error:function(){
	   			 			$.messager.progress('close');
	   			 			$.messager.alert('提示信息','删除数据失败！','error');
	   			 		}
   		    		});
    		    }
    		});
		}else{
			$.messager.alert("提示信息","请选择要导入的数据。","warning");
		}
	
	}
	function addDocument(_siteid,_parentid){ 
		var _checkChannelid=0;
		 		//跳到选择栏目页面
				var checkchnneldiv=document.createElement("div");
				checkchnneldiv.id="checkchnneldiv";
				document.body.appendChild(checkchnneldiv);
				$('#checkchnneldiv').dialog({
				        modal:true,
					    cache:false,
				        href:'<%=rootPath  %>site/to_adddocument.action?siteid='+_siteid+'&id+='+_parentid,
						title:'选择栏目',
						width:400,
						height:500,
						resizable:true,
						maximizable:false,
						 buttons:[{
						    	text: '确定', 
						    	iconCls: 'icon-ok', 
						    	handler: function(){
						    	   var _trs=$('#checkchannelul').tree('getSelected');
						    	    _checkChannelid=_trs.id;//得到他的栏目id
						    	    $('#checkchnneldiv').dialog('destroy');  
						    	  //  toadddocument(_siteid,_checkChannelid);  // 引用跳添加页面
						    	  impRadar(_siteid,_checkChannelid);
						    	 } 
						    },{
						    	text: '取消',
						    	iconCls: 'icon-no', 
						        handler: function(){
						        $('#checkchnneldiv').dialog('destroy');
						     }
						    }],
				       onClose:function(){
				    	$('#checkchnneldiv').dialog('destroy');
				      }  
				});  
 	}
	
	function impRadar(_sites,_Channelids){
		var sFlowIds = "";
		$('#flowList').find("input:checkbox[name='flowid']:checked").each(function(){sFlowIds+=','+this.value;});
		if(sFlowIds){
			sFlowIds = sFlowIds.substring(1);
			$.messager.confirm('提示信息','是否要导入这些数据？',function(r){   
    		    if(r){
    		    	$.messager.progress({
	    				text:'提交数据中...'
	    			});
   		    		$.ajax({
   	    				url: "<%=rootPath %>radar/radar_implist.action",
   	    			   	type: "POST",
   	    			   	data: {
   	    			   	  stitle: sFlowIds,
   	    			   	   sitid: _sites,
   	    			   	  chanid:_Channelids
   	    			   	},
   	    			 success: function(msg){
   	    				 	var datas =new Array();
   	    				 	datas =msg.split(",");
	    			   		$.messager.progress('close');
	    			   		$.messager.alert("提示信息","成功导入了"+datas[0]+"条数据,失败"+datas[1]+"条数据。","info");
	    			   		refresh();
	    			   	}
   		    		});
    		    }
    		});
		}else{
			$.messager.alert("提示信息","请选择要导入的数据。","warning");
		}
	}
		
	function upRadar(_ss){
		window.open("<%=rootPath%>radar/quest_Randr.action?sid="+_ss); 
	}

	$(function(){
		$('#listSearchText').searchbox({
			width:220,
		    searcher:function(value,name){   
		    	$('#pageForm').find('input[name="searchWord"]').val(value);
		    	$('#pageForm').find('input[name="searchType"]').val(name);
		    	$('#pageForm').find('input[name="pageNum"]').val('1');
		    	$('#pageForm').find('input[name="orderField"]').val('');
		    	$('#pageForm').find('input[name="orderBy"]').val('');
		    	var queryString = $('#pageForm').serialize();
		    	jump("<%=rootPath %>flow/flow_list.action?"+queryString);
		    },   
		    menu:'#listSearchType',   
		    prompt:'请输入检索词'  
		});
	});
	
	
	
	function toData(){ 
		var _checkChannelid=0;
		 		//跳到选择栏目页面
				var checkchnneldiv=document.createElement("div");
				checkchnneldiv.id="toDatadiv";
				document.body.appendChild(checkchnneldiv);
				$('#toDatadiv').dialog({
				        modal:true,
					    cache:false,
				        href:'<%=rootPath  %>radar/to_data_import.action',
						title:'选择栏位',
						width:400,
						height:500,
						resizable:true,
						maximizable:false,
						 buttons:[{
						    	text: '确定', 
						    	iconCls: 'icon-ok', 
						    	handler: function(){
						    	   $('#dataForm').form('submit',{
										url:'<%=rootPath%>radar/radar_import.action',
										onSubmit:function(){
											var isValid = $(this).form('validate');
						    				if (isValid){
						    					$.messager.progress({
						    	    				text:'提交数据中...'
						    	    			});
						    				}
						    				return isValid;
										},		
										success: function(data){
							    			$.messager.progress("close");
										$('#toDatadiv').dialog('destroy');
							    			if(data === 'timeout'){
							    				$.messager.alert("提示信息", "数据库连接失败，请检查连接配置！", "warning");
							    			}else if(data === 'error'){
							    				$.messager.alert("提示信息", "导入失败！", "error");
							    			}else{
							    				$.messager.alert("提示信息", "导入"+data+"条数据", "info");
							    			}
							    			refresh();
							    		}
									}); 
						    	 } 
						    },{
						    	text: '取消',
						    	iconCls: 'icon-no', 
						        handler: function(){
						        $('#toDatadiv').dialog('destroy');
						     }
						    }],
				       onClose:function(){
				    	$('#toDatadiv').dialog('destroy');
				      }  
				});  
 	}
</script>
<form id="pageForm">
<s:hidden name="searchWord" id="searchWord" />
<s:hidden name="searchType" id="searchType" />
<s:hidden name="pageNum" id="pageNum" />
<s:hidden name="pageSize" id="pageSize" />
<s:hidden name="orderField" id="orderField" />
<s:hidden name="orderBy" id="orderBy" />
</form>

<table width="100%" id="flowList" border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
	<tr class="list_th" style="position: relative;">
   	  	<td colspan="3" align="left" style="padding-left: 10px;" nowrap="nowrap">
   	  		<!-- <a href="javascript:void(0)" onclick="importRadarsData()">导入</a> -->
   	  		<a href="javascript:void(0)" onclick="toData()">导入</a>
			<a href="javascript:void(0)" onclick="addDocument(2,-1)">导出</a>
			<a href="javascript:void(0)" onclick="refresh()">刷新</a>
			<a href="javascript:void(0)" onclick="delFlows()">删除 </a>
   	  	</td>   	  
	</tr>

    <tr>
	    <td width="60" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="checkAll()">全选</a></td>
	    <td width="" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('doctitle','<s:if test="orderField=='doctitle'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">标题<s:if test="orderField=='doctitle'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='doctitle'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="140" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('systime','<s:if test="orderField=='systime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">创建时间<s:if test="orderField=='systime'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='systime'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="100" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('satus','<s:if test="orderField=='satus'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">状态<s:if test="orderField=='satus'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='satus'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="90" align="center" bgcolor="#F5FAFE"  class="main_bright">操作</td>
	    
    </tr>
    <s:iterator value="irpRandars" status="listStat" var="irp">
    <tr>

	    <td align="center" bgcolor="#F5FAFE" align="center" class="main_bleft"><input type="checkbox" name="flowid" value="<s:property value="sid" />"/> </td>	   
	    <td bgcolor="#F5FAFE" class="main_bright"><a href="<%=rootPath%>radar/desc_Randar.action?sid=<s:property value='sid' />"><s:property value="doctitle" /></a></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:property value="systime" /></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:if test="satus==null||satus==1">新稿</s:if><s:if test="satus==2">已导入</s:if></td>
	    <td align="center" valign="middle" bgcolor="#F5FAFE"  class="main_bright"><img border="0" src="images/icons/pencil.png" title="修改" style="cursor:pointer; margin: 0 5px;" onclick="upRadar('<s:property value='sid' />')"/><img border="0" src="images/icons/cancel.png" title="删除" style="cursor:pointer; margin: 0 5px;" onclick="delFlow('<s:property value='sid' />')" /></td>
    </tr>
    </s:iterator>
    <tr bgcolor="#FFFFFF">
		<td colspan="6" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
	</tr>
</table>
</body>
</html>
