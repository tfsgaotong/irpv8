<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> 
  <head>
     <title>任务</title>
	
	<link rel="shortcut icon" href="<%=rootPath%>favicon.ico"
	type="image/x-icon" />
<link rel="bookmark" href="<%=rootPath%>favicon.ico" />
<link href="<%=rootPath%>client/images/normalize.css" rel="stylesheet"
	type="text/css" />
	<link href="<%=rootPath%>client/css/common1.css" rel="stylesheet"
	type="text/css" />
	<%-- <link href="<%=rootPath%>view/css/common.css" rel="stylesheet"
	type="text/css" /> --%>
<link href="<%=rootPath%>client/css/oacss.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" href="<%=rootPath %>client/css/zTreeStyle.css" type="text/css"/>
<script src="<%=rootPath%>client/images/modernizr.custom.js"></script>
<script src="<%=rootPath%>client/images/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/modernizr.custom.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.cookie.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/admin/js/zyz_easydatecheck.js"></script>
	<script type="text/javascript" src="<%=rootPath %>client/js/jquery.uploadify.min.js"></script>
	<script type="text/javascript" src="<%=rootPath %>client/js/ztree/jquery.ztree.core.js"></script>
<jsp:include page="../include/client_skin.jsp" />
	<style type="text/css">body{behavior:url(hover.htc);}</style>
<style type="text/css">
.searchSec .radios span {
margin-top: 0px;
}
</style> 

  </head>
<body onload="selected('projectli')" > 
<script type="text/javascript"> 
//分页
function page(_nPageNum){
var project_id=$('#projectselect').val(); 
	$('#pageForm').find('input[name="pageNum"]').val(_nPageNum);   
	$('#ProjectTaskH').find('a').each(function(){   
			if(this.className=="over"){  
				   var sData =$(this).attr('_link');  
					var pageForm=$('#pageForm'); 
					if(pageForm!=null){
						var pageString=$('#pageForm').serialize(); 
						sData=sData+"?"+pageString;
				 	}
				 	 if(project_id!="0"){
				    	showTask(sData+"&projectId="+project_id);
				    }   
				 showTask(sData);
			} 
	});  
}  
//得到左边菜单
function allprojectLeft(){ 
		var result = $.ajax({
			url: '<%=rootPath%>project/allproject.action?protype=0',
			type:"post",
			data:{'taskorproject':'project'},
			dataType: "json", 
		    async: false 
		}).responseText; 
		$('#leftMenu').html(result);
		
}  
function tabs(adom){ 
	$("#projectselect").val("0"); 
	$('#ProjectTaskH').find('a').each(function(){  
		if(adom==null ||adom=='undifined'){ 
			if(this.className=="over"){  
			 	adom=this; 
			} 
		}
		this.className=""; 
	}); 
	if(adom!=null){  
		adom.className="over";  
	    var sData =$(adom).attr('_link');    
		showTask(sData+"?pageNum=1&themetype=0");
    }  
} 
	//刷新任务列表
	function refrechTaskList(){
	var project_id=$('#projectselect').val(); 
	$('#ProjectTaskH').find('a').each(function(){  
			if(this.className=="over"){  
			 	adom=this; 
				adom.className="over";  
			    var sData =$(adom).attr('_link');
			    if(project_id=="0"){
					showTask(sData+"?pageNum=1&themetype=0");
			    }else{
			    
			    	showTask(sData+"?pageNum=1&themetype=0&projectId="+project_id);
			    }  
			} 
	}); 
	}
//显示任务列表
function showTask(sData){
	 
	var result = $.ajax({
			url:sData,
			type:"post",
			dataType: "json", 
		    async: false 
		}).responseText;   
	 $('#TaskShowDiv').html(result);  
}
 /** 首先进入，   */
	 $(function(){
	 		 allprojectLeft();//得到左边    
			$('#tabMenu').find('a').each(function(){   
				if(this.className=="c"){  
					 this.className='';
						$(this).removeClass("c"); 
				}    else{ 
					$(this).addClass("c"); 
				}
		}); 
	 		 tabs(); 	  
	 });

</script> 
<div class="bg01">
<!--头部菜单-->

	 <jsp:include page="../../view/include/client_head.jsp" />

<!--头部菜单end-->
<section class="mainBox">
	<nav class="privateNav">
	</nav>
	</section>
<div class="mainBox" >

<!--左侧内容-->
<div  id="leftMenu"></div>
<!--左侧内容结束--> 
<!--右侧内容-->
	<div class="layoutMiddle1" style="float: right;margin-right: 5px;margin-top: 0px;"> 
		<div>
		<h1 id="ProjectTaskH" class="zl3" style="font-size:18px; font-weight:normal; line-height:50px; border-bottom:1px  solid #efefef; text-align:left; font-family:'微软雅黑'; margin:0 0 0px 5px; color:#121212;">
		<a href="javascript:void(0);" onclick="tabs(this)" class="over" _link="<%=rootPath%>project/myalltask.action">我负责的任务</a>
		<a href="javascript:void(0);" onclick="tabs(this)" _link="<%=rootPath%>project/meispersonalltask.action">我参与的任务</a> 
		<a href="javascript:void(0);" onclick="tabs(this)" _link="<%=rootPath%>project/givealltask.action">我给别人的任务</a>
		
		<span  style="float: right;padding-top: 20px;font-size: 15px;">
			<select id="projectselect" onchange="refrechTaskList()">
	              	<option value="0" selected="selected">全部</option> 
	              	<s:iterator value="irpProjects">
	              	<option value="<s:property value='projectid'/>" ><s:property value="prname"/></option>
	              	
	              	</s:iterator>
	         </select>
		</span>
		</h1>
		</div> 
		<div class="  clearfix" >
   	   		<div class="left_wbi"  id="TaskShowDiv"> </div>
		</div>  
	</div> 

<!--右侧内容结束--> 
</div>
<!--尾部信息-->
<jsp:include page="../include/client_foot.jsp" />
<!--尾部信息end-->
</div>
</body>
</html>

