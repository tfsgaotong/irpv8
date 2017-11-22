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
//增加修改专题描述
function topicDesc(topicid){
	var loadPage=$.ajax({
		url: '<%=rootPath%>microblog/loadupdateframe.action', 
		data:{
			topicid:topicid
		},
		type:"post", 
		async: false,
		cache: false
	}).responseText;
	$.dialog({
		title:'修改描述',
		max:false,
		min:false,
		lock:true,
		resize: false,
		width:600,
		content:loadPage,
		okVal:'保存',
		ok:function(){
			var topicdescs = $("#topicdescform").val();
			if($.trim(topicdescs).length>$('#topicdesccount').val()){
				return false;
			}else{
				$('#topicform').form('submit',{
					url:'<%=rootPath%>microblog/saveTopicDesc.action',
					cache:false,
					success:function(msg){
						if(msg==1){
							$.dialog.tips('修改成功',1,'32X32/succ.png',function(){
								$('#topicdesc_'+topicid).html(topicdescs);
							});					
						}else{	
							$.dialog.tips('保存失败',1,'32X32/fail.png');	
						}	
					}
				});	
			}
		},
		cancelVal:"关闭",
		cancel:function(){}
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
		<div class="pan">
			<ul class="myDiscuss list5">
			<s:iterator value="irptopiclist">
			<s:set var="currUser" value="@com.tfs.irp.util.LoginUtil@findUserById(userid)" />
				<li>
					<s:date name="crtime" format="yyyy-MM-dd HH:mm" />
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
							<a href="#" target="_blank"><s:property value="topicname" /></a>
							<p id="topicdesc_<s:property value='topicid'/>" style="font-size:13px;">
								专题描述：<s:property value="topicdesc" />
							</p>
							<p>
								<a href="javascript:void(0)" onclick="topicDesc(<s:property value='topicid'/>)">描述</a>&nbsp;|
								<a href="javascript:void(0)" onclick="deletetopic(<s:property value='topicid'/>,'<s:property value="topicname" />')">删除</a>
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
	<div>未找到已审核微知专题</div>
</s:else>
</div>