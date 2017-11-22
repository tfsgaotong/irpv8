<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />
<title>站点管理</title> 
<link rel="stylesheet" href="../css/icon.css" type="text/css"></link>
<script type="text/javascript" src="../js/jquery-1.8.0.min.js"></script> 
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>

<script type="text/javascript" src="../js/easyui-lang-zh_CN.js"></script></head> 
<body>
<script type="text/javascript">
function jump(_sUrl){   
	$('body').layout('panel','center').panel('refresh',_sUrl); 
}    
$(function(){   
		$('#allsitediv').accordion({   
				     onSelect : function(title,index){ 
				     var n_siteid=$('#allsitediv').accordion('getSelected').attr('siteid'); 
				     if(title!="新增知识库"){ 
				    	  if(title=="知识地图"){
				    		  var _sUrl="<%=rootPath%>site/to_documentmap.action?siteid="+n_siteid+"&channelorDocument=channel&id=<s:property value='irpChannel.channelid'/>";//右边默认显示栏目
								 jump(_sUrl);  //加载他的详细信息   
							     //同时需要加载里面的栏目树 //就是他自己的名称(根节点)  
							      $('#documentmap').tree({
										url:'<%=rootPath%>site/admindocumentmaptree.action', 
										animate:false,
										lines:true ,
										onClick : function(node){ 
													 var tab=$('#tab').tabs('getSelected');   
													 var tabid=tab.attr('id'); 
													 if(tabid=="dt"){ //查询栏目信息，右边默认选择栏目一栏  并且将栏目id（node.id）放在tab为文档的url里面，这样点击它可以加载他里面的文档
														 var _cUrl="<%=rootPath%>site/to_documentmap.action?siteid="+n_siteid+"&channelorDocument=channel&id="+node.id;
														 jump(_cUrl);//加载他的详细信息
												 	 }
													 else if(tabid=="wd"){
														 var _cUrl="<%=rootPath%>site/to_documentmap.action?siteid="+n_siteid+"&channelorDocument=document&id="+node.id;
														 jump(_cUrl);//加载他的详细信息
													 }
										},
										onContextMenu:function(e,node){ 
												e.preventDefault();
												//初始化菜单添加点击事件
												$('#channeloperation').menu({
													onClick:function(item){
														if(item.id=='updatechannelmenu'){
															updatemap(n_siteid,node.id);
														}else if(item.id=='removechannelmenu'){
															removemap(n_siteid,node.id);
														}
													}
												});
													//显示需要的菜单
												$('#channeloperation').menu('show', {
													left: e.pageX,
													top: e.pageY
												});
 										},
										onLoadSuccess:function(node, data){
											if(!node){
												var root = $('#documentmap').tree('getRoot');
												$('#documentmap').tree('select',root.target);
												$('#documentmap').tree('expand',root.target);
											} 
										}
								});   
				    	  }else if(title=="知识专题"){
				    		  var _sUrl="<%=rootPath%>site/to_documentSub.action?siteid="+n_siteid+"&channelorDocument=channel&id=<s:property value='irpChannelSub.channelid'/>";//右边默认显示栏目
								 jump(_sUrl);  //加载他的详细信息   
				    		  $('#documentsub').tree({
									url:'<%=rootPath%>site/documentSubjectMap.action', 
									animate:false,
									lines:true ,
									onClick : function(node){ 
												 var tab=$('#tab').tabs('getSelected');   
												 var tabid=tab.attr('id'); 
												 if(tabid=="lm"){ //查询栏目信息，右边默认选择栏目一栏  并且将栏目id（node.id）放在tab为文档的url里面，这样点击它可以加载他里面的文档
													 var _cUrl="<%=rootPath%>site/to_documentsubject.action?siteid="+n_siteid+"&channelorDocument=channel&id="+node.id;
													 jump(_cUrl);//加载他的详细信息
											 	 }
												 else if(tabid=="wd"){
													 var _cUrl="<%=rootPath%>site/to_documentsubject.action?siteid="+n_siteid+"&channelorDocument=document&id="+node.id;
													 jump(_cUrl);//加载他的详细信息
												 }
									},
									onLoadSuccess:function(node, data){
										if(!node){
											var root = $('#documentsub').tree('getRoot');
											$('#documentsub').tree('select',root.target);
											$('#documentsub').tree('expand',root.target);
										} 
									}
							});
				    	  }else if(title=="百科模版配置"){
				    	  
				    	  $.fn.zTree.init($("#categoryTreetemplate"), {
											view: {
												showIcon: true
											},
											data: {
												simpleData : {
													enable : !0,
													idKey : "id",
													pIdKey : "pId"
												}
											},
											async: {
												enable: true,
												url:"<%=rootPath%>category/getallcatetemplate.action?showExpertList=4" 
											},
											callback: {
												onClick: function (event, treeId, treeNode, clickFlag){
													if(treeNode){
													 	var cateid = treeNode.id;
														var _sUrl="<%=rootPath%>set/linkqtemlistbycid.action?cateid="+cateid;
													 	jump(_sUrl);
													 }
												},
												onAsyncSuccess:function(event, treeId, treeNode, msg){
												if(msg!=''){
												$('#categoryTreetemplate_1_a').click();
												}
												
												
												}
												
											} 
											
										});
										
				    	  
				    	  }else if(title=="知识模版配置"){
				    	  
  $.fn.zTree.init($("#categoryTreetemplateknow"), {
											view: {
												showIcon: true
											},
											data: {
												simpleData : {
													enable : !0,
													idKey : "id",
													pIdKey : "pId"
												}
											},
											async: {
												enable: true,
												url:"<%=rootPath%>category/getallcatetemplateknow.action?showExpertList=5" 
											},
											callback: {
												onClick: function (event, treeId, treeNode, clickFlag){
													if(treeNode){
//													 	var catetext = treeNode.name;
													 	var cateid = treeNode.id;
//													 	$('#choicecateids').val(catetext);
//														$('#choicecatevalues').val(cateid);
													//site_template_menu.jsp
													var _sUrl="<%=rootPath%>set/linkqtemlistbycidknow.action?cateid="+cateid;
													 jump(_sUrl);
													 }
												},
												onAsyncSuccess:function(event, treeId, treeNode, msg){
												if(msg!=''){
												$('#categoryTreetemplateknow_1_a').click();
												}
												
												
												}
												
											} 
											
										});
										
				    	  
				    	  }else{
				    		  var _sUrl="<%=rootPath%>site/to_channel_info.action?siteid="+n_siteid+"&channelorDocument=channel";//右边默认显示栏目
								 jump(_sUrl);  //加载他的详细信息   
							     //同时需要加载里面的栏目树 //就是他自己的名称(根节点)  
							      $('#chanels'+n_siteid).tree({
										url:'<%=rootPath%>site/site_allchannel.action?siteid='+n_siteid, 
										animate:false,
										lines:true ,
										onClick : function(node){  
											  if(node.id==0){ 
		     									jump(_sUrl);  
											}else{  
												 var tab=$('#tab').tabs('getSelected');   
												 var tabid=tab.attr('id');
												 if(tabid=="lm"){ //查询栏目信息，右边默认选择栏目一栏  并且将栏目id（node.id）放在tab为文档的url里面，这样点击它可以加载他里面的文档
											  		  var _cUrl="<%=rootPath%>site/to_channel_info.action?siteid="+n_siteid+"&channelorDocument=channel&id="+node.id;
												 	  jump(_cUrl);//加载他的详细信息
											 	 }
												 else if(tabid=="site"){ //查询栏目信息，右边默认选择栏目一栏  并且将栏目id（node.id）放在tab为文档的url里面，这样点击它可以加载他里面的文档
													  var _cUrl="<%=rootPath%>site/to_channel_info.action?siteid="+n_siteid+"&channelorDocument=channel&id="+node.id;
												 	  jump(_cUrl);//加载他的详细信息
											 	 }
											 	 else if(tabid=="mb"){  
												 	  $('#tab').tabs('update',{
									 	      					tab:tab,
									 	      					options : {
									 	      						title : '文档'
									 	      					}
									 	       		  });  
												 	    var _cUrl="<%=rootPath %>site/tree_site_or_chan_alldocLink.action?siteid="+n_siteid+"&channelorDocument=document&isGC=0&id="+node.id;
												 		tab.attr('link',_cUrl);    
												 		jump(_cUrl);//加载他的详细信息
											 	 }
											} 
										},  
										onContextMenu:function(e,node){ 
											e.preventDefault();
											if(node.id!="0"){
												//初始化菜单添加点击事件
												$('#channeloperation').menu({
													onClick:function(item){
														if(item.id=='updatechannelmenu'){
															updatechannel(n_siteid,node.id);
														}else if(item.id=='removechannelmenu'){
															removechannel(n_siteid,node.id);
														}
													}
												});
												//显示需要的菜单
												$('#channeloperation').menu('show', {
													left: e.pageX,
													top: e.pageY
												});
											 }
										},
										onLoadSuccess:function(node, data){
											if(!node){
												var root = $('#chanels'+n_siteid).tree('getRoot');
												$('#chanels'+n_siteid).tree('select',root.target);
												$('#chanels'+n_siteid).tree('expand',root.target);
											} 
										}
								});   
				    	  }
					}else{ //当点击新增站点时，右边页面变成添加站点页面
						  var _aUrl="<%=rootPath%>site/to_addsite.action";
						  jump(_aUrl);  
					}
				}
	 	});   
}); 
function updatemap(n_siteid, _channelid){
	var updatechanneldiv=document.createElement("div");
	 updatechanneldiv.id="updatechanneldiv";
	 document.body.appendChild(updatechanneldiv);  
	 $('#updatechanneldiv').dialog({ 
		    modal:true,
		    cache:false,
		    href:'<%=rootPath  %>site/to_updatemap.action?siteid='+n_siteid+'&channelid='+_channelid,
			title:'修改地图',
			width:350,
			height:230,
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
						    	  		if(data=="1"){
						    	  			var jqTreeObj = $('#documentmap');
						    	  		    var node = jqTreeObj.tree('find',_channelid);
						    	  		    var parentNode = jqTreeObj.tree('getParent',node.target);
						    	  		  	jqTreeObj.tree('reload', parentNode.target);
							    	  		$.messager.progress('close'); 
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
function removemap(n_siteid, _channelid){
	var jqTreeObj = $('#documentmap');
	var node = jqTreeObj.tree('find',_channelid);
	if(node){ 
		$.messager.confirm('警示信息','您确定要删除这【1】个知识地图吗？',function(r){
			if(r){
				$.ajax({  
					type: "POST", 
			       	async: false,   
			       	url:'<%=rootPath%>site/deletechanneltogc.action?n_siteid='+n_siteid,   
			      	data:{'channelids':_channelid},  
			      	dataType:"json",
			      	success:function(data){
			      		if(data!="0"){
							$.messager.alert('提示信息','成功删除到回收站【'+data+'】个地图','warning');
							var selectNode = jqTreeObj.tree('getSelected');
		        			var parentNode = jqTreeObj.tree('getParent',node.target);
		        			if(parentNode!=null && parentNode.id==selectNode.id){
		        				$('#dt').panel('refresh');
		        			}
		        			jqTreeObj.tree('remove',node.target);
			      		}
			      	}
				});
			}
		});
	}
}

function updatechannel(_siteid,_channelid){
	var updatechanneldiv=document.createElement("div");
	updatechanneldiv.id="updatechanneldiv";
	document.body.appendChild(updatechanneldiv); 
	$('#updatechanneldiv').dialog({
		modal:true,
	    cache:false,
        href:'<%=rootPath %>site/to_updatechannel.action?siteid='+_siteid+'&channelid='+_channelid,
		title:'修改栏目',
		width:350,
		height:230,
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
						if(data=="1"){
							var jqTreeObj = $('#chanels'+_siteid);
		    	  		    var node = jqTreeObj.tree('find',_channelid);
		    	  		    var parentNode = jqTreeObj.tree('getParent',node.target);
		    	  		  	jqTreeObj.tree('reload', parentNode.target);
			    	  		$.messager.progress('close'); 
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

function removechannel(_siteid,_channelid){
	var jqTreeObj = $('#chanels'+_siteid);
	var node = jqTreeObj.tree('find',_channelid);
	if(node){ 
		$.messager.confirm('警示信息','您确定要删除这【1】个栏目吗？',function(r){
			if(r){
				$.ajax({  
					type: "POST", 
			        async: false,   
			        url:'<%=rootPath%>site/deletechanneltogc.action',   
			        data:{
			        	'channelids':_channelid,
			       	 	'siteid':_siteid
					},  
			        dataType:"json",
			        success:function(data){
		        		if(data!="0"){
		        			$.messager.alert('提示信息','成功删除到回收站【'+data+'】个栏目','warning');
		        			var selectNode = jqTreeObj.tree('getSelected');
		        			var parentNode = jqTreeObj.tree('getParent',node.target);
		        			if(parentNode!=null && parentNode.id==selectNode.id){
		        				$('#lm').panel('refresh');
		        			}
		        			jqTreeObj.tree('remove',node.target);
		        		}
			        }
				});
			}
		});
	}
}
</script>  
<div id="allsitediv" fit="true" border="false">
	<s:iterator value="allsiteList" var="site">
		<div title="<s:property value='sitename'/>" siteid="<s:property value='siteid'/>">
			<ul id="chanels<s:property value='siteid'/>"></ul>  
        </div>
	</s:iterator> 
	<div title="<s:property value='irpChannel.chnldesc'/>" siteid="<s:property value='irpChannel.siteid'/>">
	<ul id="documentmap"></ul></div>
	<div title="<s:property value='irpChannelSub.chnldesc'/>" siteid="<s:property value='irpChannelSub.siteid'/>">
	<ul id="documentsub"></ul></div>
	<div title="百科模版配置" >
		<ul id="categoryTreetemplate" class="ztree" style="background-color: white;border: none;">
		
		
		</ul>
	</div>
	<div title="知识模版配置" >
		<ul id="categoryTreetemplateknow" class="ztree" style="background-color: white;border: none;">
		
		
		</ul>
	</div>
	 <%--<div title="新增知识库"  border="false"  style="padding:10px;" />  --%>
</div> 
<%--上下文菜单定义如下: --%>
<div id="channeloperation" class="easyui-menu" style="width:120px;display: none;">
	<div id="updatechannelmenu" data-options="iconCls:'icon-edit'">修改</div>
	<div id="removechannelmenu" data-options="iconCls:'icon-remove'">删除</div>
</div> 
</body>
</html>
