<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
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
.edit_usergourp{
  display: none;
}
.tagUpdate{
	background: url('<%=rootPath %>client/images/editsubject.png');
    float: left;
    height: 11px;
    line-height: 12px;
    margin-left: 3px;
    margin-top: 4px;
    width: 11px;
}
.tagUpdate:HOVER {
	background: url('<%=rootPath %>client/images/editsubject2.png');
    float: left;
    height: 11px;
    line-height: 12px;
    margin-left: 3px;
    margin-top: 4px;
    width: 11px;
}
</style>

<script type="text/javascript" src="<%=rootPath %>client/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
/*
 * 新建专题
 */
function createknowSubject(){
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
	    						window.location="<%=rootPath %>site/user_subject.action";
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

function createSearchSubject(){
	 var str=$.ajax({
		 type:'post',
		 url:'<%=rootPath %>personalsearch/searchsubject_edit.action', 
		 async: false,
		 cache: false
	 }).responseText;
	 $.dialog({
		title:'创建检索专题',
		content: str ,
		max: false,
	    min: false,
		ok: function(){
			var isValid = $('#personalForm').form('validate');
			if(isValid){
				$('#personalForm').form('submit',{
	   				url:'<%=rootPath%>personalsearch/personalsearch_add_dowith.action',
	   				onSubmit: function(){
	   					var chnlid = $('input[name="personalSearch.searchdchnlid"]').val();
	   					if(isNaN(chnlid)){
	   						$('input[name="personalSearch.searchdchnlid"]').val(0);
	   					}
	   				},
	   				cache:false,
	   				success:function(msg){
						if(msg==1){
							window.location.reload(true);
						}else{
							$.dialog.tips('保存失败',1,'32X32/fail.png');
						}
	   				}
	   			});
			}
			return isValid;
	    },
	    okVal:'创建',
	    cancelVal: '取消',
	    cancel:true,
	    lock: true,
	    padding: 0
	});
}

function editSearchSubject(_nId){
	 var str=$.ajax({
		 type:'post',
		 url:'<%=rootPath %>personalsearch/searchsubject_edit.action?personalSearchId='+_nId,
		 async: false,
		 cache: false
	 }).responseText;
	 $.dialog({
		title:'修改检索专题',
		content: str ,
		max: false,
	    min: false,
		ok: function(){
			var isValid = $('#personalForm').form('validate');
			if(isValid){
				$('#personalForm').form('submit',{
	   				url:'<%=rootPath%>personalsearch/personalsearch_add_dowith.action',
	   				onSubmit: function(){
	   					var chnlid = $('input[name="personalSearch.searchdchnlid"]').val();
	   					if(isNaN(chnlid)){
	   						$('input[name="personalSearch.searchdchnlid"]').val(0);
	   					}
	   				},
	   				cache:false,
	   				success:function(msg){
						if(msg==1){
							window.location.reload(true);
						}else{
							$.dialog.tips('保存失败',1,'32X32/fail.png');
						}
	   				}
	   			});
			}
			return isValid;
	    },
	    okVal:'修改',
	    cancelVal: '取消',
	    cancel:true,
	    lock: true,
	    padding: 0
	});
}

function toupdate(channelid){
	var result = $.ajax({
		type:'post',
	    url:'<%=rootPath %>site/updatesubjectnow.action', 
	    data:{"irpChannel.channelid":channelid},
	    dataType:"json",
	    async: false,
	 	cache: false
	}).responseText;
	$.dialog({
		title:'修改专题',
		content: result ,
		max: false,
	    min: false,
		ok: function(){
			var validate = $('#createsubjectform').form('validate');
			$('#createsubjectform').form('submit',{
    			url : "<%=rootPath %>site/updatesubject.action?irpChannel.channelid="+channelid,
    			success:function(data){
    				if(data=='1'){
    					$.dialog.tips('修改成功',1,'32X32/succ.png',function(){
    						window.location="<%=rootPath %>site/user_subject.action";
    					});
    				}else if(data=='2'){
    					$.dialog.tips('专题名称已存在',1,'32X32/fail.png');
    				}else{
    					$.dialog.tips('修改失败',1,'32X32/fail.png');
    				}
    			}
    		});
			return validate;
	    },
	    okVal:'修改',
	    cancelVal: '取消',
	    cancel:true,
	    lock: true,
	    padding: 0
	});
  $('#createsubjectform').find("input[name='irpChannel.chnlname']").validatebox();
}
function deleteChannel(channelid){
	var sNum = $.ajax({
		url:'<%=rootPath %>site/checkSubjectDocNum.action',
		dataType:'json',
		data:{
		"irpChannel.channelid":channelid
		},
		async: false,
	 	cache: false
	}).responseText;
	var replayInfo ='确认删除这个专题吗?';
	if(sNum>0){
		replayInfo = "此专题已引用[ <font color='red'>"+sNum+"</font> ]个知识,删除专题将导致引用失效,继续删除?";
	}
	$.messager.confirm('确认信息',replayInfo,function(r){
		if(r){
			$.ajax({
				url:"<%=rootPath %>site/deletesubject.action?irpChannel.channelid="+channelid,
				async: false,
				cache: false,
				success:function(data){
					if(data=="1"){
						$.dialog.tips('删除成功',1,'32X32/succ.png',function(){
							window.location="<%=rootPath %>site/user_subject.action";
						});
					}else{
						$.dialog.tips('删除失败',1,'32X32/fail.png');
					}
				}
			});
		}
	});
}

function goToDetail(id){
	window.open("<%=rootPath%>site/knowledgeSubject.action?chid="+id);
}

function deletePsearch(_nPersonalSearchId){
	$.ajax({
		cache : false,
		type : 'get',
		url : '<%=rootPath%>personalsearch/delete_personalsearch.action',
		data : {
			personalSearchId: _nPersonalSearchId
		},
		success : function(callback) {
			if(callback=='1'){
				$.dialog.tips('删除成功',1,'32X32/succ.png',function(){
					$('#pli_'+_nPersonalSearchId).remove();
				});
			}else{
				$.dialog.tips('删除失败',1,'32X32/fail.png');
			}
		}
	});
}

function toSearchSubject(_SSId){
	window.open("<%=rootPath%>personalsearch/searchsubject_detail.action?personalSearchId="+_SSId);
}
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
       	  	<dt><em class="c"></em><a href="<%=rootPath %>user/user_tag.action">个人标签</a></dt>
            <dt><em class="d"></em><a href="<%=rootPath %>user/user_privacy.action">隐私设置</a></dt>
			
			<dt><em class="g"></em><a href="<%=rootPath %>user/user_group.action">个人分组</a></dt>
			<dt><em class="i"></em><a href="<%=rootPath %>site/user_subject.action" class="x">个人专题</a></dt>
			<dt><em class="h"></em><a href="<%=rootPath%>site/personallchanneltoshow.action?personid=<s:property value="@com.tfs.irp.util.LoginUtil@getLoginUserId()" />">个人分类</a></dt>
        	<dt><em class="e"></em><a href="<%=rootPath %>user/user_binding.action">账号绑定</a></dt>
        </dl>
    </div>
</div>
<!--左侧内容结束-->
<!--右侧内容-->
<div class="right">
	<div id="addEdu" style="margin-bottom:5px;">
		<a href="javascript:void(0);" onclick="createknowSubject()" class="zhuanz1">创建专题</a>
		<a href="javascript:void(0);" onclick="createSearchSubject()" style="margin-left:10px;" class="zhuanz1">创建检索专题</a>
	</div>
	<div id="rightbuttom" style="border-top:1px solid #E9E9E9;">
		<div style="margin-left:20px;font-size: 14px;font-weight: bold;">已创建专题</div>
		<div class="tagList">
		<s:if test="irpChannels==null || irpChannels.size()==0">未发现专题...</s:if>
		<s:else>
			<ul>
			<s:iterator value="irpChannels">
			<s:if test="%{channelid!=irpChannel.channelid}">
				<li>
					<span class="tagName" onclick="goToDetail(<s:property value='channelid'/>)">  
						<a href="javascript:void(0);"><s:property value="%{chnlname}" /></a> 
					</span> 
					<a href="javascript:void(0)" class="tagUpdate"  onclick="toupdate(<s:property value='channelid'/>)" title="编辑专题"></a>
					<a href="javascript:void(0)" class="tagDelete"  onclick="deleteChannel(<s:property value="channelid" />)" title="删除专题"></a>
				 </li>
			</s:if>
			</s:iterator>
		</ul>
		</s:else>
		</div>
		
		<div style="margin-left:20px;font-size: 14px;font-weight: bold;">已创建检索专题</div>
		<div class="tagList">
		<s:if test="personalSearchs==null || personalSearchs.size()==0">未发现检索专题...</s:if>
		<s:else>
			<ul>
			<s:iterator value="personalSearchs">
				<li id="pli_<s:property value="personalsearchid" />">
					<span class="tagName" style="border:0;padding:0;cursor:pointer;" onclick="toSearchSubject(<s:property value='personalsearchid'/>)" /><s:property value="searchname" /></span> 
					<a href="javascript:void(0)" class="tagUpdate"  onclick="editSearchSubject(<s:property value='personalsearchid'/>)" title="编辑检索专题"></a>
					<a href="javascript:void(0)" class="tagDelete"  onclick="deletePsearch(<s:property value="personalsearchid" />)" title="删除检索专题"></a>
				</li>
			</s:iterator>
		</ul>
		</s:else>
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
