<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.tfs.irp.util.LoginUtil,com.tfs.irp.user.entity.IrpUser"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="<%=rootPath%>/client/css/oacss.css" rel="stylesheet"type="text/css" />
<link href="<%=rootPath%>/client/css/easyui.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath%>/client/css/icon.css" rel="stylesheet" type="text/css"  />
<link rel="stylesheet" href="<%=rootPath%>client/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css" />
<jsp:include page="../include/client_skin.jsp" />
<link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css"/>
<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css"/>
<link href="<%=rootPath%>client/css/examlist.css" rel="stylesheet" type="text/css"/>
<style type="text/css">

</style>
<script type="text/javascript" src="<%=rootPath%>/client/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/client/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript">
$(function(){
	//选择头部标签
	selected('sysexam');
});
/**
* 改变状态
*/
function changeY(_this){
	if(_this.id=="nocanexam"){
		window.location.href="<%=rootPath%>exam/exammenu.action?pagenumpaper=1";
	}else if(_this.id=="yescanexam"){
		window.location.href="<%=rootPath%>exam/alreadyexammenu.action?pagenumpaper=1";		
	}	
}
/**
* 前台考试分页
*/
function pageEMenu(_pages){
	window.location.href="<%=rootPath%>exam/alreadyexammenu.action?exammenunum="+_pages;
}
/**
*查看详情
*/
function searchDetail(_examid,_testpaperid,_recordid){
	window.open("<%=rootPath%>exam/linksinglequesalready.action?paper="+_testpaperid+"&exam="+_examid+"&recordid="+_recordid);
}
</script>
<style type="text/css">
.over{
border-bottom: 3px solid #448CC8;
}
</style>
<title>考试管理</title>
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
<!-- 

<jsp:include page="../include/client_head.jsp" />
 -->
 <jsp:include page="../../view/include/client_head.jsp" />
		   <section class="mainBox">
			<nav class="privateNav">
			</nav>
			</section>
</head>
<body>
<div class="main" style="background-image: url('');width:1200px;margin-top:20px;">
	<div style="" class="leftmenu" style="width:280px;">
			<ul>
				<li style="font-size:135%;font-weight:bold" onclick="changeY(this)" id="nocanexam">
					<span><img src="<%=rootPath %>client/images/examicon.png" style="width:16px;height:16px;margin-right:5px;"></span><a>待参加的考试</a>
				</li>
				<li style="font-size:135%;font-weight:bold;background-color:#ADE5EF" onclick="changeY(this)" id="yescanexam">
					<span><img src="<%=rootPath %>client/images/examicon.png" style="width:16px;height:16px;margin-right:5px;"></span><a>参加过的考试</a>
				</li>
			</ul>
	</div>
	<div class="rightcontent">
		<ul>
		<s:if test="examrecordlist.size==0"> 
				<div style="height:360px;font-size:20px;text-align:center;color:#ccc;line-height:360px;">暂无考试记录</div>
		</s:if>
		<s:else>
		<s:iterator value="examrecordlist" >
			<li>
				<div>
					<div class="examimg" style="background:url('<%=rootPath %>/client/images/paper.jpg') no-repeat;background-size:100% 100%;font-size:30px;">
						<p style="height:50px;line-height:100px;text-align:center;"><s:property value="examgrade" /></p>
						<p style="font-size:20px;height:70px;line-height:70px;text-align:center;"><s:if test="examstatus==10">通过</s:if><s:elseif test="examstatus==20">未通过</s:elseif></p>
					</div>
					<div class="examinfo" style="">
						<p><span>考试名称:</span><span><s:property value="getIrpExamByExamId(examid).examname" /></span></p>
						<p><span>开始时间：</span><span><s:date name="getIrpExamByExamId(examid).begintime" format="yyyy-MM-dd HH:mm" /></span></p>
						<p><span>结束时间：</span><span><s:date name="getIrpExamByExamId(examid).endtime" format="yyyy-MM-dd HH:mm" /></span></p>
						<span>考试分数:</span><span><s:property value="examgrade" /></span><span>分</span>
						<span>考试结果:</span><span><s:if test="examstatus==10">通过</s:if><s:elseif test="examstatus==20">未通过</s:elseif></span>
					</div>
					
				</div>
				<div class="enterexam" style="width:100px;" onclick="searchDetail(<s:property value="examid" />,<s:property value="getIrpExamByExamId(examid).examcontent" />,<s:property value="recordid" />)" >
						查看详情
				</div>
				<div style="clear: both">
					
				</div>
			</li>
			</s:iterator>
			</s:else>
		</ul>
		<div>
			<div class="left">
				<div class="fyh">
					<ul  style="width:300px;">
					<s:property value="pagemenuhtml" escapeHtml="false" />
					</ul>
				</div>
			</div>	
		</div>
	</div>
	<jsp:include page="../include/client_foot.jsp" />
</div>
</body>
</html>