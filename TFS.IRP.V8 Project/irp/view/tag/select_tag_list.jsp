<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>选择标签</title>
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/oacss.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/icon.css" />
<link id="skin" rel="stylesheet" type="text/css" />
</head> 
<body>
<script type="text/javascript">
	//全局变量，
	var m_checked = false;  
	
	//全选
	function checkAll(){
		m_checked = !m_checked;
		var checks = $('#importTagList').find('input[name="tagid"]');
		for(var i=0;i<checks.length;i++){
			if(checks[i] && checks[i].checked!=m_checked){
				checks[i].checked = m_checked;
				checkClick(checks[i]);
			}
		}
	}
	
	function jump(_form){ 
		var queryString = _form.serialize(); 
		var result = $.ajax({
			type: 'POST',
			url: '<%=rootPath %>tag/select_tag.action',
			data:queryString,
		    async: false,
		    cache: false
		}).responseText;
		lhbDialog.get('selectTag',1).content(result);  
	}
	
	//分页
	function page(_nPageNum){ 
		$('#dialogPageForm').find('input[name="pageNum"]').val(_nPageNum);
	    jump($('#dialogPageForm'));
	}
	//排序
	function orderBy(_sFiled,_sOrderBy){ 
		$('#dialogPageForm').find('input[name="orderField"]').val(_sFiled);
		$('#dialogPageForm').find('input[name="orderBy"]').val(_sOrderBy);
	    jump($('#dialogPageForm'));
	}
	  
	$(function(){
		$('#sWord').searchbox({
			width:240,
		    menu:'#sType',
		    prompt:'请输入检索词',
		    searcher:function(value,name){
		    	$('#dialogPageForm').find('input[name="tagName"]').val(value);
		    	$('#dialogPageForm').find('input[name="pageNum"]').val('1');
		    	$('#dialogPageForm').find('input[name="orderField"]').val('');
		    	$('#dialogPageForm').find('input[name="orderBy"]').val('');
		        jump($('#dialogPageForm'));
		    } 
		}); 
	});
	
	//记录选择标签
	function checkClick(checkBox){
		var sTagIds = $('#dialogPageForm').find('input[name="tagIds"]').val();
		var nVal = checkBox.value;
		var arr;
		if(sTagIds){
			arr = sTagIds.split(',');
		}else{
			arr = new Array();
		}
		var nIndex = $.inArray(nVal, arr);
		if(checkBox.checked){
			arr.push(nVal);
		}else{
			arr.splice(nIndex, 1);
		}
		$('#dialogPageForm').find('input[name="tagIds"]').val(arr.join(','));
	}
</script>
<form id="dialogPageForm">
	<s:hidden name="typeId" id="typeId" />
	<s:hidden name="tagIds" id="tagIds" />
	<s:hidden name="pageNum" id="pageNum" />
	<s:hidden name="pageSize" id="pageSize" />
	<s:hidden name="orderField" id="orderField" />
	<s:hidden name="orderBy" id="orderBy" />
	<s:hidden name="tagName" id="tagName" />
</form>
<div style="padding:5px; text-align: center;">
<div style="padding:0px 10px 5px 0px; float: right;">
<input name="sWord" id="sWord" />
<div id="sType">  
    <div data-options="name:'tagname'">标签名称&nbsp;&nbsp;</div>
</div>
</div>
<table id="importTagList" width="600" border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
    <tr>
	    <td width="70" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="checkAll()">全选</a></td>
	    <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('tagname','<s:if test="orderField=='tagname'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">标签名称<s:if test="orderField=='tagname'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='tagname'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="70" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('ncount','<s:if test="orderField=='ncount'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">点击量<s:if test="orderField=='ncount'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='ncount'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
    </tr>
    <s:set var="arr" value='tagIds.split(",")' />
    <s:iterator value="irpTags" status="listStat">
    <tr>
	    <td align="center" bgcolor="#F5FAFE"  class="main_bleft"><input type="checkbox" id="<s:property value="tagid" />" <s:if test='@org.apache.commons.lang.ArrayUtils@contains(#arr,""+tagid)'>checked="true"</s:if> onchange="checkClick(this)" name="tagid" value="<s:property value="tagid" />" /> <label for="<s:property value="tagid" />"><s:property value="(pageNum-1)*pageSize+#listStat.count"/></label></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="tagname" /></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="ncount" /></td>
    </tr>
    </s:iterator>
    <tr bgcolor="#FFFFFF">
		<td colspan="4" align="right"><div class="clientpage">
			<ul>
				<s:property value="pageHTML" escapeHtml="false" />
			</ul>
		</div></td>
	</tr>
</table>
</div>
</body>
</html>
