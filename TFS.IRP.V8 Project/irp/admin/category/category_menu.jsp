<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />
<title>分类管理</title>
</head>

<body>
<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.exedit-3.5.js"></script>
<link rel="stylesheet" href="<%=rootPath%>client/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css" />
<script type="text/javascript">
function jump(_sUrl){
	$('body').layout('panel','center').panel('refresh',_sUrl);
}
$(function (){
	var _sUrl="<%=rootPath%>category/toRightPart.action?categoryorfile=category&cid=1";
	jump(_sUrl);
	$('#menu').accordion({
		onSelect : function(title){
			if(title=="分类管理"){
				$(function(){ 
					var setting = {
						view: {
							showIcon: true ,
							addHoverDom: addHoverDom,
							removeHoverDom: removeHoverDom
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
							url:"<%=rootPath%>category/getAllCategoryBack.action"
						},
						callback: {
							onClick: oncClick
						}
					};
					$.fn.zTree.init($("#questionManage"), setting);
				});
			}
		}
	});
});

function oncClick(event, treeId, treeNode, clickFlag){
	 if(treeNode){
		 var tab=$('#tab').tabs('getSelected'); 
		 var tabid=tab.attr('id'); 
		 if(tabid=="fl"){ //查询栏目信息，右边默认选择栏目一栏  并且将栏目id（node.id）放在tab为文档的url里面，这样点击它可以加载他里面的文档
			 var _cUrl="<%=rootPath%>category/toRightPart.action?categoryorfile=category&cid="+treeNode.id;
			 jump(_cUrl);//加载他的详细信息
	 	 }
		 else if(tabid=="wd"){
			 var _cUrl="<%=rootPath%>site/to_documentsubject.action?siteid="+n_siteid+"&channelorDocument=document&id="+node.id;
			 jump(_cUrl);//加载他的详细信息
		 } 
	 }
} 

var IDMark_A = "_a";
function addHoverDom(treeId, treeNode){
	if ($("#diyBtn1_"+treeNode.id).length>0) return;
	if ($("#diyBtn2_"+treeNode.id).length>0) return;
	var aObj = $("#" + treeNode.tId + IDMark_A);
	var editStr = "<a id='diyBtn1_" +treeNode.id+ "' style='margin:0 0 0 5px;color:red;'>("+treeNode.id+")</a>";
	aObj.append(editStr);
}

function removeHoverDom(treeId, treeNode) {
	$("#diyBtn1_"+treeNode.id).unbind().remove();
}

</script>
<div id="menu" class="easyui-accordion" fit="true" border="false">
	<div title="分类管理" style="padding:0px;" selected="true" class="">
		<ul id="questionManage" class="ztree" style="background-color: white;border: none;overflow-y:none;"></ul>
	</div>
</div>
</body>
</html>
