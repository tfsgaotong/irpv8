<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>

<!-- 话题详情页面 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head>
    <title>话题</title>
    <link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/easyui.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/icon.css" />
	<link href="<%=rootPath %>client/css/oacss.css" rel="stylesheet" type="text/css" />
    <link href="<%=rootPath %>client/css/asx.css" rel="stylesheet" type="text/css" /> 
	<link href="<%=rootPath %>client/css/workhome-3482.min.css" rel="stylesheet" type="text/css" />
	<link href="<%=rootPath %>client/css/lib-3322.min.css" rel="stylesheet" type="text/css" />
	<link href="<%=rootPath %>admin/js/jquery.uploadify/uploadify.css" rel="stylesheet" type="text/css" />
	<jsp:include page="../include/client_skin.jsp"/>  
	<link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css"/>
	<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="<%=rootPath %>client/js/jquery-1.8.0.min.js"></script> 
	<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
	<script type="text/javascript" src="<%=rootPath %>client/js/jquery.easyui.min.js"></script> 
	<script type="text/javascript" src="<%=rootPath %>client/js/easyui-lang-zh_CN.js"></script>   
	<style type="text/css">
	body{behavior:url(hover.htc);}  
	textarea { resize:none; }
	.tab_l{ border-right: 1px solid;}
	.tab_2{ padding-left:10px;}
	</style> 
</head>

<body>  
    <div class="bg01">
        <jsp:include page="../../view/include/client_head.jsp" />
        <section class="mainBox">
            <nav class="privateNav"></nav>
        </section>
        
        <div class="main-gr"> 
	        <!--左侧内容-->
	        <s:iterator>
                <table width="100%" class="wp" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td align="center" class="tab_l" style="width:220px;">查看: 0 | 回复: 0 </td>
                        <td class="tab_2">
                            <span>
                                <a href="javascript:CTRSAction_gotoPage(2);"></a>
                            </span>											
                            <h1 style="font-size: 20px;">
                                <s:property value='irpProjectShareTask.sharedesc'/>
                            </h1>
                        </td>
                    </tr>
                    <tr>
                        <td align="center" class="tab_l" >
                        </td>
                        <td class="tab_2">
                            <span>发表于 <s:property value='irpProjectShareTask.crtime' /></span>
                        </td>
                    </tr>  
                    <tr>
                        <td align="center" class="tab_l" valign="top">
                            <div class="post_img">
                                <img style="width: 120px; height: 120px;" src="<%=rootPath %>client/images/male.jpg" />
                            </div>
                            <div class="post_nr">用户主题：
                                <a style="cursor: pointer;">0</a> 
      						</div>
                        </td>
                        <td class="tab_2">
                            <div class="post_r_nr">
                                <s:property value='irpProjectShareTask.themetext' escapeHtml="false"/>
                            </div>
        			    </td>
        			</tr>
                </table>
            </s:iterator>
            <div class="wp_980"></div> 
        </div> 
    </div>
    
    <jsp:include page="../include/client_foot.jsp" />
</body>
</html> 
