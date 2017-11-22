<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!doctype html>
<html lang="en" class="no-js">
<head>
<meta charset="utf-8">
<title>知识地图-<s:property value="irpChannel.chnldesc" /></title>
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
<link rel="stylesheet" href="<%=rootPath %>view/css/cssTwo/normalize.css" type="text/css"></link>
<link rel="stylesheet" href="<%=rootPath %>view/css/cssTwo/common1.css" type="text/css"></link>
<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css">
<link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=rootPath %>view/js/jsTwo/modernizr.custom.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/modernizr.custom.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.cookie.js"></script>

<script type="text/javascript">
var c_name='tfs_irp_map';
var c_validDay=365;
$(function(){
	var c_value = $.cookie(c_name);
	if(c_value){
		if(c_value=='list'){
			$('#list').show();
		}else if(c_value=='view'){
			$('#view').show();
		}else{
			$.cookie(c_name,'list',{expires:c_validDay});
			$('#list').show();
		}
	}else{
		$.cookie(c_name,'list',{expires:c_validDay});
		$('#list').show();
	}
});

function tabView(tabid){
	$('.resultList').each(function(){
		$(this).hide();
	});
	$('#'+tabid).show();
	if($.cookie(c_name)!=tabid){
		$.cookie(c_name,tabid,{expires:c_validDay});
	}
}

function page(_nPageNum){
	$('#pageNum').val(_nPageNum);
	$('#pageForm').submit();
}
</script>
</head>

<body>
<form id="pageForm" action="<s:url namespace="/document" action="document_map_list"><s:param name="channelid" value="channelid" /></s:url>" method="post">
	<s:hidden name="pageNum" id="pageNum" />
	<s:hidden name="pageSize" id="pageSize"/> 
</form>
<s:include value="../include/client_head.jsp"></s:include>
<section class="mainBox">
	<nav class="privateNav">
    	<dl>
        	<dt class="leftBox">全部知识地图</dt>
            <dd class="leftBox navBtns">
            	<a href="<s:url namespace="/document" action="document_list" />">知识分类</a>
            	<a href="<s:url namespace="/document" action="map_index" />" class="current">知识地图</a>
            	<a href="<s:url namespace="/document" action="document_subject_list" />">知识专题</a>
            </dd>
            <dd class="clear"></dd>
        </dl>
    </nav>
</section>
<section class="mainBox">
	<section class="layoutLeft">
        <nav class="folders">
        
         <s:if test="documentMapOne!=null && documentMapOne.size()>0">
        	<div class="folder">
            		<p><a href="<s:url namespace="/document" action="map_index_wei"><s:param name="maptype" value="410" /></s:url>" title="一维知识地图">一维知识地图</a></p>
                <section>
                	<ul>
                	<s:iterator value="documentMapOne">
                    	<li><a href="<s:url namespace="/document" action="document_map_list"><s:param name="channelid" value="channelid" /><s:param name="maptype" value="410" /></s:url>" title="<s:property value="chnldesc" />"><s:property value="chnldesc" /></a></li>
                    </s:iterator>
                    </ul>
                </section>
            </div>
           </s:if>
           <s:if test="documentMapTwo!=null && documentMapTwo.size()>0">
        	<div class="folder">
            		<p><a href="<s:url namespace="/document" action="map_index_wei"><s:param name="maptype" value="420" /></s:url>" title="二维知识地图">二维知识地图</a></p>
                <section>
                	<ul>
                	<s:iterator value="documentMapTwo">
                    	<li><a href="<s:url namespace="/document" action="document_map_list"><s:param name="channelid" value="channelid" /><s:param name="maptype" value="420" /></s:url>" title="<s:property value="chnldesc" />"><s:property value="chnldesc" /></a></li>
                    </s:iterator>
                    </ul>
                </section>
            </div>
           </s:if>
           <s:if test="documentMapThree!=null && documentMapThree.size()>0">
        	<div class="folder">
            		<p><a href="<s:url namespace="/document" action="map_index_wei"><s:param name="maptype" value="430" /></s:url>" title="多维知识地图">多维知识地图</a></p>
                <section>
                	<ul>
                	<s:iterator value="documentMapThree">
                    	<li><a href="<s:url namespace="/document" action="document_map_list"><s:param name="channelid" value="channelid" /><s:param name="maptype" value="430" /></s:url>" title="<s:property value="chnldesc" />"><s:property value="chnldesc" /></a></li>
                    </s:iterator>
                    </ul>
                </section>
            </div>
           </s:if>
           <s:if test="documentMapOther!=null && documentMapOther.size()>0">
        	<div class="folder">
            		<p><a href="<s:url namespace="/document" action="map_index_wei"><s:param name="maptype" value="440" /></s:url>" title="聚类知识地图">聚类知识地图</a></p>
                <section>
                	<ul>
                	<s:iterator value="documentMapOther">
                    	<li><a href="<s:url namespace="/document" action="document_map_list"><s:param name="channelid" value="channelid" /><s:param name="maptype" value="440" /></s:url>" title="<s:property value="chnldesc" />"><s:property value="chnldesc" /></a></li>
                    </s:iterator>
                    </ul>
                </section>
            </div>
           </s:if>
           
        </nav>
	</section>
    <section class="layoutMiddle1"  <%-- style="background: url(<%=rootPath %>view/images/imagesTwo/map_bg.jpg) center top ;" --%>>
      <s:if test="documentMapOne!=null && documentMapOne.size()>0">
	     <div class="zszt_menu"  style="float:left;margin-right: 0px;">
	            <div class="title1" style="padding-left: 0px;padding-top: 0px;"><h4><a style="padding-bottom: 7px;" href="<s:url namespace="/document" action="map_index_wei"><s:param name="maptype" value="410" /></s:url>" title="一维知识地图">一维知识地图</a></h4></div>
	                <a href="<s:url namespace="/document" action="map_index_wei"><s:param name="maptype" value="410" /></s:url>" >
	                   <img width="450" height="300"  src="<%=rootPath %>view/images/imagesTwo/map_01.jpg" />
	            </a>
	        </div>
  	 </s:if>
  	 <s:if test="documentMapTwo!=null && documentMapTwo.size()>0">
	     <div class="zszt_menu"  <s:if test="documentMapOne!=null && documentMapOne.size()>0"> style="float:right;margin-right: 0px;" </s:if>>
	            <div class="title1" style="padding-left: 0px;padding-top: 0px;"><h4><a style="padding-bottom: 7px;" href="<s:url namespace="/document" action="map_index_wei"><s:param name="maptype" value="420" /></s:url>" title="二维知识地图">二维知识地图</a></h4></div>
	                <a href="<s:url namespace="/document" action="map_index_wei"><s:param name="maptype" value="420" /></s:url>" >
	                   <img width="450" height="300"  src="<%=rootPath %>view/images/imagesTwo/map_02.jpg" />
	            </a>
	        </div>
  	 </s:if>
  	 <s:if test="documentMapThree!=null && documentMapThree.size()>0">
	     <div class="zszt_menu"  <s:if test="documentMapTwo!=null && documentMapTwo.size()>0"><s:if test="documentMapOne==null || documentMapOne.size()<=0">style="float:right;margin-right: 0px;"</s:if></s:if>>
	            <div class="title1" style="padding-left: 0px;padding-top: 0px;"><h4><a style="padding-bottom: 7px;" href="<s:url namespace="/document" action="map_index_wei"><s:param name="maptype" value="430" /></s:url>" title="多维知识地图">多维知识地图</a></h4></div>
	                <a href="<s:url namespace="/document" action="map_index_wei"><s:param name="maptype" value="430" /></s:url>" >
	                   <img width="450" height="300" src="<%=rootPath %>view/images/imagesTwo/map_03.jpg" />
	            </a>
	        </div>
  	 </s:if>
  	 <s:if test="documentMapOther!=null && documentMapOther.size()>0">
	     <div class="zszt_menu"  style="margin-right:20px;">
	            <div class="title1" style="padding-left: 0px;padding-top: 0px;" ><h4><a style="padding-bottom: 7px;" href="<s:url namespace="/document" action="map_index_wei"><s:param name="maptype" value="440" /></s:url>" title="聚类知识地图">聚类知识地图</a></h4></div>
	                <a href="<s:url namespace="/document" action="map_index_wei"><s:param name="maptype" value="440" /></s:url>" >
	                   <img width="450" height="300" src="<%=rootPath %>view/images/imagesTwo/map_04.jpg" />
	            </a>
	        </div>
  	 </s:if>
       
    </section>
</section>
<s:include value="../include/client_foot.jsp"></s:include>
</body>
</html>