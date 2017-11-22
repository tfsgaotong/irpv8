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
		
		<ul class="clearfix">
			<s:iterator value="examlist" >
				<li>
					<div>
						<div class="examimg">
							<img src="<%=rootPath %>/client/images/testpaper.jpg" alt="">
						</div>
						<div class="examinfo">
							<p style="margin-top:0"><span>考试名称: </span><span><s:property value="examname" /></span></p>
							<p><span>开始时间：</span><span><s:date name="begintime" format="yyyy-MM-dd HH:mm" /></span></p>
							<p><span>结束时间：</span><span><s:date name="endtime" format="yyyy-MM-dd HH:mm" /></span></p>
							<span>考试时长:</span><span><s:property value="answertime" /></span><span>分钟</span>
							<div id="joinexam" onclick="visitExam(<s:property value="examcontent" />,<s:property value="examid" />)" style="cursor:pointer;margin-top:10px;background-color:#2061b0;color:#fff;width:70px;text-align:center;border-radius:5px;">
								参加考试
							</div>
						</div>
					</div>
					<div style="clear: both"></div>
				</li>
			</s:iterator>
			<div>
			<div class="left">
				<div class="fyh">
					<ul style="width:880px">
					<s:property value="pagemenuhtmlexam" escapeHtml="false" />
					</ul>
				</div>
			</div>	
		</div>
		</ul>
</body>
</html>