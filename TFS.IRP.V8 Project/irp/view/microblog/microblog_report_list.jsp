<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<script type="text/javascript">
<!--
//通过举报
function informpass(microblogid){
	$.dialog.confirm('您确要把此条微知置为非法微知吗？',function(){
		$.ajax({
			type:"get",
			url:"<%=rootPath%>microblog/passinformdesc.action",
			cache:false,
			async:false,
			data:{
				microblogids:microblogid
			},
			success:function(msg){
				if(msg>=1){
					$.dialog.tips('通过成功',1,'32X32/succ.png',function(){
						findMicroblogConn();
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

//删除举报内容
function informdelete(informid){
	 $.dialog.confirm('您确定要取消这条举报内容吗？',function(){
		$.ajax({
			type:"get",
			url:"<%=rootPath%>microblog/deleteinformdesc.action",
			cache:false,
			async:false,
			data:{
				informid:informid
			},
			success:function(msg){
				if(msg==1){
					$.dialog.tips('取消成功',1,'32X32/succ.png',function(){
						findMicroblogConn();
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
	<s:if test="irpMicrobloglist.size()>0">
		<div class="pan">
			<ul class="myDiscuss list5">
			<s:iterator value="irpMicrobloglist">
			<s:set var="currUser" value="@com.tfs.irp.util.LoginUtil@findUserById(USERIDER)" />
				<li>
					&nbsp;&nbsp;&nbsp;&nbsp;<a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="#currUser.userid" /></s:url>" target="_blank"><s:property value="#currUser.showName" /></a> &nbsp;&nbsp;&nbsp;&nbsp;举报&nbsp;&nbsp;&nbsp;&nbsp; <s:date name="CRTIMEER" format="yyyy-MM-dd HH:mm" /> 
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
						
							<dl>
							<dt>
								<a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="USERID" /></s:url>" target="_blank"><s:property value="SHOWNAME" /></a>
							</dt>
						</dl>
							<p style="font-size:13px;">
								<s:property value='getMicroblogContent(MICROBLOGCONTENT)' escapeHtml="false" />
							</p>
						<s:if test="TRANSPONDID>0">
							<s:set var="transpondMicroblog" value="transpondIrpMicroblog(TRANSPONDID)" />
							<s:if test="#transpondMicroblog!=null && #transpondMicroblog.isdel==0">
							<article class="quote">
	                            <p>
	                            	<a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="#transpondMicroblog.userid" /></s:url>" target="_blank"><s:property value="getShowPageViewNameByUserId(#transpondMicroblog.userid)" /></a>
	                            </p>
	                            <p>
	                            	<s:property value='getMicroblogContent(#transpondMicroblog.microblogcontent)' escapeHtml="false" />
                            	</p>
								<p class="shareInfo">
									<span><s:date name="#transpondMicroblog.crtime" format="yyyy-MM-dd HH:mm" /></span>
									<span>来自<s:property value="#transpondMicroblog.fromdata" /> </span>
									<span class="links">
										<a href="#" target="_blank">原文转发(<s:property value="#transpondMicroblog.transpondcount" />)</a>
										<a href="#" target="_blank">原文评论(<s:property value="#transpondMicroblog.commentcount" />)</a>
									</span>
								</p>
	                        </article>
							</s:if>
						</s:if>
							<p style="font-size:13px;">
								举报内容：<s:property value="INFORMCONTENT" />&nbsp;&nbsp;<s:property value="INFORMVALUE" />
							</p>
							<p>
								<a href="javascript:void(0)" onclick="informpass(<s:property value='MICROBLOGID'/>)">通过</a>&nbsp;|
								<a href="javascript:void(0)" onclick="informdelete(<s:property value='INFORMID'/>)">取消</a>
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
	<div class="emptyDiv">未找到投诉微知</div>
</s:else>
</div>