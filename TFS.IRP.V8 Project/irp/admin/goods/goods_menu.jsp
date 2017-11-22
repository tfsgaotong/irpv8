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
<title>商品管理</title>
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
			 if(isInit&&title=="商品管理"){
				jump('<%=rootPath %>admin/goods/goods_manage.jsp');
				isInit = true; 
			}else if(title=="勋章管理"){
				isInit = true;
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
							url:"<%=rootPath%>category/getMedalCategoryBack.action"
						},
						callback: {
							onClick: oncClick
						}
					};
					$.fn.zTree.init($("#medalManage"), setting);
				});
				jump('<%=rootPath %>admin/goods/medal_manage.jsp');
			}
				
		}
	});  
});

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
function oncClick(event, treeId, treeNode, clickFlag){
	 if(treeNode){
		 var tab=$('#roleTabs').tabs('getSelected'); 
		 var tabid=tab.attr('id'); 
		 if(tabid=="medalManager"){ //查询栏目信息，右边默认选择栏目一栏  并且将栏目id（node.id）放在tab为文档的url里面，这样点击它可以加载他里面的文档
			 var _cUrl="<%=rootPath%>medal/tolistmedalcategoryBackground.action?categoryId="+treeNode.id;
			 jump(_cUrl);//加载他的详细信息
	 	 }
		 else if(tabid=="medalRecord"){
			 var _cUrl="<%=rootPath%>site/to_documentsubject.action?siteid="+n_siteid+"&channelorDocument=document&id="+node.id;
			 jump(_cUrl);//加载他的详细信息
		 } 
	 }
} 
</script>
<div id="formdiv" class="easyui-accordion" fit="true" border="false" >
	 
	<div title="商品管理" style="padding:0px;"  class="asseroomsbmenu">
        <ul id="form_cate" class="ztree" style="background-color: white;border: none;" >
		
		
		</ul>
	</div> 
	<div title="勋章管理" style="padding:0px;"  class="asseroomsbmenu">
       <ul id="medalManage" class="ztree" style="background-color: white;border: none;overflow-y:none;"></ul>
	</div> 
	 
</div>
</body>
</html>