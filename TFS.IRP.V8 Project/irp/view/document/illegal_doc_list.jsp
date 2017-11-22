<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<script type="text/javascript">
//恢复举报知识
function notpassinformdocument(_docid){
	$.dialog.confirm('您确定要恢复该举报知识吗？',function(){
		$.ajax({
			type:"get",
			url:"<%=rootPath%>site/huifuinfodocument.action",
			cache:false,
			async:false,
			data:{
				docid:_docid
			},
			success:function(msg){
				if(msg>=1){
					$.dialog.tips('恢复成功',1,'32X32/succ.png',function(){
						findDocReportConn();
					});					
				}else{
					$.dialog.tips('操作失败',1,'32X32/fail.png');
				}
			},
			error:function(){
				$.dialog.tips('操作错误',1,'32X32/fail.png');
			}
		}); 
	});
}
</script>
<div id="knowjbdonemenu">
	<div id="knowjbdoneprepend">
	<s:if test="jubaodocuments.size()>0">
		<div class="pan">
			<ul class="myDiscuss list5">
			<s:iterator value="jubaodocuments" var="map" status="st">
				<s:set var="reportUser" value="@com.tfs.irp.util.LoginUtil@findUserById(CRUSERID)" />
				<li>
					<s:date name="JUBAOTIME" format="yyyy-MM-dd HH:mm" />
					<div class="userIcon">
						<dl>
							<dt>
								<a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="#reportUser.userid" /></s:url>" target="_blank" title="<s:property value="#reportUser.showName" />">
									<img src="<s:property value="#reportUser.defaultUserPic" />" alt="用户头像" width="60" height="60" /> 
								</a>
							</dt>
						</dl>
					</div>
					<section>
						<aside></aside>
						<div>
							<a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="DOCID" /></s:url>" target="_blank"><s:property value='DOCTITLE' escapeHtml="false" /></a>
							<p style="font-size:13px;">
								举报内容：<s:property value="INFORMCONTENT" />&nbsp;&nbsp;<s:property value="INFORMVALUE" />
							</p>
							<p>
								<a href="javascript:void(0)" onclick="notpassinformdocument(<s:property value='DOCID'/>)">恢复</a>
							</p>
						</div>
					</section>
					<div class="clear"></div>
				</li>
			</s:iterator>
			</ul>
		</div>
		<div class="pages">
			<s:property value="pageHTML" escapeHtml="false" />
		</div>
	</s:if>
	</div>
<s:else>
	<div class="emptyDiv">未找到非法知识</div>
</s:else>
</div>