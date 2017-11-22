<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
<title>知识审核</title>
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
<link href="<%=rootPath %>client/images/css.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath %>client/css/css.css" rel="stylesheet" type="text/css" />      
<link href="<%=rootPath %>client/css/oacss.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>client/css/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>client/css/icon.css" />
<link href="<%=rootPath%>client/js/skins/iblue.css" rel="stylesheet" type="text/css" />
<!--
div,form,img,ul,ol,li,dl,dt,dd {margin: 0; padding: 0; border: 0;}
h1,h2,h3,h4,h5,h6 { margin:0; padding:0;}
table,td,tr,th{font-size:12px;}
/* 星级评分 */
.shop-rating {
height: 25px;
overflow: hidden;
zoom: 1;
padding: 2px 0px;
position: relative;
z-index: 999;
}
.shop-rating span {
height: 23px;
display: block;
line-height: 23px;
float: left;
}
.shop-rating span.title {
width: 125px;
text-align: right;
margin-right: 5px;
}
.shop-rating ul {
float: left;
margin:0;padding:0
}
.shop-rating .result {
margin-left: 20px;
padding-top: 2px;
}
.shop-rating .result span {
color: #ff6d02;
}
.shop-rating .result em {
color: #f60;
font-family: arial;
font-weight: bold;
}
.shop-rating .result strong {
color: #666666;
font-weight: normal;
}
.rating-level,
.rating-level a {
	background: url( <%=rootPath%>client/images/star_v2.png) no-repeat scroll 1000px 1000px;
}
.rating-level {
background-position: 0px 0px;
width: 120px;
height: 23px;
position: relative;
z-index: 1000;
}
.rating-level li {
display: inline;
}
.rating-level a {
line-height: 23px;
height: 23px;
position: absolute;
top: 0px;
left: 0px;
text-indent: -999em;
*zoom: 1;
outline: none;
}
.rating-level a.one-star {
width: 20%;
z-index: 6;
}
.rating-level a.two-stars {
width: 40%;
z-index: 5;
}
.rating-level a.three-stars {
width: 60%;
z-index: 4;
}
.rating-level a.four-stars {
width: 80%;
z-index: 3;
}
.rating-level a.five-stars {
width: 100%;
z-index: 2;
}
.rating-level .current-rating,.rating-level a:hover{background-position:0 -28px;}
.rating-level a.one-star:hover,.rating-level a.two-stars:hover,.rating-level a.one-star.current-rating,.rating-level a.two-stars.current-rating{background-position:0 -116px;}
.rating-level .three-stars .current-rating,.rating-level .four-stars .current-rating,.rating-level .five-stars .current-rating{background-position:0 -28px;}
-->
</style>
<style type="text/css">
/*文章分页*/
#page_break {

}
#page_break .collapse {
display: none;
}
#page_break .num {
	padding: 10px 0;
	text-align: center;
}
#page_break .num li{
	display: inline;
	margin: 0 2px;
	padding: 3px 5px;
	border: 1px solid #72BBE6;
	background-color: #fff;
	
	color: #72BBE6;
	text-align: center;
	cursor: pointer;
	font-family: Arial;
	font-size: 12px;
	overflow: hidden;
}
#page_break .num li.on{
	background-color: #72BBE6;
	color: #fff;
	font-weight: bold;
} 
</style> 
<script type="text/javascript" src="<%=rootPath%>client/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
function showAllData(){
	var _show=$.trim($('#show').html());
	if(_show=="查看全文"){
		$('#allDoc').show();
		$('#partOfDoc').hide();
		$('#show').html('取消查看全文');
	} 
}
$(document).ready(function(){
	$('#page_break .num li:first').addClass('on');
	$('#page_break .num a').click(function(){
		var _text= $.trim($(this).text()) ;
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
	});
<!--
function transferProcess(transferType){
	var jqPostDesc = $('#postDesc');
	var validate = jqPostDesc.validatebox('isValid');
	if(!validate){
		jqPostDesc.focus();
		return false;
	}else{
		$.ajax({
			cache : false,
			type : 'post',
			url : '<%=rootPath%>flow/transfer_process.action',
			data : {
				flowObjId: ${flowObj.flowobjid},
				postDesc: jqPostDesc.val(),
				transferType: transferType
			},
			success : function(callback) {
				if(callback=='1'){
					$.dialog.tips('审核成功',1,'32X32/succ.png',function(){
						if (window.opener && !window.opener.closed) {
							try{
								window.opener.page(1);
							}catch(e){}
	    				}
	    			   	window.close();
					});
				}else{
					$.dialog.tips('审核失败',1,'32X32/fail.png');
				}
			}
		});
	}
}
//搜索名字
function searchInfo1(searchInfo){  
		searchtype = 5;  
		if(searchInfo.length>38){
			searchInfo = searchInfo.substring(0,37);	
		}
		var eacapeInfo = encodeURI(searchInfo);
	    window.location.href="<%=rootPath%>solr/searchcontentofsolr.action?searchInfo="+eacapeInfo+"&searchtype="+searchtype; 
}

//修改文章
function updateDocument(_docid){
	window.open('<%=rootPath%>site/to_update_subject.action?docid='+_docid);
}

//删除文档
function deleteDocument(_docid){
	$.dialog.confirm('您确定要删除这个知识吗？',function(){
		$.ajax({
			type:'post',
			url:'<%=rootPath%>site/clientdeletedocument.action',
			data:{'docid':_docid},
			success: function(msg){
				if(msg=="1"){ //删除成功 
			  		$.dialog.tips('删除知识成功',0.5,'32X32/succ.png',function(){ 
				 		window.opener.location.reload(true);
	 				 	window.close();
					});  
				} 
			}
   	 	});
	},function(){}); 
}

//查看流转轨迹
function showFlowPath(_nObjId){
	$.dialog({
		title:'流转轨迹',
		max:false,
		min:false,
		lock:true,
		resize: false,
		width:'600px',
		height:'300px',
		content:'<iframe width="600" height="300" frameborder="0" marginheight="0" marginwidth="0" src="<%=rootPath %>flow/flow_path.action?docId='+_nObjId+'" scrolling="auto" ></iframe>',
		cancelVal:'关闭',
		okVal:'转发',
		cancel:function(){
		}
	});
}
$(function(){
	/* 扩展验证  */
	$.extend($.fn.validatebox.defaults.rules, {
		maxLength : {
			validator: function(value, param){   
	            return value.length < param[0];   
	        },
	        message: '输入内容长度不能超过{0}'  
		}
	}); 
});
//-->
</script>
</head>

<body>
<s:include value="../include/enterprise_head.jsp"></s:include>
<div class="zj_wBox">
	<h1 class="zj_pageMark">您当前的位置：<a href="javascript:void(0);" onclick="showallpublicdoc()">首页</a>&nbsp;&gt;&nbsp;知识审核</h1>
	<div class="zj_xl_txt"> 
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="olbg7" height="300">
      	<tr>
        	<td valign="top" class="olbg8">
        		<table width="874" border="0" cellspacing="0" cellpadding="0" class="boxcenter dttitle">
            	<tr>
              		<td width="874" class="box doctitle boxcenter dttitle" align="center"><s:property value="irpDocument.doctitle" escapeHtml="false" /></td>
            	</tr>
          		</table>
          		<div class="date">
            		<center>创建时间：<s:date name="irpDocument.crtime" format="yyyy-MM-dd HH:mm"/></center>
          		</div>
          		<div class="intro">核心提示：<s:property value="irpDocument.docabstract" escapeHtml="false" /></div>
          		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="dtlab">
            	<tr>
              		<td valign="top" height="57" class="gray12" style="padding-top:10px; padding-left: 66px;">
              			<strong>作者</strong>：<s:property value="irpDocument.docauthor" escapeHtml="false" />
              			<strong style="margin-left: 30px;">来源</strong>：<s:property value="irpDocument.docsourcename" escapeHtml="false" />
              		</td>
            	</tr>
          		</table>
		      	<div class="documenttxt">
          	<%--锚点 --%>
          	<a id="documenttop" name="documenttop"></a> 
	          <div id="partOfDoc" class=" documenttxt"> 
				<s:property value="irpDocument.dochtmlcon" escapeHtml="false" /> 
		      </div> 
		       <div  id="allDoc"  style="display: none;" class=" documenttxt">
	   			  	 <s:property value="irpDocument.attachedcontent" escapeHtml="false" />
	   			</div> 
   			</div>
		      	<s:if test="attacheds!=null && attacheds.size()>0">
		      	<ul class="dtfj">
	          	   <table>
         <s:iterator value="attacheds">
          <tr>
          <td>
          <s:if test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@JPG_FIELD_NAME==typeid">
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
         	   <s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@ID_FIELD_NAMEOTHER==typeid" >
         	       <span style="display: block;background-image: url(<%=rootPath %>client/images/att.png); width:40px; height:43px; background-position: -210px -10px;"></span> 
         	  </s:elseif> 
          </td>
          <td style="padding-left: 20px;"><a href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>" ><s:property value="attdesc"/></a>
          	</td>
          <td style="padding-left: 20px;"> 
          <a href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>" >下载</a>
          <s:if test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@WORD_FIELD_NAME==typeid">
	                 	| <a target="_blank" href="<%=rootPath%>file/online_preview.action?fileName=<s:property value='attfile'/>">在线浏览</a></dd>
	               </s:if>
	                <s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@JPG_FIELD_NAME==typeid">
	               	|  <a target="_blank" href="<%=rootPath%>file/inlinereadfile.action?fileName=<s:property value='attfile'/>">在线浏览</a></dd>
	               </s:elseif></td>
          </tr>
         </s:iterator>
          </table>
          		</ul>
		      	</s:if>
          		<div class="dtgjc">标签：  
	                <s:iterator value="irpDocument.dockeywords.split(',')"  status="st" var="as">
	  				 <a href="javascript:void:(0)" onclick="searchInfo1('<s:property value="#as"/>')"  ><s:property value="#as"/></a>&nbsp;&nbsp;
	  				</s:iterator>
	            </div>
          		<div class="detailcontrol">
          			<h2 onclick="showFlowPath(<s:property value='irpDocument.docid'/>)">
			            <a href="javascript:void(0);">
			            <img src="<%=rootPath %>client/images/xl_r10_c12.gif" />流转轨迹
			            </a>
		            </h2>
            		<h2 onclick="updateDocument(<s:property value='irpDocument.docid'/>)">
			            <a href="javascript:void(0);">
			            <img src="<%=rootPath %>client/images/xl_r10_c17.gif" />修改
			            </a>
		            </h2>
            		<h2 onclick="deleteDocument(<s:property value='irpDocument.docid'/>)">
			            <a href="javascript:void(0);">
			            <img src="<%=rootPath %>client/images/xl_r10_c19.gif" />删除
			            </a>
		            </h2>
          		</div>
          	</td>
      	</tr>
    	</table>
		<div class="marginb7"><center><img src="<%=rootPath %>client/images/xl_r13_c28.gif" border="0" usemap="#Map" /></center>
              <map name="Map" id="Map">
                <area shape="rect" coords="812,1,909,20" href="#" />
              </map>
		</div>
	</div>
	<div class="area1"></div>
	<div class="marginb7 dtbox">
		<div class="zj_title1">
			<div class="zj_tt"><h1>审核信息</h1></div>
		</div>
        <div class="zj_box4" style="text-align: center; padding: 5px;">
			<textarea id="postDesc" class="easyui-validatebox" required="true" validType="maxLength[300]" style="width: 550px; height: 200px; border: 1px solid #999999;"></textarea>
		</div>
	</div>
	<div class="area1"></div>
	<div style="text-align: center;">
		<s:if test="flowNode.nodetype!='start'">
		<a href="javascript:void(0)" onclick="transferProcess(1)" class="newabtngrn">审核通过</a>
		<a href="javascript:void(0)" onclick="transferProcess(2)" class="newabtngrn">返回上一级</a>
		<a href="javascript:void(0)" onclick="transferProcess(3)" class="newabtngrn">返回作者</a>
		</s:if>
		<s:else>
		<a href="javascript:void(0)" onclick="transferProcess(1)" class="newabtngrn">继续审核</a>
		</s:else>
		<a href="javascript:void(0)" onclick="window.close()" class="newabtngrn">关闭</a>
	</div>     
</div>
<s:include value="../include/enterprise_foot.jsp"></s:include>
</body>
</html>
