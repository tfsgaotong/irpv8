<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<section class="setUp" style="width:100%;">
	<div class="tabTitlenoline" style="margin-left:0px;">
		<div class="current" _href="<s:url namespace="/phone" action="calender_app" />">
			<a href="javascript:void(0)" onclick="switchFlow(this)">我的日程</a>
			<p class="labBg"></p>
		</div>
<!-- 		<div _href="">
			<a href="javascript:void(0)" onclick="switchFlow(this)">已办</a>
			<p class="labBg"></p>
		</div> -->
		<div  _href="<s:url namespace="/phone" action="apply_app" />">
			<a href="javascript:void(0)" onclick="switchFlow(this)">我的会议室预约</a>
			<p class="labBg"></p>
		</div>
	</div>
	<div class="area20"></div>
	<div id="flowConn"></div>
</section>
<script type="text/javascript">
$(function(){
	findFlowConn();
});
function switchFlow(_obj){
	var jqObj = $(_obj);
	$('.tabTitlenoline').find('.current').removeClass('current');
	jqObj.parent().addClass('current');
	findFlowConn();
}
//获得审核内容
function findFlowConn(_pageNum){
	var tokens = $("#tokens").val();
	var jqObj = $('.tabTitlenoline').find('.current');
	var sHref = jqObj.attr('_href')+"?token="+tokens;
	if(sHref){
		var flowHtmlConn = $.ajax({
			type : 'get',
			url : sHref,
			cache : false,
			async : false,
		}).responseText;
		$('#flowConn').html(flowHtmlConn);
	}
}
</script>