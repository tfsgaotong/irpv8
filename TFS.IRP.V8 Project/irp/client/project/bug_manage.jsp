<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.tfs.irp.util.LoginUtil"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> 
  <head>
     <title>BUG</title>

	<link rel="shortcut icon" href="<%=rootPath%>favicon.ico"
	type="image/x-icon" />
<link rel="bookmark" href="<%=rootPath%>favicon.ico" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/client/css/easyui.css" />
<link href="<%=rootPath%>client/images/normalize.css" rel="stylesheet"
	type="text/css" />
	<link href="<%=rootPath %>client/css/workhome-3482.min.css" rel="stylesheet" type="text/css" />
	<link href="<%=rootPath %>client/css/lib-3322.min.css" rel="stylesheet" type="text/css" /> 
	<link href="<%=rootPath%>client/css/common1.css" rel="stylesheet"
	type="text/css" />
	<link rel="stylesheet" href="<%=rootPath%>view/css/oapf-blue.css" type="text/css"></link>
<link href="<%=rootPath%>client/css/oacss.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/modernizr.custom.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=rootPath%>dwr/engine.js"></script>  
<script type="text/javascript" src="<%=rootPath%>dwr/util.js"></script> 
<script type="text/javascript" src="<%=rootPath%>dwr/interface/chatvolet.js"></script>  
<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<jsp:include page="../include/client_skin.jsp" />
	<style type="text/css">body{behavior:url(hover.htc);}</style> 

<style type="text/css">
body{behavior:url(hover.htc);}
.searchSec .radios span {
margin-top: 0px;
}	


</style> 

  </head>
<body style="background: url()"> 

<div class="bg01">
<!--头部菜单-->

  <jsp:include page="../../view/include/client_head.jsp" />
   <section class="mainBox">
	<nav class="privateNav">
	</nav>
	</section>
<!--头部菜单end-->

<div class="main-gr" style="width: 1200px;">

<!--左侧内容-->
<div class="left" id="leftMenu" ></div>
<!--左侧内容结束-->
 
<!--右侧内容-->
<div class="right" style="max-height: 10000;width: 960px;">
	<s:hidden  name="nstate" id="nstate" />
	<div>
		<div style="float: left;">
			<h1 id="projectH" class="zl3" style="font-size:15px; font-weight:normal; line-height:50px; text-align:left; font-family:'微软雅黑'; margin:0 0 0px 5px; color:#121212;display: none;">
				<a href="javascript:void(0);"  onclick="tabss(this)"  id="topagestatistics"   link="<%=rootPath%>bug/topagestatistics.action" class="over" >项目概况</a>
				<a href="javascript:void(0);"  onclick="tabss(this)"  id="tomebug"  link="<%=rootPath%>bug/tomebug.action">分配给我的bug</a>
				<a href="javascript:void(0);"  onclick="tabss(this)"  id="bugmeto"  link="<%=rootPath%>bug/bugmeto.action">我创建的bug</a>
				<a href="javascript:void(0);"  onclick="tabss(this)"  id="allProject"     link="<%=rootPath%>bug/simpleprojectbug.action">所有bug</a>
				<a href="javascript:void(0);"  onclick="tabss(this)"  id="bugStatistics"     link="<%=rootPath%>bug/bugstatistics.action">Bug统计</a>
			</h1> 
		</div>
		<div class="columnInfo" id="addbugbtn" style="float:right;padding:2px 0 0 0 ;height: 70px;">
			<input  type="button" style="background: url(../images/icon-02.png) no-repeat 9px center #62a53b; background-color: #62a53b;border-radius:5px;width:250px;height:65px;color:#fff; font-size:18px;padding-left:20px;background-position:6px;" class="create" onclick="toAddBugpage();" value="创建Bug">
		</div>
	</div>
	<div style="clear: both;"></div>
	<div id="newprojectdiv">
				<div class="columnInfo" style="width: 100%;height: 100px ;" >
					<input type="button" style="background: url(../images/icon-02.png) no-repeat 9px center #62a53b;margin:10px 0 10px 0;border-radius:3px; font-size:18px; width: 260px;height:65px;color: white;background-color: #62a53b;" onclick="toaddproject()" class="create"  id="getCworkPopup" value="新建一个项目" ></input>
				</div>
				
			<style type="text/css">
				.xm_01{
					float: left;
					line-height: 40px;
					text-align: center;
				}
			</style> 
			<div style="float: left;width: 100%;background:#ddedfe;font-size: 12px;font-weight: bold;border-bottom: 2px solid #fff;">
				<div class="xm_01" style="width: 28%;text-align: left;margin-left: 10px;">项目名称</div>
				<div class="xm_01" style="width: 25%;">加入日期</div>
				<div class="xm_01" style="width: 25%;">是否创建者</div>
				<div class="xm_01" style="width: auto;">操作</div>
			</div><br/>
		<s:iterator value="irpProjects"  >
			<div style="float: left;width: 100%;background:#f6f6f6;font-size: 12px;font-weight: bold;border-bottom: 2px solid #fff;">
				<div class="xm_01" style="width: 28%;text-align: left;margin-left: 10px;">
				<a href="javascript:void(0);"  onclick="projectMenuTab(this,<s:property value="projectid" />)" > 
   	  				<s:property value="prname"/>
   	  			</a> </div>
				<div class="xm_01" style="width: 25%;"><s:date name="starttime" format="yyyy-MM-dd" /> </div>
				<div class="xm_01" style="width: 25%;">
					<s:if test="loginuserid==cruserid">是</s:if> 
					<s:else>否</s:else>	 
				</div>
				<div class="xm_01" style="width: auto;">
					<a href="javascript:void(0)" onclick="gotoproject(this)">[进入]<input type="hidden"  value="<s:property value='projectid' />" /></a>
					<s:if test="loginuserid==cruserid">
					<a href="javascript:void(0)" onclick="editorProject(this)">[编辑]</a>
					</s:if> 
					<s:else>
					<a href="javascript:void(0)" onclick="outofproject(this)">[退出项目]<input type="hidden"  value="<s:property value='projectid' />"/></a>
					</s:else> 
				</div>
			</div><br/>
		</s:iterator>
	</div>
	<!--div  --> 
	<div id="projectmaindiv" >
		
	</div>
<!--leftcontent-->
<!--leftcontent end-->
</div>
<!--右侧内容结束--> 
</div>
</div>
<!--尾部信息-->
 <jsp:include page="../include/client_foot.jsp" />
<!--尾部信息end-->

<script type="text/javascript">  
	function projectList(_href){
		var pid=$('#projectId').val();
		var urlStr=_href+"&projectId="+pid;
		//alert(urlStr);
		
		var result = $.ajax({
			url: urlStr,
			type:"post",
		    async: false
		}).responseText;  
		$('#projectmaindiv').html(result);
		
	}
	
	//切换头部菜单	
	function tabss(lidom){ 
		var nstate=$('#nstate').val();
		$('#projectH').find('a').each(function(){
			if(this.id ==lidom.id){ 
				this.className="over"; 
				var _href=	$(this).attr('link'); 
	 			projectList(_href+"?pageNum=1&protype=9&nstate="+nstate); 
			}else{
				this.className="";
			}
		});
		$('#newprojectdiv').css('display','none');
		$('#addbugbtn').show();
}
	function delClass(){
		$('#topagestatistics').attr("class","");
		$('#tomebug').attr("class","");
		$('#bugmeto').attr("class","");
		$('#allProject').attr("class","");
	}
	
	
	function  projectAllProjectList(_href){
		var result = $.ajax({
			url: _href,
			type:"post",
		    async: false 
		}).responseText;  
		$('#projectmaindiv').html(result);
	}
//删除项目
function deleteproject(_projectId){
	$.dialog.confirm('您确定删除这个项目吗？',function(){
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
		var tabpid=<s:property value="tabPid"  />;
		var result = $.ajax({
			url: '<%=rootPath%>bug/addleft.action?protype=9&tabPid='+tabpid,
			data:{'taskorproject':'project'},
			type:"post",
			dataType: "json", 
		    async: false 
		}).responseText; 
		$('#leftMenu').html(result);
} 

 /** 首先进入，查询自己的负责的项目  */
$(function(){
	$('#projectmaindiv').hide();
	$('#addbugbtn').hide();
	allprojectLeft();//得到左边
	
}); 
function refrechProjectList(){
	var project_state=$('#projectstateselect').val(); 
	$('#projectH').find('a').each(function(){
		if(this.className=="over"){  
			var _href=	$(this).attr('link');
			_href=_href+"?isClosed="+project_state+"&protype=9"; 
		 	var pageForm=$('#pageForm');
			if(pageForm!=null){
				var pageString=$('#pageForm').serialize(); 
				_href=_href+"&"+pageString;
		 	} 
			projectList(_href);//刷新列表 
		 }
	}); 
}
/**
 * 添加bug
 */
function toAddBugpage(){
	var pid=$('#projectId').val();
	var url="<%=rootPath%>bug/toAddBugPage.action?projectId="+pid;
	window.open(url)
}

/**
 * 编辑项目
 */
function editorProject(objj){
	var projectId=$(objj).parent().find('input[type=hidden]').val();
	$.ajax({
		url: '<%=rootPath%>project/editorproject.action?projectId='+projectId+'&protype=9',
		data:{'taskorproject':'project'},
		type:"post",
	    async: false,
	    cache: false,
	    success:function(dat){
	    	$('#newprojectdiv').html(dat);
	    	$('#projectId').val(projectId);
	    	$('#projectmaindiv').show();
	    }
	});
	delClass();
	//$('#newprojectdiv').html(dat);
	
	//window.location.href="<%=rootPath%>project/editorproject.action?projectId="+projectId+"&protype=9";
	
}
/**
 * 退出项目
 */
function outofproject(objj){
	var pid=$(objj).find('input[type=hidden]').val();
	//alert('项目Id是'+pid);
	$.dialog.confirm("您确定要退出该项目?",function(bol){
		if(bol){
			$.ajax({
				url: '<%=rootPath%>project/outproject.action',
				data:{'projectId':pid},
				type:"post",
			    async: false,
			    cache: false,
			    success:function(){
			    	$.dialog.tips('您已退出该项目！',1,'32X32/succ.png',function(){ 
		    			
		    			window.location.href='<%=rootPath%>bug/tobugmanage.action';
		    		});
			    }
			});
		}
	});
}

</script> 
</body>
</html>

