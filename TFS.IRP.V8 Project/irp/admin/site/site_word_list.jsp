<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>表单数据</title>
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />
</head>

<body>
<script type="text/javascript">
var tablename = '<s:property value="formtablename"/>';
var address = '<s:property value="newpage"/>';		
var m_roleChecked = false;
	$(function(){
		$('#listSearchText').searchbox({
			width:220,
		    searcher:function(value,name){   
		    	$('#roleUserPageForm').find('input[name="searchWord"]').val(value);
		    	$('#roleUserPageForm').find('input[name="searchType"]').val(name);
		    	$('#roleUserPageForm').find('input[name="pageNum"]').val('1');
		    	$('#roleUserPageForm').find('input[name="orderField"]').val('');
		    	$('#roleUserPageForm').find('input[name="orderBy"]').val('');
		    	var queryString = $('#roleUserPageForm').serialize();
		    	$('#mb').panel('refresh',"<%=rootPath %>site/site_or_chan_alldocLink.action?"+queryString);
		    },   
		    menu:'#listSearchType',   
		    prompt:'请输入检索词'  
		});
	});
	//刷新
	function documentinfoinit(){ 
	  $('#mb').panel('refresh');
	} 
	//分页
	function page(_nPageNum){
		$('#roleUserPageForm').find('input[name="pageNum"]').val(_nPageNum);
		var queryString = $('#roleUserPageForm').serialize();
		$('#mb').panel('refresh',"<%=rootPath %>site/site_or_chan_alldocLink.action?"+queryString);
	}
	//排序
	function roleUserOrderBy(_sFiled,_sOrderBy){
		$('#roleUserPageForm').find('input[name="orderField"]').val(_sFiled);
		$('#roleUserPageForm').find('input[name="orderBy"]').val(_sOrderBy);
		var queryString = $('#roleUserPageForm').serialize();
		$('#mb').panel('refresh',"<%=rootPath %>site/site_or_chan_alldocLink.action?"+queryString);
	}
	
	//全选
	function checkRoleUserAll(){
		m_roleChecked = !m_roleChecked;
		$('#roleUserList').find("input:checkbox[name='forminfoid']").attr("checked",m_roleChecked);
	}
	
	//跳转到新添加文档页面
		function addDocument1(_siteid,_parentid){ 
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
						    	    toadddocument1(_siteid,_checkChannelid);  // 引用跳添加页面
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
			toadddocument1(_siteid,_parentid);// 引用跳添加页面
		}   
 	}
 	//去添加文档
		function  toadddocument1(_siteid,_checkChannelid){
		if(address==null||address==""){
			window.open('<%=rootPath  %>site/to_adddocument.action?siteid='+_siteid+'&id='+_checkChannelid);  
		}else{
		   window.open('<%=rootPath  %>'+address);  
		}
		}
	//将文档简约表删除到文档回收站   //删除是删除一个简约表所以按照chnldocid删除，不是docid
  function deletedocumenttogc(){
    var channelids=$("input[name='chandocids']:checked");
  	   if(channelids.length==0){ 
  	         $.messager.alert('提示信息','请选择你要删除的文档','warning');
  	   }else{
  	   		$.messager.confirm('提示信息','您确定要删除这'+channelids.length+'个到文档回收站吗？',function(r){
  	   			if(r){
  	   			 $('#showalldocumentfrm2').form('submit',{
		 		  	url : '<%=rootPath%>site/deletedocumentlinktogc.action',  
		 		  	success : function(data){  
		 		  		if(data!="0"){
							$.messager.alert('提示信息','成功删除【'+data+'】个知识','warning',function(){
							 $('#mb').panel('refresh'); }); 
						}else{
							$.messager.alert('提示信息','删除失败','warning',function(){}); 
						} 
		 		  	}
		 		  });  
  	   			}
  	   		}); 
 		 }
  }
  
  //刷新到站点或者栏目下的文档回收站
  function site_or_channel_alldocumentgc(_siteid,_parentid){//站点id或者栏目id 
          var panel = $('#tab').tabs('getSelected'); 
           if(panel){ 
 	      	panel.panel('refresh','<%=rootPath %>site/site_or_channel_alldocumentgc.action?channelorDocument=document&siteid='+m_nSiteId+'&id='+_parentid+'&isGC=1');
          	$('#tab').tabs('update',{
 	      					tab:panel,
 	      					options : {
 	      						title : '文档回收站'
 	      					}
 	      	});
 	      	 panel.attr('link','<%=rootPath %>site/site_or_channel_alldocumentgc.action?channelorDocument=document&siteid='+m_nSiteId+'&id='+_parentid+'&isGC=1');
 	 	 } 
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
	function delFormInfo(_forminfoid){
			$.messager.confirm('提示信息','是否要删除这条数据？',function(r){   
    		    if(r){
   		    		$.ajax({
   	    				url: "<%=rootPath %>form/delforminfo.action",
   	    			   	type: "POST",
   	    			   	data: {
   	    			   		formid: _forminfoid,
   	    			   		formtablename: tablename
   	    			   	},
   	    			   	success: function(msg){
   	    			   		$.messager.alert("提示信息","成功删除了这条数据","info");
   	    			   		roleUserRefresh();
   	    			   	}
   		    		});
    		    }
    		});
	}
	function formInfoEdit(_forminfoid){
		var count = $.ajax({
			type:'post',
			url:'<%=rootPath %>form/countcolumn.action?formtablename='+tablename,
			async: false,
			cache: false
		}).responseText;
		var dheight=225;
		if(count>0){
		dheight = count*60;
		if(dheight>400){
		dheight=400;
		}
		}
		var dialogDiv = document.createElement("div");
		dialogDiv.id="editinfo";
		document.body.appendChild(dialogDiv);
		$('#editinfo').dialog({
			modal:true,
			href:'<%=rootPath %>form/tofindobjetpage.action?formtablename='+tablename+'&formid='+_forminfoid,
			title:'数据修改',
			resizable:true,
		    width:350,
		    height:dheight,
		    buttons: [{
		    	text: '提交', 
		    	iconCls: 'icon-ok', 
		    	handler: function() {
		    		$('#editForm').form('submit',{
		    			url : '<%=rootPath %>form/updateforminfo.action?formtablename='+tablename,
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
		    				$('#editinfo').dialog('destroy');
		    				$.messager.progress('close');
		    				if(data==1){
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
		    		$('#editinfo').dialog('destroy');
		    	} 
		    }],
		    onClose:function(){
		    	$('#editinfo').dialog('destroy');
		    }
		});
	}
	function copyinfoaddjsp(tablename,_docid){
		//if(randomdata==2){
			randomdata=Math.random();
		//}
		
		var url=$.ajax({
		 				url: '<%=rootPath %>form/tofindobjetpage.action?formtablename='+tablename+'&docid='+_docid,
		  			   	type: 'POST',
		  			   	async: false,
						cache: false
		   		}).responseText;
		   		//alert(url);
		   		var now=new Date();
				var date=1900+now.getYear()+""+(now.getMonth()+1)+""+now.getDate()+""+now.getHours()+""+now.getMinutes()+""+now.getSeconds();
				var fileName=date;
				$.ajax({
		 				url: "<%=rootPath %>sheet/copyupdatejsp.action",
		  			   	type: "POST",
		  			   	data:{content:url,formtablename:tablename,randomdata:randomdata},
		  			   	success: function(data){
		  			   	if(data!=0){
							
		  			   	}
		  			   	}
		   		});
	}
	
	/////////////修改知识
	function updatedocument(_docid){
	copyinfoaddjsp(tablename,_docid);
		var updatepageurl = $.ajax({
			type:'post',
			url:'<%=rootPath %>sheet/findurl.action?formtablename='+tablename,
			async: false,
			cache: false
		}).responseText;
		window.open('<%=rootPath%>'+updatepageurl);
		
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
		    					//documentinfoinit();
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
</script>

<form id="roleUserPageForm">
<s:hidden name="searchWord" id="searchWord" />
<s:hidden name="searchType" id="searchType" />
<s:hidden name="formtablename" id="formtablename" />
<s:hidden name="pageNum" id="pageNum" />
<s:hidden name="pageSize" id="pageSize" />
<s:hidden name="orderField" id="orderField" />
<s:hidden name="orderBy" id="orderBy" />
</form>
<form id="showalldocumentfrm2"   method="post">
<div id="listSearchType" style="width:120px;"> 
<div data-options="name:'all'">全部&nbsp;&nbsp;</div>
	<s:iterator value="irpFormColumnList"> 
    <div data-options="name:'<s:property value="columntablenamecol"/>'"><s:property value="columnname"/>&nbsp;&nbsp;</div>
    </s:iterator>
</div>
 <s:set var="wd" value="irpFormColumnList.size()"></s:set>
<table id="roleUserList" width="100%"  border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
	<tr class="list_th" style="position: relative;">
   	  	<td colspan="3" align="left" style="padding-left: 10px;" nowrap="nowrap">
   	  		<a href="javascript:void(0)" onclick="addDocument1(<s:property value='siteid'/>,<s:property value="id"/>)">增加</a> 
   	 	 	<a href="javascript:void(0)" onclick="bindingcolumn('<s:property value="formtablename" />',<s:property value="id"/>)">绑定字段</a>
   	  		<a href="javascript:void(0)" onclick="deletedocumenttogc()">删除</a>
   	  		<a href="javascript:void(0)" onclick="site_or_channel_alldocumentgc(<s:property value='siteid'/>,<s:property value='id'/>)">文档回收站</a>
			<a href="javascript:void(0)" onclick="documentinfoinit()">刷新</a>
			<s:iterator value="attacheds" >
			<a  href="<%=rootPath%>file/readfileformexcel.action?srcfile=<s:property value='srcfile'/>&fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>">下载模版</a></s:iterator>
			<a href="javascript:void(0)" onclick="importtotable()">导入数据</a>
			<a href="javascript:void(0)" onclick="exportToZIP()">导出数据</a><a id="daochu"></a>
   	  	</td>
   	  	<td colspan="<s:property value='#wd'/>" align="right" style="padding-right: 2px;"><input id='listSearchText' /></td>
	</tr>
    <tr>
     <td width="6%" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="checkRoleUserAll()">全选</a></td>
    	<s:iterator value="irpFormColumnList">
    	
    	 <td width="<s:property value="87/#wd"/>%" align="center" bgcolor="#F5FAFE" class="main_bright">
    	  	<a href="javascript:void(0)" onclick="roleUserOrderBy('<s:property value="columntablenamecol"/>','<s:if test="orderField==columntablenamecol&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">
    	  		<s:property value="columnname"/>
    	  	<s:if test="orderField==columntablenamecol&&orderBy=='desc'">
    	  	<img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" />
    	  	</s:if>
    	  	<s:elseif test="orderField==columntablenamecol&&orderBy=='asc'">
    	  	<img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" />
    	  	</s:elseif>
    	  	</a>
    	  </td>
    	</s:iterator>	   
	    <td width="7%" align="center" bgcolor="#F5FAFE"  class="main_bright">操作</td>
    </tr>
    
    
    
    
    
    <s:iterator value="formInfoList" var="list" status="listStat">
    <tr>
	    <td align="center" bgcolor="#F5FAFE" class="main_bleft"><input type="checkbox" name="chandocids"  value="<s:property value="DOCID" />#<s:property value="DOCID" />" /> <s:property value="(pageNum-1)*pageSize+#listStat.count"/></td>	     
	     	<s:iterator value="irpFormColumnList">
	     	<s:iterator  value="#list" status="listststsu">
	     	<s:if test="#listststsu.first==false">
	     		<s:if test="key.indexOf(columntablenamecol)!=-1">
			     	<td bgcolor="#F5FAFE"  class="main_bright"> 
			     		<s:property value="value"/>
			     	</td>
	     		</s:if>
	     	</s:if>
	     	</s:iterator>
	     	</s:iterator>   
	     <td align="center" valign="middle" bgcolor="#F5FAFE"  class="main_bright"><img border="0" src="images/icons/pencil.png" title="修改" style="cursor:pointer; margin: 0 5px;" onclick="updatedocument(<s:property value="DOCID" />)" />
	     <img border="0" src="images/icons/cancel.png" title="删除" style="cursor:pointer; margin: 0 5px;" onclick="delFormInfo(<s:property value="FORMINFOID" />)"/></td>
    </tr>
    </s:iterator>
    <tr bgcolor="#FFFFFF">
		<td colspan="<s:property value='#wd+2'/>" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
	</tr>
</table>
</form>
</body>
</html>
