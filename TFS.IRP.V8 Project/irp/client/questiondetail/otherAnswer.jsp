<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.tfs.irp.util.LoginUtil,com.tfs.irp.user.entity.IrpUser"%>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
IrpUser loginUser = LoginUtil.getLoginUser();
%>
<script type="text/javascript">
//分页
function page(pageNum){
	var sUrl = '<%=rootPath%>phone/loadOtherAnswers.action?token='+$('#tokens').val();
	$('#pageNum').val(pageNum);
	var queryString = $('#pageForm').serialize();
	var sHtmlConn = $.ajax({ 
 		type:'post', 
 		url: sUrl,
 		data: queryString,
 		dataType: "json",
		async: false,
	    cache: false  
 	}).responseText;
	$('#answerDiv').html(sHtmlConn);
}
</script>
<!-- 其他答案 -->
<div id="append">
	<b>其他<font><s:property value="num" /></font>条答案</b>:
</div>
<s:iterator value="listQuestion">
<div id="question<s:property value="questionid"/>ofanswer">
	<div id="otheranswer">
		<div id="otheranswer<s:property value="questionid"/>" style="font-size: 14px;">
			<P>
				<s:property value="htmlcontent" escapeHtml="false" />
			</P>

			<s:if test="getIrpAttached(questionid).size()>0">
			<p>
				<div id="title1">
			<s:if test="getIrpAttached(questionid).size()>0">
			<s:iterator value="getIrpAttached(questionid)" status="otherattstatus">
			
				<img id="<s:property value="#otherattstatus.index" />" name="<s:property value="getAttNameStrForList(getIrpAttached(questionid))" />" onclick="checkMaxPic(this.src,this.name,this.id)" src="<%=rootPath%>file/readfile.action?fileName=<s:property value="updatePagePic(attfile)" />" style="width: 140px;height: 150px;cursor:url(<%=rootPath%>client/images/maxpic_03.cur),auto;" />
				&nbsp;
			</s:iterator><br/>
			</s:if>
			<s:if test="getIrpAttachedOther(questionid).size()>0">
			<s:iterator value="getIrpAttachedOther(questionid)">
			<a href="<%=rootPath%>file/readfile.action?fileName=<s:property value="attfile" />&fileTrueName=<s:property value="attdesc" />" title="<s:property value="attlinkalt" />" style="text-decoration: underline;" target="_blank"><s:property value="attdesc" /></a><br/>
			</s:iterator>
			</s:if>
	            </div>
	            </p>
			</s:if>
			
			<div class="action_mfed clearfix"
				style="text-align:right; border-top:1px solid #eee; padding-top:0;">
  	     <s:property value="getShowPageViewNameByUserId(cruserid)" />
				&nbsp;回答于
				<s:date name="crtime" format="yyyy-MM-dd HH:mm" />
				&nbsp;<a  class="linkc12" href="javascript:void(0)" onclick="deleteAnswer(<s:property value="questionid"/>)" >删除</a>
			</div>
			<div style="text-align: left;" id="consent<s:property value="questionid" />"><s:property value="getUsernameByQuestionid(questionid)" escapeHtml="false" /></div>
		</div>
		<div>
			<s:if
				test="irpQuestion.status==null || irpQuestion.status==0 && irpQuestion.cruser==loginUsername">
				<div  id="bestAnswerBtn">
					<ul>
						<li style="text-align: left;padding: 0 0 2% 0;" id="id"><a hideFocus="true" title=""
							href="javascript:void(0);" id="setBestAnswer"
							onclick="setBestAnswer(<s:property value="questionid"/>)">设为最佳答案</a>
						</li>
						<li id="replyclist<s:property value="questionid"/>" ></li>
							<li id="compllethecombox<s:property value="questionid"/>" style="text-align: left;padding: 2% 0 2% 0;display: none;">
								<div><textarea cols="70%;" id="ctcbtext<s:property value="questionid"/>" placeholder="请输入追问内容..."></textarea></div>
								<div style="text-align: right;padding:1% 0 0 0;">
									<a hideFocus="true" title=""  href="javascript:void(0);" onclick="replyCommentV(<s:property value="questionid"/>,<s:property value="cruserid"/>,'<s:property value="getShowPageViewNameByUserId(cruserid)"/>')" style="margin: 0 12% 1% 0;">
										提交
									</a>   
								</div>
							</li>
					</ul>
				</div>
			</s:if>
		</div>
	</div>
	</div>
</s:iterator>
<div>
	<div align="right" class="clientpage" style="padding-top:0;">
		<ul style="width:850px;">
			<s:property value="pageHTML" escapeHtml="false" />
		</ul>
	</div>
</div>

