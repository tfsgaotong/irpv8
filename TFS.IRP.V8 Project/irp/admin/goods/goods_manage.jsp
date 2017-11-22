<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>表单管理</title>
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />
</head>

<body>
<script type="text/javascript">
$(function(){
	$('#roleTabs').tabs({
		onSelect:function(title,index){
			var panels = $('#roleTabs').tabs('tabs');
			for(var i=0;i<panels.length;i++){
				var panel = panels[i];
				if(i==index){
					panel.panel('refresh',panel.attr('link'));
				}else{
					panel.html('');
				}
			}
		}
	});
});
</script>
<div id="roleTabs" fit="true" plain="true">
	<div id="goodsManager" title="商品管理" width="100" link="<%=rootPath %>goods/listGoods.action" style="overflow:auto;padding:5px;"></div>
	<div id="covertRecord" title="兑换记录" width="100" link="<%=rootPath %>covert/covert_search_action.action?goodsname&username&c_start_end=covert_month&c_date_start_time&c_date_end_time&pageNum=1" style="overflow:auto;padding:5px;"></div>
</div>
</body>
</html>
