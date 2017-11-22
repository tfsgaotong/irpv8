<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<script type="text/javascript">
<!--
//查看流转轨迹
function showFlowPath(_nObjId){
	$.dialog({
		title:'流转轨迹',
		max:false,
		min:false,
		lock:true,
		resize: false,
		width:'600px',
		height:'300px',
		content:'<iframe width="600" height="300" frameborder="0" marginheight="0" marginwidth="0" src="<%=rootPath %>flow/flow_path.action?docId='+_nObjId+'" scrolling="auto" ></iframe>',
		cancelVal:'关闭',
		okVal:'转发',
		cancel:function(){
		}
	});
}

//处理审核信息
function processFlow(_nFlowObjId, _nObjId){
	var winUrl = "<%=rootPath%>flow/flow_doc.action?flowObjId="+_nFlowObjId+"&docId="+_nObjId;
	window.open(winUrl);
}

//签收审核信息
function signFlow(flowObjId){
	$.ajax({
		cache : false,
		type : 'post',
		url : '<%=rootPath%>flow/sign_flow.action',
		data : {
			flowObjId: flowObjId
		},
		success : function(callback) {
			if(callback=='1'){
				$.dialog.tips('签收成功',1,'32X32/succ.png',function(){
					loadNotSignFlow();
					findFlowConn();
				});
			}else{
				$.dialog.tips('签收失败',1,'32X32/fail.png');
			}
		}
	});
}
//-->
</script>
<div id="flowmanagemenu">
	<div id="flowmanageprepend">
	<s:if test="flowInfos.size()>0">
		<div class="pan">
			<ul class="myDiscuss list5">
				<s:iterator value="flowInfos">
				<s:set var="flowUser" value="@com.tfs.irp.util.LoginUtil@findUserById(CRUSERID)" />
				<li>
					<s:date name="CRTIME" format="yyyy-MM-dd HH:mm" />
					<div class="userIcon">
						<dl>
							<dt>
								<a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="#flowUser.userid" /></s:url>" target="_blank" title="<s:property value="#flowUser.showName" />">
									<img src="<s:property value="#flowUser.defaultUserPic" />" alt="用户头像" width="60" height="60" /> 
								</a>
							</dt>
						</dl>
					</div>
					<section>
					<aside></aside>
					<div>
						<a href="javascript:void(0)" onclick="processFlow(<s:property value="FLOWOBJID" />,<s:property value="OBJID" />)"><s:property value="DOCTITLE" /></a>
						&nbsp;<font color="#999999"><s:if test="STATUS==1">[未签收]</s:if><s:elseif test="STATUS==2">[已签收]</s:elseif></font>
						<p style="font-size:13px;">
							审核信息：<s:property value="POSTDESC" />
						</p>
						<p>
							<a href="javascript:void(0)" onclick="showFlowPath(<s:property value="OBJID" />)">流转轨迹</a>&nbsp;|
							<s:if test="STATUS==1"><a href="javascript:void(0)" onclick="signFlow(<s:property value="FLOWOBJID" />)">签收</a>&nbsp;|</s:if>
							<a href="javascript:void(0)" onclick="processFlow(<s:property value="FLOWOBJID" />,<s:property value="OBJID" />)">处理</a>
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
	<div class="emptyDiv">没有待办信息</div>
</s:else>
</div>