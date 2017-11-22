<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
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
<link href="<%=rootPath %>view/css/easyui.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath %>view/css/icon.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath %>view/css/discuz.css" rel="stylesheet" type="text/css" />
<style type="text/css">
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
.index_title span {font-size:13px;}
</style>

<script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jsAddress.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/modernizr.custom.js"></script>
<script type="text/javascript" >
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
				$.dialog.tips('删除成功',1,'32X32/succ.png',function(){
					location.reload(true);
				});
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
				 htmlli+="<li><input type='checkbox' value='"+tag+"' style='display:none;'/> <a href='javascript:void(0);' onclick='searchInfo1(\""+tag+"\")'>"+tag+"</a><span></span></li>";
			 });
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
function searchInfo1(searchInfo){  
	searchtype = 5;  
	if(searchInfo.length>38){
		searchInfo = searchInfo.substring(0,37);	
	}
	var eacapeInfo = encodeURI(searchInfo);
    window.open('<%=rootPath%>solr/searchcontentofsolr.action?searchInfo='+eacapeInfo+'&searchtype='+searchtype); 
}
//-->
</script>
</head>
<style>
	 #tagstip{
     	left:157px;
     	top:265px;
     }
</style>
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
            	<p><a href="<%=rootPath %>user/user_pic.action" target="_self">设置头像</a></p>
            </div>
           <div class="folder">
            	<p><a href="<%=rootPath %>user/user_group.action" target="_self">个人分组</a></p>
            </div>
            <div class="folder">
            	<p class="current"><a href="<%=rootPath %>user/user_tag.action" target="_self">个人标签</a></p>
            </div>
            <div class="folder">
            	<p><a href="<%=rootPath %>user/user_binding.action" target="_self">账号绑定</a></p>
            </div>
        </nav>
  </section>
  <section class="setUp">
        <div class="pan title"><span>个人标签</span></div>
   		<div class="line area20"></div>
   		<div class="rightN">
   		 <div class="pan title" style="padding-left:0px;"><em>添加描述自己职业、兴趣爱好等方面的词语，多个标签之间请用逗号、分号、空格分开。</em></div>
        <form id="tagForm" method="post" onsubmit="return false;">
            <table>
                <tr>
                    <td class="inputText"><input name="tagName" value="" style="height: 24px; width: 240px;" class="easyui-validatebox" required="true" validType="maxLength[50]" missingMessage="请填写个人标签" /></td><td width="3"></td><td width="20"><font color="red">*</font></td><td class="sub"><input type="button" onclick="addTag()" value="添加标签"/>&nbsp;&nbsp;&nbsp;&nbsp;<a id="tuijian" onclick="findMytag()" href="javascript:void(0);">推荐</a>&nbsp;&nbsp;&nbsp;&nbsp;<a id="tiqu" onclick="tiquMytag()" href="javascript:void(0);">从标签中提取</a></td>
                </tr>
            </table>
            </form>
       
        <div id="tagstip" style="position:absolute;background-color:#5f9ddd;">
        	<em></em>
        </div>
        <div class="area20"></div>
        <div class="index_title"><span>我关注的标签</span>
        <h3 style="margin-bottom:10px;"></h3>
        </div>
        <div class="pan">
            <ul class="list8">
            <s:iterator value="irpTags">
                <li><a href="javascript:void(0)" onclick="searchInfo1('<s:property value="tagname" />')"><s:property value="tagname" /></a><aside onclick="deleteTag(<s:property value="tagid" />)"><b>&nbsp;X</b></aside></li>
            </s:iterator>
            </ul>
            <div class="clear"></div>
        </div>  
        <div class="area20"></div>
        <div class="index_title"><span>我的标签</span>
        <h3 style="margin-bottom:10px;"></h3>
        </div>
        <div class="pan">
            <ul id="mytagul" class="list8">
            </ul>
            <a style="color:#fff;padding:4px;background-color:#5f9ddd;" id="tomytag" class="newabtngrn" onclick="addmyguanzhutag()" href="javascript:void(0);">确&nbsp;&nbsp;&nbsp;&nbsp;定</a>
            <div class="clear"></div>
        </div>
        
        		
        </div>
        
  </section>
    
</section>

<jsp:include page="../include/client_foot.jsp" />
</body>
</html>
