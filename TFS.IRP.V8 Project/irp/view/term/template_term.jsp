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
    <title>引入模版</title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/oacss.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/icon.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/ztree/css/zTreeStyle/zTreeStyle.css"/>
	<script type="text/javascript" src="<%=rootPath%>view/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/js/lhgdialog.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/ztree/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/ztree/js/jquery.ztree.excheck-3.5.js"></script>
</head>
<script type="text/javascript">
		$(function(){
		
		initZTree();
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
					url:"<%=rootPath%>category/getallcatetemplate.action?showExpertList=4" 
				},
				callback: {
					onClick: function (event, treeId, treeNode, clickFlag){
						if(treeNode){
							var cateid = treeNode.id;
							pageQTemplate(cateid,1);
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
		function pageQTemplate(_cateid,_num){
			$.ajax({
					type:'post',
					url:'<%=rootPath%>term/findtemplatebycate.action',
					cache:false,
					async:false,
					data:{
						cateid:_cateid,
						pagetemnums:_num
					},
					success:function(content){
						$('#templatedetail').html(content);
					}
				});
		}
</script>
  
<body>
  	<div style="width: 780px;height: 30px;">
  		<label style="font-size: 14px;letter-spacing: 2px;font-weight: bold;">请根据词条类型选择基本信息模板：</label>
  	</div>
  	<div style="width: 780px;height: 530px;">
		<div style="width: 200px;height: 500px;border:1px solid;float: left;overflow: scroll;">
				<ul id="categoryTreetemplate" class="ztree" style="background-color: white;border: none;">
				
				</ul>
		</div>
		<div style="width: 500px;height: 500px;border:1px solid;float: left;margin-left: 25px;">
			<div style="width: 100%;height: 30px;border-bottom: 1px solid;font-size: 14px;letter-spacing: 2px;font-weight: bold;background-color: #ECECFF;">
			&nbsp;模版概览
			</div>
			<div id="templatedetail">
			
			</div>
		</div>
	</div>
</body>
</html>
