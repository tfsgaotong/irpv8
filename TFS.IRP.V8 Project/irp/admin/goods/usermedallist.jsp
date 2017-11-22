<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户管理</title>
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />
</head>

<body>
<script type="text/javascript" src="js/log/log-format-time.js"></script>
<script type="text/javascript">
	//全局变量，
	var m_checked = false;
	
	//分页
	function page(_nPageNum){
		$('#pageNum').val(_nPageNum);
		var queryString = $('#pageForm').serialize();
		var date_start_time=$('#start_time').datebox('getValue');   
       var date_end_time=$('#end_time').datebox('getValue');                      
	   var _medalname=encodeURIComponent($('#_medalname').val());
	   var _username=encodeURIComponent($('#_username').val());
	   var start_end=$('#Irp_issuetime').combobox('getValue');
		$('#medalRecord').panel('refresh','<%=rootPath %>usermedal/listUserMedal.action?'+queryString+'&medalname='+_medalname+'&username='+_username+'&c_start_end='+start_end+'&c_date_start_time='+date_start_time+'&c_date_end_time='+date_end_time);
	}
	//排序
	function orderBy(_sFiled,_sOrderBy){
	alert(_sFiled+"ddd"+_sOrderBy);
		$('#orderField').val(_sFiled);
		$('#orderBy').val(_sOrderBy);
		var queryString = $('#pageForm').serialize();
		var date_start_time=$('#start_time').datebox('getValue');   
       var date_end_time=$('#end_time').datebox('getValue');                      
	   var _medalname=encodeURIComponent($('#_medalname').val());
	   var _username=encodeURIComponent($('#_username').val());
	   var start_end=$('#Irp_issuetime').combobox('getValue');
		$('#medalRecord').panel('refresh','<%=rootPath %>usermedal/listUserMedal.action?'+queryString+'&medalname='+_medalname+'&username='+_username+'&c_start_end='+start_end+'&c_date_start_time='+date_start_time+'&c_date_end_time='+date_end_time);
	}
	
	//全选
	function checkAll(){
		m_checked = !m_checked;
		$('#roleList').find("input:checkbox[name='usermedalid']").attr("checked",m_checked); 
	}
	
	//刷新
	/* function refresh(){
		$('body').layout('panel','center').panel('refresh');
	} */
	function refresh(){
		$('#medalRecord').panel('refresh');
	}
	$(function(){
	if('${c_start_end}'=="issue_appoint_date"){
 			$('#start_time').datebox({
 				value:'${c_date_start_time}'
 			});
 			$('#end_time').datebox({
 				value:'${c_date_end_time}'
 			});
		$('#_time_date').show();
		
		 }
	$('#Irp_issuetime').combobox({
	 	 panelHeight:'130',
	 	 editable:false,
	 	 onSelect:function(){
	 		 start_end = $(this).combobox('getValue');
	 		if (start_end=="issue_appoint_date") {
	 			var testDate = new Date();
	 			$('#start_time').datebox('setValue',testDate.format("yyyy-MM-dd"));
	 			$('#end_time').datebox('setValue',testDate.format("yyyy-MM-dd"));
	 			$('#_time_date').show();
	 		}
	 		if (start_end=="issue_week") {
	 			$('#_time_date').hide();
	 		}
	 		if (start_end=="issue_month") {
	 			$('#_time_date').hide();
	 		}
	 		if (start_end=="issue_quarter") {
	 			$('#_time_date').hide();
	 		}
	 	 }
	 	 
	  });
	});
	//检索
   function umedal_CheckSearch(){
	   //日期选择one,two
	   var date_start_time=$('#start_time').datebox('getValue');   
       var date_end_time=$('#end_time').datebox('getValue');                      
	   var _medalname=encodeURIComponent($('#_medalname').val());
	   var _username=encodeURIComponent($('#_username').val());
	   var start_end=$('#Irp_issuetime').combobox('getValue');
	
	   if (date_start_time>date_end_time) {
		   $.messager.alert("操作提示","结束日期必须在开始日期之后");
		   return false;
	   }
	   $('#medalRecord').panel('refresh','<%=rootPath%>usermedal/listUserMedal.action?medalname='+_medalname+'&username='+_username+'&c_start_end='+start_end+'&c_date_start_time='+date_start_time+'&c_date_end_time='+date_end_time+'&pageNum=1&orderField');
   }
	
	/*添加商品*/
	function addUesrMedal(){
		var dialogDiv = document.createElement("div");
		dialogDiv.id="editRole";
		document.body.appendChild(dialogDiv);
		
		$('#editRole').dialog({   
		    modal:true,
		    href:'<%=rootPath %>usermedal/toaddusermedal.action',
		    title:'为用户添加勋章',
		    width:400,
		    height:350,
		    resizable:true,
		    buttons: [{
		    	text: '提交', 
		    	iconCls: 'icon-ok', 
		    	handler: function() {
		    		$('#addUserMedal').form('submit',{
		    			url : '<%=rootPath %>usermedal/addusermedal.action',
		    			onSubmit: function(){
		    				var isValid = $(this).form('validate');
		    				if (isValid){
		    					$.messager.progress({
		    	    				text:'提交数据中...'
		    	    			});
		    				}
		    				return isValid;
		    			},
		    			success:function(data){
		    				$('#editRole').dialog('destroy');
		    				$.messager.progress('close');
		    				
		    				if(data==1){
		    					$.messager.alert("提示信息","颁发勋章成功！","info");
		    					refresh();
		    				}else{
		    					$.messager.alert("提示信息","勋章不足！","error");
		    				}
		    			}
		    		});
		    	} 
		    },{ 
		    	text: '取消', 
		    	iconCls: 'icon-cancel', 
		    	handler: function() { 
		    		$('#editRole').dialog('destroy');
		    	} 
		    }],
		    onClose:function(){
		    	$('#editRole').dialog('destroy');
		    }
		});
		
	}
	//删除兑换记录
	function pldelete(){
		var covertIds = "";
		$('#roleList').find("input:checkbox[name='usermedalid']:checked").each(function(){covertIds+=','+this.value;});
		if(covertIds){
			covertIds = covertIds.substring(1);
			$.messager.confirm('提示信息','是否要删除这些记录？',function(r){   
    		    if(r){
    		    	$.messager.progress({
	    				text:'提交数据中...'
	    			});
   		    		$.ajax({
   	    				url: "<%=rootPath %>covert/plDeleteCovert.action",
   	    			   	type: "POST",
   	    			   	data: {
   	    			   	covertids: covertIds
   	    			   	},
   	    			   	success: function(msg){
   	    			   		$.messager.progress('close');
   	    			   		$.messager.alert("提示信息","成功删除了"+msg+"条兑换记录","info");
   	    			   		refresh();
   	    			   	},
   	    			 	error:function(){
	    			 		$.messager.progress('close');
	    			 		$.messager.alert('提示信息','删除记录失败！','error');
	    			 	}
   		    		});
    		    }
    		});
		}else{
			$.messager.alert("提示信息","请选择要删除的记录。","warning");
		}
	}
	
	///////删除
	function deletegoods(_goodsid){
			$.messager.confirm('提示信息','是否要删除这条数据？',function(r){   
    		    if(r){
   		    		$.ajax({
   	    				url: "<%=rootPath %>goods/deleteGoods.action",
   	    			   	type: "POST",
   	    			   	data: {
   	    			   		goodsid: _goodsid,
   	    			   	},
   	    			   	success: function(msg){
   	    			   		$.messager.alert("提示信息","成功删除了这条数据","info");
   	    			   		refresh();
   	    			   	}
   		    		});
    		    }
    		});
	}
	
	//////////修改
	function editgoods(_goodsid){
		var dialogDiv = document.createElement("div");
		dialogDiv.id="editdoods";
		document.body.appendChild(dialogDiv);
		$('#editdoods').dialog({
			modal:true,
			href:'<%=rootPath %>goods/toeditgoodspage.action?goodsid='+_goodsid,
			title:'数据修改',
			resizable:true,
		    width:400,
		    height:350,
		    buttons: [{
		    	text: '提交', 
		    	iconCls: 'icon-ok', 
		    	handler: function() {
		    		$('#addGoods').form('submit',{
		    			url : '<%=rootPath %>goods/updateGoods.action',
		    			onSubmit: function(){
		    				var isValid = $(this).form('validate');
		    				if (isValid){
		    					$.messager.progress({
		    	    				text:'提交数据中...'
		    	    			});
		    				}
		    				return isValid;
		    			},
		    			success:function(data){
		    				$('#editdoods').dialog('destroy');
		    				$.messager.progress('close');
		    				if(data>0){
		    					refresh();
		    				}else{
		    					$.messager.alert("提示信息","修改商品数据失败！","error");
		    				}
		    			}
		    		});
		    	} 
		    	},{ 
		    	text: '取消', 
		    	iconCls: 'icon-cancel', 
		    	handler: function() { 
		    		$('#editdoods').dialog('destroy');
		    	} 
		    }],
		    onClose:function(){
		    	$('#editdoods').dialog('destroy');
		    }
		});
	}
	//查看详细信息
	function recordinfo(_covertid){
		var dialogDiv = document.createElement("div");
		dialogDiv.id="recordinfo";
		document.body.appendChild(dialogDiv);
		
		$('#recordinfo').dialog({   
		    modal:true,
		    href:'<%=rootPath %>covert/findCovertInfoById.action?covertid='+_covertid,
		    title:'兑换记录信息',
		    width:400,
		    height:350,
		    resizable:true,
		    buttons: [{ 
		   	 text:'关闭',
			 iconCls:'icon-cancel',
			 handler:function()
			 {
			$('#recordinfo').dialog('destroy');	 
			 }
		    }],
		    onClose:function(){
		    	$('#recordinfo').dialog('destroy');
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
<s:hidden name="formType" id="formType" value="10"/>
</form>
<!-- <div id="listSearchType" style="width:120px;">  
    <div data-options="name:'all'">全部&nbsp;&nbsp;&nbsp;&nbsp;</div>
    <div data-options="name:'formname'">商品名称</div>
    <div data-options="name:'formdesc'">兑换用户</div>
</div> -->
<table width="100%" id="roleList" border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
	<tr class="list_th" style="position: relative;">
	<td colspan="1" align="left" style="padding-left: 10px;" nowrap="nowrap">
   	  		
   	  		<a href="javascript:void(0)" onclick="addUesrMedal()">添加</a>
   	  		
   	  	</td>
   	  	<td colspan="5" align="left" style="padding-left: 10px;" nowrap="nowrap">
   	  		<form id="check_logs" method="post">
   	  		 勋章名称:<input name="medalname" id="_medalname"
					class="easyui-validatebox" value="<s:property value="medalname" />">
					&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;
   	  		 用户名称:<input name="username" id="_username"
					class="easyui-validatebox" value="<s:property value="username" />">
					&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;
			请选择时间段:<select id="Irp_issuetime" name="c_start_end"
						onchange="ck_date()">
						<option value="issue_week" id="issue_week"
							<s:if test="c_start_end=='issue_week'">selected="true"</s:if>>本周</option>
						<option value="issue_month" id="issue_month" selected="selected"
							<s:if test="c_start_end=='issue_month'">selected="true"</s:if>>本月</option>
						<option value="issue_quarter" id="issue_quarter"
							<s:if test="c_start_end=='issue_quarter'">selected="true"</s:if>>本季</option>
						<option value="issue_appoint_date" id="issue_appoint_date"
							<s:if test="c_start_end=='issue_appoint_date'">selected="true"</s:if>>指定</option>
					</select>&nbsp;&nbsp; <span id="_time_date" style="display: none;">
						开始日期<input type="text" id="start_time" class="easyui-datebox"
						editable="false"  />"&nbsp;&nbsp; 结束日期<input type="text"
						id="end_time" class="easyui-datebox" editable="false"
						validType="EndTime" />" </span>
					&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;
					<a href="javascript:void(0)" id="log_search"
						class="easyui-linkbutton" data-options="iconCls:'icon-search'"
						onclick="umedal_CheckSearch()">检索</a>&nbsp;&nbsp;<a href="javascript:void(0)"
						onclick="refresh()" class="easyui-linkbutton"
						data-options="iconCls:'icon-reload'">刷新</a>
				</form>
   	  	</td>
   	  	<!-- <td colspan="5" align="right" style="padding-right: 2px;"><input id='listSearchText' /></td> -->
	</tr>
	<!-- <tr class="list_th" style="position: relative;">
   	  	<td colspan="4" align="left" style="padding-left: 10px;" nowrap="nowrap">
   	  		
   	  		<a href="javascript:void(0)" onclick="pldelete()">批量删除</a>
   	  		
			<a href="javascript:void(0)" onclick="refresh()">刷新 </a>
   	  	</td>
   	  	<td colspan="5" align="right" style="padding-right: 2px;"><input id='listSearchText' /></td>
	</tr> -->
    <tr>
	    <td width="6%" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="checkAll()">全选</a></td>
	    <td width="15%" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('username','<s:if test="orderField=='username'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">用户名称<s:if test="orderField=='username'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='username'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="15%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('medalname','<s:if test="orderField=='medalname'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">勋章名称<s:if test="orderField=='medalname'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='medalname'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="15%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('medalstate','<s:if test="orderField=='medalstate'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">兑换状态<s:if test="orderField=='medalstate'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='medalstate'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="12%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('coverttime','<s:if test="orderField=='coverttime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">获得时间<s:if test="orderField=='coverttime'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='coverttime'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="10%" align="center" bgcolor="#F5FAFE"  class="main_bright">操作</td>
    </tr>
    <s:iterator value="listusermedal" status="listStat">
    <tr>
	    <td align="center" bgcolor="#F5FAFE"  class="main_bleft"><input type="checkbox" name="usermedalid" value="<s:property value="usermedalid" />" /> <s:property value="(pageNum-1)*pageSize+#listStat.count"/></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="username" /></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="medalname" /></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="medalstate" /></td>
	    <td bgcolor="#F5FAFE"  class="main_bright">
	     <s:date name="%{coverttime}" format="yyyy-MM-dd HH:mm"/>
	    </td>
	    <td align="center" valign="middle" bgcolor="#F5FAFE"  class="main_bright">
	    <a href="javascript:void(0)" onclick="recordinfo(<s:property value="usermedalid" />)">查看</a>
	    </td>
    </tr>
    </s:iterator>
    <tr bgcolor="#FFFFFF">
		<td colspan="6" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
	</tr>
</table>
</body>
</html>
