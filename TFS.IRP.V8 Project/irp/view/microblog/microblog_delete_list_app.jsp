<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.tfs.irp.user.entity.IrpUser,com.tfs.irp.util.LoginUtil"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
    IrpUser loginUser = LoginUtil.getLoginUser();
%>  
<script type="text/javascript">
<!--
/*
 * 恢复被举报的微知
 */
function restoreMicroblog(microblogid){
	$.dialog.confirm('您确要把此条微知恢复为正常状态的吗？',function(){
		$.ajax({
			type:"get",
			url:"<%=rootPath%>microblog/recoverinformdesc.action",
			cache:false,
			async:false,
			data:{
				microblogids:microblogid
			},
			success:function(msg){
				if(msg>=1){
					$.dialog.tips('恢复成功',1,'32X32/succ.png',function(){
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

//彻底删除
function thoroughDelete(_microblogid){
	$.dialog.confirm('您确定要彻底删除此条微知吗',function(){
		$.ajax({
			type:'post',
			url:'<%=rootPath%>microblog/thoroughdeletemicroblog.action',
			cache:false,
			data:{
				microblogidill:_microblogid
			},
			success:function(msg){
				if(msg==1){
					$.dialog.tips('删除成功',1,'32X32/succ.png',function(){
						findMicroblogConn();
					});
				}else{
					$.dialog.tips('删除失败',1,'32X32/fail.png');
				}
			}
		});
	});
}
//-->
</script>
<div>
	<div>
	<s:if test="irpMicrobloglist.size()>0">
		<div class="pan" style="padding-left:0px">
			<ul class="myDiscuss list5" style="width:100%;">
			<s:iterator value="irpMicrobloglist">
			<s:set var="currUser" value="@com.tfs.irp.util.LoginUtil@findUserById(USERID)" />
				<li style="font-size:13px">
					<s:date name="CRTIME" format="yyyy-MM-dd HH:mm" />
					<div class="userIcon"  style="width:40px;height:40px;">
						<dl>
							<dt>
								<a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="#currUser.userid" /></s:url>" target="_blank" title="<s:property value="#currUser.showName" />">
									<img src="<s:property value="#currUser.defaultUserPic" />" alt="用户头像" width="40" height="40" /> 
								</a>
							</dt>
						</dl>
					</div>
					<section style="margin-left:40px;padding-left:10px;padding-right:10px;">
						<aside></aside>
						<div style="padding:5px 10px">
							<a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="#currUser.userid" /></s:url>" target="_blank"><s:property value="#currUser.showName" /></a>
							<p style="font-size:13px;">
								<s:property value='getMicroblogContent(MICROBLOGCONTENT)' escapeHtml="false" />
							</p>
						<s:if test="TRANSPONDID>0">
							<s:set var="transpondMicroblog" value="transpondIrpMicroblog(TRANSPONDID)" />
							<s:if test="#transpondMicroblog!=null && #transpondMicroblog.isdel==0">
							<article class="quote clearfix" style="margin:0px">
	                            <p>
	                            	<a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="#transpondMicroblog.userid" /></s:url>" target="_blank"><s:property value="getShowPageViewNameByUserId(#transpondMicroblog.userid)" /></a>
	                            </p>
	                            <p>
	                            	<s:property value='getMicroblogContent(#transpondMicroblog.microblogcontent)' escapeHtml="false" />
                            	</p>
								<p class="shareInfo" style="margin-top:5px;height:auto">
									<span><s:date name="#transpondMicroblog.crtime" format="yyyy-MM-dd HH:mm" /></span>
									<span>来自<s:property value="#transpondMicroblog.fromdata" /> </span>
									<span class="links clearfix" style="position:static;display:block">
										<a style="margin-left:0px" href="#" target="_blank">原文转发(<s:property value="#transpondMicroblog.transpondcount" />)</a>
										<a href="#" target="_blank">原文评论(<s:property value="#transpondMicroblog.commentcount" />)</a>
									</span>
								</p>
	                        </article>
							</s:if>
						</s:if>
							<p>
								<a href="javascript:void(0)" onclick="restoreMicroblog(<s:property value='MICROBLOGID'/>)">恢复</a>&nbsp;|
								<a href="javascript:void(0)" onclick="thoroughDelete(<s:property value='MICROBLOGID' />)">彻底删除</a>
							</p>
						</div>
					</section>
					<div class="clear"></div>
				</li>
			</s:iterator>
			</ul>
		</div>
		<div class="pages" style="padding-bottom:20px;">
			<s:property value="pageHTML" escapeHtml="false" />
		</div>
	</s:if>
	</div>
<s:else>
	<div class="emptyDiv">未找到微知信息</div>
</s:else>
</div>