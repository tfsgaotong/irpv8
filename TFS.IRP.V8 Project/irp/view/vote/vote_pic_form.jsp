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
width:200px;
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
	htmlget();
	loadimg($("#voteimg1"));
	loadimg($("#voteimg2"));
});
function htmlget(){
	htmlquestion=$("#questionvote").html();
	htmloption=$("#opteionvote").html();
	htmlcheckli=$("#checkli").html();
}
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
	var newDom = $("#optionul").append("<li><label>&nbsp;&nbsp;"+(index+1)+"&nbsp;&nbsp;&nbsp;</label><input name=\"option\" type=\"text\" class=\"textinput\"/><br/>URL&nbsp;<input name=\"urltext\" type=\"text\" class=\"textinput\" style=\"margin-top: 3px;\"/><div style=\"margin-top: -20px;\"><img style=\"margin-left: 250px;\" name=\"\" width=\"50px;\" height=\"60px\" src=\"<%=rootPath%>view/images/uploadicon.jpg\"/></div><div class=\"fileicon\"><input id=\"voteimg"+(maxId++)+"\" style=\"display: none;\" type=\"file\" name=\"\" /></div></li>");
	loadimg(newDom.find('li').last().find('input:file'));
	$("#optionul li div a").remove();
	if(newDom.find('li').length>2){
		$.each(newDom.find('li'),function(i,n){
			$(n).find('.fileicon').append("<a onclick=\"deleteoption(this)\" ><img title=\"删除选项\" style=\"margin-left: -310px;margin-top: 0px;cursor: pointer;\" width=\"20px;\" height=\"20px\" src=\"<%=rootPath%>view/images/deleteicon.jpg\" /></a>");
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
		$(n).html("&nbsp;&nbsp;"+(i+1)+".&nbsp;&nbsp;&nbsp;");
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
		'uploadLimit':10,
		'queueSizeLimit': 1,      //允许同时上传文件数量
		'fileSizeLimit' : "10MB",
		'fileTypeExts': '*.jpg;*.jpeg;*.gif;*.png',
        'fileTypeDesc': '图片文件',
        'onUploadStart' : function(file){
        	$.dialog.tips('图片上传中...',600,'loading.gif');
        },
		'onUploadSuccess' : function(file, data, response){	
			priImg.attr("src","<%=rootPath%>file/readfile.action?fileName="+data);
			priImg.attr("name",data);
			$.dialog.tips('图片上传完毕',1,'32X32/succ.png');
		},
        'onUploadError': function (file, errorCode, errorMsg, errorString) {    //错误提示 
        	$.dialog.tips('数据加载完毕',1,'32X32/fail.png',function(){
				alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
			});
        }
	});
}
var ispubmic=0;
var strRegex = "^(http|https|ftp)\\://([a-zA-Z0-9\\.\\-]+(\\:[a-zA-"   
    + "Z0-9\\.&%\\$\\-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{"   
    + "2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}"   
    + "[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|"   
    + "[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-"   
    + "4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0"   
    + "-9\\-]+\\.)*[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,4})(\\:[0-9]+)?(/"   
    + "[^/][a-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)*$"; 
var re = new RegExp(strRegex);
//保存
function savevote(){
	var fag=false;
	var title=$.trim($("#title").val());
	var votetitle=$.trim($("#votetitle").val());
	var queryString="";
	var startd=$("#starttime").datetimebox('getValue');
	var endd=$("#endtime").datetimebox('getValue');
	var startDate =  new  Date(startd.replace(/-/g,"/"));
	var endDate =  new  Date(endd.replace(/-/g,"/"));  
	var todayTime= new Date(<%=new java.util.Date().getTime()%>);
	var desc=$.trim($("#description").val());
	if(title==""||votetitle==""){
		fag=false;
		$.dialog.tips('投票标题或投票问题为空',1,'32X32/fail.png');
	}else if(title.length>100||title.length<2||votetitle.length>200||votetitle.length<2||$.trim(desc).length>200){
		fag=false;
		$.dialog.tips('投票标题、投票或说明长度不符合',1,'32X32/fail.png');
	}else{
		if($.trim(desc)>200){
			fag=false;
			$.dialog.tips('说明长度不能超过200',1,'32X32/fail.png');
		}
		if($.trim(startd)==""||$.trim(startd)==""){
			fag=false;
			$.dialog.tips('日期不能为空',1,'32X32/fail.png');
		}else if(todayTime>startDate){
			fag=false;
			$.dialog.tips('开始不能小于今天',1,'32X32/fail.png');
		}else if(startd>endd){
			fag=false;
			$.dialog.tips('结束时间不能小于开始时间',1,'32X32/fail.png');
		}else{
			fag=true;
		}
	}
	if(fag){
		queryString+="starttime="+$("#starttime").datetimebox('getValue');
		queryString+="&irptitle.endtime="+$("#endtime").datetimebox('getValue');
		
		var isph=$("#ispublish").attr("checked");
		if(isph=="checked"){
			$("#ispublish").val(2);
			ispubmic=2;
			queryString+="&irptitle.ispublish=2";
		}else{
			$("#ispublish").val(1);
			ispubmic=1;
			queryString+="&irptitle.ispublish=1";
		}
		queryString =queryString+"&"+ $('#votefirst').serialize();
		var title=$("#title").val();
		$("#titlename").val(title);
		var opvalue=$("#optionul li input[name='option']");
		var opimgattr=$("#optionul li img");
		var jsonoption="";
		var jsonoptionimg="";
		var urloption="";
		for(var i=0;i<opvalue.length;i++){
			var option=$("#optionul li:eq("+i+") input[name='option']").val();
			var attrimg=$("#optionul li:eq("+i+") img").attr("name");
			var optionurl=$("#optionul li:eq("+i+") input[name='urltext']").val();
			if($.trim(option)==null||$.trim(option)==""){
				$.dialog.tips('请填写选项',1,'32X32/fail.png');
				fag=false;
				break;
			}else if($.trim(option).length>200||$.trim(option).length<1){
				$.dialog.tips('选项长度应该在1到200之间',1,'32X32/fail.png');
				fag=false;
				break;
			}else if($.trim(attrimg)=="imgattr"){
				$.dialog.tips('请先上传图片',1,'32X32/fail.png');
				fag=false;
				break;
			}else{
				if($.trim(optionurl)==""||$.trim(optionurl)==null){
					optionurl=null;
					urloption+=optionurl+",";
					jsonoption+=option+",";
					jsonoptionimg+=attrimg+",";
					fag=true;
				}else{
					if(re.test($.trim(optionurl))){
						urloption+=optionurl+",";
						jsonoption+=option+",";
						jsonoptionimg+=attrimg+",";
						fag=true;
					}else{
						fag=false;
						$.dialog.tips('请输入有效网址',1,'32X32/fail.png');
					}
				}
			}
		 }
		  var title=$("#title").val();
		 var votetitle=$("#votetitle").val();
		 var description=$("#description").val();
		if(fag){
			$.ajax({
				type:"post",
				dataType:"json",
				data:{'soptionstr':jsonoption,'urloption':urloption,'jsonoptionimg':jsonoptionimg,'irptitle.title':title,'irpvotetitle.votetitle':votetitle,' irptitle.description':description},
			    async: false,
			    cache: false,
				url:"<%=rootPath%>menu/save_picvotefirst.action?"+queryString,
				success:function(votedid){
					$("#firstul").empty();
					$("#voteid").val(votedid);
					$.dialog.tips('图片投票保存成功',1,'32X32/succ.png');
					fag=true;
					if(ispubmic==2){
						mictext(votedid);
						$.dialog({id:'votedialog'}).close();
					}else{
						$.dialog({id:'votedialog'}).close();
						if($('#vote_launch').length>0){
							tabtopic('vote_launch');
						}else{
							window.open('<%=rootPath%>/menu/find_vote.action?ismyorall=2','_blank',''); 
						}
					}
				}
			});
		}
	}
	return fag;
}
//保存
function savevotenomic(){
	var fag=false;
	var title=$.trim($("#title").val());
	var votetitle=$.trim($("#votetitle").val());
	var queryString="";
	var startd=$("#starttime").datetimebox('getValue');
	var endd=$("#endtime").datetimebox('getValue');
	var startDate =  new  Date(startd.replace(/-/g,"/"));
	var endDate =  new  Date(endd.replace(/-/g,"/"));  
	var todayTime= new Date(<%=new java.util.Date().getTime()%>);
	var desc=$.trim($("#description").val());
	if(title==""||votetitle==""){
		fag=false;
		$.dialog.tips('投票标题或投票问题为空',1,'32X32/fail.png');
	}else if(title.length>100||title.length<2||votetitle.length>200||votetitle.length<2||$.trim(desc).length>200){
		fag=false;
		$.dialog.tips('投票标题、投票或说明长度不符合',1,'32X32/fail.png');
	}else{
		if($.trim(desc)>200){
			fag=false;
			$.dialog.tips('说明长度不能超过200',1,'32X32/fail.png');
		}
		if($.trim(startd)==""||$.trim(startd)==""){
			fag=false;
			$.dialog.tips('日期不能为空',1,'32X32/fail.png');
		}else if(todayTime>startDate){
			fag=false;
			$.dialog.tips('开始不能小于今天',1,'32X32/fail.png');
		}else if(startd>endd){
			fag=false;
			$.dialog.tips('结束时间不能小于开始时间',1,'32X32/fail.png');
		}else{
			fag=true;
		}
	}
	if(fag){
		queryString+="starttime="+$("#starttime").datetimebox('getValue');
		queryString+="&irptitle.endtime="+$("#endtime").datetimebox('getValue');
		
		var isph=$("#ispublish").attr("checked");
		if(isph=="checked"){
			$("#ispublish").val(2);
			ispubmic=2;
			queryString+="&irptitle.ispublish=2";
		}else{
			$("#ispublish").val(1);
			ispubmic=1;
			queryString+="&irptitle.ispublish=1";
		}
		queryString =queryString+"&"+ $('#votefirst').serialize();
		var title=$("#title").val();
		$("#titlename").val(title);
		var opvalue=$("#optionul li input[name='option']");
		var opimgattr=$("#optionul li img");
		var jsonoption="";
		var jsonoptionimg="";
		var urloption="";
		for(var i=0;i<opvalue.length;i++){
			var option=$("#optionul li:eq("+i+") input[name='option']").val();
			var attrimg=$("#optionul li:eq("+i+") img").attr("name");
			var optionurl=$("#optionul li:eq("+i+") input[name='urltext']").val();
			if($.trim(option)==null||$.trim(option)==""){
				$.dialog.tips('请填写选项',1,'32X32/fail.png');
				fag=false;
				break;
			}else if($.trim(option).length>200||$.trim(option).length<1){
				$.dialog.tips('选项长度应该在1到200之间',1,'32X32/fail.png');
				fag=false;
				break;
			}else if($.trim(attrimg)=="imgattr"){
				$.dialog.tips('请先上传图片',1,'32X32/fail.png');
				fag=false;
				break;
			}else{
				if($.trim(optionurl)==""||$.trim(optionurl)==null){
					optionurl=null;
					urloption+=optionurl+",";
					jsonoption+=option+",";
					jsonoptionimg+=attrimg+",";
					fag=true;
				}else{
					if(re.test($.trim(optionurl))){
						urloption+=optionurl+",";
						jsonoption+=option+",";
						jsonoptionimg+=attrimg+",";
						fag=true;
					}else{
						fag=false;
						$.dialog.tips('请输入有效网址',1,'32X32/fail.png');
					}
				}
			}
		 }
		  var title=$("#title").val();
		 var votetitle=$("#votetitle").val();
		 var description=$("#description").val();
		if(fag){
			$.ajax({
				type:"post",
				dataType:"json",
				data:{'soptionstr':jsonoption,'urloption':urloption,'jsonoptionimg':jsonoptionimg,'irptitle.title':title,'irpvotetitle.votetitle':votetitle,' irptitle.description':description},
			    async: false,
			    cache: false,
				url:"<%=rootPath%>menu/save_picvotefirst.action?"+queryString,
				success:function(votedid){
					//htmlget();
					$("#firstul").empty();
					$("#voteid").val(votedid);
					$.dialog.tips('图片投票保存成功',1,'32X32/succ.png');
					fag=true;
				}
			});
		}
	}
	return fag;
}
//保存选项
function savevotesecond(){
	var fag=false;
	var votetitle=$.trim($("#votetitle").val());
	if(votetitle==""){
		fag=false;
		$.dialog.tips('投票问题为空',1,'32X32/fail.png');
	}else if(votetitle.length>200||votetitle.length<2){
		fag=false;
		$.dialog.tips('投票问题长度不符合',1,'32X32/fail.png');
	}else{
		var queryString = $('#votefirst').serialize();
		var opvalue=$("#optionul li input[name='option']");
		var opimgattr=$("#optionul li img");
		var jsonoption="";
		var jsonoptionimg="";
		var urloption="";
		for(var i=0;i<opvalue.length;i++){
			var option=$("#optionul li:eq("+i+") input[name='option']").val();
			var attrimg=$("#optionul li:eq("+i+") img").attr("name");
			var optionurl=$("#optionul li:eq("+i+") input[name='urltext']").val();
			if($.trim(option)==null||$.trim(option)==""){
				$.dialog.tips('请填写选项',1,'32X32/fail.png');
				fag=false;
				break;
			}else if($.trim(option).length>200||$.trim(option).length<1){
				$.dialog.tips('选项长度应该在1到200之间',1,'32X32/fail.png');
				fag=false;
				break;
			}else if($.trim(attrimg)=="imgattr"){
				$.dialog.tips('请先上传图片',1,'32X32/fail.png');
				fag=false;
				break;
			}else{
				if($.trim(optionurl)==""||$.trim(optionurl)==null){
					optionurl=null;
					urloption+=optionurl+",";
					jsonoption+=option+",";
					jsonoptionimg+=attrimg+",";
					fag=true;
				}else{
					if(re.test($.trim(optionurl))){
						urloption+=optionurl+",";
						jsonoption+=option+",";
						jsonoptionimg+=attrimg+",";
						fag=true;
					}else{
						fag=false;
						$.dialog.tips('请输入有效网址',1,'32X32/fail.png');
					}
				}
			}
		 }
		 
		   var title=$("#title").val();
		 var votetitle=$("#votetitle").val();
		 var description=$("#description").val();
		if(fag){
			var pid=$("#voteid").val();
			$.ajax({
				type:"post",
				dataType:"json",
				data:{'soptionstr':jsonoption,'urloption':urloption,'jsonoptionimg':jsonoptionimg,'voteid':pid,'irptitle.title':title,'irpvotetitle.votetitle':votetitle,' irptitle.description':description},
			    async: false,
			    cache: false,
				url:"<%=rootPath%>menu/save_picvotesecond.action?"+queryString,
				success:function(data){
					//htmlget();
					$("#firstul").empty();
					$.dialog.tips(data,1.5,'32X32/succ.png');
				}
			});
		}
		return fag;
	}
}
//保存选项
function savevotesecondwithmic(){
	var fag=false;
	var votetitle=$.trim($("#votetitle").val());
	if(votetitle==""){
		fag=false;
		$.dialog.tips('投票问题为空',1,'32X32/fail.png');
	}else if(votetitle.length>200||votetitle.length<2){
		fag=false;
		$.dialog.tips('投票问题长度不符合',1,'32X32/fail.png');
	}else{
		var queryString = $('#votefirst').serialize();
		var opvalue=$("#optionul li input[name='option']");
		var opimgattr=$("#optionul li img");
		var jsonoption="";
		var jsonoptionimg="";
		var urloption="";
		for(var i=0;i<opvalue.length;i++){
			var option=$("#optionul li:eq("+i+") input[name='option']").val();
			var attrimg=$("#optionul li:eq("+i+") img").attr("name");
			var optionurl=$("#optionul li:eq("+i+") input[name='urltext']").val();
			if($.trim(option)==null||$.trim(option)==""){
				$.dialog.tips('请填写选项',1,'32X32/fail.png');
				fag=false;
				break;
			}else if($.trim(option).length>100||$.trim(option).length<1){
				$.dialog.tips('选项长度应该在1到100之间',1,'32X32/fail.png');
				fag=false;
				break;
			}else if($.trim(attrimg)=="imgattr"){
				$.dialog.tips('请先上传图片',1,'32X32/fail.png');
				fag=false;
				break;
			}else{
				if($.trim(optionurl)==""||$.trim(optionurl)==null){
					optionurl=null;
					urloption+=optionurl+",";
					jsonoption+=option+",";
					jsonoptionimg+=attrimg+",";
					fag=true;
				}else{
					if(re.test($.trim(optionurl))){
						urloption+=optionurl+",";
						jsonoption+=option+",";
						jsonoptionimg+=attrimg+",";
						fag=true;
					}else{
						fag=false;
						$.dialog.tips('请输入有效网址',1,'32X32/fail.png');
					}
				}
			}
		 }
		   var title=$("#title").val();
		 var votetitle=$("#votetitle").val();
		 var description=$("#description").val();
		if(fag){
			var pid=$("#voteid").val();
			$.ajax({
				type:"post",
				dataType:"json",
				data:{'soptionstr':jsonoption,'urloption':urloption,'jsonoptionimg':jsonoptionimg,'voteid':pid,'irptitle.title':title,'irpvotetitle.votetitle':votetitle,' irptitle.description':description},
			    async: false,
			    cache: false,
				url:"<%=rootPath%>menu/save_picvotesecond.action?"+queryString,
				success:function(data){
					$("#firstul").empty();
					$.dialog.tips(data,1.5,'32X32/succ.png');
					if(ispubmic==2){
						mictext($("#voteid").val());
						$.dialog({id:'votedialog'}).close();
					}else{
						$.dialog({id:'votedialog'}).close();
						if($('#vote_launch').length>0){
							tabtopic('vote_launch');
						}else{
							window.open('<%=rootPath%>/menu/find_vote.action?ismyorall=2','_blank',''); 
						}
					}
				}
			});
		}
		return fag;
	}
}
//保存并添加1
function addQuestion(){
	var fag=savevotenomic();
	if(fag){
		addHtml();
		loadimg($("#voteimg1"));
		loadimg($("#voteimg2"));
	}
}
//保存并添加2
function addQuestionsecond(){
	var fag=savevotesecond();
	if(fag){
		addHtml();
		loadimg($("#voteimg1"));
		loadimg($("#voteimg2"));
	}
}

function addHtml(){
	var htmltext="<li id=\"checkli\">"+htmlcheckli+"</li>";
	htmltext+="<li id=\"questionvote\">"+htmlquestion+"</li>";
	htmltext+="<li id=\"opteionvote\">"+htmloption+"</li>";
	htmltext+=" <li style=\"font-size: 20px;\"><a href=\"javascript:void(0)\" onclick=\"addQuestionsecond()\">保存并添加分组</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:void(0)\" onclick=\"savevotesecondwithmic()\">完成</a></li>";
	$("#firstul").html(htmltext);
	$("#lesscheck").hide();
	$("#morecheck").hide();
}
function mictext(votedid){
	var context="我刚发起了  "+$("#titlename").val()+" 的投票，大家快来看看吧";
	$("#sendmictext").val(context);
	var rsmic="<a class=\"linkbh14\" target=\"_blank\" href=\"<%=rootPath%>menu/vote_detil.action?voteid="+votedid+"\" >"+$("#titlename").val()+"</a>";
	sendmic($("#sendmictext").val(),rsmic);
}
function sendmic(microblog_text,urltext){
	 var result=" <textarea rows=\"1.5\" cols=\"36\" style=\"width: 400px;height: 100px;\" id=\"votemictext\">"+microblog_text+"</textarea>";
	 $.dialog({
			title:'发投票微知',
			content: result,
			max: false,
		    min: false, 
		    lock:true,
		    width:500,
		    height:200,
		    cancel:function(){ 
	   		},
		    cancelVal: '关闭',
		    ok: function(){
		    	if($.trim($("#votemictext").val())==""){
		    		$.dialog.tips('微知不能为空',1,'32X32/fail.png');
		    	}else{
		    		var microblog_type=0;
		    		var microblog_text= $("#votemictext").val()+urltext;
	    			var microbloggroup = "公开";
	    			$.ajax({
	    				type:"POST",
	    				url:'<%=rootPath%>microblog/microblogShare.action',
	    				cache:false,
	    				data:{
	    					publishInfo:microblog_text,
	    					microblogType:microblog_type,
	    					microbloggroup:microbloggroup
	    				},
	    				success:function(callback){
	    					if(callback!=null){
	    						$.dialog.tips('分享成功',1,'32X32/succ.png');
	    						hitMicroblogTab();
	    					}else{
	    						$.dialog.tips('分享失败',1,'32X32/fail.png'); 
	    					}
	    				}
	    			});
		    	}
		    },
		    okVal:'发投票微知',
		    padding: 0
		});
}

</script>
<s:hidden type="text" id="titlename" />
<s:hidden type="text" id="voteid" />
<form id="votefirst">
    <ul style="float: left" id="firstul">
        <!-- 投票标题 --> 
        <li>
            <div>
                <label style="display: inline-block;height:30px;line-height:30px;">
                    <span style="color:red;">*</span>投票标题：
                </label>
                <ul>
                    <li style="height:30px; margin-left: 10px;display: inline-block;margin-top:-11px;">
                        <input id="title" name="title" type="text" class="inputvote" />
                    </li>
                    <li id="adddesc" style="margin-left: 10px;">
                        <a href="javascript:void(0)" onclick="addDesc()">添加说明</a>
                    </li>
                    <li id="titledesc" style="display: none;margin-left: 10px;">
                       <textarea id="description" style="font-size: 12px;" name="description" rows="2" cols="46" ></textarea>
                    </li>
                </ul>
            </div>
        </li>
        <!-- 投票可见 -->
        <li>
            <div>
                <label style="display: inline-block;height:30px;line-height:30px;">
                    <span style="color:red;">*</span>结果可见：
                </label>
                <ul>
                    <li style="display:inline-block;height:30px;margin-top:-8px; margin-left: 10px;line-height: 30px;">                
                        <input id="voteToSee" type="radio" checked="checked" value="0" name="irptitle.resultshow"/>
                        <label for="voteToSee">投票后可见&nbsp;&nbsp;&nbsp;</label>
                        <input id="everyoneToSee" type="radio" value="2" name="irptitle.resultshow"/>
                        <label for="everyoneToSee">所有人可见</label>
                    </li>
                </ul>
            </div>
        </li>
        <!-- 投票时间 -->
        <li>
            <div>
                <label style="display: inline-block;height:30px;line-height:30px;">
                    <span style="color:red;">*</span>投票时间：
                </label>                   
                <ul>
                    <li style="display:inline-block;height:30px;margin-top:-10px; margin-left: 10px;line-height: 30px;">
                                                                         从&nbsp;<input id="starttime" type="text" style="width: 175px;"/>&nbsp;到
                        <input validType="EndTime" id="endtime" type="text" style="width: 175px;"/>
                    </li>
                </ul>
            </div>
        </li>
        <!-- 投票类型 -->
        <li id="checkli">
            <div>
                <label style="display: inline-block;height:30px;line-height:30px;">
                    <span style="color:red;">*</span>投票类型：
                </label>                
                <select name="irpvotetitle.checktype" onchange="checkoneormore(this)" style="display:inline-block; width:60px; margin-left:3px;">
                    <option value="1">单选</option>
                    <option value="2">复选</option>
                </select>
                &nbsp;&nbsp;&nbsp;<span id="lesscheck">至少选<input style="width: 30px;" value="1" type="text" name="irpvotetitle.lesscheck"/> 个</span>
                &nbsp;&nbsp;&nbsp;<span id="morecheck">至多选<input style="width: 30px;" value="1" type="text" name="irpvotetitle.morecheck"/> 个</span>
            </div>
        </li>
        <!-- 投票问题 -->
        <li id="questionvote">
            <div>
                <label style="display: inline-block;height:30px;line-height:30px;">
                    <span style="color:red;">*</span>投票问题：
                </label>                
                <ul>
                    <li style="height:30px; margin-left: 10px;display: inline-block;margin-top:-11px;">
                        <input id="votetitle" name="votetitle" type="text" class="inputvote" required/>
                    </li>
                </ul>
            </div>
        </li>
        <!-- 投票选项 -->
        <li id="opteionvote" style="margin-top: 2px;">
            <div>
                <label style="display: inline-block;height:30px;line-height:30px;">
                    <span style="color:red;">*</span>投票选项：
                </label>
                <ul id="optionul" style="margin:-30px 0 0 73px; width: 340px;padding: 0">
                    <li>
                        <label>&nbsp;&nbsp;1&nbsp;&nbsp;&nbsp;
                        </label><input name="option" type="text" class="textinput"/>
	                    <br/>URL&nbsp;
	                    <input name="urltext" type="text" class="textinput" style="margin-top: 3px;"/>
		                <div style="margin-top: -20px;">
                            <img style="margin-left: 250px;" id="img1" name="imgattr" width="50px;" height="60px" src="<%=rootPath%>view/images/uploadicon.jpg" />
                        </div>
		                <div class="fileicon" id="fileicon1">
                            <input id="voteimg1" style="display: none;" type="file" name="file" />
                        </div>
	               </li>
	               <li>
	                   <label>&nbsp;&nbsp;2&nbsp;&nbsp;&nbsp;</label>
	                   <input name="option" type="text" class="textinput"/>
	                   <br/>URL&nbsp;
	                   <input name="urltext" type="text" class="textinput" style="margin-top: 3px;"/>
	                   <div style="margin-top: -20px;">
	                       <img style="margin-left: 250px;" id="img2" name="imgattr" width="50px;" height="60px" src="<%=rootPath%>view/images/uploadicon.jpg" />
	                   </div>
	                   <div class="fileicon" id="fileicon2">
	                       <input id="voteimg2" style="display: none;" type="file" name="file" title="添加/修改图片"/>
	                   </div>
	               </li>
	           </ul>  
            </div>
            <div>
		        <ul style="margin-top: 3px;">
		            <li id="addoptionli">
			            <a href="javascript:void(0)" onclick="addoption()">再加一项</a>&nbsp;&nbsp;&nbsp;
			            <span style="margin-left:50px;color:gray;">至少设置两项，每项最多200个字 </span>
			       </li>
		        </ul>
            </div>
        </li>
        <li>
            <input type="checkbox" id="ispublish" onclick="checkchose(this)"/> 
            <label for="ispublish">立即发布</label>
        </li>
        <li style="font-size: 20px;">
            <a href="javascript:void(0)" onclick="addQuestion()">保存并添加分组</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="javascript:void(0)" onclick="savevote()">完成</a>
        </li>
    </ul>
</form>
  <ul style="float: left;display:none;" id="secondul"> 
     <li id="microblogtext" ><span style="color:red;">*</span>微知文字：<ul><li><textarea rows="1.5" cols="36" id="sendmictext"></textarea></li><li><span style="padding-right: 68px;" style="color:gray;">投票发起成功后将以此文字发送一条微知，其他用户参与投票后将转发此条微知</span></li></ul> </li>
  </ul> 
