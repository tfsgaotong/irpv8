<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.tfs.irp.util.LoginUtil"%>
<%
    String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- 论坛列表页 -->
<html>
<head>
    <title>论坛</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="<%=rootPath%>favicon.ico" type="image/x-icon"/>
    <link rel="bookmark" href="<%=rootPath%>favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/common1.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/oacss.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/wiki-home_2685284.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>view/forum/css/forumList.css"/>
    <script type="text/javascript" src="<%=rootPath%>view/js/jquery-1.8.0.min.js"></script> 
    <script type="text/javascript" src="<%=rootPath%>view/js/jquery.easyui.min.js"></script> 
    <script type="text/javascript" src="<%=rootPath%>view/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="<%=rootPath%>view/js/modernizr.custom.js"></script>
</head>
<body> 
    <jsp:include page="../../view/include/client_head.jsp" />
    <section class="mainBox">
        <nav class="privateNav" style="background:url(../images/bg-04.gif) repeat-x">
            <dl>
                <dt class="leftBox">全部版块分类</dt>
                <dd class="clear"></dd>
            </dl>
        </nav>
    </section>
	    
    <!-- 中间内容 -->
	<div class="content">
		<!-- 左侧区域 -->
		<section class="layoutLeft">
			<article class="location">
	            <s:iterator value="listCategory" status="index">
					<s:if test="#index.first">
						<strong>
						    <a href="<s:url namespace="/forum" action="toForumListByCategoryId"><s:param name="categoryId" value="categoryid" /></s:url>">
								<s:property value="cdesc" />
							</a>
						</strong>
					</s:if>
					<s:else>
		                ><a href="<s:url namespace="/forum" action="toForumListByCategoryId"><s:param name="categoryId" value="categoryid" /></s:url>">
							<s:property value="cdesc" />
						</a>
					</s:else>
	            </s:iterator> 
	        </article>
	
			<div style="width: 245px;float: left;display: inline;">
				<nav class="folders">
	                <s:iterator value="list">
						<div class="folder">
							<p>
								<a href="<s:url namespace="/forum" action="toForumListByCategoryId"><s:param name="categoryId" value="categoryid" /></s:url>">
									<s:property value="cdesc" />
								</a>
							</p>
							<s:set var="childCate" value="findChildCategoryByParentId(categoryid)" />
							<s:if test="#childCate!=null && #childCate.size()>0">
								<section>
									<ul>
										<s:iterator value="#childCate">
											<li>
												<a href="<s:url namespace="/forum" action="toForumListByCategoryId"><s:param name="categoryId" value="categoryid" /></s:url>">
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
		</section>

		<div class="right-postsList">
			<button id="addPost">发帖</button>
			<div class="right-postsList-head">
				<span class="theme">主题</span>
				<span class="browser">回复/浏览</span>
				<span class="autor">作者</span>
			</div>

			<!-- 帖子列表 -->
			<s:if test="dataList.size > 0">
				<div class="themelist">
					<ul>
						<s:iterator value="dataList">
							<li data-id="<s:property value="id" />">
								<div class="title">
									<s:property value="title" />
								</div>
								<div class="autorname" style="color: #3481f2;">
									<s:property value="username" />
								</div>
								<div class="date" style="color: #8a8b8c;">
									<s:property value="date" />
								</div>
								<div class="browsernum">
									<span style="color: #3481f2;">
										<s:property value="commentCount" />
									</span>
									/
									<span style="color: #8a8b8c;">
										<s:property value="viewCount" />
									</span>
								</div>
							</li>
						</s:iterator>
					</ul>

					<!--  分页显示 -->
					<div class="page" style="height: 50px; margin-top: 20px; margin-left: 750px">
						<s:property value="pageHTML" escapeHtml="false" />
					</div>
				</div>
			</s:if>
			<s:else>
				<span class="noContent">暂无相关帖子</span>
			</s:else>
		</div>
	</div>

	<!--尾部信息-->
	<s:include value="../../view/include/client_foot.jsp"></s:include>
</body>
<script type="text/javascript">
// 创建获取一个全局分类id
var globalCategoryId = <s:property value="categoryId" />;

/**
 * 点击分页跳转
 */
function page(pageNum){
	window.location = "<%=rootPath%>forum/toForumListByCategoryId.action?categoryId=" + globalCategoryId + "&pageNum=" + pageNum;
}


/**
 * 点击帖子内容进入贴子详情页
 */
jQuery(".themelist").on("click", "li", function(){
    var liDom = jQuery(this);
    var postId = liDom.attr("data-id");
    window.location = "<%=rootPath%>forum/showForumInfo.action?forumId=" + postId;
});

/**
 * 跳转到发帖页面
 */
jQuery("#addPost").click(function(){
    window.location = "<%=rootPath%>view/forum/addForum.jsp";
});
</script> 
</html>