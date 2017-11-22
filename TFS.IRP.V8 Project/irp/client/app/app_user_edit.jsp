<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<s:if test="irpApps.size!=0">
      <ul class="cardList" style="width: 960px;">
		<s:iterator value="irpApps">
		<li style="margin-right: 10px;margin-bottom: 30px;margin-left: 20px;margin-top: 0px;width: 290px;">
			<table  width="270" border="0" align="center" cellpadding="10" cellspacing="0" style="width: 290px;BORDER: 1px solid #C1DDF2;height: 200px;">
				<tr>
					<td rowspan="2" width="80px" style="padding-top: 5px; padding-left: 5px;">
					   <a href="javascript:void(0)">
						 <s:if test="iconurl!=null">
					        <img style="width:67px" src="<%=rootPath %>admin/images/<s:property value='iconurl' />" alt="用户图片" />
					     </s:if>
					     <s:else>
				            <img src="<%=rootPath %>admin/images/appicon.jpg" alt="用户图片" width="67px" />
					      </s:else>		
					    </a>
				    </td>
					<td valign="top" style="text-align:center; padding-top:20px;" >
						<font class="linkc12" >名称:
						<s:property value="applistalias"/></font>
					</td>
				</tr>
				<tr>
					<td valign="bottom" style="text-align:center; padding-bottom:20px;">
					<font class="linkc12" >更新时间：<s:date name="lastupdatetime" format="yyyy-MM-dd"/></font>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:left;margin:10px;padding:10px;margin-top:0px;">
						<font class="linkc12" >
					应用描述：<s:property value="description"/>
					</font>
					</td>
				</tr>
				<tr>
					<td colspan="2" bgcolor="#C1DDF2" height="30" align="center">
						<span class="STYLE1">
						    <s:if test="anzhuangorxiezai=='安装'">+<a id="statusa" href="javascript:void(0);" onclick="updatedisplay(<s:property value="applistid"/>)"><s:property value="anzhuangorxiezai"/> </a>+</s:if> 
						    <s:else>-<a id="statusa" href="javascript:void(0);" onclick="updatedisplay(<s:property value="applistid"/>)"><s:property value="anzhuangorxiezai"/> </a>-</s:else>
					   </span>
					</td>
				</tr>
			</table>
			<div class="darkSh"></div>
		 </li>
	    </s:iterator>
    </ul>
    <div class="left">
      <div style="clear:both" ></div>
      <div class="fyh">
     		<ul style="width:900px;"><s:property value="pageHTML" escapeHtml="false" /></ul>
      </div>
    </div>
</s:if>
<s:else>
     暂无安装应用
</s:else>	    
    