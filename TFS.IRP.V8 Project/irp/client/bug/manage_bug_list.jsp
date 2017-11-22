<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head>
     <title><s:property value="irpProject.prname"/> </title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/easyui.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/icon.css" />
	<link href="<%=rootPath %>client/css/oacss.css" rel="stylesheet" type="text/css" /> 
    <link href="<%=rootPath %>client/css/asx.css" rel="stylesheet" type="text/css" /> 
	<link href="<%=rootPath %>client/css/workhome-3482.min.css" rel="stylesheet" type="text/css" />
 	<link href="<%=rootPath %>client/css/lib-3322.min.css" rel="stylesheet" type="text/css" /> 
 	<link rel="Bookmark" href="images/24pinico.ico" />
	<link rel="Shortcut Icon" href="images/24pinico.ico" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/client/css/easyui.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/client/css/icon.css" />
	<link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css">
	<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css">
	<link href="<%=rootPath%>/client/css/oacss.css" rel="stylesheet" type="text/css" />
	<jsp:include page="../include/client_skin.jsp" />  
	<link href="<%=rootPath %>admin/js/jquery.uploadify/uploadify.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=rootPath %>client/js/jquery-1.8.0.min.js"></script> 
	<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
	<script type="text/javascript" src="<%=rootPath %>client/js/jquery.easyui.min.js"></script> 
	<script type="text/javascript" src="<%=rootPath %>client/js/easyui-lang-zh_CN.js"></script>  
	<script type="text/javascript" src="<%=rootPath %>admin/js/jquery.uploadify/swfobject.js"></script>
	<script type="text/javascript" src="<%=rootPath %>client/js/jquery.uploadify.min.js"></script> 

	<link rel="Bookmark" href="images/24pinico.ico" />
	<link rel="Shortcut Icon" href="images/24pinico.ico" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/client/css/easyui.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/client/css/icon.css" />
	<link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css">
	<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css">
	<link href="<%=rootPath%>/client/css/oacss.css" rel="stylesheet" type="text/css" />

<jsp:include page="../include/client_skin.jsp" />
<script type="text/javascript">
function bugInfo(serianum){
	//alert(bugId);
	hrefStr="<%=rootPath%>bug/simplebuginfo.action?serianum="+serianum;
	//alert(hrefStr);
	window.open(hrefStr);
}
$(function(){
	var pid=<s:property value="projectId" />;
	 //alert(pid);
	 document.getElementById("addBug").href="<%=rootPath%>bug/toAddBugPage.action?projectId="+pid;
});
</script>
<style type="text/css">
 .fyh{width:650px;margin:0px auto 10px 15px; clear:both;}
 .fyh ul{width:486px;margin:10px auto 20px auto; display:block;}
 .fyh ul span{color:#999; padding:2px 8px; border:1px solid #dbdbdb;}
 .fyh ul a {color:#999; padding:2px 8px; border:1px solid #dbdbdb;}
 .fyh ul a:hover{color:#333; padding:2px 8px; border:1px solid #c8c8c8;background:#ededed;}
</style>
  </head>
  
  <body>
  <div>
  <a  id="addBug"  class="easyui-linkbutton" data-options="iconCls:'icon-add'" plain="true" style="text-align: right;float: right;">发布一个Bug</a>
  </div>
  <div>
	  <form id="pageForm">
		<s:hidden name="pageNum" id="pageNum" />
		<s:hidden name="pageSize" id="pageSize" value="10"/>
		<s:hidden name="projectId" id="projectId" />
	  </form>
    <s:if test="irpBugs ==null">
    	<span>该项目没有Bug信息</span>
    </s:if>
    <s:else>
    	<table align="center">
    		<tr>
    			<td>标题</td>
    			<td>优先级</td>
    			<td>分配人</td>
    			<td>状态</td>
    		</tr>
    	<s:iterator value="irpBugs" var="bug">
    		<tr>
    			<td width="200px"><a title="<s:property value='#bug.title' />" onclick="bugInfo(<s:property value='#bug.serianum' />)" href="javascript:void(0)"><s:property value="#bug.title" /></a></td>
    			<td width="100px"><s:property  value="#bug.priority"/></td>
    			<td width="100px"><s:property  value="founderNameMap.get(#bug.founderid)"/></td>
    			<td width="100px"><s:property  value="#bug.neweststate"/></td>
    		</tr>
    	</s:iterator>	
    	</table>
    	
    	<div class="fyh" style="width:1200px;"><ul><s:property value="pageHTML" escapeHtml="false"/></ul></div>
    	<script type="text/javascript">
	/**
	 * 分页
	 */
	function pageNavigain(_nPageNum){
		//alert('进入分页方法');
		$('#pageNum').val(_nPageNum);
		var queryString = $('#pageForm').serialize();
		//alert(queryString);
		var urlStr="<%=rootPath %>bug/allbughome.action?"+queryString;
		//alert(urlStr);
		var result=$.ajax({
			type:"post",
			url:urlStr,
			async:false,
			cache:false,
			success:function(html){
				if(html!=null){
					//alert('分页完成');
					//$('#showFileList').html(html);
				}
			}
		}).responseText;	
		$('#showlinkprojectdiv').html(result);
	}
	</script>
    </s:else>
    </div>
  </body>
</html>
