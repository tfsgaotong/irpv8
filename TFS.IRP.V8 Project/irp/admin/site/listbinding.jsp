<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>文档-栏目中间表信息</title>   
  </head> 
  <body>   
	<script type="text/javascript">
	   var m_checked = false;//判定复选框的全选变量
	  		//全选
	    function checkAll(){
			m_checked = !m_checked;
			$("input[name='bindingid']").attr("checked",m_checked); 
		} 
 //分页
	function page(_nPageNum){
		$('#wordpageForm').find('input[name="pageNum"]').val(_nPageNum);
		var queryString = $('#wordpageForm').serialize();
		$('#tab').tabs('getSelected').panel('refresh',"<%=rootPath %>binding/listBindingByChannelid.action?"+queryString);
	}
 //排序
	function orderBy(_sFiled,_sOrderBy){
		$('#orderField').val(_sFiled);
		$('#orderBy').val(_sOrderBy);
		var queryString = $('#wordpageForm').serialize(); 
	    $('#showallbindings').panel('refresh',"<%=rootPath %>binding/listBindingByChannelid.action?"+queryString);   
	}
  //当进入页面时候，对检索框进行初始化
  $(function(){
  		 $('#checkalldocument').searchbox({
				width:240, 
			    prompt:'请输入文档标题',
			    searcher:function(value,name){ 
			    	$('#wordpageForm').find('input[name="searchDocTitle"]').val(encodeURIComponent(value)); 
			    	$('#wordpageForm').find('input[name="pageNum"]').val('1');
			    	$('#wordpageForm').find('input[name="orderField"]').val('');
			    	$('#wordpageForm').find('input[name="orderBy"]').val(''); 
			    	$('#wordpageForm').find('input[name="searchModal"]').val($('#modalselect').val());
			    	  var queryString = $('#wordpageForm').serialize(); 
				 	 $('#mb').panel('refresh',"<%=rootPath %>site/site_or_chan_alldocLink.action?"+queryString);   
				 	  
			    } 
			}); 
		 //当前页面是否全部选中
		var isCheckedAll = true;
		var checks = $("input[name='chandocids']");
		for(var i=0;i<checks.length;i++){
			if(checks[i] && !checks[i].checked){
				isCheckedAll = false;
				break;
			}
		}
		if(isCheckedAll){
			m_checked = true;
		} 
  });
  	
	//刷新
	function roleUserRefresh(){
		$('#tab').tabs('getSelected').panel('refresh');
	}
	//绑定字段
	function bindingcolumn(tablename,channelid){
		var dialogDiv = document.createElement("div");
		dialogDiv.id="bindingcolumn";
		document.body.appendChild(dialogDiv);
		$('#bindingcolumn').dialog({
			modal:true,
			href:'<%=rootPath %>form/listcolumn.action?formtablename='+tablename+'&channelid='+channelid,
			title:'绑定字段',
			resizable:true,
		    width:350,
		    height:230,
		    buttons: [{
		    	text: '提交', 
		    	iconCls: 'icon-ok', 
		    	handler: function() {
		    		$('#binding').form('submit',{
		    			url : '<%=rootPath %>binding/addBinding.action?formtablename='+tablename,
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
		    				$('#bindingcolumn').dialog('destroy');
		    				$.messager.progress('close');
		    				if(data>0){
		    					roleUserRefresh();
		    					$.messager.alert("提示信息","绑定字段成功！","info");
		    				}else{
		    					$.messager.alert("提示信息","绑定字段失败！","error");
		    				}
		    			}
		    		});
		    	} 
		    	},{ 
		    	text: '取消', 
		    	iconCls: 'icon-cancel', 
		    	handler: function() { 
		    		$('#bindingcolumn').dialog('destroy');
		    	} 
		    }],
		    onClose:function(){
		    	$('#bindingcolumn').dialog('destroy');
		    }
		});
	}
	///////删除
	function deleteBinding(_bindingid){
			$.messager.confirm('提示信息','是否要删除这条数据？',function(r){   
    		    if(r){
   		    		$.ajax({
   	    				url: "<%=rootPath %>binding/deleteBinding.action",
   	    			   	type: "POST",
   	    			   	data: {
   	    			   		bindingid: _bindingid,
   	    			   	},
   	    			   	success: function(msg){
   	    			   		$.messager.alert("提示信息","成功删除了这条数据","info");
   	    			   		roleUserRefresh();
   	    			   	}
   		    		});
    		    }
    		});
	}
	//////////修改
	function updateBinding(_bindingid){
		var dialogDiv = document.createElement("div");
		dialogDiv.id="editbinding";
		document.body.appendChild(dialogDiv);
		$('#editbinding').dialog({
			modal:true,
			href:'<%=rootPath %>binding/toeditbindingpage.action?bindingid='+_bindingid,
			title:'数据修改',
			resizable:true,
		    width:350,
		    height:230,
		    buttons: [{
		    	text: '提交', 
		    	iconCls: 'icon-ok', 
		    	handler: function() {
		    		$('#updateBinding').form('submit',{
		    			url : '<%=rootPath %>binding/updatebinding.action',
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
		    				$('#editbinding').dialog('destroy');
		    				$.messager.progress('close');
		    				if(data>0){
		    					roleUserRefresh();
		    				}else{
		    					$.messager.alert("提示信息","修改表单数据失败！","error");
		    				}
		    			}
		    		});
		    	} 
		    	},{ 
		    	text: '取消', 
		    	iconCls: 'icon-cancel', 
		    	handler: function() { 
		    		$('#editbinding').dialog('destroy');
		    	} 
		    }],
		    onClose:function(){
		    	$('#editbinding').dialog('destroy');
		    }
		});
	}
	///////////////批量删除
  function deletebindings(){
	
	var bindingids = "";
	$('#showallbindings').find("input:checkbox[name='bindingid']:checked").each(function(){bindingids+=','+this.value;});
	
	if(bindingids){
		bindingids = bindingids.substring(1);
		$.messager.confirm('提示信息','是否要删除这些字段组？',function(r){   
		    if(r){
		    	$.messager.progress({
	    				text:'提交数据中...'
	    			});
	    		$.ajax({
   	    				url: "<%=rootPath %>binding/deleteBindings.action",
   	    			   	type: "POST",
   	    			   	data: {
   	    			   	bindingids: bindingids
   	    			   	},
   	    			   	success: function(msg){
   	    			   		$.messager.progress('close');
   	    			   		$.messager.alert("提示信息","成功删除了"+msg+"个字段组","info");
   	    			   		roleUserRefresh();
   	    			   	},
   	    			 	error:function(){
	    			 		$.messager.progress('close');
	    			 		$.messager.alert('提示信息','删除字段组失败！','error');
	    			 	}
   		    		});
				} 
		});
	}else{
		$.messager.alert("提示信息","请选择要删除的字段组。","warning");
	}
	
}
	</script>
	<form id="showallbindings"   method="post"> 
      <table width="100%"  border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea" >
	   <tr>
		 <td colspan="8">  
		     <table cellpadding="0" cellspacing="0" width="100%" >
		     	<tr>
		     		<td width="50%" style=" padding-left: 5px;"> 
   	 	 	          <a href="javascript:void(0)" onclick="bindingcolumn('<s:property value="formtablename" />',<s:property value="channelid"/>)">增加</a> 
	  	 	          <a href="javascript:void(0)" onclick="deletebindings()">删除</a>
	 	 	          <a href="javascript:void(0)" onclick="roleUserRefresh()">刷新</a>
	 	 	        </td>
		     		<td width="30%" align="center">
		     		     所属栏目:  <select name="modalselect" id="modalselect">
		     		     <option value="0">--请选择--</option>
					  				<s:iterator value="listchannel">
					  					<option value="<s:property value='statusid'/>" <s:if test="searchModal==statusid">selected</s:if> >
					  						<s:property  value="sname"/> 
					  					</option>
					  				</s:iterator>
				  				</select>
 				 </td>
 				 <td align="right" style=" padding-right: 5px;">
 				 <input name="checkalldocument"  id="checkalldocument" value="<s:property value='searchDocTitle'/>"/> </td>
		     	</tr>
		     </table>
		 </td> 
  	 	</tr>
  	 	<tr>
              <td width="5%" align="center" bgcolor="#F5FAFE" class="main_bleft"><a href="javascript:void(0)" style="width:100%" onclick="checkAll()" >全选</a></td>
			 <td width="13%" bgcolor="#F5FAFE"  class="main_bright">
             		 所属栏目
             		 
			</td>
              <td width="8%"  bgcolor="#F5FAFE"  class="main_bright">
                                               绑定字段
              
              </td>
               <td  width="12%" bgcolor="#F5FAFE"  class="main_bright">
               	文章表字段
			</td>
               <td width="5%" bgcolor="#F5FAFE" class="main_bright"><a href="#"  style="width:100%"><u><font color="#000000">操作</font></u></a></td>
              </tr>
          <s:iterator value="listbinding" status="listStat">
          	<tr>
	          	  <td align="center" bgcolor="#F5FAFE"  class="main_bleft">
	             	  <input type="checkbox" name="bindingid" id="checkbox" value="<s:property value='bindingid'/>"/>  
	              <s:property value="(pageNum-1)*pageSize+#listStat.count"/>
	              </td> 
	              <td bgcolor="#F5FAFE"  class="main_bright">
	              <s:property value="channelid"/>
	              </td>
	                <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="bindingcolumn"/> </td>
	              <td bgcolor="#F5FAFE"  class="main_bright"> <s:property value="doccolumn"/> </td>
	              
	                <td bgcolor="#F5FAFE" class="main_bright"> 
	             	<img border="0" src="images/icons/pencil.png" title="修改" style="cursor:pointer; margin: 0 5px;" onclick="updateBinding(<s:property value="bindingid" />)"/>
	             	<img border="0" src="images/icons/cancel.png" title="删除" style="cursor:pointer; margin: 0 5px;" onclick="deleteBinding(<s:property value="bindingid" />)"/>
	              </td>
          	</tr>
       </s:iterator>
         <tr bgcolor="#FFFFFF">
	       	<td colspan="8"  align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
	    </tr>
  	 </table>  
  	</form>
   <form id="wordpageForm">
		  <s:hidden name="pageNum" id="pageNum" />
		  <s:hidden name="pageSize" id="pageSize" />
		  <s:hidden name="orderField" id="orderField" />
		  <s:hidden name="orderBy" id="orderBy" /> 
		 <!--  <s:hidden name="searchDocTitle" id="searchDocTitle" />   
	      <s:hidden name="searchModal" id="searchModal" />
		  <s:hidden name="siteid"  />   -->
		  <s:hidden name="channelid"  />   
		 <!--  <s:hidden name="isGC" id="isGC" value="%{isGC }"/> -->
</form> 
  </body>
</html>
