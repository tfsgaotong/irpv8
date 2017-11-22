<%@page import="com.tfs.irp.channel.entity.IrpChannel"%>
<%@page import="com.tfs.irp.util.SysConfigUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
int personId = SysConfigUtil.getSysConfigNumValue("DOCUMENT_PERSON_SUBJECT_ID");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html> 
  <body>
  <script type="text/javascript">
  //查看专题回收站中的专题
  function gctoallChannel(_siteid,_parentid){ 
	  var gcid="<s:property value='isGCChannel'/>"; 
  		//将上一级的div的地址刷新成现在的地址。
  	 	var panel = $('#tab').tabs('getSelected'); 
  	 	if(panel){
  	 		panel.panel('refresh','<%=rootPath %>site/gc_alldocumentsublist.action?siteid='+_siteid+'&id='+_parentid+'&isGCChannel=0');
  	 		//将选项卡名称改给专题回收站
  	 		 $('#tab').tabs('update',{
  	 						tab:panel,
  	 						options:{
  	 								title:'专题回收站' 
  	 						}
  	 		}); 
  	 		 panel.attr('link','<%=rootPath %>site/gc_alldocumentsublist.action?siteid='+_siteid+'&id='+_parentid+'&isGCChannel=0');
  	 	 } 
  }
  //进入正常专题的子专题中
  	function init(m_nSiteId,_nId){ 
  	 	//将上一级的div的地址刷新成现在的地址。
  	 	var gcid=<s:property value='isGCChannel'/>;
  	 	if(gcid==0){}
  	 	else{ 	//同时，将左边选中当前专题或者站点   
			     var node=$('#chanels'+m_nSiteId).tree('find',_nId);//找到当前需要选中的节点 
			     if(node){
				      $('#documentsub').tree('select',node.target); 
					  $('#documentsub').tree('reload',node.target);   
					  var _cUrl="<%=rootPath%>site/to_documentsub.action?siteid="+m_nSiteId+"&channelorDocument=channel&id="+_nId;
					  jump(_cUrl);//加载他的详细信息  
			     } 
  	 	} 
  	} 
  //普通企业专题
  function selected(){
	  var _siteid = <s:property value="siteid" />;
	  var _parentid = <s:property value="id" />;
	  $('#addchanneldiv').dialog({
		    modal:true,
		    cache:false,
	        href:'<%=rootPath%>site/to_addsub.action?siteid='+_siteid+'&id='+_parentid,
			title:'添加专题',
			width:350,
			height:200,
			resizable:true,
			maximizable:false,
	        buttons:[{
		    	text: '提交', 
		    	iconCls: 'icon-ok', 
		    	handler: function(){
		    		$('#addchannelfrm').form('submit',{
			    	  	  url:"<%=rootPath  %>site/addnormalchannel.action", 
			    	  	  	onSubmit : function(){
				  	 		 	  var isValid =  $('#addchannelfrm').form('validate');// 表单提交之前所需要做的事情，对表单进行验证
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
				    	  		 var node = $('#documentsub').tree('getSelected');
				    	  		 if(node){
				    	  			 //如果当前节点没有展开则刷父节点
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
				    	  			 //刷新左边选中的根节点
				    					$('#documentsub').tree('reload',node.target);
						    	  	  //同时需要刷新右边的专题页面
				    				 $('#lm').panel('refresh','<%=rootPath %>site/to_documentSub.action?channelorDocument=channel&siteid='+_siteid+'&id='+_parentid+'&isGCChannel=1');
							         
				    	  		 }
				    	  		 $('#addchanneldiv').dialog('destroy');
			    	  		}else{
			    	  				$messager.alert('提示信息','添加失败','warning');
			    	  		}
			    	  	}
			    	 });
		    	} 
		    },
		    {
		    	text: '取消',
		    	iconCls: 'icon-no', 
		        handler: function(){
		        $('#addchanneldiv').dialog('destroy');
		     }
		    }],
		       onClose:function(){
		    	$('#addchanneldiv').dialog('destroy');
		    }   
	});  
  }
  
//检索专题
  function superselected(){
	  var _siteid = <s:property value="siteid" />;
	  var _parentid = <s:property value="id" />;
	  $('#addchanneldiv').dialog({
		    modal:true,
		    cache:false,
	        href:'<%=rootPath%>site/to_addsupersub.action?siteid='+_siteid+'&id='+_parentid,
			title:'检索专题',
			width:350,
			height:225,
			resizable:true,
			maximizable:false,
	        buttons:[{
		    	text: '提交', 
		    	iconCls: 'icon-ok', 
		    	handler: function(){
		    		$('#addchannelfrm').form('submit',{
			    	  	  url:"<%=rootPath  %>site/addsuperchannel.action?id="+_parentid, 
			    	  	  	onSubmit : function(){
				  	 		 	  var isValid =  $('#addchannelfrm').form('validate');// 表单提交之前所需要做的事情，对表单进行验证
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
				    	  		 var node = $('#documentsub').tree('getSelected');
				    	  		 if(node){
				    	  			 //如果当前节点没有展开则刷父节点
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
				    	  			 //刷新左边选中的根节点
				    					$('#documentsub').tree('reload',node.target);
						    	  	  //同时需要刷新右边的专题页面
				    				 $('#lm').panel('refresh','<%=rootPath %>site/to_documentSub.action?channelorDocument=channel&siteid='+_siteid+'&id='+_parentid+'&isGCChannel=1');
							         
				    	  		 }
				    	  		 $('#addchanneldiv').dialog('destroy');
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
		        $('#addchanneldiv').dialog('destroy');
		     }
		    }],
		       onClose:function(){
		    	$('#addchanneldiv').dialog('destroy');
		    }   
	});  
  }
  
  
  //添加专题
  function addChannel(_siteid, _parentid){  
	var addchanneldiv=document.createElement("div");
	addchanneldiv.id="addchanneldiv";
	document.body.appendChild(addchanneldiv); 
	if(_parentid!=<%=personId%>){
		$('#addchanneldiv').dialog({
			modal:true,
		    cache:false,
 			title:'选择类型',
 			content:'<div style="line-height:59px;"><a href="javascript:void(0)" onclick="selectedwhich(0)" style="font-size:19px;font-weight:bold;margin-left:24%;">普通专题</a><a href="javascript:void(0)" onclick="selectedwhich(1)" style="font-size:19px;font-weight:bold;margin-left:5%;">检索专题</a></div>',
 			width:350,
			height:100,
			resizable:true,
			maximizable:false,
			onClose:function(){
			}
	 	});
	}else{
		selected();  
	}
  } 
  
  function selectedwhich(_a){
	  if(_a==0){
		  selected();  
	  }else{
		  superselected();
	  }
  }
  function deletechannel(_siteid){
  	//批量删除到回收站 
  	   var channelids=$("input[name='channelids']:checked");
  	   if(channelids.length==0){
  	   		$.messager.alert('提示信息','请选择需要删除到回收站的专题','warning'); 
  	   }else{
   			$.messager.confirm('提示信息','您确定要删除这'+channelids.length+'个专题吗？',function(r){
 				if(r){
					$('#showchannelidfrm').form('submit',{
		 		  		url : '<%=rootPath%>site/deletechanneltogc.action', 
			 		  	success : function(data){  
		 		  			$.messager.alert('提示信息','成功删除到回收站【'+data+'】个专题','warning');  
		 		  			 var node = $('#documentsub').tree('getSelected'); 
						     if(node){
						    	 //刷新左边选中的根节点
								  $('#documentsub').tree('reload',node.target); 
			 		  		      $('#lm').panel('refresh');
						     }
		 		  		}
	 		  		});  
 				} 
   		});  
  }
  }
  
  //点击修改图片时候修改
  function updatechannelbychannelid(_siteid,_channelid){ 
  
    	    var _parentid=$('#channelpageForm').find('input[name=id]').val(); 
     		var updatechanneldiv=document.createElement("div");
			 updatechanneldiv.id="updatechanneldiv";
			 document.body.appendChild(updatechanneldiv);  
			 var _id=<s:property value='id'/>;
			 $('#updatechanneldiv').dialog({ 
				    modal:true,
				    cache:false,
			        href:'<%=rootPath  %>site/to_updatemap.action?siteid='+_siteid+'&id='+_id+'&channelid='+_channelid,
					title:'修改专题',
					width:350,
					height:222,
					resizable:true,
					maximizable:false,
			        buttons:[{
					    	text: '修改', 
					    	iconCls: 'icon-ok', 
					    	handler: function(){
					    		 $('#addchannelfrm').form('submit',{
						    	  		url:"<%=rootPath%>site/updatechannel.action", 
						    	  		onSubmit : function(){ 
						    	  		 	var isValid= $('#addchannelfrm').form('validate');
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
								    	  		    var node = $('#documentsub').tree('getSelected');
								    	  		    $('#documentsub').tree('reload',node.target); 
									    	  	 	$('#lm').panel('refresh','<%=rootPath%>/site/to_documentSub.action?channelorDocument=channel&siteid='+_siteid+'&id='+_parentid+'&isGCChannel=1');
										    	  	$('#updatechanneldiv').dialog('destroy');
								    	  		}else{
								    	  		   $messager.alert('提示信息','修改失败','warning');
								    	  		}
							    	  	}
							    });
					    	}
					    },
					    {
					    	text: '取消',
					    	iconCls: 'icon-no', 
					        handler: function(){
					        $('#updatechanneldiv').dialog('destroy');
					     }
					    }],
					       onClose:function(){
					    	$('#updatechanneldiv').dialog('destroy');
					    }   
			});   
  }
  
  //刷新当前页面
  function lminfoinit(){
  	$('#lm').panel('refresh');
  } 
  //正常专题分页 
	function page(_nPageNum){
		$('#pageNum').val(_nPageNum);
		//取的搜索框里面的值放入表单中
	     $('#channelpageForm').find('input[name="searchWord"]').val(encodeURIComponent($('#checkallchannel').val()));
		var queryString = $('#channelpageForm').serialize(); 
	 	//得到isGCChannel的值若为1正常，0回收站
	 	var isGCChannel=$('#isGCChannel').val();
	 	if(isGCChannel!=0){
	 		$('#lm').panel('refresh',"<%=rootPath %>site/allsubjectlist.action?"+queryString);
	 	}else{
	 		$('#lm').panel('refresh',"<%=rootPath %>site/allsubjectlist.action?"+queryString);
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
				 	$('#lm').panel('refresh',"<%=rootPath %>site/allsubjectlist.action?"+queryString);  
			    } 
			}); 
	});
	//全选
	 var m_checked = false; 
	function checkallsite(){  
	   m_checked = !m_checked;
	   $("input[name='channelids']").attr("checked",m_checked); 
	 }
	//点击专题图标
	function clickDom(divDom){
		var ckBox = $(divDom).find("input:checkbox");
		var cked = ckBox.attr("checked");
		ckBox.attr("checked",!cked);
	}
  </script> 	 
  <form id="showchannelidfrm" method="post" id="showchannelidfrm">  
  	   <s:set var="amount" value="5"/>
  		<table width="100%" cellpadding="10" cellspacing="0">  
			<tr class="main_qbut">
 	           <td colspan="5" nowrap="nowrap" bgcolor="#cad9ea">
 	           <table width="100%" cellpadding="0" cellspacing="0">
 	           <tr>
 	           	<td width="50%" style="padding-left: 5px;">
 	           	        <a href="javascript:void(0)" onclick="checkallsite()">全选</a> 
	 	           		<a href="javascript:void(0)" onclick="addChannel(<s:property value='siteid'/>,<s:property value='id'/>)">增加</a>
	 	           	    <a href="javascript:void(0)" onclick="deletechannel(<s:property value='siteid'/>)">删除</a>  
	 	           	    <a href="javascript:void(0)" onclick="gctoallChannel(<s:property value='siteid'/>,<s:property value='id'/>)">专题回收站</a> 
	 	           	    <a href="javascript:void(0)" onclick="lminfoinit()">刷新</a> 
	 	        </td>
 	           	<td width="50%" align="right">
 	                    	<input name="checkallchannel" id="checkallchannel" value="<s:property value="searchWord"/>" />
							<div id="channelType">  
							    <div data-options="name:'all'">全部&nbsp;&nbsp;&nbsp;&nbsp;</div>
							    <div data-options="name:'chnlname'">唯一标识&nbsp;&nbsp;</div>
							    <div data-options="name:'chnldesc'">显示名称</div>
							</div></td>
 	           </tr>
 	           </table></td>
 	        </tr> 
 	           <s:if test="%{irpChannels.size==0}"> 
		  		<tr class="main_qbut">   
		  				<td colspan="5">
		  				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;未找到子专题...
		  				</td> 
		  		</tr> 
  			</s:if>
  			<tr class="main_qbut">   
  		 		 <s:iterator  value="irpChannels" status="listStat">
		  				<td width="20%" style="padding-top: 5px;" align="center"> 
		  					<div style="background-position:center 10px;" onclick="clickDom(this)" ondblclick="init(<s:property value='siteid'/>,<s:property value='channelid'/>)" >
				  				<a href="javascript:void(0)" title="<s:property value='chnldesc'/>" style=" text-decoration: none;">  
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
	       <tr bgcolor="#FFFFFF">
	       	<td colspan="5" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
	       </tr>
  		</table> 
  </form> 
   <form id="channelpageForm">
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
