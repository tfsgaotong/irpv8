<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.tfs.irp.util.LoginUtil"%>
<%
    String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- 已审核、未审核和非法的词条 -->
<script type="text/javascript">
$(function(){
	getWordList(1);
});

/**
 * 获取未审核词条列表数据
 */
function getWordList(pageNum){
	var jqObj = $('.tabTitlenoline').find('.current');
    var sHref = jqObj.attr('_href');
    if(sHref){
		$.ajax({
			type:'get',
			url: sHref,
			data:{
				pagenum:pageNum
			},
			cache:false,
			success:function(content){
				$('#termcontent').html(content);
			}
		});
    }
}

/**
 * 分页
 */
function pageterm(pageNum){
	$('#termcontent').html("");
	getWordList(pageNum);
}

/**
 * 跳转到管理员审核页面
 */
function linkVerContent(termid){
    window.open('<%=rootPath%>term/linkversionmgr.action?termid='+ termid);
}

function switchTermType(_obj){
    var jqObj = $(_obj);
    $('.tabTitlenoline').find('.current').removeClass('current');
    jqObj.parent().addClass('current');
    getWordList(1);
}
</script>
<body style="background: url()">
	<section class="setUp">
		<div style="width:100%;margin:0 auto" class="tabTitlenoline">
			<div class="current" _href="<s:url namespace="/term" action="getAllCheckTerm" />">
				<a style="position:relative;float:left;width:100%;" href="javascript:void(0)" onclick="switchTermType(this)">已审核词条</a>
				<p class="labBg"></p>
			</div>
			<div _href="<s:url namespace="/term" action="getAllNoCheckTerm" />">
				<a style="position:relative;float:left;width:100%;" href="javascript:void(0)" onclick="switchTermType(this)">待审核词条</a>
				<p class="labBg"></p>
			</div>
			<div _href="<s:url namespace="/term" action="getAllIllegalTerm" />">
				<a style="position:relative;float:left;width:100%;" href="javascript:void(0)" onclick="switchTermType(this)">非法词条</a>
				<p class="labBg"></p>
			</div>
		</div>
		<input type="hidden" name="categoryId" id="categoryId" value="<s:property value="categoryId"/>" />
		<div class="main" style="display:inline-block; width: 930px;margin:20px 0 0 20px; background: url();font-size: 0;">
			<!-- 词条列表 -->
			<div id="termcontent"></div>
		</div>
	</section>
</body>
