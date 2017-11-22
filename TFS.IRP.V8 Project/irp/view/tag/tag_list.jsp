<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<script type="text/javascript">
<!--
function removeTag(_tagId){
	$.ajax({
		url: "<%=rootPath %>tag/remove_tag_dowith.action",
	   	type: "GET",
	   	data: {
	   		tagId: _tagId
	   	},
	   	success: function(msg){
	   		if(msg=="1"){
				$.dialog.tips('删除成功',1,'32X32/succ.png',function(){
					showTagList(<s:property value="typeId" />);
				});
			}else{
				$.dialog.tips('删除失败',1,'32X32/fail.png');
			}
	   	}
	});
}
//-->
</script>
<s:if test="irpTags!=null && irpTags.size()>0">
	<ul class="list6">
		<s:iterator value="irpTags">
			<li style="width:auto;padding:0;background:none;margin-bottom:20px; float:left; display:inline; margin-right:20px; height:28px; overflow:hidden;">
				<a href="#" target="_blank"><s:property value="tagname" /></a><span></span><aside title="移除标签" onclick="removeTag(<s:property value="tagid" />)">X</aside>
			</li>
		</s:iterator>
	</ul>
</s:if>
<s:else>
<div style="padding: 5px 10px;">当前标签分类中暂无标签</div>
</s:else>