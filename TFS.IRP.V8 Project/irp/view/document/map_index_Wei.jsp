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
	<article class="location">
		<strong><s:property value="maptypeName" /></strong>
	</article>
	<section class="layoutLeft">
        <nav class="folders">
		<s:iterator value="irpChannels">
        	<div class="folder">
        		 <s:set var="childChnls11" value="findChildMapByParentId(channelid)" />
              		 <s:if test="#childChnls11!=null && #childChnls11.size()>0">
            		<p><a href="<s:url namespace="/document" action="document_map_list"><s:param name="channelid" value="channelid" /><s:param name="maptype" value="maptype" /></s:url>" title="<s:property value="chnldesc" />"><s:property value="chnldesc" /></a></p>
                  	</s:if>
                  	<s:else>
                  	<p><a href="<s:url namespace="/document" action="document_map_list"><s:param name="channelid" value="channelid" /><s:param name="isLeft" value="1" /><s:param name="maptype" value="maptype" /></s:url>" title="<s:property value="chnldesc" />"><s:property value="chnldesc" /></a></p>
                  	<%-- <p><s:property value="chnldesc" /></p> --%>
                  
                  	</s:else>
                <s:set var="childChnls" value="findChildMapByParentId(channelid)" />
                <s:if test="#childChnls!=null && #childChnls.size()>0">
                <section>
                	<ul>
                	<s:iterator value="#childChnls">
                		<%--  <s:set var="childChnls1" value="findChildMapByParentId(channelid)" />
                		 <s:if test="#childChnls1!=null && #childChnls1.size()>0">
                    		<li><a href="<s:url namespace="/document" action="document_map_list"><s:param name="channelid" value="channelid" /></s:url>" title="<s:property value="chnldesc" />"><s:property value="chnldesc" /></a></li>
                    	</s:if>
                    	<s:else>
                    		<li><a href="<s:url namespace="/document" action="document_map_list"><s:param name="channelid" value="channelid" /><s:param name="isLeft" value="1" /></s:url>" title="<s:property value="chnldesc" />"><s:property value="chnldesc" /></a></li>
                    
                    	</s:else> --%>
                  
                    		<li><s:property value="chnldesc" /></li>
                    
                    </s:iterator>
                    </ul>
                </section>
                </s:if>
            </div>
		</s:iterator>
        </nav>
	</section>
    <section class="layoutMiddle1" style=" margin: 0px 0 0 20px;">
    <s:iterator value="irpChannels" status="index">
     <div class="zszt_menu" <s:if test="#index.count%2==0">style="float: right;margin-right: 0px;"</s:if> style="margin-right: 0px;">
            <div class="title1"style="padding-top: 0px;padding-left: 0px;" ><h4><a style="padding-bottom: 7px;" href="<s:url namespace="/document" action="document_map_list"><s:param name="channelid" value="channelid" /><s:param name="maptype" value="maptype" /></s:url>" title="<s:property value="chnldesc" />"><s:property value="chnldesc" /></a></h4></div>
             <s:if test="imageid!=null">
                 <s:iterator value="imageid.split(',')" var="attid"  status="index1">
                  <s:if test="#index1.first">
                    <a href="<s:url namespace="/document" action="document_map_list"><s:param name="channelid" value="channelid" /><s:param name="maptype" value="maptype" /></s:url>" >
                      <img width="450" height="300" src="<s:property value='coverPathSource(#attid)' />" />
                    </a>
                  </s:if>
                    </s:iterator>
                 </s:if>
                 <s:else>
                <a href="<s:url namespace="/document" action="document_map_list"><s:param name="channelid" value="channelid" /><s:param name="maptype" value="maptype" /></s:url>" >
                    <img width="450" height="300" src="<%=rootPath %>view/images/imagesTwo/zszt_pic_03.jpg" />
                    </a>
                 </s:else>
        </div>
		</s:iterator>
      
       
    </section>
</section>
<s:include value="../include/client_foot.jsp"></s:include>
</body>
</html>