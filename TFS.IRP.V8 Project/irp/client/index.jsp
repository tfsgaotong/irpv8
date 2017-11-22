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
<title>企业社会化智力资源管理系统</title>
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
<link rel="stylesheet" href="<%=rootPath%>client/css/lrtk.css" type="text/css"/>
<style type="text/css">
#shareul li{
	display: block;
	width:100px;
	float: left;
	padding-left: 0px;
	text-align: center;
}
#shareul li a:hover{
	display: block;
	width:100px;
	background-color: #fff;
	text-align: center;
}
#daohangul li a{
	overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
</style>
<link rel="Bookmark" href="<%=rootPath %>client/images/24pinico.ico" />
<link rel="Shortcut Icon" href="<%=rootPath %>client/images/24pinico.ico" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/client/css/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/client/css/icon.css" />
<link href="<%=rootPath%>/client/css/oacss.css" rel="stylesheet" type="text/css" />
<jsp:include page="include/client_skin.jsp" />
<style type="text/css">
body{
	behavior:url(<%=rootPath%>client/js/hover.htc);
}
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
.menuTd a{
	float: left;
}
.menuTd a .uploadify{
	float: left;
}  
.wtgy {
    height: 28px;
    line-height:28px;
    border: 1px solid rgb(209, 209, 209);
    color: rgb(102, 102, 102);
    padding: 0px 5px;
}
</style>
<script type="text/javascript" src="<%=rootPath%>client/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/swfobject.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/jquery.Jcrop.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/jQuery.UtrialAvatarCutter.js"></script>

</head>
<body onload="selectFocusUser()">

<script language="JavaScript" type="text/javascript">
//分页评论变量
var microblogidpage =0;
//微知评论“评论”id +
var replycommentidpage=0;
//分享微知字数
var microlognumber = 0;
//评论微知字数
var microblgnumberComment = 0;
//一共能上传多少图片
var uploadpicinall = 3;

var startloadfilenum = 0; 
//微知图片
var micrpicall="";
function searchInfo1(searchInfo){ 
	searchtype = 5;  
	if(searchInfo.length>38){
		searchInfo = searchInfo.substring(0,37);	
	}
	var eacapeInfo = encodeURI(searchInfo);
    window.open('<%=rootPath%>solr/searchcontentofsolr.action?searchInfo='+eacapeInfo+'&searchtype='+searchtype); 
}
$(function(){
	selected('person');
	//加载个人基本信息
	$.ajax({
		type:"get",
		url:"<%=rootPath%>microblog/loadUserInfo.action",
		async:false,
		cache:false,
		success:function(callback){
			$('.right').prepend(callback);
			
		}
	});
	var choicetab ='<s:property value="skiptab" />';
	if(choicetab=='collect'){
		//收藏选项
		$('#document_collection').click();
	}else{
		//微知选项
		hitMicroblogTab();
	}
	//记载评论微知字数
	$.ajax({
		type:"get",
		url:"<%=rootPath%>microblog/initMicroblogCommentNumberWord.action",
		async:false,
		cache:false,
		success:function(msg){
			microblgnumberComment=parseInt(msg);
		}
	});
    $.ajax({
		type:"post",
		url:'<%=rootPath%>user/sendmicrotypegroupitem.action',
		cache:false,
		async:false,
		success:function(content){
			$('#microblogtypeitem').html(content);
		}
	});
    $('#microblogtype').menubutton({   
        iconCls: 'icon-edit',   
        menu: '#microblogtypeitem'  
    }); 
    $('#microblogtypetext').val(0);
    
	//得到导航栏
    personallchannel();
	//同时发送请求，得到知识的数量
	//mydocumentcountajax();
	//同时发送请求，得到收藏数量
	//mydoccollectioncountajax();
	//正在热议
	hottalkdocument();
	//猜你喜欢
	youlovedocument();
	//分享排行
	sharedocument();
	//人气TOP
	hotpersondocument();
	//加载微知上传字数
	initMicroShareNum();
});
/**
 * 加载微知字数
 */
function initMicroShareNum(){
	$.ajax({
		type:"get",
		url:"<%=rootPath%>microblog/initMicroblogNumberWord.action",
		async:false,
		cache:false,
		success:function(msg){
			microblognumber = parseInt(msg);
			$('#microblogContentCount_01').text(microblognumber);
		}
	});
}
var microbloguploadfilenum = 1;
//加载上传组件
function loadUploadPic(){
	//加载上传图片张数
	$.ajax({
		type:"get",
		url:"<%=rootPath%>microblog/initMicrobloguploadpicnum.action",
		async:false,
		cache:false,
		success:function(msg){
			uploadpicinall = parseInt(msg);
		}
	});
	$("#microblogPicFile").uploadify({
		'auto': true,
		'multi' : false,
		'swf' : '<%=rootPath%>client/js/uploadify.swf',
		'uploader' : '<%=rootPath%>microblog/microblogContentPic.action;jsessionid=<%=session.getId() %>',
		'formData':{
            'userId':'1'
        },
		'queueID' : 'fileList',
		'fileObjName' : 'picFile',
		'buttonText' : '图片',
		'width' : "30",
		'height' : "16",
		'removeCompleted': true,
		'removeTimeout': 0.1,
		'uploadLimit':uploadpicinall,
		'queueSizeLimit': 3,      //允许同时上传文件数量
		'fileSizeLimit' : "1000MB",
		'fileTypeExts': '*.jpg;*.jpeg;*.gif;*.png',
        'fileTypeDesc': '图片文件',
        'onUploadStart' : function(file){
        	$.dialog.tips('图片上传中...',600,'loading.gif');
        }, 
		'onUploadSuccess' : function(file, data, response){	
			$.dialog.tips('图片上传完毕',1,'32X32/succ.png',function(){
				var imgStr = '<%=rootPath%>file/readfile.action?fileName='+data;	
				var microspicid = data.split(".")[0];
				$('#microblogSharePic').css('display','block');
				var micropicdesc = microspicid+"picdesc";
				$('#microblogSharePiclittle').append("<div style=\"width:150px;height:200px;display:inline;\"><img id="+microspicid+" style=\"margin-left: 5px;margin-top: 5px;\" src=\""+imgStr+"\" width=\"150px\" height=\"200px\" /><a  id="+micropicdesc+"  style=\"background-color: black;color: white;position: absolute;z-index:10;margin-left: -25px;margin-top: 183px;\"  href=\"javascript:void(0)\" onclick=\"micrPicCancel('"+data+"')\">取消</a></div>");
				
				microbloguploadfilenum++;
				micrpicall+=data+";";
				$('#uploadfilefont').css({display:'block'});
			   	startloadfilenum = parseInt(startloadfilenum+1);
	            if(startloadfilenum>=uploadpicinall){
	            	$("#microblogPicFile").uploadify('disable',true);
				}else{
					$("#microblogPicFile").uploadify('disable',false);
				}
				$('#uploadfilefont').text("共"+startloadfilenum+"张,还能上传"+parseInt(uploadpicinall-startloadfilenum)+"张");
			});
		},
		'onUploadError': function (file, errorCode, errorMsg, errorString) {
			$.dialog.tips('数据加载完毕',1,'32X32/fail.png',function(){
				alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
			});
		}
	});
}
/**
 *公开
 */
function microblogtypeItemPublic(_text){
	$('#microblogtype').menubutton({   
	    iconCls: 'icon-edit',   
	    menu: '#microblogtypeitem', 
	    text:_text
	});  
	$('#microblogtypetext').val(0);
}
/**
 * 私密
 */
function microblogtypeItemPrivate(_text){
	$('#microblogtype').menubutton({   
	    iconCls: 'icon-edit',   
	    menu: '#microblogtypeitem', 
	    text:_text
	});
	$('#microblogtypetext').val(1);
}
/**
 * 其它
 */
function microblogtypeItemOther(_text){
	$('#microblogtype').menubutton({   
	    iconCls: 'icon-edit',   
	    menu: '#microblogtypeitem', 
	    text:_text
	});  
	$('#microblogtypetext').val(2);
}
/*发布；分享*/
function  publish(){
	$('#publishclassselec').unbind('click');
	var MicroPicValue_01="";
	var micrarray; 
   	//判断分享的内容是否存在图片(存在则存入正文图片)
	   if(micrpicall!=""){
		  $.ajax({
			  type:"post",
			  url:"<%=rootPath%>microblog/microblogContentPicTrue.action",
			  data:{
				  microbloguploadpic:micrpicall
			  },
			  cache:false,
			  async:false,
			  success:function(disposemsg){
			     micrarray = disposemsg.split(" ");
			       for(var i =0;i<micrarray.length-1;i++){
			    	   if(i==micrarray.length-2){
			    		   MicroPicValue_01+="<img src=\"<%=rootPath %>file/readfile.action?fileName="+micrarray[i]+"\" id="+i+" name="+micrarray.toString()+"    height=\"150px\" width=\"140px\" style=\"float:none;margin-right:5px;cursor:url(<%=rootPath%>client/images/maxpic_03.cur),auto;\"  onclick=\"checkMaxPic(this.src,this.name,this.id)\" />";   
			    	   }else{
			    		   MicroPicValue_01+="<img src=\"<%=rootPath %>file/readfile.action?fileName="+micrarray[i]+"\" id="+i+" name="+micrarray.toString()+"   height=\"150px\" width=\"140px\" style=\"float:left;margin-right:5px;cursor:url(<%=rootPath%>client/images/maxpic_03.cur),auto;\" onclick=\"checkMaxPic(this.src,this.name,this.id)\" />";
			    	   }
			    	    	
			       }
			  }
		  });
	   }
    var publish_info ="";
    if(MicroPicValue_01!="" && $.trim($('#publish_info').val())==""){
    	$('#publish_info').val("#分享图片#")
    }
    publish_info =  $('#publish_info').val().replace("<","&lt;").replace(">","&gt;");
	microblogInfoControl(publish_info);
    if($.trim(publish_info).length>microblognumber){
    	$('#publishclassselec').bind('click','publish()');
    	microbloguploadfilenum=1;
    	return false;
    }else if($.trim(publish_info).length<=0){
    	  $.dialog.tips("微知内容不能为空",0.3,"alert.gif");
    	  $('#publishclassselec').bind('click','publish()');
    	  microbloguploadfilenum=1;
    	return false;
    }else{
	var microblog_type;
	var microblog_text= $('#microblogtypetext').val();
	var microbloggroup = $.trim($('#microblogtype').text());
		$.ajax({
			type:"POST",
			url:'<%=rootPath%>microblog/microblogShare.action',
			cache:false,
			async:false,
			data:{
				publishInfo:publish_info+"<br/>"+MicroPicValue_01,
				microblogType:microblog_text,
				microbloggroup:microbloggroup
			},
			success:function(callback){
				$('#publish_info').val("");
				if(callback!=null){
					$.dialog.tips('分享成功',1,'32X32/succ.png',function(){
						var mymicroblogcount = $('#mymicroblogcount').text();
						if(mymicroblogcount==""){
							mymicroblogcount = 0;
						}
						$('#mymicroblogcount').text(parseInt(mymicroblogcount)+1);
						$('#microblogSharePiclittle').empty();
						$('#microblogContentCount_01').text(microblognumber);
						microbloguploadfilenum=1;
						MicroPicValue_01="";
						micrpicall="";
						 $('#uploadfilefont').css({display:'none'});
						$('#microblogSharePic').css('display','none');
						 startloadfilenum = 0; 
						if($("#microblog_menu").attr("class")=="hover"){
							$('#maxdiv').prepend(callback);		
						}
						$('#publishclassselec').bind('click','publish()');
					});
					
					
				}else{
					microbloguploadfilenum=1;
					$('#microblogSharePic').css('display','none');
					$.dialog.tips('分享失败',1,'32X32/fail.png'); 
					$('#publishclassselec').bind('click','publish()');
				}
			},
			error:function(){
				microbloguploadfilenum=1;
				$('#microblogSharePic').css('display','none');
				$.dialog.tips('分享失败',1,'32X32/fail.png');
				$('#publishclassselec').bind('click','publish()');
			}
		});
    }
}
/*取消上传图片*/
function micrPicCancel(_cancelpic){
	var disposecancelpic = _cancelpic.split(".")[0];
	var micropidcesc = "#"+disposecancelpic+"picdesc";
	var cancelpicdiv = "#"+disposecancelpic;
	
	$.ajax({
		type:"POST",
		url:"<%=rootPath %>microblog/microblogPicDelete.action",
		data:{
			microblogPic:_cancelpic
		},
		async:false,
		success:function(msg){
		  if(msg==1){
			$(cancelpicdiv).fadeOut();
			$(micropidcesc).remove();
			   startloadfilenum = parseInt(startloadfilenum-1);
			   if(startloadfilenum==0){
				   $('#uploadfilefont').css({display:'none'});
				   $('#microblogSharePic').css('display','none');
				}
	            if(startloadfilenum>=uploadpicinall){
	            	$("#microblogPicFile").uploadify('disable',true);
				}else{
					$("#microblogPicFile").uploadify('disable',false);
					$("#microblogPicFile").uploadify('destroy');
					microbloguploadfilenum=1;
				}
				$('#uploadfilefont').text("共"+startloadfilenum+"张,还能上传"+parseInt(uploadpicinall-startloadfilenum)+"张");	
			}  
		}
		
	});
	
}
//默认加载（系统为你挑选的用户）
function selectFocusUser(){
	//加载上传图片组件
	loadUploadPic();
	$('#microblogPicFile_font').css({
		display:'none'
	});
	 var recommendstatus = $.ajax({
       url: "<%=rootPath%>user/checkingrecommend.action",
       async: false,
       cache:false
               }).responseText;
	
	if(recommendstatus==1){
		//判断当前用户关注是否为0
		$.ajax({
			type:"post",
			url:"<%=rootPath%>microblog/loadLoginFoucs.action",
			cache:false,
			success:function(msg){
				if(msg<=1){
					//加载关注人列表（？） 默认选择关注人
					var loadPage=$.ajax({
						url: '<%=rootPath%>microblog/selectFocusUser.action', 
						type:"post", 
					    async: false,
					    cache: false
					}).responseText; 
					 $.dialog({
						 title:'由于您未关注任何好友，系统向您推荐如下好友。',
							max:false,
							min:false,
							lock:true,
							resize: false,
							width:460,
							height:350,
							content:loadPage,
							okVal:'全部关注',
							ok:function(){
								var defaultallfocusval = "";
								$("input[name='defaultallfocusid']").each(function(){
								  defaultallfocusval = defaultallfocusval + $(this).val()+",";
								});
								if(defaultallfocusval!=""){
									$.ajax({
										type:'post',
										url:'<%=rootPath%>microblog/okMicroblogFocusdefault.action',
										data:{
											defaultallfocusval:defaultallfocusval
										},
										cache:false,
										async:false,
										success:function(){
											window.location.reload();
										}
									});
								}
							},
							cancelVal:'不再提示',
							cancel:function(){
								$.ajax({
									type:"post",
							        url:"<%=rootPath%>user/changeRecommendStatus.action",
							        async:false,
							        cache:false,
									success:function(status){
										if(status==1){
											return true;
										}
									}
								});
							}
					 });
				}else{
					return true;
				}
			}
		});
	}
	

	
} 

//检索关注的人员
function searchFocusUser(_blogcontent){
	if(_blogcontent.indexOf('@')>0){
         return true;
	}else{
		return true;
	}
}
//转发
function transpond(_showname,_microblogid,_userid,_transpondid,_isdel){
	var commentlimits = "";
	if(_isdel==2){
		$.dialog.tips("抱歉，此微知为非法微知，无法进行转发!",1.5,"tips.gif");
	}else if(_isdel==1){
		//原微知是否已经删除	
		$.dialog.tips("抱歉，此微知已经被删除，无法进行转发!",1.5,"tips.gif");
	}else{
	//同时作为评论给用户评论
	var  microblogtranspondcount="#microblogtranspondcount"+_microblogid;
	var microblogcommentcount ="#microblogcommentcount"+_microblogid;
	//获得内容
	var shownames = encodeURI(_showname);
	var result = $.ajax({
		url: '<%=rootPath%>microblog/transpondMicroblog.action',
		cache:false,
		async:false,
	    data:{
	    	showname:shownames,
			microblogid:_microblogid,
			//源微知
			micrtranspondid:_transpondid
	    }
	}).responseText;
	
	$.dialog({
		title:'转发',
		max:false,
		min:false,
		lock:true,
		resize: false,
		width:'500px',
		height:'150px',
		content:result,
		cancelVal:'关闭',
		okVal:'转发',
		cancel:true,
        init:function(){
        	 var tea = document.getElementById("transpondInfo");
        	    if (tea.setSelectionRange){ 
        	        setTimeout(function() { 
        	        tea.setSelectionRange(0, 0); //将光标定位在textarea的开头，需要定位到其他位置的请自行修改 
        	        tea.focus(); 
        	        }, 0); 
        	        }else if(tea.createTextRange){ 
        	        	var range = tea.createTextRange();
        	        	range.moveStart("character",1);
        	        	range.collapse(true);
        	        	//alert(range.text.length);
        	        	//txt.moveStart('character',obj.value.length);
        	        	range.select();
        	        } 
		},
		ok:function(){
			//转发
			if($.trim($('#transpondInfo').val()).length>tranmicronumberwrod){	
				return false;
			}else{
				if($("input[type='checkbox']").is(':checked')){
					//获取隐私设置
			         commentlimits = $.ajax({
				    	     type:"post",
				    	     url:"<%=rootPath%>microblog/getprivacyofcomment.action",
				    	     data:{
				    	        	userid:_userid
				            	},
				            	cache:false,
				            	async:false
				             }).responseText;
				    	
				}
		    	
				if($.trim($('#transpondInfo').val()).length==0){
					$('#transpondInfo').val("转发微知");
					
				}
			$('#transpondcontent1').form('submit',{
				url:'<%=rootPath%>microblog/addTranspondMicroblog.action',
				onSubmit:function(){
					if(commentlimits!="false"){
						if($("input[type='checkbox']").is(':checked')){
					    	//同时评论该微知				    	
					         $.ajax({
			                     type:"post",
			                     url:"<%=rootPath%>microblog/microblogComment.action",
			                     cache:false,
			                     data:{
			                       replyuserid:_userid,
			                       replaymicroblogid:_microblogid,
			                       replaycontent:$('#transpondInfo').val(),
			                       replycommentidpage:replycommentidpage 
			                       },
			                       success:function(callback){
			               			if(callback!=null){
			               				$(microblogcommentcount).text(parseInt($(microblogcommentcount).text())+1);
			               				return true;
			               			}else{
			               				$.dialog.tips('转发时同时评论失败',1,'32X32/fail.png');
			               				return false;
			               			}
			               			
			                       }
					           });
					    }else{
			                return true;
			            }
					}
				},
				success:function(callback){
					if(callback!=null){
						if(commentlimits=="false"){
				    		$.dialog.tips('转发成功,但是由于用户设置,评论失败',2,'32X32/hits.png',function(){
								$(microblogtranspondcount).text(parseInt($(microblogtranspondcount).text())+1);
								if($("#microblog_menu").attr("class")=="hover"){
									$('#maxdiv').prepend(callback);		
								}
							});
				    	}else{
				    		$.dialog.tips('转发成功',1,'32X32/succ.png',function(){
								$(microblogtranspondcount).text(parseInt($(microblogtranspondcount).text())+1);
								if($("#microblog_menu").attr("class")=="hover"){
									$('#maxdiv').prepend(callback);		
								}
							});		
				    	}		
					}else{
						$.dialog.tips('转发失败',1,'32X32/fail.png');	
					}
				},
				error:function(){
					$.dialog.tips('转发失败',1,'32X32/fail.png');
				}
			});
			}
		}
	});
	}
}
//收藏
function collect(_microblogid){
	var shoucang = "#"+_microblogid;
	var collectionvalue = $(shoucang).text();
	if(collectionvalue=='收藏'){
		$.ajax({
			type:'POST',
			url:'<%=rootPath%>microblog/microblogcollect.action',
		    dataType: "json",
		    async:false,
			data:{
				microblogid:_microblogid
			},
		    success:function(msg){
		    	var mydoccollectioncount = $('#mydoccollectioncount').text();
		    	if(mydoccollectioncount==""){
		    		mydoccollectioncount = 0;
		    	}
		    	$('#mydoccollectioncount').text(parseInt(mydoccollectioncount)+1);
		    	if(msg==1){
		    		$.dialog.tips('收藏成功',0.5,'32X32/succ.png',function(){});
		    		$(shoucang).prepend("取消");
		    	}else{
		    		$.dialog.tips('收藏失败',1,'32X32/fail.png');
		    	}
		    },
		    error:function(){
		    	$.dialog.tips('收藏失败',1,'32X32/fail.png');
		    }
		});
	}else{
		$.dialog.confirm('您确定要取消收藏这条微知吗',function(){
		$.ajax({
			type:'POST',
			url:'<%=rootPath%>microblog/microblogcollectcancel.action',
			dataType:"json",
			async:false,
			data:{
				microblogid:_microblogid
			},
			success:function(data){
				var mydoccollectioncount = $('#mydoccollectioncount').text();
				if(mydoccollectioncount==""){
					mydoccollectioncount = 0;
				}
				$('#mydoccollectioncount').text(parseInt(mydoccollectioncount)-1);
				if(data==1){
					$(shoucang).text("收藏");
				}else{
					$.dialog.tips('取消错误',1,'32X32/fail.png');
				}
			},
			error:function(){
				$.dialog.tips('取消错误',1,'32X32/fail.png');
				
			}
		});
		});
	}
}
//微知删除
function deleteMicroBlog(_microblogid){
	var shoucangdiv = "#"+_microblogid+"div";
$.dialog.confirm('你确定要删除这条微知吗',function(){
	$.ajax({
		type:"POST",
		url:'<%=rootPath%>microblog/deletemicroblog.action',
		dataType:"json",
		data:{
		microblogid:_microblogid	
		},
		success:function(msg){
			
			if(msg==1){
				$('#mymicroblogcount').text($('#mymicroblogcount').text()-1);
				$(shoucangdiv).fadeOut();
			}else{
				$.dialog.tips('删除失败',1,'32X32/fail.png');
			}
		},
		error:function(){
			$.dialog.tips('删除失败',1,'32X32/fail.png');
		}
	});
}
);	
} 
//展开评论
var microblogidpage =0;
function microblogComment(_microblogid){	
	var commentdiv = "#commentDiv"+_microblogid;
	var appandspancomment = "#apendSpanMicroComment"+_microblogid;
	var commentdivinfo="#commentInfo"+_microblogid;
	var commenttextarea = "#microCommentFontCount_01"+_microblogid;
	$(commentdivinfo).val("");
	$(appandspancomment).empty();
	if($(commentdiv).css("display")=="none"){
		microblogidpage = _microblogid;
		$.ajax({
			type:"post",
			cache:false,
			url:"<%=rootPath%>microblog/microblogReplyList.action",
			cache:false,
			data:{
				microblogid:_microblogid
			},
			success:function(callback){
				$(commenttextarea).text(microblgnumberComment);
			  $(commentdiv).slideDown();
			  $(appandspancomment).append(callback);
			  }
		});
	}else{
		 $(commentdiv).slideUp();
	}
 }
//判断删除事件 鼠标移上
function microblogdeletejudge(_microblogid){
	var deleteMicroblogid ="#microblogDeleteLabel"+_microblogid;
	var informmicroblogid = "#microbloginform"+_microblogid;
	$(deleteMicroblogid).css({visibility:"visible"});
	$(informmicroblogid).css({visibility:"visible"});
	microblogidpage=_microblogid;
}
 /*评论分页*/
 function pageComment(_nPageNum){
	 var appandspancomment = "#apendSpanMicroComment"+microblogidpage;
		$('#pageNum').val(_nPageNum);
		var queryString = $('#pageForm').serialize();	     
		$.ajax({
			type:"post",
			cache:false,
			url:"<%=rootPath%>microblog/microblogReplyList.action?microblogid="+microblogidpage+"&"+queryString,
			success:function(callback){
				$(appandspancomment).empty();
			  $(appandspancomment).append(callback);
			  }
		});	
 }
 
	//回复回复的评论
	function replyCommentReply(_microblogid,_showname,_content,_commentid){
		var commentInfoid ="#commentInfo"+_microblogid;
		replycommentidpage=_commentid;
	    $(commentInfoid).val($(commentInfoid).val()+"回复@"+_showname+": ||");
	    $(commentInfoid).focus();
	} 
	//控制回复内容的长度
	function microblogCommentFontInfo(_microblogid){
		//选中的评论框
		var commentTextArea =  "#commentInfo"+_microblogid;
		var microCommentFont_01 = "#microCommentFont_01"+_microblogid;
		var microCommentFontCount_01 = "#microCommentFontCount_01"+_microblogid;
		var microCommentFont_02 = "#microCommentFont_02"+_microblogid;
		var microCommentFontCount_02 = "#microCommentFontCount_02"+_microblogid;
		
		var commentTextAreaLength = $(commentTextArea).val();
		if(microblgnumberComment-$.trim(commentTextAreaLength).length==microblgnumberComment){
			replycommentidpage=0;	
			
		}
		if(microblgnumberComment-$.trim(commentTextAreaLength).length>=0){
			$(microCommentFont_02).css({display:'none'});
			$(microCommentFont_01).css({display:'block'});
			$(microCommentFontCount_01).text(microblgnumberComment-$.trim(commentTextAreaLength).length);
		}else{
			$(microCommentFont_01).css({display:'none'});
			$(microCommentFont_02).css({display:'block'});
			$(microCommentFontCount_02).text(Math.abs(microblgnumberComment-$.trim(commentTextAreaLength).length));
		}
		
		
	}

//评论回复
function commentReply(_microblogid,_userid){
	var commentInfoid ="#commentInfo"+_microblogid;
	var commentInfo =  $(commentInfoid).val();
	var appandspancomment = "#apendSpanMicroComment"+_microblogid;
	var microblogcommentcount ="#microblogcommentcount"+_microblogid;
	var microCommentFontCount_01 = "#microCommentFontCount_01"+_microblogid;
	microblogCommentFontInfo(_microblogid);
  	//获取隐私设置
    var commentlimits = $.ajax({
    	     type:"post",
    	     url:"<%=rootPath%>microblog/getprivacyofcomment.action",
    	     data:{
    	        	userid:_userid
            	},
            	cache:false,
            	async:false
             }).responseText;
    	if(commentlimits=="false"){
    		 $.dialog.tips("由于用户设置，您无法回复评论。",1,"alert.gif");	
    		return false;
    	}else{
	  if($.trim(commentInfo).length>microblgnumberComment){
	    	return false;
	    }else if($.trim(commentInfo).length<=0){
	    	 $.dialog.tips("评论内容不能为空",0.3,"alert.gif");	
	    	return false;
	    }else{	
	  
	    		$.ajax({
	    			type:"post",
	    			url:"<%=rootPath%>microblog/microblogComment.action",
	    			cache:false,
	    			data:{
	    			 replyuserid:_userid,
	    			 replaymicroblogid:_microblogid,
	    			 replaycontent:commentInfo,
	    			 replycommentidpage:replycommentidpage 
	    			},
	    			success:function(callback){
	    				$(microCommentFontCount_01).text(microblgnumberComment);
	    				if(callback!=null){
	    					$(microblogcommentcount).text(parseInt($(microblogcommentcount).text())+1);
	    					$(commentInfoid).val("");
	    					replycommentidpage=0;
	    					$(appandspancomment).prepend(callback);
	    					
	    				}else{
	    					
	    					$.dialog.tips('评论失败',1,'32X32/fail.png');	
	    				}
	    			  },
	    			error:function(){
	    				$(microCommentFontCount_01).text(microblgnumberComment);
	    				$.dialog.tips('评论失败',1,'32X32/fail.png');
	    				
	    			}
	    		});	    		
	    	}
	    }
} 

 //删除微知评论回复
 function deleteMicroBlogComment(_commentid,_microblogid){
	 var microblogCommentSpan ="#commentReply"+_commentid
	 var microblogcommentcount ="#microblogcommentcount"+_microblogid;
	 $.dialog.confirm('你确定要删除这条评论吗',function(){
		 $.ajax({
			 type:"POST",
			url:'<%=rootPath%>microblog/deletemicroblogComment.action',
			data:{
				commentid:_commentid,
				replaymicroblogid:_microblogid
			},
			success:function(_msg){
				if(_msg==1){
					$(microblogcommentcount).text(parseInt($(microblogcommentcount).text())-1);
					  $(microblogCommentSpan).fadeOut();
					  $(microblogCommentSpan).fadeOut("slow");
					  $(microblogCommentSpan).fadeOut(2000);
				}else{
					$.dialog.tips('删除评论回复失败',1,'32X32/fail.png');
				}
			},
			error:function(){
				$.dialog.tips('删除评论回复失败',1,'32X32/fail.png');
			}
			 
		 });  
		 
		 
		 
	 });
	 
	 
	 
 }  
 //文档的排序按照创建时间排序
  	function documentSort(sort){  
  		 var liDom = $('#daohangul').find('.hover');
  		 var sData = liDom.attr('_data');
  		 sData = sData.substring(0,(sData.lastIndexOf('=')+1))+sort;
  		 liDom.attr('_data',sData);
  		 page(1); 
  	}
  	///对收藏的文档进行安装创建时间排序
	 function doccollectionSort(sort){ 
		var liDom = $('#daohangul').find('.hover');
  		var sData = liDom.attr('_data');
  		sData = sData.substring(0,(sData.lastIndexOf('=')+1))+sort;
  		liDom.attr('_data',sData);
  		page(1);
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
				max:false,
				min:false,
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

//转发某个文档给某些人
var _focusperson=null;
function transdocument(_docid){ 
	//弹出框，显示所有人。。。然后选择 
		var result = $.ajax({
			url: '<%=rootPath%>site/findallmicroblogforcusjson.action',
			type:"post",
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
				$('#transdocument').find('#docid').val(_docid);
				$('#transdocument').form('submit',{
					url:'<%=rootPath%>site/addtransmite.action',
					success:function(callback){ 
						var num=parseInt(callback) ;
						if(num>0){
							$.dialog.tips('推荐成功',1,'32X32/succ.png',function(){ 
								//将转发的里面数字加 
								$('#translabel'+_docid).text(parseInt($('#translabel'+_docid).text())+num); 	
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
		}); 
	} 
	var _allchannel=null; 
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
	//得到自己收藏数量
	function mydocumentcountajax(){
				$.ajax({
					type:'post',
					url:'<%=rootPath%>site/mydocumentcount.action',
					success:function(msg){ 
						$('#mydocumentcount').html(msg);
					}
				});
	}
	//得到自己所有知识数量
	function mydoccollectioncountajax(){
			$.ajax({
					type:'post',
					url:'<%=rootPath%>site/mydoccollectioncount.action',
					success:function(msg){ 
						$('#mydoccollectioncount').html(msg);
					}
				});
	}
	//跳刀添加文档
	function toaddDocument(){
			window.open('<%=rootPath %>site/client_toadd_document.action');  
	}
	//查看文档修改
	function documentinfo(_docid){
		window.open('<%=rootPath %>site/client_toupdate_document.action?docid='+_docid);  
	}
	//查看文档
	function documentinfo_see(_docid){ 
		window.open('<%=rootPath %>site/client_documentinfo.action?refrechstatus=1&docid='+_docid);  
	}
	
	//控制微知内容的长度
	function microblogInfoControl(_microblogcontent){		
		if(microblognumber-$.trim(_microblogcontent).length>=0){
			$('#microblogContentprompt_02').css({display:'none'});
			$('#microblogContentprompt_01').css({display:'block'});
			$('#microblogContentCount_01').text(microblognumber-$.trim(_microblogcontent).length);
		}else{
			$('#microblogContentprompt_01').css({display:'none'});
			$('#microblogContentprompt_02').css({display:'block'});
			$('#microblogContentCount_02').text(Math.abs(microblognumber-$.trim(_microblogcontent).length));
		}
	}
	//构建微知个人卡片 鼠标移动到上面
	function personalCard(_microblogid,_userid){
		var personalCard = $("#"+_microblogid+"div");
		  if(personalCard && !personalCard.attr("binit")){
			  $.ajax({
				  type:"POST",
				  url:"<%=rootPath%>microblog/searchPersonalCard.action",
				  data:{
					  userid:_userid,
					  microblogid:_microblogid
				  },
				  success:function(html){
					  personalCard.prepend(html);
					  personalCard.attr("binit", false);
				  },
				  error:function(){
					  $.dialog.tips('加载好友卡片失败',1,'32X32/fail.png');
				  }
			  });
		  }else{
			  $('#microblogCard'+_microblogid).css({display:""});
		  }
	}
	//构建微知个人卡片  鼠标从头像上移走
	function personalCardOut(_microblogid){
		var microbligdiv ="#microblogCard"+_microblogid;
		$(microbligdiv).css({
			display:"none"
		});
		
	}

	//判断删除事件 鼠标移出
	function microblogdeletejudgeOut(_microblogid){
		var deleteMicroblogid ="#microblogDeleteLabel"+_microblogid;
		var informMicroblogid ="#microbloginform"+_microblogid;
		$(deleteMicroblogid).css({visibility:"hidden"});
		$(informMicroblogid).css({visibility:"hidden"});
	}

 //收藏页面只有收藏的文档，因此刷新所有页面
 function deletefocusdoclink(_documentid){
	 $.dialog.confirm('您确定要取消收藏这个知识吗？',function(){
			  $.ajax({
				type:'post',
				url:'<%=rootPath%>site/deletefocusdoclink.action',
				data:{'documentid': _documentid},
				success:function(msg){
					if(msg=="1"){ 
					   // mydoccollectioncountajax();
					   $('#mydoccollectioncount').text(parseInt($('#mydoccollectioncount').text())-1);
						// mydoccollectioncount('desc'); 
						//然后将这个dl隐藏
						 $('#shoucangdoc'+_documentid).fadeOut("slow");
					}
				} 
			});
		},function(){});  
 }
//对文档进行关注以及取消关注
function addfocusdoclink(_documentid){ 
	var tValue=$('#focus'+_documentid).text();
	if(tValue=="收藏"){
		$.ajax({
			type:'post',
			url:'<%=rootPath%>site/addfocusdoclink.action',
			data:{'documentid': _documentid},
			success:function(msg){
				if(msg=="1"){ 
				// mydoccollectioncountajax();
				$('#mydoccollectioncount').text(parseInt($('#mydoccollectioncount').text())+1);
					$('#focus'+_documentid).html('取消收藏');
					//撤销他的可点击操作
				} 
			}
		}); 
	}else{
			$.ajax({
			type:'post',
			url:'<%=rootPath%>site/deletefocusdoclink.action',
			data:{'documentid': _documentid},
			success:function(msg){
				if(msg=="1"){ 
				//mydoccollectioncountajax();
				$('#mydoccollectioncount').text(parseInt($('#mydoccollectioncount').text())-1);
					$('#focus'+_documentid).html('收藏');
					//撤销他的可点击操作
				} 
			}
		}); 
	} 
}  
//猜你喜欢
function youlovedocument(){
	var str=$.ajax({
		type:'post',
		url:'<%=rootPath%>site/guesslovedocument.action',
		dataType: "json",
		async: false,
   		cache: false  
		}).responseText; 
		$('#youlovedocument').html(str);
}
//人气TOP
function hotpersondocument(){
	var str=$.ajax({
		type:'post',
		url:'<%=rootPath%>site/hothumandocument.action',
		dataType: "json",
		async: false,
   		cache: false  
		}).responseText;
		$('#hotpersondocument').html(str);
}
//正在热议
function hottalkdocument(){
	var str=$.ajax({
		type:'post',
		url:'<%=rootPath%>site/hottalkdocument.action',
		dataType: "json",
		async: false,
   		cache: false  
		}).responseText;
		$('#hottalkdocument').html(str);
}
//分享排行
function sharedocument(){
	var str=$.ajax({
		type:'post',
		url:'<%=rootPath%>site/sharedocument.action',
		dataType: "json",
		async: false,
   		cache: false  
		}).responseText;
	
		$('#sharedocument').html(str);
} 
//我的导航
function findMyNavigation(){
	var htmlcontent = $.ajax({
		type:"post",
		url:'<%=rootPath%>user/getmynavigation.action',
		cache:false,
		async:false
	}).responseText;
	$('#mynavigation').html(htmlcontent);
}
/*处理tag事件 ;信息*/
function tabs(lidom){
	$('#daohangul').find('li').each(function(){
		if(this.id ==lidom.id){
			this.className="hover";
		}else{
			this.className="";
		}
	});
	page(1);
}
/**
 * 刷新到个人所有微知
 */
function tabsPersonalLink(){
	$('#daohangul').find('li').each(function(){
		if(this.id =="microblog_menu"){
			this.className="hover";
		}else{
			this.className="";
		}
	});
	var linkdata = "personid=<%=LoginUtil.getLoginUserId()%>";
	$('#microblog_menu').attr("_href","<%=rootPath%>microblog/microblogPersonalPage.action");
	$('#microblog_menu').attr("_data",linkdata);
	page(1);
}
/**
 * 处理微知标签
 */
function hitMicroblogTab(){
	$('#microblog_menu').attr("_href","<%=rootPath%>microblog/microblogAll.action");
	$('#microblog_menu').attr("_data","");
	page(1);	
}
/*处理*/
function tabmanas(_a){
	$('#manageritem').find('a').each(function(){
		if(this.id ==_a){
			this.className="over";
			if(this.id=="manager_report" || this.id=="manager_illegality"){
				$('#controlinformkeyval').css("display","block");
			}else{
				$('#controlinformkeyval').css("display","none");
			}
		}else{
			this.className="";
		}
		
	}); 
	pagemana(1);
} 

 /*处理投票*/
 function tabtopic(_a){
	 
	$('#topicitem').find('a').each(function(){
		if(this.id ==_a){
			this.className="over";
			
		}else{
			this.className="";
		}
		
	});
	pagetopic(1);
}
 /*page 处理专题项*/
 function pagetopic(pageNum){
 	
 	var liDom = $('#topicitem').find('.over');
 	var sUrl = liDom.attr("_href");
 	var sData = liDom.attr("_data");
 	if(sData==''){
 		sData = "pageNum="+pageNum;
 	}else{
 		sData += "&pageNum="+pageNum;
 	}
 	var sHtmlConn = $.ajax({ 
  		type:'post', 
  		url: sUrl,
  		data: sData,
  		dataType: "json",
 		async: false,
 	    cache: false  
  	}).responseText;
 	$('#topictabmenu').html(sHtmlConn);
 }
/*pagemana 处理方法*/
function pagemana(pageNum){
	
	var liDom = $('#manageritem').find('.over');
	
	var sUrl = liDom.attr("_href");
	var sData = liDom.attr("_data");
	if(sData==''){
		sData = "pageNum="+pageNum;
	}else{
		sData += "&pageNum="+pageNum;
	} 
	var sHtmlConn = $.ajax({ 
 		type:'post', 
 		url: sUrl,
 		data: sData,
 		dataType: "json",
		async: false,
	    cache: false  
 	}).responseText;
	$('#microblogitems').html(sHtmlConn);
}
/**
 * 待处理下的知识处理
 */
 function pagemanadocument(pageNum){
 		var liDom = $('#DocumentOrJuBaoP').find('.over');
		var sUrl = liDom.attr("_href");
		var sData = liDom.attr("_data"); 
		if(sData==''){
			sData = "pageNum="+pageNum;
		}else{
			sData += "&pageNum="+pageNum;
		} 
		var sHtmlConn = $.ajax({ 
	 		type:'post', 
	 		url: sUrl,
	 		data: sData,
	 		dataType: "json",
			async: false,
		    cache: false  
	 	}).responseText; 
		$('#daidispose').html(sHtmlConn);
	}
/*page 处理方法*/
function page(pageNum){
	var liDom = $('#daohangul').find('.hover');
	var sUrl = liDom.attr("_href");
	var sData = liDom.attr("_data");
	if(sData==''){
		sData = "pageNum="+pageNum;
	}else{
		sData += "&pageNum="+pageNum;
	}
	var sHtmlConn = $.ajax({ 
 		type:'post', 
 		url: sUrl,
 		data: sData,
 		dataType: "json",
		async: false,
	    cache: false  
 	}).responseText;
	$('#maxdiv').html(sHtmlConn);
}
//展开折叠菜单项
function moreTabs(domA){
	var nHeight = $('#daohangul').css('height');
	if(nHeight=='34px'){
		$(domA).html('折叠&gt;&gt;');
		$('#daohangul').css('height','auto');
		$('#daohangul').find('li').first().css('display','block');
	}else{
		$(domA).html('更多&gt;&gt;');
		$('#daohangul').css('height','34px');
		$('#daohangul').find('li').first().css('display','inline');
	}
}
	/**
	复制高彤的关注/粉丝(知识，微知)
	查询当前登录者，不需要personid
	*/ 
	function focusTab(linkId,_href){ 
		//知识
			$('#document_collection').attr('_href',_href);
		$('#FocusByFocusDocumentOrWeibo').find('a').each(function(){ 
	    	if(this.id==linkId){ 
				this.style.color='rgb(255, 204, 51)';
			}else{ 
				this.style.color='';
			}
		});
		page(1);
	}	
	//举报知识和知识流程的切换
	function focusTabDocument(linkId){ 
		//知识 
		if(linkId!=null && $.trim(linkId)!=''){
			$('#DocumentOrJuBaoP').find('a').each(function(){
				if(this.id ==linkId){
					this.className="over";
				}else{
					this.className="";
				}
			});
		}
		//修改超链接里面的data的值
		 var liDom = $('#DocumentOrJuBaoP').find('.over');
  		 var sData = liDom.attr('_data');
  		 var sort=$('#siteTypeselect').val();
  		 var sortkey=$('#informkeyselect').val();
  		 var expt1="sitetype="+sort;
  		 var expt2="informKey="+sortkey;
  		 var expt=expt1+"&"+expt2;
  		//  sData = sData.substring(0,(sData.lastIndexOf('=')+1))+sort;
  		 sData=expt;
  		 liDom.attr('_data',sData);
  		 pagemanadocument(1);
	}
	//提问
	function askQuestion(){
		//获得内容
		var result = $.ajax({
			url: '<%=rootPath%>expert/askExpert.action',
			dataType: "json",
		    async: false,
		    cache: false
		}).responseText;
		//初始化弹出框
		$.dialog({
			title:'提问',
			content: result,
			max: false,
		    min: false, 
		    lock:true,
		    ok: function(){
		    	var questiontitlecontent = $("#questiontitlecontent").val();
		    	var questionInfo=$("#question_info").val();
		    	if(questiontitlecontent == "在此输入问题概要,150字以内"){
		    		questiontitlecontent="";
		    	}
		    	if($.trim(questiontitlecontent)=="" && $.trim(questionInfo)=="" ){
		    		$.dialog.tips("标题或者内容必须填写一项",1.5,"alert.gif");
		    		return false;
		    	}else{
		    		if(questiontitlecontent.length>150){
		    			$.dialog.tips("请填写150字以下的标题内容",1,"alert.gif");
			    		return false;
		    		}else if(questionInfo.length>300){
		    			$.dialog.tips("请填写300字以下的问题内容",1,"alert.gif");
						return false;
		    		}else{
		    			$.ajax({
				    		type:"POST",
				    		data:{
				    			questionInfo:questionInfo,
				    			questiontitle:questiontitlecontent
				    		},
				    		url:'<%=rootPath%>question/ask.action',
				    		dataType: "json",
				    		success:function(Msg){
			    				if(Msg==1){
			    					$.dialog.tips('提问成功',1,'32X32/succ.png');
			    				}else{
			    					$.dialog.tips('提问失败',1,'32X32/fail.png');
			    				}
			    			}
			    		});
		    		}
		    	}
		    },
		    okVal:'提问',
		    cancelVal: '关闭',
		    cancel: true,
		    lock: true,
		    padding: 0
		});
	}

	/*
	 * 选择创建的专题类型
	 */
	function selectSubjectType(){
		//获得内容
		var result = $.ajax({
			url : "<%=rootPath %>site/selectNameType.action",
		    async: false,
		    cache: false
		}).responseText;
		//初始化弹出框
		$.dialog({
			id:'subjectNameDialog',
			title:'选择专题类型',
			content: result,
			max: false,
		    min: false, 
		    lock:true,
		    width:500,
		    height:150,
		    cancel:function(){ 
	   		},
	   		close:function(){
	   			
		    },
		    cancelVal: '关闭',
		    padding: 0
		});
	}

	/*
	 * 新建专题
	 */
	function createknowSubject(){
		$.dialog({id:'subjectNameDialog'}).close();
		 var str=$.ajax({
			 type:'post',
			 url:'<%=rootPath %>site/addsubjectnow.action', 
			 async: false,
			 cache: false
		 }).responseText;
		 $.dialog({
				title:'创建专题',
				content: str ,
				max: false,
			    min: false,
				ok: function(){
					var validate = $('#createsubjectform').form('validate');
					$('#createsubjectform').form('submit',{
		    			url : "<%=rootPath %>site/begincreatesubject.action",
		    			success:function(data){
		    				if(data=='1'){
		    					$.dialog.tips('创建成功',1,'32X32/succ.png',function(){
		    					});
		    				}else if(data=='2'){
		    					$.dialog.tips('专题名称已存在',1,'32X32/fail.png');
		    				}else{
		    					$.dialog.tips('创建失败',1,'32X32/fail.png');
		    				}
		    			}
		    		});
					return validate;
			    },
			    okVal:'创建',
			    cancelVal: '取消',
			    cancel:true,
			    lock: true,
			    padding: 0
			});
		  $('#createsubjectform').find("input[name='irpChannel.chnlname']").validatebox();
	}
	
/*
 * 插入专题
 */
function topicInsert(){
	$.dialog({id:'subjectNameDialog'}).close();
	var textcontent = $("#publish_info").val();
	
	  
	var con = "请在这里输入自定义话题";

	
    //文字
    $("#publish_info").val(textcontent+"#"+con+"#");
   var l = $("#publish_info").val().length;
  
    //创建选择区域	

    if(document.getElementById("publish_info").createTextRange){//IE浏览器
    	
        var range = document.getElementById("publish_info").createTextRange();
    
        range.moveEnd("character",-l)         
        //range.moveStart("character",-l)              
        range.moveEnd("character",l-1);
        range.moveStart("character", l-1-con.length);
        range.select();
    }else{
    	
    	document.getElementById("publish_info").setSelectionRange(l-1-con.length,l-1);
    	
    	document.getElementById("publish_info").focus();
    }


} 
/**
 * 链接到主题
 */
function getInfoTopicPage(_info){
	/*判断此话题是否通过审核*/
		var linkstatu = $.ajax({
	    	type:"post",
	    	url:"<%=rootPath%>microblog/booleanaudit.action",
	    	data:{
	    		topicname:_info
	    	},
	    	cache:false,
	    	async:false
	    	 }).responseText;
	
	if(linkstatu==10){
		$.dialog.alert('此专题尚未通过管理员审核',function(){});
		return false;
	}
  
    var status = $.ajax({
    	type:"post",
    	url:"<%=rootPath%>microblog/searchtopicnum.action",
    	data:{
    		topicname:_info
    	},
    	cache:false,
    	async:false
    	 }).responseText;
    
	if(status>=1){
		window.open("<%=rootPath%>microblog/searchTopic.action?topicname="+_info);	
	}else{
		$.dialog.alert('此专题已被管理员删除',function(){});
	}
	
	return true;
}
/*
 * 连接到知识列表页面
 */
 function getKnowPage(_channelid){
	window.open("<%=rootPath%>site/knowledgeSubject.action?chid="+_channelid);
}

//删除专题
function deletetopic(topicid,topicname){
	var topicdiv = "#topic"+topicid;
	
	 $.dialog.confirm('你确定要删除这个专题吗',function(){
			$.ajax({
			  type:"post",
			  url:"<%=rootPath%>microblog/deletetopic.action",
			  cache:false,
			  async:false,
			  data:{
				  topicid:topicid,
				  topicname:topicname
			  },
			  success:function(status){
				  
				  if(status>=1){

					  $(topicdiv).fadeOut();
					  
				  }else{

					  $.dialog.tips('删除专题失败',1,'32X32/fail.png');
					  
				  } 
			  },
			  error:function(){
				  $.dialog.tips('删除专题失败',1,'32X32/fail.png');
			  }
			});
		 
	 });
}
//增加修改专题描述
function topicDesc(topicid){
	var topicdescdiv = "#topicdesc"+topicid;
	//打开更改框
					var loadPage=$.ajax({
						url: '<%=rootPath%>microblog/loadupdateframe.action', 
						data:{
							topicid:topicid
						},
						type:"post", 
					    async: false,
					    cache: false
					}).responseText; 
					 $.dialog({
						 title:'修改描述',
							max:false,
							min:false,
							lock:true,
							resize: false,
							width:600,
							content:loadPage,
							okVal:'保存',
							ok:function(){
								var topicdescs = $("#topicdescform").val();
								 if($.trim(topicdescs).length>$('#topicdesccount').val()){
								    	return false;
								    }else{
								    	
						 				$('#topicform').form('submit',{
						 					url:'<%=rootPath%>microblog/saveTopicDesc.action',
						 					cache:false,
						 					success:function(msg){
						 						if(msg==1){
						 							$(topicdescdiv).text(topicdescs);
						 							$.dialog.tips('保存成功',1,'32X32/succ.png');					
						 						}else{	
						 							$.dialog.tips('保存失败',1,'32X32/fail.png');	
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
//举报
function informdetail(microblogid){
	var loadPage=$.ajax({
		url: '<%=rootPath%>microblog/informframe.action', 
		data:{
			microblogid:microblogid
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
			resize: false,
			width:500,
			content:loadPage,
			okVal:'举报',
			ok:function(){
				var informkeykind=$("input[name=informkeykind]:checked").val();
						
				if(informkeykind==undefined){
					$.dialog.tips("请选择举报类型",1.5,"alert.gif");
					return false;
				}		
				$("#informdescform").val($("#informdescform").val()+"  "+$("input[name=informkeykind]:checked").attr("title"));
				var informdescs = $("#informdescform").val();
			
				 if($.trim(informdescs).length>$('#informdesccount').val()){
				    	return false;
				    }else{
				    	$('#informform').form('submit',{
		 					url:'<%=rootPath%>microblog/saveinformdesc.action',
		 					cache:false,
		 					data:{
		 						informkeykind:informkeykind
		 					},
		 					success:function(msg){
		 						if(msg==1){
		 							
		 							$.dialog.tips('提交成功',1,'32X32/succ.png');					
		 						}else{	
		 							$.dialog.tips('提交失败',1,'32X32/fail.png');	
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
//通过举报知识
function infromDocument(_docid){
	$.dialog.confirm('您确要把此条知识置为非法知识吗？',function(){
		$.ajax({
			type:"get",
			url:"<%=rootPath%>site/passinformdocument.action",
			cache:false,
			async:false,
			data:{
				docid:_docid
			},
			success:function(msg){
				if(msg>=1){
					 focusTabDocument('jubaoid');
				}else{
					$.dialog.tips('操作失败',1,'32X32/fail.png');
				}
			},
			error:function(){
				$.dialog.tips('操作错误',1,'32X32/fail.png');
			}
			
		}); 
		
	});
}
//恢复举报知识
function notpassinformdocument(_docid){
	$.dialog.confirm('您确定要恢复该举报知识吗？',function(){
		$.ajax({
			type:"get",
			url:"<%=rootPath%>site/huifuinfodocument.action",
			cache:false,
			async:false,
			data:{
				docid:_docid
			},
			success:function(msg){
				if(msg>=1){
					  focusTabDocument('feifaid');
				}else{
					$.dialog.tips('操作失败',1,'32X32/fail.png');
				}
			},
			error:function(){
				$.dialog.tips('操作错误',1,'32X32/fail.png');
			}
			
		}); 
	});
}
//通过举报
function informpass(microblogid){
	
	$.dialog.confirm('您确要把此条微知置为非法微知吗？',function(){
		$.ajax({
			type:"get",
			url:"<%=rootPath%>microblog/passinformdesc.action",
			cache:false,
			async:false,
			data:{
				microblogids:microblogid
			},
			success:function(msg){
				if(msg>=1){
					pagemana(1);
				}else{
					$.dialog.tips('操作失败',1,'32X32/fail.png');
				}
			},
			error:function(){
				$.dialog.tips('操作错误',1,'32X32/fail.png');
			}
			
		}); 
		
	});
	
}
//删除举报内容知识
function informdeletedocument(docid,informid){
	var microblogiddiv = "#informdiv"+informid;
	 $.dialog.confirm('您确定要取消这条举报内容吗',function(){
		$.ajax({
			type:"get",
			url:"<%=rootPath%>site/notpassinformdocument.action",
			cache:false,
			async:false,
			data:{
				docid:informid
			},
			success:function(msg){
				if(msg==1){
					$(microblogiddiv).fadeOut();
				}else{
					$.dialog.tips('操作失败',1,'32X32/fail.png');
				}
				
			},
			error:function(){
				$.dialog.tips('操作错误',1,'32X32/fail.png');
			}
			
		}); 
	 });
	
}
//删除举报内容
function informdelete(microblogid,informid){
	var microblogiddiv = "#informdiv"+informid;
	 $.dialog.confirm('您确定要取消这条举报内容吗',function(){
		$.ajax({
			type:"get",
			url:"<%=rootPath%>microblog/deleteinformdesc.action",
			cache:false,
			async:false,
			data:{
				informid:informid
			},
			success:function(msg){
				if(msg==1){
					$(microblogiddiv).fadeOut();
				}else{
					$.dialog.tips('操作失败',1,'32X32/fail.png');
				}
				
			},
			error:function(){
				$.dialog.tips('操作错误',1,'32X32/fail.png');
			}
			
		}); 
	 });
	
}
/*
 * 恢复被举报的微知
 */
function recoverInformMicroblog(microblogid,informid){
	

	$.dialog.confirm('您确要把此条微知恢复为正常状态的吗？',function(){
		$.ajax({
			type:"get",
			url:"<%=rootPath%>microblog/recoverinformdesc.action",
			cache:false,
			async:false,
			data:{
				microblogids:microblogid
			},
			success:function(msg){
				if(msg>=1){
					pagemana(1);
				}else{
					$.dialog.tips('操作失败',1,'32X32/fail.png');
				}
			},
			error:function(){
				$.dialog.tips('操作错误',1,'32X32/fail.png');
			}
			
		}); 
		
	});
	
}

/*管理员新建专题*/
 function addTopicEmpty(){
		var loadPage=$.ajax({
				url: '<%=rootPath%>microblog/loadaddtopicframe.action', 
				type:"post", 
			    async: false,
			    cache: false
			}).responseText; 
			 $.dialog({
				 title:'新增专题',
					max:false,
					min:false,
					lock:true,
					resize: false,
					width:600,
					content:loadPage,
					okVal:'保存',
					ok:function(){
						 var topicdescformcount = $("#topicdescform").val();
						 var topicnameformcount = $("#topicnameform").val();
						 var status = 0;
							//验证名字是否重复
							$.ajax({
						            url: '<%=rootPath%>microblog/checkingtopicname.action', 
						            data:{
							        topicname:topicnameformcount
					     	        },
						             type:"post", 
					                 async: false,
					                 cache: false,
					                 success:function(msg){
					                	 status = msg;
					                	 
					                	 
					                 }
					         });
							if(status==1){
		                		 $.dialog.tips("专题名字已存在!",1.5,"alert.gif");
		                		 return false;
		                	 }
						 if($.trim(topicnameformcount).length<1){
							 $.dialog.tips("专题名字不能为空",1,"alert.gif");
								return false;
						 }
						 if($.trim(topicdescformcount).length>$('#topicdesccount').val() || $.trim(topicnameformcount).length>$('#topicnamecount').val()){
						    	return false;
						    }else{
						    	
				 				$('#topicform').form('submit',{
				 					url:'<%=rootPath%>microblog/saveTopicNameDesc.action',
				 					cache:false,
				 					success:function(html){
				 						if(html!=null){
				 							$('#topicprepend').prepend(html);
				 							$('#topictalknot').css("display","none");
				 							$.dialog.tips('保存成功',1,'32X32/succ.png');					
				 						}else{	
				 							$.dialog.tips('保存失败',1,'32X32/fail.png');	
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
 /*专题通过*/
  function topicPass(topicid,topicname){
	  var topicdiv = "#topic"+topicid;
		 $.dialog.confirm('您确定要通过这个专题吗',function(){
				$.ajax({
				  type:"post",
				  url:"<%=rootPath%>microblog/passtopic.action",
				  cache:false,
				  async:false,
				  data:{
					  topicid:topicid,
					  topicname:topicname
				  },
				  success:function(status){
					  
					  if(status>=1){
						  $.dialog.tips('专题通过审核',1,'32X32/succ.png');
						  $(topicdiv).fadeOut();
						  
					  }else{

						  $.dialog.tips('专题通过失败',1,'32X32/fail.png');
						  
					  } 
				  },
				  error:function(){
					  $.dialog.tips('专题通过失败',1,'32X32/fail.png');
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
  //彻底删除
  function thoroughDelete(_microblogid){
	  var microdiv = "#"+_microblogid+"div";
	  $.dialog.confirm('您确定要彻底删除此条微知吗',function(){
		  $.ajax({
			 type:'post',
			 url:'<%=rootPath%>microblog/thoroughdeletemicroblog.action',
			 cache:false,
			 data:{
				 microblogidill:_microblogid
			 },
			 success:function(msg){
				 if(msg==1){
					 $.dialog.tips('删除成功了',1,'32X32/succ.png');
					 $(microdiv).fadeOut();
				 }else{
					 $.dialog.tips('删除失败',1,'32X32/fail.png');
				 }
			 }
		  });
	  });
  }
  //清空所有非法微知
  function emptyIllegality(){
	  $.dialog.confirm('您确定要清空所有的非法微知信息吗',function(){ 
		  $.ajax({
				 type:'post',
				 url:'<%=rootPath%>microblog/emptythoroughmicroblog.action',
				 cache:false,
				 success:function(msg){
					 if(msg>=1){
						 tabmanas("manager_delete");
					 }
				 }
			  });
	  });
  }
  /**
  *选择举报类型
  */
  function selectInfomSortType(){
  	var infomsorttypekey = $('#selectinfomsorttype option:selected').val();
    var liDom = $('#manageritem').find('.over');
	
	var sUrl = liDom.attr("_href");
	var sData = liDom.attr("_data");
	if(sData==''){
		sData = "pageNum=1&infomsorttypekey="+infomsorttypekey;
	}else{
		sData += "&pageNum=1&infomsorttypekey="+infomsorttypekey;
	} 
	var sHtmlConn = $.ajax({ 
 		type:'post', 
 		url: sUrl,
 		data: sData,
 		dataType: "json",
		async: false,
	    cache: false  
 	}).responseText;
	$('#microblogitems').html(sHtmlConn);
  
  	
  } 
  //排序
  function topicsortorderval(){
 	var optionval = $('#topicsortorderval option:selected').val();
 	var liDom = $('#topicitem').find('.over');
	var sUrl = liDom.attr("_href");
	var sData = liDom.attr("_data");
		if(sData==''){
			sData = "pageNum=1&optionvaltopic="+optionval;
		}else{
			sData += "&pageNum=1&optionvaltopic="+optionval;
		}
	var sHtmlConn = $.ajax({ 
		type:'post', 
		url: sUrl,
		data: sData,
		dataType: "json",
		async: false,
	    cache: false  
	}).responseText;
	$('#topictabmenu').html(sHtmlConn);
  }
  
</script>
<script type="text/javascript">
 ///常用面板
  function startb(){
		h = $(window).height();
		t = $(document).scrollTop();
		if(t > h){
			$('#gotop').show();
		}else{
			$('#gotop').hide();
		}
	}
	$(window).scroll(function(e){
		startb();		
	})
	//加载常用面板数据
	function loadOffenMenu(){
		$.ajax({
			url: '<%=rootPath%>menu/offen_menu.action',
			dataType: "json",
		    async: false,
		    cache: false,
		    success:function(irpInformTypelist){
		    	var offenul="";
				$.each(irpInformTypelist,function(i,t){
					var ffenname="";
					if(t.informkey.length>10){
						ffenname=t.informkey.substring(0,8);
					}else{
						ffenname=t.informkey;
					}
					offenul+="<li><a target='_blank' href='<%=rootPath%>"+t.informvalue+"'>"+ffenname+"</a></li>";				
				});
				$("#shareul").html(offenul);
		    }
		});
		}
	//当鼠标指针在指定的元素中移动时，就会发生 mousemove 事件
	function funCodemousemove(){
		var cimgdis=$('#code_img').attr("style");
		if(cimgdis.indexOf("display: none")>=0){
			loadOffenMenu();
			$('#code_img').show();
			oDiv=document.getElementById("code_img");  
			$(document).bind('mousemove',function(e){
				   DisplayCoord(e,oDiv);
			   });
		}else if(cimgdis.indexOf("display: none")==-1){
			loadOffenMenu();
			$('#code_img').show();
			oDiv=document.getElementById("code_img");  
			$(document).bind('mousemove',function(e){
				   DisplayCoord(e,oDiv);
			   });
		}else{
			$('#code_img').hide();
			$("#shareul").html("");
		}
	}
	//意见反馈
	function clickComplain(){
		//获得内容
		var result = $.ajax({
			url: '<%=rootPath%>menu/add_formComplain.action',
			dataType: "json",
		    async: false,
		    cache: false
		}).responseText;
		//初始化弹出框
		$.dialog({
			title:'意见反馈',
			content: result,
			max: false,
		    min: false, 
		    lock:true,
		    width:500,
		    height:200,
		    ok: function(){
		    	var complaindesc=$("#complaindesc").val();
		    	var complaintypeid=$("#complaintypeid").val();
		    	if($.trim(complaindesc)==''||complaintypeid==0){
		    		$.dialog.tips("内容为空或反馈主题没选择",1.5,"alert.gif");
		    		return false;
		    	}else if($.trim(complaindesc).length>300 || $.trim(complaindesc).length<=0){
			    	return false;
			    }else{
			       var queryString = $("#addComform").serialize();
			       $.getJSON("<%=rootPath%>menu/add_comform.action?"+queryString,function(data){
			    	   $.dialog.tips(data,1.5,"32X32/succ.png");
		            });	
		    	}
		    },
		    okVal:'提交',
		    cancelVal: '关闭',
		    cancel: true,
		    padding: 0
		});
	}
//   获取鼠标
   function getX(obj){  
        var parObj=obj;    
        var left=obj.offsetLeft;    
        while(parObj=parObj.offsetParent){    
            left+=parObj.offsetLeft;    
        }    
        return left;    
    }    
    function getY(obj){    
        var parObj=obj;    
        var top=obj.offsetTop;    
        while(parObj = parObj.offsetParent){    
            top+=parObj.offsetTop;    
        }    
     return top;    
    }    
 //
    function DisplayCoord(event,oDiv){    
        var top,left,oDiv;    
        top=getY(oDiv);    
        left=getX(oDiv);    
        var xvalue=event.clientX-left+document.body.scrollLeft;
        var yvalue=event.clientY-top+document.body.scrollTop;
        if(event.clientX==xvalue){
        	
        }else{
        	//好的浏览器
        	if((xvalue>0 && xvalue<200)&&(yvalue>0&&yvalue<330)){
            }else{
          	   $("#code_img").hide();
            }
        }
     }
   //查询用户安装的应用中发布的应用
   function checkuserapp(){
	   $.ajax({
			type:'post',
			url:'<%=rootPath%>menu/select_userappbystatus.action',
			dataType: "json",
			async: false,
	   		cache: false,
	   		success:function(data){
	   		  for(var i=0;i<data.length;i++){
				   if(data[i].applistname=="意见反馈"){
					  // $("#complain").show();
				   }
				   else if(data[i].applistname=="常用功能"){
					   $("#menuDiv").show();
					   startb();
					   $('#gotop').click(function(){
							$(document).scrollTop(0);	
						})
				   }else if(data[i].applistname=="我的导航"){
						//我的导航
						findMyNavigation();
					   
				   }  
			   }
	   		}
		});
   }
 //初始化签到连接
   $(function(){
  	 $.ajax({
  		type:'post',
  		url:'<%=rootPath%>menu/select_userappbystatus.action',
  		dataType: "json",
  		async: false,
     		cache: false,
     		success:function(data){
     		  for(var i=0;i<data.length;i++){
  			  if(data[i].applistname=="签到"){
  				   $('#initsignpic').show();
  				   break;
  			   }  
  		   }
     		}
  	});
   });
   
 //加载
 $(function(){
    startb();
    $('#gotop').click(function(){
		$(document).scrollTop(0);	
	})
	 findMyNavigation();
 })
 //隐藏
 function closeimg(){
	 $("#code_img").hide();
 }
 //发起投票
 function clickvote(){
		//获得内容
		var result = $.ajax({
			url: '<%=rootPath%>client/vote/vote_menu.jsp',
			dataType: "json",
		    async: false,
		    cache: false
		}).responseText;
		//初始化弹出框
		$.dialog({
			id:'votedialog',
			title:'发起投票',
			content: result,
			max: false,
		    min: false, 
		    lock:true,
		    width:500,
		    height:150,
		    cancel:function(){ 
	   		},
	   		close:function(){
	   			
		    },
		    cancelVal: '关闭',
		    padding: 0
		});
	}

</script>
	<form id="pageForm">
		<s:hidden name="pageNum" id="pageNum" />
		<s:hidden name="pageSize" id="pageSize" />
	</form> 
<!--头部菜单-->
<jsp:include page="include/client_head.jsp" />
<!--头部菜单end-->
<div class="main">
<!--左侧内容-->
<div class="left">
	<div class="banner"><img src="<s:property value="@com.tfs.irp.util.SysFileUtil@getSiteBanner()" />" width="684" height="111" /></div>
    <div class="shuru">
    	<ul>
    	    <li id="microblogContentprompt_01" style="text-align: right;">您还可以输入<label id="microblogContentCount_01" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;" ></label>个字</li>
    	    <li id="microblogContentprompt_02" style="text-align: right;display: none;">超出了<label id="microblogContentCount_02" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color:red;" ></label>个字</li>
        	<li><textarea style="resize: none;" name="textarea" rows="4" id="publish_info"  onkeyup="microblogInfoControl(this.value)" ></textarea></li>
        	<li>
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
            		<tr>
			            <td width="63%" align="left" valign="middle" class="menuTd"> 
			            	<a href="javascript:void(0)" onclick="toaddDocument()" class="shru1">知识</a>
			           		<a href="javascript:void(0)" class="shru2" ><label id="microblogPicFile_font">图片</label><input style="display: none;" type="file" name="" id="microblogPicFile" /></a>
			           		<a href="javascript:void(0)" onclick="askQuestion()" class="shru5">提问</a>
			           		<a class="shru3" href="javascript:void(0)" onclick="selectSubjectType()">专题</a>
			           		<a class="shru4" href="javascript:void(0)" onclick="clickvote()">投票</a>
			           		<a id="initsignpic" style="display: none;" class="shru6" href="javascript:void(0)" onclick="window.location.href='<%=rootPath%>sign/signInit.action'">签到</a>
			           	</td>
          				<td width="24%" align="right">
          				
          				<div>
				          <a href="javascript:void(0)" id="microblogtype">公开</a> 
				          <input style="display: none;" id="microblogtypetext" value="0" />
				           <div id="microblogtypeitem"  style="width:150px;">
				           </div>
	      				</div>
	      				
	      				</td>
	      				
    					<td width="13%" align="right" valign="middle"><a id="publishclassselec"  href="javascript:void(0)" class="fb" onclick="publish()"></a></td>
  					</tr>
  				</table>
			</li>
			<li id="uploadfilefont" style="display: none;"></li>
		  	<li id="microblogSharePic" style="display: none;">
   				 <div id="microblogSharePiclittle" ></div>
  			</li>
  			<li class="shuruend"></li>
        </ul>
    </div> 
    <div class="fy">
    	<span><a href="javascript:void(0)" onclick="moreTabs(this)" style="text-align: left;" class="linkc12">更多&gt;&gt;</a></span>
		<ul id="daohangul">
		<li id="microblog_menu" class="hover" onclick="tabs(this)"  _href="<%=rootPath%>microblog/microblogAll.action"  _data=""  ><a href="javascript:void(0)">微知</a></li>
		<li id="" onclick="tabs(this)" _href="<%=rootPath%>microblog/topicmenulist.action"><a href="javascript:void(0)">专题</a></li>
		<li id="document_collection" onclick="tabs(this)"  _href="<%=rootPath%>site/myalldoccollection.action"  _data="crtimesort=desc" ><a href="javascript:void(0)">收藏</a></li>
		<li id="myFlow" onclick="tabs(this)" _href="<%=rootPath%>site/flowmenu.action" _data=""><a href="javascript:void(0)">待处理</a></li>
		<%if(loginUser.isMicroblogManager()){ %>
		<li id="mymicrmanager" onclick="tabs(this)" _href="<%=rootPath%>microblog/microblogAllOfManager.action" _data=""><a href="javascript:void(0)">微知管理</a></li>
		<%}%>		
		<%if(loginUser.isExpert()){ %>
		<li id="questionsWait" onclick="tabs(this)" _href="<%=rootPath%>expert/questionToExpert.action" _data=""><a href="javascript:void(0)">待解答</a></li>
		<%}%>
	    </ul>
    </div>        
    <!-- 显示 -->
	<div id="maxdiv" class="fyh"> 
	
   	    <!-- 默认 -->
        <div class="fyh" id="mydoclink"></div>
    </div> 
    
</div>
<!--左侧内容结束--> 
<!--右侧内容-->
<div class="right">
<span></span>
    <div class="duo">
        <%--正在热议 --%>
		<dl id="hottalkdocument"></dl> 
		<%--猜你喜欢--%> 
        <dl id="youlovedocument"></dl> 
        <%--分享排行 --%>
        <dl class="bz" id="sharedocument"></dl>
        <%--人气TOP10 --%>
        <dl class="pw" id="hotpersondocument"></dl>
        <%--我的导航 --%>
        <dl id="mynavigation"></dl>
    </div> 
</div>
<!--右侧内容结束-->
<!--尾部信息-->
<jsp:include page="include/client_foot.jsp" />
<!--尾部信息end-->
  
</div>
<!-- 常用菜单   意见反馈-->
 <div id="menuDiv" style="display: none;">
     <div id="code" onmouseover="funCodemousemove()"></div>
	 <div title="点击关闭" id="code_img" style="display:none;" onclick="closeimg()">
		  <div id="offenmenu" style="width:200px;height:323px;">
		    <span style="display:block;height: 24px;padding-top:3px;width: 165px;" class="ui_t ui_title">常用菜单</span>
		    <ul id="shareul" style="border: 1px solid #CCC;display: block;width: 200px;height: 300px;background-color: #F1F1F1;">
		    </ul>
		  </div>
     </div>
     <div id="gotop"></div>
 </div>
 <div id="complain" style="cursor:pointer; "><span onclick="clickComplain()">意见反馈</span></div>
</body>
</html>

