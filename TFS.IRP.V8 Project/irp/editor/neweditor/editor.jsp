<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
	<title>快捷文章发表框</title>
<link rel="StyleSheet" href="<%=rootPath %>editor/neweditor/editor/base.css">
<script type="text/javascript" src="<%=rootPath %>editor/neweditor/editor/editor.js"></script>

<script type="text/javascript">
var guid = "1324481743" ;
var sState = "iframe";
var checkEdit;
function getHTML(){
	return $('editor_body_area').contentWindow.document.body.innerHTML.replace(window.location+"#", "#");	
}

function setHTML(_sHTML){
	return $('editor_body_area').contentWindow.document.body.innerHTML = _sHTML;	
}

function getText(){
//	et.delEditorRsave();
	return $('editor_body_area').contentWindow.document.body.innerText;	
}

function article_preview(){
	if (check_editor()){
		var editor_win = window.open('', "_blank", '');
			editor_win.document.open('text/html', 'replace');
			editor_win.opener = null 
			editor_win.document.writeln($('editor_body_textarea').value);
			editor_win.document.close();
		}
}
function check_editor(){
	var err_msg = '请先输入文章内容';
	if ($('editor_body_textarea').value.toLowerCase() =="<div></div>" ||$('editor_body_textarea').value ==""){ 
		alert(err_msg);
		return false;
	}
	else if($('editor_body_textarea').value.toLowerCase() =="<p>&nbsp;</p>" ||$('editor_body_textarea').value ==""){ 
		alert(err_msg);
		return false;
	}
	else
		return true;
}
</script>

</head>
<body id="editor"  style="overflow:visible"> 
<div id="editor_body" style="height:280px;width:100%;"></div> 
</body>
</html>
<script type="text/javascript">
// "EditerBox" 就是 textarea 的名子
var et;
//自动保存历史开关
function readCookie(name)
{
var cookieValue = "";
var search = name + "=";
if(document.cookie.length > 0)
{ 
offset = document.cookie.indexOf(search);
if (offset != -1)
{ 
 offset += search.length;
 end = document.cookie.indexOf(";", offset);
 if (end == -1) end = document.cookie.length;
 cookieValue = unescape(document.cookie.substring(offset, end))
}
}
return cookieValue;
}

function writeCookie(name, value, hours)
{
var expire = "";
if(hours != null)
{
expire = new Date((new Date()).getTime() + hours * 3600000);
expire = "; expires=" + expire.toGMTString();
}
document.cookie = name + "=" + escape(value) + expire + ";path=/";
}
function init() {
	writeCookie("EDiaryEditor_RSave", "true", 1); 
	if(sState == "iframe1"){
	alert(sState);
		EDiaryEditor.initialize("EDiaryEditor", "editor_body", true, "<div></div>");
		et = EDiaryEditor;
	}else{
		EDiaryEditor.initialize("EDiaryEditor", "editor_body", true, "");
		et = EDiaryEditor;
	}
		try{
		$('editor_body_area').onfocus = function(){ }
		$('editor_body_area').onblur = function(){ }
	}catch(e){}
}
    if(window.Event)
        	window.onload = init;
	else
        	setTimeout(init, 100); 
</script> 