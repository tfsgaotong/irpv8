<%@page import="com.tfs.irp.channel.entity.IrpChannel"%>
<%@page import="com.tfs.irp.util.SysConfigUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html> 
  <body>
  <script type="text/javascript">
  //新增類別
  function addCategory(){
	  var _parentid = <s:property value="cid" />;
	  var addCategoryDiv = document.createElement("div");
	  addCategoryDiv.id="addCategoryDiv";
	  document.body.appendChild(addCategoryDiv);
	  $('#addCategoryDiv').dialog({
		    modal:true,
		    cache:false,
	        href:'<%=rootPath %>category/toAddCategory.action?cid='+_parentid+'&insertorupdate=insert',
			title:'新增分类',
			width:350,
			height:225,
			resizable:true,
			maximizable:false,
	        buttons:[{
		    	text: '提交', 
		    	iconCls: 'icon-ok', 
		    	handler: function(){
		    		$('#addCategoryfrm').form('submit',{
			    	  	  url:"<%=rootPath  %>category/addCategory.action?cid="+_parentid, 
			    	  	  onSubmit : function(){
			  	 		 	  var isValid =  $('#addCategoryfrm').form('validate');// 表单提交之前所需要做的事情，对表单进行验证
			  	 		 	  if (isValid){
			    					$.messager.progress({
			    	    				text:'提交数据中...'
			    	    			});
			    				}
				    		   return isValid;
				  	 		}, 
			    	  		success:function(data){
			    	  	  		$.messager.progress('close');
			    	  				if(data=="1"){   
			    	  					var treeObj = $.fn.zTree.getZTreeObj("questionManage");
					    				treeObj.reAsyncChildNodes(null, "refresh",true);
						    			$('#fl').panel('refresh','<%=rootPath %>category/childCategory.action?categoryorfile=category&cid='+_parentid);
						    	  		$('#addCategoryDiv').dialog('destroy');
			    	  				}else{
			    	  					$.messager.alert('提示信息','添加失败','warning');
			    	  				}
			    	  			}
			    	 		});
		    			} 
		    		},
		   	 {
		    	text: '取消',
		    	iconCls: 'icon-no', 
		        handler: function(){
		        $('#addCategoryDiv').dialog('destroy');
		     }
		    }],
		       onClose:function(){
		    	$('#addCategoryDiv').dialog('destroy');
		    }   
	});  
  }
  
  //批量删除
  function deletechannel(){
   var _parentid=<s:property value='cid' />;
   var categories=$("input[name='categoryIds']:checked");
   if(categories.length==0){
   		$.messager.alert('提示信息','请选择需要删除的类别','warning'); 
   }else{
		$.messager.confirm('提示信息','您确定要删除这'+categories.length+'个类别吗,其子类也将会被删除',function(r){
			if(r){
				$('#showcategoryidfrm').form('submit',{
				  		url : '<%=rootPath%>category/deletecategory.action?parentId='+_parentid, 
		 		  	success : function(data){ 
		 		  		if(data!='0'){   
		 		  			$.messager.alert('提示信息','成功删除【'+data+'】个类别','warning');
    	  					var treeObj = $.fn.zTree.getZTreeObj("questionManage");
		    				treeObj.reAsyncChildNodes(null, "refresh",true);
			    			$('#fl').panel('refresh','<%=rootPath %>category/childCategory.action?categoryorfile=category&cid='+_parentid);
			    	  		$('#addCategoryDiv').dialog('destroy');
    	  				}else{
    	  					$.messager.alert('提示信息','删除失败','warning');
    	  				}
				  	}
			  	});  
			} 
		});  
  	}
  }
  
  //点击修改图片时候修改
  function updateCategoryById(categoryId){ 
    	    var _parentid=<s:property value='cid' />;
     		var updatediv=document.createElement("div");
     		updatediv.id="updatediv";
			document.body.appendChild(updatediv);  
			$('#updatediv').dialog({ 
				    modal:true,
				    cache:false,
			        href:'<%=rootPath %>category/toUpdateCategory.action?selfId='+categoryId+'&cid='+_parentid,
					title:'修改分类',
					width:350,
					height:222,
					resizable:true,
					maximizable:false,
			        buttons:[{
					    	text: '修改', 
					    	iconCls: 'icon-ok', 
					    	handler: function(){
					    		 $('#addCategoryfrm').form('submit',{
						    	  		url:"<%=rootPath%>category/updateCategory.action?cid="+_parentid, 
						    	  		onSubmit : function(){ 
						    	  		 	var isValid= $('#addCategoryfrm').form('validate');
						    	  		 	if (isValid){
						    					$.messager.progress({
						    	    				text:'提交数据中...'
						    	    			});
						    				}
						    				return isValid;
						    	  		},
							    	  	success:function(data){ 
							    	  		 $.messager.progress('close');
							    	  		 if(data=="1"){   
					    	  					var treeObj = $.fn.zTree.getZTreeObj("questionManage");
							    				treeObj.reAsyncChildNodes(null, "refresh",true);
								    			$('#fl').panel('refresh','<%=rootPath %>category/childCategory.action?categoryorfile=category&cid='+_parentid);
								    			$('#updatediv').dialog('destroy');
					    	  				 }else{
					    	  					$.messager.alert('提示信息','修改失败','warning');
					    	  				 }
							    	  	}
							    });
					    	}
					    },
					    {
					    	text: '取消',
					    	iconCls: 'icon-no', 
					        handler: function(){
					        $('#updatediv').dialog('destroy');
					     }
					    }],
					       onClose:function(){
					    	$('#updatediv').dialog('destroy');
					    }   
			});
  }
  
  //刷新当前页面
  function lminfoinit(){
  	$('#fl').panel('refresh');
  } 
    //分页 
	function page(_nPageNum){
		$('#pageNum').val(_nPageNum);
		//取的搜索框里面的值放入表单中
	     $('#categorypageForm').find('input[name="searchWord"]').val(encodeURIComponent($('#checkallchannel').val()));
		var queryString = $('#categorypageForm').serialize(); 
	 	$('#fl').panel('refresh',"<%=rootPath %>category/childCategory.action?"+queryString);
	} 
	///当进入到这个页面的时候就想该页面的搜索框初始化
	<%-- $(function(){
		 $('#checkallchannel').searchbox({
				width:240,
			    menu:'#channelType', 
			    prompt:'请输入检索词',
			    searcher:function(value,name){ 
			    	$('#channelpageForm').find('input[name="searchWord"]').val(encodeURIComponent(value));
			    	$('#channelpageForm').find('input[name="searchType"]').val(name);
			    	$('#channelpageForm').find('input[name="pageNum"]').val('1');
			    	$('#channelpageForm').find('input[name="orderField"]').val('');
			    	$('#channelpageForm').find('input[name="orderBy"]').val('');   
			    	var queryString = $('#channelpageForm').serialize();
			    	var isGCChannel=$('#isGCChannel').val();  
				 	$('#lm').panel('refresh',"<%=rootPath %>site/allsubjectlist.action?"+queryString);  
			    } 
			}); 
	}); --%>
	//全选
	 var m_checked = false; 
	function checkallsite(){  
	   m_checked = !m_checked;
	   $("input[name='categoryIds']").attr("checked",m_checked); 
	 }
	//点击图标
	function clickDom(divDom){
		var ckBox = $(divDom).find("input:checkbox");
		var cked = ckBox.attr("checked");
		ckBox.attr("checked",!cked);
	}
	
	function init(obj){
		var cked = $(obj).attr("checked");
		$(obj).attr("checked",!cked);
	}
	
	function openup(_parentid){
		 var treeObj = $.fn.zTree.getZTreeObj("questionManage");
		 var node = treeObj.getNodeByParam("id", _parentid, null);
		 treeObj.expandNode(node, true, true, true);
	}
  </script> 	 
  <form id="showcategoryidfrm" method="post" id="showcategoryidfrm">  
  	   <s:set var="amount" value="5"/>
  		<table width="100%" cellpadding="10" cellspacing="0">  
			<tr class="main_qbut">
 	           <td colspan="5" nowrap="nowrap" bgcolor="#cad9ea">
 	           <table width="100%" cellpadding="0" cellspacing="0">
 	           <tr>
 	           	<td width="50%" style="padding-left: 5px;">
 	           	        <a href="javascript:void(0)" onclick="checkallsite()">全选</a> 
	 	           		<a href="javascript:void(0)" onclick="addCategory()">增加</a>
	 	           	    <a href="javascript:void(0)" onclick="deletechannel()">删除</a>  
	 	           	    <a href="javascript:void(0)" onclick="lminfoinit()">刷新</a> 
	 	           	    <a href="javascript:void(0)" onclick="openup(<s:property value='cid' />)">展开</a>
	 	        </td>
 	           </tr>
 	           </table></td>
 	        </tr> 
 	           <s:if test="categoryList==null || categoryList.size()==0"> 
		  		<tr class="main_qbut">   
		  				<td colspan="5">
		  				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;未找到子分类...
		  				</td> 
		  		</tr> 
  			</s:if>
  			<tr class="main_qbut">   
  		 		 <s:iterator  value="categoryList" status="listStat">
		  				<td width="20%" style="padding-top: 5px;" align="center"> 
		  					<div style="background-position:center 10px;" onclick="clickDom(this)" >
				  				<a href="javascript:void(0)" title="<s:property value='cname'/>" style=" text-decoration: none;">  
				  				<input type="checkbox" name="categoryIds" value="<s:property value='id'/>" onclick="init(this)">
				  			    <s:if test="cname.length()>4">
								    <s:property value='cname.substring(0,4)'/>...
								 </s:if>   
								 <s:else>      
								     <s:property value='cname'/>
								 </s:else>
				  				</a> 
		  					</div>
		  					<img onclick="updateCategoryById(<s:property value='id'/>)" style="position: relative; ;top:-50px;left: 30px; cursor: pointer;" border="0" alt="修改"  src="<%=rootPath %>admin/images/24.png" />
		  				</td>
		  				<s:set var="amount" value="#amount-1"/> 
	  					<s:if test="#listStat.count%5==0"> 
	  						<s:set var="amount" value="5"/>
	  		 				</tr><tr class="main_qbut">
	  					</s:if> 
	  			 </s:iterator> 
	  		     <s:bean name="org.apache.struts2.util.Counter" var="counter">
					<s:param name="first" value="1" />
					<s:param name="last" value="#amount"/>
					<s:iterator>
				     	<td width="20%" style="padding-top: 5px;" align="center"></td>
				   	</s:iterator>
				 </s:bean>
  			</tr>  
	       <tr bgcolor="#FFFFFF">
	       	<td colspan="5" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
	       </tr>
  		</table> 
  </form> 
   <form id="categorypageForm">
		  <s:hidden name="pageNum" id="pageNum" />
		  <s:hidden name="pageSize" id="pageSize" />
		  <s:hidden name="cid"/> 
   </form>
  </body>
</html>
