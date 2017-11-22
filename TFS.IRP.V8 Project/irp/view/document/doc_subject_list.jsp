<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!doctype html>
<html lang="en" class="no-js">
<head>
<meta charset="utf-8">
<title>知识专题-<s:property value="irpChannel.chnldesc" /></title>
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>

<link href="<%=rootPath %>client/images/normalize.css" rel="stylesheet" type="text/css">
<link href="<%=rootPath %>client/css/common1.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<%=rootPath %>view//css/jquery.slideBox.css" type="text/css"></link>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/modernizr.custom.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.slideBox.js"></script>
<script type="text/javascript">
var c_name='tfs_irp_subject';
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


jQuery(function($){


	$('#slide').slideBox({

		duration : 0.1,//滚动持续时间，单位：秒

		easing : 'linear',//swing,linear//滚动特效

		delay : 5,//滚动延迟时间，单位：秒

		hideClickBar : false,//不自动隐藏点选按键

		clickBarRadius : 0

	});
});
</script>
</head>

<body>
<form id="pageForm" action="<s:url namespace="/document" action="document_subject_list"><s:param name="channelid" value="channelid" /></s:url>" method="post">
	<s:hidden name="pageNum" id="pageNum" />
	<s:hidden name="pageSize" id="pageSize"/> 
</form>
<s:include value="../include/client_head.jsp"></s:include>
<section class="mainBox">
	<nav class="privateNav" style="background:url(../images/bg-04.gif) repeat-x">
    	<dl>
        	<dt class="leftBox">全部知识专题</dt>
            <dd class="leftBox navBtns">
				<a href="<s:url namespace="/document" action="document_list" />">知识分类</a>
            	<a href="<s:url namespace="/document" action="map_index" />">知识地图</a>
            	<a href="<s:url namespace="/document" action="document_subject_list" />" class="current">知识专题</a>
			</dd>
            <dd class="clear"></dd>
        </dl>
    </nav>
</section>

<section class="mainBox">
	<section class="layoutLeft">
        <nav class="folders">
		<s:iterator value="irpChannels">
        	<div class="folder">
            	<p><a href="<s:url namespace="/document" action="document_subject_list_old"><s:param name="channelid" value="channelid" /></s:url>" title="<s:property value="chnldesc" />"><s:property value="chnldesc" /></a></p>
                <s:set var="childChnls" value="findChildChannelByParentId(channelid)" />
                <s:if test="#childChnls!=null && #childChnls.size()>0">
                <section>
                	<ul>
                	<s:iterator value="#childChnls">
                    	<li><a href="<s:url namespace="/document" action="document_subject_list_old"><s:param name="channelid" value="channelid" /></s:url>" title="<s:property value="chnldesc" />"><s:property value="chnldesc" /></a></li>
                    </s:iterator>
                    </ul>
                </section>
                </s:if>
            </div>
		</s:iterator>
        </nav>
	</section>
    <section class="layoutMiddle1">
        <div class="banner">
        
          <!-- 轮播 -->
            
        <div id="slide" class="slideBox" >
		<ul class="items" >
		 <s:iterator value="chnlDocLinks52" status="wt">
	       <s:if test="#wt.index<4">
	         <s:set var="document" value="getIrpDocumentById(docid)" />
	        	 <s:if test="#document.urladdress!='' && #document.urladdress!=null">
					<li>
						<a href="<s:property value='#document.urladdress'/>" target="_blank">
							<img src="<s:property value='docCoverPathSource(docid,docflag)' />" width="935" height="400"/>
							<p class="img_title"><s:property value='doctitle'/></p>
						</a>
					</li>
				</s:if>
				<s:else>
					<li>
						<a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>"  target="_blank">
							<img src="<s:property value='docCoverPathSource(docid,docflag)' />" width="935" height="400"/>
							<p class="img_title"><s:property value='doctitle'/></p>
						</a>
						</li>
				</s:else>
			</s:if>
		</s:iterator>
		</ul>
	</div>    
    <!-- /轮播 -->

        </div>
        <div class="rmzj">
            <div class="title1"><h4><a href="#">热门专题</a></h4></div>
            <div class="expert-box">
                <div class="expert-slide">
                    <div class="swiper-box">
                        <div class="expert-container">
                            <div class="swiper-wrapper">
                            <s:iterator value="chnlDocLinks29" status="index">
	                            <s:set var="document" value="getIrpDocumentById(docid)" />
	                			<s:if test="#document.urladdress!='' && #document.urladdress!=null">
	                			<!-- 有知识链接 -->
                            	<s:if test="#index.last" >
                                 <div class="swiper-slide ss-one" style="margin-right: 0">
                                   	<div class="e-card">
                                        <div class="e-img">
                                            <a class="from_expert" href="<s:property value='#document.urladdress'/>" title="<s:property value='doctitle'/>" >
                                                <img width="220" height="150" src="<s:property value='docCoverPath(docid,docflag)' />" />
                                                <div class="fb transition">
                                                    <h3 style="text-align: justify;"><s:property value='doctitle'/></h3>
                                                </div>
                                            </a>
                                        </div>
                                        <div class="e-name"><a href="<s:property value='#document.urladdress'/>" title="<s:property value='doctitle'/>" target="_blank"><s:property value='doctitle'/></a></div>
                                    </div>
                                </div>
                                </s:if>
                                <s:else>
	                                <div class="swiper-slide ss-one">
	                                    <div class="e-card">
	                                        <div class="e-img">
	                                            <a class="from_expert" href="<s:property value='#document.urladdress'/>" title="<s:property value='doctitle'/>" >
	                                                <img width="220" height="150" src="<s:property value='docCoverPath(docid,docflag)' />" />
	                                                <div class="fb transition">
	                                                    <h3 style="text-align: justify;"><s:property value='doctitle'/></h3>
	                                                </div>
	                                            </a>
	                                        </div>
	                                        <div class="e-name"><a href="<s:property value='#document.urladdress'/>" title="<s:property value='doctitle'/>" target="_blank"><s:property value='doctitle'/></a></div>
	                                    </div>
	                                </div>
                                </s:else>
                                </s:if>
                                <!-- 无知识链接 -->
                                <s:else>
                                <s:if test="#index.last" >
                                 <div class="swiper-slide ss-one" style="margin-right: 0">
                                   	<div class="e-card">
                                        <div class="e-img">
                                            <a class="from_expert" href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" >
                                                <img width="220" height="150" src="<s:property value='docCoverPath(docid,docflag)' />" />
                                                <div class="fb transition">
                                                    <h3 style="text-align: justify;"><s:property value='doctitle'/></h3>
                                                </div>
                                            </a>
                                        </div>
                                        <div class="e-name"><a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" target="_blank"><s:property value='doctitle'/></a></div>
                                    </div>
                                </div>
                                </s:if>
                                <s:else>
	                                <div class="swiper-slide ss-one">
	                                    <div class="e-card">
	                                        <div class="e-img">
	                                            <a class="from_expert" href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" >
	                                                <img width="220" height="150" src="<s:property value='docCoverPath(docid,docflag)' />" />
	                                                <div class="fb transition">
	                                                    <h3 style="text-align: justify;"><s:property value='doctitle'/></h3>
	                                                </div>
	                                            </a>
	                                        </div>
	                                        <div class="e-name"><a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" target="_blank"><s:property value='doctitle'/></a></div>
	                                    </div>
	                                </div>
                                </s:else>
                                </s:else>
								</s:iterator>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <div class="rmzj">
            <div class="title1"><h4><a href="#">推荐专题</a></h4></div>
            <div class="expert-box">
                <div class="expert-slide">
                    <div class="swiper-box">
                        <div class="expert-container">
                            <div class="swiper-wrapper">
                            <s:iterator value="chnlDocLinks30" status="index">
	                            <s:set var="document" value="getIrpDocumentById(docid)" />
	                			<s:if test="#document.urladdress!='' && #document.urladdress!=null">
	                			<!-- 有知识链接 -->
                            	<s:if test="#index.last" >
                                 <div class="swiper-slide ss-one" style="margin-right: 0">
                                   	<div class="e-card">
                                        <div class="e-img">
                                            <a class="from_expert" href="<s:property value='#document.urladdress'/>" title="<s:property value='doctitle'/>" >
                                                <img width="220" height="150" src="<s:property value='docCoverPath(docid,docflag)' />" />
                                                <div class="fb transition">
                                                    <h3 style="text-align: justify;"><s:property value='doctitle'/></h3>
                                                </div>
                                            </a>
                                        </div>
                                        <div class="e-name"><a href="<s:property value='#document.urladdress'/>" title="<s:property value='doctitle'/>" target="_blank"><s:property value='doctitle'/></a></div>
                                    </div>
                                </div>
                                </s:if>
                                <s:else>
	                                <div class="swiper-slide ss-one">
	                                    <div class="e-card">
	                                        <div class="e-img">
	                                            <a class="from_expert" href="<s:property value='#document.urladdress'/>" title="<s:property value='doctitle'/>" >
	                                                <img width="220" height="150" src="<s:property value='docCoverPath(docid,docflag)' />" />
	                                                <div class="fb transition">
	                                                    <h3 style="text-align: justify;"><s:property value='doctitle'/></h3>
	                                                </div>
	                                            </a>
	                                        </div>
	                                        <div class="e-name"><a href="<s:property value='#document.urladdress'/>" title="<s:property value='doctitle'/>" target="_blank"><s:property value='doctitle'/></a></div>
	                                    </div>
	                                </div>
                                </s:else>
                                </s:if>
                                <!-- 无知识链接 -->
                                <s:else>
                                <s:if test="#index.last" >
                                 <div class="swiper-slide ss-one" style="margin-right: 0">
                                   	<div class="e-card">
                                        <div class="e-img">
                                            <a class="from_expert" href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" >
                                                <img width="220" height="150" src="<s:property value='docCoverPath(docid,docflag)' />" />
                                                <div class="fb transition">
                                                    <h3 style="text-align: justify;"><s:property value='doctitle'/></h3>
                                                </div>
                                            </a>
                                        </div>
                                        <div class="e-name"><a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" target="_blank"><s:property value='doctitle'/></a></div>
                                    </div>
                                </div>
                                </s:if>
                                <s:else>
	                                <div class="swiper-slide ss-one">
	                                    <div class="e-card">
	                                        <div class="e-img">
	                                            <a class="from_expert" href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" >
	                                                <img width="220" height="150" src="<s:property value='docCoverPath(docid,docflag)' />" />
	                                                <div class="fb transition">
	                                                    <h3 style="text-align: justify;"><s:property value='doctitle'/></h3>
	                                                </div>
	                                            </a>
	                                        </div>
	                                        <div class="e-name"><a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" target="_blank"><s:property value='doctitle'/></a></div>
	                                    </div>
	                                </div>
                                </s:else>
                                </s:else>
								</s:iterator>
                        </div>
                    </div>

                </div>
            </div>
        </div>

      <%--   <div class="zt_left">
            <div class="title1"><h4><a href="#">推荐专题</a></h4></div>
            <s:iterator value="chnlDocLinks30" status="index">
            <s:set var="document" value="getIrpDocumentById(docid)" />
            <s:if test="#document.urladdress!='' && #document.urladdress!=null">
            <!-- 有知识链接 -->
            	<s:if test="#index.last" >
            		<div class="tjzj" style="margin-right: 0">
		                <div class="zj_pic">
			                <a href="<s:property value='#document.urladdress'/>" title="<s:property value='doctitle'/>" target="_blank">
								<img width="220" height="150" src="<s:property value='docCoverPath(docid,docflag)' />" />
							</a>
						</div>
						<p><s:property value='doctitle'/></p>
	            	</div>
            	</s:if>
            	<s:else>
	            	<div class="tjzj">
		                <div class="zj_pic">
			                <a href="<s:property value='#document.urladdress'/>" title="<s:property value='doctitle'/>" target="_blank">
								<img width="220" height="150" src="<s:property value='docCoverPath(docid,docflag)' />" />
							</a>
						</div>
					 <p><s:property value='doctitle'/></p>
	            	</div>
            	</s:else>
            </s:if>
            <!-- 无知识链接 -->
            <s:else>
            	<s:if test="#index.last" >
            		<div class="tjzj" style="margin-right: 0">
		                <div class="zj_pic">
			                <a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" target="_blank">
								<img width="220" height="150" src="<s:property value='docCoverPath(docid,docflag)' />" />
							</a>
						</div>
						<p><s:property value='doctitle'/></p>
	            	</div>
            	</s:if>
            	<s:else>
	            	<div class="tjzj">
		                <div class="zj_pic">
			                <a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" target="_blank">
								<img width="220" height="150" src="<s:property value='docCoverPath(docid,docflag)' />" />
							</a>
						</div>
					 <p><s:property value='doctitle'/></p>
	            	</div>
            	</s:else>
            </s:else>
            </s:iterator>
        </div> --%>

        
        
    </section>
    
</section>



<div class="area10"></div>
<s:include value="../include/client_foot.jsp"></s:include>
</body>
</html>