<%@page import="com.tfs.irp.util.LoginUtil"%>
<%@page import="com.tfs.irp.user.entity.IrpUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
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
<title>企业社会化智力资源管理系统</title>
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/oacss.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/icon.css" />
<jsp:include page="../include/client_skin.jsp" />

<style type="text/css">
body{
	behavior:url(<%=rootPath %>client/js/hover.htc);
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
.main-gr .right .combo input{
  border: 0px;
}

</style>

<script type="text/javascript" src="<%=rootPath %>client/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
<!--
//添加个人标签 
  function addChannel(){
  	$.ajax({
  			type:'post', 
			url:'<%=rootPath%>site/iscreatechannel.action',
			async : false,
			success : function(msg){
				if(msg=="1"){
				$("#addchannelfrm").form('submit',{
						url : "<%=rootPath %>site/clientaddchannel.action",
						success:function(data){
							if(data!=""){
								$.dialog.tips('添加成功',1,'32X32/succ.png',function(){
									location.reload(true);
								});
							}else{
								$.dialog.tips('添加失败',1,'32X32/fail.png');
						     } 
					   }  
					   });
				}else{
				$.dialog.alert('您创建分类个数已达最大',function(){});
				}
			}
  	});
  }
//删除教育信息
function deleteChannel(_channelid){
	$.dialog.confirm('删除此分类，分类下的知识会进入默认分类里，您确认继续吗？',function(){
		   $.ajax({
					cache : false,
					type : 'post',
					url : '<%=rootPath%>/site/clientdeletechannel.action',
					data : {
						id: _channelid
					},
					success : function(callback) { 
						if(callback=="1"){
							location.reload(true);
							$.dialog.tips('删除成功',1,'32X32/succ.png');
						}else{
							$.dialog.tips('删除失败',1,'32X32/fail.png');
						}
					}
				});
		},function(){},parent); 
}

  	function initDesc(){
  	  var chnlname= $('#addchannelfrm').find('input[name="irpChannel.chnlname"]').val();
  	  $('#addchannelfrm').find('input[name="irpChannel.chnldesc"]').val(chnlname);
  	} 
 
$(function(){  
	$.extend($.fn.validatebox.defaults.rules, {	 
	        maxLength : {
				validator: function(value, param){   
					return value.length < param[0];  
	            },
				message : '该字符长度至多{0}位'
	        },	
	        //知识库名称的长度和唯一验证   
	        checkName: { 
		        validator: function(value, param){
		            if(value.length<param[0] || value.length>param[1]){
		            	$.fn.validatebox.defaults.rules.checkName.message = $.fn.validatebox.defaults.rules.length.message;
		            	return false;
		            }else{  
		             	var data = $.ajax({  
	                        type: "POST", 
	                        async: false,   
                            url:'<%=rootPath %>site/checkchnlname.action?siteid=<s:property value="irpChannel.siteid"/>&id=<s:property value="irpChannel.channelid"/>',   
                            data:{'chnlName':value},  
	                        dataType:"json"}
	                        ).responseText;
	                    if(data=='false'){
	                    	$.fn.validatebox.defaults.rules.checkName.message = "输入的唯一标识已存在";
	                    	return false;
	                    }else{
	                    	return true;
	                    }
		           }
		        }, 
	         	message: ""
	      }
	}); 
}); 
 
function toupdate(_channelid){  
	  var str=$.ajax({
				type:'post',
				url:'<%=rootPath%>site/client_to_updatechannel.action', 
				data: {'id':_channelid},
				async: false,
			    cache: false 
				}).responseText; 
				 $.dialog({
						title:'修改分类',
						max:false,
						min:false,
						lock:true,
						resize: false,
						width:'250px',
						height:'70px',
						content:str,
						cancelVal:'关闭',
						okVal:'确定',
						init : function(){
							 $('#chnlnameinputupdate').validatebox({required: true});   
						},
						cancel:function(){ 
						},
						ok:function(){  
							var isSubmit=$('#updatechannelfrm').form('validate');  
							      $('#updatechannelfrm').form('submit',{
										url:'<%=rootPath  %>site/updatechannel.action',
										success:function(callback){  
												if(callback!=""){ 
													$.dialog.tips('修改分类成功',1,'32X32/succ.png',function(){   
														window.location.reload(true); 
													});						
												}else{
													$.dialog.tips('修改分类失败',0.5,'32X32/fail.png');	
												}
										},
										error:function(){
											$.dialog.tips('修改分类失败',0.5,'32X32/fail.png');
										}
									}); 
									return isSubmit;
					  } 
				});   
} 
//-->
</script>
</head> 
<body>
<div class="bg01">
<!--头部菜单-->
<s:include value="../include/client_head.jsp"></s:include>
<!--头部菜单end-->
<div class="main-gr">
<!--左侧内容-->
<div class="left">
	<div class="leftmenu">
    	<h1>账号设置</h1>
        <dl>
        	<dt><em class="a"></em><a href="<%=rootPath %>user/user_set.action">个人资料</a></dt>
            	<dd><a href="<%=rootPath %>user/user_set.action">基本信息</a>
            	<a href="<%=rootPath %>user/user_edu.action">教育信息</a>
            	<a href="<%=rootPath %>user/user_career.action">职业信息</a></dd>
       	 	<dt><em class="b"></em><a href="<%=rootPath %>user/user_pic.action">修改头像</a></dt>
       	  	<dt><em class="c"></em><a href="<%=rootPath %>user/user_tag.action" >个人标签</a></dt>
            <dt><em class="d"></em><a href="<%=rootPath %>user/user_privacy.action">隐私设置</a></dt>
		
			<dt><em class="g"></em><a href="<%=rootPath %>user/user_group.action">个人分组</a></dt>
			<dt><em class="i"></em><a href="<%=rootPath %>site/user_subject.action">个人专题</a></dt>
			<dt><em class="h"></em><a href="<%=rootPath%>site/personallchanneltoshow.action?personid=<%=loginUser.getUserid()%>" class="x">个人分类</a></dt>
        	<dt><em class="e"></em><a href="<%=rootPath %>user/user_binding.action">账号绑定</a></dt>
        </dl>
    </div>
</div>
<!--左侧内容结束-->
<!--右侧内容-->
<div class="right">
	<div class="gr"><h1>个人分类</h1></div>
	 <div style="padding: 10px 20px;">  
	  <form   id="addchannelfrm" method="post" onsubmit="return false;"> 
   	   <input type="hidden" name="irpChannel.siteid" value="1"/> 
   	   	 		 <input name="irpChannel.chnltype" type="hidden" value="<s:property value='@com.tfs.irp.channel.entity.IrpChannel@CHANNEL_TYPE_PRIVATE'/>"/>   
	        	 <input type="hidden" name="irpChannel.chnlorder" value="0"/><%--排序 --%>
	        	 <input type="hidden" name="irpChannel.publishpro" value="1"/><%--是否发布 --%>
	        	 <input type="hidden" name="irpChannel.parentid" value="<s:property value='irpChannel.channelid'/>" /><%--用户一级栏目id --%>
			     <input onblur="initDesc()"  style="height: 24px; width: 240px;"  class="easyui-validatebox" id="chnlnameinput" validType="checkName[1,20]"  type="text" name="irpChannel.chnlname" required="true" />  
		   		 <input  type="hidden" name="irpChannel.chnldesc"  />   
   	<font color="red">*</font><a href="javascript:void(0);" onclick="addChannel()" class="zhuanz1">添加分类</a>
	</form></div>
	<div style="border-top:1px solid #E9E9E9;">
		<div style="font-size: 14px;font-weight: bold;">我已经添加的分类</div>
		<div class="tagList">
		<ul>
			<s:iterator value="irpChannels">
			<s:if test="%{channelid!=irpChannel.channelid}">
				<li>
				<%--默认分类是不显示的 --%>
				<%-- 目前先注释掉栏目修改操作 --%>
					<span class="tagName" onclick="toupdate(<s:property value='channelid'/>)">  
						<a href="javascript:void(0);"><s:property value="%{chnldesc}" /></a> 
					</span> 
					<a href="javascript:void(0)" class="tagDelete"  onclick="deleteChannel(<s:property value="channelid" />)" title="删除分类"></a>
					
				 </li>
			</s:if>
			</s:iterator>
		</ul>
		</div>
	</div>
</div>
<!--右侧内容结束-->
<!--尾部信息-->
<s:include value="../include/client_foot.jsp"></s:include>
<!--尾部信息end-->
</div>
</div>
</body>
</html>
