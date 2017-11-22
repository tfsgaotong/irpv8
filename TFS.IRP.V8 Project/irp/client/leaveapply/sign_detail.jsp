<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
    <%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请假申请明细</title>

<style type="text/css">
a {
    color: #333;
    text-decoration: none;
}
a:hover {
    color: #5f9ddd;
    text-decoration: underline;
}
</style>


</head>
<body>
<form id="addBugPage"  method="post" onsubmit="return false;">
	 	
	 	<div style="float: left;width: 100%;">&nbsp;</div><br/>
	 	<div  class="newForm" style="width: 100%;float: none;margin-top: 30px;"> 
			<div> 
				<div class="txt_lptaskt"  style="width: 8%;float: left;line-height: 30px;text-align: right;">请假类型：</div>
				&nbsp;&nbsp;<input  class="artTitle easyui-validatebox"value="<s:property value='irpLeaveapplyInfo.applytypeid'/>" />  
			</div> 
        </div>
        <div style="float: left;width: 100%;">&nbsp;</div><br/>
        <div class="newForm"  style="width: 100%;float: none;margin-top: 20px;"> 
        
        <div class="txt_lptaskt"  style="width: 8%;float: left;line-height: 30px;text-align: right;">请假理由：</div> 
	    &nbsp;&nbsp;<input  class="artTitle easyui-validatebox"      value="<s:property value='irpLeaveapplyInfo.applyreason'/>" />  
	    
	    </div> 
	    <div    style="float: left;width: 100%;">&nbsp;</div><br/>
	    <div class="newForm"  style="width: 100%;float: none;margin-top: 20px;" >  
	    
	    <div class="txt_lptaskt"  style="width: 8%;float: left;line-height: 30px;text-align: right;">请假开始时间:</div>
	    &nbsp;&nbsp;<input  class="artTitle easyui-validatebox"      value="<s:date name="irpLeaveapplyInfo.starttime" format="yyyy-MM-dd HH:mm"/>" />
        </div>
		<div style="float: left;width: 100%;">&nbsp;</div><br/>
	    <div   class="newForm" style="width: 100%;float: none;margin-top: 20px;">  
	    	    <div class="txt_lptaskt"  style="width: 8%;float: left;line-height: 30px;text-align: right;">请假结束时间:</div>
	    &nbsp;&nbsp;<input  class="artTitle easyui-validatebox"      value="<s:date name="irpLeaveapplyInfo.endtime" format="yyyy-MM-dd HH:mm"/>" />
	    </div>
	    <div style="float: left;width: 100%;">&nbsp;</div><br/>
        <div class="newForm"  style="width: 100%;float: none;margin-top: 20px;"> 
        	<div class="txt_lptaskt"  style="width: 8%;float: left;line-height: 30px;text-align: right;">处理人：</div>
        	&nbsp;&nbsp; 
				<input  class="artTitle easyui-validatebox"      value="<s:property value="irpLeaveapplyInfo.checker" />" />
        </div>
         <div style="float: left;width: 100%;">&nbsp;</div><br/>
        <div class="newForm"  style="width: 100%;float: none;margin-top: 20px;"> 
        	<div class="txt_lptaskt"  style="width: 8%;float: left;line-height: 30px;text-align: right;">审核进度：</div>
        	&nbsp;&nbsp; 
				<input  class="artTitle easyui-validatebox"      value="<s:property value="irpLeaveapplyInfo.applystatus" />" />
        </div>
         <div style="float: left;width: 100%;">&nbsp;</div><br/>
        <div class="newForm"  style="width: 100%;float: none;margin-top: 20px;"> 
        	<div class="txt_lptaskt"  style="width: 8%;float: left;line-height: 30px;text-align: right;">紧急程度：</div>
        	&nbsp;&nbsp; 
				<input  class="artTitle easyui-validatebox"      value="<s:property value="irpLeaveapplyInfo.emergency" />" />
        </div>
        <s:iterator value="attacheds" >
         <div style="float: left;width: 100%;">&nbsp;</div><br/>
        <div class="newForm"  style="width: 100%;float: none;margin-top: 20px;"> 
        	<div class="txt_lptaskt"  style="width: 8%;float: left;line-height: 30px;text-align: right;">相关附件：</div>
        	&nbsp;&nbsp; 
				<a  href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>"><s:property value="attdesc" /></a>
				&nbsp;&nbsp; 
				<a  href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>">下载</a>
        </div>
        </s:iterator>
		<div style="float: left;width: 100%;">&nbsp;</div><br/>
		</form>

</body>
</html>