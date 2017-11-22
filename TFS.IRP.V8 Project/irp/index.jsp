<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	<%@page import="com.tfs.irp.util.SysFileUtil"%>
<%
	String rootPath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ request.getContextPath() + "/";
%>

<html>
<head>
<meta charset="utf-8">

<title>IRP-从优秀到卓越</title>
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
<script src="<%=rootPath %>images/modernizr.custom.js"></script>
	<script src="<%=rootPath %>images/jquery-1.8.0.min.js"></script>
	<link href="<%=rootPath %>images/normalize.css" rel="stylesheet" type="text/css">
	<link href="<%=rootPath %>css/common1.css" rel="stylesheet" type="text/css">
<style>
    .flexslider {
        position: relative;
        width: 100%;
        height: 510px;
        overflow: hidden;
        zoom: 1;
    }
    
    .flexslider .slides li {
        width: 100%;
        height: 100%;
    }
    
    .flex-direction-nav a {
        width: 70px;
        height: 70px;
        line-height: 99em;
        overflow: hidden;
        margin: -35px 0 0;
        display: block;
        background: url(images/ad_ctr.png) no-repeat;
        position: absolute;
        top: 50%;
        z-index: 10;
        cursor: pointer;
        opacity: 0;
        filter: alpha(opacity=0);
        -webkit-transition: all .3s ease;
        border-radius: 35px;
    }
    
    .flex-direction-nav .flex-next {
        background-position: 0 -70px;
        right: 0;
    }
    
    .flex-direction-nav .flex-prev {
        left: 0;
    }
    
    .flexslider:hover .flex-next {
        opacity: 0.8;
        filter: alpha(opacity=25);
    }
    
    .flexslider:hover .flex-prev {
        opacity: 0.8;
        filter: alpha(opacity=25);
    }
    
    .flexslider:hover .flex-next:hover,
    .flexslider:hover .flex-prev:hover {
        opacity: 1;
        filter: alpha(opacity=50);
    }
    
    .flex-control-nav {
        width: 100%;
        position: absolute;
        bottom: 10px;
        text-align: center;
    }
    
    .flex-control-nav li {
        margin: 0 2px;
        display: inline-block;
        zoom: 1;
        *display: inline;
    }
    
    .flex-control-paging li a {
        background: url(images/dot.png) no-repeat 0 -16px;
        display: block;
        height: 16px;
        overflow: hidden;
        text-indent: -99em;
        width: 16px;
        cursor: pointer;
    }
    
    .flex-control-paging li a.flex-active,
    .flex-control-paging li.active a {
        background-position: 0 0;
    }
    
    .flexslider .slides a img {
        width: 100%;
        height: 510px;
        display: block;
    }
    footer{ margin-top:0px;}
    .area10 {
    height: 2px;
    clear: both;
}
    </style>
</head>

<body>

<div class="header" style="position: relative;z-index: 1;">
    <div class="w1200">
     <a href="<%=rootPath %>" target="_blank"><img src="<s:property value="@com.tfs.irp.util.SysFileUtil@getSiteBanner()" />"/></a>
     
        <ul>
           <%--  <li><a href="<%=rootPath %>">首页</a></li>
	            <li><a href="<%=rootPath %>document/toDocumentIndex.action">知识库</a></li>
	            <li><a href="<%=rootPath %>client/index.action">微知</a></li>
	            <li><a href="<%=rootPath %>question/questionIndex.action">问答</a></li>
	            <li><a href="<%=rootPath %>project/projectIndex.action">项目</a></li>
	            <li><a href="<%=rootPath %>expert/expertCategory.action">专家</a></li> --%>
	            <li><a href="<%=rootPath %>reg.action">注册</a></li>
	            <div class="clear"></div>
        </ul>
         <a href="<%=rootPath %>login.action">
        <div class="reg">登录</div>
        </a>
    </div>
    
</div>


<div id="banner_tabs" class="flexslider">
        <ul class="slides">
            <li style="position: relative;">
                <a title="" target="_blank" href="<%=rootPath %>login.action">
                    <img width="1920" height="532" alt="" style="background: url(images/b1.jpg) no-repeat center;" src="images/alpha.png">
                </a>
                <div style="position: absolute;left: 100px;top:120px;font-size: 42px;color: #fff">知识零散？查找困难？ <br />
                    <span style="font-size: 42px;padding-top:22px;display: block">新人培训耗精力？ </span>
                    <span style="font-size: 22px;padding-top:22px;display: block">IRP知识库帮您简单搞定！ </span><br>
                    <div onclick="window.open('<%=rootPath %>login.action','_self');" style="width: 148px;height: 48px;border: solid 1px #fff;font-size: 20px;color: #fff;line-height: 48px;text-align: center;cursor: pointer;">查看更多</div>
                </div>
            </li>
            <li style="position: relative;">
                <a title="" href="<%=rootPath %>login.action">
                    <img width="1920" height="532" alt="" style="background: url(images/b2.jpg) no-repeat center;" src="images/alpha.png">
                </a>
                <div style="position: absolute;left: 100px;top:120px;font-size: 42px;color: #fff">去哪提问？与大咖面对面？<br />
                 <span style="font-size: 42px;padding-top:22px;display: block">加强员工归属感？</span>
                    <span style="font-size: 22px;padding-top:22px;display: block">IRP知识社区搞定内部社交！ </span><br>
                     <div onclick="window.open('<%=rootPath %>login.action','_self');" style="width: 148px;height: 48px;border: solid 1px #fff;font-size: 20px;color: #fff;line-height: 48px;text-align: center;cursor: pointer;">查看更多</div>
 
</div>
            </li>
            <li style="position: relative;">
                <a title="" href="<%=rootPath %>login.action">
                    <img width="1920" height="532" alt="" style="background: url(images/b3.jpg) no-repeat center;" src="images/alpha.png">
                    <div style="position: absolute;left: 100px;top:120px;font-size: 42px;color: #fff">实施效果如何？焦点在哪？<br />
                     <span style="font-size: 42px;padding-top:22px;display: block">系统价值几何？</span>
                    <span style="font-size: 22px;padding-top:22px;display: block">IRP知识运营助力企业智力资产升值！ </span><br>
                     <div onclick="window.open('<%=rootPath %>login.action','_self');" style="width: 148px;height: 48px;border: solid 1px #fff;font-size: 20px;color: #fff;line-height: 48px;text-align: center;cursor: pointer;">查看更多</div>
 
                    </div> 
                    
                </a>
            </li>
        </ul>
        <ul class="flex-direction-nav">
            <li><a class="flex-prev" href="javascript:;">Previous</a></li>
            <li><a class="flex-next" href="javascript:;">Next</a></li>
        </ul>
        <ol id="bannerCtrl" class="flex-control-nav flex-control-paging">
            <li><a>1</a></li>
            <li><a>2</a></li>
            <li><a>2</a></li>
        </ol>
    </div>
   <jsp:include page="/view/include/client_foot.jsp" />
	 <script src="<%=rootPath %>images/jquery-1.10.2.min.js"></script>
    <script src="<%=rootPath %>images/slider.js"></script>
    <script type="text/javascript">
    $(function() {
        var bannerSlider = new Slider($('#banner_tabs'), {
            time: 5000,
            delay: 400,
            event: 'hover',
            auto: true,
            mode: 'fade',
            controller: $('#bannerCtrl'),
            activeControllerCls: 'active'
        });
        $('#banner_tabs .flex-prev').click(function() {
            bannerSlider.prev()
        });
        $('#banner_tabs .flex-next').click(function() {
            bannerSlider.next()
        });
    })
    </script>


</body>
</html>
