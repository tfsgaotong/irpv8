<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@ page import="com.tfs.irp.util.LoginUtil"%>
<%@ page import="com.tfs.irp.user.entity.IrpUser"%>

<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	IrpUser loginUser = LoginUtil.getLoginUser();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="irpDocument.doctitle"/> </title>  
<style type="text/css">
div,form,img,ul,ol,li,dl,dt,dd {margin: 0; padding: 0; border: 0;}
h1,h2,h3,h4,h5,h6 { margin:0; padding:0;}
table,td,tr,th{font-size:12px;}
/* 星级评分 */
.shop-rating {
height: 25px;
overflow: hidden;
zoom: 1;
padding: 2px 0px;
position: relative;
z-index: 999;
}
.shop-rating span {
height: 23px;
display: block;
line-height: 23px;
float: left;
}
.shop-rating span.title {
width: 125px;
text-align: right;
margin-right: 5px;
}
.shop-rating ul {
float: left;
margin:0;padding:0
}
.shop-rating .result {
margin-left: 20px;
padding-top: 2px;
}
.shop-rating .result span {
color: #ff6d02;
}
.shop-rating .result em {
color: #f60;
font-family: arial;
font-weight: bold;
}
.shop-rating .result strong {
color: #666666;
font-weight: normal;
}
.rating-level,
.rating-level a {
	background: url( <%=rootPath%>client/images/star_v2.png) no-repeat scroll 1000px 1000px;
}
.rating-level {
background-position: 0px 0px;
width: 120px;
height: 23px;
position: relative;
z-index: 1000;
}
.rating-level li {
display: inline;
}
.rating-level a {
line-height: 23px;
height: 23px;
position: absolute;
top: 0px;
left: 0px;
text-indent: -999em;
*zoom: 1;
outline: none;
}
.rating-level a.one-star {
width: 20%;
z-index: 6;
}
.rating-level a.two-stars {
width: 40%;
z-index: 5;
}
.rating-level a.three-stars {
width: 60%;
z-index: 4;
}
.rating-level a.four-stars {
width: 80%;
z-index: 3;
}
.rating-level a.five-stars {
width: 100%;
z-index: 2;
}
.rating-level .current-rating,.rating-level a:hover{background-position:0 -28px;}
.rating-level a.one-star:hover,.rating-level a.two-stars:hover,.rating-level a.one-star.current-rating,.rating-level a.two-stars.current-rating{background-position:0 -116px;}
.rating-level .three-stars .current-rating,.rating-level .four-stars .current-rating,.rating-level .five-stars .current-rating{background-position:0 -28px;}

.ellipsis{
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap; 
}

.zj_wBox{
	margin: 0 auto;
	width: 1000px;
}

#page_break {

}
#page_break .collapse {
	display: none;
}
#page_break .num {
	padding: 10px 0;
	text-align: center;
}
#page_break .num li{
	display: inline;
	margin: 0 2px;
	padding: 3px 5px;
	border: 1px solid #72BBE6;
	background-color: #fff;
	
	color: #72BBE6;
	text-align: center;
	cursor: pointer;
	font-family: Arial;
	font-size: 12px;
	overflow: hidden;
}
#page_break .num li.on{
	background-color: #72BBE6;
	color: #fff;
	font-weight: bold;
} 
.documenttxt td p{
	text-indent:0;
}
</style> 
<!-- 

<link href="<%=rootPath %>client/images/css.css" rel="stylesheet" type="text/css" />
 -->
<link href="<%=rootPath%>client/css/oacss.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath%>client/js/skins/iblue.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>client/css/easyui.css" />
<link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css"/>
<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=rootPath%>/client/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/client/js/easyui-lang-zh_CN.js"></script> 
<script type="text/javascript" src="<%=rootPath%>/editor/simple_ckeditor/ckeditor.js"></script>

<script type="text/javascript">
//转发某个文档给某些人
var _focusperson=null;
$(function(){
	//判断当前用户是否评论过知识，若未评论，则加载页面
	var isScore=<s:property value='isScore'/>;
	if(!isScore){
		documentscore(<s:property value="irpDocument.docid"/>);//查询他的分数
	}
   	//相关知识
	xiangguandocument(<s:property value="irpDocument.docid"/>); 
	//显示所有评论 
	findmydocrecommend(<s:property value='irpDocument.docid'/>,<s:property value='irpDocument.cruserid'/>);
	//相关案例
	jinghuadocument(<s:property value='irpDocument.channelid'/>); 
	personcollectiondocument(<s:property value='irpDocument.cruserid'/>);
	initRES();
	
	$('#page_break .num li:first').addClass('on');
	$('#page_break .num a').click(function(){
		var _text= $.trim($(this).text()) ;
		var _num=parseInt(0);
		 if(_text=="上一页"){
			 //获取当前选中的li ,然后将它小于1的点击一下，如果当前选中的是1就不执行
			 $('#page_break').find('li').each(function(){ 
					if(this.className=="on"){
						var _id=this.id;
						if(_id==1)return;
						_num=parseInt(_id)-1;
					}	 
		      });  
		 }else if(_text=="下一页"){
			 var len=$('#page_break').find('li').length;
		    $('#page_break').find('li').each(function(){ 
		    	if(this.className=="on"){
					var _id=this.id;
					if(_id==len)return;
					 _num=parseInt(_id)+1;
				}	 
	        });  
		 }
		 $("#page_break").find('#'+_num).click();return ;
	});
	$('#page_break .num li').click(function(){
		//隐藏所有页内容
		$("#page_break div[id='page_1']").hide(); 
		//显示当前页内容。 
		  var li=$('#page_break').find('li');
	      var len= li.length ;
		if ($(this).hasClass('on')) { 
			$('#page_break #page_' + $(this).text()).show();
		} else {
			$('#page_break .num li').removeClass('on');
			$(this).addClass('on');
			$('#page_break #page_' + $(this).text()).fadeIn('normal');
			for(i=1;i<=len;i++){
				if(parseInt($(this).text())==i){}else{
					$("#page_break div[id='page_"+i+"']").hide(); 
				}
			}
		}
	});
});

function initRES(){
	CKEDITOR.replace('editor');
}
 
//查询知识的评分
function documentscore(_docid){
	var str=$.ajax({
		type:'post',
		url:'<%=rootPath%>site/finddocumentscore.action',
		data:{'docid':_docid},
		dataType: "json",
		async: false,
	    cache: false 
	}).responseText;   
    $('#documentscore').html(str);   
}

/* For TEST */
function teststars(){
	var score= document.getElementById("stars2-input").value ;
	var docid=<s:property value='irpDocument.docid'/>;
	//添加一条记录返回将评分这个div隐藏
	if(parseInt(score)>0){
		$.dialog.confirm('您确定对知识进行评分吗？',function(){
			$.ajax({
				type:'post',
				url:'<%=rootPath%>site/addscore.action',
				data:{
					'docid': docid,
					'score':score	
				},
				success:function(msg){ 
					if(msg=="1"){   
						documentscore(docid);
					} 
				}
			}); 
		 },function(){}); 
	}else{
		$.dialog.alert('请选择评分分数...',function(){});
	}
}

//对文档进行关注以及取消关注
function addfocusdoclink(_documentid){  
	var tValue=$('#focus').text(); 
	if($.trim(tValue)=="收藏"){ 
			$.ajax({
				type:'post',
				url:'<%=rootPath%>site/addfocusdoclink.action',
				data:{'documentid': _documentid},
				success:function(msg){ 
					if(msg=="1"){   
						$.dialog.tips('收藏成功',1,'32X32/succ.png');
						$('#doccollectcountlabel').text(parseInt($('#doccollectcountlabel').text())+1); 	
					   	$('#focus').html('取消收藏');	
				    	//隐藏div
				       	$('#divcoll').css({ display:"none"});
				    	$('#collectionCount').text((parseInt($('#collectionCount').text())+1));
					} 
				}
			});  
	}else{
		$.dialog.confirm('您确定要取消收藏吗？',function(){
			$.ajax({
				type:'post',
				url:'<%=rootPath%>site/deletefocusdoclink.action',
				data:{'documentid': _documentid},
				success:function(msg){
					if(msg=="1"){  
					    $('#doccollectcountlabel').text(parseInt($('#doccollectcountlabel').text())-1); 	
						$('#focus').html('收藏');
					    $('#divcoll').css({ display:"block"});
					    $('#collectionCount').text((parseInt($('#collectionCount').text())-1));
					} 
				}
			}); 
		 },function(){}); 
	} 
}

//回复的回复操作
function appendReplayUsername(replayUserId,_replyUsername){  
	$('#replayUserId').val(replayUserId); 
} 
 
//添加评论
function adddocrecommend(_docid,_cruserid){
	$.dialog.confirm('您确定要评论这个知识吗？',function(){
		var replayUserId=$('#replayUserId').val();  
      	var obj=$('#editor');
     	var mydes= CKEDITOR.instances.editor.getData();
  		if($.trim(mydes).length>0){
			$.ajax({
				type:'post',
				url:'<%=rootPath%>site/addrecommend.action',
				data:{'docid':_docid,
					 'recommend':$.trim(mydes),
					 'replayUserId':replayUserId
					},
				success:function(msg){ 
						findmydocrecommend(_docid,_cruserid);//调用，刷新
						CKEDITOR.instances.editor.setData('');
					    $('#recommendlabel').text(parseInt($('#recommendlabel').text())+1); 	
			   	}
			});
		}else{
			$.dialog.alert('评论不能为空',function(){});
		} 
	},function(){}); 
}

//查看这个文档的所有评论
function findmydocrecommend(_docid,_cruserid){
	//将当前文档的评论显示出来   
	  var str=$.ajax({
			type:'post',
			url:'<%=rootPath%>site/findmydocrecommend.action',
			data:{'docid':_docid,'createuserid':_cruserid},
			dataType: "json",
			async: false,
		    cache: false 
			}).responseText;   
	    $('#speek').html(str);  
} 
 
// 相关知识
function xiangguandocument(_docid){
	var str=$.ajax({
			type:'post',
			url:'<%=rootPath%>site/caredocxiangguandocument.action',
			dataType: "json",
			data:{'docid':_docid},
			async: false,
	   		cache: false  
			}).responseText;
			$('#xiangguandocument').html(str);
}
 //精华知识
function jinghuadocument(_channelid){
	var str=$.ajax({
			type:'post',
			url:'<%=rootPath%>site/alljianghuadocument.action',
			data :{'id': _channelid},
			async: false,
	   		cache: false  
			}).responseText;
			$('#jianghuadocument').html(str);
}

/*构建私信框*/
function messageContentView(_messageuser,_messageid,_docid){
   	//构建页面结果 
   	var result = $.ajax({
   		url: '<%=rootPath%>microblog/messageContentPage.action',
   		type:"post",
   		dataType: "json",
   	    data: {
   	    	messageuser:_messageuser,
   	    	messageid:_messageid,
   	    	docid:_docid
   	    },
   	    async: false
   	}).responseText; 
   	$.dialog({
   		title:'发私信',
   		max:false,
   		min:false,
   		lock:true,
   		resize: false,
   		width:'450px',
   		height:'180px',
   		content:result,
   		cancelVal:'关闭',
   		okVal:'发送',
   		cancel:function(){ 
   		},
   		ok:function(){
   			var informdescs = $("#messageInfo").val();
			if($.trim(informdescs).length>$('#messagecount').val()){
		    	return false;
		    }else{
	   			$('#messageContentForm').form('submit',{
	   				url:'<%=rootPath%>microblog/sendMessageContentToUser.action',
	   				cache:false,
	   				success:function(msg){
						if(msg==0){
							$.dialog.tips('发送成功',1,'32X32/succ.png');		
						}else if(msg==1){	
							$.dialog.tips('部分发送成功,另一部分由于用户的设置未能发送成功',2,'32X32/hits.png');	
						}else{
							$.dialog.tips('发送失败,由于用户的设置',2,'32X32/fail.png');	
						}	
	   				}
	   			});
		    }
   		}
   	}); 
}

function personcollectiondocument(_cruserid){   
		var str=$.ajax({
			type:'post',
			url:'<%=rootPath%>site/person_collection_document.action',
			data : {'personid':_cruserid,
					'docid':<s:property value='irpDocument.docid'/>
					},
			async: false,
	   		cache: false  
			}).responseText;
			$('#personcollectiondocument').html(str);
}
//删除文档
function deleteDocument(_docid){
	$.dialog.confirm('您确定要删除这个知识吗？',function(){
			$.ajax({
					type:'post',
					url:'<%=rootPath%>site/clientdeletedocument.action',
					data:{'docid':_docid},
					success: function(msg){
						if(msg=="1"){ //删除成功 
								  $.dialog.tips('删除知识成功',0.5,'32X32/succ.png',function(){ 
									 window.opener.location.reload(true);
					 				 window.close();
								});  
						} 
					}
	    	 });
		},function(){}); 
}
//加精
function documentClassisl(_docid){
	var tValue=$.trim($('#classlable').text());  
	if(tValue=="加精"){
		jiajing(_docid);
	}else if(tValue=="取消加精"){
		 $.dialog.confirm('您确定要取消加精这个知识吗？',function(){
			$.ajax({
				type:'post',
				url:'<%=rootPath%>site/deletedocumentclassicl.action',
				data:{'informid': _docid},
				success:function(msg){
					if(msg!="0"){
						var loginUserId=<s:property value='loginUserId'/>; 
						var _id=loginUserId+"_"+_docid;
						$('#'+_id).hide();
						$('#classlable').html('加精');
						$('#essenceCount').text((parseInt($('#essenceCount').text())-1));
					}
				}
			}); 
		},function(){}); 
	} 
}
//加精
function jiajing(_docid){
	var str=$.ajax({
		url: '<%=rootPath%>set/addclassial.action', 
		data:{'docid': _docid},
		type:"post", 
	    async: false,
	    cache: false
	}).responseText; 
	
	$.dialog({
		 	title:'加精',
			max:false,
			min:false,
			lock:true,
			resize: false, 
			init : function(){
			},
			content:str,
			cancelVal:'关闭',
			okVal:'确定',
			cancel:function(){ 
			},
			ok:function(){  
				var informkeykind=$("input[name=informkeykind]:checked").val();
				if(informkeykind==undefined){
					$.dialog.tips("请选择加精理由",1.5,"alert.gif");
					return false;
				}	
				var informdescs = $("#mydes").val();
				if($.trim(informdescs).length>$('#informdesccount').val()){
			    	return false;
			    }else{
			    	 var mydes=$('#mydes').val(); 
			    	 var radiotext= $('input:radio[name="informkeykind"]:checked').attr("data");
					 $.ajax({
	                    type: "POST",
	                    dataType: "JSON",
	                    url: '<%=rootPath %>site/adddocumentclassicl.action',
	                    data: $('#addclassialfrm').serialize(),
	                    success: function (result) {
	                    	 $('#classlable').html('取消加精');
	                    	 $('#essenceCount').text((parseInt($('#essenceCount').text())+1));
	                    	 var loginUserId=<s:property value='loginUserId'/>;
	                    	 var _docid=<s:property value='irpDocument.docid'/>;
	                    	 var str=""; 
	                    	 str+="<ul id='"+loginUserId+"_"+_docid+"' ><li>"; 
	                    	 str+="<a target='_blank' href='<%=rootPath%>site/client_to_index_person.action?personid="+loginUserId+"'><strong>";
	                    	 str+='<s:property value="@com.tfs.irp.projectperson.web.IrpProjectPersonAction@findUserNameByUserId(loginUserId)"/>';;
	                    	 str+="</strong></a>";
	                    	 str+="加精理由："+mydes+","+radiotext;
	                    	 str+="</li></ul>";
	               		  $('#jiajingcommend').append(str);
	                    }
	                });
			    }	
			}
	});  
}
//选择人或者组织
function checkuserorgroup(){
	var str=$.ajax({
		url: '<%=rootPath%>site/to_check_group_or_user.action', 
		type:"post", 
	    async: false,
	    cache: false
	}).responseText;   
	 $.dialog({
		 	title:'推荐', 
			lock:true,
			resize: false, 
			init : function(){
				$('#tab').tabs({ 
					  border:false,   
  					  onSelect:function(title,index){ 
  					  		tabs(); 
  				      }   
				});
			},
			content:str,
			cancelVal:'关闭',
			okVal:'确定',
			cancel:function(){ 
			},
			ok:function(){  
	    		var userIds= $('#transdocument').find('#userIds').val();
	         	var userNames= $('#transdocument').find('#userNames').val();
	         	
	  	 		$('#transdocument').find('#userdiv').html(userNames);
	  	 		$('#transdocument').find('#userIds').val(userIds);
	    	    var groupIds=$('#transdocument').find('#groupIds').val();
	  	 	    var groupNames= $('#transdocument').find('#groupNames').val();
	  	 	    $('#transdocument').find('#groupdiv').html(groupNames);
	  	 	    $('#transdocument').find('#groupIds').val(groupIds);
			}
	});  
}

function transdocument(_docid){ 
//弹出框，显示所有人。。。然后选择 
	var result = $.ajax({
		url: '<%=rootPath%>site/findallmicroblogforcusjson.action',
		type:"post",
		data:{docid: _docid},
		dataType: "json", 
	    async: false 
	}).responseText; 
	$.dialog({
		title:'推荐',
		max:false,
		min:false,
		lock:true, 
		resize: false,
		width:'500px',
		height:'150px',
		content:result,
		cancelVal:'关闭',
		okVal:'推荐',
		cancel:function(){ 
		},
		ok:function(){ 
			var informdescs = $("#informdescform").val();
			if($.trim(informdescs).length>$('#informdesccount').val()){
		    	return false;
		    }else{
				$('#transdocument').find('#docid').val(_docid);
				$('#transdocument').form('submit',{
					url:'<%=rootPath%>site/addtransmite.action',
					success:function(callback){ 
						var num=parseInt(callback) ;
						if(num>0){
							$.dialog.tips('推荐成功',1,'32X32/succ.png',function(){ 
							});						
						}else{
							$.dialog.tips('推荐失败',1,'32X32/fail.png');	
						}
					},
					error:function(){
						$.dialog.tips('推荐失败',1,'32X32/fail.png');
					}
				});
		    }
		}
	}); 
} 
function showDoc(_channelid){
	//首先判断他有没有查询他里面的文章列表权限
 $.ajax({
		url: '<%=rootPath%>site/clientrightdoclist.action',
		data:{'id':_channelid},
		type:'post', 
	    async: false ,
	    success:function(mes){
	    	if(mes=="success"){
    			 window.open('<%=rootPath %>site/clientshowchanneldoc.action?id='+_channelid,'_parent');
    		 }else{
    			 $.messager.alert("提示信息","您没有查看知识权限","warning");
    		 }
	    }
	}) ;
} 
//修改文章
function updateDocument(_docid){
	window.open('<%=rootPath%>site/to_update_subject.action?docid='+_docid);
}
//搜索名字
function searchInfo1(searchInfo){  
		searchtype = 5;  
		if(searchInfo.length>38){
			searchInfo = searchInfo.substring(0,37);	
		}
		var eacapeInfo = encodeURI(searchInfo);
	    window.open('<%=rootPath%>solr/searchcontentofsolr.action?searchInfo='+eacapeInfo+'&searchtype='+searchtype); 
}
//顶
function ding(_docid){
	var tValue=$.trim($('#ding').text());
	var cValue=$.trim($('#cai').text());
	if( cValue!="已踩"){
		if(tValue=="顶"){
			$.ajax({
					type:'post',
					url:'<%=rootPath%>site/dingdocument.action',
					data:{'docid':_docid},
					dataType:'json',
					async:false,
					cache:false,
					success:function(msg){ 
					if(msg=="1"){
							$.dialog.tips('已顶成功',1,'32X32/succ.png');
							$('#dinglabel').text(parseInt($.trim($('#dinglabel').text()))+parseInt(1));//dinglabel
							$('#ding').html('已顶'); 
							$('#dings').show();
							$('#dinga').hide();
						} 
					}  	
			 });
		}else{
			$.dialog.alert("已顶",function(){});
		} 
	}else{
		$.dialog.alert("已踩",function(){});
	}
}
//踩
function cai(_docid){
	var cValue=$.trim($('#ding').text());
	var tValue=$.trim($('#cai').text());
	if( cValue!="已顶"){
			if(tValue=="踩"){
				$.ajax({
						type:'post',
						url:'<%=rootPath%>site/caidocument.action',
						data:{'docid':_docid},
						dataType:'json',
						async:false,
						cache:false,
						success:function(msg){ 
							if(msg=="1"){
								$.dialog.tips('已踩成功',1,'32X32/succ.png');
							$('#cailabel').text(parseInt($.trim($('#cailabel').text()))+parseInt(1));
								$('#cai').html('已踩'); 
							} 
						} 
				 });
			}else{
				$.dialog.alert("已踩",function(){});
			} 	
	}else{
		$.dialog.alert("已顶",function(){});
	}
}
//举报
function clientjubaodocument(_docid){
	 var loadPage=$.ajax({
					url: '<%=rootPath%>microblog/documentinformrame.action', 
					data:{
						microblogid:_docid
					},
					type:"post", 
				    async: false,
				    cache: false
				}).responseText; 
				$.dialog({
				title:'举报',
				max:false,
				min:false,
				lock:true,
				width:'350px',
				resize: false,
				content:loadPage,
				okVal:'举报',
				ok:function(){
					var informkeykind=$("input[name=informkeykind]:checked").val();
					if(informkeykind==undefined){
						$.dialog.tips("请选择举报类型",1.5,"alert.gif");
						return false;
					}	
					var informdescs = $("#informdescform").val();
					 if($.trim(informdescs).length>$('#informdesccount').val()){
					    	return false;
					    }else{
					    	$('#informform').form('submit',{
									url:'<%=rootPath%>microblog/savedocuentinformdesc.action',
									cache:false,
									success:function(msg){
										if(msg==1){
											$.dialog.tips('举报成功',1,'32X32/succ.png');					
										}else{	
											$.dialog.tips('举报失败',1,'32X32/fail.png');	
										}	
									}
								});	
					    
					    }
				},
				cancelVal:"关闭",
				cancel:function(){
					
				}
			}); 
}

function showAllData(){
	var _show=$.trim($('#show').html());
	if(_show=="查看全文"){
		$('#allDoc').show();
		$('#partOfDoc').hide();
		$('#show').html('取消查看全文');
	} 
}
/**
 * 引用到专题
 */
 var subdialong ;
function catchknowto(){
	var idi = <s:property value="irpDocument.docid" />;
	var contentresult = $.ajax({
		url:"<%=rootPath%>site/catchknowto.action?irpDocument.docid="+<s:property value='irpDocument.docid'/>,
		async: false,
	    cache: false
	}).responseText;
	subdialong = $.dialog({
		id:'subjectdia',
		title:'我的专题',
		max:false,
		min:false,
		lock:true,
		width:'350px',
		resize: false,
		content:contentresult,
		okVal:'引用',
		ok:function(){
			$('#addsubform').form('submit',{
				url:"<%=rootPath%>site/addqiyesub.action?irpDocument.docid="+<s:property value='irpDocument.docid' />,
				data:{"irpDocument.docid":idi},
				async: false,
			    cache: false,
			    success:function(data){
			    	if(data==1){
			    		$.dialog.tips('引用成功',1,'32X32/succ.png');		
			    	}else{
			    		$.dialog.tips('未找到新的引用',1,'32X32/fail.png');	
			    	}
			    }
			});
		},
		cancelVal:"取消",
		cancel:function(){
			
		}
	});
}
/**
* 链接到词条概览
*/
var _idcounts = 0;
function linkTerms(_tname,_this){
	var disstr = "block";
	var kids = "";
	if(_this.id=="key_words"){
		kids = "key_words"+_idcounts;
	}else{
		kids = _this.id;
		var fkidsel = "#"+kids+"div";
		if($(fkidsel).css("display")=="none"){
			$(fkidsel).css("display","block");
			disstr = "block";
			return false;
		}else{
		   
			$(fkidsel).css("display","none");
			
			disstr = "none";
			return false;
		}
	}
	_this.id=kids;
	var jleftpx = parseInt(_this.offsetLeft)-90;
	var kidssel = "#"+kids;
	$(kidssel).prepend("<div onclick=\"\" id=\""+kids+"div\" class=\"jinzhao2\" style=\"height:35px;background-color:#F5F5F5;width:350px;position:absolute;line-height:35px;margin:-65px 0 0 "+jleftpx+"px;-moz-border-radius: 15px;-webkit-border-radius: 15px;border-radius:10px;display:"+disstr+";\">"
	+"<a href=\"javascript:void(0)\" style=\"margin: 0 0 0 -15px;\" onclick=\"linkCJ('cijie','"+_tname+"')\">词解</a>&nbsp;|&nbsp;"
	+"<a href=\"javascript:void(0)\" style=\"margin: 0 0 0 5px;\" onclick=\"linkCJ('hotwords','"+_tname+"')\">相关热词</a>&nbsp;|&nbsp;"
	+"<a href=\"javascript:void(0)\" style=\"margin: 0 0 0 5px;\" onclick=\"linkCJ('zixun','"+_tname+"')\">相关资讯</a>&nbsp;|&nbsp;"
	+"<a href=\"javascript:void(0)\" style=\"margin: 0 0 0 5px;\" onclick=\"linkCJ('author','"+_tname+"')\">相关作者</a>&nbsp;|&nbsp;"
	+"<a href=\"javascript:void(0)\" style=\"margin: 0 0 0 5px;\" onclick=\"linkCJ('area','"+_tname+"')\">行业地域</a>"
	+"<span class=\"b222\"></span></div>");
	
	_idcounts = _idcounts+1;
	
}
/**
* 链接到词条概览
*/
function linkCJ(_idstr,_tname){
	var result = $.ajax({
		url:"<%=rootPath%>site/quotehotwordssearch.action",
		type:'post',
		data:{
			shotstr:_idstr,
			hotdocword:_tname
		},
		cache:false,
		async:false
	}).responseText;
	$.dialog({
  		content:result,
  		width:600,
  		height:400,
  		title:'['+_tname+']',
  		min:false,
  		max:false,
	    lock:true,
	    padding: 0
  	});
}

function tabConn(tab){
	var conn = $('#dtzw');
	var read = $('#dtyd');
	var tabId = $(tab).attr('id');
	if(tabId==read.attr('id')){
		conn.attr('class','dtzw1');
		read.attr('class','dtyd');
		$('#partOfDoc').hide();
		$('#DocReadLog').show();
	}else if(tabId==conn.attr('id')){
		conn.attr('class','dtzw');
		read.attr('class','dtyd1');
		$('#partOfDoc').show();
		$('#DocReadLog').hide();
	}
}
/**
* 查看历史版本
*/
function searchDocVersion(_docid){

	window.open("<%=rootPath%>site/linkdocversionhistory.action?docid="+_docid+"&pageNum=1");

}

//订阅知识
function addSubscribe(_documentid){
	var sHtml = $('#subscribe').html();
	if(sHtml=='订阅'){
		$.ajax({
			type:'post',
			url:'<%=rootPath%>subscribe/add_subscribe_doc.action',
			data:{'objId': _documentid},
			success:function(msg){ 
				if(msg=="1"){   
					$.dialog.tips('订阅成功',1,'32X32/succ.png',function(){
						$('#subscribe').html('取消订阅');
					});
				} 
			}
		});
	}else if(sHtml=='取消订阅'){
		$.ajax({
			type:'post',
			url:'<%=rootPath%>subscribe/del_subscribe_doc.action',
			data:{'objId': _documentid},
			success:function(msg){ 
				if(msg=="1"){   
					$.dialog.tips('取消订阅成功',1,'32X32/succ.png',function(){
						$('#subscribe').html('订阅');
					});
				} 
			}
		});
	}
}

</script>
</head>
<body> 

 <jsp:include page="../../view/include/client_head.jsp" />
		   <section class="mainBox">
			<nav class="privateNav">
			</nav>
			</section>
<div class="area1"></div>
<div class="zj_wBox">
	<h1 class="zj_pageMark">
		<div style="  width: 900px; overflow: hidden; text-overflow:ellipsis; white-space: nowrap; padding-left: 10px;">
			您当前的位置:<a href="<%=rootPath %>site/showallpublicdoc.action">知识库</a>
			&nbsp;&gt;&nbsp;
			<a href="javascript:void(0);" onclick="showDoc(<s:property value='irpDocument.channelid'/>)"><s:property value="findChnlName(irpDocument.channelid)"/></a>
			&nbsp;&gt;&nbsp;
			<s:property value="irpDocument.doctitle"/> 
    	</div>
   	</h1>
		<div class="zj_xl_txt">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="olbg7" height="300">
				<tr>
					<td valign="top" class="olbg8">
						<!-- 开始打印部分 -->
						<div id="doprint">
							<table width="874" border="0" cellspacing="0" cellpadding="0"
								class="box doctitle boxcenter dttitle ">
								<tr>
									<td width="874" align="center"
										class="box doctitle boxcenter dttitle "><s:property
											value="irpDocument.doctitle " /></td>
								</tr>
							</table>
							<div class="date">
								<center>
									发布时间：
									<s:date name="%{irpDocument.docpubtime}"
										format="yyyy-MM-dd HH:mm" />
									<%--
		            &nbsp;&nbsp;到期时间： 
		             --%>
									<s:date name="%{irpDocument.docvalid}"
										format="yyyy-MM-dd HH:mm" />
								</center>
							</div>
							<div class="intro">
								<strong>核心提示：</strong>
								<s:property value="irpDocument.docabstract" />
							</div>
							<table width="1000" border="0" cellspacing="0" cellpadding="0"
								class="dtlab">
								<tr>
									<td width="66" height="57">&nbsp;</td>
									<td width="519" valign="top" class="gray12"
										style="padding-top:10px;"><strong>创建者</strong>：
										 <a target="_blank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='irpDocument.cruserid'/>"><s:property value="irpDocument.cruser" /></a> <%--如果创建者等于登陆者，就不可以发送私信 --%> 
										 <s:if test="loginUserId != irpDocument.cruserid">
											<a href="javascript:void(0);" onclick="messageContentView('<s:property value="irpDocument.cruser"/>',<s:property value="irpDocument.cruserid"/>,<s:property value='irpDocument.docid'/>)"><img
												src="<%=rootPath %>client/images/xl_r3_c10.gif" width="25"
												height="11" style="margin-bottom:-2px; margin-left:10px;" /></a>
										</s:if>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <%--<strong>来源</strong>：<s:property value="irpDocument.docsourcename"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
										<strong>评论人数</strong>：<label id="recommendlabel"><s:property
												value="irpDocument.recommendcounts" /> </label> <s:if
											test="irpDocument.docoutupid!=null && irpDocument.docoutupid!=0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	              		<strong><a href="javascript:void(0);"
												onclick="window.open('<%=rootPath %>site/client_documentinfo.action?refrechstatus=1&docid=<s:property value="irpDocument.docoutupid"/>');">原稿件</a>
											</strong>
										</s:if> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<%--<strong><a href="javascript:void(0);" onclick="showAllData()" id="show">查看全文</a> </strong>--%>
									</td>
									<td width="355" valign="top">
										<div id="dtyd" class="dtyd1" onclick="tabConn(this)"></div>
										<div id="dtzw" class="dtzw" onclick="tabConn(this)"></div>
									</td>
									<td width="60">&nbsp;</td>
								</tr>
							</table>
							<div class="documenttxt">
								<%--锚点 --%>
								<a id="documenttop" name="documenttop"></a>
								<div id="partOfDoc" class=" documenttxt" >
									<s:property value="irpDocument.dochtmlcon" escapeHtml="false" />
								</div>
								<div id="DocReadLog" style="display: none;" class=" documenttxt" >
									<ul>
									<s:iterator value="irpLogs">
									<li><s:property value="loguser" /> 在 <s:date name="logoptime" format="yyyy-MM-dd HH:mm:ss" /> <s:property value="logoptype" /></li>
									</s:iterator>
									</ul>
								</div>
								<%--<div id="allDoc" style="display: none;" class=" documenttxt">
									<s:property value="irpDocument.attachedcontent"
										escapeHtml="false" />
								</div>--%>
							</div>
						</div> <!-- 结束打印 --> <s:if test="attacheds!=null && attacheds.size()!=0">
							<ul class="dtfj1">
								<table>
									<s:iterator value="attacheds">
										<tr>
											<td><s:if
													test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@JPG_FIELD_NAME==typeid">
													<span
														style="display: block;background-image: url(<%=rootPath %>client/images/att.png); width:40px; height:43px; background-position: -11px -10px;"></span>
												</s:if> <s:elseif
													test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@WORD_FIELD_NAME==typeid">
													<span
														style="display: block;background-image: url(<%=rootPath %>client/images/att.png); width:40px; height:43px; background-position: -60px -10px;"></span>
												</s:elseif> <s:elseif
													test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@MENU_FIELD_NAME==typeid">
													<span
														style="display: block;background-image: url(<%=rootPath %>client/images/att.png); width:40px; height:43px; background-position: -110px -10px;"></span>
												</s:elseif> <s:elseif
													test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@MICUS_FIELD_NAME==typeid">
													<span
														style="display: block;background-image: url(<%=rootPath %>client/images/att.png); width:40px; height:43px; background-position: -160px -10px;"></span>
												</s:elseif> <s:elseif
													test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@ID_FIELD_NAMEOTHER==typeid">
													<span
														style="display: block;background-image: url(<%=rootPath %>client/images/att.png); width:40px; height:43px; background-position: -210px -10px;"></span>
												</s:elseif></td>
											<td style="padding-left: 20px;"><a
												href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>"><s:property
														value="attdesc" /></a></td>
											<td style="padding-left: 20px;">
						<s:if test="isFuJian=='true'">
						<a	href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>">下载</a>
						</s:if>					
											
											
											
												<s:if
													test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@WORD_FIELD_NAME==typeid">
	                 	| <a target="_blank"
														href="<%=rootPath%>file/online_preview.action?fileName=<s:property value='attfile'/>">在线浏览</a>
													</dd>
												</s:if> <s:elseif
													test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@JPG_FIELD_NAME==typeid">
	               	|  <a target="_blank"
														href="<%=rootPath%>file/inlinereadfile.action?fileName=<s:property value='attfile'/>">在线浏览</a>
													</dd>
												</s:elseif></td>
										</tr>
									</s:iterator>
								</table>
								<%--
	 <s:iterator value="attacheds">
	         	 
	         	 <li>  
	         	 <!-- 
	         	 <img src='<s:property value="findTuBiao(typeid)"/>' width="29" height="30" />    
	         	  --> 
	         	  <s:if test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@JPG_FIELD_NAME==typeid">
	         	  	 <span style="display: block;background-image: url(<%=rootPath %>client/images/att.png); width:40px; height:43px; background-position: -11px -10px;"></span> 
	         	  </s:if>
	         	  <s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@WORD_FIELD_NAME==typeid">
	         	  <span style="display: block;background-image: url(<%=rootPath %>client/images/att.png); width:40px; height:43px; background-position: -60px -10px;"></span> 
	         	  </s:elseif>
	         	   <s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@MENU_FIELD_NAME==typeid">
	         	    <span style="display: block;background-image: url(<%=rootPath %>client/images/att.png); width:40px; height:43px; background-position: -110px -10px;"></span> 
	         	   </s:elseif>
	         	    <s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@MICUS_FIELD_NAME==typeid">
	         	    <span style="display: block;background-image: url(<%=rootPath %>client/images/att.png); width:40px; height:43px; background-position: -160px -10px;"></span> 
	         	    </s:elseif>
	         	   <s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@ID_FIELD_NAMEOTHER==typeid" >
	         	       <span style="display: block;background-image: url(<%=rootPath %>client/images/att.png); width:40px; height:43px; background-position: -210px -10px;"></span> 
	         	  </s:elseif>
	               	<a href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>" >
	               	<s:property value="attdesc"/></a>
				    <dl>
	                <dd> 
	               		 <a href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>" >下载</a>
	                		&nbsp;&nbsp;
		               <s:if test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@WORD_FIELD_NAME==typeid">
		                 	|&nbsp;&nbsp; <a target="_blank" href="<%=rootPath%>file/online_preview.action?fileName=<s:property value='attfile'/>">在线浏览</a></dd>
		               </s:if>
		                <s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@JPG_FIELD_NAME==typeid">
		               	|&nbsp;&nbsp; <a target="_blank" href="<%=rootPath%>file/inlinereadfile.action?fileName=<s:property value='attfile'/>">在线浏览</a></dd>
		               </s:elseif>
	              </dl>
	            </li> 
	          </s:iterator>
	 --%>
							</ul>
						</s:if>

						<div class="dtgjc">
							标签：
							<s:iterator value="irpDocument.dockeywords.split(',')"
								status="st" var="as">
								<a href="javascript:void(0);"
									onclick="searchInfo1('<s:property value="#as"/>')"><s:property
										value="#as" /></a>&nbsp;&nbsp;
  				</s:iterator>
						</div> <%--推荐精华 --%> <s:if
							test="@com.tfs.irp.document.web.DocumentAction@countByDocId(irpDocument.docid)>0">
							<div id="jinghuaimg"
								style="z-index: 100;position: absolute; top: 160px;right:200px;">
								<img src="<%=rootPath%>client/images/001.gif" />
							</div>
						</s:if>
						<div class="detailcontrol">
							<h2>
								<a href="javascript:void(0);"
									onclick="transdocument(<s:property value='irpDocument.docid'/>)">
									<img src="<%=rootPath %>client/images/xl_r10_c4.gif" width="15"
									height="15" />推荐
								</a>
							</h2>
							<%if(loginUser.isExpert()){ %>
							<h2>
								<a href="javascript:void(0);" style="cursor: hand;"
									onclick="documentClassisl(<s:property value='irpDocument.docid'/>)">
									<img src="<%=rootPath %>client/images/xl_r10_c8.gif" width="15"
									height="15" /> <s:if test="docClassicl==null">
										<a id="classlable" href="javascript:void(0);"
											style="cursor: hand;"
											onclick="documentClassisl(<s:property value='irpDocument.docid'/>)">
											加精 </a>
									</s:if> <s:else>
										<a id="classlable" href="javascript:void(0);"
											style="cursor: hand;"
											onclick="documentClassisl(<s:property value='irpDocument.docid'/>)">
											取消加精 </a>
									</s:else>
								</a>
								<label>(<label id="essenceCount"><s:property value="countEssenceByDocId(irpDocument.docid)" /></label>)</label>
							</h2>
							<%} %>

							<h2>
								<s:if test="%{irpDocument.irpDocumentCollection!=null}">
									<img src="<%=rootPath %>client/images/xl_r10_c12.gif"
										width="15" height="15" />
									<a href="javascript:void(0)" id="focus"
										onclick="addfocusdoclink(<s:property value='irpDocument.docid'/>)">
										取消收藏</a>
								</s:if>
								<s:else>
									<img src="<%=rootPath %>client/images/xl_r10_c12.gif"
										width="15" height="15" />
									<a href="javascript:void(0)" id="focus"
										onclick="addfocusdoclink(<s:property value='irpDocument.docid'/>)">
										收藏</a>
								</s:else>
								<label>(<label id="collectionCount"><s:property value="countCollectionByDocId(irpDocument.docid)" /></label>)</label>
							</h2>
							<h2>
								<s:if test="subscribe==1">
									<img src="<%=rootPath %>client/images/xl_r10_c8.gif" width="15" height="15" />
									<a href="javascript:void(0)" id="subscribe" onclick="addSubscribe(<s:property value='irpDocument.docid'/>)">取消订阅</a>
								</s:if>
								<s:else>
									<img src="<%=rootPath %>client/images/xl_r10_c8.gif" width="15" height="15" />
									<a href="javascript:void(0)" id="subscribe" onclick="addSubscribe(<s:property value='irpDocument.docid'/>)">订阅</a>
								</s:else>
							</h2>
							<s:if test="isUpdate=='true'">
								<h2
									onclick="updateDocument(<s:property value='irpDocument.docid'/>)">
									<a href="javascript:void(0);"> <img
										src="<%=rootPath %>client/images/xl_r10_c17.gif" width="15"
										height="15" />修改
									</a>
								</h2>
							</s:if>

							<s:if test="isDelete=='true'">
								<h2
									onclick="deleteDocument(<s:property value='irpDocument.docid'/>)">
									<a href="javascript:void(0);"> <img
										src="<%=rootPath %>client/images/xl_r10_c19.gif" width="15"
										height="15" />删除
									</a>
								</h2>
							</s:if>
							<h2 id="dingh">
								<img src="<%=rootPath %>client/images/thread_rate.gif"
									width="15" height="15" /> <label id="ding" class="linkc12">
									<s:if test="%{irpDocument.irpMostTreadDing!=null}">已顶 </s:if> <s:else>
										<a href="javascript:void(0);"
											onclick="ding(<s:property value='irpDocument.docid'/>)">顶</a>
									</s:else>
								</label> (<label id="dinglabel"><s:property
										value="irpDocument.isvalue" /></label>)
							</h2>
							<h2 id="caih">
								<img src="<%=rootPath %>client/images/cyx.gif" width="15"
									height="15" /> <label id="cai" class="linkc12"> <s:if
										test="%{irpDocument.irpMostTreadCai!=null}">已踩</s:if> <s:else>
										<a href="javascript:void(0);"
											onclick="cai(<s:property value='irpDocument.docid'/>)">踩</a>
									</s:else>
								</label> (<label id="cailabel"><s:property
										value='irpDocument.novalue' /></label>)
							</h2>
							<h2>
								<img src="<%=rootPath %>client/images/report.gif" width="15"
									height="15" />
								<s:if test="irpDocument.informtype>0">
									<a href="javascript:void(0);">已举报</a>
								</s:if>
								<s:else>
									<a href="javascript:void(0);"
										onclick="clientjubaodocument(<s:property value='irpDocument.docid'/>)">举报</a>
								</s:else>
							</h2>
							
							
							<s:if test="isPriant=='true'">
							<h2>
								<a
									href="<%=rootPath %>site/printdocumentinfo.action?docid=<s:property value='irpDocument.docid'/>"
									target="_blank"> <img
									src="<%=rootPath %>client/images/print.jpg" width="15"
									height="15" />打印
								</a>
							</h2>
							</s:if>
							<h2><a onclick="catchknowto()" href="javascript:void(0)"><img src="<%=rootPath %>client/images/yin.png" alt="引" title="引用到专题"/>专题</a></h2>
							<s:if test="isHistoryversion=='true'">
							<h2><a onclick="searchDocVersion(<s:property value='irpDocument.docid'/>)" href="javascript:void(0)" style="margin-left: -20px;" title="查看历史版本" >历史版本</a></h2>
							</s:if>
							<h2 id="documentscore" style="float: right;">
								<s:if test="isScore=='true' && irpDocument.markpersonamount >= @com.tfs.irp.util.SysConfigUtil@getSysConfigNumValue('DOC_SCORE_SHOW_NUM')">
									<div style="float: left;" id="allscore" class="shop-rating">
										<span>
											综合评分：
											<s:property value='irpDocument.avgscore' default="0" />分
											/
											<s:property value='irpDocument.markpersonamount' default="0" />人
										</span>
										<ul class="rating-level" id="stars1">
											<li><a
												class="one-star <s:if test='irpDocument.avgscore>0 && 1.5>irpDocument.avgscore'> current-rating</s:if>">1</a></li>
											<li><a
												class="two-stars <s:if test='irpDocument.avgscore>=1.5 && 2.5>irpDocument.avgscore'> current-rating</s:if>">2</a></li>
											<li><a
												class="three-stars <s:if test='irpDocument.avgscore>=2.5 && 3.5>irpDocument.avgscore'> current-rating</s:if>">3</a></li>
											<li><a
												class="four-stars <s:if test='irpDocument.avgscore>=3.5 && 4.5>irpDocument.avgscore'> current-rating</s:if>">4</a></li>
											<li><a
												class="five-stars <s:if test='irpDocument.avgscore>=4.5'> current-rating</s:if>">5</a></li>
										</ul>
									</div>
								</s:if>
							</h2>
						</div>
					</td>
				</tr>
			</table>
			<div class="marginb7 dtbox">
				<div class="" id="jiajingcommend"
					style="float: left;padding-left: 70px;padding-right: 55px; padding-top: 3px;">
					<s:if test="docClassicls!=null &&docClassicls.size()>0">
						<s:iterator value="docClassicls">
							<ul
								id="<s:property value='userid'/>_<s:property value='informnameid'/>">
								<li><a target="_blank"
									href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid'/>">
										<strong><s:property
												value="@com.tfs.irp.projectperson.web.IrpProjectPersonAction@findUserNameByUserId(userid)" /></strong>
								</a> 加精理由：<s:property value="informcontent" /></li>
							</ul>
						</s:iterator>
					</s:if>
				</div>
			</div>
			<div class="marginb7">
				<center>
					<img src="<%=rootPath %>client/images/xl_r13_c28.gif" border="0"
						usemap="#Map" />
				</center>
				<map name="Map" id="Map">
					<area shape="rect" coords="812,1,909,20" href="#" />
				</map>
			</div>
		</div>
		<div class="area1"></div>
        <div class="marginb7 dtbox">
              <div class="zj_title1">
            		<div class="zj_tt1"><h1>系统推荐的相关知识</h1></div>
            		<div class="zj_more"><a href="#"></a></div>
              </div>
               <!--可隐藏区S-->  <%--相关知识 --%>
              <div class="zj_box4" id="xiangguandocument"> </div>
              <!--可隐藏区E-->   
       </div>
            <div class="area1"></div>
            <div class="marginb7 dtbox">
              <div class="zj_title1">
            	<div class="zj_tt"><h1>精华知识</h1></div>
            	<div class="zj_more">
	            	<a href="#"></a>
            	</div>
            </div>
              <!--可隐藏区S-->
              <div class="zj_box4">
              <table width="100%" border="0" cellspacing="0" cellpadding="0" class="olbg11"> 
                	<tbody id="jianghuadocument"></tbody>
              </table>
              </div>
              <!--可隐藏区E--> 
            </div>
            <div class="area1"></div>  
            <div class="marginb7 dtbox">
	              <div class="zj_title1">
	            		<div class="zj_tt1"><h1>创建者其它知识</h1></div>
		            	<div class="zj_more">
		            		<a href="#"></a>
		            	</div>
	             </div> 
	              <!--可隐藏区S-->  <%--相关知识 --%>
            	  <div class="zj_box4" id="personcollectiondocument"></div>
            	  <!--可隐藏区E--> 
            </div>
            
            <div class="zj_box4" id="xiangguandocument"></div>
            <div class="area1"></div>
            <div class="marginb7 dtbox">
              <div class="zj_title1">
            	<div class="zj_tt"><h1>相关评论</h1></div>
            	<div class="zj_more">
	            	<a href="#"></a>
            	</div>
            </div>
            <div class="zj_box4">
            	<div class="olbg11">
                 <div id="speek" ></div> <%-- class="zwen intro" --%>
                <div class="ppl">
                <img src="<%=rootPath %>client/images/pl.gif" width="878" height="39" border="0" usemap="#Map2" />
                  <map name="Map2" id="Map2">
                    <area shape="rect" coords="745,9,863,33" href="#" />
                  </map>
                  <a name="001" id="001" > </a>
                  <s:if test="isXiangGuan=='true'">
				  <div style="width: 800px;padding-left: 50px;">
				  <textarea id="editor" name="editor"></textarea>
				   <p style="width:100%;">
	        		  <a href="javascript:void(0);" onclick="adddocrecommend(<s:property value='irpDocument.docid'/>,<s:property value='irpDocument.cruserid'/>)"  id="submit" class="newabtngrn" style=" margin:0 auto;">
	        		   发评论</a>
        		   </p>
				  </div>
				  </s:if>
                  </div>
              </div>
            </div> 
            </div>
    </div>
    <div class="area2"></div>
    <!-- 
    
    <s:include value="../include/enterprise_foot.jsp"></s:include>
     -->
     <footer><section class="mainBox"><span>北京睿思鸣信息技术有限公司版权所有©1997-2014&nbsp;|&nbsp;</span><a href="#" target="_blank">关于</a>&nbsp;<a href="#" target="_blank">商务合作</a>&nbsp;<a href="#" target="_blank">官方博客</a>&nbsp;<a href="#" target="_blank">官方微知</a>&nbsp;&nbsp;</section></footer>
</body>
</html>