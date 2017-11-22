<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s" %>    
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<s:if test="documentlist.size()>0">
<s:iterator value="documentlist">
<dl>
	<dt>
	<s:if test="getIrpUserByUsername(CRUSERID).userpic!=null">
		<a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='CRUSERID.toString().substring(1,CRUSERID.toString().length()-1)' /> " >
			<img src="<%=rootPath%>file/readfile.action?fileName=<s:property value='getIrpUserByUsername(CRUSERID).userpic' />" alt="用户头像" width="55px" />
		</a>						
	</s:if>
	<s:else>			
		<a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='CRUSERID.toString().substring(1,CRUSERID.toString().length()-1)' /> " >
			<img <s:if test="getIrpUserByUsername(CRUSERID).sex==2">src="<%=rootPath%>client/images/female.jpg"</s:if><s:else>src="<%=rootPath%>client/images/male.jpg"</s:else> alt="用户头像" width="55px" />
		</a>
	</s:else>
	</dt>
	<dd>
		<a href="javascript:void(0)" onclick="documentinfo_see(<s:property value="DOCID" />)" class="linkb14"><s:property value="DOCTITLE.toString().substring(1,DOCTITLE.toString().length()-1)" escapeHtml="false" /></a>
		.
		<s:property value="getIrpUserByUsername(CRUSERID).username" />
		.
		<s:date name="CRTIME" format="yyyy-MM-dd" /><br/>
		标签:&nbsp;
		<s:set var="start" value="@com.tfs.irp.util.SysConfigUtil@getSysConfigValue(@com.tfs.irp.solr.entity.IrpSolr@STAYLE_START)" />
		<s:set var="end" value="@com.tfs.irp.util.SysConfigUtil@getSysConfigValue(@com.tfs.irp.solr.entity.IrpSolr@STAYLE_END)" />
		<s:iterator value="DOCKEYWORDS.toString().substring(1,DOCKEYWORDS.toString().length()-1).split(',')"  status="st" var="as">
		<a href="javascript:void:(0)" onclick="searchInfo1('<s:property value="#as.replaceAll(#start,'').replaceAll(#end,'')" />')" class="linkc12"><s:property value="#as" escapeHtml="false" /></a>&nbsp;
		</s:iterator>
		<span>
			<s:property value="DOCCONTENT.toString().substring(1,DOCCONTENT.toString().length()-1)" escapeHtml="false" />
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