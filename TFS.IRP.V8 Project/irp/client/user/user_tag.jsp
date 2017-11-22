<%@page import="com.tfs.irp.util.LoginUtil"%>
<%@page import="com.tfs.irp.user.entity.IrpUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>企业社会化智力资源管理系统</title>
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/oacss.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/icon.css" />
<jsp:include page="../include/client_skin.jsp" />

<style type="text/css">
body{
	behavior:url(<%=rootPath %>client/js/hover.htc);
}
.STYLE1 {
	color: #0000FF;
	font-weight: bold;
	font-size:18px;
}
.STYLE1 a:hover{
	color: #0000FF;
	font-weight: bold;
	font-size:18px;
}
.main-gr .right .combo input{
  border: 0px;
}
.tagcss{
	border: 1px solid #E9E9E9;
	width: 250px;
	margin-top: -100px;
	margin-left: 470px;
	padding-left: 4px;
}
</style>

<script type="text/javascript" src="<%=rootPath %>client/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
<!--
//添加个人标签
function addTag(){
	$("#tagForm").form('submit',{
		url : "<%=rootPath %>user/add_usertag.action",
		success:function(data){
			if(data=="1"){
				$.dialog.tips('添加成功',1,'32X32/succ.png',function(){
					location.reload(true);
				});
			}else{
				$.dialog.tips('添加失败',1,'32X32/fail.png');
			}
		}
	});
}

//删除教育信息
function deleteTag(_nTagId){
	$.ajax({
		cache : false,
		type : 'get',
		url : '<%=rootPath%>/user/delete_usertag.action',
		data : {
			tagId: _nTagId
		},
		success : function(callback) {
			if(callback=='1'){
				location.reload(true);
				$.dialog.tips('删除成功',1,'32X32/succ.png');
			}else{
				$.dialog.tips('删除失败',1,'32X32/fail.png');
			}
		}
	});
}

$(function(){
	/* 扩展验证  */
	$.extend($.fn.validatebox.defaults.rules, {
		maxLength : {
			validator: function(value, param){   
	            return value.length < param[0];   
	        },
	        message: '输入内容长度不能超过{0}'  
		}
	});
	findkeywordtag();
	 $("#tomytag").hide();
});
function findkeywordtag(){
	$.ajax({
		 type:'post',
		 dataType: "json",
		 url:'<%=rootPath %>menu/find_mykeywordstag.action', 
		 async: false,
		 cache: false,
		 success:function(data){
			 var htmlli="";
			 $.each(data,function(i,tag){
				 htmlli+="<li><input type='checkbox' value='"+tag+"' style='display:none;'/> "+tag+"</li>"
			 })
			 $("#mytagul").html(htmlli);
			 $("#tomytag").show();
		 }
	 });
	
}

//从标签中提取
function tiquMytag(){
	if($.trim($("#tiqu").text())=="从标签中提取"){
	 $("#mytagul li").find("input:checkbox").show();
	 $("#tomytag").show();
	 $("#tiqu").text("关闭");
	}else{
		 $("#tiqu").text("从标签中提取");
		 $("#mytagul li").find("input:checkbox").hide();
		 $("#tomytag").hide();
	}
	
}
//推荐
function findMytag(){
	if($.trim($("#tuijian").text())=="推荐"){
		var str=$.ajax({
			 type:'post',
			 dataType: "json",
			 url:'<%=rootPath %>menu/find_mykeywords.action', 
			 async: false,
			 cache: false
		 }).responseText;
	 $("#tagstip").addClass("tagcss");
	 $("#tagstip").html(str);
	 $("#tuijian").text("取消");
	 $('#tagstip').draggable({ }); 
	}else{
		 $("#tagstip").removeClass("tagcss");
		 $("#tagstip").html("");
		 $("#tuijian").text("推荐");
	}
	
}

function addtoText(_value){
	var tag= $.trim($(_value).attr("title"));
	var keytext=$.trim($("input[name='tagName']").val());
	if(keytext==null||keytext.length==0){
		$("input[name='tagName']").val(tag);
	}else{
		$("input[name='tagName']").val(keytext+","+tag);
	}
}
function addmyguanzhutag(){
	var keytext=$.trim($("input[name='tagName']").val());
	var choseck="";
	var cktag=$("#mytagul li").find("input:checked");
	for(var i=0;i<cktag.length;i++){
		choseck=choseck+$("#mytagul li").children("input:checked:eq("+i+")").val()+",";
	}
	if(keytext==null||keytext.length==0){
		$("input[name='tagName']").val(choseck);
	}else{
		$("input[name='tagName']").val(keytext+","+choseck);
	}
	addTag();
}
var count_index=1;
function toMoretag(_value){
	 if(count_index==1){
		 $("#tagstip").find("li:gt(9)").show();
		 $(_value).text("取消");
		 count_index=2;
	 }
	 else{
		 $("#tagstip").removeClass("tagcss");
		 $("#tagstip").html("");
		 $("#tuijian").text("推荐");
		 $(_value).text("更多");
		 count_index=1;
	 }
}
function hidDiv(){
	 $("#tagstip").html("");
}
//-->
</script>
</head>

<body>
<div class="bg01">
<!--头部菜单-->
<s:include value="../include/client_head.jsp"></s:include>
<!--头部菜单end-->
<div class="main-gr">
<!--左侧内容-->
<div class="left">
	<div class="leftmenu">
    	<h1>账号设置</h1>
        <dl>
        	<dt><em class="a"></em><a href="<%=rootPath %>user/user_set.action">个人资料</a></dt>
            	<dd><a href="<%=rootPath %>user/user_set.action">基本信息</a>
            	<a href="<%=rootPath %>user/user_edu.action">教育信息</a>
            	<a href="<%=rootPath %>user/user_career.action">职业信息</a></dd>
       	 	<dt><em class="b"></em><a href="<%=rootPath %>user/user_pic.action">修改头像</a></dt>
       	  	<dt><em class="c"></em><a href="<%=rootPath %>user/user_tag.action" class="x">个人标签</a></dt>
            <dt><em class="d"></em><a href="<%=rootPath %>user/user_privacy.action">隐私设置</a></dt>
		
			<dt><em class="g"></em><a href="<%=rootPath %>user/user_group.action">个人分组</a></dt>
			<dt><em class="i"></em><a href="<%=rootPath %>site/user_subject.action">个人专题</a></dt>
			<dt><em class="h"></em><a href="<%=rootPath%>site/personallchanneltoshow.action?personid=<s:property value="@com.tfs.irp.util.LoginUtil@getLoginUserId()" />">个人分类</a></dt>
        	<dt><em class="e"></em><a href="<%=rootPath %>user/user_binding.action">账号绑定</a></dt>
        </dl>
    </div>
</div>
<!--左侧内容结束-->
<!--右侧内容-->
<div class="right">
	<div class="gr"><h1>个人标签</h1></div>
	<div style="margin-top: 5px;color: #B8B7B7;">添加描述自己职业、兴趣爱好等方面的词语，多个标签之间请用逗号、分号、空格分开。</div>
	<div style="padding: 10px 20px;"><form id="tagForm" method="post" onsubmit="return false;">
		<input name="tagName" value="" style="height: 24px; width: 240px;" class="easyui-validatebox" required="true" validType="maxLength[50]" missingMessage="请填写个人标签" />&nbsp;<font color="red">*</font><a href="javascript:void(0);" onclick="addTag()" class="zhuanz1">添加标签</a>
		&nbsp;&nbsp;&nbsp;&nbsp;<a id="tuijian" onclick="findMytag()" href="javascript:void(0);">推荐</a>
		&nbsp;&nbsp;&nbsp;&nbsp;<a id="tiqu" onclick="tiquMytag()" href="javascript:void(0);">从标签中提取</a>
		</form>
	</div>
	<div id="tagstip" style="position:fixed;background-color:#FFFFFF;"></div>
	<div style="border-top:1px solid #E9E9E9;">
		<div style="font-size: 14px;font-weight: bold;">我关注的标签</div>
		<div class="tagList">
		<ul>
			<s:iterator value="irpTags">
				<li>
					<span class="tagName"><s:property value="tagname" /></span>
					<a href="javascript:void(0)" class="tagDelete" onclick="deleteTag(<s:property value="tagid" />)" title="删除标签"></a>
				</li>
			</s:iterator>
		</ul>
		</div>
	</div>
	<div style="border-top:1px solid #E9E9E9;">
		<div style="font-size: 14px;font-weight: bold;">我的标签</div>
		<div class="tagList">
			<ul id="mytagul"></ul>
			<input type="button" value="确定" id="tomytag" onclick="addmyguanzhutag()"/>
		</div>
	</div>
</div>
<!--右侧内容结束-->
<!--尾部信息-->
<s:include value="../include/client_foot.jsp"></s:include>
<!--尾部信息end-->
</div>
</div>
</body>
</html>
