<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
    String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- 创建帖子页面 -->
<html>
<head>
    <title>创建帖子</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="<%=rootPath%>favicon.ico" type="image/x-icon"/>
    <link rel="bookmark" href="<%=rootPath%>favicon.ico"/>
    <link href="<%=rootPath%>view/css/normalize.css" rel="stylesheet" type="text/css"/>
    <link href="<%=rootPath%>view/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="<%=rootPath%>view/css/easyui.css" rel="stylesheet" type="text/css"/>
    <link href="<%=rootPath%>view/css/icon.css" rel="stylesheet" type="text/css"/>
    <link href="<%=rootPath%>view/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=rootPath%>view/js/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="<%=rootPath%>view/js/modernizr.custom.js"></script>
    <script type="text/javascript" src="<%=rootPath%>view/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="<%=rootPath%>view/js/lhgdialog.min.js"></script>
    <script type="text/javascript" src="<%=rootPath%>view/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=rootPath%>view/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="<%=rootPath%>view/editor/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="<%=rootPath%>view/ztree/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="<%=rootPath%>view/ztree/js/jquery.ztree.excheck-3.5.js"></script>
    <script type="text/javascript" src="<%=rootPath%>view/js/swfobject.js"></script>
    <script type="text/javascript" src="<%=rootPath%>view/js/jquery.uploadify.min.js"></script>
    <jsp:include page="../../view/include/client_skin.jsp" /> 
	<style type="text/css">
    .wtgy {
        height: 28px;
        line-height:28px;
        border: 1px solid rgb(209, 209, 209);
        color: rgb(102, 102, 102);
        padding: 0px 5px;
        width:300px;
        margin:20px 0 20px 5px;
    }
    
    body{
        behavior:url(hover.htc);
    }
    
    .searchSec .radios span {
        margin-top: 0px;
    }   
    </style>
</head>
<body style="background: url()">
    <s:include value="../../view/include/client_head.jsp"></s:include>
    <section class="mainBox">
        <nav class="privateNav"></nav>
    </section>
    <section class="mainBox">
        <section class="newForm">
            <article class="location">
                <strong>创建帖子（<span>*</span>必须填写项）</strong>
            </article>
            <table>
                <tr>
                    <td>
                        <input type="text" placeholder="请输入帖子名称..." id="postTitle" class="artTitle easyui-validatebox" required="true" validType="length[2,300]" />
                    </td>
                    <td width="20"><span class="must">*</span></td>
                    <td>
                        <input id="cate"  type="text" class="wtgy" placeholder="点击选择分类" readonly="readonly" onclick="choiceCate()" value="<s:property value="irpCategoryById(forumCategoryId).cname" />" style="width: 190px;">
                    </td>
                    <td width="20"><span class="must">*</span></td>
                    <input id="cateIds" type="hidden" value="<s:property value="forumCategoryId" />"/>
                </tr>
            </table>
            <div>
                <textarea id="termexplaincontent" name="editor"></textarea>
                <script>
                    var editor = CKEDITOR.replace('editor',{
                        filebrowserUploadUrl: '<%=rootPath%>file/ck_upload.action',
                        uiColor: '#EAF2FF',
                        height: 200
                    });
                </script>
            </div>
            <div style="text-align: center; margin-top: 20px;">
                <input type="button" class="btn" id="forumsubmit" value="保&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;存" />
                <input type="button" class="btn" id="closethispage" value="关&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;闭" />
            </div>
        </section>
        <section class="layoutRight formInfo" style="height: 458px;">
            <section>  
                <div class="title" style="margin-left:10px">附件管理</div>
                <input type="button" class="artFile" value="选择附件" style="width:100px; margin-left:8px;" onclick="tosaveAttacthed()"/>
            </section>
        </section>
    </section>
    <s:include value="../../view/include/client_foot.jsp"></s:include>
</body>
<script type="text/javascript">
/**
 * 
 *选择分类
 */
function choiceCate(){
    var result = $.ajax({
        url:"<%=rootPath%>category/getLeftCategoryByForum.action",
        cache:false,
        async:false
    }).responseText;
    
    $.dialog({
        content:result,
        title:'选择分类',
        min:false,
        max:false,
        lock:true,
        ok:function(){},
        okVal:'确认',
        padding: 0
    });
}

/**
 * 点击关闭按钮
 */
$('#closethispage').click(function(){
    window.close();
});

/**
 * 新建帖子
 */
function addPost(title, content, categoryId, addJsonFileList, callback){
    jQuery.ajax({
        url:"<%=rootPath%>forum/addForum.action",
        type:"POST",
        data:{
        	postTitle: title,
            postContent: content,
            categoryId: categoryId,
            attachedFile: JSON.stringify(addJsonFileList)
        },
        dataType: "json",
        beforeSend:function(){
            jQuery("#submit").attr("disabled", "true");
        },
        success:function(json){
            callback(json);
        },
        complete:function(){
            jQuery("#submit").removeAttr("disabled");
        }
    });
}

/**
 * 添加附件
 */
var addJsonFileList = new Array();
function tosaveAttacthed(){
    var str=$.ajax({
        type:'post',
        dataType: "json",
        url:'<%=rootPath %>site/client_to_save_attached.action?isqusertionordoc=2', 
        async: false,
        cache: false
    }).responseText;
    
    $.dialog({
        title:'附件管理',
        max:false,
        min:false,
        lock:true,
        resize: false,  
        content:str,
        cancelVal:'确定', 
        cancel:function(){
            if(addJsonFileList){  
                var id=$('input:radio[name="editversions"]:checked').attr("id"); 
                for(var i=0;i<addJsonFileList.length;i++){ 
                    if(addJsonFileList[i].attfile==id){
                        addJsonFileList[i].editversions=1; 
                    }else{ 
                        if(addJsonFileList[i].editversions=="2"){
                        }else{
                            addJsonFileList[i].editversions=0;
                        }
                    }
                }
            }
        }
    });   
}

/**
 * 点击提交按钮
 */
$('#forumsubmit').click(function(){
    // 获得分类
    var categoryId = $('#cateIds').val();
    
    // 获得标题
    var title = $('#postTitle').val();
    
    // 获得html内容
    var htmlcontent = editor.document.getBody().getHtml();
    
    // 获得文本内容
    var textcontent = editor.document.getBody().getText();

    // 标题不能为空
    if(title.trim().length === 0){
    	jQuery.dialog.tips("帖子标题不能为空！",1,"alert.gif");
        return;
    }
    
    // 标题字数限制
    if(title.trim().length > 50){
        jQuery.dialog.tips("帖子标题字数超出！",1,"alert.gif");
        return;
    }
    
    // 分类不能为空
    if(categoryId === ""){
    	jQuery.dialog.tips("请选择帖子分类！",1,"alert.gif");
        return;
    }
    
    // 帖子内容不能为空
    if(textcontent.trim().length === 0){
    	jQuery.dialog.tips("帖子内容不能为空！",1,"alert.gif");
        return;
    }
    
    addPost(title, htmlcontent, categoryId, addJsonFileList, function(json){
        if(json.resultStatus === "success"){
        	jQuery.dialog.tips("创建成功！", 1, "success.gif", function(){
        		window.location = "<%=rootPath%>forum/toForumIndex.action";
        	});
        }else{
        	jQuery.dialog.tips("创建失败，请重试！", 1, "error.gif", function(){
        		location.reload();
        	});
        }
    });
});

/**
 * 添加这个空函数，编辑器就可以执行加粗等一系列操作了
 */
function clearImages(){}
</script>
</html>