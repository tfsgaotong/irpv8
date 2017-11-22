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
			$("input[name='docids']").attr("checked",m_checked); 
		} 
		//去添加文档
		function  toadddocument(_siteid,_checkChannelid){
		   window.open('<%=rootPath  %>site/to_adddocument.action?siteid='+_siteid+'&id='+_checkChannelid);  
		}
		//添加文档
		function addDocument(_siteid,_parentid){ 
		var _checkChannelid=0;
		//在这里判断跳到添加文档还是选择栏目页面
		if(_parentid==-1){
		 		//跳到选择栏目页面
				var checkchnneldiv=document.createElement("div");
				checkchnneldiv.id="checkchnneldiv";
				document.body.appendChild(checkchnneldiv);
				$('#checkchnneldiv').dialog({
				        modal:true,
					    cache:false,
				        href:'<%=rootPath  %>site/to_adddocument.action?siteid='+_siteid+'&id='+_parentid,
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
						    	    toadddocument(_siteid,_checkChannelid);  // 引用跳添加页面
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
		}else{ 
			toadddocument(_siteid,_parentid);// 引用跳添加页面
		}   
 	}
	 	//去修改文档信息
	function updateDocument(_docid,_channelid){  
	 window.open("<%=rootPath%>site/to_updatedocument.action?id="+_channelid+"&docid="+_docid);  
    }  
  //正常文档的分页
  function page(_nPageNum){   
        $('#pageNum').val(_nPageNum);
        //取的搜索框里面的值放入表单中
	    $('#channelpageForm').find('input[name="searchDocTitle"]').val(encodeURIComponent($('#checkalldocument').val()));
		var queryString = $('#wordpageForm').serialize();   
		//isGC为0正常，为1文档回收站    
		    $('#wd').panel('refresh',"<%=rootPath %>site/mapalldocument.action?"+queryString);  
 }
 //排序
	function orderBy(_sFiled,_sOrderBy){
		$('#orderField').val(_sFiled);
		$('#orderBy').val(_sOrderBy);
		var queryString = $('#wordpageForm').serialize(); 
	    $('#wd').panel('refresh',"<%=rootPath %>site/mapalldocument.action?"+queryString);   
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
				 	 $('#wd').panel('refresh',"<%=rootPath %>site/mapalldocument.action?"+queryString);   
				 	  
			    } 
			}); 
		 //当前页面是否全部选中
		var isCheckedAll = true;
		var checks = $("input[name='docids']");
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
function documentinfoinit(){ 
  $('#wd').panel('refresh');
} 
	 
 
//将文档简约表删除到文档回收站   //删除是删除一个简约表所以按照chnldocid删除，不是docid
 function deletedocumenttogc(){
	var channelids=$("input[name='docids']:checked");
		if(channelids.length==0){ 
			$.messager.alert('提示信息','请选择你要删除的文档','warning');
		}else{
			$.messager.confirm('提示信息','您确定要删除这'+channelids.length+'个知识吗？',function(r){
				if(r){
					$('#showalldocumentfrm2').form('submit',{
						url : '<%=rootPath%>site/delete_document_from_map.action',  
						success : function(data){ 
							if(data!="0"){
								$.messager.alert('提示信息','成功删除【'+data+'】个知识','warning',function(){
								 $('#wd').panel('refresh'); }); 
							}else{
								$.messager.alert('提示信息','删除失败','warning',function(){}); 
							}
						}
					});  
				}
			}); 
	}
}
//引用知识
 function quoteKnowledge(_id){
 	var dialogdiv=document.createElement("div");
  	dialogdiv.id="quotedivs";
  	document.body.appendChild(dialogdiv);
  	$('#quotedivs').dialog({
  		modal:true,
  		cache:false,
  		href:'<%=rootPath%>site/tocheckdocument.action?channelid='+_id,
  		height:500,
  		width:1000,
  		title:'知识分类',
  		resizable:true,
  		buttons:[{
  			text:'提交',
  			iconCls: 'icon-ok',
  			handler:function(){
  				 var str=$('#alreadyselectedknow').val();
  		           $.ajax({
  		        	   type:"post",
  		        	   url:"<%=rootPath%>site/givedocumentaddmap.action?flag=sub",
  		        	   data:{
  		        		 "docidlist":str,
  		        		 "id":_id 
  		        	   },
  		        	   success:function(dochtml){
							$('#quotedivs').dialog('destroy');
  		        		   if(dochtml!="0"){
  	  		        		  documentinfoinit();
  		        		   }
  		        	   }
  		           });
  		
  			}
  		},{
  			text:'取消',
  			iconCls:'icon-cancel',
  			handler:function(){
  				$('#quotedivs').dialog('destroy');
  			}
  		}],
  		onClose:function(){
  			$('#quotedivs').dialog('destroy');
  		}
  	});
 }

	</script>    
	<form id="showalldocumentfrm"   method="post"></form>
	<form id="showalldocumentfrm2"   method="post"> 
	<input name="id"  type="hidden" value="<s:property value='id'/>">
      <table width="100%"  border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea" >
	   <tr>
		 <td colspan="8">  
		     <table cellpadding="0" cellspacing="0" width="100%" >
		     	<tr>
		     		<td width="50%" style=" padding-left: 5px;">  
   	 	 	          <a href="javascript:void(0)" onclick="quoteKnowledge(<s:property value='id'/>)">增加</a> 
	  	 	          <a href="javascript:void(0)" onclick="deletedocumenttogc()">删除</a>
	 	 	          <a href="javascript:void(0)" onclick="documentinfoinit()">刷新</a>
	 	 	        </td>
		     		<td width="30%" align="center">
		     		     文档状态:  <select name="modalselect" id="modalselect">
		     		     <option value="0">--请选择--</option>
					  				<s:iterator value="irpDocstatus">
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
              <td width="8%" align="center" bgcolor="#F5FAFE" class="main_bleft"><a href="javascript:void(0)" style="width:100%" onclick="checkAll()" >全选</a></td>
            <td width="37%" bgcolor="#F5FAFE"  class="main_bright">
              <a href="#" onclick="orderBy('doctitle','<s:if test="orderField=='doctitle'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">
             		 文档标题<s:if test="orderField=='doctitle'&&orderBy=='desc'">
								 <img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" />
							</s:if>
							<s:elseif test="orderField=='doctitle'&&orderBy=='asc'">
										<img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" />
							</s:elseif>
             		 
			</a></td>
              <td width="10%"  bgcolor="#F5FAFE"  class="main_bright">
              <a href="#" onclick="orderBy('cruser','<s:if test="orderField=='cruser'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">
                                               作者<s:if test="orderField=='cruser'&&orderBy=='desc'">
										<img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" />
							</s:if>
							<s:elseif test="orderField=='cruser'&&orderBy=='asc'">
										<img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" />
							</s:elseif>
              
              </a></td>
               <td  width="15%" bgcolor="#F5FAFE"  class="main_bright">
               <a href="#" onclick="orderBy('crtime','<s:if test="orderField=='crtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">
               	创建时间<s:if test="orderField=='crtime'&&orderBy=='desc'">
										<img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" />
							</s:if>
							<s:elseif test="orderField=='crtime'&&orderBy=='asc'">
										<img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" />
							</s:elseif>
			</a></td>
              <td width="15%" bgcolor="#F5FAFE"  class="main_bright">
              <a href="#" onclick="orderBy('docpubtime','<s:if test="orderField=='docpubtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">
			发稿时间<s:if test="orderField=='docpubtime'&&orderBy=='desc'">
										<img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" />
							</s:if>
							<s:elseif test="orderField=='docpubtime'&&orderBy=='asc'">
										<img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" />
							</s:elseif>
			</a></td>
              <td width="15%"  bgcolor="#F5FAFE"  class="main_bright">
              <a href="#"  onclick="orderBy('docstatus','<s:if test="orderField=='docstatus'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">
                                     文档状态<s:if test="orderField=='docstatus'&&orderBy=='desc'">
										<img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" />
							</s:if>
							<s:elseif test="orderField=='docstatus'&&orderBy=='asc'">
										<img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" />
							</s:elseif>
             </a></td>
              </tr>
          <s:iterator value="irpDocumentList" status="listStat">
          	<tr>
	          	  <td align="center" bgcolor="#F5FAFE"  class="main_bleft">
	             	  <input type="checkbox" name="docids" id="checkbox" value="<s:property value='docid'/>"/>
	             	  <s:property value="(pageNum-1)*pageSize+#listStat.count"/>
	              </td> 
	              <td bgcolor="#F5FAFE"  class="main_bright">
	              <a href="javascript:void(0)"> <s:property value="doctitle"/> </a>
	              </td>
	              <td bgcolor="#F5FAFE"  class="main_bright"> <s:property value="cruser"/> </td>
	               <td bgcolor="#F5FAFE"  class="main_bright">
	                   <s:date name="%{crtime}" format="yyyy-MM-dd HH:mm"/>
	               </td>
	              <td bgcolor="#F5FAFE"  class="main_bright"> 
	              	<s:date name="%{docpubtime}" format="yyyy-MM-dd HH:mm"/>
	              </td> 
	              <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="findStatusNameByStatusId(docstatus)" />
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
		  <s:hidden name="searchDocTitle" id="searchDocTitle" />   
	      <s:hidden name="searchModal" id="searchModal" />
		  <s:hidden name="siteid"  />  
		  <s:hidden name="id"  />   
		  <s:hidden name="isGC" id="isGC" value="%{isGC }"/>
	</form> 
  </body>
</html>
