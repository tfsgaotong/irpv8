<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />
<!-- 
<script type="text/javascript" src="admin/js/jquery-1.8.0.min.js"></script>
 -->
<title>会议室管理</title>
</head>
<body>
<script type="text/javascript">
function jump(_sUrl){
	$('body').layout('panel','center').panel('refresh',_sUrl);
}
$(function (){
	var isInit = false;
	$('#formdiv').accordion({
		onSelect : function(title){ 
			 if(title=="自定义表单"){
				//jump('<%=rootPath %>admin/form/form_list.jsp');
				formCate();
				isInit = true; 
			}
				
		}
	});  
});
/**
* 表单分类
*/
function formCate(){
	$.fn.zTree.init($("#form_cate"), {
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
			url:"<%=rootPath%>category/formcate.action?showExpertList=10" 
		},
		callback: {
			onClick: function (event, treeId, treeNode, clickFlag){
				if(treeNode){
				 	var cateid = treeNode.id;
				 	
				 	jump('<%=rootPath %>admin/form/form_list.jsp');
				 	
				 }
			},
			onAsyncSuccess:function(event, treeId, treeNode, msg){
			if(msg!=''){
				$('#form_cate_1_a').click();
				
			}
			
			
			}
			
		} 
		
	});	
}
</script>
<div id="formdiv" class="easyui-accordion" fit="true" border="false" >
	 
	<div title="自定义表单" style="padding:0px;"  class="asseroomsbmenu">
        <ul id="form_cate" class="ztree" style="background-color: white;border: none;" >
		
		
		</ul>
	</div> 
	 
</div>
</body>
</html>