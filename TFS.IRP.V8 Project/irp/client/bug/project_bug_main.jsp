<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
<style type="text/css">
 .div_one_tab{
	float: left;
	width: 24%;
	line-height: 40px;
	text-align: center; 
	background: url("<%=rootPath%>client/images/search_bug.gif"); 
	}
</style>
</head>
  
  <body>
  <script type="text/javascript">
  function bugInfo(serianum){
	hrefStr="<%=rootPath%>bug/simplebuginfo.action?serianum="+serianum;
	window.open(hrefStr);
	}
  /**
   *  跳转到概览页面
   */	
  function toSeeAllBug(sta){
	  var dom=$('#allProject')[0];
	  $('#nstate').val(sta);
	  tabss(dom);
  }
  
  /**
  *设置左侧菜单css
  */	  
  function initleftsel(){
	  var pid=<s:property  value="projectId"   />;
		$("#dlmenutabs").find("a").each(function(_index,_this2){
			if($(_this2).find('input[type=hidden]').val()==pid){
				$(_this2).css("background-color","#DFF2F8");
			}else{
				$(_this2).css("background-color","");
			}
		});
  }
  $(function (){
		initleftsel(); 
  });
  </script>
    
    <div id="statistics" style="width: 100%;float: left;  " > 
    	<div class="div_one_tab div_one_tab_border" style="width: 25%;border:none;" ><a  onclick="toSeeAllBug('')" style="font-size:15px;   background: url(<%=rootPath%>client/images/common.gif) no-repeat left center;background-position: -474px -82px;" href="javascript:void(0)">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Bug总数
    	<s:property value="@com.tfs.irp.bug.web.BugAction@bugCountForProject(projectId)"/>
    	</a></div> 
    	<div class="div_one_tab div_one_tab_border"  style="width: 25%;border:none;" ><a  onclick="toSeeAllBug('1')"  style="font-size:15px;   background: url(<%=rootPath%>client/images/common.gif) no-repeat left center;background-position: -474px -105px;color: #CC3300;" href="javascript:void(0)">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;未修复的Bug数
    	<s:property value="@com.tfs.irp.bug.web.BugAction@bugCountForNotRepair(projectId)"/>
    	</a></div>
    	<div class="div_one_tab div_one_tab_border" style="width: 25%;border:none;"  ><a  onclick="toSeeAllBug('2')" style="font-size:15px;   background: url(<%=rootPath%>client/images/common.gif) no-repeat left center;background-position: -474px -152px;color: #FEA900;" href="javascript:void(0)">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;待审核的Bug数
    	<s:property value="@com.tfs.irp.bug.web.BugAction@bugCountForNotAudit(projectId)"/>
    	</a></div>
    	<div class="div_one_tab div_one_tab_border"   style="width: 25%;border:none; " ><a  onclick="toSeeAllBug('3')"  style="font-size:15px;   background: url(<%=rootPath%>client/images/common.gif) no-repeat left center;background-position: -474px -128px;color: #339900;" href="javascript:void(0)">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;已解决的Bug数
    	<s:property value="@com.tfs.irp.bug.web.BugAction@bugCountForResolved(projectId)"/>
    	</a></div>
    	
    	<div id="buginfotop"><jsp:include page="project_bug_main_top.jsp"/></div>
    	<div id="buginfobottom"><jsp:include page="project_bug_main_down.jsp"/></div>
    	
    </div>
    <div style="clear: both;"></div>
    <br/>
  </body>
</html>
