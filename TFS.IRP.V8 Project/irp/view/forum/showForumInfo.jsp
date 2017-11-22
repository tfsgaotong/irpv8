<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- 帖子详情页-->
<html>
<head>
    <title>帖子详情</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="<%=rootPath%>favicon.ico" type="image/x-icon"/>
    <link rel="bookmark" href="<%=rootPath%>favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/common1.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/oacss.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/wiki-home_2685284.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/forum/css/forumInfo.css"/>
	<script type="text/javascript" src="<%=rootPath%>view/js/jquery-1.8.0.min.js"></script> 
    <script type="text/javascript" src="<%=rootPath%>view/js/jquery.easyui.min.js"></script> 
    <script type="text/javascript" src="<%=rootPath%>view/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="<%=rootPath%>view/js/modernizr.custom.js"></script>
	<script type="text/javascript" src="<%=rootPath%>editor/templateeditor/ckeditor.js"></script>
	<jsp:include page="../../view/include/client_skin.jsp" />
</head>

<body style="background: url()"> 
    <!-- 头部导航 -->
    <jsp:include page="../../view/include/client_head.jsp" />
	    
    <!-- 蓝色横条 -->
    <section class="mainBox">
        <nav class="privateNav" style="background:url(../images/bg-04.gif) repeat-x">
        </nav>
    </section>
	    
    <div class="main-content">
        <div class="post-info-box">
	
            <div class="post-title-box">
                <span class="post-num-box">
	                                                   查看:&nbsp;<span class="post-view-num"> <s:property value='irpForum.forumviewcount'/> </span> 
	                 &nbsp;&nbsp;回复:&nbsp;<span class="post-comment-num"> <s:property value='commentCount'/> </span>
                </span>
	            <span class="post-title"> <s:property value='irpForum.forumtitle'/> </span>
	        </div>
	
	        <div class="post-content-box" id="postContent">
	            <div class="user-box">
	                <div class="category-name"> <s:property value='categoryName'/> </div>
	                <div class="user-info-box">
	                    <s:if test="irpUser.userpic!=null">
                            <img src="<%=rootPath%>file/readfile.action?fileName=<s:property value='irpUser.userpic' />"/>
                        </s:if>
                        <s:else>
                            <img <s:if test="irpUser.sex==2">src="<%=rootPath%>view/images/female.jpg"</s:if><s:else>src="<%=rootPath%>view/images/male.jpg"</s:else> alt="用户头像"/>
                        </s:else>
	                    <div class="user-name"> <s:property value='irpUser.username'/> </div>
	                </div>
	            </div>
	            <div class="content-box">
	                <div class="content-date">
	                    <img src="https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3587788205,2073078615&fm=27&gp=0.jpg" alt="">
	                                                                      发表于<span> <s:date name="irpForum.forumcrtime" format="yyyy-MM-dd hh:mm:ss"/> </span>
	                </div>
	                <div class="content" data-id="<s:property value="irpForum.forumid"/>">
	                    <s:property value='irpForum.forumcontent' escapeHtml="false"/>                                               
	                </div>
	                
	                <!-- 附件处理 -->
	                <s:iterator value="attachedList">
	                    <div style="display: inline-block;width: 245px;margin-right: 10px;">
	                                                                   附件：
	                        <s:if test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@JPG_FIELD_NAME==typeid">
	                            <span style="display:inline-block; background-image: url(<%=rootPath %>view/images/att.png); width:40px; height:40px; background-position: -11px -10px; vertical-align: middle;"></span>
	                        </s:if>
	                        <s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@WORD_FIELD_NAME==typeid">
	                            <span style="display:inline-block; background-image: url(<%=rootPath %>view/images/att.png); width:40px; height:40px; background-position: -60px -10px; vertical-align: middle;"></span>
	                        </s:elseif>
	                        <s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@MENU_FIELD_NAME==typeid">
	                            <span style="display:inline-block; background-image: url(<%=rootPath %>view/images/att.png); width:40px; height:40px; background-position: -110px -10px; vertical-align: middle;"></span>
	                        </s:elseif>
	                        <s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@MICUS_FIELD_NAME==typeid">
	                            <span style="display:inline-block; background-image: url(<%=rootPath %>view/images/att.png); width:40px; height:40px; background-position: -160px -10px; vertical-align: middle;"></span>
	                        </s:elseif>
	                        <s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@ID_FIELD_NAMEOTHER==typeid">
	                            <span style="display:inline-block; background-image: url(<%=rootPath %>view/images/att.png); width:40px; height:40px; background-position: -210px -10px; vertical-align: middle;"></span>
	                        </s:elseif>
	                        <a href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>"  style="cursor: pointer; text-decoration: none;">
	                            <s:property value="attdesc" />
	                        </a>
	                        <a href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>"  style="cursor: pointer; text-decoration: none;">下载</a>
	                        <s:if test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@WORD_FIELD_NAME==typeid">
	                            | <a target="_blank" href="<%=rootPath%>file/online_preview.action?fileName=<s:property value='attfile'/>"  style="cursor: pointer; text-decoration: none;">在线浏览</a>
	                        </s:if>
	                        <s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@JPG_FIELD_NAME==typeid">
	                            |  <a target="_blank" href="<%=rootPath%>file/inlinereadfile.action?fileName=<s:property value='attfile'/>"  style="cursor: pointer; text-decoration: none;">在线浏览</a>
	                        </s:elseif>
	                    </div>
	                </s:iterator>
	            </div>
	        </div>
	        <div id="commentContent"></div>
	    </div>
	        
	    <div class="page" style="height: 50px; margin-top:30px; margin-left:1000px"></div>
	
	    <div class="comment-box">
	       <div class="comment-head">快速回帖</div>
	       <textarea name="editor01" id="postContentEditor" cols="10" rows="10"></textarea>
		   <script type="text/javascript">var editor=CKEDITOR.replace('editor01', { height: '250px', width: '1200px' });</script>
	       <button id="addComment">发表</button>
	    </div>
	        
	    <!--尾部信息-->
        <s:include value="../../view/include/client_foot.jsp"></s:include>
	</div>
</body>
<script type="text/javascript">
// 获取帖子id
var postId = jQuery(".content").attr("data-id");

window.onload = function(){
	getCommentList(postId, 1);
};

/**
 * 分页获取所有回复
 */
function getCommentList(postId, pageNum){
	jQuery.ajax({
        url:"<%=rootPath%>forumComment/getCommentList.action",
        type:"POST",
        data:{
            forumId:postId,
            pageNum:pageNum,
        },
        dataType: "json",
        success:function(json){
        	
        	// 清空回复内容
        	jQuery("#commentContent").html("");
        	if(json.resultStatus === "success"){
                
                // 拼接字符串
                var commentListHtml = "";
                var len = json.dataList.length;
                for(var i = 0; i < len; i++){
                	
                	// 判断是否采用默认头像
                	var userPic = json.dataList[i].authorPic;
                	var imgHtml = "";
                	if(userPic === ""){
                		imgHtml = "<%=rootPath%>view/images/male.jpg";
                	}else{
                		imgHtml = "<%=rootPath%>file/readfile.action?fileName="+userPic;
                	}
                	
                	commentListHtml += "<div class='post-content-box'>"
                                           +"<div class='user-box'>"
                                               +"<div class='category-name'> <s:property value='categoryName'/> </div>"
                                               +"<div class='user-info-box'>"
						                            +"<img src='"+ imgHtml +"'/>"
                                                    +"<div class='user-name'>"+ json.dataList[i].author +"</div>"
                                               +"</div>"
                                           +"</div>"
                                           +"<div class='content-box'>"
                                               +"<div class='content-date'>"
                                                   +"<img src='https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3587788205,2073078615&fm=27&gp=0.jpg'>"
                                                   +"发表于<span>"+ json.dataList[i].date +"</span>"
                                               +"</div>"
                                               +"<div class='content' data-id='"+ json.dataList[i].commentId +"'>"+ json.dataList[i].commentContent +"</div>"
                                           +"</div>"
                                       +"</div>";
                }

                /*
                 * 判断当前页数，如果是第一页，则显示帖子的内容，
                 * 否则，隐藏帖子内容，只显示回复数据。
                 */
                if(pageNum == 1){
                	jQuery(".post-content-box").show();
                    jQuery("#commentContent").html(commentListHtml);
                    document.querySelector(".page").innerHTML = json.pageHtml;
                } else {
                	jQuery(".post-content-box").hide();
                	jQuery("#commentContent").html(commentListHtml);
                    document.querySelector(".page").innerHTML = json.pageHtml;
                }
            }
        }
    });
}

/**
 * 点击分页跳转
 */
function page(pageNum){
	getCommentList(postId, pageNum);
}

/**
 * 添加回复
 */
function addComment(postId, content, callback){
	jQuery.ajax({
        url:"<%=rootPath%>forumComment/addComment.action",
        type:"POST",
        data:{
            forumId:postId,
            commentContent:content,
        },
        dataType: "json",
        beforeSend:function(){
        	jQuery("#addComment").attr("disabled", "true");
        },
        success:function(json){
        	callback(json);
        },
        complete:function(){
        	jQuery("#addComment").removeAttr("disabled");
        }
    });
}

/**
 * 点击确定提交评论
 */
jQuery("#addComment").click(function(){
	// 获得回复html内容
    var htmlcontent = editor.document.getBody().getHtml();
	
    // 获得回复文本内容
    var textcontent = editor.document.getBody().getText();
    
    // 回复内容不能为空
    if(textcontent.trim().length === 0){
        jQuery.messager.alert('警告','回复内容不能为空！');
        return;
    }
    
    // 提交回复
    addComment(postId, htmlcontent, function(json){
        if(json.resultStatus === "success"){
        	jQuery.dialog.tips("回复成功！", 1, "success.gif", function(){
        		
        		// 回复成功清空文本框的值
                editor.setData("");
                location.reload();
            });
        }else{
            jQuery.dialog.tips("回复失败，请重试！", 1, "error.gif", function(){
                
            	location.reload();
            });
        }
    })
});
</script> 
</html>