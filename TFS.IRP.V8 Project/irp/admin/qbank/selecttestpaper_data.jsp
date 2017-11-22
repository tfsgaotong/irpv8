<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>流程管理</title>
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />
</head>

<body>
<script type="text/javascript">
function jump(_sUrl){
	$('#quoteaddqbankcontent').panel('refresh',_sUrl);
}
/**
* 刷新页面
*/	
function refreshPage(){
	$('#quoteaddqbankcontent').panel('refresh');
}	
/**
*反显 
*/
function defaultSel(){
	var selvalues = $("#selcontentvalues").val();
	if(selvalues!=""){
		$("input[name='selcheckpid']").each(function(){
			if(this.value==selvalues){
				this.checked=true;
			}
		});
	}
}


$(function(){
defaultSel();
$('#searchinputs').searchbox({
		width:220,
	    searcher:function(value,name){   
	    	$('#pageFormss').find('input[name="searchWord"]').val(value);
	    	$('#pageFormss').find('input[name="searchType"]').val(name);
	    	$('#pageFormss').find('input[name="pageadminnum"]').val('1');
	    	$('#pageFormss').find('input[name="orderField"]').val('');
	    	$('#pageFormss').find('input[name="orderBy"]').val('');
	    	var cateid =  '<s:property value="cateid" />';
	    	if(cateid==""){
				cateid = 0;
			}
	    	$('#pageFormss').find('input[name="cateid"]').val(cateid);
	    	var queryString = $('#pageFormss').serialize();
	    	jump("<%=rootPath%>exam/seldatabylinktestpaper.action?"+queryString);
	    },   
	    menu:'#listSearchTexts',   
	    prompt:'请输入检索词'  
	});

});
	
function pagePaper(_nums){
	var cateid = '<s:property value="cateid" />';
	if(cateid==""){
		cateid = 0;
	}
	$('#cateid').val(cateid);
	$("#pageadminnum").val(_nums);
	var queryString = $('#pageFormss').serialize();
	jump("<%=rootPath%>exam/seldatabylinktestpaper.action?"+queryString);
}
/**
*排序
*/
function orderBysel(_sFiled,_sOrderBy){
	$('#orderFields').val(_sFiled);
	$('#orderBys').val(_sOrderBy);
	var cateid = '<s:property value="cateid" />';
	if(cateid==""){
		cateid = 0;
	}
	$('#cateid').val(cateid);
	var queryString = $('#pageFormss').serialize();
	
	jump("<%=rootPath%>exam/seldatabylinktestpaper.action?"+queryString);
}
</script>
<form id="pageFormss">
	<s:hidden name="searchWord" id="searchWord" />
	<s:hidden name="searchType" id="searchType" />
	<s:hidden name="pageadminnum" id="pageadminnum" />
	<s:hidden name="cateid" id="cateid" />
	<s:hidden name="orderField" id="orderFields" />
	<s:hidden name="orderBy" id="orderBys" />
</form>
<table width="100%" id="flowList" border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
	<tr class="list_th" style="position: relative;">
   	  	<td colspan="1" align="left" style="padding: 5px 0 5px 10px;" nowrap="nowrap">
			
			
			
   	  	</td>   
   	  	<td colspan="5" align="right" style="padding: 5px 0 5px 10px;" nowrap="nowrap">
   	  			<input name="searchinput" id="searchinputs"  />
				<div id="listSearchTexts">  
				    <div data-options="name:'title'">标题&nbsp;&nbsp;&nbsp;&nbsp;</div>
				</div>
			
   	  	</td>   	  	
	</tr>

    <tr>

	    <td width="10%" align="center" bgcolor="#F5FAFE"  class="main_bright">选择</td>
	    <td width="50%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBysel('papertitle','<s:if test="orderField=='papertitle'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">标题<s:if test="orderField=='papertitle'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='papertitle'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="10%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBysel('paperstatus','<s:if test="orderField=='paperstatus'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">状态<s:if test="orderField=='paperstatus'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='paperstatus'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="10%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBysel('extendsone','<s:if test="orderField=='extendsone'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">组卷方式<s:if test="orderField=='extendsone'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='extendsone'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="10%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBysel('papertime','<s:if test="orderField=='papertime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">总分<s:if test="orderField=='papertime'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='papertime'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="10%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBysel('papercontent','<s:if test="orderField=='papercontent'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">试题数量<s:if test="orderField=='papercontent'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='papercontent'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>


	    
    </tr>
    <s:iterator value="irpTestpaperlist" status="irpTestpaperliststatus" >
    <tr>
    
    
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><input name="selcheckpid" _score="<s:property value="papertime" />" _vals="<s:property value="papertitle" />" type="radio" value="<s:property value='paperid' />">&nbsp;<s:property value="(pagenumpaper-1)*10+#irpTestpaperliststatus.count"/></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:property value="papertitle" /></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright">
	    	<s:if test="paperstatus==@com.tfs.irp.testpaper.entity.IrpTestpaper@NOFABU">
	    	未发布
	    	</s:if>
	    	<s:elseif test="paperstatus==@com.tfs.irp.testpaper.entity.IrpTestpaper@YESFABU">
	    	已发布
	    	</s:elseif>
		</td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:if test="extendsone==@com.tfs.irp.testpaper.entity.IrpTestpaper@SELECTQUESTION">选择组卷</s:if><s:elseif test="extendsone==@com.tfs.irp.testpaper.entity.IrpTestpaper@RADOMQUESTION">随机组卷</s:elseif> </td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:property value="papertime" /></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:property value="getNumBySContent(papercontent)" /></td>
	    

    </tr>
   </s:iterator>
    <tr bgcolor="#FFFFFF">
		<td colspan="6" align="right" class="page"><s:property value="paperpagehtml" escapeHtml="false" /></td>
	</tr>
</table>
</body>
</html>
