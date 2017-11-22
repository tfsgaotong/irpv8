<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.tfs.irp.util.LoginUtil"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 


<script type="text/javascript"> 
var setting = {
			view: {
				selectedMulti: false
			},
			data: {
				key: {
					title:"t"
				},
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeClick: beforeClick
			
			}
		};
		var zNodes = <s:property value="grouplistjson" escapeHtml="false" />;
		
$(function(){

$.fn.zTree.init($("#grouptree"), setting, zNodes);

});


</script>
<div>
	<ul id="grouptree" class="ztree" >

	</ul>
</div>
