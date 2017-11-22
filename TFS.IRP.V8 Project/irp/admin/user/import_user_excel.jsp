<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>导入Excel</title>
</head>

<body>
<style type="text/css">
.ellipsis {
    table-layout: fixed;
}
.ellipsis td{
	word-wrap:normal;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
.ellipsis td img{
   cursor: pointer;
}
.main_bright label{
	cursor: pointer;
}
</style>
<link href="<%=rootPath %>admin/js/jquery.uploadify/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=rootPath %>admin/js/jquery.uploadify/swfobject.js"></script>
<script type="text/javascript" src="<%=rootPath %>admin/js/jquery.uploadify/jquery.uploadify.js"></script> 
<script type="text/javascript" src="<%=rootPath %>admin/js/json2.js"></script>
<script type="text/javascript">
//初始化
$(function(){
	$("#uploadify").uploadify({
        //开启调试
        'debug' : false,
        //是否自动上传
        'auto':true,
        //是否多文件上传
        'multi' : true,
        //超时时间
        'method': 'POST',
        'successTimeout':99999, 
        //附带值
        'formData':{},
        //flash
        'swf': "<%=rootPath%>admin/js/jquery.uploadify/uploadify.swf",
        //不执行默认的onSelect事件
        'overrideEvents' : ['onDialogClose'],
        //文件选择后的容器ID
        'queueID':'fileQueue',
        //服务器端脚本使用的文件对象的名称 $_FILES个['upload']
        'fileObjName':'myFile',
        //上传处理程序
        'uploader':'<%=rootPath%>site/upload.action;jsessionid=<%=session.getId()%>',
        //浏览按钮的宽度
        'width':'100',
        //浏览按钮的高度
        'height':'20', 
        //在浏览窗口底部的文件类型下拉菜单中显示的文本
        'fileTypeDesc':'支持的格式：', 
        //上传文件的大小限制
        'buttonText' : '选择文件',//浏览按钮
        //上传数量
        'queueSizeLimit' : 100,  
		'fileSizeLimit' : '102400K', 
		'fileTypeExts': '*.XLSX;*.XLS', 
        'removeCompleted': true,
        'removeTimeout':1, 
        'onSelectError':function(file, errorCode, errorMsg){
            switch(errorCode) {
                case -100:
                    alert("上传的文件数量已经超出系统限制的"+$('#uploadify').uploadify('settings','queueSizeLimit')+"个文件！");
                    break;
                case -110:
                    alert("文件 ["+file.name+"] 大小超出系统限制的"+$('#uploadify').uploadify('settings','fileSizeLimit')+"大小！");
                    break;
                case -120:
                    alert("文件 ["+file.name+"] 大小异常！");
                    break;
                case -130:
                    alert("文件 ["+file.name+"] 类型不正确！");
                    break;
            }
        }, 
        'onFallback':function(){
            $.messager.alert("提示信息","您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。","warning");
        }, 
        'onUploadSuccess':function(file, data, response){
        	var fileName = file.name;
        	$('#uploadInfo').append('<tr><td name="fileName" title="'+fileName+'" _upFileName="'+data+'">'+fileName+'</td><td width="20"><img border="0" title="删除" onclick="delRow(this)" src="<%=rootPath%>admin/images/icons/cancel.png" /></td><tr>');
        } 
    });
});

//删除行
function delRow(elmObj){
	$.messager.confirm('提示信息','您确定要删除吗？',function(r){
		if(r){  
	        var vTr= $(elmObj).parent().parent(); 
	        vTr.remove(); //移除这一行 
   	 	}
	});
}
</script>
<form id="uploadDoc">
<input type="hidden" id="cruser" name="document.cruser" value="" />
<input type="hidden" id="cruserid" name="document.cruserid" value="" />
<div id="fileQueue" style="position: absolute;"></div>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cad9ea">
	<tr>
		<td width="120" align="right" bgcolor="#f5fafe" class="main_bleft">用户状态：</td>
		<td bgcolor="#FFFFFF" class="main_bright">
			<input type="radio" checked="checked" name="userStatus" id="status_reg" value="<s:property value="@com.tfs.irp.user.entity.IrpUser@USER_STATUS_REG"/>" /><label for="status_reg">已开通</label>
			<input type="radio" name="userStatus" id="status_apply" value="<s:property value="@com.tfs.irp.user.entity.IrpUser@USER_STATUS_APPLY"/>" /><label for="status_apply">未开通</label>
			<input type="radio" name="userStatus" id="status_forbid" value="<s:property value="@com.tfs.irp.user.entity.IrpUser@USER_STATUS_FORBID"/>" /><label for="status_forbid">已停用</label>
		</td>
	</tr>
	<tr>
		<td width="120" align="center" valign="top" bgcolor="#f5fafe" class="main_bleft" style="padding-top: 10px;">
			<input style="position:absolute;" type="file" name="uploadify" id="uploadify" multiple="true"/>
		</td>
		<td bgcolor="#FFFFFF" class="main_bright"><table id="uploadInfo" class="ellipsis" width="100%">
		</table></td>
	</tr>
</table>
</form>
</body>
</html>