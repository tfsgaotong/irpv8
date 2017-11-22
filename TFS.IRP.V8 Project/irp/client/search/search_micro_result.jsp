<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>    
<s:if test="microbloglist.size()>0">
<s:iterator value="microbloglist">
<dl>
	<dt>
	<s:if test="getIrpUserByUsername(USERID).userpic!=null">
		<a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID.toString().substring(1,USERID.toString().length()-1)' /> " >
			<img src="<%=rootPath%>file/readfile.action?fileName=<s:property value='getIrpUserByUsername(USERID).userpic' />" alt="用户头像" width="55px" />
		</a>						
	</s:if>
	<s:else>			
		<a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID.toString().substring(1,USERID.toString().length()-1)' /> " >
			<img <s:if test="getIrpUserByUsername(USERID).sex==2">src="<%=rootPath%>client/images/female.jpg"</s:if><s:else>src="<%=rootPath%>client/images/male.jpg"</s:else> alt="用户头像" width="55px" />
		</a>
	</s:else>
	</dt>
	<dd>
		<a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID.toString().substring(1,USERID.toString().length()-1)' />" class="linkb14">
			<s:property value="getIrpUserByUsername(USERID).username" />
		</a>：
		<s:property value="getMicroblogContent(MICROBLOGCONTENT.toString().substring(1,MICROBLOGCONTENT.toString().length()-1))" escapeHtml="false" />
	 	<span>
	 		<s:date name="CRTIME" format="yyyy-MM-dd" />&nbsp;来自<s:property value="FROMDATA.toString().substring(1,FROMDATA.toString().length()-1)" />
			<p>&nbsp;</p>
	 	</span>
	</dd>
</dl>
</s:iterator>
<ul>
 <s:property value="pageHTML" escapeHtml="false" />
</ul>
</s:if>
<s:else>
<span>没有找到相关记录</span>
</s:else>
