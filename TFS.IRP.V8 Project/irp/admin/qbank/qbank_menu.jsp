<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">

$(function(){
		$("#menu").accordion({
			onSelect:function(title){
				if(title=='题库设置'){
					qBCate();
				
				}else if(title=='试卷管理'){
					tPCate();
				
				}else if(title=='考试管理'){
					examCate();
				
				}else if(title=='成绩管理'){
					
					jump("<%=rootPath%>exam/examresultmanger.action?pagenum=1");
				}
				
			
			}
			
		});
		qBCate();
});
function qBCate(){
	$.fn.zTree.init($("#categoryTreequestionbank"), {
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
				 	var cateid = treeNode.id;
				 	jump("<%=rootPath%>exam/qbanklist.action?cateid="+cateid+"&pagenum=1");
				 	
				 }
			},
			onAsyncSuccess:function(event, treeId, treeNode, msg){
			if(msg!=''){
				$('#categoryTreequestionbank_1_a').click();
				
			}
			
			
			}
			
		} 
		
	});		

}

function tPCate(){
	$.fn.zTree.init($("#catetestpaper"), {
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
			url:"<%=rootPath%>category/getallcatetemplatetpaper.action?showExpertList=8" 
		},
		callback: {
			onClick: function (event, treeId, treeNode, clickFlag){
				if(treeNode){
				 	var cateid = treeNode.id;
				 	
				 	jump("<%=rootPath%>exam/testpapermenu.action?cateid="+cateid+"&pagenum=1");
				 	
				 }
			},
			onAsyncSuccess:function(event, treeId, treeNode, msg){
			if(msg!=''){
				$('#catetestpaper_1_a').click();
				
			}
			
			
			}
			
		} 
		
	});	
}
/**
* 考试管理分类
*/
function examCate(){
	$.fn.zTree.init($("#examcate"), {
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
			url:"<%=rootPath%>category/getallcatetemplateexam.action?showExpertList=9" 
		},
		callback: {
			onClick: function (event, treeId, treeNode, clickFlag){
				if(treeNode){
				 	var cateid = treeNode.id;
				 	
				 	jump("<%=rootPath%>exam/linkexammenu.action?cateid="+cateid+"&pagenum=1");
				 	
				 }
			},
			onAsyncSuccess:function(event, treeId, treeNode, msg){
			if(msg!=''){
				$('#examcate_1_a').click();
				
			}
			
			
			}
			
		} 
		
	});	
}
function jump(_sUrl){
	$('body').layout('panel','center').panel('refresh',_sUrl);
}
</script>
<div id="menu" class="easyui-accordion" fit="true" border="false" >
	<div title="题库设置">
        <ul id="categoryTreequestionbank" class="ztree" style="background-color: white;border: none;" >
		
		</ul>
    </div>
	<div title="试卷管理">
        <ul id="catetestpaper" class="ztree" style="background-color: white;border: none;">
		
		
		</ul>
	</div>
	<div title="考试管理">
        <ul id="examcate" class="ztree" style="background-color: white;border: none;">
		
		
		</ul>
	</div>	
	<div title="成绩管理">
	
	
	</div>
</div>
</body>
</html>