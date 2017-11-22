<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>  
<title>知识库</title>  
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/client/css/easyui.css" />
<link href="<%=rootPath %>client/css/css.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath %>client/images/css.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath%>/client/css/oacss.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath%>/client/js/skins/iblue.css" rel="stylesheet" type="text/css" />
<style type="text/css">
#pic {width:900px; float:left; overflow:hidden; height:100%;}
#pic li {float:left; margin:5px;} 
.clearit{clear:both; height:0px; overflow:hidden;}
#main { width:1000px; text-align:center;padding: 0px;}
.ellipsis{overflow: hidden;text-overflow: ellipsis;white-space: nowrap;}
</style> 

<script type="text/javascript" src="<%=rootPath%>/client/js/jquery-1.8.0.min.js"></script> 
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/client/js/easyui-lang-zh_CN.js"></script> 
<script type="text/javascript" src="<%=rootPath%>/client/js/ScrollPic.js"></script>
<script type="text/javascript">
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
function showdocumentinfo(_docid){
	window.open('<%=rootPath %>site/showdocumentinfo.action?refrechstatus=1&docid='+_docid); 
} 
  		//跳刀添加文档
function toaddDocument(){
		window.open('<%=rootPath %>site/to_add_subject.action');  
}
//进来加载最新知识
function toloadnewdocumentnew(){
	//将当前文档的评论显示出来   
	  var str=$.ajax({
			type:'post',
			url:'<%=rootPath%>site/indexshownewdocumentnew.action', 
			dataType: "json",
			async: false,
		    cache: false 
			}).responseText;   
			//将自己选择
			$('#hotdocumenth').removeClass().addClass("over");//class="over"
			$('#valuabledocumenth').removeClass().addClass("over");
			$('#newdocumenth').removeClass();
			$('#pubtimeorhit').html('发布时间');
	    $('#indexshowdocumenttable').html(str);  
}
//加载最热知识
function toloadnewdocumenthot(){ 
	//将当前文档的评论显示出来   
	  var str=$.ajax({
			type:'post',
			url:'<%=rootPath%>site/indexshownewdocumenthot.action', 
			dataType: "json",
			async: false,
		    cache: false 
			}).responseText; 
			$('#newdocumenth').removeClass().addClass("over");
			$('#valuabledocumenth').removeClass().addClass("over");
			$('#hotdocumenth').removeClass();  
			$('#pubtimeorhit').html('点击量');
	    $('#indexshowdocumenttable').html(str);  
}

function toloadnewdocumentvaluable(){
	var str=$.ajax({
		type:'post',
		url:'<%=rootPath%>site/indexshownewdocumentvaluable.action', 
		dataType: "json",
		async: false,
		cache: false 
	}).responseText; 
	$('#newdocumenth').removeClass().addClass("over");
	$('#hotdocumenth').removeClass().addClass("over");
	$('#valuabledocumenth').removeClass();  
	$('#pubtimeorhit').html('知识积分');
	$('#indexshowdocumenttable').html(str);  
}

// 先加载ScrollPic.js插件后再执行以下语句
/*function initPic(){
	var width=$("#pic").width();
	var height=$("#pic").height(); 
    scrollPic_01 = new ScrollPic();
    scrollPic_01.scrollContId   = "pic"; //图片容器ID
    scrollPic_01.arrLeftId      = "getPre"; //左按钮ID
    scrollPic_01.arrRightId     = "getNext"; //右按钮ID
    scrollPic_01.frameWidth     = width; //图片容器宽度 
    scrollPic_01.pageWidth      = width; //每张图片宽度 
    scrollPic_01.speed          = 10; //移动速度(单位毫秒，越小越快)
    scrollPic_01.space          = 50; //每次移动像素(单位px，越大越快)
    scrollPic_01.autoPlay       = false; //自动播放
    scrollPic_01.autoPlayTime   = 0.5; //自动播放间隔时间(秒)
    scrollPic_01.initialize();  //初始化
}*/
//搜索名字
function searchInfo1(searchInfo){ 
	
		searchtype = 5;  
		if(searchInfo.length>38){
			searchInfo = searchInfo.substring(0,37);	
		}
		var eacapeInfo = encodeURI(searchInfo);
	    window.location.href="<%=rootPath%>solr/searchcontentofsolr.action?searchInfo="+eacapeInfo+"&searchtype="+searchtype; 
}
</script>
</head>

<body> 
<s:include value="../include/enterprise_head.jsp"></s:include> 
<div class="area1"></div>
<div class="zj_wBox"> 
	<%--左边是门户和概览共用的一个页面  --%>
	<div class="zj_fl">
	<s:action name="allrightchannelshow" namespace="/site" executeResult="true" />
	</div>
	<div class="zj_fl zj_w2">
		<div class="zj_title1">
			<div class="zj_tt">
         		<a href="javascript:void(0)" onclick="toloadnewdocumentnew()" style="text-decoration: none;">
         			<h1 id="newdocumenth">最新知识</h1>
         		</a>
         		<a href="javascript:void(0)" onclick="toloadnewdocumenthot()" style="text-decoration: none;">
         			<h1 id="hotdocumenth">最热知识</h1>
         		</a>
         		<a href="javascript:void(0)" onclick="toloadnewdocumentvaluable()" style="text-decoration: none;">
         			<h1 id="valuabledocumenth">最有价值</h1>
         		</a>
         	</div>
         	<div class="zj_more"><a href="#"> </a></div>
		</div>
		<div class="zj_box2">
			<table cellpadding="0" cellspacing="0" border="0" background="<%=rootPath %>client/images/_34.jpg" width="482" class="boxcenter listTitle">
				<tr>
					<td width="82" height="25" align="center">分类名称</td>
					<td width="266" align="center">标题</td>
             		<td width="71" align="center" id="pubtimeorhit">发布时间</td>
             		<td width="63" align="center">创建者</td>
             	</tr>
			</table> 
			<div id="indexshowdocumenttable"></div>
			<script type="text/javascript">
				toloadnewdocumentnew();
			</script>
         </div>
	</div> 
	<div class="zj_fr zj_w3">
  	<s:if test="chnlDocLinksGongGao!=null && chnlDocLinksGongGao.size()>0">
		<div class="zj_title1">
			<div class="zj_tt"><h1>公告栏</h1></div>
		</div>
     	<s:iterator value="chnlDocLinksGongGao">
      	<div class="zj_box1" style="overflow: auto;">
      		<a href="javascript:void(0);" style="text-decoration: none;" onclick="showdocumentinfo(<s:property value='docid' />)"> 
              	<h2 style="height: 180px;"><p style="text-indent:1em;"><s:property value="document.docabstract"/></p></h2>
			</a>
		</div>
     	</s:iterator>
     	<div class="area1"></div>
	</s:if>
		<a href="javascript:void(0)" onclick="toaddDocument()"><img src="<%=rootPath %>client/images/_42.jpg" /></a>
		<div class="area1"></div>
		<div class="zj_title1">
		<div class="zj_tt">
			<h1>热门检索</h1></div>
		</div>
		<div class="zj_boxn">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="olbg11 ellipsis">
				<tr>
					<td><ul class="ul_dtlist2 black12" style="padding-left: 10px;">
					<s:iterator value="irpTags">
                  	<li>  
             			·<a title='<s:property value="tagname"/>' style="width: 50%; overflow: hidden;" href="javascript:void(0)" onclick="searchInfo1('<s:property value="tagname"/>')"><s:property value="tagname"/>(<s:property value="ncount"/>)</a>
                  	</li>
                	</s:iterator>
                 	</ul></td>
				</tr>
			</table>
		</div>
	</div>
	<div class="zj_cl"></div>
</div>
<div class="area2"></div>
<s:include value="../include/enterprise_foot.jsp"></s:include>
</body>
</html>