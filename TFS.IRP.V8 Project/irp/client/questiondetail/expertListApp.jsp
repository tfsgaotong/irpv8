<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<script type="text/javascript">
$(function(){ 
	initZTree();
	sepage(1);
});

function initZTree(){
	$.fn.zTree.init($("#categoryTree"), {
		view: {
			showIcon: true
		},
		data: {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "pId"
			}
		},
		async: {
			enable: true,
			url:"<%=rootPath%>phone/getAllCategory.action?showExpertList=2&token="+$('#tokens').val() 
		},
		callback: {
			onClick: function (event, treeId, treeNode, clickFlag){
				if(treeNode){
					if($('#selectExportForm')){
						$('#selectExportForm').find('input[name="categoryId"]').val(treeNode.id);
					}
					sepage(1);
				}
			}
		} 
	});
}

function sepage(_nPageNum){
	var pageSize,categoryId,searchWord;
	if($('#selectExportForm')){
		pageSize = $('#selectExportForm').find('input[name="pageSize"]').val();
		categoryId = $('#selectExportForm').find('input[name="categoryId"]').val();
		searchWord = $('#selectExportForm').find('input[name="searchWord"]').val();
	}
	$.ajax({
		type:'post',
		url:'<%=rootPath%>phone/select_expert_list.action?token='+$('#tokens').val(),
		data:{
			pageNum: _nPageNum,
			pageSize: pageSize,
			categoryId: categoryId,
			searchWord: searchWord,
			searchType: 'all'
		},
		success:function(html){
			$('#selExpertList').html(html);
		}
	});
}

function searchExpert(){
	var treeObj = $.fn.zTree.getZTreeObj("categoryTree");
	var nodes = treeObj.getSelectedNodes();
	if(nodes.length==1){
		$('#selectExportForm').find('input[name="categoryId"]').val(nodes[0].id);
	}
	$('#selectExportForm').find('input[name="searchWord"]').val($('#expertName').val());
	sepage(1);
}
</script>
<input type="hidden" value="<s:property value='tokens'/>" id="tokens"/>
<div id="expertDiv">
	<div style="background-color:#D1E9E9;text-align: right; padding-right: 5px;">
		请输入专家名称：<input type="text" id="expertName" onclick="this.select()" /> <input type="button" value="搜索" style="cursor:pointer;" onclick="searchExpert()" />		
	</div>
	<div class="main-gr" style="width: auto;margin-top:0px;">
		<div class="left" style="width: 160px;">
			<ul id="categoryTree" class="ztree" style="width:130px;"></ul>
		</div>
		<!--右侧内容-->
		<div id="selExpertList" class="right" style="width:210px;">
			加载专家信息...
		</div>
	</div>
</div>