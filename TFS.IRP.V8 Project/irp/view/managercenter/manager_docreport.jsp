<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<section class="setUp">
	<div class="tabTitlenoline">
		<div class="current" _href="<s:url namespace="/site" action="report_doc_list" />" _data="pageSize=10&sitetype=1">
			<a href="javascript:void(0)" onclick="switchDocReport(this)">举报</a>
			<p class="labBg"></p>
		</div>
 		<div _href="<s:url namespace="/site" action="illegal_doc_list" />" _data="pageSize=10&sitetype=1">
			<a href="javascript:void(0)" onclick="switchDocReport(this)">非法知识</a>
			<p class="labBg"></p>
		</div>
	</div>
	<div class="area20"></div>
	<div id="docReportConn"></div>
</section>
<script type="text/javascript">
$(function(){
	findDocReportConn();
});
function switchDocReport(_obj){
	var jqObj = $(_obj);
	$('.tabTitlenoline').find('.current').removeClass('current');
	jqObj.parent().addClass('current');
	findDocReportConn();
}
//获得管理内容
function findDocReportConn(_pageNum){
	var jqObj = $('.tabTitlenoline').find('.current');
	var sHref = jqObj.attr('_href');
	var sData = jqObj.attr('_data');
	if(_pageNum){
		sData += "&pageNum="+_pageNum;
	}
	if(sHref){
		var docReportHtmlConn = $.ajax({
			type : 'post',
			url : sHref,
			cache : false,
			async : false,
			data : sData
		}).responseText;
		$('#docReportConn').html(docReportHtmlConn);
	}
}
</script>