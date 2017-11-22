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
	//全选
    var m_checked = false;//判定复选框的全选变量 
    function checkAll(){
		m_checked = !m_checked;
		$("input[name='chandocids']").attr("checked",m_checked); 
	}  
  //恢复正常状态
  function huifudocumentbydocid(){  
       var channelids=$("input[name='chandocids']:checked");
  	   if(channelids.length==0){ 
  	       $.messager.alert('提示信息','请选择你要恢复的文档','warning');
  	   }else{
  	   		$.messager.confirm('提示信息','你确定要恢复这'+channelids.length+'个文档吗？',function(r){
	  	   			if(r){
			  	   			$('#showalldocumentfrm2').form('submit',{
				 		  	url : '<%=rootPath%>site/huifudocumentfromgc.action', 
				 		  	success : function(data){  
				 		  		 $.messager.alert('提示信息','成功恢复【'+data+'】个文档','warning',function(){
				 		  		     $('#mb').panel('refresh'); 
				 		  		 }); 
				 		  	}
				 		  });  
			 		 }
  	   		});
 		  
 		}
  }
  //彻底删除文档，
  function deletedocumentbydocid(tablename){ 
    var channelids=$("input[name='chandocids']:checked");
  	   if(channelids.length==0){ 
  	       $.messager.alert('提示信息','请选择您要删除的文档','warning');
  	   }else{
  	   	   $.messager.confirm('警示信息','您确定要删除这'+channelids.length+'个文档吗？',function(r){
  	   	    if(r){
		   	   	   $('#showalldocumentfrm2').form('submit',{
		 		  	url : '<%=rootPath%>site/deletedocumentfromgc.action?formtablename='+tablename, 
		 		  	success : function(data){  
		 		  		$.messager.alert('提示信息','成功删除【'+data+'】个文档','warning');
		 		  		$('#mb').panel('refresh'); 
		 		  	}
		 		  }); 
		 	  }
	  	   }); 
 		  }
  }
  //回到正常文档列表
  function fanhuidocument(_siteid,_parentid){ 
     $('#mb').panel('refresh','<%=rootPath %>site/site_or_chan_alldocLink.action?channelorDocument=document&siteid='+_siteid+'&id='+_parentid); 
     var panel = $('#tab').tabs('getSelected');
     if(panel){
          $('#tab').tabs('update',{
 	      					tab:panel,
 	      					options : {
 	      						title : '文档'
 	      					}
 	       });
     	 panel.attr('link','<%=rootPath %>site/site_or_chan_alldocLink.action?channelorDocument=document&siteid='+_siteid+'&id='+_parentid);
     }  
  }
  //文档回收站中的文档分页
  function page(_nPageNum){   
        $('#pageNum').val(_nPageNum);
        //取的搜索框里面的值放入表单中
	    $('#channelpageForm').find('input[name="searchDocTitle"]').val(encodeURIComponent($('#checkalldocument').val()));
		var queryString = $('#wordpageForm').serialize();   
		//isGC为0正常，为1文档回收站   
		 $('#mb').panel('refresh',"<%=rootPath %>site/site_or_channel_alldocumentgc.action?"+queryString);   
	  
 }
 //排序
	function orderBy(_sFiled,_sOrderBy){
		$('#orderField').val(_sFiled);
		$('#orderBy').val(_sOrderBy);
		var queryString = $('#wordpageForm').serialize(); 
	    $('#mb').panel('refresh',"<%=rootPath %>site/site_or_channel_alldocumentgc.action?"+queryString);   
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
				     $('#mb').panel('refresh',"<%=rootPath %>site/site_or_channel_alldocumentgc.action?"+queryString);   
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
	function documentinfoinit(){
	  $('#mb').panel('refresh');
	}
	
	//删除数据
	function delFormInfos(){
		var ninfoIds = "";
		$('#roleUserList').find("input:checkbox[name='forminfoid']:checked").each(function(){ninfoIds+=','+this.value;});
		if(ninfoIds){
			ninfoIds = ninfoIds.substring(1);
			$.messager.confirm('提示信息','是否要删除这些数据？',function(r){   
    		    if(r){
   		    		$.ajax({
   	    				url: "<%=rootPath %>form/delforminfos.action",
   	    			   	type: "POST",
   	    			   	data: {
   	    			   		formid: ninfoIds,
   	    			   		formtablename: tablename
   	    			   	},
   	    			   	success: function(msg){
   	    			   		$.messager.alert("提示信息","成功删除 了"+msg+"条数据","info");
   	    			   		roleUserRefresh();
   	    			   	}
   		    		});
    		    }
    		});
		}else{
			$.messager.alert("提示信息","请选择要删除的数据","warning");
		}
	} 
	</script>    
	<form id="showalldocumentfrm"   method="post"></form>
	<form id="showalldocumentfrm2"   method="post"> 
      <table width="100%"  border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea" >
	   <tr>
		 <td colspan="8">  
		     <table cellpadding="0" cellspacing="0" width="100%" >
		     	<tr>
		     		<td width="50%" style=" padding-left: 5px;"> 
		     	        <a href="javascript:void(0)" onclick="huifudocumentbydocid()">恢复</a>
	  	 	   			<a href="javascript:void(0)" onclick="deletedocumentbydocid('<s:property value="formtablename" />')">彻底删除</a>
	  	 	   			<a href="javascript:void(0)" onclick="fanhuidocument(<s:property value='siteid'/>,<s:property value='id'/>)">回到文档列表</a> 
  	 	                <a href="javascript:void(0)" onclick="documentinfoinit()">刷新</a>  
  	 	            </td> 
 				 <td  align="right" style=" padding-right: 5px;">
 				 <input name="checkalldocument"  id="checkalldocument" value="<s:property value='searchDocTitle'/>"/></td>
		     	</tr>
		     </table>
		 </td> 
  	 	</tr>
  	 	<tr>
          <td width="5%" align="center" bgcolor="#F5FAFE" class="main_bleft">
          <a href="javascript:void(0)" style="width:100%" onclick="checkAll()" >全选</a></td>
          <td width="15%" bgcolor="#F5FAFE"  class="main_bright">
              <a href="#" onclick="orderBy('doctitle','<s:if test="orderField=='doctitle'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">
             		 文档标题<s:if test="orderField=='doctitle'&&orderBy=='desc'">
										<img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" />
							</s:if>
							<s:elseif test="orderField=='doctitle'&&orderBy=='asc'">
										<img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" />
							</s:elseif>
             		 
			</a></td>
			 <td width="15%" bgcolor="#F5FAFE"  class="main_bright">
              <a href="#" onclick="orderBy('channelid','<s:if test="orderField=='channelid'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">
             		 所属栏目<s:if test="orderField=='channelid'&&orderBy=='desc'">
										<img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" />
							</s:if>
							<s:elseif test="orderField=='channelid'&&orderBy=='asc'">
										<img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" />
							</s:elseif>
             		 
			</a></td> 
              <td width="10%"  bgcolor="#F5FAFE"  class="main_bright">
              <a href="#" onclick="orderBy('cruser','<s:if test="orderField=='cruser'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">
                                               创建者<s:if test="orderField=='cruser'&&orderBy=='desc'">
										<img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" />
							</s:if>
							<s:elseif test="orderField=='cruser'&&orderBy=='asc'">
										<img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" />
							</s:elseif>
              
              </a></td>
              <td width="10%" bgcolor="#F5FAFE"  class="main_bright">
              <a href="#" onclick="orderBy('docpubtime','<s:if test="orderField=='docpubtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">
			发稿时间<s:if test="orderField=='docpubtime'&&orderBy=='desc'">
										<img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" />
							</s:if>
							<s:elseif test="orderField=='docpubtime'&&orderBy=='asc'">
										<img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" />
							</s:elseif>
			</a></td>
			  <td width="10%"  bgcolor="#F5FAFE"  class="main_bright">
              <a href="#"  onclick="orderBy('deluser','<s:if test="orderField=='deluser'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">
                                    删除者<s:if test="orderField=='deluser'&&orderBy=='desc'">
										<img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" />
							</s:if>
							<s:elseif test="orderField=='deluser'&&orderBy=='asc'">
										<img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" />
							</s:elseif>
             </a></td>
              <td width="10%"  bgcolor="#F5FAFE"  class="main_bright">
              <a href="#"  onclick="orderBy('deltime','<s:if test="orderField=='deltime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">
                                    删除时间<s:if test="orderField=='deltime'&&orderBy=='desc'">
										<img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" />
							</s:if>
							<s:elseif test="orderField=='deltime'&&orderBy=='asc'">
										<img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" />
							</s:elseif>
             </a></td>
          </tr>
          <s:iterator value="chnlDocLinks" status="listStat">
          	<tr>
	          	 <td align="center" bgcolor="#F5FAFE"  class="main_bleft">
	             		 <input type="checkbox" name="chandocids" id="checkbox" value="<s:property value='docid'/>#<s:property value='chnldocid'/>"/>  
	               			<s:property value="(pageNum-1)*pageSize+#listStat.count"/>
	               </td>
	            
	              <td bgcolor="#F5FAFE"  class="main_bright">
	              <a href="<%=rootPath%>site/admin/adminpreviewdocument.action?docid=<s:property value="docid"/>" target="_blank"><s:property value='doctitle'/></a>
	              </td>
	                <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="chnldesc"/> </td> 
	              <td bgcolor="#F5FAFE"  class="main_bright"><s:property value='cruser'/></td> 
	              <td bgcolor="#F5FAFE"  class="main_bright"> 
	              	<s:date name="%{docpubtime}" format="yyyy-MM-dd HH:mm"/>
	              </td> 
	              <td bgcolor="#F5FAFE"  class="main_bright"> 
		               <s:property value="deluser"/>
	              </td>
	                <td bgcolor="#F5FAFE" class="main_bright">
	              			<s:date name="%{deltime}" format="yyyy-MM-dd HH:mm"/>
	              </td>
          	</tr>
       </s:iterator>
         <tr bgcolor="#FFFFFF">
	       	<td colspan="9"  align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
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
