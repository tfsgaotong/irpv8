<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
   String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<script type="text/javascript">
/**
 * 分页
 */
function pageNavigain(_nPageNum){
		$('#pageNum').val(_nPageNum);
		var queryString = $('#pageFormofnavigian').serialize();
		$.ajax({
			type:"post",
			url:"<%=rootPath%>user/editpersonalnavigation.action?"+queryString,
			async:false,
			cache:false,
			success:function(html){
				if(html!=null){
					$('#navigaineditdiv').html(html);
				}
			}
		});	
}

</script>
<form id="pageFormofnavigian">
	<s:hidden name="pageNum" id="pageNum" />
	<s:hidden name="pageSize" id="pageSize" value="10" />
</form>
<div id="navigaineditdiv" >
	<div style="width: 300px;height: 360px;background-color: rgb(245, 245, 245);">
		<s:iterator value="irpUserNavigationlist">
			<div style="width: 250px;height: 30px;background-color: rgb(250, 250, 250);margin-top: 5px;margin-left: 20px;">
				<div style="width: 200px;height: 20px;float: left;overflow: hidden;text-overflow:ellipsis;white-space: nowrap;" title="<s:property value="navigationname" />">
					<s:property value="navigationname" />
				</div>
				<div style="width: 50px;height: 30px;float: left;">
					<a style="float: right;margin-left: 10px;" title="删除" href="javascript:void(0)" onclick="deleteUserNavigation(<s:property value="navigationid" />)"><img style="width: 15px;" src="<%=rootPath%>client/images/icons/cancel.png"  ></a>
					<a style="float: right;margin-left: 10px;" title="修改" href="javascript:void(0)" onclick="updateUserNavigation(<s:property value="navigationid" />)" ><img style="width: 15px;"  src="<%=rootPath%>client/images/icons/pencil.png"  ></a>
				</div>
			</div>
		</s:iterator>
	</div>
	<div class="main" style="width: 300px;">
		<div class="left" style="width: 300px;">
			<div class="fyh" style="width: 300px;">
				<ul style="width: 300px;">
					<s:property value="pageHTML" escapeHtml="false" />
				</ul>
			</div>
		</div>
	</div>
</div>