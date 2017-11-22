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
<link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css">
<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css">

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
            	<a href="<s:url namespace="/document" action="document_map_list" />" class="current">知识地图</a>
            	<a href="<s:url namespace="/document" action="document_subject_list" />">知识专题</a>
            </dd>
            <dd class="clear"></dd>
        </dl>
    </nav>
</section>
<section class="mainBox">
	<article class="location">
	<s:iterator value="rootChannels" status="index">
		<s:if test="#index.first">
		<strong><a href="<s:url namespace="/document" action="document_map_list" />" title="<s:property value="chnldesc" />"><s:property value="chnldesc" /></a></strong>
		</s:if>
		<s:else>
		 > <a href="<s:url namespace="/document" action="document_map_list"><s:param name="channelid" value="channelid" /></s:url>" title="<s:property value="chnldesc" />"><s:property value="chnldesc" /></a>
		</s:else>
	</s:iterator>
	</article>
	<section class="layoutLeft">
        <nav class="folders">
		<s:iterator value="irpChannels">
        	<div class="folder">
            	<p><a href="<s:url namespace="/document" action="document_map_list"><s:param name="channelid" value="channelid" /></s:url>" title="<s:property value="chnldesc" />"><s:property value="chnldesc" /></a></p>
                <s:set var="childChnls" value="findChildMapByParentId(channelid)" />
                <s:if test="#childChnls!=null && #childChnls.size()>0">
                <section>
                	<ul>
                	<s:iterator value="#childChnls">
                    	<li><a href="<s:url namespace="/document" action="document_map_list"><s:param name="channelid" value="channelid" /></s:url>" title="<s:property value="chnldesc" />"><s:property value="chnldesc" /></a></li>
                    </s:iterator>
                    </ul>
                </section>
                </s:if>
            </div>
		</s:iterator>
        </nav>
	</section>
    <section class="result">
        <section id="list" class="resultList" style="display:none;">
        	<div class="reorder"><span>显示方式：</span><em class="current" onclick="tabView('list')">列表</em><em onclick="tabView('view')">视图</em></div>
			<s:if test="chnlDocLinks!=null && chnlDocLinks.size()>0">
			<ul class="list1">
				<s:iterator value="chnlDocLinks">
					<s:set var="cruser" value="findUserByUserId(cruserid)" />
            	<li>
            		<em class="ellipsis"><s:if test="docchannel>0">
				 		<img src="<%=rootPath %>view/images/jinghua.gif"/> 
				 	</s:if>
				 	<s:else>
				 		<img src="<%=rootPath %>view/images/gl_r5_c3.gif" />
				 	</s:else></em>
            		<label class="ellipsis">|</label>
            		<span style="width:680px;" class="ellipsis">
            			<a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" target="_blank"><s:property value='doctitle'/></a>
            		</span>
            		<aside style="width:200px;" class="ellipsis">
            			<s:property value="hitscount"/>&nbsp;/&nbsp;<s:property value="commentcount"/>
						·
						<s:date name="docpubtime" format="yyyy-MM-dd" />
						<a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="#cruser.userid" /></s:url>" target="_blank"><s:property value="#cruser.showName" /></a>
					</aside>
            	</li>
	            </s:iterator>
			</ul>
			<div class="area20"></div>
            <div class="pages"><s:property value="pageHTML" escapeHtml="false" /></div>
			</s:if>
			<s:else>
			<ul class="list1">
				<li>该分类下暂时没有知识...</li>
			</ul>
			</s:else>
        </section>
        
		<section id="view" class="resultList" style="display:none;">
        	<div class="reorder"><span>显示方式：</span><em onclick="tabView('list')">列表</em><em onclick="tabView('view')" class="current">视图</em></div>
		<s:if test="chnlDocLinks!=null && chnlDocLinks.size()>0">
			<s:iterator value="chnlDocLinks">
			<s:set var="cruser" value="findUserByUserId(cruserid)" />
			<s:set var="document" value="getIrpDocumentById(docid)" />
			<article class="item">
            	<h1 class="ellipsis nowrap"><a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" target="_blank"><s:property value='doctitle'/></a></h1>
            	<aside>
					<img width="220" height="150" src="<s:property value='docCoverPath(docid,docflag)' />" />
            	</aside>
                <section>
                	<p style="max-height:123px;" class="ellipsis" title="<s:property value="#document.docabstract" />">
                		<s:property value="#document.docabstract" />
               		</p>
                	<div class="date">
                		<s:property value="hitscount"/>&nbsp;/&nbsp;<s:property value="commentcount"/>
						·
						<s:date name="docpubtime" format="yyyy-MM-dd" />
						<a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="#cruser.userid" /></s:url>" target="_blank"><s:property value="#cruser.showName" /></a>
					</div>
                </section>
                <div class="clear"></div>
			</article>
			</s:iterator>
			<div class="area20"></div>
            <div class="pages"><s:property value="pageHTML" escapeHtml="false" /></div>
		</s:if>
		<s:else>
			<ul class="list1">
				<li>该分类下暂时没有知识...</li>
			</ul>
		</s:else>
        </section>
    </section>
</section>
<s:include value="../include/client_foot.jsp"></s:include>
</body>
</html>