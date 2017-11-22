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
function searchToMe(){
	var priority=$('#priority').val();
	var keyword=$('#keyword').val();
	var projectId=<s:property value="projectId"  />;
	var urlStr="<%=rootPath%>bug/tomebugsearch.action";
	$.ajax({
		url: urlStr,
		type:"post",
		data:{
			'priority':priority,
			'keyword':keyword,
			'projectId':projectId
		},
	    async: false,
	    cache: false,
	    success:function (dat){
	    	$('#info_page').html(dat);
	    }
	});
}

/**
 * 按照不同条件排序top
 */
function selOrderTop(type,orderStr){
	if($('#ordertoptype').val()==type){
		$('#ordertop').val(orderStr+'  desc');
		$('#ordertoptype').val('');
	}else{
		$('#ordertop').val(orderStr);
		$('#ordertoptype').val(type);
	}
	var projectId=<s:property value="projectId"  />;
	var urlStr="<%=rootPath %>bug/tomebugtoppage.action?";
	var ordertop=$('#ordertop').val();
	var ordertoptype=$('#ordertoptype').val();
	var priority=$('#priority').val();
	var keyword=$('#keyword').val(); 
	//alert(ordertop);
	$.ajax({
		type:"post",
		url:urlStr,
		data:{
			'priority':priority,
			'keyword':keyword,
			'ordertoptype':ordertoptype,
			'ordertop':ordertop,
			'projectId':projectId
		},
		async:false,
		cache:false,
		success:function(html){
			if(html!=null){
				$('#buginfotop').html(html);
			}
		}
	});	
}

/**
 * 按照不同条件排序bottom
 */
function selOrderBottom(type,orderStr){
	if($('#orderbottomtype').val()==type){
		$('#orderbottom').val(orderStr+'  desc');
		$('#orderbottomtype').val('');
	}else{
		$('#orderbottom').val(orderStr);
		$('#orderbottomtype').val(type);
	}
	var projectId=<s:property value="projectId"  />;
	var urlStr="<%=rootPath %>bug/tomebugbottompage.action?";
	var orderbottom=$('#orderbottom').val();
	var orderbottomtype=$('#orderbottomtype').val();
	var priority=$('#priority').val();
	var keyword=$('#keyword').val(); 
	$.ajax({
		type:"post",
		url:urlStr,
		data:{
			'priority':priority,
			'keyword':keyword,
			'orderbottomtype':orderbottomtype,
			'orderbottom':orderbottom,
			'projectId':projectId
		},
		async:false,
		cache:false,
		success:function(html){
			if(html!=null){
				$('#buginfobottom').html(html);
			}
		}
	});	
}
</script>

</head>
  <body>
    <div  id="item" style="background: url('<%=rootPath%>client/images/search_bug.gif') repeat-x;width:98%;border: thin solid #BBB; ">
    	<span>优先级</span>
    	<select style="width: 120px;" id="priority"  onchange="searchToMe()">
    		<option value="">==不限==</option>
    		<option value="1">低</option>
    		<option value="2">中</option>
    		<option value="3">高</option>
    		<option value="4">紧急</option>
    		<option value="5">严重</option>
    	</select>
		<span style="margin-left: 50px;" >关键字</span>
		<input   type="text" id="keyword"  width="120px"/>   
		<a type="text"  class="easyui-linkbutton"  onclick="searchToMe()" data-options="iconCls:'icon-search'" plain="true">搜索</a> 	
    </div>
      <script type="text/javascript">
    //更改回车事件
    $(function(){
        $('#keyword').bind('keypress',function(event){
            if(event.keyCode == "13")    
            {
            	setTimeout(function(){
            		searchToMe();
           		 },0);
            	
            	event.preventDefault ? event.preventDefault() : (event.returnValue = false);
            }
        });
    });
    </script>
  </body>
</html>
