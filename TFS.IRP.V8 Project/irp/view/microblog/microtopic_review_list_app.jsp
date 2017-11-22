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
//专题通过
function topicPass(topicid,topicname){
	$.dialog.confirm('您确定要通过这个专题吗',function(){
		$.ajax({
			type:"post",
			url:"<%=rootPath%>microblog/passtopic.action",
			cache:false,
			async:false,
			data:{
				topicid:topicid,
				topicname:topicname
			},
			success:function(status){
				if(status>=1){
					$.dialog.tips('专题通过审核',1,'32X32/succ.png',function(){
						findMicrotopicConn();
					});
				}else{
					$.dialog.tips('专题通过失败',1,'32X32/fail.png');
				}
			},
			error:function(){
				$.dialog.tips('专题通过失败',1,'32X32/fail.png');
			}
		});
	});
}

//删除专题
function deletetopic(topicid,topicname){
	$.dialog.confirm('你确定要删除这个专题吗',function(){
		$.ajax({
			type:"post",
			url:"<%=rootPath%>microblog/deletetopic.action",
			cache:false,
			async:false,
			data:{
				topicid:topicid,
				topicname:topicname
			},
			success:function(status){
				if(status>=1){
					$.dialog.tips('删除专题成功',1,'32X32/succ.png',function(){
						findMicrotopicConn();
					});
				}else{
					$.dialog.tips('删除专题失败',1,'32X32/fail.png');
				}
			},
			error:function(){
				$.dialog.tips('删除专题失败',1,'32X32/fail.png');
			}
		});
	});
}
//-->
</script>
<div>
	<div>
	<s:if test="irptopiclist.size()>0">
		<div class="pan" style="padding-left:0px;">
			<ul class="myDiscuss list5" style="width:100%;">
			<s:iterator value="irptopiclist">
			<s:set var="currUser" value="@com.tfs.irp.util.LoginUtil@findUserById(userid)" />
				<li style="font-size:13px">
					<s:date name="crtime" format="yyyy-MM-dd HH:mm" />
					<div class="userIcon" style="width:40px;height:40px;">
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
							<a href="#" target="_blank"><s:property value="topicname" /></a>
							<p style="font-size:13px;">
								专题描述：<s:property value="topicdesc" />
							</p>
							<p>
								<a href="javascript:void(0)" onclick="topicPass(<s:property value='topicid'/>,'<s:property value="topicname" />')">通过</a>&nbsp;|
								<a href="javascript:void(0)" onclick="deletetopic(<s:property value='topicid'/>,'<s:property value="topicname" />')">删除</a>
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
	<div class="emptyDiv">未找到待审核微知专题</div>
</s:else>
</div>