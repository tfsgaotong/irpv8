<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.tfs.irp.util.LoginUtil"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="<%=rootPath%>/client/css/oacss.css" rel="stylesheet"type="text/css" />
	<link href="<%=rootPath%>/client/css/easyui.css" rel="stylesheet" type="text/css" />
	<link href="<%=rootPath%>/client/css/icon.css" rel="stylesheet" type="text/css"  />
	<link href="<%=rootPath%>/client/css/oacss.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="<%=rootPath%>client/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css" />
    <style type="text/css" >
    .selectxiaoguos{
    	background-color: #DEDEBE;
    	color: white;
    	
    }
    </style>
	<script type="text/javascript" src="<%=rootPath%>/client/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/client/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/client/js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript">
	var hotdocword = '<s:property value="hotdocword"/>';
	var shotstr = '<s:property value="shotstr"/>'; 
	$(function(){
		$("#"+shotstr).click();
	});
	function findLData(_this){
		$("#cijieul").find("li").each(function(_in,_th){
			if(_this.id==_th.id){
				$("#"+_th.id).addClass("selectxiaoguos");
				var href = $("#"+_th.id).attr("href");
				if(href!=null){
					linkContent(href,hotdocword);
				}else{
					$('#hotwordcontent').html("");
				}
			}else{
				$("#"+_th.id).removeClass("selectxiaoguos");
			}
		});
	}
	/**
	* 获得下文注释
	*/	
	function linkContent(_url,_word){
		$.ajax({
			type:'post',
			cache:false,
			url:_url,
			data:{
				hotdocword:_word
			},
			success:function(content){
				if(content!=""){
					$('#hotwordcontent').html(content);
				}else{
					$('#hotwordcontent').html("");	
				}
				
			}
		});	
	}
	/**
	 * 链接到版本选择
	 */
	function linkVerContent(_termid){
		//浏览
		$.get("<%=rootPath%>term/updatebrowsecount.action",{termid:_termid});
		window.open('<%=rootPath%>term/linkversion.action?termid='+_termid);
	}
	</script>
	<title>相关信息</title>
  </head>
  
  <body>

  		<div style="width: 650px;height: 40px;background-color: #F5F5F5;">
  			<ul style="" id="cijieul">
  				<li id="cijie" href="<%=rootPath %>term/findtermdochotword.action" style="width: 19%;float: left;line-height:40px;height:40px;text-align: center;border-right: solid 1px;cursor: pointer;" onclick="findLData(this)">词解</li>
  				<li id="hotwords"  href="<%=rootPath %>term/findtermcorrebycate.action"  style="width: 20%;float: left;line-height:40px;height:40px;text-align: center;border-right: solid 1px;cursor: pointer;" onclick="findLData(this)">相关热词</li>
  				<li id="zixun"  style="width: 20%;float: left;line-height:40px;height:40px;text-align: center;border-right: solid 1px;cursor: pointer;" onclick="findLData(this)">相关资讯</li>
  				<li id="author"  style="width: 19%;float: left;line-height:40px;height:40px;text-align: center;border-right: solid 1px;cursor: pointer;" onclick="findLData(this)">相关作者</li>
  				<li id="area"  style="width: 20%;float: left;line-height:40px;height:40px;text-align: center;cursor: pointer;" onclick="findLData(this)">行业地域</li>
  			</ul>
  		</div>
  		<div id="hotwordcontent" style="overflow-y:scroll;width: 650px;height: 360px;padding: 10px 5px 5px 5px;">
  				
  				
  			
  				
  		</div>
  				
  </body>
</html>
