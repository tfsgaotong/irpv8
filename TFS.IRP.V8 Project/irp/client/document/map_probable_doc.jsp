<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
<title>知识地图</title>
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
	 <link rel="stylesheet" type="text/css" href="<%=rootPath%>/client/css/easyui.css" />
	 <link href="<%=rootPath %>client/css/css.css" rel="stylesheet" type="text/css" />
	 <link href="<%=rootPath %>client/images/css.css" rel="stylesheet" type="text/css" />
	 <link href="<%=rootPath%>/client/js/skins/iblue.css" rel="stylesheet" type="text/css" />
 	 <link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/oacss.css" /> 
	<link rel="stylesheet" href="<%=rootPath%>/client/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"></link>
  <style>
.ellipsis {
    table-layout: fixed;
}
.ellipsis td{
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap; 
}
</style>  
</head> 
<script type="text/javascript" src="<%=rootPath %>client/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/easyui-lang-zh_CN.js"></script> 
<script type="text/javascript" src="<%=rootPath %>client/ztree/js/jquery.ztree.core-3.5.js"></script>
 <body>
 <s:include value="../include/enterprise_head.jsp"></s:include> 
    <div class="area1"></div>
    <div class="zj_wBox">
     <div id="first_index_left1">
      <div class="zj_fl zj_w1"> 
	        <div class="zj_title1">
		            	<div class="zj_tt"> <h1>知识地图</h1> </div>
	 	 </div>
	      <div class="zj_box1" style="overflow: auto; height: 495px; padding-left: 10px;">
				<div class="content_wrap">
					<div class="zTreeDemoBackground left">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div>
		 </div> 
	</div>
     </div>
      <div class="zj_fl zj_w4" id="documentlistdiv"></img>
        	<div id="probabledocumentshow"></div>
      </div> 
       <div class="zj_cl"></div>
    </div>
     <div class="area2"></div>
    <s:include value="../include/enterprise_foot.jsp"></s:include>
    <script type="text/javascript">
    //列表更多精华知识
    function showmorejinghuadocument(){
    	 str=$.ajax({
				type:'post',
				url:'<%=rootPath%>site/jinghuadocumentlist.action?id=<s:property value="id"/>', 
				dataType: "json",
				async: false,
			    cache: false 
				}).responseText;  
    	   $('#documentlistdiv').html(str);  
    }
    //列表
    function  probabledocumentshow(querystring){
    	 var str =""; 
    	if(querystring!="" && querystring!= undefined){
    		   str=$.ajax({
 				type:'post',
 				url:"<%=rootPath%>site/mapprobabledocumentshow.action?"+querystring, 
 				dataType: "json",
 				async: false,
 			    cache: false 
 				}).responseText;  
    	}else{
    		   str=$.ajax({
 				type:'post',
 				url:'<%=rootPath%>site/mapprobabledocumentshow.action?id=<s:property value="id"/>', 
 				dataType: "json",
 				async: false,
 			    cache: false 
 				}).responseText;  
    	}
		    $('#probabledocumentshow').html(str);  
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
  //点击排行的时候
  function updatePageHtml(_sFiled,_sOrderBy){
	  //修改表单里面的值
	  $('#orderField').val(_sFiled);
	  $('#orderBy').val(_sOrderBy);
	  var  _nPageNum=$('#pageForm').find('input[name="pageNum"]').val();
	  page(_nPageNum);
  }
    //分页
	function page(_nPageNum){ 
		$('#pageForm').find('input[name="pageNum"]').val(_nPageNum);
		var queryString=$('#pageForm').serialize();   
	    probabledocumentshow(queryString);
	}
    	function showdocumentinfo(_docid){
    	 	window.open('<%=rootPath %>site/showdocumentinfo.action?refrechstatus=1&docid='+_docid );  
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
  	  function filter(treeId, parentNode, childNodes) {
  			if (!childNodes) return null;
  			for (var i=0, l=childNodes.length; i<l; i++) {
  				childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
  			}
  			return childNodes;
  		}
	$(function(){ 
		var setting = {
				view: {
					showIcon: true 
				},
				 data: {
	                     simpleData : {
	                         enable : !0,
	                         idKey : "id",
	                         pIdKey : "pId"
	                    }
	                },
				async: {
					enable: true,
					url:"<%=rootPath%>site/docmapztee.action" 
				},
				  callback: {
					onClick: oncClick
				} 
			};
		$.fn.zTree.init($("#treeDemo"), setting);
		$.ajax({
			url: '<%=rootPath%>site/getFirstMapNode.action',
   			type:'post', 
   		    async: false ,
   		    cache: false ,
   		    success:function(mes){
   		    	if(mes!=0){
   		    		str=$.ajax({
   		 				type:'post',
   		 				url:'<%=rootPath%>site/mapprobabledocumentshow.action?id='+mes, 
   		 				dataType: "json",
   		 				async: false,
   		 			    cache: false 
   		 				}).responseText;  
   				    $('#probabledocumentshow').html(str);
   	    		 }else{
   	    			probabledocumentshow();
   	    		 }
   		    }
		});
	});
	function oncClick(event, treeId, treeNode, clickFlag){
	 if(treeNode){
		 $('#pageForm').find('input[name="pageNum"]').val(1);
		 $('#pageForm').find('input[name="id"]').val(treeNode.id);
		 var queryString=$('#pageForm').serialize();   
		 probabledocumentshow(queryString);
	 }
	}
    </script>
</body>
</html>
