<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<section class="setUp">
	<div class="tabTitlenoline">
		<div id="covert" class="current" _href="<s:url namespace="/question" action="allquestion" />">
			<a href="javascript:void(0)" onclick="switchFlow(this)">问答记录</a>
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
	if(sHref){
		var flowHtmlConn = $.ajax({
			type : 'get',
			url : sHref,
			cache : false,
			async : false,
		}).responseText;
		$('#covertrecord').html(flowHtmlConn);
	}
}

</script>