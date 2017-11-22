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
<script>
/**
* 前台已参加考试分页
*/
function pageAlreadyeMenu(_pages){
	$.ajax({
		type:"post",
		url:"<%=rootPath%>exam/alreadypageexam.action?exammenunum="+_pages,
		data:{
		},
		async:false,
		cache:false,
		success:function(data){
			$('.examlist').html(data);
		}
	});
}
</script>
<body>
		
		<ul>
				<s:iterator value="examrecordlist" >
					<li>
						<div>
							<div class="leftexamimg" style="width:90px;height:90px;background:url('<%=rootPath %>/client/images/testpaper.jpg') no-repeat;background-size:100% 100%;font-size:15px;padding-left:0;">
								 <p style="height:40px;line-height:70px;text-align:center;"><strong><s:property value="examgrade" />分</strong></p>
								 <p style="font-size:13px;height:50px;line-height:50px;text-align:center;"><s:if test="examstatus==10"><strong>通过</strong></s:if><s:elseif test="examstatus==20"><strong>未通过</strong></s:elseif></p>
							</div>	
							<div class="leftexaminfo">
								<p style="margin-top:0"><span><span>考试名称：</span><s:property value="getIrpExamByExamId(examid).examname" /></span><a style="cursor:pointer;color:#225491;margin-left: 5px" onclick="searchDetail(<s:property value="examid" />,<s:property value="getIrpExamByExamId(examid).examcontent" />,<s:property value="recordid" />)" >[查看详情]</a></p>
								<p><span>开始时间：</span><span><s:date name="getIrpExamByExamId(examid).begintime" format="yyyy-MM-dd HH:mm" /></span></p>
								<span>结束时间：</span><span><s:date name="getIrpExamByExamId(examid).endtime" format="yyyy-MM-dd HH:mm" /></span>
								<p><span>考试分数：</span><span><s:property value="examgrade" /></span><span>分</span></p>
								<span>考试结果：</span><span><s:if test="examstatus==10">通过</s:if><s:elseif test="examstatus==20">未通过</s:elseif></span>
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