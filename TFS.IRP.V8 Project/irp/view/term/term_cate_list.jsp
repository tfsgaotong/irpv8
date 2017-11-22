<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.tfs.irp.util.LoginUtil"%>
<%
    String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- 词条分类列表页 -->
<html>
<head>
	<title>百科词条</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="shortcut icon" href="<%=rootPath%>favicon.ico" type="image/x-icon"/>
	<link rel="bookmark" href="<%=rootPath%>favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/normalize.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/oacss.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/wiki-home_2685284.css"/>
	<script src="<%=rootPath%>view/js/modernizr.custom.js"></script>
	<script src="<%=rootPath%>view/js/jquery-1.8.0.min.js"></script>
	<style type="text/css">
	.wtgy {
		height: 28px;
		line-height: 28px;
		border: 1px solid rgb(209, 209, 209);
		color: rgb(102, 102, 102);
		padding: 0px 5px;
		width: 300px;
		margin: 0 0 0 5px;
	}
	
	.btn_ccw {
		background: none repeat scroll 0 0 #63C7E6;
		color: #FFFFFF;
		display: block;
		width: 100px;
		float: left;
		line-height: 28px;
		font-size: 18px;
		padding: 0px 5px;
		font-style: normal;
		height: 30px;
		text-align: center;
	}

	body {
		behavior: url(hover.htc);
	}
	
	.searchSec .radios span {
		margin-top: 0px;
	}
	</style>
</head>
<script type="text/javascript">
$(function(){
	var qidval = $('#categoryId').val();
	if(qidval==''){
		qidval = null;
	}
	getWordList(1,qidval);
});

/**
 * 获取词条列表数据
 */
function getWordList(_pnum,_cid){
	var swords = $('#searchtermname').val();
	$.ajax({
		type:'post',
		url:'<%=rootPath%>term/termlistcate.action',
		data:{
			pagenum:_pnum,
			sword:swords,
			qclassifyid:_cid
		},
		cache:false,
		success:function(content){
			$('#termcontent').html(content);
		}
	});
}

/**
 * 分页
 */
function pageterm(_pnum){
	$('#termcontent').html("");
	var qidval = $('#categoryId').val();
	if(qidval==''){
		qidval = null;
	}
	getWordList(_pnum,qidval);
	
}

/**
 * 链接到版本选择
 */
function linkVerContent(_termid){
    //浏览
    $.get("<%=rootPath%>term/updatebrowsecount.action",{termid:_termid});
    window.open('<%=rootPath%>term/linkversion.action?termid='+ _termid);
}
</script>
<body style="background: url()">
	<jsp:include page="../../view/include/client_head.jsp" />
	<section class="mainBox">
	    <nav class="privateNav" style="background:url(../images/bg-04.gif) repeat-x">
			<dl>
				<dt class="leftBox">全部词条分类</dt>
				<dd class="clear"></dd>
			</dl>
		</nav>
    </section>
	<section class="mainBox" style="min-height: 55vh">
		<div style="display:inline-block;width: 245px;vertical-align: top;">
            <article class="location" style="display:inline-block; width:235px;height:40px">
                <s:iterator value="listCategory" status="index">
                    <s:if test="#index.first">
	                    <strong>
	                        <a href="<s:url namespace="/term" action="termCategory"><s:param name="categoryId" value="categoryid" /></s:url>">
	                            <s:property value="cdesc" />
	                        </a>
	                    </strong>
	                </s:if>
	                <s:else>
	                    ><a href="<s:url namespace="/term" action="termCategory"><s:param name="categoryId" value="categoryid" /></s:url>">
	                     <s:property value="cdesc" />
	                    </a>
	                </s:else>
	            </s:iterator>
	        </article>
			<nav class="folders">
	            <s:iterator value="list">
					<div class="folder">
						<p>
							<a href="<s:url namespace="/term" action="termCategory"><s:param name="categoryId" value="categoryid" /></s:url>">
								<s:property value="cdesc" />
							</a>
						</p>
						<s:set var="childCate" value="findChildCategoryByParentId(categoryid)" />
						<s:if test="#childCate!=null && #childCate.size()>0">
							<section>
							<ul>
								<s:iterator value="#childCate">
									<li>
										<a href="<s:url namespace="/term" action="termCategory"><s:param name="categoryId" value="categoryid" /></s:url>">
											<s:property value="cdesc" />
										</a>
									</li>
								</s:iterator>
							</ul>
							</section>
						</s:if>
					</div>
	            </s:iterator>
			</nav>
		</div>
        <input type="hidden" name="categoryId" id="categoryId" value="<s:property value="categoryId"/>" />
		<div class="main" style="display:inline-block; width: 930px;margin:20px 0 0 20px; background: url();font-size: 0;">
			<!-- 词条列表 -->
			<div id="termcontent"></div>
		</div>
	</section>
	<div class="clear"></div>
	<footer>
	   <section class="mainBox">
	       <jsp:include page="../../view/include/client_foot.jsp" />
	   </section>
    </footer>
</body>
</html>