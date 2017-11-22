<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>   
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
</style>
<body>
<div id="newSubjectList" class="more_tit"  ><span> 引用到专题 : </span><span><a href="javascript:void(0);"  onclick="deleteSubDiv()">重置</a></span></div>
		<div id="documentsubdiv">
			<s:if test="irpChannels!=null">
				<s:iterator var="irpChan" value="irpChannels" status="status">
					<s:if test="#status.count<7">
						<input
						<s:if test="selectstatus==1">checked="checked"</s:if>
						<s:elseif test="doorchannelid==channelid">checked="checked"</s:elseif>
						 id="check<s:property value='channelid' />" value="<s:property value='channelid' />"  style="margin-right:10px;margin-top:4px;line-height: 40px;float: left;" name="subjectselect" type="checkbox" />&nbsp;<div style="float:left;width:100px; white-space:nowrap;overflow:hidden;text-overflow:ellipsis;"><label for="check<s:property value='channelid' />" title="<s:property value='chnlname'/>" style="cursor:pointer;"><s:property value="chnlname"/></label></div>
					</s:if>
				</s:iterator> 
				<s:if test="irpChannels.size()>6"><a href="javascript:void(0)" class="linkbh14" style="margin-left:10px;font-size:11px;" onclick="moresubject()">更多专题</a></s:if>
				<a href="javascript:void(0)" style="margin-left:10px;font-size:11px;" class="linkbh14" onclick="createknowSubject()">新建专题</a>
				<div id="hidesubdiv" style="display:none;width: 100%;">
					<s:iterator value="irpChannels" status="status">
					<s:if test="#status.count>6">
						<input
						<s:if test="selectstatus==1">checked="checked"</s:if>
						<s:elseif test="doorchannelid==channelid">checked="checked"</s:elseif>
						 id="check<s:property value='channelid' />" value="<s:property value='channelid' />"  style="margin-right:10px;margin-top:4px;line-height: 40px;float: left;" name="subjectselect" type="checkbox" />&nbsp;<div style="float:left;width:100px; white-space:nowrap;overflow:hidden;text-overflow:ellipsis;"><label for="check<s:property value='channelid' />" title="<s:property value='chnlname'/>" style="cursor:pointer;"><s:property value="chnlname"/></label></div>
					</s:if>
				</s:iterator> 
				</div>
			</s:if>
			<s:else><a style="margin-left:5px;color:green" href="javascript:void(0)" onclick="createknowSubject()">还没有专题,马上创建</a></s:else>
		</div>
</body>
</html>