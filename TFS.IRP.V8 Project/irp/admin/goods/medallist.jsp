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
<script type="text/javascript">
	//全局变量，
	var m_checked = false;
	
	//分页
	function page(_nPageNum){
		$('#pageNum').val(_nPageNum);
		var queryString = $('#pageForm').serialize();
    	$('body').layout('panel','center').panel('refresh',"<%=rootPath %>medal/tolistmedalcategoryBackground.action?"+queryString+"&categoryId=${categoryId}");
	}
	//排序
	function orderBy(_sFiled,_sOrderBy){
		$('#orderField').val(_sFiled);
		$('#orderBy').val(_sOrderBy);
		var queryString = $('#pageForm').serialize();
		$('#medalManager').panel('refresh',"<%=rootPath %>medal/listMedal.action?"+queryString);
	}
	
	//全选
	function checkAll(){
		m_checked = !m_checked;
		$('#roleList').find("input:checkbox[name='medalid']").attr("checked",m_checked); 
	}
	
	//刷新
	/* function refresh(){
		$('body').layout('panel','center').panel('refresh');
	} */
	function refresh(){
		$('#medalManager').panel('refresh');
	}
	$(function(){
		$('#listSearchText1').searchbox({
			width:220,
		    searcher:function(value,name){   
		    	$('#pageForm').find('input[name="searchWord"]').val(value);
		    	$('#pageForm').find('input[name="searchType"]').val(name);
		    	$('#pageForm').find('input[name="pageNum"]').val('1');
		    	$('#pageForm').find('input[name="orderField"]').val('');
		    	$('#pageForm').find('input[name="orderBy"]').val('');
		    	var queryString = $('#pageForm').serialize();
		    	$('body').layout('panel','center').panel('refresh',"<%=rootPath %>medal/tolistmedalcategoryBackground.action?"+queryString+"&categoryId=${categoryId}");
		    },   
		    menu:'#listSearchType1',
		    	prompt:'请输入检索词',
		});
	});
	
	
	/*添加商品*/
	function addMedal(){
		var dialogDiv = document.createElement("div");
		dialogDiv.id="editRole";
		document.body.appendChild(dialogDiv);
		var m='${medalCategoryId}';
		if(m==""){
		$.messager.alert("提示信息","请选择勋章分类！","info");
			return ;
		}
		$('#editRole').dialog({   
		    modal:true,
		    href:'<%=rootPath %>medal/toaddmedal.action?categoryId=${medalCategoryId}',
		    title:'添加勋章',
		    width:500,
		    height:350,
		    resizable:true,
		    buttons: [{
		    	text: '提交', 
		    	iconCls: 'icon-ok', 
		    	handler: function() {
		    	$('input[name="jsonFile"]').val(JSON.stringify(addJsonFileList));
		    		$('#addMedal').form('submit',{
		    			url : '<%=rootPath %>medal/addMedal.action',
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
		    					$.messager.alert("提示信息","添加勋章成功！","info");
		    					refresh();
		    				}else{
		    					$.messager.alert("提示信息","添加勋章失败！","error");
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
	//删除勋章
	function pldelete(){
		var medalIds = "";
		$('#roleList').find("input:checkbox[name='medalid']:checked").each(function(){medalIds+=','+this.value;});
		if(medalIds){
			medalIds = medalIds.substring(1);
			$.messager.confirm('提示信息','是否要删除这些勋章？',function(r){   
    		    if(r){
    		    	$.messager.progress({
	    				text:'提交数据中...'
	    			});
   		    		$.ajax({
   	    				url: "<%=rootPath %>medal/deletemoremedal.action",
   	    			   	type: "POST",
   	    			   	data: {
   	    			   	medalids: medalIds
   	    			   	},
   	    			   	success: function(msg){
   	    			   		$.messager.progress('close');
   	    			   		$.messager.alert("提示信息","成功删除了"+msg+"个勋章","info");
   	    			   		refresh();
   	    			   	},
   	    			 	error:function(){
	    			 		$.messager.progress('close');
	    			 		$.messager.alert('提示信息','删除勋章失败！','error');
	    			 	}
   		    		});
    		    }
    		});
		}else{
			$.messager.alert("提示信息","请选择要删除的商品。","warning");
		}
	}
	
	///////删除
	function deletemedal(_medalid){
			$.messager.confirm('提示信息','是否要删除这条数据？',function(r){   
    		    if(r){
   		    		$.ajax({
   	    				url: "<%=rootPath %>medal/deleteMedal.action",
   	    			   	type: "POST",
   	    			   	data: {
   	    			   		medalid: _medalid,
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
	function editmedal(_medalid){
		var dialogDiv = document.createElement("div");
		dialogDiv.id="editmedal";
		document.body.appendChild(dialogDiv);
		$('#editmedal').dialog({
			modal:true,
			href:'<%=rootPath %>medal/findMedalById.action?medalid='+_medalid,
			title:'修改勋章',
			resizable:true,
		    width:400,
		    height:350,
		    buttons: [{
		    	text: '提交', 
		    	iconCls: 'icon-ok', 
		    	handler: function() {
		    	
		    	$('input[name="jsonFile"]').val(JSON.stringify(_allattacheds));
		    		$('#addMedal').form('submit',{
		    			url : '<%=rootPath %>medal/updateMedal.action',
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
		    				$('#editmedal').dialog('destroy');
		    				$.messager.progress('close');
		    				if(data>0){
		    					refresh();
		    				}else{
		    					$.messager.alert("提示信息","修改勋章数据失败！","error");
		    				}
		    			}
		    		});
		    	} 
		    	},{ 
		    	text: '取消', 
		    	iconCls: 'icon-cancel', 
		    	handler: function() { 
		    		$('#editmedal').dialog('destroy');
		    	} 
		    }],
		    onClose:function(){
		    	$('#editmedal').dialog('destroy');
		    }
		});
	}
	function detail(_id){
		window.open("<%=rootPath %>medal/medalDetial.action?medalid="+_id+"&d="+new Date().getTime(),"_blank");
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
<div id="listSearchType1" style="width:120px;">  
    <div data-options="name:'medalname'">勋章名称</div>
</div>
<table width="100%" id="roleList" border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
	<tr class="list_th" style="position: relative;">
   	  	<td colspan="4" align="left" style="padding-left: 10px;" nowrap="nowrap">
   	  		
   	  		<a href="javascript:void(0)" onclick="addMedal()">添加</a>
   	  		<a href="javascript:void(0)" onclick="pldelete()">删除</a>
   	  		
			<a href="javascript:void(0)" onclick="refresh()">刷新 </a>
   	  	</td>
   	  	<td colspan="4" align="right" style="padding-right: 2px;"><input id='listSearchText1' /></td>
	</tr>
	
    <tr>
	    <td width="6%" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="checkAll()">全选</a></td>
	    <td width="13%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('medalname','<s:if test="orderField=='medalname'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">勋章名称<s:if test="orderField=='medalname'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='medalname'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="11%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('minworth','<s:if test="orderField=='minworth'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">最低兑换值<s:if test="orderField=='minworth'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='minworth'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="9%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('maxworth','<s:if test="orderField=='maxworth'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">最高兑换值<s:if test="orderField=='maxworth'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='maxworth'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="12%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('crtime','<s:if test="orderField=='crtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">添加时间<s:if test="orderField=='crtime'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='crtime'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="11%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('categoryname','<s:if test="orderField=='categoryname'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">勋章分类<s:if test="orderField=='categoryname'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='categoryname'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="6%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('medalnum','<s:if test="orderField=='medalnum'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">勋章库存<s:if test="orderField=='medalnum'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='medalnum'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="9%" align="center" bgcolor="#F5FAFE"  class="main_bright">操作</td>
    </tr>
    <s:iterator value="listmedal" status="listStat">
    <tr>
	    <td align="center" bgcolor="#F5FAFE"  class="main_bleft"><input type="checkbox" name="medalid" value="<s:property value="medalid" />" /> <s:property value="(pageNum-1)*pageSize+#listStat.count"/></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="detail('<s:property value="medalid" />')" _tableName="<s:property value="medalname" />"><s:property value="medalname" /></a></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="minworth" /></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="maxworth" /></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright" align="center"><s:date name="crtime" format="yyyy-MM-dd HH:mm:ss" /></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="categoryname" /></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="medalnum" /></td>
	    <td align="center" valign="middle" bgcolor="#F5FAFE"  class="main_bright">
	    <img border="0" src="images/icons/pencil.png" title="修改" style="cursor:pointer; margin: 0 5px;" onclick="editmedal(<s:property value="medalid" />)" />
	    &nbsp;&nbsp;
	    <img border="0" src="images/icons/cancel.png" title="删除" style="cursor:pointer; margin: 0 5px;" onclick="deletemedal(<s:property value="medalid" />)"/>
	    </td>
    </tr>
    </s:iterator>
    <tr bgcolor="#FFFFFF">
		<td colspan="10" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
	</tr>
</table>
</body>
</html>
