<%@page import="com.tfs.irp.util.SysConfigUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
String categorySelectType = SysConfigUtil.getSysConfigValue("CATEGORY_SELECT_TYPE");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>选择类别</title>
  <script type="text/javascript"> 
   if(<%=categorySelectType%>=='1'){
	   //全局单选
	   var setting = {
		   		check: {
		   			enable: true,
		   			chkStyle: "radio",//单选
		   			radioType: "all"//所有的只能选择一个
		   		},
		   		view: {
		   			dblClickExpand: false,
		   			showIcon:showIconForTree
		   		},
		   		data: {
		   			simpleData: {
		   				enable: true
		   			}
		   		},
		   		async: {
		   			enable: true,
		   			url:"<%=rootPath%>phone/getAllCategory.action?categoryName=&token="+$('#tokens').val()
		   		},
		   		callback: {
		   			beforeClick: beforeClick,
		   			onCheck: onCheck
		   		}
		   	};
   }else if(<%=categorySelectType%>=='2'){
	   //各级单选
	   var setting = {
		   		check: {
		   			enable: true,
		   			chkStyle: "radio",//单选
		   			radioType: "level" //level是按等级 每级可以选择一个
		   		},
		   		view: {
		   			dblClickExpand: false,
		   			showIcon:showIconForTree
		   		},
		   		data: {
		   			simpleData: {
		   				enable: true
		   			}
		   		},
		   		async: {
		   			enable: true,
		   			url:"<%=rootPath%>phone/getAllCategory.action?categoryName=&token="+$('#tokens').val()
		   		},
		   		callback: {
		   			beforeClick: beforeClick,
		   			onCheck: onCheck
		   		}
		   	};
   }else{
	   //复选
	   var setting = {
		   		check: {
		   			enable: true,
		   			chkboxType: {"Y":"", "N":""} //多选
		   		},
		   		view: {
		   			dblClickExpand: false,
		   			showIcon:showIconForTree
		   		},
		   		data: {
		   			simpleData: {
		   				enable: true
		   			}
		   		},
		   		async: {
		   			enable: true,
		   			url:"<%=rootPath%>category/getAllCategory.action?categoryName=&token="+$('#tokens').val()
		   		},
		   		callback: {
		   			beforeClick: beforeClick,
		   			onCheck: onCheck
		   		}
		   	};
   }
   	function beforeClick(treeId, treeNode) {
   		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
   		zTree.checkNode(treeNode, !treeNode.checked, null, true);
   		return false;
   	}
   	
   	function showIconForTree(treeId,treeNode){
   		//子节点不显示图标样式
   		return treeNode.isParent;
   	}
   	
   	function onCheck(e, treeId, treeNode) {
   		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
   		nodes = zTree.getCheckedNodes(true);
   		v = "";
   		vid = "";
   		for (var i=0, l=nodes.length; i<l; i++) {
   			v += nodes[i].name + ";";
   			vid += nodes[i].id +",";
   		}
   		if (v.length > 0 ){
   			v = v.substring(0, v.length-1);
   			vid = vid.substring(0, vid.length-1);
   		} 
   		var cateName = $("#cate");
   		var cateId = $("#cateIds");
   		cateName.attr("value", v);
   		cateId.attr("value", vid);
   		
   	}

   	$(document).ready(function(){
   		$.fn.zTree.init($("#treeDemo"), setting);
   	});
  </script> 
  </head> 
  <body> 
  <input type="hidden" value="<s:property value='tokens'/>" id="tokens"/>
	  <ul id="treeDemo" class="ztree" style="margin-top:0; width:260px; height: 350px;"></ul>
  </body>
</html>
