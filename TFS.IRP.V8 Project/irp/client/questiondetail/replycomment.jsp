<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<s:iterator value="listQuestion" status="listQuestionstatus">
	<div style="border:thin solid #ADADAD;border-style: none none solid none;width: 90%;text-align: left;padding:1% 1% 1% 1%; " onmouseover="overRComm(<s:property value="questionid" />,1)" onmouseout="overRComm(<s:property value="questionid" />,2)">
		<div><label class="linkb14" onclick="linkUser(<s:property value="cruserid" />)" style="cursor:pointer;" ><s:property value="getShowPageViewNameByUserId(cruserid)" /></label>
			 <label style="float: right;"><s:property value="rootid" />楼</label>
		</div>
		<div><s:property value="textcontent" /></div>
		<div><s:date name="crtime"  format="yyyy-MM-dd HH:mm" /><label style="margin: 0 0 0 5%;color:#9D9D9D;visibility: hidden;cursor: pointer;" id="replytwo<s:property value="questionid" />" onclick="replyTwoReply(<s:property value="questionid" />,'<s:property value="getShowPageViewNameByUserId(cruserid)" />')">回复</label></div>
			<s:if test="replyquestionid!=null">

				<div style=" border:thin solid #9D9D9D;">
					<div><label style="">回复:</label><label class="linkb12"><s:property value="getShowPageViewNameByUserId(getIrpQuestion(replyquestionid).cruserid)"/></label><label style="float: right;">引用<s:property value="getIrpQuestion(replyquestionid).rootid" />楼</label></div>
					<div><s:property value="getIrpQuestion(replyquestionid).textcontent" /></div>
					<div><s:date name="getIrpQuestion(replyquestionid).crtime" format="yyyy-MM-dd HH:mm" /></div>
				</div>
				
			</s:if>
		<div id="vhrediv<s:property value="questionid" />" style="padding:2% 1% 2% 1%;display: none;">
			<textarea cols="70%;" id="vhredivtext<s:property value="questionid"/>" placeholder="回复内容..."></textarea>
			<div style="text-align: right;padding:1% 0 0 0;">
				<a hideFocus="true" title=""  href="javascript:void(0);" onclick="replyCPT(<s:property value="questionid" />,<s:property value="parentid" />)" style="margin: 0 0 1% 0;">
					提交
				</a>   
			</div>
		</div>
	</div>
</s:iterator>
<s:if test="vnum>pageNum">
<div id="<s:property value="pageNum" />isnextboolc<s:property value="questionid" />" style="border:thin solid #ADADAD;border-style: none none solid none;width: 90%;text-align: center;padding:1% 1% 1% 1%;cursor: pointer; " onclick="cVAllComm(<s:property value="questionid" />,<s:property value="pageNum" />)">
	显示下一页
</div>
</s:if>