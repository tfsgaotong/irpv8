<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<section class="setUp">
	<div class="tabTitlenoline">
		<div class="current" _href="<s:url namespace="/tag" action="tag_type_list" />" _data="pageSize=10">
			<a href="javascript:void(0)" onclick="switchTag(this)">知识标签</a>
			<p class="labBg"></p>
		</div>
	</div>
	<div class="area20"></div>
	<div id="tagConn"></div>
</section>
<script type="text/javascript">
$(function(){
	findTagConn();
});
function switchTag(_obj){
	var jqObj = $(_obj);
	$('.tabTitlenoline').find('.current').removeClass('current');
	jqObj.parent().addClass('current');
	findTagConn();
}

//获得管理内容
function findTagConn(_pageNum){
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
		$('#tagConn').html(sHtmlConn);
	}
}
</script>