<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>

<style type="text/css">
.selper{float: left;}
.bot{float: left;margin: 70px 30px 0 30px;}
</style>
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
	
	<link href="<%=rootPath %>client/css/easyui.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/icon.css" />
	<link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css">
	<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css">
	<link href="<%=rootPath%>/client/css/oacss.css" rel="stylesheet" type="text/css" />
	<jsp:include page="../include/client_skin.jsp" />  
	<script type="text/javascript" src="<%=rootPath %>client/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="<%=rootPath %>view/js/jquery.easyui.min.js"></script> 
	<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
	<script type="text/javascript" src="<%=rootPath %>client/js/easyui-lang-zh_CN.js"></script>  
<script type="text/javascript">
/**
 * 初始化版本/模块信息
 */
function initBugConfig(){
	var pid=<s:property value="projectId" />;
	urlStr='<%=rootPath %>/phone/projectallbugconfig.action?projectId='+pid+'&token='+$('#tokens').val();
	var result = $.ajax({
		url:urlStr,
		type:"post",
		//dataType: "json", 
	    async: false,
	    cache: false,
	    success:function(html){
	    	$('#bugconfig').html(html); 
	    }
	});  
}

/**
 * 初始化人员信息
 */
function initPersons(){
	var pid=<s:property value="projectId" />;
	//teamandprojectperson
	//alert(pid);
	urlStr='<%=rootPath %>/phone/teamandprojectperson.action?projectId='+pid+'&token='+$('#tokens').val();
	var result = $.ajax({
		url:urlStr,
		type:"post",
		//dataType: "json", 
	    async: false,
	    cache: false,
	    success:function(html){
	    	$('#persons').html(html); 
	    }
	});  
}

/**
 * 初始化项目基本信息
 */
function proinfoInit(){
	var pid=<s:property value="projectId" />;
	urlStr='<%=rootPath %>/phone/initproinfo.action?projectId='+pid+'&token='+$('#tokens').val();
	var result = $.ajax({
		url:urlStr,
		type:"post",
		//dataType: "json", 
	    async: false,
	    cache: false,
	    success:function(html){
	    	$('#proinfo').html(html); 
	    }
	});  
}

/**
 *设置左侧菜单css
 */	  
 function initleftsel(){
	  var pid=<s:property  value="projectId"   />;
		$("#dlmenutabs").find("a").each(function(_index,_this2){
			if($(_this2).find('input[type=hidden]').val()==pid){
				$(_this2).css("background-color","#DFF2F8");
			}else{
				$(_this2).css("background-color","");
			}
		});
 }
 
/*************************首次进入*********************************/
 $(function (){
	 proinfoInit();
	 initBugConfig();
	 initPersons();
	 initleftsel(); 
 });
/*****************************************************************/
</script>
</head>
<body style="background-image: url('');width: 100%">
<input type="hidden" value="<s:property value='tokens'/>" id="tokens"/>
<div>
<div align="center"><span style="font-size: 16px;font-weight: 800;">项目信息</span></div>
	<br/>
	<br/>
	<div id="proinfo">
	<!-- 基本信息部分 -->
	</div>
	<div id="bugconfig" style="width: 100%;margin-top:30px;">
	<!-- 版本/模块部分 -->
	</div>
	<br/>
	<div style="width: 100%;">&nbsp;</div><br/>
	<div id="persons" >
	<!-- 人员部分 -->
	</div>
</div>

</body>
</html>
