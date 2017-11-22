<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>

<script language="JavaScript" type="text/javascript">
	//删除问题
	function delQuestion(questionid) {
		$.dialog.confirm('你确定要删除吗',function() {
										$.ajax({
											type : "POST",
											data : {
												questionid : questionid
											},
											url : '<%=rootPath%>question/delQuestion.action',
											dataType : "json",
											success : function(nMsg) {
													  if (nMsg = 1) {
													  $.dialog.tips('删除成功',1,'32X32/succ.png',
																	 function() {
																		$("#meAskQuestion"+questionid).hide();
																	});
													  } else {
													  $.dialog.tips('删除失败', 1,'32X32/fail.png');
													  }
											},
										});
									});
	}
	
</script>
<div class="bg01">
	<div class="main">
		<!--左侧内容-->
		<div class="left">
			<div class="fyh">
				<!-- 问题主题开始 -->
				<s:iterator value="listQuestion">
					<table width="100%" bordercolor="#000000"
						style="border:0;border-bottom:1px dashed #eee; padding-bottom:10px; padding-top:10px;"
						onmousemove="javascript:this.style.background = '#f6f6f6'"
						onmouseout="javascript:this.style.background = '#fff'"
						id="meAskQuestion<s:property value="questionid"/>">
						<tbody>
							<tr>
								<!-- 头像 -->
								<td rowspan="2" align="center" width="80" cellpadding="10px">
								<s:if test="getIrpUserByUserid(cruserid).userpic!=null">
								  <img src="<%=rootPath %>file/readfile.action?fileName=<s:property value="getIrpUserByUserid(cruserid).userpic" />" width="55px" alt="用户图片" />
								</s:if>
								<s:else>
								 <s:if test="getIrpUserByUserid(cruserid).sex==2">
								   <img src="<%=rootPath%>client/images/female.jpg" alt="用户图片" width="55px" />
								 </s:if>
								 <s:else>
								   <img src="<%=rootPath%>client/images/male.jpg" alt="用户图片" width="55px" />
								 </s:else>
								</s:else>
								</td>
								<!-- 标题 -->
								<s:if test="title.trim().length()>0">
								<td width="550" align="left">
									<a title="<s:property value="title" />" class="linkc12" target="_blank" href="<%=rootPath%>question/questionDetail.action?questionid=<s:property value="questionid"/>"> 
										<s:if test="title!=null && title.length()>30">
											<s:property value="title.substring(0,30)+'......'" />
										</s:if>
										<s:else>
											<s:property value="title" />
										</s:else>
									</a>
								</td>
								</s:if>
								<s:else>
								<td width="550" align="left">
									<a title="<s:property value="htmlcontent" />" class="linkc12" target="_blank" href="<%=rootPath%>question/questionDetail.action?questionid=<s:property value="questionid"/>"> 
										<s:if test="htmlcontent!=null && htmlcontent.length()>30">
											<s:property value="htmlcontent.substring(0,30)+'......'" />
										</s:if>
										<s:else>
											<s:property value="htmlcontent" />
										</s:else>
									</a>
								</td>
								</s:else>
								
								
								
								
							</tr>
							<tr>
							<td align="left">
								<font class="linkc12">
									<s:if test="status==0">未解决</s:if>
									<s:else>已解决</s:else>
								</font>&nbsp;.&nbsp;
								<s:property value="getShowPageViewNameByUserName(cruser)" /> &nbsp;.&nbsp; <s:date
										name="crtime" format="yyyy-MM-dd HH:mm" />
							</td>
								<td width="48"><a href="javascript:void(0)"
									onclick="delQuestion(<s:property value="questionid"/>);">删除</a>
								</td>
							</tr>
						</tbody>
					</table>
				</s:iterator>
				<!-- 问题主题结束-->
				<!--分页开始 -->
				<ul>
					<s:property value="pageHTML" escapeHtml="false" />
				</ul>
				<!--分页结束 -->
			</div>
		</div>
		<!--左侧内容结束-->
	</div>
</div>