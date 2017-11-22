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
	getCateBySecond();
	
	

});



/**
* 引用分类树
*/
function getCateBySecond(){
	$.fn.zTree.init($("#categorysecordqbank"), {
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
				 	$("#searchWord").val("");
				 	jumps("<%=rootPath%>exam/seldatabylinktestpaper.action?cateid="+cateid+"&pagenum=1");
				 }
			},
			onAsyncSuccess:function(event, treeId, treeNode, msg){
			if(msg!=''){
				$('#categorysecordqbank_1_a').click();
				
			}
			
			
			}
			
		} 
		
	});	
}

function jumps(_sUrl){
//	$('#quoteaddqbank').layout('panel','center').panel('refresh',_sUrl);
	$('#quoteaddqbankcontent').panel('refresh',_sUrl);

}
</script>
<div id="cc" class="easyui-layout" style="width:769px;height:427px;">  
    <div data-options="region:'west',title:'分类',split:true" style="width:150px;">
        <ul id="categorysecordqbank" class="ztree" style="background-color: white;border: none;" >
		
		
		</ul>
    </div>  
    <div id="quoteaddqbankcontent" data-options="region:'center',title:'题目'" style="padding:5px;background:#eee;">
    
    </div>  
</div>  
</body>
</html>