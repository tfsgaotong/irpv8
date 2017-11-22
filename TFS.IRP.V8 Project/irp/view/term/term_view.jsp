<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.tfs.irp.util.LoginUtil"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- 百科词条详情页 -->
<html>
<head>
	<title>百科词条</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
	<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/icon.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/oacss.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/asx.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/normalize.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/ztree/css/zTreeStyle/zTreeStyle.css"/>
	<script type="text/javascript" src="<%=rootPath%>view/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/js/lhgdialog.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/js/swfobject.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/js/jquery.uploadify.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/ztree/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/ztree/js/jquery.ztree.excheck-3.5.js"></script>
	<jsp:include page="../../view/include/client_skin.jsp" />
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
	var qidval = '<s:property value="qclassifyid"/>';
	$(function() {
		//选择头部标签
		//selected('wordterm');
		chTWOrds();
		initZTree();
		//获取数据
		if (qidval == '') {
			qidval = null;
		}
		getWordList(1, qidval);
	});

	function initZTree() {
		$.fn.zTree
				.init(
						$("#categoryTree"),
						{
							view : {
								showIcon : true
							},
							data : {
								simpleData : {
									enable : !0,
									idKey : "id",
									pIdKey : "pId"
								}
							},
							async : {
								enable : true,
								url : "<%=rootPath%>category/getAllCategoryofterm.action?showExpertList=3" 
		},
		callback: {
			onClick: function (event, treeId, treeNode, clickFlag){
				if(treeNode){
					 $('#categoryId').val(treeNode.id);
						$('#termwords').css({'border-bottom-style':'solid'});
						$('#audittermg').css({'border-bottom-style':'none'});
						$('#alltermg').css({'border-bottom-style':'none'});
						$('#minetwords').css({'border-bottom-style':'none'});
						$('#illtermg').css({'border-bottom-style':'none'});
					 getWordList(1,treeNode.id);
				 }
			}
		} 
	});
}

//鼠标移动到上面
function choiceTerV(_idstr,_tid){
	var chv = "#"+_idstr+_tid;
	$(chv).css({'background-color':'#FCFCFC'});
}
//鼠标离开
function outTerV(_idstr,_tid){
	var chv = "#"+_idstr+_tid;
	$(chv).css({'background-color':'#FFFFFF'});
}

/**
 * 获取词条列表数据
 */
function getWordList(_pnum,_cid){
	
	var swords = $('#searchtermname').val();
	$.ajax({
		type:'post',
		url:'<%=rootPath%>term/termlist.action',
		data:{
			pagenum:_pnum,
			sword:swords,
			qclassifyid:_cid
		},
		cache:false,
		success:function(content){
			
			$('#termcontent').html(content);
		}
		
	});
}

/**
 * 分页
 */
function pageterm(_pnum){
	$('#termcontent').html("");
	if(qidval==''){
		qidval = null;
	}
	getWordList(_pnum,qidval);
	
}
/**
 * 查询词条关键字
 */
function searchTName(){
	$('#termcontent').html("");
	var swords = $('#searchtermname').val();
	<%if(LoginUtil.getLoginUser().isTermswordManager()){%>
	chTWOrds();
	<%}else{%>
	if(qidval==''){
		qidval = null;
	}
	getWordList(1,qidval);
	<%}%>
}
/**
 * 链接到创建词条页面
 */
function linkCreateTwords(){
	window.open('<%=rootPath%>term/createtermwords.action');
	window.close();
	
}
/**
 * 链接到版本选择
 */
function linkVerContent(_termid){
	//浏览
	$.get("<%=rootPath%>term/updatebrowsecount.action",{termid:_termid});
	window.open('<%=rootPath%>term/linkversion.action?termid='+_termid);
}
/**
 *已审核
 */
function allTerm(){
	$('#alltermg').css({'border-bottom-style':'solid'});
	$('#audittermg').css({'border-bottom-style':'none'});
	$('#termwords').css({'border-bottom-style':'none'});
	$('#minetwords').css({'border-bottom-style':'none'});
	$('#illtermg').css({'border-bottom-style':'none'});
	findauditlist(1,2);
}
/**
 * 待审核标签
 */
function auditTerm(){
	$('#alltermg').css({'border-bottom-style':'none'});
	$('#audittermg').css({'border-bottom-style':'solid'});
	$('#termwords').css({'border-bottom-style':'none'});
	$('#minetwords').css({'border-bottom-style':'none'});
	$('#illtermg').css({'border-bottom-style':'none'});
	findauditlist(1,1);
}
/**
 * 词条标签
 */
function chTWOrds(){
	$('#termwords').css({'border-bottom-style':'solid'});
	$('#audittermg').css({'border-bottom-style':'none'});
	$('#alltermg').css({'border-bottom-style':'none'});
	$('#minetwords').css({'border-bottom-style':'none'});
	$('#illtermg').css({'border-bottom-style':'none'});
	getWordList(1,null);
}
/**
 * 个人词条
 */
function mineTWords(){
	$('#minetwords').css({'border-bottom-style':'solid'});
	$('#alltermg').css({'border-bottom-style':'none'});
	$('#audittermg').css({'border-bottom-style':'none'});
	$('#termwords').css({'border-bottom-style':'none'});
	$('#illtermg').css({'border-bottom-style':'none'});
	pageTermPersonal(1);
}
/**
* 非法词条	
*/
function illTerm(){
	$('#illtermg').css({'border-bottom-style':'solid'});
	$('#minetwords').css({'border-bottom-style':'none	'});
	$('#alltermg').css({'border-bottom-style':'none'});
	$('#audittermg').css({'border-bottom-style':'none'});
	$('#termwords').css({'border-bottom-style':'none'});
	illTermList(1);
}
//个人词条
function findPersonalList(_pags){
	var swords = $('#searchtermname').val();
	$.ajax({
		type:'post',
		url:'<%=rootPath%>term/personaltwords.action',
		data:{
			auditnopsize:_pags,
			auditnoword:swords
		},
		async:false,
		cache:false,
		success:function(content){
			$('#termcontent').html(content);
		}
	});
}

function findauditlist(_pags,_status){
	var swords = $('#searchtermname').val();
	$.ajax({
		type:'post',
		url:'<%=rootPath%>term/noaudittlist.action',
		data:{
			auditstatus:_status,
			auditnopsize:_pags,
			auditnoword:swords
		},
		async:false,
		cache:false,
		success:function(content){
			$('#termcontent').html(content);
		}
	});
}
//非法词条
function illTermList(_pags){
var swords = $('#searchtermname').val();
	$.ajax({
		type:'post',
		url:'<%=rootPath%>term/illtermlist.action',
		data:{
			auditnopsize:_pags,
			auditnoword:swords
		},
		async:false,
		cache:false,
		success:function(content){
			$('#termcontent').html(content);
		}
	});
}





/**
 * 分页
 */
function pagetermaudit(_pnum){
	$('#termcontent').html("");
	findauditlist(_pnum,1);
}
/**
 * 分页
 */
function pagetermnoeaudit(_pnum){
	$('#termcontent').html("");
	findauditlist(_pnum,2);
}
/**
 * 个人分页
 */
function pageTermPersonal(_pnum){
	$('#termcontent').html("");
	findPersonalList(1);
}

/**
 * 审查版本内容
 */
function linkMGVContent(_tid){
	window.open('<%=rootPath%>term/linkversionmgr.action?termid='+_tid);
}
function linkMGVContentPersonal(_tid){
	
	window.open('<%=rootPath%>term/linkversionpersonal.action?termid='+_tid);
}

</script>
<body style="background: url()">
 <jsp:include page="../../view/include/client_head.jsp" />
   <section class="mainBox">
	<nav class="privateNav">
	</nav>
	</section>
<div class="main">
	<div class="left">

	
	<!-- 1 -->
	<div style="margin: 5% 5% 5% 5%;">
	<div style="float: left;">
		<lable class="linkbh14">词条名称 :</lable>
		<input id="searchtermname" type="text" class="wtgy" placeholder="请输入词条名称...">
	</div>
	<div style="float: left;margin: 0 0 0 20px;">
		<a class="btn_ccw" onclick="searchTName()" href="javascript:void(0)">
			查询
		</a>
	</div>
	<div style="float: left;margin: 0 0 0 10px;">
		<a class="btn_ccw" onclick="linkCreateTwords()" href="javascript:void(0)">
			创建词条
		</a>
	</div>
	
	</div>
	<!-- 1.5 -->
	
	<br/>
	<div style="width: 100%;font-size: 18px;margin: 20px 0 -100px 0;">
		
		<a class="linkb14" href="javascript:void(0)"  style="font-size: 15px;border-bottom: 1px solid #D0D0D0;padding: 5px 5px 5px 5px;" onclick="chTWOrds()" id="termwords">词条</a>
		<%if(LoginUtil.getLoginUser().isTermswordManager()){ %>
		<a class="linkb14" href="javascript:void(0)"  style="font-size: 15px;border-bottom: 1px none #D0D0D0;margin-left:15px;padding: 5px 5px 5px 5px;" onclick="allTerm()" id="alltermg">已审核</a>
		<a class="linkb14" href="javascript:void(0)"  style="font-size: 15px;border-bottom: 1px none #D0D0D0;margin-left:15px;padding: 5px 5px 5px 5px;" onclick="auditTerm()" id="audittermg">待审核</a>
		<a class="linkb14" href="javascript:void(0)"  style="font-size: 15px;border-bottom: 1px none #D0D0D0;margin-left:15px;padding: 5px 5px 5px 5px;" onclick="illTerm()" id="illtermg">非法词条</a>
		<%} %>
		<a class="linkb14" href="javascript:void(0)"  style="font-size: 15px;border-bottom: 1px none #D0D0D0;padding: 5px 5px 5px 5px;" onclick="mineTWords()" id="minetwords">我的草稿</a>
	</div>
	
	
	<!-- 2 -->

	<div id="termcontent">
		
	</div>
	
	</div>

	<div class="right">
		<div class="duo">
			<%--百科分类--%>
			<dl id="termclassify" style="margin-top:10px;">
				<span class="linkbh14" style="font-weight:bold;cursor:pointer;">词条分类</span>
				<a onclick="javascript:window.location.reload()" style="font-size:12px;margin-left: 15px;" href="javascript:void(0)">刷新</a>
				<ul id="categoryTree" class="ztree" style="background-color: white;height: 100%;border: none;overflow-y:auto;">
				
				
				</ul>
			</dl> 
			
	    </div>
	</div>
	
</div>
	<jsp:include page="../../view/include/client_foot.jsp" />
</body>
</html>