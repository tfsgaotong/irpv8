<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<html lang="en" class="no-js">
<head>
	<meta charset="utf-8">
	<title>管理中心</title>
	<link rel="shortcut icon" href="<%=rootPath%>favicon.ico" type="image/x-icon"/>
	<link rel="bookmark" href="<%=rootPath%>favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/normalize.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/common1.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/oacss.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/common.css"/>
	<script type="text/javascript" src="<%=rootPath%>view/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/js/lhgdialog.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/js/modernizr.custom.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/js/jquery.cookie.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/js/easyui-lang-zh_CN.js"></script>
	<jsp:include page="../../view/include/client_skin.jsp" />
</head>
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

//切换管理菜单
function switchManager(_obj){
	var jqObj = $(_obj).parent();
	$('.layoutLeft').find('.current').removeClass('current');
	jqObj.addClass('current');
	findManagerConn();
}

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
	var jqObj = $('.layoutLeft').find('.current').find('a');
	var sHref = jqObj.attr('_href');
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
<body style="background: url();">
	<s:include value="../../view/include/client_head.jsp"></s:include>
	<section class="mainBox">
		<nav class="publicNav"></nav>
	</section>
	<section class="mainBox">
		<article class="location">
			<strong>管理中心</strong>
		</article>
		<section class="layoutLeft">
			<nav class="sets">
				<s:if test="irpUser.isMicroblogManager()">
					<div class="folder">
						<p id="microblogP">
							<a href="javascript:void(0)" onclick="switchManager(this)" _href="<s:url namespace="/microblog" action="microblog_manage" />">微知信息管理</a>
						</p>
					</div>
				</s:if>
				<s:if test="irpUser.isTopicManager()">
					<div class="folder">
						<p id="microtopicP">
							<a href="javascript:void(0)" onclick="switchManager(this)" _href="<s:url namespace="/microblog" action="microtopic_manage" />">微知专题管理</a>
						</p>
					</div>
				</s:if>
				<s:if test="irpUser.isDocumentManager()">
					<div class="folder">
						<p id="subjectP">
							<a href="javascript:void(0)" onclick="switchManager(this)" _href="<s:url namespace="/site" action="subject_manage" />">知识专题管理</a>
						</p>
					</div>
				</s:if>
				<s:if test="irpUser.isDocumentManager()">
					<div class="folder">
						<p id="doctagP">
							<a href="javascript:void(0)" onclick="switchManager(this)" _href="<s:url namespace="/tag" action="doctag_manage" />">知识标签管理</a>
						</p>
					</div>
				</s:if>
				<s:if test="irpUser.isDocumentManager()">
					<div class="folder">
						<p id="docreportP">
							<a href="javascript:void(0)" onclick="switchManager(this)" _href="<s:url namespace="/site" action="docreport_manage" />">知识举报管理</a>
						</p>
					</div>
				</s:if>
				<%-- <s:if test="irpUser.isDocumentManager()">
			<div class="folder">
				<p id="flowP" >
					<a href="javascript:void(0)" onclick="switchManager(this)" _href="<s:url namespace="/flow" action="flow_manage" />">知识审核管理</a>
				</p>
			</div>
			</s:if> --%>
				<div class="folder">
					<p id="flowP">
						<a href="javascript:void(0)" onclick="switchManager(this)" _href="<s:url namespace="/flow" action="flow_manage" />">知识审核管理</a>
					</p>
				</div>
				<s:if test="irpUser.isAsseroomManager()">
					<div class="folder">
						<p id="meeting">
							<a href="javascript:void(0)" onclick="switchManager(this)" _href="<s:url namespace="/asseroomapply" action="rootapply" />">会议室预约</a>
						</p>
					</div>
				</s:if>
				<div class="folder">
					<p id="meet">
						<a id="room" href="javascript:void(0)" onclick="switchManager(this)" _href="<s:url namespace="/asseroomapply" action="play" />">我的会议日程</a>
					</p>
				</div>
				<div class="folder">
					<p id="meet">
						<a id="covert" href="javascript:void(0)" onclick="switchManager(this)" _href="<s:url namespace="/covert" action="covertRecord" />">我的兑换记录</a>
					</p>
				</div>
				<s:if test="irpUser.isWorkApplyManager()">

					<div class="folder">
						<p id="leaveapply20">
							<a href="javascript:void(0)" onclick="switchManager(this)" _href="<s:url namespace="/leave" action="getLeaveList" > <s:param name="type" value="20"/></s:url>">加班审核管理</a>
						</p>
					</div>
				</s:if>
				<s:if test="irpUser.isLeaveApplyManager()">
					<div class="folder">
						<p id="leaveapply10">
							<a href="javascript:void(0)" onclick="switchManager(this)" _href="<s:url namespace="/leave" action="getLeaveList" ><s:param name="type" value="10"/></s:url>">请假审核管理</a>
						</p>
					</div>
				</s:if>
				<s:if test="irpUser.isTopManager()">
					<div class="folder">
						<p id="leaveapply10">
							<a href="javascript:void(0)" onclick="switchManager(this)" _href="<s:url namespace="/leave" action="getJingliLeaveList" ><s:param name="type" value="10"/></s:url>">总经理审核管理</a>
						</p>
					</div>
				</s:if>
				<s:if test="irpUser.isDocumentManager()">
		            <div class="folder">
		                <p id="meet" >
		                    <a id="covert" href="javascript:void(0)" onclick="switchManager(this)" _href="<s:url namespace="/question" action="allQandaManager" />">问答记录管理</a>
		                </p>
		            </div>
	            </s:if>
	            <div class="folder">
	                <p id="questionAndAnswer" >
	                    <a id="question" href="javascript:void(0)" onclick="switchManager(this)" _href="<s:url namespace="/question" action="qandaManager" />">个人问答记录</a>
	                </p>
	            </div>
	            <s:if test="irpUser.isExpert()">
		            <div class="folder">
		                <p id="expertask" >
		                    <a id="expertask" href="javascript:void(0)" onclick="switchManager(this)" _href="<s:url namespace="/question" action="noanswerManager" />">待解决问答管理</a>
		                </p>
		            </div>         
	            </s:if>
				<s:if test="irpUser.isTermswordManager()">
					<div class="folder">
						<p id="meet">
							<a id="covert" href="javascript:void(0)" onclick="switchManager(this)" _href="<s:url namespace="/term" action="toCheckAndIllegalTermList" />">词条审核管理</a>
						</p>
					</div>
				</s:if>
				<div class="folder">
                    <p id="meet">
                        <a id="covert" href="javascript:void(0)" onclick="switchManager(this)" _href="<s:url namespace="/term" action="toPersonalTermList" />">个人词条管理</a>
                    </p>
                </div>
			</nav>
		</section>
		<div id="manager_conn"></div>
	</section>
	<jsp:include page="../../view/include/client_foot.jsp" />
</body>
</html>
