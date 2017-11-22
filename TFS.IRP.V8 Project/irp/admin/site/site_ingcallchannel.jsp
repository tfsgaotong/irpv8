<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>回收站所有栏目列表</title> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page"> 
  </head> 
  <body>
  <script type="text/javascript"> 
  	//返回到正常栏目列表
  	function fanhuiallchannel(_siteid,_parentid){
  	  $('#lm').panel('refresh','<%=rootPath %>site/channel_info.action?siteid='+_siteid+'&channelorDocument=channel&id='+_parentid+'&isGCChannel=1');
  	  var panel = $('#tab').tabs('getSelected'); 
  	  if(panel){
  	      $('#tab').tabs('update',{
					tab:panel,
					options:{
							title:'栏目' 
					}
	      }); 
  	   panel.attr('link','<%=rootPath %>site/channel_info.action?siteid='+_siteid+'&channelorDocument=channel&id='+_parentid+'&isGCChannel=1');
  	  } 
   }
   
  //恢复栏目回收站里面的栏目
  function huifuchannelfromgc(_siteid){
       var channelids=$("input[name='channelids']:checked");
  	   if(channelids.length==0){
  	   			$.messager.alert('提示信息','请选择您要恢复的栏目','warning');
  	   }else{
  	   		$.messager.confirm('警示信息','您确定要恢复这'+channelids.length+'个栏目吗？',function(r){
				if(r){
  	   			    $('#showchannelidfrm').form('submit',{
			 		  	url : '<%=rootPath%>site/huifuchannelfromgc.action', 
			 		  	success : function(data){  
							$.messager.alert('提示信息','成功恢复【'+data+'】个栏目','warning');
							var jqTreeObj = $('#chanels'+_siteid);
							var node = jqTreeObj.tree('getSelected'); 
							if(node){
								//刷新左边选中的根节点
								if(!node.state){
		    						var _b0 = $(node.target);
		    						var _b1=_b0.find("span.tree-icon");
		    						if(_b1.hasClass("tree-file")){
		    							_b1.removeClass("tree-file").addClass("tree-folder tree-folder-open");
		    							var hit=$("<span class=\"tree-hit tree-expanded\"></span>").insertBefore(_b1);
		    							if(hit.prev().length){
		    								hit.prev().remove();
		    							}
		    						}
		    					}
								jqTreeObj.tree('reload',node.target);
								$('#lm').panel('refresh');
							}
			 		  	}
					}); 
  	   			} 
  	   		}); 
  	   } 
  }
   //刷新当前页面
  function lminfoinit(){
  	$('#lm').panel('refresh');
  } 
  //彻底删除
  function deleteallfromgc(){
	  var channelids=$("input[name='channelids']:checked");
	   if(channelids.length==0){
	     	$.messager.alert('提示信息','请选择您要删除的栏目','warning');
	   }else{
	   		 $.messager.confirm('警示信息','您确定要删除这'+channelids.length+'个栏目吗？',function(r){
	   		 	if(r){
   		 			 $('#showchannelidfrm').form('submit',{
			 		  	url : '<%=rootPath%>site/deletechannelfromgc.action', 
			 		  	success : function(data){ 
			 		  			$.messager.alert('提示信息','彻底删除【'+data+'】个栏目','warning');
			 		  			$('#lm').panel('refresh');
			 		  	}
			 		  }); 
	   		 	}
	   		 });
	   } 
  } 
  //栏目回收战中的分页
	function page(_nPageNum){
		$('#pageNum').val(_nPageNum);
		//取的搜索框里面的值放入表单中
	     $('#channelpageForm').find('input[name="searchWord"]').val(encodeURIComponent($('#checkallchannel').val()));
		var queryString = $('#channelpageForm').serialize(); 
	 	//得到isGCChannel的值若为1正常，0回收站
	 	var isGCChannel=$('#isGCChannel').val();
	 	if(isGCChannel!=0){
	 		$('#lm').panel('refresh',"<%=rootPath %>site/channel_info.action?"+queryString);
	 	}else{
	 		$('#lm').panel('refresh',"<%=rootPath %>site/gc_channel_info.action?"+queryString);
	 	} 
	} 
	///当进入到这个页面的时候就想该页面的搜索框初始化
	$(function(){
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
				 	 $('#lm').panel('refresh',"<%=rootPath %>site/gc_channel_info.action?"+queryString); 
			    } 
			}); 
	});
	//全选
	 var m_checked = false; 
	function checkallsite(){  
	   m_checked = !m_checked;
	   $("input[name='channelids']").attr("checked",m_checked); 
	 } 
  </script> 		
  
  <form id="showchannelidfrm" method="post" id="showchannelidfrm">  
  	<s:hidden name="id" />
  	<s:hidden name="siteid" />
  	   <s:set var="amount" value="5"/>
  		<table width="100%" cellpadding="10" cellspacing="0">  
			<tr class="main_qbut">
 	           <td colspan="5" nowrap="nowrap" bgcolor="#cad9ea">
	 	           <table width="100%" cellpadding="0" cellspacing="0">
	 	           <tr>
	 	           	<td width="50%" style="padding-left: 5px;">  
	 	           	    <a href="javascript:void(0)" onclick="checkallsite()">全选</a>
	 	           	    <a href="javascript:void(0)" onclick="huifuchannelfromgc(<s:property value='siteid'/>)">恢复</a> 
		 	            <a href="javascript:void(0)"  onclick="deleteallfromgc()">彻底删除 </a> 
		 	            <a href="javascript:void(0)" onclick="fanhuiallchannel(<s:property value='siteid'/>,<s:property value='id'/>)">回到栏目列表</a> 
		 	            <a href="javascript:void(0)" onclick="lminfoinit()">刷新</a>
		 	        </td>
	 	           	<td width="50%" align="right">
	 	           	<input name="checkallchannel" id="checkallchannel" value="<s:property value='searchWord'/>" />
								<div id="channelType">  
								    <div data-options="name:'all'">全部&nbsp;&nbsp;&nbsp;&nbsp;</div>
								    <div data-options="name:'chnlname'">唯一标识&nbsp;&nbsp;</div>
								    <div data-options="name:'chnldesc'">显示名称</div>
								</div></td>
	 	           </tr>
	 	           </table>
 	           </td>
 	        </tr>
 	        <s:if test="%{irpChannels.size==0}"> 
		  		<tr class="main_qbut">   
		  				<td colspan="5">
		  				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;未找到子栏目...
		  				</td> 
		  		</tr> 
  			</s:if>
  			<s:else>
  			<tr class="main_qbut">   
  		 		 <s:iterator  value="irpChannels" status="listStat">
		  				<td width="20%" style="padding-top: 5px;" align="center"> 
		  					<div style="background-position:center 10px;" onclick="clickDom(this)" ondblclick="init(<s:property value='siteid'/>,<s:property value='channelid'/>)" >
				  				<a href="javascript:void(0)" title=" <s:property  value='chnldesc'/>" style=" text-decoration: none;">  
				  				<input type="checkbox" name="channelids" onclick="this.checked=!this.checked" value="<s:property value='channelid'/>">
				  			    <s:if test="chnldesc.length()>4">
								    <s:property value='chnldesc.substring(0,4)'/>...
								 </s:if>   
								 <s:else>      
								     <s:property value='chnldesc'/>
								 </s:else>
				  				</a> 
		  					</div>
		  					<img onclick="updatechannelbychannelid(<s:property value='siteid'/>,<s:property value='channelid'/>)" style="position: relative; ;top:-50px;left: 30px; cursor: pointer;" border="0" alt="修改"  src="<%=rootPath %>admin/images/24.png" />
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
	  	</s:else>
	    <tr bgcolor="#FFFFFF">
	       	<td colspan="5" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
	    </tr>
  		</table> 
  </form> 
   <form id="channelpageForm" method="post">
		  <s:hidden name="pageNum" id="pageNum" />
		  <s:hidden name="pageSize" id="pageSize" />
		  <s:hidden name="orderField" id="orderField" />
		  <s:hidden name="orderBy" id="orderBy" /> 
		  <s:hidden name="siteid"  /> 
		  <s:hidden name="searchWord" id="searchWord"  />
	      <s:hidden name="searchType" id="searchType" />
		  <s:hidden name="id"/>
 		  <s:hidden name="isGCChannel"  id="isGCChannel"/>   
   </form>
  </body>
</html>
