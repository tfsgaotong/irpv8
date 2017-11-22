<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<table id="importUserList" width="600" border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
<s:if test="irpChannels==null || irpChannels.size()==0">未找到相关信息</s:if>
<s:else>
    <tr>
	    <td width="70" align="center" bgcolor="#F5FAFE" class="main_bright">序号</td>
	    <td width="200" align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('chnlname','<s:if test="orderField=='chnlname'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">专题名称<s:if test="orderField=='chnlname'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='chnlname'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="200" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('chnldesc','<s:if test="orderField=='chnldesc'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">专题描述<s:if test="orderField=='chnldesc'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='chnldesc'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
	    <td width="130" align="center" bgcolor="#F5FAFE"  class="main_bright"><a href="javascript:void(0)" onclick="orderBy('crtime','<s:if test="orderField=='crtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">创建时间<s:if test="orderField=='crtime'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='crtime'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
    </tr>
    <s:iterator var="test" value="irpChannels" status="listStat">
    <tr>
	    <td align="center" bgcolor="#F5FAFE"  class="main_bleft">
		    <input
		    <s:if test="selectstatus==1">checked="checked"</s:if>
		     type="checkbox" id="sub<s:property value="channelid" />" name="selectedsub" value="<s:property value="channelid" />" />
		    <label for="<s:property value="channelid" />"><s:property value="(pageNum-1)*pageSize+#listStat.count"/></label>
		</td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="chnlname" /></td>
	    <td bgcolor="#F5FAFE"  class="main_bright"><s:property value="chnldesc" /></td>
	    <td bgcolor="#F5FAFE" align="center" class="main_bright"><s:date name="crtime" format="yyyy-MM-dd HH:mm" /></td>
    </tr>
    </s:iterator>
    <tr bgcolor="#FFFFFF">
		<td colspan="4" align="right"><div class="clientpage">
			<ul>
				<s:property value="pageHTML" escapeHtml="false" />
			</ul>
		</div></td>
	</tr>
</s:else>
</table>
