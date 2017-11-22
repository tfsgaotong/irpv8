<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="../css/css_body.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
//全局变量，
var m_checked = false;

function jump(_sUrl){
	$('body').layout('panel','center').panel('refresh',_sUrl);
}
//全选
function checkAll(){
	m_checked = !m_checked;
	$("input[name='checkbox_opertypeId']").attr("checked",m_checked); 
}

//分页
function page(_nPageNum){
	$('#pageNum').val(_nPageNum);
	var queryString = $('#pageForm').serialize();
	jump("<%=rootPath%>set/opertype_set_list.action?"+queryString);
}
//排序
function orderBy(_sFiled,_sOrderBy){
	$('#orderField').val(_sFiled);
	$('#orderBy').val(_sOrderBy);
	var queryString = $('#pageForm').serialize();
	jump("<%=rootPath%>set/opertype_set_list.action?"+queryString);
}
//刷新
function operRefresh(){
	$('body').layout('panel','center').panel('refresh');
}

//增加
function createOpertype(){
	var dialogdiv=document.createElement("div");
	dialogdiv.id="createopertype";
	document.body.appendChild(dialogdiv);

	$('#createopertype').dialog({
	    modal:true,
	    cache:false,
        href:'<%=rootPath%>set/opertype_set_add.action',
		title:'添加操作类型',
		width:400,
		height:200,
		resizable:true,
        buttons:[{
        	text: '提交', 
	    	iconCls: 'icon-ok', 
	    	handler: function(){
				$('#opertypeInsert').form('submit',{
					url:"<%=rootPath%>set/opertypeAdd.action",
					onSubmit:function(){
	    				var isValid = $(this).form('validate');
	    				if (isValid){
	   						$.messager.progress({
	   	    					text:'提交数据中...'
	    	    			});
	    				}
	    				return isValid;
					},
					success:function(){
						jump("<%=rootPath%>set/opertype_set_list.action");
						$.messager.progress('close');
						$('#createopertype').dialog('destroy');
					}
				});
			}
		},
		{
			text: '取消',
			iconCls: 'icon-cancel', 
			handler: function(){
				$('#createopertype').dialog('destroy');
			}
		}],
		onClose:function(){
			$('#createopertype').dialog('destroy');
		}  
	});
}

//修改
function updateEdit(_opertypeUpdate){
 var dialogdiv=document.createElement("div");
 dialogdiv.id="updateOpertype";
 document.body.appendChild(dialogdiv);
 $('#updateOpertype').dialog({
        modal:true,
        href:'<%=rootPath%>set/opertype_set_update.action?_opertypeUpdate='+_opertypeUpdate,
		title:'修改操作类型',
		width:400,
		height:200,
		resizable:true,
		buttons:[{
		 text:'提交',
		 iconCls: 'icon-ok',
		 handler:function(){
		   $('#opertypeInsert').form('submit',
		    {
		     url:'<%=rootPath%>set/opertypeUpdate.action?_opertypeUpdate='+_opertypeUpdate,
		    onSubmit:function(){
		    	var isValid = $(this).form('validate');
  				if (isValid){
  					$.messager.progress({
  	    				text:'提交数据中...'
  	    			});
  				}
  				return isValid;
		    },		 
	         success:function(nCount){
	        	 $.messager.progress('close');
	        	 $('#updateOpertype').dialog('destroy');
		    	  jump("<%=rootPath%>set/opertype_set_list.action");
		    	  },
		    	  error:function(){
		    		  $.messager.progress('close');
			        	 $('#updateOpertype').dialog('destroy');
				    	  jump("<%=rootPath%>set/opertype_set_list.action");
		    	  }
		    });
		 
		   }
		},{
		  text:'取消',
		  iconCls: 'icon-cancel',
		  handler: function(){
		  $('#updateOpertype').dialog('destroy');
		
		  }
		
		}
		],
		onClose:function(){
		    	$('#updateOpertype').dialog('destroy');
		    	
		    }  
 
 });
}

   //删除
function deleteEdit(_opertypeDelete,_opertype){
$.messager.confirm("删除类型","您确定要删除"+_opertype+"吗?",function(r){   
    if (r){ 
        $.messager.progress({
			text:'提交数据中...'
		});
       $.ajax({
       type:'post',
       url:'<%=rootPath%>set/opertypeDelete.action?_opertypeDelete='+_opertypeDelete,
       success:function(){
    	$.messager.progress('close');
       jump("<%=rootPath%>set/opertype_set_list.action");
        },
        error:function(){
        	$.messager.progress('close');	
        }
       });
    }   
});  

}   
//多选删除
function deleteAllOpertype(){
var _opertypeId;


$("input[name='checkbox_opertypeId']:checked").each(
function(){
_opertypeId+=$(this).val() + ',';
}
);
if (_opertypeId==null) {
	$.messager.alert("操作提示","请您先选择要删除的对象");
	return false;
}

var _opertypeIdLength=$("input[name='checkbox_opertypeId']:checked").length;

$.messager.confirm("操作提示","您确定要删除选中的"+_opertypeIdLength+"条记录吗",function(r){
if(r){
	   $.messager.progress({
			text:'提交数据中...'
		});
  $.ajax({
   type:'post',

   url:'<%=rootPath%>set/opertypeDeleteAll.action?deleteAllOpertype='+_opertypeId,
     success:function(){
    	 $.messager.progress('close');
        jump("<%=rootPath%>set/opertype_set_list.action");
     },
     error:function(){
    	 $.messager.progress('close');
     }
});
}
}
);



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
	    	jump("<%=rootPath%>set/opertype_set_list.action?"+queryString);
	    },   
	    menu:'#listSearchType',   
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
	</form>
	<div id="listSearchType" style="width:120px;">
		<div data-options="name:'all'">全部&nbsp;&nbsp;&nbsp;&nbsp;</div>
		<div data-options="name:'opertype'">操作类型</div>
		<div data-options="name:'opername'">操作名称</div>
		<div data-options="name:'operdesc'">操作说明</div>
		<div data-options="name:'cruser'">创建者</div>
	</div>

	<table width="100%" border="0" align="center" cellpadding="10"
		cellspacing="1" bgcolor="#cad9ea">
		<tr class="list_th"
			style="position: relative;top:expression(this.offsetParent.scrollTop);">
			<td colspan="4" style="padding-left: 10px;"><a
				href="javascript:void(0)" onclick="createOpertype()">新建操作类型</a>&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="deleteAllOpertype()">删除操作类型</a>
				&nbsp;&nbsp;<a href="javascript:void(0)" onclick="operRefresh()">刷新</a>
				&nbsp;</td>
			<td colspan="4" align="right" style="padding-right: 2px;"><input
				id='listSearchText' /></td>
		</tr>
		<tr>
			<td align="center" width="60" bgcolor="#F5FAFE" class="main_bright"><a
				href="javascript:void(0)" onclick="checkAll()">全选</a></td>
			<td align="center" bgcolor="#F5FAFE" class="main_bright"><a
				href="javascript:void(0)"
				onclick="orderBy('opertype','<s:if test="orderField=='opertype'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">操作类型<s:if
						test="orderField=='opertype'&&orderBy=='desc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_down.gif" />
					</s:if> <s:elseif test="orderField=='opertype'&&orderBy=='asc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_up.gif" />
					</s:elseif>
			</a></td>
			<td align="center" bgcolor="#F5FAFE" class="main_bright" width="100"><a
				href="javascript:void(0)"
				onclick="orderBy('opername','<s:if test="orderField=='opername'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">操作名称<s:if
						test="orderField=='opername'&&orderBy=='desc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_down.gif" />
					</s:if> <s:elseif test="orderField=='opername'&&orderBy=='asc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_up.gif" />
					</s:elseif>
			</a></td>
			<td align="center" bgcolor="#F5FAFE" class="main_bright" width="100"><a
				href="javascript:void(0)"
				onclick="orderBy('operdesc','<s:if test="orderField=='operdesc'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">操作描述<s:if
						test="orderField=='operdesc'&&orderBy=='desc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_down.gif" />
					</s:if> <s:elseif test="orderField=='operdesc'&&orderBy=='asc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_up.gif" />
					</s:elseif>
			</a></td>
			<td align="center" bgcolor="#F5FAFE" class="main_bright" width="120"><a
				href="javascript:void(0)"
				onclick="orderBy('crtime','<s:if test="orderField=='crtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">创建时间<s:if
						test="orderField=='crtime'&&orderBy=='desc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_down.gif" />
					</s:if> <s:elseif test="orderField=='crtime'&&orderBy=='asc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_up.gif" />
					</s:elseif>
			</a></td>
			<td align="center" bgcolor="#F5FAFE" class="main_bright" width="80"><a
				href="javascript:void(0)"
				onclick="orderBy('cruser','<s:if test="orderField=='cruser'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">创建作者<s:if
						test="orderField=='cruser'&&orderBy=='desc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_down.gif" />
					</s:if> <s:elseif test="orderField=='cruser'&&orderBy=='asc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_up.gif" />
					</s:elseif>
			</a></td>
			<td align="center" bgcolor="#F5FAFE" class="main_bright" width="60">
				<a href="javascript:void(0)"
				onclick="orderBy('modified','<s:if test="orderField=='modified'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">是否可修改<s:if
						test="orderField=='modified'&&orderBy=='desc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_down.gif" />
					</s:if> <s:elseif test="orderField=='modified'&&orderBy=='asc'">
						<img src="<%=rootPath%>admin/images/spinner_arrow_up.gif" />
					</s:elseif>
			</a>
			</td>
			<td align="center" bgcolor="#F5FAFE" class="main_bright" width="60">操作</td>
		</tr>
		<s:iterator value="irpOpertypes" status="irpopertypestate">
			<tr>
				<td align="left" bgcolor="#F5FAFE" class="main_bright"><input
					name="checkbox_opertypeId" type="checkbox"
					value="<s:property value='opertypeid'/>"> <s:property
						value="(pageNum-1)*pageSize+#irpopertypestate.count" /></td>
				<td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property
						value="opertype" /></td>
				<td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property
						value="opername" /></td>
				<td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property
						value="operdesc" /></td>
				<td align="center" bgcolor="#F5FAFE" class="main_bright"><s:date
						name="crtime" format="yyyy-MM-dd HH:mm:ss" /></td>
				<td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property
						value="cruser" /></td>
				<s:if test="modified==1">
					<td style="text-align: center;" bgcolor="#F5FAFE"
						class="main_bright">是</td>
				</s:if>
				<s:if test="modified==0">
					<td style="text-align: center;" bgcolor="#F5FAFE"
						class="main_bright">否</td>
				</s:if>
				<td style="text-align: center;" bgcolor="#F5FAFE"
					class="main_bright"><img border="0"
					src="images/icons/pencil.png" title="修改"
					style="cursor:pointer; margin: 0 5px;"
					onclick="updateEdit(<s:property value="opertypeid"/>)" /> <img
					border="0" src="images/icons/cancel.png" title="删除"
					style="cursor:pointer; margin: 0 3px;"
					onclick="deleteEdit(<s:property value="opertypeid"/>,'<s:property value="opertype"/>')" /></td>
			</tr>
		</s:iterator>
		<tr bgcolor="#FFFFFF">
			<td colspan="8" align="right" class="page"><s:property
					value="pageHTML" escapeHtml="false" /></td>
		</tr>
	</table>
</body>
</html>