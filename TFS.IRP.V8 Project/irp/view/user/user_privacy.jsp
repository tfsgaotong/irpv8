<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tfs.irp.util.LoginUtil"%>
<%@ page import="com.tfs.irp.user.entity.IrpUser"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<%
	String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
	Cookie[] cookies = request.getCookies();
	String sSkin = null;
	String skinColor = null;
	if (cookies != null) {  
        for (Cookie cookie : cookies) {  
            if ("nowskin".equals(cookie.getName())) {  
            	sSkin = cookie.getValue();  
            }  
        }  
    }
    if(sSkin==null){
    	skinColor="orange";
    }else{
	   skinColor= sSkin.substring(sSkin.lastIndexOf("/oapf-")+6,sSkin.lastIndexOf(".css"));
    }
   request.setAttribute("skinColor", skinColor);
	if(sSkin!=null){
		sSkin = sSkin.replaceAll("%3A", ":");
		out.println("<link id=\"skin\" href=\""+sSkin+"\" rel=\"stylesheet\" type=\"text/css\" />");
		String sColor = "i"+sSkin.substring(sSkin.lastIndexOf("/oapf-")+6,sSkin.lastIndexOf(".css"));
		out.println("<link id=\"skin1\" href=\""+rootPath+"/client/js/skins/"+sColor+".css\" rel=\"stylesheet\" type=\"text/css\" />");
	}else{
		out.println("<link id=\"skin\" href=\""+rootPath+"/client/css/oapf-blue.css\" rel=\"stylesheet\" type=\"text/css\" />");
		out.println("<link id=\"skin1\" href=\""+rootPath+"/client/js/skins/iblue.css\" rel=\"stylesheet\" type=\"text/css\" />");
	}
%>

<!doctype html>
<html lang="en" class="no-js">
<head>
<meta charset="utf-8">
<title>企业社会化智力资源管理系统</title>
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
<link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath %>view/css/easyui.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath %>view/css/icon.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath %>view/css/discuz.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.main-gr .right .combo input{
  border: 0px;
}
.index_title{font-size:14px;}
.index_title h3{
	font-size: 14px;
	font-weight: 700;
	border-bottom: 1px solid rgb(224, 224, 224);
	padding: 0px 0px 10px;
}
.index_title strong{
	color:#2061b0;
}
.gt_three{
    padding-left: 100px;
    padding-top: 20px;
}
.rb_t {
    font-size: 14px;
    line-height: 20px;
    padding-left: 55px;
    padding-top: 20px;
}
.huang{background:#fbb239; height:22px;padding:2px 25px;}
.huang:hover{ background:#fbb93f;padding:2px 25px;direction: none}
.lv{background:#9fce66; height:22px;padding:2px 25px;}
.lv:hover{ background:#aedb78;padding:2px 25px;}
.lan{background:#22b7de; height:22px;padding:2px 25px;}
.lan:hover{ background:#37bee1;padding:2px 25px;}
.hei{background:#252525; height:22px;padding:2px 25px; color:#999999;}
.hei:hover{ background:#353535;padding:2px 25px;}
</style>

<script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/modernizr.custom.js"></script>
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
		 if(selfMail=="" || selfMail==null || selfMail==undefined){
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

function changecss(url) {
	if (url != "") {
		document.getElementById('skin').href = url;
		var sColor  = 'i'+url.substring(url.lastIndexOf('/oapf-')+6,url.lastIndexOf('.css'));
	    document.getElementById('skin1').href='<%=rootPath%>client/js/skins/'+sColor+'.css';
		var expdate = new Date();
		expdate.setTime(expdate.getTime() + (24 * 60 * 60 * 1000 * 30));
		//expdate=null;
		//以下设置COOKIES时间为1年,自己随便设置该时间..
		SetCookie("nowskin", url, expdate, "/", null, false);
		if(typeof(CKEDITOR)!="undefined"){
			if(sColor=="iblack"){
				CKEDITOR.instances.editor.setUiColor('#EEEEEE');
			}else if(sColor=="iorange"){
				CKEDITOR.instances.editor.setUiColor('#F7EDD7');
			}else if(sColor=="igreen"){
				CKEDITOR.instances.editor.setUiColor('#EBF4DA');
			}else if(sColor=="iblue"){
				CKEDITOR.instances.editor.setUiColor('#ECFAFB');
			}else{
				CKEDITOR.instances.editor.setUiColor('#ECFAFB');
			}
		}
	}
}

function SetCookie(name, value) {
	var argv = SetCookie.arguments;
	var argc = SetCookie.arguments.length;
	var expires = (2 < argc) ? argv[2] : null;
	var path = (3 < argc) ? argv[3] : null;
	var domain = (4 < argc) ? argv[4] : null;
	var secure = (5 < argc) ? argv[5] : false;
	document.cookie = name
			+ "="
			+ escape(value)
			+ ((expires == null) ? "" : ("; expires=" + expires
					.toGMTString()))
			+ ((path == null) ? "" : ("; path=" + path))
			+ ((domain == null) ? "" : ("; domain=" + domain))
			+ ((secure == true) ? "; secure" : "");
}

function GetCookie(Name) { 
	var search = Name + "=";
	var returnvalue = "";
	if (document.cookie.length > 0) {
		offset = document.cookie.indexOf(search);
		if (offset != -1) {
			offset += search.length;
			end = document.cookie.indexOf(";", offset);
			if (end == -1)
				end = document.cookie.length;
			returnvalue = unescape(document.cookie.substring(offset, end));
		}
	} 
	return returnvalue;
}
</script>

</head>
<body>
<jsp:include page="../include/client_head.jsp" />
<section class="mainBox">
	<nav class="publicNav">
    </nav>
</section>
<section class="mainBox">
	<article class="location"><strong>个人设置</strong></article>
	<section class="layoutLeft">
        <nav class="sets">
        	<div class="folder">
            	<p><a href="<%=rootPath %>user/user_set.action" target="_self">基本资料</a></p>
            </div>
            <div class="folder">
            	<p class="current"><a href="<%=rootPath %>user/user_privacy.action" class="x" target="_self">隐私设置</a></p>
            </div>
            <div class="folder">
            	<p><a href="<%=rootPath %>user/user_pic.action" target="_self">设置头像</a></p>
            </div>
            <div class="folder">
            	<p><a href="<%=rootPath %>user/user_group.action" target="_self">个人分组</a></p>
            </div>
            <div class="folder">
            	<p><a href="<%=rootPath %>user/user_tag.action" target="_self">个人标签</a></p>
            </div>
            <div class="folder">
            	<p><a href="<%=rootPath %>user/user_binding.action" target="_self">账号绑定</a></p>
            </div>
        </nav>
	</section>
	<section class="setUp">
		<div class="pan title"><span>隐私设置</span></div>
		<div class="line area20"></div>
		<div class="pan"><em>你可以在这里对个人接收信息、私信、等进行设置</em></div>
		
		<div class="index_title">
			<div class="rb_t">
				<strong>换肤</strong>
				<span style="color: rgb(153, 153, 153);font-size: 14px;" >&nbsp;设置背景皮肤颜色</span>
			</div>  
			<div class="index_title gt_three" >
				<p><input type="radio" name="huanfu" onClick="changecss('<%=rootPath%>client/css/oapf-orange.css')" value="orange" <s:if test="#request.skinColor==orange">checked="checked"</s:if> >&nbsp;&nbsp;<label class="huang">橘黄</label>&nbsp;</p>
				<p><input type="radio" name="huanfu" onClick="changecss('<%=rootPath%>client/css/oapf-green.css')" value="green" <s:if test="#request.skinColor=='green'">checked="checked"</s:if> >&nbsp;&nbsp;<label class="lv">新绿</label>&nbsp;</p>
				<p><input type="radio" name="huanfu" onClick="changecss('<%=rootPath%>client/css/oapf-blue.css')" value="blue" <s:if test="#request.skinColor==blue">checked="checked"</s:if> >&nbsp;&nbsp;<label class="lan">水蓝</label>&nbsp;</p>
				<p><input type="radio" name="huanfu" onClick="changecss('<%=rootPath%>client/css/oapf-black.css')" value="black" <s:if test="#request.skinColor==black">checked="checked"</s:if> >&nbsp;&nbsp;<label class="hei">雅黑</label>&nbsp;</p>
			</div>
			<h3></h3>
		</div>
		<div class="index_title">
			<div class="rb_t" >
				<strong>评论</strong>
				<span style="color: rgb(153, 153, 153);font-size: 14px;" >&nbsp;设置谁可以对我评论</span>
			</div>  
			<div class="index_title gt_three" >
				<p><input type="radio" name="comment" value="0" <s:if test="irpUserPrivacyComment.privacyvalue==0">checked="checked"</s:if> >&nbsp;&nbsp;<label>所有人</label>&nbsp;<span style="color: rgb(153, 153, 153);font-size: 14px;" >(全部用户)</span></p>
				<p><input type="radio" name="comment" value="1" <s:if test="irpUserPrivacyComment.privacyvalue==1">checked="checked"</s:if> >&nbsp;&nbsp;<label>我关注的人</label>&nbsp;<span style="color: rgb(153, 153, 153);font-size: 14px;" >(被你关注的人可以对你评论,其他人不可)</span></p>
				<p><input type="radio" name="comment" value="2" <s:if test="irpUserPrivacyComment.privacyvalue==2">checked="checked"</s:if> >&nbsp;&nbsp;<label>关注我的人</label>&nbsp;<span style="color: rgb(153, 153, 153);font-size: 14px;" >(关注你的人可以对你评论,其他人不可)</span></p>
			</div>
   			<h3></h3>
		</div>
		<div class="index_title">
			<div class="rb_t" >
				<strong>私信</strong>
				<span style="color: rgb(153, 153, 153);font-size: 14px;" >&nbsp;设置谁可以对我发私信</span>
			</div>  
			<div class="index_title gt_three" >
				<p><input type="radio" name="letter" value="0" <s:if test="irpUserPrivacyMessage.privacyvalue==0">checked="checked"</s:if> >&nbsp;&nbsp;<label>所有人</label>&nbsp;<span style="color: rgb(153, 153, 153);font-size: 14px;" >(全部用户)</span></p>
				<p><input type="radio" name="letter" value="1" <s:if test="irpUserPrivacyMessage.privacyvalue==1">checked="checked"</s:if> >&nbsp;&nbsp;<label>我关注的人</label>&nbsp;<span style="color: rgb(153, 153, 153);font-size: 14px;" >(被你关注的人可以对你发私信,其他人不可)</span></p>
				<p><input type="radio" name="letter" value="2" <s:if test="irpUserPrivacyMessage.privacyvalue==2">checked="checked"</s:if> >&nbsp;&nbsp;<label>关注我的人</label>&nbsp;<span style="color: rgb(153, 153, 153);font-size: 14px;" >(关注你的人可以对你发私信,其他人不可)</span></p>
			</div>
	   		<h3></h3>
   		</div>
		<div class="index_title">
			<div class="rb_t">
				<strong>@我的</strong>
				<span style="color: rgb(153, 153, 153);font-size: 14px;" >&nbsp;设置谁可以@我</span>
			</div>  
			<div class="index_title gt_three" >
				<p><input type="radio" name="atme" value="0" <s:if test="irpUserPrivacyAtme.privacyvalue==0">checked="checked"</s:if> >&nbsp;&nbsp;<label>所有人</label>&nbsp;<span style="color: rgb(153, 153, 153);font-size: 14px;" >(全部用户)</span></p>
				<p><input type="radio" name="atme" value="1" <s:if test="irpUserPrivacyAtme.privacyvalue==1">checked="checked"</s:if> >&nbsp;&nbsp;<label>我关注的人</label>&nbsp;<span style="color: rgb(153, 153, 153);font-size: 14px;" >(被你关注的人可以@你,其他人不可)</span></p>
				<p><input type="radio" name="atme" value="2" <s:if test="irpUserPrivacyAtme.privacyvalue==2">checked="checked"</s:if> >&nbsp;&nbsp;<label>关注我的人</label>&nbsp;<span style="color: rgb(153, 153, 153);font-size: 14px;" >(关注你的人可以@你,其他人不可)</span></p>
			</div>
			<h3></h3>
		</div>
		<div class="index_title">
			<div class="rb_t" >
				<strong>用户推荐</strong>
				<span style="color: rgb(153, 153, 153);font-size: 14px;" >&nbsp;如果用户的关注为空,系统则自动给您推荐用户</span>
			</div>  
			<div class="index_title gt_three" >
				<p><input type="radio" name="recommend" value="1" <s:if test="irpUserPriVacyRecommend.privacyvalue==1">checked="checked"</s:if> >&nbsp;&nbsp;<label>开启</label>&nbsp;<span style="color: rgb(153, 153, 153);font-size: 14px;" >(开启系统推荐用户)</span></p>
				<p><input type="radio" name="recommend" value="2" <s:if test="irpUserPriVacyRecommend.privacyvalue==2">checked="checked"</s:if> >&nbsp;&nbsp;<label>关闭</label>&nbsp;<span style="color: rgb(153, 153, 153);font-size: 14px;" >(关闭系统推荐 用户)</span></p>
			</div>
			<h3></h3>
		</div>
		<div class="index_title">
			<div class="rb_t" >
				<strong>默认首页</strong>
				<span style="color: rgb(153, 153, 153);font-size: 14px;" >&nbsp;指定登录成功后跳转到哪个页面</span>
			</div>  
			<div class="index_title gt_three" >
				<p><input type="radio" name="beginlocation" value="1" <s:if test="irpUserPrivacyPageLoaction.privacyvalue==1">checked="checked"</s:if> >&nbsp;&nbsp;<label>个人首页</label>&nbsp;<span style="color: rgb(153, 153, 153);font-size: 14px;" ></span></p>
				<p><input type="radio" name="beginlocation" value="2" <s:if test="irpUserPrivacyPageLoaction.privacyvalue==2">checked="checked"</s:if> >&nbsp;&nbsp;<label>企业首页</label>&nbsp;<span style="color: rgb(153, 153, 153);font-size: 14px;" ></span></p>
			</div>
			<h3></h3>
		</div>
		<div class="index_title">
			<div class="rb_t" >
				<strong>热词开关</strong>
				<span style="color: rgb(153, 153, 153);font-size: 14px;" >&nbsp;查看文章时如果文章内包含百科词条中的词语标识出来,并加以解释.</span>
			</div>  
			<div class="index_title gt_three" >
				<p><input type="radio" name="hotdocwords" value="1" <s:if test="irpUserPrivacyhotword.privacyvalue==1">checked="checked"</s:if> >&nbsp;&nbsp;<label>是</label>&nbsp;<span style="color: rgb(153, 153, 153);font-size: 14px;" ></span></p>
				<p><input type="radio" name="hotdocwords" value="0" <s:if test="irpUserPrivacyhotword.privacyvalue==0">checked="checked"</s:if> >&nbsp;&nbsp;<label>否</label>&nbsp;<span style="color: rgb(153, 153, 153);font-size: 14px;" ></span></p>
			</div>
			<h3></h3>
		</div>
		<div class="index_title">
			<div class="rb_t" >
				<strong>在线消息</strong>
				<span style="color: rgb(153, 153, 153);font-size: 14px;" >&nbsp;在线消息同时推送到其他位置</span>
			</div>  
			<div class="index_title gt_three" >
				<p>
					<input type="checkbox" name="onLineMessageMail" value="1" <s:if test="onLineMessageMailObj.privacyvalue==1">checked="checked"</s:if> >&nbsp;&nbsp;<label>邮箱</label>&nbsp;<span style="color: rgb(153, 153, 153);font-size: 14px;" >(消息推送到邮箱)&nbsp;&nbsp;
					<font id="selfMail">
					<s:if test="irpUser.email==null || irpUser.email==''">
						未设置邮箱
					</s:if>
					<s:else>
						<s:property value="irpUser.email"/>
					</s:else>
					</font>
					&nbsp;&nbsp;<a href="<%=rootPath %>user/user_set.action">编辑邮箱</a>
				</span></p>
			</div>
		<h3></h3>
		</div>
		<div class="sub" style="text-align:left;padding:20px 0 0 80px;">
   			<input style="text-indent:0px;" type="button" value="保&nbsp;&nbsp;&nbsp;&nbsp;存" onclick="savePrivacy()">
   		</div>
	</section>
</section>
<jsp:include page="../include/client_foot.jsp" />
</body>
</html>