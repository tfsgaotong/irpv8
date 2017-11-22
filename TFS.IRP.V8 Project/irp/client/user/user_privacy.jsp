<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>企业社会化智力资源管理系统</title>
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/oacss.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/icon.css" />
<jsp:include page="../include/client_skin.jsp" />

<style type="text/css">
body{
	behavior:url(<%=rootPath %>client/js/hover.htc);
}
.STYLE1 {
	color: #0000FF;
	font-weight: bold;
	font-size:18px;
}
.STYLE1 a:hover{
	color: #0000FF;
	font-weight: bold;
	font-size:18px;
}
.main-gr .right .combo input{
  border: 0px;
}
.index_title h3 {
    font-size: 14px;
    font-weight: 700;
    border-bottom: 1px solid rgb(224, 224, 224);
    padding: 0px 0px 10px;
}
.rb_t {
    line-height: 20px;
    font-size: 12px;
    padding-left: 20px;
    padding-top: 20px;
}
.gt_three{
    padding-left: 100px;
    padding-top: 20px;


}
</style>

<script type="text/javascript" src="<%=rootPath %>client/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" >

/**
 * 保存隐私配置
 */
function savePrivacy(){
	 var onLineMessageMail = $("input[name=onLineMessageMail]:checked").val();
	 if(onLineMessageMail==null || onLineMessageMail=='' || onLineMessageMail==undefined){
		 onLineMessageMail = 0;
	 }else{
		 var selfMail = $('#selfMail').html();
		 if(selfMail.trim()=='未设置邮箱' || selfMail==null || selfMail==undefined){
			 $.dialog.tips('邮箱未正确设置',1,'32X32/fail.png'); 
			 $("input[name=onLineMessageMail]").removeAttr("checked");
			 return false;
		 }
	 }
	 var comment = $("input[name=comment]:checked").val();
	 var letter = $("input[name=letter]:checked").val();
	 var atme = $("input[name=atme]:checked").val();
	 var recommend  = $("input[name=recommend]:checked").val();
	 var beginlocation  = $("input[name=beginlocation]:checked").val();
	 var hotwordstatus = $("input[name=hotdocwords]:checked").val();
	 $.ajax({
		 type:"POST",
		 async:false,
		 cache:false,
		 url:'<%=rootPath%>user/saveprivacyset.action',
		 data:{
			 comment:comment,
			 letter:letter,
			 atme:atme,
			 recommend:recommend,
			 beginlocation:beginlocation,
			 onLineMessageMail:onLineMessageMail,
			 hotwordstatus:hotwordstatus
		 },
		 success:function(callback){
			 if(callback==7){
				 $.dialog.tips('保存成功',1,'32X32/succ.png');
			 }else{
				 $.dialog.tips('保存失败',1,'32X32/fail.png');   
			 }
		 },
		 error:function(){
			 $.dialog.tips('保存失败',1,'32X32/fail.png');   
			 
		 }
		 
	 });
	
}
</script>

</head>
<body>


<div class="bg01">
<!--头部菜单-->
<s:include value="../include/client_head.jsp"></s:include>
<!--头部菜单end-->
<div class="main-gr">
<!--左侧内容-->
<div class="left">
	<div class="leftmenu">
    	<h1>账号设置</h1>
        <dl>
        	<dt><em class="a"></em><a href="<%=rootPath %>user/user_set.action">个人资料</a></dt>
            	<dd><a href="<%=rootPath %>user/user_set.action">基本信息</a>
            	<a href="<%=rootPath %>user/user_edu.action">教育信息</a>
            	<a href="<%=rootPath %>user/user_career.action">职业信息</a></dd>
       	 	<dt><em class="b"></em><a href="<%=rootPath %>user/user_pic.action">修改头像</a></dt>
       	  	<dt><em class="c"></em><a href="<%=rootPath %>user/user_tag.action" >个人标签</a></dt>
            <dt><em class="d"></em><a href="<%=rootPath %>user/user_privacy.action" class="x">隐私设置</a></dt>
		
			<dt><em class="g"></em><a href="<%=rootPath %>user/user_group.action">个人分组</a></dt>
			<dt><em class="i"></em><a href="<%=rootPath %>site/user_subject.action">个人专题</a></dt>
			<dt><em class="h"></em><a href="<%=rootPath%>site/personallchanneltoshow.action?personid=<s:property value="@com.tfs.irp.util.LoginUtil@getLoginUserId()" />">个人分类</a></dt>
        	<dt><em class="e"></em><a href="<%=rootPath %>user/user_binding.action">账号绑定</a></dt>
        </dl>
    </div>
</div>
<!--左侧内容结束-->
<!--右侧内容-->
<div class="right">
   <div class="index_title">
    <h3>隐私设置</h3>
   </div>
   <div class="index_title">
   <div class="rb_t">
     <strong>评论</strong>
     <span style="color: rgb(153, 153, 153);font-size: 12px;" >&nbsp;设置谁可以对我评论</span>
   </div>  
   <div class="index_title gt_three">
     <p><input type="radio" name="comment" value="0" <s:if test="irpUserPrivacyComment.privacyvalue==0">checked="checked"</s:if> >&nbsp;&nbsp;<label>所有人</label>&nbsp;<span style="color: rgb(153, 153, 153);font-size: 12px;" >(全部用户)</span></p>
     <p><input type="radio" name="comment" value="1" <s:if test="irpUserPrivacyComment.privacyvalue==1">checked="checked"</s:if> >&nbsp;&nbsp;<label>我关注的人</label>&nbsp;<span style="color: rgb(153, 153, 153);font-size: 12px;" >(被你关注的人可以对你评论,其他人不可)</span></p>
     <p><input type="radio" name="comment" value="2" <s:if test="irpUserPrivacyComment.privacyvalue==2">checked="checked"</s:if> >&nbsp;&nbsp;<label>关注我的人</label>&nbsp;<span style="color: rgb(153, 153, 153);font-size: 12px;" >(关注你的人可以对你评论,其他人不可)</span></p>
   </div>
   <h3></h3>
   </div>
   
  <div class="index_title">
   <div class="rb_t">
     <strong>私信</strong>
     <span style="color: rgb(153, 153, 153);font-size: 12px;" >&nbsp;设置谁可以对我发私信</span>
   </div>  
   <div class="index_title gt_three">
     <p><input type="radio" name="letter" value="0" <s:if test="irpUserPrivacyMessage.privacyvalue==0">checked="checked"</s:if> >&nbsp;&nbsp;<label>所有人</label>&nbsp;<span style="color: rgb(153, 153, 153);font-size: 12px;" >(全部用户)</span></p>
     <p><input type="radio" name="letter" value="1" <s:if test="irpUserPrivacyMessage.privacyvalue==1">checked="checked"</s:if> >&nbsp;&nbsp;<label>我关注的人</label>&nbsp;<span style="color: rgb(153, 153, 153);font-size: 12px;" >(被你关注的人可以对你发私信,其他人不可)</span></p>
     <p><input type="radio" name="letter" value="2" <s:if test="irpUserPrivacyMessage.privacyvalue==2">checked="checked"</s:if> >&nbsp;&nbsp;<label>关注我的人</label>&nbsp;<span style="color: rgb(153, 153, 153);font-size: 12px;" >(关注你的人可以对你发私信,其他人不可)</span></p>
   </div>
   <h3></h3>
   </div>


 <div class="index_title">
   <div class="rb_t">
     <strong>@我的</strong>
     <span style="color: rgb(153, 153, 153);font-size: 12px;" >&nbsp;设置谁可以@我</span>
   </div>  
   <div class="index_title gt_three">
     <p><input type="radio" name="atme" value="0" <s:if test="irpUserPrivacyAtme.privacyvalue==0">checked="checked"</s:if> >&nbsp;&nbsp;<label>所有人</label>&nbsp;<span style="color: rgb(153, 153, 153);font-size: 12px;" >(全部用户)</span></p>
     <p><input type="radio" name="atme" value="1" <s:if test="irpUserPrivacyAtme.privacyvalue==1">checked="checked"</s:if> >&nbsp;&nbsp;<label>我关注的人</label>&nbsp;<span style="color: rgb(153, 153, 153);font-size: 12px;" >(被你关注的人可以@你,其他人不可)</span></p>
     <p><input type="radio" name="atme" value="2" <s:if test="irpUserPrivacyAtme.privacyvalue==2">checked="checked"</s:if> >&nbsp;&nbsp;<label>关注我的人</label>&nbsp;<span style="color: rgb(153, 153, 153);font-size: 12px;" >(关注你的人可以@你,其他人不可)</span></p>
   </div>
   <h3></h3>
   </div>
   
   <div class="index_title">
   <div class="rb_t">
     <strong>是否开启用户推荐</strong>
     <span style="color: rgb(153, 153, 153);font-size: 12px;" >&nbsp;如果用户的关注为空,系统则自动给您推荐用户</span>
   </div>  
   <div class="index_title gt_three">
     <p><input type="radio" name="recommend" value="1" <s:if test="irpUserPriVacyRecommend.privacyvalue==1">checked="checked"</s:if> >&nbsp;&nbsp;<label>开启</label>&nbsp;<span style="color: rgb(153, 153, 153);font-size: 12px;" >(开启系统推荐用户)</span></p>
     <p><input type="radio" name="recommend" value="2" <s:if test="irpUserPriVacyRecommend.privacyvalue==2">checked="checked"</s:if> >&nbsp;&nbsp;<label>关闭</label>&nbsp;<span style="color: rgb(153, 153, 153);font-size: 12px;" >(关闭系统推荐 用户)</span></p>
   </div>
   <h3></h3>
   </div>
   
   <div class="index_title">
   <div class="rb_t">
     <strong>指定首页位置</strong>
     <span style="color: rgb(153, 153, 153);font-size: 12px;" >&nbsp;指定登录成功后跳转到哪个页面</span>
   </div>  
   <div class="index_title gt_three">
     <p><input type="radio" name="beginlocation" value="1" <s:if test="irpUserPrivacyPageLoaction.privacyvalue==1">checked="checked"</s:if> >&nbsp;&nbsp;<label>个人首页</label>&nbsp;<span style="color: rgb(153, 153, 153);font-size: 12px;" ></span></p>
     <p><input type="radio" name="beginlocation" value="2" <s:if test="irpUserPrivacyPageLoaction.privacyvalue==2">checked="checked"</s:if> >&nbsp;&nbsp;<label>企业首页</label>&nbsp;<span style="color: rgb(153, 153, 153);font-size: 12px;" ></span></p>
   </div>
   <h3></h3>
   </div>
   
   <div class="index_title">
   <div class="rb_t">
     <strong>是否过滤文章中的热词</strong>
     <span style="color: rgb(153, 153, 153);font-size: 12px;" >&nbsp;查看文章时如果文章内包含百科词条中的词语标识出来,并加以解释.</span>
   </div>  
   <div class="index_title gt_three">
     <p><input type="radio" name="hotdocwords" value="1" <s:if test="irpUserPrivacyhotword.privacyvalue==1">checked="checked"</s:if> >&nbsp;&nbsp;<label>是</label>&nbsp;<span style="color: rgb(153, 153, 153);font-size: 12px;" ></span></p>
     <p><input type="radio" name="hotdocwords" value="0" <s:if test="irpUserPrivacyhotword.privacyvalue==0">checked="checked"</s:if> >&nbsp;&nbsp;<label>否</label>&nbsp;<span style="color: rgb(153, 153, 153);font-size: 12px;" ></span></p>
   </div>
   <h3></h3>
   </div>
   
   <div class="index_title">
   <div class="rb_t">
     <strong>在线消息</strong>
     <span style="color: rgb(153, 153, 153);font-size: 12px;" >&nbsp;在线消息同时推送到其他位置</span>
   </div>  
   <div class="index_title gt_three">
     <p><input type="checkbox" name="onLineMessageMail" value="1" <s:if test="onLineMessageMailObj.privacyvalue==1">checked="checked"</s:if> >&nbsp;&nbsp;<label>邮箱</label>&nbsp;<span style="color: rgb(153, 153, 153);font-size: 12px;" >(消息推送到邮箱)&nbsp;&nbsp;
	      	<font id="selfMail">
	      		<s:if test="irpUser.email==null || irpUser.email==''">
	      			未设置邮箱
		      	</s:if>
		      	<s:else>
		      		<s:property value="irpUser.email"/>
		      	</s:else>
	      	</font>
	      	<a href="<%=rootPath %>user/user_set.action">&nbsp;&nbsp;编辑邮箱</a>
	      </span></p>
   </div>
   <h3></h3>
   </div>
   
  <div style="padding-top: 10px;text-align: center;">
   <a class="zhuanz1" onclick="savePrivacy()" href="javascript:void(0);">保存</a>
  </div>


	
</div>
<!--右侧内容结束-->
<!--尾部信息-->
<s:include value="../include/client_foot.jsp"></s:include>
<!--尾部信息end-->
</div>
</div>


</body>
</html>