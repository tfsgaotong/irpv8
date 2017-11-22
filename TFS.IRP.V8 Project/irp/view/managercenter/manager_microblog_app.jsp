<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>

<section class="setUp" style="width:100%;">
	<div style="width:100%;margin:0 auto" class="tabTitlenoline">
		<div  class="current" _href="<s:url namespace="/phone" action="microallmanagera_app" />" _data="">
			<a style="position:relative;float:left;width:100%;" href="javascript:void(0)" onclick="switchMicroblog(this)">微知信息</a>
			<p class="labBg"></p>
		</div>
		<div _href="<s:url namespace="/microblog" action="microblog_report_app" />" _data="">
			<a style="position:relative;float:left;width:100%;" href="javascript:void(0)" onclick="switchMicroblog(this)">举报</a>
			<p class="labBg"></p>
		</div>
 		<div _href="<s:url namespace="/microblog" action="microblog_illegal_app" />" _data="">
			<a style="position:relative;float:left;width:100%;" href="javascript:void(0)" onclick="switchMicroblog(this)">非法微知</a>
			<p class="labBg"></p>
		</div>
		<div _href="<s:url namespace="/microblog" action="microdeletemanager_app" />" _data="">
			<a style="position:relative;float:left;width:100%;" href="javascript:void(0)" onclick="switchMicroblog(this)">已删除</a>
			<p class="labBg"></p>
		</div>
	</div>
	<div class="area20"></div>
	<div id="microblogConn"></div>
</section>
<script type="text/javascript">
$(function(){
	findMicroblogConn();
});
function switchMicroblog(_obj){
	var jqObj = $(_obj);
	$('.tabTitlenoline').find('.current').removeClass('current');
	jqObj.parent().addClass('current');
	findMicroblogConn();
}
//获得管理内容
function findMicroblogConn(_pageNum){
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
		$('#microblogConn').html(sHtmlConn);
	}
}
</script>