<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
	<s:if test="hisirptermlist.size()>0">
		<ul>
			<li style="border-style: none none solid none;border-width: thin; border-color: #E0E0E0;font-size: 15px;padding: 2px 0 2px 0;line-height: 28px;width: 10%;float:left;" class="linkbh14" >
				
				<a href="javascript:void(0)" onclick="compareVersion()" class="linkbh14" style="border: 1px solid;padding: 2px 4px 2px 4px;">版本对比</a>&nbsp;
			</li>
			<li style="border-style: none none solid none;border-width: thin; border-color: #E0E0E0;font-size: 15px;padding: 2px 0 2px 0;line-height: 28px;width: 15%;float:left;" >
				更新时间
			</li>
			<li style="border-style: none none solid none;border-width: thin; border-color: #E0E0E0;font-size: 15px;padding: 2px 0 2px 0;line-height: 28px;width: 15%;float:left;" >
				版本
			</li>
			<li style="border-style: none none solid none;border-width: thin; border-color: #E0E0E0;font-size: 15px;padding: 2px 0 2px 0;line-height: 28px;width: 15%;float:left;" >
				贡献者
			</li>
			<li style="border-style: none none solid none;border-width: thin; border-color: #E0E0E0;font-size: 15px;padding: 2px 0 2px 0;line-height: 28px;width: 10%;float:left;" >
				状态
			</li>
			
			<li style="border-style: none none solid none;border-width: thin; border-color: #E0E0E0;font-size: 15px;padding: 2px 0 2px 0;line-height: 28px;width: 35%;float:left;" >
				修改原因
			</li>	
		</ul>
		<s:iterator value="hisirptermlist" >
		<ul>
			<li style="border-style: none none solid none;border-width: thin; border-color: #E0E0E0;font-size: 14px;padding: 2px 0 2px 0;line-height: 28px;width: 10%;float:left;" >
				<input type="checkbox" onclick="choiceCK(<s:property value="termid" />)" id="termck<s:property value="termid" />">&nbsp;
			</li>
			<li style="border-style: none none solid none;border-width: thin; border-color: #E0E0E0;font-size: 14px;padding: 2px 0 2px 0;line-height: 28px;width: 15%;float:left;overflow: hidden; text-overflow: ellipsis; white-space: nowrap;" >
				<s:date name="crtime" format="yyyy-MM-dd HH:mm" />
			</li>
			<li style="border-style: none none solid none;border-width: thin; border-color: #E0E0E0;font-size: 14px;padding: 2px 0 2px 0;line-height: 28px;width: 15%;float:left;overflow: hidden; text-overflow: ellipsis; white-space: nowrap;" title="<s:property value="termversion" />(查看)">
				
				<a class="linkb14" style="font-size: 14px;" href="javascript:void(0)" onclick="findVerFL(<s:property value="termid" />)"><s:property value="termversion" />(查看)</a>
			
			</li>
			<li style="border-style: none none solid none;border-width: thin; border-color: #E0E0E0;font-size: 14px;padding: 2px 0 2px 0;line-height: 28px;width: 15%;float:left;overflow: hidden; text-overflow: ellipsis; white-space: nowrap;" title="<s:property value="getShowPageViewNameByUserId(cruserid)" />">
				<a class="linkb14" style="font-size: 14px;" href="<%=rootPath %>site/client_to_index_person.action?personid=<s:property value="cruserid" />" target="_blank">
					<s:property value="getShowPageViewNameByUserId(cruserid)" />
				</a>
			</li>
			<li style="border-style: none none solid none;border-width: thin; border-color: #E0E0E0;font-size: 14px;padding: 2px 0 2px 0;line-height: 28px;width: 10%;float:left;overflow: hidden; text-overflow: ellipsis; white-space: nowrap;" title="" >
				<s:if test="termstatus==10">
					通过
				</s:if>
				<s:elseif test="termstatus==20">
					未审核
				</s:elseif>
				<s:else>
					非法
				</s:else>
			</li>
			<li style="border-style: none none solid none;border-width: thin; border-color: #E0E0E0;font-size: 14px;padding: 2px 0 2px 0;line-height: 28px;width: 35%;float:left;overflow: hidden; text-overflow: ellipsis; white-space: nowrap;" title="<s:property value="eidtcause" />" >
				<s:property value="eidtcause" />&nbsp;
			</li>
		</ul>	
		</s:iterator>
		<div class="left">
		<div style="margin:0 0 0 0;" class="fyh">
			<ul style="text-align: left;" >  
				<s:property value="pagehtmlver" escapeHtml="false" />
			</ul>
		</div>
		</div>
	</s:if>
	<s:else>
	没找到版本
	</s:else>