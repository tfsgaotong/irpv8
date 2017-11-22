<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<section class="setUp" style="width:100%;">
	<div class="tabTitlenoline" style="width:100%;margin-left:0px;">
		<div id="covert" class="current" _href="<s:url namespace="/phone" action="allgoods_app" />">
			<a href="javascript:void(0)" onclick="switchFlow(this)">我的兑换记录</a>
			<p class="labBg"></p>
		</div>
	</div>
	<div class="area20"></div>
	<div id="covertrecord"></div>
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
	var jqObj = $('.tabTitlenoline').find('.current');
	var sHref = jqObj.attr('_href');
	var tokens = $("#tokens").val();
	if(sHref){
		var flowHtmlConn = $.ajax({
			type : 'get',
			url : sHref+"?token="+tokens,
			cache : false,
			async : false,
		}).responseText;
		$('#covertrecord').html(flowHtmlConn);
	}
}

</script>