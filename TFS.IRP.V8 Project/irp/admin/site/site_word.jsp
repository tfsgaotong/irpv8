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
	var tablename = '<s:property value="formtablename"/>';
	var address = '<s:property value="newpage"/>';
	   var m_checked = false;//判定复选框的全选变量
	  		//全选
	    function checkAll(){
			m_checked = !m_checked;
			$("input[name='chandocids']").attr("checked",m_checked); 
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
		   window.open('<%=rootPath  %>'+address);  
		}
 	//添加表单数据
	function importInfo(tablename,channelid){
	alert(address);
		var count = $.ajax({
			type:'post',
			url:'<%=rootPath %>form/countcolumn.action?formtablename='+tablename,
			async: false,
			cache: false
		}).responseText;
		var dheight=225;
		if(count>0){
		dheight = count*70;
		if(dheight>400){
		dheight=400;
		}else if(dheight<=70){
		dheight=120;
		}
		}
		var dialogDiv = document.createElement("div");
		dialogDiv.id="importinfo";
		document.body.appendChild(dialogDiv);
		$('#importinfo').dialog({
			modal:true,
			href:'<%=rootPath %>'+address,
			title:'添加数据',
			resizable:true,
		    width:350,
		    height:dheight,
		    buttons: [{
		    	text: '提交', 
		    	iconCls: 'icon-ok', 
		    	handler: function() {
		    		$('#addForm').form('submit',{
		    			url : '<%=rootPath %>form/addforminfo.action?formtablename='+tablename+'&channelid='+channelid,
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
		    				$('#importinfo').dialog('destroy');
		    				$.messager.progress('close');
		    				if(data>0){
		    					documentinfoinit();
		    				}else{
		    					$.messager.alert("提示信息","添加表单数据失败！","error");
		    				}
		    			}
		    		});
		    	} 
		    	},{ 
		    	text: '取消', 
		    	iconCls: 'icon-cancel', 
		    	handler: function() { 
		    		$('#importinfo').dialog('destroy');
		    	} 
		    }],
		    onClose:function(){
		    	$('#importinfo').dialog('destroy');
		    }
		});
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
	
	 	//去修改文档信息
	function updateDocument(_docid,_channelid){  
	 window.open("<%=rootPath%>site/to_updatedocument.action?id="+_channelid+"&docid="+_docid);  
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
  //正常文档的分页
  <%-- function page(_nPageNum){   
        $('#pageNum').val(_nPageNum);
        //取的搜索框里面的值放入表单中
	    $('#channelpageForm').find('input[name="searchDocTitle"]').val(encodeURIComponent($('#checkalldocument').val()));
		var queryString = $('#wordpageForm').serialize();   
		//isGC为0正常，为1文档回收站    
		    $('#mb').panel('refresh',"<%=rootPath %>site/site_or_chan_alldocLink.action?"+queryString);  
 }
 //排序
	function orderBy(_sFiled,_sOrderBy){
		$('#orderField').val(_sFiled);
		$('#orderBy').val(_sOrderBy);
		var queryString = $('#wordpageForm').serialize(); 
	    $('#mb').panel('refresh',"<%=rootPath %>site/site_or_chan_alldocLink.action?"+queryString);   
	} --%>
	//正常文档的分页
  function page(_nPageNum){   
        $('#pageNum').val(_nPageNum);
        //取的搜索框里面的值放入表单中
	    $('#channelpageForm').find('input[name="searchDocTitle"]').val(encodeURIComponent($('#checkalldocument').val()));
		var limitTime = $('#timeselect').combobox('getValue');
		var date_start_time=$('#starttime').datebox('getValue');
		var date_end_time=$('#endtime').datebox('getValue'); 
		
		var user_name=$('#user_name').val();
		if(limitTime=="otherdate"){
			if(date_start_time>=date_end_time){
				$.messager.alert("提示","开始时间不能大于等于结束时间");
				return false;
			}
		}
    	
    	
    	$('#wordpageForm').find('input[name="timeLimit"]').val(limitTime);
    	$('#wordpageForm').find('input[name="username"]').val(user_name);
		var queryString = $('#wordpageForm').serialize();   
		//isGC为0正常，为1文档回收站    
		    $('#mb').panel('refresh',"<%=rootPath %>site/site_or_chan_alldocLink.action?"+queryString+"&start="+date_start_time+"&end="+date_end_time);  
 }
 //排序
	function orderBy(_sFiled,_sOrderBy){
		$('#orderField').val(_sFiled);
		$('#orderBy').val(_sOrderBy);
		var limitTime = $('#timeselect').combobox('getValue');
		var date_start_time=$('#starttime').datebox('getValue');
		var date_end_time=$('#endtime').datebox('getValue'); 
		
		var user_name=$('#user_name').val();
		if(limitTime=="otherdate"){
			if(date_start_time>=date_end_time){
				$.messager.alert("提示","开始时间不能大于等于结束时间");
				return false;
			}
		}
    	
    	
    	$('#wordpageForm').find('input[name="timeLimit"]').val(limitTime);
    	$('#wordpageForm').find('input[name="username"]').val(user_name);
		var queryString = $('#wordpageForm').serialize(); 
	    $('#mb').panel('refresh',"<%=rootPath %>site/site_or_chan_alldocLink.action?"+queryString+"&start="+date_start_time+"&end="+date_end_time);   
	}
	
	function initTime(){
		$('#timeselect').combobox({
			panelHeight:'160',
			width:'90',
			editable:false,
			onSelect:function(){
				var  start_end = $(this).combobox('getValue');

				if(start_end!="otherdate"){ 
					$('#timedatespan').hide(); 
				}else{
					 var testDate = new Date();
	  	 			//$('#start_time').datebox();
	  	 			//$('#end_time').datebox();
	  	 			$('#starttime').datebox('setValue',testDate.format("yyyy-MM-dd"));
	  	 			$('#endtime').datebox('setValue',testDate.format("yyyy-MM-dd"));
					$('#timedatespan').show();
				}
			}
		 }); 
} 
	function timeDateSpan(){
 var start_end = $('#timeselect').combobox('getValue');
		
		if(start_end=="otherdate"){
		var start=$("#start").val();
		var end =$("#end").val();
		$('#timedatespan').show();
		var startDate=new Date(start);

		var endDate=new Date(end);
		$('#endtime').datebox({
			 value: endDate.format("yyyy-MM-dd"),
			 required: true,
    		 showSeconds: false
		});

		$('#starttime').datebox({
		 	value: startDate.format("yyyy-MM-dd"),
		 	required: true,
    		showSeconds: false
		});
		
		}
		
	}

	Date.prototype.format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
  //当进入页面时候，对检索框进行初始化
  <%-- $(function(){
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
  }); --%>
  $(function(){

			initTime();
  		 $('#checkalldocument').searchbox({
				width:200, 
			    prompt:'请输入文档标题',
			    searcher:function(value,name){ 
			    	$('#wordpageForm').find('input[name="searchDocTitle"]').val(encodeURIComponent(value)); 
			    	$('#wordpageForm').find('input[name="pageNum"]').val('1');
			    	$('#wordpageForm').find('input[name="orderField"]').val('');
			    	$('#wordpageForm').find('input[name="orderBy"]').val(''); 
			    	$('#wordpageForm').find('input[name="searchModal"]').val($('#modalselect').val());
			    	
			    	var limitTime = $('#timeselect').combobox('getValue');
	
					var date_start_time=$('#starttime').datebox('getValue');
					var date_end_time=$('#endtime').datebox('getValue'); 
					var user_name=$('#user_name').val();
					if(limitTime=="otherdate"){
						if(date_start_time>=date_end_time){
							$.messager.alert("提示","开始时间不能大于等于结束时间");
							return false;
						}
					}
			    	
			    	
			    	$('#wordpageForm').find('input[name="timeLimit"]').val(limitTime);
			    	$('#wordpageForm').find('input[name="username"]').val(user_name);
			    	  var queryString = $('#wordpageForm').serialize(); 
				 	 $('#mb').panel('refresh',"<%=rootPath %>site/site_or_chan_alldocLink.action?"+queryString+"&start="+date_start_time+"&end="+date_end_time);   
				 	  
			    } 
			});
  setTimeout(function () { 
			timeDateSpan();	
   		 }, 0);
  
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
	 
	//调整栏目
	function updatetochannel(){ 
		var channelids=$("input[name='chandocids']:checked");
		if(channelids.length==0){ 
			$.messager.alert('提示信息','请选择你需要移动的文档','warning');
		}else{
			var movetochannel=document.createElement("div");
			movetochannel.id="movetochannel";
			document.body.appendChild(movetochannel);
			$('#movetochannel').dialog({
			    modal:true,
			    cache:false,
		        href:'<%=rootPath%>site/movetochannel.action',
				title:'选择栏目', 
				resizable:true,
				maximizable:false,
		        buttons:[{
		        	text: '提交', 
			    	iconCls: 'icon-ok', 
			    	handler: function(){  
						var _node=$('#checkchannelul').tree('getSelected');
						if(_node!=null){
							var _id=_node.id;
							$('#movetoChannel').val(_id);
							$('#showalldocumentfrm2').form('submit',{
			 		 		  	url : '<%=rootPath%>site/move_channel.action',  
			 		 		  	success : function(data){  
			 		 		  		 $.messager.alert('提示信息','移动成功','warning',function(){
			 		 		  		      $('#mb').panel('refresh'); 
			 		 		  		 }); 
			 		 		  	}
							}); 
							$('#movetochannel').dialog('destroy'); 
							window.closed;
			    		 }
		    		} 
			    },
			    {
			    	text: '取消',
			    	iconCls: 'icon-no', 
			        handler: function(){
			        $('#movetochannel').dialog('destroy');
			     }
			    }],
				onClose:function(){
					$('#movetochannel').dialog('destroy');
				}   
			}); 
		}
	}	
	<s:if test="id>0&&siteid==2">
	function batchUpload(){
		var dialogDiv = document.createElement("div");
		dialogDiv.id="batchUpload";
		document.body.appendChild(dialogDiv);
		$('#batchUpload').dialog({   
		    modal:true,
		    href:'<%=rootPath%>file/to_batch_upload.action?channelId=<s:property value="id"/>',
		    title:'批量上传',
		    width:450,
		    height:300,
		    resizable:true,
		    buttons: [{
		    	id: 'btnUpload',
		    	text: '开始转换', 
		    	iconCls: 'icon-ok', 
		    	handler: function() {
		    		var tds = $('#uploadInfo').find('td[name="fileName"]');
		    		if(tds.length>0){
		    			$.messager.progress({
		    				text:'文档转换中...'
		    			});
		    			setTimeout(function(){
				    		$.each(tds, function(i, n){
				    			var upFileName = $(n).attr('_upFileName');
				    			var upTrueName = $(n).attr('title');
				    			var cruser = $('#uploadDoc').find('input:hidden[name="document.cruser"]').val();
				    			var cruserid = $('#uploadDoc').find('input:hidden[name="document.cruserid"]').val();
				    			var docauthor = $('#uploadDoc').find('input:text[name="document.docauthor"]').val();
				    			var isremove = $('#uploadDoc').find('input:radio[name="removeStyle"]:checked').val();
				    			
			    				var r = convertDoc(upFileName, upTrueName, <s:property value="id"/>,cruser, cruserid, docauthor, isremove);
				    			if(r=="1"){
				    				$(n).css('color','green');
				    			}else{
				    				$(n).css('color','red');
				    			}
			    				var bar = $.messager.progress('bar');
			    				if(bar){
			    					var barVal = 100/tds.length*(i+1);
			    					bar.progressbar('setValue', parseInt(barVal));
			    				}
			    			});
				    		$.messager.progress('close');
				    		$('#btnUpload').linkbutton('disable');
				    		$('#btnClose').linkbutton({text: '关闭'});
			    		},1);
		    		}else{
		    			$.messager.alert('提示信息','请选择上传的文档','warning');
		    		}
		    	} 
		    },{ 
		    	id : 'btnClose',
		    	text: '取消', 
		    	iconCls: 'icon-cancel', 
		    	handler: function() { 
		    		$('#batchUpload').dialog('destroy');
		    		documentinfoinit();
		    	} 
		    }],
		    onClose:function(){
		    	$('#batchUpload').dialog('destroy');
		    	documentinfoinit();
		    }
		});
	}
	
	//转换文档
	function convertDoc(fileName, trueName, chnlId, cruser, cruserid, docauthor, isremove){
		var r = $.ajax({
			type:"post",
	    	url:"<%=rootPath%>file/convert_doc.action",
	    	data : {
	    		'fileName' : fileName,
	    		'fileTrueName' : trueName,
	    		'channelId' : chnlId,
	    		'document.cruser' : cruser,
	    		'document.cruserid' : cruserid,
	    		'document.docauthor' : docauthor,
	    		'isremove' : isremove
	    	},
	    	cache:false,
	    	async:false
		}).responseText;
		return r;
	}
	
	function importXML(){
		var dialogDiv = document.createElement("div");
		dialogDiv.id="importXML";
		document.body.appendChild(dialogDiv);
		$('#importXML').dialog({   
		    modal:true,
		    href:'<%=rootPath%>file/to_import_xml.action?channelId=<s:property value="id"/>',
		    title:'导入XML',
		    width:450,
		    height:300,
		    resizable:true,
		    buttons: [{
		    	id: 'btnUpload',
		    	text: '开始转换', 
		    	iconCls: 'icon-ok', 
		    	handler: function() {
		    		var tds = $('#uploadInfo').find('td[name="fileName"]');
		    		if(tds.length>0){
		    			$.messager.progress({
		    				text:'文档转换中...'
		    			});
		    			setTimeout(function(){
				    		$.each(tds, function(i, n){
				    			var upFileName = $(n).attr('_upFileName');
				    			var upTrueName = $(n).attr('title');
				    			var cruser = $('#uploadDoc').find('input:hidden[name="document.cruser"]').val();
				    			var cruserid = $('#uploadDoc').find('input:hidden[name="document.cruserid"]').val();
				    			var docauthor = $('#uploadDoc').find('input:text[name="document.docauthor"]').val();
				    			
			    				var r = convertXml(upFileName, upTrueName, <s:property value="id"/>,cruser, cruserid, docauthor);
				    			if(r>0){
				    				$(n).css('color','green');
				    			}else{
				    				$(n).css('color','red');
				    			}
			    				var bar = $.messager.progress('bar');
			    				if(bar){
			    					var barVal = 100/tds.length*(i+1);
			    					bar.progressbar('setValue', parseInt(barVal));
			    				}
			    			});
				    		$.messager.progress('close');
				    		$('#btnUpload').linkbutton('disable');
				    		$('#btnClose').linkbutton({text: '关闭'});
			    		},1);
		    		}else{
		    			$.messager.alert('提示信息','请选择上传的文档','warning');
		    		}
		    	} 
		    },{ 
		    	id : 'btnClose',
		    	text: '取消', 
		    	iconCls: 'icon-cancel', 
		    	handler: function() { 
		    		$('#importXML').dialog('destroy');
		    		documentinfoinit();
		    	} 
		    }],
		    onClose:function(){
		    	$('#importXML').dialog('destroy');
		    	documentinfoinit();
		    }
		});
	}
	
	function convertXml(fileName, trueName, chnlId, cruser, cruserid, docauthor){
		var r = $.ajax({
			type:"post",
	    	url:"<%=rootPath%>file/convert_xml.action",
	    	data : {
	    		'fileName' : fileName,
	    		'fileTrueName' : trueName,
	    		'channelId' : chnlId,
	    		'document.cruser' : cruser,
	    		'document.cruserid' : cruserid,
	    		'document.docauthor' : docauthor
	    	},
	    	cache:false,
	    	async:false,
	    	error:function (XMLHttpRequest, textStatus, errorThrown) {
	    	   alert(textStatus);
	    	}
		}).responseText;
		return r;
	}
	</s:if>
	function exportHTML(){
		var chnlDocIds="";
		$.each($("input[name='chandocids']:checked"),function(i,n){
			if(i==0){
				chnlDocIds = $(n).val().split('#')[1];
			}else{
				chnlDocIds +=","+$(n).val().split('#')[1];
			}
		});
		if(chnlDocIds.length==0){ 
			$.messager.alert('提示信息','请选择你需要导出的文档','warning');
		}else{
			window.open('<%=rootPath%>file/export_html.action?docids='+chnlDocIds);
		}
	}
	
	function updatestatus(){
		var chandocids = $("input[name='chandocids']:checked");
		if(chandocids.length==0){ 
 	         $.messager.alert('提示信息','请选择要修改的知识','warning');
		}else{
			var statusHtml = '<select id="docstatus" style="margin:15px 45px;">'+$('#modalselect').html()+'</select>';
			var dialogDiv = document.createElement("div");
			dialogDiv.id="edit_status";
			document.body.appendChild(dialogDiv);
			$('#edit_status').dialog({   
			    modal:true,
			    title:'修改知识状态',
			    width:200,
			    height:120,
			    content:statusHtml,
			    resizable:true,
			    buttons: [{
			    	id: 'btnUpload',
			    	text: '修改', 
			    	iconCls: 'icon-ok', 
			    	handler: function() {
			    		var nStatus = $('#docstatus option:selected').val();
			    		if(nStatus<=0){
			    			return false;
			    		}
			    		var sDocIds="";
			    		$.each(chandocids,function(i,n){
			    			if(i>0)
			    				sDocIds+=','+n.value;
			    			else
			    				sDocIds=n.value;
			    		});
			    		$.ajax({
			    			type: "POST",
			    			url: '<%=rootPath%>site/edit_docstatus.action',
			    			data:{
			    				docids:sDocIds,
			    				status:nStatus
			    			},
			    			success:function(data){
			    				$.messager.alert('提示信息','成功修改【'+data+'】篇知识','warning',function(){
			    					documentinfoinit();
			    					$('#edit_status').dialog('destroy');
			    				}); 
			    			}
			    		});
			    	} 
			    },{ 
			    	id : 'btnClose',
			    	text: '取消', 
			    	iconCls: 'icon-cancel', 
			    	handler: function() { 
			    		$('#edit_status').dialog('destroy');
			    	} 
			    }],
			    onClose:function(){
			    	$('#edit_status').dialog('destroy');
			    }
			});
		}
	}
	function copyinfoaddjsp(tablename){
		//if(randomdata==2){
			randomdata=Math.random();
		//}
		var url=$.ajax({
		 				url: '<%=rootPath %>form/tofindobjetpage.action?formtablename='+tablename,
		  			   	type: 'POST',
		  			   	async: false,
						cache: false
		   		}).responseText;
		   		//alert(url);
		   		var now=new Date();
				var date=1900+now.getYear()+""+(now.getMonth()+1)+""+now.getDate()+""+now.getHours()+""+now.getMinutes()+""+now.getSeconds();
				var fileName=date;
				$.ajax({
		 				url: "<%=rootPath %>sheet/copyjsp.action",
		  			   	type: "POST",
		  			   	data:{content:url,formtablename:tablename,randomdata:randomdata},
		  			   	success: function(data){
		  			   	if(data!=0){
							
		  			   	}
		  			   	}
		   		});
	}
	
	/////////////修改知识
	function updatedocument(_docid,tablename){
		var updatepageurl = $.ajax({
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
			href:'<%=rootPath %>form/tofindobjetpage.action?formtablename='+tablename+'&docid='+_docid,
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
		    					documentinfoinit();
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
	function formInfoEdit(_docid,tablename){
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
			href:'<%=rootPath %>form/tofindobjetpage.action?formtablename='+tablename+'&docid='+_docid,
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
		    					documentinfoinit();
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
	function exportToZip(){
	//var now=new Date();
	//var date=1900+now.getYear()+""+(now.getMonth()+1)+""+now.getDate()+""+now.getHours()+""+now.getMinutes()+""+now.getSeconds();
	var fileName=Math.random();
		var checkalldocument=$('#checkalldocument').val();
		$('#wordpageForm').find('input[name="searchDocTitle"]').val(encodeURIComponent(checkalldocument)); 
    	$('#wordpageForm').find('input[name="pageNum"]').val('1');
    	$('#wordpageForm').find('input[name="orderField"]').val('');
    	$('#wordpageForm').find('input[name="orderBy"]').val(''); 
    	$('#wordpageForm').find('input[name="searchModal"]').val($('#modalselect').val());
    	var limitTime = $('#timeselect').combobox('getValue');
		var date_start_time=$('#starttime').datebox('getValue');
		var date_end_time=$('#endtime').datebox('getValue'); 
		var user_name=$('#user_name').val();
		if(limitTime=="otherdate"){
			if(date_start_time>=date_end_time){
				$.messager.alert("提示","开始时间不能大于等于结束时间");
				return false;
			}
		}
    	$('#wordpageForm').find('input[name="timeLimit"]').val(limitTime);
    	$('#wordpageForm').find('input[name="username"]').val(user_name);
    	var queryString = $('#wordpageForm').serialize(); 
	 	var s=new Date().getTime();
		var chnlDocIds="";
		$.each($("input[name='chandocids']:checked"),function(i,n){
			if(i==0){
				chnlDocIds = $(n).val().split('#')[1];
			}else{
				chnlDocIds +=","+$(n).val().split('#')[1];
			}
		});
		if(chnlDocIds.length==0){
			chnlDocIds=0;
		} 
	$.ajax({
		type:"post",
		url:"<%=rootPath %>site/exporttozip.action?"+queryString+"&start="+date_start_time+"&end="+date_end_time+"&fileName="+fileName+"&chnldocids="+chnlDocIds+"&s="+s+"&username="+user_name+"&timeLimit="+limitTime,
		async:false,
		success:function(msg){
			if(msg!=0){
				document.getElementById("daochu").href="../docment/doc"+msg+".zip";
				document.getElementById("daochu").click();
			}else{
				$.messager.alert('提示信息','请选择你需要导出的文档','warning');
			}
		}
        
	});
	}
	</script>
	<form id="showalldocumentfrm"   method="post"></form>
	<form id="showalldocumentfrm2"   method="post"> 
	<input name="id" id="movetoChannel" type="hidden" value="">
      <table width="100%"  border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea" >
	   <tr>
		 <td colspan="8">  
		     <table cellpadding="0" cellspacing="0" width="100%" >
		     <tr >
		     	<td  colspan="1" align="right">
		     	</td>
		     	<td style=" padding-left:50px;" colspan="3" align="left">
		     			请选择时间段:<select id="timeselect">
		     			<option value="shijian"  >请选择时间</option>
					 	<option value="thismonth" id="thismonth"  <s:if test="timeLimit=='thismonth'">selected="selected"</s:if>>本月</option>
						<option value="thisweek" id="thisweek" <s:if test="timeLimit=='thisweek'">selected="selected"</s:if>>本周</option>
						<option value="thisquarter" id="thisquarter" <s:if test="timeLimit=='thisquarter'">selected="selected"</s:if>>本季</option>
						<option value="otherdate" id="otherdate" <s:if test="timeLimit=='otherdate'">selected="selected"</s:if>>指定</option>
					</select>
					<!--style="display: none;" -->
					<span id="timedatespan" style="display: none;">
							开始日期
							<input type="text" id="starttime" value='<s:property value="start"/>'class="easyui-datebox" editable="false" validType="StartTime">&nbsp;&nbsp; 
							结束日期
							<input type="text" id="endtime" value= '<s:property value="end"/>' class="easyui-datebox" editable="false" validType="EndTime">
					
					</span>	
					 
		     		</td>
		     	</tr>
		     	<tr>
		     		<td width="50%" style=" padding-left: 5px;"> 
		     		  <s:if test="formtablename==null||formtablename==''"> 
   	 	 	          <a href="javascript:void(0)" onclick="addDocument(<s:property value='siteid'/>,<s:property value='id'/>)">增加</a> 
   	 	 	          </s:if>
   	 	 	          <s:else>
   	 	 	          <a href="javascript:void(0)" onclick="addDocument1(<s:property value='siteid'/>,<s:property value="id"/>)">增加</a> 
   	 	 	          <a href="javascript:void(0)" onclick="bindingcolumn('<s:property value="formtablename" />',<s:property value="id"/>)">绑定字段</a> 
	  	 	          </s:else>
	  	 	          <a href="javascript:void(0)" onclick="deletedocumenttogc()">删除</a>
	  	 	          <a href="javascript:void(0)" onclick="updatetochannel()">移动</a>
	  	 	          <a href="javascript:void(0)" onclick="updatestatus()">修改状态</a>
	  	 	          <a href="javascript:void(0)" onclick="site_or_channel_alldocumentgc(<s:property value='siteid'/>,<s:property value='id'/>)">文档回收站</a>
	 	 	          <s:if test="id>0&&siteid==2">
	 	 	          <a href="javascript:void(0)" onclick="batchUpload()">批量上传</a>
	 	 	          <a href="javascript:void(0)" onclick="importXML()">导入XML</a>
	 	 	          </s:if>
	 	 	          <a href="javascript:void(0)" onclick="exportHTML()">导出HTML</a>
	 	 	          <a href="javascript:void(0)" onclick="exportToZip()">导出EXcel</a>
	 	 	          <a  id="daochu"></a>
	 	 	          <a href="javascript:void(0)" onclick="documentinfoinit()">刷新</a>
	 	 	        </td>
	 	 	        <td width="25%" align="right">
		     		   用户名:  <input id="user_name" type="text" value='<s:property value="username"/>'/>
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
              <td width="5%" align="center" bgcolor="#F5FAFE" class="main_bleft"><a href="javascript:void(0)" style="width:100%" onclick="checkAll()" >全选</a></td>
            <td width="30%" bgcolor="#F5FAFE"  class="main_bright">
              <a href="#" onclick="orderBy('doctitle','<s:if test="orderField=='doctitle'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">
             		 文档标题<s:if test="orderField=='doctitle'&&orderBy=='desc'">
								 <img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" />
							</s:if>
							<s:elseif test="orderField=='doctitle'&&orderBy=='asc'">
										<img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" />
							</s:elseif>
             		 
			</a></td>
			 <td width="13%" bgcolor="#F5FAFE"  class="main_bright">
              <a href="#" onclick="orderBy('channelid','<s:if test="orderField=='channelid'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">
             		 所属栏目<s:if test="orderField=='channelid'&&orderBy=='desc'">
										<img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" />
							</s:if>
							<s:elseif test="orderField=='channelid'&&orderBy=='asc'">
										<img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" />
							</s:elseif>
             		 
			</a></td>
              <td width="8%"  bgcolor="#F5FAFE"  class="main_bright">
              <a href="#" onclick="orderBy('cruser','<s:if test="orderField=='cruser'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">
                                               作者<s:if test="orderField=='cruser'&&orderBy=='desc'">
										<img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" />
							</s:if>
							<s:elseif test="orderField=='cruser'&&orderBy=='asc'">
										<img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" />
							</s:elseif>
              
              </a></td>
               <td  width="12%" bgcolor="#F5FAFE"  class="main_bright">
               <a href="#" onclick="orderBy('crtime','<s:if test="orderField=='crtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">
               	创建时间<s:if test="orderField=='crtime'&&orderBy=='desc'">
										<img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" />
							</s:if>
							<s:elseif test="orderField=='crtime'&&orderBy=='asc'">
										<img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" />
							</s:elseif>
			</a></td>
              <td width="12%" bgcolor="#F5FAFE"  class="main_bright">
              <a href="#" onclick="orderBy('docpubtime','<s:if test="orderField=='docpubtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">
			发稿时间<s:if test="orderField=='docpubtime'&&orderBy=='desc'">
										<img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" />
							</s:if>
							<s:elseif test="orderField=='docpubtime'&&orderBy=='asc'">
										<img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" />
							</s:elseif>
			</a></td>
              <td width="10%"  bgcolor="#F5FAFE"  class="main_bright">
              <a href="#"  onclick="orderBy('docstatus','<s:if test="orderField=='docstatus'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">
                                     文档状态<s:if test="orderField=='docstatus'&&orderBy=='desc'">
										<img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" />
							</s:if>
							<s:elseif test="orderField=='docstatus'&&orderBy=='asc'">
										<img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" />
							</s:elseif>
             </a></td>
               <td width="5%" bgcolor="#F5FAFE" class="main_bright"><a href="#"  style="width:100%"><u><font color="#000000">修改</font></u></a></td>
              </tr>
          <s:iterator value="chnlDocLinks" status="listStat">
          	<tr>
	          	  <td align="center" bgcolor="#F5FAFE"  class="main_bleft">
	             	  <input type="checkbox" name="chandocids" id="checkbox" value="<s:property value='docid'/>#<s:property value='chnldocid'/>"/>  
	              <s:property value="(pageNum-1)*pageSize+#listStat.count"/>
	              </td> 
	              <td bgcolor="#F5FAFE"  class="main_bright">
	              <a a href="<%=rootPath%>site/admin/adminpreviewdocument.action?docid=<s:property value="docid"/>" target="_blank"><s:property value="doctitle"/></a>
	              </td>
	                <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="chnldesc"/> </td>
	              <td bgcolor="#F5FAFE"  class="main_bright"> <s:property value="cruser"/> </td>
	               <td bgcolor="#F5FAFE"  class="main_bright">
	                   <s:date name="%{crtime}" format="yyyy-MM-dd HH:mm"/>
	               </td>
	              <td bgcolor="#F5FAFE"  class="main_bright"> 
	              	<s:date name="%{docpubtime}" format="yyyy-MM-dd HH:mm"/>
	              </td> 
	              <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="findStatusNameByStatusId(docstatus)" />
	              </td>
	                <td bgcolor="#F5FAFE" class="main_bright"> 
	                <s:if test="formtablename==null||formtablename==''">  
	             	<img border="0" src="images/icons/pencil.png" title="修改" style="cursor:pointer; margin: 0 5px;" onclick="updateDocument(<s:property value='docid'/>,<s:property value='channelid'/>)"/>
	             	</s:if>
	             	<s:else>
	             	<img border="0" src="images/icons/pencil.png" title="修改" style="cursor:pointer; margin: 0 5px;" onclick="formInfoEdit(<s:property value="docid" />,'<s:property value="formtablename" />')"/>
	              	</s:else>
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
