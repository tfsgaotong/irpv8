<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<s:iterator value="jubaodocuments" var="map" status="st">  
<div id="informdiv<s:property value='#map["INFORMID"]'/>">
	<div style="margin-left: 20px;">
		 <div>
	        <div style="width: 450px;float: left;" ><font style="color: black;font-size: 14px;">举报人：</font><font size="2"><a target="_blank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='#map["USERID"]'/> " class="linkb14"><s:property value="@com.tfs.irp.projectperson.web.IrpProjectPersonAction@findUserNameByUserId(#map['USERID'])"/></a></font></div>
	        <div style="float: left;width: 200px;"><font  style="color: black;font-size: 14px;">举报时间：</font><font size="2"><s:date name="#map['JUBAOTIME']" format="yyyy-MM-dd HH:mm" /></font></div>
		</div>
		  <div style="float: left;width: 650px;"><font style="color: black;font-size: 14px;">举报内容：</font><font size="2"><s:property value="#map['INFORMCONTENT']" />&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="#map['INFORMVALUE']" />
			  【 &nbsp;<s:property value="@com.tfs.irp.site.web.SiteAction@findSiteTypeBySiteId(#map['SITEID'])" />&nbsp;】
			  </font>
			  </div>
			  <div style="float: left;width: 650px;text-align: right;">
		         <label style="margin-left:200px;">
					<a href="javascript:void(0)" class="linkb14" onclick="notpassinformdocument(<s:property value='#map["DOCID"]'/>)">恢复</a>&nbsp;
				</label> 
			  </div>
	 	<div>
	</div>
<div>
	<dl id="<s:property value='#map["DOCID"]'/>div"
		onmouseout="microblogdeletejudgeOut(<s:property value='#map["DOCID"]'/>)"
		onmouseover="microblogdeletejudge(<s:property value='#map["DOCID"]'/>)" >
			<dt>
			<a	href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='#map["CRUSERID"]'/>" target="_blank">
				<img id="microblogPersonalCard<s:property value='#map["DOCID"]'/>"
				src="<s:property value='@com.tfs.irp.document.web.DocumentAction@userPic(#map["CRUSERID"])'/>"
				alt="用户头像" width="55px"
				onmouseover="personalCard(<s:property value='#map["DOCID"]'/>,<s:property value='#map["CRUSERID"]'/>)"
				onmouseout="personalCardOut(<s:property value='#map["DOCID"]'/>)" />
		   </a>			
		   </dt> 
		<dd>
			<a href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='#map["CRUSERID"]'/>" class="linkb14" target="_blank">
			  <s:property value="@com.tfs.irp.projectperson.web.IrpProjectPersonAction@findUserNameByUserId(#map['CRUSERID'])"/>
			</a>:
				<a href="javascript:void(0);" onclick="documentinfo_see(<s:property value='#map["DOCID"]'/>)">
					<s:property value='#map["DOCTITLE"]' escapeHtml="false" />
				</a>
		</dd>
		<dd style="margin-left: 12px; "></dd>
		<dd style="padding-bottom: 5px;">
			<span style="margin-left: 10px;"> <s:date name="CRTIME"
					format="yyyy-MM-dd HH:mm" /> 
			 </span>
		</dd>
	</dl>
</div>
</div>	
</div>
</s:iterator> 
<ul>
	<s:property value="pageHTML" escapeHtml="false" />
</ul>

