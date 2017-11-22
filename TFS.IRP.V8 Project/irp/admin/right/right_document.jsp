<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>权限管理</title>
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />
</head>

<body>
<script type="text/javascript">
/** 
 * 扩展表格列对齐属性： 
 *      自定义一个列字段属性： 
 *      headalign ：原始align属性针对数据有效, headalign针对列名有效
 *      
 */  
$.extend($.fn.treegrid.defaults,{  
    onLoadSuccess : function() {  
        var target = $(this);  
        var opts = $.data(this, "datagrid").options;  
        var panel = $(this).datagrid("getPanel");  
        //获取列
        var fields=$(this).datagrid('getColumnFields',false);
        //datagrid头部 table 的第一个tr 的td们，即columns的集合
        var headerTds =panel.find(".datagrid-view2 .datagrid-header .datagrid-header-inner table tr:first-child").children();
        //重新设置列表头的对齐方式
        headerTds.each(function (i, obj) {
            var col = target.datagrid('getColumnOption',fields[i]);
            if (!col.hidden && !col.checkbox)
            {
                var headalign=col.headalign||col.align||'left';
                $("div:first-child", obj).css("text-align", headalign);
            }
        })
    }  
});

$(function(){
	var editIndex = undefined;
	$('#documentRight').treegrid({
		url:'<%=rootPath%>right/find_treegrid_node.action?type=<s:property value="type" />&objType=<s:property value="objType" />&objId=<s:property value="objId" />',
		fit:true,
		fitColumns:true,
		idField:'operid',
		treeField:'opername',
		columns:[[
			{
				field:'opername',
				title:'操作对象',
				width:100,
				align:'left',
				headalign:'center'
			}
			<s:iterator value="operTypes">
			,{
				field:'<s:property value="opertypeid" />',
				title:'<s:property value="operdesc" />',
				width:100,
				align:'center',
				headalign:'center',
				editor:{
					type:'checkbox',
					options:{on:1,off:0}
				},
				formatter: function(value,row,index){
					if (value==1){
						return "<span class=\"tree-icon tree-file tree-dnd-yes\"></span>";
					} else if(value==0){
						return "<span class=\"tree-icon tree-file tree-dnd-no\"></span>";
					}else{
						return value;
					}
				}
			}
			</s:iterator>
		]],
		onDblClickRow:function(row){
			if(row && editIndex != row.operid){
				if(editIndex!=undefined){
					$('#documentRight').treegrid('endEdit', editIndex);
				}
				editIndex = row.operid;
				$('#documentRight').treegrid('beginEdit', editIndex);
			}
		}
	});
});
</script>
<table id="documentRight" class="treegridobj"></table>
</body>
</html>
