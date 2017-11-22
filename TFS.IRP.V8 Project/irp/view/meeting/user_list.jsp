<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>选择申请人</title>
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/oacss.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/icon.css" />
<link id="skin" rel="stylesheet" type="text/css" />
</head> 
<script type="text/javascript">
$(function(){	
var userids='${userids}';
	$('#ttnote').tree({		
					url:'<%=rootPath%>user/find_tree_node.action',
					animate:false,
					lines:true,
					onClick: function(node){
						$('#dialogPageForm').find("input[name='groupId']").val(node.id);
						var queryString =$('#dialogPageForm').serialize();	
						userSearch('<%=rootPath %>asseroomapply/queryUser.action?'+queryString);
					},
					onBeforeLoad: function (node, param) {
					if(node==null){
					param.id= 0;
					param.groupType=1;
					}else{
					param.id= node.id
					param.groupType=1;
					}
            },onLoadSuccess:function(node, data){
				if(!node){
					
					var root = $('#ttnote').tree('getRoot');
					$(root.target).find(".tree-checkbox").removeClass("tree-checkbox tree-checkbox0");
					$('#ttnote').tree('expand',root.target);
					 $('#dialogPageForm').find("input[name='groupId']").val(node.id);
						var queryString =$('#dialogPageForm').serialize();						
						userSearch('<%=rootPath %>asseroomapply/queryUser.action?userids='+userids+'&'+queryString);
				}else{
				 		$('#dialogPageForm').find("input[name='groupId']").val(1);
						var queryString =$('#dialogPageForm').serialize();						
						userSearch('<%=rootPath %>asseroomapply/queryUser.action?userids='+userids+'&'+queryString);
				
				}
			},
	});
});


function searchbox(){
	var name = $('#sType').val();
	$('#dialogPageForm').find('input[name="searchType"]').val(name);
	var queryString =$('#dialogPageForm').serialize(); 
	userSearch('<%=rootPath %>asseroomapply/queryUser.action?userids='+userids+'&'+queryString);
}	
function userSearch(_url,queryString){
var value = $('#sWord').val();
	if(value=='请输入检索词'){
	value='';
	}
	var result = $.ajax({
			url: _url,
			type:'post',
			data:{
			searchWord:value,
			},
		    async: false,
		    cache: false
		}).responseText;
	$('#usercontent').html(result);
}

//审核人分页
function pagecheck(_nPageNum){
	$('#dialogPageForm').find('input[name="pageNum"]').val(_nPageNum);	
	var queryString = $('#dialogPageForm').serialize();
	userSearch('<%=rootPath %>asseroomapply/queryUser.action?userids='+userids+'&'+queryString);
}


//审核人查询分页
function pagequerycheck(_nPageNum){

	
	$('#dialogPageForm').find('input[name="pageNum"]').val(_nPageNum);
	$('#dialogPageForm').find('input[name="searchWord"]').val($('#dialogPageForm').find('input[name="searchWord"]').val());
	
	var queryString = $('#dialogPageForm').serialize();
	userSearch('<%=rootPath %>asseroomapply/queryUser.action?userids='+userids+'&'+queryString);
	
}
</script>
<body>
 <table width="100%" cellspacing="0" cellpadding="0">
<tbody>
<tr>
<td id="treeTd" style=" vertical-align: top; width: 30%; text-align: left;">
<div class="tab" style="  height: 250px;width:140px">
 <ul id="ttnote"  class="easyui-tree"  >       
                </ul>
</div>
</td>
<td style=" vertical-align: top; width: 70%; text-align: center;">
 <div id="usercontent">
</div>

</td>
</tr>
</tbody>
</table>
 </table>
</body>
</html>
