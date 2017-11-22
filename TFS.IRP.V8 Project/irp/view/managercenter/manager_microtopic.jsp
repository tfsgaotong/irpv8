<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<section class="setUp">
	<div class="tabTitlenoline">
		<div class="current" _href="<s:url namespace="/microblog" action="microtopic_review" />" _data="">
			<a href="javascript:void(0)" onclick="switchMicrotopic(this)">待审核</a>
			<p class="labBg"></p>
		</div>
		<div _href="<s:url namespace="/microblog" action="microtopic_approved" />" _data="">
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
	var jqObj = $('.tabTitlenoline').find('.current');
	var sHref = jqObj.attr('_href');
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