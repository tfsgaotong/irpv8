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

</script>
</head>

<body>
<input id="channelidStr" type="hidden" value="<s:property value='channelidStr' />" >
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
</body>
</html>