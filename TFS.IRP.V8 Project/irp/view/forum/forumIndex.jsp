<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.tfs.irp.util.LoginUtil"%>
<%
    String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- 论坛首页 -->
<html>
<head>
    <title>论坛</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="<%=rootPath%>favicon.ico" type="image/x-icon"/>
    <link rel="bookmark" href="<%=rootPath%>favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/common1.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/oacss.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/jquery.slideBox.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/wiki-home_2685284.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>view/forum/css/forum.css"/>
    <script type="text/javascript" src="<%=rootPath%>view/js/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="<%=rootPath%>view/js/modernizr.custom.js"></script>
    <script type="text/javascript" src="<%=rootPath%>view/js/jquery.slideBox.js"></script>
    <script type="text/javascript" src="<%=rootPath%>view/js/jquery.easyui.min.js"></script> 
    <script type="text/javascript" src="<%=rootPath%>view/js/easyui-lang-zh_CN.js"></script>
</head>
<script type="text/javascript">
jQuery(function($){

	$('#slide').slideBox({

		duration : 0.1,//滚动持续时间，单位：秒

		easing : 'linear',//swing,linear//滚动特效

		delay : 5,//滚动延迟时间，单位：秒

		hideClickBar : false,//不自动隐藏点选按键

		clickBarRadius : 0

	});
});
</script>
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
    
    <div class="mainContent">
        <!-- 左侧区域 -->
        <section class="layoutLeft">
        
            <!-- 分类列表 -->
            <nav class="allBtns">
                <ul>
                    <s:iterator value="listCategory">
                        <li>
                            <a href="<s:url namespace="/forum" action="toForumListByCategoryId"><s:param name="categoryId" value="categoryid" /></s:url>">
                                <s:property value="cdesc" />
                            </a>
                            <s:set var="childCate" value="findChildCategoryByParentId(categoryid)" /> 
                            <s:if test="#childCate!=null && #childCate.size()>0">
                                <div class="moreBtns">
                                    <div class="line">
                                        <s:iterator value="#childCate">
                                            <h1>
                                                <a href="<s:url namespace="/forum" action="toForumListByCategoryId"><s:param name="categoryId" value="categoryid" /></s:url>" title="<s:property value="chnldesc" />">
                                                    <s:property value="cdesc" />
                                                </a>
                                            </h1>
                                            <s:set var="grandsonCate" value="findChildCategoryByParentId(categoryid)" />
                                            <s:if test="#grandsonCate!=null && #grandsonCate.size()>0">
                                                <s:iterator value="#grandsonCate">
                                                    <P>
                                                        <a href="<s:url namespace="/forum" action="toForumListByCategoryId"><s:param name="categoryId" value="categoryid" /></s:url>" title="<s:property value="chnldesc" />">
                                                            <s:property value="cdesc" />
                                                        </a>
                                                    </P>
                                                </s:iterator>
                                            </s:if>
                                        </s:iterator>
                                    </div>
                                </div>
                            </s:if>
                        </li>
                    </s:iterator>
                </ul>
            </nav>
            
            <!-- 回复动态 -->
            <div class="commentLog"></div>
        </section>
        
        <!-- 右侧内容区域 -->
        <div class="rightContent">
            <section class="layoutMiddle1"> 
		        <!-- 轮播 -->
                <div class="banner" style="float:none;">
                    <div id="slide" class="slideBox" >
                        <ul class="items" >
                            <s:iterator value="chnlDocLinks" status="wt">
                                <s:if test="#wt.index<4">
                                    <s:set var="document" value="getIrpDocumentById(docid)" />
                                    <s:if test="#document.urladdress!='' && #document.urladdress!=null">
                                        <li>
                                            <a href="<s:property value='#document.urladdress'/>" target="_blank">
                                                <img src="<s:property value='docCoverPathSource(docid,docflag)' />" width="935" height="400"/>
                                                <p class="img_title"><s:property value='doctitle'/></p>
                                            </a>
                                        </li>
                                    </s:if>
                                    <s:else>
                                        <li>
                                            <a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>"  target="_blank">
                                                <img src="<s:property value='docCoverPathSource(docid,docflag)' />" width="935" height="400"/>
                                                <p class="img_title"><s:property value='doctitle'/></p>
                                            </a>
                                        </li>
                                    </s:else>
                                </s:if>
                            </s:iterator>
                        </ul>
                    </div>    
                </div>
                
                <!-- 版块信息 -->
                <div class="category" >
                    <div style="border-bottom: 2px solid #d1d1d1;padding-bottom: 5px;">
                        <span style="border-bottom: solid 2px #186fd2;color: #111;padding-bottom: 5px;" class="category-head">版块信息</span>
                        <button id="addPost">发帖</button>
                    </div>
                    <div class="category-content">
                        <ul>
                            
                        </ul>
                    </div>
                </div>
                
                <!-- 论坛达人 -->
                <div class="forumUser">
                    <div style="border-bottom: 2px solid #d1d1d1;padding-bottom: 5px;">
                        <span style="border-bottom: solid 2px #186fd2;color: #111;padding-bottom: 5px;"  class="forumUser-head">论坛达人</span>
                    </div>
                    <div class="forumUser-card">
                        <div class="forumUser-info">
                            <div class="forumUser-info-head"></div>
                            <div class="forumUser-info-img">
                                <img data-userId="" id="forumUserImgOne" src="<%=rootPath%>view/images/male.jpg" alt="用户头像">
                                <span id="forumUserOne">暂无用户</span>
                            </div>
                        </div>
                        <div class="forumUser-info">
                            <div class="forumUser-info-head"></div>
                            <div class="forumUser-info-img">
                                <img data-userId="" id="forumUserImgTwo" src="<%=rootPath%>view/images/male.jpg" alt="用户头像">
                                <span id="forumUserTwo">暂无用户</span>
                            </div>
                        </div>
                        <div class="forumUser-info">
                            <div class="forumUser-info-head"></div>
                            <div class="forumUser-info-img">
                                <img data-userId="" id="forumUserImgThree" src="<%=rootPath%>view/images/male.jpg" alt="用户头像">
                                <span id="forumUserThree">暂无用户</span>
                            </div>
                        </div>
                        <div class="forumUser-info">
                            <div class="forumUser-info-head"></div>
                            <div class="forumUser-info-img">
                                <img data-userId="" id="forumUserImgFour" src="<%=rootPath%>view/images/male.jpg" alt="用户头像">
                                <span id="forumUserFour">暂无用户</span>
                            </div>
                        </div>
                    </div>
                </div>
                
                <!-- 最新帖子 -->
                <div class="newPost">
                    <div style="border-bottom: 2px solid #d1d1d1;padding-bottom: 5px;">
                        <span style="border-bottom: solid 2px #186fd2;color: #111;padding-bottom: 5px;"  class="newPost-head">最新帖子</span>
                    </div>
                    <ul>
                       
                    </ul>
                </div>
                
                <!-- 最热帖子 -->
                <div class="hotPost">
	                <div style="border-bottom: 2px solid #d1d1d1;padding-bottom: 5px;">
	                    <span style="border-bottom: solid 2px #186fd2;color: #111;padding-bottom: 5px;"  class="hotPost-head">最热帖子</span>
	                </div>
                    <ul>
                        
                    </ul>
                </div>
            </section>
        </div>
    </div>
    
	<!--尾部信息-->
	<s:include value="../../view/include/client_foot.jsp"></s:include>
</body>
<script type="text/javascript">
window.onload = function(){
    // 获取全部分类
    getForumCategory(function(json){
    	if(json.resultStatus === "success"){
    		
	        // 拼接html字符串
	        var categoryInfoHtml = "";
	        var len = json.data.length;
	        for(var i = 0; i < len; i++){
	        	
	            categoryInfoHtml += "<li data-id='"+ json.data[i].categoryId +"'>"
	                                   +"<img src='<%=rootPath %>view/forum/images/forumImage.png'>"
	                                   +"<span class='category-title'>"+ json.data[i].categoryName +"</span>"
	                                   +"<span class='category-description'>"+ json.data[i].categoryInfo +"</span>"
	                                   +"<span class='postNum'>帖子："+ json.data[i].forumNum +"</span>"
	                                   +"<span class='date'>最新："+ json.data[i].date +"</span>"
	                                   +""
	                               +"</li>";
	        }
	        document.querySelector(".category-content ul").innerHTML = categoryInfoHtml;
    	}
    });
    
    // 论坛达人
    getCreateForumUserList();
    
    // 最新帖子列表
    getNewForumList();
    
    // 最热帖子列表
    getHotForumList();
    
    // 回复动态
    getCommentLog();
}

/**
 * 获取所有论坛分类及帖子数量和最新日期
 */
function getForumCategory(callback){
    jQuery.ajax({
        url:"<%=rootPath%>forum/getAllCategoryForum.action",
       type:"GET",
       data:{},
        dataType: "json",
        success:function(json){
            callback(json);
        }
    });
}

/**
 * 加载论坛达人
 */
function getCreateForumUserList(){
    jQuery.ajax({
        url:"<%=rootPath%>forum/getCreateForumUserList.action",
        type:"GET",
        data:{},
        dataType: "json",
        success:function(json){
            if(json.resultStatus === "success"){
                var length = json.data.length;
                if(length === 1){
                    // 设置用户名
                    jQuery("#forumUserOne").text(json.data[0].username);
                    
                    // 设置Id
                    jQuery("#forumUserImgOne").attr("data-userId", json.data[0].userId);
                    
                    // 设置头像
                    if(json.data[0].userImage !== ""){
                        jQuery("#forumUserImgOne").attr("src",'<%=rootPath%>file/readfile.action?fileName=' + json.data[0].userImage);
                    }
                }else if (length === 2){
                    // 设置用户名
                    jQuery("#forumUserOne").text(json.data[0].username);
                    jQuery("#forumUserTwo").text(json.data[1].username);
                    
                    // 设置Id
                    jQuery("#forumUserImgOne").attr("data-userId", json.data[0].userId);
                    jQuery("#forumUserImgTwo").attr("data-userId", json.data[1].userId);
                    
                    // 设置头像
                    if(json.data[0].userImage !== ""){
                        jQuery("#forumUserImgOne").attr("src",'<%=rootPath%>file/readfile.action?fileName=' + json.data[0].userImage);
                    }
                    if(json.data[1].userImage !== ""){
                        jQuery("#forumUserImgTwo").attr("src",'<%=rootPath%>file/readfile.action?fileName=' + json.data[1].userImage);
                    }
                }else if (length === 3){
                    // 设置用户名
                    jQuery("#forumUserOne").text(json.data[0].username);
                    jQuery("#forumUserTwo").text(json.data[1].username);
                    jQuery("#forumUserThree").text(json.data[2].username);
                    
                    // 设置Id
                    jQuery("#forumUserImgOne").attr("data-userId", json.data[0].userId);
                    jQuery("#forumUserImgTwo").attr("data-userId", json.data[1].userId);
                    jQuery("#forumUserImgThree").attr("data-userId", json.data[2].userId);
                    
                    // 设置头像
                    if(json.data[0].userImage !== ""){
                        jQuery("#forumUserImgOne").attr("src",'<%=rootPath%>file/readfile.action?fileName=' + json.data[0].userImage);
                    }
                    if(json.data[1].userImage !== ""){
                        jQuery("#forumUserImgTwo").attr("src",'<%=rootPath%>file/readfile.action?fileName=' + json.data[1].userImage);
                    }
                    if(json.data[2].userImage !== ""){
                        jQuery("#forumUserImgThree").attr("src",'<%=rootPath%>file/readfile.action?fileName=' + json.data[2].userImage);
                    }
                }else{
                    // 设置用户名
                    jQuery("#forumUserOne").text(json.data[0].username);
                    jQuery("#forumUserTwo").text(json.data[1].username);
                    jQuery("#forumUserThree").text(json.data[2].username);
                    jQuery("#forumUserFour").text(json.data[3].username);
                    
                    // 设置Id
                    jQuery("#forumUserImgOne").attr("data-userId", json.data[0].userId);
                    jQuery("#forumUserImgTwo").attr("data-userId", json.data[1].userId);
                    jQuery("#forumUserImgThree").attr("data-userId", json.data[2].userId);
                    jQuery("#forumUserImgFour").attr("data-userId", json.data[3].userId);
                    
                    // 设置头像
                    if(json.data[0].userImage !== ""){
                        jQuery("#forumUserImgOne").attr("src",'<%=rootPath%>file/readfile.action?fileName=' + json.data[0].userImage);
                    }
                    if(json.data[1].userImage !== ""){
                        jQuery("#forumUserImgTwo").attr("src",'<%=rootPath%>file/readfile.action?fileName=' + json.data[1].userImage);
                    }
                    if(json.data[2].userImage !== ""){
                        jQuery("#forumUserImgThree").attr("src",'<%=rootPath%>file/readfile.action?fileName=' + json.data[2].userImage);
                    }
                    if(json.data[3].userImage !== ""){
                        jQuery("#forumUserImgFour").attr("src",'<%=rootPath%>file/readfile.action?fileName=' + json.data[3].userImage);
                    }
                }
            }
        }
    });
}

/**
 * 获得最新帖子列表
 */
function getNewForumList(){
    jQuery.ajax({
        url:"<%=rootPath%>forum/getNewForumList.action",
        type : "GET",
        data : {},
        dataType : "json",
        success : function(json) {
            if(json.resultStatus === "success"){
                    
                // 拼接字符串
                var postListHtml = "";
                var len = json.dataList.length;
                for(var i = 0; i < len; i++){
                    postListHtml += "<li data-id='"+ json.dataList[i].id +"'>"
                                       +"<span class='post-title'>"+ json.dataList[i].title +"</span>"
                                       +"<span class='post-viewnum'>浏览量："+ json.dataList[i].viewnum +"</span>"
                                   +"</li>";
                }
                document.querySelector(".newPost ul").innerHTML = postListHtml;
            }
        }
    });
}

/**
 * 获得最热帖子列表
 */
function getHotForumList(){
    jQuery.ajax({
        url:"<%=rootPath%>forum/getHotForumList.action",
        type : "GET",
        data : {},
        dataType : "json",
        success : function(json) {
            if(json.resultStatus === "success"){
                    
                // 拼接字符串
                var postListHtml = "";
                var len = json.dataList.length;
                for(var i = 0; i < len; i++){
                	postListHtml += "<li data-id='"+ json.dataList[i].id +"'>"
                                       +"<span class='post-title'>"+ json.dataList[i].title +"</span>"
                                       +"<span class='post-viewnum'>浏览量："+ json.dataList[i].viewnum +"</span>"
                                   +"</li>";
                }
                document.querySelector(".hotPost ul").innerHTML = postListHtml;
            }
        }
    });
}

/**
 * 获得回复动态
 */
function getCommentLog(){
	jQuery.ajax({
        url:"<%=rootPath%>forumComment/getCommentLog.action",
        type : "GET",
        data : {},
        dataType : "json",
        success : function(json) {
            if(json.resultStatus === "success"){
            	
                // 拼接字符串
                var html = "";
                var len = json.dataList.length;
                for(var i = 0; i < len; i++){
                	html += "<div class='commentLog-box'>"
                	            +"<i>"+ json.dataList[i].friendlyDate +"</i>&nbsp;"
                	            +"<span>"+ json.dataList[i].username +"</span>"
                	            +"&nbsp;回复了帖子&nbsp;"
                	            +"<span>"+ json.dataList[i].forumName +"</span>"
                	        +"</div>";
                }
                document.querySelector(".commentLog").innerHTML = html;
            }else{
            	jQuery(".commentLog").hide();
            }
        }
    });
}

/**
 * 点击左侧分类，进入列表显示页
 */
jQuery(".left-category").on("click", "a", function(){
    var aDom = jQuery(this);
    var categoryId = aDom.attr("data-id");
    window.location = "<%=rootPath%>forum/toForumListByCategoryId.action?categoryId=" + categoryId;
});

/**
 * 点击版块名称，进入列表显示页
 */
jQuery(".category-content").on("click", "li", function(){
    var liDom = jQuery(this);
    var categoryId = liDom.attr("data-id");
    window.location = "<%=rootPath%>forum/toForumListByCategoryId.action?categoryId=" + categoryId;
});

/**
 * 点击最新帖子内容进入贴子详情页
 */
jQuery(".newPost").on("click", "li", function(){
    var spanDom = jQuery(this);
    var postId = spanDom.attr("data-id");
    window.location = "<%=rootPath%>forum/showForumInfo.action?forumId=" + postId;
});

/**
 * 点击最热帖子内容进入贴子详情页
 */
jQuery(".hotPost").on("click", "li", function(){
    var spanDom = jQuery(this);
    var postId = spanDom.attr("data-id");
    window.location = "<%=rootPath%>forum/showForumInfo.action?forumId=" + postId;
});

/**
 * 跳转到发帖页面
 */
jQuery("#addPost").click(function(){
    window.location = "<%=rootPath%>view/forum/addForum.jsp";
});

/**
 * 点击用户头像进入用户详情页
 */
jQuery(".forumUser").on("click", "img", function(){
    var imgDom = jQuery(this);
    var userId = imgDom.attr("data-userId");
    if(userId !== ""){
        window.location = "<%=rootPath%>site/client_to_index_person.action?personid=" + userId;
    }
});
</script> 
</html>