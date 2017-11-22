<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>  
<script type="text/javascript">
<!--
//通过举报知识
function infromDocument(_docid){
	$.dialog.confirm('您确要把此条知识置为非法知识吗？',function(){
		$.ajax({
			type:"get",
			url:"<%=rootPath%>site/passinformdocument.action",
			cache:false,
			async:false,
			data:{
				docid:_docid
			},
			success:function(msg){
				if(msg>=1){
					$.dialog.tips('通过成功',1,'32X32/succ.png',function(){
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

//删除举报内容知识
function informdeletedocument(docid,informid){
	 $.dialog.confirm('您确定要取消这条举报内容吗',function(){
		$.ajax({
			type:"get",
			url:"<%=rootPath%>site/notpassinformdocument.action",
			cache:false,
			async:false,
			data:{
				docid:informid
			},
			success:function(msg){
				if(msg==1){
					$.dialog.tips('取消成功',1,'32X32/succ.png',function(){
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
//-->
</script>
<div>
	<div>
	<s:if test="jubaodocuments.size()>0">
		<div class="pan">
			<ul class="myDiscuss list5">
			<s:iterator value="jubaodocuments">
			<s:set var="currUser" value="@com.tfs.irp.util.LoginUtil@findUserById(CRUSERID)" />
				<li>
					<s:date name="JUBAOTIME" format="yyyy-MM-dd HH:mm" />
					<div class="userIcon">
						<dl>
							<dt>
								<a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="#currUser.userid" /></s:url>" target="_blank" title="<s:property value="#currUser.showName" />">
									<img src="<s:property value="#currUser.defaultUserPic" />" alt="用户头像" width="60" height="60" /> 
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
								<a href="javascript:void(0)" onclick="infromDocument(<s:property value='DOCID'/>)">通过</a>&nbsp;|
								<a href="javascript:void(0)" onclick="informdeletedocument(<s:property value='DOCID'/>,<s:property value='INFORMID'/>)">取消</a>
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
	<div class="emptyDiv">未找到投诉知识</div>
</s:else>
</div>