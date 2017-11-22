<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.tfs.irp.managementoper.entity.IrpManagementOper"%>
<%@page import="java.util.List"%>
<%@page import="com.tfs.irp.util.RightUtil"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	String sType = request.getParameter("type");
	RightUtil rightUtil = new RightUtil();
	List<IrpManagementOper> managementOpers = rightUtil.findManagementOper();
	if((sType == null || "".equals(sType)) && managementOpers!=null && managementOpers.size()>0){
		sType = managementOpers.get(0).getOpername();
	}

	//定义功能模块地址
	Map mHrefs = new HashMap();
	mHrefs.put("home", new String[] { rootPath+"admin/home/home_menu.jsp", "include/admin_info.jsp", "您的位置：<a href='index.action?type=home'>首页</a>"});
	mHrefs.put("set", new String[] {rootPath+"set/knowledgemenu.action",rootPath+"admin/set/set_knowledge.jsp", "您的位置：<a href='index.action?type=home'>首页</a>&nbsp;&gt;&nbsp;<a href='index.action?type=set'>全局设置</a>"});
	mHrefs.put("site", new String[] { rootPath+"site/all_sitename.action",	"include/admin_loading.jsp", "您的位置：<a href='index.action?type=home'>首页</a>&nbsp;&gt;&nbsp;<a href='index.action?type=site'>知识管理</a>"});
	mHrefs.put("category", new String[] { rootPath+"category/category_menu.action",	"include/admin_loading.jsp", "您的位置：<a href='index.action?type=home'>首页</a>&nbsp;&gt;&nbsp;<a href='index.action?type=category'>分类管理</a>"});
	mHrefs.put("user", new String[] { rootPath+"admin/user/user_menu.jsp",	rootPath+"user/use_user_list.action", "您的位置：<a href='index.action?type=home'>首页</a>&nbsp;&gt;&nbsp;<a href='index.action?type=user'>用户管理</a>"});
	mHrefs.put("schedule", new String[] { "include/admin_menu.jsp",	"include/admin_loading.jsp", "您的位置：<a href='index.action?type=home'>首页</a>"});
	mHrefs.put("stat", new String[] {rootPath+"admin/stat/statistics_info_menu.jsp",rootPath+"stat/statuserscoreexperience.action?pageNum=1&orderField", "您的位置：<a href='index.action?type=home'>首页</a>&nbsp;&gt;&nbsp;<a href='index.action?type=stat'>统计报表</a>"});
	mHrefs.put("log", new String[] {rootPath+"admin/log/log_logs_menu.jsp",	rootPath+"log/log_checkSearch_action.action?c_v_log_type=0&c_v_log_obj_oper=0&c_v_log_oper_obj_id=-1&c_v_log_oper_user&c_v_Irp_type_oper=0&c_start_end=logs_month&c_v_Irp_oper_result=0&c_date_start_time&c_date_end_time&pageNum=1", "您的位置：<a href='index.action?type=home'>首页</a>&nbsp;&gt;&nbsp;<a href='index.action?type=log'>日志管理</a>"});
	//mHrefs.put("app", new String[] {rootPath+"menu/findall_apptype.action",rootPath+"menu/select_allAppright.action", "您的位置：<a href='index.action?type=home'>首页</a>&nbsp;&gt;&nbsp;<a href='index.action?type=app'>应用中心</a>"});
	mHrefs.put("flow", new String[] { rootPath+"/admin/flow/flow_menu.jsp",	rootPath+"flow/flow_list.action", "您的位置：<a href='index.action?type=home'>首页</a>&nbsp;&gt;&nbsp;<a href='index.action?type=flow'>流程管理</a>"});
	mHrefs.put("radar", new String[] { rootPath+"radar/radar_menulist.action",	rootPath+"radar/radar_list.action", "您的位置：<a href='index.action?type=home'>首页</a>&nbsp;&gt;&nbsp;<a href='index.action?type=radar'>数据管理</a>"});
	
	mHrefs.put("qbank", new String[] { rootPath+"exam/qbankmenu.action",	rootPath+"exam/qbanklist.action?cateid=0&pagenum=1", "您的位置：<a href='index.action?type=home'>首页</a>&nbsp;&gt;&nbsp;<a href='index.action?type=qbank'>考务管理</a>"});
	mHrefs.put("meeting", new String[] { rootPath+"admin/meeting/statistics_info_menu.jsp",	rootPath+"admin/meeting/asseroomsb.jsp", "您的位置：<a href='index.action?type=home'>首页</a>&nbsp;&gt;&nbsp;<a href='index.action?type=meeting'>OA管理</a>"});
	mHrefs.put("form", new String[] { rootPath+"admin/form/form_menu.jsp",	rootPath+"admin/form/form_list.jsp", "您的位置：<a href='index.action?type=form'>首页</a>&nbsp;&gt;&nbsp;<a href='index.action?type=form'>表单管理</a>"});
	mHrefs.put("goods", new String[] { rootPath+"admin/goods/goods_menu.jsp",	rootPath+"admin/goods/goods_manage.jsp", "您的位置：<a href='index.action?type=home'>首页</a>&nbsp;&gt;&nbsp;<a href='index.action?type=goods'>积分兑换</a>"});
	/* mHrefs.put("covertrecord", new String[] { rootPath+"admin/covert/covertrecord_menu.jsp",	rootPath+"covert/listCovertRecord.action", "您的位置：<a href='index.action?type=home'>首页</a>&nbsp;&gt;&nbsp;<a href='index.action?type=covertrecord'>兑换记录</a>"}); */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>IRP系统后台管理</title>
<link rel="shortcut icon" href="../favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="../favicon.ico"/>
<link rel="stylesheet" type="text/css" href="./css/easyui.css" />
<link rel="stylesheet" type="text/css" href="./css/icon.css" />
<link rel="stylesheet" type="text/css" href="./css/css_body.css" />
<link rel="stylesheet" href="<%=rootPath%>client/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css" />
<link href="<%=rootPath%>/admin/js/jquery.uploadify/uploadify.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="./js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="./js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="./js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="./js/highcharts/highcharts.js"></script>
<script type="text/javascript" src="js/highcharts/exporting.js"></script>
<script type="text/javascript" src="./js/zyz_easydatecheck.js"></script>
<script type="text/javascript" src="<%=rootPath %>admin/ckeditor/ckeditor.js" ></script>
<script type="text/javascript" src="./js/json2.js"></script> 
<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.excheck-3.5.js"></script>
</head>

<body id="indexLayout" class="easyui-layout">

	<!-- head begin -->
	<div region="north" href="include/admin_head.jsp?MenuType=<%=sType %>"
		split="false"
		style="height:116px;padding:0px;margin:0px; overflow: hidden;"></div>
	<!-- head end -->
	<!-- foot begin -->
	<div region="south" href="include/admin_foot.jsp" split="false"
		style="height:30px;padding:0px;margin:0px;"></div>
	<!-- foot end -->
	<!-- left begin -->
	<div region="west" href="<%=((String[])mHrefs.get(sType))[0] %>" split="true"
		title="首 页" style="width:200px;padding1:1px;overflow:hidden;" id="uptitle"></div>
	<!-- left end -->
	<!-- center begin -->
	<div region="center" href="<%=((String[])mHrefs.get(sType))[1] %>" title="<%=((String[])mHrefs.get(sType))[2] %>"
		split="false" style="overflow:auto;padding: 5px;"></div>
	<!-- center end -->
	
</body>
</html>
