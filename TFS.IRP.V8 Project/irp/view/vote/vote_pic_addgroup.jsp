<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>   
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
.inputvote{
height:30px;
width: 300px;
}
input,textarea,select{
border: 1px solid #CCCCCC;
}

#firstul li,#secondul li{
padding:0px 0px 2px 2px;
}
#firstul li ul,#secondul li ul{
padding-left: 60px;
margin-top: -20px;
}
.fileicon{
position:relative;
font-size:18px;
margin-top: -60px;
margin-left: 280px;
display: block;
width: 20px;
height: 50px;
}
#firstul,#secondul{
margin-left: 40px;
}
#optionul{
display:block;
width:300px;
margin-top: 10px;
}
#optionul li {
display: block;
width:300px;
height:100px;
}
#optionul li img{
position:relative;
margin-left: 230px;
margin-top: -46px;
padding: 0px;
}
#optionul li a{
margin: 0px;
padding: 0px;
}
.textinput{
width:210px;
height:30px;
margin-left: 0px;
}
</style>
</head>
<body>

<script type="text/javascript">
var htmlquestion,htmloption,htmlcheckli;
var maxId=3;
$(function(){
	htmlquestion=$("#questionvote").html();
	htmloption=$("#opteionvote").html();
	htmlcheckli=$("#checkli").html();
	$("#lesscheck").hide();
	
	loadimg($("#voteimg1"));
	loadimg($("#voteimg2"));
	
});
//添加说明
function addDesc(){
	var $oc=$("#adddesc a").text();
	if($.trim($oc)=="关闭说明"){
		$("#adddesc a").text("添加说明");
		$("#description").val("");
		$("#titledesc").hide();
	}else{
		$("#adddesc a").text("关闭说明");
		$("#titledesc").show();
	}
}
//再加一项
function addoption(){
	//获取li
	var index=$("#optionul li").length;
	var newDom = $("#optionul").append("<li><label>&nbsp;"+(index+1)+".&nbsp;&nbsp;&nbsp;</label><input name=\"option\" type=\"text\" class=\"textinput\"/><br/>URL<input name=\"urltext\" type=\"text\" class=\"textinput\"/ style=\"margin-top: 3px;\"><div style=\"margin-top: -20px;\"><img style=\"margin-left: 250px;\" name=\"\" width=\"50px;\" height=\"60px\" src=\"<%=rootPath%>view/images/uploadicon.jpg\"/></div><div class=\"fileicon\"><input id=\"voteimg"+(maxId++)+"\" style=\"display: none;\" type=\"file\" name=\"\" /></div></li>");
	loadimg(newDom.find('li').last().find('input:file'));
	$("#optionul li div a").remove();
	if(newDom.find('li').length>2){
		$.each(newDom.find('li'),function(i,n){
			$(n).find('.fileicon').append("<a onclick=\"deleteoption(this)\" ><img style=\"margin-left: -310px;margin-top: 0px;\"  title=\"删除选项\" style=\"margin-left: 0px;margin-top: 4px;\" width=\"20px;\" height=\"20px\" src=\"<%=rootPath%>view/images/deleteicon.jpg\" /></a>");
		});
	}
}

//删除选项
function deleteoption(value){
	//删除
	$(value).parent().parent().remove();
	//判断
	var index=$("#optionul li").length;
	if(index<=2){
		$("#optionul li div a").remove();
	}
	
	//重排序号
	$.each($("#optionul li label"),function(i,n){
		$(n).html(i+1+".&nbsp;&nbsp;&nbsp;");
	});
}

function checkoneormore(_value){
	if($(_value).val()==2){
		if($("#optionul li").length<3){
			addoption();
		}
		$("#lesscheck").show();
		$("#morecheck").find("input").val($("#optionul li").length);
		$("#morecheck").show();
	}else{
		$("#lesscheck").hide();
		$("#morecheck").hide();
	}
}

function checkchose(_value){
	var ischeck= $(_value).attr("checked");
	if(ischeck=="checked"){
		$(_value).attr("checked","checked");
	}else{
		$(_value).attr("checked",false);
	}
 }
function loadimg(value){
	var priImg = value.parent().parent().find('img');
	value.uploadify({
		'auto': true,
		'multi' : false,
		'swf' : '<%=rootPath%>view/js/uploadify.swf',
		'uploader' : '<%=rootPath%>microblog/microblogContentPic.action;jsessionid=<%=session.getId() %>',
		'formData':{
            'userId':'1'
        },
		'queueID' : 'fileList',
		'fileObjName' : 'picFile',
		'buttonImage':'<%=rootPath%>view/images/addicon.jpg',
		'width' : "20",
		'height' : "20",
		'removeCompleted': true,
		'removeTimeout': 0.1,
		'uploadLimit':1,
		'queueSizeLimit': 1,      //允许同时上传文件数量
		'fileSizeLimit' : "10MB",
		'fileTypeExts': '*.jpg;*.jpeg;*.gif;*.png',
        'fileTypeDesc': '图片文件',
		'onUploadSuccess' : function(file, data, response){	
			priImg.attr("src","<%=rootPath%>file/readfile.action?fileName="+data);
			priImg.attr("name",data);
		},
        'onUploadError': function (file, errorCode, errorMsg, errorString) {    //错误提示 
          alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
        }
	});
}



</script>
<form id="addgroupform">
<ul style="float: left" id="firstul"> 
	 <li id="checkli">&nbsp;单选/多选：<select name="irpvotetitle.checktype" onchange="checkoneormore(this)"><option value="1">单选</option><option  value="2">复选</option> </select>
	    &nbsp;&nbsp;&nbsp;<span id="lesscheck" style="display:none;">至少选<input style="width: 30px;" value="1" type="text" name="irpvotetitle.lesscheck"/> 个</span>
	    &nbsp;&nbsp;&nbsp;<span id="morecheck" style="display:none;">至多选<input style="width: 30px;" value="1" type="text" name="irpvotetitle.morecheck"/> 个</span>
	 </li>
	 <li id="questionvote">
	     <div><span style="color:red;">*</span>投票问题：
	          <ul><li><input id="votetitle" name="irpvotetitle.votetitle" type="text" class="inputvote"/></li></ul>
	     </div>
	 </li>
	 <li id="opteionvote">
	     <div><span style="color:red;">*</span>投票选项：
	        <ul id="optionul">
	            <li><label>&nbsp;1.&nbsp;&nbsp;&nbsp;</label><input name="option" type="text" class="textinput"/>
	                <br/>URL<input name="urltext" type="text" class="textinput" style="margin-top: 3px;"/>
	                <div style="margin-top: -20px;"><img style="margin-left: 250px;" id="img1" name="imgattr" width="50px;" height="60px" src="<%=rootPath%>view/images/uploadicon.jpg" /></div>
	                <div class="fileicon" id="fileicon1"><input id="voteimg1" style="display: none;" type="file" name="file" /></div>
	            </li>
	            <li><label>&nbsp;2.&nbsp;&nbsp;&nbsp;</label><input name="option" type="text" class="textinput"/>
	                 <br/>URL<input name="urltext" type="text" class="textinput" style="margin-top: 3px;"/>
	                <div style="margin-top: -20px;"><img style="margin-left: 250px;" id="img2" name="imgattr" width="50px;" height="60px" src="<%=rootPath%>view/images/uploadicon.jpg" /></div>
	                <div class="fileicon" id="fileicon2"><input id="voteimg2" style="display: none;" type="file" name="file" title="添加/修改图片"/></div>
	            </li>
	        </ul>  
	     </div>
	     <div>
	        <ul style="margin-top: 3px;">
	            <li id="addoptionli">
		            <a href="javascript:void(0)" onclick="addoption()">再加一项</a>&nbsp;&nbsp;&nbsp;
		            <span style="float:right;padding-right: 76px;color:gray;">至少设置两项，每项最多50个字 </span>
		       </li>
	        </ul>
	     </div>
	 </li>
  </ul>
  </form>