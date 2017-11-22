<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String rootPath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath() + "/";
%>
<!doctype html>
<html lang="en" class="no-js">
<head>
<meta charset="utf-8">
<title>企业社会化智力资源管理系统</title>
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
<link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath %>view/css/uploadify.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath %>view/css/jquery.Jcrop.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath %>view/css/easyui.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath %>view/css/icon.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath %>view/css/discuz.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.main-gr .right .combo input{
  border: 0px;
}
.uploadify-button {background-color:#5f9ddd;background-image:none;}
.uploadify:hover .uploadify-button {background-color:#114e88;background-image:none;}
</style>

<script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/swfobject.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.Jcrop.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jQuery.UtrialAvatarCutter.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/modernizr.custom.js"></script>
<script type="text/javascript" >
<!--
//添加个人标签
$(function() {
	var cutter = new jQuery.UtrialAvatarCutter();
	var left = ($(window).width() - 100)/2;
	var tempImg;
	$("#fileList").css("left", left);
	$("#picFile").uploadify({
		'auto': true,
		'multi' : false,
		'swf' : '<%=rootPath%>view/js/uploadify.swf',
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
<jsp:include page="../include/client_head.jsp" />
<section class="mainBox">
	<nav class="publicNav">
    </nav>
</section>
<section class="mainBox">
	<article class="location"><strong>个人设置</strong></article>
	<section class="layoutLeft">
        <nav class="sets">
        	<div class="folder">
            	<p><a href="<%=rootPath %>user/user_set.action" target="_self">基本资料</a></p>
            </div>
            <div class="folder">
            	<p><a href="<%=rootPath %>user/user_privacy.action" target="_self">隐私设置</a></p>
            </div>
            <div class="folder">
            	<p class="current"><a href="<%=rootPath %>user/user_pic.action" target="_self">设置头像</a></p>
            </div>
           <div class="folder">
            	<p><a href="<%=rootPath %>user/user_group.action" target="_self">个人分组</a></p>
            </div>
            <div class="folder">
            	<p><a href="<%=rootPath %>user/user_tag.action" target="_self">个人标签</a></p>
            </div>
            <div class="folder">
            	<p><a href="<%=rootPath %>user/user_binding.action" target="_self">账号绑定</a></p>
            </div>
        </nav>
  </section>
  <section class="setUp">
        <div class="pan title"><span>设置头像</span></div>
   		<div class="line area20"></div>
   		<div class="rightN">
        <div class="area20"></div>
        
        	<table align="left">
				<tr>
					<td valign="top">
					<div id="imgInfo">
						<div style="font-size:13px">本地照片：选择一张本地图片作为头像</div>
						<div style="font-size:13px;">支持jpg、jpeg、gif、png格式，文件小于5M</div>
						<div class="area20"></div>
						<div><input type="file" id="picFile" name="picFile" /></div>
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
						<div style="color: #f50; width: 240px; padding-bottom:20px;font-size:13px;">您上传的头像会自动生160X160尺寸，请注意中小尺寸的头像是否清晰</div>
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
        
  </section>
    
</section>


<jsp:include page="../include/client_foot.jsp" />
</body>
</html>
