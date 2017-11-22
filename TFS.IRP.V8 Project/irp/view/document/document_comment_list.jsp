<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<input type="hidden" name="replayUserId" id="replayUserId"/>
<s:if test="irpDocrecommends==null || irpDocrecommends.size==0">
<div style="margin:8px;">暂时没有评论...</div>
</s:if>
<s:else>
<form id="document_docrecommendfrm">
	<s:hidden name="docid" />
	<s:hidden name="channelid" />
	<s:hidden name="pageNum" id="pageNum" />
	<s:hidden name="pageSize" id="pageSize"/> 
</form>
<s:iterator value="irpDocrecommends" var="docs">
<s:set var="userName" value="@com.tfs.irp.projectperson.web.IrpProjectPersonAction@findUserNameByUserId(cruserid)" />
<s:if test="isXiangGuan=='true'">
<div id="quote_<s:property value='recommendid'/>" style="display:none;">
	<blockquote>
		<span><s:property value="#userName"/> 发表于 <s:date name="crtime" format="yyyy-MM-dd HH:mm"/> <a href="#discuss" onclick="removeReplay()">[取消]</a></span>
		<br /><s:property value="recommend" escapeHtml="false"/>
	</blockquote>
</div>
</s:if>
<div class="labs" <s:if test="isXiangGuan=='true'">onmouseover="mouseEvt(<s:property value='recommendid'/>,1)" onmouseout="mouseEvt(<s:property value='recommendid'/>,0)"</s:if>>
	<dl>
    	<dt><a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="cruserid" /></s:url>" target="_blank"><img src="<s:property value='userPicUrl'/>"/></a></dt>
        <dd class="text">
        	<div class="user">
        		<a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="cruserid" /></s:url>" target="_blank"><s:property value="#userName"/></a>
        		<aside>
        			<s:if test="isXiangGuan=='true'"><a href="#discuss" id="replyLink_<s:property value='recommendid'/>" style="display:none;" onclick="appendReplayUsername(<s:property value='recommendid'/>)">[回复]</a></s:if>
        			<span><s:date name="crtime" format="yyyy-MM-dd HH:mm"/></span>
        		</aside>
        	</div>
        	<s:if test="parentDocRecommend!=null">
            <article class="quote">
            	<div class="user">
            		<a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="parentDocRecommend.cruserid" /></s:url>" target="_blank"><s:property value="@com.tfs.irp.projectperson.web.IrpProjectPersonAction@findUserNameByUserId(parentDocRecommend.cruserid)"/></a>
            		<aside>
            			<span><s:date name="parentDocRecommend.crtime" format="yyyy-MM-dd HH:mm"/> </span>
            		</aside>
            	</div>
                <p><s:property value="parentDocRecommend.recommend" escapeHtml="false"/></p>
            </article>
            </s:if>
            <p><s:property value="recommend" escapeHtml="false"/></p>
        </dd>
        <dd class="clear"></dd>
	</dl>
</div>
</s:iterator>
<div class="area20"></div>
<div class="pages" style="text-align:right;">
	<s:property value="pageHTML" escapeHtml="false" />
</div>
<%--下面这个方法，留着这个页面共用 --%>
<script type="text/javascript">
<s:if test="isXiangGuan=='true'">
//显示隐藏回复
function mouseEvt(rId,nType){
	var rLink = $('#replyLink_'+rId);
	if(rLink){
		if(nType==1&&rLink.css('display')=='none'){
			rLink.show();
		}else if(nType==0&&rLink.css('display')!='none'){
			rLink.hide();
		}
	}
}
</s:if>
//取消回复
function removeReplay(){
	$('#quoteInfo').hide();
	$('#quoteInfo').html('');
	$('#replayUserId').val('');
}
//回复的回复操作
function appendReplayUsername(replayUserId){  
	$('#replayUserId').val(replayUserId);
	$('#quoteInfo').html($('#quote_'+replayUserId).html());
	$('#quoteInfo').show();
}
function page(pageNum){ 
	$('#pageNum').val(pageNum);
	var sData=$('#document_docrecommendfrm').serialize(); 
	var sHtmlConn = $.ajax({ 
 		type:'post', 
 		url: '<%=rootPath%>document/doccomment_list.action',
 		data: sData,
 		dataType: "json",
		async: false,
	    cache: false  
 	}).responseText;
	$('#commentList').html(sHtmlConn);
}
</script>
</s:else>