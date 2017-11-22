<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 显示历史版本 -->
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<title>版本对比</title>
	<link rel="shortcut icon" href="<%=rootPath%>favicon.ico" type="image/x-icon" />
    <link rel="bookmark" href="<%=rootPath%>favicon.ico" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>client/css/easyui.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>client/css/icon.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>client/css/oacss.css" />
	<link href="<%=rootPath%>client/css/oacss.css" rel="stylesheet" type="text/css" />
	<link href="<%=rootPath%>client/css/asx.css" rel="stylesheet" type="text/css" />
	<link href="<%=rootPath%>view/css/normalize.css" rel="stylesheet" type="text/css">
	<link href="<%=rootPath%>view/css/common.css" rel="stylesheet" type="text/css">
	<jsp:include page="../include/client_skin.jsp" />
	<script type="text/javascript" src="<%=rootPath%>client/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>client/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>client/js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=rootPath%>client/js/swfobject.js"></script>
	<script type="text/javascript" src="<%=rootPath%>client/js/jquery.uploadify.min.js"></script>
	<style type="text/css">
	.wtgy {
		height: 28px;
		line-height: 28px;
		border: 1px solid rgb(209, 209, 209);
		color: rgb(102, 102, 102);
		padding: 0px 5px;
		width: 300px;
		margin: 0 0 0 5px;
	}
	
	.btn_ccw {
		background: none repeat scroll 0 0 #63C7E6;
		color: #FFFFFF;
		display: block;
		width: 100px;
		float: left;
		line-height: 28px;
		font-size: 18px;
		padding: 0px 5px;
		font-style: normal;
		height: 30px;
		text-align: center;
	}
	
	body {
		behavior: url(hover.htc);
	}
	
	.searchSec .radios span {
		margin-top: 0px;
	}
	</style>

</head>
 
<script type="text/javascript">
$(function(){
	//选择头部标签
	//selected('wordterm');
	findData(1);
});
/**
 * 获得数据
 */
function findData(pagenum){
	var hostorytermid =  '<s:property value="hostorytermid" />';
	$.ajax({
		type:'post',
		url:'<%=rootPath%>term/gethistorydata.action',
		data:{
			hostorytermid:hostorytermid,
			pagenumver:pagenum
		},
		cache:false,
		success:function(content){
			$('#hiscontent').html("");
			$('#hiscontent').html(content);
		}
		
	});
	
	
} 
/**
 * 查看历史版本细览
 */
function findVerFL(_termid){
	window.open('<%=rootPath%>term/gohistoryvcontent.action?historyid='+_termid);
}
//分页
function pagetermver(_pnum){
	findData(_pnum);
}
/**
 * 选择比较标签
 */
var firsttermid = 0;
var secondtermid = 0;
function choiceCK(_termid){
	if(_termid==secondtermid){
		secondtermid = 0;
		return false;
	}
	if(_termid==firsttermid){
		firsttermid = 0;
		return false;
	}
	if(firsttermid>0 && secondtermid>0){
		if(_termid!=firsttermid && _termid!=secondtermid){
			var onectcmid = "#termck"+firsttermid;
			$(onectcmid).attr("checked",false);	
			firsttermid = secondtermid;
			secondtermid = _termid;
		}else{
			if(_termid==secondtermid){
				secondtermid = 0;
			}
			if(_termid==firsttermid){
				firsttermid = 0;
			}
			
		}
	}else{
		if(firsttermid==0){
			firsttermid = _termid;
		}else{
			secondtermid = _termid;
			
		}
	}
}
/**
 * 点击版本比较
 */
function compareVersion(){
	if(firsttermid>0 && secondtermid>0){
		window.open("<%=rootPath%>term/versioncompare.action?firsttermid="+firsttermid+"&secondtermid="+secondtermid);
	}else{
		$.dialog.alert("请选择需要对比的两个版本!");
	}
}
</script>
<body style="background: url()" >
    <jsp:include page="../../view/include/client_head.jsp" />
    <section class="mainBox">
        <nav class="privateNav"></nav>
	</section>
    <div class="main" style="background: url('');width: 1200px;min-height: 60vh;">
		<div id="hiscontent"></div>
    </div>
    <jsp:include page="../../view/include/client_foot.jsp" />
</body>
</html>