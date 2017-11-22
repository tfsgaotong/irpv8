<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head>
     <title><s:property value="irpProject.prname"/> </title>
<link rel="shortcut icon" href="<%=rootPath%>favicon.ico"
	type="image/x-icon" />
<link rel="bookmark" href="<%=rootPath%>favicon.ico" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/client/css/easyui.css" />
<link href="<%=rootPath%>client/images/normalize.css" rel="stylesheet"
	type="text/css" />
<link href="<%=rootPath%>client/css/oacss.css" rel="stylesheet"
	type="text/css" />
	<link rel="stylesheet" href="<%=rootPath %>client/images/project/common.css" type="text/css"></link>
	<link href="<%=rootPath %>admin/js/jquery.uploadify/uploadify.css" rel="stylesheet" type="text/css" />
	<link href="<%=rootPath %>client/css/workhome-3482.min.css" rel="stylesheet" type="text/css" />
 	<link href="<%=rootPath %>client/css/lib-3322.min.css" rel="stylesheet" type="text/css" /> 
	<link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css">
	<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css">
	<link href="<%=rootPath%>/client/css/oacss.css" rel="stylesheet" type="text/css" />
<script src="<%=rootPath%>client/images/modernizr.custom.js"></script>
<script src="<%=rootPath%>client/images/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/modernizr.custom.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath%>dwr/engine.js"></script>  
<script type="text/javascript" src="<%=rootPath%>dwr/util.js"></script> 
<script type="text/javascript" src="<%=rootPath%>dwr/interface/chatvolet.js"></script>  
<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="<%=rootPath %>admin/js/jquery.uploadify/swfobject.js"></script>
	<script type="text/javascript" src="<%=rootPath %>client/js/jquery.uploadify.min.js"></script> 
<jsp:include page="../include/client_skin.jsp" />

  </head>

<style type="text/css">
	.searchSec .radios span {
margin-top: 0px;
}
	.in{
	width: 118px;
height: 36px;
background: #5f9ddd;
border: none;
border-radius: 3px;
overflow: hidden;
text-align: center;
line-height: 36px;
font-size: 15px;
color: #fff;
float: right;
display: inline;
margin-left: 15px;
	}
	
	
	.inputTitle{
	font-size:16px;
	height:35px;
	border-radius: 3px;
border: 1px solid #cecece;;
margin-bottom: 20px;
padding-left: 5px;
	}
	
	.lefttitle{
	border-bottom: 2px solid #d1d1d1;
	font-size: 15px;
	color: #171717;
	padding-top: 10px;
	line-height: 2.67;
}

.lefttitle a {
    color: #171717;
    font-size: 15px;
    border-bottom: solid 2px #186fd2;
    padding-bottom: 10px;
}

a b{
    border-radius: 3px;
    border: none;
    background: #5f9ddd;
    overflow: hidden;
    font-size: 15px;
    color: #fff;
    padding: 9px 20px;
    font-weight:normal;
    margin-left: 10px;

}

.disable_pub_cpsw{
	background: #e9e9e9;
	border: 1px solid #d9d9d9;
	width: 118px;
	height: 36px;
	border: none;
	border-radius: 3px;
	overflow: hidden;
	text-align: center;
	line-height: 36px;
	font-size: 15px;
	color: #fff;
	float: right;
	display: inline;
	margin-left: 15px
}
.disable_pub_aptaskt{
	background: #e9e9e9;
	border: 1px solid #d9d9d9;
	width: 118px;
	height: 36px;
	border: none;
	border-radius: 3px;
	overflow: hidden;
	text-align: center;
	line-height: 36px;
	font-size: 15px;
	color: #fff;
	float: right;
	display: inline;
	margin-left: 15px;
}
 .editBar {
  float: right;
display: inline;
height: auto;
line-height: 20px;
    
}
</style>
</head>

  
<body style="background-color: white;"> 

<script type="text/javascript">
  //修改正在参与为退出圈子
  function changestateValueOne(projectid){  
	  var cValue=$('#person'+projectid).text(); 
	  if($.trim(cValue=="正在参与")){
		  $('#person'+projectid).html("<b>退出圈子</b>");
	  } 
  }
  function changestateValueTwo(projectid){  
	  var cValue=$('#person'+projectid).text(); 
	  if($.trim(cValue)=="退出圈子"){ 
		  $('#person'+projectid).html("<b>正在参与</b>");  
	  }  
  }
  //退出圈子
  function deletePerson(projectid){
	$.dialog.confirm('确定退出圈子？再次加入需要与圈子负责人联系',function(){
			$.ajax({
				type:'post',
				url:'<%=rootPath%>project/deleteattention.action',
				data:{ 
					'projectId':projectid
				},
				success: function(msg){
				 	if(msg=="1"){
				 		$.dialog.tips('退出成功',1,'32X32/succ.png',function(){  
				 			 showoneproject();  
				 		 });	 
				 	} else{
						$.dialog.tips('退出失败',0.5,'32X32/fail.png');	
					}
				}
			 });
	});  
  }
  
function showoneproject(){ 
	window.location.reload(true);
}
    //关注圈子 /取消关注
function addOrDeleteAttaction(_projectId){ 
		var _att=$('#attention'+_projectId).text(); 
		var _attText=$.trim(_att); 
		if(_attText=="关注圈子"||_attText=="关注圈子"){
			$.ajax({
				url: '<%=rootPath%>project/addattention.action',
				data:{'projectId':_projectId},
				type:"post",
				dataType: "json", 
			    async: false ,
			    success : function(msg){
			    	if(msg!="0"){
			    		showoneproject();  
			    	}
			    }
			});
		}else if(_attText=="取消关注"){
			 $.ajax({
					url: '<%=rootPath%>project/deleteattention.action',
					data:{'projectId':_projectId},
					type:"post",
					dataType: "json", 
				    async: false ,
				    success : function(msg){
				    	if(msg!="0"){
				    		showoneproject();  
				    	}
				    }
				});
		} 	
}
//更换按钮文本
function changeTextOne(_projectId){
	var butText=$('#attention'+_projectId).text();  
	if($.trim(butText)=="正在关注"){
		$('#attention'+_projectId).html("<b>取消关注</b>"); 
	}
}
function changeTextTwo(_projectId){
	var butText=$('#attention'+_projectId).text();  
      if($.trim(butText)=="取消关注"){
		$('#attention'+_projectId).html("<b>正在关注</b>"); 
	  }
}	 
 
//判断删除事件 鼠标移上
function mouseinrow(sharetaskid){
	var deletealable ="#deletealable"+sharetaskid;  
	$(deletealable).css({visibility:"visible"});
}
	//判断删除事件 鼠标移出
	function mouseoutrow(sharetaskid){
		var deletealable ="#deletealable"+sharetaskid; 
		$(deletealable).css({visibility:"hidden"});
	}
 
//搜索名字
function searchInfo1(searchInfo){  
		searchtype = 5;  
		if(searchInfo.length>38){
			searchInfo = searchInfo.substring(0,37);	
		}
		var eacapeInfo = encodeURI(searchInfo);
	    window.location.href="<%=rootPath%>solr/searchcontentofsolr.action?searchInfo="+eacapeInfo+"&searchtype="+searchtype; 
}
 function exist(arr,value){
	 if(arr){
		 for(var i=0;i<arr.length;i++){
			 if(arr[i]==value){
				 return true;
			 }
		 }
	 }
	 return false;
 }
function toupdateproject(projectid){
var result = $.ajax({
			url: '<%=rootPath%>project/projectinfotoupdate.action',
			type:"post",
		data:{'projectId':projectid},
			dataType: "json", 
		    async: false 
		}).responseText; 
		 $.dialog({
		 	title:'修改圈子',
			max:false,
			min:false,
			width:'300px',
			height:'150px',
			lock:true,
			resize: false, 
			init:function(){
				<%--  var allChannelIds = "<s:property value='allChannelIds'/>";
				 var channelid = <s:property value="irpProject.prostatus" />;
				 var arrChannelIds = allChannelIds.split(',');
				 $('#projectStatus').combotree({
						url: '<%=rootPath%>site/findprojecttree.action',
						required: 'true',
						onLoadSuccess:function(node, data){
							for(var i=0;i<data.length;i++){
								var treeId = data[i].id; 
								if(exist(arrChannelIds,treeId)){
									var tree = $('#projectStatus').combotree('tree');
									var node = tree.tree('find',treeId);
									tree.tree('expand',node.target);
									if(channelid==treeId){
										tree.tree('select',node.target);
										$('#projectStatus').combo('setText',node.text);
										$('#projectStatus').combo('setValue',node.id);
									}
								}
							}
						}
				 });  --%>
				  $('#prnameinput').validatebox({required: true});  
				 $('#prdescinput').validatebox({required: true});  
				 $('#keyprint').validatebox({required:true});
			}, 
			content:result,
			cancelVal:'关闭',
			okVal:'确定',
			cancel:function(){},
			ok:function(){
					var isSubmit=$('#addprojectfrm').form('validate'); 
				      $('#addprojectfrm').form('submit',{
							url:'<%=rootPath%>project/updateproject.action',
							success:function(callback){  
									if(callback!=""){ 
										$.dialog.tips('修改圈子成功',1,'32X32/succ.png',function(){ 
											window.location.reload(true);
										});						
										}else{
											$.dialog.tips('修改圈子失败',0.5,'32X32/fail.png');	
										}
								},
								error:function(){
									$.dialog.tips('修改圈子失败',0.5,'32X32/fail.png');
								}
							});  
							return isSubmit;
			} 
		 });
}

//初始化dialog弹框选择人
function initDialogShow(){
	$('#dialogPageForm').find('searchWord').val('');
	$('#dialogPageForm').find('searchType').val('');
	$('#dialogPageForm').find('orderField').val('');
	$('#dialogPageForm').find('pageNum').val(1);  
}
var OffPersonDialog;//任务负责人 //圈子负责人 
var ProjectPersonDialog;//圈子成员/圈子关注者///任务成员
//分页
function page(_nPageNum){
	$('#pageForm').find('input[name="pageNum"]').val(_nPageNum);
	$('#tabMenu').find('a').each(function(){   
			if(this.className=="over"){  
				 var sData =$(this).attr('_link');
				 var pageString=$('#pageForm').serialize(); 
				 sData=sData+"?"+pageString; 
				 showMyShareTask(sData);
			}    
	}); 
}  
//分页
function pageperson(_nPageNum){
	$('#pageForm').find('input[name="pageNum"]').val(_nPageNum);
	$('#tabMenu').find('a').each(function(){   
			if(this.className=="over"){  
				 var sData =$(this).attr('_link');
				 var pageString=$('#pageForm').serialize(); 
				 sData=sData+"?"+pageString; 
				 showMyShareTask(sData);
			}    
	}); 
} 
//任务负责人的选择
function checkTaskOffpersonId(){
    var projectid=<s:property value="irpProject.projectid"/>; 
	var taskPersonId=$('#taskPersonId').val();
	var result = $.ajax({
		type: 'POST',
		url: '<%=rootPath %>project/select_user.action',
		data : {  'userIds':taskPersonId,
				  'projectId':projectid,
				  'pageNum':1},
	    async: false,
	    cache: false
	}).responseText; 
	OffPersonDialog= $.dialog({ 
		 			id : 'selectUser',
					title:'选择任务负责人',
					max:false,
					min:false,
					lock:true, 
					init : function(){  
						$('#sWord').searchbox({}); 
						 initDialogShow();
					},
					resize: false,
					width:'500px',
					height:'150px',
					content:result,
					cancelVal:'关闭',
					okVal:'确定',
					cancel:true,
					ok:function(){    
						 var _personId=$('#userIds').val();//以逗号分割的所有用户id  
						 var _personNames=$('#userNames').val(); 
						 $('#offPersonName').val(_personNames);
						 $('#taskPersonId').val(_personId);
						 //销毁掉上次查询的条件
					} 
				});  	 
}

//弹出选择选择一个人员（圈子负责人的转交）
function  toCheckProjectOffPerson(_ids){ 
		var result = $.ajax({
			type: 'POST',
			url: '<%=rootPath %>project/select_user.action',
			data : {  'userIds':_ids,
				'pageNum':1},
		    async: false,
		    cache: false
		}).responseText;
		OffPersonDialog= $.dialog({
			id:'selectUser',
			title:'选择圈子负责人',
			max:false,
			min:false,
			lock:true, 
			init : function(){  
				 $('#sWord').searchbox({}); 
			},
			resize: false,
			width:'500px',
			height:'150px',
			content:result,
			cancelVal:'关闭',
			okVal:'确定',
			cancel:true,
			ok:function(){    
				 var _personId=$('#userIds').val();//以逗号分割的所有用户id 
				 //发送请求修改圈子的负责人
				 var projectid=<s:property value="irpProject.projectid"/>;  
						$.ajax({
							url: '<%=rootPath%>project/updateprojectoffperson.action',
							data:{
									'irpProject.projectid':projectid, 
									'irpProject.offpersonid':_personId
							     },
							type:"post",
							dataType: "json", 
						    async: false ,
						    success:function(msg){
						     if(msg=="1"){
						      	window.location.reload(true);
						     }
						    }
						}); 
			} 
		});  	 
} 
//从圈子中删除圈子成员
function delPersonFromProject(_userId){ 
	 var projectid= <s:property value="irpProject.projectid"/> ; 
	 $.dialog.confirm('您确定要删除这个人员吗？',function(){
			$.ajax({
				type:'post',
				url:'<%=rootPath%>project/deletepersonfromproject.action',
				data:{
					'personId':_userId ,
					'projectId':projectid
				},
				success: function(msg){
				 	if(msg=="1"){
				 		$.dialog.tips('删除成功',1,'32X32/succ.png',function(){ 
				 		    fidnTabSpan(-1);
				 			// $('#user'+_userId).hide();//因此用户所在的li标签
				 			tabs();
				 		});	 
				 	} else{
						$.dialog.tips('删除失败',0.5,'32X32/fail.png');	
					}
				}
			 });
	}); 
}
	//分享中的聚焦
	function focusTextAreaText(){
		var textValue=$.trim($('#sharedesctextarea').val());
		if(textValue=='你在忙什么？'){
			$('#sharedesctextarea').val('');  
			$('#sharebutton').addClass("disable_pub_cpsw"); 
			$('#sharebutton').unbind('click');
		}; 
	}
	//分享中的失去焦点
	function blurTextAreaText(){
		var textValue=$.trim($('#sharedesctextarea').val());
		if(textValue=='') {
			$('#sharedesctextarea').val('你在忙什么？ ');  
			$('#sharebutton').addClass("disable_pub_cpsw"); 
			$('#sharebutton').unbind('click');
		}
	}
	//当值改变时候
	function changeTextAreaText(_info){
		var textValue=$.trim($('#sharedesctextarea').val()); 
		if(textValue=='' ||textValue=='你在忙什么？'){  
			$('#sharebutton').unbind('click');
			$('#topicFontCount_01').text(${maxAmount});
			$('#sharebutton').addClass("disable_pub_cpsw"); 
			$('#sharebutton').unbind('click');
		}
		else{  
			 if(${maxAmount}-$.trim(_info).length>=0){
			 	$('#sharebutton').removeClass("disable_pub_cpsw"); 
				$('#topicFont_02').css({display:'none'});
				$('#topicFont_01').css({display:'block'});
				$('#topicFontCount_01').text(${maxAmount}-$.trim(_info).length);
				$('#sharebutton').unbind('click').bind('click',function(){
					addShare();
				});
			 }else{
				$('#sharebutton').addClass("disable_pub_cpsw"); 
			 	$('#sharebutton').unbind('click');
				$('#topicFont_01').css({display:'none'});
				$('#topicFont_02').css({display:'block'});
				$('#topicFontCount_02').text(Math.abs(${maxAmount}-$.trim(_info).length));
			}
		} 
	} 
  	function inputhowmuchs(_sharetaskid){
		$(".input_waprfed").keyup(function(){
		    var inputlen=	$(this).val().length;
		    $("#inputcounts").text(inputlen);
		  	var ss ='#replayShareDescBut'+_sharetaskid;
		    var inputlab=$("#inputcounts").text();
		    var maxinput=$("#maxinputs").text();
		    if(parseInt(maxinput-inputlab)<=0){
		    	$(".input_waprfed").attr("style","border-color: red");
		    	$(ss).addClass("disable_pub_cpsw"); 
				$(ss).unbind('click');
		    }else{
		    	$(".input_waprfed").removeAttr("style");
		    }
		}); 
	}
	 function checkTaskDescAmount(_info){
			 if(${maxAmount}-$.trim(_info).length>=0){
				var textValue=$.trim(_info); 
		    	if(textValue!='更详细的说明' && textValue!=''){ 
					$('#topicFont_021').css({display:'none'});
					$('#topicFont_011').css({display:'block'});
					$('#topicFontCount_011').text(${maxAmount}-$.trim(_info).length);
					var taskinput=$.trim($('#TaskInput').val());
					if(taskinput==""  || taskinput=="要做什么事？"){
						$('#addtaskbut').unbind('click');
						$('#addtaskbut').addClass("disable_pub_aptaskt");
					}else{
						$('#addtaskbut').unbind('click').bind('click',function(){addTask();});
						$('#addtaskbut').removeClass("disable_pub_aptaskt"); 
					}
				}else{ 
					$('#addtaskbut').unbind('click');
					$('#topicFontCount_011').text(${maxAmount});
				} 
			 }else{
				$('#addtaskbut').addClass("disable_pub_aptaskt"); 
			 	$('#addtaskbut').unbind('click');
				$('#topicFont_011').css({display:'none'});
				$('#topicFont_021').css({display:'block'});
				$('#topicFontCount_021').text(Math.abs(${maxAmount}-$.trim(_info).length));
			}
	 } 
	//添加分享
	function addShare(){
		 $('#sharebutton').unbind('click');
		 $('#sharebutton').addClass("disable_pub_cpsw"); 
		 var textValue=$.trim($('#sharedesctextarea').val());
		 var isvisibleselect=$('#isvisibleselect').val();  
		 var projectid=<s:property value="irpProject.projectid"/>; 
		 var arrList='';
		$("input[name='projectFileList']").each(function(){ 
		 		arrList+=this.value+","; 
		 }); 
		 $.ajax({
			type:'post',
			url:'<%=rootPath%>project/addshare.action',
			data:{
				'irpProjectShareTask.sharedesc':textValue,
				'irpProjectShareTask.isvisible':isvisibleselect,
				'irpProjectShareTask.projectid': projectid,
				'irpProjectShareTask.parentid':0,
				'projectFileListStr':arrList
			},
			success: function(msg){
			 	if(msg=="1"){
			 		$.dialog.tips('分享成功',1,'32X32/succ.png',function(){
			 			var tabShare=$('#tabShare').text() ;
			 			tabShare=tabShare.replace("(","").replace(")","");
			 			var s=parseInt(tabShare)+1;
			 		     $('#tabShare').text("("+s+")");  
			 		    $('#sharedesctextarea').val('你在忙什么？');
			 			 tabs();   
			 			 var nCount=$('#showFileList').find('span').length;  
			 			 var tabDocument=$('#tabDocument').text() ;
			 			tabDocument=tabDocument.replace("(","").replace(")",""); 
			 			var num=$('#showFileList').find('span').length+parseInt(tabDocument)
			 			 $('#tabDocument').html("("+num+")"); 
			 			 $('#showFileList').children().remove(); 
			 			 $('#topicFontCount_01').text(${maxAmount});
			 		});	 
			 	} else{
					$.dialog.tips('分享失败',0.5,'32X32/fail.png');	
				}
			}
		 }); 
	}
//左边显示该圈子的相关知识


//显示和隐藏 两个添加div
function showOrHideShareTaskDiv(showObj,hideObj){
		$('#'+showObj).show();
		$('#'+hideObj).hide();
}  
//查询和自己有关的动态
function showMyShareTask(_href){ 

	var _projectId=<s:property value='irpProject.projectid'/>; 

	var result = $.ajax({
			url:_href,
			type:"post",
			data :{
				'projectId' : _projectId
				},
			dataType: "json", 
		    async: false 
		}).responseText;   
	 $('#showlinkprojectdiv').html(result);  
}
//回复框显示
function showreplayTexarea(_sharetaskid,selfId,_personname){  
	if($('#replayTexarea'+_sharetaskid).css("display")=='none'){
		$ ('#selfid').val(selfId);
		$('#hidejiandiv'+_sharetaskid).show();
		$('#replayTexarea'+_sharetaskid).show(); 
	} 
	else{
	var replayAmount=$('#replayAmount'+_sharetaskid).text();
	if($.trim(replayAmount)=="(0)"){
		$('#hidejiandiv'+_sharetaskid).hide();
	}
		$('#replayTexarea'+_sharetaskid).hide();
	} 
}
//回复框隐藏
function hidereplayTexarea(_sharetaskid){
	$('#replayTexarea'+_sharetaskid).hide();
}
//回复框的值不等于“”的时候显示回复按钮
function showreplayShareDescButton(_sharetaskid){
	var sharetask=$('#replayShareDesc'+_sharetaskid).val(); 
	if($.trim(sharetask)==""){
			$('#replayShareDescBut'+_sharetaskid).addClass("disable_pub_aprfed"); 
			$('#replayShareDescBut'+_sharetaskid).unbind('click');
	}else{
			$('#replayShareDescBut'+_sharetaskid).removeClass("disable_pub_aprfed"); 
			$('#replayShareDescBut'+_sharetaskid).unbind('click');
 			$('#replayShareDescBut'+_sharetaskid).bind('click',function(){
				replayShareText( _sharetaskid );
			});			
			inputhowmuchs(_sharetaskid);
	}	

}
//回复方法
function replayShareText(_sharetaskid){
		$('#replayShareDescBut'+_sharetaskid).unbind('click');
		 var sharetaskValue=$('#replayShareDesc'+_sharetaskid).val();  
		 $.ajax({
			type:'post',
			url:'<%=rootPath%>project/addreplay.action',
			data:{
				'irpProjectShareTask.sharedesc':sharetaskValue,
				'irpProjectShareTask.parentid' : _sharetaskid,
				'irpProjectShareTask.sharetaskid': $('#selfid').val()
			},
			success: function(msg){
			 	if(msg=="1"){
			 		$.dialog.tips('回复成功',1,'32X32/succ.png',function(){
			 		  tabs();  
			 		   fidnTabSpan(msg); 
			 		});	 
			 	} else{
					$.dialog.tips('回复失败',0.5,'32X32/fail.png');	
				}
			}
		 });
		 
}
//删除动态
function deleteShareTask(_sharetaskid){
	$.dialog.confirm('您确定要删除这条动态吗',function(){
			$.ajax({
				type:'post',
				url:'<%=rootPath%>project/deletesharetask.action',
				data:{
					'irpProjectShareTask.sharetaskid':_sharetaskid 
				},
				success: function(msg){
				 	if(msg!="0"){
				 		$.dialog.tips('删除成功',1,'32X32/succ.png',function(){
				 			  fidnTabSpan(-msg); 
				 			  tabs();
				 		});	 
				 	} else{
						$.dialog.tips('删除失败',0.5,'32X32/fail.png');	
					}
				}
			 });
	}); 
}
//获取当前选中的tab里面的span标签对象
function fidnTabSpan(amount){
	$('#tabMenu').find('a').each(function(){   
			if(this.className=="over"){
				 var spanB=$(this).find("span")[0];
				 if(spanB){ 
				  	$(spanB).text(parseInt($(spanB).text())+parseInt(amount));  
				  }
			} 
	}); 
}
function tabs(adom){ 
	
 
	$('#tabMenu').find('span').each(function(){   
		if(adom==null ||adom=='undifined'){ 
			if(this.className=="on"){  
			 adom=this; 
			} 
		}
		this.className=""; 
	}); 
	if(adom!=null){  
		adom.className="on"; 
		var thisId=adom.id;
		if(thisId=="attacheda"){
			$('#publishShareT').hide();
			$('#addTaskDiv').hide(); 
		}else{
			if(thisId=="taska"){
				showOrHideShareTaskDiv('addTaskDiv','publishShareT'); 
			}else if( thisId=="collecta"){
				$('#addTaskDiv').hide();
				$('#publishShareT').hide();
			}
			else{
				showOrHideShareTaskDiv('publishShareT','addTaskDiv'); 
			}
		}
		    var sData =$(adom).attr('_link'); 
			showMyShareTask(sData+"?pageNum=1");
    }  
} 
function allprojectLeft(){
	var result = $.ajax({
		url: '<%=rootPath%>project/simpleproject.action?protype=0',
		data:{'taskorproject':'project'},
		type:"post",
		dataType: "json", 
		async: false 
	}).responseText; 
	$('#leftMenu').html(result);
	
} 

 /** 首先进入，   */
	 $(function(){
		 	//alert();
		    //allprojectLeft();//初始化左侧菜单
		    hotProject();
	 		//alert('左侧菜单加载完毕');
		   initWindowOpen();//初始化弹框，这样隐私圈子，被邀请关注的人先关注才可以看这个圈子的
	 		//showXiangguanDocumentToProject();//相关知识
	 		 tabs(); 	
	 		 $('#addTaskDiv').hide(); 
	 		 initProjectTime();// 初始化圈子剩余时间
	 		 loadUploadPic();//初始化上传插件
	 		 loadUploadPicTask();//初始化任务上传c
	 		$('#TaskInput').val('要做什么事？');
	 		 $('#taskDescTextArea').val('更详细的说明');
	 		 $('#inputcounttask').val(0); 
	 		 $('#sharedesctextarea').val('你在忙什么？');
	 		 $('#projectStatus').combotree({
					url: '<%=rootPath%>site/findprojecttree.action',
					width: '200px' 
			});
			$('#sharebutton').unbind('click').bind('click',function(){
					addShare();
				});
				
		$('#addtaskbut').unbind('click').bind('click',function(){addTask();});
	 });
 function initWindowOpen(){
	 	var _status="<s:property value='irpProjectPerson.status'/>";
	 	var _projectIsPub="<s:property value='irpProject.ispublish'/>";
	 	var projectId="<s:property value='irpProject.projectid'/>";
	 	var _static_status="<s:property value='@com.tfs.irp.projectperson.entity.IrpProjectPerson@NOT_STATUS'/>";
	 	var _static_isPub="<s:property value='@com.tfs.irp.project.entity.IrpProject@IS_PUBLISH'/>";
	  
	   if(_projectIsPub==_static_isPub) return ;
	   if(_status==_static_status){//如果关注者没有开通
	    	$.dialog.confirm('您需要关注该圈子，才可以查看圈子动态，确定关注吗？',function(){
				$.ajax({
						type:'post',
						url:'<%=rootPath%>project/updateperonstatus.action',
						data:{'projectId':projectId},
						success: function(msg){    
							if(msg!="1"){
								 window.history.go(-1);
							}else{ 
								 window.location.reload(true);//确定关注了
							}
						}
		    	 });
			},function(){
				 window.history.go(-1); 
			},parent); 
	    }
 } 
	 function tabsH(objId){ 
	 	 $('#shareTaskH').find('a').each(function(){
	 	 	 if(this.id==objId){
	 	 	 	this.className='over';
	 	 	 	$('#taska').click();
	 	 	 	//激活日期控件
				$('#taskStart').datebox({ required:true });  
				$('#taskEnd').datebox({ required:true});
	 	 	 }else{
	 	 	 	this.className='';
	 	 	 	$('#sharea').click();
	 	 	 }
	 	 }); 
	 } 
	 
	  function toShare(){ 
	 	 	 	$('#taska').click();
	 	 	 	//激活日期控件
				$('#taskStart').datebox({ required:true });  
				$('#taskEnd').datebox({ required:true});
	 } 
	//比较两个日期的大小
		function  compareTime(startDate,endDate){ 
			startDate=startDate.replace(/-/g,"/");
			endDate=endDate.replace(/-/g,"/"); 
			return Date.parse(startDate)<Date.parse(endDate);
		} 
		//引入扩展验证
		$.extend($.fn.validatebox.defaults.rules, {   
		    EndTime:{
		    	validator:function(){
		    		var startDate=$('#taskStart').datebox('getValue');
		    		var endDate=$('#taskEnd').datebox('getValue');
		    		return  compareTime(startDate,endDate);
		    	},
		    	message:'结束日期必须在开始日期之后'
		    },
		    StartTime:{
		    	validator:function(){
		    		var startDate=$('#taskStart').datebox('getValue');
		    		var endDate=$('#taskEnd').datebox('getValue');
		    		return  compareTime(startDate,endDate);
		    	},
		    	message:'开始日期必须在结束日期之前'
		    }
			}); 
	 /***************************************************************/
	 
	//任务中的聚焦
	function focusTaskInput(){
		var textValue=$.trim($('#TaskInput').val());
		if(textValue=='要做什么事？' || textValue==''){
			$('#TaskInput').val('');
			$('#addtaskbut').addClass("disable_pub_aptaskt"); 
			$('#addtaskbut').unbind('click');
		} else{
			var taskdesc=$.trim($('#taskDescTextArea').val());
			if(taskdesc!="" && taskdesc!="更详细的说明"){
				$('#addtaskbut').unbind('click').bind('click',function(){addTask();});
				$('#addtaskbut').removeClass("disable_pub_aptaskt"); 
			}else{
				$('#addtaskbut').unbind('click');
				$('#addtaskbut').addClass("disable_pub_aptaskt"); 
			}
		}
	}
	//任务中的失去焦点
	function blurTaskInput(){
		var textValue=$.trim($('#TaskInput').val());
		if(textValue=='' || textValue=='要做什么事？') {
			$('#TaskInput').val('要做什么事？'); 
			$('#addtaskbut').unbind('click');
			$('#addtaskbut').addClass("disable_pub_aptaskt");  
		}else{
			var taskdesc=$.trim($('#taskDescTextArea').val());
			if(taskdesc!="" && taskdesc!="更详细的说明"){
				$('#addtaskbut').unbind('click').bind('click',function(){addTask();});
				$('#addtaskbut').removeClass("disable_pub_aptaskt"); 
			}else{
				$('#addtaskbut').unbind('click');
				$('#addtaskbut').addClass("disable_pub_aptaskt"); 
			}
		}
	} 
	//添加任务
	function addTask(){
		$('#addtaskbut').unbind('click');
		$('#addtaskbut').addClass("disable_pub_aptaskt");
	 	var textValue=$.trim($('#TaskInput').val());
		if(${maxAmount}-$.trim(textValue).length>=0){
		 var isvisibleselect=$('#isvisibleselecttask').val(); 
			 var projectid=<s:property value="irpProject.projectid"/>; 
			 var projectpersonidString=$('#taskPersonIds').val();
			 var taskDescTextArea=$('#taskDescTextArea').val();
			 var starttime=$('#taskStart').datebox('getValue');
			 var endtime=$('#taskEnd').datebox('getValue');
			 var offpersonid=$('#taskPersonId').val(); 
			 var arrList='';
				$("input[name='projectFileListTask']").each(function(){ 
			 		arrList+=this.value+","; 
			 });  
			 $.ajax({
				type:'post',
				url:'<%=rootPath%>project/addtask.action',
				data:{
					'irpProjectShareTask.sharedesc':textValue,
					'irpProjectShareTask.isvisible':isvisibleselect,
					'irpProjectShareTask.projectid': projectid,
					'irpProjectShareTask.taskdesc':taskDescTextArea,
					'irpProjectShareTask.starttime':starttime,
					'irpProjectShareTask.endtime':endtime,
				  	'irpProjectShareTask.offpersonid':offpersonid,
					'projectPersonIdString':projectpersonidString,
					'projectFileListStr':arrList
				},
				success: function(msg){
				 	if(msg=="1"){
				 		$('#offPersonName').val('');
						$('#taskPersonId').val('');
				 		$.dialog.tips('发布成功',1,'32X32/succ.png',function(){	
				 		var tabShare=$('#tabShare').text() ;
			 			tabShare=tabShare.replace("(","").replace(")","");
			 			var num=parseInt(tabShare)+2;
				 		$('#tabShare').text("("+num+")");
				 		var tabTask=$('#tabTask').text() ;
			 			tabTask=tabTask.replace("(","").replace(")",""); 
			 			num=parseInt(tabTask)+1;
				 		$('#tabTask').text("("+num+")"); 	
			 			tabs(); 
						$('#taskDescTextArea').val('');
						$('#taskStart').datebox('setValue', '');	
						$('#taskEnd').datebox('setValue', '');	
						$('#taskPersonIds').val('');
						$('#taskPersonNames').val('');  
						$('#TaskInput').val('要做什么事？');
						$('#taskDescTextArea').val('更详细的说明');
						$('#inputcounttask').val(0); 
				 		 
						var nCount=$('#showFileListTask').find('span').length;
						   var tabDocument=$('#tabDocument').text() ;
			 			tabDocument=tabDocument.replace("(","").replace(")",""); 
			 			var num=$('#showFileList').find('span').length+parseInt(tabDocument)
			 			 $('#tabDocument').html("("+num+")"); 
						$('#showFileListTask').children().remove(); 
							$('#topicFontCount_011').text(${maxAmount});
						
					});	 
				 	} else{
						$.dialog.tips('发布失败',0.5,'32X32/fail.png');	
					}
				}
			 });
		}
 	}
function jump(_form){  
		var queryString = _form.serialize(); 
		var result = $.ajax({
					type: 'post',
					url: '<%=rootPath %>project/select_usertask.action', 
					data:queryString,
				    async: false,
				    cache: false
					}).responseText;    
	ProjectPersonDialog.get('selectUser',1).content(result); 
}
 
	//弹出选择 任务人员框
	function  toCheckTaskPerson(projectid){  
		 var _names=$('#taskPersonNames').val();
		 var _ids=$('#taskPersonIds').val();  
		 var _userNames=$('#taskPersonNames').val();
			var result = $.ajax({
				type: 'POST',
				url: '<%=rootPath %>project/select_usertask.action',
				data : {'userIds':_ids,
						'projectId': projectid ,
						'userNames':_userNames,
						'pageNum':1},
			    async: false,
			    cache: false
			}).responseText;
			ProjectPersonDialog=$.dialog({
				id: 'selectUser',
				title:'选择任务成员',
				max:false,
				min:false,
				lock:true, 
				init : function(){  
					$('#sWord').searchbox({}); 
					 initDialogShow();
				},
				resize: false,
				width:'500px',
				height:'150px',
				content:result,
				cancelVal:'关闭',
				okVal:'确定',
				cancel:true,
				ok:function(){   
					var _personNames=$('#userNames').val();//这是用，分割的用户名称
					var _personIds=$('#userIds').val();//以逗号分割的所有用户id
					
					$('#taskPersonNames').val(_personNames);
					$('#taskPersonIds').val(_personIds); 
				},
				close:function(){
					$('#userIds').val('');
				} 
			});  	 
 }
  
	function changeTaskTextArea(){ 
		var textVlaue=$('#taskDescTextArea').val();
		if($.trim(textVlaue)=="更详细的说明"){
			$('#taskDescTextArea').val('');
			$('#addtaskbut').addClass("disable_pub_aptaskt");
			$('#addtaskbut').unbind('click');
			$('#topicFontCount_011').text(${maxAmount});
			
		}else if($.trim(textVlaue)==""){
			$('#taskDescTextArea').val('更详细的说明');
			$('#addtaskbut').addClass("disable_pub_aptaskt");
			$('#addtaskbut').unbind('click');
			$('#topicFontCount_011').text(${maxAmount});
		}else{ 
			var taskinput=$.trim($('#TaskInput').val());
			if(taskinput==""  || taskinput=="要做什么事？"){
				$('#addtaskbut').unbind('click');
				$('#addtaskbut').removeClass("disable_pub_aptaskt");
			}else{
				$('#addtaskbut').unbind('click').bind('click',function(){addTask();});
				$('#addtaskbut').addClass("disable_pub_aptaskt");
			}
		}
	}
	//删除圈子
	function delteProject(_projectId){
		$.dialog.confirm('您确定删除这个圈子吗？',function(){
			$.ajax({
					type:'post',
					url:'<%=rootPath%>project/deleteproject.action',
					data:{
						'projectId':_projectId  
					},
					success: function(msg){ 
					 	if(msg=="1"){
					 		$.dialog.tips('删除成功',1,'32X32/succ.png',function(){
					 			 window.location='<s:url action="projectIndex" namespace="/project" />'; 
					 		});	 
					 	} else{
							$.dialog.tips('删除失败',0.5,'32X32/fail.png');	
						}
					}
		  });
	}); 
	}

//当圈子时间改变之后就发送ajax请求
function changeProjectTime(){
		var _projectEnd=$('#projectEnd').val();
		var _projectStart=$('#projectStart').val();
		var projectid=<s:property value="irpProject.projectid"/>;  
		$.ajax({
			url: '<%=rootPath%>project/updateproject.action',
			data:{
					'irpProject.projectid':projectid,
					'irpProject.starttime':_projectStart,
					'irpProject.endtime':_projectEnd
			     },
			type:"post",
			dataType: "json", 
		    async: false ,
		    success : function(msg){
		    	if(msg=="1"){
		    		initProjectTime(_projectStart,_projectEnd);
		    	}
		    }
		}); 
}
//初始化圈子时间
function initProjectTime(_firstDate,_lastDate){ 
		var msg=' <b>剩余</b><span style="font-family: Gabriola,sans-serif;color: #f28933;font-size: 30px;" id="dValue">' ; 
		var starttime='<s:date name="irpProject.starttime" />';
		var endtime='<s:date name="irpProject.endtime" /> '; 
		
		if(_firstDate!=null ||_lastDate!=null){
			 starttime=_firstDate;
			 endtime=_lastDate;
		} 
		var startDate =  new  Date(starttime.replace(/-/g,"/"));
		var endDate =  new  Date(endtime.replace(/-/g,"/"));  
		
		var todayTime= new Date(<%=new java.util.Date().getTime()%>); 
		var dayTime= Math.ceil(Math.abs(((new Date(<%=new java.util.Date().getTime()%>)).getTime() - endDate.getTime())/(1000*60*60*24)));  
		msg+=dayTime+'</span><b>天'; 
		if(endDate<=todayTime){  msg='<b>快处理吧，已到期'; }
		msg+=' </b>'; 
		 $('#dayP').html(msg); 
}  
//邀请关注者(发送私信)
function yaoPersonAttention(projectid){ 
			 var result = $.ajax({
				type: 'POST',
				url: '<%=rootPath %>project/select_allusertask.action',
				data:{'isPerson':false,
					'projectId':projectid,
					'isPerson':false,
					'userIds':"",
					"pageNum":1},
			    async: false,
			    cache: false
			}).responseText;  
			 ProjectPersonDialog= $.dialog({
				 id: 'selectUser',
				title:'邀请关注者',
				max:false,
				min:false,
				lock:true, 
				init : function(){  
					$('#sWord').searchbox({}); 
					 initDialogShow();
				},
				resize: false,
				width:'500px',
				height:'150px',
				content:result,
				cancelVal:'关闭', 
				okVal:'确定',
				cancel:true,
				ok:function(){
					var _personIds=$('#userIds').val();//以逗号分割的所有用户id   
					var _loginUserId=<s:property value="loginUserid"/>;
					if(_personIds==null ||_personIds=="" ||_personIds==_loginUserId){}
					else{
						$.ajax({
							type:'post',
							url:'<%=rootPath%>project/addpersonattention.action',
							data:{ 'personIds':_personIds,
									'projectId': projectid
							   },
							 success : function(msg){
							 	if(msg!="0"){
							 		$.dialog.tips('发送私信邀请成功',1,'32X32/succ.png',function(){  
				 		 			});
							 	}else{
							 		$.dialog.tips('发送私信邀请失败',1,'32X32/fail.png');	
							 	}
							 }  
						}); 
					}
				} ,
				close:function(){
					 $('#userIds').val('');
 				} 
			});	
			
}
//添加成员
	function  addProjectPerson(projectid){  
			 var result = $.ajax({
				type: 'POST',
				url: '<%=rootPath %>project/select_allusertask.action', 
				data:{'isPerson':true,
						'projectId':projectid,
						'pageNum':1,
						'userIds':<s:property value='irpProject.offpersonid'/>},
			    async: false,
			    cache: false
			}).responseText; 
			 ProjectPersonDialog= $.dialog({
				id: 'selectUser',
				title:'添加圈子成员',
				max:false,
				min:false,
				lock:true, 
				init : function(){  
					$('#sWord').searchbox({}); 
					 initDialogShow();
				},
				resize: false,
				width:'500px',
				height:'150px',
				content:result,
				cancelVal:'关闭',
				okVal:'确定',
				cancel:true,
				ok:function(){    
					var _personIds=$('#userIds').val();//以逗号分割的所有用户id   
					var _loginUserId=<s:property value="loginUserid"/>;
					if(_personIds==null ||_personIds=="" ||_personIds==_loginUserId){}
					else{
						$.ajax({
							type:'post',
							url:'<%=rootPath%>project/addprojectperson.action',
							data:{
								'personIds': _personIds,
								'projectId': projectid
							},
							success: function(msg){ 
							 	if(msg!="0"){
							 		$.dialog.tips('添加成员成功',1,'32X32/succ.png',function(){  
							 		fidnTabSpan(msg);
									tabs();
							 		});	 
							 	}else{
									$.dialog.tips('添加成员失败',0.5,'32X32/fail.png');	
								}
							} 
						}); 
					}
				},
				close:function(){
				 $('#userIds').val('');
				} 
			});	
 } 
//加载上传组件
function loadUploadPic(){ 
		//发送ajax请求得到所有可以上传的文件类型以及上传的文件大小
		var maxamount = $.ajax({
	    	type:"get",
	    	url:"<%=rootPath%>file/findsize.action",
	    	cache:false,
	    	async:false
    	 }).responseText;
    	var allfileext = $.ajax({
	    	type:"get",
	    	url:"<%=rootPath%>file/findfileext.action",
	    	cache:false,
	    	async:false
    	 }).responseText;
    	 var picext = $.ajax({
	    	type:"get",
	    	url:"<%=rootPath%>file/findpicext.action",
	    	cache:false,
	    	async:false
    	 }).responseText;
   	  var picexts =picext.split(",");
	$("#projectFileBut").uploadify({
		'auto': true,
		'multi' : false,
		'swf' : '<%=rootPath%>client/js/uploadify.swf',
		'uploader' : '<%=rootPath%>project/projectfilelist.action;jsessionid=<%=session.getId() %>',
		'queueID' : 'fileList',
		'fileObjName' : 'picFile',
		'buttonText' : '图片/文件',  
		'removeCompleted': true,
		'width' : "60",
		'height' : "16",
		'removeTimeout': 1,
		'queueSizeLimit': 3,//允许同时上传文件数量
		'fileSizeLimit' : maxamount, 
		'fileTypeExts': allfileext, 
	    'fileTypeDesc': '图片文件',
		'onUploadSuccess' : function(file, data, response){	
			var filetype=file.type;
			var maxwrite=filetype.toLocaleUpperCase();//大写
			var minwrite=filetype.toLocaleLowerCase();//小写
			var fileListStr=$('#showFileList').html(); 
			var names=data.split("#");//names[0]上传之后的名字nams[1]上传之前的名字
			var imgStr="";
			if(exist(picexts,maxwrite)|| exist(picexts,minwrite)){//如果是图片
			    var imgStr = '<%=rootPath%>file/readfile.action?fileName='+names[0];
			 }else{
			 	imgStr="<%=rootPath%>client/images/doc.jpg";
			 }
	  		 var nCount=0;
	  		 $('#showFileList').find('span').each(function(){
	  		 	var thisId=$(this).attr("id");
	  		 	var Kid=thisId.substring("divfile".length,$.trim(thisId).length) ;
	  		 		if(parseInt(Kid)>=parseInt(nCount)){
	  		 			nCount=parseInt(Kid)+1;
	  		 		}
	  		 });
	  		 var fileListStr=fileListStr+'<span id="divfile'+nCount+'">';
	    		fileListStr+='<img src="'+imgStr+'" width="150px" height="120px" style="margin-left: 5px;margin-top: 5px;" />';
	    		fileListStr+='<input type="hidden" name="projectFileList" id="filename'+nCount+'" value="'+data+'" /></td>';	
 	    		fileListStr+='<a href="javascript:void(0);" onclick="delFile('+nCount+')" style="background-color: black;color: white;margin-left: -25px;">删除</a>';		
	    		fileListStr+='</span>';
	    			
	  		$('#showFileList').html(fileListStr);
	  		var numberSpan=$('#showFileList').find('span'); 
	  		if(numberSpan.length!=0){
	  			$('#sharedesctextarea').val("分享了"+numberSpan.length+"个文件");
	  			changeTextAreaText();
	  		}  
		},
		'onUploadError': function (file, errorCode, errorMsg, errorString) {    //错误提示 
            alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
        }
	}); 
}
//加载任务中的文件上传
 
//加载上传组件
function loadUploadPicTask(){ 
		var maxamount = $.ajax({
	    	type:"get",
	    	url:"<%=rootPath%>file/findsize.action",
	    	cache:false,
	    	async:false
    	 }).responseText;
    	var allfileext = $.ajax({
	    	type:"get",
	    	url:"<%=rootPath%>file/findfileext.action",
	    	cache:false,
	    	async:false
    	 }).responseText;
    	 var picext = $.ajax({
	    	type:"get",
	    	url:"<%=rootPath%>file/findpicext.action",
	    	cache:false,
	    	async:false
    	 }).responseText;
   	  var picexts =picext.split(",");

	$("#projectFileButTask").uploadify({
		'auto': true,
		'multi' : false,
		'swf' : '<%=rootPath%>client/js/uploadify.swf',
		 'uploader' : '<%=rootPath%>project/projectfilelist.action;jsessionid=<%=session.getId() %>',  //  上传的是临时文件 
		'queueID' : 'fileList',
		'fileObjName' : 'picFile',
		'buttonText' : '图片/文件',  
		'removeCompleted': true,
		'width' : "60",
		'height' : "16",
		'removeTimeout': 1,
		'queueSizeLimit': 3,      //允许同时上传文件数量
		'fileSizeLimit' : maxamount, 
		'fileTypeExts': allfileext, 
	    'fileTypeDesc': '图片文件',
		'onUploadSuccess' : function(file, data, response){	
			var filetype=file.type;
			var maxwrite=filetype.toLocaleUpperCase();//大写
			var minwrite=filetype.toLocaleLowerCase();//小写
			var fileListStr=$('#showFileListTask').html(); 
			var names=data.split("#");//names[0]上传之后的名字nams[1]上传之前的名字
	  	     var imgStr="";
			if(exist(picexts,maxwrite)|| exist(picexts,minwrite)){//如果是图片
			    var imgStr = '<%=rootPath%>file/readfile.action?fileName='+names[0];
			 }else{
			 	imgStr="<%=rootPath%>client/images/doc.jpg";
			 }
 	  		  var nCount=0;
	  		 $('#showFileListTask').find('span').each(function(){
	  		 	var thisId=$(this).attr("id");
	  		 	var Kid=thisId.substring("divfileTask".length,$.trim(thisId).length) ;
	  		 		if(parseInt(Kid)>=parseInt(nCount)){
	  		 			nCount=parseInt(Kid)+1;
	  		 		}
	  		 });
	  		 var fileListStr=fileListStr+'<span id="divfileTask'+nCount+'" style="float:left;">';	
	    		fileListStr+='<img src="'+imgStr+'" width="150px" height="120px"  style="margin-left: 5px;margin-top: 5px;"/><input type="hidden" name="projectFileListTask" id="filenameTask'+nCount+'" value="'+data+'" />';	
	    		fileListStr+='<a href="javascript:void(0);" onclick="delFileTask('+nCount+')"  style="background-color: black;color: white;margin-left: -25px;">删除</a>';		
	    		fileListStr+='</span>';
	  		$('#showFileListTask').html(fileListStr); 
		},
		'onUploadError': function (file, errorCode, errorMsg, errorString) {    //错误提示 
            alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
        }
	}); 
}
function delFileTask(fileId){
 	$('#filenameTask'+fileId).val(''); 
	 $('#divfileTask'+fileId).remove();
}
//删除上传附件
function delFile(fileId){ 
	 $('#filename'+fileId).val(''); 
	 $('#divfile'+fileId).remove();
	 var numberSpan=$('#showFileList').find('span'); 
	 if(numberSpan.length!=0){
	 	 $('#sharedesctextarea').val("分享了"+numberSpan.length+"个文件");
	 }else{
	 	$('#sharedesctextarea').val("你在忙什么？");
	 } 
}
//退出圈子
function deletePersonMe(projectid){
	$.dialog.confirm('确定退出圈子？再次加入需要与圈子负责人联系',function(){
			$.ajax({
				type:'post',
				url:'<%=rootPath%>project/deleteattention.action',
				data:{ 
					'projectId':projectid
				},
				success: function(msg){
				 	if(msg=="1"){
				 		$.dialog.tips('退出成功',1,'32X32/succ.png',function(){   
				 			window.location='<s:url action="projectIndex" namespace="/project" />'
				 		 });	 
				 	} else{
						$.dialog.tips('退出失败',0.5,'32X32/fail.png');	
					}
				}
			 });
	});  
}  //关注圈子 /取消关注
function addOrDeleteAttactionMe(_projectId){  
		var _att=$('#attention'+_projectId).text();  
		var _attText=$.trim(_att); 
		if(_attText=="关注圈子"||_attText=="关注圈子"){
			$.ajax({
				url: '<%=rootPath%>project/addattention.action',
				data:{'projectId':_projectId},
				type:"post",
				dataType: "json", 
			    async: false ,
			    success : function(msg){   
			    	if(msg!="0"){  
			    		window.location.reload(true);
			    	}
			    }
			});
		}else if(_attText=="取消关注"){
			 $.ajax({
					url: '<%=rootPath%>project/deleteattention.action',
					data:{'projectId':_projectId},
					type:"post",
					dataType: "json", 
				    async: false ,
				    success : function(msg){
				    	if(msg!="0"){ 
				  			window.location='<s:url action="projectIndex" namespace="/project" />'
				    	}
				    }
				});
		} 	
} 
function startOrCloseProject(projectid,_isClosed){ 
	 var textval=$('#stopprojecti'+projectid).html(); 
	 var msgshow='';  
	 if($.trim(textval)=='结束圈子'||$.trim(textval)=='结束圈子')  msgshow='确定该圈子已完成？'; 
	 else  msgshow='确认要重启该圈子？';  
	  $.dialog.confirm(msgshow,function(){
			$.ajax({
					url: '<%=rootPath%>project/startorcloseproject.action',
					data:{'irpProject.projectid':projectid,
						  'irpProject.isclosed':_isClosed},
					type:"post",
					dataType: "json", 
				    async: false ,
				    success:function(msg){ 
				    	if(msg=="1"){
				    	   window.location.reload(true);
				    	} 
				    }
				}); 
		}); 
}

//热门圈子
function hotProject(){
	$.ajax({
		type:"post",
		cache:false,
		url:"<%=rootPath%>project/hotproject.action",
		success:function(html){
			$('#hotproject').html(html);
		}
	});
} 
</script>

<div class="bg01">
<!--头部菜单-->

 <jsp:include page="../../view/include/client_head.jsp" />
 <section class="mainBox">
	<nav class="privateNav">
	</nav>
</section>

<!--头部菜单end-->

<div class="mainBox">
<!--左侧内容 -->
<!-- <div id="leftMenu" >
</div> -->

<!--左侧内容结束--> 
<!--右侧内容-->
<div class="layoutMiddle1" style="float: left;width: 835px;padding-top: 20px;padding-right: 20px;min-height:380px;height:auto;" id="rightMenu"> 

		<div class="myQz">
            <div class="myQzTitle">
                    <div class="editBar">
                     	 <%--如果他是负责人 --%>
                             <s:if test="loginUserid==irpProject.offpersonid">
                              <a  href="javascript:void(0);" 
                              	 id="startprojecti<s:property value='irpProject.projectid'/>" onclick="toupdateproject(<s:property value='irpProject.projectid'/>)" >
                              	<b>修改圈子</b></a>
                        	<a  id="deleteprojecta<s:property value='projectid'/>" onclick="delteProject(<s:property value='irpProject.projectid'/>)" href="javascript:void(0);">
                        	<b>删除圈子</b>
                        	</a> 
                              <s:if test="irpProject.isclosed==@com.tfs.irp.project.entity.IrpProject@NOT_CLOSED">
                             	   <a  style="padding: 2px 0px;margin-top: -22px;margin-left: 0px;"href="javascript:void(0);" ><b 
                             	    id="stopprojecti<s:property value='irpProject.projectid'/>" onclick="startOrCloseProject(<s:property value='irpProject.projectid'/>,<s:property value='@com.tfs.irp.project.entity.IrpProject@IS_CLOSED'/>)" >
                             	   结束圈子</b></a>
                              </s:if>
                              <s:else>
                              	<a  style="padding: 2px 0px;margin-top: -22px;margin-left: 0px;"href="javascript:void(0);"><b
                              	 id="startprojecti<s:property value='irpProject.projectid'/>" onclick="startOrCloseProject(<s:property value='irpProject.projectid'/>,<s:property value='@com.tfs.irp.project.entity.IrpProject@NOT_CLOSED'/>)" >
                              	重启圈子</b></a>
                              </s:else>
                             
                         </s:if> 
                         <%--如果他是成员 --%>
                         <s:elseif test="@com.tfs.irp.projectperson.entity.IrpProjectPerson@IS_STATE==@com.tfs.irp.projectperson.web.IrpProjectPersonAction@findIsStateByProjectIds(irpProject.projectid)">
                      				<a  style="padding: 2px 0px;" onclick="deletePersonMe( <s:property value='irpProject.projectid'/>)" 
                         				id="person <s:property value='irpProject.projectid'/>" 
                         				onmouseover="changestateValueOne( <s:property value='irpProject.projectid'/>)" 
                         				onmouseout="changestateValueTwo( <s:property value='irpProject.projectid'/>)"  
                         				href="javascript:void(0);" ><b>
                             	   正在参与</b></a></s:elseif>
                         <%--如果他是关注者 --%>
                         <s:elseif test="@com.tfs.irp.projectperson.entity.IrpProjectPerson@NOT_STATUS==@com.tfs.irp.projectperson.web.IrpProjectPersonAction@findNotStateByProjectIds(irpProject.projectid)">
                         			<a  style="padding: 2px 0px;" id="attention<s:property value='irpProject.projectid'/>"  
                           		  onmouseout="changeTextTwo( <s:property value='irpProject.projectid'/>)" 
                           		  onmouseover="changeTextOne( <s:property value='irpProject.projectid'/>)" 
                           		  onclick="addOrDeleteAttactionMe( <s:property value='irpProject.projectid'/>)"  
                           		  href="javascript:void(0);" ><b >
                           		 正在关注</b></a> 
                         
                         </s:elseif>
                         <%--不是关注者，也不是成员，圈子公开情况 --%>
                         <s:else >
                           		<a  style="padding: 2px 0px;" id="attention<s:property value='irpProject.projectid'/>"   
                           		  onclick="addOrDeleteAttactionMe( <s:property value='irpProject.projectid'/>)"  
                           		  href="javascript:void(0);" ><b>
                           		 关注圈子</b></a> 
                         </s:else>
                         </div>
                         <h1><a href="#" target="_blank" style="font-size: 25px;"> <s:property value='irpProject.prname'/></a></h1>
            </div>
            <div class="qzInfo">
            	<div style="font-size: 13px;">私密性：<s:if test="@com.tfs.irp.project.entity.IrpProject@IS_PUBLISH==irpProject.ispublish">公开</s:if>
                   <s:if test="@com.tfs.irp.project.entity.IrpProject@NOT_PUBLISH==irpProject.ispublish">私密</s:if>
                     &nbsp;&nbsp;	&nbsp;&nbsp;
                                   <%--      圈子阶段：<s:property value="@com.tfs.irp.project.web.ProjectAction@findProjectStatus(irpProject.prostatus)"/> --%></div>
                <div style="font-size: 13px;"><span class="qzDec">描述：
                                       	 	<s:property value='irpProject.prdesc'/>
                                       	 	</span></div>
            </div>
            <div class="timeInfo" style="padding-top: 10px;">
            <s:if test="irpProject.isclosed==@com.tfs.irp.project.entity.IrpProject@NOT_CLOSED">
                                        	 <p class="ending" id="dayP"></p>
                                        	 <s:if test="irpProject.isclosed==@com.tfs.irp.project.entity.IrpProject@NOT_CLOSED"> 
                                        			 <p class="ending" style="font-size: 15px;" id="dayP"></p> 
                               	       		</s:if>
                               	       		</s:if><s:else>
                               	       				 <p class="ending"style="font-size: 15px;"  >已完成</p>
			                                 </s:else>    
                                            <div class="node_trwbi" style="margin: 0px 0 20px 0;" id="starEndDate" align="right" style="font-size: 13px">  
                                            <span id="starData" class="timeLine"> 
                                                	<b>
													<s:date name="irpProject.starttime" format="yyyy-MM-dd" />
	                                        		</b></span>—<span id="endData"><b>
											    		<s:date name="irpProject.endtime" format="yyyy-MM-dd"/> 
											    	</b>
											     </span>
                                            </div>
            </div>
            <div class="clear"></div>
        </div>
		<div class="qzSelTitleBar" id="tabMenu" style="">
        	 <span class="on"   id="sharea" href="javascript:void(0);"  onclick="tabs(this)" _link="<%=rootPath %>project/link_me.action" >
                <b>动态</b>
                <span id="tabShare" style="height: 0px;">
             (<s:property value="@com.tfs.irp.projectsharetask.web.IrpProjectShareTaskAction@countShareTaskCollectByShareTaskId(irpProject.projectid)"/>)
               </span>
        	</span> |
            <span id="taska" href="javascript:void(0);"  onclick="tabs(this)" _link="<%=rootPath %>project/alltask.action">
                  <b>任务</b><span id="tabTask" style="height: 0px;">
                 (<s:property value="@com.tfs.irp.projectsharetask.web.IrpProjectShareTaskAction@countTaskCollectByShareTaskId(irpProject.projectid)"/>)
               </span>

			</span>|
            <span id="attacheda" href="javascript:void(0);"  onclick="tabs(this)" _link="<%=rootPath %>/project/allprojectattached.action">
                                      <b>附件</b>
                                          <span id="tabDocument" style="height: 0px;">
											(<s:property value="@com.tfs.irp.project.web.ProjectAction@countProjectAttached(irpProject.projectid)"/>)
										</span>
                                    </span>|
            <span id="attacheda" href="javascript:void(0);"  onclick="tabs(this)" _link="<%=rootPath %>/project/projectallperson.action">
                                  <b>成员</b>
                                  <span id="tabPerson" style="height: 0px;">
			(<s:property value="@com.tfs.irp.projectperson.web.IrpProjectPersonAction@findProjectPersonCount(irpProject.projectid)" default="0"/>)
					</span>
                          </span>|
            <span  id="attacheda" href="javascript:void(0);"  onclick="tabs(this)" _link="<%=rootPath %>/project/projectallpersonstate.action">
                           <b>关注者</b>
                           <span id="tabPersonState" style="height: 0px;">
						(<s:property value="@com.tfs.irp.projectperson.web.IrpProjectPersonAction@findProjectPersonAttCount(irpProject.projectid)" default="0"/>)
						</span>
                   </span>
        </div>
        
<!--z-->
<s:if test="irpProject.isclosed==@com.tfs.irp.project.entity.IrpProject@NOT_CLOSED">
  <s:if test="@com.tfs.irp.projectperson.web.IrpProjectPersonAction@findIsStateByProjectIds(irpProject.projectid)==@com.tfs.irp.projectperson.entity.IrpProjectPerson@IS_STATE">

 <div class="area20"></div>
        <div class="qzNews">
        	<div class="maintext" id="publishShareT" style="width: auto;">
        	<textarea class="inputTitle" style="font-size: 16px;width:800px;color: #cecece;margin-bottom: 5px;" name="irpProjectShareTask.sharedesc" onkeyup="changeTextAreaText(this.value)" onblur="blurTextAreaText()" onfocus="focusTextAreaText()" id="sharedesctextarea" class="write_wtcpsw" defaultvalue="你在忙什么？"></textarea>
            <div class="textCount"id="topicFont_01" style="float: right;" >您还可以输入<font id="topicFontCount_01" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;"><s:property value="maxAmount" /></font>字</div>
             <div class="textCount" style="display: none;float: right;"id="topicFont_02" >您已超出<font id="topicFontCount_02" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color:red;"><s:property value="maxAmount" /></font>字</div>
             <div class="action_cpsw" >
            <ul class="list_acpsw" style="float: left; padding-left: 0px;">
                <li class="ament_lacpsw">
                <a href="javascript:void(0);"  title=""><input type="file" name="" id="projectFileBut" /></a></li> 
            </ul>  
        </div>
        
        <div class="sub" style="float: left;margin-top: 20px;margin-bottom: 20pxpadding-right: 0px;padding-left: 590px;padding-top: 0px;" >
            <input class="in"  type="button" id="sharebutton" style="width: 120px;" value="分&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;享"/></div>
           
       </div>
             <div class="clear"></div>
             <div id="showFileList" ></div>  
        </div>
   <%--task --%>                                
<!--z-->
  <!--2--> 
  
 <%--task --%>  
 <div  id="addTaskDiv" class="publishsharewrap line_publishsharewrap"  style="border: 0px solid #e9e9e9;overflow:hidden;background: white;width: 100%;">
		<div >
			<input class="inputTitle" id="TaskInput" class="txt_ptaskt" style="width: 800px;color: #cecece;" onfocus="focusTaskInput()" onblur="blurTaskInput()" onkeyup="blurTaskInput()"  value="要做什么事？" type="text" />  
		
		 <span class="txt_lptaskt" style="color:red;">*</span> </div> 
		<div > 
			<input class="inputTitle" id="offPersonName" style="width: 800px;color: #a2a0a0;" class="txt_ptaskt" readonly="readonly" onclick="checkTaskOffpersonId()"  placeholder="负责人"type="text"/> 
			<input id="taskPersonId" type="hidden" />  <span class="txt_lptaskt" style="color:red;">*</span> 
	    </div>  
	     
	    <div >
	    	<input name="taskPersonIds"  id="taskPersonIds" type="hidden"/> 
			<input class="inputTitle" style="width: 800px;color: #a2a0a0;" class="txt_ptaskt" name="taskPersonNames" readonly="readonly"  placeholder="还有谁参与？"  id="taskPersonNames"  onclick="toCheckTaskPerson(<s:property value='irpProject.projectid'/>)" type="text" /> 
			 <span class="txt_lptaskt" style="color:red;">*</span> 
		</div> 
	    <div >
        	<textarea class="inputTitle" style="width: 800px;margin-bottom: 5px;height: 60px;color: #cecece;font-size: 16px;border-botom:5px;"onfocus="changeTaskTextArea()" onblur="changeTaskTextArea()" onkeyup="checkTaskDescAmount(this.value)" id="taskDescTextArea" class="txt_ptaskt">更详细的说明</textarea> 
	      <span class="txt_lptaskt" style="color:red;padding-top: 50px;">*</span> 
		</div> 
		<div>
			<div class="count_cpsw" style="text-align: left;margin-bottom: 10px;">
	            <input type="hidden" value="0" id="inputcounttask"/>
	            <input type="hidden" id="maxinputtask" value="maxAmount"/>
	            <span id="topicFont_011"  class="num_ccpsw" style="float: right;">
	          	还可以输入
	          <label id="topicFontCount_011" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color: #e69521;">
	          	<s:property value="maxAmount" />
	          </label>个字
	          </span>
			  <span id="topicFont_021"  class="num_ccpsw" style="display: none;float: right;">
				  已超出
			  <label id="topicFontCount_021" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color:red;"></label>个字
			  </span>
	             
	        </div> 
		
		</div> 
         <div >
         	 <ul class="list_acpsw">
                <li class="ament_lacpsw">
                <a href="javascript:void(0);" title="">
                	<input type="file" name="" id="projectFileButTask" />
                </a>
                </li> 
                 <span class="end_ptask date_lptaskt clearfix ">
                 <input title="何时开始" name="irpProjectShareTask.starttime" type="text"   id="taskStart" class="easyui-datebox" editable="false" validType="StartTime">&nbsp;&nbsp; 
		   	     —— 
		         <input title="何时完成" name="irpProjectShareTask.endtime" type="text" id="taskEnd" class="easyui-datebox" editable="false" validType="EndTime">
		   	    </span> 
            </ul> 
         
           </div>
           
	   
	 <!--    <div class="action_cpsw " >
              <a  id="addtaskbut" class="pub_aptaskt disable_pub_aptaskt" title="发布" href="javascript:void(0);"><b>发布</i></b></a>
	    </div>  -->

	    
	     <div class="sub"  style="float: right;margin-top: 25px;padding-right: 30px;">
            <input class="in" id="addtaskbut"  type="button" style="color: white;font-size: 16px;width: 120px;" value="发&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;布"/></div>
            <div class="clear"></div>
           
	    <div id="showFileListTask" class=" action_cpsw action_ptaskt" style="float: left;padding-top: 20px;width: 600px;"></div>
 </div> 
  <%--task --%>
 </s:if>
 </s:if>
  <!--2-->   
  <div id="showlinkprojectdiv"></div>
  </div>
  <div style="width: 325px;float: left;margin-left:20px">
  <div class="lefttitle"><h4><a href="javascript:void(0)">最热圈子</a></h4></div>
     <ul id="hotproject">
     </ul>
  
  
  </div>
  
  
</div>
<!--right E-->
  </div> 
<!--leftcontent end-->
<!--右侧内容结束-->
<!--尾部信息--> 
<jsp:include page="../include/client_foot.jsp" />
<!--尾部信息end--></body>
</html>