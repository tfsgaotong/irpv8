<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<html>
<head>
</head>

<body>
		<div class="title">
			参加过的考试	
		</div>
		<ul>
				<s:iterator value="examrecordlist" >
					<li>
						<div>
							<div class="leftexamimg" style="background:url('<%=rootPath %>/client/images/paper.jpg') no-repeat;background-size:100% 100%;font-size:15px;">
								 <p style="height:30px;line-height:60px;text-align:center;"><s:property value="examgrade" /></p>
								 <p style="font-size:13px;height:40px;line-height:40px;text-align:center;"><s:if test="examstatus==10">通过</s:if><s:elseif test="examstatus==20">未通过</s:elseif></p>
							</div>	
							<div class="leftexaminfo">
								<p style="margin-top:0"><span><span>考试名称: </span><s:property value="getIrpExamByExamId(examid).examname" /></span><a style="cursor:pointer;color:#225491;margin-left: 5px" onclick="searchDetail(<s:property value="examid" />,<s:property value="getIrpExamByExamId(examid).examcontent" />,<s:property value="recordid" />)" >[查看详情]</a></p>
								<p><span>开始时间：</span><span><s:date name="getIrpExamByExamId(examid).begintime" format="yyyy-MM-dd HH:mm" /></span></p>
								<span>结束时间：</span><span><s:date name="getIrpExamByExamId(examid).endtime" format="yyyy-MM-dd HH:mm" /></span>
								<p><span>考试分数:</span><span><s:property value="examgrade" /></span><span>分</span></p>
								<span>考试结果:</span><span><s:if test="examstatus==10">通过</s:if><s:elseif test="examstatus==20">未通过</s:elseif></span>
							</div>
						</div>
						<div style="clear: both"></div>
					</li>
				</s:iterator>
				
			</ul>
</body>
</html>