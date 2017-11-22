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
		$('#goodsManager').panel('refresh',"<%=rootPath %>goods/listGoods.action?"+queryString);
		<%-- $('body').layout('panel','center').panel('refresh',"<%=rootPath %>goods/listGoods.action?"+queryString); --%>
	}
	//排序
	function orderBy(_sFiled,_sOrderBy){
		$('#orderField').val(_sFiled);
		$('#orderBy').val(_sOrderBy);
		var queryString = $('#pageForm').serialize();
		$('#goodsManager').panel('refresh',"<%=rootPath %>goods/listGoods.action?"+queryString);
	}
	
	//全选
	function checkAll(){
		m_checked = !m_checked;
		$('#roleList').find("input:checkbox[name='goodsid']").attr("checked",m_checked); 
	}
	
	/* 刷新
	function refresh(){
		$('body').layout('panel','center').panel('refresh');
	} */
	//刷新
	function refresh(){
		$('#goodsManager').panel('refresh');
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
		    	$('body').layout('panel','center').panel('refresh',"<%=rootPath %>goods/listGoods.action?"+queryString);
		    },   
		    menu:'#listSearchType',   
		    prompt:'请输入检索词'  
		});
	});
	
	
	/*添加商品*/
	function addGoods(){
		var dialogDiv = document.createElement("div");
		dialogDiv.id="editRole";
		document.body.appendChild(dialogDiv);
		//var editor = CKEDITOR.instances.editor;
		//if(editor.goods){}
		$('#editRole').dialog({   
		    modal:true,
		    href:'<%=rootPath %>goods/toaddgoods.action',
		    title:'添加商品',
		    width:600,
		    height:550,
		    resizable:true,
		    buttons: [{
		    	text: '提交', 
		    	iconCls: 'icon-ok', 
		    	handler: function() {
		    	$('input[name="jsonFile"]').val(JSON.stringify(addJsonFileList));
		    		$('#addGoods').form('submit',{
		    			url : '<%=rootPath %>goods/addGoods.action',
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
		    					$.messager.alert("提示信息","添加商品成功！","info");
		    					refresh();
		    				}else{
		    					$.messager.alert("提示信息","添加商品失败！","error");
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
	//删除商品
	function pldelete(){
		var goodsIds = "";
		$('#roleList').find("input:checkbox[name='goodsid']:checked").each(function(){goodsIds+=','+this.value;});
		if(goodsIds){
			goodsIds = goodsIds.substring(1);
			$.messager.confirm('提示信息','是否要删除这些商品？',function(r){   
    		    if(r){
    		    	$.messager.progress({
	    				text:'提交数据中...'
	    			});
   		    		$.ajax({
   	    				url: "<%=rootPath %>goods/deletemoregoods.action",
   	    			   	type: "POST",
   	    			   	data: {
   	    			   	goodsids: goodsIds
   	    			   	},
   	    			   	success: function(msg){
   	    			   		$.messager.progress('close');
   	    			   		$.messager.alert("提示信息","成功删除了"+msg+"个商品","info");
   	    			   		refresh();
   	    			   	},
   	    			 	error:function(){
	    			 		$.messager.progress('close');
	    			 		$.messager.alert('提示信息','删除商品失败！','error');
	    			 	}
   		    		});
    		    }
    		});
		}else{
			$.messager.alert("提示信息","请选择要删除的商品。","warning");
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
		    width:600,
		    height:550,
		    buttons: [{
		    	text: '提交', 
		    	iconCls: 'icon-ok', 
		    	handler: function() {
		    	$('input[name="jsonFile"]').val(JSON.stringify(_allattacheds));
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
	function detail(_id){
		window.open("<%=rootPath%>goods/goodsDetial.action?goodsid="+_id+"&s="+new Date().getTime(),"_blank");
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
<div id="listSearchType" style="width:120px;">  
    <div data-options="name:'all'">全部&nbsp;&nbsp;&nbsp;&nbsp;</div>
    <div data-options="name:'goodsname'">商品名称</div>
    <div data-options="name:'goodsdesc'">商品描述</div>
    <div data-options="name:'salestate'">上架状态</div>
    <div data-options="name:'coverstate'">兑换状态</div>
</div>
<table width="100%" id="roleList" border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
	<tr class="list_th" style="position: relative;">
   	  	<td colspan="4" align="left" style="padding-left: 10px;" nowrap="nowrap">
   	  		
   	  		<a href="javascript:void(0)" onclick="addGoods()">添加</a>
   	  		<a href="javascript:void(0)" onclick="pldelete()">删除</a>
   	  		
			<a href="javascript:void(0)" onclick="refresh()">刷新 </a>
   	  	</td>
   	  	<td colspan="8" align="right" style="padding-right: 2px;"><input id='listSearchText' /></td>
	</tr>
    <tr>
	    <td width="6%" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="checkAll()">全选</a></td>
	    <td width="12%" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('goodsname','<s:if test="orderField=='goodsname'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">商品名称<s:if test="orderField=='goodsname'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='goodsname'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="12%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('goodsdesc','<s:if test="orderField=='goodsdesc'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">商品描述<s:if test="orderField=='goodsdesc'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='goodsdesc'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="8%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('needscore','<s:if test="orderField=='needscore'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">所需积分<s:if test="orderField=='needscore'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='needscore'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="8%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('price','<s:if test="orderField=='price'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">商品价格<s:if test="orderField=='price'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='price'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="6%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('stocknum','<s:if test="orderField=='stocknum'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">商品库存<s:if test="orderField=='stocknum'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='stocknum'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="8%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('salestate','<s:if test="orderField=='salestate'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">上架状态<s:if test="orderField=='salestate'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='salestate'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="6%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('coverstate','<s:if test="orderField=='coverstate'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">兑换状态<s:if test="orderField=='coverstate'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='coverstate'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="12%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('crtime','<s:if test="orderField=='crtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">添加时间<s:if test="orderField=='crtime'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='crtime'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="8%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('medalname','<s:if test="orderField=='medalname'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">勋章名称<s:if test="orderField=='medalname'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='medalname'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="5%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('reorder','<s:if test="orderField=='reorder'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">序号<s:if test="orderField=='reorder'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='reorder'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="9%" align="center" bgcolor="#F5FAFE"  class="main_bright">操作</td>
    </tr>
    <s:iterator value="listgoods" status="listStat">
    <tr>
	    <td align="center" bgcolor="#F5FAFE"  class="main_bleft"><input type="checkbox" name="goodsid" value="<s:property value="goodsid" />" /> <s:property value="(pageNum-1)*pageSize+#listStat.count"/></td>
	    <td  bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="detail('<s:property value="goodsid" />')" >
	    <s:if test="goodsname.length<8">
	    <s:property value="goodsname"/>
	    </s:if><s:else>
	    <s:property value="goodsname.substring(0,8)+'...'" />
	    </s:else>
	    
	    </a></td>
	    <td bgcolor="#F5FAFE"  class="main_bright">
	     <s:if test="goodsdesc.length<8">
	    <s:property value="goodsdesc"/>
	    </s:if><s:else>
	    <s:property value="goodsdesc.substring(0,8)+'...'" />
	    </s:else>
	    </td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="needscore" /></td>
	    <td  bgcolor="#F5FAFE"  class="main_bright"><s:property value="price" /></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="stocknum" /></td>
	    <td  bgcolor="#F5FAFE"  class="main_bright">
	    <s:if test="salestate==20">
			    已上架
	    </s:if>
	    <s:else>
			    未上架
	    </s:else>
	    </td>
	    <td bgcolor="#F5FAFE"  class="main_bright">
	    <s:if test="coverstate==20">
		    积分兑换
	    </s:if>
	    <s:else>
		   勋章兑换
	    </s:else>
	    </td>
	    <td  bgcolor="#F5FAFE" align="center" class="main_bright" align="center"><s:date name="crtime" format="yyyy-MM-dd HH:mm:ss" /></td>
	    <td  bgcolor="#F5FAFE" align="center" class="main_bright" align="center"><s:property value="medalname" /></td>
	    <td  bgcolor="#F5FAFE" align="center" class="main_bright" align="center"><s:property value="reorder" /></td>
	    <td  align="center" valign="middle" bgcolor="#F5FAFE"  class="main_bright">
	    <img border="0" src="images/icons/pencil.png" title="修改" style="cursor:pointer; margin: 0 5px;" onclick="editgoods(<s:property value="goodsid" />)" />
	    &nbsp;&nbsp;
	    <img border="0" src="images/icons/cancel.png" title="删除" style="cursor:pointer; margin: 0 5px;" onclick="deletegoods(<s:property value="goodsid" />)"/>
	    </td>
    </tr>
    </s:iterator>
    <tr bgcolor="#FFFFFF">
		<td colspan="12" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
	</tr>
</table>
</body>
</html>
