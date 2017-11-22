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
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />
<title>应用中心</title>
<body>
</head>
<script type="text/javascript">
function jump(_sUrl){
	$('body').layout('panel','center').panel('refresh',_sUrl);
}
/*
 * 全局设置
 */
function opertypeConf(_ctypeId,_cname){
  //意见反馈
   jump('<%=rootPath  %>menu/jump_to.action?appid='+_ctypeId+'&appname='+_cname);
}
$(function(){
	$('#appmenu').accordion({
		onSelect:function(title,index){
			var p = $('#appmenu').accordion('getSelected');
            var typeid=p.attr("id");
            $.getJSON("<%=rootPath  %>menu/select_allApp.action",{'typedesc':title},function(data){
            	var app="";
            	$.each(data,function(i,a){
            		app+="<li><a href=\"javascript:void(0)\" id="+a.applistid+" onclick=\"opertypeConf("+a.applistid+",'"+a.applistname+"')\" >"+a.applistalias+"</a></li>";
            	});
            	$("#"+typeid+" ul").html(app);
            });
		}
	});
}); 
</script>
<div id="appmenu" class="easyui-accordion" fit="true" border="false">
    <s:iterator value="irpApptypes">
        <div id='apptypediv<s:property value="appypeid"/>' title='<s:property value="appname"/>' selected="true" style="padding:0px;" class="arrowsidemenu">
            <ul class="menucontents" id="appul">
            </ul>
        </div>
    </s:iterator>
	
</div>
</body>
</html>