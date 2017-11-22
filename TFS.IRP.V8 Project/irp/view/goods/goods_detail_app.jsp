<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<s:set var="loginUser" value="@com.tfs.irp.util.LoginUtil@getLoginUser()" />
<!doctype html>
<html lang="en" class="no-js">
<head>
<meta charset="utf-8">
<title><s:property value="irpGoods.goodsname" /></title>
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
<link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css">
<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css">
<link href="<%=rootPath %>view/css/easyui.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath%>view/css/icon.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath%>view/css/discuz.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath%>view/css/rating.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.clearfix:before{
    content: ".";     /*内容为“.”就是一个英文的句号而已。也可以不写。*/
    display: block;   /*加入的这个元素转换为块级元素。*/
    clear: both;     /*清除左右两边浮动。*/
    visibility: hidden;      /*可见度设为隐藏。注意它和display:none;是有区别的。visibility:hidden;仍然占据空间，只是看不到而已；*/
    line-height: 0;    /*行高为0；*/
    height: 0;     /*高度为0；*/
    font-size:0;    /*字体大小为0；*/
}
.clearfix:after {       
    content: ".";     /*内容为“.”就是一个英文的句号而已。也可以不写。*/
    display: block;   /*加入的这个元素转换为块级元素。*/
    clear: both;     /*清除左右两边浮动。*/
    visibility: hidden;      /*可见度设为隐藏。注意它和display:none;是有区别的。visibility:hidden;仍然占据空间，只是看不到而已；*/
    line-height: 0;    /*行高为0；*/
    height: 0;     /*高度为0；*/
    font-size:0;    /*字体大小为0；*/
}
#partOfDoc{
	width: 100%;
    height: 0px;
    padding-bottom: 100%;
    margin: 0;
    position:relative;
}
#partOfDoc p{
	
	position:absolute;
    width: 100%;
    height: 100%;
}
#partOfDoc img{
    width: 100%;
    height: 100%;
}
 
</style>

<script type="text/javascript" src="<%=rootPath %>view/js/modernizr.custom.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.PrintArea.js"></script>
<script type="text/javascript" src="<%=rootPath %>editor/simple_ckeditor/ckeditor.js"></script>
<script type="text/javascript">
var m_docid = <s:property value="irpGoods.goodsid" />;


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
    window.open('<%=rootPath%>solr/searchcontentofsolr.action?searchInfo='+eacapeInfo+'&searchtype='+searchtype); 
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
</head>

<body>
<form id="addcovert" method="post">
</section>
<s:set var="cruser" value="getIrpUser(irpDocument.cruserid)" />
    	
        <section class="text" style="width:100%;">
        	<article class="straight">
	       		<div id="printConn" style="width:100%;">
	            	
	                <ul class="list4" style="width:100%;">
	                	<li  style="width:100%;">
	                		<section style="width:100%;padding-left:0px;">
	                			<p style="text-align:center;font-size:14px;line-height:24px;color:#666;text-indent:0px;"><s:property value="irpGoods.goodsdesc" /></p>
	                		</section>
	                		<div class="clear"></div>
	                	</li>
	                </ul>
	                <div id="partOfDoc" class="documenttxt">
						<s:property value="irpGoods.contentdetial" escapeHtml="false" />
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
            	<%-- <font>标签：</font>
            	<s:iterator value="irpDocument.dockeywords.split(',')" status="st" var="as">
				<a href="javascript:void(0)" onclick="searchInfo1('<s:property value="#as"/>')"><s:property value="#as" /></a>&nbsp;&nbsp;
  				</s:iterator> --%>
            </div>
            
        </section>
		<!-- <section id="informList" class="discussions"></section> -->
    

</form>

</body>
</html>