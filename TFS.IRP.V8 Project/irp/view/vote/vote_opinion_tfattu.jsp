<%@page import="com.tfs.irp.util.LoginUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<s:if test="irpMicroblogs.size>0">
<s:iterator value="irpMicroblogs">
<div class="main_con clearfix">
	<div class="face">
		<dl id="<s:property value='microblogid'/>div">
			<s:if test="findUserByUserId(userid).userpic!=null">
		       	<dt>
			       	<a target="_bank"	href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' /> " >
			     	  	<img src="<%=rootPath%>file/readfile.action?fileName=<s:property value='findUserByUserId(userid).userpic' />"  alt="用户头像" width="48px" onmouseover="personalCard(<s:property value='userid' />,<s:property value='microblogid' />)" onmouseout="personalCardOut(<s:property value='microblogid' />)"   /> 
			       	</a>
		       	</dt>
		    </s:if>
		    <s:if test="findUserByUserId(userid).userpic==null">
		       	<dt>
		       	<a target="_bank"	href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' /> " >
		       	<img <s:if test="findUserByUserId(userid).sex==2">src="<%=rootPath%>view/images/female.jpg"</s:if><s:else>src="<%=rootPath%>view/images/male.jpg"</s:else> alt="用户头像" width="48px" onmouseover="personalCard(<s:property value='userid' />,<s:property value='microblogid' />)" onmouseout="personalCardOut(<s:property value='microblogid' />)"  />
		       	</a>
		       	 </dt>
		    </s:if>
		</dl>
	</div>
	<div class="content">
		<p class="con_txt">
			<s:property value="getShowPageViewNameByUserId(userid)"/>
			：
			<em>
				<s:property value="microblogcontent" escapeHtml="false"/>
			</em>
		</p>
		<div class="con_opt clearfix">
		<p class="fl W_linkb">
			<span class="time W_textc">
				<s:date name="crtime" format="yyyy-MM-dd HH:mm"/>
			</span>
		</p>
		</div>
	</div>
</div>
</s:iterator> 
</s:if>

</html>