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
//全局变量，

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
*分页
*/
function page(_nPageNum){
	$('#pagenumabc').val(_nPageNum);
	var cateid = '<s:property value="cateid" />';
	if(cateid==""){
		cateid = 0;
	}
	$('#cateid').val(cateid);
	var queryString = $('#pageFormabc').serialize();
	
	jump("<%=rootPath%>exam/qbanklistquote.action?"+queryString);
} 
/**
*排序
*/
function orderBy(_sFiled,_sOrderBy){
	$('#orderField').val(_sFiled);
	$('#orderBy').val(_sOrderBy);
	var cateid = '<s:property value="cateid" />';
	if(cateid==""){
		cateid = 0;
	}
	$('#cateid').val(cateid);
	var queryString = $('#pageFormabc').serialize();
	jump("<%=rootPath%>exam/qbanklistquote.action?"+queryString);
}	
$(function(){
	var checkstr = $("#testpapercontents").val();
	if(checkstr!=""){
		var ckstr = checkstr.split(",");
			$("input[name='qbankids']").each(function(){
			var ids = 0;
				for(var i in ckstr){
					if(this.value==ckstr[i]){
					ids = 1;
					}
			
				}
			if(ids==1){
				this.checked = true;
				checkBC(this);
			}	
			});
	}

$('#searchinputqbanks').searchbox({
		width:220,
	    searcher:function(value,name){   
	    	$('#pageFormabc').find('input[name="searchWord"]').val(value);
	    	$('#pageFormabc').find('input[name="searchType"]').val(name);
	    	$('#pageFormabc').find('input[name="pagenum"]').val('1');
	    	$('#pageFormabc').find('input[name="orderField"]').val('');
	    	$('#pageFormabc').find('input[name="orderBy"]').val('');
	    	var cateid =  '<s:property value="cateid" />';
	    	if(cateid==""){
				cateid = 0;
			}
	    	$('#pageFormabc').find('input[name="cateid"]').val(cateid);
	    	var queryString = $('#pageFormabc').serialize();
	    	jump("<%=rootPath%>exam/qbanklistquote.action?"+queryString);
	    },   
	    menu:'#listquoteqbankText',   
	    prompt:'请输入检索词'  
	});
});
</script>
<form id="pageFormabc">
	<s:hidden name="searchWord" id="searchWord" />
	<s:hidden name="searchType" id="searchType" />
	<s:hidden name="pagenum" id="pagenumabc" />
	<s:hidden name="cateid" id="cateid" />
	<s:hidden name="orderField" id="orderField" />
	<s:hidden name="orderBy" id="orderBy" />
</form>
<table width="100%" id="flowList" border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
	<tr class="list_th" style="position: relative;">
   	  	<td colspan="1" align="left" style="padding: 5px 0 5px 10px;" nowrap="nowrap">
			
			
			
   	  	</td>   
   	  	<td colspan="5" align="right" style="padding: 5px 0 5px 10px;" nowrap="nowrap">
   	  		<input name="searchinputqbanks" id="searchinputqbanks"  />
				<div id="listquoteqbankText">  
				    <div data-options="name:'all'">全部&nbsp;&nbsp;&nbsp;&nbsp;</div>
				    <div data-options="name:'qtext'">题干</div>
				    <div data-options="name:'qexplain'">解析</div>
				</div>
			
   	  	</td>   	  	
	</tr>

    <tr>

	    <td width="10%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="checkAll()">全选</a></td>
	    <td width="45%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('questiontext','<s:if test="orderField=='questiontext'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">题干<s:if test="orderField=='questiontext'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='questiontext'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="10%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('qbtype','<s:if test="orderField=='qbtype'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">题型<s:if test="orderField=='qbtype'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='qbtype'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="10%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('qbscore','<s:if test="orderField=='qbscore'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">分值<s:if test="orderField=='qbscore'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='qbscore'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="10%" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('qblevel','<s:if test="orderField=='qblevel'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">级别<s:if test="orderField=='qblevel'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='qblevel'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="15%" align="center" bgcolor="#F5FAFE"  class="main_bright">操作</td>
	    
    </tr>
    <s:iterator value="questionbanklist" status="questionbankliststatus" >
    <tr>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><input onclick="checkBC(this)" name="qbankids" type="checkbox" value="<s:property value='qbankid' />">&nbsp;<s:property value="(pagenum-1)*10+#questionbankliststatus.count"/></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:property value="questiontext" escapeHtml="false" /></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:property value="getTypeStrByTypes(qbtype)" /></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:property value="qbscore" /></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:property value="getLevleByStatus(qblevel)" /></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright">
	    	<img border="0" src="images/icons/pencil.png" title="修改" style="cursor:pointer; margin: 0 5px;" onclick="updateEdit(<s:property value="qbankid" />)"  />
        	<img border="0" src="images/icons/cancel.png" title="删除" style="cursor:pointer; margin: 0 3px;" onclick="deleteEdit(<s:property value="qbankid" />)" />
	    </td>

    </tr>
   </s:iterator>
    <tr bgcolor="#FFFFFF">
		<td colspan="6" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
	</tr>
</table>
</body>
</html>
