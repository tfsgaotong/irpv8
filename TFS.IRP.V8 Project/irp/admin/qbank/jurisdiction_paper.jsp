<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<style type="text/css">
.cqt td{
	padding:5px 0 5px 0;
}
label{
	font-weight: bold;
}

</style>
<script type="text/javascript">
var groupids = "";
$(function(){
var grouptidsarrays = '<s:property value="grouptids"/>';
	$('#groupAddTree').tree({
		url:'<%=rootPath%>user/find_tree_node.action?groupType=1',
		animate:false,
		lines:true,
		checkbox:true,
		cascadeCheck:false,
			onLoadSuccess:function(node, data){
				if(!node){
					
					var root = $('#groupAddTree').tree('getRoot');
					$(root.target).find(".tree-checkbox").removeClass("tree-checkbox tree-checkbox0");
					$('#groupAddTree').tree('expand',root.target);
					 
				}
			},onCheck:function(node, checked){
				if(checked){
					groupids += node.id +",";
				}else{
					var groupidsary = "";
					var groupidsarray = groupids.split(",");
					for(var i in groupidsarray){
						if(groupidsarray[i]!=node.id){
							if(groupidsarray[i]!=""){
							groupidsary+=groupidsarray[i]+",";
							}
						}
					}
					groupids = groupidsary;
					grouptidsarrays = 	groupids;
				}
			},onExpand:function(node){
			
				
				groupids = grouptidsarrays;
				var grouptidsarraysarray = grouptidsarrays.split(",");
				for(var i in grouptidsarraysarray){
					 var nodess = $('#groupAddTree').tree('find',grouptidsarraysarray[i]);
					 if(nodess!=null){
						 $("#groupAddTree").tree('check',nodess.target);
					 }
				  	 
				}
			}
	});
	
});


</script>

<div style="padding: 10px 10px 10px 10px;">
	
	
	<ul id="groupAddTree" style="width: 200px;float: left;"></ul>
	
</div>


