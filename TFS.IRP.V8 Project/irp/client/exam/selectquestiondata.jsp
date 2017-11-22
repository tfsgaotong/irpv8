<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.tfs.irp.util.LoginUtil"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<style type="text/css" >
.gone_client_c{
	float: left;
	background-color: #F0F0F0;
	padding: 3px 0 3px 0;
}
.gone_client_c_b{
	float: left;
	overflow: hidden;
	text-overflow:ellipsis;
	white-space: nowrap;
}

</style>
<script type="text/javascript">
$(function(){

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
			}	
			});
	}

});
</script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<ul style="text-align: center;">
	<li  class="gone_client_c" style="width: 60px;" ><a href="javascript:void(0)" onclick="checkAllByPage()" >全选</a></li>
	<li  class="gone_client_c" style="width: 390px;">题干</li>
	<li  class="gone_client_c" style="width: 50px;">题型</li>
	<li  class="gone_client_c" style="width: 50px;">分值</li>
	<li  class="gone_client_c" style="width: 50px;">级别</li>
	<s:iterator value="questionbanklist"  status="questionbankliststatus">
	<li  class="gone_client_c_b" style="width: 60px;"><input onclick="checkBC(this)" name="qbankids" type="checkbox" value="<s:property value='qbankid' />"><s:property value="(pagenum-1)*10+#questionbankliststatus.count"/></li>
	<li  class="gone_client_c_b" style="width: 390px;" title="<s:property value="questiontext" escapeHtml="false" />"><s:property value="questiontext" escapeHtml="false" /></li>
	<li  class="gone_client_c_b" style="width: 50px;"><s:property value="getTypeStrByTypes(qbtype)" /> </li>
	<li  class="gone_client_c_b" style="width: 50px;"><s:property value="qbscore" /></li>
	<li  class="gone_client_c_b" style="width: 50px;"><s:property value="getLevleByStatus(qblevel)" /></li>
	</s:iterator>
</ul>
<div class="main" style="margin-left: -100px;">
	<div class="left">
		<div class="fyh">
				<ul>
				<s:property value="pageHTML" escapeHtml="false" />
				</ul>
		</div>
	</div>
</div>

	
	


