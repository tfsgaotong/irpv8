<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
<script type="text/javascript" src="<%=rootPath %>client/js/jquery.easyui.min.js"></script> 

<style type="text/css">
#item{
	padding: 10px 10px;
}	
</style>
<script type="text/javascript">
function searchMeTo(){
	var priority=$('#priority').val();
	var keyword=$('#keyword').val();
	var neweststate=$('#neweststate').val();
	var projectId=<s:property value="projectId"  />;
	var order=$('#order').val();
	var ordertype=$('#ordertype').val();
	var urlStr="<%=rootPath%>bug/metobugsearch.action";
	$.ajax({
		url: urlStr,
		type:"post",
		data:{
			'priority':priority,
			'keyword':keyword,
			'projectId':projectId,
			'state':neweststate,
			'order':order,
			'ordertype':ordertype
		},
	    async: false,
	    cache: false,
	    success:function (dat){
	    	$('#info_page').html(dat);
	    }
	});
}

/**
 * 按照不同条件排序
 */
 
function selOrder(type,orderStr){
	if($('#ordertype').val()==type){
		$('#order').val(orderStr+'  desc');
		$('#ordertype').val('');
	}else{
		$('#order').val(orderStr);
		$('#ordertype').val(type);
	}
	//alert($('#order').val());
	searchMeTo();
}
</script>

</head>
  <body>
  <form id="pageForm">
	<s:hidden name="pageNum" id="pageNum" />
	<s:hidden name="pageSize" id="pageSize" value="10"/>
	<s:hidden name="projectId" id="projectId"/>
  </form>
    <div id="item" style="background: url('<%=rootPath%>client/images/search_bug.gif') repeat-x;width:98%;border: thin solid #BBB; ">
    	<span>优先级</span>
    	<select style="width: 120px;" id="priority" onchange="searchMeTo()">
    		<option value="">==不限==</option>
    		<option value="1">低</option>
    		<option value="2">中</option>
    		<option value="3">高</option>
    		<option value="4">紧急</option>
    		<option value="5">严重</option>
    	</select>
    	<span style="margin-left:50px;">状态</span>
    	<select style="width: 120px;" id="neweststate"  onchange="searchMeTo()">
    		<option value="">==不限==</option>
    		<option value="1">未修复</option>
    		<option value="2">待审核</option>
    		<option value="3">已解决</option>
    	</select>
		<span style="margin-left: 50px;" >关键字</span>
		<input   type="text" id="keyword"  width="120px"/>   
		<a type="text"  onclick="searchMeTo()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" plain="true">搜索</a> 	
    </div>
        <script type="text/javascript">
    //更改回车事件
    $(function(){
        $('#keyword').bind('keypress',function(event){
            if(event.keyCode == "13")    
            {
            	setTimeout(function(){
            		searchMeTo();
           		 },0);
            	
            	event.preventDefault ? event.preventDefault() : (event.returnValue = false);
            }
        });
    });
    </script>
  </body>
</html>
