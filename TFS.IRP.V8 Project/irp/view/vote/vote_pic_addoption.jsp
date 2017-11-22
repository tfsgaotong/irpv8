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
var maxId=3;
$(function(){
	loadimg($("#voteimg1"));
	
});
function checkoneormore(_value){
	if($(_value).val()==2){
		$("#lesscheck").show();
	}else{
		$("#lesscheck").hide();
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
<form id="addoption">
<div><span style="color:red;">*</span>投票选项：
	        <ul id="optionul">
	            <li><label>&nbsp;1.&nbsp;&nbsp;&nbsp;</label><input id="addoption" type="text" class="textinput"/>
	                <br/>URL<input id="addurl" type="text" class="textinput" style="margin-top: 3px;"/>
	                 <div style="margin-top: -20px;"><img style="margin-left: 250px;" id="fileimg" name="imgattr" width="50px;" height="60px" src="<%=rootPath%>view/images/uploadicon.jpg" /></div>
	                <div class="fileicon" id="fileicon1"><input id="voteimg1" style="display: none;" type="file" name="file" /></div>
	            </li>
	        </ul>  
	     </div>
  </form>