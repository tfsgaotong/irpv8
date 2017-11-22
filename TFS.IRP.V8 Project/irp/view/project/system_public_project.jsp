<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> 
  <head>
     <title>圈子</title>
		<link rel="shortcut icon" href="<%=rootPath%>favicon.ico"
	type="image/x-icon" />
<link rel="bookmark" href="<%=rootPath%>favicon.ico" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/client/css/easyui.css" />
<link href="<%=rootPath%>client/images/normalize.css" rel="stylesheet"
	type="text/css" />
	<link href="<%=rootPath%>client/css/common1.css" rel="stylesheet"
	type="text/css" />
<link href="<%=rootPath%>client/css/oacss.css" rel="stylesheet"
	type="text/css" />
	<link rel="stylesheet" href="<%=rootPath %>client/images/project/common.css" type="text/css"></link>
<script type="text/javascript" src="<%=rootPath %>view/js/modernizr.custom.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath%>/admin/js/zyz_easydatecheck.js"></script>
	<script type="text/javascript" src="<%=rootPath %>client/js/jquery.uploadify.min.js"></script>
	<script type="text/javascript" src="<%=rootPath %>client/js/ztree/jquery.ztree.core.js"></script>
<%-- <jsp:include page="../include/client_skin.jsp" /> --%>
	<style type="text/css">body{behavior:url(hover.htc);}</style> 

<style type="text/css">
body{behavior:url(hover.htc);}
.searchSec .radios span {
margin-top: 0px;
}
a b{
    border-radius: 3px;
    border: none;
    background: #5f9ddd;
    overflow: hidden;
    font-size: 15px;
    color: #fff;
    padding: 5px 10px;
	margin-left: 10px;
	font-weight:normal;

}

 .editBar {
  float: right;
display: inline;
height: auto;
line-height: 20px;
    
}
</style> 

  </head>
<body onload="selected('projectli')" style="background: url()"> 
<script type="text/javascript"> 
	
	function projectList(_href){  
		var result = $.ajax({
			url: _href,
			type:"post",
			dataType: "json", 
		    async: false 
		}).responseText;  
		$('#project_listDiv').html(result);
	}
	function tabs(lidom){ 
	$('#projectstateselect').val("0"); 
	$('#projectH').find('a').each(function(){
		if(this.id ==lidom.id){ 
			this.className="over"; 
			var _href=	$(this).attr('link'); 
 			projectList(_href+"?pageNum=1&protype=0"); 
		}else{
			this.className="";
		}
	});  
}
//删除项目
function deleteproject(_projectId){
	$.dialog.confirm('您确定删除这个圈子吗？',function(){
		$.ajax({
				url: '<%=rootPath%>project/deleteproject.action',
				data:{'projectId':_projectId},
				type:"post",
				dataType: "json", 
			    async: false 
			}); 
			showoneproject(); 
	}); 
}

function showoneproject(){ 
	refrechProjectList();//刷新列表
	allprojectLeft();//刷新左边
}
function allprojectLeft(){
		var result = $.ajax({
			url: '<%=rootPath%>project/allproject.action?protype=0',
			data:{'taskorproject':'project'},
			type:"post",
			dataType: "json", 
		    async: false 
		}).responseText; 
		$('#leftMenu').html(result);
} 

 /** 首先进入，查询自己的负责的项目  */
$(function(){
	allprojectLeft();//得到左边
	projectList('<%=rootPath%>project/meoffproject.action?protype=0');
}); 
function refrechProjectList(){
	var project_state=$('#projectstateselect').val(); 
	$('#projectH').find('a').each(function(){
		if(this.className=="over"){  
			var _href=	$(this).attr('link');
			_href=_href+"?isClosed="+project_state+"&protype=0"; 
		 	var pageForm=$('#pageForm');
			if(pageForm!=null){
				var pageString=$('#pageForm').serialize(); 
				_href=_href+"&"+pageString;
		 	} 
			projectList(_href);//刷新列表 
		 }
	}); 
}
</script> 
<div class="bg01">
<!--头部菜单-->

  <jsp:include page="../include/client_head.jsp" />
   <section class="mainBox">
	<nav class="privateNav">
	</nav>
	</section>
<!--头部菜单end-->

<div class="mainBox">

<!--左侧内容-->
<div id="leftMenu"></div>
<!--左侧内容结束-->
 
<!--右侧内容-->
<div class="layoutMiddle1" style="float: right;margin-top: 0px;">
	
	<h1 id="projectH" class="zl3" style="font-size:15px; font-weight:normal;width: 700px;margin-top: 20px;float: left; height:100px;line-height:12px; text-align:left; font-family:'微软雅黑'; margin:0 0 0px 0px; color:#121212;">
	<a  href="javascript:void(0);" style="float: left;padding-top: 10px;" onclick="tabs(this)"  id="meOffProject"   link="<%=rootPath%>project/meoffproject.action" class="over" >我负责的圈子</a>
	<a href="javascript:void(0);" style="float: left;padding-top: 10px;" onclick="tabs(this)"  id="meJoinProject"  link="<%=rootPath%>project/mejoinproject.action">我参与的圈子</a>
	<a href="javascript:void(0);" style="float: left;padding-top: 10px;" onclick="tabs(this)"  id="meFocusProject"  link="<%=rootPath%>project/meattacetionproject.action">我关注的圈子</a>
	<a href="javascript:void(0);" style="float: left;padding-top: 10px;" onclick="tabs(this)"  id="allProject"     link="<%=rootPath%>project/allprojectlist.action">所有圈子</a>
	<span  style="float: left;padding-top:27px;margin-left: 25px;padding-right: 10px;">
	<select id="projectstateselect" onchange="refrechProjectList()">
              	<option value="0" <s:if test="isClosed==0">selected</s:if>>所有</option> 
                <option   value="<s:property value='@com.tfs.irp.project.entity.IrpProject@NOT_CLOSED'/>"<s:if test="@com.tfs.irp.project.entity.IrpProject@NOT_CLOSED==isClosed">selected</s:if>>进行中</option>
             	<option value="<s:property value='@com.tfs.irp.project.entity.IrpProject@IS_CLOSED'/>"<s:if test="@com.tfs.irp.project.entity.IrpProject@IS_CLOSED==isClosed">selected</s:if>>已完成</option>
             </select>
	</span>
	
	</h1> 
	 <input value="创建圈子" onclick="toaddproject()" style="float: right;background: url(../images/icon-02.png) no-repeat 9px center #62a53b;margin-top: 10px;border-radius:3px; font-size:18px; width:  220px;height:65px;color: white;background-color: #62a53b;" type="button"/>
	
	
<div id="project_listDiv" class="pan2" style="float: left;margin-left: 0px;"></div> 
<!--leftcontent-->
<!--leftcontent end-->
<!--右侧内容结束--> 
</div>
<!--尾部信息-->
 <jsp:include page="../include/client_foot.jsp" />
<!--尾部信息end-->
</div>
</body>
</html>

