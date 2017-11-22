<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.tfs.irp.util.LoginUtil,com.tfs.irp.user.entity.IrpUser"%>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
	IrpUser loginUser = LoginUtil.getLoginUser();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="irpQuestion.title" /></title>
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>client/css/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>client/css/icon.css" />
<link href="<%=rootPath%>client/css/oacss.css" rel="stylesheet"	type="text/css" />
<link href="<%=rootPath%>client/css/lib-3322.min.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath%>client/css/workhome-3482.min.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath%>client/css/asx.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath%>client/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
<jsp:include page="../include/client_skin.jsp" />
<link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css"/>
<link href="<%=rootPath %>view/css/common1.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
body {
	behavior: url(<%=rootPath%>client/js/hover.htc);
}
.wd_right ul{padding-top:4px;}
.wd_right li{background: url(<%=rootPath%>client/images/dot_08.jpg) left center no-repeat;}
.wd_right li a{width: 100%;display: block;line-height: 30px;font-size: 14px;text-overflow: ellipsis;white-space: nowrap;overflow: hidden;text-indent: 14px;}

.cmt_fed {
	padding-top: 15px;
}

.question {
	background: url(<%=rootPath%>client/images/ico08.gif);
	width: 640px;
	height: 46px;
	padding: 5px;
	overflow: hidden;
	margin-bottom: 5px;
}

.inputButton{
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
.inputButton:hover{
text-decoration: none;
}

</style>
<script type="text/javascript" src="<%=rootPath %>client/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath %>editor/simple_ckeditor/ckeditor.js"></script>	
<script type="text/javascript" src="<%=rootPath%>client/js/questionjson/json2.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<style type="text/css">
body{behavior:url(hover.htc);}
.searchSec .radios span {
margin-top: 0px;
}	
.ui_content table td {
 border: 0px none rgb(209, 209, 209);
   
    }	
</style> 

  </head>
<body style="background: url()"> 
<script language="JavaScript" type="text/javascript">
var addJsonFileList = new Array();
$(function(){
	//加载个人基本信息
	 personallchannel();//得到导航栏
		hotQuestion();
		newestQuestion();
		userQuestion();
	$("#all").click();
	$('#headDaohang').find('a').each(function(){
			this.className="";	
	});
	$('#questions').addClass("hover");
	var cssUrl = $('#skin').attr('href');
	var sColor  = cssUrl.substring(cssUrl.lastIndexOf('/oapf-')+6,cssUrl.lastIndexOf('.css'));
	if(sColor=="black"){
		sColor='#EEEEEE';
	}else if(sColor=="orange"){
		sColor='#F7EDD7';
	}else if(sColor=="green"){
		sColor='#EBF4DA';
	}else if(sColor=="blue"){
		sColor='#ECFAFB';
	}else{
		sColor='#ECFAFB';
	}
	CKEDITOR.replace('editor',{
		uiColor: sColor
	});
	
	
	
});
//最热问答 
function newestQuestion(){
	$.ajax({
		type:"post",
		cache:false,
		url:"<%=rootPath%>question/hotQuestion.action",
		success:function(html){
			$('#hottalkdocument').html(html);
		}
	});
}
//最新问答
function hotQuestion(){
	$.ajax({
		type:"post",
		cache:false,
		url:"<%=rootPath%>question/newestQuestionNew.action",
		success:function(html){
			$('#youlovedocument').html(html);
		}
	});
}
//问答达人
function userQuestion(){
	$.ajax({
		type:"post",
		cache:false,
		url:"<%=rootPath%>question/userQuestionill.action",
		success:function(html){
			$('#sharedocument').html(html);
		}
	});
}

//进入页面发送ajax请求，然后得到导航栏（二级栏目列表）
function personallchannel(){
	var str=$.ajax({
		type:'post',
		url:'<%=rootPath%>site/selectchannel.action',
		dataType: "json",
		async: false,
	    cache: false 
	}).responseText; 
	 $('#daohangul').append($.trim(str));  
}
//回答
function reply(){
	
	var traget=document.getElementById("answer");
	if(traget.style.display=="none"){
         traget.style.display="";
 	}else{
 		CKEDITOR.instances.editor.document.getBody().setHtml("");
         traget.style.display="none";
	}
}
//修改
function modify(){
	var traget=document.getElementById("modify");
	if(traget.style.display=="none"){
         traget.style.display="";
 	}else{
         traget.style.display="none";
		}
}
//发布回答
function publish(questionid){
	var doccontenteditor=CKEDITOR.instances.editor;
	var title=$("#title1").html();
	var browsecount=${browsecount};
	if($.trim(doccontenteditor.document.getBody().getText()).length>0){
		if(doccontenteditor.document.getBody().getText().length>1300){
			   $.dialog.tips("您输入的内容长度超出了限制",0.5,"alert.gif");
				return false;
			}else{
			$.ajax({
				type:"POST",
				url:'<%=rootPath%>question/publishAnswer.action',
				dataType: "json",
				data:{
					text:$.trim(doccontenteditor.document.getBody().getHtml()),
					contentanswer:$.trim(doccontenteditor.document.getBody().getText()),
					questionid:questionid,
					title:title,
					browsecount:browsecount,
					jsonFile:JSON.stringify(addJsonFileList)
				},
				success:function(nMsg){
					var msg=parseInt(nMsg/10);
					var expertStatus=nMsg%10;
					if(msg==1){
						$.dialog.tips('回答成功',1,'32X32/succ.png',function(){
							//清空
							addJsonFileList.length=0; 
							CKEDITOR.instances.editor.document.getBody().setHtml("");
							$("#answer").hide(); 
							if(expertStatus==0){
								//局部刷新其他答案div
								$.ajax({
									type:'post',
									url:'<%=rootPath%>question/loadOtherAnswers.action',
									data:{
										questionid:questionid
									},
									success:function(callback){
										$('#answerDiv').html(callback);
										var total=$("#totlaReply").text();
										$("#totlaReply").text(parseInt(total) + 1);
									}
								});
							}else{
								//局部刷新专家div
								$.ajax({
									type:'post',
									url:'<%=rootPath%>question/loadExpertAnswers.action',
									data:{
										questionid:questionid
									},
									success:function(callback){
										if(callback!=null){
											$('#expertAnswer').html(callback);
											var total=$("#totlaReply").text();
											$("#totlaReply").text(parseInt(total) + 1);
										}
									}
								});	
							}
						});
					} else {
						//清空
						addJsonFileList.length=0; 
						$.dialog.tips('回答失败', 1, '32X32/fail.png');
						$("#answer").hide(); 
					}
				}
			});
			}
	}else{
		$.dialog.tips("发布内容不能为空",1,"alert.gif");
		   return false;
	} 
}
//发布修改
function publishModify(questionid){
	var traget=document.getElementById("modify");
	var textareaModifytitle=$('#textareaModifytitle').val();
    var textareaModifycontent=$('#textareaModifycontent').val();
			CKEDITOR.instances.editor.document.getBody().setHtml("");
			if($.trim(textareaModifytitle)=="" && $.trim(textareaModifycontent)=="" ){
				$.dialog.tips("标题或者内容必须填写一项",1.5,"alert.gif");
				return false;
			}else{
				 if(textareaModifytitle.length>150){
						$.dialog.tips("请填写150字以下的标题内容",1,"alert.gif");
						return false;
					}else if(textareaModifycontent.length>300){
						$.dialog.tips("请填写300字以下的问题内容",1,"alert.gif");
						return false;
					}else{
						$.ajax({
							url:'<%=rootPath%>question/publishModify.action',
							type:"POST",
							cache:false,
							async:false,
							data:{
								questionid:questionid,
								textareaModifytitle:textareaModifytitle,
								textareaModifycontent:textareaModifycontent
							},
							success:function(nMsg){
								if(nMsg==1){
									$.dialog.tips('修改成功',1,'32X32/succ.png',function(){
										traget.style.display="none";
										window.location.reload(true);
									});
								} else {
									$.dialog.tips('修改失败', 1, '32X32/fail.png');
								}
							}
						});
					}
			}
}
//设最佳答案
function setBestAnswer(answerid,cruserid){
	var questionid=$("#questionid").val();
	var answer=$("#otheranswer"+answerid).text();
	var browsecount=${browsecount};
	$.ajax({
		url:'<%=rootPath%>question/modifyStatus.action',
		type:"POST",
		data:{
			answerid:answerid,
			answer:answer,
			questionid:questionid,
			browsecount:browsecount,
			answercruserid:cruserid
		},
		success:function(nMsg){
			if(nMsg==1){
				$.dialog.tips('设置成功',1,'32X32/succ.png',function(){
					$("#setBestAnswer").hide();
					window.location.reload(true);
				});
			} else {
				$.dialog.tips('设置失败', 1, '32X32/fail.png');
			}
		}
	});
}
//分页
function page(pageNum){
	var sUrl = '<%=rootPath%>question/loadOtherAnswers.action';
	$('#pageNum').val(pageNum);
	var queryString = $('#pageForm').serialize();
	var sHtmlConn = $.ajax({ 
 		type:'post', 
 		url: sUrl,
 		data: queryString,
 		dataType: "json",
		async: false,
	    cache: false  
 	}).responseText;
	$('#answerDiv').html(sHtmlConn);
}
//添加文档
function toaddDocument(){
	    var questionid = <s:property value="irpQuestion.questionid" />;
		window.open('<%=rootPath %>question/client_toadd_document_question.action?questionid='+questionid);  
}
//引用知识
function quoteKnowledge(){
	var doccontenteditor=CKEDITOR.instances.editor;
	var dialogdiv=document.createElement("div");
 	dialogdiv.id="quotedivs";
 	document.body.appendChild(dialogdiv);
 	$('#quotedivs').dialog({
 		modal:true,
 		cache:false,
 		href:'<%=rootPath%>client/questiondetail/quote_knowledge.jsp',
 		height:600,
 		width:800,
 		title:'引用知识',
 		resizable:true,
 		buttons:[{
 			text:'提交',
 			iconCls: 'icon-ok',
 			handler:function(){
 				 var str="";
 		           $("input[name='chnlDocLinkscatach']:checkbox").each(function(){ 
 		               if($(this).attr("checked")){
 		            	  str += $(this).val()+","
 		               }
 		           })
 		           $.ajax({
 		        	   type:"post",
 		        	   url:"<%=rootPath%>site/selecteddoctitle.action",
 		        	   data:{
 		        		  docidlist:str
 		        	   },
 		        	   success:function(dochtml){
 		        		  var quoteknowledgecontent = ""; 
 		        		  if(dochtml!=''){
 		        			 quoteknowledgecontent = '引用知识:';
 		        		  }
 		        		 doccontenteditor.document.getBody().setHtml(doccontenteditor.document.getBody().getHtml()+quoteknowledgecontent+dochtml);
 		        		  $('#quotedivs').dialog('destroy');
 		        		  $('#hideknowselected').html(doccontenteditor.document.getBody().getHtml()+dochtml);
 		        	   }
 		           });
 			}
 		},{
 			text:'取消',
 			iconCls:'icon-cancel',
 			handler:function(){
 				$('#quotedivs').dialog('destroy');
 			}
 		}],
 		onClose:function(){
 			$('#quotedivs').dialog('destroy');
 		}
 		});
}
//初始化
function init(){
	CKEDITOR.replace('editor');
 }
 /**
 *展开知识
 */
function showdocumentinfo(_docid){
	 	window.open('<%=rootPath %>site/showdocumentinfo.action?refrechstatus=1&docid='+_docid); 
	} 
/**
 * 赞同
 */
function endorseAnswer(_questionid){
	//查看当前登录 用户是否已经赞同过此答案了
	var mote = "#mote"+_questionid;
	var oppo = "#oppo"+_questionid;
	var consent = "#consent"+_questionid;
	$.ajax({
		url:"<%=rootPath %>question/findendorseansweruser.action",
		data:{
			questionid:_questionid
		},
		async:false,
		cache:false,
		success:function(msg){
			if(msg==0){
				$.ajax({
					url:"<%=rootPath %>question/addendorseanswer.action",
					data:{
						questionid:_questionid
					},
					async:false,
					cache:false,
					success:function(status){
						$(mote).text(parseInt($(mote).text())+1);
						if(status==1){
							 $.ajax({
									  url: "<%=rootPath %>question/gethaspeopleconsent.action",
									  data:{
										questionid:_questionid  
									  },
									  cache:false,
									  success:function(consentinfo){
										  $(consent).html(consentinfo);
									  }
					              	});
						}
					}
				});
			}
		}
	})
} 
/**
 * 反对
 */
function opposeAnswer(_questionid){
	//查看当前登录 用户是否已经取消过此答案了
	var mote = "#mote"+_questionid;
	var consent = "#consent"+_questionid;
	var oppo = "#oppo"+_questionid;
	
	$.ajax({
		url:"<%=rootPath %>question/findopposeansweruser.action",
		data:{
			questionid:_questionid
		},
		async:false,
		cache:false,
		success:function(msg){
				if(msg==0){
					$.ajax({
						url:"<%=rootPath %>question/addopposeanswer.action",
						data:{
							questionid:_questionid
						},
						async:false,
						cache:false,
						success:function(status){
							$(oppo).text(parseInt($(oppo).text())+1);
							if(status==1){
								 $.ajax({
									  url: "<%=rootPath %>question/gethaspeopleconsent.action",
									  data:{
										questionid:_questionid  
									  },
									  cache:false,
									  success:function(consentinfo){
										  $(consent).html(consentinfo);
									  }
					              	});
							}
						}
					});
			}
		}
	})
} 
/**
 * 展开全部
 */
function unfoldAllAndorse(_questionid){
	var consent = "#consent"+_questionid;
	 $.ajax({
		  url: "<%=rootPath %>question/gethaspeopleconsentall.action",
		  data:{
			questionid:_questionid  
		  },
		  cache:false,
		  success:function(consentinfo){
			  $(consent).html(consentinfo);
		  }
     	});
}
/**
 *检索关键字
 */
function searchInfoWord(_searchInfo){
	var disposecontent =  _searchInfo.replace(":","\\:");
	if(searchInfo.length>38){
		searchInfo = searchInfo.substring(0,37);	
	}
	var eacapeInfo = encodeURI(disposecontent);
	//查询数据库配置表的检索开关
	
	$.getJSON("<%=rootPath%>menu/search_ts.action",function(data){
		var result=data.message;
		if(result=="t"){
			window.location.href="<%=rootPath%>menu/trs_search.action?searchInfo="+eacapeInfo+"&searchtype=1"; 
		}else{
			window.location.href="<%=rootPath%>solr/searchcontentofsolr.action?searchInfo="+eacapeInfo+"&searchtype=1";
		}
	});
	
}
/**
 * 答案添加附件
 */
function tosaveAttactheAnswer(){
	 var str=$.ajax({
		 type:'post',
		 dataType: "json",
		 url:'<%=rootPath %>site/client_to_save_attached.action?isqusertionordoc=1', 
		 async: false,
	     cache: false
	 }).responseText;  
   $.dialog({
			title:'附件管理',
			max:false,
			min:false,
			lock:true,
			resize: false,  
			content:str,
			cancelVal:'确定', 
			cancel:function(){
			 if(addJsonFileList){  
	       		 var id=$('input:radio[name="editversions"]:checked').attr("id"); 
	       		 for(var i=0;i<addJsonFileList.length;i++){
	       			if(addJsonFileList[i].attfile==id){
	       				addJsonFileList[i].editversions=1; 
	       			}else{ 
	       				  if(addJsonFileList[i].editversions=="2"){//一种就是附件，
	       				  }else{
	       				    addJsonFileList[i].editversions=0;
	       				  }
	       			}
	     		  }
	     	   }  
			}  
	  });   
}
/**
 * 删除答案
 */
function deleteAnswer(_questionid){
	var questionofanswer = "#question"+_questionid+"ofanswer";
	$.dialog.confirm('您要确定要删除此条答案吗?',function(){
		$.ajax({
			type:'post',
			cache:false,
			async:false,
			data:{
				 questionid:_questionid
			},
			url:'<%=rootPath%>question/deleteanswerbyquestionid.action',
			success:function(msg){
				if(msg>=1){
					 $(questionofanswer).fadeOut();
					 $('#totlaReply').text(parseInt($('#totlaReply').text())-1);
					 $.dialog.tips('删除成功', 1,'32X32/succ.png');
				}else{
					 $.dialog.tips('删除失败', 1,'32X32/fail.png');
				}
			}
		});
	});
} 
/*
 * 查看某个图片的大图
 */
function checkMaxPic(_src,_name,_ids){
	$.dialog({
		title:"查看图片",
		content: 'url:<%=rootPath%>microblog/checkMaxPic.action?picfile='+_src+'&picfilenamelist='+_name+'&picfileids='+_ids,
		cache:false,
		cancelVal: '关闭',
	    cancel: true,
		max: false,
	    min: false,
	    lock:true,
	    width:650,
	    height:500
	});
}	
	/**
	*	回答评论 展开
	*/
	function replyComment(_questionid){
		var cbox =	"#compllethecombox"+_questionid;
		var rclist =  "#replyclist"+_questionid; 
		if($(cbox).css('display')=='none'){
			$(cbox).css({'display':'block'});
			$.ajax({
				type:'get',
				url:'<%=rootPath%>question/findcreplycontent.action',
				data:{
					questionid:_questionid,
					pageNum:1
				},
				async:false,
				cache:false,
				success:function(content){
					$(rclist).append(content);
				}
			});
		}else{
			$(rclist).html("");
			$(cbox).css({'display':'none'});	
		}
		
		
		
		

	}
	/**
	*	回答评论 发送
	*/
	function replyCommentV(_questionid,_cruserid,_showname){
		var ctctext = "#ctcbtext"+_questionid;
		var rclist =  "#replyclist"+_questionid;
		var cques = "#commentquest"+_questionid;
		var ctval = $(ctctext).val();
		if($.trim(ctval)==''){
			$.dialog.tips('评论不能为空',0.3,'alert.gif');
			return false;
		}else{
			if(ctval.length>300){
				$.dialog.tips('评论字数不能超过300',0.3,'alert.gif');
				return false;	
			}else{
				var myDate = new Date();
				$.ajax({
					type:'post',
					url:'<%=rootPath%>question/addanswercomment.action',
					data:{
						questionInfo:ctval,
						questionid:_questionid
					},
					async:false,
					cache:false,
					success:function(msg){
						$(ctctext).val("");
						if(msg==1){
							$.dialog.tips('评论成功', 1,'32X32/succ.png');
							var _vhtml = "<div style=\"border:thin solid #ADADAD;border-style: none none solid none;width: 90%;text-align: left;padding:1% 1% 1% 1%; \">"
												+"<div><label class=\"linkb14\" onclick=\"linkUser("+_cruserid+")\" style=\"cursor:pointer;\" >"+_showname+"</label></div>"
												+"<div>"+ctval+"</div>"
												+"<div>"+myDate.toLocaleString().replace("年","-").replace("月","-").replace("日","").replace("时",":").replace("分",":")+"</div>"
										  +"</div>"
										  
							$(rclist).append(_vhtml);
							var htval = parseInt($(cques).text().replace("追问(","").replace(")",""))+1;
							$(cques).html("追问("+htval+")");
						}else{
							$.dialog.tips('评论失败', 1,'32X32/fail.png');
						}
						
					}
				});
			}

		}
	}
	/**
	*连接到个人
	*/
	function linkUser(cruserid){
		
		window.open("<%=rootPath%>site/client_to_index_person.action?personid="+cruserid);
	}
	function cVAllComm(_questionid,_pnum){
		var nextbool = "#"+_pnum+"isnextboolc"+_questionid;
		var rclist =  "#replyclist"+_questionid; 
		$.ajax({
			type:'get',
			url:'<%=rootPath%>question/findcreplycontent.action',
			data:{
				questionid:_questionid,
				pageNum:parseInt(_pnum)+1
			},
			async:false,
			cache:false,
			success:function(content){
				$(nextbool).css("display","none");
				$(rclist).append(content);
			}
		});
	}
	function overRComm(_questionid,_st){
		var overpoint = "#replytwo"+_questionid;
		if(_st==1){
			$(overpoint).css("visibility","visible");	
		}else{
			$(overpoint).css("visibility","hidden");
		}
	}
	function replyTwoReply(_questionid,_showname){
		
		var vhqid =	"#vhrediv"+_questionid;
		var vhqtext = "#vhredivtext"+_questionid;
		if($(vhqid).css('display')=='none'){
			$(vhqid).css({'display':'block'});
		}else{
			$(vhqid).css({'display':'none'});
		}	
	}
	function replyCPT(_questionid,parentid){
		var vhqtext = "#vhredivtext"+_questionid;
		var vval = $(vhqtext).val();
		if($.trim(vval)==''){
			$.dialog.tips('评论不能为空',0.3,'alert.gif');
			return false;
		}else{
			if(vval.length>300){
				$.dialog.tips('评论字数不能超过300',0.3,'alert.gif');
				return false;	
			}else{
				$.ajax({
				type:'post',
				url:'<%=rootPath%>question/addanswercomment.action',
				data:{
					questionInfo:vval,
					questionid:parentid,
					replyqid:_questionid
				},
				async:false,
				cache:false,
				success:function(msg){
					if(msg==1){
						$(vhqtext).val("");
						$.dialog.tips('回复成功', 1,'32X32/succ.png');
					}else{
						$.dialog.tips('回复失败', 1,'32X32/fail.png');
					}
					
				}
				});
			}
		}
	}
	
	/**
	* 推荐专家
	*/
	function recommendExpert(){
		//获得专家
		var result = $.ajax({
			url: '<%=rootPath%>expert/selectExpert.action',
			type:"post",
			dataType: "json",
		    async: false
		}).responseText;
		$.dialog({
			title:'选择专家',
			max:false,
			min:false,
			lock:true,
			resize: false,
			content:result,
			cancelVal:'关闭',
			okVal:'推荐',
			cancel:function(){},
			ok:function(){
				var expertId,expertName;
				var expertTab = $('.selectedExpert');
				if(expertTab){
					expertId = expertTab.attr('_eid');
					expertName = expertTab.attr('_ename');
				}
				$.ajax({
					url:"<%=rootPath%>expert/recommend_expert.action",
					type:"post",
					data:{
						expertId : expertId,
						questionId : <s:property value="irpQuestion.questionid" />
					},
					cache:false,
					success:function(msg){
						if(msg==1){
							$.dialog.tips('推荐成功', 1,'32X32/succ.png');
						}else{
							$.dialog.tips('推荐失败', 1,'32X32/fail.png');
						}
					}
				});
			}
		});
	}
</script>

	<!-- 分页参数开始 -->
	<form id="pageForm">
		<s:hidden name="pageNum" id="pageNum" />
		<s:hidden name="pageSize" id="pageSize" />
		
		<input type="hidden" value="<s:property value="irpQuestion.questionid" />" id="questionid"
			name="questionid" />
	</form>
	<!-- 分页参数结束 -->
	<div class="bg01">
		<jsp:include page="../../view/include/client_head.jsp" />
			<section class="mainBox">
				<nav class="privateNav">
				</nav>
			</section>
		<!--头部菜单end-->
		<div class="main" style="width:1200px;background:none;">
			<!--左侧内容-->
			<div class="left" style="width:870px;background:none;">
				<div class="fyh" style="width:870px;margin:0;">
					<!-- 问题主题开始 -->
					<div>
						<div>
							<div>
								<!-- 问题主体开始 -->
								<div class="itemfeed" style="width:860px; overflow:hidden; border:none; padding-left:5px;">
									<div class="left_ifed">
										<a hidefocus="true" title="" href="javascript:void(0)" style="width:60px;height:65px;">
											<img src="<s:property value="irpUser.defaultUserPic" />" width="60px" height="65px" />
										</a>
									</div>
									<div class="right_ifed" style="padding-left:70px;">
										<div class="main_fed">
											<div class="source_fed">
												<a hidefocus="true" class="name_sfed skip_mmfed" title="" href="javascript:void(0)">
													<s:property value="getShowPageViewNameByUserId(irpQuestion.cruserid)" />
												</a>
												<span class="cont_sfed"><s:date name="irpQuestion.crtime" format="yyyy-MM-dd HH:mm" />&nbsp;提问:
													<div></div>
													<div id="title1" style="font-size: 14px;word-break:break-all;margin-top: 5px;">
														<s:if test="irpQuestion.title!=null">
														<s:property value="irpQuestion.title" /><br/>
														</s:if>
														<s:property value="irpQuestion.htmlcontent" />
													</div>
													<div id="title1" >
													<s:if test="attacheds.size()>0">
														<s:iterator value="attacheds" status="attachedsstatus">
															<img  id="<s:property value="#attachedsstatus.index" />" name="<s:property value="pageviewname" />" onclick="checkMaxPic(this.src,this.name,this.id)"   src="<%=rootPath%>file/readfile.action?fileName=<s:property value="updatePagePic(attfile)" />" style="width: 140px;height: 150px;cursor:url(<%=rootPath%>client/images/maxpic_03.cur),auto;" />
															&nbsp;
														</s:iterator><br/>
													</s:if>
													<s:if test="attachedsofother.size()>0">
														<s:iterator value="attachedsofother">
																<a href="<%=rootPath%>file/readfile.action?fileName=<s:property value="attfile" />&fileTrueName=<s:property value="attdesc" />" title="<s:property value="attlinkalt" />" style="text-decoration: underline;" target="_blank"><s:property value="attdesc" /></a><br/>
														</s:iterator>
													</s:if>
													</div>
												 </span> 
												 <input type="hidden" id="questionid" value="<s:property value="irpQuestion.questionid" />" />
											</div>

											<div class="master_mfed" style="position:relative">
												共<b id="totlaReply"><s:property value="totalReply" /></b>条回答 共<font><s:property value="browsecount" /></font>人浏览
												<div style="position:absolute; right:0; top:0; bottom:0; line-height:36px;">
													<div class="btn_amfed">
														<ul>
															<s:if test="irpQuestion.cruser==loginUsername && irpQuestion.status==0">
																<li style="float: right;" class="more_bamfed"><a
																	hideFocus="true" href="javascript:void(0)"
																	onclick="modify()">修改</a>
																</li>
															</s:if>
														<li style="float: right;" class="reply_bamfed"
															id="replyDiv"><a hideFocus="true"
															href="javascript:void(0)" onclick="reply()">
																回答 </a>
														</li>
														<s:if test="irpQuestion.status==0">
															<li style="float: right;" class="reply_bamfed"
																id="replyDiv"><a hideFocus="true"
																href="javascript:void(0)" onclick="recommendExpert()">
																	推荐回答 </a>
															</li>
														</s:if>
														<s:elseif test="irpQuestion.status==1">
														<%if(LoginUtil.getLoginUser().isQuestionManager()){%>
														<li style="float: right;" class="reply_bamfed"
															id="transitionknowledge"><a hideFocus="true"
															href="javascript:void(0)" onclick="toaddDocument()">
																转为知识 </a>
														</li>
														<%}else{ %>
														<s:if test="irpQuestion.cruser==loginUsername">
														<li style="float: right;" class="reply_bamfed"
															id="transitionknowledge"><a hideFocus="true"
															href="javascript:void(0)" onclick="toaddDocument()">
																转为知识 </a>
														</li>
														</s:if>
														<%} %>
														</s:elseif>
													</ul>
												</div>
											</div>
										</div>
										<div class="action_mfed clearfix" style="height:10px;"></div>
									</div>
									<!-- 回答的隐藏div开始-->
									<div id="answer" class="" style="display:none;">
										<div id="" class="" style="border-color:rgb(65,172,178);text-align:right;">
											<iframe name="editor" id="editor" align="middle" frameborder="0" scrolling="no" width="490" height="300"></iframe>
										</div>
										<div id="hideknowselected" style="display: none;"></div>
										<div style="height: 10px;"></div>
										<div>
											<div>
											   <span>
												   <a href="javascript:void(0)" style="border: 1px solid rgb(200, 200, 200);padding: 2px 8px;" onclick="quoteKnowledge()"  >引用知识</a>
												   <a class="linkc12" style="margin-left: 20px;" href="javascript:void(0)" onclick="tosaveAttactheAnswer()">添加附件</a>
											   </span>
												<a href="javascript:void(0)"
													onclick="publish(${questionid});"> <img
													src="<%=rootPath%>client/images/fabu.png" alt=""
													align="right" />
												</a>
											</div>
										</div>
									</div>
									<!-- 回答的隐藏div结束 -->
									<!-- 修改的隐藏div开始 -->
									<div id="modify" class="" style="display:none">
										<div id="" class="" style="border-color:rgb(65,172,178);">
										    <font class="linkbh14">标题:<font/><br/>
										    <textarea style="background-color: rgb(241, 241, 241);" rows="1" id="textareaModifytitle" cols="70"><s:property value="irpQuestion.title" /></textarea><br/>
										    <font class="linkbh14">内容:<font/><br/>
										    <textarea style="background-color: rgb(241, 241, 241);" rows="5" id="textareaModifycontent" cols="70"><s:property value="irpQuestion.htmlcontent" /></textarea>
											<p id="" class="" style="display:none;"></p>
										</div>
										<div>
											<div>
												<a href="javascript:void(0)" 
													onclick="publishModify(${questionid});"> <img
													src="<%=rootPath%>client/images/fabu.png" alt=""
													align="right"  />
												</a>
											</div>
										</div>
									</div>
									<!-- 修改的隐藏div结束 -->
								</div>
							</div>
							<!-- 问题主体结束 -->
							<!--答案div开始-->
							<div class="itemfeed" style="padding: 11px 0 5px 0;position: relative; border-bottom: none;zoom: 1;">
								<!-- 最佳答案开始 -->
								<div id="bestAnswerDiv" style="padding-left:70px;">
									<s:iterator value="irpQuestion1">
										<div id="question<s:property value="questionid"/>ofanswer">
											<s:if test="bestAnswer==0">
												<div id="bestAnswer">
													<div class="tit" style="background-image:url('<%=rootPath%>view/images/bestanswer.png')">最佳答案</div>
													<div id="bestAnswerDiv"></div>
												</div>
											</s:if>
											<s:else>
												<div id="bestAnswer">
													<div class="tit" style="background-image:url('<%=rootPath%>view/images/bestanswer.png')">最佳答案</div>
													<div id="bestAnswerDiv" style="font-size: 14px;">
														<p>
															<s:property value="htmlcontent"  escapeHtml="false" />
														</p>
														<p>
															<s:if test="getIrpAttached(questionid).size()>0 || getIrpAttachedOther(questionid).size()>0">
																<div id="title1">
																<s:if test="getIrpAttached(questionid).size()>0">
																	<s:iterator value="getIrpAttached(questionid)" status="otherattstatus">
																		<img id="<s:property value="#otherattstatus.index" />" name="<s:property value="getAttNameStrForList(getIrpAttached(questionid))" />" onclick="checkMaxPic(this.src,this.name,this.id)" src="<%=rootPath%>file/readfile.action?fileName=<s:property value="updatePagePic(attfile)" />" style="width: 140px;height: 150px;cursor:url(<%=rootPath%>client/images/maxpic_03.cur),auto;" />
																		&nbsp;
																	</s:iterator><br/>
																</s:if>
																<s:if test="getIrpAttachedOther(questionid).size()>0">
																	<s:iterator value="getIrpAttachedOther(questionid)">
																		<a href="<%=rootPath%>file/readfile.action?fileName=<s:property value="attfile" />&fileTrueName=<s:property value="attdesc" />" title="<s:property value="attlinkalt" />" style="text-decoration: underline;" target="_blank"><s:property value="attdesc" /></a><br/>
																	</s:iterator>
																</s:if>
													            </div>
												            </s:if>
												         </p>
														 <span style="float:left;">
															<a href="javascript:void(0)" class="linkbh14" style="font-size: 12px;" onclick="endorseAnswer(<s:property value="questionid"/>)" >赞同(<font id="mote<s:property value="questionid"/>"><s:property value="getMotetread(questionid)" /></font>)</a>&nbsp;
															<a href="javascript:void(0)" class="linkbh14" style="font-size: 12px;" onclick="opposeAnswer(<s:property value="questionid"/>)" >反对(<font id="oppo<s:property value="questionid"/>"><s:property value="getOppose(questionid)" /></font>)</a>&nbsp;
															<a href="javascript:void(0)" class="linkbh14" style="font-size: 12px;" onclick="replyComment(<s:property value="questionid"/>)" id="commentquest<s:property value="questionid"/>" >追问(<s:property value="findRCNum(questionid)" />)</a>
														</span>
														<h1>
															  回答者：
													  	     <s:property value="getShowPageViewNameByUserId(cruserid)" />
													  	     <%if(loginUser.isQuestionManager()){ %>
													  	     &nbsp;&nbsp;<a class="linkc12" href="javascript:void(0)" onclick="deleteAnswer(<s:property value="questionid"/>)" >删除</a>
													  	     <%} %>
														</h1>
														<div style="text-align: left;margin-top: 1%;" id="consent<s:property value="questionid" />"><s:property value="getUsernameByQuestionid(questionid)" escapeHtml="false" /></div>
															<ul style="width: 800px !important">
																<li id="replyclist<s:property value="questionid"/>" style="background-color: #FCFCFC;"></li>
																	<li id="compllethecombox<s:property value="questionid"/>" style="text-align: left;padding: 0% 0 0% 2%;display: none;background-color: #FCFCFC;">
																		<div><textarea style="width: 750px;border-radius: 5px;border: 1px solid #cbcbd2;padding-left: 10px;padding-top: 5px;"cols="100%;" id="ctcbtext<s:property value="questionid"/>" placeholder="请输入追问内容..."></textarea></div>
																		<div style="text-align: right;padding:2% 5% 2% 0;">
																			<a class="inputButton" style="color:#fff;padding: 4px 10px;" hideFocus="true" title=""  href="javascript:void(0);" onclick="replyCommentV(<s:property value="questionid"/>,<s:property value="cruserid"/>,'<s:property value="getShowPageViewNameByUserId(cruserid)"/>')" style="margin: 0 1.5% 1% 0;">
																				提交
																			</a>   
																		</div>
																	</li>
															</ul>														
														</div>
													</div>
											</s:else>
										</div>
									</s:iterator>
								</div>
								<!-- 最佳答案结束 -->
								<!-- 专家答案开始 -->
								<div id="expertAnswer" style="margin-left:70px;">
									<s:if test="irpQuestion.isexpertanswer==1">
										<div class="tit" style="margin-bottom:0;background-image:url('<%=rootPath%>view/images/expert.png')">专家答案:</div>
											<s:iterator value="listExpertQuestion">
											<div id="question<s:property value="questionid"/>ofanswer">
												<div class="main_fed" style="font-size: 14px;word-break:break-all;padding:0 13px;">
													<p class="source_fed"
														id="otheranswer<s:property value="questionid"/>">
														<s:property value="htmlcontent" escapeHtml="false" />
													</p>
													<p>
														<s:if test="getIrpAttached(questionid).size()>0 || getIrpAttachedOther(questionid).size()>0">
												        <div id="title1">
															<s:if test="getIrpAttached(questionid).size()>0">
																<s:iterator value="getIrpAttached(questionid)" status="otherattstatus">
																
																	<img id="<s:property value="#otherattstatus.index" />" name="<s:property value="getAttNameStrForList(getIrpAttached(questionid))" />" onclick="checkMaxPic(this.src,this.name,this.id)" src="<%=rootPath%>file/readfile.action?fileName=<s:property value="updatePagePic(attfile)" />" style="width: 140px;height: 150px;cursor:url(<%=rootPath%>client/images/maxpic_03.cur),auto;" />
																	&nbsp;
																</s:iterator><br/>
															</s:if>
															<s:if test="getIrpAttachedOther(questionid).size()>0">
																<s:iterator value="getIrpAttachedOther(questionid)">
																<a href="<%=rootPath%>file/readfile.action?fileName=<s:property value="attfile" />&fileTrueName=<s:property value="attdesc" />" title="<s:property value="attlinkalt" />" style="text-decoration: underline;" target="_blank"><s:property value="attdesc" /></a><br/>
																</s:iterator>
															</s:if>
												         </div>	
														 </s:if>
												      </p>
													  <div class="action_mfed clearfix"
														 style="text-align:right;padding-top:10px;">
														  <span style="float:left;">
															<a href="javascript:void(0)" class="linkbh14" style="font-size: 12px;" onclick="endorseAnswer(<s:property value="questionid"/>)" >赞同(<font id="mote<s:property value="questionid"/>"><s:property value="getMotetread(questionid)" /></font>)</a>&nbsp;
															<a href="javascript:void(0)" class="linkbh14" style="font-size: 12px;" onclick="opposeAnswer(<s:property value="questionid"/>)" >反对(<font id="oppo<s:property value="questionid"/>"><s:property value="getOppose(questionid)" /></font>)</a>&nbsp;
															<a href="javascript:void(0)" class="linkbh14" style="font-size: 12px;" onclick="replyComment(<s:property value="questionid"/>)" id="commentquest<s:property value="questionid"/>" >追问(<s:property value="findRCNum(questionid)" />)</a>
														  </span>
													  	<s:property value="getShowPageViewNameByUserId(cruserid)" />
														&nbsp;回答于
														<s:date name="crtime" format="yyyy-MM-dd HH:mm" />
														<%if(loginUser.isQuestionManager()){ %>
														&nbsp;<a class="linkc12" href="javascript:void(0)" onclick="deleteAnswer(<s:property value="questionid"/>)" >删除</a>
														<%} %>
													  </div>
													  <div style="text-align: left;margin-top:1%;" id="consent<s:property value="questionid" />"><s:property value="getUsernameByQuestionid(questionid)" escapeHtml="false" /></div>
													  <div>	
													  <div id="bestAnswerBtn">
													  	<ul style="width: 800px !important">
															<s:if test="irpQuestion.status==null || irpQuestion.status==0 && irpQuestion.cruser==loginUsername">
																<li  id="id" style="text-align: left;padding: 0 0 2% 0;">
																
																<a hideFocus="true"
																	title="" href="javascript:void(0);" id="setBestAnswer"
																	onclick="setBestAnswer(<s:property value="questionid"/>,<s:property value="cruserid"/>)">设为最佳答案</a>
																	
																</li>
															</s:if>
															<li id="replyclist<s:property value="questionid"/>" ></li>
															<li id="compllethecombox<s:property value="questionid"/>" style="text-align: left;padding: 0% 0 0% 0;display: none;">
																<div><textarea cols="100%;"style="width: 750px;border-radius: 5px;border: 1px solid #cbcbd2;padding-left: 10px;padding-top: 5px;" id="ctcbtext<s:property value="questionid"/>" placeholder="请输入追问内容..."></textarea></div>
																<div style="text-align: right;padding:2% 5% 2% 0;">
																	<a  class="inputButton" style="color:#fff;padding: 4px 10px;" hideFocus="true" title=""  href="javascript:void(0);" onclick="replyCommentV(<s:property value="questionid"/>,<s:property value="cruserid"/>,'<s:property value="getShowPageViewNameByUserId(cruserid)"/>')" style="margin: 0 3.5% 0 0;">
																		提交
																	</a>   
																</div>
															</li>
															</ul>
														</div>
													</div>
													<div class="master_mfed"></div>
												</div>
											</div>
										</s:iterator>
									</s:if>
								</div>
								<!-- 专家答案结束 -->
								<!-- 其他答案开始 -->
								<div class="cmt_fed" style="padding-top:0;">
									<div id="answerDiv"  style="padding-left:70px;">
										<div id="append">
											<b>其它<font>${num }</font>条答案</b>:
										</div>
										<s:iterator value="listQuestion">
											<div id="question<s:property value="questionid"/>ofanswer">
												<div id="otheranswer">
													<div id="otheranswer<s:property value="questionid"/>" style="font-size: 14px;word-break:break-all;">
														<p>
															<s:property value="htmlcontent" escapeHtml="false" />
														</p>
														<p>
															<s:if test="getIrpAttached(questionid).size()>0 || getIrpAttachedOther(questionid).size()>0">
																<div id="title1">
																	<s:if test="getIrpAttached(questionid).size()>0">
																		<s:iterator value="getIrpAttached(questionid)" status="otherattstatus">
																		
																			<img id="<s:property value="#otherattstatus.index" />" name="<s:property value="getAttNameStrForList(getIrpAttached(questionid))" />" onclick="checkMaxPic(this.src,this.name,this.id)" src="<%=rootPath%>file/readfile.action?fileName=<s:property value="updatePagePic(attfile)" />" style="width: 140px;height: 150px;cursor:url(<%=rootPath%>client/images/maxpic_03.cur),auto;" />
																			&nbsp;
																		</s:iterator><br/>
																	</s:if>
																	<s:if test="getIrpAttachedOther(questionid).size()>0">
																		<s:iterator value="getIrpAttachedOther(questionid)">
																		<a href="<%=rootPath%>file/readfile.action?fileName=<s:property value="attfile" />&fileTrueName=<s:property value="attdesc" />" title="<s:property value="attlinkalt" />" style="text-decoration: underline;" target="_blank"><s:property value="attdesc" /></a><br/>
																		</s:iterator>
																	</s:if>
																</div>
															</s:if>
														 </p>
														<div class="action_mfed clearfix"
															style="text-align:right; border-top:1px solid #eee; padding-top:0;">
															<span style="float:left;">
																<a href="javascript:void(0)" class="linkbh14" style="font-size: 12px;" onclick="endorseAnswer(<s:property value="questionid"/>)" >赞同(<font id="mote<s:property value="questionid"/>"><s:property value="getMotetread(questionid)" /></font>)</a>&nbsp;
																<a href="javascript:void(0)" class="linkbh14" style="font-size: 12px;" onclick="opposeAnswer(<s:property value="questionid"/>)" >反对(<font id="oppo<s:property value="questionid"/>"><s:property value="getOppose(questionid)" /></font>)</a>&nbsp;
																<a href="javascript:void(0)" class="linkbh14" style="font-size: 12px;" onclick="replyComment(<s:property value="questionid"/>)" id="commentquest<s:property value="questionid"/>" >追问(<s:property value="findRCNum(questionid)" />)</a>
																
															</span>
													  	    <s:property value="getShowPageViewNameByUserId(cruserid)" />
															&nbsp;回答于
															<s:date name="crtime" format="yyyy-MM-dd HH:mm" />
															<%if(loginUser.isQuestionManager()){ %>
															&nbsp;<a class="linkc12" href="javascript:void(0)" onclick="deleteAnswer(<s:property value="questionid"/>)">删除</a>
															<%} %>
														</div>
														<div style="text-align: left;margin-top:1%;" id="consent<s:property value="questionid" />"><s:property value="getUsernameByQuestionid(questionid)" escapeHtml="false" /></div>
													</div>
													<div >
														<div class="" id="bestAnswerBtn">
															<ul style="width: 800px !important">
																<s:if test="irpQuestion.status==null || irpQuestion.status==0 && irpQuestion.cruser==loginUsername">
																	<li id="id" style="text-align: left;padding: 0 0 2% 0;">																	
																		<a hideFocus="true" title="" href="javascript:void(0);"
																			id="setBestAnswer"
																			onclick="setBestAnswer(<s:property value="questionid"/>,<s:property value="cruserid"/>)">设为最佳答案</a>
																	</li>
																</s:if>
																<li id="replyclist<s:property value="questionid"/>"></li>
																<li id="compllethecombox<s:property value="questionid"/>" style="text-align: left;padding: 0 0 0 0;display: none;">
																	<div><textarea style="width: 750px;border-radius: 5px;border: 1px solid #cbcbd2;padding-left: 10px;padding-top: 5px;" cols="99%;" id="ctcbtext<s:property value="questionid"/>" placeholder="请输入追问内容..."></textarea></div>
																	<div style="text-align: right;padding:2% 5% 2% 0;">
																		<a  class="inputButton" style="color:#fff;padding: 4px 10px;"hideFocus="true" title=""  href="javascript:void(0);" onclick="replyCommentV(<s:property value="questionid"/>,<s:property value="cruserid"/>,'<s:property value="getShowPageViewNameByUserId(cruserid)"/>')" style="margin: 0 4.2% 0 0;">
																			提交
																		</a>   
																	</div>
																</li>
																</ul>
															</div>
														</div>
													</div>
												</div>
											</s:iterator>
											<div>
												<div align="right" class="clientpage" style="padding-top:0;">
													<ul style="width:780px;">
													<s:property value="pageHTML" escapeHtml="false" />
													</ul>
												</div>
											</div>
										</div>
									</div>
									<!-- 其他答案结束 -->
								</div>
								<!-- 答案div结束  -->
							</div>
						</div>
					</div>
					<!-- 问题主题结束-->
				</div>
			</div>
			<!--左侧内容结束-->
			<!--右侧内容-->
			<div style="float:right;width:310px;margin-left:10px;">
			<div class="wd_right">
				<div class="title1" style="margin-top:15px;"><h4><a href="javascript:void(0)">最热回答</a></h4></div>
		            <ul id="hottalkdocument">
		            </ul>
				<div class="title1"><h4><a href="javascript:void(0)">最新回答</a></h4></div>
		            <ul id="youlovedocument">
		            </ul>
		     <!-- 
				<div class="duo">
					<%--正在热议 --%>
					<dl id="hottalkdocument"></dl>
					<%--猜你喜欢--%>
					<dl id="youlovedocument"></dl>
					<%--分享排行 --%>
					<dl class="bz" id="sharedocument"></dl>
					<%--人气TOP10 --%>
					<dl class="pw" id="hotpersondocument"></dl>
				</div>
				 -->
				
			</div>
				<div class="title1"><h4><a href="javascript:void(0)">问答达人</a></h4></div>
	            <ul id="sharedocument">
	            </ul>
			</div>
				
			<!--右侧内容结束-->
			<!--尾部信息-->
			<s:include value="../include/client_foot.jsp" />
			<!--尾部信息end-->
		</div>
	</div>
</body>
</html>