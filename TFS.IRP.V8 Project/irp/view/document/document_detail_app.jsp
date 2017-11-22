<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<link href="<%=rootPath%>view/css/discuz.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=rootPath %>view/js/modernizr.custom.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.PrintArea.js"></script>
<script type="text/javascript">
var m_docid = <s:property value="irpDocument.docid" />;
$(function(){
	//初始化评论
	initConnPage();
	eliteDoc();
	authorOtherDoc();
	docRecommendList();
	docInformList();
	documentscore();
});

//初始化文章正文中的分页
function initConnPage(){
	$('#page_break .num li:first').addClass('on');
	$('#page_break .num a').click(function(){
		var _text= $.trim($(this).text());
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
}

//显示全部正文数据
function showAllData(){
	$('.collapse').each(function(){
		$(this).show();
	});
	$('.num').hide();
}

//精华知识
function eliteDoc(){
	var str=$.ajax({
		type:'post',
		url:'<%=rootPath%>document/elite_doc.action',
		dataType: "json",
		data:{'id':<s:property value="irpDocument.channelid" />},
		async: false,
		cache: false
	}).responseText;
	$('#eliteList').html(str);
}

//创建者其它知识
function authorOtherDoc(){
	var str=$.ajax({
		type:'post',
		url:'<%=rootPath%>document/author_other_doc.action',
		dataType: "json",
		data:{
			'personid': <s:property value="irpDocument.cruserid" />,
			'docid': m_docid
		},
		async: false,
		cache: false
	}).responseText;
	$('#otherList').html(str);
}

//知识专家推荐列表
function docInformList(){
	var str=$.ajax({
		type:'post',
		url:'<%=rootPath%>document/docinform_list.action',
		data:{
			'docid':m_docid
		},
		dataType: "json",
		async: false,
		cache: false 
	}).responseText;   
	$('#informList').html(str);
}

//知识评论列表
function docRecommendList(){
	var str=$.ajax({
		type:'post',
		url:'<%=rootPath%>phone/doccomment_list_app.action?token='+$('#tokens').val(),
		data:{
			'docid':m_docid,
			'channelid':<s:property value="irpDocument.channelid" />,
			'createuserid':<s:property value="irpDocument.cruserid" />,
		},
		dataType: "json",
		async: false,
		cache: false 
	}).responseText;   
	$('#commentList').html(str);
}

//发表评论
function addDocComment(){
	var replayUserId=$('#replayUserId').val();
  	var obj=$('#editor');
 	var conn= $('#editor').val();
	if($.trim(conn).length>0){
		$.dialog.confirm('您确定要评论这个知识吗？',function(){
			$.ajax({
				type:'post',
				url:'<%=rootPath%>phone/addrecommend_app.action?token='+ $('#tokens').val(),
				data:{
					'docid':m_docid,
					'recommend':$.trim(conn),
					'replayUserId':replayUserId,
				},
				success:function(msg){
					$.dialog.tips('发布评论成功',1,'32X32/succ.png',function(){
						docRecommendList();
						window.location.hash = 'commentView';
						$('#quoteInfo').html('');
						 $('#editor').val('');
						$('#commentCount').text(parseInt($('#commentCount').text())+1);
					});
			   	}
			});
		},function(){}); 
	}else{
		$.dialog.alert('请填写评论内容',function(){});
	} 
}

//知识推荐
function recommendDocument(){ 
	var result = $.ajax({
		url: '<%=rootPath%>site/findallmicroblogforcusjson.action',
		type:"post",
		data:{docid: m_docid},
		dataType: "json", 
	    async: false
	}).responseText; 
	$.dialog({
		title:'推荐',
		max:false,
		min:false,
		lock: true,
		resize: false,
		width:'500px',
		content:result,
		okVal:'推荐',
		ok:function(){ 
			var informdescs = $("#informdescform").val();
			if($.trim(informdescs).length>$('#informdesccount').val()){
		    	return false;
		    }else{
				$('#transdocument').find('#docid').val(m_docid);
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
		},
		cancelVal:'关闭',
		cancel:function(){}
	}); 
}

//知识推荐-选择人或者组织
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

//举报
function reportDocument(){
	var loadPage=$.ajax({
		url: '<%=rootPath%>microblog/documentinformrame.action', 
		data:{
			microblogid:m_docid
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
		cancel:function(){}
	}); 
}

<s:if test="#loginUser.isExpert()">
//加精
function essenceDocument(_objLink){
	var objShow = $(_objLink).find('span');
	var objHover = $(_objLink).find('label');
	if($.trim(objShow.html())=='加精'){
		var str=$.ajax({
			url: '<%=rootPath%>set/addclassial.action', 
			data:{'docid': m_docid},
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
			init : function(){},
			content:str,
			cancelVal:'关闭',
			okVal:'确定',
			cancel:function(){},
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
						success: function (result){
							$.dialog.tips('加精成功',1,'32X32/succ.png',function(){
								$('#essenceCount').text(parseInt($('#essenceCount').text())+1);
								objShow.html('已加精');
								objHover.html('取消');
								docInformList();
							});
						}
					});
				}	
			}
		});  
	}else if($.trim(objShow.html())=='已加精'){
		$.dialog.confirm('您确定要取消加精吗？',function(){
			$.ajax({
				type:'post',
				url:'<%=rootPath%>site/deletedocumentclassicl.action',
				data:{'informid': m_docid},
				success:function(msg){
					if(msg!="0"){
						$.dialog.tips('取消加精成功',1,'32X32/succ.png',function(){
							$('#essenceCount').text(parseInt($('#essenceCount').text())-1);
							objShow.html('加精');
							objHover.html('加精');
							docInformList();
						});
					} 
				}
			}); 
		},function(){}); 
	}
}
</s:if>

//收藏
function collectionDocument(_objLink){  
	var objShow = $(_objLink).find('span');
	var objHover = $(_objLink).find('label');
	if($.trim(objShow.html())=='收藏'){
		$.ajax({
			type:'post',
			url:'<%=rootPath%>site/addfocusdoclink.action',
			data:{'documentid': m_docid},
			success:function(msg){ 
				if(msg=='1'){
					$.dialog.tips('收藏成功',1,'32X32/succ.png',function(){
						$('#collectionCount').text(parseInt($('#collectionCount').text())+1);
						objShow.html('已收藏');
						objHover.html('取消');
					});
				} 
			}
		});  
	}else if($.trim(objShow.html())=='已收藏'){
		$.dialog.confirm('您确定要取消收藏吗？',function(){
			$.ajax({
				type:'post',
				url:'<%=rootPath%>site/deletefocusdoclink.action',
				data:{'documentid': m_docid},
				success:function(msg){
					if(msg=='1'){
						$.dialog.tips('取消收藏成功',1,'32X32/succ.png',function(){
							$('#collectionCount').text(parseInt($('#collectionCount').text())-1);
							objShow.html('收藏');
							objHover.html('收藏');
						});
					} 
				}
			}); 
		},function(){}); 
	}
}

//订阅知识
function subscribeDocument(_objLink){
	var objShow = $(_objLink).find('span');
	var objHover = $(_objLink).find('label');
	if($.trim(objShow.html())=='订阅'){
		$.ajax({
			type:'post',
			url:'<%=rootPath%>subscribe/add_subscribe_doc.action',
			data:{'objId': m_docid},
			success:function(msg){ 
				if(msg=="1"){   
					$.dialog.tips('订阅成功',1,'32X32/succ.png',function(){
						objShow.html('已订阅');
						objHover.html('取消');
					});
				} 
			}
		});
	}else if($.trim(objShow.html())=='已订阅'){
		$.dialog.confirm('您确定要取消订阅吗？',function(){
			$.ajax({
				type:'post',
				url:'<%=rootPath%>subscribe/del_subscribe_doc.action',
				data:{'objId': m_docid},
				success:function(msg){ 
					if(msg=='1'){   
						$.dialog.tips('取消订阅成功',1,'32X32/succ.png',function(){
							objShow.html('订阅');
							objHover.html('订阅');
						});
					} 
				}
			});
		});
	}
}
<s:if test="irpDocument.irpMostTreadDing==null && irpDocument.irpMostTreadCai==null">
//顶
function ding(_objLink){
	var objShow = $(_objLink).find('span');
	var objHover = $(_objLink).find('label');
	if($.trim(objShow.html())=='顶' && $.trim(objHover.html())=='顶'){
		$.ajax({
			type:'post',
			url:'<%=rootPath%>phone/dingdocument.action?token='+$('#tokens').val(),
			data:{'docid': m_docid},
			dataType:'json',
			async:false,
			cache:false,
			success:function(msg){ 
				if(msg=="1"){
					$.dialog.tips('已顶成功',1,'32X32/succ.png',function(){
						$('#dingCount').text(parseInt($('#dingCount').text())+1);
						objShow.html('已顶');
						objHover.html('已顶');
						$(_objLink).removeAttr("onclick");
						$(_objLink).next().remove();
					});
				} 
			}  	
		});
	}else{
		$.dialog.alert("已顶",function(){});
	}
}

//踩
function cai(_objLink){
	var objShow = $(_objLink).find('span');
	var objHover = $(_objLink).find('label');
	if($.trim(objShow.html())=='踩' && $.trim(objHover.html())=='踩'){
		$.ajax({
			type:'post',
			url:'<%=rootPath%>phone/caidocument.action?token='+$('#tokens').val(),
			data:{'docid':m_docid},
			dataType:'json',
			async:false,
			cache:false,
			success:function(msg){ 
				if(msg=="1"){
					$.dialog.tips('已踩成功',1,'32X32/succ.png',function(){
						$('#caiCount').text(parseInt($('#caiCount').text())+1);
						objShow.html('已踩');
						objHover.html('已踩');
						$(_objLink).removeAttr("onclick");
						$(_objLink).prev().remove();
					});
				} 
			}  	
		});
	}else{
		$.dialog.alert("已踩",function(){});
	}
}
</s:if>
<s:if test="isDelete=='true'">
//删除文档
function deleteDocument(){
	$.dialog.confirm('您确定要删除这篇知识吗？',function(){
		$.ajax({
			type:'post',
			url:'<%=rootPath%>site/clientdeletedocument.action',
			data:{'docid':m_docid},
			success: function(msg){
				if(msg=='1'){
					$.dialog.tips('删除知识成功',0.5,'32X32/succ.png',function(){
						if(window.opener){
							window.opener.location.reload(true);
						}
						window.close();
					});  
				} 
			}
		});
	},function(){}); 
}
</s:if>
<s:if test="isPriant=='true'">
function documentPrint(){
	console.info($('#printConn').html());
	$('#printConn').printArea({
		extraCss : '',
		retainAttr : ["class", "id", "style", "on"],
		extraHead: '<meta charset="utf-8" />,<meta http-equiv="X-UA-Compatible" content="IE=edge"/>'
	});
}
</s:if>

//引用专题
function subjectDocument(){
	var contentresult = $.ajax({
		url:"<%=rootPath%>site/catchknowto.action?irpDocument.docid="+m_docid,
		async: false,
	    cache: false
	}).responseText;
	$.dialog({
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
				url:"<%=rootPath%>site/addqiyesub.action",
				data:{"irpDocument.docid":m_docid},
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
		cancel:function(){}
	});
}

//查询知识的评分
function documentscore(){
	var str=$.ajax({
		type:'post',
		url:'<%=rootPath%>site/finddocumentscore.action',
		data:{'docid':m_docid},
		dataType: "json",
		async: false,
	    cache: false 
	}).responseText;   
    $('#documentscore').html(str);   
}

//知识评分
function teststars(){
	var score= document.getElementById("stars2-input").value ;
	//添加一条记录返回将评分这个div隐藏
	if(parseInt(score)>0){
		$.dialog.confirm('您确定对知识进行评分吗？',function(){
			$.ajax({
				type:'post',
				url:'<%=rootPath%>site/addscore.action',
				data:{
					'docid': m_docid,
					'score':score	
				},
				success:function(msg){ 
					if(msg=="1"){   
						documentscore();
					} 
				}
			}); 
		 },function(){}); 
	}else{
		$.dialog.alert('请选择评分分数...',function(){});
	}
}

<s:if test="isHistoryversion=='true'">
/**
* 查看历史版本
*/
function documentHistory(){
	window.open("<%=rootPath%>document/document_history_list.action?docid="+m_docid);
}
</s:if>

<s:if test="isCrUser=='true'">

function documentLogs(_pageNum){
	var contentresult = findDialogConn();
	$.dialog({
		id: 'docLogsD',
		title:'阅读者记录',
		max:false,
		min:false,
		lock:true,
		width:'450px',
		resize: false,
		content:contentresult,
		cancelVal:"关闭",
		cancel:function(){}
	});
}

function docLogsPage(_pageNum){
	var contentresult = findDialogConn(_pageNum);
	$.dialog({id:'docLogsD'}).content(contentresult);
}

//获得窗口内容
function findDialogConn(_pageNum){
	if(_pageNum<1){
		_pageNum = 1;
	}
	var contentresult = $.ajax({
		url: '<%=rootPath%>document/document_logs_list.action',
		async: false,
	    cache: false,
	    data:{
	    	docid : m_docid,
	    	pageNum : _pageNum
	    }
	}).responseText;
	return contentresult;
}
</s:if>

function searchInfo1(searchInfo){  
	searchtype = 5;  
	if(searchInfo.length>38){
		searchInfo = searchInfo.substring(0,37);	
	}
	var eacapeInfo = encodeURI(searchInfo);
    window.open('<%=rootPath%>phone/searchcontentofsolr.action?searchInfo='+eacapeInfo+'&searchtype='+searchtype+'&token='+$('#tokens').val()); 
}

function similarityDocument(){
	var contentresult = $.ajax({
		url:'<%=rootPath%>document/similarity_document.action?docid='+m_docid,
		async: false,
	    cache: false
	}).responseText;
	$.dialog({
		title:'相似知识',
		max:false,
		min:false,
		lock:true,
		resize: false,
		content:contentresult,
		cancelVal:"取消",
		cancel:function(){}
	});
}

</script>
  <head>
    <title>My JSP 'document_detail_app.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css">
  </head>
  <body>
  <input type="hidden" value="<s:property value='tokens'/>" id="tokens"/>
   <s:set var="cruser" value="getIrpUser(irpDocument.cruserid)" />
<section class="mainBox" style="width:95%">
	<section   class="details"  style="float:none;width:100%">
    	<a id="documenttop" name="documenttop"></a>
        <section class="text">
        	<article class="straight">
	       		<div id="printConn">
	            	<h1><s:property value="irpDocument.doctitle" /></h1>
	                <table class="textInfo" style="margin-left: 0px;">
	                	<tr>
	                	
	                		<td>发布时间：<s:date name="irpDocument.docpubtime" format="yyyy-MM-dd HH:mm" /></td>
	                	</tr>
	                	<tr>
	                	<td>创建者：<a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="#cruser.userid" /></s:url>" target="_blank"><s:property value="#cruser.showName" /></a></td>
	                	</tr>
	                	<tr>
	                		<td>点击量：<label id="caiCount"><s:property value="irpDocument.hitscount" /></label></td>
	                	</tr>
	                </table>
	                <ul class="list4">
	                	<li>
	                		<section style="padding-left:0px;">
	                			<p style="font-size:14px;line-height:24px;color:#666;text-indent:0px;"><s:property value="irpDocument.docabstract" /></p>
	                		</section>
	                		<div class="clear"></div>
	                	</li>
	                </ul>
	                <div id="partOfDoc" class="documenttxt">
						<s:property value="irpDocument.dochtmlcon" escapeHtml="false" />
					</div>
				</div>
				<s:if test="attacheds!=null && attacheds.size()>0">
				<div class="annex">
                	<font>相关附件：</font>
                	<span><table>
					<s:iterator value="attacheds">
						<tr>
							<td><s:if test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@JPG_FIELD_NAME==typeid">
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
							<s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@ID_FIELD_NAMEOTHER==typeid">
								<span style="display: block;background-image: url(<%=rootPath %>client/images/att.png); width:40px; height:43px; background-position: -210px -10px;"></span>
							</s:elseif></td>
							<td style="padding-left: 20px;">
								<a href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>">
									<s:property value="attdesc" />
								</a>
							</td>
							<td style="padding-left: 20px;">
								<s:if test="isFuJian=='true'">
								<a href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>">下载</a>
								</s:if>
								<s:if test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@WORD_FIELD_NAME==typeid">
								| <a target="_blank" href="<%=rootPath%>file/online_preview.action?fileName=<s:property value='attfile'/>">在线浏览</a>
								</s:if>
								<s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@JPG_FIELD_NAME==typeid">
								|  <a target="_blank" href="<%=rootPath%>file/inlinereadfile.action?fileName=<s:property value='attfile'/>">在线浏览</a>
								</s:elseif>
							</td>
						</tr>
					</s:iterator>
					</table></span>
                </div>
				</s:if>
            </article>
            
            <div class="tags">
            	<font>标签：</font>
            	<s:iterator value="irpDocument.dockeywords.split(',')" status="st" var="as">
				<a href="javascript:void(0)" style="text-decoration: none;"><s:property value="#as" /></a>&nbsp;&nbsp;
  				</s:iterator>
            </div>
            
            <div class="sociality">
            	
            	
            	<s:if test="irpDocument.irpMostTreadDing==null && irpDocument.irpMostTreadCai==null">
            	<a href="javascript:void(0)" onclick="ding(this)"><span>顶</span><label>顶</label></a>
            	<a href="javascript:void(0)" onclick="cai(this)"><span>踩</span><label>踩</label></a>
            	</s:if>
            	<s:elseif test="irpDocument.irpMostTreadDing!=null">
            	<a href="javascript:void(0)"><span>已顶</span><label>已顶</label></a>
            	</s:elseif>
            	<s:elseif test="irpDocument.irpMostTreadCai!=null">
            	<a href="javascript:void(0)"><span>已踩</span><label>已踩</label></a>
            	</s:elseif>
            </div>
        </section>
        <section id="commentView" class="discussions">
        	<div class="title">相关评论</div>
        	<div  id="commentList"></div>
        	<s:if test="isXiangGuan=='true'">
          	<div class="discussing">
            	<div id="discuss">我要评论</div>
            	<div id="quoteInfo" class="quoteInfo" style="display:none;"></div>
                <div class="someText"><textarea id="editor" name="editor" style="width: 100%;height: 10%"></textarea></div>
                <div style="padding-bottom: 5%;" class="sub"><input type="button" onclick="addDocComment()" value="发布评论"/></div>
            </div>
            </s:if>
        </section>
    </section>
</section>
  </body>
</html>
