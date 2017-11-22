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

<script type="text/javascript">
/**
 * 初始化版本/模块信息
 */
function initBugConfig(){
	var pid=<s:property value="projectId" />;
	urlStr="<%=rootPath %>/bugconfig/projectallbugconfig.action?projectId="+pid;
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
	urlStr="<%=rootPath %>/project/teamandprojectperson.action?projectId="+pid;
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
	urlStr="<%=rootPath %>/project/initproinfo.action?projectId="+pid;
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
<body>
<div>
<span style="font-size: 16px;font-weight: 800;">项目信息</span>
	<br/>
	<br/>
	<div id="proinfo">
	<!-- 基本信息部分 -->
	</div>
	<div id="bugconfig" style="width: 100%;margin-left:80px;margin-top:30px;">
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
