<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<section class="setUp" style="width:100%">
	<div class="tabTitlenoline" style="width:100%;margin-left:0">
		<div class="current" _href="<s:url namespace="/phone" action="allleave_app" ><s:param name="type" value="10"/>
		<s:param name="status" value="20"/></s:url>">
			<a href="javascript:void(0)" onclick="switchFlow(this)">待办</a>
			<p class="labBg"></p>
		</div>
 		<div _href="<s:url namespace="/phone" action="allleave_app" ><s:param name="type" value="10"/>
		<s:param name="status" value="40"/></s:url>">
			<a href="javascript:void(0)" onclick="switchFlow(this)">已办</a>
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
	var jqObj = $('.tabTitlenoline').find('.current');
	var sHref = jqObj.attr('_href');
	var tokens = $("#tokens").val();
	sHref+="&token="+tokens;
	if(sHref){
		var flowHtmlConn = $.ajax({
			type : 'get',
			url : sHref,
			cache : false,
			async : false,
			data : {
				pageSize : 10,
				pageNum : _pageNum
			}
		}).responseText;
		$('#flowConn').html(flowHtmlConn);
	}
}

</script>