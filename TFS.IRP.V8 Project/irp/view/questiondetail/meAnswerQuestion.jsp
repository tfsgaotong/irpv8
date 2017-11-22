<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.tfs.irp.question.entity.IrpQuestion"%>
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
																		$("#meAnswerDiv"+questionid).hide();
																	});
													  } else {
													  $.dialog.tips('删除失败', 1,'32X32/fail.png');
													  }
											},
										});
									});
	}
	
</script>
<!-- 分页参数结束 -->

            <div class="labels">我回答的</div>
<s:iterator value="listQuestion">
            <div class="labs">
            	<dl>
                	<dt><a href="#" target="_blank"><img src="<%=rootPath %>view/images/user2.jpg"/></a></dt>
                    <dd class="text">
                    <s:if test="title.trim().length()>0">
	                    <div class="pan title"><a title="<s:property value="title" />" class="linkc12" target="_blank" href="<%=rootPath%>question/questionDetail.action?questionid=<s:property value="questionid"/>"> 
										<s:if test="title!=null && title.length()>30">
											<s:property value="title.substring(0,30)+'......'" />
										</s:if>
										<s:else>
											<s:property value="title" />
										</s:else>
									</a></div>
	                </s:if>
				        <div class="area20"></div>
				               <div class="pan" style="padding-left:10px;">
				            <ul class="questionList">
				                <s:if test="status==0"><li><aside class="await">待</aside><a href="<%=rootPath%>question/questionDetail.action?questionid=<s:property value='questionid'/>" target="_blank">求达人帮忙看看，这道题该怎么做，要有过程哦，谢谢</a>&nbsp;&nbsp;<s:property value="getShowPageViewNameByUserName(cruser)" /> &nbsp;<s:date
										name="crtime" format="yyyy-MM-dd HH:mm" /><a href="javascript:void(0)" style="margin-left:15px;"  onclick="delQuestion(<s:property value="questionid"/>);">删除</a></li></s:if>
				                <s:else><li><aside class="solve">解</aside><a href="#" target="_blank">求达人帮忙看看，这道题该怎么做，要有过程哦，谢谢</a></li></s:else>
				            </ul>
				        </div>
                    </dd>
                    <dd class="clear"></dd>
                </dl>
            </div>
</s:iterator>
