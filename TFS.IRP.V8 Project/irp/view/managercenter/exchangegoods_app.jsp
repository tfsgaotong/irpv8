<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<html lang="en" class="no-js">
<head>
<meta charset="utf-8">
<title>问答记录</title>
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
<link href="<%=rootPath%>view/css/normalize.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath%>view/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath %>view/css/easyui.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath%>view/css/icon.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="<%=rootPath%>client/js/skins/iblue.css" type="text/css"></link>
<style>
.clearfix:before{
}
.clearfix:after {       
    content: ".";     /*内容为“.”就是一个英文的句号而已。也可以不写。*/
    display: block;   /*加入的这个元素转换为块级元素。*/
    clear: both;     /*清除左右两边浮动。*/
    visibility: hidden;      /*可见度设为隐藏。注意它和display:none;是有区别的。visibility:hidden;仍然占据空间，只是看不到而已；*/
    line-height: 0;    /*行高为0；*/
    height: 0;     /*高度为0；*/
    font-size:0;    /*字体大小为0；*/
}
body{
	font-size:14px;
}
</style>
<!-- 

 -->
<script type="text/javascript" src="<%=rootPath%>view/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/modernizr.custom.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
$(function(){
	var showWay = $('#<s:property value="showway" default="microblogP" />');
	if(showWay.length>0){
		showWay.addClass('current');
	}else{
		$('.layoutLeft p:first').addClass('current');
	}
	findManagerConn();
});

function findmeeting(_obj){
	var jqObj = $(_obj).parent();
	$('.layoutLeft').find('.current').removeClass('current');
	jqObj.addClass('current');
	var sHref =$("#room").attr('_href');
	if(sHref){
		var managerHtmlConn = $.ajax({
			type : 'get',
			url : sHref,
			cache : false,
			async : false
		}).responseText;
		$('#manager_conn').html(managerHtmlConn);
	}
}
function findcovert(_obj){
	var jqObj = $(_obj).parent();
	$('.layoutLeft').find('.current').removeClass('current');
	jqObj.addClass('current');
	var sHref =$("#covert").attr('_href');
	if(sHref){
		var managerHtmlConn = $.ajax({
			type : 'get',
			url : sHref,
			cache : false,
			async : false
		}).responseText;
		$('#manager_conn').html(managerHtmlConn);
	}
}
//请求选择的管理内容
function findManagerConn(){
	var tokens = $("#tokens").val();
	if('/irp/phone/goodslist_app.action'){
		var managerHtmlConn = $.ajax({
			type : 'get',
			url : '/irp/phone/goodslist_app.action?token='+tokens,
			cache : false,
			async : false
		}).responseText;
		$('#manager_conn').html(managerHtmlConn);
	}
}

function handel(_leaveapplyid,_emergency){
 $.dialog.confirm("是否要同意这条申请？",function(){
 	$.ajax({
					url:'<%=rootPath%>leave/upstatus.action',
					type:'post',
					data:{
					leaveconfigid:_leaveapplyid,
					emergency:_emergency,
					checkmore:0
					},
					cache : false,
					success:function(data){
						if(data==1){
						$.dialog.tips('申请处理成功',1,'32X32/succ.png',function(){
							findManagerConn();
				    	});	
						}
					}
		}); 
 },function(){});
}

function detailView(id,ismanager){
	hrefStr="<%=rootPath%>leave/getleavebyidFromManager.action?isMangcenter="+ismanager+"&leaveapplyid="+id;
	window.open(hrefStr);	
}
function refuse(_leaveapplyid,_emergency){
	var result = $.ajax({
		url: '<%=rootPath%>leave/torefusefrom.action',
	    async: false,
	    cache: false,
	    data:{
			leaveconfigid:_leaveapplyid,
			emergency:_emergency,
			checkmore:0
			}
	}).responseText;
	//弹出对话框
		$.dialog({
	title:'拒绝信息',
	content: result ,
	max: false,
    min: false,
    height:40,	
	ok: function(){
	var validate = $('#refuseForm').form('validate');
		/* 执行提交操作表单 */
		$('#refuseForm').form('submit', {   
		    url:'<%=rootPath%>leave/upstatus.action',   			   
		    success:function(data){   
		    	$.messager.progress('close');
		        if(data=='1'){
		        	$.dialog.tips('拒绝申请成功',1,'32X32/succ.png',function(){
					findManagerConn();
					});			    
		        }else{
		        	$.dialog.tips('拒绝申请失败',1,'32X32/fail.png',function(){
					});	
		        }  
		    }   
		});
		return validate;
    },
    okVal:'确定',
    cancelVal: '取消',
    cancel: function(){
    	$("#typeadddiv").dialog("close");
    },
    lock: true,
    padding: 0,
});		
}
</script>
</head>
<body>
	<input type="hidden" value="<s:property value='tokens'/>" id="tokens"/>
	<div class="clearfix" style="width:100%;" id="manager_conn"></div>
</body>
</html>
