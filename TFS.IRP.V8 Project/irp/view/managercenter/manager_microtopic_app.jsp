<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<section class="setUp" style="width:100%">
	<div class="tabTitlenoline" style="margin-left:0px;">
		<div class="current" _href="<s:url namespace="/phone" action="microtopic_review_app" />" _data="">
			<a href="javascript:void(0)" onclick="switchMicrotopic(this)">待审核</a>
			<p class="labBg"></p>
		</div>
		<div _href="<s:url namespace="/phone" action="microtopic_approved_app" />" _data="">
			<a href="javascript:void(0)" onclick="switchMicrotopic(this)">已审核</a>
			<p class="labBg"></p>
		</div>
	</div>
	<div class="area20"></div>
	<div id="microtopicConn"></div>
</section>
<script type="text/javascript">
$(function(){
	findMicrotopicConn();
});
function switchMicrotopic(_obj){
	var jqObj = $(_obj);
	$('.tabTitlenoline').find('.current').removeClass('current');
	jqObj.parent().addClass('current');
	findMicrotopicConn();
}
//获得管理内容
function findMicrotopicConn(_pageNum){
	var tokens = $("#tokens").val();
	var jqObj = $('.tabTitlenoline').find('.current');
	var sHref = jqObj.attr('_href')+"?token="+tokens;
	var sData = jqObj.attr('_data');
	if(_pageNum){
		sData += "&pageNum="+_pageNum;
	}
	if(sHref){
		var sHtmlConn = $.ajax({
			type : 'post',
			url : sHref,
			cache : false,
			async : false,
			data : sData
		}).responseText;
		$('#microtopicConn').html(sHtmlConn);
	}
}
</script>