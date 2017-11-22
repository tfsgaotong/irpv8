<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
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
				page(1);
				$.dialog.tips('签收成功',1,'32X32/succ.png');
			}else{
				$.dialog.tips('签收失败',1,'32X32/fail.png');
			}
		}
	});
}
//-->
</script> 
<s:if test="flowInfos==null || flowInfos.size()==0">
暂无待办信息。
</s:if>
<s:iterator value="flowInfos">
<dl>
	<dt>
		<a href="javascript:void(0)" onclick="tothispersonindex()"><img
			src="<%=rootPath %>client/images/zanshi-03.gif">
		</a>
	</dt>
	<dd>
		<a href="javascript:void(0)" onclick="processFlow(<s:property value="FLOWOBJID" />,<s:property value="OBJID" />)" class="linkb14"><s:property value="DOCTITLE" /></a>&nbsp;&nbsp;<font color="#999999" size="2"><s:if test="STATUS==1">[未签收]</s:if><s:elseif test="STATUS==2">[已签收]</s:elseif></font>
		<p style="background-color: #F6F6F6;">
			审核信息：<s:property value="POSTDESC" />
		</p>
		<span style="padding-bottom:30px;">
			<p>
				<s:date name="CRTIME" format="yyyy-MM-dd HH:mm:ss" />&nbsp;&nbsp;
				<a href="javascript:void(0)" class="linkc12" onclick="showFlowPath(<s:property value="OBJID" />)">流转轨迹</a>&nbsp;&nbsp;
				<s:if test="STATUS==1"><a href="javascript:void(0)" class="linkc12" onclick="signFlow(<s:property value="FLOWOBJID" />)">签收</a>&nbsp;&nbsp;</s:if>
				<a href="javascript:void(0)" class="linkc12" onclick="processFlow(<s:property value="FLOWOBJID" />,<s:property value="OBJID" />)">处理</a>&nbsp;&nbsp;
			</p>
		</span>
	</dd>
</dl>
</s:iterator>
<ul>
	<s:property value="pageHTML" escapeHtml="false" />
</ul> 