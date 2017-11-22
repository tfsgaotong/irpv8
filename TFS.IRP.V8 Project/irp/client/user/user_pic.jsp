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
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/uploadify.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/jquery.Jcrop.css" />
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
</style>

<script type="text/javascript" src="<%=rootPath %>client/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/swfobject.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/swfobject.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/jquery.Jcrop.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/jQuery.UtrialAvatarCutter.js"></script>
<script type="text/javascript">
<!--
$(function() {
	var cutter = new jQuery.UtrialAvatarCutter();
	var left = ($(window).width() - 100)/2;
	var tempImg;
	$("#fileList").css("left", left);
	$("#picFile").uploadify({
		'auto': true,
		'multi' : false,
		'swf' : '<%=rootPath%>client/js/uploadify.swf',
		'uploader' : '<%=rootPath%>user/user_pic_upload.action;jsessionid=<%=session.getId() %>',
		'formData':{
            'userId':'1'
        },
		'queueID' : 'fileList',
		'fileObjName' : 'picFile',
		'buttonText' : '点击上传照片',
		'width' : "120",
		'height' : "40",
		'removeCompleted': true,
		'removeTimeout': 1,
		'queueSizeLimit': 1,      //允许同时上传文件数量
        'uploadLimit': 1,        //允许上传文件总数，指打开一次浏览器
		'fileSizeLimit' : "5MB",
		'fileTypeExts': '*.jpg;*.jpeg;*.gif;*.png',
        'fileTypeDesc': '图片文件',
		'onUploadSuccess' : function(file, data, response){
			setTimeout(function(){
				$("#imgInfo").css("display","none");
				$("#imgPic").css("display","block");
				$("#userimg").css("height","auto");
				var imgSrc = '<%=rootPath%>file/readfile.action?fileName='+data;
				tempImg = data;
				$("#userimg").attr("src", imgSrc);
				$("#preview").attr("src", imgSrc);
			} ,1000);
		},
		'onUploadError': function (file, errorCode, errorMsg, errorString) {    //错误提示 
            alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
        }
	});

	$("#userimg").bind("load", function() {
		cutter.init();
	});
   
	$("#submit").bind("click", function() {
		var x = $("#x").val();
		var y = $("#y").val();
		var w = $("#w").val();
		var h = $("#h").val();
		$.ajax({
			cache : false,
			type : 'post',
			url : '<%=rootPath%>/user/user_pic_modify.action',
			data : {
				x: x,
				y: y,
				width: w,
				height: h,
				fileName: tempImg
			},
			success : function(callback) {
				if(callback=='1'){
					$.dialog.tips('保存成功',1,'32X32/succ.png',function(){
						location.reload();
					});
				}else{
					$.dialog.tips('修改失败',1,'32X32/fail.png');
				}
			}
		});
	});

	$("#uploadFile").click(function() {
		$("#file").uploadifyUpload();
	});
	$("#cancelUpload").click(function() {
		$("#file").uploadifyClearQueue();
	});
	$(window).unload(function(){
		if(tempImg){
			$.ajax({
				async : false,
				cache : false,
				type : 'post',
				url : '<%=rootPath%>/user/temp_pic_del.action',
				data : {
					fileName: tempImg
				}
			});
		}
	});
});
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
       	 	<dt><em class="b"></em><a href="<%=rootPath %>user/user_pic.action" class="x">修改头像</a></dt>
       	  	<dt><em class="c"></em><a href="<%=rootPath %>user/user_tag.action">个人标签</a></dt>
            <dt><em class="d"></em><a href="<%=rootPath %>user/user_privacy.action">隐私设置</a></dt>
			</dt>
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
	<div class="gr"><h1>修改头像</h1></div>
	<table align="left">
		<tr>
			<td valign="top">
			<div id="imgInfo">
				<div>本地照片：选择一张本地图片作为头像</div>
				<div><input type="file" id="picFile" name="picFile" /></div>
				<div>支持jpg、jpeg、gif、png格式，文件小于5M</div>
			</div>
			<div id="imgPic" style="display: none;">
				<div style="width: 310px; border: #ccc 1px solid;">
					<div style="margin:5px;">
						<img id="userimg" width="300" />
					</div>
				</div>
				<div style="padding-top: 20px; text-align: center;">
					<a href="javascript:void(0);" id="submit" class="newabtngrn">保存图片</a>
					<a href="<%=rootPath %>user/user_pic.action" class="newabtngrn">取消</a>
				</div>
			</div>
			</td>
			<td width="20"><div id="fileList" style="width: 200px; height:51px; position:absolute; z-index: 999999"></div></td>
			<td width="20" style="border-left: #ccc 1px solid;"></td>
			<td>
				<div style="color: #f50; width: 240px; padding-bottom:20px;">您上传的头像会自动生160X160尺寸，请注意中小尺寸的头像是否清晰</div>
				<div style="width: 170px; height: 170px; border: #ccc 1px solid;">
				<div style="width: 160px; height: 160px; margin:5px; overflow: hidden;">
					<img id="preview" src="<s:property value="userPicUrl" />" />
				</div>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<input type="hidden" id="x" />
				<input type="hidden" id="y" />
				<input type="hidden" id="w" />
				<input type="hidden" id="h" />
			</td>
		</tr>
	</table>
</div>
<!--右侧内容结束-->
<!--尾部信息-->
<s:include value="../include/client_foot.jsp"></s:include>
<!--尾部信息end-->
</div>
</div>
</body>
</html>
