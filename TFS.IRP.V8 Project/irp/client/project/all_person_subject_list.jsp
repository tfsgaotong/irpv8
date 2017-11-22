<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>选择专题</title>
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/oacss.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/icon.css" />
<link href="<%=rootPath %>client/css/css.css" rel="stylesheet" type="text/css" />
<link id="skin" rel="stylesheet" type="text/css" />
</head> 
<body>
<script type="text/javascript">
	//全局变量，
	var m_checked = false;
	function jump1(_form){ 
		var queryString = _form.serialize(); 
		var attrstatus = $('#qiyesubject').css('display');
		var _url = '<%=rootPath %>site/initpersonsubject.action?irpDocument.docid='+<s:property value='irpDocument.docid'/>;
		if(attrstatus=='block'){
			var attrclass = $('#qiyesubject').attr('class');
			if(attrclass==undefined || attrclass==null || attrclass==''){
				_url = '<%=rootPath %>site/initqiyesubject.action?irpDocument.docid='+<s:property value='irpDocument.docid'/>;
			}
		}
		var result = $.ajax({
							type: 'POST',
							url: _url,
							data:queryString,
						    async: false,
						    cache: false
						}).responseText;    
		$('#all_kindof_subject').html(result);  
	} 
	//分页
	function page(_nPageNum){ 
		$('#dialogPageForm').find('input[name="pageNum"]').val(_nPageNum);
		$('#dialogPageForm').find('input[name="searchWord"]').val($('#dialogPageForm').find('input[name="searchWord"]').val());
	    jump1($('#dialogPageForm'));
	}
	//排序
	function orderBy(_sFiled,_sOrderBy){ 
		$('#dialogPageForm').find('input[name="orderField"]').val(_sFiled);
		$('#dialogPageForm').find('input[name="orderBy"]').val(_sOrderBy);
		  
		$('#dialogPageForm').find('input[name="searchWord"]').val();
	    jump1($('#dialogPageForm'));
	}
	  
	$(function(){
		$('#sWord').searchbox({
			width:240,
		    menu:'#sType',
		    prompt:'请输入检索词',
		    searcher:function(value,name){
		    	$('#dialogPageForm').find('input[name="searchWord"]').val(value);
		    	$('#dialogPageForm').find('input[name="searchType"]').val(name);
		    	$('#dialogPageForm').find('input[name="pageNum"]').val('1');
		    	$('#dialogPageForm').find('input[name="orderField"]').val('');
		    	$('#dialogPageForm').find('input[name="orderBy"]').val('');
		        jump1($('#dialogPageForm'));
		    } 
		}); 
	});
	
	//加载企业专题
	function loadqiyesubject(){
		//将当前文档的评论显示出来   
		var str=$.ajax({
				type:'post',
				url:'<%=rootPath%>site/initqiyesubject.action?irpDocument.docid='+<s:property value='irpDocument.docid'/>, 
				dataType: "json",
				async: false,
			    cache: false 
				}).responseText;
				$('#personsubject').removeClass().addClass("over");//class="over"
				$('#qiyesubject').removeClass();
	  		    $('#all_kindof_subject').html(str);
	}
	//加载个人知识
	function loadpersonsubject(){ 
		 var str=$.ajax({
				type:'post',
				url:'<%=rootPath%>site/initpersonsubject.action?irpDocument.docid='+<s:property value='irpDocument.docid'/>, 
				dataType: "json",
				async: false,
			    cache: false 
				}).responseText;
				$('#qiyesubject').removeClass().addClass("over");//class="over"
				$('#personsubject').removeClass();  
				$('#all_kindof_subject').html(str); 
	}
	
	//修改专题权限
	$(function(){
		 $.ajax({
			url: '<%=rootPath%>site/subclientrightadddoc.action',
			type:'post', 
		    async: false ,
		    success:function(mes){
		    	if(mes=="double" || mes =="quote"){
		    		$('#qiyesubject').show();
		    		loadqiyesubject();
	    		}else{
	    			$('#personsubject').removeClass();
	    			loadpersonsubject();
	    		}
		    }
		}) ;
	});
</script>
<form id="dialogPageForm">
	<s:hidden name="pageNum" id="pageNum" />
	<s:hidden name="pageSize" id="pageSize" />
	<s:hidden name="orderField" id="orderField" />
	<s:hidden name="orderBy" id="orderBy" />
	<s:hidden name="searchWord" id="searchWord" />
	<s:hidden name="searchType" id="searchType" /> 
	<s:hidden name="projectId"></s:hidden>
</form>
<div style="padding:5px; text-align: center;">
<div style="padding:0px 10px 5px 0px; float: right;">
<input name="sWord" id="sWord" />
<div id="sType">  
    <div data-options="name:'channelname'">专题名称&nbsp;&nbsp;&nbsp;&nbsp;</div>
    <div data-options="name:'channeldesc'">专题描述</div>
</div>
</div>
<form id="addsubform">
<input type="hidden" name="irpDocument.docid"  value="<s:property value='irpDocument.docid' />"/>
<br/>
<div class="zj_title1">
         	<div class="zj_tt">
         		<a href="javascript:void(0)" onclick="loadqiyesubject()" style="text-decoration: none;">
         			<h1 id="qiyesubject" style="display: none;">企业专题</h1>
         		</a>
         		<a href="javascript:void(0)" onclick="loadpersonsubject()" style="text-decoration: none;">
         			<h1 id="personsubject" class="over">我的专题</h1>
         		</a>
         	</div>
         </div>
<div id="all_kindof_subject"></div>
</form>
</div>
</body>
</html>
