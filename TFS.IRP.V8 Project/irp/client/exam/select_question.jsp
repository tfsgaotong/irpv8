<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.tfs.irp.util.LoginUtil"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="<%=rootPath%>/client/css/oacss.css" rel="stylesheet"type="text/css" />
	<link href="<%=rootPath%>/client/css/easyui.css" rel="stylesheet" type="text/css" />
	<link href="<%=rootPath%>/client/css/icon.css" rel="stylesheet" type="text/css"  />
	<link href="<%=rootPath%>/client/css/oacss.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="<%=rootPath%>client/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css" />
    
	<script type="text/javascript" src="<%=rootPath%>client/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>client/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript">
	var cateids = 0;
		$(function(){
		
		initZTree();
		$('#searchinput').searchbox({
		width:220,
	    searcher:function(value,name){   
	    	$('#pageForm').find('input[name="searchWord"]').val(value);
	    	$('#pageForm').find('input[name="searchType"]').val(name);
	    	$('#pageForm').find('input[name="pagenum"]').val('1');
	    	$('#pageForm').find('input[name="orderField"]').val('');
	    	$('#pageForm').find('input[name="orderBy"]').val('');
	    	var cateid =  cateids;
	    	if(cateid==""){
				cateid = 0;
			}
	    	$('#pageForm').find('input[name="cateid"]').val(cateid);
	    	var queryString = $('#pageForm').serialize();
	    	
			$.ajax({
				type:'post',
				url:'<%=rootPath%>exam/finddatainqtype.action',
				data:queryString,
				cache:false,
				async:false,
				success:function(content){
					$("#templatedetail").html(content);
				}
			
			
			});
	    	
	    },   
	    menu:'#listSearchText',   
	    prompt:'请输入检索词'  
	});
		});
		
		
		
		
	
function initZTree(){
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
			url:"<%=rootPath%>category/getallcatetemplateqbank.action?showExpertList=7" 
		},
		callback: {
			onClick: function (event, treeId, treeNode, clickFlag){
				if(treeNode){
					$("#searchinput").searchbox('setValue','');
					$("#searchWord").val("");
					var cateid = treeNode.id;
					cateids = cateid;
					getDataByCateid(cateid);
					
					
				 }
			},
			onAsyncSuccess:function(event, treeId, treeNode, msg){
			if(msg!=''){
				$('#categoryTreetemplate_1_a').click();
			}
			}
		} 
	});
}
/**
* 根据分类id 找出相应的数据
*/
function getDataByCateid(_cateid){
	$.ajax({
		type:'post',
		url:'<%=rootPath%>exam/finddatainqtype.action',
		data:{
			cateid:_cateid
		},
		cache:false,
		async:false,
		success:function(content){
			$("#templatedetail").html(content);
		}
	
	
	});


}
		
/**
*分页
*/
function page(_nPageNum){
	$('#pagenum').val(_nPageNum);
	var cateid = cateids;
	if(cateid==""){
		cateid = 0;
	}
	$('#cateid').val(cateid);
	var queryString = $('#pageForm').serialize();
	
	$.ajax({
		type:'post',
		url:'<%=rootPath%>exam/finddatainqtype.action',
		data:queryString,
		cache:false,
		async:false,
		success:function(content){
			$("#templatedetail").html(content);
		}
	
	
	});
} 		
	</script>
	<title>
	</title>
  </head>
  
  <body>
  	<form id="pageForm">
		<s:hidden name="searchWord" id="searchWord" />
		<s:hidden name="searchType" id="searchType" />
		<s:hidden name="pagenum" id="pagenum" />
		<s:hidden name="cateid" id="cateid" />
		<s:hidden name="orderField" id="orderField" />
		<s:hidden name="orderBy" id="orderBy" />
	</form>
	
  	<div style="width: 780px;height: 30px;">
  		<label style="font-size: 14px;letter-spacing: 2px;font-weight: bold;">请根据题库类型选择试题：</label>
  	</div>
  	<div style="width: 780px;height: 330px;">
		<div style="width: 150px;height: 330px;border:1px solid;float: left;overflow: scroll;">
				<ul id="categoryTreetemplate" class="ztree" style="background-color: white;border: none;">
				
				
				</ul>
		</div>
		<div style="width: 600px;height: 330px;border:1px solid;float: left;margin-left: 25px;">
			<div style="width: 100%;height: 30px;border-bottom: 1px solid;font-size: 14px;letter-spacing: 2px;font-weight: bold;background-color: #ECECFF;padding: 4px 0 1px 0;">
			
			&nbsp;题目
			<div style="float: right;">
	  	  		<input name="searchinput" id="searchinput"  />
				<div id="listSearchText">  
				    <div data-options="name:'all'">全部&nbsp;&nbsp;&nbsp;&nbsp;</div>
				    <div data-options="name:'qtext'">题干</div>
				    <div data-options="name:'qexplain'">解析</div>
				</div>&nbsp;	
			</div>
		
			</div>
			<div id="templatedetail" style="width: 600px;">
			
			</div>
		</div>
		
	</div>
  </body>
</html>
