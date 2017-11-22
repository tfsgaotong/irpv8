<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<script type="text/javascript">
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
														  				$("#questionDiv"+questionid).hide();
																	});
													  } else {
													  $.dialog.tips('删除失败', 1,'32X32/fail.png');
													  }
											},
										});
									});
	}
	//判断删除事件 鼠标移出
	function questionDeleteJudgeOut(_questionid){
		var deletequestionid ="#delfontnoquestion"+_questionid;
		$(deletequestionid).css({visibility:"hidden"});
	}
	//判断删除事件 鼠标移上
	function questionDeleteJudge(_questionid){
		var deletequestionid ="#delfontnoquestion"+_questionid;
		$(deletequestionid).css({visibility:"visible"});
	}
</script>
<s:if test="listQuestion.size()>0">
<s:iterator value="listQuestion">
<div  onmousemove="questionDeleteJudge(<s:property value="questionid"/>)" onmouseout="questionDeleteJudgeOut(<s:property value="questionid"/>)">
	<table width="100%" bordercolor="#000000"
		style="border:0;border-bottom:1px dashed #eee; padding-bottom:10px; padding-top:10px;"
		onmousemove="javascript:this.style.background = '#f6f6f6'"
		onmouseout="javascript:this.style.background = '#fff'"
		id="questionDiv<s:property value="questionid"/>">
		<tbody>
			<tr>
				<!-- 头像 -->
				<td rowspan="2" align="center" width="80" cellpadding="10px"><a target="_bank"
					href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value="cruserid"/>">
					
					<s:if test="getIrpUserByUserid(cruserid).userpic!=null">
					 <img style="width:55px" src="<%=rootPath %>file/readfile.action?fileName=<s:property value='getIrpUserByUserid(cruserid).userpic' />" alt="用户图片" />
					</s:if>
					<s:else>
					    	 <s:if test="getIrpUserByUserid(cruserid).sex==2">
				                  <img src="<%=rootPath%>client/images/female.jpg" alt="用户图片" width="55px" />
				             </s:if>
				             <s:else>
				                  <img src="<%=rootPath%>client/images/male.jpg" alt="用户图片" width="55px" />
				             </s:else>
					</s:else>
						
				</a>
				</td>
				<!-- 标题 -->
				<td width="550" align="left">
				<s:if test="title.trim().length()>0">
				<a title="<s:property value="title"/>"
					href="<%=rootPath%>question/questionDetail.action?questionid=<s:property value="questionid"/>"
					class="linkc12"> 
						<s:if test="title!=null && title.length()>questionShowNum">
								<s:property value="title.substring(0,questionShowNum)+'......'" />
						</s:if>
						<s:else>
							<s:property value="title" />
						</s:else>
					</a>
				
				</s:if>
				<s:else>
				<a title="<s:property value="htmlcontent"/>"
					href="<%=rootPath%>question/questionDetail.action?questionid=<s:property value="questionid"/>"
					class="linkc12"> 
						<s:if test="htmlcontent!=null && title.length()>questionShowNum">
								<s:property value="title.substring(0,questionShowNum)+'......'" />
						</s:if>
						<s:else>
							<s:property value="htmlcontent" />
						</s:else>
					</a>
				
				
				</s:else>
				
					
				</td>
			</tr>
			<tr>
				<td align="left"><font class="linkc12"> <s:if
							test="status==0">未解决</s:if> <s:else>已解决</s:else> </font>&nbsp;.&nbsp; <s:property
						value="getShowPageViewNameByUserName(cruser)" /> &nbsp;.&nbsp; <s:date name="crtime"
						format="yyyy-MM-dd HH:mm" /></td>
				<s:if test="loginUsername==cruser">
					<td width="48"><a href="javascript:void(0)"
						onclick="delQuestion(<s:property value="questionid"/>);" id="delfontnoquestion<s:property value="questionid"/>">删除</a></td>
				</s:if>
			</tr>
		</tbody>
	</table>
	</div>
</s:iterator>
<ul>
	<s:property value="pageHTML" escapeHtml="false" />
</ul>
</s:if>
<s:else>
   <div style="width:955px;line-height:300px;text-align:center;color:#ccc;font-size:20px;">暂无问题</div>
</s:else>